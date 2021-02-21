package com.srpl.crm.ejb.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the assigned_tasks database table.
 * 
 */
@Entity
@Table(name="assigned_tasks")
public class AssignedTaskORM implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ASSIGNED_TASKS_ID_GENERATOR", sequenceName="ASSIGNED_TASKS_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ASSIGNED_TASKS_ID_GENERATOR")
	private Long id;

	@Column(name="assigned_by")
	private Long assignedBy;

    @Temporal( TemporalType.DATE)
	@Column(name="assigned_date")
	private Date assignedDate;

	@Column(name="assigned_to")
	private Long assignedTo;

    @Temporal( TemporalType.DATE)
	@Column(name="completed_on")
	private Date completedOn;

    @Temporal( TemporalType.DATE)
	@Column(name="due_date")
	private Date dueDate;

	@Column(name="ref_id")
	private Long refId;

	private String title;

	//bi-directional many-to-one association to AtCategory
    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="category_id")
	private AtCategoryORM category;

	//bi-directional many-to-one association to AtPriority
    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="priority_id")
	private AtPriorityORM priority;

	//bi-directional many-to-one association to AtStatus
    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="status_id")
	private AtStatusORM status;

    public AssignedTaskORM() {
    }

    public String getCategoryText()
    {
    	return category.getTableName();
    }
    
    public String getStatusText()
    {
    	return status.getTitle();
    }
    
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAssignedBy() {
		return this.assignedBy;
	}

	public void setAssignedBy(Long assignedBy) {
		this.assignedBy = assignedBy;
	}

	public Date getAssignedDate() {
		return this.assignedDate;
	}

	public void setAssignedDate(Date assignedDate) {
		this.assignedDate = assignedDate;
	}

	public Long getAssignedTo() {
		return this.assignedTo;
	}

	public void setAssignedTo(Long assignedTo) {
		this.assignedTo = assignedTo;
	}

	public Date getCompletedOn() {
		return this.completedOn;
	}

	public void setCompletedOn(Date completedOn) {
		this.completedOn = completedOn;
	}

	public Date getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Long getRefId() {
		return this.refId;
	}

	public void setRefId(Long refId) {
		this.refId = refId;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public AtCategoryORM getCategory() {
		return category;
	}

	public void setCategory(AtCategoryORM category) {
		this.category = category;
	}

	public AtPriorityORM getPriority() {
		return priority;
	}

	public void setPriority(AtPriorityORM priority) {
		this.priority = priority;
	}

	public AtStatusORM getStatus() {
		return status;
	}

	public void setStatus(AtStatusORM status) {
		this.status = status;
	}
	
}