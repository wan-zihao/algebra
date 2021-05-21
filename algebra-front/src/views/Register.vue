<template>
  <div class="loginMaxContainer">
    <div class="loginContainer">
      <el-link id="userLogin" type="primary" @click="jump('/')">用户登录&gt;&gt;</el-link>
      <el-link id="adminLogin" type="primary">&lt;&lt;后台登录</el-link>
      <h2 class="loginTitle">用户注册</h2>
      <el-form :rules="rules" ref="loginForm" :model="loginForm">
        <el-form-item prop="username">
          <el-input prefix-icon="el-icon-user" type="text" auto-complete="false" v-model="loginForm.username" placeholder="请输入用户名"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input prefix-icon="el-icon-lock" type="password" auto-complete="false" v-model="loginForm.password" placeholder="请输入密码" show-password></el-input>
        </el-form-item>
        <el-form-item prop="email">
          <el-input prefix-icon="el-icon-message" type="text" auto-complete="false" v-model="loginForm.email" placeholder="请输入邮箱"></el-input>
        </el-form-item>
        <el-form-item prop="code">
          <el-input prefix-icon="el-icon-lock" type="text" auto-complete="false" v-model="loginForm.code" placeholder="请输入验证码" style="width: 63%"></el-input>
          <el-button @click="mail" style="float: right">点击发送验证码</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" style="width: 100%" @click="submitRegister">注册</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>

const querystring = require('querystring')

export default {
  name: 'Register',
  data () {
    return {
      loginForm: {
        username: '',
        password: '',
        email: '',
        code: ''
      },
      rules: {
        username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
        password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
        email: [{ required: true, message: '请输入邮箱', trigger: 'blur' }],
        code: [{ required: false }]
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
    submitRegister () {
      this.$refs.loginForm.validate((valid) => {
        // 开启加载
        this.openFullScreen()
        if (valid) {
          // 注册请求
          this.postRequest('/register', querystring.stringify(this.loginForm)).then(resp => {
            // 判断响应数据是否存在
            if (resp) {
              setTimeout(() => {
                this.$router.push('/')
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
    },
    mail () {
      this.$refs.loginForm.validate((valid) => {
        if (valid) {
          // 邮件发送请求
          this.postRequest('/mail', querystring.stringify(this.loginForm))
        } else {
          this.$message.error('请正确填写信息哦')
          return false
        }
      })
    }
  }
}
</script>

<style scoped>
#adminLogin {
  opacity: 0;
  margin: 20px 0 0 0;
  float: left;
}

#userLogin {
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
  top: 100px;
}

.loginTitle {
  color: #048BE5;
  margin: 0px auto 30px auto;
  text-align: center;
}

.loginMaxContainer{
  position: relative;
  left: 0px;
  top: 0px;
  margin: 0 auto;
  width: 100%;
  height: 900px;
  padding: 0;
  background-size:cover;
  background-image: url("../assets/login-background.jpg");
}
</style>
