package cn.momosv.blog.ws.config;



import cn.momosv.blog.common.util.Constants;
import cn.momosv.blog.login.component.TokenManager;
import cn.momosv.blog.login.component.impl.AuthManager;
import cn.momosv.blog.login.model.UserInfoPO;
import cn.momosv.blog.ws.entity.User;
import cn.momosv.blog.ws.interceptor.UserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import javax.security.auth.message.AuthException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Map;


/**
 *
 * @ClassName: WebSocketStompConfig
 * @Description: springboot websocket stomp配置
 * @author cheng
 * @date 2017年9月27日 下午3:45:36
 */

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketStompConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Autowired
    AuthManager authManager;

    /**
     * 注册stomp的端点
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 允许使用socketJs方式访问，访问点为webSocketServer，允许跨域
        // 在网页上我们就可以通过这个链接
        // http://localhost:8080/webSocketServer
        // 来和服务器的WebSocket连接
//        registry.addEndpoint("/webSocketServer").setAllowedOrigins("*").withSockJS();
        registry.addEndpoint("/webSocketServer") //注册一个 /websocket 的 websocket 节点
                .addInterceptors(myHandshakeInterceptor())  //添加 websocket握手拦截器
                .setHandshakeHandler(myDefaultHandshakeHandler())   //添加 websocket握手处理器
                .setAllowedOrigins("*") //设置允许可跨域的域名
                .withSockJS();  //指定使用SockJS协议
    }


    private static long HEART_BEAT=10000;
    /**
     * 配置信息代理
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        ThreadPoolTaskScheduler te = new ThreadPoolTaskScheduler();
        te.setPoolSize(1);
        te.setThreadNamePrefix("wss-heartbeat-thread-");
        te.initialize();
        // 订阅Broker名称
        registry.enableSimpleBroker("/queue", "/topic").setHeartbeatValue(new long[]{HEART_BEAT,HEART_BEAT}).setTaskScheduler(te);
        // 全局使用的消息前缀（客户端订阅路径上会体现出来）
        registry.setApplicationDestinationPrefixes("/app");
        // 点对点使用的订阅前缀（客户端订阅路径上会体现出来），不设置的话，默认也是/user/
         registry.setUserDestinationPrefix("/user/");
    }

    /**
     * 配置客户端入站通道拦截器
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.setInterceptors(createUserInterceptor());
    }

    /**
     *
     * @Title: createUserInterceptor
     * @Description: 将客户端渠道拦截器加入spring ioc容器
     * @return
     */
    @Bean
    public UserInterceptor createUserInterceptor() {
        return new UserInterceptor();
    }

    /**
     * WebSocket 握手拦截器
     * 可做一些用户认证拦截处理
     */
    private HandshakeInterceptor myHandshakeInterceptor(){
        return new HandshakeInterceptor() {
            /**
             * websocket握手连接
             * @return 返回是否同意握手
             */
            @Override
            public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
                ServletServerHttpRequest req = (ServletServerHttpRequest) request;

                //通过url的query参数获取认证参数
               HttpServletRequest request1= req.getServletRequest();
                //获取用户凭证
                String token = request1.getHeader(Constants.USER_TOKEN);
                if(StringUtils.isEmpty(token)){
                    token = request1.getParameter(Constants.USER_TOKEN);
                }
                if(StringUtils.isEmpty(token)){
                    Object obj = request1.getSession().getAttribute(Constants.USER_TOKEN);
                    if(null!=obj){
                        token=obj.toString();
                    }
                }
                UserInfoPO user = authenticate(token);
                //根据token认证用户，不通过返回拒绝握手
                if(user == null){
                    return false;
                }
                User puser = new User(user.getUserName());
                puser.setId(user.getId());
                //保存认证用户
                attributes.put("user", puser);
                return true;
            }

            @Override
            public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

            }
        };
    }

    //WebSocket 握手处理器
    private DefaultHandshakeHandler myDefaultHandshakeHandler(){
        return new DefaultHandshakeHandler(){
            @Override
            protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
                //设置认证通过的用户到当前会话中
                return (Principal)attributes.get("user");
            }
        };
    }

    @Autowired
    private TokenManager tokenManager;
    /**
     * 根据token认证授权
     * @param token
     */
    private UserInfoPO authenticate(String token){
        if(StringUtils.isEmpty(token))return null;
        return  tokenManager.getUserInfoByToken(token);
    }


}
