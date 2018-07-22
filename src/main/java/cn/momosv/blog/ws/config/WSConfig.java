package cn.momosv.blog.ws.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("cn.momosv.blog.ws.dao1")
public class WSConfig {
}
