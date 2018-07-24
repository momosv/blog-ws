package cn.momosv.blog.ws.dao;


import cn.momosv.blog.base.mybatis.dao.BasicMapper;
import cn.momosv.blog.ws.model.TbFriendMessagePO;

public interface TbFriendMessagePOMapper extends BasicMapper {
    TbFriendMessagePO selectByPrimaryKey(String id);
}