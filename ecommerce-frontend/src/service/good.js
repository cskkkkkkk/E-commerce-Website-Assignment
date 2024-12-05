import request from "@/http_request/request";

/**
 * @brief 查询商品的具体信息然后进行展示
 * @param id 商品编号
 * @returns {Promise<axios.AxiosResponse<any>>}
 * @return param: {goodsCarouselList(存放商品信息图片url地址),goodsName(商品名字),sellingPrice(售卖价格)
 * goodsDetailContent(商品描述信息)
 * }
 */
export function getDetail(id) {
    return request.get(`/product/detail/${id}`);
}

export function getCategory() {
    return request.get('/categories');
}

export function search(params) {
    return request.get('/search',  params );
}

/**
 * @brief 用户浏览记录登记，要被后端加入到用户浏览登记表中
 */
export function userViewRecord(params)
{
    return request.post('/user/view/record',params);
}