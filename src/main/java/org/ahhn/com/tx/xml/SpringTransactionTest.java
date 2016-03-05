package org.ahhn.com.tx.xml;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

/**
 * Created by XJX on 2016/3/5.
 */
public class SpringTransactionTest {

	private ApplicationContext ctx = null;
	private BookShopDao bookShopDao = null;
	private BookShopService bookShopService = null;
	private Cashier cashier = null;

	{
		ctx = new ClassPathXmlApplicationContext("applicationContext-tx-xml.xml");
		bookShopDao = ctx.getBean(BookShopDao.class);
		bookShopService = ctx.getBean(BookShopService.class);
		cashier = ctx.getBean(Cashier.class);
	}

	@Test
	public void tsetBookShopDaoFindPriceByIsbn() {
		System.out.println(bookShopDao.findBookPriceByIsbn("1001"));
	}

	@Test
	public void testBookShopDaoUpdateBookStock() {
		bookShopDao.updateBookStore("1001");
	}

	@Test
	public void testBookShopDaoUpdateAccountBalance() {
		bookShopDao.updateUserAccount("AA", 70);
	}

	@Test
	public void testBookShopServiceImpl() {
		bookShopService.purchase("AA", "1001");
	}

	/***
	 * 测试事务的传播行为
	 */
	@Test
	public void testTransactonPropagation() {
		cashier.checkout("AA", Arrays.asList("1001","1002"));
	}
}
