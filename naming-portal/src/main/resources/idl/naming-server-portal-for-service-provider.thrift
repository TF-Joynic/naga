include "base_type.thrift"

namespace java indi.joynic.naga.server.portal.serviceprovider
namespace php Naming.Server.Portal.ServiceProvider

struct RegisterArgs {
    1: required String namespace,
    2: required String protocolType,
    3: required String serviceName,
    4: required String host,
    5: required int port,
    6: required int weight
}

service ThriftNamingServerPortal {
    bool doRegister(RegisterArgs registerArgs);
}
