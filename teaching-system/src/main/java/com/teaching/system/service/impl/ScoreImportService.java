package com.teaching.system.service.impl;

import com.teaching.common.core.domain.entity.SysUser;
import com.teaching.common.exception.UtilException;
import com.teaching.common.utils.DateUtils;
import com.teaching.system.domain.BusCourse;
import com.teaching.system.domain.BusScore;
import com.teaching.system.domain.dto.ScoreImportParam;
import com.teaching.system.service.IBusCourseService;
import com.teaching.system.service.IBusScoreService;
import com.teaching.system.service.ISysUserService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ScoreImportService {

    private static final Logger log = LoggerFactory.getLogger(ScoreImportService.class);

    @Autowired
    private IBusScoreService busScoreService;
    @Autowired
    private ISysUserService userService;
    @Autowired
    private IBusCourseService busCourseService;

    private static final int COURSE_START = 1; // 课程名称在第2行
    private static final int STUDENT_START = 2; // 学生从第3行开始
    private static final int SCORE_START = 2; // 成绩从第3列开始
    private static final int STUDENT_NAME = 1; // 学生姓名在第2列

    public List<BusScore> processExcell(ScoreImportParam scoreImportParam, InputStream is) throws IOException {
        List<BusScore> result = new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(is);
        Date now = DateUtils.getNowDate();
        try {
            BusCourse busCourse = new BusCourse();
            busCourse.setStatus("0");
            Map<String, List<BusCourse>> courseMap = busCourseService.selectBusCourseList(new BusCourse()).stream().collect(Collectors.groupingBy(BusCourse::getCourseName));
            for (int sheetIdx = 0; sheetIdx < workbook.getNumberOfSheets(); sheetIdx++) {
                List<String> subjects = new ArrayList<>();
                Sheet sheet = workbook.getSheetAt(sheetIdx); // 获取第一个sheet
                // 读取表头（科目列）
                Row headerRow = sheet.getRow(COURSE_START);
                for (int col = SCORE_START; col < headerRow.getPhysicalNumberOfCells(); col++) {
                    Cell cell = headerRow.getCell(col);
                    subjects.add(cell.getStringCellValue());
                }
                for (String subject : subjects) {
                    if (!courseMap.containsKey(subject)) {
                        log.error("课程不存在 {}", subject);
                    }
                }
                // 读取每个学生的数据
                for (int rowIdx = STUDENT_START; rowIdx <= sheet.getLastRowNum(); rowIdx++) {
                    Row row = sheet.getRow(rowIdx);
                    String studentName = row.getCell(STUDENT_NAME).getStringCellValue();
                    // TODO 姓名重复，需要提供学号
                    SysUser sysUser = userService.selectUserByNickName(studentName);
                    if (Objects.isNull(sysUser)) {
                        log.error("学生不存在 {}", studentName);
                    } else {
                        for (int colIdx = SCORE_START; colIdx < row.getPhysicalNumberOfCells(); colIdx++) {
                            String subject = subjects.get(colIdx - SCORE_START);
                            if (courseMap.containsKey(subject)) {
                                Double score = row.getCell(colIdx).getNumericCellValue();
                                BusScore busScore = new BusScore();
                                busScore.setTestId(scoreImportParam.getTestId());
                                busScore.setStudentId(sysUser.getUserId());
                                busScore.setCourseId(courseMap.get(subject).get(0).getCourseId());
                                busScore.setScore(score.floatValue());
                                busScore.setStatus("0");
                                busScore.setCreateTime(now);
                                result.add(busScore);
                            }
                        }
                    }
                }
            }
        }
        catch (Exception e)
        {
            log.error("导入Excel异常{}", e.getMessage());
            throw new UtilException(e.getMessage());
        }
        finally
        {
            workbook.close();
            IOUtils.closeQuietly(is);
        }
        return result;
    }

}
