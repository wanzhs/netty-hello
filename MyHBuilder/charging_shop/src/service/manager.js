/**
 * 管理员
 * */
import bs from '@/service/base'

let url="/manager"

export default{
	getLocal(){//获取localstorage中用户信息
		let user=JSON.parse(localStorage.getItem(bs.cont.LOCAL_MANAGER_KEY));
		if(!user){
			bs.msg.fail("登录失效，重新登录");
			bs.router.push("/login");
		}else{
			return user;
		}
	},
	checkToken(){
		return bs.http.get(url+"/token/check");
	}
}