package indi.joynic.naga.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class NagaNamingServer {
    public static void main(String[] args) {
        SpringApplication.run(NagaNamingServer.class, args);
    }
}
