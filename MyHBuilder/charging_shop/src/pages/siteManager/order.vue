<template>
    <div class="orderList">
        <el-tabs v-model="activeName" type="card" size="mini" style="height: 41px;" @tab-click="handleClick">
            <el-tab-pane label="充电订单管理" name="first"></el-tab-pane>
            <el-tab-pane label="评论管理" name="second"></el-tab-pane>
            <el-tab-pane label="充值订单" name="third"></el-tab-pane>
        </el-tabs>
        <router-view class="orderView"></router-view>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                activeName: 'first'
            }
        },
        methods: {
            handleClick(tab) {
                switch(tab.name) {
                    case 'first':
                        this.$router.push({path: '/admin/siteManager/order'});
                        break;
                    case 'second':
                        this.$router.push({path: '/admin/siteManager/order/commentManager'});
                        break;
                    case 'third':
                        this.$router.push({path: '/admin/siteManager/order/orderManager'});
                        break;
                }
            },
        },
    }
</script>

<style scoped lang="less">
    .orderList {
        width: 100%;
        height: 100%;
        .orderView {
            width: 100%;
            height: 97%;
            padding: 5px;
        }
    }
</style>