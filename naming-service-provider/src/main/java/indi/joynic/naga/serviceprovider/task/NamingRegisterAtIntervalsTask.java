package indi.joynic.naga.serviceprovider.task;

import indi.joynic.naga.server.portal.serviceprovider.register.RegisterOnServerPortalAccessor;

public class NamingRegisterAtIntervalsTask implements Runnable {
    private String hosts;
    private Long intervalTimeMillis;

    public NamingRegisterAtIntervalsTask(String hosts, Long intervalTimeMillis) {
        this.hosts = hosts;
        this.intervalTimeMillis = intervalTimeMillis;
    }

    @Override
    public void run() {
        while (true) {

            // registering to naming server...
//            RegisterOnServerPortalAccessor accessor = new RegisterOnServerPortalAccessor();

            try {
                Thread.sleep(intervalTimeMillis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



}
