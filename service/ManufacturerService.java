
package com.capgemini.storesmanagementsystem.service;

import java.util.List;

import com.capgemini.storesmanagementsystem.dto.DealerInfoBean;
import com.capgemini.storesmanagementsystem.dto.ManufacturerInfoBean;
import com.capgemini.storesmanagementsystem.dto.OrderDetails;
import com.capgemini.storesmanagementsystem.dto.ProductInfoBean;

public interface ManufacturerService {
	public boolean setCostPrice(ProductInfoBean product,ManufacturerInfoBean bean);
	public OrderDetails getPaymentDetails(int orderId,String name);
	public ManufacturerInfoBean login(String name, String password);
	public boolean addProduct(ManufacturerInfoBean bean,ProductInfoBean product);
	public List<ProductInfoBean> getAllProducts(ManufacturerInfoBean bean);
	public boolean checkIdAvailability(int id);
	public boolean checkNameAvailability(String name) ;
	public boolean checkProductAvailability(int id,ManufacturerInfoBean bean);
}
