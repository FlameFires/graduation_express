<template>
	<view>
		<div class="ui center aligned grid">
			<div class="column viewh">
				<div class="ui segment">

					<img src="@/static/images/kdk.png" class="image" style="width: 100px;border-radius: 50px;margin-top: 3rem;">
					<p style="color: teal">快递快,快快递</p>

					<div class="ui divider"></div>
					<h3 style="color: grey">{{userTypeTitle}}</h3>
					<form class="ui form">
						<div class="ui field">
							<div class="ui left icon input">
								<i class="mobile icon"></i>
								<input type="text" style="height: 38px;" placeholder="手机" v-model="account" @keydown.enter="enter">
							</div>
						</div>
						<div class="ui error message"></div>

						<div class="ui field">
							<div class="ui left icon input">
								<i class="key icon"></i>
								<input type="password" style="height: 38px;" placeholder="密码" v-model="pwd" @keydown.enter="enter">
							</div>
						</div>
						<div class="ui error message"></div>

						<div class="ui fluid teal submit button" type="submit" style="margin-bottom: 10px;" v-on:click='login'>立即登录</div>
						<navigator url="../regist/regist" class="ui fluid teal submit button" style="margin-bottom: 10px;background-color: #e0e1e2;color: black;">马上注册</navigator>

					</form>
					<div class="ui divider" style="margin-top: 4rem;margin-bottom: 2rem;"></div>

					<div class="ui large buttons" style="margin:20px 0">
						<div class="ui button" :class="{teal:selectUserType=='管理员'}" @tap="change('admin')"><i class="sign out icon"></i>
							管理员
						</div>
						<div class="or"></div>
						<div class="ui button" :class="{teal:selectUserType=='用户'}" @tap="change('customer')"><i class="sign in icon"></i>
							用户
						</div>
						<div class="or"></div>
						<div class="ui button" :class="{teal:selectUserType=='送单员'}" @tap="change()"><i class="sign out icon"></i>
							送单员
						</div>
					</div>


				</div>
			</div>
		</div>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				selectUserType: '用户',
				userTypeTitle: '用户登录',
				account: '',
				pwd: ''
			}
		},
		methods: {
			enter() {
				this.login();
			},
			change: function(uType) {
				if (uType == 'customer') {
					this.selectUserType = '用户';
					this.userTypeTitle = '用户登录';
					return;
				}
				if (uType == 'admin') {
					this.selectUserType = '管理员';
					this.userTypeTitle = '管理员登录';
					return;
				}
				this.selectUserType = '送单员';
				this.userTypeTitle = '送单员登录';
			},
			login: function() {

				if (!this.account || this.account.length < 0) {
					uni.showToast({
						mask: true,
						icon: 'none',
						title: '账号不能为空'
					});
					return;
				}
				if (!this.pwd || this.pwd.length < 0) {
					uni.showToast({
						mask: true,
						icon: 'none',
						title: '密码不能为空'
					});
					return;
				}

				let selectUserType = this.selectUserType;
				let type = 'c';
				if (selectUserType === "用户") {
					type = 'c';
				} else if (selectUserType === "送单员") {
					type = 'd';
				} else if (selectUserType === "管理员") {
					type = 'a';
				}

				this.$req.user.login(type, this.account, this.pwd, (res) => {
					let data = res.data;
					if (data.status) {
						this.$req.user.getSelfInfo((res) => {
							if (!res.data.status) {
								uni.showToast({
									title: res.data.msg,
									icon: 'none'
								})
							} else {
								this.info = res.data.data;
								this.$utils.setUser(this.info)
							}
						})
						uni.showToast({
							mask: true,
							icon: 'none',
							title: '登录成功！',
							success: function() {
								setTimeout(() => {
									let url = '../customer/community'
									if (selectUserType === "用户") {
										url = '../customer/community'
									} else if (selectUserType === "送单员") {
										url = '../courier/me'
									} else if (selectUserType === "管理员") {
										url = '../admin/home'
									}
									uni.navigateTo({
										url: url,
										animationType: 'pop-in',
										animationDuration: 1000
									})
								}, 1000)
							}
						})
					} else {
						this.$utils.showToast(data.msg)
					}
				});
			}
		},
		computed: {}
	}
</script>

<style>
	body {
		background-color: #DADADA;
	}

	body>.grid {
		height: 100%;
	}

	.column {
		max-width: 450px;
	}

	/* div.column,
	div.ui.stacked.segment {
		height: 100%;
	} */

	/* form div {
		margin-bottom: 1rem;
	} */
</style>
