/***
 * 事务
 */
package org.ahhn.com.tx.xml;

/**
 * Created by XJX on 2016/3/5.
 */
public interface BookShopDao {
	//根据书号获取书的单价
	public int findBookPriceByIsbn(String isbn);

	//更新书的库存，使书号对应的书的库存-1
	public void updateBookStore(String isbn);

	//更新用户的账户余额：使username的balance-price
	public void updateUserAccount(String username, int price);
}
