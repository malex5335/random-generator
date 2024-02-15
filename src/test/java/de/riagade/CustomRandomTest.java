package de.riagade;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class RandomTest {

	static Random random = Random.create();

	@RepeatedTest(value = 150)
	void rands() {
		// Given
		var length = 15;

		// When
		var result = random.rands(length);

		// Then
		System.out.println(result);
		assertEquals(length, result.length());
	}

	@RepeatedTest(value = 150)
	void randi() {
		// Given
		var min = 1;
		var max = 100;

		// When
		var result = random.randi(min, max);

		// Then
		System.out.println(result);
		assertTrue(result >= min);
		assertTrue(result <= max);
	}

	@RepeatedTest(value = 150)
	void randf() {
		// Given
		var min = 0;
		var max = 1;

		// When
		var result = random.randf();

		// Then
		System.out.println(result);
		assertTrue(result >= min);
		assertTrue(result <= max);
	}
}