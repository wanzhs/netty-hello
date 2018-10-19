import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)
import loginState from './modules/loginstate'
import pageSize from './modules/pageSize'
import siteData from './modules/siteData'
const store = new Vuex.Store({
    modules: {
        loginState,
        pageSize,
        siteData
    }
})

export default {
    store
}