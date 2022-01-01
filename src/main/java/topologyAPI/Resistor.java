package topologyAPI;

public class Resistor extends Device{
	
	private Resistance  resistance ;
	private Netlist netlist;
	
	public Resistor()
	{
		super();
		this.setType("resistor");
		this.resistance= new Resistance();
		this.netlist= new Netlist();
		
	}
	
	public Resistor(String id,int res_default,int res_min, int res_max,String net_t1,String net_t2) {
		super(id);
		this.resistance= new Resistance(res_default,res_min,res_max);
		this.netlist = new Netlist(net_t1,net_t2);
	}
	
	
	public double getRes_default() {
		return this.resistance.get_default();
	}

	public double getRes_max() {
		return this.resistance.get_max();
	}

	public double getRes_min() {
		return this.resistance.get_min();
	}


	public void setRes(double res_default, double res_min,double res_max) {
		this.resistance.set_default(res_default);
		this.resistance.set_max(res_max);
		this.resistance.set_min(res_min);
	}


	public String getNet_t1() {
		return this.netlist.t1;
	}

	public String getNet_t2() {
		return this.netlist.t2;
	}


	public void setNet(String t1,String t2) {
		this.netlist.t1=t1;
		this.netlist.t2=t2;
	
	}


	private class Netlist {
		
		private String t1;
		private String t2;
		
		private Netlist() {
			
		}
		private Netlist(String t1,String t2) {
			this.t1 = t1;
			this.t2 = t2;
		}
	}
}


