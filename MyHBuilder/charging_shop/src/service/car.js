/**
 * 登录或登出相关
 * */
import bs from '@/service/base'

let url="/car"

export default{
	/**
	 * 登录
	 * */
	getPage(param){
		return bs.http.body(url+'/page',param);
	},
	/**获得其他详情数据*/
	getCarDetail(param){
		return bs.http.post(url + '/detail/one',param);
	},
	getCarInsur(param){
		return bs.http.post(url + '/insur/one',param);
	},
	getCarMile(param){
		return bs.http.post(url + '/getCarMile',param);  //  没有找到
	},
	/**更新其他数据**/
	updateCarDetail(param){
		return bs.http.body(url + '/detail/update',param);
	},
	updateCarMile(param){
		return bs.http.body(url + '/updateCarMile',param);  // 没有找到
	},
	getDel(param){
		return bs.http.post(url+'/del',param);
	},
	upCar(param){
		return bs.http.body(url+'/base/update',param);
	},
	addCar(param){
		return bs.http.body(url+'/addAll',param);
	},
	
	// 获得车辆信息  --添加司机车辆
	getCarMessage(param){
		return bs.http.post(url + '/vehicleNo/one',param);
	},
	getCarInsurPage(param){
		return bs.http.body(url + '/insur/page',param);
	},
	//保险相关
	updateCarInsur(param){
		return bs.http.body(url + '/insur/update',param);
	},
	addCarInsur(param){
		return bs.http.body(url + '/insur/add',param); // 没有找到
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