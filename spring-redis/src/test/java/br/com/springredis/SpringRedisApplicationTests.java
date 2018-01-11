package br.com.springredis;

import br.com.springredis.service.RedisService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisService.class)
public class SpringRedisApplicationTests {

    @Before
    public void setUp() {
    }

    @Test
    public void contextLoads() {
    }
}
