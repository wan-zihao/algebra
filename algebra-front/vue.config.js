const proxyObj = {}

proxyObj['/'] = {
  // 目标地址
  target: 'http://localhost:8081',
  // 发送请求会被设置为target
  changeOrigin: true,
  // 重写请求地址    这里没有改写
  pathRewrite: {
    '^/api': '/'
  }
}

module.exports = {
  devServer: {
    host: '0.0.0.0',
    port: 8080,
    proxy: proxyObj
  }
}
