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
      >
      </el-table-column>
      <el-table-column
          prop="loginName"
          label="登录名"
          width="120"
      >
      </el-table-column>
      <el-table-column
          prop="orderNo"
          label="订单编号"
          width="120"
      >
      </el-table-column>
      <el-table-column
          prop="createTime"
          label="购买时间"
          width="120"

      >
      </el-table-column>
      <el-table-column
          prop="totalPrice"
          label="支付金额"
          width="120"
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
  axios.get('/buy_record', {
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

</style>