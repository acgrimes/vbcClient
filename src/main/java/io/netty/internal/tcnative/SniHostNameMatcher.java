package io.netty.internal.tcnative;

public interface SniHostNameMatcher {

    /**
     * Returns {@code true} if the hostname was matched and so SNI should be allowed.
     * @param ssl the SSL instance
     * @param hostname the hostname to match.
     * @return {@code true} if the hostname was matched
     */
    boolean match(long ssl, String hostname);
}