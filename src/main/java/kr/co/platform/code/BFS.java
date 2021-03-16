package kr.co.platform.code;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @BFS(너비우선탐색)
 * @설명 : 너비우선탐색이란 ? => 루트 노드(혹은 다른 임의의 노드)에서 시작해서 인접한 노드를 먼저 탐색하는 방법
 * 
 * 	1. 시작 정점으로부터 가까운 정점을 먼저 방문하고 멀리 떨어져 있는 정점을 나중에 방문하는 순회방법
 * 	2. 즉, 깊게(deep) 탐색하기 전에 넓게(wide) 탐색하는 것이다.
 * 	3. 사용하는 경우: 두 노드 사이의 최단 경로 혹은 임의의 경로를 차고 싶을 때 이 방법을 선택한다.
 * 		ex) 지구상에 존재하는 모든 친구 관계를 그래프로 표현 한 후 Ash와 Vanessa 사이의 존재하는 경로를 찾는 경우
 * 			& 깊이 우선 탐색의 경우 모든 친구 관계를 다 살펴봐야 할지도 모른다.
 *  		& 넓이 우선 탐색의 경우 Ash와 가까운 관계부터 탐색
 *  
 *  4. 너비 우선 탐색(BFS)이 깊이 우선 탐색(DFS)보다 좀 더 복잡하다.
 */
public class BFS {
	
	
	private int V; // 노드의 개수
	private LinkedList<Integer>[] bfsGraph; // bfs 그래프
	
	
	BFS(int v) {
		V = v;
		bfsGraph = new LinkedList[v];
		
		for (int i=0; i<v; i++) { // 리스트 초기화
			bfsGraph[i] = new LinkedList<>();
		}
	}
	
	/**
	 * @설명 : 노드를 연결해준다. v -> w 
	 * @param v
	 * @param w
	 */
	void addEdge(int v, int w) { 
		bfsGraph[v].add(w); 
	}
	
	/**
	 * @설명 : s를 시작 노드로 한 BFS를 탐색하고 방문노드를 순서대로 출력
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
		while (!queue.isEmpty()) {
			
			// 방문한 노드를 큐에서 추출(dequeue)하고 값을 출력
			s = queue.poll();
			System.out.print(s + " ");
			
			// 방문한 노드와 인접한 모든 노드를 가져온다.
			Iterator<Integer> i = bfsGraph[s].listIterator();
			
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
	 * 검색 실행 main 함수
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
