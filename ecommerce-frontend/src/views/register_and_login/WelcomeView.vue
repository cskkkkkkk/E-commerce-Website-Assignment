<template>
  <div class="app">
    <header class="header">
      <img src="@/assets/main_logo.png" class="logo"/>

      <nav class="nav">
        <router-link to="/home"><b>首页</b></router-link>
        <router-link to="/home"><b>关于我们</b></router-link>
        <router-link to="/home"><b>隐私</b></router-link>
      </nav>

    </header>

    <div class="main-content">
      <aside class="sidebar">
        <ul class="function">
          <li><router-link to="/login">登录</router-link></li>
          <li><router-link to="/register">注册</router-link></li>
        </ul>
      </aside>
      <div class="content">
        <h1>cssc's shopping website</h1>
        <h2>欢迎来到 商城</h2>
      </div>
    </div>
    <footer class="footer">
      <p>&copy; 2024 cssc's shopping</p>
    </footer>

  </div>
<!--  <div class="main">-->
<!--    <div class="left-content">-->
<!--      <div class="logo-bg"></div>-->
<!--      <div class="top-login-register">-->
<!--        <router-link to="/rl/register">注册账户</router-link>-->
<!--        ||-->
<!--        <router-link to="/rl/login">登录账户</router-link>-->
<!--      </div>-->
<!--    </div>-->
<!--    <div class="right-content">-->
<!--      <div class="contain">-->
<!--        <h3 class="title" style="font-size: 40px">欢迎来到cssc购物商城</h3>-->
<!--        <br>-->

<!--        <router-view></router-view>-->
<!--      </div>-->
<!--    <div class="top-login-register">-->
<!--      <router-link to="/rl/register">注册账户</router-link>-->
<!--      ||-->
<!--      <router-link to="/rl/login">登录账户</router-link>-->
<!--    </div>-->
<!--    <div class="logo">-->
<!--      <img src="@/assets/main_logo.png" class="main-logo" alt="Main Logo" />-->
<!--    </div>-->
<!--    <div class="welcome-content">-->
<!--      <h1 class="title">欢迎来到cssc购物商城</h1>-->
<!--    </div>-->
<!--  </div>-->
<!--  </div>-->
</template>

<script>
import { defineComponent } from "vue";

export default defineComponent({});
</script>

<style scoped>
.app {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
}

.header {

  background-color: white;
  color: black;
  padding: 20px;
  display: flex;
  align-items: center;
}
.logo{
  height: 100px;
  width: auto;
  margin-right: auto;
}
.nav {
  display: flex;
  justify-content: center; /* 将导航项居中对齐 */
  flex-grow: 1; /* 使导航栏占据剩余空间 */
  font-size: 21px;
}

.nav a {
  color: black;
  margin-left: 20px;
  text-decoration: none;
}

.main-content {
  display: flex;
  flex: 1;

}

.sidebar {
  width: 200px;
  background-color: #f9fafb;
  padding: 20px;
}
.function li {
  margin-bottom: 30px; /* 添加间距 */
  font-size: 25px;
}
.function a
{
  text-decoration: none;
}


.content {
  flex: 1;
  padding: 20px;
  background-color: white;
  text-align: center;
  font-size: 20px;
}

.footer {
  background-color: #1e3a8a;
  color: white;
  text-align: center;
  padding: 10px;
}
</style>
