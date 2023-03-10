package com.myTodoApplication.springBoot.MyFirstTodoApplication.sayHello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SayHelloController {
    @RequestMapping("Hello")
    @ResponseBody
    public String hello() {
        return "i will complete this cource";
    }

    @RequestMapping("Hello-jsp")
    public String hellojsp() {
        return "Hello";
    }

    @RequestMapping("Hello-html")
    @ResponseBody
    public String hellohtml() {
        StringBuffer sb = new StringBuffer();
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<title>");
        sb.append("My page");
        sb.append("</title>");
        sb.append("</head>");
        sb.append("<body>");
        sb.append("my Html page");
        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();
    }
}
