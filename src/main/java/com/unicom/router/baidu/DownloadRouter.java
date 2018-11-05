package com.unicom.router.baidu;

import com.unicom.router.BaseRoute;
import com.unicom.router.utils.ContentTypeUtils;
import org.springframework.stereotype.Component;

/**
 * 浏览器访问 http://localhost:8888/demo/baidu/download *
 * 浏览器返回"download success..."
 * 控制台打印"download active..."
 * 本地磁盘 D:\Users\beyond\tmp\baidu 目录下新增 baidu.html文件
 *
 * 或者定时任务"0/30 * * * * ? "从0秒开始，每30秒执行一次
 * 控制台打印"download active..."
 * 本地磁盘 D:\Users\beyond\tmp\baidu 目录下新增 baidu.html文件
 */
@Component
public class DownloadRouter extends BaseRoute {

	@Override
	public void doConfigure() {
		//restful 浏览器访问触发url
		rest("/baidu")
		.produces(ContentTypeUtils.jsonPlain())
		.get("/download")
		.route()
		.to("direct:download");
		
		//定时任务触发
		from("quartz2:demo/download?cron=0/30 * * * * ?&job.name=download&stateful=true")
		.to("direct:download");
		
		//下载业务实现
		from("direct:download")
		.process(exchange -> {
			System.out.println(" download active...");
		})
		.to("http4:www.baidu.com?bridgeEndpoint=true&connectionClose=true")
		.to("file:/Users/beyond/tmp/baidu?fileName=baidu.html")
		.setBody(constant("download success..."))
		.end();
	}

}
