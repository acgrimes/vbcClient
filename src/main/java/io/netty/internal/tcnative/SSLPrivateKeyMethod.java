package io.netty.internal.tcnative;

/**
 * Allows to customize private key signing / decrypt (when using RSA).
 */
public interface SSLPrivateKeyMethod {
    int SSL_SIGN_RSA_PKCS1_SHA1 = NativeStaticallyReferencedJniMethods.sslSignRsaPkcsSha1();
    int SSL_SIGN_RSA_PKCS1_SHA256 = NativeStaticallyReferencedJniMethods.sslSignRsaPkcsSha256();
    int SSL_SIGN_RSA_PKCS1_SHA384 = NativeStaticallyReferencedJniMethods.sslSignRsaPkcsSha384();
    int SSL_SIGN_RSA_PKCS1_SHA512 = NativeStaticallyReferencedJniMethods.sslSignRsaPkcsSha512();
    int SSL_SIGN_ECDSA_SHA1 = NativeStaticallyReferencedJniMethods.sslSignEcdsaPkcsSha1();
    int SSL_SIGN_ECDSA_SECP256R1_SHA256 = NativeStaticallyReferencedJniMethods.sslSignEcdsaSecp256r1Sha256();
    int SSL_SIGN_ECDSA_SECP384R1_SHA384 = NativeStaticallyReferencedJniMethods.sslSignEcdsaSecp384r1Sha384();
    int SSL_SIGN_ECDSA_SECP521R1_SHA512 = NativeStaticallyReferencedJniMethods.sslSignEcdsaSecp521r1Sha512();
    int SSL_SIGN_RSA_PSS_RSAE_SHA256 = NativeStaticallyReferencedJniMethods.sslSignRsaPssRsaeSha256();
    int SSL_SIGN_RSA_PSS_RSAE_SHA384 = NativeStaticallyReferencedJniMethods.sslSignRsaPssRsaeSha384();
    int SSL_SIGN_RSA_PSS_RSAE_SHA512 = NativeStaticallyReferencedJniMethods.sslSignRsaPssRsaeSha512();
    int SSL_SIGN_ED25519 = NativeStaticallyReferencedJniMethods.sslSignEd25519();
    int SSL_SIGN_RSA_PKCS1_MD5_SHA1 = NativeStaticallyReferencedJniMethods.sslSignRsaPkcs1Md5Sha1();

    /**
     * Sign the input with given EC key and returns the signed bytes.
     *
     * @param ssl                   the SSL instance
     * @param signatureAlgorithm    the algorithm to use for signing
     * @param input                 the input itself
     * @return                      the sign
     * @throws Exception            thrown if an error accours while signing.
     */
    byte[] sign(long ssl, int signatureAlgorithm, byte[] input) throws Exception;

    /**
     * Decrypts the input with the given RSA key and returns the decrypted bytes.
     *
     * @param ssl                   the SSL instance
     * @param input                 the input which should be decrypted
     * @return                      the decrypted data
     * @throws Exception            thrown if an error accours while decrypting.
     */
    byte[] decrypt(long ssl, byte[] input) throws Exception;
}