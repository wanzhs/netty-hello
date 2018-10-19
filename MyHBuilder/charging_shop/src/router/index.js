import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
    routes: [
        {
            path: '/',
            redirect: '/admin/welcome',
        },
        {
            path: '/login',
            component: resolve => require(['@/pages/login.vue'], resolve)
        },
        {
            path: '/admin',
            redirect: '/admin/welcome',
            meta: {
                auth: true //设置登录认证
            },
            component: resolve => require(['@/pages/home.vue'], resolve),
            children: [
                {
                    //欢迎页面
                    path: 'welcome',
                    component: resolve => require(['@/pages/welcome.vue'], resolve)
                },
                {
                    path: 'system/siteList',
                    component: resolve => require(['@/pages/sysManager/siteList.vue'], resolve)
                },
                {
                    path: 'price/specialPrice',
                    component: resolve => require(['@/pages/priceManager/specialPrice.vue'], resolve)
                },
                {
                    path: 'price/noSpecialPrice',
                    component: resolve => require(['@/pages/priceManager/noSpecialPrice.vue'], resolve)
                },
                {
                    path: 'price/addPriceStrategy',
                    component: resolve => require(['@/pages/priceManager/addPriceStrategy.vue'], resolve)
                },
                {
                    path: 'price/poleElec',
                    component: resolve => require(['@/pages/priceManager/poleElec.vue'], resolve)
                },
                {
                    path: 'siteManager/siteState',
                    component: resolve => require(['@/pages/siteManager/operationSituation.vue'], resolve)
                },
                {
                    // 充电桩详情页
                    path: '/admin/siteManager/siteDetail',
                    component: resolve => require(['@/pages/siteManager/siteDetail.vue'], resolve)
                },
                {
                    path: 'siteManager/siteList',
                    component: resolve => require(['@/pages/siteManager/siteList.vue'], resolve)
                },
                {
                    path: 'siteManager/order',
                    component: resolve => require(['@/pages/siteManager/order.vue'], resolve),
                    children: [
                        {
                            path: '/',
                            component: resolve => require(['@/pages/siteManager/chargeOrderManager.vue'], resolve)
                        },
                        {
                            path: 'commentManager',
                            component: resolve => require(['@/pages/siteManager/commentManager.vue'], resolve)
                        },
                        {
                            path: 'orderManager',
                            component: resolve => require(['@/pages/siteManager/orderManager.vue'], resolve)
                        }
                    ]
                },
                {
                    path: 'opinion',
                    component: resolve => require(['@/pages/opinion/index.vue'], resolve)
                },
                { //无路由
                    path: '*',
                    component: resolve => require(['@/pages/error/nofind.vue'], resolve)
                }]
        }, {
            path: '*',
            component: resolve => require(['@/pages/error/nofind.vue'], resolve)
        }]
})
