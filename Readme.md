## Pumpkin-rpc
是作者在学习RPC框架中，仿照Dubbo的设计思想，自己动手开发的一个RPC框架。

Version 1.0
---------------
    当前版本目前仅支持点对点通信，但RPC模型基本完成
### v1.0 设计思路
    当前版本将通信过程自上到下的分为了6层
##### 1.用户层
    用户的Service和Reference代码都在这层，框架本身实现不涵盖这层。而框架的整体目的就是为了让Client顺利的调用到Service。
##### 2.代理层
    代理层的目的是为了让用户层透明化发起调用，对上层更加友好。(JdkProxy/CgLibProxy)
##### 3.协议层
    协议层规定了用户层的Reference与Service在通信时，采用的通信方式；（PumpkinProtocol）
    以框架默认的pumpkin协议为例，当使用"南瓜协议"时，在不修改其他配置的情况就意味着你会:
        ① 基于TCP层传输通信 
        ② 采用Netty传输 
        ③ 保持单一长连接 
        ④ Kryo序列化方式
    当然协议层的规定也不是绝对的，这只是一种协议设计者推荐的搭配，最终通信方式仍然是取决于客户端。
##### 4.数据交换层
    在最早一版(可以认为是v0.0.1)时我将"数据交换层"与"传输层"合并为"传输层"，但在实现时发现还是可以继续解耦。最终我将通信的
    重试、超时、同步异步等功能与传输功能分开。其中"重试、超时、同步异步"这些功能实质上是与下层的传输没有关系的，因此将这部分功能放
    到了"数据交换层"
##### 5.传输层
    有了上面"数据交换层"的解耦，传输层就只专注于数据传输实现。（Netty/TcpSocket）
##### 6.表示层
    表示层不单单是序列化规则，这也是我在设计v1.0版本过程中，学习其他Rpc框架得出结果。其目的是为了以后扩展跨语言调用做准备。目前
    在传输时直接将Request对象转成字节流传输，计划在v2.0时对这层进行改造。
    
### v1.0 完成功能
    1.Transporter层支持Jdk-Socket、Netty 
    2.Serialization支持Jdk-ObjectStream、Kryo
    3.thread-pool采用ExcecutorService
    4.扩展组件采用JDK自带的SPI 
    5.Proxy采用Jdk自带反射组件

### 代码运行
    1.启动服务端：src/test/java/org/hum/pumpkin/test/simple/ServerTest.java
    2.启动客户端：src/test/java/org/hum/pumpkin/test/simple/ClientTest.java
