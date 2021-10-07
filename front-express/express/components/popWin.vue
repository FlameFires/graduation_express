<template>
	<view>
		<div class="tipWin" :class="{show:isShow,hidden:!isShow}">
			<div class="tipWinTitle">
				{{data.title}}
				<br>
				<span class="tipWinDate">{{format(data.createDate)}}</span>
			</div>

			<div class="closeIcon" @click='close'>x</div>

			<div class="tipWinContent">
				&nbsp;&nbsp; {{data.content}}
			</div>
		</div>
	</view>
</template>

<script>
	export default {
		name: "popWin",
		data() {
			return {

			};
		},
		props: {
			isShow: {
				default: false
			},
			data: {
				required: true
			}
		},
		methods: {
			close() {
				this.$emit('close');
			},
			format(val) {
				return this.$utils.dateFormat("YYYY年mm月dd  HH:MM", new Date(val));
			},
		}
	}
</script>

<style>
	.tipWin {
		width: 300px;
		height: auto;
		max-height: 300px;
		background-color: rgba(255, 255, 255, 0.99);
		z-index: 100;
		position: absolute;
		left: 0;
		right: 0;
		top: 0;
		bottom: 0;
		margin: auto;
		padding: 10px;
		border-radius: 10px;
		padding-top: 20px;
		animation-fill-mode: forwards;
		box-shadow: 3px 3px 10px rgba(0,0,0,.2);
	}

	.closeIcon {
		position: absolute;
		right: 20px;
		top: 10px;
		width: 25px;
		height: 25px;
		border-radius:30px;
		font-size: 20px;
	}
	
	.closeIcon:hover{
		background-color: red;
		color: white;
	}

	.show {
		animation: show .5s ease-in 1;
		visibility: visible;
	}

	.hidden {
		animation: hidden 2s 1;
		visibility: hidden;
	}

	@keyframes show {
		from {
			transform: scale(0, 0);
		}
	}

	@keyframes hidden {
		to {
			transform: scale(0, 0);
		}
	}

	.tipWinDate {
		font-weight: normal;
		font-size: 14px;
		color: #ababab;
	}

	.tipWinTitle {
		font-weight: bold;
		font-size: 22px;
	}

	.tipWinContent {
		width: 80%;
		margin: 0 auto;
		text-align: left;
		margin-top: 10px;
		line-height: 24px;
	}
</style>
