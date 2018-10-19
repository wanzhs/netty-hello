import qs from 'querystring'
import axios from 'axios'
// axios.defaults.baseURL = 'http://192.168.2.26:8011'
var HttpPlugin = {};

HttpPlugin.install = function(Vue, options) {
	Vue.prototype.http = {
			get(url, param = "") {
				//如果参数为空 自动加上一个时间
				if(param == '') {
					param = {
						'time': new Date().getTime() + ""
					}
				}
				return axios.get(url, {
					params: param
				});
			},
			post(url, param = '') {
				return  axios.post(url,param);
				//return axios.post(url, qs.stringify(param));
			},
			body(url, param) {
				return axios.post(url, param);
			},
			//body参数
			file(url, param, uploadProgress = (p) => {}) {
				let formdata = new FormData();
				for(let item in param) {
					formdata.append(item, param[item])
				}
				return axios.post(url, formdata, {
					headers: {
						'Content-Type': 'multipart/form-data'
					},
					onUploadProgress: function(progressEvent) {
						uploadProgress(progressEvent);
					}
				});
			}
		}
}

export default HttpPlugin
