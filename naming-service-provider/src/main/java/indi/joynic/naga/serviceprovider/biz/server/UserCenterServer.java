package indi.joynic.naga.serviceprovider.biz.server;

import indi.joynic.naga.lib.server.AbstractServer;
import indi.joynic.naga.serviceprovider.biz.service.UserCenterService;
import org.apache.thrift.server.TServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * [demo biz server]UserCenter server
 */
public class UserCenterServer extends AbstractServer {

    private static final Logger logger = LoggerFactory.getLogger(UserCenterServer.class);

    @Resource(name = "userCenterService")
    private UserCenterService userCenterService;

    private TServer tServer = null;

    @Override
    protected void doStart() {
    }

    @Override
    protected void doStop() {

    }
}
