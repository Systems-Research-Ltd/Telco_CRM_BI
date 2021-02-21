package com.srpl.crm.ejb.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the table_mappings database table.
 * 
 */
@Entity
@Table(name="table_mappings")
public class TableMapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TABLE_MAPPINGS_MAPPINGID_GENERATOR", sequenceName="TABLE_MAPPINGS_MAPPING_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TABLE_MAPPINGS_MAPPINGID_GENERATOR")
	@Column(name="mapping_id")
	private Long mappingId;

	@Column(name="mapping_positions")
	private String mappingPositions;

	@Column(name="mapping_table")
	private String mappingTable;

	@Column(name="mapping_title")
	private String mappingTitle;

    public TableMapping() {
    }

	public Long getMappingId() {
		return this.mappingId;
	}

	public void setMappingId(Long mappingId) {
		this.mappingId = mappingId;
	}

	public String getMappingPositions() {
		return this.mappingPositions;
	}

	public void setMappingPositions(String mappingPositions) {
		this.mappingPositions = mappingPositions;
	}

	public String getMappingTable() {
		return this.mappingTable;
	}

	public void setMappingTable(String mappingTable) {
		this.mappingTable = mappingTable;
	}

	public String getMappingTitle() {
		return this.mappingTitle;
	}

	public void setMappingTitle(String mappingTitle) {
		this.mappingTitle = mappingTitle;
	}

}