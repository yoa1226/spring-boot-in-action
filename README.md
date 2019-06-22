1. 使用**Spring Boot**开发第一个**RESTful**接口，`/students/{id}`

2. 增加**web**测试与参数校验并封装校验结果
    
    ```json
    [
        {
            "field": "school",
            "msg": "must not be blank"
        },
        {
            "field": "age",
            "msg": "must not be null"
        }
    ]
    ```
    
    * [boot-features-testing](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-testing.html)
    * [bean-validation-with-spring-boot](https://reflectoring.io/bean-validation-with-spring-boot/)

3. 增加swagger，访问`swagger-ui.html`

4. 利用mockito测试没有实现的方法

5. 热重启
    
    * 目前项目重启花费是时间4s左右，热重启是1.5s左右
    * 每次修改文件点击 `build project`,快捷键`command+F9`
    * 更多关于`devtools`访问 [boot-devtools-restart](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#using-boot-devtools-restart)
    
6. `banner`设置，比如禁用`banner`
    * 更多参考[boot-features-banner](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-banner)
    
7. 配置启动环境。
    * [boot-features-external-config-application-property-files](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-external-config-application-property-files)