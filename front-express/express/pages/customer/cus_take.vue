<template>
	<view>
		<div class="ui center aligned grid">
			<div class="column viewh">
				<div class="ui segment">
					<page-head :active=2></page-head>

					<!-- <div class="ui action input fluid" style="margin-top: 15px;">
						<input type="text" style="height: 2.5rem;" placeholder="搜索...">
						<div class="ui button" @click="search">Search</div>
					</div> -->

					<navigator url='../customer/take_go' class="circular big ui icon button" style="position: absolute;right: 20px;bottom: 80px;z-index: 100;">
						<i class="icon plus"></i>
					</navigator>

					<scroll-view scroll-y="true"  style="margin-top: 14px;height: 590px;" >
					<div class="ui info message" v-if="data.length < 1">
						<div class="header">
							请开始下单吧
						</div>
					</div>
						<div id="listContents" class="ui">
							<div class="ui segment " @click='check(item.id)' v-for="(item ,i) in data" :key="item.id">
								<div class="desc">
									<div>姓名：{{item.takerName}}</div>
									<div>手机号：{{item.phoneNum}}</div>
									<div>下单时间：{{item.createDate}}</div>
								</div>
								<div class="stat">
									<div class="ui label">
										<i class="check circle icon" :class="{outline:item.payStatus == '已支付'}"></i>
										{{item.payStatus}}</div>
									<div class="ui label">
										<i class="check circle icon" :class="{outline:item.orderStatus != '进行中'}"></i>
										{{item.orderStatus}}</div>
								</div>
							</div>
						</div>
					</scroll-view>

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
					url: '/pages/customer/take_detail?id=' + id
				});
			}
		},
		onLoad() {
			this.$req.order.getNewOrderList('t', (res) => {
				let data = res.data;
				if (data.status) {
					this.data = data.data;
				}
			})
		}
	}
</script>


<style scoped>
</style>
