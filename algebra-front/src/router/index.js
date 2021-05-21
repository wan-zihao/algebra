import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    component: () => import('../views/Login'),
    hidden: true
  },
  {
    path: '/home',
    component: () => import('../views/Home'),
    hidden: true,
    children: [
      {
        path: '/indexHome',
        component: () => import('../components/home/IndexHome')
      },
      {
        path: '/algorithm',
        component: () => import('../components/home/Algorithm')
      },
      {
        path: '/map',
        component: () => import('../components/home/Map')
      },
      {
        path: '/question',
        component: () => import('../components/home/Question'),
        children: [
          {
            // markdown子组件 默认显示
            path: '/question',
            component: () => import('../components/question/Markdown')
          },
          {
            path: '/information',
            component: () => import('../components/question/Information')
          }
        ]
      },
      {
        path: '/suggest',
        component: () => import('../components/home/Suggest')
      }
    ]
  },
  {
    path: '/adminLogin',
    component: () => import('../views/AdminLogin'),
    hidden: true
  },
  {
    path: '/register',
    component: () => import('../views/Register'),
    hidden: true
  },
  {
    path: '/admin-home',
    name: '系统功能',
    component: () => import('../views/AdminHome'),
    children: [
      {
        path: '/user-manage',
        name: '人员管理',
        component: () => import('../components/adminHome/UserManage')
      },
      {
        path: '/algorithm-manage',
        name: '算法管理',
        component: () => import('../components/adminHome/AlgorithmManage')
      },
      {
        path: '/question-manage',
        name: '问答管理',
        component: () => import('../components/adminHome/QuestionManage')
      }
    ]
  },
  {
    path: '/admin-home',
    name: '其他功能',
    component: () => import('../views/AdminHome'),
    children: [
      {
        path: '/log-manage',
        name: '日志查看',
        component: () => import('../components/adminHome/LogManage')
      }
    ]
  }
]

const router = new VueRouter({
  mode: 'hash',
  base: process.env.BASE_URL,
  routes
})

export default router
