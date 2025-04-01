package com.herobattle.configuration;

import com.herobattle.service.model.Hero;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Hero> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Hero> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(new StringRedisSerializer()); // Ключи как строки
        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(Hero.class)); // Значения как JSON
        template.afterPropertiesSet();
        return template;
    }

}
