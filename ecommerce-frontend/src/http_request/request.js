import axios  from "axios";
import {ElMessage} from "element-plus";
import router from "@/router";
//原本使用的是axios（）没有使用create函数
const request = axios.create(
    {
        baseURL: ,
        timeout: 30000
    }
)
request.interceptors.request.use(config => {
    config.headers['Content-Type']='application/json';
    let token=localStorage.getItem("token");
    config.headers['Authorization']='';
    if(token)
    {
        //config.headers['token']=token;
        // 将 token 添加到 Authorization 头部
        config.headers['Authorization'] = `Bearer ${token}`;
    }
    return config;

},error => {
    console.log('request error'+error)
    return Promise.reject(error);
    }
)

request.interceptors.response.use(res=>{
        //判断返回对象是否合理，以及判断返回的状态码是否合适
        if (typeof res !== 'object') {
            ElMessage.error('服务端异常！')
            return Promise.reject(res)
        }

        if (res.data.code != "200") {
            // if (res.data.msg) ElMessage.error(res.data.message)
            if (res.data.code == "419") {
                router.push({ path: '/login' })
            }
            //比如说加入到购物车就会返回201
            if(res.data.code=="201")
            {
                return res.data
            }
            if(res.data.code=="0")
            {
                return res.data
            }
            ElMessage.error(res.data.msg)
            return Promise.reject(res.data)
        }
        console.log(res)
        return res.data.data
})

export default  request