
<!--<template>-->
<!--  <div class="layout">-->
<!--    <el-container v-if="state.showMenu" class="container">-->
<!--      <el-aside class="aside">-->
<!--        <div class="head">-->
<!--          <div>-->
<!--            <span>vue3 admin</span>-->
<!--          </div>-->
<!--        </div>-->
<!--        <div class="line" />-->
<!--        <el-menu-->
<!--            background-color="#222832"-->
<!--            text-color="#fff"-->
<!--            :router="true"-->
<!--            :default-openeds="state.defaultOpen"-->
<!--            :default-active="state.currentPath"-->
<!--            @open="handleOpen"-->
<!--            @close="handleClose"-->
<!--            class="menu"-->
<!--        >-->
<!--          <el-sub-menu index="1">-->
<!--            <template #title>-->
<!--              <span>Dashboard</span>-->
<!--            </template>-->
<!--            <el-menu-item-group >-->
<!--              <el-menu-item index="/">首页</el-menu-item>-->
<!--              <el-menu-item index="/add">添加商品</el-menu-item>-->
<!--            </el-menu-item-group>-->
<!--          </el-sub-menu>-->
<!--          <el-sub-menu index="2">-->
<!--            <template #title>-->
<!--              <span>模块管理</span>-->
<!--            </template>-->
<!--            <el-menu-item-group>-->
<!--              <el-menu-item index="/category">分类管理</el-menu-item>-->
<!--              <el-menu-item index="/good">商品管理</el-menu-item>-->
<!--              <el-menu-item index="/guest">用户管理</el-menu-item>-->
<!--              <el-menu-item index="/order">订单管理</el-menu-item>-->
<!--            </el-menu-item-group>-->
<!--          </el-sub-menu>-->
<!--          <el-sub-menu index="4">-->
<!--            <template #title>-->
<!--              <span>系统管理</span>-->
<!--            </template>-->
<!--            <el-menu-item-group>-->
<!--              <el-menu-item index="/account">修改密码</el-menu-item>-->
<!--            </el-menu-item-group>-->
<!--          </el-sub-menu>-->
<!--        </el-menu>-->
<!--      </el-aside>-->
<!--      <el-container class="content">-->
<!--        <Header />-->
<!--        <div class="main">-->
<!--          <router-view />-->
<!--        </div>-->
<!--        <Footer />-->
<!--      </el-container>-->
<!--    </el-container>-->
<!--    <el-container v-else class="container">-->
<!--      <router-view />-->
<!--    </el-container>-->
<!--  </div>-->
<!--</template>-->

<!--<script setup>-->
<!--import { reactive } from 'vue'-->
<!--import { useRouter } from 'vue-router'-->
<!--import Header from '@/components/HeaderOfPage.vue'-->
<!--import Footer from '@/components/FooterOfPage.vue'-->
<!--import { localGet, pathMap } from '@/utils'-->
<!--import { ElMenu, ElSubMenu, ElMenuItem, ElMenuItemGroup} from 'element-plus'-->

<!--const noMenu = ['/login']-->
<!--const router = useRouter()-->
<!--const state = reactive({-->
<!--  showMenu: true,-->
<!--  defaultOpen: ['1', '2', '3', '4'],-->
<!--  currentPath: '/',-->
<!--})-->

<!--router.beforeEach((to, from, next) => {-->
<!--  if (to.path == '/login') {-->
<!--    // 如果路径是 /login 则正常执行-->
<!--    next()-->
<!--  } else {-->
<!--    //如果不是 /login，判断是否有 token-->
<!--    if (!localGet('token')) {-->
<!--      // 如果没有，则跳至登录页面-->
<!--      next({ path: '/login' })-->
<!--    } else {-->
<!--      // 否则继续执行-->
<!--      next()-->
<!--    }-->
<!--  }-->
<!--  state.showMenu = !noMenu.includes(to.path)-->
<!--  state.currentPath = to.path-->
<!--  document.title = pathMap[to.name]-->
<!--})-->
<!--</script>-->

<!--<style scoped>-->
<!--.layout {-->
<!--  min-height: 100vh;-->
<!--  background-color: #ffffff;-->
<!--}-->
<!--.container {-->
<!--  height: 100vh;-->
<!--  display: flex;-->
<!--}-->
<!--.aside {-->
<!--  width: 200px!important;-->
<!--  background-color: white;-->
<!--  transition: width 0.3s ease;-->
<!--}-->
<!--.head {-->
<!--  display: flex;-->
<!--  align-items: center;-->
<!--  justify-content: center;-->
<!--  height: 50px;-->
<!--}-->
<!--.head > div {-->
<!--  display: flex;-->
<!--  align-items: center;-->
<!--}-->

<!--.head img {-->
<!--  width: 50px;-->
<!--  height: 50px;-->
<!--  margin-right: 10px;-->
<!--}-->
<!--.head span {-->
<!--  font-size: 20px;-->
<!--  color: black;-->
<!--}-->
<!--.line {-->
<!--  border-top: 1px solid hsla(0,0%,100%,.05);-->
<!--  border-bottom: 1px solid rgba(0,0,0,.2);-->
<!--}-->
<!--.content {-->
<!--  display: flex;-->
<!--  flex-direction: column;-->
<!--  max-height: 100vh;-->
<!--  overflow: hidden;-->
<!--}-->
<!--.main {-->
<!--  height: calc(100vh - 100px);-->
<!--  overflow: auto;-->
<!--  padding: 10px;-->
<!--}-->

<!--.el-menu {-->
<!--  border-right: none !important;-->
<!--}-->
<!--.el-submenu {-->
<!--  border-top: 1px solid hsla(0, 0%, 100%, .05);-->
<!--  border-bottom: 1px solid rgba(0, 0, 0, .2);-->
<!--  margin-bottom: 10px;-->
<!--}-->
<!--.el-submenu:first-child {-->
<!--  border-top: none;-->
<!--}-->
<!--.el-submenu [class^="el-icon-"] {-->
<!--  vertical-align: -1px ;-->
<!--  margin-right: 8px;-->
<!--  width: 10px;-->
<!--}-->

<!--/* Hide the submenu arrow */-->
<!--.el-submenu__icon {-->
<!--  display: none;-->
<!--  font-size: 1px; /* 调整为合适的大小 */-->
<!--}-->

<!--a {-->
<!--  color: #409eff;-->
<!--  text-decoration: none;-->
<!--}-->
<!--.el-pagination {-->
<!--  text-align: center;-->
<!--  margin-top: 20px;-->
<!--}-->
<!--.el-popper__arrow {-->
<!--  display: none;-->
<!--}-->
<!--</style>-->

<template>
  <div class="layout">
    <el-container v-if="state.showMenu" class="container">
      <el-aside class="aside">
        <div class="head">
          <span class="brand">Vue3 Admin</span>
        </div>
        <el-menu
            background-color="#1f2d3d"
            text-color="#bfcbd9"
            active-text-color="#409eff"
            :router="true"
            id="one"
            :default-openeds="state.defaultOpen"
            :default-active="state.currentPath"
            @open="handleOpen"
            @close="handleClose"
            class="menu"
        >
          <el-sub-menu index="1">
            <template #title>
              <span>Dashboard</span>
            </template>
            <el-menu-item index="/">首页</el-menu-item>
            <el-menu-item index="/add">添加商品</el-menu-item>
            <el-menu-item index="/statistic">统计报表</el-menu-item>
          </el-sub-menu>

          <el-sub-menu index="2">
            <template #title>
              <span>模块管理</span>
            </template>
            <el-menu-item index="/category">分类管理</el-menu-item>
            <el-menu-item index="/good">商品管理</el-menu-item>
            <el-menu-item index="/guest">用户管理</el-menu-item>
            <el-menu-item index="/order">订单管理</el-menu-item>
            <el-menu-item index="/view">浏览记录</el-menu-item>
            <el-menu-item index="/buy">购买记录</el-menu-item>
          </el-sub-menu>

          <el-sub-menu index="4">
            <template #title>
              <span>系统管理</span>
            </template>
            <el-menu-item index="/account">修改密码</el-menu-item>
          </el-sub-menu>
        </el-menu>
      </el-aside>

      <el-container class="content">
        <Header />
        <div class="main">
          <router-view />
        </div>
        <Footer />
      </el-container>
    </el-container>

    <!-- 如果不显示菜单，则直接渲染路由视图 -->
    <el-container v-else class="container">
      <router-view />
    </el-container>
  </div>
</template>

<script setup>
import { reactive } from 'vue'
import { useRouter } from 'vue-router'
import Header from '@/components/HeaderOfPage.vue'
import Footer from '@/components/FooterOfPage.vue'
import { localGet, pathMap } from '@/utils'

const noMenu = ['/login']
const router = useRouter()
const state = reactive({
  showMenu: true,
  defaultOpen: ['1', '2', '3', '4'],
  currentPath: '/',
})

router.beforeEach((to, from, next) => {
  if (to.path == '/login') {
    // 如果路径是 /login 则正常执行
    next()
  } else {
    //如果不是 /login，判断是否有 token
    if (!localGet('token')) {
      // 如果没有，则跳至登录页面
      next({ path: '/login' })
    } else {
      // 否则继续执行
      next()
    }
  }
  state.showMenu = !noMenu.includes(to.path)
  state.currentPath = to.path
  document.title = pathMap[to.name]
})
</script>

<style scoped>
.layout {
  min-height: 100vh;
  background-color: #f5f7fa;
}

.container {
  display: flex;
  height: 100vh;
}

.aside {
  width: 220px;
  background-color: #2c3e50;
  color: #fff;
  transition: width 0.3s ease;
}

.head {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 60px;
  background-color: #34495e;
}

.brand {
  font-size: 20px;
  font-weight: 600;
}

.el-menu {
  margin-top: 20px;
}

.el-submenu {
  border-top: 1px solid rgba(255, 255, 255, 0.1);
}

/* Hide the submenu arrow */
.el-submenu__icon {
  display: none !important;
}

.el-submenu > .el-menu-item {
  padding-left: 20px;
}

.el-menu-item {
  padding: 15px 20px;
}

.el-menu-item:hover {
  background-color: #16a085;
}

.el-submenu__title {
  color: #bfcbd9;
  font-weight: 600;
}

.el-menu-item.is-active {
  background-color: #409eff !important;
  color: #fff !important;
}

.content {
  flex-grow: 1;
  display: flex;
  flex-direction: column;
}

.main {
  padding: 20px;
  flex-grow: 1;
  overflow-y: auto;
}

a {
  color: #409eff;
  text-decoration: none;
}

.el-pagination {
  text-align: center;
  margin-top: 20px;
}

.el-popper__arrow {
  display: none;
}


#one >>> .el-icon{
  display: none;
}

.el-table th {
  display: table-cell !important;

}
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
