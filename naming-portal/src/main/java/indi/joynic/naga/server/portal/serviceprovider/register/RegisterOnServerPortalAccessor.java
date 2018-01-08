package indi.joynic.naga.server.portal.serviceprovider.register;

import indi.joynic.naga.AbstractPortalAccessorImpl;
import indi.joynic.naga.server.portal.serviceprovider.service.ThriftNamingServerPortal;

public class RegisterOnServerPortalAccessor extends AbstractPortalAccessorImpl<Boolean> {
    public RegisterOnServerPortalAccessor(RegisterOnServerSubjectWithThrift.AccessArgs accessArgs, ThriftNamingServerPortal.Iface client) {

        super(new RegisterOnServerSubjectWithThrift(accessArgs, client));

    }
}