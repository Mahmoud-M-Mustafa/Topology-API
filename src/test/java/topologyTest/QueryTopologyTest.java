package topologyTest;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import topologyAPI.Topology;
import topologyAPI.TopologyAPI;

public class QueryTopologyTest {

	String file1="Topology.json";
	String file2= "Topology2.json";
	@Test
	public void testQueryTopology() throws Exception
	{
		TopologyAPI topologyAPI = new TopologyAPI();
		
		topologyAPI.readData(file1);
		
		ArrayList<Topology> TopologyList=topologyAPI.QueryTopology();
		
	
		
		// assert that the first element in the list has a "top1" id
		Assert.assertEquals("top1",TopologyList.get(0).getId());
		
		topologyAPI.readData(file2);
		TopologyList=topologyAPI.QueryTopology();
		
		
		// assert that the second element in the list has a "top2" id
		Assert.assertEquals("top2",TopologyList.get(1).getId());
		
		
	}
}
