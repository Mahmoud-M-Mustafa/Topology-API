package topologyAPI;

import java.util.ArrayList;

public class Topology {
	private String id;
	private ArrayList<Device> components;
	
		Topology(String id){
			this.setId(id);
			this.components = new ArrayList<Device>();
		}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ArrayList<Device> getComponents() {
		return components;
	}

	public void setComponents(ArrayList<Device> components) {
		this.components = components;
	}
	
	public void addComponent(Device d)
	{
		this.components.add(d);
	}

}
