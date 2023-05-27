package org.gfg.junitmock.demo.Redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.gfg.junitmock.demo.model.Employee;
import org.gfg.junitmock.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfiguration {

    @Value("${redis.datasource.host}")
    private String redisDsHost;

    @Value("${redis.datasource.port}")
    private Integer redisDsport;

    @Value("${redis.datasource.password}")
    private String redisDsPassword;


    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory(){
        RedisStandaloneConfiguration redisStandaloneConfiguration =
                new RedisStandaloneConfiguration(redisDsHost, redisDsport);
        redisStandaloneConfiguration.setPassword(redisDsPassword);
        LettuceConnectionFactory lettuceConnectionFactory =
                new LettuceConnectionFactory(redisStandaloneConfiguration);
        return lettuceConnectionFactory;

    }


    @Bean
    public RedisTemplate<String, Object> redisTemplate(){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setConnectionFactory(lettuceConnectionFactory());
        return redisTemplate;
    }

    @Bean
    public ObjectMapper getObjectMapper(){
        return new ObjectMapper();
    }




}
