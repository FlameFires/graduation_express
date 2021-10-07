//验证的规则
const rules = {
	phone: {
		rule: /^1[0-9]{10}$/,
		msg: '手机号输入有误'
	},
	uname: {
		rule: /\S/,
		msg: "账号不能为空"
	},
	pwd: {
		rule: /^[0-9a-zA-Z]{6,16}$/,
		msg: "密码应该为6-16位"
	}
};


export default {
	isEmptyObject(obj) {
		for (var n in obj) {
			return false
		}
		return true;
	},
	timestampToTime(timestamp) {
		var date = new Date(timestamp); //时间戳为10位需*1000，时间戳为13位的话不需乘1000
		var Y = date.getFullYear() + '-';
		var M = date.getMonth() + 1 + '-';
		var D = date.getDate() + ' ';
		var h = date.getHours() + ':';
		var m = date.getMinutes() + ':';
		var s = date.getSeconds();
		return Y + M + D + h + m + s;
	},
	dateFormat(fmt, date) {
		let ret;
		const opt = {
			"Y+": date.getFullYear().toString(), // 年
			"m+": (date.getMonth() + 1).toString(), // 月
			"d+": date.getDate().toString(), // 日
			"H+": date.getHours().toString(), // 时
			"M+": date.getMinutes().toString(), // 分
			"S+": date.getSeconds().toString() // 秒
			// 有其他格式化字符需求可以继续添加，必须转化成字符串
		};
		for (let k in opt) {
			ret = new RegExp("(" + k + ")").exec(fmt);
			if (ret) {
				fmt = fmt.replace(ret[1], (ret[1].length == 1) ? (opt[k]) : (opt[k].padStart(ret[1].length, "0")))
			};
		};
		return fmt;
	},
	// 注:时间戳转时间（ios手机NaN）
	getTime(nS) {
		var date = new Date(parseInt(nS) * 1000);
		var year = date.getFullYear();
		var mon = date.getMonth() + 1;
		var day = date.getDate();
		validate
		var hours = date.getHours();
		var minu = date.getMinutes();
		var sec = date.getSeconds();

		return year + '/' + mon + '/' + day + ' ' + hours + ':' + minu + ':' + sec;
	},
	//判断验证是否符合要求
	validate(data, key, errHandler) {
		let bool = true;
		if (data[key] == undefined) {
			console.log(1111, data, rules[key].rule, data[key], rules[key].rule.test(data[key]))
			if (errHandler == undefined) {
				//提示信息
				uni.showToast({
					title: rules[key].msg,
					icon: 'none'
				})
			} else
				errHandler(false, rules[key].msg);
			//取反
			bool = false;
		}

		console.log(data, rules[key].rule, data[key], rules[key].rule.test(data[key]))
		if (!rules[key].rule.test(data[key])) {
			if (errHandler == undefined) {
				//提示信息
				uni.showToast({
					title: rules[key].msg,
					icon: 'none'
				})
			} else
				errHandler(false, rules[key].msg);
			//取反
			bool = false;
		}
		console.log(bool)
		return bool;
	},
	addPreZero(num) {
		return ('000000000' + num).slice(-15);
	},
	showToast(msg, success) {
		uni.showToast({
			icon: 'none',
			title: msg,
			success() {
				if (success != undefined) success();
			}
		})
	},
	showModal(msg, success, title) {
		title = title == undefined ? '提示' : title;
		uni.showModal({
			title: title,
			content: msg,
			success: function(res) {
				success(res);
			}
		});
	},
	setVal(key, val) {
		try {
			uni.setStorageSync(key, val);
		} catch (e) {
			console.log('缓存数据设置失败', e);
		}
	},
	getVal(key) {
		try {
			const value = uni.getStorageSync(key);
			return value;
		} catch (e) {
			console.log('缓存数据获取失败', e);
		}
	},
	getUser() {
		let user = this.getVal('user');
		if (user != null && user.length > 1) {
			return JSON.parse(user);
		}
		return {};
	},
	setUser(data) {
		return this.setVal('user', JSON.stringify(data))
	},
	exitUser() {
		let u = this.getUser();
		if (u) {
			try {
				uni.removeStorageSync('user');
			} catch (e) {
				console.log('缓存数据删除失败', e);
			}
		}
	}
}
