import Vue from 'vue'

function sendMessage(msg, type) {
	Vue.prototype.$message({
		showClose: false,
		message: msg,
		type: type
	});
}

var MsgPlugin = {};

var msg={
		success(msg = '操作成功') {
			sendMessage(msg, 'success');
		},
		fail(msg = '操作失败') {
			sendMessage(msg, 'error');
		}
	}

MsgPlugin.install = function(Vue, options) {
	Vue.prototype.msg = msg
}

MsgPlugin.static= msg

export default MsgPlugin