package indi.joynic.naga.portal.server.serviceprovider.register;

import indi.joynic.naga.portal.AbstractPortalAccessor;
import indi.joynic.naga.portal.server.serviceprovider.service.ThriftNamingServerPortal;

public class RegisterOnServerPortalAccessor extends AbstractPortalAccessor<Boolean> {

    public RegisterOnServerPortalAccessor(RegisterOnServerSubjectWithThrift.AccessArgs accessArgs,
                                          ThriftNamingServerPortal.Iface client) {

        super(new RegisterOnServerSubjectWithThrift(accessArgs, client));

    }
}
