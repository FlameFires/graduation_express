const domain = 'http://localhost:8060';
const version = 'v1';
const modules = {
	user: 'user',
	order: 'order',
	admin: 'admin',
	post: 'post',
	notice: 'notice',
	money: 'money',
	comment: 'comment'
}


export default {
	domain,
	version,
	admin: {
		addTag(type, name, handler) {
			let url = `${domain}/api/${version}/${modules.admin}/${type}/login?name=${name}&pwd=${pwd}`
			uni.request({
				url: url,
				withCredentials: true,
				success: (res) => {
					handler(res)
				},
				fail() {
					uni.showToast({
						icon: 'none',
						title: '请求失败，请检查网络或者联系服务器维护人员'
					})
				}
			});
		}
	},
	user: {
		verifyCode() {
			let url = `${domain}/api/${version}/${modules.user}/verifyCode?` + new Date().getTime()
			return url;
		},
		login: function(type, name, pwd, handler, ) {
			let url = `${domain}/api/${version}/${modules.user}/${type}/login?name=${name}&pwd=${pwd}`
			uni.request({
				url: url,
				withCredentials: true,
				success: (res) => {
					handler(res)
				},
				fail() {
					uni.showToast({
						icon: 'none',
						title: '请求失败，请检查网络或者联系服务器维护人员'
					})
				}
			});
		},
		regist(type, data, handler) {
			let url =
				`${domain}/api/${version}/${modules.user}/${type}/regist?code=${data.ycode}&phone=${data.phone}&pwd=${data.pwd}`
			uni.request({
				url: url,
				withCredentials: true,
				success: (res) => {
					handler(res)
				},
				fail() {
					uni.showToast({
						icon: 'none',
						title: '请求失败，请检查网络或者联系服务器维护人员'
					})
				}
			});
		},
		updatePwd: function(np, np2, handler) {
			let url = `${domain}/api/${version}/${modules.user}/modpwd?np=${np}&np2=${np2}`
			uni.request({
				url: url,
				withCredentials: true,
				success: (res) => {
					handler(res)
				},
				fail() {
					uni.showToast({
						icon: 'none',
						title: '请求失败，请检查网络或者联系服务器维护人员'
					})
				}
			});
		},
		exit: function(handler) {
			let url = `${domain}/api/${version}/${modules.user}/exit`
			uni.request({
				url: url,
				withCredentials: true,
				success: (res) => {
					handler(res)
				},
				fail() {
					uni.showToast({
						icon: 'none',
						title: '请求失败，请检查网络或者联系服务器维护人员'
					})
				}
			});
		},
		getSelfInfo(handler) {
			let url = `${domain}/api/${version}/${modules.user}/selfInfo`
			uni.request({
				url: url,
				withCredentials: true,
				success: (res) => {
					handler(res)
				},
				fail() {
					uni.showToast({
						icon: 'none',
						title: '请求失败，请检查网络或者联系服务器维护人员'
					})
				}
			});
		},
		saveSelfInfo(t, data, handler) {
			let url = `${domain}/api/${version}/${modules.user}/save${t}Info?`
			uni.request({
				url: url,
				method: 'POST',
				data: data,
				withCredentials: true,
				success: (res) => {
					handler(res)
				},
				fail() {
					uni.showToast({
						icon: 'none',
						title: '请求失败，请检查网络或者联系服务器维护人员'
					})
				}
			});
		}
	},
	order: {
		getNewOrderList(t, handler) {
			let url = `${domain}/api/${version}/${modules.order}/orderList?t=${t}`
			uni.request({
				url: url,
				withCredentials: true,
				success: (res) => {
					handler(res)
				},
				fail() {
					uni.showToast({
						icon: 'none',
						title: '请求失败，请检查网络或者联系服务器维护人员'
					})
				}
			});
		},
		checkOrder(t, id, handler) {
			let url = `${domain}/api/${version}/${modules.order}/check?t=${t}&id=${id}`
			uni.request({
				url: url,
				withCredentials: true,
				success: (res) => {
					handler(res)
				},
				fail() {
					uni.showToast({
						icon: 'none',
						title: '请求失败，请检查网络或者联系服务器维护人员'
					})
				}
			});
		},
		addOrder(t, data, handler) {
			let s_url = `${domain}/api/${version}/${modules.order}/s_add`
			let t_url = `${domain}/api/${version}/${modules.order}/t_add`
			let url = t != 's' ? t_url : s_url;
			uni.request({
				url: url,
				method: 'POST',
				data: data,
				withCredentials: true,
				success: (res) => {
					handler(res)
				},
				fail() {
					uni.showToast({
						icon: 'none',
						title: '请求失败，请检查网络或者联系服务器维护人员'
					})
				}
			});
		},
		delOrder(t, id, handler) {
			let url = `${domain}/api/${version}/${modules.order}/delOrder?t=${t}&id=${id}`
			uni.request({
				url: url,
				method: 'DELETE',
				withCredentials: true,
				success: (res) => {
					handler(res)
				},
				fail() {
					uni.showToast({
						icon: 'none',
						title: '请求失败，请检查网络或者联系服务器维护人员'
					})
				}
			});
		},
		cancelOrder(t, id, handler) {
			let url = `${domain}/api/${version}/${modules.order}/cancel?t=${t}&id=${id}`
			uni.request({
				url: url,
				withCredentials: true,
				success: (res) => {
					handler(res)
				},
				fail() {
					uni.showToast({
						icon: 'none',
						title: '请求失败，请检查网络或者联系服务器维护人员'
					})
				}
			});
		},
		acceptOrder(t, id, handler) {
			let url = `${domain}/api/${version}/${modules.order}/accOrder?t=${t}&id=${id}`
			uni.request({
				url: url,
				withCredentials: true,
				success: (res) => {
					handler(res)
				},
				fail() {
					uni.showToast({
						icon: 'none',
						title: '请求失败，请检查网络或者联系服务器维护人员'
					})
				}
			});
		},
		sendedOrder(t, id, handler) {
			let url = `${domain}/api/${version}/${modules.order}/sucOrder?t=${t}&id=${id}`
			uni.request({
				url: url,
				withCredentials: true,
				success: (res) => {
					handler(res)
				},
				fail() {
					uni.showToast({
						icon: 'none',
						title: '请求失败，请检查网络或者联系服务器维护人员'
					})
				}
			});
		},
		getRecList(t, date, key, handler) {
			let url =
				`${domain}/api/${version}/${modules.order}/recList?t=${t}&date=${date}&key=${key}`
			uni.request({
				url: url,
				withCredentials: true,
				success: (res) => {
					handler(res)
				},
				fail() {
					uni.showToast({
						icon: 'none',
						title: '请求失败，请检查网络或者联系服务器维护人员'
					})
				}
			});
		},
		getTagList(handler) {
			let url = `${domain}/api/${version}/${modules.order}/taglist`
			uni.request({
				url: url,
				withCredentials: true,
				success: (res) => {
					handler(res)
				},
				fail() {
					uni.showToast({
						icon: 'none',
						title: '请求失败，请检查网络或者联系服务器维护人员'
					})
				}
			});
		},
		delTag(t, handler) {
			let url = `${domain}/api/${version}/${modules.order}/delTag?t=${t}`
			uni.request({
				url: url,
				method: "DELETE",
				withCredentials: true,
				success: (res) => {
					handler(res)
				},
				fail() {
					uni.showToast({
						icon: 'none',
						title: '请求失败，请检查网络或者联系服务器维护人员'
					})
				}
			});
		},
		updTag(t, id, name, handler) {
			let url = `${domain}/api/${version}/${modules.order}/upd?t=${t}&id=${id}&name=${name}`
			uni.request({
				url: url,
				withCredentials: true,
				success: (res) => {
					handler(res)
				},
				fail() {
					uni.showToast({
						icon: 'none',
						title: '请求失败，请检查网络或者联系服务器维护人员'
					})
				}
			});
		},
		addTag(t, name, handler) {
			let url = `${domain}/api/${version}/${modules.order}/addTag?t=${t}&name=${name}`
			uni.request({
				url: url,
				withCredentials: true,
				success: (res) => {
					handler(res)
				},
				fail() {
					uni.showToast({
						icon: 'none',
						title: '请求失败，请检查网络或者联系服务器维护人员'
					})
				}
			});
		},
		aorder(t, date, key, handler) {
			let url = `${domain}/api/${version}/${modules.order}/aorder?t=${t}&date=${date}`
			if (key.length > 0)
				url += `&key=${key}`
			uni.request({
				url: url,
				withCredentials: true,
				success: (res) => {
					handler(res)
				},
				fail() {
					uni.showToast({
						icon: 'none',
						title: '请求失败，请检查网络或者联系服务器维护人员'
					})
				}
			});
		}
	},
	post: {
		query(key, handler) {
			let url = `${domain}/api/${version}/${modules.post}/query?k=${key}`
			uni.request({
				url: url,
				withCredentials: true,
				success: (res) => {
					handler(res)
				},
				fail() {
					uni.showToast({
						icon: 'none',
						title: '请求失败，请检查网络或者联系服务器维护人员'
					})
				}
			});
		},
		add(data, handler) {
			let url = `${domain}/api/${version}/${modules.post}/add`
			uni.request({
				url: url,
				method: 'POST',
				data: data,
				withCredentials: true,
				success: (res) => {
					handler(res)
				},
				fail() {
					uni.showToast({
						icon: 'none',
						title: '请求失败，请检查网络或者联系服务器维护人员'
					})
				}
			});
		},
		del(id, handler) {
			let url = `${domain}/api/${version}/${modules.post}/del?id=${id}`
			uni.request({
				url: url,
				method: 'DELETE',
				withCredentials: true,
				success: (res) => {
					handler(res)
				},
				fail() {
					uni.showToast({
						icon: 'none',
						title: '请求失败，请检查网络或者联系服务器维护人员'
					})
				}
			});
		},
		check(id, handler) {
			let url = `${domain}/api/${version}/${modules.post}/check?id=${id}`
			uni.request({
				url: url,
				withCredentials: true,
				success: (res) => {
					handler(res)
				},
				fail() {
					uni.showToast({
						icon: 'none',
						title: '请求失败，请检查网络或者联系服务器维护人员'
					})
				}
			});
		}
	},
	notice: {
		query(handler) {
			let url = `${domain}/api/${version}/${modules.notice}/query`
			uni.request({
				url: url,
				withCredentials: true,
				success: (res) => {
					handler(res)
				},
				fail() {
					uni.showToast({
						icon: 'none',
						title: '请求失败，请检查网络或者联系服务器维护人员'
					})
				}
			});
		},
		add(data, handler) {
			let url = `${domain}/api/${version}/${modules.notice}/add`
			uni.request({
				url: url,
				method: 'POST',
				data: data,
				withCredentials: true,
				success: (res) => {
					handler(res)
				},
				fail() {
					uni.showToast({
						icon: 'none',
						title: '请求失败，请检查网络或者联系服务器维护人员'
					})
				}
			});
		},
		del(id, handler) {
			let url = `${domain}/api/${version}/${modules.notice}/del?id=${id}`
			uni.request({
				url: url,
				method: 'DELETE',
				withCredentials: true,
				success: (res) => {
					handler(res)
				},
				fail() {
					uni.showToast({
						icon: 'none',
						title: '请求失败，请检查网络或者联系服务器维护人员'
					})
				}
			});
		},
		check(id, handler) {
			let url = `${domain}/api/${version}/${modules.notice}/check?id=${id}`
			uni.request({
				url: url,
				withCredentials: true,
				success: (res) => {
					handler(res)
				},
				fail() {
					uni.showToast({
						icon: 'none',
						title: '请求失败，请检查网络或者联系服务器维护人员'
					})
				}
			});
		},
		update(data, handler) {
			let url = `${domain}/api/${version}/${modules.notice}/upd`
			uni.request({
				url: url,
				method: 'POST',
				data: data,
				withCredentials: true,
				success: (res) => {
					handler(res)
				},
				fail() {
					uni.showToast({
						icon: 'none',
						title: '请求失败，请检查网络或者联系服务器维护人员'
					})
				}
			});
		}
	},
	money: {
		get(handler) {
			let url = `${domain}/api/${version}/${modules.money}/query`
			uni.request({
				url: url,
				withCredentials: true,
				success: (res) => {
					handler(res)
				},
				fail() {
					uni.showToast({
						icon: 'none',
						title: '请求失败，请检查网络或者联系服务器维护人员'
					})
				}
			});
		}
	},
	comment: {
		query(handler) {
			let url = `${domain}/api/${version}/${modules.comment}/query`
			uni.request({
				url: url,
				withCredentials: true,
				success: (res) => {
					handler(res)
				},
				fail() {
					uni.showToast({
						icon: 'none',
						title: '请求失败，请检查网络或者联系服务器维护人员'
					})
				}
			});
		},
		del(id, handler) {
			let url = `${domain}/api/${version}/${modules.comment}/del?id=${id}`
			uni.request({
				url: url,
				method: 'DELETE',
				withCredentials: true,
				success: (res) => {
					handler(res)
				},
				fail() {
					uni.showToast({
						icon: 'none',
						title: '请求失败，请检查网络或者联系服务器维护人员'
					})
				}
			});
		},
		put(msg, handler) {
			let url = `${domain}/api/${version}/${modules.comment}/put`
			uni.request({
				url: url,
				method: 'POST',
				data: msg,
				withCredentials: true,
				success: (res) => {
					handler(res)
				},
				fail() {
					uni.showToast({
						icon: 'none',
						title: '请求失败，请检查网络或者联系服务器维护人员'
					})
				}
			});
		},
	}
}
