package topologyAPI;

 public abstract  class Device {
	 
	private String type;
	private String id;
	
	Device()
	{
		
	}
	
	Device(String id) {
		this.setId(id);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


}
