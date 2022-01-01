package topologyAPI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.function.Function;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TopologyAPI implements ITopologyAPI {
	
	// ArrayList of Topology instances
	public ArrayList<Topology> topologies = new ArrayList<Topology>();
	
	// Functional interface that fetches a passed file from Resources
	Function<String,File> fileReader = fname -> {
		
		return new File(getClass().getClassLoader().getResource(fname).getFile());
	};
	
	
	@Override
	public Topology readData(String Filename) throws Exception
	{
		// initialize the topology to be read with null
		Topology t=null;
		
		
		File file =fileReader.apply(Filename);
		JSONParser parser = new JSONParser();
		
		// try reading the JSON file
		try( Reader reader = new FileReader(file)){
			
			// parsing JSON file into JSON object
			Object object = parser.parse(reader);
            JSONObject jsonObject = (JSONObject)object;
            
            // fetching values of the given keys : id, components
            String id_ = (String) jsonObject.get("id");
            JSONArray components = (JSONArray) jsonObject.get("components");
            
            // creating a new Topology with the fetched "id" key value
            t = new Topology(id_);
            
            // add the constructed topology to the topologies list
            topologies.add(t);
            
            
            JSONObject component; // to store the casted component
            for(Object component_tmp:components)
            {	
            	component= ((JSONObject) component_tmp);
               
            	
            	// check the type of component to create an instance of that component in memory
               if( component.get("type").equals("resistor"))
               {
            	   	// creating a resistor instance
            	   Resistor r = new Resistor();
            	   
            	   /* initializing a variables to hold the values of the components 
            	    * that corresponds with the passed keys
            	   */
            	   String id,net_t1,net_t2; double  res_default,res_min,res_max;
            	   
            	   // fetching id value
            	   id= (String)component.get("id");
                   r.setId(id);
                   
                   
                   // fetching resistance sub values
                   JSONObject resistance = (JSONObject) component.get("resistance");
                   res_min = new Double((String) resistance.get("min").toString());
                   res_max = new Double((String) resistance.get("max").toString());
                   res_default = new Double((String) resistance.get("deafult").toString());
                   // storing fetched values in memory
                   r.setRes(res_default, res_min, res_max);
                           
                   // fetching netlist sub values
                   JSONObject netlist = (JSONObject) component.get("netlist");
                   net_t1 = (String) netlist.get("t1");
                   net_t2 = (String) netlist.get("t2");
                   r.setNet(net_t1, net_t2);
                   
                   // adding the created component into that topology instance
                   t.addComponent(r);

               }
               
           	   // check the type of component to create an instance of that component in memory
               else if(component.get("type").equals("nmos"))
               {
           	   	// creating an NMOS instance
            	   Nmos n = new Nmos();
            	   
            	   /* initializing a variables to hold the values of the components 
            	    * that corresponds with the passed keys
            	   */
            	   String id,net_drain,net_gate,net_source;double ml_default,ml_min,ml_max;
            	   
            	// fetching id value
            	   id= (String)component.get("id");
            	   n.setId(id);
            	// fetching resistance (m1) sub values
                   JSONObject ml = (JSONObject) component.get("ml");
                   ml_min = new Double((String) ml.get("min").toString());
                   ml_max = new Double((String) ml.get("max").toString());
                   ml_default = new Double((String) ml.get("deafult").toString());
                   // storing setched values in nmos instance
                   n.setRes(ml_default, ml_min, ml_max);
                           
                   // fetching netlist sub values
                   JSONObject netlist = (JSONObject) component.get("netlist");
                   net_drain = (String) netlist.get("drain");
                   net_gate = (String) netlist.get("gate");
                   net_source = (String) netlist.get("source");
                   n.setNet(net_drain, net_gate,net_source);
                   
                   // adding the created component into that topology instance
                   t.addComponent(n);

               }

            }
            

			
		}catch(Exception e) {
			
			// throw an Exception if the file does not exist or the keys of json file contradicts with the given ones
			throw new Exception("Invalid topology json");
		}
		
		
		return t;
	}
	
	
	@Override
	public String WriteData(String TopologyId) throws Exception
	{
		// filename to store the name and a path to put it in the resources file
		String Filename=null,path=null;
		
		for(Topology  t : topologies )
		{	
			// checks for the given TopologyId
			if(t.getId().equals(TopologyId))
			{
				path="src/test/resources/output_"+TopologyId+".json";
				Filename="output_"+TopologyId+".json";
				//convert Topology instance to json string using GSON library
				String jsonString = (String)( new GsonBuilder().setPrettyPrinting().create().toJson(t));
				// saving the file in the provided Path
				FileOutputStream fos=new FileOutputStream(path, false);  // false for override   
				byte[] b= jsonString.getBytes();       
				fos.write(b);         
				fos.close();           
			}
		}
		if(Filename==null)
			// throw an Exception if the Given TopologyId does not exist
			throw new Exception("TopologyID not found");
		return Filename;
	}
	
	
	
	/*
	 * returns the current topologies in memory
	 * */
	@Override
	public ArrayList<Topology> QueryTopology()
	{	
		
		return topologies;
	}

	
	@Override
	public ArrayList<Topology> deleteTopology(String TopologyId) throws Exception {
		
		int counter =0; // a temp variable to help get the index of the Topology to be deleted 
		int index=-1;  // should contain the index of the topology to be deleted from memory
		for(Topology t:topologies)
		{
			
			if(t.getId().equals(TopologyId)) {
				index=counter;
				break;
			}
			counter++;
		}
		 // checks whether the topologyId exists in memory
		if(index!=-1)
		{
			// removes the Topology from memory
			topologies.remove(index);
		}
		else
		{
			
			throw new Exception("TopologyId given is not in memory");
		}
		
		// return the topologies in memory after the deleted topology
		return topologies;
	}
	
	@Override
	public ArrayList<Device> QueryDevices(String TopologyId) throws Exception {
		
		ArrayList<Device> DeviceList=null; // array list of the devices to be queried 
		
		for(Topology t:topologies)
		{
			
			if(t.getId().equals(TopologyId)) {
				
				// store the devices of the given topology into device list
				DeviceList=t.getComponents();
			}

		}
		
		if(DeviceList==null)
		{
			throw new Exception("TopologyId given is not in memory");
		}
		
		// return the devices of the given topology
		return DeviceList;
	}
	
	
	
	@Override
	public ArrayList<Device> queryDevicesWithNetlistNode(String TopologyId, String NetlistNodeId ) throws Exception
	{
		// create an array list of type Device 
		ArrayList<Device> DeviceList=new ArrayList<Device>() ;
		
		boolean flag = true; //flag to check that the given topologyId exists
		
		for(Topology t:topologies)
		{
			
			if(t.getId().equals(TopologyId)) {
				
				flag=false;
				// iterate over the devices of Topology t
				for(Device d: t.getComponents())
				{
					/*checks that the netlistId is equal to type of device
					 * as there no details about a NetlistnodeID in the input file
					 * nor the Documentation
					 * 
					 * Assuming that each component of the same type is connected to the same id
					*/
					if(d.getType().equals(NetlistNodeId))
							DeviceList.add(d);
				}
			}

		}
		
		if(flag)//checks whether the given TopologyId is in memory or not 
		{
			throw new Exception("The given topologyId is not memory");
		}
		
		
		return DeviceList;
	}
	
}
