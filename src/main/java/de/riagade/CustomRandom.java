package de.riagade;

import lombok.*;

import java.util.stream.*;

@Getter
public class Random {

	private static final String uppercase_letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String lowercase_letters = "abcdefghijklmnopqrstuvwxyz";
	private static final String numbers = "0123456789";
	private static final String special_letters = "ß?+*~#'-_.:,;<|>!\"§$%&/()=}][{\\";
	private static final String all_characters = uppercase_letters + lowercase_letters + numbers + special_letters;
	private static final float RAND_MAX = 1f;
	private static final float RAND_CONST = 0.167298f;

	private final long seed;
	private final float randSalt;
	private float next;

	private Random(long seed) {
		this.seed = seed;
		this.randSalt = seed / (seed * 5f) * RAND_CONST;
		this.next = randSalt;
	}

	public static Random create() {
		return create(System.currentTimeMillis());
	}
	public static Random create(long seed) {
		return new Random(seed);
	}

	public String rands(int length) {
		return rands(length, all_characters);
	}
	public String rands(int length, String characters) {
		return IntStream.range(0,length)
				.mapToObj(i -> randc(characters.toCharArray()))
				.map(String::valueOf)
				.collect(Collectors.joining());
	}

	public char randc(char[] options) {
		return options[randi(options.length - 1)];
	}

	public int randi(int max) {
		return randi(0, max);
	}

	public int randi(int min, int max) {
		return roundUp( (max - min) * randf()) + min;
	}

	private int roundUp(float number) {
		return (int) (number % 1 >= 0.5 ? 1 + number : number);
	}

	public float randf() {
		next = (next * (RAND_CONST + randSalt));
		while (next < RAND_MAX){
			next *= 10 + RAND_MAX;
		}
		return next % RAND_MAX;
	}
}
