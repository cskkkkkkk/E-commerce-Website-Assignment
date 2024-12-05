import request from '@/http_request/request'

export function createOrder(params) {
    return request.post('/saveOrder', params);
}

export function getOrderList(params) {
    return request.get('/order', { params });
}

export function getOrderDetail(id) {
    return request.get(`/order/${id}`);
}

export function cancelOrder(id) {
    return request.put(`/order/${id}/cancel`);
}

export function confirmOrder(id) {
    return request.put(`/order/${id}/finish`)
}

export function payOrder(params) {
    return request.get('/paySuccess', { params })
}




