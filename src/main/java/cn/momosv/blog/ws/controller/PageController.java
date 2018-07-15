package cn.momosv.blog.ws.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class PageController {

    @RequestMapping("chat")
    public String chat(@RequestParam(defaultValue = "admin") String name, Map map){
        map.put("name",name);
        return "chat";
    }
}
