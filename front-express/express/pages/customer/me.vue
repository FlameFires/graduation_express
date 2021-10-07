<template>
	<view>
		<div class="ui center aligned grid">
			<div class="column viewh">
				<div class="ui segment">
					<page-head v-bind:active=4></page-head>
					<img src="@/static/images/kdk.png" class="image left aligned" style="margin-top:20px;width: 70px;border-radius: 50px">

					<div class="ui segment">
						<p>账号: {{info.name}}</p>
						<p>手机:{{info.phone}}</p>
						<!-- <p>微信:jyttkd</p>
		                    <p>地址:女寝室10栋打印店</p> -->
					</div>

					<div class="ui segment">
						<div class="ui relaxed divided list selection">
							<div class="item">
								<div class="content">
									<navigator url="/pages/customer/selfInfo">
										<h3 class="header">个人信息</h3>
									</navigator>
								</div>
							</div>
							<!-- <div class="item">
								<div class="content">
									<navigator url="/pages/customer/notice_list">
										<h3 class="header">查看公告</h3>
									</navigator>
								</div>
							</div> -->
							<div class="item">
								<div class="content">
									<navigator url="/pages/customer/account">
										<h3 class="header">账号安全</h3>
									</navigator>
								</div>
							</div>
						</div>
					</div>


					<div class="ui field">
						<navigator url="../login/login" class="ui fluid red button" style="margin-bottom: 10px;height: 42px;" @click="exit">退出</navigator>
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
				info: {
					name:'',
					phone:''
				}
			}
		},
		methods: {
			exit: function() {
				if(this.$utils.getUser()){
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
			},
		},
		onLoad() {
			this.info = this.$utils.getUser();
			if(!this.info){
				uni.showToast({
					icon:'none',
					title:'请先登录'
				})
				return;
			}
		}
	}
</script>

<style>
</style>
