<template>
    <div class="priceManage">
        <el-tabs v-model="activeName" type="border-card" tab-position="top" @tab-click="handleClick">
            <el-tab-pane label="专用场站费用设置" name="firstSetting" ></el-tab-pane>
            <el-tab-pane label="非专用场站费用设置" name="secondSetting"></el-tab-pane>
            <el-tab-pane label="添加计费" name="thirdSetting"  ></el-tab-pane>
            <el-tab-pane label="专用场站费用"></el-tab-pane>
            <el-tab-pane label="桩电量" name="fourthSetting" ></el-tab-pane>
        </el-tabs>
        <router-view class="rout"></router-view>
    </div>
</template>

<script>
    export default {
        data() {
            return{
                state: '编辑',
                activeName: 'firstSetting',

            }
        },
        methods: {
            handleClick(tab, event){
                if(tab.index == 0){
                    this.$router.push({path: "/admin/price"});
                }else if(tab.index == 1) {
                    this.$router.push({path: "/admin/price/noSpecialPrice"});
                }else if(tab.index == 2){
                    this.$router.push({path: "/admin/price/specialPriceSetting"});
                }else if(tab.index == 3){
                    this.$router.push({path: "/admin/price/"})
                } else {
                    this.$router.push({path: "/admin/price/poleElec"});
                }
            },
            changeEdit(row) {
                row.isEdit = !row.isEdit;
            },

            saveEdit(row) {
                row.isEdit = !row.isEdit;
            },
            deleteRow(index, rows) {
                rows.splice(index, 1);
            }
        }
    }
</script>
<style scoped lang="less">
    .priceManage {
        width: 100%;
        height: 100%;
    }
    .rout {
        width: 100%;
        height: 95%;
    }
</style>
