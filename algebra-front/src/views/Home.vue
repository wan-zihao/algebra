<template>
  <div>
    <el-container>
      <el-header>
        <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect" router>
          <el-menu-item index="/indexHome">首页</el-menu-item>
          <el-menu-item index="/question">学习论坛</el-menu-item>
          <el-menu-item index="/algorithm">线代算法</el-menu-item>
          <el-menu-item index="/map">思维导图</el-menu-item>
          <el-menu-item index="/suggest">学习建议</el-menu-item>
          <el-menu-item id="user" :index=userLink><b>{{userMsg}}</b></el-menu-item>
        </el-menu>
      </el-header>
      <el-main>
        <router-view/>
      </el-main>
    </el-container>
  </div>
</template>

<script>
export default {
  name: 'Home',
  created () {
    this.getRequest('/user/info', null).then(resp => {
      if (resp.obj != null) {
        this.userMsg = resp.obj.username
        this.$store.commit('modifyCurrentUserId', resp.obj.userId)
      } else {
        this.userMsg = '未登录'
        this.userLink = '/'
      }
    })
  },
  data () {
    return {
      activeIndex: '1',
      activeIndex2: '1',
      userMsg: '',
      userLink: ''
    }
  },
  methods: {
    handleSelect (key, keyPath) {
      console.log(key, keyPath)
    }
  }
}
</script>

<style scoped>
#user {
  font-size: 24px;
  color: black;
  float: right;
}
</style>
