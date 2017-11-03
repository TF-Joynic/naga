package indi.joynic.naga.lib;

/**
 * Created by xiaolei on 2017/11/1.
 */
public enum ProtocolType {
    HTTP,
    THRIFT;

    public String value() {
        return this.name().toLowerCase();
    }

    @Override
    public String toString() {
        return "ProtocolType: " + this.value();
    }
}
