/**
 * @author prasenjit das
 */
package com.prasenjit.shoppingApplication.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;


public class Utilities {

	private static int outputFileCounter=1;
	private static float roundUpValue=100.0f;
	/**
	 * 
	 * @param items
	 * @throws IOException
	 */
	public static void generateReciept(List<String> items) throws IOException {
	    FileWriter fstream = new FileWriter(IApplicationConstants.APP_RESOURCES+"/"+IApplicationConstants.APP_OUTPUT+"/"+IApplicationConstants.APP_OUTPUT+outputFileCounter+".txt");
        BufferedWriter out = new BufferedWriter(fstream);
        try{
        out.write("OUTPUT "+outputFileCounter+" :");	
        out.newLine();
        for (String string : items) {
        	out.write(string);
        	out.newLine();
        	out.flush();
		}
        outputFileCounter++;
        }finally{
        	if (out!=null) {
				out.close();
			}
        }
	}
	/**
	 * 
	 * @param value
	 * @param roundUpValue
	 * @return
	 */
	public static float format(float value,float roundUpValue) {
		return (float) (Math.ceil( value / roundUpValue ) * roundUpValue);
	}
	/**
	 * 
	 * @param value
	 * @return
	 */
	public static float format(float value) {
		return format(value, roundUpValue);
	}
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	public static String formatNumber(float value) {
		DecimalFormat decimalFormat=new DecimalFormat("###,##0.00");
		return decimalFormat.format(value);
	}
	/**
	 * 
	 * @return
	 */
	public static double getRoundUpValue() {
		return roundUpValue;
	}
	/**
	 * 
	 * @param roundUpValue
	 */
	public static void setRoundUpValue(float roundUpValue) {
		Utilities.roundUpValue = roundUpValue;
	}
}
