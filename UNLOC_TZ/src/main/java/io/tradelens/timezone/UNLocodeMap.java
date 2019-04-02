package io.tradelens.timezone;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class UNLocodeMap {
	static Map<String, String> unlocMap = new HashMap<String, String>();
	 
	public static Boolean checkForUNLocode(String UNLocode) {
		return unlocMap.containsKey(UNLocode);
	}
	
	public static String getCrapLatLong(String UNLocode) {
		return UNLocode;	

	
//		static {
//			unlocMap.put("ADALV", "4230N 00131E");
//			unlocMap.put("ADCAN", "4234N 00135E");
//			unlocMap.put("ADEAC", "4233N 00131E");
//		}
	}
}
