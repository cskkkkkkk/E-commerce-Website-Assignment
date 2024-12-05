<!--这个组件有用在客户管理中 -->
<template>
  <el-table
      :load="state.loading"
      :data="state.tableData"
      tooltip-effect="dark"
      style="width: 100%"
      @selection-change="handleSelectionChange"
  >
    <slot name='column'></slot>
  </el-table>
  <el-pagination
      background
      layout="prev, pager, next"
      :total="state.total"
      :page-size="state.pageSize"
      :current-page="state.currentPage"
      @current-change="changePage"
  />
</template>

<script setup>
import { onMounted, reactive, getCurrentInstance,defineProps,defineExpose } from 'vue'
import axios from '@/utils/axios'

const props = defineProps({
  action: String
})//写清楚名字和属性
const app = getCurrentInstance()
const { goTop } = app.appContext.config.globalProperties
const state = reactive({
  loading: false,
  tableData: [], // 数据列表
  //total: 绑定总记录数，state.total 代表分页器所需的总数据量。分页器会根据总记录数和当前页大小（page-size）计算出总页数，并为用户提供翻页功能。
  total: 0, // 总条数
  currentPage: 1, // 当前页
  pageSize: 10, // 分页大小
  multipleSelection: []
})
onMounted(() => {
  getList()
})

const getList = () => {
  state.loading = true
  axios.get(props.action, {
    params: {
      pageNumber: state.currentPage,
      pageSize: state.pageSize
    }
  }).then(res => {
    console.log(res)
    state.tableData = res.list
    state.total = res.totalCount
    //state.currentPage = res.currPage
    state.loading = false
    goTop && goTop() // 回到顶部
  })
}

const handleSelectionChange = (val) => {
  state.multipleSelection = val
}

const changePage = (val) => {
  state.currentPage = val
  getList()
}
// script setup 写法，需要通过 defineExpose 方法，将属性暴露出去，才能在父组件通过 ref 形式拿到本组件的内部参数
defineExpose({ state: state, getList: getList })
</script>