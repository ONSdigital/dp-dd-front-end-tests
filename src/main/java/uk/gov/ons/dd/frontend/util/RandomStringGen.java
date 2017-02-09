package uk.gov.ons.dd.frontend.util;


import java.util.Random;


public class RandomStringGen {
	static final String letters_numbers = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	static Random random = new Random();

	public static String getRandomString(int lengthToBuild) {
		StringBuilder stringBuilder = new StringBuilder(lengthToBuild);
		for (int i = 0; i < lengthToBuild; i++)
			stringBuilder.append(letters_numbers.charAt(random.nextInt(letters_numbers.length())));
		return stringBuilder.toString();
	}

	public static int getRandomInt(int lengthToBuild) {
		return random.nextInt(lengthToBuild);
	}

}
