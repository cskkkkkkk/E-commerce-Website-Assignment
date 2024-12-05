// Axios 库，用于发起 HTTP 请求。
import axios from 'axios'
//用于显示全局提示消息
import { ElMessage } from 'element-plus'
//引入 Vue Router，用于路由跳转。
import router from '@/router/index'
//
import { localGet } from './index'
//import config from '~/config'

axios.defaults.baseURL=
// 这边由于后端没有区分测试和正式，姑且都写成一个接口。
//axios.defaults.baseURL = config[import.meta.env.MODE].baseUrl   这一行暂时不使用
// 携带 cookie，对目前的项目没有什么作用，因为我们是 token 鉴权
axios.defaults.withCredentials = true
// 请求头，headers 信息
//axios.defaults.headers['X-Requested-With'] = 'XMLHttpRequest'
//这样在每次请求时，都会自动携带 token，用于身份验证。
//axios.defaults.headers['token'] = localGet('token') || ''
// 默认 post 请求，使用 application/json 形式
axios.defaults.headers['Content-Type'] = 'application/json'
axios.defaults.headers['Authorization']=`Bearer ${localGet("token")}`
// 请求拦截器，内部根据返回值，重新组装，统一管理。
axios.interceptors.response.use(res => {
    //判断返回对象是否合理，以及判断返回的状态码是否合适
    if (typeof res !== 'object') {
        ElMessage.error('服务端异常！')
        return Promise.reject(res)
    }
    console.log(res)
    if (res.data.code != 200) {
        if (res.data.msg) ElMessage.error(res.data.message)
        if (res.data.code == 419) {
            router.push({ path: '/login' })
        }
        return Promise.reject(res.data.data)
    }
    console.log(res.data.data)
    return res.data.data
})

export default axios