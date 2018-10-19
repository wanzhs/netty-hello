<template>
    <el-menu :default-active="onRoutes" class="el-menu-vertical" unique-opened background-color="#324157" text-color="#fff"
             active-text-color="#20a0ff" :collapse="collapse">
        <template v-for="item in items" v-perm="'system:module'">
            <template v-if="item.subs">
                <el-submenu :index="item.index">
                    <template slot="title"><i :class="item.icon"></i><span slot="title">{{ item.title }}</span>
                    </template>
                    <el-menu-item v-for="(subItem,i) in item.subs"
                                  @click="submenuClick(item,subItem,'/admin'+item.index+subItem.index)" :key="i"
                                  :index="'/admin'+item.index+subItem.index">{{ subItem.title }}
                    </el-menu-item>
                </el-submenu>
            </template>
            <template v-else>
                <el-menu-item :index="'/admin'+item.index" @click="menuClick(item,'/admin'+item.index)">
                    <i :class="item.icon"></i><span slot="title">{{ item.title }}</span>
                </el-menu-item>
            </template>
        </template>
    </el-menu>
</template>

<script>
    import manager from '@/service/manager'
    export default {
        props: {
            collapse: {
                type: Boolean,
                default: true
            }
        },
        data() {
            return {
                perms: [],
                items: [
                    {
                        icon: 'iconfont el-icon-cdz-gongzuotai',
                        index: '/welcome',
                        title: '充电通综合后台管理系统'
                    },
                    {
                        icon: 'iconfont el-icon-cdz-xitongguanli1',
                        index: '/system',
                        title: '系统管理',
                        subs: [
                            {
                                index: '/siteList',
                                title: '充电站列表'
                            },
                        ]
                    },
                    {
                        icon: 'iconfont el-icon-cdz-feiyongguanli',
                        index: '/price',
                        title: '充电费用管理',
                        subs: [
                            {
                                index: '/specialPrice',
                                title: '专用场站费用设置'
                            },
                            {
                                index: '/noSpecialPrice',
                                title: '非专用场站费用设置'
                            },
                            {
                                index: '/addPriceStrategy',
                                title: '添加计费'
                            },
                            {
                                index: '/poleElec',
                                title: '桩电量'
                            }
                        ]
                    },
                    {
                        icon: 'iconfont el-icon-cdz-charger',
                        index: '/siteManager',
                        title: '充电站管理',
                        subs: [
                            {
                                index: '/siteState',
                                title: '运营情况',
                            },
                            {
                                index: '/siteList',
                                title: '充电站列表'
                            },
                            {
                                index: '/order',
                                title: '充电订单',
                            }
                        ]
                    },
                    {
                        icon: 'iconfont el-icon-cdz-navicon-yjjy',
                        index: '/opinion',
                        title: '意见反馈',
                    }
                ]
            }
        },
        methods: {
            isPerms(perm) {
                if (this.perms.indexOf(perm) > -1) {
                    return true;
                } else {
                    return false;
                }
            },
            menuClick(item, index) {
                let breadcrumbs = [];
                breadcrumbs.push(item.title);
                var param = {
                    item: item,
                    index: index,
                    breadcrumbs: breadcrumbs
                }
                this.$emit('menuClk', param);
            },
            submenuClick(item, subItem, index) {
                let breadcrumbs = [];
                breadcrumbs.push(item.title);
                breadcrumbs.push(subItem.title);
                console.log("submenuClick")
                var param = {
                    item: subItem,
                    index: index,
                    breadcrumbs: breadcrumbs
                }
                this.$emit('menuClk', param);
            }
        },
        computed: {
            onRoutes() {
                return this.$route.path;
            }
        },
        mounted() {
            let item = {
                icon: 'el-icon-ysb-welcome',
                index: '/welcome',
                title: '工作台'
            };
            var param = {
                item: item,
                index: '/admin/welcome',
                breadcrumbs: ['工作台']
            }
            this.$emit('menuClk', param);
        }
    }
</script>

<style>
    .el-menu--inline .el-menu-item {
        padding-left: 40px !important;
    }
    .el-menu {
        border: none;
    }

    .el-menu-vertical:not(.el-menu--collapse) {
        width: 240px;
    }
</style>
