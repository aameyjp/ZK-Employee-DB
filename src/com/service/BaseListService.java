package com.service;

import java.util.*;

import com.entity.Base;

public interface BaseListService {

//	get data
	public List<Base> getBaseList();
	
//	get filtered data
	public List<Base> getFilterData(Base base);
	
//	add records
	public Base add(Base base);

//  remove temporarily & store that data in another list	
	public void tempremove(Base base);
	
//	clear data from templists & unsaved data of baseList
	public void TP();

//	update data
	void update(Base bs);

	void saveDB();   
}
