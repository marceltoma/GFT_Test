package com.hackerrank;

import java.io.*;
import java.lang.*;

public class Solution {
	public static void main(String[] args) {
		
		File banks = new File("banks.txt");
		File requests = new File("client_requests.txt");
		if (banks.exists() && requests.exists()) {
			try {
				BankTree bankTree = populateTree(banks);
				printNodes(bankTree.getRoot(), "");

				validateRequests(requests, bankTree);
			} catch (IOException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private static void validateRequests(File requests, BankTree btree) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new FileReader(requests));
		String line = br.readLine();
	
		// validate
		if (line.equals("END") || line == null) throw new Exception("file doesn't have at least 1 entry");
		int totalQueries=0;
		
		do {
			//System.out.println(line);
			String[] strArray = line.split(",");
			if (strArray.length != 3) throw new Exception("invalid number of parameters in request "+line);
			int[] intArray = new int[strArray.length];
			for(int i = 0; i < strArray.length; i++) {
			    intArray[i] = Integer.parseInt(strArray[i]);
			}
			if (	intArray[0]<1 || intArray[1]<1 || 
					intArray[0] == intArray[1] ||
					intArray[0]>btree.getTotalNodes() ||
					intArray[1]>btree.getTotalNodes() ||
					intArray[2]<0 ||
					intArray[2]>100) throw new Exception("invalid parameter value");
			
			Node nodeA = getNode(btree.getRoot(), intArray[0]);
			Node nodeB = getNode(btree.getRoot(), intArray[1]);
			//System.out.println(line+" - "+nodeA.pathTo(nodeB) + " - " + (nodeA.pathTo(nodeB)<=intArray[2]));
			System.out.println(nodeA.pathTo(nodeB)<=intArray[2]?"YES":"NO");
			
			if(totalQueries >= (10^4)) throw new Exception("exceeded maximum number of requests");
			totalQueries++;
			line = br.readLine();
			if (line.equals("END")) break;
		} while (line != null);
		br.close();
	}

	private static BankTree populateTree(File banks) throws FileNotFoundException, IOException, Exception {
		BufferedReader br = new BufferedReader(new FileReader(banks));
		int numLines = Integer.parseInt(br.readLine());
		if ( !(2 <= numLines && numLines <= (10^6)) ) {
			System.out.println("N is not a valid number.");
		}
		Node root = null;
		for (int index=0;index<numLines-1;index++) {
			String line = br.readLine();
			int values[] = validateLine(numLines, line);
			if (index==0) {
				root = new Node(values[0]);
				root.addChild(new Node(values[1]));
			} else {
				Node parent = getNode(root, values[0]);
				parent.addChild(new Node(values[1]));
			}
			//System.out.println(values[0] + "-" + values[1]);
		}
		br.close();
		BankTree btree = new BankTree();
		btree.setRoot(root);
		btree.setTotalNodes(numLines);
		return btree;
	}

	private static void printNodes(Node root, String step) {
		System.out.println(step+(root.getParent()==null?"":"|_")+root.getValue());
		for (Node childNode : root.getChildren()) {
			printNodes(childNode, " "+step);
		}
	}

	private static Node getNode(Node root, int node) {
		if (root.getValue() == node) return root;
		for (Node childNode : root.getChildren()) {
			Node result = getNode(childNode, node);
			if (result != null) return result;
		}
		return null;
	}

	private static int[] validateLine(int numLines, String line) throws Exception {
		String values[] = line.split(",");
		int iValues[] = new int[2];
		iValues[0] = Integer.parseInt(values[0]);
		iValues[1] = Integer.parseInt(values[1]);
		
		if (!(1 <= iValues[0] && iValues[0] < iValues[1] && iValues[1] <= numLines)) {
			throw new Exception("Not a valid node pair "+line);
		}
		return iValues;
	}
}