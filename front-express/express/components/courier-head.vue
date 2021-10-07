<template>
	<view>
		<div class="ui grey three item inverted menu">
			<navigator url="/pages/courier/cou_take" class="item" :class="{'active':active==1}" hover-class="navigator-hover">
				代取单
			</navigator>
			<navigator url="/pages/courier/cou_send" class="item" :class="{'active':active==2}" hover-class="navigator-hover">
				代寄单
			</navigator>
			<navigator url="/pages/courier/me" class="item" :class="{'active':active==3}" hover-class="navigator-hover">
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
