<template>
  <div class="blog-detail">
    <div class="blog-header">
      <h1 class="blog-title">{{ blog.title }}</h1>
      <p class="author-info">
        <el-tag type="primary">{{ blog.author }}</el-tag>
        <span class="date">{{ blog.date }}</span>
      </p>
    </div>

    <div class="blog-content">
      <p>{{ blog.content }}</p>
    </div>

  </div>
</template>

<script>
export default {
  data() {
    return {
      blog: null,  // 存储博客详情内容
    };
  },
  created() {
    const blogId = 1
    this.fetchBlogDetail(blogId);
  },
  methods: {
    // 模拟从数据中获取博客详情
    fetchBlogDetail(id) {
      const blogs = [
        { id: 1, title: '大学生活的点滴', author: '张三', date: '2025-02-20', content: '这篇博客分享了我在大学生活中的一些有趣经历。' },
        { id: 2, title: 'Vue.js 深入浅出', author: '李四', date: '2025-02-15', content: '本文讲解了 Vue.js 的核心概念及其使用技巧，适合初学者阅读。' },
        { id: 3, title: '校园活动大揭秘', author: '王五', date: '2025-02-18', content: '本篇博客介绍了我们学校的一些热门校园活动，活动参与感受非常丰富。' },
        { id: 4, title: '学长分享：大学期间如何高效学习', author: '赵六', date: '2025-02-10', content: '这篇博客分享了一些在大学期间如何管理时间、提高学习效率的方法。' },
        // 可以添加更多的博客数据
      ];
      this.blog = blogs.find(blog => blog.id === parseInt(id));
    },
    goBack() {
      this.$router.push('/');
    }
  }
};
</script>

<style scoped>
.blog-detail {
  padding: 20px;
  margin: 0 auto;
  background-color: #f9f9f9;
}

.blog-header {
  background-color: #fff;
  padding: 20px;
  border-radius: 8px;
  margin-bottom: 20px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.blog-title {
  font-size: 28px;
  font-weight: bold;
  margin-bottom: 10px;
  color: #333;
}

.author-info {
  display: flex;
  justify-content: space-between;
  font-size: 14px;
  color: #777;
}

.date {
  font-size: 12px;
  color: #999;
}

.blog-content {
  background-color: #fff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  margin-bottom: 20px;
  color: #666;
}

.back-btn {
  text-align: center;
  margin-top: 20px;
}
</style>
