package com.teaching.web.controller.course;

import com.teaching.common.annotation.Log;
import com.teaching.common.core.controller.BaseController;
import com.teaching.common.core.domain.AjaxResult;
import com.teaching.common.core.domain.entity.SysDept;
import com.teaching.common.core.domain.entity.SysUser;
import com.teaching.common.core.page.TableDataInfo;
import com.teaching.common.enums.BusinessType;
import com.teaching.common.utils.DateUtils;
import com.teaching.common.utils.poi.ExcelUtil;
import com.teaching.common.utils.sign.Md5Utils;
import com.teaching.common.utils.uuid.IdUtils;
import com.teaching.system.domain.BusCourse;
import com.teaching.system.domain.BusSchedule;
import com.teaching.system.domain.BusScheduleDetail;
import com.teaching.system.domain.vo.BusScheduleDto;
import com.teaching.system.domain.vo.CourseHourVo;
import com.teaching.system.service.*;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 课程Controller
 *
 * @author qiaoting
 * @date 2025-01-12
 */
@RestController
@RequestMapping("/course/schedule")
public class BusScheduleController extends BaseController {
    @Autowired
    private IBusScheduleService busScheduleService;
    @Autowired
    private ISysDeptService deptService;
    @Autowired
    private IBusCourseService courseService;
    @Autowired
    private IBusTeacherService teacherService;
    @Autowired
    private ISysUserService userService;

    @GetMapping("/listTeacher")
    public TableDataInfo listTeacher()
    {
        startPage();
        List<SysUser> list = teacherService.selectBusTeacheList(new String[]{"teacher"});
        return getDataTable(list);
    }

    /**
     * 查询课程列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BusSchedule busSchedule) {
        startPage();
        List<BusSchedule> list = busScheduleService.selectBusScheduleList(busSchedule);
        return getDataTable(list);
    }

    /**
     * 导出课程列表
     */
    @Log(title = "课程", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BusScheduleDto busScheduleDto) {
        List<BusSchedule> dbSchedules = busScheduleService.selectBusScheduleByScheduleIds(busScheduleDto.getScheduleIds());
        ExcelUtil<CourseHourVo> util = new ExcelUtil<CourseHourVo>(CourseHourVo.class);
        List<CourseHourVo> list = new ArrayList<>();
        ZoneId zoneId = ZoneId.systemDefault();
        Instant instant = busScheduleDto.getStartDay().toInstant();
        LocalDate startDate = instant.atZone(zoneId).toLocalDate();
        Instant instantEnd = busScheduleDto.getEndDay().toInstant();
        LocalDate endDate = instantEnd.atZone(zoneId).toLocalDate();
        long dayNum = ChronoUnit.DAYS.between(startDate, endDate);
        List<BusScheduleDetail> busScheduleDetailList = new ArrayList<>();
        for (BusSchedule dbSchedule : dbSchedules) {
            busScheduleDetailList.addAll(dbSchedule.getBusScheduleDetailList());
        }
        Map<String, List<BusScheduleDetail>> groupingMap =
                busScheduleDetailList.stream().collect(Collectors.groupingBy(
                        a -> a.getDeptId() + "##**##" + a.getCourseId() + "##**##" + a.getTeacherId()));

        for (Map.Entry<String, List<BusScheduleDetail>> entry : groupingMap.entrySet()) {
            CourseHourVo courseHourVo = new CourseHourVo();
            courseHourVo.setScheduleId(entry.getValue().get(0).getScheduleId());
            SysDept sysDept = deptService.selectDeptById(entry.getValue().get(0).getDeptId());
            courseHourVo.setDeptName(sysDept.getDeptName());
            courseHourVo.setCourseId(entry.getValue().get(0).getCourseId());
            courseHourVo.setTeacherId(entry.getValue().get(0).getTeacherId());
            BusCourse busCourse = courseService.selectBusCourseByCourseId(courseHourVo.getCourseId());
            courseHourVo.setCourseName(busCourse.getCourseName());
            SysUser sysUser = userService.selectUserById(courseHourVo.getTeacherId());
            courseHourVo.setTeacherName(sysUser.getNickName());
            Class<?> clazz = courseHourVo.getClass();
            Field[] fields = clazz.getDeclaredFields();
            int times = 1;
            for (int i = 0; i <= dayNum; i++) {
                LocalDate stepDate = startDate.plusDays(i);
                for (BusScheduleDetail busScheduleDetail : entry.getValue()) {
                    if (Integer.parseInt(busScheduleDetail.getDayWeek()) == stepDate.getDayOfWeek().getValue()) {
                        for (Field field : fields) {
                            if (field.getName().endsWith("_" + times)) {
                                field.setAccessible(true);
                                try {
                                    field.set(courseHourVo, stepDate.format(DateTimeFormatter.ofPattern(DateUtils.MMDD))
                                            + "第" + busScheduleDetail.getHourDay() + "节");
                                } catch (Exception e) {
                                    logger.error("field set error", e);
                                }
                                times ++;
                                break;
                            }
                        }
                    }
                }
            }
            list.add(courseHourVo);
        }
        util.exportExcel(response, list, "课程数据");
    }

    /**
     * 获取课程详细信息
     */
    @GetMapping(value = "/{scheduleId}")
    public AjaxResult getInfo(@PathVariable("scheduleId") Long scheduleId) {
        return success(busScheduleService.selectBusScheduleByScheduleId(scheduleId));
    }

    @Log(title = "课程", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusSchedule busSchedule) {
        return toAjax(busScheduleService.insertBusSchedule(busSchedule));
    }

    /**
     * 新增课程
     */
    @Log(title = "课程", businessType = BusinessType.INSERT)
    @PostMapping("/copy")
    public AjaxResult copy(@RequestBody BusSchedule busSchedule) {
        BusSchedule copySchedule = new BusSchedule();
        copySchedule.setScheduleName(busSchedule.getScheduleName() + "拷贝" + IdUtils.fastUUID());
        List<BusScheduleDetail> busScheduleDetailList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(busSchedule.getBusScheduleDetailList())) {
            for (BusScheduleDetail busScheduleDetail : busSchedule.getBusScheduleDetailList()) {
                BusScheduleDetail copyDetail = new BusScheduleDetail();
                copyDetail.setCourseId(busScheduleDetail.getCourseId());
                copyDetail.setDayWeek(busScheduleDetail.getDayWeek());
                copyDetail.setHourDay(busScheduleDetail.getHourDay());
                copyDetail.setDeptId(busScheduleDetail.getDeptId());
                copyDetail.setTeacherId(busScheduleDetail.getTeacherId());
                busScheduleDetailList.add(copyDetail);
            }
        }
        copySchedule.setBusScheduleDetailList(busScheduleDetailList);
        return toAjax(busScheduleService.insertBusSchedule(copySchedule));
    }

    /**
     * 修改课程
     */
    @Log(title = "课程", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusSchedule busSchedule) {
        return toAjax(busScheduleService.updateBusSchedule(busSchedule));
    }

    /**
     * 删除课程
     */
    @Log(title = "课程", businessType = BusinessType.DELETE)
    @DeleteMapping("/{scheduleIds}")
    public AjaxResult remove(@PathVariable Long[] scheduleIds) {
        return toAjax(busScheduleService.deleteBusScheduleByScheduleIds(scheduleIds));
    }
}
