<template>
  <div class="app-container">
    <splitpanes :horizontal="this.$store.getters.device === 'mobile'" class="default-theme">
      <!--部门数据-->
      <pane size="16">
        <el-col>
          <div class="head-container">
            <el-input v-model="deptName" placeholder="请输入部门名称" clearable size="small" prefix-icon="el-icon-search" style="margin-bottom: 20px" />
          </div>
          <div class="head-container">
            <el-tree :data="deptOptions" :props="defaultProps" :expand-on-click-node="false" :filter-node-method="filterNode" ref="tree" node-key="id" default-expand-all highlight-current @node-click="handleNodeClick" />
          </div>
        </el-col>
      </pane>
      <!--用户数据-->
      <pane size="84">
        <el-col>
          <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
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
            <el-form-item label="事项分类" prop="scoreType">
              <el-select v-model="queryParams.scoreType" placeholder="请选择事项分类">
                <el-option
                  v-for="dict in dict.type.bus_study_status"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
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
            <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
          </el-row>

          <el-table v-loading="loading" :data="studyList" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55" align="center" />
            <el-table-column label="课程" align="center" prop="courseId" >
              <template slot-scope="scope">
                <template v-for="item in courseList">
                  <span v-if="item.courseId === scope.row.courseId">{{ item.courseName }}</span>
                </template>
              </template>
            </el-table-column>
            <el-table-column label="部门" align="center" prop="deptName" />
            <el-table-column label="用户" align="center" prop="nickName" />
            <el-table-column label="事项分类" align="center" prop="scoreType" >
              <template slot-scope="scope">
                <dict-tag :options="dict.type.bus_study_status" :value="scope.row.scoreType"/>
              </template>
            </el-table-column>
            <el-table-column label="时间点" align="center" prop="realTime" width="180">
              <template slot-scope="scope">
                <span>{{ parseTime(scope.row.realTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
              </template>
            </el-table-column>
            <el-table-column label="学习情况" align="center" prop="content" />
            <el-table-column label="状态" align="center" prop="status" />
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
        </el-col>
      </pane>
    </splitpanes>
    <!-- 添加或修改学习情况记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" :close-on-click-modal="false" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="课程" prop="courseId">
          <el-select v-model="form.courseId" filterable placeholder="请选择课程" clearable>
            <el-option
              v-for="dict in courseList"
              :key="dict.courseId"
              :label="dict.courseName"
              :value="dict.courseId"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="事项分类" prop="scoreType">
          <el-select v-model="form.scoreType" filterable placeholder="请选择事项分类" clearable>
            <el-option
              v-for="dict in dict.type.bus_study_status"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="用户" prop="userId">
          <el-select v-model="form.userId" filterable placeholder="请选择用户" clearable>
            <el-option
              v-for="dict in userList"
              :key="dict.userId"
              :label="dict.nickName"
              :value="dict.userId"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="时间点" prop="realTime">
          <el-date-picker clearable
            v-model="form.realTime"
            type="datetime"
            :picker-options="pickerOptions"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="请选择时间点">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="学习情况">
          <editor v-model="form.content" :min-height="192"/>
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
import { listStudy, getStudy, delStudy, addStudy, updateStudy } from "@/api/teaching/study";
import { listUser, getUser, delUser, addUser, updateUser, resetUserPwd, changeUserStatus, deptTreeSelect } from "@/api/system/user";
import { getToken } from "@/utils/auth";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
import { Splitpanes, Pane } from "splitpanes";
import "splitpanes/dist/splitpanes.css";
import { listCourse, getCourse, delCourse, addCourse, updateCourse } from "@/api/course/course";

export default {
  name: "Study",
  dicts: ['sys_normal_disable', 'sys_user_sex', 'bus_study_status'],
  components: { Treeselect, Splitpanes, Pane },
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
      // 学习情况记录表格数据
      studyList: [],
      userList: [],
      courseList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        courseId: null,
        deptId: null,
        userId: null,
        scoreType: null,
        realTime: null,
        content: null,
        status: null,
      },
      pickerOptions: {
        shortcuts: [{
          text: '今天',
          onClick(picker) {
            picker.$emit('pick', new Date());
          }
        }, {
          text: '昨天',
          onClick(picker) {
            const date = new Date();
            date.setTime(date.getTime() - 3600 * 1000 * 24);
            picker.$emit('pick', date);
          }
        }]
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      // 所有部门树选项
      deptOptions: undefined,
      // 过滤掉已禁用部门树选项
      enabledDeptOptions: undefined,
      // 部门名称
      deptName: undefined,
      defaultProps: {
        children: "children",
        label: "label"
      },
    };
  },
  watch: {
    // 根据名称筛选部门树
    deptName(val) {
      this.$refs.tree.filter(val);
    }
  },
  created() {
    this.getList();
    this.getDeptTree();
    this.getConfigKey("sys.user.initPassword").then(response => {
      this.initPassword = response.msg;
    });
    this.getUserList()
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
    getUserList() {
      let query = {"pageNum": 1, "pageSize": 10000, "deptId": this.queryParams.deptId}
      listUser(query).then(response => {
          this.userList = response.rows;
        }
      );
    },
    /** 查询部门下拉树结构 */
    getDeptTree() {
      deptTreeSelect().then(response => {
        this.deptOptions = response.data;
        this.enabledDeptOptions = this.filterDisabledDept(JSON.parse(JSON.stringify(response.data)));
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
    // 筛选节点
    filterNode(value, data) {
      if (!value) return true;
      return data.label.indexOf(value) !== -1;
    },
    // 节点单击事件
    handleNodeClick(data) {
      this.queryParams.deptId = data.id;
      this.getUserList();
      this.handleQuery();
    },
    /** 查询学习情况记录列表 */
    getList() {
      this.loading = true;
      listStudy(this.queryParams).then(response => {
        this.studyList = response.rows;
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
        id: null,
        courseId: null,
        deptId: null,
        userId: null,
        scoreType: null,
        realTime: null,
        content: null,
        status: null,
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
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加学习情况记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getStudy(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改学习情况记录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateStudy(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            if (this.queryParams.deptId != null && this.queryParams.deptId != '') {
              this.$set(this.form, 'deptId', this.queryParams.deptId)
            } else {
              this.$modal.msgError("请先选择左侧部门");
            }
            addStudy(this.form).then(response => {
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
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除学习情况记录编号为"' + ids + '"的数据项？').then(function() {
        return delStudy(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('teaching/study/export', {
        ...this.queryParams
      }, `study_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
