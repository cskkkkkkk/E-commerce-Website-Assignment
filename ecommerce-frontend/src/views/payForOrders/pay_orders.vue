<template>
  <div class="create-order">
    <!-- 主容器，用于居中对齐整个页面内容 -->
    <el-card class="order-card">
      <!-- 地址信息区域 -->
      <div class="address-wrap">
        <!-- 用户姓名和电话 -->
        <div class="name" >
          <span>{{ state.address.userName }}</span>
          <!-- 显示用户电话 -->
          <span>{{ state.address.userPhone }}</span>
        </div>
        <!-- 用户地址 -->
        <div class="address">
          {{ state.address.receiveAddr}}
        </div>
        <!-- 箭头图标 -->
        <el-icon class="arrow">
          <arrow-right />
        </el-icon>
      </div>
      <!-- 商品信息区域 -->
      <div class="good">
        <div class="good-item" v-for="(item, index) in state.cartList" :key="index">
          <!-- 商品图片 -->
          <div class="good-img">
            <img :src="item.goodsCoverImg" alt="" />
          </div>
          <!-- 商品描述 -->
          <div class="good-desc">
            <!-- 商品标题和数量 -->
            <div class="good-title">
              <span>{{ item.goodsName }}</span>
              <span>x{{ item.goodsCount }}</span>
            </div>
            <!-- 商品价格 -->
            <div class="good-btn">
              <div class="price">¥{{ item.sellingPrice }}</div>
            </div>
          </div>
        </div>
      </div>
      <!-- 支付信息区域 -->
      <div class="pay-wrap">
        <!-- 商品总金额 -->
        <div class="price">
          <span>商品金额</span>
          <span>¥{{ total }}</span>
        </div>
        <!-- 创建订单按钮 -->
        <el-button @click="handleCreateOrder" class="pay-btn" type="primary" block>生成订单</el-button>
      </div>
    </el-card>

    <!-- 支付方式弹窗 -->
    <el-dialog v-model="state.showPay" title="选择支付方式" width="30%" @close="close">
      <!-- 支付选项 -->
      <div class="payment-options">
        <el-button type="primary" class="button1" block @click="handlePayOrder(1)">支付宝支付</el-button>
        <el-button type="success" class="button2" block @click="handlePayOrder(2)"> 微信支付</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script setup>
import { reactive, onMounted, computed } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import {ElMessage} from "element-plus";
// 引入服务接口
import { getByCartItemIds } from '@/service/cart'
import { createOrder, payOrder } from '@/service/pay_service'
import { setLocal, getLocal } from '@/common/utils/utils'

const router = useRouter()
const route = useRoute()

// 定义响应式状态
const state = reactive({
  cartList: [], // 购物车商品列表
  address: "", // 用户地址信息
  userName: "",
  userPhone: "",
  showPay: false, // 是否显示支付弹窗
  orderNo: '', // 订单号
  cartItemIds: [], // 购物车商品ID
  tmp:[],
  totalPrice:0
})

// 组件挂载时初始化数据
onMounted(() => {
  init()
})

// 初始化函数
const init = async () => {
  const { cartItemIds } = route.query
  const _cartItemIds = cartItemIds ? JSON.parse(cartItemIds) : JSON.parse(getLocal('cartItemIds'))
  setLocal('cartItemIds', JSON.stringify(_cartItemIds))
  const list = await getByCartItemIds({ cartItemIds: _cartItemIds.join(',') })
  //const { data: address } = addressId ? await getAddressDetail(addressId) : await getDefaultAddress()
  // if (!address) {
  //   router.push({ path: '/address' })
  //   return
  // }
  state.cartList = list
  // state.address = address
  //取出本地
  const user=JSON.parse(localStorage.getItem("userInfo"));
  state.address=user.address;
  state.userName=user.userName;
  state.userPhone=user.phone;
}

// // 跳转到地址选择页面
// const goTo = () => {
//   router.push({ path: '/address', query: { cartItemIds: JSON.stringify(state.cartItemIds), from: 'create-order' }})
// }

// 创建订单
const handleCreateOrder = async () => {

  state.tmp=state.cartList.map(item => item.cartItemId)
  const tmpString=state.tmp.join(',')

  const user=JSON.parse(localStorage.getItem("userInfo"))
  state.totalPrice=total
  const params = {
    //addressId: state.address.addressId,
    cartItemIds: tmpString,
    userID: user.userID,
    orderStatusString: 0,
    totalPrice:state.totalPrice
  }

  //创建一个订单，要加入到订单表里面
  const  data  = await createOrder(params)

  //值为空，表示已经处理完一个订单了已经支付了
  setLocal('cartItemIds', '')
  state.orderNo = data

  state.showPay = true

}

// 关闭弹窗,返回到购物车页面就可以了 这个界面/order要替换掉
const close = () => {
  router.push({ path: '/top/cart' })
}

// 支付订单
const handlePayOrder = async (type) => {
  await payOrder({ orderNo: state.orderNo, payType: type, orderStatusString:1})
  ElMessage.success("支付成功")
  router.push({ path: '/top/cart' })
}

// 计算商品总金额
const total = computed(() => {
  let sum = 0
  state.cartList.forEach(item => {
    sum += item.goodsCount * item.sellingPrice
  })
  return sum
})
</script>

<style lang="less" scoped>
.create-order {
  display: flex; /* 使用 flex 居中对齐 */
  justify-content: center; /* 水平居中 */
  align-items: center; /* 垂直居中 */
  min-height: 100vh; /* 页面高度 */
  background-color: #f9f9f9; /* 背景颜色 */

  .order-card {
    width: 50%; /* 卡片宽度 */
    padding: 20px; /* 内边距 */
  }

  .address-wrap {
    margin-bottom: 20px; /* 下边距 */
    background: #fff; /* 背景颜色 */
    padding: 15px; /* 内边距 */
    border: 1px solid #ebeef5; /* 边框 */
    border-radius: 8px; /* 圆角 */

    .name {
      font-size: 16px; /* 字体大小 */
      margin-bottom: 10px; /* 下边距 */
    }

    .address {
      color: #666; /* 文字颜色 */
      margin-bottom: 10px; /* 下边距 */
    }

    .arrow {
      font-size: 20px; /* 图标大小 */
      color: #ccc; /* 图标颜色 */
      float: right; /* 浮动到右边 */
    }
  }

  .good {
    margin-top: 20px; /* 上边距 */

    .good-item {
      display: flex; /* Flex 布局 */
      align-items: center; /* 垂直居中 */
      margin-bottom: 15px; /* 下边距 */
      padding: 10px; /* 内边距 */
      border: 1px solid #ebeef5; /* 边框 */
      border-radius: 8px; /* 圆角 */
      background: #fff; /* 背景颜色 */

      .good-img img {
        width: 80px; /* 图片宽度 */
        height: 80px; /* 图片高度 */
        border-radius: 8px; /* 图片圆角 */
      }

      .good-desc {
        flex: 1; /* 填满剩余空间 */
        padding: 0 10px; /* 水平内边距 */

        .good-title {
          display: flex; /* Flex 布局 */
          justify-content: space-between; /* 两端对齐 */
          font-size: 16px; /* 字体大小 */
          font-weight: bold; /* 字体加粗 */
        }

        .price {
          color: #e74c3c; /* 价格文字颜色 */
        }
      }
    }
  }

  .pay-wrap {
    margin-top: 20px; /* 上边距 */

    .price {
      display: flex; /* Flex 布局 */
      justify-content: space-between; /* 两端对齐 */
      font-size: 18px; /* 字体大小 */
      margin-bottom: 10px; /* 下边距 */
    }

    .pay-btn {
      width: 100%; /* 按钮宽度 */
    }
  }

  .payment-options {
    display: flex; /* Flex 布局 */
    flex-direction: column; /* 垂直排列 */
    gap: 15px; /* 子元素间距 */
    justify-content: center;
    align-items: center; /* 水平居中 */
    button {
      width: 80%; /* 按钮宽度 */
    }
  }

  .button1
  {
    margin-left: 10px;
  }

  .button2
  {
    margin-left: 10px;
  }
}
</style>
