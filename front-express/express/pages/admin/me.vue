<template>
	<view>
		<div class="ui center aligned grid">
			<div class="column viewh">
				<div class="ui segment">
					<admin-head v-bind:active=3></admin-head>


					<div class="ui segments">
						<div class="ui segment">
							<div class="ui two column grid">
								<div class="column">
									<div class="ui header">{{data.today}}元</div>
									<div class="field" style="color: red;">今日收入</div>
								</div>
								<div class="column">
									<div class="ui header">{{data.total}}元</div>
									<div class="field" style="color: red;">总收入</div>
								</div>
							</div>
						</div>

						<!-- 		<div class="ui segment">
							<div class="ui relaxed divided list selection">
								<div class="item">
									<div class="content">
										<navigator url="/pages/admin/account">
											<h3 class="header">账号安全</h3>
										</navigator>
									</div>
								</div>
							</div>
						</div> -->
						<div class="ui segment">
							<div class="ui relaxed divided list selection">
								<div class="item">
									<div class="content">
										<navigator url="/pages/admin/adm_take">
											<h3 class="header">查看代取单</h3>
										</navigator>
									</div>
								</div>
							</div>
						</div>
						<div class="ui segment">
							<div class="ui relaxed divided list selection">
								<div class="item">
									<div class="content">
										<navigator url="/pages/admin/adm_send">
											<h3 class="header">查看代寄单</h3>
										</navigator>
									</div>
								</div>
							</div>
						</div>
						<div class="ui segment">
							<div class="ui relaxed divided list selection">
								<div class="item">
									<div class="content">
										<navigator url="/pages/admin/notice_list">
											<h3 class="header">查看公告</h3>
										</navigator>
									</div>
								</div>
							</div>
						</div>
				<!-- 		<div class="ui segment">
							<div class="ui relaxed divided list selection">
								<div class="item">
									<div class="content">
										<navigator url="/pages/admin/notice_list">
											<h3 class="header">查看评论</h3>
										</navigator>
									</div>
								</div>
							</div>
						</div> -->
					</div>


					<div class="ui field">
						<navigator url="../login/login" class="ui fluid red button"
							style="margin-bottom: 10px;height: 42px;" @click="exit">退出</navigator>
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
				info: {},
				data: {}
			}
		},
		methods: {
			exit: function() {
				if (this.info) {
					this.$req.user.exit((res) => {
						if (res.data.status) {
							uni.showToast({
								icon: 'none',
								title: res.data.msg
							})

							this.$utils.exitUser();
						}
					})
				}
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

			this.$req.money.get((res) => {
				let data = res.data;
				if (data.status) {
					this.data = data.data;
					if (!this.data.today) {
						this.data.today = 0;
					}
				} else {
					this.$utils.showToast(data.msg)
				}
			})
		}
	}
</script>

<style>
</style>
