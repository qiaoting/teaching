<template>
  <div class="dashboard-editor-container">

    <panel-group @handleSetLineChartData="handleSetLineChartData" />

    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
        <div slot="header">
          <span><i class="el-icon-monitor"></i>任课列表</span>
        </div>
        <div class="el-table el-table--enable-row-hover el-table--medium">
          <table cellspacing="0" style="width: 100%;">
            <tbody>
            <tr>
              <td class="el-table__cell is-leaf" style="width: 180px"><div class="cell">《Hadoop大数据开发》</div></td>
              <td class="el-table__cell is-leaf" style="width: 200px"><div class="cell">平时表现50%+期末考试50%</div></td>
              <td class="el-table__cell is-leaf"><div class="cell">Hadoop的核心是一个框架，用于将数据存储在大型商用硬件集群上——负担得起且易于使用的日常计算机硬件，并针对该数据运行应用程序。使用负担得起的计算资源网络来获得业务解决能力是Hadoop的关键价值主张，通俗地讲，就是把一堆PC通过网络连接起来能完成大型数据处理。</div></td>
            </tr>
            <tr>
              <td class="el-table__cell is-leaf"><div class="cell">《Docker容器技术》</div></td>
              <td class="el-table__cell is-leaf"><div class="cell">平时表现50%+期末考试50%</div></td>
              <td class="el-table__cell is-leaf"><div class="cell">众多的云平台之间标准规范不统一，每个云平台都有各自独立的资源管理策略、网络映射策略和内部依赖关系，导致各个平台无法做到相互兼容、相互连接。同时，应用的规模愈发庞大、逻辑愈发复杂，任何一款产品都无法顺利地从一个云平台迁移到另外一个云平台。但Docker的出现，打破了这种局面，Docker利用容器弥合了各个平台之间的差异，Docker通过容器来打包应用、解耦应用和运行平台。在进行迁移的时候，只需要在新的服务器上启动所需的容器即可，而所付出的成本是极低的。</div></td>
            </tr>
            </tbody>
          </table>
        </div>
    </el-row>
  </div>
</template>

<script>
import PanelGroup from './dashboard/PanelGroup'
import LineChart from './dashboard/LineChart'
import RaddarChart from './dashboard/RaddarChart'
import PieChart from './dashboard/PieChart'
import BarChart from './dashboard/BarChart'

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
      lineChartData: lineChartData.newVisitis
    }
  },
  methods: {
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
