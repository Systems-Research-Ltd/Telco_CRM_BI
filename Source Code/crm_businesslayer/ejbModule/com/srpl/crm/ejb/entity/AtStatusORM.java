package com.srpl.crm.ejb.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;


/**
 * The persistent class for the at_statuses database table.
 * 
 */
@Entity
@Table(name="at_statuses")
public class AtStatusORM implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="AT_STATUSES_ID_GENERATOR", sequenceName="at_statuses_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AT_STATUSES_ID_GENERATOR")
	private Integer id;

	private String title;

	//bi-directional many-to-one association to AssignedTask
	@OneToMany(fetch = FetchType.LAZY, mappedBy="status")
	private Set<AssignedTaskORM> assignedTasks = new HashSet<AssignedTaskORM>(0);

    public AtStatusORM() {
    }

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<AssignedTaskORM> getAssignedTasks() {
		return this.assignedTasks;
	}

	public void setAssignedTasks(Set<AssignedTaskORM> assignedTasks) {
		this.assignedTasks = assignedTasks;
	}
	
}