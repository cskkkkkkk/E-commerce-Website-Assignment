
<template>
  <div class="add">
    <el-card class="add-container">
      <el-form :model="state.goodForm" :rules="state.rules" ref="goodRef" label-width="120px" class="goodForm">
        <el-form-item required label="商品分类" style="margin-top: 20px">
          <el-cascader :placeholder="state.defaultCate" style="width: 2%;height: 5%" :props="state.category" :lazy="true" @change="handleChangeCate" v-model="state.categoryName"></el-cascader>
        </el-form-item>
        <el-form-item label="商品名称" prop="goodsName" style="margin-top: 20px">
          <el-input style="width: 100%" v-model="state.goodForm.goodsName" placeholder="请输入商品名称"></el-input>
        </el-form-item>
        <el-form-item label="商品简介" prop="goodsIntro" style="margin-top: 20px">
          <el-input style="width: 100%" type="textarea" v-model="state.goodForm.goodsIntro" placeholder="请输入商品简介(100字)" rows="3"></el-input>
        </el-form-item>
        <el-form-item label="商品价格" prop="originalPrice" style="margin-top: 20px">
          <el-input type="number" min="0" style="width: 100%" v-model="state.goodForm.originalPrice" placeholder="请输入商品价格"></el-input>
        </el-form-item>
        <el-form-item label="商品售卖价" prop="sellingPrice" style="margin-top: 20px">
          <el-input type="number" min="0" style="width: 100%" v-model="state.goodForm.sellingPrice" placeholder="请输入商品售价"></el-input>
        </el-form-item>
        <el-form-item label="商品库存" prop="stockNum" style="margin-top: 20px">
          <el-input type="number" min="0" style="width: 100%" v-model="state.goodForm.stockNum" placeholder="请输入商品库存"></el-input>
        </el-form-item>
        <el-form-item label="商品标签" prop="tag" style="margin-top: 20px">
          <el-input style="width: 100%" v-model="state.goodForm.tag" placeholder="请输入商品小标签"></el-input>
        </el-form-item>
        <el-form-item label="上架状态" prop="goodsSellStatus" style="margin-top: 20px">
          <el-radio-group v-model="state.goodForm.goodsSellStatus">
            <el-radio label="0">上架</el-radio>
            <el-radio label="1">下架</el-radio>
          </el-radio-group>
        </el-form-item >
        <el-form-item required label="商品主图" prop="goodsCoverImg" style="margin-top: 40px">
          <el-upload
              class="avatar-uploader"
              :action="state.uploadImgServer"
              accept="jpg,jpeg,png"
              :headers="{
              token: state.token
            }"
              :show-file-list="false"
              :before-upload="handleBeforeUpload"
              :on-success="handleUrlSuccess"
              style="margin-top: 20px;margin-left: 50px"
          >
            <img style="width: 100px; height: 100px; border-radius: 8px; object-fit: cover;margin-top: 200px" v-if="state.goodForm.goodsCoverImg" :src="state.goodForm.goodsCoverImg" class="avatar">
            <el-icon style="margin-top: 150px;width: 5%;height: 5%" v-else class="avatar-uploader-icon"><Plus /></el-icon>
          </el-upload>
        </el-form-item >
        <el-form-item label="详情内容" style="margin-top: 100px">
          <div ref='editor'></div>
        </el-form-item>
        <el-form-item style="margin-top: 20px">
          <el-button type="primary" size="large" @click="submitAdd()">{{ state.id ? '立即修改' : '立即创建' }}</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import { reactive, ref, onMounted, onBeforeUnmount, getCurrentInstance } from 'vue'
import WangEditor from 'wangeditor'
import axios from '@/utils/axios'
import { ElMessage } from 'element-plus'
import { useRoute, useRouter } from 'vue-router'
import { localGet, uploadImgServer, uploadImgsServer } from '@/utils'

// eslint-disable-next-line no-unused-vars
const { proxy } = getCurrentInstance()
const editor = ref(null)
const goodRef = ref(null)
const route = useRoute()
const router = useRouter()
const { id } = route.query
const state = reactive({
  uploadImgServer,
  token: localGet('token_admin') || '',
  id: id,
  defaultCate: '',
  categoryList:[],
  goodForm: {
    goodsName: '',
    goodsDetailContent: '',
    originalPrice: '',
    sellingPrice: '',
    stockNum: '',
    goodsSellStatus: '0',
    goodsCoverImg: '',
    tag: ''
  },
  rules: {
    goodsName: [
      { required: 'true', message: '请填写商品名称', trigger: ['change'] }
    ],
    originalPrice: [
      { required: 'true', message: '请填写商品价格', trigger: ['change'] }
    ],
    sellingPrice: [
      { required: 'true', message: '请填写商品售价', trigger: ['change'] }
    ],
    stockNum: [
      { required: 'true', message: '请填写商品库存', trigger: ['change'] }
    ],
  },
  categoryName:[],
  categoryNameSubmit:'',
  categoryId: '',
  category: {
    lazy: true,
    lazyLoad(node, resolve) {
      axios.get('/categories', {
        params: {
          pageNumber: 1,
          pageSize: 1000,
        }
      }).then(res => {

        const list = res
        state.categoryList = list
        const nodes = list.map(item => ({
          value: item.categoryId,
          label: item.categoryName,
          leaf: true
        }))
        resolve(nodes)
      })
    }
  }
})
let instance
onMounted(() => {
  //配置 WangEditor 富文本编辑器的上传图片功能
  instance = new WangEditor(editor.value)
  //控制是否显示插入图片链接的功能
  instance.config.showLinkImg = false
  //控制是否显示插入图片时的替代文本
  instance.config.showLinkImgAlt = false
  //控制是否显示插入图片时指定图片链接的功能。
  instance.config.showLinkImgHref = false
  //限制上传的图片文件的最大尺寸。
  instance.config.uploadImgMaxSize = 2 * 1024 * 1024 // 2M
  // 设置上传文件的字段名称
  instance.config.uploadFileName = 'file'
  // 设置上传图片时所需的 HTTP 请求头。
  instance.config.uploadImgHeaders = {
    token: state.token
  }
  //设置图片上传的服务器 URL。
  instance.config.uploadImgServer = uploadImgsServer
  Object.assign(instance.config, {
    onchange() {
      console.log('change')
    },
  })
  instance.create()
  if (id) {
    axios.get(`/goods/${id}`).then(res => {
      state.goodForm = {
        goodsName: res.goodsName,
        goodsIntro: res.goodsIntro,
        originalPrice: res.originalPrice,
        sellingPrice: res.sellingPrice,
        stockNum: res.stockNum,
        goodsSellStatus: String(res.goodsSellStatus),
        goodsCoverImg: res.goodsCoverImg,
        tag: res.tag
      }
      state.categoryId = res.goodsCategoryId

      //state.defaultCate = `${firstCategory.categoryName}/${secondCategory.categoryName}/${thirdCategory.categoryName}`
      //instance.txt.html(goods.goodsDetailContent) 会把这个 HTML 内容加载到 WangEditor 编辑器中，让用户能够看到和编辑商品的详细信息。
      if (instance) {
        // 初始化商品详情 html
        instance.txt.html(res.goodsDetailContent)
      }
    })
  }
})
onBeforeUnmount(() => {
  instance.destroy()
  instance = null
})
const submitAdd = () => {
  goodRef.value.validate((valid) => {
    if (valid) {
      let httpOption = axios.post

      let params = {
        goodsCategoryId: state.categoryId,
        goodsCoverImg: state.goodForm.goodsCoverImg,
        goodsKind:state.categoryNameSubmit,
        goodsDetailContent: instance.txt.html(),
        goodsIntro: state.goodForm.goodsIntro,
        goodsName: state.goodForm.goodsName,
        goodsSellStatus: state.goodForm.goodsSellStatus,
        originalPrice: state.goodForm.originalPrice,
        sellingPrice: state.goodForm.sellingPrice,
        stockNum: state.goodForm.stockNum,
        tag: state.goodForm.tag
      }
      if (id) {
        params.goodsId = id
        httpOption = axios.put
      }
      httpOption('/goods', params).then(() => {
        ElMessage.success(id ? '修改成功' : '添加成功')
        router.push({ path: '/good' })
      })
    }
  })
}
const handleBeforeUpload = (file) => {
  const sufix = file.name.split('.')[1] || ''
  console.log("..........................")
  if (!['jpg', 'jpeg', 'png'].includes(sufix)) {
    ElMessage.error('请上传 jpg、jpeg、png 格式的图片')
    return false
  }
}
const handleUrlSuccess = (val) => {
  state.goodForm.goodsCoverImg = val.data || ''
}
const handleChangeCate = () => {
  state.categoryId = state.categoryName[0]
  state.categoryNameSubmit=state.categoryList.find(item=> item.categoryId===state.categoryId).categoryName
}
</script>

<style scoped>
.add {
  display: flex;
  justify-content: center;
  padding: 20px;
  background-color: #f7f9fb;
}

.add-container {
  flex: 1;
  background-color: #ffffff;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  padding: 20px;  /* 添加内边距避免内容和边框紧贴 */
  margin: 0 auto;  /* 确保容器居中 */
}

.avatar-uploader {
  border: 2px dashed #dcdfe6;
  padding: 20px;
  width: 100px;
  height: 100px;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #f5f5f5;
  border-radius: 8px;
}

.avatar-uploader-icon {
  font-size: 40px;
  color: #c0c4cc;
}

.el-input,
.el-select,
.el-cascader {
  width: 100%;
}

.el-form-item {
  margin-bottom: 20px;
}

.el-button {
  width: 100%;
  height: 40px;
}
</style>
