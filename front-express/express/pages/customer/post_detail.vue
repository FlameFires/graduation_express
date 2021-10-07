<template>
	<view>
		<div class="ui center aligned grid">
			<div class="column viewh">
				<div class="ui segment" >

					<h3 style="background-color: #767676;text-align: center;padding: 10px 0;color: white;height: 43px;">
						<navigator url='community'>
							<i class="icon arrow left" style="position: absolute;left: 10px;top: 0px;line-height: 43px;"></i>
						</navigator>
						{{data.title}}
					</h3>
					<div class="ui " style="padding:0 15px; line-height: 30px;font-size: 15px;font-family: '微软雅黑', Courier, monospace;">
						<div class="content" style="text-align: left;background-color: #ffffff;padding: 10px;font-size: 15px;font-family: '微软雅黑', Courier, monospace;">
							<pre>
					{{data.content}}
									</pre>


						</div>

						<!-- <five-commentlist :commentList="commentList" @clickPraise="clickPraiseSig" @clickDelete="clickDeleteSig"></five-commentlist>

						<view class="view-conmment-send-bottom">
							<view class=" view-comment-textarea" @click="clickComment()">
								<view class="textarea-comment">写评论…</view>
							</view>
						</view>
						<five-comment ref="detailComment" @sendComment="sendComment" :placeholder="placeholder"></five-comment> -->
					</div>


				</div>
			</div>
		</div>


	</view>
</template>

<script>
	export default {
		// name:'post_detail',
		data() {
			return {
				data: {},
				commentList: [{
						"COMMENT_TIME": "2020-07-07 10:33:29",
						"FIRSTNICKNAME": "网友1271646542",
						"IS_PRAISE": null,
						"TAG": "",
						"COMMENT": "我地方撒机开绿灯解放了 ",
						"PRAISE_NUM": 0,
						"CANDELETE": 1,
						"HEADIMGURL": "http://img2.imgtn.bdimg.com/it/u=2659658743,1944621503&fm=26&gp=0.jpg",
						"SECONDNICKNAME": 'hello'
					},
					{
						"COMMENT_TIME": "2020-07-07 10:32:52",
						"FIRSTNICKNAME": "网友3778839",
						"IS_PRAISE": null,
						"TAG": "",
						"COMMENT": "",
						"PRAISE_NUM": 0,
						"CANDELETE": 0,
						"HEADIMGURL": "http://pic1.zhimg.com/50/v2-e88c0426c1ccc429dbedea3d01e5fac2_hd.jpg",
						"PARENTID": "1",
						"SECONDNICKNAME": null

					}
				],
				detailInfo: {},
				placeholder: '请输入评论内容…',
				commentParam: {}
			}
		},
		methods: {
			/**
			 * 获取用户信息
			 */
			getData() {
				this.detailInfo = data.dataInfo;
			},
			/**
			 * 获取评论详情
			 */
			getComment() {
				this.commentList = data.commentList;
			},

			/**
			 * 格式化发布日期
			 * @param {Object} dateString
			 */
			formateDate(dateString) {
				dateString = parseInt(dateString) * 1000;
				let date = new Date(dateString);
				let formatStr = dateUtils.dateFormat('YYYY年mm月dd日 HH:MM:SS', date);
				return formatStr;
			},

			/**
			 * 回复问题
			 */
			clickComment() {
				this.commentParam = {
					COMMENT_TIME: '2020-07-07 14:30:01',
					FIRSTNICKNAME: '网友45454545',
					CHILD_ANWSER_LIST: [],
					IS_PRAISE: null,
					PRAISE_NUM: 0,
					CANDELETE: 1,
					HEADIMGURL: 'http://img4.imgtn.bdimg.com/it/u=2858424520,3197172862&fm=11&gp=0.jpg',
					SECONDNICKNAME: null
				};
				this.$refs.detailComment.open();
			},
			/**
			 * 详情点赞
			 * @param {Object} isPraise
			 */
			clickPraise(isPraise) {
				let praiseParam = '1';
				let praiseNum = parseInt(this.detailInfo.PRAISE_NUM);
				switch (isPraise) {
					case '0':
						praiseParam = '1';
						praiseNum++;
						break;
					case '1':
						praiseParam = '0';
						praiseNum--;
						break;
				}
				this.detailInfo.IS_PRAISE = praiseParam;
				this.detailInfo.PRAISE_NUM = praiseNum--;
			},

			/**
			 *  查看图片大图
			 * @param {Object} images
			 * @param {Object} index
			 */
			cliclImage(images, index) {
				uni.previewImage({
					urls: images,
					current: index,
					longPressActions: {
						itemColor: '#12B368',
						success: function(data) {},
						fail: function(err) {}
					}
				});
			},
			/**
			 * 删除详情
			 */
			clickDeleteDetail() {
				uni.showModal({
					title: '提示',
					content: '确定要删除吗？',
					confirmColor: '#12B368',
					success: function(res) {
						/* 调用接口删除 */
					}
				});
			},
			/**
			 * 点赞多级评论
			 * @param {Object} item
			 */
			clickPraiseComment(item) {
				//只能点赞不能取消
				if (item.IS_PRAISE == '1') {
					return;
				}
				item.IS_PRAISE = '1';
				item.PRAISE_NUM++;
			},
			/**
			 * 删除多级评论
			 */
			clickDeleteComment(item) {
				uni.showModal({
					title: '提示',
					content: '确定要删除评论吗？',
					confirmColor: '#12B368',
					success: function(res) {
						/* 调用接口删除 */
					}
				});
			},
			/**
			 * 删除多级子评论
			 */
			clickDeleteCommentChild(item) {
				uni.showModal({
					title: '提示',
					content: '确定要删除评论吗？',
					confirmColor: '#12B368',
					success: function(res) {
						/* 调用接口删除 */
					}
				});
			},

			/**
			 * 回复 评论
			 * @param {Object} item
			 */
			clickRecomment(item) {
				this.commentParam = {};
				this.$refs.detailComment.open();
				this.placeholder = '回复' + item.FIRSTNICKNAME + ':';
			},
			/**
			 * 回复评论的评论
			 * @param {Object} item
			 */
			clickRecommentChild(item) {
				this.commentParam = {};
				this.$refs.detailComment.open();
				this.placeholder = '回复' + item.FIRSTNICKNAME + ':';
			},
			/**
			 * 删除单级评论
			 */
			clickDeleteSig(item) {
				uni.showModal({
					title: '提示',
					content: '确定要删除评论吗？',
					confirmColor: '#12B368',
					success: function(res) {
						/* 调用接口删除 */
					}
				});
			},
			/**
			 * 点赞单级评论
			 */
			clickPraiseSig(item) {
				//只能点赞不能取消
				if (item.IS_PRAISE == '1') {
					return;
				}
				item.IS_PRAISE = '1';
				item.PRAISE_NUM++;
			},
			/**
			 * 发送评论
			 * @param {Object} result
			 */
			sendComment(result) {
				this.commentParam.COMMENT = result;
				this.commentList.push(this.commentParam);
			}
		},
		onLoad(option) {
			console.log(option.id)
			this.$req.post.check(option.id, (res) => {
				let data = res.data;
				if (data.status) {
					this.data = data.data;
				} else {
					this.$utils.showToast(data.msg);
				}
			})
		}
	}
</script>

<style scoped>
	
	.ui.segment {
	    padding: 0 !important;
	}
</style>

<style lang="scss">
	@import '@/common/css/iconfont.css';

	// page,
	// .content-ques {
	// 	background-color: #fbfbfb;
	// }

	.view-delete-detail {
		flex: 1;
		display: flex;
		font-size: 26rpx;
	}

	.bottom-item {
		flex: 1;
		display: flex;
		flex-direction: row;
		align-items: center;
		height: 80rpx;

		.bottom-item-image {
			font-size: 40rpx;
			color: $uni-text-color;
		}

		.bottom-item-text {
			font-size: $uni-font-size-sm;
			margin-left: 20rpx;
			color: $uni-text-color;
		}
	}

	.view-content-content {
		background-color: white;
		flex-direction: column;
		padding: 30rpx 30rpx 0 30rpx;
	}

	.view-header {
		display: flex;
		align-items: center;
		flex-direction: row;

		.image-photo {
			height: 90rpx;
			width: 90rpx;
			background-color: #fbfbfb;
			border: 1px solid #f6f6f6;
			border-radius: 45rpx;
			margin-right: 30rpx;
		}

		.header-txt {
			display: flex;
			flex-direction: column;
			color: $uni-text-color-grey;
			line-height: 1.5;
			justify-content: center;
			font-size: $uni-font-size-sm;

			.name {
				color: #004261;
				font-weight: bold;
				font-size: 32rpx;
			}
		}
	}

	.sub {
		color: $uni-text-color-grey;
		font-size: $uni-font-size-sm;
	}

	.item-desc {
		color: $uni-text-color;
		line-height: 1.6;
		font-size: 32rpx;
		margin-top: 20rpx;
	}

	.item-imgs {
		margin: 20rpx 0;
		display: flex;
		flex-direction: row;
		-webkit-flex-wrap: wrap;
		flex-wrap: wrap;

		.image-imgs {
			border-radius: 10rpx;
			background-color: #fbfbfb;
			height: 220rpx;
			width: 220rpx;
			margin: 1px 4rpx;
		}
	}

	.view-conmment-send-bottom {
		width: 100%;
		position: fixed;
		bottom: 0;
		left: 0;
		display: flex;
		flex-direction: row;
		background-color: white;
		border-top: 1px solid #fbfbfb;
	}

	.view-comment-textarea {
		background-color: #f8f8f8;
		border-radius: 40rpx;
		padding: 15rpx 20rpx;
		margin: 25rpx 20rpx;
		width: 80%;
		flex: 1;
		align-items: center;
	}

	.textarea-comment {
		padding: 0;
		// width: 100%;
		height: 100%;
		font-size: 32rpx;
		// color: $uni-text-color;
		color: #666;
	}

	.icon-comment {
		font-size: 46rpx;
		color: $uni-text-color;
		margin: 0 30rpx 0 5rpx;
	}

	.view-btn-comment {
		display: flex;
		justify-content: center;
		align-items: center;
		font-size: 32rpx;
		color: white;
		height: 70rpx;
		width: 120rpx;
		border-radius: 10rpx;
		background-color: #f9546f;
		font-weight: 500;
	}

	.textarea-bottom {
		background-color: white;
		padding: 20rpx;
	}
</style>
