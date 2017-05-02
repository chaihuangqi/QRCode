

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.alibaba.fastjson.JSONObject;

public class Test {
	private static String path = "http://www.xinxindai.com/m/static/html/activity/merchantReg.html?pCode=";

	public static void main(String[] args) {
		addLogo();
	}

	public static void addLogo() {
		JSONObject json = new JSONObject();
		
		String str  = ReadFile("/qrcode.json");
		System.out.println(str);

		/*String content = path + "mcdonalds_mzgc";
		int size = 7;
		QRCoder qr = new QRCoder();
		String imgPath = System.getProperty("user.dir")
				+ "/WebRoot/upload/www1.png";
		try {
			qr.encoderQRCode(content, imgPath, "png", size);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

	public static String ReadFile(String Path) {
		BufferedReader reader = null;
		String laststr = "";
		try {
			FileInputStream fileInputStream = new FileInputStream(Path);
			InputStreamReader inputStreamReader = new InputStreamReader(
					fileInputStream, "UTF-8");
			reader = new BufferedReader(inputStreamReader);
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				laststr += tempString;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return laststr;
	}

}
