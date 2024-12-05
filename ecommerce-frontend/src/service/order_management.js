import request from "@/http_request/request"

/**
 * @brief 将所有相应订单状态的所有订单查询后返回
 * @param params { pageNumber: state.page, status: state.status }
 */
export function getOrderList(params)
{
    return request.post("/orderManage/getOrderbyStatus",params);
}