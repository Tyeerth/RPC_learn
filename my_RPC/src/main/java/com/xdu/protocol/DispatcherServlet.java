package com.xdu.protocol;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

/**
 * @author tyeerth
 * @date 2023/9/1 - 上午10:02
 * @description
 */
public class DispatcherServlet extends HttpServlet {
//    重写代码处理逻辑
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        new HttpServerHandler().handle(req, res);
    }
}
