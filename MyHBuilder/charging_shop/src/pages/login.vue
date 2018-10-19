<template>
	<div class="login-wrap">
		<div class="ms-title">充电通综合后台管理系统</div>
		<div class="ms-login">
			<el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="0px" class="demo-ruleForm">
				<el-form-item prop="username">
					<el-input v-model="ruleForm.username" placeholder="请输入用户名"></el-input>
				</el-form-item>
				<el-form-item prop="password">
					<el-input type="password" placeholder="请输入密码" v-model="ruleForm.password" @keyup.enter.native="submitForm('ruleForm')"></el-input>
				</el-form-item>
				<div class="login-btn">
					<el-button type="primary" @click="submitForm('ruleForm')" :loading="ruleForm.isLoading" :disabled="ruleForm.isable">登录</el-button>
				</div>
				<p style="font-size:12px;line-height:30px;color:#999;">Tips : 忘记密码请联系后台管理员。</p>
			</el-form>
		</div>
		<div class="ms-login-copy"></div>
	</div>
</template>

<script>
	import account from "@/service/account"
	import crypto from 'crypto'
	import axios from 'axios'
	export default {
		data() {
			return {
				ruleForm: {
					username: '',
					password: '',
					isLoading: false,
					isable: false
				},
				rules: {
					username: [{
							required: true,
							message: '请输入用户名',
							trigger: 'blur'
						},
						{
							min: 5,
							max: 12,
							message: '长度在 6 到 12 个字符',
							trigger: 'blur'
						}
					],
					password: [{
							required: true,
							message: '请输入密码',
							trigger: 'blur'
						},
						{
							min: 6,
							max: 12,
							message: '长度在 6 到 12 个字符',
							trigger: 'blur'
						}
					]
				}
			}
		},
		methods: {
			submitForm(formName) {
				const self = this;
				self.$refs[formName].validate((valid) => {
					if(valid) {
						this.ruleForm.isLoading = true;
						this.ruleForm.isable = true;
						// var md5 = crypto.createHash('md5');
    					// var md5Pwd = md5.update(this.ruleForm.password).digest('hex');
                        this.$router.push("/admin");
						account.login(this.ruleForm.username,this.ruleForm.password).then(res => {
							if(res.data.code == this.cont.HTTP_SUCCESS) {
								this.msg.success(res.data.message);
								//成功后返回
								localStorage.setItem(this.cont.LOCAL_MANAGER_KEY, JSON.stringify(res.data.data));
								this.$router.push("/admin");
							} else {
								this.msg.fail(res.data.message);
							}
						}).catch((err) => {
							console.log(err)
						}).then(() => {
							this.ruleForm.isLoading = false;
							this.ruleForm.isable = false;
						});
					} else {
						this.msg.fail("请完善登录信息");
						return false;
					}
				});
			}
		},
		mounted() {

		}
	}
</script>

<style scoped>
	@-webkit-keyframes bluePulse {
		from {
			opacity: 1;
		}
		to {
			opacity: 0;
		}
	}
	@-ms-keyframes bluePulse {
		from {
			opacity: 1;
		}
		to {
			opacity: 0;
		}
	}
	@-moz-keyframes bluePulse {
		from {
			opacity: 1;
		}
		to {
			opacity: 0;
		}
	}
	@keyframes bluePulse {
		from {
			opacity: 1;
		}
		to {
			opacity: 0;
		}
	}
	@-webkit-keyframes blueTextShadow {
		from {
			text-shadow: 2px 2px 10px rgba(45, 174, 191, 0);
		}
		to {
			text-shadow: 2px 2px 10px rgba(45, 174, 191, 1);
		}
	}
	@-ms-keyframes blueTextShadow {
		from {
			text-shadow: 2px 2px 10px rgba(45, 174, 191, 0);
		}
		to {
			text-shadow: 2px 2px 10px rgba(45, 174, 191, 1);
		}
	}
	@-moz-keyframes blueTextShadow {
		from {
			text-shadow: 2px 2px 10px rgba(45, 174, 191, 0);
		}
		to {
			text-shadow: 2px 2px 10px rgba(45, 174, 191, 1);
		}
	}
	@keyframes blueTextShadow {
		from {
			text-shadow: 2px 2px 10px rgba(45, 174, 191, 0);
		}
		to {
			text-shadow: 2px 2px 10px rgba(45, 174, 191, 1);
		}
	}
	.login-wrap {
		position: relative;
		width: 100%;
		height: 100%;
		background: #324157;
	}
	
	.ms-title {
		position: absolute;
		top: 50%;
		width: 100%;
		margin-top: -190px;
		text-align: center;
		text-shadow: 2px 2px 10px #2daebf;
		animation: blueTextShadow 1s infinite alternate;
		font-size: 30px;
		color: #fff;
	}
	
	.ms-login {
		position: absolute;
		left: 0;
		right: 0;
		bottom: 0;
		top: 0;
		width: 300px;
		height: 250px;
		margin: auto;
		padding: 40px;
		border-radius: 5px;
		/*background: #fff;*/
		z-index: 1;
	}
	.ms-login-copy {
		position: absolute;
		left: 0;
		right: 0;
		bottom: 0;
		top: 0;
        width: 300px;
        height: 250px;
		margin: auto;
		border-radius: 5px;
        box-shadow: 0 0 5px #2daebf;
        /*animation-iteration-count: infinite;*/
        /*animation-direction:alternate;*/
        /*animation-name: bluePulse;*/
        /*animation-duration: 2s;*/
	}
	
	.login-btn {
		text-align: center;
	}
	
	.login-btn button {
		width: 100%;
		height: 36px;
	}
	.el-input {
		vertical-align: middle;
		line-height: 100%;
	}
	.el-input__inner {
		vertical-align: middle;
		line-height: 100%;
	}
</style>