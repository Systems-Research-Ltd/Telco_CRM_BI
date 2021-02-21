package com.srpl.bi.web.model.reportsbuilder;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.CloseEvent;
import org.primefaces.model.DualListModel;

import com.bitguiders.util.KeyValueItem;
import com.bitguiders.util.jsf.JSFBeanSupport;
import com.srpl.bi.service.DBService;
import com.srpl.bi.service.DataService;
import com.srpl.bi.web.common.SessionDataBean;
import com.srpl.bi.web.controller.BeanFactory;

@ManagedBean(name="dbConnBean")
@ViewScoped
public class DatabaseConnectionBackingBean extends JSFBeanSupport implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer connectionId = 0;
	private String connection = null;
	private Integer dataSource;
	private String driver = null;
	private String database = null;
	private String host = null;
	private Long port;
	private String username = null;
	private String password = null;
	private String schema = null;
	private String url = null;
	private String dbManager = null;
	private Boolean savepass;
	private String connStatus = null;
	private String currAction = "create";
	
	@ManagedProperty(value="#{dataSourceBean}")
	private DataSourceBackingBean dsBean;
	
	@PostConstruct
	public void init(){
		dataSource = Integer.parseInt(availableDataSources().get(0).getKey());
		driver = availableDrivers().get(0).getKey();
		url = availableDrivers().get(0).getValue();
		database = "database";
		switch(dataSource){
			case 1: host = "localhost"; port = (long)5432; break;
			case 2: host = "localhost"; port = (long)3306; break;
			case 3: host = "localhost"; port = (long)1521; break;
			case 5: host = "localhost"; port = (long)1433; break;
			default: host = ""; port = null;
		}
	}
	
	public DatabaseConnectionBackingBean(){
					
	}
	
	public List<KeyValueItem> availableDataSources(){
		List<KeyValueItem> sources = DataService.getInstance().getDatabaseTypeList();
		return sources;
	}
	
	public List<KeyValueItem> availableDrivers(){
		List<KeyValueItem> drivers = DataService.getInstance().getDriverListByDatabaseTypeId(dataSource);
		return drivers;
	}
	
	public void testConnection(){
		Boolean sts = DBService.getInstance().testConnection(url,driver,username,password);
		connStatus = sts ? "Connection Successful!" : "Connection Failed!";
	}
	
	public void updateDriver(){
		driver = availableDrivers().get(0).getKey();
		url = availableDrivers().get(0).getValue();
		database = "database";		
		switch(dataSource){
			case 1: host = "localhost"; port = (long)5432; break;
			case 2: host = "localhost"; port = (long)3306; break;
			case 3: host = "localhost"; port = (long)1521; break;
			case 5: host = "localhost"; port = (long)1433; break;
			default: host = ""; port = null;
		}
	}
	
	public void updateURL(){
		url = availableDrivers().get(0).getValue();
		if(showLabel()) {
			int start = (dataSource != 3) ? url.indexOf("//")+2 : url.indexOf('@')+1;
			int end = (dataSource == 3) ? url.lastIndexOf(':') : ((dataSource == 5) ? url.lastIndexOf(';') : url.lastIndexOf('/'));
			String prt = (port != 0) ? ":"+port : "";
			setUrl(url.replace(url.substring(start, end), host+prt).replace("database", database));
		}else
			setUrl(url.replace("database", database));
	}
	
	public void createConnection(){
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		System.out.println("User Id is "+session.getUserId()+"------"+session.getCompanyId());
		DBService.getInstance().createConnection(connection,database,host,port,username,password,schema,dataSource, session.getUserId(), session.getCompanyId());
	}
	
	public void updateConnection(){
		DBService.getInstance().updateConnection(connectionId,connection,database,host,port,username,password,schema,dataSource);
	}

	public void deleteConnection(Integer connId){
		System.out.println("CONNID "+connId);
		DBService.getInstance().deleteConnection(connId);
		dsBean.setSelectConnection(0);
		dsBean.setConTableSource(new ArrayList<String>());
		dsBean.setConTableTarget(new ArrayList<String>());
	}	

	public Integer getConnectionId() {
		return connectionId;
	}

	public void setConnectionId(Integer connectionId) {
		this.connectionId = connectionId;
	}
	
	public void connectionData(Integer connId){
		connectionId = connId;
		ResultSet rs = DBService.getInstance().getConnection(connectionId);
		try {
			rs.next();
			connection = rs.getString("connection_name");
			database = rs.getString("database");
			host = rs.getString("host");
			port = rs.getLong("port");
			username = rs.getString("user");
			password = rs.getString("password");
			schema = rs.getString("schema");
			dataSource = rs.getInt("bi_database_type_id");
			currAction = "edit";
			updateURL();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	
	public DataSourceBackingBean getDsBean() {
		return dsBean;
	}

	public void setDsBean(DataSourceBackingBean dsBean) {
		this.dsBean = dsBean;
	}

	public boolean showLabel(){
		List<Integer> dss = new ArrayList<>(Arrays.asList(1,2,3,5));
		return dss.contains(dataSource);
	}
	
	public boolean showSchema(){
		List<Integer> dss = new ArrayList<>(Arrays.asList(1,3,5));
		return dss.contains(dataSource);
	}
	
	public void handleClose(CloseEvent event) {
		FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove("dbConnBean");  
    }	

	public String getCurrAction() {
		return currAction;
	}

	public void setCurrAction(String currAction) {
		this.currAction = currAction;
	}

	public String getConnStatus() {
		return connStatus;
	}

	public void setConnStatus(String connStatus) {
		this.connStatus = connStatus;
	}

	public Integer getDataSource() {
		return dataSource;
	}

	public void setDataSource(Integer dataSource) {
		this.dataSource = dataSource;
	}

	public String getConnection() {
		return connection;
	}

	public void setConnection(String connection) {
		this.connection = connection;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Long getPort() {
		return port;
	}

	public void setPort(Long port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDbManager() {
		return dbManager;
	}

	public void setDbManager(String dbManager) {
		this.dbManager = dbManager;
	}

	public Boolean getSavepass() {
		return savepass;
	}

	public void setSavepass(Boolean savepass) {
		this.savepass = savepass;
	}	
}
