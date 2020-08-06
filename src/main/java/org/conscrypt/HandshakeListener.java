package org.conscrypt;

import javax.net.ssl.SSLException;

/**
 * Similar in concept to {@link javax.net.ssl.HandshakeCompletedListener}, but used for listening directly
 * to the engine. Allows the caller to be notified immediately upon completion of the TLS handshake.
 */
public abstract class HandshakeListener {

    /**
     * Called by the engine when the TLS handshake has completed.
     */
    public abstract void onHandshakeFinished() throws SSLException;
}
