<template>
  <el-card class="category-container">
    <el-table
        :load="state.loading"
        ref="multipleTable"
        :data="state.tableData"
        tooltip-effect="dark"
        style="width: 100%"
        >
      <el-table-column
          prop="nickName"
          label="昵称"
          width="120px"
      >
      </el-table-column>
      <el-table-column
          prop="loginName"
          label="登录名"
          width="120px"
      >
      </el-table-column>
      <el-table-column
          label="商品图片"
          width="200px"
      >
        <template #default="scope">
          <img style="width: 100px; height: 100px;" :key="scope.row.goodsId" :src="scope.row.goodsCoverImg" alt="商品主图">
        </template>
      </el-table-column>
      <el-table-column
          prop="goodsName"
          label="商品名称"
          width="120px"
      >
      </el-table-column>
      <el-table-column
          prop="browseTime"
          label="浏览时间"
          width="120px"
      >
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
        style="display: block; text-align: center;"
    />
  </el-card>
</template>

<script setup>
import { onMounted, reactive,watchEffect } from 'vue'
import axios from '@/utils/axios'

const state = reactive({
  loading: false,
  tableData: [], // 数据列表
  total: 0, // 总条数
  currentPage: 1, // 当前页
  pageSize: 10, // 分页大小
})
onMounted(() => {
  getBrowseRecord()
})
watchEffect(() => {
  console.log(state.pageSize)
})

// 获取分类列表
const getBrowseRecord = () => {
  state.loading = true
  axios.get('/browse_record', {
    params: {
      pageNumber: state.currentPage,
      pageSize: state.pageSize,
    }
  }).then(res => {
    state.tableData = res.list
    state.total = res.totalCount
    state.loading = false
  })
}
const changePage = (val) => {
  state.currentPage = val
  getBrowseRecord()
}


</script>

<style>
/* 确保分页器的容器宽度足够 */
.el-pagination {
  width: 100%;
  display: flex;
  justify-content: center; /* 居中显示 */
  align-items: center;
}

/* 调整分页按钮和图标的大小 */
.el-pagination .el-icon {
  font-size: 16px; /* 图标大小 */
}

/* 设置分页按钮显示为水平排列 */
.el-pagination .el-pager li {
  display: inline-block;
  margin: 0 5px;
}

/* 调整分页按钮的尺寸 */
.el-pagination .el-button {
  padding: 0 10px; /* 按钮内边距 */
  height: 30px; /* 按钮高度 */
  line-height: 30px; /* 行高，确保文本垂直居中 */
  font-size: 14px; /* 按钮文本大小 */
}

/* 确保分页的左右箭头图标显示正常 */
.el-pagination .el-icon-arrow-left,
.el-pagination .el-icon-arrow-right {
  font-size: 20px; /* 设置图标大小 */
  margin: 0 10px /* 图标之间的间距 */
}
</style>