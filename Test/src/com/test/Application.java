package com.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application {

	private static final String[] COLORS = { "R", "B", "G" };

	public static void main(String[] args) {

		int numOfRows = 5;
		int numOfColumns = 5;

		Node[][] colorGrid = createColorGrid(numOfRows, numOfColumns);
		Node[][] nodes = new Node[numOfRows][numOfColumns];

		printNodes(colorGrid);

		for (int r = 0; r < colorGrid.length; r++) {

			for (int c = 0; c < colorGrid[r].length; c++) {
				Node node = colorGrid[r][c];

				Node leftNode = (c - 1 >= 0) ? colorGrid[r][(c - 1)] : null;
				Node rightNode = (c + 1 != numOfColumns) ? colorGrid[r][(c + 1)] : null;
				Node topNode = (r - 1 >= 0) ? colorGrid[(r - 1)][c] : null;
				Node bottomNode = (r + 1 != numOfRows) ? colorGrid[(r + 1)][c] : null;

				node.setLeftNode(leftNode);
				node.setRightNode(rightNode);
				node.setTopNode(topNode);
				node.setBottomNode(bottomNode);

				nodes[r][c] = node;
			}
		}

		eliminateIsolatedNodes(nodes);

		Map<Integer, List<Map<String, String>>> summary = new HashMap<Integer, List<Map<String, String>>>();

		for (int r = 0; r < nodes.length; r++) {

			int start = 0;
			List<Map<String, String>> temp = new ArrayList<Map<String, String>>();

			for (int c = 0; c < nodes[r].length; c++) {
				Node n = nodes[r][c];

				if (n != null) {
					String color = n.getColor();

					if (n.getRightNode() != null) {

						if (!color.equals(n.getRightNode().getColor())) {
							Map<String, String> m = new HashMap<String, String>();
							m.put(color, start + "-" + c + "-" + ((c - start) + 1));

							temp.add(m);

							start = c + 1;
							color = n.getRightNode().getColor();
						}
					} else {
						Map<String, String> m = new HashMap<String, String>();
						m.put(color, start + "-" + c + "-" + ((c - start) + 1));

						temp.add(m);

						start = c + 1;
					}
				} else {
					start = c + 1;
				}
			}
			summary.put(r, temp);
		}

		int max = 0;
		String color = "";

		for (int r = 0; r < nodes.length; r++) {
			List<Map<String, String>> list = summary.get(r);

			for (Map<String, String> map : list) {

				for (String key : map.keySet()) {

					String[] mm = map.get(key).split("-");
					int start = Integer.parseInt(mm[0]);
					int end = Integer.parseInt(mm[1]);
					int count = Integer.parseInt(mm[2]);

					int mx = count + getMax(start, end, r, summary, nodes, key);

					if (max < mx) {
						max = mx;
						color = key;
					}

				}
			}
		}

		System.out.println("Max = " + max + ", Color = " + color);

	}

	public static int getMax(int start, int end, int rw, Map<Integer, List<Map<String, String>>> summary, Node[][] nodes, String color) {
		int count = 0;

		outer:
		for (int r = 0; r < nodes.length; r++) {

			if (r > rw) {
				List<Map<String, String>> list = summary.get(r);
				
				for(Map<String, String> map: list) {
					
					for(String colorKey: map.keySet()) {
						
						String [] arr = map.get(colorKey).split("-");
						int s = Integer.parseInt(arr[0]);
						int e = Integer.parseInt(arr[1]);
						int c = Integer.parseInt(arr[2]);
						
						
						
						if(colorKey.equals(color) && ((start <= s && s <= end) || (s <= start && start <= e))) {
							count += c;
						}else {
							break outer;
						}		
					}
				}
			}
		}

		return count;
	}

	public static void printNodes(Node[][] nodes) {

		for (int r = 0; r < nodes.length; r++) {

			for (int c = 0; c < nodes[r].length; c++) {
				Node n = nodes[r][c];

				if (n != null) {
					System.out.print(n.getColor());
				} else {
					System.out.print("*");
				}
			}

			System.out.println();
		}
	}

	public static Node[][] eliminateIsolatedNodes(Node[][] nodes) {

		for (int r = 0; r < nodes.length; r++) {

			for (int c = 0; c < nodes[r].length; c++) {
				Node n = nodes[r][c];
				String color = n.getColor();

				if ((n.getRightNode() != null && color.equals(n.getRightNode().getColor()))
						|| (n.getLeftNode() != null && color.equals(n.getLeftNode().getColor()))
						|| (n.getTopNode() != null && color.equals(n.getTopNode().getColor()))
						|| (n.getBottomNode() != null && color.equals(n.getBottomNode().getColor()))) {
					continue;
				}

				nodes[r][c] = null;
			}
		}

		return nodes;
	}

	public static Node[][] createColorGrid(int rows, int columns) {

		Node[][] colorGrid = new Node[rows][columns];

		for (int r = rows - 1; r >= 0; r--) {

			for (int c = 0; c < columns; c++) {
				String id = r + "" + c;
				String color = COLORS[(int) (Math.random() * 10) % 3];

				colorGrid[r][c] = new Node(id, null, null, null, null, color, rows, columns);
			}
		}

		return colorGrid;
	}
}
