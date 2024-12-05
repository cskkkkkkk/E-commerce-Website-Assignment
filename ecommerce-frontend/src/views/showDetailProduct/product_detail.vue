
<template>
  <div class="product-detail">
    <s-header :name="'商品详情'"></s-header>
    <div class="detail-content">
      <div class="detail-swipe-wrap">
        <van-swipe class="my-swipe" indicator-color="#1baeae">
          <van-swipe-item  :key="index">
            <img :src="state.detail.goodsCoverImg" alt="">
          </van-swipe-item>
        </van-swipe>
      </div>
      <div class="product-info">
        <div class="product-title">
          {{ state.detail.goodsName || '' }}
        </div>
        <div class="product-desc">免邮费 顺丰快递</div>
        <div class="product-price">
          <span>¥{{ state.detail.sellingPrice || '' }}</span>
          <!-- <span>库存203</span> -->
        </div>
      </div>
      <div class="product-intro">
        <ul>
          <li>概述</li>
          <li>参数</li>
          <li>安装服务</li>
          <li>常见问题</li>
        </ul>
        <div class="product-content" >
          <!-- 判断 goodsDetailContent 是否是图片 URL -->
          <el-image v-if="isImageUrl(state.detail.goodsDetailContent)" :src="state.detail.goodsDetailContent" alt="Product Image">
          </el-image>
          <!-- 如果是 HTML 内容，使用 v-html 渲染 -->
          <div v-else v-html="state.detail.goodsDetailContent"></div>
        </div>
      </div>
    </div>
    <van-action-bar>
      <van-action-bar-icon icon="chat-o" text="客服" />
<!--      <van-action-bar-icon icon="cart-o" :badge="!cart.count ? '' : cart.count" @click="goTo()" text="购物车" />-->
      <van-action-bar-icon icon="cart-o"  @click="goTo()" text="购物车" />
      <van-action-bar-button type="warning" @click="handleAddCart" text="加入购物车" />
      <van-action-bar-button type="danger" @click="goToCart" text="立即购买" />
    </van-action-bar>
  </div>
</template>

<script setup>
import { reactive, onMounted, nextTick } from 'vue'
import { useRoute} from 'vue-router'
//import { useCartStore } from '@/stores/cart'
import {getDetail, userViewRecord} from '@/service/good'
import { addCart } from '@/service/cart'
import sHeader from '@/components/SimpleHeader.vue'
import router from '@/router/index'
import {ElMessage} from "element-plus";

const route = useRoute()
//const router = useRouter()
//const cart = useCartStore()

const state = reactive({
  detail: {

  }
})// goodsCarouselList: []

onMounted(async () => {
  const { id } = route.query
  const data = await getDetail(id)
  state.detail = data
  console.log(state.detail)
  const savedUserInfo = JSON.parse(localStorage.getItem('userInfo'));
  //发送浏览记录，让后端保存某个用户的浏览记录
  const param={userID: savedUserInfo.userID ,goodsId:id}
  await userViewRecord(param)
  //cart.updateCart()
})

nextTick(() => {
  // 一些和DOM有关的东西
  const content = document.querySelector('.detail-content')
  content.scrollTop = 0
})

// const goBack = () => {
//   router.go(-1)
// }
//返回购物车
const goTo = () => {
  router.push({ path: '/top/cart' })
}

const handleAddCart = async () => {
  const userSaved=JSON.parse(localStorage.getItem("userInfo"))
   const data1=await addCart({userID:userSaved.userID, goodsCount: 1, goodsId: state.detail.goodsId })
   if(data1.code=="201") {
     ElMessage.success("添加至购物车成功");//showSuccessToast('添加成功')
   }
  else
   {
    ElMessage.error(data1.msg)
   }
  //cart.updateCart()
}

const goToCart = async () => {
  const userSaved=JSON.parse(localStorage.getItem("userInfo"))
  //data是一个字符串表示该商品在购物车中的ID
  console.log("id:")
  console.log(state.detail.goodsId)
  console.log({ userID:userSaved.userID,goodsCount: 1, goodsId: state.detail.goodsId })

  const data=await addCart({ userID:userSaved.userID,goodsCount: 1, goodsId: state.detail.goodsId })
  //cart.updateCart()
  const params = JSON.stringify(data)
  router.push({ path: '/pay' ,query:{ cartItemIds:params}})//进入订单页面
}
// 判断 URL 是否是图片
// 判断 URL 是否是图片
function isImageUrl(url) {
  // 确保 url 是字符串并且以 http 或 https 开头
  if (typeof url === 'string' && (url.startsWith('http://') || url.startsWith('https://'))) {
    // 判断 url 中是否包含图片文件扩展名
    return true;
  }
  return false;
}


</script>

<style lang="less">
@import '../../common/mixin';
.product-detail {
  .detail-header {
    position: fixed;
    top: 0;
    left: 0;
    z-index: 10000;
    .fj();
    .wh(100%, 44px);
    line-height: 44px;
    padding: 0 10px;
    .boxSizing();
    color: #252525;
    background: #fff;
    border-bottom: 1px solid #dcdcdc;
    .product-name {
      font-size: 14px;
    }
  }
  .detail-content {
    height: calc(100vh - 50px);
    overflow: hidden;
    overflow-y: auto;
    .detail-swipe-wrap {
      .my-swipe .van-swipe-item {
        img {
          width: 100%;
          // height: 300px;
        }
      }
    }
    .product-info {
      padding: 0 10px;
      .product-title {
        font-size: 18px;
        text-align: left;
        color: #333;
      }
      .product-desc {
        font-size: 14px;
        text-align: left;
        color: #999;
        padding: 5px 0;
      }
      .product-price {
        .fj();
        span:nth-child(1) {
          color: #F63515;
          font-size: 22px;
        }
        span:nth-child(2) {
          color: #999;
          font-size: 16px;
        }
      }
    }
    .product-intro {
      width: 100%;
      padding-bottom: 50px;
      ul {
        .fj();
        width: 100%;
        margin: 10px 0;
        li {
          flex: 1;
          padding: 5px 0;
          text-align: center;
          font-size: 15px;
          border-right: 1px solid #999;
          box-sizing: border-box;
          &:last-child {
            border-right: none;
          }
        }
      }
      .product-content {
        padding: 0 20px;
        img {
          width: 100%;
        }
      }
    }
  }
  .van-action-bar-button--warning {
    background: linear-gradient(to right,#6bd8d8, @primary)
  }
  .van-action-bar-button--danger {
    background: linear-gradient(to right, #0dc3c3, #098888)
  }
}
</style>
