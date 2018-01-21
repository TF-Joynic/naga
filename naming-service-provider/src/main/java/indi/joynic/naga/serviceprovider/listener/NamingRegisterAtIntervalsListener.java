package indi.joynic.naga.serviceprovider.listener;

import indi.joynic.naga.serviceprovider.task.NamingRegisterAtIntervalsTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class NamingRegisterAtIntervalsListener implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(NamingRegisterAtIntervalsListener.class);

    @Value("${naming.server.hosts}")
    private String hosts;

    @Value("${naming.register.interval}")
    private Long registerInterval;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {


        // TODO
        /*NamingRegisterAtIntervalsTask namingRegisterAtIntervalsTask
                = new NamingRegisterAtIntervalsTask(hosts, registerInterval);

        Thread namingRegisterAtIntervalsThread = new Thread(namingRegisterAtIntervalsTask);
        namingRegisterAtIntervalsThread.setName("namingRegisterAtIntervals");
        namingRegisterAtIntervalsThread.setDaemon(true);
        namingRegisterAtIntervalsThread.start();

        logger.info("namingRegisterAtIntervalsThread started! " + System.currentTimeMillis());*/
    }
}
