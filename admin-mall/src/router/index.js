import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
    history: createWebHistory(), // hash模式：createWebHashHistory，history模式：createWebHistory
    routes: [
        {
            path: '/',
            redirect: '/login'
        },
        {
            path: '/login',
            name: 'login',
            component: import("@/views/LoginAdmin.vue")
        },
        {
            path: '/add',
            name: 'add',
            component: () => import(/* webpackChunkName: "add" */ '../views/addGood.vue')
        },
        {
            path: '/category',
            name: 'category',
            component: () => import(/* webpackChunkName: "category" */ '../views/category_manage.vue'),
            children: [
                {
                    path: '/category/level2',
                    name: 'level2',
                    component: () => import(/* webpackChunkName: "level2" */ '../views/category_manage.vue'),
                },
                {
                    path: '/category/level3',
                    name: 'level3',
                    component: () => import(/* webpackChunkName: "level3" */ '../views/category_manage.vue'),
                }
            ]
        },
        {
            path: '/good',
            name: 'good',
            component: () => import(/* webpackChunkName: "new" */ '../views/good_management.vue')
        },
        {
            path: '/guest',
            name: 'guest',
            component: () => import(/* webpackChunkName: "guest" */ '../views/user_management.vue')
        },
        {
            path: '/order',
            name: 'order',
            component: () => import(/* webpackChunkName: "order" */ '../views/order_management.vue')
        },
        {
            path: '/order_detail',
            name: 'order_detail',
            component: () => import(/* webpackChunkName: "order_detail" */ '../views/OrderDetail.vue')
        },
        {
            path: '/account',
            name: 'account',
            component: () => import(/* webpackChunkName: "account" */ '../views/modify_count.vue')
        },
        {
            path: '/statistic',
            name: 'statistic',
            component:()=>import('@/views/show_data.vue')
        },
        {
            path:'/view',
            name:'view',
            component:()=>import('@/views/BrowseRecord.vue')
        },
        {
            path:'/buy',
            name:'buy',
            component:()=>import('@/views/PayRecord.vue')
        }
    ]
})

export default router