<template>
  <div class="order-box">
    <!-- 页头组件，显示“我的订单”标题并支持返回操作 -->
    <el-page-header @back="goBack" content="我的订单" class="order-header"></el-page-header>

    <!-- 订单状态选项卡 -->
    <el-tabs v-model="state.status" @tab-click="onChangeTab" class="order-tab">
      <!-- 各个选项卡，按状态筛选订单 -->
<!--      <el-tab-pane label="全部" name=""></el-tab-pane>-->
      <el-tab-pane label="待支付" name="0" ></el-tab-pane>
      <el-tab-pane label="已支付" name="1" ></el-tab-pane>
      <el-tab-pane label="已确认" name="2" ></el-tab-pane>
      <el-tab-pane label="已发货" name="3"></el-tab-pane>
      <el-tab-pane label="交易完成" name="4"></el-tab-pane>
    </el-tabs>

    <!-- 内容部分，包括订单列表和加载功能 -->
    <div class="content">
      <el-scrollbar>
        <!-- 骨架屏，用于加载时占位 -->
        <el-skeleton v-if="state.loading" rows="4" animated></el-skeleton>
        <!-- 空状态提示，当无订单时显示 -->
        <el-empty v-if="!state.loading && state.list.length === 0" description="暂无订单"></el-empty>
        <!-- 订单列表 -->
        <div v-for="(item, index) in state.list" :key="index" class="order-item-box">
          <!-- 每个订单的标题，显示时间和状态 -->
          <div class="order-item-header">
            <span>订单时间：{{ item.createTime }}</span>
            <span>{{ item.orderStatusString }}</span>
          </div>
          <!-- 每个订单的商品列表 -->
          <el-card
              v-for="one in item.goodsInOrder"
              :key="one.goodsId"
              class="order-card"
              :body-style="{ padding: '10px' }"
              shadow="always"
          >
            <!-- 商品缩略图 -->
            <template #header>
              <img :src="one.goodsCoverImg" class="order-card-thumb" alt="商品图片" />
            </template>
            <!-- 商品信息 -->
            <div>
              <h4>{{ one.goodsName }}</h4>
              <p>数量：{{ one.goodsCount }}</p>
              <p>价格：￥{{ one.sellingPrice }}</p>
              <!-- 查看详情按钮 -->
<!--              <el-button size="small" @click="goTo(item.orderNo)">查看详情</el-button>-->
            </div>
          </el-card>
        </div>
        <!-- 加载更多按钮，显示更多订单 -->
        <el-button
            v-if="!state.finished"
            class="load-more"
            type="primary"
            plain
            size="small"
            @click="onLoad"
        >
          加载更多
        </el-button>
      </el-scrollbar>
    </div>
  </div>
</template>

<script setup>
import {onMounted, reactive,} from 'vue';
import { useRouter } from 'vue-router';
import { getOrderList } from '@/service/order_management';

// 路由实例
const router = useRouter();

// 页面状态管理
const state = reactive({
  status: '0', // 当前选中的订单状态
  loading: false, // 是否正在加载数据
  finished: false, // 是否加载完所有数据
  list: [], // 订单列表数据
  page: 1, // 当前页码
  totalPage: 0, // 总页数
});
onMounted(()=> {
      loadData()
    }
)
// 加载订单数据
const loadData = async () => {
  state.loading = true; // 显示加载状态
  const data=JSON.parse(localStorage.getItem("userInfo"))
  const params= { pageNumber: state.page, status: state.status,userID:data.userID }
  console.log(params)
  const response= await getOrderList(params);
  console.log(response.list)
  state.list = response.list
  state.totalPage =response.totalPage; // 更新总页数
  state.loading = false; // 关闭加载状态
  if (state.page >= state.totalPage) state.finished = true; // 如果当前页码大于等于总页数，标记加载完成
};

// 选项卡切换时触发，更新状态并重新加载数据
const onChangeTab = (tabName) => {

  state.status=tabName.props.name
  console.log("...........")
  console.log(state.status)
  state.page = 1; // 重置页码
  state.list = []; // 清空订单列表
  state.finished = false; // 重置加载完成状态
  loadData(); // 重新加载数据
};

// 跳转到订单详情页
// const goTo = (id) => {
//   router.push({ path: '/order-detail', query: { id } });
// };

// 返回上一页
const goBack = () => {
  router.push("/top/mall")
};

// 加载更多订单数据
const onLoad = () => {
  if (state.page < state.totalPage) {
    state.page++; // 增加页码
    loadData(); // 加载下一页数据
  }
};
</script>

<style lang="less" scoped>
/* 样式定义 */
.order-box {
  /* 页头样式 */
  .order-header {
    background: #fff; /* 白色背景 */
    border-bottom: 1px solid #ebeef5; /* 底部边框 */
    padding: 10px 20px; /* 内边距 */
  }

  /* 选项卡样式 */
  .order-tab {
    display: flex; /* 使用 flex 布局 */
    justify-content: center; /* 水平居中 */
    margin-top: 10px; /* 上边距 */
    border-bottom: 1px solid #ebeef5; /* 底部边框 */

  }
  //.order-tab {
  //  margin: 0 auto; /* 水平居中 */
  //  text-align: center; /* 对齐子内容 */
  //  border-bottom: 1px solid #ebeef5; /* 底部边框 */
  //}
  //
  //.el-tabs {
  //  display: inline-block; /* 将其内容内联化 */
  //  width: auto; /* 自适应宽度 */
  //}

  /* 内容区域样式 */
  .content {
    margin: 10px 20px; /* 内边距 */
    height: calc(100vh - 120px); /* 高度自适应 */
    overflow-y: auto; /* 垂直滚动 */
  }

  /* 每个订单的外层容器 */
  .order-item-box {
    margin-bottom: 20px; /* 下边距 */
    .order-item-header {
      display: flex; /* 使用Flex布局 */
      justify-content: space-between; /* 两端对齐 */
      margin-bottom: 10px; /* 下边距 */
      font-size: 14px; /* 字体大小 */
      color: #606266; /* 字体颜色 */
    }
    .order-card {
      display: flex; /* 使用Flex布局 */
      align-items: center; /* 垂直居中 */
      .order-card-thumb {
        width: 80px; /* 缩略图宽度 */
        height: 80px; /* 缩略图高度 */
        margin-right: 10px; /* 右外边距 */
        object-fit: cover; /* 保持缩放比例 */
      }
    }
  }

  /* 加载更多按钮样式 */
  .load-more {
    display: block; /* 块级元素 */
    margin: 20px auto; /* 居中对齐，上下间距20px */
  }
}
</style>
