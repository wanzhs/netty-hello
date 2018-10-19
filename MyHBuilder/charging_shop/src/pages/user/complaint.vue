<template>
	<div>
		<div class="handle-box clearfix">
				<div class="fl">
					<el-input class="ip-w40" v-model="name" placeholder="乘客姓名"></el-input>
					<el-input class="ip-w40" v-model="id" placeholder="订单编号"></el-input>
					<el-button @click="query" type="primary">查询</el-button>
				</div>
		</div>
		<div class="handle-box">
			<el-row class="handle-box">
			<!--表格部分-->
			<el-table :data="page.list" stripe v-loading="page.loading.status" style="width: 100%">
				<el-table-column type="index"></el-table-column>
				<el-table-column prop="user.name" label="乘客姓名"></el-table-column>
				<el-table-column prop="complaint.id" label="投诉编号"></el-table-column>
				<el-table-column prop="complaint.detail" label="投诉内容"></el-table-column>
				<el-table-column prop="complaint.result" label="投诉结果"></el-table-column>
				<el-table-column prop="complaint.complaintTime" label="投诉时间" :formatter="timeFormatter"></el-table-column>
					<el-table-column label="操作">
						<template slot-scope="scope" >
							<div v-show="!scope.row.complaint.result"> 
								<el-button size="mini" type="primary" @click="updateClick(scope.row)"  :disabled="disabled"  class="div-button1">添加处理结果</el-button>
							</div>
						</template>
					</el-table-column>
			</el-table>
			</el-row>
		</div>
		<div class="handle-box">
			<!--分页部分-->
			<el-pagination background @size-change="handleSizeChange" @current-change="handleCurrentChange" 
				layout="sizes,prev, pager, next" :page-sizes="cont.PAGE_SIZES" :page-size="page.size" :total="page.total" class="fr">
			</el-pagination>
		</div>
		<!--修改 列表信息-->
		<el-dialog :title="dialog.updateClick.title"  :visible.sync="dialog.updateClick.status" v-loading="dialog.updateClick.loading.status" 
			width="30%" @close="resetForm('updateForm')" style="font-size: 16px;">
			<el-form ref="updateForm" :model="updateForm" label-width="80px" :rules="dialog.updateClick.rule">
				<!--<el-form-item label="投诉单号" prop="id">
					<el-input v-model="updateForm.id"></el-input>
				</el-form-item>-->
				<el-form-item label="投诉结果" prop="result">
					<el-input v-model="updateForm.result" type="textarea"  :rows="5" style="width: 85%;min-height: 33px;" placeholder="请输入处理结果"></el-input>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click="dialog.updateClick.status=false">取消</el-button>
				<el-button type="primary" @click="submitUpdate()">更新</el-button>
			</div>
		</el-dialog>
	
	</div>
</template>


<script>
	import user from '@/service/user'
	export default{
		data(){
			return{
			id:'',
			name:'',
			disabled:false,
			page: {
					index: 1,
					size: this.cont.PAGE_SIZES[0],
					total: 0,
					list: [],
					loading: {
						status: false
					}
			},
			updateForm:{
				id:'',
				result:''
			},
			dialog:{
				updateClick:{
					title:'处理投诉',
					status:false,
					loading:{
						title:'更新中',
						status:false
					},
					rule:{
							id: [{
								required: true,
								message: '订单号不能为空'
							}]
							,result: [{
								required: true,
								message: '处理结果不能为空'
							}]
					}
				}
			}
			}
		},
		
		methods:{
			submitUpdate() {
				console.log(this.updateForm);
				user.updateComplaintResult(this.updateForm).then(res=>{
					if(res.data.code==this.cont.HTTP_SUCCESS){
						this.getList();
						this.dialog.updateClick.status=false;
					}
				}).catch(err=>{});

			},
			updateClick(row){
				this.dialog.updateClick.title = "订单编号：" +row.complaint.id;  
						this.updateForm.id=row.complaint.id;
						this.dialog.updateClick.status=true;
				  
			},
			resetForm(name) {
				if(name == 'addForm') {
					this.addForm.superior = '';
				}
				this.$refs[name].resetFields();
			},
			handleCurrentChange(val) {
				this.page.index = val
				this.getList();
			},
			handleSizeChange(val) {
				this.page.size = val
				this.getList();
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
							id:this.id,
							name:this.name
						}	
				}
				this.page.loading.status = true;
				user.getMPage(param).then(res => {
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
		.div-button1{
		width: 100px;
    margin-top: 2px;
    margin-left: 0 !important;
	}
	
</style>