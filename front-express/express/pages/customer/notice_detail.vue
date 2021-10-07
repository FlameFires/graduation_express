<template>
	<view>
		<div class="ui center aligned grid">
			<div class="column viewh">
				<div class="ui segment">

					<h3 style="background-color: #767676;text-align: center;padding: 10px 0;color: white;height: 43px;">
						<navigator url="notice_list">
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
				data: {}
			}
		},
		methods: {
		},
		onLoad(option) {
			console.log(option.id)
			this.$req.notice.check(option.id, (res) => {
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
