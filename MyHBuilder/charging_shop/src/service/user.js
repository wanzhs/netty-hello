/**
 * 登录或登出相关
 * */
import bs from '@/service/base'

let url="/user"


export default{
	getUserListByPage(param){
		return bs.http.body(url+'/getPage',param);
	},
	
	/**
	 * 获取用户评价
	 */
	getUserRateMPage(param){
		return bs.http.body(url+'/rate/getPage',param);
	},
	
	/**
	 * 获取投诉信息
	 */
	getMPage(param){
		return bs.http.body(url+'/getMPage',param);
	},
	
	/**
	 * 投诉结果处理
	 */
	updateComplaintResult(param){
		return bs.http.body(url+'/complaint/update/rsult',param);
	},
/**
	 * 刷新登录
	 * */
	refreshToken(username,token,refreshToken){
		return bs.http.post(url+"/refresh/token",{
			userName:username,
			token:token,
			refreshToken:refreshToken
		});
	}
}