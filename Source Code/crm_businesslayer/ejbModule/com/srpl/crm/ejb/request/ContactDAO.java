package com.srpl.crm.ejb.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//import javax.annotation.security.DeclareRoles;
//import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.srpl.crm.common.utils.CsContactDetails;
import com.srpl.crm.ejb.entity.CsAccount;
import com.srpl.crm.ejb.entity.CsContactORM;
import com.srpl.um.ejb.entity.UmUser;
import com.srpl.crm.ejb.exceptions.AccountNotFoundException;
import com.srpl.crm.ejb.exceptions.ContactNotFoundException;
import com.srpl.um.ejb.exceptions.UserNotFoundException;
import com.srpl.um.ejb.request.UserDAO;

/**
 * Session Bean implementation class ContactDAO
 */
// @DeclareRoles({"AccountManager","User"})
@Stateless
@LocalBean
public class ContactDAO extends GenericDAO<CsContactORM> {

	@EJB
	UserDAO userDao;
	@EJB
	AccountDAO accountDao;

	/**
	 * Default constructor.
	 */
	public ContactDAO() {
		// TODO Auto-generated constructor stub
		super(CsContactORM.class);
	}

	/*
	 * public List<CsContactDetails> listContacts(){ List<CsContact> contacts =
	 * findAll(); List<CsContactDetails> detailsList = new
	 * ArrayList<CsContactDetails>(); Iterator<CsContact> i =
	 * contacts.iterator(); while (i.hasNext()) { CsContact contact =
	 * (CsContact) i.next(); CsContactDetails details = new
	 * CsContactDetails(contact.getContactId(), contact.getContactFname(),
	 * contact.getContactLname(), contact.getContactFatherName(),
	 * contact.getContactAddress(), contact.getContactCountry(),
	 * contact.getContactState(), contact.getContactCity(),
	 * contact.getContactZipcode(), contact.getContactEmail(),
	 * contact.getContactPhone(), contact.getContactCnic(),
	 * contact.getContactCnicCopy(), contact.getContactDob(),
	 * contact.getContactCreatedon(), contact.getContactStatus(),
	 * contact.getCsAccount().getAccountId()); detailsList.add(details); }
	 * return detailsList; }
	 */
	
	public List<CsContactORM> listContacts(long companayId){
		return listContacts(companayId, "", "");
	}

	public List<CsContactORM> listContacts(long companyId, String filterBy,
			String filterValue)
	/* throws ContactNotFoundException */{
		List<CsContactORM> contacts = new ArrayList<CsContactORM>();
		String query = "SELECT c FROM CsContactORM c ";
		String where = " WHERE c.companyId = :cId ";
		String join = "";
		if (filterBy != null && !filterBy.equals("")) {
			switch (filterBy) {
			case "fname":
				filterValue = filterValue.toLowerCase();
				where += " AND lower(c.contactFname) like '%"+filterValue+"%'";
				break;
			case "lname":
				filterValue = filterValue.toLowerCase();
				where += " AND lower(c.contactLname) like '%"+filterValue+"%'";
				break;
			case "fathername":
				filterValue = filterValue.toLowerCase();
				where += " AND lower(c.contactFatherName) like '%"+filterValue+"%'";
				break;
			case "loginId":
				where += " AND u.userName = "+filterValue;
				join += " INNER JOIN  c.contactUser u";
				break;
			case "phone":
				where += " AND c.contactPhone = "+filterValue;
				break;
			case "email":
				where += " AND c.contactEmail = "+filterValue;
				break;
			case "cnic":
				where += " AND c.contactCnic = "+filterValue;
				break;
			}
			 contacts = em.createQuery(query+join+where, CsContactORM.class)
					.setParameter("cId", companyId)
					.getResultList();
		} else {
			 contacts = em.createQuery(query+join+where, CsContactORM.class)
						.setParameter("cId", companyId)
						.getResultList();
		}

		
		/*
		 * if (contacts.size() == 0) { throw new
		 * ContactNotFoundException("No Customer Contacts Found"); }
		 */
		return contacts;
	}

	public List<CsContactORM> listAutoContacts(long companyId, String query)
			throws ContactNotFoundException {
		String lowerCaseQuery = query.toLowerCase();
		List<CsContactORM> contacts = em
				.createQuery(
						"SELECT c FROM CsContactORM c WHERE companyId = :cId AND (lower(contactFname) like'"
								+ lowerCaseQuery + "%')", CsContactORM.class)
				.setParameter("cId", companyId).getResultList();
		if (contacts.size() == 0) {
			throw new ContactNotFoundException("No Customer Contacts Found");
		}
		return contacts;
	}

	// @RolesAllowed("User")
	/*
	 * public Long createContact(CsContactDetails details){ CsAccount account =
	 * null; CsContact contact = new CsContact(details.getContactFname(),
	 * details.getContactLname(), details.getContactFatherName(),
	 * details.getContactAddress(), details.getContactCountry(),
	 * details.getContactState(), details.getContactCity(),
	 * details.getContactZipcode(), details.getContactEmail(),
	 * details.getContactPhone(), details.getContactCnic(),
	 * details.getContactCnicCopy(), details.getContactDob(),
	 * details.getContactCreatedon(), details.getContactStatus());
	 * if(details.getAccountId() == 0){ CsAccount indaccount = new
	 * CsAccount(details.getContactFname()+' '+details.getContactLname(),
	 * details.getContactAddress(), details.getContactCountry(),
	 * details.getContactState(), details.getContactCity(),
	 * details.getContactZipcode(), details.getContactEmail(),
	 * details.getContactPhone(), details.getContactCreatedon(), false,
	 * details.getContactStatus()); em.persist(indaccount);
	 * details.setAccountId(indaccount.getAccountId()); } account =
	 * em.find(CsAccount.class, details.getAccountId());
	 * contact.setCsAccount(account); save(contact); return
	 * contact.getContactId(); }
	 */

	// @RolesAllowed("User")
	public Long createContact(CsContactORM contact, Long accountId)
			throws AccountNotFoundException {
		CsAccount account = null;
		if (accountId == null) {
			throw new AccountNotFoundException("Invalid Account Id");
		}
		if (accountId == 0) {
			CsAccount indaccount = new CsAccount(
					contact.getCompanyId(),
					contact.getContactFname() + ' ' + contact.getContactLname(),
					contact.getContactAddress(), contact.getContactCountry(),
					contact.getContactState(), contact.getContactCity(),
					contact.getContactZipcode(), contact.getContactEmail(),
					contact.getContactPhone(), contact.getContactCreatedon(),
					false, contact.getContactStatus(), contact.getCreatedBy());
			em.persist(indaccount);
			accountId = indaccount.getAccountId();
		}
		account = em.find(CsAccount.class, accountId);
		contact.setCsAccount(account);

		// Create User and Attach

		try {
			Long userId = userDao.createCustomer(contact.getContactUser());

			try {
				System.out.println("the customer's user id is : "
						+ contact.getContactUser().getUserId());
			} catch (Exception e) {
				// TODO: handle exception }

			}
		} catch (Exception e) {
			System.out.println("usre not created for customer");
		}

		save(contact);
		return contact.getContactId();
	}

	// @RolesAllowed("User")
	public Long createCustomerContact(CsContactORM contact, Long accountId)
			throws AccountNotFoundException, UserNotFoundException {
		CsAccount account = null;
		if (accountId == null) {
			throw new AccountNotFoundException("Invalid Account Id");
		}
		if (accountId == 0) {
			CsAccount indaccount = new CsAccount(
					contact.getCompanyId(),
					contact.getContactFname() + ' ' + contact.getContactLname(),
					contact.getContactAddress(), contact.getContactCountry(),
					contact.getContactState(), contact.getContactCity(),
					contact.getContactZipcode(), contact.getContactEmail(),
					contact.getContactPhone(), contact.getContactCreatedon(),
					false, contact.getContactStatus(), contact.getCreatedBy());
			em.persist(indaccount);
			accountId = indaccount.getAccountId();
		}
		account = em.find(CsAccount.class, accountId);
		contact.setCsAccount(account);
		/*
		 * UmUser user = null; if (userId == null) { throw new
		 * UserNotFoundException("Invalid User Id"); }
		 */

		/*
		 * if (userId == 0) { UmUser conUser = new
		 * UmUser(contact.getContactFname(), "", contact.getContactFname(),
		 * contact.getContactLname(), contact.getContactAddress(),
		 * contact.getContactCountry(), contact.getContactState(),
		 * contact.getContactCity(), contact.getContactZipcode(),
		 * contact.getContactEmail(), contact.getContactPhone(), " ", 0L, " ",
		 * false, contact.getCompanyId(), contact.getContactCreatedon(), false,
		 * false, true); em.persist(conUser); userId = conUser.getUserId(); }
		 * user = em.find(UmUser.class, userId); contact.setContactUser(user);
		 */
		// Create User and Attach

		try {
			Long userId = userDao.createCustomer(contact.getContactUser());
			System.out.println("the customer's user id is : "
					+ contact.getContactUser().getUserId()
					+ "--user id using cs contacts" + userId);
		} catch (Exception e) {
			System.out.println("usre not created for customer");
		}

		save(contact);
		return contact.getContactId();
	}

	// @RolesAllowed("User")
	/*
	 * public void updateContact(CsContactDetails details, Long hidaccountId){
	 * CsContact contact = new CsContact(details.getContactFname(),
	 * details.getContactLname(), details.getContactFatherName(),
	 * details.getContactAddress(), details.getContactCountry(),
	 * details.getContactState(), details.getContactCity(),
	 * details.getContactZipcode(), details.getContactEmail(),
	 * details.getContactPhone(), details.getContactCnic(),
	 * details.getContactCnicCopy(), details.getContactDob(),
	 * details.getContactCreatedon(), details.getContactStatus());
	 * contact.setContactId(details.getContactId()); CsAccount account =
	 * em.find(CsAccount.class, details.getAccountId()); CsAccount hidaccount =
	 * em.find(CsAccount.class, hidaccountId);
	 * 
	 * if(account.getAccountIscompany() != hidaccount.getAccountIscompany()){
	 * if(account.getAccountIscompany() == false){ CsAccount indaccount = new
	 * CsAccount(details.getContactFname()+' '+details.getContactLname(),
	 * details.getContactAddress(), details.getContactCountry(),
	 * details.getContactState(), details.getContactCity(),
	 * details.getContactZipcode(), details.getContactEmail(),
	 * details.getContactPhone(), details.getContactCreatedon(), false,
	 * details.getContactStatus()); em.persist(indaccount);
	 * details.setAccountId(indaccount.getAccountId()); }
	 * if(hidaccount.getAccountIscompany() == false){ em.remove(hidaccount); } }
	 * 
	 * contact.setCsAccount(account); update(contact); }
	 */

	// @RolesAllowed("User")
	public void updateContact(CsContactORM contact, Long accountId,
			Long hidaccountId) throws AccountNotFoundException {
		if (accountId == null) {
			throw new AccountNotFoundException("Invalid Account Id");
		}
		if (hidaccountId == null) {
			throw new AccountNotFoundException("Invalid Account Id");
		}
		CsAccount selaccount = em.find(CsAccount.class, accountId);
		CsAccount hidaccount = em.find(CsAccount.class, hidaccountId);
		if (selaccount.getAccountIscompany() != hidaccount
				.getAccountIscompany()) {
			if (selaccount.getAccountIscompany() == false) {
				CsAccount indaccount = new CsAccount(contact.getCompanyId(),
						contact.getContactFname() + ' '
								+ contact.getContactLname(),
						contact.getContactAddress(),
						contact.getContactCountry(), contact.getContactState(),
						contact.getContactCity(), contact.getContactZipcode(),
						contact.getContactEmail(), contact.getContactPhone(),
						contact.getContactCreatedon(), false,
						contact.getContactStatus(), contact.getCreatedBy());
				em.persist(indaccount);
				accountId = indaccount.getAccountId();
			} else {
				em.remove(hidaccount);
			}
		}
		CsAccount account = em.find(CsAccount.class, accountId);
		contact.setCsAccount(account);
		update(contact);
	}

	// Delete this
	public void updateSubscription(CsContactORM customer) {
		update(customer);
	}

	// @RolesAllowed("User")
	public void deleteContact(Long contactId) {
		// I have changed the data type from int to long (Hammad Hassan).
		// Delete User first
		CsContactORM contact = find(contactId);
		userDao.deleteUser(contact.getContactUser().getUserId());
		delete(contactId);
	}

	public List<CsContactORM> getImportContacts(Timestamp stamp) {
		List<CsContactORM> cnt = null;
		cnt = em.createQuery(
				"from CsContactORM where contactCreatedon = :stamp",
				CsContactORM.class).setParameter("stamp", stamp)
				.getResultList();
		return cnt;
	}

	/*
	 * @RolesAllowed("User") public void deleteContact(Long contactId) throws
	 * ContactNotFoundException { if (contactId == null) { throw new
	 * ContactNotFoundException("Invalid Contact Id"); } delete(contactId); }
	 */

	/*
	 * public CsContactDetails contactDetails(Long contactId){ CsContact contact
	 * = null; CsContactDetails details = null; contact = find(contactId);
	 * details = new CsContactDetails(contact.getContactId(),
	 * contact.getContactFname(), contact.getContactLname(),
	 * contact.getContactFatherName(), contact.getContactAddress(),
	 * contact.getContactCountry(), contact.getContactState(),
	 * contact.getContactCity(), contact.getContactZipcode(),
	 * contact.getContactEmail(), contact.getContactPhone(),
	 * contact.getContactCnic(), contact.getContactCnicCopy(),
	 * contact.getContactDob(), contact.getContactCreatedon(),
	 * contact.getContactStatus(),contact.getCsAccount().getAccountId()); return
	 * details; }
	 */

	public CsContactORM contactDetails(Long contactId)
			throws ContactNotFoundException {
		CsContactORM contact = null;
		if (contactId == null) {
			throw new ContactNotFoundException("Invalid Contact Id");
		}
		contact = find(contactId);
		return contact;
	}
	
	public CsContactORM mappedContactDetails(Long mappedId) {
		return (CsContactORM)em.createQuery("from CsContactORM where mappedId = :mid",CsContactORM.class).setParameter("mid", mappedId).getSingleResult();
	}

	/*
	 * public static boolean compareArrays(String[] arr1, String[] arr2) {
	 * boolean findcol = false; for (int i = 0; i < arr1.length; i++) { for (int
	 * j = 0; j < arr2.length; j++) { if (arr1[i].equals(arr2[j])) { findcol =
	 * true; } } if(findcol == false){ return true; } } return false; }
	 */

	public List<CsContactDetails> mapContacts(InputStream in) {
		java.util.Date date = new java.util.Date();
		List<CsContactDetails> detailsList = new ArrayList<CsContactDetails>();
		BufferedReader input = new BufferedReader(new InputStreamReader(in));
		String line = null;

		try {
			input.readLine();

			while ((line = input.readLine()) != null) {
				String[] rec = line.split(",");
				CsContactDetails contact = new CsContactDetails();
				contact.setContactFname(rec[0]);
				contact.setContactLname(rec[1]);
				contact.setContactFatherName(rec[2]);
				contact.setContactAddress(rec[3]);
				/*
				 * Date dob=new Date(rec[4]); Timestamp time=new
				 * Timestamp(dob.getTime()); time.setDate(dob.getDate());
				 * time.setMonth(dob.getMonth()); time.setYear(dob.getMonth());
				 */

				// contact.setContactDob(time);
				contact.setContactCnic(rec[5]);
				contact.setAccountId(Long.valueOf(rec[6]));
				contact.setContactCreatedon(new Timestamp(date.getTime()));
				contact.setContactStatus(true);
				detailsList.add(contact);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return detailsList;
	}

	/*
	 * @RolesAllowed({"AccountManager","User"}) public List<CsContact>
	 * mapContacts(InputStream in) throws FileIOException { java.util.Date date=
	 * new java.util.Date(); List<CsContact> detailsList = new
	 * ArrayList<CsContact>(); BufferedReader input = new BufferedReader(new
	 * InputStreamReader(in)); String line = null; try { input.readLine();
	 * while((line = input.readLine()) != null){ String[] rec = line.split(",");
	 * CsContact contact = new CsContact(); contact.setContactFname(rec[0]);
	 * contact.setContactLname(rec[1]); contact.setContactFatherName(rec[2]);
	 * contact.setContactAddress(rec[3]);
	 * contact.setContactDob(Timestamp.valueOf(rec[4]));
	 * contact.setContactCnic(rec[5]); CsAccount account =
	 * em.find(CsAccount.class, Integer.parseInt(rec[6]));
	 * contact.setCsAccount(account); contact.setContactCreatedon(new
	 * Timestamp(date.getTime())); contact.setContactStatus(true);
	 * detailsList.add(contact); } } catch (IOException e) { throw new
	 * FileIOException(e.getMessage()); } return detailsList; }
	 */

	public boolean importUsers(List<CsContactDetails> contacts) {
		java.util.Date date = new java.util.Date();
		/*
		 * String[] reqlist =
		 * {"contact_fname","contact_lname","contact_father_name"
		 * ,"contact_address","contact_dob","contact_cnic"}; Iterator<String[]>
		 * itr = contacts.iterator(); String[] sublist = itr.next();
		 * if(compareArrays(reqlist, sublist)) return false;
		 */
		Iterator<CsContactDetails> itr = contacts.iterator();
		while (itr.hasNext()) {
			CsContactDetails rec = itr.next();
			CsContactORM contact = new CsContactORM();
			contact.setContactFname(rec.getContactFname());
			contact.setContactLname(rec.getContactLname());
			contact.setContactFatherName(rec.getContactFatherName());
			contact.setContactAddress(rec.getContactAddress());
			contact.setContactDob(new Timestamp(date.getTime()));
			contact.setContactCnic(rec.getContactCnic());
			contact.setContactCreatedon(new Timestamp(date.getTime()));
			contact.setContactStatus(true);
			save(contact);
		}
		return true;
	}

	// @RolesAllowed({"AccountManager","User"})
	// public boolean importContacts(List<CsContactORM> contacts, Boolean
	// isContact)
	// why there was this constructoer
	public boolean importContacts(List<CsContactORM> contacts, Long company) {
		Iterator<CsContactORM> itr = contacts.iterator();
		while (itr.hasNext()) {
			CsContactORM contact = itr.next();
			/*if(contact.getCsAccount().getAccountId() != null && !accountDao.accntIDExists(contact.getCsAccount().getAccountId()))*/ accountDao.createAccount(contact.getCsAccount());
			userDao.createCustomer(contact.getContactUser());			
			save(contact);
		}
		return true;
	}
	
	public void createMappedContact(CsContactORM contact){
		accountDao.createAccount(contact.getCsAccount());
		userDao.createCustomer(contact.getContactUser());		
		save(contact);
	}
	
	public void updateMappedContact(CsContactORM contact){
		update(contact);
	}

	public Boolean contactIDExists(Long cntId) /* throws UserNotFoundException */{
		List<CsContactORM> cnts = em
				.createQuery("from CsContactORM where contactId = :uid",
						CsContactORM.class).setParameter("uid", cntId)
				.getResultList();
		return (cnts.size() > 0);
	}

	public Boolean mappedIDExists(Long mappedId) /* throws UserNotFoundException */{
		List<CsContactORM> cnts = em
				.createQuery("from CsContactORM where mappedId = :mid",
						CsContactORM.class).setParameter("mid", mappedId)
				.getResultList();
		return (cnts.size() > 0);
	}

	public CsContactORM contactByEmail(String email) {
		System.out.println(email);
		CsContactORM contact = null;
		if (email == null) {
			// throw new ContactNotFoundException("Invalid Contact Id");
		}
		// contact =
		// em.createQuery("SELECT c FROM CsContact c WHERE c.contactEmail = :email",
		// CsContact.class).setParameter("email", email).getSingleResult();
		contact = find(25);
		return contact;
	}

	public CsContactORM getContactByUserId(Long userId)
			throws ContactNotFoundException {
		CsContactORM contact = null;
		UmUser user = null;
		try {
			user = userDao.umUserDetails(userId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			contact = em
					.createQuery(
							"from CsContactORM c where c.contactUser =:user ",
							CsContactORM.class).setParameter("user", user)
					.getSingleResult();
		} catch (Exception e) {
			System.out.println("Exception in ContactDAO getContactByUserId()");
		}
		return contact;
	}

	public Long getContactId(String name) {
		Long id = null;
		CsContactORM customer = null;
		try {
			customer = em
					.createQuery(
							"SELECT c FROM CsContactORM c WHERE c.loginName = :name",
							CsContactORM.class).setParameter("name", name)
					.getSingleResult();
			id = customer.getContactId();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return id;
	}
}