<template>
  <div class="app-container">
    <div class="blog-detail">
      <div class="blog-header">
        <h1 class="blog-title">{{ blog.postTitle }}</h1>
        <p class="author-info">
          <el-tag type="primary">{{ blog.nickName }}</el-tag>
          <span class="date">{{ blog.createTime }}</span>
        </p>
      </div>

      <div class="blog-content">
        <div v-html="blog.postContent"></div>
      </div>

    </div>
  </div>
</template>

<script>
import { getPost } from "@/api/bbs/post";
import store from "@/store";

export default {
  name: "PostDetail",
  data() {
    return {
      postId: null,
      blog: {}
    };
  },
  created() {
    this.postId = this.$route.params && this.$route.params.postId
    this.getDetail();
  },
  methods: {
    /** 查询帖子列表 */
    getDetail() {
      getPost(this.postId).then(response => {
        this.blog = response.data;
      });
    }
  }

};
</script>
<style scoped>
.blog-detail {
  padding: 2px 20px;
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
