import axios from 'axios'
import { Message } from 'element-ui'
import router from '../router'

// 请求拦截器
axios.interceptors.request.use(config => {
  // 判断token是否存在，存在则携带token
  if (window.sessionStorage.getItem('tokenStr')) {
    config.headers.Authorization = window.sessionStorage.getItem('tokenStr')
  }
  return config
}, error => {
  console.log(error)
})

// 响应拦截器
axios.interceptors.response.use(success => {
  // 进入这里说明至少服务器已经访问到了

  if (success.status && success.status === 200) {
    // 业务逻辑错误
    // 判断响应码
    if (success.data.code === 500 || success.data.code === 401 || success.data.code === 403) {
      Message.error({ message: success.data.message })
      return
    }
    if (success.data.message) {
      Message.success({ message: success.data.message })
    }
  }
  return success.data
}, error => {
  // 进入这里说明服务器都没访问到

  if (error.response.status === 504 || error.response.status === 404) {
    Message.error({ message: '服务器挂了，呜呜呜' })
  } else if (error.response.status === 403) {
    Message.error({ message: '权限不足，请联系管理员' })
  } else if (error.response.status === 401) {
    Message.error({ message: '尚未登录，请登录' })
    router.replace('/')
  } else {
    if (error.response.data.message) {
      Message.error({ message: error.response.data.message })
    } else {
      Message.error({ message: '出现未知错误' })
    }
  }
})

const base = ''

const querystring = require('querystring')
// 请求封装
export const postRequest = (url, params) => {
  return axios({
    method: 'post',
    url: `${base}${url}`,
    data: params
  })
}

export const postRequest2 = (url, params) => {
  return axios({
    method: 'post',
    url: `${base}${url}`,
    data: querystring.stringify(params)
  })
}

export const getRequest = (url, params) => {
  return axios({
    method: 'get',
    url: `${base}${url}` + '?' + querystring.stringify(params)
  })
}

export const putRequest = (url, params) => {
  return axios({
    method: 'put',
    url: `${base}${url}`,
    params: params
  })
}

export const deleteRequest = (url, params) => {
  return axios({
    method: 'delete',
    url: `${base}${url}` + '?' + querystring.stringify(params)
  })
}
