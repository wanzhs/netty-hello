<template>
    <div class="wrap">
        <div class="hd-box clearfix">
            <el-input v-model="qc.poleName" placeholder="充电桩名称" clearable style="width: 150px;"></el-input>
            <el-input v-model="qc.deviceSubName" placeholder="充电枪名称" clearable style="width: 150px;"></el-input>
            <el-button type="primary" size="mini">查询</el-button>
        </div>
        <el-table
                :data="commentList"
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
                    prop="userRealName">
            </el-table-column>
            <el-table-column
                    label="站点编码"
                    prop="stationCode">
            </el-table-column>
            <el-table-column
                    label="站点名称"
                    prop="stationName">
            </el-table-column>
            <el-table-column
                    label="桩编号"
                    prop="deviceCode">
            </el-table-column>
            <el-table-column
                    label="桩名称"
                    prop="deviceName">
            </el-table-column>
            <el-table-column
                    label="枪编号"
                    prop="deviceSubId">
            </el-table-column>
            <el-table-column
                    label="充电方式">
            </el-table-column>
            <el-table-column
                    label="操作"
                    width="80%">
                <template slot-scope="scope">
                    <el-button type="text" size="mini" class="deleteBtn" @click="showDeleteDialog(scope.row, scope.$index)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <!--删除评论弹窗开始-->
        <el-dialog
                :visible.sync="deleteDialogVisible"
                :close-on-click-modal="!closeOnlyByClick"
                :close-on-press-escape="!closeOnlyByClick"
                width="380px">
            <span slot="title">
                <i class="iconfont el-icon-cdz-iconfontcolor100-copy"></i>
            </span>
            <span style="font-size: 1.2em; margin-left: 2.5em;">是否删除？</span>
            <span slot="footer" class="dialog-footer">
                <el-button type="primary" plain @click.native.prevent="deleteDialogVisible=false">否</el-button>
                <el-button v-if="loading" type="danger" :loading="true"></el-button>
                <el-button v-else type="danger" @click.native.prevent="deleteComment" class="dangerOperate">是</el-button>
            </span>
        </el-dialog>
        <!--删除评论弹窗结束-->
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
                commentList: [], // 所有订单列表
                currentPage: 1,
                pageSize: 10,
                total: 0,
                deleteDialogVisible: false,
                closeOnlyByClick: true,
                willDeleteData: null,
                willDeleteDataIndex: 0,
                loading: false,
            }
        },
        created() {
            this.http.post('/charging/order/comment/list',{
                "index":1,
                "size":10
            }).then((res)=>{
                console.log(res)
                this.commentList = res.data.data.records;
                this.total = res.data.data.total;
            })
        },
        methods: {
            handleSizeChange(pageSize) {
                this.currentPage = 1;
                this.pageSize = pageSize;
                this.http.post('/charging/order/comment/list',{
                    "index":1,
                    "size": pageSize
                }).then((res)=>{
                    this.commentList = res.data.data.records;
                })
            },
            handleCurrentChange(page) {
                this.currentPage = page;
                this.http.post('/charging/order/comment/list',{
                    "index": page,
                    "size":this.pageSize
                }).then((res)=>{
                    this.commentList = res.data.data.records;
                })
            },
            showDeleteDialog(row, index) {
                this.willDeleteData = row;
                this.willDeleteDataIndex = index;
                this.deleteDialogVisible = true;
            },
            deleteComment() {
                this.loading = true;
                setTimeout(()=>{
                    this.commentList.splice(this.willDeleteDataIndex, 1);
                    this.deleteDialogVisible = false;
                    this.msg.success("删除成功！");
                },800);
            }
        }
    }
</script>

<style scoped lang="less">
    .inner-ft-box {
        bottom: 20px;
    }
</style>