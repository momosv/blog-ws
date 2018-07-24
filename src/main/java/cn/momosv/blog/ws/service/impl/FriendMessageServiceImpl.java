package cn.momosv.blog.ws.service.impl;


import cn.momosv.blog.base.mybatis.service.impl.BasicServiceImpl;
import cn.momosv.blog.ws.dao.TbFriendMessagePOMapper;
import cn.momosv.blog.ws.service.FriendMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("friendMessageService")
public class FriendMessageServiceImpl extends BasicServiceImpl implements FriendMessageService {

    @Autowired
    private TbFriendMessagePOMapper mapper;

/*    @Autowired
    public void setFMMapper() {
       setMapper(mapper);
    }*/

}
