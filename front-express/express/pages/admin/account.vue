<template>
	<view>
		<div class="ui center aligned grid">
			<div class="column viewh">
				<div class="ui segment">
					<form class="ui form" style="margin-top: 50px">
						<admin-head v-bind:active=3></admin-head>

						
						<h3>修改密码</h3>
						<div class="field" style="margin-top: 10px">
							<div class="ui left icon input">
								<i class="key icon"></i>
								<input type="password" name="password" placeholder="新密码" v-model="pwd1">
							</div>
						</div>

						<div class="field">
							<div class="ui left icon input">
								<i class="key icon"></i>
								<input type="password" placeholder="再输入一次" v-model="pwd2">
							</div>
						</div>

						<div class="ui red " :class="{'message':!validated}">
							{{errMsg}}
						</div>

						<div class="ui fluid teal submit button" style="margin-bottom: 10px" @click="save">保存</div>
						<navigator open-type="navigateBack" class="ui fluid  submit button">返回</navigator>


					</form>

				</div>

			</div>
		</div>

	</view>
</template>

<script>
	export default {
		data() {
			return {
				pwd1: '',
				pwd2: '',
				validated: true,
				errMsg: ''
			};
		},
		methods: {
			save: function() {
				if (this.pwd1.length < 1 || this.pwd2.length < 1) {
					this.validated = false;
					this.errMsg = '密码为空!';
				} else if (this.pwd1 != this.pwd2) {
					this.validated = false;
					this.errMsg = '两次输入密码不一致!';
				} else {
					this.validated = true;
					this.errMsg = '';
					// 保存
					this.$req.user.updatePwd(this.pwd1, this.pwd2, (res) => {
						if (res.data.status) {
							uni.showToast({
								icon: 'none',
								title: '修改成功'
							})
						} else {
							this.validated = false;
							this.errMsg = res.data.msg;
						}
					})
				}
			}
		},
		onLoad() {
			this.info = this.$store.state.info;
			if (!this.$store.getters.isLogin) {
				uni.showToast({
					icon: 'none',
					title: '请先登录'
				})
			}
		}
	}
</script>

<style>

</style>
