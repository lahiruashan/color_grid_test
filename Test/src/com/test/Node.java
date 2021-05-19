package com.test;

public class Node {

	private String id;
	private Node leftNode;
	private Node rightNode;
	private Node topNode;
	private Node bottomNode;
	private String color;
	private int row;
	private int column;

	public Node() {
	}

	public Node(String id, Node leftNode, Node rightNode, Node topNode, Node bottomNode, String color, int row,
			int column) {
		super();
		this.id = id;
		this.leftNode = leftNode;
		this.rightNode = rightNode;
		this.topNode = topNode;
		this.bottomNode = bottomNode;
		this.color = color;
		this.row = row;
		this.column = column;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Node getLeftNode() {
		return leftNode;
	}

	public void setLeftNode(Node leftNode) {
		this.leftNode = leftNode;
	}

	public Node getRightNode() {
		return rightNode;
	}

	public void setRightNode(Node rightNode) {
		this.rightNode = rightNode;
	}

	public Node getTopNode() {
		return topNode;
	}

	public void setTopNode(Node topNode) {
		this.topNode = topNode;
	}

	public Node getBottomNode() {
		return bottomNode;
	}

	public void setBottomNode(Node bottomNode) {
		this.bottomNode = bottomNode;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

}
