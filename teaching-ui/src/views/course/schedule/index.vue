<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="课表名称" prop="scheduleName">
        <el-input
          v-model="queryParams.scheduleName"
          placeholder="请输入课表名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="openExport()"
        >生成课时</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="scheduleList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="课表名称" align="center" prop="scheduleName" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
          >删除</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleCopy(scope.row)"
          >复制课表</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改课程对话框 -->
    <el-dialog :title="title" v-if="open" :visible.sync="open" width="80%" top="5vh" :close-on-click-modal="false" ref="ruleDialog" append-to-body>
      <el-row>
        <el-form :inline="true" ref="form" :model="form" :rules="rules" label-width="80px">
          <el-form-item label="课表名称" prop="scheduleName">
            <el-input v-model="form.scheduleName" placeholder="请输入课表名称" />
          </el-form-item>
          <el-form-item label="备注" prop="remark">
            <el-input v-model="form.remark" placeholder="请输入备注" />
          </el-form-item>
          <el-divider content-position="center">课表明细信息</el-divider>
          <el-row :gutter="10" class="mb8">
            <el-col :span="1.5">
              <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteBusScheduleDetail">删除</el-button>
            </el-col>
          </el-row>
        </el-form>
      </el-row>
      <el-row>
        <el-table style="width: 100%" :data="busScheduleDetailList" :row-class-name="rowBusScheduleDetailIndex" @selection-change="handleBusScheduleDetailSelectionChange" ref="busScheduleDetail">
          <el-table-column type="selection" align="center" />
          <el-table-column width="auto" label="课程" prop="courseId">
            <template slot-scope="scope">
              <el-select v-model="scope.row.courseId" filterable placeholder="请选择课程">
                <el-option
                  v-for="dict in courseList"
                  :key="dict.courseId"
                  :label="dict.courseName"
                  :value="dict.courseId"
                ></el-option>
              </el-select>
            </template>
          </el-table-column>
          <el-table-column width="auto" label="班级" prop="deptId">
            <template slot-scope="scope">
              <treeselect append-to-body z-index="9999" v-model="scope.row.deptId" filterable :options="enabledDeptOptions" placeholder="请选择班级" />
            </template>
          </el-table-column>
          <el-table-column width="auto" label="教师" prop="teacherId">
            <template slot-scope="scope">
              <el-select v-model="scope.row.teacherId" filterable placeholder="请选择教师">
                <el-option
                  v-for="dict in teacherList"
                  :key="dict.userId"
                  :label="dict.nickName"
                  :value="dict.userId"
                ></el-option>
              </el-select>
            </template>
          </el-table-column>
          <el-table-column width="auto" label="周几" prop="dayWeek">
            <template slot-scope="scope">
              <el-select v-model="scope.row.dayWeek" placeholder="请选择周几">
                <el-option
                  v-for="dict in dict.type.bus_day_week"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </template>
          </el-table-column>
          <el-table-column width="auto" label="第几节课" prop="hourDay">
            <template slot-scope="scope">
              <el-select v-model="scope.row.hourDay" multiple placeholder="请选择第几节课">
                <el-option
                  v-for="dict in dict.type.bus_hour_day"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </template>
          </el-table-column>
        </el-table>
      </el-row>
      <el-row type="flex" justify="end" :gutter="10" class="mb8" style="margin: 10px;">
        <el-col :span="1.5">
          <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddBusScheduleDetail">添加</el-button>
        </el-col>
      </el-row>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <el-dialog title="教学周期设置" :visible.sync="periodOpen" width="500px" append-to-body>
      <el-form ref="periodForm" :model="exportParams" label-width="120px">
        <el-form-item label="开始日期（含）">
            <el-date-picker
              v-model="exportParams.startDay"
              type="date"
              value-format="yyyy-MM-dd"
              placeholder="选择日期">
            </el-date-picker>
        </el-form-item>
        <el-form-item label="结束日期（含）">
          <el-date-picker
            v-model="exportParams.endDay"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择日期">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="handleExport">确 定</el-button>
        <el-button @click="cancelPeriod">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listUser, getUser, delUser, addUser, updateUser, resetUserPwd, changeUserStatus, deptTreeSelect } from "@/api/system/user";
import { listCourse, getCourse, delCourse, addCourse, updateCourse } from "@/api/course/course";
import {
  copySchedule,
  listTeacher,
  listSchedule,
  getSchedule,
  delSchedule,
  addSchedule,
  updateSchedule
} from "@/api/course/schedule";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "Schedule",
  dicts: ['bus_day_week', 'bus_hour_day', 'bus_teacher'],
  components: { Treeselect },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedBusScheduleDetail: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 课程表格数据
      scheduleList: [],
      courseList: [],
      teacherList: [],
      // 课明细表格数据
      busScheduleDetailList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      periodOpen: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        scheduleName: null,
        status: null,
      },
      exportParams: {
        scheduleId: null,
        startDay: null,
        endDay: null
      },
      // 表单参数
      form: {},
      schedule: {
        scheduleId: null,
        scheduleName: null,
        status: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null
      },
      // 表单校验
      rules: {
        scheduleName: [
          { required: true, message: "课表名称不能为空", trigger: "blur" },
        ],
      },
      // 所有部门树选项
      deptOptions: undefined,
      // 过滤掉已禁用部门树选项
      enabledDeptOptions: undefined
    };
  },
  created() {
    this.getList();
    this.getDeptTree();
    this.getCourseList()
    this.getTeacherList()
  },
  methods: {
    getTeacherList() {
      listTeacher().then(response => {
        this.teacherList = response.rows;
      });
    },
    getCourseList() {
      let query = {"pageNum": 1, "pageSize": 10000}
      listCourse(query).then(response => {
        this.courseList = response.rows;
      });
    },
    // 过滤禁用的部门
    filterDisabledDept(deptList) {
      return deptList.filter(dept => {
        if (dept.disabled) {
          return false;
        }
        if (dept.children && dept.children.length) {
          dept.children = this.filterDisabledDept(dept.children);
        }
        return true;
      });
    },
    getDeptTree() {
      deptTreeSelect().then(response => {
        this.deptOptions = response.data;
        this.enabledDeptOptions = this.filterDisabledDept(JSON.parse(JSON.stringify(response.data)));
      });
    },
    /** 查询课程列表 */
    getList() {
      this.loading = true;
      listSchedule(this.queryParams).then(response => {
        this.scheduleList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        scheduleId: null,
        scheduleName: null,
        status: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null
      };
      this.busScheduleDetailList = [];
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.scheduleId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加课程表";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const scheduleId = row.scheduleId || this.ids
      getSchedule(scheduleId).then(response => {
        this.form = response.data;
        this.busScheduleDetailList = response.data.busScheduleDetailList;
        this.busScheduleDetailList.forEach(function (item, index) {
          item.hourDay = item.hourDay.split(',')
        });
      });
      this.title = "修改课程表";
      this.open = true;
    },
    handleCopy(row) {
      this.reset();
      const scheduleId = row.scheduleId || this.ids
      getSchedule(scheduleId).then(response => {
        this.schedule = response.data;
        this.schedule.busScheduleDetailList = response.data.busScheduleDetailList;
      });
      copySchedule(this.schedule).then(response => {
        this.$modal.msgSuccess("复制成功");
        this.getList();
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.busScheduleDetailList.forEach(function (item, index) {
            item.hourDay = item.hourDay.join(',')
          });
          this.form.busScheduleDetailList = this.busScheduleDetailList;
          if (this.form.scheduleId != null) {
            updateSchedule(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSchedule(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const scheduleIds = row.scheduleId || this.ids;
      this.$modal.confirm('是否确认删除课程编号为"' + scheduleIds + '"的数据项？').then(function() {
        return delSchedule(scheduleIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 课明细序号 */
    rowBusScheduleDetailIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 课明细添加按钮操作 */
    handleAddBusScheduleDetail() {
      let obj = {};
      if (this.busScheduleDetailList != null && this.busScheduleDetailList.length > null) {
        let lastObj = this.busScheduleDetailList.slice(-1)[0];
        obj.courseId = lastObj['courseId'];
        obj.classId = lastObj['classId'];
        obj.teacherId = lastObj['teacherId'];
        obj.dayWeek = lastObj['dayWeek'];
        obj.hourDay = lastObj['hourDay'];
        obj.status = lastObj['status'];
        obj.remark = lastObj['remark'];
      } else {
        obj.courseId = "";
        obj.classId = "";
        obj.teacherId = "";
        obj.dayWeek = "";
        obj.hourDay = "";
        obj.status = "";
        obj.remark = "";
      }
      this.busScheduleDetailList.push(obj);
    },
    /** 课明细删除按钮操作 */
    handleDeleteBusScheduleDetail() {
      if (this.checkedBusScheduleDetail.length == 0) {
        this.$modal.msgError("请先选择要删除的任课记录");
      } else {
        const busScheduleDetailList = this.busScheduleDetailList;
        const checkedBusScheduleDetail = this.checkedBusScheduleDetail;
        this.busScheduleDetailList = busScheduleDetailList.filter(function(item) {
          return checkedBusScheduleDetail.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleBusScheduleDetailSelectionChange(selection) {
      this.checkedBusScheduleDetail = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    openExport() {
      this.exportParams.scheduleIds = this.ids
      if (this.exportParams.scheduleIds != null && this.exportParams.scheduleIds.length == 0) {
        this.$modal.msgError("请选择要生成课时的课程表");
      } else {
        this.periodOpen = true
      }
    },
    cancelPeriod() {
      this.periodOpen = false
    },
    handleExport() {
      this.download('course/schedule/export', {
        ...this.exportParams
      }, `课时_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
