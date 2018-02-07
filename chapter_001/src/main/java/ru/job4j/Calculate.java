package ru.job4j;

/**
* Class Calculate решение задачи части 001 урок 1
* @author Chisty Arseny
* @since 07.02.2018
*/


public class Calculate {

	/**
	*	Method Echo
	*	@param name Your name
	*	@return Echo plus your name
	*/
	
	public static String echo(String name){
		return "Echo, echo, echo:" + name;
	}
	
	/**
	* Конструктор, вывод результата echo в консоль.
	* @param arg -arg
	*/

	public static void main(String [] args){
		System.out.println(echo("Chisty Arseny"));
	}
}