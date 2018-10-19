<template>
    <div class="poleList">
        <el-button type="primary" plain @click.native="addTab" size="mini">添加充电桩</el-button>
        <div class="hd-box clearfix" style="text-align: left;">
            <el-input v-model="queryCondition.id" style="width: 150px;margin-top: 5px;" placeholder="充电站名称"></el-input>
        </div>
    </div>
</template>

<script>
    import connector from '../../pages/connector/connector'
    export default {
        data() {
            return {
                queryCondition: {
                    id: '',
                }
            }
        },
        methods: {
            addTab() {
                connector.$emit('call_siteManager',{
                    title: '添加充电桩',
                    name: 'addPole',
                    closeable: true,
                })
            }
        }
    }
</script>

<style scoped lang="less">
    .poleList {
        width: 100%;
        height: 100%;
    }
</style>