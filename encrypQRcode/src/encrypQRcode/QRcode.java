package encrypQRcode;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

public class QRcode {
	public String content;
	public String path= "D:/";
	public QRcode(byte[] data){
		content=new String(data);	
	}
	public void getQRcode(){
		try {
		     
		     MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
		     
		     Map hints = new HashMap();
		     hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		     BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 400, 400,hints);
		     File file1 = new File(path,"IMG.png");
		     MatrixToImageWriter.writeToFile(bitMatrix, "jpg", file1);
		     
		 } catch (Exception e) {
		     e.printStackTrace();
		 }
	}
}
