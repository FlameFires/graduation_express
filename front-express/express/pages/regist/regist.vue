<template>
	<view>
		<div>
			<div class="ui center aligned grid">
				<div class="column viewh">
					<div class="ui segment">

						<img src="static/images/kdk.png" class="image" style="width: 100px;border-radius: 50px;">
						<p style="color: teal">快递快,快快递</p>

						<div class="ui divider"></div>
						<h3 style="color: grey">{{title}}</h3>
						<form class="ui form">
							<div class="ui field">
								<div class="ui left icon input" style="width: 270px;">
									<i class="mobile icon"></i>
									<input type="text" placeholder="手机" v-model="data.phone" @keydown.enter="enter">
								</div>
							</div>

							<div class="ui inline field">
								<input type="text" placeholder="验证码" v-model="data.ycode" @keydown.enter="enter" />
								<img :src="ycodeurl" style="position: relative;top: 10px;" @click="r" />
							</div>

							<div class="ui field">
								<div class="ui left icon input" style="width: 270px;">
									<i class="key icon"></i>
									<input type="password" placeholder="密码" v-model="data.pwd" @keydown.enter="enter">
								</div>
							</div>

							<div class="ui field">
								<div class="ui left icon input" style="width: 270px;">
									<i class="key icon"></i>
									<input type="password" placeholder="确认密码" v-model="data.pwd2"
										@keydown.enter="enter">
								</div>
							</div>

							<div class="ui message" :class="msgClass" v-show="msg.length > 0">{{msg}}</div>

							<div class="ui teal submit button" style="margin-bottom: 10px;width: 270px;"
								@click="regist">立即注册</div>


						</form>

						<div class="ui large buttons" style="">
							<div class="ui button" :class="{teal:title=='用户注册'}" @click="change('用户注册')"><i
									class="sign in icon"></i>
								用户
							</div>
							<div class="or"></div>
							<div class="ui button" :class="{teal:title!='用户注册'}" @click="change('送单员注册')"><i
									class="sign out icon"></i>
								送单员</div>
						</div>
						<div class="ui message">
							已有账号? <navigator url="../login/login"
								style="display: inline;color: #007AFF;margin-left: 10px;">登陆</navigator>
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
				data: {
					phone: '',
					ycode: '',
					pwd: '',
					pwd2: '',
				},
				t: 'c',
				title: '用户注册',
				msgClass: '',
				msg: '',
				ycodeurl: this.$req.user.verifyCode()
			}
		},
		methods: {
			enter() {
				this.regist();
			},
			r() {
				this.ycodeurl = this.$req.user.verifyCode();
			},
			showMsg(ok, msg) {
				this.msgClass = ok ? 'green' : 'red';
				this.msg = msg;
			},
			regist: function() {
				// 验证
				if (!this.$utils.validate(this.data, 'phone', this.showMsg)) return;
				if (!this.$utils.validate(this.data, 'pwd', this.showMsg)) return;

				if (this.pwd != this.pwd2) {
					this.showMsg(false, '两次密码不一致')
					return;
				}

				this.$req.user.regist(this.t, this.data, (res) => {
					let data = res.data;
					if (data.status) {
						this.showMsg(true, '注册成功,两秒后跳转至登录页面')
						setTimeout(() => {
							uni.redirectTo({
								url: '../login/login'
							})
						}, 2000);
					} else {
						this.showMsg(false, data.msg)
					}
				});


				console.log('ok')
			},
			change(val) {
				this.title = val;
				this.t = val === "用户注册" ? 'c' : 'd';
			}
		}
	}
</script>

<style>
</style>
