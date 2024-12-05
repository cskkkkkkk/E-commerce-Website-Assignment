<template>
  <div class="main-container" style="width: 100%; height: 100%">
    <!-- 商品分类和轮播图 -->
    <el-main>
      <el-row>
        <el-col :span="4">
          <!-- 商品分类侧边栏 -->
          <el-menu class="kinds-menu" active-text-color="#ffd04b" router>
            <el-menu-item-group title="商品分类">
<!--              <el-menu-item index="/top/search_goods">笔记本电脑</el-menu-item>-->
<!--              <el-menu-item index="/top/search_goods">台式电脑</el-menu-item>-->
<!--              <el-menu-item index="/top/search_goods">平板电脑</el-menu-item>-->
<!--              <el-menu-item index="/top/search_goods">手机通信</el-menu-item>-->
<!--              <el-menu-item index="/top/search_goods">智能产品</el-menu-item>-->
<!--              <el-menu-item index="/top/search_goods">配件办公</el-menu-item>-->
              <el-menu-item
                  v-for="(category, index) in categories"
                  :key="index"
                  :index="'/top/search_goods'"
              >
                {{ category }}
              </el-menu-item>
            </el-menu-item-group>
          </el-menu>
        </el-col>

        <el-col :span="20">
          <!-- 轮播图 -->
          <div class="carousel-container">
            <el-carousel height="400px" trigger="click" :interval="2000">
              <el-carousel-item>
                <el-image :src="require('@/assets/example1.jpg')"></el-image>
              </el-carousel-item>
              <el-carousel-item>
                <el-image :src="require('@/assets/example2.png')"></el-image>
              </el-carousel-item>
              <el-carousel-item>
                <el-image :src="require('@/assets/carousel/1.png')"></el-image>
              </el-carousel-item>
              <el-carousel-item>
                <el-image :src="require('@/assets/carousel/2.png')"></el-image>
              </el-carousel-item>
<!--              <el-carousel-item v-for="item in ImageNameList" :key="item">-->
<!--&lt;!&ndash;                <el-image :src="baseImageUrl + item"></el-image>&ndash;&gt;-->
<!--                <el-image :src=require(item)></el-image>-->
<!--              </el-carousel-item>-->
            </el-carousel>
          </div>

          <div class="good" :style="{paddingTop: '100px'}">
            <header class="good-header">最新推荐</header>
            <van-skeleton title :row="3" :loading=this.loading>
              <div class="good-box">
                <div class="good-item" v-for="(item, index) in recommend" :key="index">
                  <img :src=item  />
                  <div class="good-desc">
                    <div class="title">商品{{index+1}}</div>
                    <!--                    <div class="price">2000</div>-->
                  </div>
                </div>
<!--                <div class="good-item" v-for="item in recommends" :key="item.goodsId" @click="goToDetail(item)">-->
<!--                  <img :src="item.goodsCoverImg" alt="">-->
<!--                  <div class="good-desc">-->
<!--                    <div class="title">{{ item.goodsName }}</div>-->
<!--                    <div class="price">¥ {{ item.sellingPrice }}</div>-->
<!--                  </div>-->
<!--                </div>-->
              </div>
            </van-skeleton>
          </div>

          <div class="good" :style="{paddingTop: '100px'}">
            <header class="good-header">热门商品</header>
            <van-skeleton title :row="3" :loading=this.loading>
              <div class="good-box">
<!--                <div class="good-item">-->
<!--                  <img :src="require('@/assets/example1.jpg')"/>-->
<!--                  <div class="good-desc">-->
<!--                    <div class="title">苹果平板</div>-->
<!--                    <div class="price">2000</div>-->
<!--                  </div>-->
<!--                </div>-->
                <div class="good-item" v-for="(item, index) in hot" :key="index">
                  <img :src=item  />
                  <div class="good-desc">
                    <div class="title">商品{{index+1}}</div>
<!--                    <div class="price">2000</div>-->
                  </div>
                </div>
<!--                <div class="good-item" v-for="item in hots" :key="item.goodsId" @click="goToDetail(item)">-->
<!--                  <img :src="item.goodsCoverImg" alt="">-->
<!--                  <div class="good-desc">-->
<!--                    <div class="title">{{ item.goodsName }}</div>-->
<!--                    <div class="price">¥ {{ item.sellingPrice }}</div>-->
<!--                  </div>-->
<!--                </div>-->
              </div>
            </van-skeleton>
          </div>

        </el-col>
      </el-row>
    </el-main>

  </div>
</template>

<script>
import { defineComponent } from "vue";
import {getHotGoods,getCarousel,getRecommondGoods,getGoodsKinds} from "@/service/mall_main_service";
import router from "@/router";



export default defineComponent({
  created() {
    this.getUserInfo();
    this.getUsername();
    this.getAccountType();
    this.fetchCategories();
    //this.fetchCarouselImages();
    //this.RecommondGoodsfrom();
    //this.gethotsGoods();

  },
  mounted()
  {
    this.loading=false;
  },


  data() {
    return {
      activeIndex: '1',
      categories: [], // 商品分类数据
      ImageNameList: [], // 轮播图图片文件名列表
      baseImageUrl: 'http://localhost:8080/images/download/carousel/',
      recommends: [],
      hots: [],
      // recommend:['@/assets/recommend/1.png','@/assets/recommend/2.png','@/assets/recommend/3.png','@/assets/recommend/4.png'],
      // hot:['@/assets/hots/1.png','@/assets/hots/2.png','@/assets/hots/3.png','@/assets/hots/4.png'],
      recommend: [
        require('@/assets/recommend/1.png'),
        require('@/assets/recommend/2.png'),
        require('@/assets/recommend/3.png'),
        require('@/assets/recommend/4.png'),
        require('@/assets/recommend/5.png'),
        require('@/assets/recommend/6.png'),
        require('@/assets/recommend/7.png'),
      ],
      hot: [
        require('@/assets/hots/1.png'),
        require('@/assets/hots/2.png'),
        require('@/assets/hots/3.png'),
        require('@/assets/hots/4.png'),
        require('@/assets/hots/5.png'),
        require('@/assets/hots/6.png'),
      ],
      loading: true
    };
  },

  methods: {
    getUserInfo() {
      return JSON.parse(localStorage.getItem('userInfo'));
    },
    // 用户名字
    getUsername() {
      return this.getUserInfo ? this.getUserInfo.userName : "用户未登录";
    },
    getAccountType() {
      return this.getUserInfo ? this.getUserInfo.accountType : null;
    },
    // 获取商品分类列表
    fetchCategories() {
      getGoodsKinds().then((response) => {
        this.categories = response;
        console.log(response)
      });
    },
    // 获取轮播图图片
    fetchCarouselImages() {
      getCarousel().then((result) => {
        this.ImageNameList = result;
      });
    },

    RecommondGoodsfrom()
    {
      getRecommondGoods().then((result)=>{
        this.recommends=result.data;
      });
    },
    gethotsGoods()
    {
      getHotGoods().then((result)=>{
        this.hots=result.data;
      });
    },

    goToDetail(item){
      router.push({ path: `/products/${item.goodsId}` })
    }
  },

});
</script>

<style scoped>
.main-container {
  display: flex;
  flex-direction: column;
  height: 100%;
}

.kinds-menu {
  width: 100%;
  height: 100%;
}

.carousel-container {
  width: 100%;
  text-align: center;
}

.good {
  .good-header {
    background: #f9f9f9;
    height: 50px;
    line-height: 50px;
    text-align: center;
    font-size: 25px;
    font-weight: 500;
    color: lightgreen;
  }
  .good-box {
    display: flex;
    justify-content: flex-start;
    flex-wrap: wrap;
    .good-item {
      box-sizing: border-box;
      width: 50%;
      border-bottom: 1PX solid #e9e9e9;
      padding: 10px 10px;
      img {
        display: block;
        width: 120px;
        height: auto;
        margin: 0 auto;
      }
      .good-desc {
        text-align: center;
        font-size: 20px;
        padding: 10px 0;
        .title {
          color: #222333;
        }
        .price {
          color: lightgreen;
        }
      }
    }
  }
}
</style>
