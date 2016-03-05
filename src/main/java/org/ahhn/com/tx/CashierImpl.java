package org.ahhn.com.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by XJX on 2016/3/5.
 */
@Service("cashier")
public class CashierImpl implements Cashier {

	@Autowired
	private BookShopService bookShopService;

	/**
	 * 添加事务注解
	 *
	 * @param username
	 * @param isbns
	 */
	@Transactional
	public void checkout(String username, List<String> isbns) {
		for (String isbn : isbns) {
			bookShopService.purchase(username, isbn);
		}
	}
}
