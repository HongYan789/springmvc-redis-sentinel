package client;

import com.lppz.ehr.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.resource.spi.ConfigProperty;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-redis-sentinel.xml"})
public class TestRedisSpring {

    /***
     * 哨兵集群方式使用redis
     */
    @Test
    public  void testRedisSentinel() {
        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-redis-sentinel.xml");
        RedisUtil redisUtil = (RedisUtil) context.getBean("redisUtil");

        /**string**/
        redisUtil.set("age", 15);

        //设置key为name,value为lpl,10为时间（秒）
        redisUtil.set("name", "lpl", 10);

        System.out.println(redisUtil.get("age"));
        System.out.println("姓名："+redisUtil.get("name"));
        //年龄增加1
        redisUtil.incr("age", 1);
        System.out.println("年龄增加后"+ redisUtil.get("age"));
        //年龄减去1
        redisUtil.decr("age", 1);
        System.out.println("年龄减去后"+ redisUtil.get("age"));

        /****map****/
        Map<String,Object> userMap = new HashMap<>();
        userMap.put("name", "张三");
        userMap.put("age", 16);
        redisUtil.hmset("user", userMap);


        System.out.println(redisUtil.hmget("user"));

        long times = redisUtil.getExpire("user");

        System.out.println("redis到期时间："+times);

        redisUtil.expire("user",300);

        long times2 = redisUtil.getExpire("user");

        System.out.println("redis到期时间："+times2);

    }

    /***
     * 池化使用Jedis
     */
    @Test
    public void testRedis(){
        String redisAddress = "140.143.248.222";
        int redisPort = 6301;
        int redisTimeout = 2000;

        JedisPool pool = new JedisPool(new JedisPoolConfig(), redisAddress, redisPort, redisTimeout);
        // 从池中获取一个Jedis对象
        Jedis jedis = pool.getResource();
        // 存数据
        jedis.set("test123", "lulu");

        pool.returnResource(jedis);
        // 取数据
        System.out.println("获取redis结果："+jedis.get("test123"));

        jedis.del("test123");
        // 释放对象池（如果采用del删除了jedis节点，则不能调用释放对象池，会报Could not return the resource to the pool）
//        pool.returnResource(jedis);
    }
}
