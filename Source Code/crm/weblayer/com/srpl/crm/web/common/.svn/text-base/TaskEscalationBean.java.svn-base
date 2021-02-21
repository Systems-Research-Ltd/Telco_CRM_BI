package com.srpl.crm.web.common;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import com.srpl.crm.ejb.entity.AssignedTaskORM;
import com.srpl.crm.ejb.request.AssignedTaskDAO;

@ManagedBean(name="taskEscalation")
public class TaskEscalationBean {

	@EJB AssignedTaskDAO taskDao;
	
	public TaskEscalationBean() {
		// TODO Auto-generated constructor stub
	}
	
	public void escalateSupport(){
		List<AssignedTaskORM> pendingTasks = taskDao.listPendingTasks();
		System.out.println(pendingTasks.size());
	}

}
