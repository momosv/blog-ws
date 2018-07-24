package cn.momosv.blog.ws.dao;


import cn.momosv.blog.base.mybatis.dao.BasicMapper;
import cn.momosv.blog.ws.model.TbFriendPO;

public interface TbFriendPOMapper extends BasicMapper {
    TbFriendPO selectByPrimaryKey(String id);
}