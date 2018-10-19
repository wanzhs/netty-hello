var ContPlugin={};

/**
 * 开发服务器
 * */
let dev="http://localhost:9999"


let contstant= {
  	HTTP_SUCCESS:0,
	//后台管理员存储信息的key
	LOCAL_MANAGER_KEY:"manager",
	//REFRESH_TOKEN超时时间 1小时 刷新时间50分钟
	REFRESH_TIME:1000*60*30,
	//分页数组
	PAGE_SIZES:[10,20,50]
  }

//图片服务器
/*let img=dev+"/img"
let audio=dev+"/audio"
let execl=dev+'/execl'*/

ContPlugin.install = function (Vue, options) {
  Vue.prototype.cont = contstant;
}


//main.js 中使用的属性
ContPlugin.static=contstant

export default ContPlugin
