<template>
	<div class="clearfix" style="min-width: 1200px;height: 100%;">
		<div class="left-menu" style="height: 100%;">
			<v-menu :collapse="isCollapse" @menuClk="addTab"></v-menu>
		</div>
		<div class="content">
			<div class="header">
				<ul class="header_left">
					<li>
						<svg @click="expandClick" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1691" xmlns:xlink="http://www.w3.org/1999/xlink" width="64" height="64" class="hamburger" :class="{'is-active':!isCollapse}">
							<path d="M966.8023 568.849776 57.196677 568.849776c-31.397081 0-56.850799-25.452695-56.850799-56.850799l0 0c0-31.397081 25.452695-56.849776 56.850799-56.849776l909.605623 0c31.397081 0 56.849776 25.452695 56.849776 56.849776l0 0C1023.653099 543.397081 998.200404 568.849776 966.8023 568.849776z"></path>
							<path d="M966.8023 881.527125 57.196677 881.527125c-31.397081 0-56.850799-25.452695-56.850799-56.849776l0 0c0-31.397081 25.452695-56.849776 56.850799-56.849776l909.605623 0c31.397081 0 56.849776 25.452695 56.849776 56.849776l0 0C1023.653099 856.07443 998.200404 881.527125 966.8023 881.527125z"></path>
							<path d="M966.8023 256.17345 57.196677 256.17345c-31.397081 0-56.850799-25.452695-56.850799-56.849776l0 0c0-31.397081 25.452695-56.850799 56.850799-56.850799l909.605623 0c31.397081 0 56.849776 25.452695 56.849776 56.850799l0 0C1023.653099 230.720755 998.200404 256.17345 966.8023 256.17345z"></path>
						</svg>
					</li>
					<li>
						<el-breadcrumb separator="/">
							<el-breadcrumb-item>
								<a @click="goWelcome">工作台</a>
							</el-breadcrumb-item>
							<el-breadcrumb-item v-for="(item,index) in breadcrumbs" v-if="item!='工作台'" :key="index">{{item}}</el-breadcrumb-item>
						</el-breadcrumb>
					</li>
				</ul>
				<ul class="header_right">
					<li>
						<el-dropdown trigger="click" class="el-dropdown-link" @command="handleCommand">
                            <el-tag>
								{{managerName}}
                            </el-tag>
							<el-dropdown-menu slot="dropdown">
								<el-dropdown-item command="loginout">退 出</el-dropdown-item>
							</el-dropdown-menu>
						</el-dropdown>
					</li>
				</ul>
			</div>
			<div class="header_nav" @click.stop.prevent="mouseRightClick">
				<el-tabs v-model="currTabIndex" type="border-card" @tab-remove="removeTab" @tab-click="tabChange" lazy>
					<el-tab-pane v-for="(item, index) in tablist" :key="item.index" :label="item.title" :name="item.index" :closable="index>0">
					</el-tab-pane>
				</el-tabs>
			</div>
            <!--标签栏右键菜单开始-->
            <div class="closeTip" style="text-align: right;padding: 5px;">
                <p style="cursor: pointer; text-align: right; padding-right: 3px;" @click="closeMenu">X</p>
                <div style="text-align: center;">
                    <el-button type="text" size="mini" @click="closeAllTab">关闭所有</el-button>
                </div>
            </div>
            <!--标签栏右键菜单结束-->
			<div class="main_content" :style="{height: htmlHeight +'px'}">
				<keep-alive>
					<transition name="move" mode="out-in">
						<router-view></router-view>
					</transition>
				</keep-alive>
			</div>
		</div>
		<!--<div style="text-align: center;font-size: 16px;line-height: 5vh;">Power By WYC</div>-->
	</div>
</template>

<script>
	import vMenu from '@/components/common/menu'
	import account from '@/service/account'
	import manager from '@/service/manager'
	export default {
		components: {
			vMenu
		},
		data() {
			return {
				isCollapse: false,
				currTabIndex: '',
				breadcrumbs:[],
				tablist: [],
				managerName:"",
          		company:{},
          		interval: '',
				htmlHeight: 0,
			}
		},
        created() {
            let wHeight = document.documentElement.offsetHeight;
            //this.$store.store.commit('updatePageSize_mutations', wHeight); // 存入store
            this.htmlHeight = wHeight - 110;
			window.addEventListener('load',()=>{
                if (localStorage.getItem('currentPage')) {
					this.$router.push({path: localStorage.getItem('currentPageCopy')});
                }
			},false);
        },
		methods: {
		    randomMethod(min,max) {
		      	return Math.random() * (max - min ) + min;
			},
		    randomColor() {
		        let [r, g, b, a] = [parseInt(this.randomMethod(0,255)),parseInt(this.randomMethod(0,255)),parseInt(this.randomMethod(0,255)),this.randomMethod(0,1)];
		        console.log(1)
		        document.getElementsByClassName('main_content')[0].style.backgroundColor = "rgba("+[r, g, b, a].join(",")+")";
			},
			goWelcome(){
				let item={
						icon: 'el-icon-ysb-welcome',
						index: '/welcome',
						title: '工作台'
				};
				var param={
					item:item,
					index:'/admin/welcome',
					breadcrumbs:['工作台']
				}
				this.addTab(param);
			},
			tabChange(item){
				this.tablist.find(curr=>{
					if(curr.name==item.name){
						this.breadcrumbs=curr.breadcrumbs;
						return false;
					}
				})
				this.$router.push(item.name);
			},
			addTab(param) {
				var mItem=param.item;
				var index=param.index;
				var breadcrumbs=param.breadcrumbs;
				let hasItem = false;
				this.tablist.find(item => {
					if(item.index == index) {
						hasItem = true;
						return;
					}
				});
				if(!hasItem) { //菜单项第一次添加
					// this.tablist.splice(1,1,{ //只允许添加一个面包屑
					// 	title: mItem.title,
					// 	index: index,
					// 	name: index,
					// 	breadcrumbs:breadcrumbs
					// });
					this.tablist.push({
                        title: mItem.title,
                        index: index,
                        name: index,
                        breadcrumbs:breadcrumbs
                    })
				}
				this.currTabIndex=index;
				this.breadcrumbs=breadcrumbs;
				this.$router.push(index);
			},
			removeTab(targetName) {
				let tabs = this.tablist;
				let activeName = this.currTabIndex;
				let breadcrumbs=[];
				let path='';
				if(activeName === targetName) {
					tabs.forEach((tab, index) => {
						if(tab.name === targetName) {
							let nextTab = tabs[index + 1] || tabs[index - 1];
							if(nextTab) {
								activeName = nextTab.name;
								breadcrumbs= nextTab.breadcrumbs;
								path=nextTab.index;
							}
						}
					});
				}
				this.currTabIndex = activeName;
				this.breadcrumbs=breadcrumbs;
				this.tablist = tabs.filter(tab => tab.name !== targetName);
				if(path!=''){
					this.$router.push(path);
				}else{
					this.goWelcome();
				}
				
			},
			handleCommand(command) {
				if(command == 'loginout') {

					this.$router.push('/login');
				}
			},
			expandClick() {
				this.isCollapse = !this.isCollapse;
			},
			handleOpen(key, keyPath) {
				console.log(key, keyPath);
			},
			handleClose(key, keyPath) {
				console.log(key, keyPath);
			},
			handleCommand(command) {
				if(command == 'loginout') {
					this.$router.push('/login');
				}
			},
            // 鼠标右键点击弹出菜单
            mouseRightClick(e) {

            },
            // 关闭右键菜单
            closeMenu() {
			    document.getElementsByClassName('closeTip')[0].style.display = 'none';
            },
            closeAllTab() {
			    this.tablist.splice(1,this.tablist.length);
			    this.$router.push({path:'/admin'});
			    document.getElementsByClassName('closeTip')[0].style.display = 'none';
                document.getElementById('tab-/admin/welcome').setAttribute('aria-selected','true');
                document.getElementById('tab-/admin/welcome').className += 'is-active';
            },
			refreshToken() {//刷新token
				var user = JSON.parse(localStorage.getItem(this.cont.LOCAL_MANAGER_KEY));
				account.refreshToken(user.adminName, user.token, user.refreshToken).then(res => {
					if(res.data.status == this.cont.HTTP_SUCCESS) {
						let token = res.data.data.token;
						let refreshToken = res.data.data.refreshToken;
						user.token = token;
						user.refreshToken = refreshToken;
						localStorage.setItem(this.cont.LOCAL_MANAGER_KEY, JSON.stringify(user));
						console.log("refreshToken已经刷新！！！");
					} else {
						this.msg.fail("登录超时");
						this.$router.push('/login');
					}
				}).catch(err => {
					this.msg.fail("登录超时");
					this.$router.push('/login');
				});
			}
		},

		mounted() {
      		var user = JSON.parse(localStorage.getItem(this.cont.LOCAL_MANAGER_KEY));
      		this.managerName=user.adminName;
      		//定时刷新token功能
			// this.interval = setInterval(this.refreshToken, this.cont.REFRESH_TIME);
			// //验证token是否失效
			// manager.checkToken().then(res=>{
			//     console.log(res);
			// });

            document.getElementsByClassName('header_nav')[0].addEventListener('contextmenu', function(e) {
                e.preventDefault();
                e.stopPropagation();
                document.getElementsByClassName('closeTip')[0].style.cssText = "display: block; z-index: 999;top:"+ e.clientY + "px;" + "left:" + e.clientX + "px;";
            }, false);
		},
		beforeDestroy() {
			console.log("stop now!!!");
		//停止token刷新
			//clearInterval(this.interval);
		},
	}
</script>


<style>
	@import "../../static/css/home.css";
	/*IE10,IE11,IE12*/
	.scroll_content{
		-ms-scroll-chaining: chained;
		-ms-overflow-style: none;
		-ms-content-zooming: zoom;
		-ms-scroll-rails: none;
		-ms-content-zoom-limit-min: 100%;
		-ms-content-zoom-limit-max: 500%;
		-ms-scroll-snap-type: proximity;
		-ms-scroll-snap-points-x: snapList(100%, 200%, 300%, 400%, 500%);
		overflow: auto;
	}

	.left-menu, main-content {
		/*隐藏滚动条，当IE下溢出，仍然可以滚动*/
		-ms-overflow-style:none;
		/*火狐下隐藏滚动条*/
		-moz-overflow-style: none;
		overflow:-moz-scrollbars-none;
	}
	/*.left-menu::-webkit-scrollbar, main-content::-webkit-scrollbar {*/
		/*display: none;*/
	/*}*/
	/*滚动条样式*/
	*::-webkit-scrollbar {/*滚动条整体样式*/
		width: 0;     /*高宽分别对应横竖滚动条的尺寸*/
		height: 12px;
	}
	*::-webkit-scrollbar-thumb {/*滚动条里面小方块*/
		/*border-radius: 5px;*/
		-webkit-box-shadow: inset 0 0 5px #324157;
		background: rgb(57, 57, 87);
	}
	*::-webkit-scrollbar-track {/*滚动条里面轨道*/
		-webkit-box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.09);
		border-radius: 0;
		background: #FFFFFF;
	}
    .closeTip {
        display: none;
        position: absolute;
        width: 80px;
        height: 60px;
        border-radius: 3px;
        box-shadow: 0 0 5px #808080;
        background-color: #FFFFFF;
    }
</style>