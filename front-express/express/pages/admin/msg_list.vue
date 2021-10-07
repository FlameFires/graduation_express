<template>
	<view>
		<div class="ui center aligned grid">
			<div class="column viewh">
				<div class="ui segment">
					<admin-head v-bind:active=2></admin-head>
					
					<div class="ui segments">
						<navigator url="./notice_go" class="ui segment selection">发布公告</navigator>
						<navigator url="./notice_list" class="ui segment selection">查看公告</navigator>
						<!-- <div class="ui segment selection">发布活动</div> -->
						<!-- <div class="ui segment selection">优惠卷</div> -->
					</div>

				</div>
			</div>
		</div>

	</view>
</template>

<script>
</script>

<style>
	.selection:hover{
		background-color: rgba(0,0,0,0.1);
	}
</style>
