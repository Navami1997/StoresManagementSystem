package com.capgemini.storesmanagementsystem.dao;

import java.time.LocalDate;
import java.util.Date;
import java.util.Iterator;

import com.capgemini.storesmanagementsystem.db.CollectionDbClass;
import com.capgemini.storesmanagementsystem.dto.CustomerInfoBean;
import com.capgemini.storesmanagementsystem.dto.DealerInfoBean;
import com.capgemini.storesmanagementsystem.dto.ManufacturerInfoBean;
import com.capgemini.storesmanagementsystem.dto.OrderDetails;
import com.capgemini.storesmanagementsystem.dto.ProductInfoBean;

public class CustomerDAOImpl implements CustomerDAO {

	private DealerDAOImpl dealerImpl = new DealerDAOImpl();
	
	static boolean  flag = false;
	
	@Override
	public boolean buyProduct(DealerInfoBean dealer, OrderDetails corder, CustomerInfoBean customer, String pname) {
		Iterator<DealerInfoBean> itr = CollectionDbClass.dealerSet.iterator();
		while (itr.hasNext()) {
			DealerInfoBean bean = itr.next();
			if (bean.getDealerName().equalsIgnoreCase(dealer.getDealerName())) {
				for (ProductInfoBean prods : bean.getProduct()) {
					if (prods.getProductName().equals(pname)) {
						corder.setProductName(prods.getProductName());
						LocalDate date = LocalDate.now();
						corder.setDateOfOrder(date);
						corder.setDateOfDelivery(date.plusDays(3));
						corder.setAmount(prods.getSellingPrice());
						customer.getOrders().add(corder);

						int quantity = prods.getQuantity() - 1;
						prods.setQuantity(quantity);
						if (prods.getQuantity() <= bean.getMinimumQuantity()) {
							flag=true;
							/*
							 * dealerBean.setDealerName(bean.getDealerName()); ProductInfoBean product = new
							 * ProductInfoBean(); product.setProductName(pname); OrderDetails order = new
							 * OrderDetails(); for (OrderDetails orders : bean.getOrders()) {
							 * if(orders.getProductName().equalsIgnoreCase(prods.getProductName())) {
							 * 
							 * //prods.setQuantity(prods.getQuantity() * 2); //int newOid =
							 * orders.getOrderId() + 1; order.setOrderId(orders.getOrderId());
							 * 
							 * order.setProductName(prods.getProductName()); LocalDate newDate =
							 * LocalDate.now(); order.setDateOfOrder(newDate);
							 * order.setDateOfDelivery(LocalDate.now().plusDays(2));
							 * order.getDealers().add(bean); bean.getOrders().add(order);
							 * 
							 * } } dealerBean.getOrders().add(order); dealerBean.getProduct().add(product);
							 * Runnable r = new AutoBuy(); Thread t1 = new Thread(r); t1.start(); try {
							 * Thread.currentThread().sleep(2000); } catch (InterruptedException e) { //
							 * TODO Auto-generated catch block e.printStackTrace(); }
							 */

							
							/*
							 * for (OrderDetails orders : bean.getOrders()) { if
							 * (orders.getProductName().equalsIgnoreCase(prods.getProductName())) {
							 * OrderDetails order = new OrderDetails();
							 * prods.setQuantity(prods.getQuantity() * 2); int newOid = orders.getOrderId()
							 * + 1; order.setOrderId(newOid); order.setProductName(prods.getProductName());
							 * LocalDate newDate = LocalDate.now(); order.setDateOfOrder(newDate);
							 * order.setDateOfDelivery(LocalDate.now().plusDays(2));
							 * order.getDealers().add(bean); bean.getOrders().add(order); } }
							 */
							 

						}

						return true;
					}
				}
			}
		}

		return false;
	}

	@Override
	public CustomerInfoBean login(int id, String password) {
		Iterator<CustomerInfoBean> itr = CollectionDbClass.customerSet.iterator();
		while (itr.hasNext()) {
			CustomerInfoBean bean = itr.next();
			if (bean.getCustomerId() == id && bean.getPassword().equals(password)) {
				return bean;
			}
		}
		return null;
	}

	@Override
	public OrderDetails getOrderDetails(int id, CustomerInfoBean customer) {
		for (OrderDetails order : customer.getOrders()) {
			if (order.getOrderId() == id) {
				return order;
			}
		}
		return null;
	}

	@Override
	public boolean checkEmailAvailability(String email) {
		Iterator<CustomerInfoBean> itr = CollectionDbClass.customerSet.iterator();
		if (itr.hasNext()) {
			while (itr.hasNext()) {
				CustomerInfoBean bean = itr.next();
				if (bean.getEmail().equals(email)) {
					return false;
				} else {
					return true;
				}
			}
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean checkIdAvailability(int id) {
		Iterator<CustomerInfoBean> itr = CollectionDbClass.customerSet.iterator();
		if (itr.hasNext()) {
			while (itr.hasNext()) {
				CustomerInfoBean bean = itr.next();
				if (bean.getCustomerId() == id) {
					return false;
				} else {
					return true;
				}
			}
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void autoBuy(DealerInfoBean dealer,String name) {
		
		if(flag) {
			for (DealerInfoBean dealers : CollectionDbClass.dealerSet) {
				if(dealer.getDealerName().equalsIgnoreCase(dealers.getDealerName())) {
					for (ProductInfoBean prods : dealers.getProduct()) {
						if(prods.getProductName().equalsIgnoreCase(name)) {
							OrderDetails order = new OrderDetails();
							for (OrderDetails orders : dealers.getOrders()) {
								if(orders.getProductName().equalsIgnoreCase(name)) {
									
									prods.setQuantity(prods.getQuantity() * 2);
									int newOid = orders.getOrderId() + 1;
									order.setOrderId(newOid);
									order.setAmount(prods.getCostPrice()*prods.getQuantity());
									order.setProductName(prods.getProductName());
									LocalDate newDate = LocalDate.now();
									order.setDateOfOrder(newDate);
									order.setDateOfDelivery(LocalDate.now().plusDays(2));
									
								}
							}
							order.getDealers().add(dealers);
							dealers.getOrders().add(order);
							flag=false;
						}
					}
					
				}
			}
		}
		
	}

}
