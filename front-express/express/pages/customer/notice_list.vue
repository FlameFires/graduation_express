<template>
	<view>
		<div class="ui center aligned grid">
			<div class="column viewh">
				<div class="ui segment">
					<page-head :active=4></page-head>

					<scroll-view scroll-y="true"  style="margin-top: 14px;height: 590px;" >

						<div class="ui info message" v-if="data.length < 1">
							<div class="header">
								没有任何数据
							</div>
						</div>
						<div id="listContents" class="ui segments">
							<div class="ui segment " @click='check(item.id)' v-for="(item ,i) in data" :key="item.id" style="padding-bottom: 60px;padding-left: 30px;">
								
								<div class="desc">
									<div>标题：{{item.title}}</div>
									<div>日期：{{item.createDate}}</div>
								</div>
								<div class="ui tag label small red" :class="{green:item.type=='活动'}" style="z-index: 100;" @click="del(item.id)">
									{{item.type}}</div>
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
			del(id) {
				uni.showModal({
					content: '确定删除',
					showCancel: true,
					success(res) {
						if (res.confirm) {
							// TODO 删除
						}
					}
				})
			},
			check: function(id) {
				console.log('查看')
				uni.navigateTo({
					url: '/pages/customer/notice_detail?id=' + id
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
			this.$req.notice.query((res) => {
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
