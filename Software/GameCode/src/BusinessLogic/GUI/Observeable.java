package BusinessLogic.GUI;

import java.util.ArrayList;
import java.util.List;

public class Observeable {

	private List<IObserver> observers;
	
	public Observeable() {
		observers = new ArrayList<IObserver>();
	}
	
	public void addObserver (IObserver o) {
		observers.add(o);
	}
	
	public void removeObserver (IObserver o) {
		observers.remove(o);
	}
	
	protected void notifyObservers() {
		for (IObserver o : observers) {
			o.update();
		}
	}
}
