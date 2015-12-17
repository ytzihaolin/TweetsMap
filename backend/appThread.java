package backend;

import java.io.IOException;

import javax.servlet.ServletException;





public class  appThread{


	public static class GTweet implements Runnable{

		public synchronized void run(){
			
			try {
				backend.GetTweet.go();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
	}
	
	public static class RHandler implements Runnable{
		public synchronized void run(){
		
			backend.DeletTweet.go();
		}
	}
	
	
	public static void go(){
		
		System.out.println("go");
		try{
			Thread t1 = new Thread(new GTweet());
            t1.start();
			Thread t2 = new Thread(new RHandler());
			t2.start();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
}

