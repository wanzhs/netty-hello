<template>
	<div>
		<div class="handle-box clearfix">
				<div class="fl">
					<el-input class="ip-w15" v-model="userName" placeholder="乘客姓名"></el-input>
					<el-input class="ip-w15" v-model="userPhone" placeholder="乘客电话"></el-input>
					<el-input class="ip-w15" v-model="driverName" placeholder="驾驶员电话"></el-input>
					<el-input class="ip-w15" v-model="driverPhone" placeholder="驾驶员电话"></el-input>
					<el-input class="ip-w15" v-model="vehicleNo" placeholder="车牌号"></el-input>
					<el-button @click="query" type="primary">查询</el-button>
				</div>
		</div>
		<div class="handle-box">
			<el-row class="handle-box">
			<!--表格部分-->
			<el-table :data="page.list" stripe v-loading="page.loading.status" style="width: 100%">
				<el-table-column type="index"></el-table-column>
				<el-table-column prop="id" label="订单编号"></el-table-column>
				<el-table-column prop="rate.detail" label="评价内容"></el-table-column>
				<el-table-column prop="rate.serviceScore" label="服务分数"></el-table-column>
				<el-table-column prop="user.name" label="乘客姓名"></el-table-column>
				<el-table-column prop="user.phone" label="乘客电话"></el-table-column>
				<el-table-column prop="user.gender" label="乘客性别" :formatter="genderFormatter"></el-table-column>
				<el-table-column prop="driver.name" label="驾驶员姓名"></el-table-column>
				<el-table-column prop="driver.phone" label="驾驶员电话"></el-table-column>
				<el-table-column prop="driver.gender" label="驾驶员性别" :formatter="genderFormatter"></el-table-column>
				<el-table-column prop="car.vehicleNo" label="车牌号"></el-table-column>
				<el-table-column prop="rate.evaluateTime" label="评价时间" :formatter="timeFormatter"></el-table-column>
				<el-table-column prop="rate.createTime" label="创建时间" :formatter="timeFormatter"></el-table-column>
			</el-table>
			</el-row>
		</div>
		<div class="handle-box">
			<!--分页部分-->
			<el-pagination background @size-change="handleSizeChange" @current-change="handleCurrentChange" 
				layout="sizes,prev, pager, next" :page-sizes="cont.PAGE_SIZES" :page-size="page.size" :total="page.total" class="fr">
			</el-pagination>
		</div>
	</div>
</template>


<script>
	import user from '@/service/user'
	export default{
		data(){
			return{
				
			userName: "",
			userPhone: "",
			driverName: "",
			driverPhone: "",
			vehicleNo: "",

			page: {
					index: 1,
					size: this.cont.PAGE_SIZES[0],
					total: 0,
					list: [],
					loading: {
						status: false
					}
				}
			}
		},
		
		methods:{
			handleCurrentChange(val) {
				this.page.index = val
				this.getList();
			},
			handleSizeChange(val) {
				this.page.size = val
				this.getList();
			},
			genderFormatter(row, column, cellValue, index){
					if(cellValue=="1"){
						return "男";
					}else{
						return "女";
					}
			},
			timeFormatter(row, column, cellValue, index){
					if(cellValue !=null){
						let strs=cellValue.trim().split(' ');
						if(strs.length>=1){
							return strs[0];
						}else{
						return cellValue;	
						}
					}
			},
			query(){
				this.getList();
			},
			getList() {
				let param = {
						index: this.page.index,
						size: this.page.size,
						object:{
							userName:this.userName,
							userPhone:this.userPhone,
							driverName:this.driverName,
							driverPhone:this.driverPhone,
							vehicleNo:this.vehicleNo
						}	
				}
				this.page.loading.status = true;
				user.getUserRateMPage(param).then(res => {
					if(res.data.code == this.cont.HTTP_SUCCESS){
						this.page.list = res.data.data.list;
						console.log(this.page.list)
						this.page.total = res.data.data.total;
					}
				}).catch(err => {}).then(() => {
					this.page.loading.status = false;
				});
			}
		},
		mounted(){
			this.getList();
		}
	}
</script>


<style>
	.el-header, .el-footer {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
	}
	.el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    line-height: 160px;
	}
	.ip-w15{
		width: 15%;
	}
</style>