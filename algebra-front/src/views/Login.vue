<template>
  <div class="loginMaxContainer">
  <div class="loginContainer">
    <el-link id="register" type="primary" @click="jump('/register')">&lt;&lt;用户注册</el-link>
    <el-link id="admin" type="primary" @click="jump('/adminLogin')">后台登录&gt;&gt;</el-link>
    <h2 class="loginTitle">系统登录</h2>
    <el-form :rules="rules" ref="loginForm" :model="loginForm">
      <el-form-item prop="username">
        <el-input prefix-icon="el-icon-user" type="text" auto-complete="false" v-model="loginForm.username" placeholder="请输入用户名"></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input prefix-icon="el-icon-lock" type="password" auto-complete="false" v-model="loginForm.password" placeholder="请输入密码" show-password></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" style="width: 100%" @click="submitLogin">登录</el-button>
      </el-form-item>
    </el-form>
  </div>
  </div>
</template>

<script>
const querystring = require('querystring')

export default {
  name: 'Login',
  data () {
    return {
      loginForm: {
        username: '',
        password: ''
      },
      rules: {
        username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
        password: [{ required: true, message: '请输入密码', trigger: 'blur' }]
      }
    }
  },
  methods: {
    openFullScreen () {
      const loading = this.$loading({
        lock: true,
        text: 'Loading',
        spinner: 'el-icon-loading',
        background: 'rgba(0, 0, 0, 0.7)'
      })
      setTimeout(() => {
        loading.close()
      }, 750)
    },
    submitLogin () {
      this.$refs.loginForm.validate((valid) => {
        // 开启加载
        this.openFullScreen()
        if (valid) {
          // 登录请求
          this.postRequest('/user/login', querystring.stringify(this.loginForm)).then(resp => {
            // 判断响应数据是否存在
            if (resp) {
              // 存储用户token
              const tokenStr = resp.obj.tokenHead + resp.obj.token
              window.sessionStorage.setItem('tokenStr', tokenStr)
              setTimeout(() => {
                this.$router.push('/indexHome')
              }, 750)
            }
          })
        } else {
          this.$message.error('请正确填写信息哦')
          return false
        }
      })
    },
    jump (target) {
      this.$router.push(target)
    }
  }
}
</script>

<style scoped>
#register {
  margin: 20px 0 0 0;
  float: left;
}
#admin {
  margin: 20px 0 0 0;
  float: right;
}

.loginContainer {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 0 auto;
  width: 400px;
  padding: 15px 35px 15px 35px;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
  position: relative;
  top: 150px;
}

.loginTitle {
  color: #048BE5;
  margin: 0px auto 30px auto;
  text-align: center;
}

.loginMaxContainer{
  position: relative;
  left: 0;
  top: 0;
  margin: 0 auto;
  width: 100%;
  height: 900px;
  padding: 0;
  background-size:cover;
  background-image: url("../assets/login-background.jpg");
}
</style>
