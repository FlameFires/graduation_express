import Vue from 'vue'
import App from './App'
import req from 'pages/common/req.js'
import utils from 'pages/common/utils.js'
//引入vuex
import store from 'store/store.js'


//把vuex定义成全局组件
Vue.prototype.$req = req
Vue.prototype.$utils= utils
Vue.prototype.$store = store
Vue.config.productionTip = true
// 可全局注册组件
import pageHead from './components/page-head.vue'
import courierHead from './components/courier-head.vue'
import adminHead from './components/admin-head.vue'
import pickerdisplay from './components/picker-display.vue'
import popWin from './components/popWin.vue'

Vue.component('pop-win',popWin)
Vue.component('page-head',pageHead)
Vue.component('courier-head',courierHead)
Vue.component('admin-head',adminHead)
Vue.component('picker-display',pickerdisplay)
// import pageHead from '@/components/page-head.vue'
// Vue.component('page-head',pageHead)

App.mpType = 'app'

const app = new Vue({
	...App,
	store
})
app.$mount()
