import { createRouter, createWebHistory } from 'vue-router';
const routes = [
    {
        path: '/',
        name: 'Welcome',
        component: import("@/views/register_and_login/WelcomeView.vue"),
    },
    {
        path: '/login',
        name: "Login",
        component: import("../views/register_and_login/LoginView.vue")
    },
    {
        path: '/register',
        name: 'Register',
        component: import("@/views/register_and_login/RegisterView.vue")
    },

        {
            path:'/top',
            name:'Top',
                component: import("@/views/homePage/TopView.vue"),
                redirect: '/top/mall',
                children:[
                    {
                        path: '/top/mall',
                        name: 'Mall',
                        component: import("@/views/homePage/mallMainView.vue")
                    },
                    {

                        path: '/top/user_informations',
                        name: 'user_informations',
                        component: import("@/views/UserRelateive/UserInfomation.vue")
                    },
                    {
                        path:'/top/cart',
                        name:'shopping_cart',
                        component:import("@/views/shopping_cart/shopping_cart.vue")
                    },
                    {
                        path:'/top/search_goods',
                        name:'search_goods',
                        component:import("@/views/search_goods/SearchView.vue")
                    },
                    {
                        path:'/top/order_management',
                        name:'order_management',
                        component: import("@/views/order_management/OrderManage.vue")
                    }

                ]


        },
    {
                path:'/pay',
                name:'pay',
                component: import("@/views/payForOrders/pay_orders.vue")
    },
    {
                path:'/products',
                name:'products',
                component: import("@/views/showDetailProduct/product_detail.vue")
    },
    {
                path:'/orders',
                name:'orders',
                component: import("@/views/showDetailOrder/orderDetail.vue")
    }



];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
