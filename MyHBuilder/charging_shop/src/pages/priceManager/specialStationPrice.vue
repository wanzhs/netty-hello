<template>
    <div class="wrap">
        <div class="hd-box"></div>
        <el-table
            :data="specialStaionPriceList">
        </el-table>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                specialStaionPriceList: []
            }
        },
        created() {
            this.http.post('')
        },
    }
</script>

<style scoped lang="less">
    .wrap {
        width: 100%;
        height: 100%;
    }
</style>