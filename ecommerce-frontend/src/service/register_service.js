import request from "@/http_request/request";

/**
 * @brief 将用户信息提交进行注册
 * @param sizeForm ：用户名，密码，邮箱，账户类型
 */
export function postRegisterUser(sizeForm)
{
    return request.post("/user/register", sizeForm)
}