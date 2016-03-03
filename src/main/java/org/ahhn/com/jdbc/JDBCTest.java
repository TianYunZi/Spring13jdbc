package org.ahhn.com.jdbc;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by XJX on 2016/3/3.
 */
public class JDBCTest {

	private ApplicationContext ctx = null;
	private JdbcTemplate jdbcTemplate;

	{
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
	}

	/***
	 * 执行Insert,Update,Delete
	 */
	@Test
	public void TestUpdate() {
		String sql = "update swf_area set name = ? where id = ?";
		jdbcTemplate.update(sql, "安徽1", 100);
	}

	/***
	 * 执行批量的Insert,Update,Delete
	 * 最后一个参数是Object[]的List集合，因为修改一条记录需要一个Object数组，多条则需要Object数组集合
	 */
	@Test
	public void TestBatchUpdate() {
		String sql = "insert into units(id,cityid) values(?,?)";

		List<Object[]> batchArgs = new ArrayList<Object[]>();
		batchArgs.add(new Object[]{"10000", "100"});
		batchArgs.add(new Object[]{"10001", "101"});
		batchArgs.add(new Object[]{"10002", "102"});
		batchArgs.add(new Object[]{"10003", "103"});
		batchArgs.add(new Object[]{"10004", "104"});
		batchArgs.add(new Object[]{"10005", "105"});
		batchArgs.add(new Object[]{"10006", "106"});
		batchArgs.add(new Object[]{"10007", "107"});
		batchArgs.add(new Object[]{"10008", "108"});
		batchArgs.add(new Object[]{"10009", "109"});

		jdbcTemplate.batchUpdate(sql, batchArgs);
	}

	@Test
	public void TestDataSource() throws SQLException {
		DataSource dataSource = ctx.getBean(DataSource.class);
		System.out.println(dataSource.getConnection());
	}
}
