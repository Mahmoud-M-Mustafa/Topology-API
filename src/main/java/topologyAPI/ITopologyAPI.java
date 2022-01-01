package topologyAPI;

import java.util.ArrayList;

public interface ITopologyAPI {

	
	/*
	 * Read a topology from a given JSON file and store it in the memory
	 * 
	 * */
	Topology readData(String Filename) throws Exception;
	
	/*
	 * Write a given topology from the memory to a JSON file
	 * */
	String WriteData(String TopologyId) throws Exception;
	
	/*
	 * Query about which topologies are currently in the memory.

	 * */
	ArrayList<Topology> QueryTopology();

	/*
	 * Delete a given topology from memory.
	 * */
	ArrayList<Topology> deleteTopology(String TopologyId)throws Exception ;
	
	/*
	 * Query about which devices are in a given topology.
	 * */
	 ArrayList<Device> QueryDevices(String TopologyId) throws Exception ;
	/*
	 * Query about which devices are connected to a given netlist node in 
		a given topology.
	 * */
	 
	 ArrayList<Device> queryDevicesWithNetlistNode(String TopologyId, String NetlistNodeId )throws Exception;
}
