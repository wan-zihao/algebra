import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import { postRequest, postRequest2, getRequest, putRequest, deleteRequest } from './utils/api'

Vue.use(ElementUI)

Vue.config.productionTip = false
Vue.prototype.getRequest = getRequest
Vue.prototype.postRequest = postRequest
Vue.prototype.postRequest2 = postRequest2
Vue.prototype.putRequest = putRequest
Vue.prototype.deleteRequest = deleteRequest

new Vue({
  router,
  store,
  render: h => h(App),
  mounted () {
    // 在页面加载时读取sessionStorage里的状态信息
    if (sessionStorage.getItem('store')) {
      this.$store.replaceState(Object.assign({}, this.$store.state, JSON.parse(sessionStorage.getItem('store'))))
    }

    // 在页面刷新时将vuex里的信息保存到sessionStorage里
    window.addEventListener('beforeunload', () => {
      sessionStorage.setItem('store', JSON.stringify(this.$store.state))
    })
  }
}).$mount('#app')
