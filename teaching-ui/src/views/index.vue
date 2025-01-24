<template>
  <div class="dashboard-editor-container">

    <panel-group @handleSetLineChartData="handleSetLineChartData" />

    <el-row :gutter="12" style="margin-bottom: 10px;">
      <el-col :span="12">
        <el-card>
          <div slot="header">
            <span><i class="el-icon-monitor"></i>学院通知公告</span>
          </div>
          <div class="el-table el-table--enable-row-hover el-table--medium" style="height: 280px;">
            <table cellspacing="0" style="width: 100%;">
              <tbody>
              <tr v-for="item in noticeList" :key="item.noticeId">
                <td class="el-table__cell is-leaf"><div v-html="truncateText(item.noticeTitle, 20)"></div></td>
              </tr>
              </tbody>
            </table>
            <pagination
              v-show="queryParams.total>0"
              :background="false"
              :total="queryParams.total"
              :page.sync="queryParams.pageNum"
              :limit.sync="queryParams.pageSize"
              layout="prev, pager, next"
              @pagination="getNoticeList"
            />
          </div>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card>
          <div slot="header">
            <span><i class="el-icon-monitor"></i>讲师布置作业</span>
          </div>
          <div class="el-table el-table--enable-row-hover el-table--medium" style="height: 280px;">
            <table cellspacing="0" style="width: 100%;">
              <tbody>
              <tr v-for="item in homeworkList" :key="item.homeworkId">
                <td class="el-table__cell is-leaf">
                  <div>
                      <template v-for="course in courseList">
                        <span v-if="item.courseId === course.courseId">{{ course.courseName }}</span>
                      </template>
                  </div>
                </td>
                <td class="el-table__cell is-leaf"><div v-html="truncateText(item.homeworkContent, 20)"></div></td>
              </tr>
              </tbody>
            </table>
            <pagination
              v-show="queryHomeParams.total>0"
              :background="false"
              :total="queryHomeParams.total"
              :page.sync="queryHomeParams.pageNum"
              :limit.sync="queryHomeParams.pageSize"
              layout="prev, pager, next"
              @pagination="getHomeworkList"
            />
          </div>
        </el-card>
      </el-col>
    </el-row>

  </div>
</template>

<script>
import PanelGroup from './dashboard/PanelGroup'
import LineChart from './dashboard/LineChart'
import RaddarChart from './dashboard/RaddarChart'
import PieChart from './dashboard/PieChart'
import BarChart from './dashboard/BarChart'
import { listNotice, getNotice, delNotice, addNotice, updateNotice } from "@/api/system/notice";
import { listHomework, getHomework, delHomework, addHomework, updateHomework } from "@/api/teaching/homework";
import {listCourse} from "@/api/course/course";

const lineChartData = {
  newVisitis: {
    expectedData: [100, 120, 161, 134, 105, 160, 165],
    actualData: [120, 82, 91, 154, 162, 140, 145]
  },
  messages: {
    expectedData: [200, 192, 120, 144, 160, 130, 140],
    actualData: [180, 160, 151, 106, 145, 150, 130]
  },
  purchases: {
    expectedData: [80, 100, 121, 104, 105, 90, 100],
    actualData: [120, 90, 100, 138, 142, 130, 130]
  },
  shoppings: {
    expectedData: [130, 140, 141, 142, 145, 150, 160],
    actualData: [120, 82, 91, 154, 162, 140, 130]
  }
}

export default {
  name: 'Index',
  components: {
    PanelGroup,
    LineChart,
    RaddarChart,
    PieChart,
    BarChart
  },
  data() {
    return {
      queryParams: {
        pageNum: 1,
        pageSize: 5,
        status: '0',
        total: 0
      },
      queryHomeParams: {
        pageNum: 1,
        pageSize: 5,
        status: '0',
        total: 0
      },
      courseList: [],
      noticeList: [],
      homeworkList: [],
      lineChartData: lineChartData.newVisitis
    }
  },
  created() {
    this.getCourseList();
    this.getNoticeList();
    this.getHomeworkList();
  },
  methods: {
    getCourseList() {
      let query = {"pageNum": 1, "pageSize": 10000}
      listCourse(query).then(response => {
          this.courseList = response.rows;
        }
      );
    },
    getHomeworkList() {
      listHomework(this.queryHomeParams).then(response => {
        this.homeworkList = response.rows;
        this.queryHomeParams.total = response.total;
      });
    },
    getNoticeList() {
      listNotice(this.queryParams).then(response => {
        this.queryParams.total = response.total;
        this.noticeList = response.rows;
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
    handleSetLineChartData(type) {
      this.lineChartData = lineChartData[type]
    }
  }
}
</script>

<style lang="scss" scoped>
.dashboard-editor-container {
  padding: 15px;
  background-color: rgb(240, 242, 245);
  position: relative;

  .chart-wrapper {
    background: #fff;
    padding: 16px 16px 0;
    margin-bottom: 32px;
  }
}

@media (max-width:1024px) {
  .chart-wrapper {
    padding: 8px;
  }
}
</style>
