<template>
	<view>
		<div class="ui center aligned grid">
			<div class="column viewh">
				<div class="ui segment">
					<page-head :active=3></page-head>

					<!-- <div class="ui action input fluid " style="margin-top: 15px;">
						<input type="text" style="height: 2.5rem;" placeholder="搜索...">
						<div class="ui button" @click="search">Search</div>
					</div> -->

					<navigator url='../customer/send_go' class="circular big ui icon button" style="position: absolute;right: 20px;bottom: 80px;z-index: 100;">
						<i class="icon plus"></i>
					</navigator>

					<scroll-view scroll-y="true" style="margin-top: 14px;height: 590px;" >

						<div class="ui info message" v-if="data.length < 1">
							<!-- <i class="close icon" data-hello='hello' id="iClose" @click="msgClose"></i> -->
							<div class="header">
								请开始下单吧
							</div>
						</div>
						<div id="listContents" class="ui">
							<div class="ui segment " @click='check(item.id)' v-for="(item ,i) in data" :key="item.id">
								<div class="desc">
									<div>姓名：{{item.receiverName}}</div>
									<div>手机号：{{item.receiverPhoneNum}}</div>
									<div>下单时间：{{item.createDate}}</div>
									<div>订单状态：<span :class="{redColor:item.orderStatus == '已取消'}">{{item.orderStatus}}</span></div>
								</div>
								<div class="stat">
									<div class="ui label">
										<i class="check circle icon" :class="{outline:item.payStatus == '已支付'}"></i>
										{{item.payStatus}}</div>
									<div class="ui label">
										<i class="check circle icon" :class="{outline:item.payStatus == '已寄出'}"></i>
										{{item.goodsStatus}}</div>
								</div>
							</div>
						</div>
					</scroll-view>


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
				data: []
			}
		},
		methods: {
			check: function(id) {
				console.log('查看')
				uni.navigateTo({
					url: '/pages/customer/send_detail?id=' + id
				});
			},
			getDataList(pageNo, pageSize) {
				this.$req.order.getNewOrderList('s', (res) => {
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
		},
		onLoad() {
			this.$req.order.getNewOrderList('s', (res) => {
				let data = res.data;
				if (data.status) {
					this.data = data.data;
				} else {
					
				}
			})
		}
	}
</script>


<style>
</style>
