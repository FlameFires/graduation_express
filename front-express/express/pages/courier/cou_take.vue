<template>
	<view>
		<div class="ui center aligned grid">
			<div class="column viewh">
				<div class="ui segment">
					<courier-head :active=1></courier-head>

					<picker-display @dateVal="getDateVal"></picker-display>


					<div class="ui action input fluid " style="margin-top: 15px;">
						<input type="text" style="height: 2.5rem;" placeholder="手机号 / 订单号" v-model="stxt">
						<div class="ui button" @click="search">Search</div>
					</div>

					<scroll-view scroll-y="true" style="margin-top: 14px;height: 500px;">
						<div class="ui info message" v-if="data.length < 1">
							<div class="header">
								没有任何订单可接
							</div>
						</div>
						<div id="listContents" class="ui">
							<div class="ui segment " @click='check(item.id)' v-for="(item ,i) in data" :key="item.id">
								<div class="desc">
									<div>姓名：{{item.takerName}}</div>
									<div>手机号：{{item.phoneNum}}</div>
									<div>下单时间：{{item.createDate}}</div>
									<div>订单状态：<span
											:class="{redColor:item.orderStatus == '已取消'}">{{item.orderStatus}}</span>
									</div>
								</div>
								<div class="stat">
									<div class="ui label">
										<i class="check circle icon" :class="{outline:item.payStatus == '已支付'}"></i>
										{{item.payStatus}}
									</div>
									<div class="ui label">
										<i class="check circle icon" :class="{outline:item.courierId >0}"></i>
										{{ item.courierId > 0 ? '已接单':'未接单'}}
									</div>
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
				data: [],
				date: '',
				stxt:''
			}
		},
		methods: {
			getDateVal(val) {
				this.date = val;
				const that = this;
				 this.$options.methods.getList(that);
			},
			check: function(id) {
				uni.navigateTo({
					url: '/pages/courier/take_detail?id=' + id
				});
			},
			search(){
				this.$options.methods.getList(this);
			},
			getList(that){
				that.$req.order.getRecList('t', that.date,that.stxt, (res) => {
					let data = res.data;
					if (data.status) {
						that.data = data.data;
					} 
					else if(data.msg === "没有获取到任何数据"){
						that.data = []
					}
					else {
						uni.showToast({
							icon: 'none',
							title: data.msg
						})
					}
				})
			}
		},
		onLoad() {
			this.getList(this);
		}
	}
</script>


<style scoped>

</style>
