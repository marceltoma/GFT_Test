package com.hackerrank;

import java.util.ArrayList;
import java.util.List;

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
		int numParents = 1;
		
		while (major.parent != null) {
			numParents++;
			if (major.parent.value == minor.value) return numParents;
			major = major.parent;
		}
		while (minor.parent != null) {
			numParents++;
			minor = minor.parent;
		}
		return numParents;
		
	}


	// TODO remove child?

}
