package cn.momosv.blog.ws.model;



import cn.momosv.blog.base.mybatis.model.base.IBaseDBPO;

import java.util.Date;

public class TbFriendMessagePO extends IBaseDBPO {
    private String id;

    private String userId;

    private String friendId;

    private Date createTime;

    private Byte isRead;

    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId == null ? null : friendId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Byte getIsRead() {
        return isRead;
    }

    public void setIsRead(Byte isRead) {
        this.isRead = isRead;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    @Override
    public String _getTableName() {
        return "tb_friend_message";
    }

    @Override
    public String _getPKValue() {
        return id;
    }

    @Override
    public void _setPKValue(Object var1) {
        this.id= (String) var1;
    }

    @Override
     public  String  _getPKColumnName(){
        return "id";
    }
}