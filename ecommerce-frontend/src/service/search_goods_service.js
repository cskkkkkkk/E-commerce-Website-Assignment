import request from "@/http_request/request";

/**
 * @brief 按照商品种类kindList去返回相应的商品。
 * @param kindList
 */
export function getGoodsByKindList(kindList)
{
    return request.post("/product/selectInfoByKind", kindList);
}

/**
 * @brief 后台会设置一系列存在的商品种类，然后将其存放在数据库，要做的是拿到这些种类然后渲染前端
 * @returns {Promise<axios.AxiosResponse<any>>}
 */
export function getKindList()
{
    return request.get("/product/getKindList");
}