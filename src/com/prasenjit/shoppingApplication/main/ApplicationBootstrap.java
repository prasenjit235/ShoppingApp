/**
 * This is the Bootstrap class of Application. When the Shopping App is started, 
 * this class will load all the necessary properties from properties file.
 * This application can be started with two options as of now. 
 * 1) With providing a properties file which will contain the File Names 
 * e.g AppMain.properties contains the following property 
 * SHOPPINGAPP.FILE_NAMES=input/input1,input/input2 , where SHOPPINGAPP.FILE_NAMES
 * will hold ',' separated file names. 
 * 2) With providing all the files as input.
 * In both the cases the files  has to be  stored under 'resources'
 * directory. ShoppingCart will be created for each files and Item will created for 
 * each ShoppingCart . Then ShoppingCart will be processed and all the calculation 
 * will be done and the receipts will be prepared for each ShoppingCart.
 *  
 * @author prasenjit das 
 */
package com.prasenjit.shoppingApplication.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.prasenjit.shoppingApplication.module.shoppingcart.ShoppingCartFactory;
import com.prasenjit.shoppingApplication.module.shoppingcart.ShoppingList;
import com.prasenjit.shoppingApplication.service.CashCounterServiceImpl;
import com.prasenjit.shoppingApplication.service.ICashCounterService;
import com.prasenjit.shoppingApplication.types.ApplicationLaunchType;
import com.prasenjit.shoppingApplication.types.ItemCategories;
import com.prasenjit.shoppingApplication.utils.IApplicationConstants;
import com.prasenjit.shoppingApplication.utils.Utilities;

public class ApplicationBootstrap {

	private Properties applicationProperties = null;

	/**
	 * 
	 * @param args - Command line arguments. 
	 * arg[0]- '0' then the Application will be launched using
	 * properties file.
	 * arg[0]- '1' then the Application will be launched using
	 * file names as argument.  
	 * @throws IOException
	 */
	private void load(String[] args) throws IOException {
		if (args.length == 0) {
			System.err.println("USAGE: "
					+ ApplicationBootstrap.class.getCanonicalName()
					+ " <Launch Type Value>");
			System.err
					.println("<Launch Type Value> can be 0 or 1. 0 for Input from AppMain.properties file , 1 for File Name as input ");
			System.exit(0);
		}

		if (Integer.parseInt(args[0]) == (ApplicationLaunchType.PropertyConfigurationBased
				.ordinal())) {
			applicationProperties = loadProperties(args.length > 1 ? args[1]
					: IApplicationConstants.APP_MAIN + ".properties");
		} else if (Integer.parseInt(args[0]) == (ApplicationLaunchType.FileInputBased
				.ordinal())) {
			applicationProperties = loadProperties(args);
		}

		start();
	}

	/**
	 * Registers all the items to Categories BOOKS,FOOD,MEDICAL,OTHERS.
	 * Get the file names from property 'SHOPPINGAPP.FILE_NAMES' and 
	 * for each file name ShoppingCart with ShoppingList as argument.
	 * Then process each ShoppingCart and generates receipt using ICashCounterService.
	 * 
	 * @throws IOException
	 */
	private void start() throws IOException {
		if (applicationProperties == null) {
			System.err
					.println("Cannot start application . Not sufficient params.");
		}
		ICashCounterService cashCounterService = new CashCounterServiceImpl();
		ItemCategories
				.registerItems(applicationProperties
						.getProperty(IApplicationConstants.BOOKS),
						ItemCategories.BOOKS);
		ItemCategories.registerItems(applicationProperties
				.getProperty(IApplicationConstants.FOOD), ItemCategories.FOOD);
		ItemCategories.registerItems(applicationProperties
				.getProperty(IApplicationConstants.MEDICAL),
				ItemCategories.MEDICAL);
		ItemCategories.registerItems(applicationProperties
				.getProperty(IApplicationConstants.OTHERS),
				ItemCategories.OTHERS);
		float roundUpValue = Float.parseFloat(applicationProperties
				.getProperty(IApplicationConstants.SALES_TAX_ROUND_UP_VALUE,
						".05"));
		Utilities.setRoundUpValue(roundUpValue);
		String[] fileNames = applicationProperties.getProperty(
				IApplicationConstants.FILE_NAMES).split(",");
		for (int i = 0; i < fileNames.length; i++) {
			cashCounterService
					.processShoppingcartAndGenerateReciept(ShoppingCartFactory
							.createShoppingCart(new ShoppingList(
									IApplicationConstants.APP_RESOURCES + "/"
											+ fileNames[i])));
		}
	}

	/**
	 * Loads all the properties from user defined property file. If 
	 * the stream is null then it loads from AppMain.properties file.
	 * 
	 * @param appMain
	 * @return
	 * @throws IOException
	 */
	private Properties loadProperties(String appMain) throws IOException {
		Properties properties = new Properties();
		InputStream inputStream = new FileInputStream(new File(
				IApplicationConstants.APP_RESOURCES + "/" + appMain));
		if (inputStream == null) {
			inputStream = new FileInputStream(new File(
					IApplicationConstants.APP_RESOURCES + "/"
							+ IApplicationConstants.APP_MAIN + ".properties"));
		}
		properties.load(inputStream);

		return properties;
	}

	/**
	 * Overloaded method of loadProperties(String appMain). The file names
	 * passed as arguments are loaded as properties. 
	 * @param strings
	 * @return
	 * @throws IOException
	 */
	private Properties loadProperties(String[] strings) throws IOException {
		Properties properties = null;
		if (strings.length > 1) {
			properties = this.loadProperties(IApplicationConstants.APP_MAIN
					+ ".properties");
			StringBuilder builder = new StringBuilder();
			for (int i = 1; i < strings.length; i++) {
				if (i < strings.length - 1) {
					builder.append(strings[i]).append(",");
				} else {
					builder.append(strings[i]);
				}
			}
			properties
					.put(IApplicationConstants.FILE_NAMES, builder.toString());
		}
		return properties;
	}

	/**
	 * Creates ApplicationBootstrap and loads it.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			new ApplicationBootstrap().load(args);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @return
	 */
	public Properties getApplicationProperties() {
		return applicationProperties;
	}
}
