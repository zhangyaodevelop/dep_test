package com.forezp.eurekaclient.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.lang.reflect.Method;


@Configuration
@EnableCaching
public class RedisConfig {

    @Autowired
    private Environment env;

    @Bean
    public KeyGenerator keyGenerator() {

        return new KeyGenerator() {

            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append("_").append(method.getName());
                for (Object obj : params) {
                    sb.append("_").append(obj.toString());
                }
                return sb.toString();
            }
        };
    }


    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        StringRedisTemplate template = new StringRedisTemplate(factory);
        @SuppressWarnings({ "rawtypes", "unchecked" })
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        JedisConnectionFactory jc = (JedisConnectionFactory) factory;
        System.out.println(jc.getHostName());
        return template;
    }

    @Bean
    public JedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(env.getProperty("spring.redis.host"));
        factory.setPort(Integer.parseInt(env.getProperty("spring.redis.port")));
        factory.setPassword(env.getProperty("spring.redis.password"));
        factory.setTimeout(Integer.parseInt(env.getProperty("spring.redis.timeout"))); //设置连接超时时间
        JedisPoolConfig jedisPoolConfig=new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(Integer.parseInt(env.getProperty("spring.redis.max-idle")));
        jedisPoolConfig.setMinIdle(Integer.parseInt(env.getProperty("spring.redis.min-idle")));
        jedisPoolConfig.setMaxTotal(Integer.parseInt(env.getProperty("spring.redis.max-active")));
        jedisPoolConfig.setMaxWaitMillis(Long.valueOf(env.getProperty("spring.redis.max-wait")));
        factory.setPoolConfig(jedisPoolConfig);
        return factory;
    }

    }

