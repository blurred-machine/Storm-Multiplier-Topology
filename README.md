## Storm-Multiplier-Topology
This repository consists of a simple Apache Storm Topology DAG with a single Spout and a Single Bolt.
![Storm Topology](https://storm.apache.org/images/storm-flow.png)

### Spout:
* Producing integers as stream data from 0 to 100 called as `IntegerSpout`

### Bolt:
* Processes the shuffeled grouping based input data from the `IntegerSpout` with a multiplication of 2 called as `MultiplyBolt`

### Topology:
* A directed acyclic graph(DAG) consisting of a single spout source of streaming data and a single bolt for proecessing the data, called as `MainTopology`

