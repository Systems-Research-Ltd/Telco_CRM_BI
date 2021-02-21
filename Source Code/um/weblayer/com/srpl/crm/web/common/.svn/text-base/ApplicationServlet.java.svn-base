package com.srpl.crm.web.common;

import java.io.IOException;

import javax.faces.FactoryFinder;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.context.FacesContextFactory;
import javax.faces.lifecycle.Lifecycle;
import javax.faces.lifecycle.LifecycleFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitguiders.util.jsf.WebConstants;
import com.bitguiders.util.security.Encryptor;
import com.srpl.um.ejb.request.Application;

import com.srpl.um.web.controller.BeanFactory;

/**
 * Servlet implementation class ApplicationServlet
 */
@WebServlet("/ApplicationServlet")
public class ApplicationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplicationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("-----UM Logout called Selected Application---->");
		request.logout();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String user = request.getParameter("j_username");
		String toLowerCase = user.toLowerCase();
		user = toLowerCase;
        String pass = request.getParameter("j_password");
        String app = request.getParameter("application");
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (facesContext == null) {
            FacesContextFactory contextFactory  = (FacesContextFactory)FactoryFinder.getFactory(FactoryFinder.FACES_CONTEXT_FACTORY);
            LifecycleFactory lifecycleFactory = (LifecycleFactory)FactoryFinder.getFactory(FactoryFinder.LIFECYCLE_FACTORY); 
            Lifecycle lifecycle = lifecycleFactory.getLifecycle(LifecycleFactory.DEFAULT_LIFECYCLE);
            facesContext = contextFactory.getFacesContext(request.getSession().getServletContext(), request, response, lifecycle);
            UIViewRoot view = facesContext.getApplication().getViewHandler().createView(facesContext, "");
            facesContext.setViewRoot(view);                
        }
        
        BeanFactory.getInstance().getSessionBean().setSelectedApplication(app);
        System.out.println(BeanFactory.getInstance().getSessionBean().getSelectedApplication());
        
        
        app = (app==null?"um":app);
        System.out.println("-----Selected Application---->"+app);
        String separator = ";";
        String loginCredentials = user+separator+pass;
        //encode loginCredentials
        Encryptor enc = new Encryptor();
        
        //application Router
        switch(app){
        /*case WebConstants.APPLICATION_UM:
        	request.login(user, pass);
        	System.out.println("----switching to um---");
        	response.sendRedirect("index.jsp?.="+enc.encrypt(loginCredentials)+"&..="+separator);
        	//response.sendRedirect("j_security_check?j_username="+user+"&j_password="+pass);
        	break;*/
        case WebConstants.APPLICATION_CRM:
        	System.out.println("----switching to crm---");
        	try {
                request.login(user, pass); 
            } catch(ServletException ex) {
            	response.sendRedirect("/um/view/public/SignInForm.jsp?loginerror=true");
                return;
            }
        	Application.getInstance().setApplicationType(app);
        	response.sendRedirect("/crm/index.jsp?.="+enc.encrypt(loginCredentials)+"&..="+separator);
        	break;
        case WebConstants.APPLICATION_BI:
        	System.out.println("----switching to bi---");
        	try {
                request.login(user, pass); 
            } catch(ServletException ex) {
            	response.sendRedirect("/um/view/public/SignInForm.jsp?loginerror=true");
                return;
            }
        	Application.getInstance().setApplicationType(app);
        	response.sendRedirect("/bi/index.jsp?.="+enc.encrypt(loginCredentials)+"&..="+separator);
        	break;
            //RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/crm/j_security_check?j_username="+user+"&j_password="+pass);
            //dispatcher.forward(request, response);
        
        }
        
	}

}
