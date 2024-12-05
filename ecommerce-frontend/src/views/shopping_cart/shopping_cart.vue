<template>
  <div class="cart-box">
    <s-header :name="'购物车'" :noback="true"></s-header>

    <div class="cart-body">
      <!-- 商品列表 -->
      <el-checkbox-group v-model="state.result" @change="groupChange">
        <div v-for="item in state.list" :key="item.cartItemId" class="good-item">
          <el-card :body-style="{ padding: '10px' }" class="cart-item-card">
            <el-row type="flex" justify="space-between">
              <el-col :span="4">
                <el-checkbox :value="item.cartItemId"/>
              </el-col>
              <el-col :span="5">
                <div class="good-img">
                  <img :src="item.goodsCoverImg" alt="商品图片" />
                </div>
              </el-col>
              <el-col :span="12">
                <div class="good-desc">
                  <div class="good-title">
                    <span>{{ item.goodsName }}</span>
                    <span>x{{ item.goodsCount }}</span>
                  </div>
                  <div class="good-btn">
                    <div class="price">¥{{ item.sellingPrice }}</div>
                    <el-input-number
                        v-model="item.goodsCount"
                        :min="1"
                        :max="5"
                        :step="1"
                        :name="item.cartItemId"
                        size="small"
                        class="goods-count-stepper"
                        @change="onChange(item.goodsCount,item.cartItemId)"

                    />
                  </div>
                </div>
              </el-col>
              <el-col :span="3" style="width: 30px;height: 30px">
                <el-button
                    size="small"
                    type="danger"
                    icon="el-icon-delete"
                    class="delete-button"
                    @click="deleteGood(item.cartItemId)"

                >
                  删除
                </el-button>
              </el-col>
            </el-row>
          </el-card>
        </div>
      </el-checkbox-group>
    </div>

    <!-- 结算栏 -->
    <el-footer v-if="state.list.length > 0" class="submit-all">
      <el-checkbox v-model="state.checkAll" @change="allCheck">全选</el-checkbox>
      <div class="price-summary">
        <span>总计：¥{{ total }}</span>
        <el-button
            type="primary"
            class="submit-btn"
            @click="onSubmit"
        >结算</el-button>
      </div>
    </el-footer>

    <!-- 空购物车提示 -->
    <div class="empty" v-if="!state.list.length">
      <img class="empty-cart" src="https://s.yezgea02.com/1604028375097/empty-car.png" alt="空购物车">
      <div class="title">购物车空空如也</div>
      <el-button round type="primary" @click="goTo" block>前往选购</el-button>
    </div>

    <nav-bar></nav-bar>
  </div>
</template>

<script setup>
import { reactive, onMounted, computed } from 'vue'
import { useRouter } from 'vue-router'
import { ElLoading, ElMessage } from 'element-plus'
import { getCart, deleteCartItem, modifyCart } from '@/service/cart'

const router = useRouter()

// 状态管理
const state = reactive({
  checked: false,
  list: [],
  all: false,
  result: [],
  checkAll: true
  // 全局的 loading 控制变量

})

onMounted(() => {
  init()
})

// 全局的 loading 控制变量
let loadingInstance = null;
const showLoadingToast = (options) => {
  loadingInstance = ElLoading.service({
    lock: true,
    text: options.message || '加载中...',
    background: 'rgba(0, 0, 0, 0.7)'
  });
}

const closeToast = () => {
  if (loadingInstance) {
    loadingInstance.close();
  }
}

const showFailToast = (message) => {
  ElMessage({
    message: message || '操作失败',
    type: 'error',
    duration: 2000
  });
}
const init = async () => {
  showLoadingToast({ message: '加载中...', forbidClick: true })
  const saved = JSON.parse(localStorage.getItem("userInfo"))
  const data = await getCart({id: saved.userID})
  state.list = data
  state.result = data.map(item => item.cartItemId)
  closeToast()
}

const total = computed(() => {
  let sum = 0
  let _list = state.list.filter(item => state.result.includes(item.cartItemId))
  _list.forEach(item => {
    sum += item.goodsCount * item.sellingPrice
  })
  return sum
})

const goTo = () => {
  router.push({ path: '/top/mall' })
}

const onChange = async (value,detail) => {

  if (value > 5) {
    showFailToast('超出单个商品的最大购买数量')
    return
  }
  if (value < 1) {
    showFailToast('商品不得小于0')
    return
  }
  if (state.list.find(item => item.cartItemId == detail.name)?.goodsCount == value) return
  showLoadingToast({ message: '修改中...', forbidClick: true })
  const params = {
    cartItemId: detail,
    goodsCount: value
  }
  await modifyCart(params)
  state.list.forEach(item => {
    if (item.cartItemId == detail.name) {
      item.goodsCount = value
    }
  })
  closeToast()
}

const onSubmit = async () => {
  if (state.result.length == 0) {
    showFailToast('请选择商品进行结算')
    return
  }
  const params = JSON.stringify(state.result)
  router.push({ path: '/pay', query: { cartItemIds: params } })
}

const deleteGood = async (id) => {
  await deleteCartItem(id)
  init()
}

const groupChange = (result) => {
  if (result.length == state.list.length) {
    state.checkAll = true
  } else {
    state.checkAll = false
  }
  state.result = result
}

const allCheck = () => {
  if (!state.checkAll) {

    state.result = []
  } else {
    state.result = state.list.map(item => item.cartItemId)
  }
}
</script>

<style lang="less">
@import '../../common/mixin';

.cart-box {
  .cart-body {
    margin: 16px 0 100px 0;
    padding-left: 10px;

    .cart-item-card {
      margin-bottom: 10px;
      .good-item {
        display: flex;
        justify-content: space-between;
        align-items: center;
      }
    }

    .good-img {
      img {
        width: 100px;
        height: 100px;
      }
    }

    .good-desc {
      flex: 1;
      padding: 0 10px;
      .good-title {
        display: flex;
        justify-content: space-between;
      }
      .good-btn {
        display: flex;
        justify-content: space-between;
        align-items: center;
      }
    }

    .price {
      font-size: 16px;
      color: red;
    }

    .goods-count-stepper {
      width: 80px;
    }

    .delete-button {
      width: 50px;
      height: 100%;
    }
  }

  .empty {
    width: 50%;
    margin: 0 auto;
    text-align: center;
    margin-top: 200px;

    .empty-cart {
      width: 150px;
      margin-bottom: 20px;
    }

    .title {
      font-size: 16px;
      margin-bottom: 20px;
    }
  }

  .submit-all {
    margin-bottom: 64px;
    .el-checkbox {
      margin-left: 10px;
    }
    .price-summary {
      display: flex;
      justify-content: space-between;
      align-items: center;
    }
    .submit-btn {
      background: @primary;
    }
  }
}
</style>
