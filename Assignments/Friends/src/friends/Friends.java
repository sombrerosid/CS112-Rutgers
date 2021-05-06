package friends;

import java.util.ArrayList;

public class Friends {

   /**
    * Finds the shortest chain of people from p1 to p2.
    * Chain is returned as a sequence of names starting with p1,
    * and ending with p2. Each pair (n1,n2) of consecutive names in
    * the returned chain is an edge in the graph.
    *
    * @param g Graph for which shortest chain is to be found.
    * @param p1 Person with whom the chain originates
    * @param p2 Person at whom the chain terminates
    * @return The shortest chain from p1 to p2. Null or empty array list if there is no
    *         path from p1 to p2
    */
   public static ArrayList<String> shortestChain(Graph g, String p1, String p2) {

      /** COMPLETE THIS METHOD **/
      boolean[] visited=new boolean[g.members.length];
		visited[g.map.get(p1)]=true;
		int[] edgeTo=new int[g.members.length];
		Queue<Integer> list=new Queue<Integer>();
		list.enqueue(g.map.get(p1));
		while(!list.isEmpty()){
			int v=list.dequeue();
			Friend f=g.members[v].first;
			while(f!=null){
				if(!visited[f.fnum]){
					list.enqueue(f.fnum);
					visited[f.fnum]=true;
					edgeTo[f.fnum]=v;
				}
				f=f.next;
			}
		}
		int c=g.map.get(p2);
		if(!visited[c]) return null;
		ArrayList<String> chain=new ArrayList<String>();
		while(c!=g.map.get(p1)){
			chain.add(0, g.members[c].name);
			c=edgeTo[c];
		}
		chain.add(0, g.members[c].name);
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE COMPILER HAPPY
		// CHANGE AS REQUIRED FOR YOUR IMPLEMENTATION
		return chain;
   }

   /**
    * Finds all cliques of students in a given school.
    *
    * Returns an array list of array lists - each constituent array list contains
    * the names of all students in a clique.
    *
    * @param g Graph for which cliques are to be found.
    * @param school Name of school
    * @return Array list of clique array lists. Null or empty array list if there is no student in the
    *         given school
    */
   public static ArrayList<ArrayList<String>> cliques(Graph g, String school) {

      /** COMPLETE THIS METHOD **/
      ArrayList<ArrayList<String>> allCliques=new ArrayList<ArrayList<String>>();
		boolean[] visited=new boolean[g.members.length];
		//for(int i=0; i<visited.length; i++) visited[i]=false;
		for(int i=0; i<visited.length; i++){
			if(visited[i]==false && g.members[i].school.equals(school)){
				ArrayList<String> friends=new ArrayList<String>();
				friends.add(g.members[i].name);
				visited[i]=true;
				addAll(g, school, friends, visited);
				allCliques.add(friends);
			}
		}
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE COMPILER HAPPY
		// CHANGE AS REQUIRED FOR YOUR IMPLEMENTATION
		return allCliques;
   }

   // RECURSIVE HELPER METHOD addAll(), ADDS ALL VIABLE NAMES TO ArrayList<String> friends
	private static void addAll(Graph g, String school, ArrayList<String> friends, boolean[] visited){
		Friend f=g.members[g.map.get(friends.get(friends.size()-1))].first;
		while(f!=null){
			if(!visited[f.fnum] && g.members[f.fnum].school.equals(school)){
				visited[f.fnum]=true;
				friends.add(g.members[f.fnum].name);
				addAll(g, school, friends, visited);
			}
			f=f.next;
		}
	}
   
   /**
    * Finds and returns all connectors in the graph.
    *
    * @param g Graph for which connectors needs to be found.
    * @return Names of all connectors. Null or empty array list if there are no connectors.
    */
   public static ArrayList<String> connectors(Graph g) {

      /** COMPLETE THIS METHOD **/
      ArrayList<Stack<String>> adjacency= new ArrayList<Stack<String>>();
		for(int i=0; i<g.members.length; i++){
			Stack<String> s=new Stack<String>();
			Friend f=g.members[i].first;
			while(f!=null){
				s.push(g.members[f.fnum].name);
			}
			adjacency.add(s);
		}
		ArrayList<String> connect=new ArrayList<String>();
		for(int i=0; i<adjacency.size(); i++){
			String n=adjacency.get(i).pop();
			if(adjacency.get(i).isEmpty() && !connect.contains(n)){
				connect.add(n);
			}
		}
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE COMPILER HAPPY
		// CHANGE AS REQUIRED FOR YOUR IMPLEMENTATION
		return connect;
   }
}

