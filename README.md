# EX2_

* Written by Shoham Cohen and Yehonatan Baruchson.
## GeoLocation
Object that stores 3 values:
* double x
* double y
* double z
### Methods
* distance: return the distance between the current Geolocation to the inputted one.
## EdgeData
EdgeData is an object that stores 3 values:
* int src
* int des
* double w

### Methods
* getSrc: return the id of the source NodeData of the edge.
* getDes: return the id of the destination NodeData of the edge.
* getWeight: return the weight of the edge.

## NodeData

Nodedata is an object that stores 3 values:
* nodedata ID.
* nodedata Geolocation.
* nodedata hash map of all the edges that related to this node.

### Methods
* GetKey: returns the ID of the Node
* GetLocation: returns the Geolocation of the Node
* SetLocation: changes the Geolocation of thr Node to the inputted Geolocation

## DirectedWeightedGraph

DirectedWeightedGraph is an object that stores 3 values:
* Hash map of NodeDatas.
* int NumOfEdges: number of the edges in the graph.
* int MC: parameter that helps us to track changes in the graph.

### Methods
* GetNode: return the NodeData by the inputted key.
* GetEdge: return the EdgeData by the inputted src and des.
* addNode: adding a NodeData to the graph.
* connect: conneect between the src NodeDatas to the des NodeData with an EdgeData.
* NodeIter: returns Iterator through all the Nodes in the graph.
* EdgeIter: returns Iterator through all the Edges in the graph.
* edgeIter: returns Iterator through all the Edges that related to the inputted Node.
* removeNode: delete the Node and his all Edges from the graph.
* removeEdge: delete the specific inputted Edge.
* nodeSize: returns the number of nodes on the graph.
* edgeSize: returns the number of Edges in the graph.

## DirectedWeightedGraphAlgorithms

DirectedWeightedGraphAlgorithms is an object who stores only one value:
* DirectedWeightedGraph - graph.

### Methods
* init: stores new graph to the class.
* copy: coping the graph and returns the copy.
* isConnected: checks if the graph connected. A random node is being chosen, 
we check if there is a path between the picked node to every other node in the graph
if so, we check again but now every edge is reversed. If there is again path to every node
the graph is connected, otherwise it's not.
* shortestPathDist: simply dijkstra's algorithm, at the end we return the value that stores in the distance array at the des place.
* shortestPath: again we will do dijkstra's algorithm, but now we will follow the values that stores in the prev array, we will start from prev[des] and see what value there is.
we will continue doing that while also we store every value in our way until we get to the prev[src].
* center: checks the node that has the smallest largest distance to other node in the graph.
we will again do the dijkstra's algorithm but now we will do it to every node. for each node we will take the biggest value in his distance array,
at the end the node with the smallest chosen value is the center node.
* tsp: returns the shortest path that go through all the inputted nodes, for each node in the list we will find the shortest path to the node the comes after him and we will close a circle.
* save: saves the graph to a new json file.
* load: load a new graph from a json file.

## NOTE

to run the jar file write in the command line: java -jar Graph_Ex2.jar filename.json

