import Vue from 'vue'
import Vuex from 'vuex'
import utils from '../pages/common/utils.js'

Vue.use(Vuex)
const store = new Vuex.Store({
	state: {
		info: {}
	},
	getters: {
		isLogin(state) {
			return !utils.isEmptyObject(state.info);
		}
	},
	mutations: {
	},
	actions: {
	 
	},
	modules: {}
})
export default store
