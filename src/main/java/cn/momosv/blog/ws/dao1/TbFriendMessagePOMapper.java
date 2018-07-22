package cn.momosv.blog.ws.dao1;


import cn.momosv.blog.common.dao.BasicMapper;
import cn.momosv.blog.ws.model.TbFriendMessagePO;

public interface TbFriendMessagePOMapper extends BasicMapper {
    TbFriendMessagePO selectByPrimaryKey(String id);
}