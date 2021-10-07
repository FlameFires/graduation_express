<template>
	<view>
		<div class="ui center aligned grid">
			<div class="column viewh">
				<div class="ui segment">
					<form class="ui equal width form">
						<h3>代取快递</h3>
						<div class="" style="text-align: left;">
							<div class="field"></div>
							<div class="field">
								<label>订单号：{{this.$utils.addPreZero(id)}}</label>
							</div>
							<div class="field">
								<label>下单时间：{{data.createDate}}</label>
							</div>
							<div class="field">
								<label>姓名：{{data.takerName}}</label>
							</div>
							<div class="field">
								<label>手机号：{{data.phoneNum}}</label>
							</div>
							<div class="field">
								<label>地址：{{data.address}}</label>
							</div>
							<div class="field">
								<label>快递点：{{data.pointName}}</label>
							</div>
							<div class="field">
								<label>快递类型：{{data.dtypeName}}</label>
							</div>
							<div class="field">
								<label>取件码：{{data.takeCode}}</label>
							</div>
							<div class="field">
								<label>支付状态：{{data.payStatus}}</label>
							</div>
							<!-- <div class="inline field">
								<label>物品状态：{{data.goodsStatus}}</label>
							</div> -->
						</div>
						<div class="ui error message"></div>
						<div class="ui fluid red submit button" style="margin-top: 15px;" @click="delOrder">
							删除订单</div>
						<navigator url="adm_take" class="ui fluid  submit button" style="margin-top: 15px;">返回
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
			delOrder: function() {
				let id = this.id;
				if (!id || id.length < 1) {
					this.$utils.showToast('id为空');
					return;
				}

				let req = this.$req;
				let utils = this.$utils;
				uni.showModal({
					title: '提示',
					content: '确定删除？',
					success: function(r) {
						if (r.confirm) {
							req.order.delOrder('t', id, (res) => {
								let data = res.data;
								if (res.data.status) {
									utils.showToast('删除成功');
									setTimeout(() => {
										uni.redirectTo({
											url: "adm_take"
										});
									}, 2000);
								} else {
									uni.showToast({
										title: res.data.msg,
										icon: 'none'
									})
								}
							})
						}
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
			this.$req.order.checkOrder('t', this.id, (res) => {
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
