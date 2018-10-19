<template>
    <div class="addPole">
        <el-form ref="poleForm" :model="poleForm" inline label-width="100px">
            <el-form-item
                label="充电站名称">
                <el-input name="siteName" size="mini"></el-input>
            </el-form-item>
            <el-form-item
                    label="充电站地址">
                <el-input name="siteAddress" size="mini"></el-input>
            </el-form-item>
            <el-form-item
                    label="充电站编码">
                <el-input name="siteCode" size="mini"></el-input>
            </el-form-item>
            <el-form-item
                    label="生产厂家">
                <el-input name="productName" size="mini"></el-input>
            </el-form-item>
            <el-form-item
                    label="充电类型">
                <el-input name="chargeType" size="mini"></el-input>
            </el-form-item>
            <el-form-item
                    label="电枪数量">
                <el-input name="poleSum" size="mini"></el-input>
            </el-form-item>
            <el-form-item
                    label="额定功率">
                <el-input name="ratePower" size="mini"></el-input>
            </el-form-item>
            <el-form-item
                    label="额定电流">
                <el-input name="rateCurrent" size="mini"></el-input>
            </el-form-item>
            <el-form-item
                    label="国家标准">
                <el-input name="standard" size="mini"></el-input>
            </el-form-item>
            <el-form-item
                    label="电桩状态">
                <el-input name="poleState" size="mini"></el-input>
            </el-form-item>
            <el-form-item
                    label="额定电压上限">
                <el-input name="rateVoltageUp" size="mini"></el-input>
            </el-form-item>
            <el-form-item
                    label="额定电压下限">
                <el-input name="rateVoltageLow" size="mini"></el-input>
            </el-form-item>
            <el-form-item
                    label="计费策略">
                <el-input name="priceStrategy" size="mini"></el-input>
            </el-form-item>
            <el-form-item
                    label="全天开放">
                <el-input name="allOpen" size="mini"></el-input>
            </el-form-item>
            <el-form-item
                    label="建设时间">
                <el-input name="buildTime" size="mini"></el-input>
            </el-form-item>
            <el-form-item
                    label="分时开放">
                <span>
                    <el-time-select
                            v-model="value1"
                            :picker-options="{
                                start: '08:30',
                                step: '00:15',
                                end: '18:30'
                              }"
                            placeholder="选择时间">
                    </el-time-select> -
                    <el-time-select
                            v-model="value1"
                            :picker-options="{
                                start: '08:30',
                                step: '00:15',
                                end: '18:30'
                              }"
                            placeholder="选择时间">
                    </el-time-select>
                </span>
            </el-form-item>
        </el-form>
        <el-button type="primary" size="mini">添 加</el-button>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                poleForm: {
                    "stationId": '',
                    "deviceName":"",
                    "deviceCode":"",
                    "cgtypeId": '',
                    "devicePower": '',
                    "deviceSubnum":''
                }
            }
        }
    }
</script>

<style scoped lang="less">

</style>