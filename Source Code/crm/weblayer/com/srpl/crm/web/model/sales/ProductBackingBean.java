package com.srpl.crm.web.model.sales;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;


import com.bitguiders.util.jsf.JSFBeanInterface;
import com.bitguiders.util.jsf.WebConstants;

import com.bitguiders.util.jsf.JSFBeanSupport;


import com.srpl.crm.ejb.entity.OrderORM;
import com.srpl.crm.ejb.entity.ProductORM;
import com.srpl.crm.ejb.entity.SalesLeadORM;
import com.srpl.crm.ejb.entity.SalesOpportunityORM;
import com.srpl.crm.ejb.entity.SupportCaseORM;
import com.srpl.crm.ejb.exceptions.LeadNotFoundException;
import com.srpl.crm.ejb.exceptions.ProductNotFoundException;
import com.srpl.crm.ejb.request.CaseDAO;
import com.srpl.crm.ejb.request.CaseHistoryDAO;
import com.srpl.crm.ejb.request.OpportunityDAO;
import com.srpl.crm.ejb.request.OrderDAO;
import com.srpl.crm.ejb.request.OrderDetailDAO;
import com.srpl.crm.ejb.request.ProductDAO;
import com.srpl.crm.web.common.AjaxListStructure;
import com.srpl.crm.web.common.InnerTabs;
import com.srpl.crm.web.common.SessionDataBean;
import com.srpl.crm.web.controller.BeanFactory;
import com.srpl.crm.web.model.MarketingModuleCampaignBackingBean;
import com.srpl.crm.web.model.common.ColumnModel;
import com.srpl.um.ejb.entity.UmCompany;
import com.srpl.um.ejb.request.CompanyDAO;

@ManagedBean(name="productBean")
@RequestScoped
public class ProductBackingBean extends JSFBeanSupport implements JSFBeanInterface,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ColumnModel> columns;
	private Long productId;
	private String productTitle;
	private Long companyId;
	private UmCompany umCompany;
	private Long productCost;
	private String productDescription;
	private String productType;
	private List<ProductORM> productList;
	private UploadedFile file;
	private String productImage;
	private SessionDataBean session;
	
	@EJB ProductDAO productDao;
	@EJB CaseDAO caseDao;
	@EJB CaseHistoryDAO caseHistoryDao;
	@EJB OrderDAO orderDao;
	@EJB OrderDetailDAO orderDetailDao;
	@EJB OpportunityDAO opportunityDao;
	@EJB CompanyDAO companyDao;
	
	public ProductBackingBean(){
		session = BeanFactory.getInstance().getSessionBean();
	}
	
	@PostConstruct
	public void postConstruct() {
		String act = getAction();
		if(act.equals("")){
			if(session.getSalesModule_selectedProduct() != 0L){
				productDetails();
				reset();
				setViewAction();
			}else
				session.resetSalesProductModule();
		}
	}
	
	@Override
	public void setViewAction(){
		super.setViewAction();
		setResetAction(false);
		setCancelAction(false);
	}

	public void loadProduct(Long id){
		ProductBackingBean bean= this;
		bean.resetBean();
		ProductORM product;
		try{
			product = productDao.productDetails(id);
			convert2Bean(product, bean);
			
		} catch(Exception e) {
			//changeTabPath(0, "/view/sales/products/productNoSelection.xhtml");
		}
	}
	
	private void convert2Bean(ProductORM db,ProductBackingBean bean) {
		System.out.println("Hello");
		bean.setProductId(db.getProductId());
		bean.setProductTitle(db.getProductTitle());
		bean.setProductDescription(db.getProductDescription());
		bean.setProductCost(db.getProductCost());
		bean.setProductImage(db.getProductImage());
		bean.setProductType(db.getProductType());		
	}
	
	public SessionDataBean getSession() {
		return session;
	}

	public void setSession(SessionDataBean session) {
		this.session = session;
	}

	public List<ColumnModel> getColumns() {
		return columns;
	}

	public void setColumns(List<ColumnModel> columns) {
		this.columns = columns;
	}


	public Long getProductId() {
		return productId;
	}


	public void setProductId(Long productId) {
		this.productId = productId;
	}


	public String getProductTitle() {
		return productTitle;
	}


	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}


	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public UmCompany getUmCompany() {
		return umCompany;
	}

	public void setUmCompany(UmCompany umCompany) {
		this.umCompany = umCompany;
	}

	public Long getProductCost() {
		return productCost;
	}


	public void setProductCost(Long productCost) {
		this.productCost = productCost;
	}


	public String getProductDescription() {
		return productDescription;
	}


	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}


	public String getProductType() {
		return productType;
	}


	public void setProductType(String productType) {
		this.productType = productType;
	}


	public List<ProductORM> getProductList() {
		return productList;
	}

	public void setProductList(List<ProductORM> productList) {
		this.productList = productList;
	}

	
	public List<ProductORM> listProducts(){
		try{
		   productList = productDao.listProducts();
		   System.out.println("product Title = "+productList.get(0).getProductTitle());
		}catch(ProductNotFoundException e){
			//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getMessage(), e.getMessage()));
		}   
		return productList;
	}
	
	@Override
	public List<AjaxListStructure> getList() {
		ArrayList<AjaxListStructure> myList = new ArrayList<AjaxListStructure>();
		AjaxListStructure e;
		List<ProductORM> productDbList;
		try {
			umCompany = companyDao.companyDetails(session.getCompanyId());
			productDbList = productDao.listProducts(umCompany);
			if(productDbList.size() > 0){
				for(ProductORM p : productDbList){
					e = new AjaxListStructure();
					e.setId(p.getProductId());
					e.setLabel(p.getProductTitle());
					myList.add(e);
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}		
		return myList;
	}

	
	public void deleteRow(ActionEvent event){
		System.out.println("ProductBackingBean deleteRow called");
        Long id = (Long)event.getComponent().getAttributes().get("del_id");
		
        ProductORM search=null;
		for(ProductORM comp : productList) 
		{  
            if(comp.getProductId()==id)
            {
            	search=comp;
            	break;
            }
        }
		if(search!=null)
		{
			//delete from datatable
			try{
			productList.remove(search);
			//delete from DB
			// before deleting any product check is there any case or orders related to this product
			
			List<SupportCaseORM> caseList = new ArrayList<>();
			caseList = caseDao.retrieveCasesByProduct(search);
			
			List<OrderORM> orderList = new ArrayList<OrderORM>();
			orderList = orderDetailDao.retrieveOrderByProduct(search);
			
			if(caseList.size()>0){
				System.out.println("case found against this product so product can not be deleted");
				addMessage(getProperty("message.sales.product.delete.case.error"));
				//throw new Exception();
			}else if(orderList.size()>0){
				System.out.println("order found against this product so product can not be deleted");
				addMessage(getProperty("message.sales.product.delete.order.error"));
				//throw new Exception();
			
			}else{
				productDao.deleteProduct(search.getProductId());
				System.out.println("product deleted");
				addMessage(getProperty("message.sales.product.deleted"));
			}
			
			}catch(Exception e){
				
			}
			
		}
	
		
	}
	
	
	
	public void deleteProduct(Long productId){
		System.out.println("ProductBackingBean deleteProduct() called");
        ProductORM product = new ProductORM();
		try{
		   product = productDao.productDetails(productId);
		}catch(ProductNotFoundException e){
			System.out.println(e.getMessage());
		}
        
			try{
		
			// before deleting any product check is there any case or orders related to this product
			
			List<SupportCaseORM> caseList = new ArrayList<>();
			caseList = caseDao.retrieveCasesByProduct(product);
			
			List<OrderORM> orderList = new ArrayList<OrderORM>();
			orderList = orderDetailDao.retrieveOrderByProduct(product);
			
			
			List<SalesOpportunityORM> opportunityList = new ArrayList<SalesOpportunityORM>();
			opportunityList = opportunityDao.retrieveOpportunityByProduct(product);
			
			if(caseList.size()>0){
				System.out.println("case found against this product so product can not be deleted");
				addMessage(getProperty("message.sales.product.delete.case.error"));
				//throw new Exception();
			}else if(orderList.size()>0){
				System.out.println("order found against this product so product can not be deleted");
				addMessage(getProperty("message.sales.product.delete.order.error"));
				//throw new Exception();
			
			}else if(opportunityList.size()>0){
				System.out.println("opportunity found against this product so product can not be deleted");
				addMessage(getProperty("message.sales.product.delete.opportunity.error"));
				//throw new Exception();
			
			}else{
				productDao.deleteProduct(product.getProductId());
				System.out.println("product deleted");
				addMessage(getProperty("message.sales.product.deleted"));
			}
			
			}catch(Exception e){
				System.out.println("Exception Occured ProductBackingBean deleteProduct()");
				System.out.println(e.getMessage());
			}
			
		
	
		
	}
	
/*	public String createProduct(){
		ProductORM product = new ProductORM(getProductTitle(), getProductCost(), getProductDescription(),getProductType());
		productId = productDao.createProduct(product);
	    addMessage("Product created successfully");
		return "productList";
	}
	*/
	
	public String createProduct(){
		if(file != null)
			uploadProductImage();
		try{
		   umCompany = companyDao.companyDetails(session.getCompanyId());	
	    }catch(Exception exception){
		   System.out.println(exception.getMessage());
		}
		ProductORM product = new ProductORM(getProductTitle(), getProductCost(), getProductDescription(), getProductType(), getProductImage(), umCompany);
		productId = productDao.createProduct(product);
	    addMessage(getProperty("message.sales.product.created"));
		return "productList";
	}
	
	public void uploadProductImage(){
    	SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss");
    	this.productImage = fmt.format(new Date()) + file.getFileName();
		String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("//resources//images//" + this.productImage);
    	System.out.println("--------------------------------------->>>>>" + path);
    	File outfile = new File(path);
        InputStream is;		
        OutputStream out;		
        byte buf[] = new byte[1024];
        int len;
        try {
        	is = file.getInputstream();
        	out = new FileOutputStream(outfile);
			while ((len = is.read(buf)) > 0)
			    out.write(buf, 0, len);
			is.close();
	        out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void editProduct(Long productId){
		if(file != null)
			uploadProductImage();
		try{
		   umCompany = companyDao.companyDetails(session.getCompanyId());	
		}catch(Exception exception){
		   System.out.println(exception.getMessage());
		}
		ProductORM product = new ProductORM(productId, getProductTitle(), getProductCost(), getProductDescription(), getProductType(), getProductImage(), umCompany);
		productDao.updateProduct(product);
		addMessage(getProperty("message.sales.product.updated"));
		//return "productList";
	}
	
	
	public void test(){
		new ProductBackingBean();
		System.out.println(productDao);
		System.out.println("product bean test method called");
	}
	
	private void changeTabPath(int index, String path) {
		InnerTabs d = session.getProductTabs().get(index);
		d.setPath(path);
		session.getProductTabs().set(index, d);
		try {
			if (getParameter("fromListing").equals("fromListing")) {
			} else {
				session.setSalesModule_productTabIndex(0);
			}
		} catch (Exception e) {
			session.setSalesModule_productTabIndex(0);
		}
	}
	
	public void resetBean(){
		setProductId(0L);
		setProductTitle("");
		setProductCost(0L);
		setProductDescription("");
		setProductImage("");
		setProductType("");
	}
	
	public void productDetails() {
		loadProduct(session.getSalesModule_selectedProduct());
		changeTabPath(0, "/view/sales/products/productForm.xhtml");
		setViewAction();
	}	
	
	public String actionListener(){	
	  Long product_id;
	  setCurrentAction(getAction(),this.getClass());
	  ProductBackingBean productBean = this;
	  switch(getCurrentAction()){
	    case WebConstants.ACTION_CREATE:
	    	productBean.resetBean();
			session.setSalesModule_selectedProduct(0L);
	    	changeTabPath(0, "/view/sales/products/productForm.xhtml");
			break;
		case WebConstants.ACTION_SAVE:			
			if(file != null)
				uploadProductImage();
			try{
			   umCompany = companyDao.companyDetails(session.getCompanyId());	
		    }catch(Exception exception){
			   System.out.println(exception.getMessage());
			}
			ProductORM addproduct = new ProductORM(getProductTitle(), getProductCost(), getProductDescription(), getProductType(), getProductImage(), umCompany);
			product_id = productDao.createProduct(addproduct);
			productBean.setProductId(product_id);
			session.setSalesModule_selectedProduct(product_id);
		    addMessage(getProperty("message.sales.product.created"));
		    reset();
		    productDetails();
			break;  
		case WebConstants.ACTION_VIEW:
			productDetails();
			break;
		case WebConstants.ACTION_CANCEL:
			try{
				product_id = Long.valueOf(getParameter("product_id".toString()));
				if(product_id != null && product_id != 0){
					session.setSalesModule_selectedProduct(product_id);
					productDetails();
				}else{
					session.resetSalesProductModule();
				}
			}catch(Exception e){
				session.resetSalesProductModule();
			}
			break;
		case WebConstants.ACTION_EDIT:
			product_id = Long.valueOf(getParameter("product_id").toString());
			productDetails();
			reset();
			setEditAction();
			break;
			
		case WebConstants.ACTION_UPDATE:
			if(file != null)
				uploadProductImage();
			try{
			   umCompany = companyDao.companyDetails(session.getCompanyId());	
			}catch(Exception exception){
			   System.out.println(exception.getMessage());
			}
			product_id = Long.valueOf(getParameter("product_id".toString()));
			ProductORM editproduct = new ProductORM(product_id, getProductTitle(), getProductCost(), getProductDescription(), getProductType(), getProductImage(), umCompany);
			productDao.updateProduct(editproduct);
			reset();
			productDetails();
			addMessage(getProperty("message.sales.product.updated"));
			break;
		case WebConstants.ACTION_DELETE:
			try{
				product_id = Long.valueOf(getParameter("product_id").toString());
				loadProduct(product_id);
				changeTabPath(0, "/view/sales/products/productForm.xhtml");
				reset();
				setDeleteAction();
			}
			catch (Exception e) {
				changeTabPath(0, "/view/sales/products/productNoSelection.xhtml");
			}
			break;
		case WebConstants.ACTION_DELETE_CONFIRMED:
			try{
				deleteProduct(productBean.getProductId());
				session.setSalesModule_selectedProduct(0L);
			}
			catch(Exception deleteExpception){
				changeTabPath(0, "/view/sales/products/productNoSelection.xhtml");
			}
			break;
		}
		return(null);
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
	
}