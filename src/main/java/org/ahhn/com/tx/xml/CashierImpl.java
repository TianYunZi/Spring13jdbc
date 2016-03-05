package org.ahhn.com.tx.xml;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by XJX on 2016/3/5.
 */
@Service("cashier")
public class CashierImpl implements Cashier {

	private BookShopService bookShopService;

	public void setBookShopService(BookShopService bookShopService) {
		this.bookShopService = bookShopService;
	}

	public void checkout(String username, List<String> isbns) {
		for (String isbn : isbns) {
			bookShopService.purchase(username, isbn);
		}
	}
}
