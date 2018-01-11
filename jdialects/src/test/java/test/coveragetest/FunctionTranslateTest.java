/*
 * jDialects, a tiny SQL dialect tool
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later. See
 * the lgpl.txt file in the root directory or
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */
package test.coveragetest;

import org.junit.Test;

import com.github.drinkjava2.jdialects.Dialect;

/**
 * 
 * @author Yong Z.
 * @since 1.0.6
 *
 */
public class FunctionTranslateTest {

	@Test
	public void doDialectTest() {

		System.out.println(
				Dialect.MySQL55Dialect.paginate(3, 10, "select concat('a','b','c'), current_time() from user_tb"));
		System.out.println(
				Dialect.Oracle12cDialect.paginate(3, 10, "select concat('a','b','c'), current_time() from user_tb"));
		System.out.println(Dialect.SQLServer2005Dialect.paginate(1, 10,
				"select concat('a','b','c'), current_time() from user_tb"));

		System.out.println("============================================");

		System.out
				.println(Dialect.MySQL55Dialect.translate("select concat('a','b','c'), current_time()   from user_tb"));
		System.out.println(
				Dialect.Oracle12cDialect.translate("select concat('a','b','c'), current_time()  from user_tb"));
		System.out.println(
				Dialect.SQLServer2008Dialect.translate("select concat('a','b','c'), current_time()  from user_tb"));

		System.out.println("============================================");

		System.out.println(Dialect.MySQL55Dialect.paginAndTranslate(3, 10,
				"select concat('a','b','c'), current_time() from user_tb"));
		System.out.println(Dialect.Oracle12cDialect.paginAndTranslate(3, 10,
				"select concat('a','b','c'), current_time() from user_tb"));
		System.out.println(Dialect.SQLServer2005Dialect.paginAndTranslate(1, 10,
				"select concat('a','b','c'), current_time() from user_tb"));
	}

	@Test
	public void doPrefixTest() {
		Dialect.setSqlFunctionPrefix("#"); // Default is null
		String result = Dialect.MySQL55Dialect.translate(
				"Select username, #concat(#second(#second(99)),'a', #second(20) ), #current_time(), #PI(), #concat('a', b, c) as b from usertable as tb");
		System.out.println(result);

		result = Dialect.SQLiteDialect.translate(
				"Select username, #concat(#second(#second(99)),'a', #second(20) ),   #concat('a', b, c) as b from usertable as tb");
		System.out.println(result);

		result = Dialect.MySQL55Dialect.paginAndTranslate(2, 10,
				"Select username, #concat(#second(#second(99)),'a', #second(20) ), #current_time(), #PI(), #concat('a', b, c) as b from usertable as tb");
		System.out.println(result);

		result = Dialect.SQLiteDialect.paginAndTranslate(2, 10,
				"Select username, #concat(#second(#second(99)),'a', #second(20) ),   #concat('a', b, c) as b from usertable as tb");
		System.out.println(result);

		System.out.println("============================================");

		result = Dialect.MySQL55Dialect.translate(
				"Select username, concat(second(second(99)),'a', second(20) ), current_time(), PI(), concat('a', b, c) as b from usertable as tb");
		System.out.println(result);

		result = Dialect.SQLiteDialect.translate(
				"Select username, concat(second(second(99)),'a', second(20) ),   concat('a', b, c) as b from usertable as tb");
		System.out.println(result);

		result = Dialect.MySQL55Dialect.paginAndTranslate(2, 10,
				"Select username, concat(second(second(99)),'a', second(20) ), current_time(), PI(), concat('a', b, c) as b from usertable as tb");
		System.out.println(result);

		result = Dialect.SQLiteDialect.paginAndTranslate(2, 10,
				"Select username, concat(second(second(99)),'a', second(20) ),   concat('a', b, c) as b from usertable as tb");
		System.out.println(result);

		System.out.println("============================================");

		Dialect.setSqlFunctionPrefix("$fn_");
		result = Dialect.MySQL55Dialect.translate(
				"Select username, $fn_concat($fn_second($fn_second(99)),'a', $fn_second(20) ), $fn_current_time(), $fn_PI(), $fn_concat('a', b, c) as b from usertable as tb");
		System.out.println(result);

		result = Dialect.SQLiteDialect.translate(
				"Select username, $fn_concat($fn_second($fn_second(99)),'a', $fn_second(20) ),   $fn_concat('a', b, c) as b from usertable as tb");
		System.out.println(result);

		result = Dialect.MySQL55Dialect.paginAndTranslate(2, 10,
				"Select username, $fn_concat($fn_second($fn_second(99)),'a', $fn_second(20) ), $fn_current_time(), $fn_PI(), $fn_concat('a', b, c) as b from usertable as tb");
		System.out.println(result);

		result = Dialect.SQLiteDialect.paginAndTranslate(2, 10,
				"Select username, $fn_concat($fn_second($fn_second(99)),'a', $fn_second(20) ),   $fn_concat('a', b, c) as b from usertable as tb");
		System.out.println(result);

		System.out.println("============================================");

		Dialect.setSqlFunctionPrefix(null);
		result = Dialect.MySQL55Dialect.translate(
				"Select username, concat(second(second(99)),'a', second(20) ), current_time(), PI(), concat('a', b, c) as b from usertable as tb");
		System.out.println(result);

		result = Dialect.SQLiteDialect.translate(
				"Select username, concat(second(second(99)),'a', second(20) ),   concat('a', b, c) as b from usertable as tb");
		System.out.println(result);

		result = Dialect.MySQL55Dialect.paginAndTranslate(2, 10,
				"Select username, concat(second(second(99)),'a', second(20) ), current_time(), PI(), concat('a', b, c) as b from usertable as tb");
		System.out.println(result);

		result = Dialect.SQLiteDialect.paginAndTranslate(2, 10,
				"Select username, concat(second(second(99)),'a', second(20) ),   concat('a', b, c) as b from usertable as tb");
		System.out.println(result);

	}

}
