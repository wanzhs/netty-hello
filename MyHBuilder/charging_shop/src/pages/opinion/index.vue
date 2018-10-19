<template>
    <div class="wrap">
        <div class="hd-box clearfix">
            <el-input v-model="qc.stationName" placeholder="充电站名称" style="width: 150px;" clearable></el-input>
            <el-input v-model="qc.stationCode" placeholder="充电站编码" style="width: 150px;" clearable></el-input>
            <el-button type="primary" size="mini" @click="query">查询</el-button>
        </div>
        <el-table
            :data="opinionList"
            size="mini"
            border
            :header-cell-style="{'background-color': '#f3f3f3',}"
            width="100%"
            height="90.5%">
            <el-table-column
                label="充电站编号"
                prop="stationCode">
            </el-table-column>
            <el-table-column
                label="充电站名称"
                prop="stationName">
            </el-table-column>
            <el-table-column
                    label="充电桩编码"
                    prop="deviceCode">
            </el-table-column>
            <el-table-column
                    label="充电桩名称"
                    prop="deviceName">
            </el-table-column>
            <el-table-column
                    label="充电方式"
                    prop="cgtypeName">
            </el-table-column>
            <el-table-column
                    label="规格"
                    prop="devicePower">
                <template slot-scope="scope">
                    {{ scope.row.devicePower | formatDevicePower }}
                </template>
            </el-table-column>
            <el-table-column
                label="序列号"
                prop="">
            </el-table-column>
            <el-table-column
                label="详情"
                prop="suggestContent">
            </el-table-column>
            <el-table-column
                label="附件"
                prop="suggestPath">
            </el-table-column>
        </el-table>
        <!--底部分页器开始-->
        <div class="ft-box">
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
                opinionList: [],
                currentPage: 1,
                total: 0,
                pageSize: 10,
                qc: {
                    stationName: '',
                    stationCode: ''
                },
            }
        },
        filters: {
            formatDevicePower(value) {
                if (!!value) {
                    return value + 'kW';
                } else {
                    return '未知规格';
                }
            }
        },
        created() {
            this.http.post('/charging/suggest/list',{ "index":1,
                "size":this.pageSize,
                "data":{
                    "stationCode":"",
                    "stationName":""
                }}).then((res)=>{
                this.opinionList = res.data.data.records;
                this.total = res.data.data.total;
            });
        },
        methods: {
            // 条件查询
            query() {
                this.http.post('/charging/suggest/list',{ "index":1,
                    "size":this.pageSize,
                    "data":this.qc}).then((res)=>{
                    this.opinionList = res.data.data.records;
                    this.total = res.data.data.total;
                });
            },
            handleSizeChange(pageSize) {
                this.pageSize = pageSize;
                this.currentPage = 1;
                this.http.post('/charging/suggest/list',{ "index":1,
                    "size":pageSize,
                    "data":{
                        "stationCode":"",
                        "stationName":""
                    }}).then((res)=>{
                    this.opinionList = res.data.data.records;
                });
            },
            handleCurrentChange(page) {
                this.currentPage = page;
                this.http.post('/charging/suggest/list',{ "index":page,
                    "size":this.pageSize,
                    "data":{
                        "stationCode":"",
                        "stationName":""
                    }}).then((res)=>{
                    this.opinionList = res.data.data.records;
                });
            },
        }
    }
</script>

<style scoped lang="less">

</style>