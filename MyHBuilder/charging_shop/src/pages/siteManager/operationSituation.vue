<template>
    <div class="operationSituation">
        <div class="condition">
            <span>
                <el-select size="mini" v-model="condition.selectAddress" clearable placeholder="地址">
                    <el-option v-for="(item,index) in optionAddress" :label="item.label" :value="item.value" :key="index"></el-option>
                </el-select>
            </span>
            <span>
                <el-select size="mini" v-model="condition.selectSite" clearable placeholder="充电站">
                    <el-option v-for="(item,index) in optionSite" :label="item.label" :value="item.value" :key="index"></el-option>
                </el-select>
            </span>
            <span>
                <el-date-picker
                        v-model="condition.dateFrom"
                        type="date"
                        size="mini"
                        placeholder="from">
                </el-date-picker>
                <span style="font-size: 10px; color: rgba(0, 0, 0, 0.5);">至</span>
                <el-date-picker
                        v-model="condition.dateTo"
                        type="date"
                        size="mini"
                        placeholder="to">
                </el-date-picker>
            </span>
            <span>
                <el-button type="primary" size="mini" @click.native.prevent="query">查询</el-button>
            </span>
        </div>
        <div id="chart" class="chart">

        </div>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                condition: {
                    selectAddress: '',
                    selectSite: '',
                    dateFrom: '',
                    dateTo: ''
                },
                optionAddress: [],
                optionSite: [],
                data: [
                    {
                        value: 25,
                        name: '正在使用'
                    },
                    {
                        value: 20,
                        name: '预约'
                    },
                    {
                        value: 10,
                        name: '故障'
                    },
                    {
                        value: 30,
                        name: '未使用'
                    }
                ]
            }
        },
        methods: {
            query() {
                // 查询条件
                let condition = this.condition;
            },
            drawChar() {
                let chart = this.$echarts.init(document.getElementById('chart'));
                let option = {
                    title: {
                        text: '充电桩状态',
                        left: '10px',
                        top: '10px',
                        textStyle: {
                            color: 'rgba(0, 0, 0, 0.5)'
                        }
                    },
                    tooltip : {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    legend: {
                        orient: 'vertical',
                        x: 'right',
                        data:['正在使用','预约','故障','未使用']
                    },
                    series: {
                        name: '状态',
                        type: 'pie',
                        radius: [0, '50%'],
                        roseType: 'radius',
                        data: this.data,
                    }
                };
                chart.setOption(option);
            }
        },
        created() {
            // this.http.get('').then((res)=>{
            //     this.condition.selectAddress = res.data.data;
            // });
            // this.http.get('').then((res)=>{
            //     this.condition.selectSite = res.data.data;
            // })
        },
        mounted() {
            this.drawChar();
        }
    }
</script>

<style scoped lang="less">
    .chart {
        width: 500px;
        height: 500px;
        margin-top: 30px;
        border-radius: 10px;
    }
</style>
