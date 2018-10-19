<template>
    <!--充电桩组件-->
    <div class="pole-list" style="cursor: pointer;" @click.stop.prevent="showPoleDetail(poleInfo)">
        <div class="stateColor" :style="{background: poleStateColor[poleInfo.deviceState]}"></div>
        <div class="pole-info" style="text-align: center; font-size: 13px;">
            <p>{{ poleInfo.deviceCode }}</p>
            <p>{{ poleInfo.deviceName }}</p>
            <p>{{ poleInfo.cgtypeName }}</p>
            <p>{{ poleInfo.devicePower + 'kW' }}</p>
        </div>
    </div>
</template>

<script>
    import connector from '@/pages/connector/connector'
    export default {
        data() {
            return {
                poleStateColor: {
                    "故障": '#ff0000',
                    "离线": '#808080',
                    "使用中": '#008000',
                    "空闲": '#0000ff'
                }
            }
        },
        props: [
            'poleInfo'
        ],
        methods: {
            showPoleDetail(poleInfo) {
                connector.$emit('clickDetail',poleInfo);
            }
        }
    }
</script>

<style scoped lang="less">
    .pole-list {
        width: 180px;
        height: 250px;
        margin: 5px;
        .stateColor {
            width: 130px;
            height: 160px;
            margin: 5px auto;
        }
    }
</style>