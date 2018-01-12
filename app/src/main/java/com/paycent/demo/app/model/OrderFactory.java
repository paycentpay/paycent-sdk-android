package com.paycent.demo.app.model;

import android.util.SparseArray;

import java.util.ArrayList;
import java.util.List;

public class OrderFactory {

	private List<Order> orders;

	public List<Order> getOrders() {

		if(orders == null){
			initOrders();
		}

		return orders;
	}

	private static SparseArray<String> iconMap = new SparseArray<>();

	private static SparseArray<String> titleMap = new SparseArray<>();

	private static SparseArray<String> amountMap = new SparseArray<>();

	static{
		iconMap.put(1, "prod0");
		iconMap.put(2, "prod1");
		iconMap.put(3, "prod2");
		iconMap.put(4, "prod3");
		iconMap.put(5, "prod4");

		titleMap.put(1, "Huawei P10 android phone");
		titleMap.put(2, "Philip air cleaner");
		titleMap.put(3, "Ikea blue sofa");
		titleMap.put(4, "Samsung android phone");
		titleMap.put(5, "Xiaomi moto scooter");

		amountMap.put(1, "RMB 2688");
		amountMap.put(2, "RMB 1988");
		amountMap.put(3, "RMB 688");
		amountMap.put(4, "RMB 4688");
		amountMap.put(5, "RMB 1688");
	}

	private void initOrders(){

		orders = new ArrayList<>();

		int num = 1;

		for(int i=1; i<6; i++){

			Order o = new Order();
			o.setTitle(titleMap.get(i) );
			o.setIconName(iconMap.get(i) );
			o.setAmount("Amount: " + amountMap.get(i) );
			o.setNumber("Number: " + num);

			if(num++ > 3){
				num = 1;
			}

			orders.add(o);
		}

	}
}
