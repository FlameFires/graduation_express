<template>
	<view>
		<view class="uni-list" style="margin-top: 15px;font-size: 14px !important;">
			<view class="uni-list-cell">
				<view class="uni-list-cell-left">
					当前选择
				</view>
				<view class="uni-list-cell-db" style="">
					<picker mode="date" :value="date" :start="startDate" :end="endDate" @change="bindDateChange">
						<view class="uni-input" style="height: auto;">{{date}}</view>
					</picker>
				</view>
			</view>
		</view>
	</view>
</template>
<script>
	export default {
		data() {
			const currentDate = this.getDate({
				format: true
			})
			return {
				index: 0,
				date: currentDate,
				time: '12:01'
			}
		},
		computed: {
			startDate() {
				return this.getDate('start');
			},
			endDate() {
				return this.getDate('end');
			}
		},
		methods: {
			bindPickerChange: function(e) {
				console.log('picker发送选择改变，携带值为', e.target.value)
				this.index = e.target.value
			},
			bindDateChange: function(e) {
				this.date = e.target.value
			},
			bindTimeChange: function(e) {
				this.time = e.target.value
			},
			getDate(type) {
				const date = new Date();
				let year = date.getFullYear();
				let month = date.getMonth() + 1;
				let day = date.getDate();

				if (type === 'start') {
					year = year - 60;
				} else if (type === 'end') {
					year = year + 2;
				}
				month = month > 9 ? month : '0' + month;;
				day = day > 9 ? day : '0' + day;
				let val = `${year}-${month}-${day}`;
				return val;
			}
		},
		watch: {
			date(val) {
				this.$emit('dateVal', val);
			}
		}
	}
</script>
<style scoped>
	@import '@/common/css/uni.css';
	/* @import '@/common/css/uni-nvue.css'; */

	.uni-input {
		height: 39px;
		padding: 12px 7px;
		line-height: 25rpx;
		font-size: 14px;
		background: #FFF;
		-webkit-box-flex: 1;
		-webkit-flex: 1;
		flex: 1;
	}

	.uni-list-cell-left {
		font-size: 14px;
		line-height: 19px;
	}
	.uni-list-cell{
		height: 36px;
	}
</style>
