package org.ahhn.com.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by XJX on 2016/3/5.
 */
@Service("bookShopService")
public class BookShopServiceImpl implements BookShopService {

	@Autowired
	private BookShopDao bookShopDao;

	/**
	 * 调用propagation，指定事务的传播行为，既当前的事务方法被另外一个事务方法调用时，如何使用事务，
	 * 默认取值为REQUIRED
	 * REQUIRES_NEW：使用用自己的事务，调用事务方法的事务被挂起5
	 * <p>
	 * 使用isolation指定事物的隔离级别，最常用的取值为READ_COMMITTED
	 * <p>
	 * 默认情况下Spring的声明式事务对所有的运行时异常进行回滚，也可以通过对应的属性进行设置
	 *
	 * readOnly默认false
	 *
	 * timmeout强制指定事务可以占用的时间
	 *
	 * @param username
	 * @param isbn
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false, timeout = 1)
	//添加事务注解
	public void purchase(String username, String isbn) {
		//1、获取书的单价
		int price = bookShopDao.findBookPriceByIsbn(isbn);
		//2、更新书的库存
		bookShopDao.updateBookStore(isbn);
		//3、更新用户余额
		bookShopDao.updateUserAccount(username, price);
	}
}
