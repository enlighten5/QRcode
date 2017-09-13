package encrypQRcode;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class encrypQRcode {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		EncrypAES aes = new EncrypAES();
		String msg ="abc";//明文信息
		byte[] encontent = aes.Encrytor(msg);
		String data=new String(encontent);
		//int a=Integer.parseInt(data);
		System.out.println("++++++++"+encontent);
		QRcode QR=new QRcode(encontent);
		QR.getQRcode();

	}

}
