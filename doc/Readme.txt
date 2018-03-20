
【优先修改】
抽象出AbstractProtocol：filter扩展、鉴权等实现都在该类中buildInvokerChain构建

【后续】
引入spring支持 √
引入注册中心 √
引入telnet调用：为telnet单独开放一个Protocol，下层不动，但需要增加一个消息类型（在Protocol层没有，这是个问题）。
引入上下文：通过ThreadLocal实现，赋值在Filter中做。
心跳（heartbeat）：因为心跳是IP间点对点的通信，不区分服务，因此打算在Exchange层做。
权限（鉴权、白名单、黑名单）：因为鉴权时可能取分服务，因此在Protocol做，采用Filter实现。
流量控制：流量取分服务，考虑在Protocol层做，使用Filter实现。
连接数控制：取分服务，在prototol层做，使用Filter实现。
缓存结果：取分服务，在Protocol层做，使用Filter？需要支持Order了吧？
通信频次监控（Monitor）：取分服务，在Protocol做，使用Filter实现。
通信调用时自定义扩展拦截过滤（Filter）：Protocol层
集群容错（failover、failfast）：跟Registry相关，但具体设计需要再仔细想想。
timeout/retry_times：在exchange实现，因为属于一次业务通信。
泛化调用：表示层修改，Seriliazation
客户端异步调用：不等待结果直接返回，不可靠调用，这个需要客户端在refer时，传入url带参数；在调用过程中判断isAsyn动态改变调用逻辑，不等待直接返回。
启动时检查（checked=false）：在Protocol传入给Exchange端，创建ExchangeClient时需要判断是否需要提前init
负载均衡：在ClusterInvoker中实现即可，可支持根据服务动态加载（顺便正好学习一下一致性hash代码实现）。
分组与版本控制：group与version概念，必然是在Protocol层实现，应该不难。
配置优先级：客户端配置优先配置，没有配置使用全局配置，再其次使用服务端配置
本地存根：客户端Protocl层做。
令牌管理：与上线的鉴权不同，这里的令牌是为了防止客户端跨过注册中心直连。


引入Directory组件配合Registry进行路由
扩展ServiceLoader，支持有参数的实例化，支持根据name动态获得Extension，以及加载多实例
重新设计ClusterInvoker和DirectInvoker，两者不是必然关系，需要解耦；ClusterInvoker仅仅是集成了目录服务、注册中心、路由策略等功能，期望可以和DirectInvoker集成，也可以直接和后面的HttpInvoker集成。
Maven使用depdencyManagement管理
扩展出多协议、多注册中心

【优化】
修改bug，启动后才能发起调用。


