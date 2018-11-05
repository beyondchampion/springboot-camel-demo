# springboot-camel-demo

#### 项目介绍
springboot 整合 Apache Camel 的案例

    定时抓取百度页面内容，并存储到本地磁盘。

         1）百度提供的服务是http://www.baidu.com --> 这里的协议便是 http。

         2）抓取百度的内容 --> 这里的内容就是数据，camel抓取到数据会把它封装进 Message,

         3）抓取到的内容存储到本地磁盘 --> 本地磁盘的协议是 file, 存储下来涉及到内容的转移，即Message 的 Exchange.

#### 软件架构
springboot、camel


