<template>
  <div class="app-container">
    <div class="create-blog-link" v-hasPermi="['bbs:post:add']">
      <el-link type="primary" @click="handleAdd" >
        <i class="el-icon-plus"></i> 创建博客
      </el-link>
    </div>
    <div class="blog-list">
      <div class="blog-item" v-for="(blog, index) in postList" :key="index">
        <div class="blog-header">
          <h3 class="blog-title">{{ blog.postTitle }}</h3>
          <div class="author-info">
            <span>{{ blog.nickName }}</span>
            <span><i class="el-icon-time"></i>{{ blog.createTime }}</span>
            <span v-if="userId === blog.userId">
              <el-link type="primary" @click="handleUpdate(blog)">修改</el-link>
            </span>
            <span v-if="userId === blog.userId">
              <el-link type="primary" @click="handleDelete(blog)">删除</el-link>
            </span>
          </div>
        </div>
        <div class="blog-description">
          <p>{{ truncateText(blog.postContent, 50) }}</p>
        </div>
        <div style="padding-bottom: 10px;">
          <router-link :to="'/bbs/post-detail/index/' + blog.postId" class="link-type">
            <span>查看详情</span>
          </router-link>
        </div>
      </div>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
    </div>
    <!-- 添加或修改帖子对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="700px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="标题" prop="postTitle">
          <el-input v-model="form.postTitle" placeholder="请输入标题" />
        </el-form-item>
        <el-form-item label="内容" prop="postContent">
          <editor v-model="form.postContent" :min-height="250"/>
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
import { listPost, getPost, delPost, addPost, updatePost } from "@/api/bbs/post";
import store from "@/store";

export default {
  name: "Post",
  dicts: ['sys_normal_disable'],
  data() {
    return {
      // 遮罩层
      loading: true,
      userId: null,
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
      // 帖子表格数据
      postList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        categoryId: null,
        userId: null,
        postTitle: null,
        postContent: null,
        status: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        postTitle: [
          { required: true, trigger: "blur", message: "请填写标题" },
          { min: 2, max: 50, message: '标题长度必须介于 2 和 50 之间', trigger: 'blur' }
        ],
        postContent: [
          { required: true, trigger: "blur", message: "请填写内容" },
        ]
      },
    };
  },
  created() {
    this.userId = store.getters && store.getters.id
    this.getList();
  },
  methods: {
    /** 查询帖子列表 */
    getList() {
      this.loading = true;
      listPost(this.queryParams).then(response => {
        this.postList = response.rows;
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
        postId: null,
        categoryId: null,
        userId: null,
        postTitle: null,
        postContent: null,
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
      this.ids = selection.map(item => item.postId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加帖子";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const postId = row.postId || this.ids
      getPost(postId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改帖子";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.postId != null) {
            updatePost(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPost(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    truncateText(text, maxLength) {
      // 去掉 HTML 标签
      const strippedText = text.replace(/<\/?[^>]+(>|$)/g, "");
      if (strippedText.length > maxLength) {
        return strippedText.slice(0, maxLength) + '...';
      }
      return strippedText;
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const postIds = row.postId || this.ids;
      this.$modal.confirm('是否确认删除帖子编号为"' + postIds + '"的数据项？').then(function() {
        return delPost(postIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('bbs/post/export', {
        ...this.queryParams
      }, `post_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
<style scoped>
.blog-list {
  padding: 2px;
  margin: 0 auto;
}

.blog-item {
  padding: 2px 30px;
  border-bottom: 1px solid #ddd;
  margin-bottom: 10px;
}

.blog-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 2px;
}

.blog-title {
  font-size: 15px;
  font-weight: bold;
  color: #333;
  margin: 0;
  text-align: center; /* 使标题居中 */
  flex-grow: 1;
}

.author-info {
  display: flex;
  gap: 30px; /* 增加作者和日期之间的间距 */
  font-size: 14px;
  color: #777;
}

.blog-description {
  font-size: 14px;
  color: #666;
  margin-bottom: 2px;
}


.create-blog-link {
  text-align: right;
  margin-bottom: 40px;
}
</style>
