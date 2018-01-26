package indi.joynic.naga.serviceprovider.task;

import indi.joynic.naga.portal.server.serviceprovider.register.RegisterOnServerPortalAccessor;
import indi.joynic.naga.portal.server.serviceprovider.register.RegisterOnServerSubjectWithThrift;
import indi.joynic.naga.portal.server.serviceprovider.service.ThriftNamingServerPortal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class NamingRegisterAtIntervalsTask implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(NamingRegisterAtIntervalsTask.class);

    private RegisterOnServerSubjectWithThrift.AccessArgs accessArgs;
    private ThriftNamingServerPortal.Iface client;
    private Long intervalTimeMillis;

    public NamingRegisterAtIntervalsTask(RegisterOnServerSubjectWithThrift.AccessArgs accessArgs,
                                         ThriftNamingServerPortal.Iface client, Long intervalTimeMillis) {

        this.accessArgs = accessArgs;
        this.client = client;
        this.intervalTimeMillis = intervalTimeMillis;
    }

    @Override
    public void run() {
        while (true) {
            // registering to naming server...
            RegisterOnServerPortalAccessor accessor
                    = new RegisterOnServerPortalAccessor(accessArgs, client);

            boolean registerResult = accessor.access();

            logger.info("thread: {}-{} register on server result: {}", Thread.currentThread().getId(),
                    Thread.currentThread().getName(), registerResult);

            try {
                Thread.sleep(intervalTimeMillis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



}
