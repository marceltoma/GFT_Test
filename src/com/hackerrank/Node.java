package com.hackerrank;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Node {

	private int value;
	private Node parent;
	private List<Node> children;
	
	public Node(int value) {
		this.value = value;
		parent = null;
		children = new ArrayList<Node>();
	}
	
	public int getValue() { 
		return value; 
	}
	
	public List<Node> getChildren() {
		return children;
	}

	public void addChild(Node child) {
		getChildren().add(child);
		child.parent = this;
	}

	public Node getParent() {
		return parent;
	}

	public int numParents() {
		int p = 0;
		Node n = this;
		while (n.getParent() != null) {
			p++;
			n = n.parent;
		}
		return p;
	}
	
	public int pathTo(Node b) {
		Node major;
		Node minor;
		if (value > b.value) {
			major = this;
			minor = b;
		} else {
			major = b;
			minor = this;
		}
		int numNodes = 2;
		Set<Integer> parents = new HashSet<Integer>();
		parents.add(major.value);
		
		while (major.parent != null) {
			if (major.parent.value == minor.value) return numNodes;
			parents.add(major.parent.value);
			numNodes++;
			major = major.parent;
		}
		
		boolean foundCommonNode = false;
		while (minor.parent != null) {
			if (parents.contains(minor.parent.value)) {
				if (!foundCommonNode) {
					foundCommonNode = true;
				} else {
					numNodes--;
				}
			} else {
				numNodes++;
			}
			minor = minor.parent;
		}
		return numNodes;
		
	}


	// TODO remove child?

}
