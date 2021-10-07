<template>
	<view>
		<div class="ui center aligned grid">
			<div class="column viewh">
				<div class="ui segment">
					<form class="ui equal width form" style="margin-top: 50px">
						<h3>代寄快递</h3>
						<div class="">
							<div class="inline required field" style="margin-top: 10px">
								<label>收件人姓名</label>
								<div class="ui left icon input">
									<i class="user icon"></i>
									<input type="text" name="username" placeholder="姓名" v-model="data.sname">
								</div>
							</div>
							<div class="inline required field">
								<label>收件人手机号</label>
								<div class="ui left icon input">
									<i class="phone icon"></i>
									<input type="text" name="mobile" placeholder="手机号" v-model="data.sphone">
								</div>
							</div>
							<div class="inline required field" style="margin-top: 10px">
								<label>收件人姓名</label>
								<div class="ui left icon input">
									<i class="user icon"></i>
									<input type="text" name="username" placeholder="姓名" v-model="data.rname">
								</div>
							</div>
							<div class="inline required field">
								<label>收件人手机号</label>
								<div class="ui left icon input">
									<i class="phone icon"></i>
									<input type="text" name="mobile" placeholder="手机号" v-model="data.rphone
									">
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
								<label>寄送地址</label>
								<div class="ui left icon input">
									<i class="marker icon"></i>
									<input type="text" name="address" placeholder="放送地址" v-model="data.saddress">
								</div>
							</div>
							<div class="inline required field">
								<label>取货时间</label>
								<select v-model="data.tdate">
									<option value="">请选择</option>
									<!-- <option v-for="(item, i) in timelist" :key="item.id" :value="item.id">
										{{item.name}}
									</option> -->
									<option value="12:00~14:00">12:00~14:00</option>
									<option value="18:00~19:30">18:00~19:30</option>
								</select>
							</div>
							<div class="inline required field">
								<label>快递点</label>
								<select v-model="data.dpointid">
									<option value="">请选择</option>
									<option v-for="item in dpoint" :key="item.id" :value="item.id">{{item.name}}
									</option>
								</select>
								<select style="margin-left: 15px;" v-model="data.dtypeid">
									<option value="">请选择</option>
									<option v-for="item in dtype" :key="item.id" :value="item.id">{{item.name}}</option>
									<!-- <option value="1">中通</option>
									<option value="2">韵达</option>
									<option value="2">申通</option>
									<option value="2">顺丰</option>
									<option value="2">圆通</option> -->
								</select>
							</div>
							<!-- <div class="inline required field">
								<label>物品类型</label>
								<select v-model="data.gid">
									<option value="">请选择</option>
									 <option v-for="item in dtype" :key="item.id" :value="item.id">{{item.name}}</option>
									<option value="1">食用品</option>
									<option value="1">化妆品</option>
									<option value="1">衣物</option>
									<option value="1">电子产品</option>
								</select>
							</div> -->
						</div>

						<div class="ui" :class="msgClass">{{msg}}</div>

						<div class="ui fluid teal submit button" style="margin-bottom: 10px;margin-top:10px;"
							:class="{disabled:!isEnable}" @click="save">下单</div>
						<navigator url="cus_send" class="ui fluid  submit button">返回</navigator>
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
					sname: '',
					sphone: '',
					rname: '',
					rphone: '',
					taddress: '',
					saddress: '',
					tdate: '',
					dpointid: '',
					dtypeid: ''
				},
				timelist: [],
				dpoint: [],
				dtype: [],
				gtype: [],
				msg: '',
				msgClass: '',
				isEnable:true
			}
		},
		methods: {
			save: function() {
				for (let item in this.data) {
					let d = this.data[item];
					if (typeof(d) === 'string') {
						if (d.length < 1) {
							this.msgClass = "red message";
							this.msg = "请填写信息";
							return;
						}
					}
					if (typeof(d) === 'number') {
						if (d < 1) {
							this.msgClass = "red message";
							this.msg = "请填写信息";
							return;
						}
					}
				}
				
				let req = this.$req;
				let data = this.data;
				uni.showModal({
					title: '支付',
					content: '费用： 2 元',
					success: function(res) {
						if (res.confirm) {
							req.order.addOrder('s', data, (res) => {
								if (res.data.status) {
									this.isEnable = false;
									console.log(this.isEnable)
									uni.showToast({
										icon: "none",
										title: '下单成功',
										success() {
											setTimeout(() => {
												uni.redirectTo({
													url:'cus_send'
												})
											}, 1000)
										}
									})
								} else {
									this.msgClass = "green message"
									this.msg = res.data.msg
								}
							})
						}
					}
				})
			}
		},
		onLoad(option) {
			this.$req.order.getTagList((res) => {
				let data = res.data.data;
				if (res.data.status) {
					this.timelist = data.times;
					this.dpoint = data.dpoints;
					this.dtype = data.dtypes;
					this.gtype = data.gtypes;
					this.msgClass = ""
					this.msg = ""
				} else {
					this.msgClass = "red message"
					this.msg = res.data.msg
				}
			});
			
			let user = this.$utils.getUser();
			if(user){
				this.data.sname = user.name;
				this.data.sphone = user.phone;
				this.data.taddress = user.address;
			}
		}
	}
</script>

<style>
</style>
