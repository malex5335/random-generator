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
	private static final float RAND_CONST = 0.167298f;

	@Getter
	private final long seed;
	private final float randSalt;
	private float next;

	private CustomRandom(long seed) {
		this.seed = seed;
		this.randSalt = seed / (seed * 5f) * RAND_CONST;
		this.next = randSalt;
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
		return (int) (number % 1 >= 0.5 ? 1 + number : number);
	}

	public float nextFloat() {
		next = (next * (RAND_CONST + randSalt));
		while (next < RAND_MAX){
			// TODO: find a math solution for this
			next *= 10 + RAND_MAX;
		}
		return next % RAND_MAX;
	}
}
