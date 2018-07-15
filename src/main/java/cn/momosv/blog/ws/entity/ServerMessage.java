package cn.momosv.blog.ws.entity;

/**
 *
 * @ClassName: ServerMessage
 * @Description: 服务端发送消息实体
 * @author cheng
 * @date 2017年9月27日 下午4:25:26
 */
public class ServerMessage {
    private String fromUser;
    private String toUser;

    private String responseMessage;

    public ServerMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public ServerMessage(String fromUser, String toUser, String responseMessage) {
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.responseMessage = responseMessage;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
