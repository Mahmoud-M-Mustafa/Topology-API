package topologyAPI;

public class Resistance {


	private double deafult;
	private double min;
	private double max;
	
	Resistance()
	{
		
	}
	Resistance(int deafult,int min,int max){
		
		this.set_default(deafult);
		this.set_max(min);
		this.set_max(max);
	}

	public double get_default() {
		return deafult;
	}

	public void set_default(double deafult) {
		this.deafult = deafult;
	}

	public double get_min() {
		return min;
	}

	public void set_min(double _min) {
		this.min = min;
	}

	public double get_max() {
		return max;
	}

	public void set_max(double _max) {
		this.max = max;
	}
	
}
