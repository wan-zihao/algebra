# 简介

> 技术栈 ：springboot + vue

# 整体架构

## 数据库设计

表的设计

* admin
  * admin_id					char（6） 		                       主键	管理员ID
  * password                   varchar（20）                        密码

* user
  * user_id						int    		                            	主键
  * user_name                 varchar（20）                       用户名
  * password                   varchar（20）                        密码  

* algorithm(由于可能是跟系统表重名，数据库中名为file)
  * algorithm_id  			int 											主键
  * uuid                            varchar(200)                            文件的唯一标识
  * user_id                                                                         算法上传用户
  
  * algorithm_introduction  varchar (200)                     文件简介
* message
  * message_id				int											主键
  * message_content      varchar（20）                       消息内容
  * time                             timestamp                             消息发生时间

* question
  * question_id				int											主键
  * question_content     varchar（50）                       提问内容
  * user_id                       int                                             问题发起者
  * time                             timestamp                             提问时间

* answer
  * question_id				int   		                       		   主键
  * answer_id                   int                                             回复消息的id
  * answer_content         varchar（1000）                    回复答案
  * user_id                        int                                             回复者的id
  * time                             timestamp                               回复时间

# 问题记录

## 已解决

### axios使用post请求后端接口，springmvc无法接收到axios的请求参数

由请求头Content-type引起，axios默认此请求头为application/json，所以后台接收不到

在给axios传递参数时，使用node自带的querystring改造参数

```js
//注意要先导入
const querystring = require('querystring')
postRequest('/user/login', querystring.stringify(this.loginForm)).then()
```

### axios同步请求

使用async关键字和await关键字

```javascript
async modifyUser () {
      await this.putRequest('/user/' + this.form.updateUserId, this.form)
      await this.getRequest('/user', this.table).then(resp => {
        this.table.total = resp.obj.total
        this.table.tableData = resp.obj.records
        this.form.username = ''
        this.form.password = ''
        this.form.email = ''
      })
      this.dialogFormVisible = false
    }
```

### SpringBoot无法解析put请求携带的参数

配置put请求解析器

```java
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    //跨域配置
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        /**
         * 1. 允许访问路径
         * 2. 添加跨域请求来源
         * 3. 允许跨域的方法
         * 4. 允许携带信息
         * 5. 最大响应时间
         */

        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }

    //put请求解析配置
    @Bean
    public FormContentFilter httpPutFormContentFilter() {
        return new FormContentFilter();
    }
}
```

### vuex中保存的数据在页面刷新之后丢失

==解决方法==

在main.js文件中加入

```javascript
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
```

## 未解决

### jwt登录过滤器

使用springsecurity+jwt做安全管理时，jwt登录过滤器即使内部没有代码，仅实现一个继承来的接口(无代码)

只要使用@Component和@Bean注解注入IOC容器，都会在springboot启动时报空指针异常，原因不明

导致无法将此过滤器注入容器，则内部属性无法使用@Value和@Autowired注入，目前只能使用原生fiter

```java
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    //@Value("${jwt.tokenHeader}")
    private String tokenHeader = "Authorization";
    //@Value("${jwt.tokenHead}")
    private String tokenHead = "Bearer";
    //@Autowired
    private JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
    //@Autowired
    private UserDetailsService userDetailsService = new userDetailsServiceImpl();

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        //存在token
        if (authHeader != null && authHeader.startsWith(tokenHead)){
            System.out.println("000000000000000000000000");
            String authToken = authHeader.substring(tokenHead.length());
            String username = jwtTokenUtil.getUserNameFromToken(authToken);
            //token存在用户名    但是未登录
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                //登录(认证？)
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                //判断token是否有效，重新设置用户对象
                if (jwtTokenUtil.validateToken(tokenHead,userDetails)){
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        //放行
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
```

