package org.ahhn.com.jdbc;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by XJX on 2016/3/3.
 */
public class JDBCTest {

	private ApplicationContext ctx = null;
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	{
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
		namedParameterJdbcTemplate = ctx.getBean(NamedParameterJdbcTemplate.class);
	}

	/***
	 * 从数据库中获取一条记录，实际得到对应的一个对象
	 * 需要调用public <T> T queryForObject(String sql, RowMapper<T> rowMapper, Object... args){}
	 * 1、其中的RowMapper指定如何去映射结果集的行，常用的实现类为BeanPropertyRowMapper
	 * 2、使用SQL中列的别名去完成实例名与属性名的映射，例如parent_id，parentid
	 * 3、不支持级联属性映射
	 */
	@Test
	public void testQueryForObject() {
		String sql = "select id,parent_id parentid,name,provinccode from swf_area where id = ?";
		RowMapper<SwfArea> rowMapper = new BeanPropertyRowMapper<SwfArea>(SwfArea.class);
		SwfArea swfArea = jdbcTemplate.queryForObject(sql, rowMapper, 100);
		System.out.println(swfArea);
	}

	/***
	 * 查询实体类集合
	 */
	@Test
	public void testOueryForList() {
		String sql = "select id,parent_id parentid,name,provinccode from swf_area where id > ?";
		RowMapper<SwfArea> rowMapper = new BeanPropertyRowMapper<SwfArea>(SwfArea.class);
		List<SwfArea> swfAreas = jdbcTemplate.query(sql, rowMapper, 101);
		System.out.println(swfAreas);
	}

	/***
	 * 查询单个列的值或做统计查询
	 */
	@Test
	public void tsetQueryForObject2() {
		String sql = "select count(id) from swf_area";
		long count = jdbcTemplate.queryForObject(sql, Long.class);
		System.out.println(count);
	}

	/***
	 * 执行Insert,Update,Delete
	 */
	@Test
	public void TestUpdate() {
		String sql = "update swf_area set name = ? where id = ?";
		jdbcTemplate.update(sql, "安徽", 100);
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

	/***
	 * 具名参数，可以为参数起名字
	 */
	@Test
	public void testNamedParameterJdbcTemplate() {
		String sql = "insert into units(id,cityid) values(:id,:cityId)";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", "10010");
		paramMap.put("cityId", 110);
		namedParameterJdbcTemplate.update(sql, paramMap);
	}

	/***
	 * 具名参数2
	 * 可以使用public int update(String sql, SqlParameterSource paramSource)方法进行更新
	 * 1、SQL语句的参数名和类的属性名一致
	 * 2、使用SqlParameterSource的BeanPropertySqlParameterSource实现类作为参数
	 *
	 */
	@Test
	public void testNamedParameterJdbcTemplate2() {
		String sql = "insert into units(id,cityid) values(:id,:cityId)";
		UnitModel unitModel = new UnitModel();
		unitModel.setId("10011");
		unitModel.setCityId(111);
		SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(unitModel);
		namedParameterJdbcTemplate.update(sql, parameterSource);
	}
}
