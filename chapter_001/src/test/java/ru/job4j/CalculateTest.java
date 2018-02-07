package ru.job4j;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test
*
* Of class Calculate решение задачи части 001 урок 1
* @author Chisty Arseny
* @since 06.02.2018
*/


public class CalculateTest {

	/**
	* Тест для метода main
	* @Test
	*/

	public void whenTakeNameThenTreeEchoPlusName () {
		String input = "Chisty Arseny";
		String expect = "Echo, echo, echo:Chisty Arseny";
		Calculate calc = new Calculate();
		String result = calc.echo(input);
		assertThat(result, is(expect));
	}
}