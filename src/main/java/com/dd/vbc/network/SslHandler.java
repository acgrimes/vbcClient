package com.dd.vbc.network;

import io.netty.handler.ssl.ClientAuth;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.SslProvider;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManagerFactory;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

public class SslHandler {

    public static final String CLIENT_NAME = "client";
    public static final char[] CLIENT_PASSWORD = "clientPassword".toCharArray();

    public static final String TRUST_STORE_NAME = "trustStore";
    public static final char[] TRUST_STORE_PASSWORD = "trustPassword".toCharArray();

    public SslContext buildSslContext() {

        SslContext sslContext = null;
        try {
            KeyManagerFactory mgrFact = KeyManagerFactory.getInstance("SunX509");
            KeyStore clientStore = KeyStore.getInstance("PKCS12");

            clientStore.load(VBCMobileClient.class.getResourceAsStream("client.p12"), CLIENT_PASSWORD);
            mgrFact.init(clientStore, CLIENT_PASSWORD);

            // set up a trust manager so we can recognize the server
            TrustManagerFactory trustFact = TrustManagerFactory.getInstance("SunX509");
            KeyStore trustStore = KeyStore.getInstance("JKS");

            trustStore.load(VBCMobileClient.class.getResourceAsStream("trustStore.jks"), TRUST_STORE_PASSWORD);
            trustFact.init(trustStore);
            sslContext = SslContextBuilder.forClient().
                    sslProvider(SslProvider.JDK).
                    protocols("TLSv1.3").
                    keyManager(mgrFact).
                    trustManager(trustFact).
                    clientAuth(ClientAuth.REQUIRE).
                    build();
        } catch(IOException | NoSuchAlgorithmException | KeyStoreException | CertificateException | UnrecoverableKeyException ioe) {
            ioe.printStackTrace();
        }
        return sslContext;

    }

}
