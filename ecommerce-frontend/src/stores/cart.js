import { ref } from 'vue'
import { defineStore } from 'pinia'
import { getCart } from '@/service/cart'

/**
 * @brief 定义了一个名为 useCartStore 的 Pinia 状态管理模块，用于在 Vue 3 项目中管理购物车的状态和相关操作。
 * @type {StoreDefinition<"cart", _ExtractStateFromSetupStore<{count: Ref<UnwrapRef<number>, UnwrapRef<number> | number>, updateCart: function(): Promise<void>}>, _ExtractGettersFromSetupStore<{count: Ref<UnwrapRef<number>, UnwrapRef<number> | number>, updateCart: function(): Promise<void>}>, _ExtractActionsFromSetupStore<{count: Ref<UnwrapRef<number>, UnwrapRef<number> | number>, updateCart: function(): Promise<void>}>>}
 * @updateCart 调用后端接口， 获取购物车数据
 * @details 将购物车中商品的数量和购物车数据的更新操作暴露出来给其他模块使用
 */
export const useCartStore = defineStore('cart', () => {
    const count = ref(0)
    async function updateCart() {
        //const { data = [] } = await getCart()
        const{ response:{ data=[]}={}}=await  getCart()
        count.value = data.length
    }

    return { count, updateCart }
})
