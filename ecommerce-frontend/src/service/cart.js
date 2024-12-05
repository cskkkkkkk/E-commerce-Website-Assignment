import request from "@/http_request/request";

/**
 * @brief 在展示商品具体信息后点击加入购物车，将商品加入到购物车中
 * @param params { goodsCount: 1, goodsId: state.detail.goodsId } 包含一个商品数量，商品id
 * @returns {Promise<axios.AxiosResponse<any>>}
 */
export function addCart(params) {
    return request.post('/shop-cart/addCart', params);
}

export function modifyCart(params) {
    console.log(params)
    return request.post('/shop-cart/updateGoodsCount', params);
}

export function getCart(params) {
    return request.post('/shop-cart/getAllGoods',params);
}

export function deleteCartItem(id) {
    return request.delete(`/shop-cart/${id}`);
}

/**
 * @brief 用在订单支付界面，用于获取购物车中商品的list
 * @param params 商品id，id,id 是一个字符串
 * @returns {Promise<axios.AxiosResponse<any>>}
 */
export function getByCartItemIds(params) {
    return request.post('/shop-cart/settle', params );
}

