package com.srpl.crm.web.model.um.alertsandreminders;

//import org.apache.log4j.Logger;

public class AlertTransmitterService {
//	Logger log = Logger.getLogger(AlertTransmitterService.class);

	public void start() {
		short SOCIAL_WELFARE = 0;
		short REMINDER = 0;
		for(int i=0;i<10;i++){
//			log.debug("-----------------------Transmitting .. "+i);
			transmit(SOCIAL_WELFARE,i);
		
			try{
				Thread.sleep(2000);
			}catch(Exception ex){
			}
		}
		update();
	}

	public boolean transmit(short objectType, int objectId) {
		switch(objectType){
		case 0:
//			log.debug("-----------------------SOCIAL_WELFARE .. "+objectType);
			break;
		}
		return false;
	}

	public void update() {
//		log.debug("-----------------------Updating .. ");

	}
}
