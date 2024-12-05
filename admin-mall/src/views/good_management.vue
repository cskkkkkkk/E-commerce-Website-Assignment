<template>
  <el-card class="good-container">
    <template #header>
      <div class="header">
        <el-button type="primary" :icon="Plus" @click="handleAdd">新增商品</el-button>
      </div>
    </template>
    <el-table
        :load="state.loading"
        :data="state.tableData"
        tooltip-effect="dark"
        style="width: 100%"
    >
      <el-table-column
          prop="goodsId"
          label="商品编号"
      >
      </el-table-column>
      <el-table-column
          prop="goodsName"
          label="商品名"
      >
      </el-table-column>
      <el-table-column
          prop="goodsDetailContent"
          label="商品简介"
      >
      </el-table-column>
      <el-table-column
          label="商品图片"
          width="150px"
      >
        <template #default="scope">
          <img style="width: 100px; height: 100px;" :key="scope.row.goodsId" :src="$filters.prefix(scope.row.goodsCoverImg)" alt="商品主图">
        </template>
      </el-table-column>
      <el-table-column
          prop="stockNum"
          label="商品库存"
      >
      </el-table-column>
      <el-table-column
          prop="sellingPrice"
          label="商品售价"
      >
      </el-table-column>
      <el-table-column
          label="上架状态"
      >
        <template #default="scope">
          <span style="color: green;" v-if="scope.row.goodsSellStatus === 0">销售中</span>
          <span style="color: red;" v-else>已下架</span>
        </template>
      </el-table-column>

      <el-table-column
          label="操作"
          width="100"
      >
        <template #default="scope">
          <a style="cursor: pointer; margin-right: 10px" @click="handleEdit(scope.row.goodsId)">修改</a>
          <a style="cursor: pointer; margin-right: 10px" v-if="scope.row.goodsSellStatus == 0" @click="handleStatus(scope.row.goodsId, 1)">下架</a>
          <a style="cursor: pointer; margin-right: 10px" v-else @click="handleStatus(scope.row.goodsId, 0)">上架</a>
        </template>
      </el-table-column>
    </el-table>
    <!--总数超过一页，再展示分页器-->
    <el-pagination
        background
        layout="prev, pager, next"
        :total="state.total"
        :page-size="state.pageSize"
        :current-page="state.currentPage"
        @current-change="changePage"
    />
  </el-card>
</template>

<script setup>
import { onMounted, reactive, getCurrentInstance } from 'vue'
import axios from '@/utils/axios'
import { ElMessage } from 'element-plus'
import { Plus} from '@element-plus/icons-vue'
import { useRouter } from 'vue-router'

const app = getCurrentInstance()
const { goTop } = app.appContext.config.globalProperties
const router = useRouter()
const state = reactive({
  loading: false,
  tableData: [], // 数据列表
  total: 0, // 总条数
  currentPage: 1, // 当前页
  pageSize: 10 // 分页大小
})
onMounted(() => {
  getGoodList()
})
// 获取轮播图列表
const getGoodList = () => {
  state.loading = true
  axios.get('/goods/list', {
    params: {
      pageNumber: state.currentPage,
      pageSize: state.pageSize
    }
  }).then(res => {
    state.tableData = res.list
    state.total = res.totalCount
    state.loading = false
    goTop && goTop()
  })
}
const handleAdd = () => {
  router.push({ path: '/add' })
}
const handleEdit = (id) => {
  router.push({ path: '/add', query: { id } })
}
const changePage = (val) => {
  state.currentPage = val
  getGoodList()
}
const handleStatus = (id, status) => {
  axios.put(`/goods/status/${status}`, {
    ids: id ? [id] : []
  }).then(() => {
    ElMessage.success('修改成功')
    getGoodList()
  })
}
</script>

<style scoped>
.good-container {
  min-height: 100%;
}
.el-card.is-always-shadow {
  min-height: 100%!important;
}

.el-pagination
{
  display: flex;
  justify-content: center;
}
.el-pagination {
  font-family: 'Arial', sans-serif;
  font-size: 14px;
  color: #333;
  border-radius: 4px;
}

.el-pagination .el-pager li {
  border-radius: 4px;
  transition: background-color 0.3s, color 0.3s;
}

.el-pagination .el-pager li.active {
  background-color: #409EFF; /* 主色调 */
  color: white;
  border-color: #409EFF;
}

.el-pagination .el-pager li:hover:not(.active) {
  background-color: #f5f5f5;
  cursor: pointer;
}

.el-pagination .el-button--primary {
  background-color: #409EFF;
  border-color: #409EFF;
  color: #fff;
  transition: background-color 0.3s, border-color 0.3s;
}

.el-pagination .el-button--primary:hover {
  background-color: #66b1ff;
  border-color: #66b1ff;
}

.el-pagination .el-button--mini {
  font-size: 12px;
}

.el-pagination .el-icon {
  color: #409EFF;
}

.el-pagination .el-icon:hover {
  color: #66b1ff;
}

/* 添加边距和内边距以优化布局 */
.el-pagination {
  margin: 20px 0;
  padding: 5px 0;
}

.el-pagination .el-pager {
  display: flex;
  gap: 10px;
}

.el-pagination .el-pager li {
  padding: 5px 10px;
  border-radius: 4px;
  background-color: transparent;
  transition: background-color 0.3s ease;
}

.el-pagination .el-pager li:hover {
  background-color: #f0f0f0;
}

</style>
