<template>
    <div class="wrap">
        <!--充值订单-->
        <div class="hd-box clearfix">
            <el-input v-model="qc.userName" placeholder="用户名称" clearable style="width: 150px;"></el-input>
            <el-input v-model="qc.userContact" placeholder="联系电话" clearable style="width: 150px;"></el-input>
            <el-button type="primary" size="mini">查询</el-button>
        </div>
        <el-table
            :data="rechargeOrderList"
            border
            :header-cell-style="{'background-color': '#f3f3f3',}"
            width="100%"
            height="87%"
            size="mini">
            <el-table-column
                label="充值编号">
            </el-table-column>
            <el-table-column
                label="充值账号/卡号">
            </el-table-column>
            <el-table-column
                label="用户名称">
            </el-table-column>
            <el-table-column
                label="联系电话">
            </el-table-column>
            <el-table-column
                label="充值时间">
            </el-table-column>
            <el-table-column
                label="充值金额（元）">
            </el-table-column>
            <el-table-column
                label="充值方式">
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
                rechargeOrderList: [], // 充值订单列表
                qc: {
                    userName: '',
                    userContact: ''
                },
                currentPage: 1,
                pageSize: 10,
                total: 0,
            }
        },
        created() {

        },
        methods: {
            // 条件查询
            query() {

            },
            handleSizeChange(pageSize) {
                this.pageSize = pageSize;
                this.currentPage = 1;
            },
            handleCurrentChange(page) {
                this.currentPage = page;
            },
        }
    }
</script>

<style scoped lang="less">
    .inner-ft-box {
        bottom: 20px;
    }
</style>