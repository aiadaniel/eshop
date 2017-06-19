package com.vigorous.rest.jedis;

import java.io.IOException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.JedisCluster;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations={"file:WebContent/WEB-INF/spring-mybatis.xml"})
public class JedisTest {

	@Test
	public void testSpringJedisCluster() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-*.xml");
		JedisCluster jedisCluster =  (JedisCluster) applicationContext.getBean("redisClient");
		String string = jedisCluster.get("a");
		System.out.println(string);
		try {
			jedisCluster.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
