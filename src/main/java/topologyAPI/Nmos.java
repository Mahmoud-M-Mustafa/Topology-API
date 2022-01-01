package topologyAPI;


public class Nmos extends Device{
	
	private Resistance  ml ;
	private Netlist netlist;
	
	public Nmos() {
		super();
		this.setType("nmos");
		this.ml = new Resistance();
		this.netlist= new Netlist();
	}
	
	public Nmos(String id,int ml_default,int ml_min, int ml_max,String net_drain,String net_gate, String net_source) {
		super(id);
		this.ml= new Resistance(ml_default,ml_min,ml_max);
		this.netlist= new Netlist(net_drain,net_gate,net_source);
		
	}
	
	
	public double getRes_default() {
		return this.ml.get_default();
	}

	public double getRes_max() {
		return this.ml.get_max();
	}

	public double getRes_min() {
		return this.ml.get_min();
	}


	public void setRes(double res_default, double res_min,double res_max) {
		this.ml.set_default(res_default);
		this.ml.set_max(res_max);
		this.ml.set_min(res_min);
	}


	public String getNet_drain() {
		return this.netlist.drain;
	}

	public String getNet_gate() {
		return this.netlist.gate;
	}

	public String getNet_source() {
		return this.netlist.source;
	}

	public void setNet(String drain,String gate,String source) {
		this.netlist.drain=drain;
		this.netlist.gate=gate;
		this.netlist.source=source;

	}
	
	
	
	
	private class Netlist {
		
		private String drain;
		private String gate;
		private String source;
		
		private Netlist()
		{
			
		}
		private Netlist(String drain,String gate,String source) {
			this.drain = drain;
			this.gate = gate;
			this.source = source;
		}
	}
	
	
}
