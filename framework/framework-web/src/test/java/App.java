import java.util.concurrent.TimeUnit;

/**
 * Copyright (c) 2006-2015 Hzins Ltd. All Rights Reserved. 
 *  
 * This code is the confidential and proprietary information of   
 * Hzins. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with Hzins,http://www.hzins.com.
 *  
 */
/**
 * <p>
 * 
 *
 *
 * </p>
 * @author	hz15101769 
 * @date	2015年11月24日 下午5:53:10
 * @version      
 */
public class App {

    public static void main(String[] args) {
	Thread t=new ShutHook();
	Runtime.getRuntime().addShutdownHook(t);
	System.out.println("end");
	
	System.exit(0);
	System.exit(1);
	
    }
    
    static class ShutHook extends Thread{
	int i=0;
	@Override
	public void run() {
	   while(true){
	       i++;
	       System.out.println("i : "+i);
	       try {
		TimeUnit.SECONDS.sleep(2);
	    } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	       
	   }
	}
    }
}
 