package indi.joynic.naga.connpool.enums;

/**
 * Connection Status in the connection pool
 *
 * @author Terrance Fung
 * @since 1.0
 */
public enum ConnectionStatus {
    /**
     * Conenction is working at this moment.
     */
    BUSY,

    /**
     * Connection is available.
     */
    IDLE;
}
