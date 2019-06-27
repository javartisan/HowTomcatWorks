###How Tomcat Works By Javartisan



### Tomcat 组件

- Connector 负责接收到来的请求并解析参数并生成Request对象。
- Container容器中加载了大量的Servlet，然后会根据请求找到Servlet触发service方法调用。

备注：Container是Connector与Servlet的连接桥梁。



### Container调用Servlet的service方法

org.apache.catalina.Container#invoke实现了Servlet方法的调用



### Http1.1新特性

- 由于http底层是基于TCP协议，因此每一次创建连接与断开连接都要有消耗；又由于每一个http请求完之后可能随后还要请求页面中的url资源，为了使这些资源共用一个http连接加载，因此http1.1支持长连接。需要在http header中添加：connection：keep-alive





### 