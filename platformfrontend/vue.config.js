module.exports = {
  devServer: {
    proxy: {
      // Proxy all requests starting with '/api'
      '/api': {
        // Target URL for the proxy
        target: 'http://localhost:8080',
        // Change the origin of the request to match the target URL
        changeOrigin: true,
        // Rewrite the cookie path to remove the leading '/'
        cookiePathRewrite:{
          '^/':''
        }
      }
    }
  }
}