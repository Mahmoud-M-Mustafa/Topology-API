# Topology API

a project Implemented in Java using maven that provide the functionality to access, manage and store device topologies such as: 

    1. Read a topology from a given JSON file and store it in the memory.
    2. Write a given topology from the memory to a JSON file.
    3. Query about which topologies are currently in the memory.
    4. Delete a given topology from memory.
    5. Query about which devices are in a given topology.
    6. Query about which devices are connected to a given netlist node in a given topology

## Design Assumptions

*   There is one Topology per json file.
*   Each generated Topology will be written in a separate file
*   Components assumed to be nmos and resistor only as the given components have different keys in the json file

