package cn.momosv.blog.ws.controller;


import cn.momosv.blog.ws.entity.ServerMessage;
import cn.momosv.blog.ws.entity.User;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 *
 * @ClassName: WebSocketAction
 * @Description: websocket控制层WebSocketController
 * @author cheng
 * @date 2017年9月27日 下午4:20:58
 */
@Controller
public class WebSocketController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //spring提供的发送消息模板
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private SimpUserRegistry userRegistry;

    @RequestMapping(value = "/toAll")
    public void toAll(@RequestParam(defaultValue = "这是系统默认消息") String msg) {
        logger.info("当前在线人数:" + userRegistry.getUserCount());
        int i = 1;
        for (SimpUser user : userRegistry.getUsers()) {
            logger.info("用户" + i++ + "---" + user);
        }
        //发送消息给指定用户
        messagingTemplate.convertAndSend("/queue/toAll", new ServerMessage("all:"+msg));
    }

    @MessageMapping("/toChat")
    public void chat(String data, User fromUser) {
        String toUser= (String) JSON.parseObject(data).get("toUser");
        String msg= (String) JSON.parseObject(data).get("msg");
        logger.info("当前在线人数:" + userRegistry.getUserCount());
        int i = 1;
        for (SimpUser user1 : userRegistry.getUsers()) {
            logger.info("用户" + i++ + "---" + user1);
        }
        //发送消息给指定用户
        messagingTemplate.convertAndSendToUser(toUser, "/queue/toChat", new ServerMessage(fromUser.getName(),toUser,msg));
    }

    @MessageMapping("/onLine")
    @SendTo("/queue/onLine")
    public ServerMessage onLine(String name) {
        String m= (String) JSON.parseObject(name).get("name");
        //发送消息给指定用户
        //  messagingTemplate.convertAndSend( "/queue/toAll", new ServerMessage("上线了"));
        return new ServerMessage(m+" 上线了");
    }

}