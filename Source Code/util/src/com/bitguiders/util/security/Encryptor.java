package com.bitguiders.util.security;

import java.io.IOException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Encryptor {
	
	public String encrypt(String str){
		BASE64Encoder enc=new BASE64Encoder();
		
		return enc.encode(str.getBytes());
	}
	
	public String decrypt(String str) throws IOException{
		BASE64Decoder dec=new BASE64Decoder();
		return new String(dec.decodeBuffer(str));
	}
	public String[] decrypt(String str,String splitter) throws IOException{
		
		return decrypt(str).split(splitter.trim());
	}

	public static void main(String[] arg ) throws IOException{
		Encryptor en = new Encryptor();
	    
	  String str = "admin;admin";
	  System.out.println(str);
	  str = en.encrypt(str);
	  System.out.println(str);
	  String vals[]= en.decrypt(str,";");
	  System.out.println(vals[0]+" "+vals[1]);
	}

}
