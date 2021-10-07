<template>
	<view>
		<div class="ui center aligned grid">
			<div class="column viewh">
				<div class="ui segment">
					<admin-head :active=2></admin-head>
					<form class="ui form">
						<div class="field" style="margin-top: 10px">
							<input type="text" placeholder="*标题" v-model="data.title">
						</div>
						<!-- <view style="margin-bottom: 12px;">
							<view>
								<radio-group @change="rchange">
									<label class="radio">
										<radio value="公告" :checked="data.type != '活动'" />公告</label>
									<label class="radio" style="margin-left: 30px;">
										<radio value="活动" :checked="data.type == '活动'" />活动</label>
								</radio-group>
							</view>
						</view> -->
						<div class="field" style="text-align: left;">
							<textarea name="moreinfo" style="height: 350px;" cols="30" rows="10" placeholder="*公告内容" v-model="data.content"></textarea>
						</div>
						<div class="field" style="margin-top: 10px">
							<input type="text" placeholder="活动链接" v-model="data.link">
						</div>
						<div class="ui fluid teal submit button" style="margin-bottom: 10px" @click="release">发布</div>
						<navigator open-type="navigateBack" class="ui fluid  submit button">返回</navigator>
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
				data: {
					title: '',
					content: '',
					type: '公告',
					link:''
				}
			}
		},
		methods: {
			rchange: function(evt) {
				this.data.type = evt.target.value;
			},
			release() {
				if (this.data.title.length < 2) {
					this.$utils.showToast('标题字数太少了')
					return;
				}

				if (this.data.content.length < 5) {
					this.$utils.showToast('内容字数太少了')
					return;
				}

				this.$req.notice.add(this.data, (res) => {
					let data = res.data;
					if (data.status) {
						uni.showToast({
							icon:'none',
							title:'发布成功',
							success() {
								setTimeout(()=>{
									uni.navigateTo({
										url:'notice_list'
									})
								},2000)
							}
						})
					} else {
						this.$utils.showToast(data.msg);
					}
				})
			}
		}
	}
</script>

<style>
</style>
