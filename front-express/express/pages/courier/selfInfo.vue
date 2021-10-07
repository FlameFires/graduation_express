<template>
	<view>
		<div class="ui center aligned grid">
			<div class="column viewh">
				<div class="ui segment">
					<form class="ui form" style="margin-top: 50px">
						<courier-head :active=3></courier-head>

						<img src="/static/images/kdk.png" class="image" style="width: 100px;border-radius: 50px;margin-top: 10px;">
						<div class="field" style="margin-top: 10px">
							<div class="ui left icon input">
								<i class="user icon"></i>
								<input type="text" name="username" placeholder="姓名" v-model="info.name">
							</div>
						</div>

						<div class="field">
							<div class="ui left icon disable input">
								<i class="phone icon"></i>
								<input type="text" name="mobile" placeholder="请输入手机号/校园短号" :value="info.phone">
							</div>
						</div>
						<div class="field">
							<select v-model="info.gender">
								<option value="">保密</option>
								<option value="男">男</option>
								<option value="女">女</option>
							</select>
						</div>

						<div class="ui error message"></div>

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
				info: {
					gender: ''
				}
			};
		},
		methods: {
			save: function() {

				if (this.info.gender == "") this.info.gender = '未知'

				this.$req.user.saveSelfInfo('D', this.info, (res) => {
					let data = res.data;
					if (data.status) {
						this.$utils.showToast('保存成功')
						if (this.info.gender == '未知') this.info.gender = ''
						
						this.$req.user.getSelfInfo((res) => {
							if (!res.data.status) {
								uni.showToast({
									title: res.data.msg,
									icon: 'none'
								})
							} else {
								this.info = res.data.data;
								this.$utils.setUser(this.info);
							}
						});
					} else {
						this.$utils.showToast(data.msg)
					}
				})
			}
		},
		onLoad() {
			this.info = this.$utils.getUser();
			if (!this.info) {
				uni.showToast({
					icon: 'none',
					title: '请先登录'
				})
				return;
			}
			
			if (this.info.gender != undefined && this.info.gender == '未知') {
				this.info.gender = ''
			}
		}
	}
</script>

<style>

</style>
