package com.cirb.archive.config.security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * An implementation of PGP encryption (PGP = Pretty Good Privacy)
 */
public final class JavaPGP {

  private static final String ALGORITHM = "AES";
  private static final String KEY = "ADBSJHJS12547896";
  private static SecretKey AESKey;

  /**
   * The message is created like so: - Generates a random KeyPair - Encrypt the
   * message with the private key from the generated key pair - Encrypt the
   * generated public key with given public key
   *
   * @param message The message to encrypt
   * @return The encrypted message
   * @throws GeneralSecurityException
   * @throws UnsupportedEncodingException
   */
  public String encrypt(String message) throws GeneralSecurityException, UnsupportedEncodingException {

    SecretKey secAESKey = AESKey;
//    Security.setProperty("crypto.policy", "unlimited");
    Cipher cipher = Cipher.getInstance("AES");
    cipher.init(Cipher.ENCRYPT_MODE, secAESKey);
    byte[] result = cipher.doFinal(message.getBytes("UTF-8"));

    return new String(Base64.getEncoder().encodeToString(result));
  }

  /**
   * The message is decrypted like so: - Read the encrypted public key - Decrypt
   * the public key with the private key - Read the encrypted message - Use the
   * decrypted public key to decrypt the encrypted message
   *
   * @param message The encrypted message
   * @param key     The private key
   * @return The decrypted message
   * @throws GeneralSecurityException
   */
  public String decrypt(String message, SecretKey key) throws GeneralSecurityException {
    Cipher cipher = Cipher.getInstance("AES");
    cipher.init(Cipher.DECRYPT_MODE, key);
    byte[] result = cipher.doFinal(Base64.getDecoder().decode(message.getBytes()));
    return new String(result);
  }

  public static SecretKey generateAESKey(byte[] key) throws NoSuchAlgorithmException {
    return new SecretKeySpec(key, ALGORITHM);
  }

  public SecretKey getAESKey() {
    return AESKey;
  }

  public static JavaPGP getInstance() throws NoSuchAlgorithmException {
    AESKey = generateAESKey(KEY.getBytes());
    return new JavaPGP();
  }

  private JavaPGP() {

  }

}
