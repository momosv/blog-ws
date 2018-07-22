package cn.momosv.blog.ws.service.impl;


import cn.momosv.blog.common.service.impl.BasicServiceImpl;
import cn.momosv.blog.ws.dao1.TbFriendPOMapper;
import cn.momosv.blog.ws.model.TbFriendPO;
import cn.momosv.blog.ws.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("friendService")
public class FriendServiceImpl extends BasicServiceImpl implements FriendService {
    @Autowired
    private TbFriendPOMapper mapper;

/*    @Autowired
    public void setFMapper() {
        setMapper(mapper);
    }*/

    @Override
    public TbFriendPO selectByPrimaryKey1(String id) {
        return mapper.selectByPrimaryKey(id);
    }
}
