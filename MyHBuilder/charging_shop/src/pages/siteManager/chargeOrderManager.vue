<template>
    <div class="wrap">
        <div class="hd-box clearfix">
            <el-input v-model="qc.poleName" placeholder="充电桩名称" style="width: 150px;"></el-input>
            <el-input v-model="qc.deviceSubName" placeholder="充电枪名称" style="width: 150px;"></el-input>
            <span>
                <el-input v-model="qc.chargeStartDt" placeholder="充电开始时间" style="width: 150px;"></el-input>
                <el-input v-model="qc.chargeStopDt" placeholder="充电结束时间" style="width: 150px;"></el-input>
            </span>
            <el-button type="primary" plain size="mini">查询</el-button>
            <el-button type="primary" plain size="mini">正在充电订单</el-button>
            <el-button type="primary" plain size="mini">充电异常订单</el-button>
            <el-button type="primary" plain size="mini">充电完成订单</el-button>
        </div>
        <el-table
            :data="orderList"
            size="mini"
            border
            :header-cell-style="{'background-color': '#f3f3f3',}"
            width="100%"
            height="87%">
            <el-table-column
                label="订单号"
                prop="orderId">
            </el-table-column>
            <el-table-column
                label="用户ID"
                prop="userId">
            </el-table-column>
            <el-table-column
                label="用户名"
                prop="userName">
            </el-table-column>
            <el-table-column
                label="站点编码"
                prop="stationCode">
            </el-table-column>
            <el-table-column
                label="站点名称">
            </el-table-column>
            <el-table-column
                label="桩编号">
            </el-table-column>
            <el-table-column
                label="桩名称">
            </el-table-column>
            <el-table-column
                label="枪编号">
            </el-table-column>
            <el-table-column
                label="充电方式">
            </el-table-column>
            <el-table-column
                label="电价">
            </el-table-column>
            <el-table-column
                label="服务费">
            </el-table-column>
            <el-table-column
                label="价格">
            </el-table-column>
            <el-table-column
                label="充电开始时间"
                width="100px">
            </el-table-column>
            <el-table-column
                label="充电结束时间"
                width="100px">
            </el-table-column>
        </el-table>
        <!--底部分页器开始-->
        <div class="ft-box inner-ft-box">
            <el-pagination
                    background
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    small
                    :current-page="currentPage"
                    :page-sizes="[10, 20, 30, 40]"
                    :page-size="pageSize"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total">
            </el-pagination>
        </div>
        <!--底部分页器结束-->
    </div>
</template>

<script>
    export default {
        data() {
            return {
                qc: {
                    poleName: '',
                    deviceSubName: '',
                    chargeStartDt: '',
                    chargeStopDt: '',
                },
                orderList: [], // 所有订单列表
                currentPage: 1,
                pageSize: 10,
                total: 0,
            }
        },
        created() {
            this.http.post('/charging/order/list',{
                "index":1,
                "size":10,
                "data":{
                    "orderState": ''
                }
            }).then((res)=>{
                console.log(res)
                this.orderList = res.data.data;
            })
        },
        methods: {
            handleSizeChange(pageSize) {
                this.pageSize = pageSize;
            },
            handleCurrentChange(page) {
                this.currentPage = page;
            }
        }
    }
</script>

<style scoped lang="less">
    .inner-ft-box {
        bottom: 20px;
    }
</style>