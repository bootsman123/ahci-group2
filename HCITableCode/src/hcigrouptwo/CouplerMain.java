package hcigrouptwo;
import TUIO.TuioClient;


public class CouplerMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TableListener app = new TableListener();
		TuioClient client = new TuioClient();
		client.addTuioListener(app);
		client.connect();
	}

}
