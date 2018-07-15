package cn.momosv.blog.ws.service;


import cn.momosv.blog.common.service.BasicService;
import cn.momosv.blog.ws.model.TbUserPO;


public interface UserService extends BasicService {
    TbUserPO selectByPrimaryKey1(String id);
}
