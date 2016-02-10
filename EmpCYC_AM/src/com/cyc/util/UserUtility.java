package com.cyc.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * 
 *  Utility class for performing common operations  
 * 
 */

public class UserUtility {

	public String getDate(){
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy  hh:mm:ss");
		System.out.println("Date:::"+df.format(date));
		return df.format(date);
	}
}
