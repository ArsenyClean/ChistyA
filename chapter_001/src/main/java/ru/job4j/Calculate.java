package ru.job4j;

import org.junit.Before;

import java.util.*;

/**
 * Class Calculate решение задачи части 001 урок 1.
 * @author Chisty Arseny.
 * @since 07.02.2018.
 */


public class Calculate implements Comparable<Integer> {

	/**
	 *	Method echo.
	 *	@param name Your name.
	 *	@return Echo plus your name.
	 */
    @Before
	public static String echo(String name) {
		return ("Echo, echo, echo:" + name);
	}

	/**
	 * Конструктор, вывод результата echo в консоль.
	 * @param args - args.
	 */

	public static void main(String[] args) throws NullPointerException {

		Set<Integer> value = new TreeSet<>();
		value.add(1);
		value.add(2);
		value.add(1);
		value.add(4);
		System.out.println(value);


		System.out.println(value);
		System.out.println(echo("Chisty Arseny"));
	}

	@Override
	public int compareTo(Integer o) {
		return this.compareTo(o);
	}
}