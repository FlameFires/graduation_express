<template>
	<view>
		<div class="ui center aligned grid">
			<div class="column viewh">
				<div class="ui segment">
					<form class="ui equal width form">
						<h3>代取快递</h3>
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
							<div class="inline required field ">
								<label>送达地址</label>
								<div class="ui left icon input ">
									<i class="marker icon"></i>
									<input type="text" name="address" placeholder="送达地址" v-model="data.address">
								</div>
							</div>
							<div class="inline required field">
								<label>快递点</label>
								<select v-model="data.dpointid">
									<option value="0">请选择</option>
									<option v-for="item in dpoint" :key="item.id" :value="item.id">{{item.name}}
									</option>
								</select>
								<select style="margin-left: 15px;" v-model="data.dtypeid">
									<option value="0">请选择</option>
									<option v-for="item in dtype" :key="item.id" :value="item.id">{{item.name}}</option>
								</select>
							</div>
							<div class="inline required field">
								<label>取件码</label>
								<input type="text" name="address" placeholder="取件码" v-model="data.tcode">
							</div>
						</div>
						<div class="ui" :class="msgClass">{{msg}}</div>

						<div class="ui fluid teal submit button " style="margin-bottom: 15px;margin-top: 15px;"
							:class="{disabled:!isEnable}" @click="save">下单</div>
						<navigator url="cus_take" class="ui fluid  submit button">返回</navigator>
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
				dpoint: [],
				dtype: [],
				gtype: [],
				data: {
					dpointid: 0,
					dtypeid: 0
				},
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

				console.log(this.data);

				let req = this.$req;
				let data = this.data;
				uni.showModal({
					title: '支付',
					content: '费用： 2 元',
					success: function(res) {
						if (res.confirm) {
							req.order.addOrder('t', data, (res) => {
								if (res.data.status) {
									this.isEnable = false;
									uni.showToast({
										icon:'none',
										title:'下单成功',
										success() {
											setTimeout(()=>{
												uni.redirectTo({
													url:'cus_take'
												})
											},2000)
										}
									})
								} else {
									this.msgClass = "red message"
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
					this.dpoint = data.dpoints;
					this.dtype = data.dtypes;
					this.msgClass = ""
					this.msg = ""
				} else {
					this.msgClass = "red message"
					this.msg = res.data.msg
				}
			});

			let user = this.$utils.getUser();
			if (user) {
				this.data.name = user.name;
				this.data.phone = user.phone;
				this.data.address = user.address;
			}
		}
	}
</script>

<style>
</style>
