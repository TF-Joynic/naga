package indi.joynic.naga.serviceprovider.task;

public class NamingRegisterAtIntervalsTask implements Runnable {
    private String hosts;

    public NamingRegisterAtIntervalsTask(String hosts) {
        this.hosts = hosts;
    }

    @Override
    public void run() {
        while (true) {

            System.out.println("registering to naming server..." + hosts);
            

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



}
