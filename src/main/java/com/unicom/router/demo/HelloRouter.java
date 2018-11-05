package com.unicom.router.demo;


import com.unicom.router.BaseRoute;
import com.unicom.router.utils.ContentTypeUtils;
import org.springframework.stereotype.Component;

/**
 * 浏览器访问 http://localhost:8888/demo/hello
 * 输出"hello world"
 * 控制台打印"invoke /hello"
 *
 * 因为此类继承了自己写的BaseRoute类，
 * 所以26行"throw new RuntimeException()"执行时，
 * 浏览器会显示BaseRoute类中定义的"系统异常"
 */
@Component
public class HelloRouter extends BaseRoute {

	@Override
	public void doConfigure() {
		rest()
		.produces(ContentTypeUtils.textPlain())
		.get("/hello")
		.route()
		.process(exchange -> {
			System.out.println(" invoke /hello");
//			throw new RuntimeException();
		})
		.to("direct:done");
		
		
		from("direct:done")
		.transform()
		.body()
		.setBody(constant("hello world"))
		.end();
		
	}

}
