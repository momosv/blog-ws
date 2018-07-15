package cn.momosv.blog.ws.service.impl;



import cn.momosv.blog.common.service.impl.BasicServiceImpl;
import cn.momosv.blog.ws.dao.TbUserPOMapper;
import cn.momosv.blog.ws.model.TbUserPO;
import cn.momosv.blog.ws.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service("userService")
public class UserServiceImpl extends BasicServiceImpl implements UserService {

    @Autowired
    private TbUserPOMapper userMapper;

    @Autowired
    public void setUserMapper(TbUserPOMapper userMapper1) {
        setMapper(userMapper);
    }

    @Override
    public TbUserPO selectByPrimaryKey1(String id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
