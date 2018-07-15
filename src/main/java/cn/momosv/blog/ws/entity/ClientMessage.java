package cn.momosv.blog.ws.entity;


/**
 *
 * @ClassName: ClientMessage
 * @Description: 客户端发送消息实体
 * @author cheng
 * @date 2017年9月27日 下午4:24:11
 */
public class ClientMessage {
    private String msg;
    private String user;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
