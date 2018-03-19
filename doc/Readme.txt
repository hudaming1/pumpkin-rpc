
【优先修改】
	1.spring配置一些参数鸡肋，例如protocol和registry标签还需要配置id？明显不科学。
	2.如果只有一个protocol和registry，service就不需要制定protocol和registry了
	3.实现client标签 


【后续】
引入spring支持 √
引入注册中心 √
引入telnet调用
引入上下文
引入过滤器
分层不明确，还有跨层调用。
修改bug，启动后才能发起调用。
将关键层的几个核心类再重新设计一下，明确各自分工
引入Directory组件配合Registry进行路由
扩展ServiceLoader，支持有参数的实例化，支持根据name动态获得Extension
重新设计ClusterInvoker和DirectInvoker，两者不是必然关系，需要解耦；ClusterInvoker仅仅是集成了目录服务、注册中心、路由策略等功能，期望可以和DirectInvoker集成，也可以直接和后面的HttpInvoker集成。
Maven使用depdencyManagement管理

