package com.service;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

import org.zkoss.zk.ui.util.Clients;

import com.entity.Base;

public class BaseServiceImpl implements BaseListService, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	static List<Base> baseList = new ArrayList<Base>();

	static List<Base> tempList1 = new ArrayList<Base>();
	static List<Base> tempList2 = new ArrayList<Base>();
	
	
	static int tempid = 0;
	static int uid = 0;
	static int size = 0;
	
	@Override
	public List<Base> getBaseList() {
		List<Base> list = new ArrayList<Base>();
		for(Base base: baseList) {
			list.add(Base.clone(base));
		}
		return list;
	}
	
//	NEW ADD METHOD
	@Override
	public Base add(Base base) {
		
		tempid++;
		base.setId(tempid);
		tempList1.add(base);
		size = tempList1.size();
		return base;
	}
	
	@Override
	public void saveDB() {
		System.out.println("size = " + size + "  templist1.size = "  + tempList1.size());
		
		try {
			// Save newly entered rows
			for(int i=0; i<size; i++) {
				System.out.println("inside 1st for loop");
				Base b = tempList1.get(0);
				System.out.println("b = " + b + " b.id = " + b.id + " b.eid = " + b.eid + " b.name = " + b.name);


				if(!b.eid.equals("") || !b.name.equals("") || b.salary != null || 
				   b.joinDate !=null || !b.role.equals("") || !b.response.isEmpty()) {
					System.out.println("inside else");
					baseList.add(b);
					
				}
				tempList1.remove(b);
				
				
			}
		}catch(Exception e) {
			System.out.println("exception e = " + e);
		}

		size = 0;   // 0
		
		// save updated records
		for(int i=0; i<baseList.size(); i++) {
			Base b = baseList.get(i);
			for(int j=0; j<tempList2.size(); j++) {
				Base t = tempList2.get(j);
				if(b.id == t.id) {
					System.out.println("updated successfully");
					baseList.set(i, t);
					break;
				}
			}
		}
		
		System.out.println("bL = " + baseList);
		System.out.println("tL = " + tempList1);
		
		// save the deleted condition
		for(int i=0; i<baseList.size(); i++) {
			Base b = baseList.get(i);
			for(int j =0; j<tempList1.size(); j++) {
				Base t = tempList1.get(j);
				if(b.id == t.id) {
					System.out.println("Deleted successfully");
					baseList.remove(b);
					i=0;
					break;
				}
			}

		}
		
		// checking
		for(int i=baseList.size()-1; i>=0; i--) {
			Base b = baseList.get(i);
			String val = "Please enter";
			
			Boolean show = false;
			if(b.eid.equals("")) {
				val += " eid, ";
				show = true;
			}
			if(b.name.equals("")) {
				val += " name, ";
				show = true;
			}
			if(b.salary == null) {
				val += " salary, ";
				show = true;
			}
			if(b.role.equals("")) {
				val += " designation, ";
				show = true;
			}if(b.joinDate == null) {
				val += " joindate, ";
				show = true;
			}
			if(b.response.isEmpty()){
				val += " responsibilities ";
				show = true;
			}
			if(show) {
				Clients.showNotification(val + " in row " + (i+1));
			}
		}
				
	}

	@Override
	public void update(Base bs) {
		System.out.println("inside overide update");
			
		System.out.println("bs data = " + bs.id + " " + bs.eid + " " + bs.name + " " + bs.salary + " " + bs.joinDate + " " + bs.role + " " + bs.response);
		System.out.println("baseList = " + baseList);
		System.out.println("tempList2 = " + tempList2);

		for(int i=0; i<baseList.size(); i++) {
			Base b = baseList.get(i);
			if(b.id == bs.id) {
				System.out.println("SUccess");
				tempList2.add(bs);				
			}
		}
		
		System.out.println("size = " + size);
		System.out.println("Final baseList : " + baseList);
		System.out.println("Final tempList2 : " + tempList2);
		
		
	}


	@Override
	public void tempremove(Base base) {
		System.out.println("inside override remove");
		
		tempList1.add(base);
		System.out.println("base = " + base);
		

	}

	
	@Override
	public List<Base> getFilterData(Base bs) {
		System.out.println("inside getFilterData & its size is : " + getBaseList().size());
		List<Base> someData = new ArrayList<Base>();
		
		try {
			String EID = bs.eid.toLowerCase();
			String NAME = bs.name.toLowerCase();
			String SALARY = null;
			if(bs.salary != null) {
				SALARY = Integer.toString(bs.salary);
			}
			String ROLE = bs.role.toLowerCase();
			String JOINDATE = bs.JD.toLowerCase().replaceAll("\\s", "");
			ArrayList<String> RESPO = bs.response; 
			System.out.println("intially = " + someData + " eid = " + EID + " name = " + NAME +  " Sal = " + SALARY + " role " + ROLE + " JD = " + JOINDATE);
			
			for(int i=0; i<baseList.size(); i++) {
				Base b = baseList.get(i);
				SimpleDateFormat sm = new SimpleDateFormat("dd MMMM yyyy");
				
				if(
					b.eid.toLowerCase().contains(EID)     &&
					b.name.toLowerCase().contains(NAME)   &&
				    b.role.toLowerCase().contains(ROLE)   &&
				    b.response.containsAll(RESPO)         &&
				    ( (SALARY ==null)  || (SALARY.equals(((Integer.toString(b.salary))))) ) &&
				    sm.format(b.joinDate).toLowerCase().replaceAll("\\s", "").contains(JOINDATE)

				){
					someData.add(b);
					
				 }
				
			}
			
			
		}catch(Exception e) {
			System.out.println("Erro = " + e);
		}
		return someData;
	}

	
	@Override
	public void TP() {
		try {
			System.out.println("inside override TP");
			tempList2.clear();
			tempList1.clear();
			
			for(int i=0; i<baseList.size(); i++) {
				Base b = baseList.get(i);
				if(b.eid.equals("") || b.name.equals("") || b.salary == null || 
				   b.joinDate ==null || b.role.equals("") || b.response.isEmpty()) {
						
					baseList.remove(b);
					i=0;
				}
			}
			
		}catch(Exception e) {
			System.out.println("err = " + e);
		}
	}
	
}



