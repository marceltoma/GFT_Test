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


	// TODO remove child?

}
