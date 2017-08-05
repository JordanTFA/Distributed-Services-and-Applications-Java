import java.io.*;
import rice.environment.Environment;
import rice.p2p.commonapi.Id;
import rice.pastry.NodeHandle;
import rice.pastry.NodeIdFactory;
import rice.pastry.PastryNode;
import rice.pastry.PastryNodeFactory;
import rice.pastry.direct.*;
import rice.pastry.standard.RandomNodeIdFactory;

public class TestMain {
	TestApp [] apps;
	Id zoneId;

	public TestMain(Environment env, int numNodes, String fileName) throws Exception {
		// Generate NodeIds Randomly
		NodeIdFactory nidFactory = new RandomNodeIdFactory(env);
		// construct the PastryNodeFactory
		PastryNodeFactory factory = new DirectPastryNodeFactory(nidFactory, new GenericNetwork(env, new File(fileName)), env);
		// create the NodeHandle to boot off of
		NodeHandle bootHandle = null;

		// construct the nodes and apps
		apps = new TestApp [numNodes];
		zoneId = nidFactory.generateNodeId();
    
		for (int curNode = 0; curNode < numNodes; curNode++) {
			// passing the null boothandle will cause the first node to start its own Pastry ring
			PastryNode node = factory.newNode(bootHandle);
			bootHandle = node.getLocalHandle();

			// a node may need to send several messages to fully boot into the ring
			synchronized (node) {
				while (!node.isReady() && !node.joinFailed()) {
					node.wait(500);
					// abort if can't join
					if (node.joinFailed()) {
						throw new IOException("Could not join the FreePastry ring.  Reason:" + node.joinFailedReason());
					}
				}
			}

			System.out.println("Finished creating new node " + node);

			// construct a new app
			TestApp app = new TestApp(node, curNode, zoneId);
			apps [curNode] = app;      
			app.subscribe();
		}
		
		env.getTimeSource().sleep(3000);
		
		/**
		 * START OF TESTING CODES
		 */


		
		/**
		 * END OF TESTING CODES
		 */
    
		// wait for 30 seconds to make sure that all the messages have been sent/received
		env.getTimeSource().sleep(30000);
		System.out.println('\n'+"Finishing the test...");
		env.destroy();
		System.out.println("Done!");
	}

    public static void main(String[] args) throws Exception {
    	Environment env = new Environment();
    	//the number of nodes
    	int numNodes = 10;
    	String fileName = "GNPINPUT";
    	TestMain test = new TestMain(env, numNodes, fileName);
    }
}