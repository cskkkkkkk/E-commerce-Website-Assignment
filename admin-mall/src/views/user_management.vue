<template>
  <div class="page-container">
    <el-card class="user-container">
      <template #header>
        <div class="header">
          <span class="header-title">用户信息浏览</span>
        </div>
      </template>

      <!-- 用户信息展示 -->
      <el-row
          v-for="(user, index) in state.tableData"
          :key="index"
          class="user-card"
          type="flex"
          justify="start"
          align="middle"
      >
        <el-col :span="8" class="user-info-item">
          <strong>昵称:</strong> {{ user.nickName }}
        </el-col>
        <el-col :span="8" class="user-info-item">
          <strong>登录名:</strong> {{ user.userName }}
        </el-col>
        <el-col :span="8" class="user-info-item">
          <strong>注册时间:</strong> {{ user.createdAt }}
        </el-col>
      </el-row>

    </el-card>
  </div>
</template>

<script setup>
import { onMounted, reactive } from 'vue'
import { ElMessage } from 'element-plus'
import axios from '@/utils/axios'

// 表格数据
const state = reactive({
  loading: false,
  tableData: [],
})

const getList = () => {
  state.loading = true
  axios.get('/users', {
    params: {
      pageNumber: 1,  // 默认第一页
      pageSize: 10
    }
  }).then(res => {
    // 确保数据结构匹配
    state.tableData = res.list
    console.log("table")
    console.log(state.tableData)
    state.loading = false
  }).catch(() => {
    state.loading = false
    ElMessage.error('加载数据失败')
  })
}

// 初始加载
onMounted(() => {
  getList()
})
</script>

<style scoped>
/* 外部容器：确保页面居中，并占据适当宽度 */
.page-container {
  display: flex;
  justify-content: center;
  align-items: flex-start;
  min-height: 100vh;
  background-color: #f5f5f5;
  width: 100%;
  padding: 20px;
  /* 为了适应 el-menu，添加额外的 padding 和 margin */
  margin-left: 200px;  /* 假设左侧有一个宽度为 200px 的菜单栏 */
}

/* 卡片容器：提升视觉效果并调整宽度 */
.user-container {
  margin: 20px;
  border-radius: 12px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  background-color: #ffffff;
  width: 100%;
  max-width: 1400px; /* 调整最大宽度 */
  overflow: hidden;
  /* 为了适应页面布局，卡片容器可自适应宽度 */
  min-width: 800px;
}

/* 页头样式 */
.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 20px;
  border-bottom: 1px solid #f0f0f0;
}

.header-title {
  font-size: 20px;
  font-weight: 600;
  color: #333;
}

/* 用户信息行 */
.user-card {
  padding: 15px;
  border-bottom: 1px solid #f0f0f0;
  display: flex;
  flex-wrap: wrap; /* 使子项换行 */
}

.user-info-item {
  padding: 10px;
  color: #333;
  font-size: 14px;
  margin-bottom: 10px; /* 每个项之间的间距 */
}
</style>
