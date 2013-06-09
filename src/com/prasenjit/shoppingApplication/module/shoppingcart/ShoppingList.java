/**
 * This class stores the file and reads a line when 
 * requested. The logic is , ShoppingList contains 
 * the file which has all the items to be created and 
 * added in ShoppingCart.
 * @author prasenjit das
 */
package com.prasenjit.shoppingApplication.module.shoppingcart;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class ShoppingList {

	private String file;
	private BufferedReader br;

	/**
	 * 
	 * @param file
	 * @throws FileNotFoundException
	 */
	public ShoppingList(String file) throws FileNotFoundException {
		this.file = file;
		InputStream fis = new FileInputStream(file);
		br = new BufferedReader(new InputStreamReader(fis, Charset
				.forName("UTF-8")));
	}

	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	public String readNext() throws IOException {
		return br.readLine();
	}

	/**
	 * 
	 * @throws IOException
	 */
	public void listDone() throws IOException {
		br.close();
	}
}
