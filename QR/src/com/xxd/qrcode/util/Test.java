package com.xxd.qrcode.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Test {
	private static String path = "http://www.xinxindai.com/m/static/html/activity/storeRegQuery.html?pCode=";
	private static String qrcodePath = System.getProperty("user.dir")
			+ "/WebRoot/qrcode_ds_men/";
	private static String logoPath = System.getProperty("user.dir")
			+ "/WebRoot/logo_ds_men/";
	private static String textPath = System.getProperty("user.dir")
			+ "/WebRoot/text_ds_men/";
	private static String jsonPaht = System.getProperty("user.dir") + "\\qrcode-ds.json";

	public static void main(String[] args) {
		 //createQRcode();
		//addLogo();
		addText();
	}
	
	public static void addText() {
		File file = new File(logoPath);
		File[] tempList = file.listFiles();
		System.out.println("该目录下有文件数：" + tempList.length);
		File tempFile = null;
		String str = ReadFile(jsonPaht);
		JSONArray jsonArr = JSONArray.parseArray(str);
		
		for (int i = 0; i < tempList.length; i++) {
			tempFile = tempList[i]; 
			if (tempFile.isFile()) {
				System.out.println((i+1)+"文     件：" + tempFile);	
				
				String srcImgPath = tempFile.getPath();			
				String fileName =  tempFile.getName();
				String targerPath = textPath + "/" +fileName;
				String pCode = fileName.substring(0, fileName.indexOf(".png"));
				JSONObject json = findJSON(jsonArr,pCode);
				//System.out.println("targerPath="+);
				// 给图片添加水印
				ImageUtil.waterMarkByText(json.getString("name")+"(商)", srcImgPath, targerPath, 0, json.getIntValue("textWidth")-40, 
						json.getIntValue("textHegith"), 1f,json.getIntValue("clearX")-40,json.getIntValue("clearY"), json.getInteger("clearWidth"), json.getIntValue("clearHeight"));
			}			
		}		
	}
	
	public static JSONObject findJSON(JSONArray jsonArr,String pCode) {
		for (int i = 0; i < jsonArr.size(); i++) {
			JSONObject json = jsonArr.getJSONObject(i);
			if(pCode.equalsIgnoreCase(json.getString("pCode"))){
				return json;
			}
		}
		return null;
	}

	public static void addLogo() {
		
		File file = new File(qrcodePath);
		File[] tempList = file.listFiles();
		System.out.println("该目录下有文件数：" + tempList.length);
		File tempFile = null;
		String iconPath = "E:\\AllProject22\\QRCode\\WebRoot\\upload\\logo3.png";
		for (int i = 0; i < tempList.length; i++) {
			tempFile = tempList[i]; 
			if (tempFile.isFile()) {
				System.out.println((i+1)+"文     件：" + tempFile);	
				
				String srcImgPath = tempFile.getPath();			
				String targerPath = logoPath + "/" + tempFile.getName();
				//System.out.println("targerPath="+targerPath);
				// 给图片添加水印
				ImageUtil.waterMarkImageByIcon(iconPath, srcImgPath, targerPath, 0, 220,220, 1f);
				//String targerPath1 = "E:\\AllProject22\\QRCode\\WebRoot\\upload2\\www1.png";
				//ImageUtil.waterMarkByText("麦当劳南城市中心广场店", targerPath, targerPath1, 0, 310, 590, 1f,300,560, 320, 40);
			}
			
		}		
	}

	public static void createQRcode() {
		String str = ReadFile(jsonPaht);
		JSONArray jsonArr = JSONArray.parseArray(str);
		int size = 7;
		QRCoder qr = new QRCoder();
		for (int i = 0; i < jsonArr.size(); i++) {
			JSONObject json = jsonArr.getJSONObject(i);
			String pCode = json.getString("pCode");
			String content = path + pCode;

			String imgPath = qrcodePath + pCode + ".png";
			try {
				qr.encoderQRCode(content, imgPath, "png", size);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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
