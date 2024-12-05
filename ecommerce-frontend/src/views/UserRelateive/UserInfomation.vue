<template>
  <div class="profile-container">
    <!-- 个人信息管理标题 -->
    <el-card class="profile-card">
      <h2>个人信息管理</h2>

      <!-- 个人信息展示 -->
      <div class="user-info">
        <el-row>
          <el-col :span="6"><strong>用户名：</strong></el-col>
          <el-col :span="18">{{ userInfo.userName }}</el-col>
        </el-row>
        <el-row>
          <el-col :span="6"><strong>邮箱：</strong></el-col>
          <el-col :span="18">{{ userInfo.email }}</el-col>
        </el-row>
        <el-row>
          <el-col :span="6"><strong>电话：</strong></el-col>
          <el-col :span="18">{{ userInfo.phone }}</el-col>
        </el-row>
      </div>

      <!-- 编辑按钮和退出登录按钮 -->
      <div class="actions">
        <el-button type="primary" @click="editInfo">编辑信息</el-button>
        <el-button type="danger" @click="logout">退出登录</el-button>
      </div>
    </el-card>

    <!-- 编辑个人信息的弹窗 -->
    <el-dialog title="编辑个人信息"  v-model="editDialogVisible" width="30%">
      <el-form :model="editForm">
<!--        <el-form-item label="用户名">-->
<!--          <el-input v-model="editForm.username"></el-input>-->
<!--        </el-form-item>-->
        <el-form-item label="邮箱">
          <el-input v-model="editForm.email"></el-input>
        </el-form-item>
        <el-form-item label="电话">
          <el-input v-model="editForm.phone"></el-input>
        </el-form-item>
        <el-form-item label="收货地址">
          <el-input v-model="editForm.address"></el-input>
        </el-form-item>
      </el-form>
      <template v-slot:footer>
      <div  class="dialog-footer">
        <el-button @click="editDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="saveChanges">保存</el-button>
      </div>
      </template>
    </el-dialog>
  </div>
</template>

<script>
import {ref, reactive, onMounted} from 'vue';
import { ElMessage } from 'element-plus';
import {defineComponent} from "vue";
import {putUserInformation} from "@/service/user_information_service";
import router from "@/router";
export default defineComponent({
  setup() {
    // 初始化用户信息
    const userInfo = reactive({
      userName: "张三",
      email: "zhangsan@example.com",
      phone: "123-456-7890",
      address: ""
    });

    // 控制编辑弹窗的显示状态
    const editDialogVisible = ref(false);

    // 编辑表单数据
    const editForm = reactive({});

    // 编辑信息方法
    const editInfo = () => {
      Object.assign(editForm, userInfo);
      editDialogVisible.value = true;
    };

    onMounted(()=>
        {
          const savedUserInfo = localStorage.getItem('userInfo');
          if (savedUserInfo) {
            const parsedUserInfo = JSON.parse(savedUserInfo);
            //Object.assign(userInfo, parsedUserInfo);  // 将 localStorage 中的值赋给响应式对象
            userInfo.email=parsedUserInfo.email;
            userInfo.phone=parsedUserInfo.phone;
            userInfo.userName=parsedUserInfo.userName;
            userInfo.address=parsedUserInfo.address;
          }
        }
    )

    // 保存编辑信息方法
    const saveChanges = async () => {
      Object.assign(userInfo, editForm);
      editDialogVisible.value = false;
      try{
        const response= await putUserInformation(userInfo);
        console.log("后端返回的数据:",response);
        ElMessage.success("信息已保存！");
      }catch(error)
      {
        ElMessage.error("提交过程中出现的错误"+error);
      }
      
    };

    // 退出登录方法
    const logout = () => {
      ElMessage("已退出登录");
      // 执行退出登录的逻辑
      localStorage.removeItem('userInfo');
      localStorage.removeItem('token');
      router.push('/')
    };

    return {
      userInfo,
      editDialogVisible,
      editForm,
      editInfo,
      saveChanges,
      logout,
    };
  },
});
</script>

<style scoped>
.profile-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f5f5f5;
}
.profile-card {
  width: 50%;
  padding: 20px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}
.user-info {
  margin: 20px 0;
}
.actions {
  display: flex;
  justify-content: space-between;
  margin-top: 20px;
}
.dialog-footer {
  text-align: right;
}
</style>
