import request from "@/http_request/request";

/**
 * @brief 更新部分用户信息
 * @param userInfo 电话，邮箱
 * @returns {Promise<axios.AxiosResponse<any>>}
 */
export function putUserInformation(userInfo)
{
    return request.put('/user/update_imfomation',userInfo);
}