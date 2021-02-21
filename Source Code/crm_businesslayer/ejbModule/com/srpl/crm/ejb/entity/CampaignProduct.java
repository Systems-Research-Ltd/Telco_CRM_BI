package com.srpl.crm.ejb.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the campaign_product database table.
 * 
 */
@Entity
@Table(name="campaign_product")
public class CampaignProduct implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CAMPAIGN_PRODUCT_CAMPAIGN_PRODUCTID_GENERATOR", sequenceName="CAMPAIGN_PRODUCT_CAMPAIGN_PRODUCT_ID_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CAMPAIGN_PRODUCT_CAMPAIGN_PRODUCTID_GENERATOR")
	@Column(name="campaign_product_id")
	private Long campaignProductId;

	@Column(name="campaign_id")
	private Long campaignId;

	@Column(name="product_id")
	private Long productId;

    public CampaignProduct() {
    }
    
    public CampaignProduct(Long campId, Long prodId) {
    	this.campaignId = campId;
    	this.productId  = prodId;
    }

	public Long getCampaignProductId() {
		return this.campaignProductId;
	}

	public void setCampaignProductId(Long campaignProductId) {
		this.campaignProductId = campaignProductId;
	}

	public Long getCampaignId() {
		return this.campaignId;
	}

	public void setCampaignId(Long campaignId) {
		this.campaignId = campaignId;
	}

	public Long getProductId() {
		return this.productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

}