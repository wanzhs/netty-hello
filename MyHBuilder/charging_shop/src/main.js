// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import axios from 'axios'
import VueAxios from 'vue-axios'
import App from './App'
import router from './router'
import constant from '@/util/constant'
import http from '@/util/http'
import msg from '@/util/msg'
import common from '@/util/common'
import './assets/css/el-table-reset.less'
import Echarts from 'echarts'
import '../static/css/icon/iconfont.css'
import store from '@/store/index'

// import 'mockjs'
// import './mock/mock'

Vue.config.productionTip = false
Vue.prototype.$echarts = Echarts
Vue.use(ElementUI);
Vue.use(VueAxios, axios)
Vue.use(constant)
Vue.use(http)
Vue.use(msg)

Vue.prototype.util = common
Vue.directive('perm', {
    inserted: function (el, value) {
        let perm = window.localStorage.getItem('manager');
        // 如果为假值，则说明本地存储信息已被删除

        // 判断是否与本地信息相同
        if (!!perm) {
            if(perm.indexOf(value.value) > -1) {

            } else {
                el.style.display = 'none';
            }
        } else {
            router.push({path:'/login'});
        }
    }
});

/**
 * 设置登录权限拦截
 * */
//路由拦截进行权限判断
router.beforeEach((to, from, next) => {

    //如果进入登录界面  清空用户的状态值
    if (to.path == '/login') {
        //清除用户信息
        //localStorage.removeItem(constant.static.LOCAL_MANAGER_KEY);
        localStorage.clear();
    }
    //判断是不是需要权限认证的路由
    if (to.matched.some(m => m.meta.auth)) {
        let user = JSON.parse(localStorage.getItem(constant.static.LOCAL_MANAGER_KEY));
        if (user != null && user.adminId > -1 && to.path != '/login') { // id
            //已经登录 判断是否需要权限
            if (to.meta.perm) {
                //权限判断
                let perms = user.adminPermissionList; //perm
                if (perms.indexOf(to.meta.perm) > -1) {
                    next();
                } else {
                    //无权限访问
                    next({path: '/admin/noperm'});
                }
            } else {
                //不需要权限判断
                next();
            }

        } else {
            // msg.static.fail('登录失效，重新登录');
            next({
                path: '/login'
            })
        }
    } else {
        next();
    }
})

//如果用户登录   每次请求需要拦截 进行token的组装
axios.interceptors.request.use(
    config => {
        /**给url加上前缀**/
        config.url = "/api" + config.url;
        /**加上请求头**/
        let user = JSON.parse(localStorage.getItem(constant.static.LOCAL_MANAGER_KEY));
        if (user != null && user.adminId > -1) {
            config.headers["Access-Token"] = user.token;
            config.headers["Client-digest"] = user.adminAccount;
        }
        return config;
    },
    err => {
        return Promise.reject(err);
    });

//全局捕获异常
axios.interceptors.response.use(function (response) {
    return response;
}, function (error) {
    if (error.response) {
        switch (error.response.status) {
            case 400:
                msg.static.fail("请求参数错误");
                break;
            case 401:
                msg.static.fail('无认证登录，需要登录');
                setTimeout(() => {
                    router.push('/login');
                }, 2000);
                break;
            case 402:
                msg.static.fail('无权限操作');
                router.push('/admin/noperm')
                break;
            case 404:
                msg.static.fail('请求不存在');
                break;
            case 500:
                msg.static.fail("服务端错误");
                break;
            case 504:
                msg.static.fail("与服务端断开了连接");
                break;
        }
    }
    return Promise.reject(error);
});

/* eslint-disable no-new */
new Vue({
    el: '#app',
    router,
    store,
    components: {App},
    template: '<App/>'
})
