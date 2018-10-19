<template>
    <div style="width: 100%; height: 100%;">
        <div class="hdNav">
            <el-tabs v-model="activeName" type="card" @tab-click="handleClick" @tab-remove="removeTab">
                <el-tab-pane
                        :key="item.name"
                        v-for="(item) in editableTabs"
                        :label="item.title"
                        :name="item.name"
                        :index="item.index"
                        :closable="item.closeable"
                        :lazy="lazy">
                </el-tab-pane>
            </el-tabs>
        </div>
        <router-view style="height: 92%;"></router-view>
    </div>
</template>

<script>
    import connector from '../../pages/connector/connector.js'
    export default {
        data() {
            return {
                activeName: 'poleList',
                lazy: true,
                editableTabs: [
                    {
                        title: '充电桩列表',
                        name: 'poleList',
                    }
                ]
            }
        },
        methods: {
            handleClick(item) {
                this.editableTabs.find(curr=>{
                    if(curr.name==item.name){
                        return false;
                    }
                })
                this.$router.push(item.name);
            },
            removeTab(targetName) {
                let tabs = this.editableTabs;
                let activeName = this.activeName;
                if (activeName === targetName) {
                    tabs.forEach((tab, index) => {
                        if (tab.name === targetName) {
                            let nextTab = tabs[index + 1] || tabs[index - 1];
                            if (nextTab) {
                                activeName = nextTab.name;
                                this.$router.push(nextTab.name);
                            }
                        }
                    });
                }
                this.activeName = activeName;
                this.editableTabs = tabs.filter(tab => tab.name !== targetName);
            }
        },
        mounted() {
            connector.$on('call_siteManager', (res)=>{
                if(this.editableTabs.some((item) => {
                    return item.name === res.name;
                })) {
                    this.$router.push(res.name);
                    this.activeName = res.name;
                } else {
                    this.editableTabs.push(res);
                    this.activeName = res.name;
                    this.$router.push(res.name);
                }
            })
        }
    }
</script>

<style scoped lang="less">

</style>
