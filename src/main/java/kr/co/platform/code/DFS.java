package kr.co.platform.code;

import java.util.Iterator;
import java.util.LinkedList;

public class DFS {
	
	private int V; // 노드의 개수
	private LinkedList<Integer>[] dfsGraph; // dfs 그래프
	
	DFS(int n) {
		V = n;
		dfsGraph = new LinkedList[n];
		
		for (int i=0; i<n; i++) {
			dfsGraph[i] = new LinkedList<>();
		}
	}
	
	/**
	 * @설명 : 노드를 연결해준다. v -> w 
	 * @param v
	 * @param w
	 */
	void addEdge(int v, int w) { 
		dfsGraph[v].add(w); 
	}
	
	/**
	 * @설명 : s를 시작 노드로 한 DFS를 탐색하고 방문노드를 순서대로 출력
	 * @param s
	 * @param visited
	 */
	void search(int s, boolean visited[]) {
		
		// 현재 노드를 방문한 것으로 표시하고 값 출력
		visited[s] = true;
		System.out.print(s + " ");
		
		// 방문한 노드와 인접한 모든 노드를 가져온다.
		Iterator<Integer> i = dfsGraph[s].listIterator();
		while (i.hasNext()) {
			int n = i.next();
			// 방문하지 않은 노드라면 해당 노드를 시작 노드로 다시 함수 호출(재귀함수)
			if (!visited[n]) {
				search(n, visited);				
			}
		}
		
	}
	
	void DFSUtil(int s) {

		// 노드의 방문여부 판단 (초기값 : false)
		boolean visited[] = new boolean[5];
		
		// v를 시작 노드로 search 순환 호출
		search(s, visited);
	}
	
	/**
	 * 검색 실행 main 함수
	 */
	public static void main(String args[]) {
		
		
		DFS dfs = new DFS(5);
		
		
		dfs.addEdge(0, 1);
		dfs.addEdge(4, 2);
		dfs.addEdge(1, 4);
		dfs.addEdge(2, 0);
		dfs.addEdge(2, 3);
		dfs.addEdge(3, 4);
		
		dfs.DFSUtil(0); // 주어진 노드를 시작 노드로 BFS 탐색 시작
		
	}
}
