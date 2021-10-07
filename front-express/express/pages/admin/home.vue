<template>
	<view>
		<div class="ui center aligned grid">
			<div class="column viewh">
				<div class="ui segment">
					<admin-head :active=1></admin-head>

					<h3 style="padding: 10px 0; border-top: 1px solid rgba(0,0,0,0.2);border-bottom: 1px solid rgba(0,0,0,0.2);">查看订单</h3>
					<div class="ui segments">
						<navigator url="./adm_take"><div class="ui segment selection">代取单</div></navigator>
						<navigator url="./adm_send"><div class="ui segment selection">代寄单</div></navigator>
					</div>
					
					<!-- <h3 style="padding: 10px 0; border-top: 1px solid rgba(0,0,0,0.2);border-bottom: 1px solid rgba(0,0,0,0.2);">查看评论</h3>
					<div class="ui segments">
						<navigator url="./adm_take"><div class="ui segment selection">评论列表</div></navigator>
					</div> -->
					

				</div>
			</div>
		</div>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				date: ''
			}
		},
		methods: {
			getDateVal(val) {
				this.date = val;
			},
			check: function(id) {
				console.log('查看')
				uni.navigateTo({
					url: '/pages/courier/send_detail?id=' + id
				});
			},
			toFirst: function() {
				console.log('跳转到首页')
			},
			toLast: function() {
				console.log('跳转到尾页')
			},
			toPage: function(e) {
				let v = e.target.dateset;
				console.log(v)
			}
		}
	}
</script>


<style>
	.selection:hover {
		background-color: rgba(0, 0, 0, 0.1);
	}
</style>
