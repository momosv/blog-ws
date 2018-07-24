package cn.momosv.blog.ws.service.impl;


import cn.momosv.blog.base.mybatis.service.impl.BasicServiceImpl;
import cn.momosv.blog.ws.dao.TbFriendPOMapper;
import cn.momosv.blog.ws.model.TbFriendPO;
import cn.momosv.blog.ws.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
