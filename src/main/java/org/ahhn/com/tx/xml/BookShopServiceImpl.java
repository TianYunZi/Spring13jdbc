package org.ahhn.com.tx.xml;

import org.springframework.stereotype.Service;
/**
 * Created by XJX on 2016/3/5.
 */
@Service("bookShopService")
public class BookShopServiceImpl implements BookShopService {

	private BookShopDao bookShopDao;

	public void setBookShopDao(BookShopDao bookShopDao) {
		this.bookShopDao = bookShopDao;
	}

	public void purchase(String username, String isbn) {
		//1、获取书的单价
		int price = bookShopDao.findBookPriceByIsbn(isbn);
		//2、更新书的库存
		bookShopDao.updateBookStore(isbn);
		//3、更新用户余额
		bookShopDao.updateUserAccount(username, price);
	}
}
