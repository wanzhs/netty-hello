<template>
    <!--充电站详情页-->
    <div class="wrap">
        <div class="hd-box-top clearfix" style="margin-bottom: 5px;">
            <p>{{ siteData.stationName }} 充电站详情</p>
            <br/>
            <el-button type="primary" size="mini" @click.native.prevent="showAddPoleDialog">添加充电桩</el-button>
            <el-button type="primary" plain size="mini">图片</el-button>
        </div>
        <div class="hd-legend" style="vertical-align: middle; height: 3em; line-height: 3em; border-top: 1px solid #808080; border-bottom: 1px solid #808080;">
            <span style="vertical-align: middle;">未充电<span style="display:inline-block; vertical-align: middle;margin-left: 3px; width: 15px;height:15px;background-color: #0000ff; border-radius: 50%;"></span></span>
            <span style="vertical-align: middle;">正在充电<span style="display:inline-block; vertical-align: middle;margin-left: 3px; width: 15px;height:15px;background-color: #008000; border-radius: 50%;"></span></span>
            <span style="vertical-align: middle;">离线<span style="display:inline-block; vertical-align: middle;margin-left: 3px; width: 15px;height:15px;background-color: #808080; border-radius: 50%;"></span></span>
            <span style="vertical-align: middle;">故障<span style="display:inline-block; vertical-align: middle;margin-left: 3px; width: 15px;height:15px;background-color: #ff0000; border-radius: 50%;"></span></span>
        </div>
        <!--充电站中的充电桩列表-->
        <div class="pole-wrap">
            <pole v-for="(item,index) in poleList" :poleInfo="item" :key="index"></pole>
        </div>
        <!--充电桩添加开始-->
        <el-dialog
                title="添加充电桩"
                :visible.sync="addPoleDialogVisible"
                :close-on-press-escape="!closeOnlyByClick"
                :close-on-click-modal="!closeOnlyByClick"
                width="640px">
            <el-form ref="poleForm" class="add-pole" :model="poleForm" inline label-width="100px" size="mini">
                <el-form-item
                        label="充电站名称">
                    <el-input v-model="siteData.stationName" disabled></el-input>
                </el-form-item>
                <el-form-item
                        label="充电桩名称">
                    <el-input v-model="poleForm.deviceName"></el-input>
                </el-form-item>
                <el-form-item
                        label="充电桩编码">
                    <el-input v-model="poleForm.deviceCode"></el-input>
                </el-form-item>
                <el-form-item
                        label="充电类型">
                    <el-select v-model="poleForm.cgtypeId">
                        <el-option v-for="item in chargeType" :label="item.cgtypeName" :value="item.cgtypeId" :key="item.cgtypeId"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item
                        label="电枪数量">
                    <el-input v-model="poleForm.deviceSubnum"></el-input>
                </el-form-item>
                <el-form-item
                        label="额定功率">
                    <el-input v-model="poleForm.devicePower"></el-input>
                </el-form-item>
                <el-form-item
                        label="电桩状态">
                    <el-select v-model="poleForm.deviceState">
                        <el-option label="维护中" value="1"></el-option>
                        <el-option label="正常使用" value="2"></el-option>
                        <el-option label="建设中" value="3"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item
                        label="全天开放">
                    <el-select v-model="poleForm.openAllDay">
                        <el-option label="是" value="1"></el-option>
                        <el-option label="否" value="0"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item
                        label="建设时间">
                    <el-date-picker
                            v-model="poleForm.buildDt"
                            type="date"
                            value-format="yyyy-MM-dd"
                            placeholder="选择日期">
                    </el-date-picker>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" size="mini" @click.native.prevent="addPole">添 加</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>
        <!--充电桩添加结束-->
        <!--充电桩详情开始-->
        <el-dialog
            title="充电桩详情"
            :visible.sync="poleDetailVisible"
            :close-on-press-escape="!closeOnlyByClick"
            :close-on-click-modal="!closeOnlyByClick"
            width="840px">
            <div class="hd-legend" style="vertical-align: middle; height: 2em; line-height: 2em; border-top: 1px solid #808080; border-bottom: 1px solid #808080;">
                <span style="vertical-align: middle;">未充电<span style="display:inline-block; vertical-align: middle;margin-left: 3px; width: 15px;height:15px;background-color: #0000ff; border-radius: 50%;"></span></span>
                <span style="vertical-align: middle;">正在充电<span style="display:inline-block; vertical-align: middle;margin-left: 3px; width: 15px;height:15px;background-color: #008000; border-radius: 50%;"></span></span>
                <span style="vertical-align: middle;">离线<span style="display:inline-block; vertical-align: middle;margin-left: 3px; width: 15px;height:15px;background-color: #808080; border-radius: 50%;"></span></span>
                <span style="vertical-align: middle;">故障<span style="display:inline-block; vertical-align: middle;margin-left: 3px; width: 15px;height:15px;background-color: #ff0000; border-radius: 50%;"></span></span>
            </div>
            <div class="pole-current" style="border-bottom: 1px solid #808080;">
                <div class="poleCurrent" :style="{background: poleStateColor[deviceData.deviceState]}"></div>
                <ul class="poleCurrentInfo">
                    <li>编号：{{ deviceData.deviceCode }}</li>
                    <li>充电类型：{{ deviceData.cgtypeName }}</li>
                    <li>充电规格：{{ deviceData.devicePower }}kW</li>
                    <li>充电桩序列号：{{ deviceData.deviceId }}</li>
                    <li>状态：{{ deviceData.deviceState }}</li>
                    <li>充电桩数量：{{ deviceData.deviceSubnum }}</li>
                </ul>
                <div class="poleOperate" style="width: 172px; text-align: center; padding: 5px 0;">
                    <span>{{ deviceData.deviceName }}</span>
                    <div style="margin-top: 5px;">
                        <el-button size="medium" style="background: #ff0000">重启</el-button>
                        <el-button  size="medium"  style="background: #ffff00; padding-left: 5px; padding-right: 5px;">停止充电</el-button>
                    </div>
                </div>
            </div>
            <div class="poleSub-list clearfix">
                <div class="psl-item" v-for="(item, index) in poleSubList" :key="index" style="text-align: center;">
                    <span>{{ item.deviceSubName }}</span>
                    <div class="psl-item-state" :style="{background: poleStateColor[item.deviceSubState]}"></div>
                </div>
            </div>
        </el-dialog>
        <!--充电桩详情结束-->
    </div>
</template>

<script>
    // 引用充电桩组件
    import pole from '@/components/common/pole'
    import connector from '../connector/connector'

    export default {
        data() {
            return {
                addPoleDialogVisible: false,
                poleDetailVisible: false,
                closeOnlyByClick: true,
                poleList: [],
                chargeType: [], // 充电桩充电类型
                poleForm: {
                    /*

                      "stationId":1,
                      "deviceName":"未来方舟#4",
                      "deviceCode":"#02",
                      "cgtypeId":1,
                      "devicePower":720,
                      "deviceSubnum":4
                     */
                    stationId: '',
                    deviceName: '', // 充电桩名称
                    deviceCode: '', //充电桩编码
                    cgtypeId: '', // 充电类型
                    devicePower: '', //充电桩功率
                    deviceState: '', // 电桩状态
                    deviceSubnum: '', // 充电桩数量
                    openAllDay: '', // 是否全天开放
                    buildDt: '', // 建设时间
                },
                siteData: null, //充电站数据
                deviceData: {},  // 充电桩数据
                deviceSubList: [], // 充电枪列表
                poleStateColor: {
                    "故障": '#ff0000',
                    "离线": '#808080',
                    "使用中": '#008000',
                    "空闲": '#0000ff'
                },
                poleSubList: [], // 充电枪列表
            }
        },
        watch: {

        },
        components: {
            pole
        },
        created() {
            // 从仓库获取
            this.poleList = this.$store.store.state.siteData.poleData;
            this.siteData = this.$store.store.state.siteData.siteData;
            this.poleForm.stationId = this.$store.store.state.siteData.siteData.stationId;
            connector.$on('clickDetail', (res)=>{
                this.deviceData = res;
                this.poleDetailVisible = true;
            });
            // 获取充电类型
            this.http.post('/charging/device/charging/type/list').then((res)=>{
                this.chargeType = res.data.data;
            })
        },
        watch: {
            'poleDetailVisible': 'getPoleSubList'
        },
        methods: {
            // 获取充电枪数据
            getPoleSubList(value) {
                if (value) {
                    this.http.post('/charging/device/sub/list',{"deviceId": this.deviceData.deviceId}).then((res)=>{
                        this.poleSubList = res.data.data;
                    });
                }
            },
            // 显示添加充电桩弹窗
            showAddPoleDialog() {
                this.addPoleDialogVisible = true;
            },
            // 添加充电桩
            addPole() {
                this.http.post('/charging/device/insert',this.poleForm).then((res)=>{
                    if (res.data.code === 0) {
                        this.msg.success('添加成功！');
                        this.addPoleDialogVisible = false;
                    } else {
                        this.msg.fail(res.data.message);
                        this.addPoleDialogVisible = false;
                    }
                });
            },
            // 显示充电桩详情
            showPoleDetail() {

            },
        }
    }
</script>

<style scoped lang="less">
    .wrap {
        width: 100%;
        height: 100%;
        overflow: auto;
    }
    .pole-wrap {
        display: flex;
        flex-wrap: wrap;
        align-items: center;
        justify-content: flex-start;
        padding: 10px 0;
    }
    .pole-current {
        width: 100%;
        margin: 10px 0;
        .poleCurrent {
            display: inline-block;
            vertical-align: middle;
            width: 172px;
            height: 208px;
        }
        .poleCurrentInfo {
            display: inline-block;
            vertical-align: middle;
            list-style: none;
            padding-left: 15px;
            line-height: 2em;
        }
    }
    .poleSub-list {
        display: flex;
        align-items: center;
        flex-wrap: wrap;
        width: 100%;

        .psl-item {
            float: left;
            width: 20%;
            height: 180px;
            padding: 5px;

            .psl-item-state {
                width: 80%;
                height: 150px;
                margin: 5px auto;
            }
        }
    }
</style>