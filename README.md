# fasterWeb

fasterWeb项目是一个基于SSM（Spring + Spring MVC + Mybatis）构建的Java Web后台网站框架，开发目的是为了快速搭建一套当下很流行的SSM框架，为构建网站省去了繁琐的配置工作，同时提供一些基本的Web小功能，到达**一键配置，快速开发**的目的。

## 基本介绍

项目有三部分构成，分别是Spring MVC, 进行Web请求流程控制，配置文件是`spring-mvc.xml`；Spring 是对系统中使用到的`bean`进行统一管理，包括`@Service`，`@Dao`，`@Entity`，配置文件是`applicationcontent.xml`；Mybatis是ORM框架，用于与不同的数据库进行交互。

1. 使用Spring MVC作为后台的基本框架, `DispatachServlet`拦截所有的请求，作为统一的url入口

2. 对于静态资源的访问，会统一拦截到`classpath:static/`目录下，可将静态的资源，如html,js,css,jpg,gif等资源放到该目录下

3. 项目引入了`herbinate-validator`对参数进行验证

4. 前后端支持使用JSON字符串进行数据交互，1) 接收JSON字符串，使用`@RequestBody`注解对参数进行注解，可进行自动装箱，映射成对应的类；2) 后台返回JSON字符串，可在`Controller`的方法上加上`@ResponseBody`即可将对象自动转换成JSON格式的字符串

5. 数据库连接采用`c3p0`连接插件。
