package com.srpl.crm.ejb.request;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jms.Session;

import com.srpl.crm.common.utils.Utils;
import com.srpl.crm.ejb.entity.CsContactORM;
import com.srpl.crm.ejb.entity.InvoiceSettingsORM;
import com.srpl.crm.ejb.entity.OrderDetailORM;
import com.srpl.crm.ejb.entity.OrderORM;
import com.srpl.crm.ejb.entity.SInvoiceDetailORM;
import com.srpl.crm.ejb.entity.SInvoiceORM;

import com.srpl.um.ejb.entity.UmCompany;
import com.srpl.um.ejb.request.CompanyDAO;

/**
 * @author Hammad Hassan Khan
 * 
 */
@Stateless
@LocalBean
public class InvoiceDAO extends GenericDAO<SInvoiceORM> {

	@EJB
	ContactDAO contactDao;
	@EJB
	OrderDAO orderDao;
	@EJB
	OrderDetailDAO orderDetailDao;
	@EJB InvoiceSettingsDAO invoiceSettingsDao;
    @EJB CompanyDAO companyDao;
	
	public InvoiceDAO() {
		super(SInvoiceORM.class);
	}

	// ================= List Invoice =======================//
	public List<SInvoiceORM> list(Long customerId) {
		// false means, return all the invoices
		return listWithPaidCheck(customerId, false);
	}

	public List<SInvoiceORM> listWithPaidCheck(Long customerId, boolean paid) {
		List<SInvoiceORM> invoices = new ArrayList<SInvoiceORM>();
		try {
			CsContactORM customer = contactDao.contactDetails(customerId);
			if (paid) {
				// if paid is true, only return single last unpaid invoice
				SInvoiceORM temp;
				temp = em
						.createQuery(
								"SELECT i FROM SInvoiceORM i WHERE i.subscriber = :c AND i.status = false",
								SInvoiceORM.class).setParameter("c", customer)
						.getResultList().get(0);
				if (temp != null) {
					invoices.add(temp);
				}
			} else {
				invoices = em
						.createQuery(
								"SELECT i FROM SInvoiceORM i WHERE i.subscriber = :c",
								SInvoiceORM.class).setParameter("c", customer)
						.getResultList();
			}
		} catch (Exception e) {
			System.out.println("error while fetching invoices.");
			e.printStackTrace();
		}
		return invoices;
	}
	
	public boolean orderInvoiceGenerated(OrderORM ord) {
		return em.createQuery("FROM SInvoiceORM WHERE orderID = :ord",SInvoiceORM.class).setParameter("ord", ord.getOrderId()).getResultList().size() > 0;
	}

	// ================= Create Invoice =======================//
	public Long create(Long order_id) {
		OrderORM currentOrder;
		List<OrderDetailORM> orderDetails;
		SInvoiceORM invoice = new SInvoiceORM();
		//SInvoiceORM previousInvoice = null;

		try{
			
		    
		   currentOrder = orderDao.retrieveOrder(order_id);
		   UmCompany company = currentOrder.getUmCompany();
		   InvoiceSettingsORM invoiceSettings =  invoiceSettingsDao.getInvoiceSettingByCompanyCustomer(company, currentOrder.getCustomer()); 
		   Timestamp currentDate = new Timestamp(new Date().getTime());
		   // add 15 days
		   Calendar cal = Calendar.getInstance();
		   cal.setTime(currentDate);
		   //cal.add(Calendar.DAY_OF_WEEK, 15);
		   // invoiceSettings.getInvoiceSettingDueDays() is number of days to make the due days 
		   cal.add(Calendar.DAY_OF_WEEK, invoiceSettings.getInvoiceSettingDueDays());
		   Timestamp dueDate = new Timestamp(cal.getTime().getTime());
		   double totalAmount = 0.00;
		
		
			try {
				// Populate invoice with data in order
				
				orderDetails = orderDetailDao.listOrderProducts(currentOrder);
				
				//fetch previous invoice against same customer
				try{
					//previousInvoice = getLatestInvoice(currentOrder.getCustomer());
					//invoice.setPreviousInvoice(previousInvoice);
				}catch (Exception e) {
					System.out.println("No previous invoice found.");
				}

				// traverse through orderDetails and populate invoiceDetails
				for (OrderDetailORM orderDetail : orderDetails) {
					SInvoiceDetailORM tmp = new SInvoiceDetailORM();
					// No need, tmp.setId(id);
					// No need, tmp.setInvoiceId(invoiceId);
					tmp.setProduct(orderDetail.getProduct());

					// Assuming the amount in order represents unit price
					//double unitPrice = orderDetail.getOrderAmmount();
					double unitPrice = (double)orderDetail.getProduct().getProductCost();
					//totalAmount += (unitPrice * orderDetail.getQuantity());
					tmp.setProductUnitPrice(unitPrice);
					tmp.setQuantity(orderDetail.getQuantity());
					invoice.getDetails().add(tmp);
				}

				totalAmount = currentOrder.getOrderTotalAmount();
				// Current Charges
				
                
				//Arrrears
				invoice.setArrears(0.00);
				/*if(previousInvoice != null){
					double arrears = 0.00;
					try{
						if(previousInvoice.getStatus()){
							//its been paid, get amount and subtract payment
							try{
								arrears = previousInvoice.getPayments().getRemainingAmount();
							}catch (Exception e) {
								System.out.println("exception while fetching payment against latest invoice");
								arrears = previousInvoice.getAmountAfterDueDate();
							}
						}else {
							arrears = previousInvoice.getAmountAfterDueDate();
						}
					}catch (Exception e) {
						System.out.println("Exception while dealing with arrears.");
					}finally{
						invoice.setArrears(arrears);
					}
				}*/
				//totalAmount += invoice.getArrears();

				// Total Amount with arears
				
				
				
				double discount = currentOrder.getDiscount();
				double netAmount = currentOrder.getNetAmount();
				double paidAmount = currentOrder.getPaidAmount();
				
				invoice.setCurrentCharges(netAmount);
				invoice.setTotalAmount(totalAmount + invoice.getArrears());
				invoice.setDiscount(discount);
				invoice.setNetAmount(netAmount);
				invoice.setPaidAmount(paidAmount);
				
				//Amount After Due Date
				// invoiceSettings.getInvoiceSettingLatePaymentFee() is percent that charged after due date
				double latePaymentPercent = invoiceSettings.getInvoiceSettingLatePaymentFee();
				latePaymentPercent = latePaymentPercent / 100;
				double amountAfterDueDate = netAmount * latePaymentPercent;
				amountAfterDueDate = netAmount + amountAfterDueDate;  
				
				invoice.setAmountAfterDueDate((amountAfterDueDate) + invoice.getArrears());

				Long cmpnyId = 0L;
				try {
					cmpnyId = currentOrder.getCreatedBy().getUserCompany();
				} catch (Exception e) {
					// TODO: handle exception
				}
				invoice.setCompanyId(cmpnyId);
				try {
					invoice.setCreatedBy(currentOrder.getCreatedBy()
							.getUserId());
				} catch (Exception e) {
					System.out.println("couldn't find created By");
				}

				// Make list, already set
				// invoice.setDetails(details);

				// Set Due Date
				invoice.setDueDate(dueDate);

				invoice.setIsBill(false);

				// Today
				invoice.setIssueDate(currentDate);

				invoice.setStatus(false);
				invoice.setOrderID(order_id);	
				invoice.setSubscriber(currentOrder.getCustomer());
				//invoice.setTitle(currentOrder.getOrderTitle() + " - Invoice"); //changed by saadia on 7-20-2013
				invoice.setTitle("Ord-"+currentOrder.getOrderId() + " - Invoice");

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			save(invoice);
			//save invoice details
			for(SInvoiceDetailORM detail:invoice.getDetails()){
				detail.setInvoice(invoice);
				em.persist(detail);
			}
			
			//Generate Mail
			final String sendTo = invoice.getSubscriber().getContactEmail();
			final String subject = "Invoice";
	        final String body = generateInvoiceHTML(invoice.getId(), invoiceSettings);
	        
	        Utils.sendMail(sendTo, subject, body, true);
	        
			return invoice.getId();
		} catch (Exception e) {
			e.printStackTrace();
			return -1L;
		}
	}
	
	public void resendInvoice(long invoice_id){
		//Generate Mail
		try{
			SInvoiceORM invoice = find(invoice_id);
			final String sendTo = invoice.getSubscriber().getContactEmail();
			final String subject = "Invoice";
			InvoiceSettingsORM invoiceSettings = new InvoiceSettingsORM();
	        final String body = generateInvoiceHTML(invoice.getId(),invoiceSettings);
	        
	        Utils.sendMail(sendTo, subject, body, true);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	// ================ Generate Invoice HTML ============== //
	public String generateInvoiceHTML(long invoice_id, InvoiceSettingsORM invoiceSettings){
		SInvoiceORM invoice = null;
		StringBuilder invoiceHtml = new StringBuilder();
		double previousAmount = 0.00;
		double previousPayment = 0.00;
		try{
			invoice = find(invoice_id);
			CsContactORM customer = invoice.getSubscriber();
			//Set previous invoice values
			/*try{
				if(invoice.getPreviousInvoice().getStatus()){
					//if paid
					previousAmount = invoice.getPreviousInvoice().getPayments().getInvoiceAmount();
					previousPayment = invoice.getPreviousInvoice().getPayments().getPaidAmount();
				}else{
					previousAmount = invoice.getPreviousInvoice().getAmountAfterDueDate();
				}
			}catch (Exception e) {
				System.out.println("exception while fetching previous invoice values in 'generateInvoiceHTML'.");
			}*/
			
			invoiceHtml.append("<table style=\"width:768px; height:110%; border: 0px solid black; box-shadow: 10px 10px 5px #888888;\" align=\"center\" cellpadding=\"0px\" cellspacing=\"0px\">");
			invoiceHtml.append("<tr><td style=\"background-image: url(http://tbaas.com/images/logo_baas.jpg); background-repeat: no-repeat; background-color: white; height:68px; width:261px; border-top-left-radius: 0px 0px;\" colspan=\"1\"></td><td style=\"background-color: white; height:68px; width:507px; padding-top: 20px; border-top-right-radius: 0px 0px;\" colspan=\"3\"><h1 style=\"color:#6ca7e7; font-weight:bold; font-size:40px; font-family:Sans-serif; letter-spacing:2px;\"> Billing Invoice</h1></td></tr>");
			invoiceHtml.append("<tr ><td bgcolor=\"#ff9c00\" colspan=\"4\" ><span style=\"float:left; color:white; margin-left:10px; font-size:10px; font-family:Sans-serif;\">");
			
			//Invoice Reference Number
			invoiceHtml.append("<b>Bill Reference Number : </b>  "+ invoice.getTitle() +" </span></td></tr>");
			
			//Customer ID
			invoiceHtml.append("<tr><td ><span style=\"float:left; color:black; font-size:10px; font-weight: bold; font-family:Sans-serif; margin-left:10px;\"> Customer ID: </span></td><td><span style=\"float:left; color:black; font-size:10px; font-family:Sans-serif; margin-left:1px;\">"+customer.getContactId()+"</span></td>");

			//Invoice Total Amount, arrears included
			invoiceHtml.append("<td ><span style=\"float:left; color:black; font-size:10px; font-weight: bold; font-family:Sans-serif; margin-left:10px;\"> Total Amount: </span></td><td><span style=\"float:left; color:black; font-size:10px; font-family:Sans-serif; margin-left:1px;\">Rs. " + invoice.getTotalAmount()+"</span></td></tr>");

			//Customer Username
			invoiceHtml.append("<tr><td><span style=\"float:left; color:black; font-size:10px; font-weight: bold; font-family:Sans-serif; margin-left:10px;\"> Username: </span></td><td><span style=\"float:left; color:black; font-size:10px; font-family:Sans-serif; margin-left:1px;\">"+customer.getContactUser().getUserName()+"</span></td>");

			Date date = new Date (invoice.getDueDate().getTime());
			String dueDate = new SimpleDateFormat("yyyy-MM-dd").format(date); // dueDate without TimeStamp 
		    
			
			invoiceHtml.append("<td ><span style=\"float:left; color:black; font-size:10px; font-weight: bold; font-family:Sans-serif; margin-left:10px;\"> Discount: </span></td><td><span style=\"float:left; color:black; font-size:10px; font-family:Sans-serif; margin-left:1px;\">Rs. " + (invoice.getTotalAmount() - invoice.getNetAmount())+"</span></td></tr>"); 
			//Customer Name
			invoiceHtml.append("<tr><td><span style=\"float:left; color:black; font-size:10px; font-weight: bold; font-family:Sans-serif; margin-left:10px;\"> Customer Name: </span></td><td><span style=\"float:left; color:black; font-size:10px; font-family:Sans-serif; margin-left:1px;\">"+customer.getContactFname() + " " + customer.getContactLname()+"</span></td>");

			invoiceHtml.append("<td ><span style=\"float:left; color:black; font-size:10px; font-weight: bold; font-family:Sans-serif; margin-left:10px;\"> Payable by due date: </span></td><td><span style=\"float:left; color:black; font-size:10px; font-weight: bold; font-family:Sans-serif; margin-left:1px;\">Rs. " +invoice.getNetAmount()+"</span></td></tr>");
			//Customer Address
			invoiceHtml.append("<tr><td><span style=\"float:left; color:black; font-size:10px; font-weight: bold; font-family:Sans-serif; margin-left:10px;\"> Billing Address: </span></td><td><span style=\"float:left; color:black; font-size:10px; font-family:Sans-serif; margin-left:1px;\">"+customer.getContactAddress()+"</span></td>");

			date = new Date (invoice.getIssueDate().getTime());
			String issueDate = new SimpleDateFormat("yyyy-MM-dd").format(date); // issueDate without TimeStamp 

			//Due Date
			invoiceHtml.append("<td><span style=\"color:black; font-size:10px; font-weight: bold; font-family:Sans-serif; margin-left:10px;\"> Payment Due Date: </span></td><td><span style=\"float:left; color:black; font-size:10px; font-family:Sans-serif; margin-left:1px;\">"+invoice.getDueDate()+"</span></td></tr>");
			//Customer Contact Number
			invoiceHtml.append("<tr><td><span style=\"float:left; color:black; font-size:10px; font-weight: bold; font-family:Sans-serif; margin-left:10px;\"> Contact Number: </span></td><td><span style=\"float:left; color:black; font-size:10px; font-family:Sans-serif; margin-left:1px;\">"+customer.getContactPhone()+"</span></td>");
			
			//Billing Month
			invoiceHtml.append("<td><span style=\"float:left; color:black; font-size:10px; font-weight: bold; font-family:Sans-serif; margin-left:10px;\"> Billing Period: </span></td><td><span style=\"float:left; color:black; font-size:10px; font-family:Sans-serif; margin-left:1px;\">"+invoice.getIssueDate()+"</span></td></tr>");			
			
			//Customer Email Address
			invoiceHtml.append("<tr><td><span style=\"float:left; color:black; font-size:10px; font-weight: bold; font-family:Sans-serif; margin-left:10px;\"> Email:</span></td><td><span style=\"float:left; color:black; font-size:10px; font-family:Sans-serif; margin-left:1px;\">"+customer.getContactEmail()+"</span></td>");
			//invoiceHtml.append("<td><span style=\"float:left; color:black; font-size:10px; font-weight: bold; font-family:Sans-serif; margin-left:10px;\">&nbsp;</span></td><td><span style=\"float:left; color:black; font-size:10px; font-family:Sans-serif; margin-left:1px;\">&nbsp;</span></td></tr>");
			
			//Amount After due date
			invoiceHtml.append("<td><span style=\"float:left; color:red; font-size:10px; font-family:Sans-serif; margin-left:10px;\"> Payable after due date: </span></td><td><span style=\"float:left; color:red; font-size:10px; font-family:Sans-serif; margin-left:1px;\">Rs. " + invoice.getAmountAfterDueDate()+"</span></td></tr>");
			
			invoiceHtml.append("<tr><td colspan=\"4\"><span style=\"float:left; color:black; font-size:10px; font-family:Sans-serif; margin-left:1px;color:red;\"  > <blink>Please pay all outstanding dues on or before "+invoice.getDueDate()+" to avoid late payment charge</blink></span></td></tr>");
			
			invoiceHtml.append("<tr><td bgcolor=\"#ff9c00\" height=\"30px\" style=\"color:white; \"><span style=\"color:white; margin-left:10px; font-size:10px; font-family:Sans-serif;color:white; margin-left:5px; margin-top:5px; font-size:14px;\">Product</span></td>");
			invoiceHtml.append("<td bgcolor=\"#ff9c00\" height=\"30px\" style=\"color:white; margin-left:5px; margin-top:5px; font-size:14px;\" >Rate</td>" +
			        "<td bgcolor=\"#ff9c00\" height=\"30px\" style=\"color:white; margin-left:5px; margin-top:5px; font-size:14px;\" >Quantity</td>" +
					"<td bgcolor=\"#ff9c00\"><span style=\"float:left; color:white; margin-left:10px; font-size:10px; font-family:Sans-serif;font-size:14px; color:white; float:right; margin-right:40px\">Rs.</span></td></tr>");
			
			//Place the loop stuff here, bill details
			
			for(SInvoiceDetailORM details:invoice.getDetails()){
				//Monthly line rent
				invoiceHtml.append("<tr>"+"" +
						"<td><span style=\"float:left; color:black; font-size:10px; font-weight: bold; font-family:Sans-serif; margin-left:10px;\">"+details.getProduct().getProductTitle()+"</span></td>"+
						"<td><span style=\"float:left; color:black; font-size:10px; font-weight: bold; font-family:Sans-serif; margin-left:5px;\">"+details.getProductUnitPrice()+"</span></td>"+
						"<td><span style=\"float:left; color:black; font-size:10px; font-weight: bold; font-family:Sans-serif; margin-left:5px;\">"+details.getQuantity()+"</span></td>"+
						"<td><span style=\"float:left; color:black; font-size:10px; font-family:Sans-serif; margin-left:1px; float:right; margin-right:30px\">"+details.getProductUnitPrice()*details.getQuantity()+"</span></td>"+
						"</tr>");
				//fetch product title here
				//monthly rate / charges
				//this month charges
			}
			
			//Adjustments and Blank row
			invoiceHtml.append("<tr><td ><span style=\"float:left; color:black; font-size:10px; font-weight: bold; font-family:Sans-serif; margin-left:10px;\"> <u>Adjustments</u> </span></td><td >&nbsp;</td><td style=\"float:left; color:black; font-size:10px; font-family:Sans-serif; margin-left:1px;\">&nbsp;</td><td><span style=\"float:left; color:black; font-size:10px; font-family:Sans-serif; margin-left:1px; float:right; margin-right:30px\">"+(invoice.getTotalAmount() - invoice.getNetAmount())+"</span></td></tr>");
			
			//Other charges and Blank row
			invoiceHtml.append("<tr><td ><span style=\"float:left; color:black; font-size:10px; font-weight: bold; font-family:Sans-serif; margin-left:10px;\"> <u> Other Charges </u> </span></td><td >&nbsp;</td><td style=\"float:left; color:black; font-size:10px; font-family:Sans-serif; margin-left:1px;\">&nbsp;</td><td><span style=\"float:left; color:black; font-size:10px; font-family:Sans-serif; margin-left:1px; float:right; margin-right:30px\"> 0.000</span></td></tr>");
			
			//Late Payment Charges
			invoiceHtml.append("<tr><td ><span style=\"float:left; color:black; font-size:10px; font-weight: bold; font-family:Sans-serif; margin-left:10px;\"> Late Payment Charges </span></td>");
			
			
			invoiceHtml.append("<tr><td bgcolor=\"#ff9c00\" height=\"30px\" style=\"color:white; \"><span style=\"color:white; margin-left:10px; font-size:10px; font-family:Sans-serif;color:white; margin-left:5px; margin-top:5px; font-size:14px;\">Payment History</span></td><td bgcolor=\"#ff9c00\" height=\"30px\">&nbsp;</td><td bgcolor=\"#ff9c00\" height=\"30px\" >&nbsp;</td><td bgcolor=\"#ff9c00\"><span style=\"float:left; color:white; margin-left:10px; font-size:10px; font-family:Sans-serif;font-size:14px; color:white; float:right; margin-right:40px\">Rs.</span></td></tr>");
			
			invoiceHtml.append("<tr><td><span style=\"float:left; color:black; font-size:10px; font-weight: bold; font-family:Sans-serif; margin-left:10px;\">Amount Payable </span></td><td>&nbsp;</td><td >&nbsp;</td><td><span style=\"float:left; color:black; font-size:10px; font-family:Sans-serif; margin-left:1px; float:right; margin-right:30px\">"+invoice.getNetAmount()+"</span></td></tr>");
			
			invoiceHtml.append("<tr><td><span style=\"float:left; color:black; font-size:10px; font-weight: bold; font-family:Sans-serif; margin-left:10px;\">Paid Amount </span></td><td>&nbsp;</td><td >&nbsp;</td><td><span style=\"float:left; color:black; font-size:10px; font-family:Sans-serif; margin-left:1px; float:right; margin-right:30px\">"+invoice.getPaidAmount()+"</span></td></tr>");
			
			//Previous Balance (last invoice total amount)
			//Actual Value 
//			invoiceHtml.append("<tr><td><span style=\"float:left; color:black; font-size:10px; font-weight: bold; font-family:Sans-serif; margin-left:10px;\">Previous Balance </span></td><td>&nbsp;</td><td >&nbsp;</td><td><span style=\"float:left; color:black; font-size:10px; font-family:Sans-serif; margin-left:1px; float:right; margin-right:30px\">"+previousAmount+"</span></td></tr>");

			//Previous Payment Received (last payment)
			//Previous Payment Received Value
//			invoiceHtml.append("<tr><td><span style=\"float:left; color:black; font-size:10px; font-weight: bold; font-family:Sans-serif; margin-left:10px;\"> Payment Received </span></td><td>&nbsp;</td><td >&nbsp;</td><td><span style=\"float:left; color:black; font-size:10px; font-family:Sans-serif; margin-left:1px; float:right; margin-right:30px\"> Rs. "+previousPayment+"</span></td></tr>");
			
			invoiceHtml.append("<tr><td style=\"border-bottom: solid gray 1px; color:white; font-size:1px; \" colspan=\"4\">.</td></td></tr>");

			//Arrears or balance
			//Arrears Value
			//invoiceHtml.append("<tr><td><span style=\"float:left; color:black; font-size:10px; font-weight: bold; font-family:Sans-serif; margin-left:10px;\"> Net Previous Balance (A) </span></td><td>&nbsp;</td><td >&nbsp;</td><td ><span style=\"float:left; color:black; font-size:10px; font-family:Sans-serif; margin-left:1px; float:right; margin-right:30px\">Rs. "+invoice.getArrears()+"</span></td></tr>");
			invoiceHtml.append("<tr><td><span style=\"float:left; color:black; font-size:10px; font-weight: bold; font-family:Sans-serif; margin-left:10px;\"> Total Amount Due </span></td><td>&nbsp;</td><td >&nbsp;</td><td ><span style=\"float:left; color:black; font-size:10px; font-family:Sans-serif; margin-left:1px; float:right; margin-right:30px\">Rs. "+(invoice.getNetAmount() - invoice.getPaidAmount())+"</span></td></tr>");			
			
			
	/*		invoiceHtml.append("<tr><td bgcolor=\"gray\" height=\"30px\" style=\"color:white; \"><span style=\"color:white; margin-left:10px; font-size:10px; font-family:Sans-serif;color:white; margin-left:5px; margin-top:5px; font-size:14px;\">Total balance</span></td><td bgcolor=\"gray\" height=\"30px\">&nbsp;</td><td bgcolor=\"gray\" height=\"30px\" style=\"color:white; margin-left:5px; margin-top:5px; font-size:14px;\" >&nbsp;</td>");
			invoiceHtml.append("<td bgcolor=\"gray\"><span style=\"float:left; color:white; margin-left:10px; font-size:10px; font-family:Sans-serif;font-size:14px; color:white; float:right; margin-right:40px\">Rs.</span></td></tr>");

			//Current Charges
			invoiceHtml.append("<tr><td ><span style=\"float:left; color:black; font-size:10px; font-weight: bold; font-family:Sans-serif; margin-left:10px;\" style=\"color:#52819e\"> Current Charges (B)  </span></td><td >&nbsp;</td><td style=\"float:left; color:black; font-size:10px; font-family:Sans-serif; margin-left:1px;\">&nbsp;</td>");
			//Current Charges Value
			invoiceHtml.append("<td><span style=\"float:left; color:black; font-size:10px; font-family:Sans-serif; margin-left:1px; float:right; margin-right:30px; color:#52819e\">"+invoice.getCurrentCharges()+"</span></td></tr>");
			
			//Current Charges + Arrears
			invoiceHtml.append("<tr><td ><span style=\"float:left; color:black; font-size:10px; font-weight: bold; font-family:Sans-serif; margin-left:10px;\" style=\"color:#52819e\"> Total Amount Due (A+B)  </span></td><td >&nbsp;</td><td style=\"float:left; color:black; font-size:10px; font-family:Sans-serif; margin-left:1px;\">&nbsp;</td>");
			//Current Charges + Arrears Value
			invoiceHtml.append("<td><span style=\"float:left; color:black; font-size:10px; font-family:Sans-serif; margin-left:1px; float:right; margin-right:30px; color:#52819e\">"+invoice.getTotalAmount()+"</span></td></tr>");
			*/
			invoiceHtml.append("<tr><td colspan=\"4\" align=\"center\"><b style=\"font-size:10px; font-weight: bold; font-family:Sans-serif;\" > Thanks for the payment </b></td></tr>");
			invoiceHtml.append("<tr><td colspan=\"4\" align=\"center\"><i style=\"font-size:10px; font-weight: bold; font-family:Sans-serif; color:#52819e\" > This is a computer generated invoice and does not require a signature </i></td></tr>");
			invoiceHtml.append("<tr><td colspan=\"4\"><span style=\"float:left; color:black; font-size:10px; font-weight: bold; font-family:Sans-serif; margin-left:10px;\" style=\"color:green\">  *"+invoiceSettings.getInvoiceSettingLatePaymentFee()+"% late payment charges will be applicable on payments made after the due date </span></td></tr>");
			invoiceHtml.append("<tr><td colspan=\"4\"><span style=\"float:left; color:black; font-size:10px; font-weight: bold; font-family:Sans-serif; margin-left:10px;\" style=\"color:black\">  For billing concern mail at <a href=\"mailto:webmaster@example.com\"> Contact Centre  </a> or call 111-111-111. </span></td></tr>");
			invoiceHtml.append("<tr><td colspan=\"4\"><span style=\"float:left; color:black; font-size:10px; font-weight: bold; font-family:Sans-serif; margin-left:10px;\" style=\"color:#52819e\">&nbsp;</span></td></tr>");
			invoiceHtml.append("<tr ><td style=\"border-top:  solid #5093d4 2px; width:5px; width:100%;\" background=\"themes/default/images/footer.png\"bgcolor=\"#E6E6FA\" align=\"center\" colspan=\"4\" ><span style=\"color:#666e76; font-size:10px; font-family:Sans-serif; letter-spacing:2px; vertical-align: bottom;\">Helpline: 111-111-111 <br/>Email: customercare@systemsresearchltd.com <br/>Website: <a href=\"http://www.systemsresearchltd.com/\" target=\"_new\"> www.systemsresearchltd.com </a> <br/>&copy; Copyright 2013 Tbaas. All Rights Reserved.</span></td></tr></table>");
			
		}catch (Exception e) {
			System.out.println("invoice id is invalid.");
		}
		
		return invoiceHtml.toString();
	}

	// ================= View Invoice =======================//
	public SInvoiceORM details(Long invoice_id) {
		SInvoiceORM invoice = null;
		try {
			invoice = find(invoice_id);
			try {
				System.out.println("Invoice details size: "
						+ invoice.getDetails().size());
			} catch (Exception e) {
				System.out.println("exception while fetching invoice details.");
			}
		} catch (Exception e) {
			System.out.println("invoice not found.");
		}
		return invoice;
	}

	// ================= View Invoice =======================//
	public SInvoiceORM getLatestInvoice(CsContactORM subscriber) {
		SInvoiceORM invoice = null;
		try {
			invoice = em.createQuery("SELECT i FROM SInvoiceORM i WHERE i.subscriber = :subscriber ORDER BY i.id DESC",SInvoiceORM.class)
					.setParameter("subscriber", subscriber)
					.getResultList().get(0);
			try {
				System.out.println("Invoice details size: "
						+ invoice.getDetails().size());
			} catch (Exception e) {
				System.out.println("exception while fetching invoice details.");
			}
		} catch (Exception e) {
			System.out.println("invoice not found.");
			//e.printStackTrace();
		}
		return invoice;
	}

	// ================= Update Invoice =======================//
	public void update(SInvoiceORM invoice) {
		
		super.update(invoice);
	}

	// ================= Delete Invoice =======================//
	public void delete(Long id) {
		super.delete(id);
	}
}
