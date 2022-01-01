package topologyTest;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import topologyAPI.Device;
import topologyAPI.Topology;
import topologyAPI.TopologyAPI;

public class QueryDevicesWithNelistTest {

	
	String file1="Topology.json";
	String file2= "Topology2.json";
	@Test
	public void testQueryDevicewithNetlistID() throws Exception
	{
		TopologyAPI topologyAPI = new TopologyAPI();
		
		topologyAPI.readData(file1);

		ArrayList<Device> DeviceList=topologyAPI.QueryDevices("top1");
		
		//assert that there are only 2 devices on topology top1
		Assert.assertEquals(2, DeviceList.size());

		DeviceList=topologyAPI.queryDevicesWithNetlistNode("top1","nmos");
		//assert that there are only 1 devices connected to nmos Netlist
		Assert.assertEquals(1, DeviceList.size());
		
		topologyAPI.readData(file2);
		
		DeviceList=topologyAPI.QueryDevices("top2");
		//assert that there are only 4 devices on topology top2
		Assert.assertEquals(4, DeviceList.size());
		
		
		DeviceList=topologyAPI.queryDevicesWithNetlistNode("top2","nmos");
		//assert that there are only 2 devices connected to nmos Netlist
		Assert.assertEquals(2, DeviceList.size());
		
		
		// try to query an invalid NodelistID
		DeviceList=topologyAPI.queryDevicesWithNetlistNode("top2","WrongID");
		//assert that there are no devices found
		Assert.assertEquals(0, DeviceList.size());
		
		try {
			// try to query a toplogy not in memory
			DeviceList=topologyAPI.queryDevicesWithNetlistNode("top3","nmos");

		}
		catch(Exception e)
		{
			Assert.assertEquals("The given topologyId is not memory", e.getMessage());
		}
		
	}
}
