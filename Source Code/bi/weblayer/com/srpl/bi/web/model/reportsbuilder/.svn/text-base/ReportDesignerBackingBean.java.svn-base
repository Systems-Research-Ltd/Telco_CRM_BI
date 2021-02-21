package com.srpl.bi.web.model.reportsbuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

import com.bitguiders.util.KeyValueItem;
import com.bitguiders.util.jsf.JSFBeanSupport;
import com.bitguiders.util.jsf.WebConstants;
import com.srpl.bi.service.ReportService;
import com.srpl.bi.web.common.SessionDataBean;
import com.srpl.bi.web.controller.BeanFactory;
import com.srpl.bi.web.model.reportsbuilder.DataPaletteBackingBean.ColumnModel;

/**
 * @author sysres-2
 * 
 */
@ManagedBean(name = "reportDesignerBean")
@ViewScoped
public class ReportDesignerBackingBean extends JSFBeanSupport implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;
	private String description;
	private int summaryLabelId;
	private String summaryLabel;
	private String summaryValue;
	private int summaryCounter;
	private String newSummaryLabel;
	private String newSummaryValue;
	private boolean isSummaryEditMode;
	private String exportType;
	private UploadedFile logo;
	private String logoName;
	List<ReportDesignerBackingBean> summary;
	private boolean serialNo;
	private int limit;
	private int selectedReport;
	private boolean showLogo = false;
	private int tempReportId;
	private boolean reportSaved;
	// Selected Graph
	String selectedGraph = "5";
	// Graph Object
	ReportGraph graph = new ReportGraph();
	DataPaletteBackingBean dataPaletteBean;

	public ReportDesignerBackingBean() {
		this.title = "Report Title";
		this.description = "Report Description";
		this.summaryLabelId = 0;
		this.summaryLabel = "";
		this.summaryValue = "";
		this.summaryCounter = 1;
		this.newSummaryLabel = "Label";
		this.newSummaryValue = "Value";
		this.isSummaryEditMode = true;
		this.exportType = "pdf";
		this.logoName = "";
		this.summary = new ArrayList<ReportDesignerBackingBean>();
		this.serialNo = false;
		this.limit = 5;
		this.selectedReport = 0;
		this.selectedGraph = "5";
		this.showLogo = false;
		this.tempReportId = 0;
		this.reportSaved = false;
		String action = getAction();
		if (action.equals("")) {
			setCurrentAction(WebConstants.ACTION_CREATE, this.getClass());
		}
	}

	@PostConstruct
	public void init() {
		ReportDesignerBackingBean temp = new ReportDesignerBackingBean();
		int temp_id = 0;
		String temp_label = null;
		String temp_value = null;

		try {
			temp_id = Integer.valueOf(this.getParameter("temp_id"));
			temp_label = this.getParameter("temp_label");
			temp_value = this.getParameter("temp_value");

			temp.setSummaryLabelId(temp_id);
			temp.setSummaryLabel(temp_label);
			temp.setSummaryValue(temp_value);

			summary.add(temp);

		} catch (Exception e) {
			System.out.println("exception while fetching values.");
		}

		dataPaletteBean = BeanFactory.getInstance().getDataPaletteBean();
	}

	public void resetBean() {
		this.title = "Report Title";
		this.description = "Report Description";
		this.summaryLabelId = 0;
		this.summaryLabel = "";
		this.summaryValue = "";
		this.summaryCounter = 1;
		this.newSummaryLabel = "Label";
		this.newSummaryValue = "Value";
		this.isSummaryEditMode = true;
		this.exportType = "pdf";
		this.logoName = "";
		this.summary = new ArrayList<ReportDesignerBackingBean>();
		this.serialNo = false;
		this.limit = 5;
		this.selectedReport = 0;
		this.selectedGraph = "5";
		this.showLogo = false;
		this.tempReportId = 0;
		this.reportSaved = false;
		DataPaletteBackingBean dpbb = BeanFactory.getInstance()
				.getDataPaletteBean();
		dpbb.resetColumns();
		dpbb.setSql("");
		if (tempReportId == 0) {
			dpbb.populateDataPalette();
			graph.clear();
		}
	}

	// creates Chart based on rows and dndcolumns datastructures
	public void populateGraph() {
		if (dataPaletteBean == null)
			dataPaletteBean = BeanFactory.getInstance().getDataPaletteBean();

		graph.clear();

		seperateColumns();
		calcuateMinMax();
		createXLabels();

		graph.chart = new CartesianChartModel();
		// String[] years = {"2008","2009", "2010", "2011", "2012"};
		if (graph.measures.size() == 0 || graph.dimensions.size() == 0) {
			graph.chartvalid = false;
			return;
		}
		for (Integer measure : graph.getMeasures()) {
			int count = 0;
			ColumnModel measureObj = dataPaletteBean.getDndColumns().get(
					measure);
			ChartSeries cs = new ChartSeries();

			for (Map<String, String> row : dataPaletteBean.getRows()) {
				cs.set(graph.getxLabels().get(count),
						Integer.parseInt(row.get(measureObj.getProperty())));
				count++;
			}
			graph.getChart().addSeries(cs);
		}
		graph.chartvalid = true;
		System.out.println("Graph Populated");
	}

	public void seperateColumns() {
		int i = 0;
		for (ColumnModel column : dataPaletteBean.getDndColumns()) {
			String colType = column.getPropertyType();
			// if Column is NUMERIC then put it in measures list else add it in
			// dimensions List
			if (checkColType(colType) == true) {
				graph.measures.add(i);
			} else {
				graph.dimensions.add(i);
			}
			i++;
		}
	}

	// Calculate Min & Max Value for Graphs Y axis
	public void calcuateMinMax() {
		graph.maxY = 0;
		graph.minY = 0;
		boolean firstTime = true;
		for (Integer column : graph.measures) {
			ColumnModel col = dataPaletteBean.getDndColumns().get(column);
			for (Map<String, String> rdata : dataPaletteBean.getRows()) {
				int colVal = Integer.parseInt(rdata.get(col.getProperty()));
				if (firstTime) {
					graph.minY = colVal;
					firstTime = false;
				}
				graph.maxY = Math.max(graph.maxY, colVal);
				graph.minY = Math.min(graph.minY, colVal);
			}
		}
	}

	private void createXLabels() {
		for (Map<String, String> row : dataPaletteBean.getRows()) {
			StringBuilder label = new StringBuilder();
			for (Integer dim : graph.getDimensions()) {
				ColumnModel dimension = dataPaletteBean.getDndColumns().get(
						dim.intValue());
				// String colproperty=dimension.getProperty();
				if (!label.toString().isEmpty())
					label.append("/");
				String rowValue = row.get(dimension.getProperty());
				label.append(rowValue);
			}
			graph.getxLabels().add(label.toString());
		}
	}

	private void createCategoryModel() {

		/*
		 * categoryModel = new CartesianChartModel(); String[] years =
		 * {"2008","2009", "2010", "2011", "2012"}; for (ColumnModel column
		 * :dndColumns) { int count = 0; ChartSeries cs = new ChartSeries();
		 * cs.setLabel(column.property); for (Map<String, String> row : rows) {
		 * cs.set(years[count], Integer.parseInt(row.get(column.property)));
		 * count++; } categoryModel.addSeries(cs); }
		 */
	}

	// return zero if string or 1 if numeric
	public boolean checkColType(String type) {
		switch (type.toLowerCase().trim()) {
		case "integer":
			return true;
		case "decimal":
			return true;
		case "bigint":
			return true;
		case "serial":
			return true;
		case "bigserial":
			return true;
		case "varchar":
			return false;
		case "text":
			return false;
		case "boolean":
			return true;
		case "timestamp":
			return false;
		case "date":
			return false;
		case "time":
			return false;
		}
		return true;
	}

	/**
	 * Getters and setters
	 */

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSummaryLabelId() {
		return summaryLabelId;
	}

	public void setSummaryLabelId(int summaryLabelId) {
		this.summaryLabelId = summaryLabelId;
	}

	public String getSummaryLabel() {
		return summaryLabel;
	}

	public void setSummaryLabel(String summaryLabel) {
		this.summaryLabel = summaryLabel;
	}

	public String getSummaryValue() {
		return summaryValue;
	}

	public void setSummaryValue(String summaryValue) {
		this.summaryValue = summaryValue;
	}

	public int getSummaryCounter() {
		return summaryCounter;
	}

	public void setSummaryCounter(int summaryCounter) {
		this.summaryCounter = summaryCounter;
	}

	public String getNewSummaryLabel() {
		return newSummaryLabel;
	}

	public void setNewSummaryLabel(String newSummaryLabel) {
		this.newSummaryLabel = newSummaryLabel;
	}

	public String getNewSummaryValue() {
		return newSummaryValue;
	}

	public void setNewSummaryValue(String newSummaryValue) {
		this.newSummaryValue = newSummaryValue;
	}

	public boolean isSummaryEditMode() {
		return isSummaryEditMode;
	}

	public void setSummaryEditMode(boolean isSummaryEditMode) {
		this.isSummaryEditMode = isSummaryEditMode;
	}

	public String getExportType() {
		return exportType;
	}

	public void setExportType(String exportType) {
		this.exportType = exportType;
	}

	public ReportGraph getGraph() {
		return graph;
	}

	public void setGraph(ReportGraph graph) {
		this.graph = graph;
	}

	public List<ReportDesignerBackingBean> getSummary() {
		return summary;
	}

	public void setSummary(List<ReportDesignerBackingBean> summary) {
		this.summary = summary;
	}

	public boolean isSerialNo() {
		return serialNo;
	}

	public void setSerialNo(boolean serialNo) {
		this.serialNo = serialNo;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getSelectedReport() {
		return selectedReport;
	}

	public UploadedFile getLogo() {
		return logo;
	}

	public void setLogo(UploadedFile logo) {
		this.logo = logo;
	}

	public String getLogoName() {
		return logoName;
	}

	public void setLogoName(String logoName) {
		this.logoName = logoName;
	}

	public void setSelectedReport(int selectedReport) {
		this.selectedReport = selectedReport;
	}

	public boolean isShowLogo() {
		return showLogo;
	}

	public void setShowLogo(boolean showLogo) {
		this.showLogo = showLogo;
	}

	public String getSelectedGraph() {
		return selectedGraph;
	}

	public void setSelectedGraph(String selectedGraph) {
		this.selectedGraph = selectedGraph;
	}

	public int getTempReportId() {
		return tempReportId;
	}

	public void setTempReportId(int tempReportId) {
		this.tempReportId = tempReportId;
	}

	public boolean isReportSaved() {
		return reportSaved;
	}

	public void setReportSaved(boolean reportSaved) {
		this.reportSaved = reportSaved;
	}

	/**
	 * Actions
	 */

	public void addSummary() {
		ReportDesignerBackingBean e = new ReportDesignerBackingBean();
		e.setSummaryLabelId(summaryCounter++);
		e.setSummaryLabel(getNewSummaryLabel());
		e.setSummaryValue(getNewSummaryValue());
		summary.add(e);
		setNewSummaryLabel("Label");
		setNewSummaryValue("Value");
	}

	public void editSummary() {
		int id = Integer.parseInt(getParameter("temp_id"));
		for (ReportDesignerBackingBean r : summary) {
			if (r.getSummaryLabelId() == id) {
				r.setSummaryLabel(r.getSummaryLabel());
				r.setSummaryValue(r.getSummaryValue());
				setSummaryEditMode(true);
				break;
			}
		}

	}

	public List<ReportDesignerBackingBean> getReportSummary() {
		return summary;
	}

	public void cancelSummary() {
		setSummaryEditMode(true);
	}

	public void resetSummaryMode() {
		setSummaryEditMode(false);
	}

	public int getSummarySize() {
		return summary.size();
	}

	public String getSummaryAsString() {
		String summary = "";
		if (this.summary.size() > 0) {
			for (ReportDesignerBackingBean r : this.summary) {
				summary += r.getSummaryLabel() + ", " + r.getSummaryValue()
						+ ";";
			}
			summary = summary.substring(0, (summary.length() - 1));
		}
		return summary;
	}

	public void getSummaryAsList(String summary) {
		if (!summary.equals("")) {
			String temp[] = summary.split(";");
			for (String s : temp) {
				String temp1[] = s.split(",");
				ReportDesignerBackingBean r = new ReportDesignerBackingBean();
				r.setSummaryLabelId(summaryCounter++);
				r.setSummaryLabel(temp1[0]);
				r.setSummaryValue(temp1[1]);
				this.summary.add(r);
			}

		}
	}

	public List<ReportDesignerBackingBean> deleteSummary() {
		int id = Integer.parseInt(getParameter("temp_id"));
		for (ReportDesignerBackingBean r : summary) {
			if (r.getSummaryLabelId() == id) {
				summary.remove(r);
				setSummaryEditMode(true);
				break;
			}
		}
		return summary;
	}

	public void generateReport() {
		DataPaletteBackingBean dp = BeanFactory.getInstance()
				.getDataPaletteBean();
		// System.out.println("Sql is " + dp.getSql());

	}

	public void exportReport() throws Exception {
		FacesContext ctx = FacesContext.getCurrentInstance();
		ExternalContext ectx = ctx.getExternalContext();
		HttpServletResponse response = (HttpServletResponse) ectx.getResponse();
		String exportType = getExportType();
		String contentType = "";
		DataPaletteBackingBean dp = BeanFactory.getInstance()
				.getDataPaletteBean();
		int ds = BeanFactory.getInstance().getDataSourceBean()
				.getSavedDataSource();
		String logo = "";
		ArrayList<String[]> colsList = new ArrayList<String[]>();
		
		ServletContext ctx1 = (ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext();

		String serverPath = ctx1.getRealPath("");
		serverPath = serverPath.substring(0, serverPath.indexOf("standalone"));

		String pathSeparator = File.separator;
		if (pathSeparator.equals("\\")) {
			pathSeparator = "\\\\";
		}
		serverPath += "standalone" + pathSeparator + "deployments";

		
		switch (exportType) {
		case "pdf":
			contentType = "application/pdf";
			break;
		case "html":
			contentType = "text/html";
			break;
		case "rtf":
			contentType = "application/rtf";
			break;
		case "xls":
			contentType = "vnd.ms-excel"; // application/msexcel
			break;
		case "doc":
			contentType = "application/msword"; // application/msexcel
			break;
		default:
			contentType = "application/pdf";
		}
		
		if (!getLogoName().equals("")) {
			String logoDir = ctx1.getInitParameter("reportsLogoPath");

			logo = serverPath + logoDir + getLogoName();
		}
		
		String path = serverPath + pathSeparator+"reports";
		File mainDir = new File(path);
		if (!mainDir.exists()) {
			try {
				mainDir.mkdir();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		path = path + pathSeparator+"bi";
		File app = new File(path);
		if (!app.exists()) {
			try {
				app.mkdir();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		path = path + pathSeparator;
		
		String sum = getSummaryAsString();
		
		for (int i = 0; i < dp.getDndColumns().size(); i++) {
			if(!dp.getDndColumns().get(i).getProperty().equals("serialNo")) {
				String column = dp.getDndColumns().get(i).getTableAlias() + "."
						+ dp.getDndColumns().get(i).getProperty();
				System.out.println("Column is "+column);
				colsList.add(new String[] { dp.getDndColumns().get(i).getHeader(),
						column, dp.getDndColumns().get(i).getPropertyType(), "30" });
			}	
		}
		
		DynamicJasperReport djr = new DynamicJasperReport(getTitle(),
				getDescription(), sum, dp.getSql(), colsList, exportType, path,
				logo, 0, 0, isShowLogo(), isSerialNo(), getSelectedReport(), ds);
		djr.setSelectedGraph(Integer.parseInt(getSelectedGraph()));
		// try {
		String name = djr.generateReport();

		File file = new File(path + name);
		byte[] fileData = new byte[(int) file.length()];
		FileInputStream fis = new FileInputStream(file);
		try {
			fis.read(fileData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.reset();
		response.setContentType(contentType);
		response.setContentLength(fileData.length);
		response.setHeader("Content-Disposition", "attachment;filename="+getTitle().replace(" ", "_")+"."
				+ getExportType());

		ServletOutputStream outputStream = response.getOutputStream();

		try {
			outputStream.write(fileData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			outputStream.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			outputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ctx.responseComplete();
		// } catch (Exception e) {
		// e.printStackTrace();
		// addError("Could not export report");

		// }
	}

	public void uploadLogo(FileUploadEvent event) {
		logo = event.getFile();
	}

	public void copyLogo() {

		InputStream in = null;
		try {
			in = logo.getInputstream();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance()
				.getExternalContext().getContext();
		String logoPath = ctx.getInitParameter("reportsLogoPath");
		String temp[] = logoPath.split("/");
		String path = ctx.getRealPath("");
		path = path.substring(0, path.indexOf("standalone"));

		String pathSeparator = File.separator;
		if (pathSeparator.equals("\\")) {
			pathSeparator = "\\\\";
		}
		path += "standalone" + pathSeparator + "deployments";

		path = path + pathSeparator + temp[1];

		File images = new File(path);
		if (!images.exists()) {
			try {
				images.mkdir();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		path += pathSeparator + temp[2];

		File logos = new File(path);
		if (!logos.exists()) {
			try {
				logos.mkdir();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		path = path + pathSeparator;
		try {
			System.out.println("Path of the logo is " + path + " logo name "
					+ getLogoName());
			// write the inputStream to a FileOutputStream
			OutputStream out = new FileOutputStream(new File(path
					+ getLogoName()));

			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = in.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}

			in.close();
			out.flush();
			out.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	public String getExtension(String name) {
		String ext = null;
		String s = name;
		int i = s.lastIndexOf('.');

		if (i > 0 && i < s.length() - 1) {
			ext = s.substring(i + 1).toLowerCase();
		}
		return ext;
	}

	public void manipulateSerialNo() {
		DataPaletteBackingBean dp = BeanFactory.getInstance()
				.getDataPaletteBean();
		if (serialNo) {
			dp.getDndColumns().clear();
			if (dp.getDndColumns().size() > 0) {
				dp.getDndColumns().add(0,
						new ColumnModel("Serial No.", "serialNo", "", "", ""));
			} else {
				dp.getDndColumns().add(
						new ColumnModel("Serial No.", "serialNo", "", "", ""));
			}
			dp.populateDataTable();
		} else {
			if (dp.getDndColumns().size() > 0) {
				ColumnModel c = dp.getDndColumns().get(0);
				if (c.getProperty().equals("serialNo")) {
					dp.getDndColumns().remove(0);
				}
			}
		}

	}

	public String actionListener() {
		System.out.println(">>>>>>>>>>>>>>>>>>>Action in action listener "+getAction());
		setCurrentAction(getAction(), this.getClass());
		// BeanFactory.getInstance().getDataSourceBean().setSavedDataSource(selectedReport);
		// BeanFactory.getInstance().getDataPaletteBean().changeDataSource();
		switch (getCurrentAction()) {
		case WebConstants.ACTION_CREATE:
			createReport();
			return null;
		case WebConstants.ACTION_SAVE:
			saveReport();
			return WebConstants.ACTION_SAVE;
		case WebConstants.ACTION_EDIT:
			editReport();
			return WebConstants.ACTION_EDIT;
		case WebConstants.ACTION_UPDATE:
			updateReport();
			return WebConstants.ACTION_UPDATE;
		case WebConstants.ACTION_DELETE:
			return WebConstants.ACTION_DELETE;
		case WebConstants.ACTION_DELETE_CONFIRMED:
			deleteReport();
			return WebConstants.ACTION_DELETE_CONFIRMED;
		case WebConstants.ACTION_CANCEL:
			createReport();
			return null;
		}
		return null;
	}

	private String getColumnsAsString() {
		String columns = "";
		DataPaletteBackingBean dpbb = BeanFactory.getInstance()
				.getDataPaletteBean();
		for (ColumnModel c : dpbb.getDndColumns()) {
			columns += c.getTable() + "." + c.getProperty() + ",";
		}
		if (!columns.equals("")) {
			columns = columns.substring(0, columns.length() - 1);
		}
		return columns;
	}

	public void createReport() {
		setCurrentAction(WebConstants.ACTION_CREATE, this.getClass());
		resetBean();
	}

	public int saveReport() {
		ReportService rs = ReportService.getInstance();
		DataSourceBackingBean dsbb = BeanFactory.getInstance()
				.getDataSourceBean();
		String summary = getSummaryAsString();
		String columns = getColumnsAsString();
		String logoExt = "";
		int reportId = 0;
		if (logo != null && showLogo) {
			logoExt = getExtension(logo.getFileName());
		}
		try {
			SessionDataBean session = BeanFactory.getInstance().getSessionBean();
			reportId = rs.saveReport(getTitle() + "_UNSAVED", getDescription(),
					dsbb.getSavedDataSource(), summary, getSelectedGraph(),
					columns, logoExt, showLogo, serialNo, session.getUserId(), session.getCompanyId());
			if (reportId != 0) {
				setTempReportId(reportId);
				setSelectedReport(reportId);
				System.out.println(getTempReportId()
						+ " selected Report Id is " + getSelectedReport());
				if (logo != null && showLogo) {
					setLogoName(reportId + "." + logoExt);
					copyLogo();
				}
			}
			 //addMessage("Report saved successfully");
			// createReport();
		} catch (Exception e) {
			addError(getProperty("message.report.saved.failed"));
			e.printStackTrace();
		}
		return reportId;
	}

	public void editReport() {
		int reportId = getSelectedReport();
		ReportService rs = ReportService.getInstance();
		DataSourceBackingBean dsbb = BeanFactory.getInstance()
				.getDataSourceBean();
		DataPaletteBackingBean dpbb = BeanFactory.getInstance()
				.getDataPaletteBean();
		Map<String, String> report = rs.editReport(reportId);
		resetBean();
		reportId = Integer.parseInt(report.get("bi_report_id"));
		setSelectedReport(reportId);
		setTitle(report.get("bi_report_title"));
		setDescription(report.get("bi_report_description"));
		dsbb.setSavedDataSource(Integer.parseInt(report
				.get("bi_data_source_id")));
		setSelectedGraph(report.get("report_graph"));
		getSummaryAsList(report.get("report_summary"));
		// dpbb.getColumnsAsList(report.get("graph_data"));
		setLogoName(report.get("report_logo"));
		if (report.get("report_show_logo") != null
				&& report.get("report_show_logo").equals("t")) {
			setShowLogo(true);
		} else {
			setShowLogo(false);
		}
		if (report.get("report_serial_no") != null
				&& report.get("report_serial_no").equals("t")) {
			setSerialNo(true);
		} else {
			setSerialNo(false);
		}
		if (report.get("report_saved").equals("f")) {
			setTempReportId(reportId);
			setShowLogo(true);
		}
		dpbb.populateDataPalette();
		dpbb.generateReport();
		dpbb.populateFormula();
		dpbb.populateGroupBy();
		dpbb.populateOrderBy();
		List<KeyValueItem> from = rs.getFromByReportId(reportId);
		dpbb.setFrom(from);
	}

	public void updateReport() {
		ReportService rs = ReportService.getInstance();
		DataSourceBackingBean dsbb = BeanFactory.getInstance()
				.getDataSourceBean();
		String summary = getSummaryAsString();
		String columns = getColumnsAsString();
		String logoName = getLogoName();
		setReportSaved(true);
		if (logo != null) {
			logoName = getSelectedReport() + "."
					+ getExtension(logo.getFileName());
		}
		try {
			setTitle(getTitle().replace("_UNSAVED", ""));
			rs.updateReport(getSelectedReport(), getTitle(), getDescription(),
					dsbb.getSavedDataSource(), summary, getSelectedGraph(),
					columns, logoName, showLogo, serialNo, isReportSaved());
			if (logo != null) {
				setLogoName(logoName);
				copyLogo();
			}
			if (getTempReportId() != 0) {
				setTempReportId(0);
				addMessage(getProperty("message.report.saved"));
			} else {
				addMessage(getProperty("message.report.updated"));
			}
			setCurrentAction(WebConstants.ACTION_EDIT, this.getClass());
		} catch (Exception e) {
			addError(getProperty("message.report.updation.failed"));
			e.printStackTrace();
		}
	}

	public void deleteReport() {
		int reportId = getSelectedReport();
		ReportService rs = ReportService.getInstance();
		try {
			rs.deleteReport(reportId);
			if (getTempReportId() == 0) {
				addMessage(getProperty("message.report.deleted"));
			}
			createReport();
		} catch (Exception e) {
			addError(getProperty("message.report.deletion.failed"));
		}
	}

	public List<KeyValueItem> savedReportsList() {
		ReportService rs = ReportService.getInstance();
		SessionDataBean session = BeanFactory.getInstance().getSessionBean();
		List<KeyValueItem> list = rs.getReportListByUserId(session.getUserId(), session.getCompanyId());
		return list;
	}

	// Actionlistener is called when graph is selected from toolbar
	public void changeSelectedGraph(ActionEvent event) {
		// System.out.println("Graph changed");
		String selection = (String) event.getComponent().getAttributes()
				.get("selectedgraph");
		selectedGraph = selection;
	}

}
