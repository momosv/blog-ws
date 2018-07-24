package cn.momosv.blog.ws.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("cn.momosv.blog.ws.dao")
public class WSConfig {
}
