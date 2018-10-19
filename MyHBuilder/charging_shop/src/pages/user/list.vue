<template>
	<div>
		<div class="handle-box clearfix">
				<div class="fl">
					<el-input class="ip-w40" v-model="qName" placeholder="姓名"></el-input>
					<el-input class="ip-w40" v-model="qPhone" placeholder="电话号码"></el-input>
					<el-button @click="query" type="primary">查询</el-button>
				</div>
		</div>
		<div class="handle-box">
			<el-row class="handle-box">
			<!--表格部分-->
			<el-table :data="page.list" stripe v-loading="page.loading.status" style="width: 100%">
				<el-table-column type="index"></el-table-column>
				<el-table-column prop="name" label="姓名"></el-table-column>
				<el-table-column prop="phone" label="电话号码"></el-table-column>
				<el-table-column prop="gender" label="性别" :formatter="genderFomatter"></el-table-column>
				<el-table-column prop="createTime" label="注册时间"></el-table-column>
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
				
			qName: "",
			qPhone: "",
			name: "",
			phone: "",
			gender: "",
			creatTime: "",
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
			genderFomatter(row, column, cellValue, index){
					if(cellValue=="1"){
						return "男";
					}else{
						return "女";
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
							name:this.qName,
							phone:this.qPhone
						}	
				}
				this.page.loading.status = true;
				user.getUserListByPage(param).then(res => {
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
	.ip-w40{
		width: 40%;
	}
</style>