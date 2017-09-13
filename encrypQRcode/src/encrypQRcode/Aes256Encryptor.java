package encrypQRcode;


import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import encrypQRcode.Hex;

//import ex.HexUtil;

public class Aes256Encryptor {
	private static final byte[] key = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};

    private static final String transform = "AES/CBC/NoPadding";

    private static final String algorithm = "AES";

    private static final SecretKeySpec keySpec = new SecretKeySpec(key, algorithm);

    public static void main(String[] args) throws Exception {

         String pwds[] = { "123", "0123456789012345", "01234567890123456", "123", "123", "0123456789012345678",

                            "012345678901234567890123456789", "b", "0123456789012345", "01234567890123456", "012345678901234567" };

        String ivss[] = { "test", "test", "test", "test0123456789012", "test01234567890123", "test", "test", "a",

                        "test", "test", "test" };

        String rr[] = new String[ivss.length];

         for (int i = 0; i < ivss.length; i++) {

            String en = encrypt(pwds[i], ivss[i]);

            String decy = decrypt(en, ivss[i]);

            rr[i] = "[" + ivss[i] + "],[" + decy + "]-->[" + en + "]";

            System.out.println(rr[i]);

           }

           System.out.println("---------");

           for (int i = 0; i < rr.length; i++) {

               System.out.println(rr[i]);

            }

}

/**

*/

public static String decrypt(String en, String pIv) throws Exception {

        Cipher cipher = Cipher.getInstance(transform);

        byte[] encryptedBytes = Hex.decodeHex(en);

        byte[] iv = createIV(pIv);

        cipher.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv));

        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

        System.arraycopy(decryptedBytes, 0, encryptedBytes, 0, encryptedBytes.length);

        String result = new String(encryptedBytes);

        return result.trim();

}

/**

*/

public static String encrypt(String pData, String pIv) throws Exception {

         Cipher cipher = Cipher.getInstance(transform);

         byte[] iv = createIV(pIv);

         cipher.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv));

         byte[] output = cipher.doFinal(paddingData(pData));

         byte[] encryptedContent = new byte[output.length];

         System.arraycopy(output, 0, encryptedContent, 0, encryptedContent.length);

         String result = new String(Hex.encodeHex(encryptedContent)).toUpperCase();

         return result;

}

/**

* 补齐的16位的整数倍

*

* @param pData

* @return

*/

private static byte[] paddingData(String pData) {

       byte[] bytes = pData.getBytes();

       int length = bytes.length / 16;

       if (length * 16 < bytes.length) {

          length++;

        }

       byte[] result = new byte[length * 16];

       System.arraycopy(bytes, 0, result, 0, bytes.length);

       for (int i = bytes.length; i < result.length; i++) {

         result[i] = 0x00;

        }

        return result;

        }

/**

* 初始化向量到16位

* */

       private static byte[] createIV(String pIv) throws UnsupportedEncodingException {

       byte[] bytes = pIv.getBytes("US-ASCII");

       int length = bytes.length / 16;

       if (length * 16 < bytes.length) {

       length++;

       }

       byte[] result = new byte[16];

       System.arraycopy(bytes, 0, result, 0, bytes.length > 16 ? 16 : bytes.length);

       for (int i = bytes.length; i < result.length; i++) {

       result[i] = 0x00;

      }

      return result;

}


}

