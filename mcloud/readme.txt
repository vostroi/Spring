Spring Cloud 搭建项目结构
优化了 maven 依赖结构
增加 jackson
增加了 consumer 模块 模拟 消费者 来调用服务 （RestTemplate）
增加 eureka-server 注册中心 ， 配置 users 服务注册到eureka , 配置consumer 注册到 eureka ， 注意 RestTemplate 要用 @LoadBalanced注解
增加 eureka-server集群 三个eureka server 节点 ； 如果 eureka server 实例的 hostname 没配置在 windows 的 hosts 中， eureka client 也就是 各微服务 使用 不了对应的 eureka server 实例的 hostname 作为defaultZone进行注册 ；
                                                如果使用 127.0.0.1 来注册， 则只会注册到其中一个 eureka server 节点中 ；
增加 maven 打可运行 jar 配置
增加 Feign : 声明式的web service 客户端， 让微服务调用变简单 类似API调用 ， 在 users-api 中增加 UserServiceClient
增加 Hystrix : https://blog.csdn.net/loushuiyifan/article/details/82702522
    熔断 和 降级  https://blog.csdn.net/zero__007/article/details/90732554
            设计目标：对来自依赖的故障/延迟进行防护和控制。
                     阻止故障的连锁反应
                     快速失败并迅速恢复
                     回退并优雅降级
                     提供接近实时的监控和警告
             如何实现：
                    每个依赖都维护着一个线程池（或信号量），线程池被耗尽则拒绝请求（而不是让请求排队）。
增加 熔断 处理
增加 降级 处理 （服务降级是在客户端完成） 需要熔断配合一起使用
增加 Hystrix Dashboard 近实时的调用监控，记录所有通过 Hystrix 发起的请求的执行信息。
mcloud
    |-api
        |-users-api|users服务模块接口
    |-core|项目核心类，公共类
          |修改了IdEntity，不采用hibernate的UUID生成策略，生成的uuid没有横线
          |增加了BaseService| 增加了save(Entity)
                            | findById(id) 写在了UserServiceImpl中 下个版本 移到BaseService中来
    |-eureka-server| 注册中心
    |-zuul-gateway| 网关 使用 zuul 实现 还未配置 服务熔断 降级 限流
                  | 配置熔断
    |-spring cloud gateway | 网关：route predicate filters
    |-micro-services
        |-users|users服务模块|增加getUser(id)接口
                             |增加addUser(User)接口
                             |配置 注册中心 注册服务
        |-consumer|
        |-
        |-