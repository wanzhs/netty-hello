<template>
    <div class="wrap">
        <el-table :data="privatePrice"
            size="mini"
            border
            :header-cell-style="{'background-color': '#f3f3f3',}"
            width="100%"
            height="95%">
            <el-table-column label="商户">
                <el-table-column
                        label="ID"
                        prop="shopId"
                        width="50px">
                </el-table-column>
                <el-table-column
                        label="编码"
                        prop="shopCode"
                        width="60px">
                </el-table-column>
                <el-table-column
                        label="名称"
                        prop="shopName">
                </el-table-column>
            </el-table-column>
            <el-table-column label="充电站">\
                <el-table-column
                        label="编码"
                        prop="stationCode"
                        width="60px">
                </el-table-column>
                <el-table-column
                        label="充电类型"
                        prop="chargeType">
                </el-table-column>
            </el-table-column>
            <el-table-column
                    label="策略名称"
                    prop="priceName">
            </el-table-column>
            <el-table-column
                    label="收费规则"
                    prop="priceType">
            </el-table-column>
            <el-table-column label="时段">
                <el-table-column label="尖峰">
                    <el-table-column
                            label="时段">
                        <template slot-scope="scope">
                            {{ scope.row.highStart +'\n'+ scope.row.highEnd }}
                        </template>
                    </el-table-column>
                    <el-table-column
                            label="价格"
                            prop="highPrice"
                            width="50px">
                    </el-table-column>
                    <el-table-column
                            label="服务费"
                            prop="highService"
                            width="65px">
                    </el-table-column>
                </el-table-column>
                <el-table-column label="平时">
                    <el-table-column
                            label="时段">
                        <template slot-scope="scope">
                            {{ scope.row.middleStart +'\n'+ scope.row.middleEnd }}
                        </template>
                    </el-table-column>
                    <el-table-column
                            label="价格"
                            prop="middlePrice"
                            width="50px">
                    </el-table-column>
                    <el-table-column
                            label="服务费"
                            prop="middleService"
                            width="65px">
                    </el-table-column>
                </el-table-column>
                <el-table-column label="低谷">
                    <el-table-column
                            label="时段">
                        <template slot-scope="scope">
                            {{ scope.row.lowStart +'\n'+ scope.row.lowEnd }}
                        </template>
                    </el-table-column>
                    <el-table-column
                            label="价格"
                            prop="lowPrice"
                            width="50px">
                    </el-table-column>
                    <el-table-column
                            label="服务费"
                            prop="lowService"
                            width="65px">
                    </el-table-column>
                </el-table-column>
            </el-table-column>
            <el-table-column
                    label="操作"
                    width="70">
                <template slot-scope="scope">
                    <span><el-button type="text" size="mini" @click.native.prevent="showUpdateDialog(scope.row)">编辑</el-button></span>
                    <!--<el-button type="text" size="mini" @click.native.prevent="">删除</el-button>-->
                </template>
            </el-table-column>
        </el-table>
        <!--修改非专用场站费用开始-->
        <el-dialog
                title="修改"
                :visible.sync="updatePriceDialog"
                :close-on-click-modal="!closeOnlyByClick"
                :close-on-press-escape="!closeOnlyByClick"
                width="520px">
            <el-form
                    :model="updatePrice"
                    size="mini"
                    label-width="130px">
                <el-form-item
                        label="策略名称">
                    <el-input clearable style="width: 292px;" v-model="updatePrice.priceName"></el-input>
                </el-form-item>
                <el-form-item
                        label="尖峰时段">
                    <el-time-picker v-model="updatePrice.highStart" value-format="HH:mm:ss" arrow-control
                                    :picker-options="{
                        selectableRange: '00:00:00 - 23:59:59'
                    }" style="width: 135px;">
                    </el-time-picker><span> 至</span>
                    <el-time-picker v-model="updatePrice.highEnd" value-format="HH:mm:ss" arrow-control :picker-options="{
                        selectableRange: '00:00:00 - 23:59:59'
                    }" style="width: 135px;">
                    </el-time-picker>
                </el-form-item>
                <el-form-item
                        label="尖峰价格">
                    <el-input clearable style="width: 292px;" v-model="updatePrice.highPrice"></el-input>
                </el-form-item>
                <el-form-item
                        label="尖峰服务费">
                    <el-input clearable style="width: 292px;" v-model="updatePrice.highService"></el-input>
                </el-form-item>
                <el-form-item
                        label="尖峰停车价格">
                    <el-input clearable style="width: 292px;" v-model="updatePrice.highParking"></el-input>
                </el-form-item>
                <el-form-item
                        label="平时段">
                    <el-time-picker v-model="updatePrice.middleStart" value-format="HH:mm:ss" arrow-control
                                    :picker-options="{
                        selectableRange: '00:00:00 - 23:59:59'
                    }" style="width: 135px;">
                    </el-time-picker><span> 至</span>
                    <el-time-picker clearable v-model="updatePrice.middleEnd" value-format="HH:mm:ss" arrow-control :picker-options="{
                        selectableRange: '00:00:00 - 23:59:59'
                    }" style="width: 135px;">
                    </el-time-picker>
                </el-form-item>
                <el-form-item
                        label="平时段价格">
                    <el-input clearable style="width: 292px;"  v-model="updatePrice.middlePrice"></el-input>
                </el-form-item>
                <el-form-item
                        label="平时段服务费">
                    <el-input clearable style="width: 292px;"  v-model="updatePrice.middleService"></el-input>
                </el-form-item>
                <el-form-item
                        label="平时段停车价格">
                    <el-input clearable style="width: 292px;"  v-model="updatePrice.middleParking"></el-input>
                </el-form-item>
                <el-form-item
                        label="低谷时段">
                    <el-time-picker v-model="updatePrice.lowStart" value-format="HH:mm:ss" arrow-control
                                    :picker-options="{
                        selectableRange: '00:00:00 - 23:59:59'
                    }" style="width: 135px;">
                    </el-time-picker><span> 至</span>
                    <el-time-picker v-model="updatePrice.lowEnd" value-format="HH:mm:ss" arrow-control :picker-options="{
                       selectableRange: '00:00:00 - 23:59:59'
                    }" style="width: 135px;">
                    </el-time-picker>
                </el-form-item>
                <el-form-item
                        label="低谷时段价格">
                    <el-input clearable style="width: 292px;"  v-model="updatePrice.lowPrice"></el-input>
                </el-form-item>
                <el-form-item
                        label="低谷时段服务费">
                    <el-input clearable style="width: 292px;"  v-model="updatePrice.lowService"></el-input>
                </el-form-item>
                <el-form-item
                        label="低谷时段停车价格">
                    <el-input clearable style="width: 292px;"  v-model="updatePrice.lowParking"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button type="primary" plain @click="updatePriceDialog=false">取消</el-button>
                <el-button type="primary" @click="updatePriceMethod">确认</el-button>
            </span>
        </el-dialog>
        <!--修改非专用场站费用结束-->

        <!--底部分页器开始-->
        <div class="ft-box" style="text-align: right; margin-top: 8px;">
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
                privatePrice: [],
                updatePriceDialog: false,
                closeOnlyByClick: true,
                updatePrice: {
                    "stationId": '',
                    "priceName": "",
                    "priceType": 10,
                    "lowStart": "",
                    "lowEnd": "",
                    "lowPrice": 0,
                    "lowParking": 0,
                    "lowService": 0,
                    "middleStart": "",
                    "middleEnd": "",
                    "middlePrice": 0,
                    "middleParking": 0,
                    "middleService": 0,
                    "highStart": "",
                    "highEnd": "",
                    "highPrice": 0,
                    "highParking": 0,
                    "highService": 0
                },
            }
        },
        created() {
            this.http.post('/charging/price/list/noprivate',{"index":1,"size":10,"data":{}}).then((res)=>{
                this.privatePrice = res.data.data.records;
                this.total = res.data.data.total || 0;
            })
        },
        methods: {
            handleSizeChange(pageSize) {
                this.currentPage = 1;
                this.pageSize = pageSize;
                this.http.post('/charging/price/list/noprivate',{"index":1,"size":pageSize,"data":{}}).then((res)=>{
                    this.privatePrice = res.data.data.records;
                })
            },
            handleCurrentChange(page) {
                this.currentPage = page;
                this.http.post('/charging/price/list/noprivate',{"index":page,"size":this.pageSize,"data":{}}).then((res)=>{
                    this.privatePrice = res.data.data.records;
                })
            },
            showUpdateDialog(row) {
                this.updatePrice.stationId = row.stationId;
                this.updatePrice.priceName = row.priceName;
                this.updatePrice.priceType = row.priceType;
                this.updatePrice.lowStart = row.lowStart;
                this.updatePrice.lowEnd = row.lowEnd;
                this.updatePrice.lowPrice = row.lowPrice;
                this.updatePrice.lowParking = row.lowParking;
                this.updatePrice.lowService = row.lowService;
                this.updatePrice.middleStart = row.middleStart;
                this.updatePrice.middleEnd = row.middleEnd;
                this.updatePrice.middlePrice = row.middlePrice;
                this.updatePrice.middleParking = row.middleParking;
                this.updatePrice.middleService = row.middleService;
                this.updatePrice.highStart = row.highStart;
                this.updatePrice.highEnd = row.highEnd;
                this.updatePrice.highPrice = row.highPrice;
                this.updatePrice.highParking = row.highParking;
                this.updatePrice.highService = row.highService;
                this.updatePriceDialog = true;
            },
            updatePriceMethod() {
                this.http.post('/charging/price/list/update',this.updatePrice).then((res)=>{
                    console.log(res);
                    if (res.data.code === 0) {
                        this.msg.success("修改成功！");
                        this.updatePriceDialog = false;
                        this.http.post('/charging/price/list/private',{"index":this.page,"size":this.pageSize,"data":{}}).then((res)=>{
                            this.privatePrice = res.data.data.records;
                        });
                    } else {
                        this.msg.fail(res.data.message);
                    }
                })
            },
        }
    }
</script>

<style scoped lang="less">
    .wrap {
        width: 100%;
        height: 100%;
    }
</style>