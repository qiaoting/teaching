<template>
  <div class="blog-list">
    <div class="blog-item" v-for="(blog, index) in pagedBlogs" :key="index">
      <div class="blog-header">
        <h3 class="blog-title">{{ blog.title }}</h3>
        <div class="author-info">
          <el-tag type="primary">{{ blog.author }}</el-tag>
          <span class="date">{{ blog.date }}</span>
        </div>
      </div>
      <div class="blog-description">
        <p>{{ blog.description }}</p>
      </div>
      <el-button type="text" @click="goToDetail(blog.id)" class="view-btn">查看详情</el-button>
    </div>

    <!-- Pagination -->
    <el-pagination
      :current-page="currentPage"
      :page-size="pageSize"
      :total="blogs.length"
      @current-change="handlePageChange"
      layout="total, prev, pager, next, jumper"
      background
    ></el-pagination>
  </div>
</template>

<script>
export default {
  data() {
    return {
      blogs: [
        { id: 1, title: '大学生活的点滴', author: '张三', date: '2025-02-20', description: '这篇博客分享了我在大学生活中的一些有趣经历...' },
        { id: 2, title: 'Vue.js 深入浅出', author: '李四', date: '2025-02-15', description: '本文讲解了 Vue.js 的核心概念及其使用技巧...' },
        { id: 3, title: '校园活动大揭秘', author: '王五', date: '2025-02-18', description: '本篇博客介绍了我们学校的一些热门校园活动...' },
        { id: 4, title: '学长分享：大学期间如何高效学习', author: '赵六', date: '2025-02-10', description: '在这篇博客中，我分享了自己大学期间的一些高效学习方法...' },
        { id: 5, title: '如何准备研究生面试', author: '孙七', date: '2025-02-05', description: '本文分享了我在准备研究生面试过程中的一些心得和建议...' },
        { id: 6, title: '掌握 Git 基本操作', author: '周八', date: '2025-02-01', description: '这篇博客讲解了 Git 的基本操作及常用命令...' },
        { id: 7, title: '校园食堂美食推荐', author: '钱九', date: '2025-01-30', description: '在这篇博客中，我推荐了学校食堂里最好吃的几道菜...' },
        { id: 8, title: '大学里的一些思考与感悟', author: '吴十', date: '2025-01-25', description: '大学生活给了我很多思考和感悟，分享一些个人体会...' },
        { id: 9, title: '如何选择适合自己的职业', author: '郑十一', date: '2025-01-20', description: '本文帮助大学生在求职过程中如何根据自己的兴趣选择合适的职业...' },
        { id: 10, title: '毕业旅行必去的几个地方', author: '冯十二', date: '2025-01-10', description: '毕业旅行时，我去了几个非常值得推荐的旅行地，分享旅行心得...' }
      ],
      currentPage: 1,
      pageSize: 5, // 每页展示 5 条博客
    };
  },
  computed: {
    pagedBlogs() {
      const start = (this.currentPage - 1) * this.pageSize;
      const end = start + this.pageSize;
      return this.blogs.slice(start, end);
    }
  },
  methods: {
    handlePageChange(page) {
      this.currentPage = page;
    },
    goToDetail(id) {
      this.$router.push({ name: 'BlogDetail', params: { id } });
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

.date {
  font-size: 12px;
  color: #999;
}

.blog-description {
  font-size: 14px;
  color: #666;
  margin-bottom: 2px;
}

.view-btn {
  padding: 2px 10px;
  font-size: 14px;
  color: #409EFF;
  text-align: left;
}

.el-pagination {
  margin-top: 20px;
  text-align: center;
}
</style>
