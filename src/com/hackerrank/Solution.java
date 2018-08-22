package com.hackerrank;

import java.io.*;
import java.lang.*;

public class Solution {
	public static void main(String[] args) {
		
		File fileName = new File("banks.txt");
		if (fileName.exists()) {
			try {
				BufferedReader br = new BufferedReader(new FileReader(fileName));
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
						Node parent = searchNode(root, new Node(values[0]));
						parent.addChild(new Node(values[1]));
					}
					System.out.println(values[0] + " - " + values[1]);
					
				}
				printNodes(root, "");
				br.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private static void printNodes(Node root, String step) {
		System.out.println((root.getParent()==null?"":"|")+step+root.getValue());
		for (Node childNode : root.getChildren()) {
			printNodes(childNode, step+"_");
		}
	}

	private static Node searchNode(Node root, Node node) {
		if (root.getValue() == node.getValue()) return root;
		for (Node childNode : root.getChildren()) {
			Node result = searchNode(childNode, node);
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