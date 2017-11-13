package indi.joynic.naga.server.registry.storage.impl.redis;

import org.apache.commons.lang3.StringUtils;

public class RedisUtil {

    public static String implode(char separator, String...segments) {
        return StringUtils.join(segments, separator);
    }

}
