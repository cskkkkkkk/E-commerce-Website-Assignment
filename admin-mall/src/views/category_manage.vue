
<!--<template>-->
<!--  <el-container style="height: 100vh; padding: 20px;">-->
<!--    &lt;!&ndash; 主体内容 &ndash;&gt;-->
<!--    <el-main class="main-content">-->
<!--      <el-card class="category-container">-->
<!--        &lt;!&ndash; 头部 &ndash;&gt;-->
<!--        <template #header>-->
<!--          <div class="header">-->
<!--            &lt;!&ndash; 增加分类按钮 &ndash;&gt;-->
<!--            <el-button type="primary" @click="handleAdd" size="small">添加分类</el-button>-->
<!--            &lt;!&ndash; 批量删除按钮 &ndash;&gt;-->
<!--            <el-popconfirm-->
<!--                title="确定删除所选项吗？"-->
<!--                confirmButtonText="确定"-->
<!--                cancelButtonText="取消"-->
<!--                @confirm="handleDelete"-->
<!--            >-->
<!--              <template #reference>-->
<!--                <el-button type="danger" size="small">批量删除</el-button>-->
<!--              </template>-->
<!--            </el-popconfirm>-->
<!--          </div>-->
<!--        </template>-->

<!--        &lt;!&ndash; 商品分类列表 &ndash;&gt;-->
<!--        <el-table-->
<!--            :data="state.tableData"-->
<!--            :loading="state.loading"-->
<!--            tooltip-effect="dark"-->
<!--            class="category-table"-->
<!--            @selection-change="handleSelectionChange"-->
<!--            stripe-->
<!--            border-->
<!--        >-->
<!--          <el-table-column-->
<!--              type="selection"-->
<!--              width="55"-->
<!--          />-->
<!--          <el-table-column-->
<!--              prop="categoryName"-->
<!--              label="分类名称"-->
<!--              width="180"-->
<!--          />-->
<!--          <el-table-column-->
<!--              prop="categoryRank"-->
<!--              label="排序值"-->
<!--              width="120"-->
<!--          />-->
<!--          <el-table-column-->
<!--              prop="createTime"-->
<!--              label="添加时间"-->
<!--              width="200"-->
<!--          />-->
<!--          <el-table-column label="操作" width="220">-->
<!--            <template #default="scope">-->
<!--              &lt;!&ndash; 修改按钮 &ndash;&gt;-->
<!--              <el-button-->
<!--                  size="small"-->
<!--                  type="text"-->
<!--                  @click="handleEdit(scope.row.categoryId)"-->
<!--                  class="action-button"-->
<!--              >-->
<!--                修改-->
<!--              </el-button>-->
<!--              &lt;!&ndash; 删除按钮 &ndash;&gt;-->
<!--              <el-popconfirm-->
<!--                  title="确定删除该分类吗？"-->
<!--                  confirmButtonText="确定"-->
<!--                  cancelButtonText="取消"-->
<!--                  @confirm="handleDeleteOne(scope.row.categoryId)"-->
<!--              >-->
<!--                <template #reference>-->
<!--                  <el-button size="small" type="text" class="delete-button">删除</el-button>-->
<!--                </template>-->
<!--              </el-popconfirm>-->
<!--            </template>-->
<!--          </el-table-column>-->
<!--        </el-table>-->

<!--        &lt;!&ndash; 分页器 &ndash;&gt;-->
<!--        <el-pagination-->
<!--            background-->
<!--            layout="prev, pager, next, total"-->
<!--            :total="state.total"-->
<!--            :page-size="state.pageSize"-->
<!--            :current-page="state.currentPage"-->
<!--            @current-change="changePage"-->
<!--            class="pagination"-->
<!--        >-->
<!--          &lt;!&ndash; 使用具名插槽 #total 来显示分页信息 &ndash;&gt;-->
<!--          <template #total>-->
<!--            <span class="total-info">当前第 {{ state.currentPage }} 页，共 {{ state.total }} 条数据</span>-->
<!--          </template>-->
<!--        </el-pagination>-->
<!--      </el-card>-->

<!--      &lt;!&ndash; 添加分类的弹窗 &ndash;&gt;-->
<!--      <DialogAddCategory ref="addCate" v-model:visible="dialogVisible" :reload="getCategory"  />-->
<!--    </el-main>-->
<!--  </el-container>-->
<!--</template>-->

<!--<script setup>-->
<!--import { onMounted, reactive, ref } from 'vue';-->
<!--import { ElMessage } from 'element-plus';-->
<!--import axios from '@/utils/axios';-->
<!--import DialogAddCategory from '@/components/DialogAddCategory.vue';-->
<!--const dialogVisible = ref(false); // 控制浮窗显示-->
<!--const addCate = ref(null);-->

<!--const state = reactive({-->
<!--  loading: false,-->
<!--  tableData: [],-->
<!--  multipleSelection: [],-->
<!--  total: 0,-->
<!--  currentPage: 1,-->
<!--  pageSize: 10,-->
<!--  //type: 'add',-->
<!--});-->

<!--onMounted(() => {-->
<!--  getCategory();-->
<!--});-->

<!--const getCategory = () => {-->
<!--  state.loading = true;-->
<!--  axios.get('/categories/get', {-->
<!--    params: {-->
<!--      pageNumber: state.currentPage,-->
<!--      pageSize: state.pageSize,-->
<!--    },-->
<!--  }).then(res => {-->
<!--    state.tableData = res.list;-->
<!--    state.total = res.totalCount;-->
<!--    state.loading = false;-->
<!--  });-->
<!--};-->

<!--const changePage = (val) => {-->
<!--  state.currentPage = val;-->
<!--  getCategory();-->
<!--};-->

<!--const handleAdd = () => {-->
<!--  //state.type = 'add';-->
<!--  dialogVisible.value = true; // 打开浮窗-->
<!--};-->

<!--const handleEdit = (id) => {-->
<!--  //state.type = 'edit';-->
<!--  dialogVisible.value = true; // 打开浮窗-->
<!--  // 在浮窗中设置为编辑模式并加载数据-->
<!--  addCate.value.open(id);-->
<!--};-->

<!--const handleSelectionChange = (val) => {-->
<!--  state.multipleSelection = val;-->
<!--};-->

<!--const handleDelete = () => {-->
<!--  if (!state.multipleSelection.length) {-->
<!--    ElMessage.error('请选择项');-->
<!--    return;-->
<!--  }-->
<!--  axios.delete('/categories/del', {-->
<!--    data: {-->
<!--      ids: state.multipleSelection.map(i => i.categoryId),-->
<!--    },-->
<!--  }).then(() => {-->
<!--    ElMessage.success('删除成功');-->
<!--    getCategory();-->
<!--  });-->
<!--};-->

<!--const handleDeleteOne = (id) => {-->
<!--  axios.delete('/categories/del', {-->
<!--    data: {-->
<!--      ids: [id],-->
<!--    },-->
<!--  }).then(() => {-->
<!--    ElMessage.success('删除成功');-->
<!--    getCategory();-->
<!--  });-->
<!--};-->
<!--</script>-->

<!--<style scoped>-->
<!--/* 使卡片样式更简洁 */-->
<!--.category-container {-->
<!--  border-radius: 8px;-->
<!--  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);-->
<!--}-->

<!--/* 使表格宽度自适应并优化外观 */-->
<!--.category-table {-->
<!--  margin-top: 20px;-->
<!--  width: 100%;-->
<!--  overflow-x: auto;-->
<!--}-->

<!--/* 表格列标题样式 */-->
<!--.el-table .el-table__header {-->
<!--  background-color: #f5f5f5;-->
<!--  font-weight: 600;-->
<!--}-->

<!--/* 表格行的过渡效果 */-->
<!--.el-table .el-table__body {-->
<!--  background-color: #fff;-->
<!--}-->

<!--.el-table .el-table__row {-->
<!--  transition: background-color 0.3s;-->
<!--}-->

<!--.el-table .el-table__row:hover {-->
<!--  background-color: #f9f9f9;-->
<!--}-->

<!--/* 操作按钮的美化 */-->
<!--.action-button {-->
<!--  margin-right: 10px;-->
<!--  color: #409EFF;-->
<!--}-->

<!--.delete-button {-->
<!--  color: #F56C6C;-->
<!--}-->

<!--/* 分页器样式 */-->
<!--.pagination {-->
<!--  margin-top: 20px;-->
<!--  text-align: right;-->
<!--}-->

<!--.total-info {-->
<!--  font-size: 14px;-->
<!--  color: #666;-->
<!--}-->

<!--.el-pagination .el-pager li {-->
<!--  font-size: 14px;-->
<!--}-->

<!--.el-pagination .el-pager li.active {-->
<!--  color: #409EFF;-->
<!--}-->

<!--/* 按钮样式 */-->
<!--.el-button {-->
<!--  margin-top: 10px;-->
<!--}-->

<!--.header {-->
<!--  display: flex;-->
<!--  justify-content: space-between;-->
<!--  align-items: center;-->
<!--}-->
<!--</style>-->
<template>
  <el-container style="height: 100vh; padding: 20px;">
    <!-- 主体内容 -->
    <el-main class="main-content">
      <el-card class="category-container">
        <!-- 头部 -->
        <template #header>
          <div class="header">
            <!-- 增加分类按钮 -->
            <el-button type="primary" @click="handleAdd" size="small">添加分类</el-button>
            <!-- 批量删除按钮 -->
            <el-popconfirm
                title="确定删除所选项吗？"
                confirmButtonText="确定"
                cancelButtonText="取消"
                @confirm="handleDelete"
            >
              <template #reference>
                <el-button type="danger" size="small">批量删除</el-button>
              </template>
            </el-popconfirm>
          </div>
        </template>

        <!-- 商品分类列表 -->
        <el-table
            :data="state.tableData"
            :loading="state.loading"
            tooltip-effect="dark"
            class="category-table"
            @selection-change="handleSelectionChange"
            stripe
            border
        >
          <el-table-column
              type="selection"
              width="55"
          />
          <el-table-column
              prop="categoryName"
              label="分类名称"
              width="180"
          />
          <el-table-column
              prop="categoryRank"
              label="排序值"
              width="120"
          />
          <el-table-column
              prop="createTime"
              label="添加时间"
              width="200"
          />
          <el-table-column label="操作" width="220">
            <template #default="scope">
              <!-- 修改按钮 -->
              <el-button
                  size="small"
                  type="text"
                  @click="handleEdit(scope.row.categoryId)"
                  class="action-button"
              >
                修改
              </el-button>
              <!-- 删除按钮 -->
              <el-popconfirm
                  title="确定删除该分类吗？"
                  confirmButtonText="确定"
                  cancelButtonText="取消"
                  @confirm="handleDeleteOne(scope.row.categoryId)"
              >
                <template #reference>
                  <el-button size="small" type="text" class="delete-button">删除</el-button>
                </template>
              </el-popconfirm>
            </template>
          </el-table-column>
        </el-table>

        <!-- 分页器 -->
        <el-pagination
            background
            layout="prev, pager, next, total"
            :total="state.total"
            :page-size="state.pageSize"
            :current-page="state.currentPage"
            @current-change="changePage"
            class="pagination"
        >
          <template #total>
            <span class="total-info">当前第 {{ state.currentPage }} 页，共 {{ state.total }} 条数据</span>
          </template>
        </el-pagination>

      </el-card>
      <!-- 修改/添加分类弹窗 -->
      <el-dialog
          :title="state.type == 'add' ? '添加分类' : '修改分类'"
          v-model="state.visible"
          width="100px"

      >
        <el-form :model="state.ruleForm" :rules="state.rules" ref="formRef" label-width="100px" class="good-form">
          <el-form-item label="商品类别" prop="name" :label-width="'140px'">
            <el-input type="text" v-model="state.ruleForm.name" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="排序值" prop="rank">
            <el-input type="number" v-model="state.ruleForm.rank"></el-input>
          </el-form-item>
        </el-form>
        <template #footer>
          <span class="dialog-footer">
            <el-button @click="state.visible = false">取 消</el-button>
            <el-button type="primary" @click="submitForm">确 定</el-button>
          </span>
        </template>
      </el-dialog>

    </el-main>
  </el-container>
</template>

<script setup>
// eslint-disable-next-line no-unused-vars
import {onMounted, reactive, ref} from 'vue';
import {ElMessage} from 'element-plus';
import axios from '@/utils/axios';
// import {stat} from "@babel/core/lib/gensync-utils/fs";
const state = reactive({
  ruleForm: {
    name: 'wjfis',
    rank: '1213'
  },
  updateCategoryId:0,
  loading: false,
  tableData: [],
  multipleSelection: [],
  total: 0,
  currentPage: 1,
  pageSize: 10,
  type: 'add', // 操作类型

  visible:false,

  rules: {
    name: [
      { required: 'true', message: '名称不能为空', trigger: ['change'] }
    ],
    rank: [
      { required: 'true', message: '编号不能为空', trigger: ['change'] }
    ]
  },
});

onMounted(() => {
  getCategory();
});

const getCategory = () => {
  state.loading = true;
  axios.get('/categories/get', {
    params: {
      pageNumber: state.currentPage,
      pageSize: state.pageSize,
    },
  }).then(res => {
    state.tableData = res.list;
    state.total = res.totalCount;
    state.loading = false;
  });
};

const changePage = (val) => {
  state.currentPage = val;
  getCategory();
};

const handleAdd = () => {
  state.type = 'add'
  state.visible=true
};

const handleEdit = (id) => {
  state.type = 'edit'
  state.visible=true
  state.updateCategoryId=id
};

const handleSelectionChange = (val) => {
  state.multipleSelection = val;
};

const handleDelete = () => {
  if (!state.multipleSelection.length) {
    ElMessage.error('请选择项');
    return;
  }
  axios.delete('/categories/del', {
    data: {
      ids: state.multipleSelection.map(i => i.categoryId),
    },
  }).then(() => {
    ElMessage.success('删除成功');
    getCategory();
  });
};

const handleDeleteOne = (id) => {
  axios.delete('/categories/del', {
    data: {
      ids: [id],
    },
  }).then(() => {
    ElMessage.success('删除成功');
    getCategory();
  });
};

const submitForm=async ()=>{
  if(state.type==='add')
  {
    await addCategory()
  }else
  {
    await editCategory()
  }
}

const addCategory = async () => {
  try {
    await axios.post('/categories/add', {
      categoryName:state.ruleForm.name,
      categoryRank: state.ruleForm.rank,
    });
    ElMessage.success('添加成功');
    getCategory(); // 刷新分类列表
  } catch (error) {
    console.error('添加分类失败', error);
  }
};

const editCategory = async () => {
  try {
    await axios.put(`/categories/edit/${state.updateCategoryId}`, {
      categoryName:state.ruleForm.name,
      categoryRank: state.ruleForm.rank,
    });
    ElMessage.success('编辑成功');
    getCategory(); // 刷新分类列表
  } catch (error) {
    console.error('编辑分类失败', error);
  }
};


</script>

<style scoped>
/* 使卡片样式更简洁 */
.category-container {
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

/* 使表格宽度自适应并优化外观 */
.category-table {
  margin-top: 20px;
  width: 100%;
  overflow-x: auto;
}

/* 表格列标题样式 */
.el-table .el-table__header {
  background-color: #f5f5f5;
  font-weight: 600;
}

/* 表格行的过渡效果 */
.el-table .el-table__body {
  background-color: #fff;
}

.el-table .el-table__row {
  transition: background-color 0.3s;
}

.el-table .el-table__row:hover {
  background-color: #f9f9f9;
}

/* 操作按钮的美化 */
.action-button {
  margin-right: 10px;
  color: #409EFF;
}

.delete-button {
  color: #F56C6C;
}

/* 分页器样式 */
.pagination {
  text-align: right;
  margin-top: 20px;
}

/* 表单区域样式 */
.category-form-card {
  margin-top: 20px;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

.form-actions {
  margin-top: 20px;
  text-align: right;
}

.el-pagination{
  display: flex;
  justify-content: center;
  }

.el-dialog {
  background-color: #f0f0f0 !important; /* 强制应用背景色 */
  margin-left: 300px;
  .good-form{
    display: flex;
    justify-content: center;
  }
  .dialog-footer{
    display: flex;
    justify-content: center;
  }
  .title{
    display: flex;
    justify-content: center;
  }
}
</style>
