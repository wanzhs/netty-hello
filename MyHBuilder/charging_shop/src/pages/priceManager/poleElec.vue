<template>
    <div class="wrap">
        <div class="hd-box clearfix">
            <el-input v-model="queryCondition.id" placeholder="充电桩编码" clearable style="width: 15%;"></el-input>
            <el-button type="primary" size="mini">查询</el-button>
        </div>
        <el-table
            :data="poleData"
            size="mini"
            border
            :header-cell-style="{'background-color': '#f3f3f3',}"
            width="100%"
            height="90.5%">
            <el-table-column
                label="充电桩编码"
                prop="">
            </el-table-column>
            <el-table-column
                label="充电桩名称"
                prop="">
            </el-table-column>
            <el-table-column
                    label="充电方式"
                    prop="">
            </el-table-column>
            <el-table-column
                    label="规格"
                    prop="">
            </el-table-column>
            <el-table-column
                    label="序列号"
                    prop="">
            </el-table-column>
            <el-table-column
                    label="电量"
                    prop="">
            </el-table-column>
            <el-table-column
                    label="费用"
                    prop="">
            </el-table-column>
        </el-table>
        <!--底部分页器开始-->
        <div class="ft-box" style="text-align: right; margin-top: 5px;">
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
                currentPage: 1,
                pageSize: 10,
                total: 0,
                poleData: [],
                queryCondition: {
                    id: '',
                }
            }
        },
        created() {

        },
        methods: {
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

</style>