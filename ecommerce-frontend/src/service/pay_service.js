import request from "@/http_request/request"

/**
 * @brief 点击支付后，就把支付成功的订单号保存到数据库
 * @param params
 * @returns {Promise<axios.AxiosResponse<any>>}
 */
export function createOrder(params)
{   console.log(99)
    return request.post( "/pay/successfulOrders"   ,params);
}

export function payOrder(params)
{
    return request.put("/pay/updateInformationOrder",params);
}