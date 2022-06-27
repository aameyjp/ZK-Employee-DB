package com.viewModel;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.ListModelList;

import com.entity.Base;
import com.service.BaseListService;
import com.service.BaseServiceImpl;


public class BaseViewModel extends Base {
	
//	services
	BaseListService baseListService = new BaseServiceImpl();
	
	@Init // @Init annotates a initial method
	public void init() {
		
		//get data from service and wrap it to model for the view
		List<Base> baseList = baseListService.getBaseList();
		baseListModel = new ListModelList<Base>(baseList);
	}
	
	@Command
	@NotifyChange("baseListModel") 
	public void addDB() {
//		System.out.println("\ninside addDB BVM");
		selectedBase = baseListService.add(new Base()); 
		baseListModel.add(selectedBase);
	}
	
	@Command
	@NotifyChange("baseListModel") 
	public void saveDB() {
//		System.out.println("\n\ninside saveDB BVM");
		baseListService.saveDB();
	}

	@Command
	@NotifyChange("selectedBase")
	public void updateDB(@BindingParam("base") Base bs) {
//		System.out.println("\n\ninside updateDB BVM");
//		System.out.println("bs data = " + bs.id + " " + bs.eid + " " + bs.name + " " + bs.salary + " " + bs.joinDate + " " + bs.role + " " + bs.response);
		baseListService.update(bs);
	}

	
	@Command
	@NotifyChange("selectedBase")
	public void deleteDB(@BindingParam("base") Base base) {
//		System.out.println("\n\ninside deleteDB BVM");
//		System.out.println("base = " + base);
		baseListService.tempremove(base);
		baseListModel.remove(base);
	}
	
	@Command
	@NotifyChange("baseListModel")
	public void changeFilter() {
//		System.out.println("\n\ninside changeFilter BVM");
		
		List<Base> baseList = baseListService.getFilterData(new Base(id, eid, name, salary, role, JD, response));
		
		baseListModel = new ListModelList<Base>(baseList);
	}
	
	@Command
	public void refresh() {
//		System.out.println("inside refresh");
		baseListService.TP();
		Executions.getCurrent().sendRedirect("");
	}

	
//	data for view
	private ListModelList<Base> baseListModel;
	private Base selectedBase;
	
	public ListModelList<Base> getBaseListModel() {
		return baseListModel;
	}
	
	public void setBaseListModel(ListModelList<Base> baseListModel) {
		this.baseListModel = baseListModel;
	}
	
	public Base getSelectedBase() {
		return selectedBase;
	}
	
	public void setSelectedBase(Base selectedBase) {
		this.selectedBase = selectedBase;
	}

	static List<String> roleList = new ArrayList<String>();
	static {
		roleList.add("Intern");
		roleList.add("Designer");
		roleList.add("Tester");
		roleList.add("Developer");
		roleList.add("Principal Engineer");
	}
	
	
	public List<String> getRoleList() {
		return roleList;
	}

	static List<String> responseList = new ArrayList<String>();
	static {
		responseList.add("Designing");
		responseList.add("Testing");
		responseList.add("Implementation");
	}
	
	
	public List<String> getResponseList() {
		return responseList;
	}
	
}



