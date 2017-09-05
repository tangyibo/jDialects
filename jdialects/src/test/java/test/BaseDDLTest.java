/*
 * jDialects, a tiny SQL dialect tool
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later. See
 * the lgpl.txt file in the root directory or
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */
package test;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;

import com.github.drinkjava2.dbutilspro.DbPro;
import com.github.drinkjava2.jbeanbox.BeanBox;
import com.github.drinkjava2.jdialects.Dialect;

import test.DataSourceConfig.DataSourceBox;

/**
 * This base test class in charge of configure and close data sources.
 * 
 * @author Yong Z.
 * @since 1.0.2
 *
 */
public class BaseDDLTest {
	protected DataSource ds = BeanBox.getBean(DataSourceBox.class);
	protected DbPro tiny = new DbPro(ds);
	protected Dialect guessedDialect = Dialect.guessDialect(ds);;

	@Before
	public void initDao() {
		System.out.println("Current guessedDialect=" + guessedDialect);
	}

	@After
	public void closeDataSource() {
		BeanBox.defaultContext.close();// close dataSource
	}

}
