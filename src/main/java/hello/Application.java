package hello;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Date;

@SpringBootApplication
public class Application {


    @Bean
    @ConditionalOnMissingBean
    public RedisConnectionFactory redisConnectionFactory()
            throws UnknownHostException {
        JedisConnectionFactory factory =
                new JedisConnectionFactory();
        factory.setHostName("localhost");
        factory.setPort(6379);
        return factory;
    }



    @Bean
    @Primary
    RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory rcf) {

        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(rcf);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new JsonRedisSerializer());

        return template;
    }

    /*@Bean
    @ConditionalOnMissingBean(StringRedisTemplate.class)
    public StringRedisTemplate stringRedisTemplate(
            RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
        StringRedisTemplate template = new StringRedisTemplate();

        return template;
    }*/

    static class JsonRedisSerializer implements RedisSerializer<Object> {

        private final ObjectMapper om;

        public JsonRedisSerializer() {
            this.om = new ObjectMapper().enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        }

        @Override
        public byte[] serialize(Object t) throws SerializationException {
            try {
                return om.writeValueAsBytes(t);
            } catch (JsonProcessingException e) {
                throw new SerializationException(e.getMessage(), e);
            }
        }

        @Override
        public Object deserialize(byte[] bytes) throws SerializationException {

            if(bytes == null){
                return null;
            }

            try {
                return om.readValue(bytes, Object.class);
            } catch (Exception e) {
                throw new SerializationException(e.getMessage(), e);
            }
        }
    }

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);








    }



}


