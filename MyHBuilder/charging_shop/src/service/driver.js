/**
 * 登录或登出相关
 * */
import bs from '@/service/base'

let url="/driver"

export default{
	getPage(param){
		return bs.http.body(url+'/page',param); 
	},
	getAreaList(){
		return bs.http.post('/area/getCity','');
	},
	/**获得其他详情数据*/
	getDriverDetail(param){
		return bs.http.post(url + '/detail/driverId',param);
	},
	getDriverLicense(param){
		return bs.http.post(url + '/license/driverId',param);
	},
	getDriverCertificate(param){
		return bs.http.post(url + '/certificate/driverId',param);
	},
	/**更新其他数据**/
	updateDriver(param){
		return bs.http.body(url + '/base/update',param);
	},
	updateDriverDetail(param){
		return bs.http.body(url + '/detail/update',param);
	},
	updateDriverLicense(param){
		return bs.http.body(url + '/license/update',param);
	},
	updateDriverCertificate(param){
		return bs.http.body(url + '/certificate/update',param);
	},
	/**添加所有数据 */
	addDriver(param){
		return bs.http.body(url + '/add',param);
	},
	delDriver(param){
		return bs.http.post(url + '/del',param);
	},
	getDriverCarPage(param){
		return bs.http.body(url + '/car/page',param)
	},
	//司机车辆
	delDriverCar(param){
		return bs.http.post(url + '/car/del',param)
	},
	getCarList(param){
		return bs.http.post(url + '/car/list',param)
	},
	addDriverCar(param){
		return bs.http.body(url+ '/car/add',param)
	},
	/**司机培训相关*/
	getEducateList(param){
		return bs.http.body(url+ '/educate/list',param)
	},
	updateEducate(param){
		return bs.http.body(url+ '/educate/update',param)
	},
	addEducate(param){
		return bs.http.body(url+ '/educate/add',param)
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
