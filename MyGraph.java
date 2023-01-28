import java.util.LinkedList;
import java.util.ArrayList;

public class MyGraph {
	static LinkedList<LinkedList<Integer>> adjacencyList = new LinkedList<LinkedList<Integer>>();
	static ArrayList<Integer> bfsnodes = new ArrayList<Integer>();
	static ArrayList<Integer> dfsnodes = new ArrayList<Integer>();
	
	static void initialize(int[][] adjacencyMatrix) {
		for (int i = 0; i < adjacencyMatrix.length; i++) {
			adjacencyList.add(new LinkedList<Integer>());
			for (int j = 0; j < adjacencyMatrix[i].length; j++) {
				if (adjacencyMatrix[i][j] == 1) {
					(adjacencyList.get(i)).add(j);
				}
			}
			bfsnodes.add(i);
			dfsnodes.add(i);
		}
	}
	
	static String DFS(int id) {
		String answer = "";
		LinkedList<Integer> adjacentnodes = adjacencyList.get(id);
		if (adjacentnodes.size() == 0) {
			if (dfsnodes.contains(id)) {
				answer += id + " ";
				dfsnodes.set(id, -1);
				return answer;
			}
		}
		else {
			if (dfsnodes.contains(id)) {
				answer += id + " ";
				dfsnodes.set(id, -1);
			}
			for (int i = 0; i < adjacentnodes.size(); i++) {
				if (dfsnodes.contains(adjacentnodes.get(i))) {
				answer += DFS(adjacentnodes.get(i));
				}
			}
		}
		return answer;
	}
	
	static String BFS(int id) {
		String answer = "";
		LinkedList<Integer> adjacentnodes = adjacencyList.get(id);
		if (bfsnodes.contains(id)) {
			answer += id + " ";
			bfsnodes.set(id,-1);
		}
		for (int i = 0; i < adjacentnodes.size(); i++) {
			if (bfsnodes.contains(adjacentnodes.get(i))) {
				answer += adjacentnodes.get(i) + " ";
			}
			
		}
		for (int i = 0; i < adjacentnodes.size(); i++) {
			if ((adjacencyList.get(adjacentnodes.get(i))).size() > 0) {
				if (bfsnodes.contains(adjacentnodes.get(i))) {
					bfsnodes.set(adjacentnodes.get(i), -1);
					answer += BFS(adjacentnodes.get(i));
				}
			}
		}
		return answer;
	}
	
	static int outDegree(int id) {
		return (adjacencyList.get(id)).size();
	}
	
	static int hops(int id1, int id2) {
		int hops = -1;
		int count = 0;
		LinkedList<Integer> adjacentnodes = adjacencyList.get(id1);
		if (id1 == id2) {
			hops++;
			return hops;
		}
		else if (adjacentnodes.contains(id2)) {
			hops += 2;
			return hops;
		}
		else if (adjacentnodes.size() == 0) {
			return hops;
		}
		else {
			hops += 2;
			for (int i=0; i < adjacentnodes.size(); i++) {
				if (hops(adjacentnodes.get(i), id2) >= 0) {
					hops += hops(adjacentnodes.get(i), id2);
				}
				else {
					count++;
				}
			}
		}
		if (count < adjacentnodes.size()) {
			return hops;
		}
		else {
			return -1;
		}
	}
	
	
	public static void main(String[] args) {
		int[][] adjacencyMatrix = {{0,0,1,0,0,1,0,0,0},{0,0,0,0,0,0,1,0,0},{0,0,0,0,0,0,1,0,0},{0,0,0,0,1,0,0,0,0},{0,0,0,0,0,1,0,0,0},{0,0,0,1,0,0,0,1,0},{0,0,0,0,0,0,0,0,0},{0,0,1,0,0,0,0,0,1},{0,0,0,0,0,0,0,0,0}};
		
		initialize(adjacencyMatrix);
		
		
		/* System.out.println(adjacencyMatrix.length);
		
		System.out.println(bfsnodes);
		
		for (int i=0; i<adjacencyList.size(); i++) {
			System.out.println(adjacencyList.get(i));
		} */
		
		System.out.println(DFS(0));
		System.out.println(BFS(0));
		System.out.println(outDegree(0));
		System.out.println(hops(0,4));
		

		

	}

}
