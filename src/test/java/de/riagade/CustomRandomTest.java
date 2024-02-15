package de.riagade;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class CustomRandomTest {

	static CustomRandom random = CustomRandom.create();

	@RepeatedTest(value = 150)
	void nextString() {
		// Given
		var length = 15;

		// When
		var result = random.nextString(length);

		// Then
		System.out.println(result);
		assertEquals(length, result.length());
	}

	@RepeatedTest(value = 150)
	void nextString_custom_chars() {
		// Given
		var length = 16;
		var chars = CustomRandom.hex_characters;
		var hexRegex = "[0-9A-F]+";

		// When
		var result = random.nextString(length, chars);

		// Then
		System.out.println(result);
		assertEquals(length, result.length());
		assertTrue(result.matches(hexRegex));
	}

	@RepeatedTest(value = 150)
	void nextInt() {
		// Given
		var min = 1;
		var max = 100;

		// When
		var result = random.nextInt(min, max);

		// Then
		System.out.println(result);
		assertTrue(result >= min);
		assertTrue(result <= max);
	}

	@RepeatedTest(value = 150)
	void nextFloat() {
		// Given
		var min = 0;
		var max = 1;

		// When
		var result = random.nextFloat();

		// Then
		System.out.println(result);
		assertTrue(result >= min);
		assertTrue(result <= max);
	}

	@RepeatedTest(value = 150)
	void nextUUID() {
		// When
		var result = random.nextUUID();

		// Then
		System.out.println(result);
		assertTrue(result.matches("\\{\\p{XDigit}{8}-\\p{XDigit}{4}-\\p{XDigit}{4}-\\p{XDigit}{4}-\\p{XDigit}{12}}"));
	}
}