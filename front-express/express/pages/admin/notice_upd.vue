<template>
	<view>
		<div class="ui center aligned grid">
			<div class="column viewh">
				<div class="ui segment">
					<h3 style="background-color: #767676;text-align: center;padding: 10px 0;color: white;height: 43px;">
						<navigator url="notice_list">
							<i class="icon arrow left"
								style="position: absolute;left: 10px;top: 0px;line-height: 43px;"></i>
						</navigator>
						修改公告
					</h3>


					<div class="ui form" style="padding: 10px;text-align: center;">
						<table>
							<tr>
								<td style="widht:50px;">标题：</td>
								<td><input type="text" placeholder="不多于10字" style="text-align: left;"
										v-model="data.title"></td>
							</tr>
							<tr style="height: 30px;">
							</tr>
							<tr style="height: 300px;">
								<td style="widht:50px;">内容：</td>
								<td>
									<textarea rows="0" placeholder="叙述内容" @input="txtChange" v-model="data.content">{{data.content}}</textarea>
									<span class="wordnum">{{word}} 字</span>
								</td>
							</tr>
							<tr style="height: 20px;">
							</tr>
							<tr>
								<td colspan="2">

									<navigator url="notice_list" class="ui button">取消</navigator>
									<div class="ui primary button" @click="save()">保存</div>
								</td>
							</tr>
						</table>
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
				data: {
					title: '',
					content: ''
				},
				word:0,
				id:'',
			}
		},
		methods: {
			save() {
				let req = this.$req;
				let data = this.data;
				uni.showModal({
					title:'提示',
					content:'确定修改？',
					showCancel:true,
					success(res){
						if(res.confirm){
							req.notice.update(
							data,(res)=>{
								data = res.data;
								if(data.status){
									uni.showToast({
										icon:'none',
										title:'修改成功'
									})
								}else{
									uni.showToast({
										icon:'none',
										title:data.msg
									})
								}
							})
						}
					}
				})
			},
			txtChange(){
				this.word = this.data.content.length;
			},
			getData(){
				
			}
		},
		onLoad(option) {
			let id = option.id;
			this.id = id;
			
			this.$req.notice.check(id,(res)=>{
				let data = res.data;
				if(data.status){
					this.data = data.data;
				}else{
					
				}
			})
		}
	}
</script>

<style>
	.ui.segment {
		padding: 0 !important;
	}

	table {
		width: 100%;
	}

	table,
	tr,
	td {
		/* border: 1px solid black; */
	}

	table>tr>td {
		text-align: center;
	}

	table tr>td:first-of-type {
		width: 50px;
	}

	textarea {
		height: 450px;
		width: 100%;
		background-color: red;
		text-align: left;
	}

	.bt_group>div {
		float: left;
		text-align: center;
	}

	.wordnum {
		position: absolute;
		right: 20px;
		bottom: 70px;
	}
</style>
