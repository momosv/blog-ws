package cn.momosv.blog.ws.dao;


import cn.momosv.blog.common.dao.BasicMapper;
import cn.momosv.blog.ws.model.TbUserPO;
import org.springframework.stereotype.Repository;


@Repository
public interface TbUserPOMapper extends BasicMapper {
    TbUserPO selectByPrimaryKey(String id);
}