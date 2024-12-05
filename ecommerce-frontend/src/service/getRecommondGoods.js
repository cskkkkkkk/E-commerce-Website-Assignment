import request from "@/http_request/request";
export function getRecommondGoods(){
    return request.get('/recommond_goods');
}