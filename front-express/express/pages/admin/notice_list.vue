<template>
	<view>
		<div class="ui center aligned grid">
			<div class="column viewh">
				<div class="ui segment">
					<admin-head :active=2></admin-head>

					<pop-win :data="showData" :isShow="isShow" v-on:close='hidden'></pop-win>

					<scroll-view scroll-y="true" style="margin-top: 14px;height: 590px;">
						<div class="ui info message" v-if="data.length < 1">
							<div class="header">
								没有任何数据
							</div>
						</div>
						<div id="listContents" class="ui">

							<div class="ui segment" style="padding-bottom: 15px;padding-left: 30px;"
								v-for="(item ,i) in data" :key="item.id">
								<div class="desc">
									<div class="ui green ribbon small label">{{item.type}}</div>
									<div style="margin-top: 10px;">标题：{{dealStr(item.title)}}</div>
									<div style="margin-top: 30px;color: gray;">日期：{{item.createDate}}</div>
								</div>
								<div class="ui label small red" style="z-index: 100;" @tap="del(item.id)">
									删除</div>
								<div class="ui label small blue" style="z-index: 100;margin-top: 13px;"
									@tap="upd(item.id)">
									修改</div>
								<div class="ui label small" style="z-index: 100;margin-top: 13px;"
									@tap="checkNotice(item.id)">
									查看</div>
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
				data: [],
				// 评论组件
				isShow: false,
				showData: {}, // 要展示的数据
				msg: '', // 要发布的评论
			}
		},
		methods: {
			dealStr(str) {
				if (str.length > 10) {
					let nstr = str.substring(0, 11) + '...';
					return nstr;
				}
				return str;
			},
			del(id) {
				let req = this.$req;
				let that = this;
				uni.showModal({
					title: '警告',
					content: '确定删除',
					showCancel: true,
					success(res) {
						if (res.confirm) {
							req.notice.del(id, (res) => {
								let data = res.data;
								if (data.status) {
									that.data.forEach((item,i)=>{
										if(item.id === id){
											that.data.splice(i)
										}
									})
									uni.showToast({
										icon: 'none',
										title: '删除成功'
									})
								} else {

								}
							})
						}
					},
					fail() {
						console.log('失败')
					}
				})
			},
			upd(id) {
				uni.navigateTo({
					url: '/pages/admin/notice_upd?id=' + id
				});
			},
			check: function(id) {
				uni.navigateTo({
					url: '/pages/admin/notice_detail?id=' + id
				});
			},
			checkNotice(id) {
				this.data.forEach((item) => {
					if (item.id === id) {
						this.showData = item;
						this.isShow = true;
					}
				})
			},
			hidden() {
				this.isShow = false;
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
	.label {}
</style>
