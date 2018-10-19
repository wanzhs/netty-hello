<template>
    <div class="wrap">
        <div class="hd-box clearfix">
            <el-input v-model="queryCondition.sttpeName" placeholder="充电站运营类型" clearable style="width: 150px; text-align: left;"></el-input>
            <el-input v-model="queryCondition.shopName" placeholder="商户名称" clearable style="width: 150px; text-align: left;"></el-input>
            <el-input v-model="queryCondition.stationName" placeholder="充电站名称" clearable style="width: 150px; text-align: left;"></el-input>
            <el-button type="primary" plain size="mini" @click="query">查询</el-button>
            <el-button type="primary" size="mini" style="float: right;" @click.native.prevent="showAddStationDialog">添加</el-button>
        </div>
        <el-table :data="siteList"
            size="mini"
            style="width: 100%"
            border
            :header-cell-style="{'background-color': '#f3f3f3',}"
            height="90%">
            <el-table-column
                label="充电站">
                <el-table-column
                        label="ID"
                        prop="stationId"
                        width="50px">
                </el-table-column>
                <el-table-column
                        label="编码"
                        prop="stationCode"
                        width="60px">
                </el-table-column>
                <el-table-column
                        label="类型"
                        prop="stationType"
                        width="85px">
                </el-table-column>
                <el-table-column
                        label="运营类型"
                        prop="sttpeName"
                        width="75px">
                </el-table-column>
                <el-table-column
                        label="名称"
                        prop="stationName"
                        width="90px">
                </el-table-column>
            </el-table-column>
            <el-table-column
                label="桩数"
                prop="deviceNums"
                width="55px">
            </el-table-column>
            <el-table-column
                label="经度"
                prop="lng">
            </el-table-column>
            <el-table-column
                label="纬度"
                prop="lat">
            </el-table-column>
            <el-table-column
                label="省"
                prop="sprovince">
            </el-table-column>
            <el-table-column
                label="市"
                prop="scity">
            </el-table-column>
            <el-table-column
                label="区/县"
                prop="scounty">
            </el-table-column>
            <el-table-column
                label="街道/乡"
                prop="scountry">
            </el-table-column>
            <el-table-column
                    label="商户编码"
                    prop="shopCode">
            </el-table-column>
            <el-table-column
                    label="商户名称"
                    prop="shopName">
            </el-table-column>
            <el-table-column
                label="插入时间">
                <template slot-scope="scope">
                    {{ scope.row.insertDt | formatInsertDt }}
                </template>
            </el-table-column>
            <el-table-column
                label="操作">
                <template slot-scope="scope">
                    <span><el-button type="text" size="mini" style="margin-left: 0;" @click.native.prevent="showUpdateStationDialog(scope.row)">修改</el-button></span>
                    <el-button type="text" size="mini" class="deleteBtn" @click.native.prevent="showDeleteDialog(scope.$index, scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <!--充电站添加开始-->
        <el-dialog
            title="添加充电站"
            :visible.sync="addChargeStationDialog"
            :close-on-press-escape="!closeOnlyByClick"
            :close-on-click-modal="!closeOnlyByClick"
            width="560px">
            <el-form ref="addChargeStationForm" :rules="rules" :model="addChargeStation" label-width="110px" size="mini">
                <el-form-item
                    label="省"
                    prop="province">
                    <el-select v-model="addChargeStation.province" @change="remoteGetCity" style="width:90%;"
                        clearable>
                        <el-option v-for="item in provinceList" :label="item.districtName" :value="item.districtCode" :key="item.districtCode"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item
                        label="市"
                        prop="city">
                        <el-select v-model="addChargeStation.city" @change="remoteGetCounty" style="width:90%;">
                            <el-option v-for="item in cityList" :label="item.districtName" :value="item.districtCode" :key="item.districtCode"></el-option>
                        </el-select>
                </el-form-item>
                <el-form-item
                        label="区/县"
                        prop="county">
                    <el-select v-model="addChargeStation.county" @change="remoteGetCountry" style="width:90%;">
                        <el-option v-for="item in countyList" :label="item.districtName" :value="item.districtCode" :key="item.districtCode"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item
                        label="乡镇（街道）"
                        prop="country">
                    <el-select v-model="addChargeStation.country" style="width:90%;">
                        <el-option v-for="item in countryList" :label="item.districtName" :value="item.districtCode" :key="item.districtCode"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item
                    label="地址"
                    prop="stationAddr">
                    <el-input v-model="addChargeStation.stationAddr" style="width: 90%;"></el-input>
                </el-form-item>
                <el-form-item
                    label="充电站类型"
                    prop="stationType">
                    <el-select
                        v-model="addChargeStation.stationType"
                        style="width:90%;">
                        <el-option label="对外开放" value="1"></el-option>
                        <el-option label="不对外开放" value="2"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item
                    label="充电站运营类型"
                    prop="stationOperatorType">
                    <el-select v-model="addChargeStation.sttpeId"
                               clearable
                               style="width:90%;">
                        <el-option v-for="(item,index) in chargeTypeList" :key="index" :label="item.sttpeName" :value="item.sttpeId"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item
                    label="运营商名称">
                    <el-select v-model="addChargeStation.operatorsId"
                               filterable
                               remote
                               clearable
                               placeholder="搜索"
                               :remote-method="remoteGetOperatorsMethod"
                               style="width:90%;">
                        <el-option v-for="item in operatorsList" :label="item.operatorsName" :value="item.operatorsId" :key="item.operatorsId"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item
                        label="充电站名称"
                        prop="stationName">
                    <el-input clearable v-model="addChargeStation.stationName" style="width:90%;"></el-input>
                </el-form-item>
                <el-form-item
                    label="充电站开放时间">
                    <template slot-scope="scope">
                        <el-time-picker
                                value-format="HH:mm:ss"
                                :picker-options="{
                                      selectableRange: '00:00:00 - 23:59:59'
                                }"
                                v-model="addChargeStation.stationOpendt" style="width:43%;"></el-time-picker> - <el-time-picker :picker-options="{
                                      selectableRange: '00:00:00 - 23:59:59'
                                }" v-model="addChargeStation.stationClosedt" value-format="HH:mm:ss" style="width:43%;"></el-time-picker>
                    </template>
                </el-form-item>
                <el-form-item
                    label="商户名称">
                    <el-select v-model="addChargeStation.shopId"
                               filterable
                               remote
                               clearable
                               :remote-method="remoteGetShopMethod"
                               style="width:90%;">
                        <el-option v-for="item in shopList" :label="item.shopName" :value="item.shopId" :key="item.shopId"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item
                    label="图片">
                    <el-upload
                            class="upload-demo"
                            ref="upload"
                            action="upload"
                            multiple
                            :limit="6"
                            :before-upload="beforeUpload"
                            :on-exceed="limitFilesNum"
                            :on-change="selectFile"
                            :file-list="fileList"
                            :http-request="myUploadMethod"
                            accept=".jpg, .png, .jpeg"
                            :auto-upload="false">
                        <el-button slot="trigger" size="mini" type="primary">选取文件</el-button>
                        <el-button style="margin-left: 10px;" size="small" type="success" @click="uploadBySelf">上传到服务器</el-button>
                        <div slot="tip" class="el-upload__tip">只能上传jpg/jpeg/png文件，且不超过500kb,一次最多上传6张</div>
                    </el-upload>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button type="primary" plain @click.native.prevent="addChargeStationDialog=false">取消</el-button>
                <el-button type="primary" @click.native.prevent="submitForm('addChargeStationForm')">确认</el-button>
            </span>
        </el-dialog>
        <!--充电站添加结束-->
        <!--充电站修改开始-->
        <el-dialog
            title="修改"
            :visible.sync="updateStationDialogVisible"
            :close-on-press-escape="!closeOnlyByClick"
            :close-on-click-modal="!closeOnlyByClick"
            width="560px">
            <el-form :model="willChangeData" label-width="120px" size="mini">
                <el-form-item
                    label="充电站类型">
                    <el-select style="width: 90%;" v-model="willChangeData.stationType">
                        <el-option label="对外开放" value="1"></el-option>
                        <el-option label="不对外开放" value="2"></el-option>
                        <el-option label="个人" value="0"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item
                    label="充电站运营类型">
                    <el-select v-model="willChangeData.sttpeName" style="width: 90%;">
                        <el-option v-for="(item,index) in chargeTypeList" :key="index" :label="item.sttpeName" :value="item.sttpeId"></el-option>
                    </el-select>
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
                        label="运营时间">
                    <el-time-picker v-model="willChangeData.stationOpendt" value-format="HH:mm:ss"
                                    :picker-options="{
                        selectableRange: '00:00:00 - 23:59:59'
                    }" style="width: 135px;">
                    </el-time-picker><span> 至</span>
                    <el-time-picker v-model="willChangeData.stationClosedt" value-format="HH:mm:ss" :picker-options="{
                       selectableRange: '00:00:00 - 23:59:59'
                    }" style="width: 135px;">
                    </el-time-picker>
                </el-form-item>
                <el-form-item
                    label="商户名称">
                    <el-select
                        v-model="willChangeData.shopName"  style="width: 90%;"
                        remote
                        filterable
                        :remote-method="remoteGetShopMethod">
                        <el-option v-for="item in shopList" :label="item.shopName" :value="item.shopId" :key="item.shopId"></el-option>
                    </el-select>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button type="primary" plain @click.native.prevent="updateStationDialogVisible=false">取消</el-button>
                <el-button type="primary" @click.native.prevent="updateSite">确认</el-button>
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
            <span style="font-size:1.2em; margin-left: 2.5em;">是否删除？</span>
            <span slot="footer" class="dialog-footer">
                <el-button @click.native.prevent="deleteSiteDialog=false">否</el-button>
                <el-button v-if="loading" type="danger" :loading="true"></el-button>
                <el-button v-else type="danger" @click.native.prevent="deleteSite" class="dangerOperate">是</el-button>
            </span>
        </el-dialog>
        <!--充电站删除对话框结束-->
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
    import axios from 'axios'
    export default {
        data() {
            return{
                addChargeStationDialog: false,
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
                fileList: [], //上传文件列表
                willChangeDataIndex: 0, //将要操作的数据索引
                willChangeData: {
                    stationId: '',
                    stationType: '',
                    stationAddr: '',
                    stationName: '',
                    stationOpendt:"",
                    stationClosedt:"",
                    stationCode: '',
                    sttpeName: '',
                    deviceNums: '',
                    lng: '',
                    lat: '',
                    sttpeId: '',
                    province:'',
                    city:'',
                    county:'',
                    country:'',
                    shopName: '',
                    shopId: '',
                    operatorsId: '',
                }, //将要操作的数据
                siteDetailTitle: '', //充电站详情弹窗标题
                detailDialogVisible: false,
                queryCondition: {
                    shopName: '',
                    sttpeName: '',
                    stationName: '',
                },
                addChargeStation: {// 添加充电站对象
                    operatorsId: '', // 运营商ID stationCode: '', // 充电站编码
                    stationOpendt: '',// 充电站开放时间
                    stationClosedt: '', // 充电站关闭时间
                    stationName: '', // 充电站名字
                    shopId: '', // 商户Id
                    stationType: '', // 充电站类型
                    sttpeId: '', // 运营商类型ID
                    stationAddr: '', // 充电桩地址
                    province: '', // 省编码
                    city: '', // 市编码
                    county: '', // 县编码
                    country: '', // 区编码
                    deviceNum: '', // 桩设备数
                    lat: '', // 经度
                    lng: '', // 纬度
                },
                rules: {
                    province: [
                        {required: true, message: '省份不能为空！', trigger: 'blur'}
                    ],
                    city: [
                        {required: true, message: '市不能为空！', trigger: 'blur'}
                    ],
                    county: [
                        {required: true, message: '县不能为空！', trigger: 'blur'}
                    ],
                    country: [
                        {required: true, message: '乡镇不能为空！', trigger: 'blur'}
                    ],
                    stationAddr: [
                        {required: true, message: '地址不能为空！', trigger: 'blur'}
                    ],
                    stationType: [
                        {required: true, message: '充电站类型不能为空！', trigger: 'blur'}
                    ],
                    sttpeId: [
                        {required: true, message: '充电站运营类型不能为空！', trigger: 'blur'}
                    ],
                },
                provinceList: [], // 省列表
                cityList: [], // 市列表
                countyList: [], // 县列表
                countryList: [], //　镇列表
                loading: false,
            }
        },
        filters: {
            formatInsertDt: function(value) {
                if (value) {
                    return Array.prototype.slice.call(value,0,10).join("");
                } else {
                    return '时间未知';
                }
            }
        },
        created() {
            this.http.post('/charging/station/list',{"index":1,"size":10,"data":{
                    "stationName":"",
                    "sttpeName":"",
                    "shopName":""
                }}).then((res)=>{
                this.siteList = res.data.data.records;
                this.total = res.data.data.total;
            });
            this.getStationOperatorType();
        },
        methods: {
            // 条件查询
            query() {
                console.log(this.queryCondition)
                this.http.post('/charging/station/list', {"index":1,"size": this.pageSize, "data":this.queryCondition}).then((res)=>{
                    this.siteList = res.data.data.records;
                    this.total = res.data.data.total;
                })
            },
            isVisibleChange(data,index) {
                this.willChangeData.stationType = data.stationType;
                this.willChangeData.sttpeName = data.sttpeName;
                this.willChangeData.stationName = data.stationName;
                this.willChangeData.deviceNums = data.deviceNums;
                this.willChangeData.lng = data.lng;
                this.willChangeData.lat = data.lat;
                this.willChangeData.shopName = data.shopName;
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
            showDeleteDialog(index, row) {
                this.deleteSiteDialog = true;
                this.willDeleteDataIndex = index;
                this.willDeleteData = row;
            },
            showAddStationDialog() {
                this.addChargeStationDialog = true;
                this.http.post('/district/list',{"districtCode":0}).then((res)=>{
                    this.provinceList = res.data.data;
                    // if(res.data.data.some((item)=>{
                    //     return item.districtLevel == '省';
                    // })) {
                    //
                    // }
                });
            },
            showUpdateStationDialog(row) {
                console.log(row)
                this.willChangeData.stationType = row.stationType;
                this.willChangeData.stationCode = row.stationCode;
                this.willChangeData.stationId = row.stationId;
                this.willChangeData.sttpeName = row.sttpeName;
                this.willChangeData.sttpeId = row.sttpeId;
                this.willChangeData.stationName = row.stationName;
                this.willChangeData.deviceNums = row.deviceNums;
                this.willChangeData.lng = row.lng;
                this.willChangeData.lat = row.lat;
                this.willChangeData.shopId = row.shopId;
                this.willChangeData.shopName = row.shopName;
                this.willChangeData.province = row.province;
                this.willChangeData.city = row.city;
                this.willChangeData.country = row.country;
                this.willChangeData.county = row.county;
                this.willChangeData.operatorsId = row.operatorsId;
                this.willChangeData.stationAddr = row.stationAddr;
                this.updateStationDialogVisible = true;
            },
            showSiteDetailDialog(stationName) {
                this.siteDetailTitle = stationName + '充电站详情';
                this.detailDialogVisible = true;
            },
            // 获取充电站运营类型
            getStationOperatorType() {
                this.http.post('/sttpe/list').then((res)=>{
                    this.chargeTypeList = res.data.data;
                })
            },
            // 选取文件时触发
            selectFile(file, fileList) {
                fileList.forEach((item)=>{
                    if (item.size / 1024 /1024 > 5) {
                        alert('文件过大，不能超过5M');
                    } else {
                        console.log(fileList);
                        this.fileList = fileList;
                    }
                });
            },
            // 文件个数超出
            limitFilesNum() {
                this.msg.fail('文件个数超出限制！');
            },
            // 图片上传(参数为upload组件的ref值)
            pictureUpload(uploadName) {
                this.$refs[uploadName].submit();
            },
            beforeUpload(file) {

            },
            // 覆盖默认上传方法
            myUploadMethod(parm) {

            },
            // 手动提交
            uploadBySelf() {
                console.log(this.fileList);
                let args = this.addChargeStation;
                let formData = new FormData();
                for(let p in args) {
                    formData.append(p, args[p]);
                }
                this.fileList.forEach((item)=>{
                    formData.append("files", item);
                });
                axios.post('/charging/station/file/upload',formData).then((res)=>{
                    if (res.data.code === 0) {
                        this.msg.success("添加成功！");
                        this.addChargeStationDialog = false;
                    } else {
                        this.msg.fail(res.data.message);
                    }
                })
            },
            deleteSite() {
                this.loading = true;
                this.http.post('/charging/station/delete',{"stationId":this.willDeleteData.stationId}).then((res)=>{
                    if (res.data.code === 0) {
                        setTimeout(()=>{
                            this.msg.success('删除成功！');
                            this.deleteSiteDialog = false;
                            this.loading = false;
                            this.siteList.splice(this.willChangeDataIndex, 1);
                            this.total--;
                        }, 800);
                    } else {
                        this.msg.fail(res.data.message);
                        this.deleteSiteDialog = false;
                        this.loading = false;
                    }
                });
            },
            updateSite() {
                this.http.post('/charging/station/update',this.willChangeData).then((res)=>{
                    if (res.data.code === 0) {
                        this.updateStationDialogVisible = false;
                        this.msg.success("修改成功！")
                    } else {
                        this.msg.fail(res.data.message);
                    }
                });
            },
            remoteGetShopMethod(value) {
                this.http.post('/charging/shop/get/id',{"shopName":value}).then((res)=>{
                    this.shopList = res.data.data;
                })
            },
            // 远程获取省份
            remoteGetProvince(value) {
                this.http.post('/district/get/code',{"districtName":value}).then((res)=>{
                    if(res.data.data.some((item)=>{
                        return item.districtLevel == '省';
                    })) {
                        this.provinceList = res.data.data;
                    }
                })
            },
            // 远程获取市列表
            remoteGetCity(value) {
                this.http.post('/district/list', {"districtCode": value}).then((res)=>{
                    this.cityList = res.data.data;
                });
            },
            // 获取县列表
            remoteGetCounty(value) {
                this.http.post('/district/list', {"districtCode": value}).then((res)=>{
                    this.countyList = res.data.data;
                })
            },
            // 获取乡镇
            remoteGetCountry(value) {
                this.http.post('/district/list', {"districtCode": value}).then((res)=>{
                    this.countryList = res.data.data;
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
                this.pageSize = pageSize;
                this.http.post('/charging/station/list',{"index":1,"size": pageSize, "data":{
                        "stationName":"",
                        "sttpeName":"",
                        "shopName":""
                    }}).then((res)=>{
                    this.siteList = res.data.data.records;
                })
            },
            handleCurrentChange(page) {
                this.currentPage = page;
                this.http.post('/charging/station/list',{"index":page,"size": this.pageSize, "data":{
                        "stationName":"",
                        "sttpeName":"",
                        "shopName":""
                    }}).then((res)=>{
                    this.siteList = res.data.data.records;
                })
            },
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        //this.pictureUpload('upload');
                        this.uploadBySelf();
                    } else {
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
        }
    }
</script>

<style scoped lang="less">

</style>
