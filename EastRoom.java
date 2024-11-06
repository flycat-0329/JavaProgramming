package test;

public class EastRoom extends Thread {
	public void run() {
    	while(true) {
    		System.out.println("가나다라마바사");
    		try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
	}
}
