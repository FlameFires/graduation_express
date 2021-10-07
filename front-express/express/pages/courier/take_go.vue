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
									<input type="text" name="username" placeholder="姓名">
								</div>
							</div>
							<div class="inline required field" style="margin-top: 10px">
								<label>昵称</label>
								<div class="ui left icon input">
									<i class="user icon"></i>
									<input type="text" name="username" placeholder="昵称">
								</div>
							</div>

							<div class="inline required field">
								<label>手机号</label>
								<div class="ui left icon input">
									<i class="phone icon"></i>
									<input type="text" name="mobile" placeholder="请输入手机号/校园短号">
								</div>
							</div>
							<div class="inline required field ">
								<label>送达地址</label>
								<div class="ui left icon input ">
									<i class="marker icon"></i>
									<input type="text" name="address" placeholder="送达地址">
								</div>
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
								<label>取件码</label>
								<input type="text" name="address" placeholder="取件码">
							</div>
						</div>
						<div class="ui error message"></div>

						<div class="ui fluid teal submit button" style="margin-bottom: 15px;margin-top: 15px;" @click="save">下单</div>
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

			}
		},
		methods: {
			save: function() {

			}
		}
	}
</script>

<style>
</style>
