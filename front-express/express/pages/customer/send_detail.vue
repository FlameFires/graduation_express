<template>
	<view>
		<div class="ui center aligned grid">
			<div class="column viewh">
				<div class="ui segment">
					<form class="ui equal width form">
						<h3>代寄快递</h3>
						<div class="" style="text-align: left;">
							<div class="field"></div>
							<div class="field">
								<label>订单号：{{this.$utils.addPreZero(data.id)}}</label>
							</div>
							<div class="field">
								<label>下单时间：{{data.createDate}}</label>
							</div>
							<div class="field">
								<label>寄件人姓名：{{data.senderName}}</label>
							</div>
							<div class="field">
								<label>寄件人手机号：{{data.senderPhoneNum}}</label>
							</div>
							<div class="field">
								<label>收件人姓名：{{data.receiverName}}</label>
							</div>
							<div class="field">
								<label>收件人手机号：{{data.receiverPhoneNum}}</label>
							</div>
							<div class="field">
								<label>取件地址：{{data.takeAddress}}</label>
							</div>
							<div class="field">
								<label>寄件地址：{{data.sendAddress}}</label>
							</div>
							<div class="field">
								<label>快递点：{{data.dpointName}}</label>
							</div>
							<div class="field">
								<label>快递类型：{{data.dtypeName}}</label>
							</div>
							<div class="field">
								<label>取件时间：{{data.takeTimes}}</label>
							</div>
							<div class="field">
								<label>支付状态：{{data.payStatus}}</label>
							</div>
							<!-- 	<div class="field">
								<label>物品状态：{{data.goodsStatus}}</label>
							</div> -->
						</div>
						<div class="ui error message"></div>

						<div class="ui fluid red submit button" style="margin-top: 15px;"
							v-show="data.orderStatus == '已取消'" @click="delOrder">删除订单</div>
						<navigator class="ui fluid  submit button" style="margin-top: 15px;"
							v-show="data.orderStatus != '已取消' && data.orderStatus != '已删除'" @click="cancelOrder">取消订单
						</navigator>
						<navigator url="cus_send" class="ui fluid  submit button" style="margin-top: 15px;">返回
						</navigator>
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
				id: '',
				data: {}
			}
		},
		computed: {
			cdate() {
				if (this.data.createDate) {
					let val = this.$utils.timestampToTime(this.data.createDate);
					console.log(val)
					return val;
				}
				return ''
			}
		},
		methods: {
			cancelOrder: function() {
				this.$utils.showModal("确定取消？", (res) => {
					if (res.confirm) {
						this.$req.order.cancelOrder('s', this.id, (res) => {
							let data = res.data;
							if (data.status) {
								uni.showToast({
									icon: 'none',
									title: "取消订单成功"
								})
								this.data.orderStatus = '已取消';
							} else {
								uni.showToast({
									icon: 'none',
									title: data.msg
								})
							}
						})
					}
				});
			},
			delOrder() {
				this.$utils.showModal("确定删除？", (res) => {
					if (res.confirm) {
						this.$req.order.delOrder('s', this.id, (res) => {
							let data = res.data;
							if (data.status) {
								this.data.orderStatus = '已删除';
								uni.showToast({
									icon: 'none',
									title: '删除成功',
									success() {
										setTimeout(() => {
											uni.redirectTo({
												url: 'cus_send'
											});
										}, 2000)
									}
								})
							} else {
								uni.showToast({
									icon: 'none',
									title: data.msg
								})
							}
						})
					}
				});
			},
		},
		onLoad(option) {
			this.id = option.id;
			if (!this.id || this.id.length < 1) {
				uni.showToast({
					icon: 'none',
					title: '此订单不存在'
				})
				return;
			}
			this.$req.order.checkOrder('s', this.id, (res) => {
				let data = res.data;
				if (data.status) {
					this.data = data.data;
				} else {
					uni.showToast({
						icon: 'none',
						title: data.msg
					})
				}
			})
		}
	}
</script>

<style>
</style>
