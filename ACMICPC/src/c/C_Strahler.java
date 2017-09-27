package c;
/*
 * urbain
 * 
 * Note: This code only reads in the first data set. The problem requires you to process several 
 * data sets.
 */

// Description:
//
// In geology, a river system can be represented as a directed graph.
// Each river segment is an edge, with the edge pointing the same way
// the water flows. Nodes are either the source of a river segment (for
// example, a lake or spring), where river segments merge or diverge,
// or the mouth of the river.
//
// The Strahler order of a river system is computed as follows.
//
// * The order of each source node is 1.
// * For every other node, let i be the highest order of all its upstream
//   nodes. If just one upstream node has order i, then this node also
//   has order i. If two or more upstream nodes have order i, then this
//   node has order i+1.
//
// The order of the entire river system is the order of the mouth node.
// In this problem, the river system will have just one mouth.
//
// You must write a program to determine the order of a given river system.
//
// The actual river with the highest order is the Amazon, with order 12.
// The highest in the U.S. is the Mississippi, with order 10.
//
// Input:
//
// The first line of input contains a single positive integer N which
// is the number of data sets that follow. Each data set should be
// processed identically and independently.
//
// Each data set consists of:
// * a line containing two positive integers, M and P. M is the number
//   of nodes in the graph, P is the number of edges.
// * P lines, each describing an edge in the graph. The line will
//   contain two positive integers, A and B, indicating that water
//   flows from node A to node B. Note 1 <= A, B <= M.
//
// Node M is the mouth of the river. It has no outgoing edges.
//
// Output:
//
// For each data set, print the data set number, a space, and the order
// of the river system.

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class C_Strahler {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			FileReader reader = new FileReader("data/in/c2.in");
			BufferedReader bufferedReader = new BufferedReader(reader);
			FileWriter writer = new FileWriter("data/out/c2.out");

			String line = bufferedReader.readLine().trim();
			int n=0;
			if( line.length() > 0 ) {
				n = Integer.parseInt(line);
			}

			// K is data set number, M is number of nodes, P is number of edges            
			line = bufferedReader.readLine().trim();
			String [] list = line.split(" ");
			int K = Integer.parseInt(list[0]);
			int M = Integer.parseInt(list[1]);
			int P = Integer.parseInt(list[2]);

			// build river as adjacency list
			List<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();
			for(int i=0; i<=M; i++) { // M nodes, ignore index 0 later
				ArrayList<Integer> nodeList = new ArrayList<Integer>();
				adjList.add(nodeList);
			}
			for(int i=1; i<=P; i++) { // M nodes, ignore index 0 later
				line = bufferedReader.readLine().trim();
				list = line.split(" ");
				int a = Integer.parseInt( list[0] );
				int b = Integer.parseInt( list[1] );
				adjList.get(b).add(a);
			}
			System.out.println("adjList init");
			System.out.println(adjList);

			// order list
			ArrayList<Integer> orderList = new ArrayList<Integer>();
			for(int i=0; i<=M; i++) { // M nodes, ignore index 0 later
				orderList.add(0); // don't know yet
			}

			for(int i=1; i<=M; i++) { // M nodes, ignore index 0 
				if( adjList.get(i).size() == 0 ) // must be source node
					orderList.set(i, 1); // set to 1
			}
			System.out.println("orderList init");
			System.out.println(orderList);

			// continue until we know last node
			boolean dirty = false;
			do {
				dirty = false;
				for( int i=1; i<=M; i++ ) {
					if( adjList.get(i).size() > 0 ) {
						int maxOrder = 0;
						int maxOrderCount = 0;
						List<Integer> nodeList = adjList.get(i);
						for( int j=0; j<nodeList.size(); j++ ) {
							int nodeIndex = nodeList.get(j); 
							int nodeOrder = orderList.get(nodeIndex);
							if( nodeOrder > maxOrder ) {
								maxOrder = nodeOrder;
								maxOrderCount = 1;
							}
							else if( nodeOrder == maxOrder ) {
								maxOrderCount += 1;
							}
						}
						int currentOrderCount = orderList.get(i);
						if( maxOrderCount >= 2) {
							orderList.set(i, maxOrder+1);
						}
						else {
							orderList.set(i, maxOrder);
						}
						if( currentOrderCount != orderList.get(i) ) {
							dirty = true;
						}
					}
					System.out.println("orderList init");
					System.out.println(orderList);
				}
			} while( dirty == true );

			writer.write("" + K + " " + orderList.get(M) + "\n");
			reader.close();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
