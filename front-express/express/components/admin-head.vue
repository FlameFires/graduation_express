<template>
	<view>
		<div class="ui grey three item inverted menu">
			<navigator url="/pages/admin/home" class="item" :class="{'active':active==1}" hover-class="navigator-hover">
首页
			</navigator>
			<navigator url="/pages/admin/msg_list" class="item" :class="{'active':active==2}" hover-class="navigator-hover">
				消息发布
			</navigator>
			<navigator url="/pages/admin/me" class="item" :class="{'active':active==3}" hover-class="navigator-hover">
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
