package indi.joynic.naga.serviceprovider.task;

import indi.joynic.naga.portal.server.serviceprovider.register.RegisterOnServerPortalAccessor;
import indi.joynic.naga.portal.server.serviceprovider.register.RegisterOnServerSubjectWithThrift;
import indi.joynic.naga.serviceprovider.portal.server.client.ThriftNamingServerPortalClient;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NamingRegisterAtIntervalsTask implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(NamingRegisterAtIntervalsTask.class);

    private RegisterOnServerSubjectWithThrift.AccessArgs accessArgs;
    private Long intervalTimeMillis;

    public NamingRegisterAtIntervalsTask(RegisterOnServerSubjectWithThrift.AccessArgs accessArgs,
                                         ThriftNamingServerPortalClient client, Long intervalTimeMillis) {

        this.accessArgs = accessArgs;
        this.intervalTimeMillis = intervalTimeMillis;
    }

    @Override
    public void run() {
        while (true) {

            ThriftNamingServerPortalClient client =
                    new ThriftNamingServerPortalClient(TBinaryProtocol.class, TSocket.class, "127.0.0.1", 27052, 3000);

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
