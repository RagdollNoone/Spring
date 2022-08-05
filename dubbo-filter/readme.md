> 这个项目主要验证如下的问题

* 常规的dubbo filter的使用方式
* provider的filter配置了group = {"provider", "consumer"} 并不会影响consumer端
* 统一的异常处理filter 
```txt
1. 注意替换默认的ExceptionFilter 应该遵循SPI机制 
2. dubbo的异常处理和平时的逻辑不太一样 需要客户端(而不是服务端)来统一处理 不然无法拦截dubbo请求超时异常
```  