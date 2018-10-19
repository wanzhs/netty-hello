<template>
    <div class="wrap">
        <div class="hd-box clearfix">
            <el-input v-model="queryCondition.stationId" placeholder="充电站ID" style="width: 150px; text-align: left;"></el-input>
            <el-input v-model="queryCondition.stationCode" placeholder="充电站编码" style="width: 150px; text-align: left;"></el-input>
            <el-input v-model="queryCondition.stationName" placeholder="充电站名称" style="width: 150px; text-align: left;"></el-input>
            <el-button type="primary" plain size="mini">查询</el-button>
            <!--<el-button type="primary" size="mini" style="float: right;" @click.native.prevent="showAddStationDialog">添加</el-button>-->
        </div>
        <el-table :data="siteList"
            size="mini"
            border
            :header-cell-style="{'background-color': '#f3f3f3',}"
            width="100%"
            height="90.5%">
            <el-table-column
                label="充电站ID"
                prop="stationId">
            </el-table-column>
            <el-table-column
                label="充电站编码"
                prop="stationCode">
            </el-table-column>
            <el-table-column
                label="充电站类型"
                prop="stationType">
            </el-table-column>
            <el-table-column
                label="充电站运营类型"
                prop="sttpeName">
            </el-table-column>
            <el-table-column
                label="充电站名称"
                prop="stationName">
            </el-table-column>
            <el-table-column
                label="桩数"
                prop="deviceNums">
            </el-table-column>
            <el-table-column
                label="经度"
                prop="lng">
            </el-table-column>
            <el-table-column
                label="纬度"
                prop="lat">
            </el-table-column>
            <!--<el-table-column-->
                    <!--label="商户编码"-->
                    <!--prop="shopCode">-->
            <!--</el-table-column>-->
            <!--<el-table-column-->
                    <!--label="商户名称"-->
                    <!--prop="shopName">-->
            <!--</el-table-column>-->
            <el-table-column
                label="插入时间">
                <template slot-scope="scope">
                    {{ scope.row.insertDt | formatInsertDt }}
                </template>
            </el-table-column>
            <el-table-column
                label="操作">
                <template slot-scope="scope">
                    <el-dropdown split-button type="info" size="mini" trigger="click" @command="handleCommand" @visible-change="isVisibleChange(scope.row,scope.$index)" @click="showSiteDetailDialog(scope.row,scope.row.stationId)">
                        详情
                        <el-dropdown-menu slot="dropdown">
                            <!--<el-dropdown-item command="update">修改</el-dropdown-item>-->
                            <el-dropdown-item command="delete">删除</el-dropdown-item>
                        </el-dropdown-menu>
                    </el-dropdown>
                </template>
            </el-table-column>
        </el-table>

        <!--充电站修改开始-->
        <el-dialog
            title="修改"
            :visible.sync="updateStationDialogVisible"
            :close-on-press-escape="!closeOnlyByClick"
            :close-on-click-modal="!closeOnlyByClick"
            width="35%">
            <el-form :model="willChangeData" label-width="120px" size="mini">
                <el-form-item
                    label="充电站类型">
                    <el-input v-model="willChangeData.stationType" style="width: 90%;"></el-input>
                </el-form-item>
                <el-form-item
                    label="充电站运营类型">
                    <el-input v-model="willChangeData.sttpeName" style="width: 90%;"></el-input>
                </el-form-item>
                <el-form-item
                    label="充电站名称">
                    <el-input v-model="willChangeData.stationName" style="width: 90%;"></el-input>
                </el-form-item>
                <el-form-item
                    label="桩数">
                    <el-input v-model="willChangeData.deviceNums" style="width: 90%;"></el-input>
                </el-form-item>
                <el-form-item
                    label="经度">
                    <el-input v-model="willChangeData.lng" style="width: 90%;"></el-input>
                </el-form-item>
                <el-form-item
                    label="纬度">
                    <el-input v-model="willChangeData.lat" style="width: 90%;"></el-input>
                </el-form-item>
                <el-form-item
                    label="商户名称">
                    <el-input v-model="willChangeData.shopName"  style="width: 90%;"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button type="primary" @click.native.prevent="updateStationDialogVisible=false">取消</el-button>
                <el-button type="primary" plain @click.native.prevent="updateSite">确认</el-button>
            </span>
        </el-dialog>
        <!--充电站修改结束-->

        <!--充电站删除对话框开始-->
        <el-dialog
            :visible.sync="deleteSiteDialog"
            :close-on-click-modal="!closeOnlyByClick"
            :close-on-press-escape="!closeOnlyByClick"
            width="380px">
            <span slot="title">
                <i class="iconfont el-icon-cdz-iconfontcolor100-copy"></i>
            </span>
            <span style="font-size: 1.2em; margin-left: 2.5em;">是否删除？</span>
            <span slot="footer" class="dialog-footer">
                <el-button type="primary" plain @click.native.prevent="deleteSiteDialog=false">否</el-button>
                <el-button v-if="loading" type="danger" :loading="true"></el-button>
                <el-button v-else type="danger" @click.native.prevent="deleteSite" class="dangerOperate">是</el-button>
            </span>
        </el-dialog>
        <!--充电站删除对话框结束-->

        <!--充电桩详情开始-->
        <el-dialog
            title="详情"
            :visible.sync="poleDetailVisible"
            :close-on-click-modal="!closeOnlyByClick"
            :close-on-press-escape="!closeOnlyByClick"
            width="1230px">
        </el-dialog>
        <!--充电桩详情结束-->
        <!--底部分页器开始-->
        <div class="ft-box" style="text-align: right;">
            <el-pagination
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
    // 引用充电桩组件
    import pole from '@/components/common/pole'
    export default {
        data() {
            return{
                addChargeStationDialog: false,
                addPoleDialogVisible: false,
                poleDetailVisible: false,
                deleteSiteDialog: false,
                closeOnlyByClick: true,
                currentPage: 1,
                total: 0,
                pageSize: 10,
                updateStationDialogVisible: false,
                willDeleteDataIndex: 0, // 要删除数据的索引
                willDeleteData: null, // 要删除的数据
                siteList: [],
                chargeTypeList: [], //充电站类型列表
                operatorsList: [], //运营商列表
                shopList: [], //商户名称
                poleList: [], // 充电桩列表
                willChangeDataIndex: 0, //将要操作的数据索引
                willChangeData: {}, //将要操作的数据
                siteDetailTitle: '', //充电站详情弹窗标题
                detailDialogVisible: false,
                detailSite: null, // 查看详情的充电站
                queryCondition: {
                    stationId: '',
                    stationCode: '',
                    stationName: '',
                },
                addChargeStation: {// 添加充电站对象
                    stationType: '',
                    sttpeName: '',
                    sttpeId: '',
                    stationCode: '',
                    station_opendt: '',
                    station_closedt: '',
                    stationName: '',
                    shopId: '',
                },
                poleForm: {

                },
                loading: false,
            }
        },
        filters: {
            formatInsertDt(value) {
                if(!!value) {
                    return Array.prototype.slice.call(value,0,10).join("");
                } else {
                    return '时间未知';
                }
            }
        },
        components: {
            pole
        },
        created() {
            this.http.post('/charging/station/list',{"index":1,"size":10, "data":{
                    "stationName":"",
                    "sttpeName":"",
                    "shopName":""
                }}).then((res)=>{
                this.siteList = res.data.data.records
                //console.log(res)
                this.total = res.data.data.total;
            });
            //this.addPole();
        },
        methods: {
            isVisibleChange(data,index) {
                this.willChangeData = data;
                this.willChangeDataIndex = index;
            },
            handleCommand(command) {
                switch (command) {
                    case 'update':
                        this.showUpdateStationDialog();
                        break;
                    case 'delete':
                        this.showDeleteDialog();
                        break;
                }
            },
            // 添加充电桩
            addPole() {
                this.http.post('/charging/device/insert',{

                    "stationId":5,
                    "deviceName":"未来方舟#12",
                    "deviceCode":"#12",
                    "cgtypeId":1,
                    "devicePower":720,
                    "deviceSubnum":4

                }).then((res)=>{
                    console.log(res);
                })
            },
            showDeleteDialog(index, row) {
                this.deleteSiteDialog = true;
                this.willDeleteDataIndex = index;
                this.willDeleteData = row;
            },
            showAddPoleDialog() {
                this.addPoleDialogVisible = true;
            },
            showAddStationDialog() {
                this.addChargeStationDialog = true;
            },
            showUpdateStationDialog() {
                this.updateStationDialogVisible = true;
            },
            // 充电站详情
            showSiteDetailDialog(row,stationId) {
                this.$router.push({path: '/admin/siteManager/siteDetail'});
                this.http.post('/charging/device/list',{"stationId":stationId}).then((res)=>{
                    this.$store.store.commit('updateSiteData_mutations',{"siteData": row,"poleData":res.data.data});
                });
            },
            deleteSite() {
                this.loading = true;
                this.http.post('/charging/station/delete',{"stationId":this.willChangeData.stationId}).then((res)=>{
                    if (res.data.code === 0) {
                        setTimeout(()=>{
                            this.msg.success('删除成功！');
                            this.deleteSiteDialog = false;
                            this.loading = false;
                            this.siteList.splice(this.willChangeDataIndex, 1);
                            this.total--;
                        },800);
                    } else {
                        this.msg.fail(res.data.message);
                        this.deleteSiteDialog = false;
                        this.loading = false;
                    }
                });
            },
            addSite() {
                this.http.post('/charging/station/add',this.addChargeStation).then((res)=>{
                    console.log(res);
                })
            },
            updateSite() {
                this.http.post('/charging/station/update',this.willChangeData).then((res)=>{
                    console.log(res);
                    this.updateStationDialogVisible = false;
                });
            },
            remoteGetShopMethod(value) {
                this.http.post('/charging/shop/get/id',{"shopName":value}).then((res)=>{
                    console.log(res);
                })
            },
            remoteGetStationTypeMethod(value) {
                this.http.post('/sttpe/list').then((res)=>{
                    this.chargeTypeList = res.data.data;
                })
            },
            remoteGetOperatorsMethod(value) {
                this.http.post('/operator/get/id',{"operatorsName":value}).then((res)=>{
                    this.operatorsList = res.data.data;
                })
            },
            handleSizeChange(pageSize) {
                this.currentPage = 1;
                this.http.post('/charging/station/list',{"index":1,"size": pageSize,"data":{
                        "stationName":"",
                        "sttpeName":"",
                        "shopName":""
                    }}).then((res)=>{
                    this.siteList = res.data.data.records;
                })
            },
            handleCurrentChange(page) {
                this.http.post('/charging/station/list',{"index":page,"size": this.pageSize,"data":{
                        "stationName":"",
                        "sttpeName":"",
                        "shopName":""
                    }}).then((res)=>{
                    this.siteList = res.data.data.records;
                })
            },
            // 查看充电桩详情
            poleDetail(data) {
                console.log(data);
            },
        }
    }
</script>

<style lang="less">
    .siteList {
        width: 100%;
        height: 100%;
    }

    .add-pole input, slect {
        width: 160px;
    }
    .pole-list {
        width: 188px;
        height: 250px;
        margin: 5px;
        .stateColor {
            width: 130px;
            height: 160px;
            margin: 5px auto;
            background-color: rgba(255, 0, 0, 0.29);
        }
    }
</style>
