Spring Cloud 搭建项目结构
优化了 maven 依赖结构
增加 jackson
增加了 consumer 模块 模拟 消费者 来调用服务 （RestTemplate）
增加 eureka-server 注册中心 ， 配置 users 服务注册到eureka , 配置consumer 注册到 eureka ， 注意 RestTemplate 要用 @LoadBalanced注解
增加 eureka-server集群 三个eureka server 节点 ； 如果 eureka server 实例的 hostname 没配置在 windows 的 hosts 中， eureka client 也就是 各微服务 使用 不了对应的 eureka server 实例的 hostname 作为defaultZone进行注册 ；
                                                如果使用 127.0.0.1 来注册， 则只会注册到其中一个 eureka server 节点中 ；
增加 maven 打可运行 jar 配置
mcloud
    |-api
        |-users-api|users服务模块接口
    |-core|项目核心类，公共类
          |修改了IdEntity，不采用hibernate的UUID生成策略，生成的uuid没有横线
          |增加了BaseService| 增加了save(Entity)
                            | findById(id) 写在了UserServiceImpl中 下个版本 移到BaseService中来
    |-eureka-server| 注册中心
    |-micro-services
        |-users|users服务模块|增加getUser(id)接口
                             |增加addUser(User)接口
                             |配置 注册中心 注册服务
        |-consumer|
        |-
        |-