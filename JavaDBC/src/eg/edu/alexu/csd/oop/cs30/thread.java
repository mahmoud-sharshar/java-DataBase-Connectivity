package eg.edu.alexu.csd.oop.cs30;


	public class thread extends Thread {
		String name;
		Boolean v=true;
		int second; 
	     public thread(int seconds) {
	    	 seconds=second;
	     }
		public void run(){
	    	 try {
	    		 v=true;
	    		 timeElapsed c = new timeElapsed(System.nanoTime());
	    		 while(c.count()<second*1000)
	    		 {
	    			 
	    		 }
    	    	 
	    	}catch (InterruptedException e) {
	    	}
	    	   v=false;
	    	}
	    	}	   
	

