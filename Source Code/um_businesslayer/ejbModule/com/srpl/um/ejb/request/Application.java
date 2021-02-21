package com.srpl.um.ejb.request;

public class Application {
	private static Application app = new Application();
	private String applicationType;
	

	// Singelton Pattern
	private Application() {
	}

	public static Application getInstance() {
		return app;
	}
	
	public String getApplicationType() {
		return (applicationType!=null?applicationType:"");
	}

	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}

}
