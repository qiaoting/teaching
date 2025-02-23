<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
        <el-form-item label="班级" prop="deptId">
          <treeselect style="width: 250px;" v-model="queryParams.deptId" filterable :show-count="true" :options="enabledDeptOptions" placeholder="请选择班级" />
        </el-form-item>
        <el-form-item label="课程" prop="courseId">
          <el-select v-model="queryParams.courseId" placeholder="请选择课程" filterable clearable>
            <el-option
              v-for="dict in courseList"
              :key="dict.courseId"
              :label="dict.courseName"
              :value="dict.courseId"
            />
          </el-select>
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
          @click="handleExport"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="homeworkList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="时间" align="center" prop="createTime" />
      <el-table-column label="班级" align="center" prop="deptName" />
      <el-table-column label="课程" align="center" prop="courseId" >
        <template slot-scope="scope">
          <template v-for="item in courseList">
            <span v-if="item.courseId === scope.row.courseId">{{ item.courseName }}</span>
          </template>
        </template>
      </el-table-column>
<!--      <el-table-column label="作业标题" align="center" prop="homeworkTitle" />-->
      <el-table-column label="作业类型" align="center" prop="status" width="100">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.bus_homework_type" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="作业内容" align="center" prop="homeworkContent" />
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

    <!-- 添加或修改作业布置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="班级" prop="deptId">
          <treeselect v-model="form.deptId" filterable :show-count="true" :options="enabledDeptOptions" placeholder="请选择班级" />
        </el-form-item>
        <el-form-item label="课程" prop="courseId">
          <el-select v-model="form.courseId" placeholder="请选择课程" filterable clearable>
            <el-option
              v-for="dict in courseList"
              :key="dict.courseId"
              :label="dict.courseName"
              :value="dict.courseId"
            />
          </el-select>
        </el-form-item>
<!--        <el-form-item label="作业标题" prop="homeworkTitle">-->
<!--          <el-input v-model="form.homeworkTitle" placeholder="请输入作业标题" />-->
<!--        </el-form-item>-->
        <el-form-item label="作业内容">
          <editor v-model="form.homeworkContent" :min-height="192"/>
        </el-form-item>
        <el-form-item label="作业类型" prop="status">
          <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in dict.type.bus_homework_type"
              :key="dict.value"
              :label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listUser, getUser, delUser, addUser, updateUser, resetUserPwd, changeUserStatus, deptTreeSelect } from "@/api/system/user";
import { listHomework, getHomework, delHomework, addHomework, updateHomework } from "@/api/teaching/homework";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
import {listCourse} from "@/api/course/course";

export default {
  name: "Homework",
  components: {Treeselect},
  dicts: ['bus_homework_type'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 作业布置表格数据
      homeworkList: [],
      courseList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        deptId: null,
        courseId: null,
        homeworkTitle: null,
        homeworkType: null,
        homeworkContent: null,
        status: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
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
  },
  methods: {
    getCourseList() {
      let query = {"pageNum": 1, "pageSize": 10000}
      listCourse(query).then(response => {
          this.courseList = response.rows;
        }
      );
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
    /** 查询作业布置列表 */
    getList() {
      this.loading = true;
      listHomework(this.queryParams).then(response => {
        this.homeworkList = response.rows;
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
        homeworkId: null,
        deptId: null,
        courseId: null,
        homeworkTitle: null,
        homeworkType: null,
        homeworkContent: null,
        status: '0',
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null
      };
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
      this.ids = selection.map(item => item.homeworkId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加作业布置";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const homeworkId = row.homeworkId || this.ids
      getHomework(homeworkId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改作业布置";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.homeworkId != null) {
            updateHomework(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addHomework(this.form).then(response => {
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
      const homeworkIds = row.homeworkId || this.ids;
      this.$modal.confirm('是否确认删除作业布置编号为"' + homeworkIds + '"的数据项？').then(function() {
        return delHomework(homeworkIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('teaching/homework/export', {
        ...this.queryParams
      }, `homework_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
