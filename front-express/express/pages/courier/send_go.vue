<template>
	<view>
		<div class="ui center aligned grid">
			<div class="column viewh">
				<div class="ui segment">
					<form class="ui equal width form" style="margin-top: 50px">
						<h3>代寄快递</h3>
						<div class="">
							<div class="inline required field" style="margin-top: 10px">
								<label>姓名</label>
								<div class="ui left icon input">
									<i class="user icon"></i>
									<input type="text" name="username" placeholder="姓名" v-model="data.name">
								</div>
							</div>
							<div class="inline required field">
								<label>手机号</label>
								<div class="ui left icon input">
									<i class="phone icon"></i>
									<input type="text" name="mobile" placeholder="手机号" v-model="data.phone">
								</div>
							</div>
							<div class="inline required field">
								<label>取件地址</label>
								<div class="ui left icon input">
									<i class="marker icon"></i>
									<input type="text" name="address" placeholder="取件地址" v-model="data.taddress">
								</div>
							</div>
							<div class="inline required field">
								<label>放送地址</label>
								<div class="ui left icon input">
									<i class="marker icon"></i>
									<input type="text" name="address" placeholder="放送地址" v-model="data.saddress">
								</div>
							</div>
							<div class="inline required error field">
								<label>取货时间</label>
								<select>
									<option value="">请选择</option>
									<option value="1">12:00~14:00</option>
									<option value="2">18:00~19:30</option>
								</select>
							</div>
							<div class="inline required field">
								<label>快递点</label>
								<select>
									<option value="">请选择</option>
									<option value="1">小兵驿站</option>
									<option value="0">摩西管家</option>
									<option value="0">益丰粮油</option>
									<option value="0">e创快递</option>
									<option value="0">摩西管家</option>
									<option value="0">摩西管家</option>
								</select>
								<select style="margin-left: 15px;">
									<option value="">请选择</option>
									<option value="1">中通</option>
									<option value="2">韵达</option>
									<option value="2">申通</option>
									<option value="2">顺丰</option>
									<option value="2">圆通</option>
								</select>
							</div>
							<div class="inline required field">
								<label>物品类型</label>
								<select>
									<option value="">请选择</option>
									<option value="1">食用品</option>
									<option value="1">化妆品</option>
									<option value="1">衣物</option>
									<option value="1">电子产品</option>
								</select>
							</div>
						</div>
						<div class="field" style="margin-top: 15px;">
							<div class="ui left icon input">
								<i class=" icon"></i>
								<textarea name="" rows="1" placeholder="备注信息" v-model="data.remarks"></textarea>
							</div>
						</div>

						<div class="ui error message"></div>

						<div class="ui fluid teal submit button" style="margin-bottom: 10px" @click="save">下单</div>
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
				data:{
					name:'',
					phone:'',
					taddress:'',
					saddress:'',
					tdate:'',
					dpointid:'',
					dtypeid:'',
					gid:'',
					remarks:''
				}
			}
		},
		methods: {
			save: function() {
				this.$req.order.orderAdd('s',this.data,(res)=>{
					if(res.data.status){
						console.log(res.data)
					}else{
						uni.showToast({
							title:res.data.msg,
							icon:'none'
						})
					}
				})
			}
		}
	}
</script>

<style>
</style>
