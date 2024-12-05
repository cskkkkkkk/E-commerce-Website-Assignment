//商城主页渲染的对外接口,主要是获取一些消息然后进行网页的渲染
import request from "@/http_request/request";

export function getHotGoods()
{
    return request.get("/main_mall/hot_goods");
}

export function getCarousel() {
    return request.get("/main_mall/carousel");
}

export function getRecommondGoods() {
    return request.get("/main_mall/recommond_goods");
}

export function getGoodsKinds()
{

    return request.get("/product/getKindList");
}