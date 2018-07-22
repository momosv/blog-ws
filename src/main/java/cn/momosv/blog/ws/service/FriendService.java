package cn.momosv.blog.ws.service;


import cn.momosv.blog.common.service.BasicService;
import cn.momosv.blog.ws.model.TbFriendPO;

public interface FriendService extends BasicService {
    TbFriendPO selectByPrimaryKey1(String id);
}
