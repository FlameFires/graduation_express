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
							<div class="field">
								<label>物品状态：{{data.goodsStatus}}</label>
							</div>
						</div>
						<div class="ui error message"></div>

						<navigator class="ui fluid green submit button" style="margin-top: 15px;" v-show="data.courierId == 0"
							@click="acceptOrder">
							接受订单</navigator>
						<navigator class="ui fluid green submit button" style="margin-top: 15px;"
							v-show="data.orderStatus!='已完成' && data.courierId >0" @click="sended">
							已送达</navigator>
						<navigator url="cou_send" class="ui fluid  submit button" style="margin-top: 15px;">返回
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
				data: {},
				id: "",
			}
		},
		methods: {
			acceptOrder: function() {
				let id = this.id;
				if (!id || id.length < 1) {
					this.$utils.showToast('id为空');
					return;
				}

				this.$req.order.acceptOrder('s', this.id, (res) => {
					if (res.data.status) {
						this.$utils.showToast('接单成功');
						let uid = this.$utils.getUser().id;
						this.data.courierId = uid;
					} else {
						uni.showToast({
							title: res.data.msg,
							icon: 'none'
						})
					}
				})
			},
			sended() {
				this.$req.order.sendedOrder('s', this.id, (res) => {
					if (res.data.status) {
						this.$utils.showToast('完成订单');
						this.data.orderStatus='已完成';
					} else {
						uni.showToast({
							title: res.data.msg,
							icon: 'none'
						})
					}
				})
			}
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
					console.log(data)
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
