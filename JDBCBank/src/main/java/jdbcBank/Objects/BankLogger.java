package jdbcBank.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BankLogger {

static Logger logger = LogManager.getLogger(BankLogger.class);
	
	void loggerLevel(String message) { 
		
		if(logger.isDebugEnabled()) {
			logger.debug("This is set to debug: " + message);	
		}
		if(logger.isInfoEnabled()) {
			logger.info("Transaction Info: " + message);
		}
		System.out.println("\n \n \n \n LOGGING SOMETHING.... \n \n \n \n");
	}	
}
