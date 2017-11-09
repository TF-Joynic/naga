package indi.joynic.naga.lib;

public enum ProtocolType {
    HTTP,
    THRIFT;

    public String value() {
        return this.name();
    }

    @Override
    public String toString() {
        return "ProtocolType: " + this.value();
    }
}
