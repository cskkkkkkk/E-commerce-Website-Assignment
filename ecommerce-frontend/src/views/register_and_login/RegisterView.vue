<template>
  <div class="page-wrapper">
    <div class="register-wrapper">
  <el-form class="register-container" :rules="rules"
           ref="sizeForm" :model="sizeForm" label-width="auto">
    <el-form-item label="用户名" prop="userName" class="register-item">
      <el-input placeholder="请设置用户名" v-model="sizeForm.userName"></el-input>
    </el-form-item>
    <el-form-item label="密码" prop="password" class="register-item">
      <el-input placeholder="请设置密码" v-model="sizeForm.password" show-password></el-input>
    </el-form-item>
    <el-form-item label="邮箱" prop="email" class="register-item">
      <el-input placeholder="请输入邮箱" v-model="sizeForm.email"></el-input>
    </el-form-item>
    <el-form-item label="账号类型" class="register-item" size="small">
      <el-radio-group v-model="sizeForm.accountType">
        <el-radio-button label="消费者"></el-radio-button>
        <el-radio-button label="商家"></el-radio-button>
      </el-radio-group>
    </el-form-item>
    <el-form-item class="register-button">
      <el-button type="primary" @click="onSubmit()" class="full-width-button">立即注册</el-button>
    </el-form-item>
    <el-form-item class="other-options">
    <div class="options">
      <el-button class="login" @click="jumpToLogin()"><u>登录</u></el-button>
      <el-button class="return-nav" @click="jumpToNav()"><u>返回导航页</u></el-button>
    </div>
    </el-form-item>
  </el-form>
    </div>
  </div>
</template>

<script>

import {postRegisterUser} from "@/service/register_service";
import {defineComponent}  from "vue";


export default defineComponent({
  data() {
    return {
      sizeForm: {
        userName: '',
        password: '',
        email: '',
        // 账户类型，消费者||商家
       accountType: '消费者'
      },
      rules: {
        userName: [
          {required: true, message: '用户名不能为空', trigger: 'blur'},
          {min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '密码不能为空', trigger: 'blur'},
          {min: 8, max: 20, message: '长度在 8 到 20 个字符', trigger: 'blur'}
        ],
        email: [
          {required: true, message: '邮箱不能为空', trigger: 'blur',},
          {type: "email", required: true, message: '邮箱格式错误', trigger: ['blur', 'change']}
        ]
      },
    };
  },
  methods: {
    onSubmit() {
          postRegisterUser(this.sizeForm).then(result => {
            console.log(result.code)
            if (result.code === "0") {
              //存在重复用户名，重新注册
              this.$message.error("存在重复用户名，请重新注册")
            } else {
              //注册成功
              this.$message.success("注册成功,请登录账号")
              this.$router.push('/login')
            }
          })
    },
    jumpToLogin()
    {
      this.$router.push('/login');
    },
    jumpToNav()
    {
      this.$router.push('/');
    }

  }
});
</script>
<style scoped>

.page-wrapper {
  background-color:#f4ecf4; /* 设置页面背景色 */
  height: 100vh; /* 使容器占满整个视口高度 */
}
.register-wrapper {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh; /* 使容器占满整个视口高度 */
}

.register-container {
  width: 90%;
  max-width: 400px; /* 设置最大宽度 */
  border-radius: 20px;
  padding: 20px;
  text-align: left;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1); /* 添加阴影 */
  background-color: white; /* 设置背景色 */
}

.register-item {
  width: 100%;
  margin-bottom: 20px; /* 为表单项添加间距 */
}

.register-button {
  width: 100%; /* 使按钮容器占满整个宽度 */
}

.full-width-button {
  width: 100%; /* 使按钮占满整个宽度 */
  background-color: orangered; /* 设置按钮背景颜色为橙色 */
  border-color: orangered; /* 设置按钮边框颜色为橙色（如果需要） */
  border-radius: 40px;
}
.other-options {
  display: flex;
  justify-content: flex-end; /* 右对齐选项 */
  margin-top: 5px; /* 添加顶部间距 */
}
.options {
  display: flex; /* 使用 flex 布局 */
}

.login,
.return-nav{
  border: none; /* 去掉按钮边框 */
  color: gray; /* 设置字体颜色为灰色 */
  font-size: 12px; /* 设置字体大小 */
  margin-left: 200px; /* 为按钮添加左间距 */
}



</style>