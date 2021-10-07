<template>
	<view>
		<div class="ui grey four item inverted menu">
			<navigator url="/pages/customer/community" class="item" :class="{'active':active==1}" hover-class="navigator-hover">
				首页
			</navigator>
			<navigator url="/pages/customer/cus_take" class="item" :class="{'active':active==2}" hover-class="navigator-hover">
				代取单
			</navigator>
			<navigator url="/pages/customer/cus_send" class="item" :class="{'active':active==3}" hover-class="navigator-hover">
				代寄单
			</navigator>
			<navigator url="/pages/customer/me" class="item" :class="{'active':active==4}" hover-class="navigator-hover">
				我的
			</navigator>
		</div>
	</view>
</template>

<script>
	export default {
		data() {
			return {

			}
		},
		props: {
			active: {
				type: Number,
				default: 1,
				required: true,
				validator: function(value) {
					return value > 0
				}
			}
		}
	}
</script>

<style>

</style>
