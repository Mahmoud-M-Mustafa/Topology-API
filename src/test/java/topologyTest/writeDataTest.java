package topologyTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import topologyAPI.Topology;
import topologyAPI.TopologyAPI;


public class writeDataTest {

		String file1 = "Topology.json";
		
		@Test
		public void testwrite() throws Exception
		{
			TopologyAPI topologyAPI = new TopologyAPI();
			
			// assert that there are no Topologies in memory
			Assert.assertEquals(topologyAPI.topologies.size(), 0);

			Topology topology = topologyAPI.readData(file1);
			
			//assert that there is one topology in memory read from file 1
			Assert.assertEquals(topologyAPI.topologies.size(), 1);

			String filename = topologyAPI.WriteData("top1");
			
			// assert that the TopologyId is in memory
			Assert.assertNotNull(filename);
			Topology topology_new = topologyAPI.readData(filename);
			Assert.assertEquals(topology.getComponents().size(), topology_new.getComponents().size());
	
		}
		
		@Test
		public void testwrite2() throws Exception
		{
			TopologyAPI topologyAPI = new TopologyAPI();
			
			// assert that there are no Topologies in memory
			Assert.assertEquals(topologyAPI.topologies.size(), 0);

			Topology topology = topologyAPI.readData(file1);
			
			//assert that there is one topology in memory read from file 1
			Assert.assertEquals(topologyAPI.topologies.size(), 1);
			String filename=null;
			try {
				filename = topologyAPI.WriteData("top2");
			}
			catch (Exception e)
			{
				Assert.assertEquals("TopologyID not found", e.getMessage());
			}
			System.out.print(filename);
			// assert that the TopologyId is not in memory
			Assert.assertNull(filename);
		
		}
}
