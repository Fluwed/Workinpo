package Rss;

import Flux.Flux;


public class RssThread implements Runnable {
	private Flux fl;
	Thread t;
	RssThread(Flux flux){
	 t = new Thread ( this, "threadRss");
	 t.start();
	 this.fl=flux;
	}
	
	@Override
	public void run() {
		try {
			fl.Parse();
			fl.Edit();
			fl.GetArticle();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
