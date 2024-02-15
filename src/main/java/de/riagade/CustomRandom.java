package de.riagade;

import lombok.*;

import java.util.stream.*;

public class CustomRandom {

	public static final String uppercase_letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String lowercase_letters = "abcdefghijklmnopqrstuvwxyz";
	public static final String numbers = "0123456789";
	public static final String special_letters = "ß?+*~#'-_.:,;<|>!\"§$%&/()=}][{\\";
	public static final String all_characters = uppercase_letters + lowercase_letters + numbers + special_letters;
	public static final String hex_characters = "ABCDEF" + numbers;
	private static final float RAND_MAX = 1f;
	private static final float RAND_SALT = 12f;
	private static final float RAND_CONST = 0.467798f;

	@Getter
	private final long seed;
	private float next;

	private CustomRandom(long seed) {
		this.seed = seed;
		this.next = seed / (seed * 5.2f);
	}

	public static CustomRandom create() {
		return create(1);
	}

	public static CustomRandom create(long seed) {
		return new CustomRandom(seed);
	}

	public String nextString(int length) {
		return nextString(length, all_characters);
	}

	public String nextString(int length, String characters) {
		return IntStream.range(0,length)
				.mapToObj(i -> nextChar(characters.toCharArray()))
				.map(String::valueOf)
				.collect(Collectors.joining());
	}

	public char nextChar(char[] options) {
		return options[nextInt(options.length - 1)];
	}

	public int nextInt(int max) {
		return nextInt(0, max);
	}

	public int nextInt(int min, int max) {
		return roundUp( (max - min) * nextFloat()) + min;
	}

	private int roundUp(float number) {
		return ((int) number + (int) (number % 1) * 2);
	}

	public float nextFloat() {
		return next = (next * (RAND_CONST + RAND_SALT)) % RAND_MAX;
	}

	public String nextUUID() {
		return "{%s-%s-%s-%s-%s}".formatted(
				nextString(8, hex_characters),
				nextString(4, hex_characters),
				nextString(4, hex_characters),
				nextString(4, hex_characters),
				nextString(12, hex_characters)
		);
	}
}
