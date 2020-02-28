package com.capgemini.storesmanagementsystem.controller;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.capgemini.storesmanagementsystem.dao.AdminDAO;
import com.capgemini.storesmanagementsystem.dao.AdminDAOImpl;
import com.capgemini.storesmanagementsystem.dao.DealerDAO;
import com.capgemini.storesmanagementsystem.dao.DealerDAOImpl;
import com.capgemini.storesmanagementsystem.dao.ManufacturerDAO;
import com.capgemini.storesmanagementsystem.dao.ManufacturerDAOImpl;
import com.capgemini.storesmanagementsystem.db.CollectionDbClass;
import com.capgemini.storesmanagementsystem.dto.CustomerInfoBean;
import com.capgemini.storesmanagementsystem.dto.DealerInfoBean;
import com.capgemini.storesmanagementsystem.dto.ManufacturerInfoBean;
import com.capgemini.storesmanagementsystem.dto.ProductInfoBean;
import com.capgemini.storesmanagementsystem.exception.EnterValidInputException;
import com.capgemini.storesmanagementsystem.service.AdminService;
import com.capgemini.storesmanagementsystem.service.AdminServiceImpl;
import com.capgemini.storesmanagementsystem.service.CustomerService;
import com.capgemini.storesmanagementsystem.service.CustomerServiceImpl;
import com.capgemini.storesmanagementsystem.service.DealerService;
import com.capgemini.storesmanagementsystem.service.DealerServiceImpl;
import com.capgemini.storesmanagementsystem.service.ManufacturerService;
import com.capgemini.storesmanagementsystem.service.ManufacturerServiceImpl;

public class StoresManagementApp {
	public static void main(String[] args) {
		System.out.println("\t \t Welcome to Stores Management System Appication  ");
		Scanner sc = new Scanner(System.in);
		AdminService adminSer = new AdminServiceImpl();
		DealerService dealerSer = new DealerServiceImpl();
		ManufacturerService manSer = new ManufacturerServiceImpl();
		CustomerService cusSer = new CustomerServiceImpl();
		String uname;
		String password;
		while (true) {

			System.out.println("Who You are..??");
			System.out.println("Available options \n " + "1. Admin \n " + "2. Manufacturer \n " + "3. Dealer \n "
					+ "4. Customer \n " + "5. Exit \n ");
			System.out.println("========================================================");
			System.out.println("Enter Your Choice");
			try {
				int choice = sc.nextInt();
				switch (choice) {
				case 1:
					System.out.println("Enter UserName");
					sc.nextLine();
					uname = sc.nextLine();
					System.out.println("Enter Password");
					password = sc.nextLine();
					if (uname.equals("Admin") && password.equals("admin@123")) {
						System.out.println("Admin Login Successful");
						AdminController admin = new AdminController();
						admin.admin();
					} else {
						System.out.println("Login Unsuccessful");
					}
					
					break;
				case 2:
					System.out.println("Enter UserName");
					sc.nextLine();
					uname = sc.nextLine();
					System.out.println("Enter Password");
					password = sc.nextLine();
					if (uname.equals("Manufacturer") && password.equals("manufacturer@123")) {
						System.out.println("Manufacturer Login Successful");
						ManufacturerController manufacturer = new ManufacturerController();
						manufacturer.manufacturer();
					} else {
						System.out.println("Login Unsuccessful");
					}
					
					break;
				case 3:
					System.out.println("Enter UserName");
					sc.nextLine();
					uname = sc.nextLine();
					System.out.println("Enter Password");
					password = sc.nextLine();
					if (uname.equals("Dealer") && password.equals("dealer@123")) {
						System.out.println("Dealer Login Successful");
						DealerController dealer = new DealerController();
						dealer.dealer();
					} else {
						System.out.println("Login Unsuccessful");
					}
					
					break;
				case 4:
					System.out.println("Enter UserName");
					sc.nextLine();
					uname = sc.nextLine();
					System.out.println("Enter Password");
					password = sc.nextLine();
					if (uname.equals("Customer") && password.equals("customer@123")) {
						System.out.println("Customer Login Successful");
						CustomerController customer = new CustomerController();
						customer.customer();
					} else {
						System.out.println("Login Unsuccessful");
					}
					
					break;
				case 5:
					System.exit(0);
				default:
					System.out.println("Invalid Choice");
				}
			} catch (InputMismatchException e) {
				try {
					throw new EnterValidInputException();
				} catch (EnterValidInputException exp) {
					System.out.println(exp.getMessage());
				}
			}
		}
	}
}
