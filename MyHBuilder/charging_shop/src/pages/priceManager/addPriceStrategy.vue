<template>
    <div class="wrap">
        <el-form ref="addPriceStrategy" :model="addPriceStrategy" label-width="125px" size="mini">
            <el-form-item
                label="充电站名称"
                style="width:420px;">
                <el-select
                        clearable v-model="addPriceStrategy.stationId" style="width:295px;"
                        filterable
                        remote
                        placeholder="搜索"
                        :remote-method="remoteGetChargeStationList">
                    <el-option v-for="item in chargeStationList" :key="item.stationId" :label="item.stationName" :value="item.stationId"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item
                label="策略名称"
                style="width:420px;">
                <el-input clearable v-model="addPriceStrategy.priceName"></el-input>
            </el-form-item>
            <el-form-item
                label="策略类型">
                <el-select
                    v-model="addPriceStrategy.priceType"  style="width:295px;">
                    <el-option label="专场计费" value="专场计费"></el-option>
                    <el-option label="非专场计费" value="非专场计费"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item
                    label="低谷时段">
                <el-time-picker v-model="addPriceStrategy.lowStart" value-format="HH:mm:ss"
                                :picker-options="{
                        selectableRange: '00:00:00 - 23:59:59'
                    }" style="width: 135px;">
                </el-time-picker><span> 至</span>
                <el-time-picker v-model="addPriceStrategy.lowEnd" value-format="HH:mm:ss" :picker-options="{
                       selectableRange: '00:00:00 - 23:59:59'
                    }" style="width: 135px;">
                </el-time-picker>
            </el-form-item>
            <el-form-item
                    label="低谷时段价格"
                    style="width:420px;">
                <el-input clearable v-model="addPriceStrategy.lowPrice"></el-input>
            </el-form-item>
            <el-form-item
                    label="低谷时段服务费"
                    style="width:420px;">
                <el-input clearable v-model="addPriceStrategy.lowService"></el-input>
            </el-form-item>
            <el-form-item
                    label="低谷时段停车价格"
                    style="width:420px;">
                <el-input clearable v-model="addPriceStrategy.lowParking"></el-input>
            </el-form-item>
            <el-form-item
                    label="平时段"
                    style="width:420px;">
                <el-time-picker v-model="addPriceStrategy.middleStart" value-format="HH:mm:ss"
                                :picker-options="{
                        selectableRange: '00:00:00 - 23:59:59'
                    }" style="width: 135px;">
                </el-time-picker><span> 至</span>
                <el-time-picker clearable v-model="addPriceStrategy.middleEnd" value-format="HH:mm:ss" :picker-options="{
                        selectableRange: '00:00:00 - 23:59:59'
                    }" style="width: 135px;">
                </el-time-picker>
            </el-form-item>
            <el-form-item
                    label="平时段价格"
                    style="width:420px;">
                <el-input clearable v-model="addPriceStrategy.middlePrice"></el-input>
            </el-form-item>
            <el-form-item
                    label="平时段服务费"
                    style="width:420px;">
                <el-input clearable v-model="addPriceStrategy.middleService"></el-input>
            </el-form-item>
            <el-form-item
                    label="平时段停车价格"
                    style="width:420px;">
                <el-input clearable v-model="addPriceStrategy.middleParking"></el-input>
            </el-form-item>
            <el-form-item
                    label="尖峰时段"
                    style="width:420px;">
                <el-time-picker v-model="addPriceStrategy.highStart" value-format="HH:mm:ss"
                                :picker-options="{
                        selectableRange: '00:00:00 - 23:59:59'
                    }" style="width: 135px;">
                </el-time-picker><span> 至</span>
                <el-time-picker v-model="addPriceStrategy.highEnd" value-format="HH:mm:ss" :picker-options="{
                        selectableRange: '00:00:00 - 23:59:59'
                    }" style="width: 135px;">
                </el-time-picker>
            </el-form-item>
            <el-form-item
                    label="尖峰价格"
                    style="width:420px;">
                <el-input clearable v-model="addPriceStrategy.highPrice"></el-input>
            </el-form-item>
            <el-form-item
                    label="尖峰服务费"
                    style="width:420px;">
                <el-input clearable v-model="addPriceStrategy.highService"></el-input>
            </el-form-item>
            <el-form-item
                    label="尖峰停车价格"
                    style="width:420px;">
                <el-input clearable v-model="addPriceStrategy.highParking"></el-input>
            </el-form-item>
        </el-form>
        <el-button size="mini" style="margin-left: 360px;" type="primary" @click="addChargeStrategy">提 交</el-button>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                addPriceStrategy: {
                    stationId: '',
                    priceName: '',
                    priceType: '',
                    lowStart: '',
                    lowEnd: '',
                    lowPrice: '',
                    lowParking: '',
                    lowService: '',
                    middleStart: '',
                    middleEnd: '',
                    middlePrice: '',
                    middleParking: '',
                    middleService: '',
                    highStart: '',
                    highEnd: '',
                    highPrice: '',
                    highParking: '',
                    highService: ''
                },
                chargeStationList: [], // 充电站列表
            }
        },
        methods: {
            // 获取充电站列表
            remoteGetChargeStationList(value) {
                this.http.post('/charging/station/get/id',{"stationName": value}).then((res)=>{
                    this.chargeStationList = res.data.data;
                })
            },
            // 添加计费策略
            addChargeStrategy() {
                this.http.post('/charging/price/list/save',this.addPriceStrategy).then((res)=>{
                    if (res.data.code === 0) {
                        this.msg.success("添加成功！");
                        this.resetForm('addPriceStrategy');
                    } else {
                        this.msg.fail(res.data.message);
                    }
                })
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            }
        }
    }
</script>

<style scoped lang="less">
    .wrap {
        width: 100%;
        height: 100%;
        overflow: auto;
        .el-form-item {
            margin-bottom: 5px;
        }
    }
</style>