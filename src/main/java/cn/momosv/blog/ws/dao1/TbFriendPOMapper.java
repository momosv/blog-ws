package cn.momosv.blog.ws.dao1;


import cn.momosv.blog.common.dao.BasicMapper;
import cn.momosv.blog.ws.model.TbFriendPO;

public interface TbFriendPOMapper extends BasicMapper {
    TbFriendPO selectByPrimaryKey(String id);
}