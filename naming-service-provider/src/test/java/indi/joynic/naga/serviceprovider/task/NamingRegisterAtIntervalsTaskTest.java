package indi.joynic.naga.serviceprovider.task;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
public class NamingRegisterAtIntervalsTaskTest {

    @Value("${naming.server.hosts}")
    private String hosts;

    @Test
    public void run() throws Exception {

        System.out.println(hosts);

    }

}