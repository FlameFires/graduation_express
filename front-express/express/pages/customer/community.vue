<template>
	<view>
		<div class="ui center aligned grid">
			<div class="column viewh">
				<div class="ui segment">
					<page-head :active="1"></page-head>

					<pop-win :data="showData" :isShow="isShow" v-on:close='hidden'></pop-win>


					<div class="lt_con" style="margin-top: 14px; height: 290px;border: 1px solid #000000;">
						<scroll-view scroll-y="true" style="height: 85%;text-align: left;padding: 0 0 0 10px;padding-bottom: 10px;">
							<div style="height: 10px;"></div>
							<div style="height: auto;padding: 5px 0 10px 10px;" v-for="(item,i) in comments" :key='i'>
								<img class="ui circular image" style="width: 50px;float: left;margin-right: 10px;" src="@/static/kdk.png">
								<div>{{item.name}}</div>
								<p style="margin-top: 5px;">{{item.content}}</p>
							<div class="ui divider" style="margin-top: 13px;margin-bottom: 0;"></div>
							</div>
						</scroll-view>
						<div style="height: 15%;padding: 4px 10px;white-space: nowrap;">
							<input v-model="msg" placeholder="发布我的评论..."
								style="background-color: #f2f2f2;border-radius: 15px;text-align: left;padding-left: 10px; width: 80%;float:left;margin-right: 5px;">
							<div class="ui primary button" @click="put">发布</div>
						</div>
					</div>

					<scroll-view scroll-y="true" class="gg_bigcon">

						<div class="gg_con" v-for="(item,i) in notices" :key="item.id" @click='checkNotice(item.id)'>
							<div class="gg_left">
								<div class="gg_left_title">{{item.title}}</div>
								<div class="gg_left_date">发布日期：{{format(item.createDate)}}</div>
							</div>
							<div class="gg_right">
								<div class="gg_right_li">公告</div>
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
				notices: [], // 公告
				key: "",
				comments:[],
				isShow: false,
				showData: {}, // 要展示的数据
				msg:'' ,// 要发布的评论
			};
		},
		methods: {
			put(){
				let msg = this.msg;
				if(msg.length > 0){
					this.$req.comment.put(msg,(res)=>{
						let data = res.data;
						if(data.status){
							this.msg = '';
							name = this.$utils.getUser().name;
							this.comments.unshift({
								name:name,
								content:msg
							})
							this.$utils.showToast('发布成功');
						}
					})
				}
			},
			hidden() {
				this.isShow = false;
			},
			check: function(val) {
				uni.navigateTo({
					url: "post_detail?id=" + val,
				});
			},
			format(val) {
				return this.$utils.dateFormat("YYYY年mm月dd  HH:MM", new Date(val));
			},
			search: function() {
				if (this.key.length < 1) {
					this.$utils.showToast("请输入搜索内容");
					return;
				} else {
					this.$req.post.query(this.key, (res) => {
						let data = res.data;
						if (data.status) {
							this.data = data.data;
						} else {
							this.$utils.showToast(data.msg);
						}
					});
				}
			},
			checkNotice(id) {
				this.notices.forEach((item) => {
					if (item.id === id) {
						this.showData = item;
						this.isShow = true;
					}
				})
			}
		},
		onLoad() {
			this.$req.comment.query((res) => {
			  let data = res.data;
			  if (data.status) {
			    this.comments = data.data;
			  } else {
			    this.$utils.showToast(data.msg);
			  }
			});

			this.$req.notice.query((res) => {
				let data = res.data;
				if (data.status) {
					this.notices = data.data;
				} else {
					this.$utils.showToast(data.msg);
				}
			})
		},
	};
</script>

<style scoped>
	.gg_bigcon {
		position: relative;
		bottom: -14px;
		height: 300px;
	}

	/* 聊天 */
	.lt_con {}



	.gg_con {
		height: 100px;
		width: 100%;
		background-color: #f2f2f2;
		border-radius: 10px;
		text-align: left;
		overflow: hidden;
		position: relative;
		margin-bottom: 10px;
	}

	.gg_con:hover {
		background-color: rgba(0, 0, 0, 0.1);
	}

	.gg_left {
		width: 90%;
		height: 100%;
		float: left;
		padding: 15px 0px 42px 30px;
	}

	.gg_left_date {
		position: absolute;
		bottom: 15px;
		left: 30px;
	}

	.gg_right {
		background-color: #ce3232;
		width: 10%;
		height: 100%;
		float: left;
		text-align: center;
		font-size: 12px;
		font-weight: bold;
		color: yellow;
	}

	.gg_right_li {
		width: 15px;
		line-height: 47px;
		margin: 0 auto;
	}
</style>
