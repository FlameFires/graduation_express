<script>
	export default {
		onLaunch: function() {
			console.log('App Launch')
		},
		onShow: function() {
			console.log('App Show')
		},
		onHide: function() {
			console.log('App Hide')
		}
	}
</script>

<style>
	/*每个页面公共css */
	@import './static/js/semantic/v2.1.7/semantic.css';

	#listContents .ui.segment:hover {
		background-color: rgba(0, 0, 0, 0.05);
	}

	input {
		height: 2.5rem;
	}

	.desc {
		text-align: left;
		width: 80%;
		float: left;
		height: 100%;
	}

	.stat::after {
		content: "";
		clear: both;
		/*清除浮动*/
		display: block;
		/*确保该元素是一个块级元素*/
	}

	body {
		background-color: #DADADA;
	}

	body>.grid {
		height: 100%;
	}

	.column {
		max-width: 450px;
	}

	.viewh {
		height: 720px;
	}

	.column>.segment {
		height: 100%;
	}


	.redColor {
		color: red;
	}

	.okColor {
		color: green;
	}

	.outline {
		color: green;
	}


	/* 设置滚动条的样式 */
	::-webkit-scrollbar {
		width: 6px;
		height: 10px
	}

	/* 滚动槽 */
	::-webkit-scrollbar-track {
		-webkit-box-shadow: inset006pxrgba(0, 0, 0, 0.3);
		border-radius: 10px;
	}

	/* 滚动条滑块 */
	::-webkit-scrollbar-thumb {
		border-radius: 10px;
		background: rgba(0, 0, 0, 0.1);
		-webkit-box-shadow: inset006pxrgba(0, 0, 0, 0.5);
	}
</style>



<style lang="less">
	// 上下滑动
	.scroll-Y {
		height: 300rpx;

		.scroll-view-item {
			height: 300rpx;
			width: 100%;
		}
	}

	// 左右滑动
	.scroll-view_H {
		height: 1200rpx;
		width: 100%;
		// white-space: nowrap; // 滚动必须加的属性

		.scroll-view-item_H {
			height: 300rpx;
			width: 40%;
			margin-right: 20rpx;
			display: inline-block;
			vertical-align: top;
		}
	}
</style>
