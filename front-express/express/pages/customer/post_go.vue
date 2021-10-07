<template>
	<view>
		<div class="ui center aligned grid">
			<div class="column viewh">
				<div class="ui segment">
					<page-head :active=1></page-head>
					<form class="ui form">
						<div class="field" style="margin-top: 10px">
								<input type="text" name="username" placeholder="标题" v-model="data.title">
						</div>
						<div class="field" style="text-align: left;">
							<textarea name="moreinfo" style="height: 400px;" cols="30" rows="10" placeholder="内容" v-model="data.content"></textarea>
						</div>
						<div class="ui fluid teal submit button" style="margin-bottom: 10px" @click="release">发布</div>
						<navigator url="community" class="ui fluid  submit button">返回</navigator>
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
				data:{
					title:'',
					content:''
				}
			}
		},
		methods:{
			release(){
				if(this.data.title.length < 2){
					this.$utils.showToast('标题字数太少了')
					return;
				}
				
				if(this.data.content.length < 10){
					this.$utils.showToast('内容字数太少了')
					return;
				}
				
				this.$req.post.add(this.data,(res)=>{
					let data = res.data;
					if (data.status) {
						this.data = data.data;
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
