import request from "@/http_request/request"

/**
 * @brief 将登录的用户名，密码，登录账号类型提交到后端验证
 * @param sizeForm 对象：用户名，密码，登录账号类型
 */
export function postLoginInfomation(sizeForm){

    return request.post("/user/login",sizeForm)
}