/*
 * jDialects, a tiny SQL dialect tool 
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later.
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */
package test.coveragetest.jdialects;

import org.junit.Test;

import com.github.drinkjava2.jdialects.Database;
import com.github.drinkjava2.jdialects.Dialect;
import com.github.drinkjava2.jdialects.Table;

/**
 * This is unit test for Table
 * 
 * @author Yong Z.
 * @since 1.0.2
 */
public class TableTest {
	private static void printDDLs(String[] ddl) {
		for (String str : ddl) {
			System.out.println(str);
		}
	}

	private static void printDialectsDDLs(Dialect d, Table t) {
		System.out.println("======" + d + "=====");
		try {
			String[] ddl = t.toCreateDDL(d, true);
			printDDLs(ddl);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception found: " + e.getMessage());
		}
	}

	private static void printAllDialectsDDLs(Table t) {
		Dialect[] diaList = Dialect.values();
		for (Dialect dialect : diaList) {
			System.out.println("======" + dialect + "=====");
			try {
				String[] ddl = t.toCreateDDL(dialect, true);
				printDDLs(ddl);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Exception found: " + e.getMessage());
			}
		}
	}

	private static Table aNormalModel() {// A normal setting
		Table t = new Table("testTable");
		t.column("b1").BOOLEAN();
		t.column("d2").DOUBLE();
		t.column("f3").FLOAT(5);
		t.column("i4").INTEGER().pkey();
		t.column("l5").LONG();
		t.column("s6").SHORT();
		t.column("b7").BIGDECIMAL(10, 2);
		t.column("s8").STRING(20);
		t.column("d9").DATE();
		t.column("t10").TIME();
		t.column("t11").TIMESTAMP();
		t.column("v12").VARCHAR(300);
		return t;
	}

	@Test
	public void testANormalModel() {
		printAllDialectsDDLs(aNormalModel());
	}

	private static Table testNoPkeyModel() {// NO Prime Key
		Table t = new Table("testTable");
		t.column("i4").INTEGER();
		t.column("l5").LONG();
		return t;
	}

	@Test
	public void testNoPkey() {
		String[] ddl = testNoPkeyModel().toCreateDDL(Dialect.Teradata14Dialect);
		printDDLs(ddl);
	}

	private static Table testCompoundPkeyModel() {// Compound PKEY
		Table t = new Table("testTable");
		t.column("i4").INTEGER().pkey().unique().autoInc().notNull().defaultValue("1");
		t.column("l5").LONG().pkey();
		t.column("s6").SHORT();
		return t;
	}

	@Test
	public void testCompondPkey() {
		printAllDialectsDDLs(testCompoundPkeyModel());
	}

	private static Table testNotNullModel() {// Not Null
		Table t = new Table("testTable");
		t.column("b1").BOOLEAN().notNull();
		t.column("d2").DOUBLE().notNull();
		t.column("f3").FLOAT(5).notNull();
		t.column("i4").INTEGER().notNull();
		t.column("l5").LONG().notNull();
		t.column("s6").SHORT().notNull();
		t.column("b7").BIGDECIMAL(10, 2).notNull();
		t.column("s8").STRING(20).notNull();
		t.column("d9").DATE().notNull();
		t.column("t10").TIME().notNull();
		t.column("t11").TIMESTAMP().notNull();
		t.column("v12").VARCHAR(300).notNull();
		return t;
	}

	@Test
	public void testNotNull() {
		printAllDialectsDDLs(testNotNullModel());
	}

	private static Table allowNullModel() {// Allow Null
		Table t = new Table("testTable");
		t.column("b1").BOOLEAN();
		t.column("d2").DOUBLE();
		t.column("f3").FLOAT(5);
		t.column("i4").INTEGER();
		t.column("l5").LONG();
		t.column("s6").SHORT();
		t.column("b7").BIGDECIMAL(10, 2);
		t.column("s8").STRING(20);
		t.column("d9").DATE();
		t.column("t10").TIME();
		t.column("t11").TIMESTAMP();
		t.column("v12").VARCHAR(300);
		return t;
	}

	@Test
	public void testAllowNull() {
		printAllDialectsDDLs(allowNullModel());
	}

	private static Table uniqueModel() {// unique
		Table t = new Table("testTable");
		t.column("s1").STRING(20).unique().notNull();
		t.column("s2").STRING(20).unique();
		t.column("s3").STRING(20).unique("uname1").notNull();
		t.column("s4").STRING(20).unique("uname2");
		return t;
	}

	@Test
	public void testUnique() {
		printAllDialectsDDLs(uniqueModel());
	}

	private static Table checkModel() {// check
		Table t = new Table("testTable");
		t.column("s1").STRING(20).unique().notNull().check("s1>5");
		t.column("s2").STRING(20).unique().check("s2>5");
		t.column("s3").STRING(20).unique("uname1").notNull().check("s3>5");
		t.column("s4").STRING(20).unique("uname2").check("s4>5");
		return t;
	}

	@Test
	public void testCheck() {
		printAllDialectsDDLs(checkModel());
	}

	private static Table tableCheckModel() {// table check
		Table t = new Table("testTable");
		t.check("s2>10");
		t.column("s1").STRING(20).unique().notNull();
		t.column("s2").STRING(20);
		return t;
	}

	@Test
	public void testTableCheck() {
		printAllDialectsDDLs(tableCheckModel());
	}

	private static Table IdentityModel() {// Identity
		Table t = new Table("testTable");
		t.check("s2>10");
		t.column("s1").INTEGER().unique().notNull().identity().pkey();
		t.column("s2").LONG().check("s2>10");
		t.column("s3").BIGINT();
		return t;
	}

	@Test
	public void testIdentity() {
		printAllDialectsDDLs(IdentityModel());
	}

	@Test
	public void testIdentity2() {
		printDialectsDDLs(Dialect.SybaseASE15Dialect, IdentityModel());
		printDialectsDDLs(Dialect.MySQL55Dialect, IdentityModel());
		printDialectsDDLs(Dialect.InformixDialect, IdentityModel());
	}

	private static Table CommentModel() {// Identity
		Table t = new Table("testTable").comment("table_comment");
		t.column("s1").INTEGER().unique().notNull().identity().pkey();
		t.column("s2").LONG().comment("column_comment1");
		t.column("s3").BIGINT().comment("column_comment2");
		return t;
	}

	@Test
	public void testComment() {
		printAllDialectsDDLs(CommentModel());
	}

	@Test
	public void testComment2() {
		printDialectsDDLs(Dialect.Ingres10Dialect, CommentModel());
		printDialectsDDLs(Dialect.DB2Dialect, CommentModel());
		printDialectsDDLs(Dialect.MariaDBDialect, CommentModel());
		printDialectsDDLs(Dialect.SQLServer2012Dialect, CommentModel());
		printDialectsDDLs(Dialect.MySQL55Dialect, CommentModel());
	}

	private static Table SequenceModel() {// Sequence
		Table t = new Table("testTable");
		t.column("i1").INTEGER().pkey().sequence("seq1");
		t.column("i1").INTEGER().pkey().sequence("seq1", 1, 1);
		t.column("i2").INTEGER().sequence("seq2", 1, 5);
		t.column("i3").INTEGER().identityOrSequence("seq3", 1, 1);
		t.column("i4").INTEGER().identityOrSequence("seq4", 1, 6);
		return t;
	}

	@Test
	public void testSequence() {
		printAllDialectsDDLs(SequenceModel());
	}

	private static Table SequenceModel2() {// Sequence
		Table t = new Table("testTable");
		t.column("i3").INTEGER().pkey().identityOrSequence("seq3", 1, 1);
		return t;
	}

	@Test
	public void testSequence2() {
		Database d;
		printAllDialectsDDLs(SequenceModel2());
	}

}