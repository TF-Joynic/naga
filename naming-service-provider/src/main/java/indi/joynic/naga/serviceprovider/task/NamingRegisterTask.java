package indi.joynic.naga.serviceprovider.task;

import indi.joynic.naga.lib.utils.SocketAddrUtil;
import indi.joynic.naga.portal.server.serviceprovider.register.RegisterOnServerPortalAccessor;
import indi.joynic.naga.portal.server.serviceprovider.register.RegisterOnServerSubjectWithThrift;
import indi.joynic.naga.portal.server.serviceprovider.service.ThriftNamingServerPortal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NamingRegisterTask implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(NamingRegisterTask.class);

    private RegisterOnServerSubjectWithThrift.AccessArgs accessArgs;
    private ThriftNamingServerPortal.Iface client;

    public NamingRegisterTask(RegisterOnServerSubjectWithThrift.AccessArgs accessArgs,
                              ThriftNamingServerPortal.Iface client) {

        this.accessArgs = accessArgs;
        this.client = client;
    }

    @Override
    public void run() {
        if (!SocketAddrUtil.portUsed(accessArgs.getPort())) {
            logger.warn("port is not launched!");
            return ;
        }

        // registering to naming server...
        RegisterOnServerPortalAccessor accessor
                = new RegisterOnServerPortalAccessor(accessArgs, client);

        boolean registerResult = accessor.access();

        if (logger.isInfoEnabled()) {

            logger.info("thread: {} register on server result: {}",
                    Thread.currentThread().getName(), registerResult);

        }
    }
}
