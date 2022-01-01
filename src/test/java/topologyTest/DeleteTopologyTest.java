package topologyTest;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import topologyAPI.Topology;
import topologyAPI.TopologyAPI;
public class DeleteTopologyTest {
	String file1="Topology.json";
	String file2= "Topology2.json";
	@Test
	public void testDeleteTopology() throws Exception
	{
		TopologyAPI topologyAPI = new TopologyAPI();
		
		topologyAPI.readData(file1);
		topologyAPI.readData(file2);
		ArrayList<Topology> TopologyList=topologyAPI.QueryTopology();
		
		//assert that there are currently two topologies in memory
		Assert.assertEquals(2,TopologyList.size());
		
		TopologyList = topologyAPI.deleteTopology("top1");
		// assert that "top1" deleted and there is only top2
		Assert.assertEquals(1,TopologyList.size());
		Assert.assertEquals("top2",TopologyList.get(0).getId());

		TopologyList = topologyAPI.deleteTopology("top2");
		Assert.assertEquals(0,TopologyList.size());

		try {
			// try to delete a toplogy not in memory
			TopologyList = topologyAPI.deleteTopology("top2");

		}
		catch(Exception e)
		{
			Assert.assertEquals("TopologyId given is not in memory", e.getMessage());
		}
		
		
		
		
	}
}
