namespace java indi.joynic.naga.server.portal.serviceprovider
namespace php Naming.Server.Potal.ServiceProvider

typedef i32 int
typedef i64 long
typedef string String


service NamingServerPortal {
    bool doRegister(String protocolType, String ns, String serviceName, String host, int port);

}