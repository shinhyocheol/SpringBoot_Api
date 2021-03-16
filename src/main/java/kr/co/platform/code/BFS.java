package kr.co.platform.code;

import java.util.Iterator;
import java.util.LinkedList;

public class BFS {
	
	
	private int V; // 노드의 개수
	private LinkedList<Integer>[] adj; // 인접 리스트
	
	
	BFS(int v) {
		V = v;
		adj = new LinkedList[v];
		
		for (int i=0; i<v; i++) { // 리스트 초기화
			adj[i] = new LinkedList<>();
		}
	}
	
	/**
	 * @설명 : 노드를 연결해준다. v -> w 
	 * @param v
	 * @param w
	 */
	void addEdge(int v, int w) { 
		adj[v].add(w); 
	}
	
	/**
	 * @설명 : s를 시작 노드로 한 BFS를 탐색하고 탐색한 노드를 출력해보자.
	 * @param s
	 */
	void search(int s) {
		
		// 해당 순분의 방문여부를 판단한 값이 들어갈 배열
		boolean visited[] = new boolean[V];
		
		// BFS 탐색을 구현하기 위해 큐(Queue) 선언
		LinkedList<Integer> queue = new LinkedList<>();
		
		// s에 해당하는 노드를 방문표시한 것으로 처리하고 해당 노드를 큐에 삽입(enqueue)
		visited[s] = true;
		queue.add(s);
		
		// 큐(Queue)가 안에 아무것도 없을때까지 반복
		while (queue.size() != 0) {
			// 방문한 노드를 큐에서 추출(dequeue)하고 값을 출력
			s = queue.poll();
			System.out.print(s + " ");
			
			// 방문한 노드와 인접한 모든 노드를 가져온다.
			Iterator<Integer> i = adj[s].listIterator();
			while (i.hasNext()) {
				int n = i.next();
				// 방문하지 않은 노드라면 방문한 것으로 표시하고 큐에 삽입(enqueue)
				if (!visited[n]) {
					visited[n] = true;
					queue.add(n);
				}
			}
		}
	}
	
	/**
	 * @BFS 검색 실행 main 함수
	 */
	public static void main(String args[]) {
		
		
		BFS bfs = new BFS(5);
		
		bfs.addEdge(0, 1);
		bfs.addEdge(4, 2);
		bfs.addEdge(1, 4);
		bfs.addEdge(2, 0);
		bfs.addEdge(2, 3);
		bfs.addEdge(3, 4);
		
		bfs.search(0); // 주어진 노드를 시작 노드로 BFS 탐색 시작
		
	}
}
