<template>
  <div class="main">
    <!-- 主页面容器 -->
    <el-container>
      <!-- 顶部复选框，用于选择商品种类 -->
      <el-header class="goods_header">
        <el-checkbox-group v-model="checkKinds" :min="0" :max="6" @change="handleCheckedKindsChange">
          <el-checkbox-button v-for="kind in kinds" :label="kind" :key="kind">
            {{ kind }}
          </el-checkbox-button>
        </el-checkbox-group>
      </el-header>
      <!-- 商品详情展示区 -->
      <el-main class="productDetail">
        <ul>
<!--           商品列表，每个商品是一个<li> -->
          <li v-for="v in currentPageItems" v-bind:key="v.goodsId" @click="goToDetailProduct(v.goodsId)">
            <!-- 商品图片 -->
            <el-image :src=(v.goodsCoverImg) alt="" class="img_Product"></el-image>
            <!-- 商品名称 -->
            <h4>{{ v.goodsName }}</h4>
            <!-- 商品价格 -->
            <p>{{ "￥" + v.sellingPrice }}</p>
            <!-- 添加至购物车按钮 -->
            <el-button @click="addToCart(v.goodsId)" type="danger">加入购物车</el-button>
          </li>

        </ul>
      </el-main>
      <!-- 分页组件 -->
      <el-pagination style="text-align: center;height: 50px"
                     background
                     layout="prev, pager, next,jumper"
                     :total=this.product_List.length
                     :current-page="currentPage"
                     :page-size="itemsPerPage"
                     @current-change="handlePageChange">
      </el-pagination>
    </el-container>
  </div>
</template>

<script>
import {getKindList,getGoodsByKindList} from "@/service/search_goods_service";
import {defineComponent} from "vue";
import example1 from "@/assets/example1.jpg";
import router from "@/router";
import {addCart} from "@/service/cart";
import {ElMessage } from "element-plus";
const kind_Options = ['笔记本电脑', '台式电脑', '平板电脑', '手机通信', '智能产品', '配件办公']
export default defineComponent({
  // 组件创建时加载本地的购物车数据
  created() {
    getKindList().then((response) => {
      this.kinds = response;
    });
    const cartData = localStorage.getItem('cart')
    if (cartData) {
      // 数据格式转换
      this.cart = JSON.parse(cartData)
    }
    // 添加商品列表信息到表单
    getGoodsByKindList([])
        .then(result => {
          // result.data = Result类对象，其data字段为Product类对象列表
          this.product_List = result
          this.handlePageChange(1)
        })
  },
  beforeUnmount() {
    // 更新本地数据
    localStorage.setItem('cart', JSON.stringify(this.cart))
  },
  data() {
    return {
      baseImageUrl: 'https://mall-image-store.cn-guangzhou.oss.aliyuncs.com/3.png',
      // 展示的原始商品列表
      product_List: [],
      // 选择的商品种类
      checkKinds: [],
      // 总共的商品种类
      kinds: [],
      // 购物车存放的商品信息
      cart: [],
      // 所有页数显示项目的总数量
      pageTotal: 100,
      // 当前页码
      currentPage: 1,
      // 每页显示的项目数量
      itemsPerPage: 8,
      // 当前页的项目数组
      currentPageItems: [],

      example1
    }
  },
  computed: {},

  methods: {
    goToDetailProduct(vid){
      router.push({path:'/products',query:{id:vid}})
    },
    /**
     * @brief 将选中种类的商品类别发送到后端，接收后端发送的所有属于这些类别的商品
     */

    handleCheckedKindsChange() {
      let kindList = this.checkKinds
      if (kindList.length === kind_Options.length) {
        // 如果选择的种类数组为全满，咋直接发送空数组表示不进行商品种类筛选
        kindList = []
      }
      // 根据商品种类更新后端筛选后的商品列表
      getGoodsByKindList(kindList)
          .then(result => {
            // result.data = Result类对象，其data字段为Product类对象列表
            this.product_List = result
            this.currentPageItems = this.getPaginatedItems(this.currentPage);
          })
    },
    /**
     * @brief 当页数发生改变就会自动调用
     * @param newPage
     */
    handlePageChange(newPage) {
      this.currentPage = newPage;
      this.currentPageItems = this.getPaginatedItems(newPage);
    },
    /**
     * @brief 将product_List里面的所有商品都拿出来切片只返回当前页面的商品
     * @param page 当前处于的页数
     * @returns {*[]} 返回的是当前页面的所有product
     */
    getPaginatedItems(page) {
      const start = (page - 1) * this.itemsPerPage;
      const end = start + this.itemsPerPage;
      return this.product_List.slice(start, end);
    },
    // 添加商品至购物车
    /**
     * @brief 检查购物车是否存在此商品，然后将其加入，可以看出这个购物车的商品要具有什么属性
     * @param product
     *
     */
    async addToCart(id) {
      // const existingProduct = this.cart.find(item => item.goodsId === product.goodsId);
      // if (existingProduct) {
      //   existingProduct.quantity++;
      // } else {
      //   this.cart.push({
      //     productID: product.goodsId,
      //     name: product.goodsName,
      //     kind: product.goodsKind,
      //     price: product.sellingPrice,
      //     goodsCount: 1,
      //     selected: false
      //   });
      // }
      const userSaved=JSON.parse(localStorage.getItem("userInfo"))
      const data= await addCart({ userID:userSaved.userID,goodsCount: 1, goodsId: id})
      console.log("data:")
      console.log(data)
      if(data.code=="201") {
        ElMessage.success("添加至购物车成功")
      }
      else
      {
        console.log(data.msg)
        ElMessage.error(data.msg)
        // ElNotification.error({
        //   title: '错误',
        //   message: data.msg, // 你从 data 中获取的错误信息
        // });
      }
    }

  }
}
)

</script>

<style scoped>
.goods_header{
  display: flex;
  justify-content: center;
}
/* 主页面容器样式 */
.main {
  display: flex; /* 将主容器设为弹性布局，使子元素可以按照弹性规则排列 */
  background-color: #f5f5f5; /* 设置主容器背景颜色为浅灰色 */
}

/* 商品列表样式 */
ul li {
  display: block; /* 将li元素从行内元素转换为块级元素，使其单独占据一行 */
  float: left; /* 将每个li浮动到左边，允许它们横向排列 */
  width: 290px; /* 设置每个商品块的固定宽度 */
  height: 385px; /* 设置每个商品块的固定高度 */
  background-color: #fff; /* 设置商品块背景为白色 */
  margin-right: 2%; /* 设置右侧外边距为容器宽度的2% */
  margin-bottom: 2%; /* 设置底部外边距为容器宽度的2% */
  vertical-align: top; /* 垂直对齐方式设为顶部对齐（防止浮动元素中基线错位） */
  padding: 10px 10px 10px 10px; /* 设置商品块内部四边的填充距离为10像素 */
  position: relative; /* 设置为相对定位，用于后续内部元素的定位调整 */
  overflow: hidden; /* 隐藏溢出的内容，避免内容超出商品块边界 */
  text-align: center;/*文字居中*/
}

/* 商品图片样式 */
.img_Product {
  width: 160px; /* 设置商品图片的固定宽度为160像素 */
  height: 160px; /* 设置商品图片的固定高度为160像素 */
  display: block; /* 将图片设为块级元素，确保其独占一行 */
  text-align: center; /* 设置图片文字内容（若有）的水平居中 */
  cursor: pointer; /* 鼠标悬停时显示为可点击的手型 */
  overflow: hidden; /* 隐藏溢出的图片内容，确保图片不会超出容器 */
  border-bottom: 1px solid #ebebeb; /* 在图片下方添加一条浅灰色边框 */
  padding-bottom: 25px; /* 图片下方增加额外间距 */
  margin: 15px auto 0; /* 设置顶部外边距为15像素，左右居中对齐（auto），底部外边距为0 */
  position: relative; /* 设置为相对定位，用于后续定位调整 */
}

</style>
