package cn.momosv.blog.ws.service.impl;


import cn.momosv.blog.common.service.impl.BasicServiceImpl;
import cn.momosv.blog.ws.dao1.TbFriendMessagePOMapper;
import cn.momosv.blog.ws.service.FriendMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("friendMessageService")
public class FriendMessageServiceImpl extends BasicServiceImpl implements FriendMessageService {

    @Autowired
    private TbFriendMessagePOMapper mapper;

/*    @Autowired
    public void setFMMapper() {
       setMapper(mapper);
    }*/

}
