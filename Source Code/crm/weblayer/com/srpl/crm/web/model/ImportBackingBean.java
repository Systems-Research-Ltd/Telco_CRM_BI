package com.srpl.crm.web.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
//import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.mail.Session;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DualListModel;
import org.primefaces.model.UploadedFile;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

import com.bitguiders.util.jsf.JSFBeanInterface;
import com.bitguiders.util.jsf.JSFBeanSupport;
import com.bitguiders.util.jsf.WebConstants;
import com.srpl.crm.web.controller.BeanFactory;
import com.srpl.crm.ejb.entity.CsAccount;
import com.srpl.crm.ejb.entity.CsContactORM;
import com.srpl.crm.ejb.entity.OrderDetailORM;
import com.srpl.crm.ejb.entity.OrderORM;
import com.srpl.crm.ejb.entity.PaymentORM;
import com.srpl.crm.ejb.entity.ProductORM;
import com.srpl.crm.ejb.entity.SPackageORM;
import com.srpl.crm.ejb.entity.TableMapping;
import com.srpl.um.ejb.entity.City;
import com.srpl.um.ejb.entity.Country;
import com.srpl.um.ejb.entity.State;
import com.srpl.um.ejb.entity.UmCompany;
import com.srpl.um.ejb.entity.UmUser;
import com.srpl.crm.ejb.exceptions.AccountNotFoundException;
import com.srpl.crm.ejb.exceptions.ContactNotFoundException;
import com.srpl.crm.ejb.exceptions.ProductNotFoundException;
import com.srpl.um.ejb.exceptions.UserNotFoundException;
import com.srpl.crm.ejb.request.AccountDAO;
import com.srpl.crm.ejb.request.ContactDAO;
import com.srpl.crm.ejb.request.InvoiceDAO;
import com.srpl.crm.ejb.request.OpportunityDAO;
import com.srpl.crm.ejb.request.OrderDAO;
import com.srpl.crm.ejb.request.PackageDAO;
import com.srpl.crm.ejb.request.PaymentDAO;
import com.srpl.crm.ejb.request.ProductDAO;
import com.srpl.crm.ejb.request.UtilsDAO;
import com.srpl.crm.web.common.SessionDataBean;
import com.srpl.crm.web.controller.AlertsAndRemindersAdmin;
import com.srpl.um.ejb.request.CompanyDAO;
import com.srpl.um.ejb.request.UserDAO;

@ManagedBean(name="import")
@SessionScoped
public class ImportBackingBean extends JSFBeanSupport implements JSFBeanInterface, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String fileFormat = "CSV";	
	private List<TableMapping> savedMappings;
	private Long mapId;
	private String dbType = "PostgreSQL";
	private String dbHost;
	private String dbPort;
	private String dbName;
	private String dbUser;
	private String dbPassword;
	private Boolean useData = false;
	private List<String> dbTables;
	private List<String> selDBTables;
	private String selTable;
	private List<String> tableColumns;
	private List<String> tableColumnsBox;
	private List<String> selectedColumns;
	private List<String> selectedColumnsBox;
	private Boolean rowHeader = true;
	private String mappingTable = "USER";
	private List<String> mappingColumns;
	private UploadedFile file;
	private Integer[] selectedCSVHeader;
	private List<String> csvHeader;
	private String[] defaultValues;	
	private String savedMap;
	private String connMessage;
	private boolean visible = false;
	private List<String> currSelectedTables;
	private List<String> allSelectedTables;
	private List<String> selectedTables;
	private boolean afterVisible = false;
	private List<String> alldata;
	private String[] mapTo;
	private String[] mapWith;
	private Integer[] itrList;
	private List<String> joinClauseList;
	private List<String> joinClause;
	private String leftTable;
	private String rightTable;
	private List<String> leftColumnList;
	private List<String> rightColumnList;
	private String leftColumn;
	private String rightColumn;
	private DualListModel<String> dualListTables;	
	private List<String> dbTypes;

	private String filePath;
	private String fileSource;
	private Timestamp stamp;	
	private List<UmUser> listUsers;
	private List<CsAccount> listAccounts;
	private List<CsContactORM> listContacts;
	private List<PaymentORM> listPayments;
	private List<ProductORM> listProducts;
	private List<SPackageORM> listPackages;
	private List<OrderORM> listOrders;
	private List<OrderDetailORM> listOrderDetails;
	private List<Country> listCountries;
	private List<State> listStates;
	private List<City> listCities;
	private int totalRows;
	private int correctRows;
	private int affectedRows;
	private String currPage = "tempdata";
	private String colMap;
	private String configConn;
	private String configQuery;
	private String driver;
	
	@ManagedProperty(value="#{alertsAndRemindersAdmin}")
	private AlertsAndRemindersAdmin alerts;
	
	@EJB UserDAO userDao;
	@EJB CompanyDAO companyDao;
	@EJB AccountDAO accountDao;
	@EJB ContactDAO contactDao;
	@EJB PaymentDAO paymentDao;
	@EJB InvoiceDAO invoiceDao;
	@EJB ProductDAO productDao;
	@EJB PackageDAO packageDao;
	@EJB OrderDAO orderDao;
	@EJB UtilsDAO utilsDao;
	@EJB OpportunityDAO opportunityDao;
	
	@PostConstruct
	public void initFields(){
		savedMappings = new ArrayList<TableMapping>();
		dbTables = new ArrayList<String>();	
		tableColumns = new ArrayList<String>();
		tableColumnsBox = new ArrayList<String>();
		selectedColumns = new ArrayList<String>();
		selectedColumnsBox = new ArrayList<String>();	
		mappingColumns = new ArrayList<String>(Arrays.asList("First Name", "Last Name", "User ID", "Password", "Address", "Phone No", "Email", "Job Title", "Status"));
		csvHeader = new ArrayList<String>();
		selectedCSVHeader = new Integer[mappingColumns.size()];
		defaultValues = new String[mappingColumns.size()];
		currSelectedTables = new ArrayList<String>();
		allSelectedTables = new ArrayList<String>();
		selectedTables = new ArrayList<String>();
		alldata = new ArrayList<String>();		
		listUsers = new ArrayList<UmUser>();
		listAccounts = new ArrayList<CsAccount>();
		listContacts = new ArrayList<CsContactORM>();
		listPayments = new ArrayList<PaymentORM>();
		listProducts = new ArrayList<ProductORM>();
		listPackages = new ArrayList<SPackageORM>();
		listOrders = new ArrayList<OrderORM>();
		listOrderDetails = new ArrayList<OrderDetailORM>();
		listCountries = new ArrayList<Country>();
		listStates = new ArrayList<State>();
		listCities = new ArrayList<City>();
		joinClauseList = new ArrayList<String>();
		joinClause = new ArrayList<String>();
		leftColumnList = new ArrayList<String>();
		rightColumnList = new ArrayList<String>();
		dualListTables = new DualListModel<String>(dbTables,selectedColumns);
		dbTypes = new ArrayList<String>(Arrays.asList("PostgreSQL", "mySQL", "SQLServer", "Oracle"));
	}

	public ImportBackingBean(){
		setCurrentAction(WebConstants.ACTION_SECURITY, this.getClass());
	}

	public String getFileFormat() {
		return fileFormat;
	}

	public void setFileFormat(String fileFormat) {
		this.fileFormat = fileFormat;
	}	

	public List<TableMapping> getSavedMappings() {
		return savedMappings;
	}

	public void setSavedMappings(List<TableMapping> savedMappings) {
		this.savedMappings = savedMappings;
	}

	public Long getMapId() {
		return mapId;
	}

	public void setMapId(Long mapId) {
		this.mapId = mapId;
	}	

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	public String getDbHost() {
		return dbHost;
	}

	public void setDbHost(String dbHost) {
		this.dbHost = dbHost;
	}

	public String getDbPort() {
		return dbPort;
	}

	public void setDbPort(String dbPort) {
		this.dbPort = dbPort;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getDbUser() {
		return dbUser;
	}

	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}	

	public Boolean getUseData() {
		return useData;
	}
	
	public void setUseData(Boolean useData) {
		this.useData = useData;
	}

	public List<String> getDbTables() {
		return dbTables;
	}

	public void setDbTables(List<String> dbTables) {
		this.dbTables = dbTables;
	}	

	public String getSelTable() {
		return selTable;
	}

	public void setSelTable(String selTable) {
		this.selTable = selTable;
	}

	public Boolean getRowHeader() {
		return rowHeader;
	}

	public void setRowHeader(Boolean rowHeader) {
		this.rowHeader = rowHeader;
	}

	public String getMappingTable() {
		return mappingTable;
	}

	public void setMappingTable(String mappingTable) {
		this.mappingTable = mappingTable;
	}	

	public List<String> getMappingColumns() {
		return mappingColumns;
	}

	public void setMappingColumns(List<String> mappingColumns) {
		this.mappingColumns = mappingColumns;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}	
	
	public List<String> getCsvHeader() {
		return csvHeader;
	}

	public void setCsvHeader(List<String> csvHeader) {
		this.csvHeader = csvHeader;
	}	

	public Integer[] getSelectedCSVHeader() {
		return selectedCSVHeader;
	}

	public void setSelectedCSVHeader(Integer[] selectedCSVHeader) {
		this.selectedCSVHeader = selectedCSVHeader;
	}	

	public String[] getDefaultValues() {
		return defaultValues;
	}

	public void setDefaultValues(String[] defaultValues) {
		this.defaultValues = defaultValues;
	}	

	public String getSavedMap() {
		return savedMap;
	}

	public void setSavedMap(String savedMap) {
		this.savedMap = savedMap;
	}

	public String getConnMessage() {
		return connMessage;
	}

	public void setConnMessage(String connMessage) {
		this.connMessage = connMessage;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public List<String> getCurrSelectedTables() {
		return currSelectedTables;
	}

	public void setCurrSelectedTables(List<String> currSelectedTables) {
		this.currSelectedTables = currSelectedTables;
	}

	public List<String> getAllSelectedTables() {
		return allSelectedTables;
	}

	public void setAllSelectedTables(List<String> allSelectedTables) {
		this.allSelectedTables = allSelectedTables;
	}

	public List<String> getSelectedTables() {
		return selectedTables;
	}

	public void setSelectedTables(List<String> selectedTables) {
		this.selectedTables = selectedTables;
	}

	public boolean isAfterVisible() {
		return afterVisible;
	}

	public void setAfterVisible(boolean afterVisible) {
		this.afterVisible = afterVisible;
	}

	public String[] getMapTo() {
		return mapTo;
	}

	public void setMapTo(String[] mapTo) {
		this.mapTo = mapTo;
	}

	public String[] getMapWith() {
		return mapWith;
	}

	public void setMapWith(String[] mapWith) {
		this.mapWith = mapWith;
	}

	public List<String> getAlldata() {
		return alldata;
	}

	public void setAlldata(List<String> alldata) {
		this.alldata = alldata;
	}

	public Integer[] getItrList() {
		return itrList;
	}

	public void setItrList(Integer[] itrList) {
		this.itrList = itrList;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}	

	public String getFileSource() {
		return fileSource;
	}

	public void setFileSource(String fileSource) {
		this.fileSource = fileSource;
	}	

	public Timestamp getStamp() {
		return stamp;
	}

	public void setStamp(Timestamp stamp) {
		this.stamp = stamp;
	}	

	public List<UmUser> getListUsers() {
		return listUsers;
	}

	public void setListUsers(List<UmUser> listUsers) {
		this.listUsers = listUsers;
	}

	public List<CsAccount> getListAccounts() {
		return listAccounts;
	}

	public void setListAccounts(List<CsAccount> listAccounts) {
		this.listAccounts = listAccounts;
	}

	public List<CsContactORM> getListContacts() {
		return listContacts;
	}

	public void setListContacts(List<CsContactORM> listContacts) {
		this.listContacts = listContacts;
	}		

	public List<PaymentORM> getListPayments() {
		return listPayments;
	}

	public void setListPayments(List<PaymentORM> listPayments) {
		this.listPayments = listPayments;
	}	

	public List<ProductORM> getListProducts() {
		return listProducts;
	}

	public void setListProducts(List<ProductORM> listProducts) {
		this.listProducts = listProducts;
	}

	public List<SPackageORM> getListPackages() {
		return listPackages;
	}

	public void setListPackages(List<SPackageORM> listPackages) {
		this.listPackages = listPackages;
	}

	public List<OrderORM> getListOrders() {
		return listOrders;
	}

	public void setListOrders(List<OrderORM> listOrders) {
		this.listOrders = listOrders;
	}

	public List<OrderDetailORM> getListOrderDetails() {
		return listOrderDetails;
	}

	public void setListOrderDetails(List<OrderDetailORM> listOrderDetails) {
		this.listOrderDetails = listOrderDetails;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}	

	public int getCorrectRows() {
		return correctRows;
	}

	public void setCorrectRows(int correctRows) {
		this.correctRows = correctRows;
	}

	public int getAffectedRows() {
		return affectedRows;
	}

	public void setAffectedRows(int affectedRows) {
		this.affectedRows = affectedRows;
	}

	public String getCurrPage() {
		return currPage;
	}

	public void setCurrPage(String currPage) {
		this.currPage = currPage;
	}
	
	public String getColMap() {
		return colMap;
	}

	public void setColMap(String colMap) {
		this.colMap = colMap;
	}	
	
	public List<String> getSelDBTables() {
		return selDBTables;
	}

	public void setSelDBTables(List<String> selDBTables) {
		this.selDBTables = selDBTables;
	}	

	public List<String> getJoinClauseList() {
		return joinClauseList;
	}

	public void setJoinClauseList(List<String> joinClauseList) {
		this.joinClauseList = joinClauseList;
	}	

	public List<String> getJoinClause() {
		return joinClause;
	}

	public void setJoinClause(List<String> joinClause) {
		this.joinClause = joinClause;
	}	

	public String getLeftTable() {
		return leftTable;
	}

	public void setLeftTable(String leftTable) {
		this.leftTable = leftTable;
	}

	public String getRightTable() {
		return rightTable;
	}

	public void setRightTable(String rightTable) {
		this.rightTable = rightTable;
	}

	public List<String> getLeftColumnList() {
		return leftColumnList;
	}

	public void setLeftColumnList(List<String> leftColumnList) {
		this.leftColumnList = leftColumnList;
	}

	public List<String> getRightColumnList() {
		return rightColumnList;
	}

	public void setRightColumnList(List<String> rightColumnList) {
		this.rightColumnList = rightColumnList;
	}	

	public String getLeftColumn() {
		return leftColumn;
	}

	public void setLeftColumn(String leftColumn) {
		this.leftColumn = leftColumn;
	}

	public String getRightColumn() {
		return rightColumn;
	}

	public void setRightColumn(String rightColumn) {
		this.rightColumn = rightColumn;
	}

	public String getConfigConn() {
		return configConn;
	}

	public void setConfigConn(String configConn) {
		this.configConn = configConn;
	}

	public String getConfigQuery() {
		return configQuery;
	}

	public void setConfigQuery(String configQuery) {
		this.configQuery = configQuery;
	}	

	public DualListModel<String> getDualListTables() {
		return dualListTables;
	}

	public void setDualListTables(DualListModel<String> dualListTables) {
		this.dualListTables = dualListTables;
	}	

	public List<String> getDbTypes() {
		return dbTypes;
	}

	public void setDbTypes(List<String> dbTypes) {
		this.dbTypes = dbTypes;
	}	
	
	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public void resetBeanData(){
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.remove("import");
	}

	public void changeFields(AjaxBehaviorEvent event){		
		switch(mappingTable){
			case "USER"    : mappingColumns = new ArrayList<String>(Arrays.asList("First Name", "Last Name", "User ID", "Password", "Address", "Phone No", "Email", "Job Title", "Status")); break;
			case "ACCOUNT" : mappingColumns = new ArrayList<String>(Arrays.asList("Account Title", "Address", "Email", "Phone", "Company Account", "Status")); break;
			case "CONTACT" : mappingColumns = new ArrayList<String>(Arrays.asList("First Name","Last Name","Father Name","Address","Email","Phone","Date of Birth","CNIC No.","Account ID","User ID","Password","Status")); break;
			case "PAYMENT" : mappingColumns = new ArrayList<String>(Arrays.asList("Invoice ID","Total Amount","Paid Amount","Paid On","Customer ID")); break;
			case "PRODUCT" : mappingColumns = new ArrayList<String>(Arrays.asList("Product Title","Product Cost","Product Description","Product Type")); break;
			case "PACKAGE" : mappingColumns = new ArrayList<String>(Arrays.asList("Package Title","Package Cost","Product ID")); break;
			case "ORDER"   : mappingColumns = new ArrayList<String>(Arrays.asList("Customer ID","Order Title","Order Date","Order Status","Opportunity ID","Order Modification Date","Created By","Assigned To","Product ID","Product Quantity","Order Amount","Paid Amount")); break;
			case "COUNTRY" : mappingColumns = new ArrayList<String>(Arrays.asList("Country Name","Country Code")); break;
			case "STATE"   : mappingColumns = new ArrayList<String>(Arrays.asList("State Name","State Code","Country ID")); break;
			case "CITY"    : mappingColumns = new ArrayList<String>(Arrays.asList("City Name","State ID")); break;
			default 	   : mappingColumns = new ArrayList<String>(Arrays.asList("First Name", "Last Name", "User ID", "Password", "Address", "Phone No", "Email", "Job Title", "Status"));
		}
		savedMappings.clear();
		if(useData) mappingColumns.add("ID");
		else mappingColumns.remove("ID");
		selectedCSVHeader = new Integer[mappingColumns.size()];
		defaultValues = new String[mappingColumns.size()];
	}
	
	public void addID(){
		if(useData) mappingColumns.add("ID");
		else mappingColumns.remove("ID");
		selectedCSVHeader = new Integer[mappingColumns.size()];
		defaultValues = new String[mappingColumns.size()];
	}
	
	public boolean mapRequired(String title){
		switch(mappingTable){
			case "USER" : 
				List<String> utitles = new ArrayList<String>(Arrays.asList("First Name", "Last Name", "User ID", "Password", "Address", "Phone No", "Email", "Job Title", "Status"));
				if(useData) utitles.add("ID");
				else utitles.remove("ID");
				for(String x : utitles){
					if(x.equals(title)){
						return true;
					}
				}	
				break; 
			case "ACCOUNT" : 
				List<String> atitles = new ArrayList<String>(Arrays.asList("Account Title", "Address", "Phone", "Company Account", "Status"));
				if(useData) atitles.add("ID");
				else atitles.remove("ID");
				for(String x : atitles){
					if(x.equals(title)){
						return true;
					}
				}	
				break;
			case "CONTACT" : 
				List<String> ctitles = new ArrayList<String>(Arrays.asList("First Name","Last Name","Father Name","Address","Phone","CNIC No.","User ID","Password","Status"));
				if(useData) ctitles.add("ID");
				else ctitles.remove("ID");
				for(String x : ctitles){
					if(x.equals(title)){
						return true;
					}
				}	
				break;
			case "PAYMENT" : 
				List<String> ptitles = new ArrayList<String>(Arrays.asList("Invoice ID", "Total Amount", "Paid Amount", "Paid On", "Customer ID"));
				if(useData) ptitles.add("ID");
				else ptitles.remove("ID");
				for(String x : ptitles){
					if(x.equals(title)){
						return true;
					}
				}	
				break;
			case "PRODUCT" : 
				List<String> protitles = new ArrayList<String>(Arrays.asList("Product Title", "Product Cost", "Product Type"));
				if(useData) protitles.add("ID");
				else protitles.remove("ID");
				for(String x : protitles){
					if(x.equals(title)){
						return true;
					}
				}	
				break;
			case "PACKAGE" : 
				List<String> pactitles = new ArrayList<String>(Arrays.asList("Package Title", "Package Cost", "Product ID"));
				if(useData) pactitles.add("ID");
				else pactitles.remove("ID");
				for(String x : pactitles){
					if(x.equals(title)){
						return true;
					}
				}	
				break;
			case "ORDER" : 
				List<String> otitles = new ArrayList<String>(Arrays.asList("Customer ID","Order Date","Order Status","Created By","Assigned To","Product ID","Product Quantity","Order Amount","Paid Amount"));
				if(useData) otitles.add("ID");
				else otitles.remove("ID");
				for(String x : otitles){
					if(x.equals(title)){
						return true;
					}
				}	
				break;
			case "COUNTRY" : 
				List<String> ctrytitles = new ArrayList<String>(Arrays.asList("Country Name","Country Code"));
				if(useData) ctrytitles.add("ID");
				else ctrytitles.remove("ID");
				for(String x : ctrytitles){
					if(x.equals(title)){
						return true;
					}
				}	
				break;
			case "STATE" : 
				List<String> stitles = new ArrayList<String>(Arrays.asList("State Name","State Code","Country ID"));
				if(useData) stitles.add("ID");
				else stitles.remove("ID");
				for(String x : stitles){
					if(x.equals(title)){
						return true;
					}
				}	
				break;
			case "CITY" : 
				List<String> cttitles = new ArrayList<String>(Arrays.asList("City Name","State ID"));
				if(useData) cttitles.add("ID");
				else cttitles.remove("ID");
				for(String x : cttitles){
					if(x.equals(title)){
						return true;
					}
				}	
				break;	
		}
		return false;
	}
	
	public void handleFileUpload(FileUploadEvent event) {
		try {
			String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
	    	SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss");			
	    	fileSource = event.getFile().getFileName();
	    	String fileType = fileSource.substring(fileSource.lastIndexOf(".")+1);
	    	filePath = path + "resources/documents/" + fmt.format(new Date()) + ((fileType.equals("csv")) ? fileSource : fileSource.substring(0,fileSource.lastIndexOf("."))+".csv");
	    	File outfile = new File(this.filePath);
			InputStream in = event.getFile().getInputstream();
			BufferedWriter out = new BufferedWriter(new FileWriter(outfile));
	    	if(fileFormat.equals("CSV") && fileType.equals("csv")){
				BufferedReader input = new BufferedReader(new InputStreamReader(in));
				String line = null;
				while((line = input.readLine()) != null){
					out.write(line);
					out.newLine();
					out.flush();
				}
				out.close();
	    	}else if(fileFormat.equals("XLS") && (fileType.equals("xls") || fileType.equals("xlsx"))){
	    		CSVWriter writer = new CSVWriter(new FileWriter(outfile));
	    		Workbook workbook;
				try {
					workbook = WorkbookFactory.create(in);
					System.out.println(workbook.getMissingCellPolicy());
		    		Sheet sheet = workbook.getSheetAt(0);
		    		int trows = sheet.getRow(0).getLastCellNum();
		    		for (Row row : sheet) {
		    			String[] line = new String[trows];
		    			for(int i=0; i<trows; i++){
		    				Cell cell = row.getCell(i, Row.RETURN_BLANK_AS_NULL);
		    				if(cell == null){
		    					line[i] = "";
		    				}else{
		    					cell.setCellType(Cell.CELL_TYPE_STRING);
			    				line[i] = cell.getRichStringCellValue().getString();
		    				}
		    			}
		    			writer.writeNext(line);
		    		}
					writer.close();
				} catch (InvalidFormatException e) {
					e.printStackTrace();
				}				
	    	}else{
	    		FacesMessage message = new FacesMessage("Provided file type mismatches with the selected import type");
	    		FacesContext.getCurrentInstance().addMessage(null, message);
	    		return;
	    	}
	    	CSVReader inputFile = new CSVReader(new FileReader(outfile));
	    	String[] tokens = inputFile.readNext();
			csvHeader.clear();
			for(String token : tokens){
				csvHeader.add(token);				
			}
			savedMappings = utilsDao.getMappings(mappingTable);
		} catch (IOException e) {
			e.printStackTrace();
		} 
    }
	
	public String importCSVData(){
		Boolean isCSV = (fileFormat.equals("CSV") || fileFormat.equals("XLS")) ? true : false;
		CSVReader input = null;
		ResultSet res = null;
		configQuery = "";
		totalRows = 0;
		affectedRows = 0;
		java.util.Date date= new java.util.Date();
		stamp = new Timestamp(date.getTime());
		try {
			if(isCSV){
				input = new CSVReader(new FileReader(filePath));
				if(rowHeader.equals(true))
					input.readNext();
			}else{
				Connection conn = null;
				String whrclause = null;
				conn = dbConnection();
				try {
					Statement st = conn.createStatement();
					StringBuilder sb = new StringBuilder();
					for(int i=0; i<csvHeader.size(); i++){
						sb.append(csvHeader.get(i));
						if(i != (csvHeader.size()-1))
							sb.append(",");
					}
					StringBuilder sbs = new StringBuilder();
					for(int i=0; i<selectedColumns.size(); i++){
						sbs.append(selectedColumns.get(i));
						if(i != (selectedColumns.size()-1))
							sbs.append(",");
					}
					if(selectedColumns.size() > 1){
						int z = 0;
						whrclause = " WHERE ";
						for(String x : joinClauseList){
							whrclause += x;
							if(joinClauseList.size() > ++z)
								whrclause += " AND ";
						}
					}
					configQuery = "SELECT "+sb.toString().replace("->", ".")+" FROM "+sbs.toString()+((selectedColumns.size() > 1) ? whrclause : "");
					res = st.executeQuery(configQuery);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			switch(mappingTable) {
				case "USER" 	: importUserData(isCSV, input, res); 	totalRows = listUsers.size(); break;
				case "ACCOUNT" 	: importAccountData(isCSV, input, res); totalRows = listAccounts.size(); break;
				case "CONTACT" 	: importContactData(isCSV, input, res); totalRows = listContacts.size(); break;
				case "PAYMENT" 	: importPaymentData(isCSV, input, res); totalRows = listPayments.size(); break;
				case "PRODUCT" 	: importProductData(isCSV, input, res); totalRows = listProducts.size(); break;
				case "PACKAGE" 	: importPackageData(isCSV, input, res); totalRows = listPackages.size(); break;
				case "ORDER" 	: importOrderData(isCSV, input, res); 	totalRows = listOrders.size(); break;
				case "COUNTRY" 	: importCountryData(isCSV, input, res); totalRows = listCountries.size(); break;
				case "STATE" 	: importStateData(isCSV, input, res); 	totalRows = listStates.size(); break;
				case "CITY" 	: importCityData(isCSV, input, res); 	totalRows = listCities.size(); break;
			}
			correctRows = totalRows - affectedRows;
			if(!savedMap.isEmpty()){
				StringBuilder sb = new StringBuilder();
				sb.append(selectedCSVHeader[0]);
				for(int i=1; i<selectedCSVHeader.length; i++){
					sb.append(",").append(selectedCSVHeader[i]);
				}
				utilsDao.saveMapping(savedMap, mappingTable, sb.toString());
				colMap = savedMap;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "mapdata";
	}
	
	public String saveConfiguration(){
		importCSVData();
		StringBuilder sb = new StringBuilder();
		sb.append(selectedCSVHeader[0]);
		for(int i=1; i<selectedCSVHeader.length; i++){
			sb.append(",").append(selectedCSVHeader[i]);
		}
		Long mappingId = utilsDao.saveMapping("", mappingTable, sb.toString());
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		utilsDao.saveConfig(session.getCompanyId(),configQuery,configConn,dbUser,dbPassword,driver,mappingId);
		saveCSVData();
		resetBeanData();
		FacesMessage message = new FacesMessage("DB Configuration Data saved successfully!");
		FacesContext.getCurrentInstance().addMessage(null, message);
		return "import";
	}
	
	public void importUserData(Boolean isCSV, CSVReader input, ResultSet res){		
		
				try{
					String[] splitLine = new String[csvHeader.size()];
					listUsers.clear();
					int cnt = 0;
					while((isCSV) ? ((splitLine = input.readNext()) != null) : (res.next())){
						if(!isCSV){
							for(int i=1; i<=csvHeader.size(); i++){
								splitLine[i-1] = res.getString(i);
							}
						}
						UmUser usr = new UmUser();
						cnt++;
						String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
						StringBuilder message = new StringBuilder();
						for(int i=0; i<selectedCSVHeader.length; i++){
							if(selectedCSVHeader[i] != 0){
								if(splitLine[selectedCSVHeader[i]-1].equals("") && !defaultValues[i].equals("")){
									splitLine[selectedCSVHeader[i]-1] = defaultValues[i];
								}
								if(splitLine[selectedCSVHeader[i]-1].equals("") && mapRequired(mappingColumns.get(i))){
									if(message.length() == 0) message.append("Record No. "+cnt+": ");
									switch(i){
										case 0: message.append("First Name Required;"); break;
										case 1: message.append("Last Name Required;"); break;
										case 2: message.append("User Name Required;"); break;
										case 3: message.append("Password Required;"); break;
										case 4: message.append("Address Required;"); break;
										case 5: message.append("Phone No Required;"); break;
										case 6: message.append("Email Required;"); break;
										case 7: message.append("Job Title Required;"); break;
										case 8: message.append("Status Required;"); break;
									}
								}
							}
						}
						SessionDataBean session = BeanFactory.getInstance().getSessionBean();
						if(!splitLine[selectedCSVHeader[2]-1].equals("") && userDao.userNameExists(splitLine[selectedCSVHeader[2]-1], session.getCompanyId())) {
							if(message.length() == 0) message.append("Record No. "+cnt+": ");
							message.append("User Name Already Exists;");
						}
						if(!splitLine[selectedCSVHeader[6]-1].equals("") && !splitLine[selectedCSVHeader[6]-1].matches(regex)) {
							if(message.length() == 0) message.append("Record No. "+cnt+": ");
							message.append("Invalid Email;");
						}
						if(message.length() > 0){
							 affectedRows += 1;
							 usr.setIsCorrect("cross.jpg");
							 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,message.toString(),message.toString()));
						}else{
							usr.setIsCorrect("tick.jpg");
						}
						usr.setUserFname(splitLine[selectedCSVHeader[0]-1]);
						usr.setUserLname(splitLine[selectedCSVHeader[1]-1]);
						usr.setUserName(splitLine[selectedCSVHeader[2]-1]);
						usr.setUserPassword(splitLine[selectedCSVHeader[3]-1]);
						usr.setUserAddress(splitLine[selectedCSVHeader[4]-1]);
						usr.setUserPhone(splitLine[selectedCSVHeader[5]-1]);
						usr.setUserEmail(splitLine[selectedCSVHeader[6]-1]);
						usr.setUserJobtitle(splitLine[selectedCSVHeader[7]-1]);
						usr.setUserStatus(splitLine[selectedCSVHeader[8]-1].equals("Active") || splitLine[selectedCSVHeader[8]-1].equals("1") || splitLine[selectedCSVHeader[8]-1].equals("true"));
						usr.setUserCompany(session.getCompanyId());
						usr.setUserAddedon(stamp);
						usr.setIsFranchiseUser(false);
						usr.setIsOnline(false);
						usr.setUserCountry(169);
						usr.setUserState(3021);
						usr.setUserCity(14548);
						usr.setIsUserCustomer(false);
						if(useData) usr.setMappedId(Long.parseLong(splitLine[selectedCSVHeader[9]-1]));
						listUsers.add(usr);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
	}
	
	public void importAccountData(Boolean isCSV, CSVReader input, ResultSet res){
		
				try{
					String[] splitLine = new String[csvHeader.size()];
					listAccounts.clear();
					int cnt = 0;
					SessionDataBean session = BeanFactory.getInstance().getSessionBean();
					while((isCSV) ? ((splitLine = input.readNext()) != null) : (res.next())){
						if(!isCSV){
							for(int i=1; i<=csvHeader.size(); i++){
								splitLine[i-1] = res.getString(i);
							}
						}
						CsAccount accnt = new CsAccount();
						cnt++;
						String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
						StringBuilder message = new StringBuilder();
						for(int i=0; i<selectedCSVHeader.length; i++){
							if(selectedCSVHeader[i] != 0){
								if(splitLine[selectedCSVHeader[i]-1].equals("") && !defaultValues[i].equals("")){
									splitLine[selectedCSVHeader[i]-1] = defaultValues[i];
								}
								if(splitLine[selectedCSVHeader[i]-1].equals("") && mapRequired(mappingColumns.get(i))){
									if(message.length() == 0) message.append("Record No. "+cnt+": ");
									switch(i){
										case 0: message.append("Account Title Required;"); break;
										case 1: message.append("Address Required;"); break;
										case 3: message.append("Phone Required;"); break;
										case 4: message.append("Is Company Account Required;"); break;
										case 5: message.append("Status Required;"); break;
									}
								}
							}
						}
						if(selectedCSVHeader[2] != 0 && !splitLine[selectedCSVHeader[2]-1].equals("")){
							if(!splitLine[selectedCSVHeader[2]-1].matches(regex)) {
								if(message.length() == 0) message.append("Record No. "+cnt+": ");
								message.append("Invalid Email;");
							}
						}	
						if(message.length() > 0){
							 affectedRows += 1;
							 accnt.setIsCorrect("cross.jpg");
							 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,message.toString(),message.toString()));
						}else{
							 accnt.setIsCorrect("tick.jpg");
						}		
						accnt.setAccountTitle(splitLine[selectedCSVHeader[0]-1]);
			    		accnt.setAccountAddress(splitLine[selectedCSVHeader[1]-1]);
			    		accnt.setAccountEmail((selectedCSVHeader[2] != 0) ? splitLine[selectedCSVHeader[2]-1] : "");
			    		accnt.setAccountPhone(splitLine[selectedCSVHeader[3]-1]);
			    		accnt.setAccountIscompany(splitLine[selectedCSVHeader[4]-1].equals("true") || splitLine[selectedCSVHeader[4]-1].equals("Yes") || splitLine[selectedCSVHeader[4]-1].equals("1"));
			    		accnt.setAccountStatus(splitLine[selectedCSVHeader[5]-1].equals("Active") || splitLine[selectedCSVHeader[5]-1].equals("true") || splitLine[selectedCSVHeader[5]-1].equals("1"));
			    		accnt.setAccountCreatedon(stamp);
			    		accnt.setAccountCountry(169);
			    		accnt.setAccountState(3021);
			    		accnt.setAccountCity(14548);
			    		accnt.setCompanyId(session.getCompanyId());
						if(useData) accnt.setMappedId(Long.parseLong(splitLine[selectedCSVHeader[6]-1]));
			    		listAccounts.add(accnt);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			
	}
	
	public void importCountryData(Boolean isCSV, CSVReader input, ResultSet res){
		
				try{
					String[] splitLine = new String[csvHeader.size()];
					int cnt = 0;
					listCountries.clear();
					while((isCSV) ? ((splitLine = input.readNext()) != null) : (res.next())){
						if(!isCSV){
							for(int i=1; i<=csvHeader.size(); i++){
								splitLine[i-1] = res.getString(i);
							}
						}
						Country ctry = new Country();
						cnt++;
						StringBuilder message = new StringBuilder();
						for(int i=0; i<selectedCSVHeader.length; i++){
							if(selectedCSVHeader[i] != 0){
								if(splitLine[selectedCSVHeader[i]-1].equals("") && !defaultValues[i].equals("")){
									splitLine[selectedCSVHeader[i]-1] = defaultValues[i];
								}
								if(splitLine[selectedCSVHeader[i]-1].equals("") && mapRequired(mappingColumns.get(i))){
									if(message.length() == 0) message.append("Record No. "+cnt+": ");
									switch(i){
										case 0: message.append("Country Name Required;"); break;
										case 1: message.append("Country Code Required;"); break;
									}
								}
							}
						}
						if(message.length() > 0){
							 affectedRows += 1;
							 ctry.setIsCorrect("cross.jpg");
							 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,message.toString(),message.toString()));
						}else{
							 ctry.setIsCorrect("tick.jpg");
						}		
						ctry.setCountryName(splitLine[selectedCSVHeader[0]-1]);
						ctry.setCountryCode(splitLine[selectedCSVHeader[1]-1]);
						if(useData) ctry.setMappedId(Long.parseLong(splitLine[selectedCSVHeader[2]-1]));
			    		listCountries.add(ctry);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			
	}
	
	public void importStateData(Boolean isCSV, CSVReader input, ResultSet res){
		
				try{
					String[] splitLine = new String[csvHeader.size()];
					int cnt = 0;
					listStates.clear();
					while((isCSV) ? ((splitLine = input.readNext()) != null) : (res.next())){
						if(!isCSV){
							for(int i=1; i<=csvHeader.size(); i++){
								splitLine[i-1] = res.getString(i);
							}
						}
						State sts = new State();
						cnt++;
						StringBuilder message = new StringBuilder();
						for(int i=0; i<selectedCSVHeader.length; i++){
							if(selectedCSVHeader[i] != 0){
								if(splitLine[selectedCSVHeader[i]-1].equals("") && !defaultValues[i].equals("")){
									splitLine[selectedCSVHeader[i]-1] = defaultValues[i];
								}
								if(splitLine[selectedCSVHeader[i]-1].equals("") && mapRequired(mappingColumns.get(i))){
									if(message.length() == 0) message.append("Record No. "+cnt+": ");
									switch(i){
										case 0: message.append("State Name Required;"); break;
										case 1: message.append("State Code Required;"); break;
										case 2: message.append("Country ID Required;"); break;
									}
								}
							}
						}
						if(message.length() > 0){
							 affectedRows += 1;
							 sts.setIsCorrect("cross.jpg");
							 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,message.toString(),message.toString()));
						}else{
							 sts.setIsCorrect("tick.jpg");
						}
						sts.setStateName(splitLine[selectedCSVHeader[0]-1]);
						sts.setStateCode(splitLine[selectedCSVHeader[1]-1]);
						sts.setCountry(utilsDao.ctryDetails(Integer.parseInt(splitLine[selectedCSVHeader[2]-1])));
						if(useData) sts.setMappedId(Long.parseLong(splitLine[selectedCSVHeader[3]-1]));
			    		listStates.add(sts);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			
	}
	
	public void importCityData(Boolean isCSV, CSVReader input, ResultSet res){
		
				try{
					String[] splitLine = new String[csvHeader.size()];
					int cnt = 0;
					listCities.clear();
					while((isCSV) ? ((splitLine = input.readNext()) != null) : (res.next())){
						if(!isCSV){
							for(int i=1; i<=csvHeader.size(); i++){
								splitLine[i-1] = res.getString(i);
							}
						}
						City ct = new City();
						cnt++;
						StringBuilder message = new StringBuilder();
						for(int i=0; i<selectedCSVHeader.length; i++){
							if(selectedCSVHeader[i] != 0){
								if(splitLine[selectedCSVHeader[i]-1].equals("") && !defaultValues[i].equals("")){
									splitLine[selectedCSVHeader[i]-1] = defaultValues[i];
								}
								if(splitLine[selectedCSVHeader[i]-1].equals("") && mapRequired(mappingColumns.get(i))){
									if(message.length() == 0) message.append("Record No. "+cnt+": ");
									switch(i){
										case 0: message.append("City Name Required;"); break;
										case 1: message.append("State ID Required;"); break;
									}
								}
							}
						}
						if(message.length() > 0){
							 affectedRows += 1;
							 ct.setIsCorrect("cross.jpg");
							 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,message.toString(),message.toString()));
						}else{
							 ct.setIsCorrect("tick.jpg");
						}
						ct.setCityName(splitLine[selectedCSVHeader[0]-1]);
						ct.setState(utilsDao.stsDetails(Integer.parseInt(splitLine[selectedCSVHeader[1]-1])));
						if(useData) ct.setMappedId(Long.parseLong(splitLine[selectedCSVHeader[2]-1]));
			    		listCities.add(ct);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			
	}
	
	public void importPaymentData(Boolean isCSV, CSVReader input, ResultSet res){		
		
			try{
				String[] splitLine = new String[csvHeader.size()];
				int cnt = 0;
				listPayments.clear();
				while((isCSV) ? ((splitLine = input.readNext()) != null) : (res.next())){
					if(!isCSV){
						for(int i=1; i<=csvHeader.size(); i++){
							splitLine[i-1] = res.getString(i);
						}
					}
					PaymentORM pmt = new PaymentORM();
					cnt++;
					StringBuilder message = new StringBuilder();
					for(int i=0; i<selectedCSVHeader.length; i++){
						if(selectedCSVHeader[i] != 0){
							if(splitLine[selectedCSVHeader[i]-1].equals("") && !defaultValues[i].equals("")){
								splitLine[selectedCSVHeader[i]-1] = defaultValues[i];
							}
							if(splitLine[selectedCSVHeader[i]-1].equals("") && mapRequired(mappingColumns.get(i))){
								if(message.length() == 0) message.append("Record No. "+cnt+": ");
								switch(i){
									case 0: message.append("Invoice ID Required;"); break;
									case 1: message.append("Total Amount Required;"); break;
									case 2: message.append("Paid Amount Required;"); break;
									case 3: message.append("Paid On Date Required;"); break;
									case 4: message.append("Customer ID Required;"); break;
								}
							}
						}
					}
					if(message.length() > 0){
						 affectedRows += 1;
						 pmt.setIsCorrect("cross.jpg");
						 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,message.toString(),message.toString()));
					}else{
						 pmt.setIsCorrect("tick.jpg");
					}
					pmt.setSInvoice(invoiceDao.details(Long.parseLong(splitLine[selectedCSVHeader[0]-1])));
					pmt.setInvoiceAmount(Integer.parseInt(splitLine[selectedCSVHeader[1]-1]));
					pmt.setPaidAmount(Integer.parseInt(splitLine[selectedCSVHeader[2]-1]));
					pmt.setRemainingAmount(Integer.parseInt(splitLine[selectedCSVHeader[1]-1]) - Integer.parseInt(splitLine[selectedCSVHeader[2]-1]));
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
					pmt.setPaidOnDate((Timestamp)sdf.parse(splitLine[selectedCSVHeader[3]-1]));
					pmt.setSubscriber(contactDao.contactDetails(Long.parseLong(splitLine[selectedCSVHeader[4]-1])));
					pmt.setPaymentCreatedOn(stamp);
					if(useData) pmt.setMappedId(Long.parseLong(splitLine[selectedCSVHeader[5]-1]));
					listPayments.add(pmt);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ContactNotFoundException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
	}
	
	public void importProductData(Boolean isCSV, CSVReader input, ResultSet res){		
		
			try{
				String[] splitLine = new String[csvHeader.size()];
				int cnt = 0;
				listProducts.clear();
				SessionDataBean session = BeanFactory.getInstance().getSessionBean();	
				UmCompany company = companyDao.companyDetails(session.getCompanyId());
				while((isCSV) ? ((splitLine = input.readNext()) != null) : (res.next())){
					if(!isCSV){
						for(int i=1; i<=csvHeader.size(); i++){
							splitLine[i-1] = res.getString(i);
						}
					}
					ProductORM prod = new ProductORM();
					cnt++;
					StringBuilder message = new StringBuilder();
					for(int i=0; i<selectedCSVHeader.length; i++){
						if(selectedCSVHeader[i] != 0){
							if(splitLine[selectedCSVHeader[i]-1].equals("") && !defaultValues[i].equals("")){
								splitLine[selectedCSVHeader[i]-1] = defaultValues[i];
							}
							if(splitLine[selectedCSVHeader[i]-1].equals("") && mapRequired(mappingColumns.get(i))){
								if(message.length() == 0) message.append("Record No. "+cnt+": ");
								switch(i){
									case 0: message.append("Product Title Required;"); break;
									case 1: message.append("Product Cost Required;"); break;
									case 3: message.append("Product Type Required;"); break;
								}
							}
						}
					}
					if(message.length() > 0){
						 affectedRows += 1;
						 prod.setIsCorrect("cross.jpg");
						 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,message.toString(),message.toString()));
					}else{
						 prod.setIsCorrect("tick.jpg");
					}
					prod.setProductTitle(splitLine[selectedCSVHeader[0]-1]);
					prod.setProductCost(Long.parseLong(splitLine[selectedCSVHeader[1]-1]));
					prod.setProductDescription((selectedCSVHeader[2] != 0) ? splitLine[selectedCSVHeader[2]-1] : "");
					prod.setProductType((splitLine[selectedCSVHeader[3]-1].equals("Simple") || splitLine[selectedCSVHeader[3]-1].equals("Value Added")) ? splitLine[selectedCSVHeader[3]-1] : "Simple");
					prod.setProductAddedon(stamp);
					prod.setUmCompany(company);
					if(useData) prod.setMappedId(Long.parseLong(splitLine[selectedCSVHeader[4]-1]));
					listProducts.add(prod);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public void importPackageData(Boolean isCSV, CSVReader input, ResultSet res){		
		
			try{
				String[] splitLine = new String[csvHeader.size()];
				int cnt = 0;
				listPackages.clear();
				SessionDataBean session = BeanFactory.getInstance().getSessionBean();			
				while((isCSV) ? ((splitLine = input.readNext()) != null) : (res.next())){
					if(!isCSV){
						for(int i=1; i<=csvHeader.size(); i++){
							splitLine[i-1] = res.getString(i);
						}
					}
					SPackageORM pkg = new SPackageORM();
					cnt++;
					StringBuilder message = new StringBuilder();
					for(int i=0; i<selectedCSVHeader.length; i++){
						if(selectedCSVHeader[i] != 0){
							if(splitLine[selectedCSVHeader[i]-1].equals("") && !defaultValues[i].equals("")){
								splitLine[selectedCSVHeader[i]-1] = defaultValues[i];
							}
							if(splitLine[selectedCSVHeader[i]-1].equals("") && mapRequired(mappingColumns.get(i))){
								if(message.length() == 0) message.append("Record No. "+cnt+": ");
								switch(i){
									case 0: message.append("Package Title Required;"); break;
									case 1: message.append("Package Cost Required;"); break;
									case 2: message.append("Product ID Required;"); break;
								}
							}
						}
					}
					if(message.length() > 0){
						 affectedRows += 1;
						 pkg.setIsCorrect("cross.jpg");
						 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,message.toString(),message.toString()));
					}else{
						 pkg.setIsCorrect("tick.jpg");
					}
					pkg.setTitle(splitLine[selectedCSVHeader[0]-1]);
					pkg.setCost(Double.parseDouble(splitLine[selectedCSVHeader[1]-1]));
					pkg.setCompanyId(session.getCompanyId());
					pkg.setPackageAddedon(stamp);
					Set<ProductORM> prods = new HashSet<ProductORM>();
					prods.add(productDao.productDetails(Long.parseLong(splitLine[selectedCSVHeader[2]-1])));
					pkg.setProducts(prods);
					if(useData) pkg.setMappedId(Long.parseLong(splitLine[selectedCSVHeader[3]-1]));
					listPackages.add(pkg);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ProductNotFoundException e) {
				e.printStackTrace();
			}
	}
	
	public void importOrderData(Boolean isCSV, CSVReader input, ResultSet res){		
		
			try{
				String[] splitLine = new String[csvHeader.size()];
				int cnt = 0;
				listOrders.clear();
				SessionDataBean session = BeanFactory.getInstance().getSessionBean();			
				while((isCSV) ? ((splitLine = input.readNext()) != null) : (res.next())){
					if(!isCSV){
						for(int i=1; i<=csvHeader.size(); i++){
							splitLine[i-1] = res.getString(i);
						}
					}
					OrderORM ord = new OrderORM();
					cnt++;
					StringBuilder message = new StringBuilder();
					OrderDetailORM ordetail = new OrderDetailORM();
					for(int i=0; i<selectedCSVHeader.length; i++){
						if(selectedCSVHeader[i] != 0){
							if(splitLine[selectedCSVHeader[i]-1].equals("") && !defaultValues[i].equals("")){
								splitLine[selectedCSVHeader[i]-1] = defaultValues[i];
							}
							if(splitLine[selectedCSVHeader[i]-1].equals("") && mapRequired(mappingColumns.get(i))){
								if(message.length() == 0) message.append("Record No. "+cnt+": ");
								switch(i){
									case 0: message.append("Customer ID Required;"); break;
									case 2: message.append("Order Date Required;"); break;
									case 3: message.append("Order Status Required;"); break;
									case 6: message.append("Created By Required;"); break;
									case 7: message.append("Assigned To Required;"); break;
									case 8: message.append("Product ID Required;"); break;
									case 9: message.append("Product Quantity Required;"); break;
									case 10: message.append("Order Amount Required;"); break;
									case 11: message.append("Paid Amount Required;"); break;
								}
							}
						}
					}
					if(message.length() > 0){
						 affectedRows += 1;
						 ord.setIsCorrect("cross.jpg");
						 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,message.toString(),message.toString()));
					}else{
						 ord.setIsCorrect("tick.jpg");
					}
					ord.setCustomer(contactDao.contactDetails(Long.parseLong(splitLine[selectedCSVHeader[0]-1])));
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
					ord.setOrderDate((Timestamp)sdf.parse(splitLine[selectedCSVHeader[2]-1]));
					ord.setStatus((splitLine[selectedCSVHeader[3]-1].equals("open") || splitLine[selectedCSVHeader[3]-1].equals("pending") || splitLine[selectedCSVHeader[3]-1].equals("closed")) ? splitLine[selectedCSVHeader[3]-1] : "open");
					ord.setOpportunity(null);
					ord.setOrderChangeDate((selectedCSVHeader[5] != 0) ? (Timestamp)sdf.parse(splitLine[selectedCSVHeader[5]-1]) : null);
					ord.setCreatedBy(userDao.umUserDetails(Long.parseLong(splitLine[selectedCSVHeader[6]-1])));
					ord.setAssignedTo(userDao.umUserDetails(Long.parseLong(splitLine[selectedCSVHeader[7]-1])));
					ord.setOrderTotalAmount(Double.valueOf(splitLine[selectedCSVHeader[10]-1]));
					ord.setDiscount(0.0);
					ord.setNetAmount(Double.valueOf(splitLine[selectedCSVHeader[10]-1]));
					ord.setPaidAmount(Double.valueOf(splitLine[selectedCSVHeader[11]-1]));
					ord.setOrderChangeDate(new Timestamp(new Date().getTime()));
					ord.setChangedBy(userDao.umUserDetails(Long.parseLong(splitLine[selectedCSVHeader[6]-1])));
					ord.setOrderCreation("Manual");
					ord.setStatus("Active");
					ord.setUmCompany(companyDao.companyDetails(session.getCompanyId()));
					if(useData) ord.setMappedId(Long.parseLong(splitLine[selectedCSVHeader[12]-1]));
					ordetail.setOrder(ord);
					ordetail.setProduct(productDao.productDetails(Long.parseLong(splitLine[selectedCSVHeader[8]-1])));
					ordetail.setQuantity(Integer.parseInt(splitLine[selectedCSVHeader[9]-1]));
					ordetail.setProductTotalAmount(0.0);
					listOrders.add(ord);
					listOrderDetails.add(ordetail);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ContactNotFoundException e) {
				e.printStackTrace();
			} catch (UserNotFoundException e) {
				e.printStackTrace();
			} catch (ProductNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void importContactData(Boolean isCSV, CSVReader input, ResultSet res){
			try{
				String[] splitLine = new String[csvHeader.size()];
				int cnt = 0;			
				listContacts.clear();
				SessionDataBean session = BeanFactory.getInstance().getSessionBean();
				while((isCSV) ? ((splitLine = input.readNext()) != null) : (res.next())){
					if(!isCSV){
						for(int i=1; i<=csvHeader.size(); i++){
							splitLine[i-1] = res.getString(i);
						}
					}
				CsContactORM contact = new CsContactORM();
				cnt++;
				String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";				
				StringBuilder message = new StringBuilder();
				for(int i=0; i<selectedCSVHeader.length; i++){
					if(selectedCSVHeader[i] != 0){
						if(splitLine[selectedCSVHeader[i]-1].equals("") && !defaultValues[i].equals("")){
							splitLine[selectedCSVHeader[i]-1] = defaultValues[i];
						}
						if(splitLine[selectedCSVHeader[i]-1].equals("") && mapRequired(mappingColumns.get(i))){
							if(message.length() == 0) message.append("Record No. "+cnt+": ");
							switch(i){
								case 0: message.append("First Name Required;"); break;
								case 1: message.append("Last Name Required;"); break;
								case 2: message.append("Father Name Required;"); break;
								case 3: message.append("Address Required;"); break;
								case 5: message.append("Phone Required;"); break;
								case 7: message.append("CNIC Required;"); break;
								case 9: message.append("User Name Required;"); break;
								case 10: message.append("Password Required;"); break;
								case 11: message.append("Status Required;"); break;
							}
						}
					}
				}
				if(!splitLine[selectedCSVHeader[9]-1].equals("") && userDao.userNameExists(splitLine[selectedCSVHeader[9]-1], session.getCompanyId())) {
					if(message.length() == 0) message.append("Record No. "+cnt+": ");
					message.append("User Name Already Exists;");
				}
				if(selectedCSVHeader[4] != 0 && !splitLine[selectedCSVHeader[4]-1].equals("") && !splitLine[selectedCSVHeader[4]-1].matches(regex)) {
					if(message.length() == 0) message.append("Record No. "+cnt+": ");
					message.append("Invalid Email;");
				}
				if(message.length() > 0){
					 affectedRows += 1;
					 contact.setIsCorrect("cross.jpg");
					 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,message.toString(),message.toString()));
				}else{
					contact.setIsCorrect("tick.jpg");
				}
	    		contact.setContactFname(splitLine[selectedCSVHeader[0]-1]);
	    		contact.setContactLname(splitLine[selectedCSVHeader[1]-1]);
	    		contact.setContactFatherName(splitLine[selectedCSVHeader[2]-1]);
	    		contact.setContactAddress(splitLine[selectedCSVHeader[3]-1]);
	    		contact.setContactCountry(169);
	    		contact.setContactState(3021);
	    		contact.setContactCity(14548);
	    		contact.setContactEmail((selectedCSVHeader[4] != 0) ? splitLine[selectedCSVHeader[4]-1] : "");
	    		contact.setContactPhone(splitLine[selectedCSVHeader[5]-1]);
	    		contact.setContactDob(null);
	    		contact.setContactCnic(splitLine[selectedCSVHeader[7]-1]);
	    		contact.setCompanyId(session.getCompanyId());
		    	CsAccount accnt = null;
		    	if(selectedCSVHeader[8] != 0){
		    		try {		    			
						if(splitLine[selectedCSVHeader[8]-1].isEmpty() || !accountDao.accntIDExists(Long.parseLong(splitLine[selectedCSVHeader[8]-1]))){
							accnt = new CsAccount();
							accnt.setAccountTitle(splitLine[selectedCSVHeader[0]-1]+" "+splitLine[selectedCSVHeader[1]-1]);
				    		accnt.setAccountAddress(splitLine[selectedCSVHeader[3]-1]);
				    		accnt.setAccountEmail((selectedCSVHeader[4] != 0) ? splitLine[selectedCSVHeader[4]-1] : "");
				    		accnt.setAccountPhone(splitLine[selectedCSVHeader[5]-1]);
				    		accnt.setAccountIscompany(false);
				    		accnt.setAccountCreatedon(stamp);
				    		accnt.setAccountStatus(splitLine[selectedCSVHeader[11]-1].equals("Active") || splitLine[selectedCSVHeader[11]-1].equals("true") || splitLine[selectedCSVHeader[11]-1].equals(1));
				    		accnt.setAccountCountry(169);
				    		accnt.setAccountState(3021);
				    		accnt.setAccountCity(14548);
		    			} else
		    				accnt = accountDao.accountDetails(Long.parseLong(splitLine[selectedCSVHeader[8]-1]));
			    		contact.setCsAccount(accnt);
					} catch (NumberFormatException e) {
						e.printStackTrace();
					} catch (AccountNotFoundException e) {
						e.printStackTrace();
					}
		    	}else{
		    		accnt = new CsAccount();
					accnt.setAccountTitle(splitLine[selectedCSVHeader[0]-1]+" "+splitLine[selectedCSVHeader[1]-1]);
		    		accnt.setAccountAddress(splitLine[selectedCSVHeader[3]-1]);
		    		accnt.setAccountEmail((selectedCSVHeader[4] != 0) ? splitLine[selectedCSVHeader[4]-1] : "");
		    		accnt.setAccountPhone(splitLine[selectedCSVHeader[5]-1]);
		    		accnt.setAccountIscompany(false);
		    		accnt.setAccountCreatedon(stamp);
		    		accnt.setAccountStatus(splitLine[selectedCSVHeader[11]-1].equals("Active") || splitLine[selectedCSVHeader[11]-1].equals("true") || splitLine[selectedCSVHeader[11]-1].equals(1));
		    		accnt.setAccountCountry(169);
		    		accnt.setAccountState(3021);
		    		accnt.setAccountCity(14548);
		    		contact.setCsAccount(accnt);
		    	}
	    		UmUser usr = new UmUser();
	    		usr.setUserFname(splitLine[selectedCSVHeader[0]-1]);
				usr.setUserLname(splitLine[selectedCSVHeader[1]-1]);
				usr.setUserName(splitLine[selectedCSVHeader[9]-1]);
				usr.setUserPassword(splitLine[selectedCSVHeader[10]-1]);
				usr.setUserAddress(splitLine[selectedCSVHeader[3]-1]);
				usr.setUserPhone(splitLine[selectedCSVHeader[5]-1]);
				usr.setUserEmail((selectedCSVHeader[4] != 0) ? splitLine[selectedCSVHeader[4]-1] : "");
				usr.setUserJobtitle("Customer");
				usr.setUserStatus(splitLine[selectedCSVHeader[11]-1].equals("Active") || splitLine[selectedCSVHeader[11]-1].equals("true") || splitLine[selectedCSVHeader[11]-1].equals(1));
				usr.setUserCompany(session.getCompanyId());
				usr.setUserAddedon(stamp);
				usr.setIsFranchiseUser(false);
				usr.setIsOnline(false);
				usr.setUserCountry(169);
				usr.setUserState(3021);
				usr.setUserCity(14548);
				usr.setIsUserCustomer(true);
				contact.setContactUser(usr);
	    		contact.setContactCreatedon(stamp);
	    		contact.setContactStatus(splitLine[selectedCSVHeader[11]-1].equals("Active") || splitLine[selectedCSVHeader[11]-1].equals("true") || splitLine[selectedCSVHeader[11]-1].equals(1));
				if(useData) contact.setMappedId(Long.parseLong(splitLine[selectedCSVHeader[12]-1]));
	    		listContacts.add(contact);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	private Connection dbConnection(){
		Connection conn = null;
		driver = "";
		configConn = "";
		Properties connectionProps = new Properties();
	    connectionProps.put("user", dbUser);
	    connectionProps.put("password", dbPassword);
	    try {
	    	switch(dbType){
	    		case "PostgreSQL": driver = "org.postgresql.Driver"; configConn = "jdbc:postgresql://"+dbHost+(dbPort.equals("") ? "" : ":"+dbPort)+"/"+dbName; break;
	    		case "mySQL"	 : driver = "com.mysql.jdbc.Driver"; configConn = "jdbc:mysql://"+dbHost+(dbPort.equals("") ? "" : ":"+dbPort)+"/"+dbName; break;
	    		case "SQLServer" : driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; configConn = "jdbc:sqlserver://"+dbHost+(dbPort.equals("") ? "" : ":"+dbPort)+";databaseName="+dbName; break;
	    		case "Oracle"	 : driver = "oracle.jdbc.driver.OracleDriver"; configConn = "jdbc:oracle:thin:@"+dbHost+(dbPort.equals("") ? "" : ":"+dbPort)+":"+dbName; break;
	    	}
	    	Class.forName(driver);
			conn = DriverManager.getConnection(configConn, connectionProps);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public String saveCSVData(){
		String message = "";
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		currPage = "dbdata";
		switch(mappingTable){
			case "USER" 	: 
				Iterator<UmUser> itr = listUsers.iterator();
				while(itr.hasNext()){
					UmUser u = itr.next();
					if(u.getIsCorrect().equals("cross.jpg")) itr.remove();
				}
				userDao.importUsers(listUsers,true); listUsers.clear(); listUsers = userDao.getImportUsers(stamp); alerts.createReminder("User Import Successful!", session.getUserId()); message = (listUsers.size() > 0) ? "Successfully Import "+correctRows+" Users" : "No User to import";
				break;
			case "ACCOUNT" 	: 
				Iterator<CsAccount> itra = listAccounts.iterator();
				while(itra.hasNext()){
					CsAccount a = itra.next();
					if(a.getIsCorrect().equals("cross.jpg")) itra.remove();
				}
				accountDao.importAccounts(listAccounts, true); listAccounts.clear(); listAccounts = accountDao.getImportAccounts(stamp); alerts.createReminder("Account Import Successful!", session.getUserId()); message = (listAccounts.size() > 0) ? "Successfully Import "+correctRows+" Accounts" : "No Account to import";
				break;
			case "CONTACT" 	: 
				Iterator<CsContactORM> itrc = listContacts.iterator();
				while(itrc.hasNext()){
					CsContactORM c = itrc.next();
					if(c.getIsCorrect().equals("cross.jpg")) itrc.remove();
				}
				contactDao.importContacts(listContacts, session.getCompanyId()); listContacts.clear(); listContacts = contactDao.getImportContacts(stamp); alerts.createReminder("Contact Import Successful!", session.getUserId()); message = (listContacts.size() > 0) ? "Successfully Import "+correctRows+" Contacts" : "No Contact to import";
				break;
			case "PAYMENT" 	: 
				Iterator<PaymentORM> itrp = listPayments.iterator();
				while(itrp.hasNext()){
					PaymentORM p = itrp.next();
					if(p.getIsCorrect().equals("cross.jpg")) itrp.remove();
				}
				paymentDao.importPayments(listPayments, session.getCompanyId()); listPayments.clear(); listPayments = paymentDao.getImportPayments(stamp); alerts.createReminder("Payment Import Successfull", session.getUserId()); message = (listPayments.size() > 0) ? "Successfully Import "+correctRows+" Payment Records" : "No Payment Record to import";
				break;
			case "PRODUCT" 	: 
				Iterator<ProductORM> itrpr = listProducts.iterator();
				while(itrpr.hasNext()){
					ProductORM p = itrpr.next();
					if(p.getIsCorrect().equals("cross.jpg")) itrpr.remove();
				}
				productDao.importProducts(listProducts, session.getCompanyId()); listProducts.clear(); listProducts = productDao.getImportProducts(stamp); alerts.createReminder("Product Import Successfull", session.getUserId()); message = (listProducts.size() > 0) ? "Successfully Import "+correctRows+" Products" : "No Product to import";
				break;
			case "PACKAGE" 	: 
				Iterator<SPackageORM> itrpa = listPackages.iterator();
				while(itrpa.hasNext()){
					SPackageORM p = itrpa.next();
					if(p.getIsCorrect().equals("cross.jpg")) itrpa.remove();
				}
				packageDao.importPackages(listPackages, session.getCompanyId()); listPackages.clear(); listPackages = packageDao.getImportPackages(stamp); alerts.createReminder("Package Import Successfull", session.getUserId()); message = (listPackages.size() > 0) ? "Successfully Import "+correctRows+" Packages" : "No Package to import";
				break;
			case "ORDER" 	: 
				Iterator<OrderORM> itro = listOrders.iterator();
				while(itro.hasNext()){
					OrderORM p = itro.next();
					if(p.getIsCorrect().equals("cross.jpg")) itro.remove();
				}
				orderDao.importOrders(listOrders, session.getCompanyId()); listOrders.clear(); listOrders = orderDao.getImportOrders(stamp); alerts.createReminder("Order Import Successfull", session.getUserId()); message = (listOrders.size() > 0) ? "Successfully Import "+correctRows+" Orders" : "No Order to import";
				break;
			case "COUNTRY" 	: 
				Iterator<Country> itrctry = listCountries.iterator();
				while(itrctry.hasNext()){
					Country ctry = itrctry.next();
					if(ctry.getIsCorrect().equals("cross.jpg")) itrctry.remove();
				}
				utilsDao.importCountries(listCountries); listCountries.clear(); listCountries = utilsDao.getImportCountries(stamp); alerts.createReminder("Countries Import Successfull", session.getUserId()); message = (listCountries.size() > 0) ? "Successfully Import "+correctRows+" Countries" : "No Country to import";
				break;
			case "STATE" 	: 
				Iterator<State> itrsts = listStates.iterator();
				while(itrsts.hasNext()){
					State sts = itrsts.next();
					if(sts.getIsCorrect().equals("cross.jpg")) itrsts.remove();
				}
				utilsDao.importStates(listStates); listStates.clear(); listStates = utilsDao.getImportStates(stamp); alerts.createReminder("States Import Successfull", session.getUserId()); message = (listStates.size() > 0) ? "Successfully Import "+correctRows+" States" : "No State to import";
				break;
			case "CITY" 	: 
				Iterator<City> itrcty = listCities.iterator();
				while(itrcty.hasNext()){
					City cty = itrcty.next();
					if(cty.getIsCorrect().equals("cross.jpg")) itrcty.remove();
				}
				utilsDao.importCities(listCities); listCities.clear(); listCities = utilsDao.getImportCities(stamp); alerts.createReminder("Cities Import Successfull", session.getUserId()); message = (listCities.size() > 0) ? "Successfully Import "+correctRows+" Cities" : "No City to import";
				break;	
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,message,message));
		return "mapdata";
	}
	public void dbConnect(){		
    	Connection conn = null;
		conn = dbConnection();
		try{
			  DatabaseMetaData dbm = conn.getMetaData();
			  String[] types = {"TABLE"};
			  ResultSet rs = dbm.getTables(null,null,"%",types);
			  while (rs.next()){
				  String table = rs.getString("TABLE_NAME");
				  dbTables.add(table);
				  conn.close();
			  }
		} catch (SQLException s){
			  System.out.println("No any table in the database");
		}
		dualListTables = new DualListModel<String>(dbTables,selectedColumns);
	}
	
	public void testConnection(){
		connMessage = (dbConnection() != null) ? "Connection Succeed" : "Connection Failed";
	}
	
	public List<String> tableCols(String tab){
		Connection conn = null;
		List<String> tabCols = new ArrayList<String>();
		conn = dbConnection();
		try{
			  Statement st = conn.createStatement();
			  ResultSet rs = st.executeQuery("SELECT * FROM "+tab);
			  ResultSetMetaData md = rs.getMetaData();
			  int col = md.getColumnCount();
			  for (int i = 1; i <= col; i++){
				  String col_name = md.getColumnName(i);
				  tabCols.add(col_name);
			  }
		} catch (SQLException s){
			  System.out.println("SQL statement is not executed!");
		}
		return tabCols; 
	}
	
	public void pickList(){
		if(selDBTables.size() == 0)
			return;
		for(String x : selDBTables){
			selectedColumns.add(x);
			dbTables.remove(x);
		}
	}
	
	public void emptyPickList(){
		if(selectedColumns.size() == 0)
			return;
		selectedColumns.clear();
		//csvHeader.clear();
	}
	
	public boolean tableExists(String str){
		for(String y : selectedTables){
			if(y.equals(str))
				return true;
		}
		return false;
	}
	
	public boolean selectedColumnsSize(){
		return dualListTables.getTarget().size() > 1;
	}
	
	public void mapTables(){
		if(dualListTables.getTarget().size() == 0)
			return;
		if(dualListTables.getTarget().size() == 1){
			selectedColumns = dualListTables.getTarget();
			selTable = selectedColumns.get(0);
			tableColumns = tableCols(selTable);
			for(String z : tableColumns){
				csvHeader.add(z);
			}
		}else{
			selectedColumns = dualListTables.getTarget();
			leftTable = selectedColumns.get(0);
			rightTable = selectedColumns.get(0);
			populateLeftCols();
			populateRightCols();
		}
	}
	
	public void populateLeftCols(){
		if(leftTable.isEmpty()){
			leftColumnList.clear();
			return;
		}
		leftColumnList = tableCols(leftTable);
	}
	
	public void populateRightCols(){
		if(rightTable.isEmpty()){
			rightColumnList.clear();
			return;
		}
		rightColumnList = tableCols(rightTable);
	}
	
	public void popPickList(){
		if(currSelectedTables.size() == 0)
			return;
		for(String x : currSelectedTables){
			if(!tableExists(x))
				selectedTables.add(x);
		}
	}
	
	public void emptyPopPickList(){
		if(selectedTables.size() == 0)
			return;
		selectedTables.clear();
	}
	
	public void mapColumns(){
		if(leftTable.isEmpty() || rightTable.isEmpty() || leftColumn.isEmpty() || rightColumn.isEmpty())
			return;
		String sqljoin = leftTable+"."+leftColumn+" = "+rightTable+"."+rightColumn;
		if(!joinClauseList.contains(sqljoin))
			joinClauseList.add(sqljoin);
	}
	
	public void removeJoin(){
		for(String x : joinClause){
			joinClauseList.remove(x);
		}	
	}
	
	public void saveJoins(){
		csvHeader.clear();
		for(String x : selectedColumns){
			for(String z : tableCols(x)){
				csvHeader.add(x+"->"+z);
			}
		}
	}
	
	public void mapData(){
		String[] mapStr = utilsDao.savedMap(mapId).split(",");
		int i = 0;
		for(String mp : mapStr){
			selectedCSVHeader[i] = Integer.parseInt(mp);
			i++;
		}	
		colMap = utilsDao.mapTitle(mapId);
	}
	
	public void deleteMap(){
		if(mapId.equals(""))
			return;
		utilsDao.deleteMap(mapId);
	}
	
	public void clearHeader(){
		csvHeader.clear();
		useData = false;
	}
	
	public void setAlerts(AlertsAndRemindersAdmin alerts) {
		this.alerts = alerts;
	}
	
	
	
		

	

	public List<String> getTableColumnsBox() {
		return tableColumnsBox;
	}

	public void setTableColumnsBox(List<String> tableColumnsBox) {
		this.tableColumnsBox = tableColumnsBox;
	}

	public List<String> getSelectedColumnsBox() {
		return selectedColumnsBox;
	}

	public void setSelectedColumnsBox(List<String> selectedColumnsBox) {
		this.selectedColumnsBox = selectedColumnsBox;
	}

	public List<String> getTableColumns() {
		return tableColumns;
	}

	public void setTableColumns(List<String> tableColumns) {
		this.tableColumns = tableColumns;
	}

	public List<String> getSelectedColumns() {
		return selectedColumns;
	}

	public void setSelectedColumns(List<String> selectedColumns) {
		this.selectedColumns = selectedColumns;
	}	
	
	public void selectedMappings(){
		for(String z : selectedColumns){
			csvHeader.add(z);
		}
		afterVisible = false;
	}
	
	public boolean checkEmail(String mail){
		String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		if(!mail.isEmpty()){
			if(mail.matches(regex))
				return true;
			else
				return false;
		}
		return true;
	}	
	
	public void deleteRow(ActionEvent event) {
		String uid = (String) event.getComponent().getAttributes().get("del_id");
		System.out.println(uid);
		switch(this.mappingTable){
			case "USER" 	: 
				for(UmUser u : listUsers){
					if(u.getUserName().equals(uid)){
						listUsers.remove(u);
					}
				}
				break;
			case "ACCOUNT" 	:
				for(CsAccount a : listAccounts){
					if(a.getAccountTitle().equals(uid)){
						listAccounts.remove(a);
					}
				}
				break;
			case "CONTACT" 	: 
				for(CsContactORM c : listContacts){
					if(c.getContactCnic().equals(uid)){
						listContacts.remove(c);
					}
				}
				break;
		}		
	}

	@Override
	public String actionListener() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> getList() {
		// TODO Auto-generated method stub
		return null;
	}	
}