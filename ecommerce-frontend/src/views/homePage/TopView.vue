<template>
  <div style="height: 100%;width: 100%;">
    <el-menu
        :default-active="activeIndex"
        mode="horizontal"
        background-color="#545c64"
        text-color="#fff"
        active-text-color="#ffd04b"
        router>
      <el-menu-item class="item" >logo</el-menu-item>
      <el-menu-item class="item" index="/top/mall">商城主页</el-menu-item>
      <el-menu-item class="item" index="/top/search_goods">商品搜索</el-menu-item>
      <el-menu-item class="item" index="/top/user_informations" >个人信息</el-menu-item>
      <el-menu-item class="item" index="/top/cart">购物车</el-menu-item>
      <el-menu-item class="item" index="/top/order_management">订单记录</el-menu-item>
      <el-menu-item class="item" index="4" disabled>消息中心</el-menu-item>
      <el-menu-item class="item" disabled>收藏夹</el-menu-item>
      <el-menu-item class="item" disabled>帮助中心</el-menu-item>
      <el-menu-item class="item" disabled>选择主题</el-menu-item>
    </el-menu>
    <router-view></router-view>
  </div>
</template>
<script>
import {defineComponent} from "vue";

export default defineComponent({
  mounted() {
  },
  computed: {
    getUserInfo() {
      return JSON.parse(localStorage.getItem('userInfo'));
    },
    // 用户名字
    getUsername() {
      return this.getUserInfo ? this.getUserInfo.userName : "用户未登录"
    },
    getAccountType() {
      return this.getUserInfo ? this.getUserInfo.accountType : null
    }
  },
  data() {
    return {
      activeIndex: '1'
    };
  },
  methods: {}
})
</script>
<style scoped>
.item
{
  margin-left: 70px;
}

</style>