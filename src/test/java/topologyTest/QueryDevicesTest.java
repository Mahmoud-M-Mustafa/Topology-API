package topologyTest;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import topologyAPI.Device;
import topologyAPI.Topology;
import topologyAPI.TopologyAPI;

public class QueryDevicesTest {

	String file1="Topology.json";
	String file2= "Topology2.json";
	@Test
	public void testQueryDevice() throws Exception
	{
		TopologyAPI topologyAPI = new TopologyAPI();
		
		topologyAPI.readData(file1);

		ArrayList<Device> DeviceList=topologyAPI.QueryDevices("top1");
		
		//assert that there are only 2 devices on topology top1
		Assert.assertEquals(2, DeviceList.size());

		topologyAPI.readData(file2);
		
		DeviceList=topologyAPI.QueryDevices("top2");
		
		//assert that there are only 4 devices on topology top2
		Assert.assertEquals(4, DeviceList.size());
		
		try {
			// try to query a toplogy not in memory
			DeviceList=topologyAPI.QueryDevices("top2");

		}
		catch(Exception e)
		{
			Assert.assertEquals("TopologyId given is not in memory", e.getMessage());
		}
		
	}
	
}
