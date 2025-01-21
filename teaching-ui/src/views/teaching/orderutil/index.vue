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
      <pane size="84">
        <div class="seating-chart">

          <!-- 讲台位置 -->
          <el-row class="lecturer-row">
            <el-col :span="2" class="lecturer-area">
              <el-card
                :body-style="{
                  padding: '0 0 5px 5px',
                  background: 'linear-gradient(45deg, #ff9a9e, #fad0c4)',
                  color: '#fff',
                  borderRadius: '10px',
                  textAlign: 'center'
                }"
                shadow="hover"
              >
                <div class="lecturer">
                  <div class="lecturer-icon">
                    <el-avatar :size="60" icon="el-icon-s-flag" />
                  </div>
                  <div class="lecturer-name">
                    <el-form>
                      <el-form-item>
                        <el-button icon="el-icon-search" size="medium" type="danger" round @click="refresh">随机点名</el-button>
                      </el-form-item>
                    </el-form>
                  </div>
                </div>
              </el-card>
            </el-col>
          </el-row>

          <!-- 座位区域 -->
          <el-row>
            <el-col :span="24">
              <div class="seats-container">
                <draggable
                  v-model="seats"
                  group="seats"
                  class="draggable-list"
                  @end="onDragEnd"
                >
                  <div
                    v-for="(seat, index) in seats"
                    :key="seat.id"
                    class="seat-item"
                    :class="{ 'selected-seat': selectedSeats.includes(seat) }"
                    @click="toggleSelectSeat(seat)"
                  >
                    <el-card
                      :body-style="{
                        padding: '10px',
                        background: 'linear-gradient(45deg, #8fd3f4, #84fab0)',
                        color: '#fff',
                        borderRadius: '10px',
                        transform: selectedSeats.includes(seat) ? 'scale(1.2)' : 'scale(1)'
                      }"
                      shadow="hover"
                    >
                      <div class="seat-content">
                        <div class="seat-icon">
                          <el-avatar :size="40" icon="el-icon-user" />
                        </div>
                        <div class="seat-name">{{ seat.name }}</div>
                      </div>
                    </el-card>
                  </div>
                </draggable>
              </div>
            </el-col>
          </el-row>
        </div>
      </pane>
    </splitpanes>
  </div>
</template>

<script>
import draggable from "vuedraggable";
import { Splitpanes, Pane } from "splitpanes";
import "splitpanes/dist/splitpanes.css";
import { listUser, deptTreeSelect } from "@/api/system/user";

export default {
  components: {
    draggable, Splitpanes, Pane
  },
  data() {
    return {
      queryParams: {
        deptId: null
      },
      defaultProps: {
        children: "children",
        label: "label"
      },
      deptName: undefined,
      // 所有部门树选项
      deptOptions: undefined,
      // 过滤掉已禁用部门树选项
      enabledDeptOptions: undefined,
      seats: [],
      selectedSeats: [],
    };
  },
  watch: {
    // 根据名称筛选部门树
    deptName(val) {
      this.$refs.tree.filter(val);
    }
  },
  created() {
    this.getDeptTree();
  },
  methods: {
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
    },
    getUserList() {
      let query = {"pageNum": 1, "pageSize": 10000, "deptId": this.queryParams.deptId}
      listUser(query).then(response => {
        this.userList = response.rows
        let id = 0
        this.seats = this.userList.map((item, index) => {
          return {id: id++, name: item.nickName}
        })
      })
    },
    refresh() {
      this.selectedSeats = []
      let idx = Math.ceil(Math.random()*this.seats.length)
      this.selectedSeats.push(this.seats[idx])
    },
    onDragEnd(event) {
      console.log("拖拽结束: ", event);
    },
    toggleSelectSeat(seat) {
      const index = this.selectedSeats.findIndex(selected => selected.id === seat.id);
      if (index > -1) {
        this.selectedSeats.splice(index, 1);
      } else {
        this.selectedSeats.push(seat);
      }
    },
  },
};
</script>

<style scoped>
.seating-chart {
  padding: 20px;
  background: #f0f2f5;
}

.lecturer-row {
  display: flex;
  justify-content: center;
  margin-bottom: 2px;
}

.lecturer-area {
  width: 100%;
  max-width: 200px; /* 确保讲台宽度为一个座位的宽度 */
  text-align: center;
}

.lecturer {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 10px;
}

.lecturer-icon {
  flex-shrink: 0;
}

.lecturer-name {
  font-size: 20px;
  font-weight: bold;
}

.seats-container {
  display: grid;
  grid-template-columns: repeat(10, 1fr);
  gap: 15px;
  padding: 20px;
  background: #f5f5f5;
  border-radius: 15px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  transition: all 0.3s ease;
}

.seat-item {
  cursor: grab;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.seat-item:active {
  transform: scale(1.1);
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
}

.seat-item.selected-seat {
  border: 2px solid #ff4d4f;
  box-shadow: 0 8px 16px rgba(255, 77, 79, 0.6);
  transform: scale(1.2);
}

.draggable-list {
  display: contents;
}

.seat-content {
  display: flex;
  align-items: center;
  gap: 10px;
}

.seat-icon {
  flex-shrink: 0;
}

.seat-name {
  font-size: 16px;
  font-weight: bold;
  color: #333;
}
</style>
