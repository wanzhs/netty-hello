/**
 * 常用的方法集合
 * */
export default{
	/**
	 * 验证字母和数字任意字符串
	 * **/
	regLetterOrNum(str,min,max){
		var reg_str="/^[a-zA-Z_0-9]{"+min+","+max+"}$/";
		if(str.match(eval(reg_str))!=null){
			return true;
		}
		return false;
	},
	/**
	 * 验证必须为正整数
	 * */
	regNumber(str){
		let reg = new RegExp(/^[0-9]*[1-9][0-9]*$/);
		if(!reg.test(str)){
			return false;
		}
		return true;
	},
	/**
	 *  验证电话
	 * */
	regPhone(phone){
		let reg = new RegExp(/^1[3456789]\d{9}$/);
		if(!reg.test(phone)) {
			return false;
		}
		return true;
	},
	/**
	 * 匹配中文或英文
	 * */
	regWordAndCn(str,min,max){
		var reg_str="/^([\u4E00-\uFA29]|[\uE7C7-\uE7F3]|[a-zA-Z]){"+min+","+max+"}$/";
		if(str.match(eval(reg_str))!=null){
			return true;
		}
		return false;
	},
	/**
	 *  uuid
	 * **/
	guid() {
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
        var r = Math.random()*16|0, v = c == 'x' ? r : (r&0x3|0x8);
        return v.toString(16);
    });
	},
}
