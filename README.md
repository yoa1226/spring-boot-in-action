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