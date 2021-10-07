<template>
	<view>
		<div class="ui center aligned grid">
			<div class="column viewh">
				<form class="ui form" style="margin-top: 50px">
					<div class="ui segment">
						<courier-head :active=3></courier-head>

						<img src="/static/images/kdk.png" class="image" style="width: 100px;border-radius: 50px;margin-top: 10px;">
						<div class="field" style="margin-top: 10px">
							<div class="ui left icon input">
								<i class="user icon"></i>
								<input type="text" name="username" placeholder="姓名" v-model="info.name">
							</div>
						</div>

						<div class="field">
							<div class="ui left icon input">
								<i class="marker icon"></i>
								<input type="text" name="address" placeholder="请输入地址详细到宿舍门牌号"  v-model="info.address">
							</div>
						</div>
						<div class="field">
							<div class="ui left icon input">
								<i class="phone icon"></i>
								<input type="text" name="mobile" placeholder="请输入手机号/校园短号" v-model="info.phone">
							</div>
						</div>
						<div class="field">
							<select>
								<option value="">保密</option>
								<option value="1">男</option>
								<option value="0">女</option>
							</select>
						</div>
						<div class="field">
							<div class="ui left icon input">
								<i class=" icon"></i>
								<textarea name="" cols="30" rows="4" placeholder="备注信息"  v-model="info.remarks"></textarea>
							</div>
						</div>

						<div class="ui error message"></div>

						<div class="ui fluid teal submit button" style="margin-bottom: 10px" @click="save">保存</div>
						<navigator open-type="navigateBack" class="ui fluid  submit button">返回</navigator>

						
					</div>


				</form>
			</div>
		</div>

	</view>
</template>

<script>
	export default {
		data() {
			return {
				info: {}
			};
		},
		methods: {
			save: function() {
				console.log('保存')
			}
		},
		onLoad() {
			this.info = this.$store.state.info;
			if(!this.$store.getters.isLogin){
				uni.showToast({
					icon:'none',
					title:'请先登录'
				})
			}
		}
	}
</script>

<style>

</style>
