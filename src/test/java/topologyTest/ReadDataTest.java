package topologyTest;


import org.junit.Assert;
import org.junit.Test;

import topologyAPI.Nmos;
import topologyAPI.Resistor;
import topologyAPI.Topology;
import topologyAPI.TopologyAPI;



public class ReadDataTest {
		//valid files
		String file1="Topology.json";
		String file2= "Topology2.json";
		
		//invalid file
		String file3= "Topology3.json";
	
	@Test
	public void testReadData()  throws Exception
	{
		
		TopologyAPI topologyAPI = new TopologyAPI();
		// assert that there are no Topologies in memory
		Assert.assertEquals(topologyAPI.topologies.size(), 0);

		Topology topology = topologyAPI.readData(file1);
		
		//assert that there is one topology in memory read from file 1
		Assert.assertEquals(topologyAPI.topologies.size(), 1);
		
		// assert that id of read topology is top1
		Assert.assertEquals(topology.getId(), "top1");
		
		// assert that there are only two components
		Assert.assertEquals(2,topology.getComponents().size());

		// Assertions for the first component
		Assert.assertEquals(topology.getComponents().get(0).getId(), "res1");
		Assert.assertEquals(topology.getComponents().get(0).getType(), "resistor");
		Assert.assertEquals(((Resistor)topology.getComponents().get(0)).getNet_t1(), "vdd");
		
		// Assertions for the second component
		Assert.assertEquals(topology.getComponents().get(1).getId(), "m1");
		Assert.assertEquals(((Nmos)topology.getComponents().get(1)).getNet_source(), "vss");
		Assert.assertEquals( 1.5,((Nmos)topology.getComponents().get(1)).getRes_default(),0.0f);
	}
	
	/*
	 * 
	 * read data from multiple json files (Multiple topologies)*/
	@Test
	public void testReadData2() throws Exception {
		
		TopologyAPI topologyAPI = new TopologyAPI();
		// assert that there are no Topologies in memory
		Assert.assertEquals(topologyAPI.topologies.size(), 0);

		Topology topology = topologyAPI.readData(file1);
		Topology topology2 = topologyAPI.readData(file2);

		//assert that there are two topologies in memory read from file 1 and file2
		Assert.assertEquals(topologyAPI.topologies.size(), 2);
		
		Assert.assertEquals(topologyAPI.topologies.get(0), topology);
		Assert.assertEquals(topologyAPI.topologies.get(1), topology2);
		
		
		/*
		 * assert that there are two component in the first topology 
		 *    and four in the second topology*/
		Assert.assertEquals(2,topologyAPI.topologies.get(0).getComponents().size());
		Assert.assertEquals(4,topologyAPI.topologies.get(1).getComponents().size());
		
		
		
		// Assertions for the first component in topology 1
		Assert.assertEquals(topology.getComponents().get(0).getId(), "res1");
		Assert.assertEquals(topology.getComponents().get(0).getType(), "resistor");
		Assert.assertEquals(((Resistor)topology.getComponents().get(0)).getNet_t1(), "vdd");
		
		// Assertions for the fourth component in topology 2
		Assert.assertEquals(topology2.getComponents().get(3).getId(), "m2");
		Assert.assertEquals(((Nmos)topology2.getComponents().get(3)).getNet_source(), "vss");
//		Assert.assertEquals( 1.2,((Nmos)topology2.getComponents().get(3)).getRes_min(),0.0f);

	}
	
	/*
	 * reading corrupted files different keys that the given structre or invalid json format
	 *  */
	@Test
	public void testReadData3() {
		
		try {
			TopologyAPI topologyAPI = new TopologyAPI();

			Topology topology = topologyAPI.readData(file3);
		}
		
		catch(Exception e)
		{
			Assert.assertEquals("Invalid topology json",e.getMessage());
		}
	}
	
}
