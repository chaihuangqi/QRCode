package test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.xxd.qrcode.util.QRCoder;

public class QRCoderImageTest {

	public String imgPath = System.getProperty("user.dir")
            + "/WebRoot/upload/www1.png";
 
    @Test
    public void testencoderQRCode() {
 
        String content = "http://www.xinxindai.com/m/static/html/activity/merchantReg.html?pCode=mcdonalds_mzgc";
        int size =7;
        QRCoder qr = new QRCoder();
        try {
            qr.encoderQRCode(content, imgPath, "png", size);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
 
    @Test
    public void testdecoderQRCode() {
 
        QRCoder qr = new QRCoder();
        String con = "";
        try {
            con = qr.decoderQRCode(imgPath);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(con);
    }

}
