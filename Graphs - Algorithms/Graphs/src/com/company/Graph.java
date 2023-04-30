package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.KeyPair;
import java.sql.Array;
import java.util.*;

public class Graph {

    public Map<Integer, Map<Integer, Integer>> graph; /* first int for node number - second map <neighbourNode, weight> */
    final int oo = 1000000000;
    public int V,E;
    public int[][] costsMatrix;

    public Graph(String path){
        graphInitializer(path);
    }

    private void graphInitializer(String inputFile){
        try {
            Scanner scanner = new Scanner(new File(inputFile));
            V = scanner.nextInt();
            graph = new HashMap<>(V);
            for(int i=0; i<V; i++){
                Map<Integer, Integer> innerMap  = new HashMap<>();
                graph.put(i, innerMap);
            }

            E = scanner.nextInt();
            costsMatrix= new int[V][V];
            for (int i = 0; i < E; i++) {
                int start = scanner.nextInt();
                int end = scanner.nextInt();
                int weight = scanner.nextInt();
                costsMatrix[start][end] = weight;
                Map<Integer, Integer> innerMap  = graph.get(start);
                innerMap.put(end, weight);
                graph.put(start, innerMap);
            }
            scanner.close();
            // Print the graph
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if(i != j && costsMatrix[i][j] == 0)costsMatrix[i][j] = oo;
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int size(){
        return graph.size();
    }

     public void apply_dijkstra(int source_node, int []costs, int []parent){
        System.out.println("Inside apply dij");
        int vertices = size();
        boolean []visited = new boolean[vertices];
        /* first int in PQ is weight, second is node number */
        PriorityQueue<Pair> priorityQueue = new PriorityQueue<>();

        Arrays.fill(visited, false);
        Arrays.fill(costs, oo);
        Arrays.fill(parent, -1);

        costs[source_node] = 0;
        priorityQueue.offer(new Pair(0, source_node));
        while (!priorityQueue.isEmpty()){
            int node = priorityQueue.peek().second;
            priorityQueue.poll();
            Map<Integer, Integer> neighbours = graph.get(node);
            visited[node] = true;

            for(Map.Entry<Integer, Integer> element : neighbours.entrySet()){
                Integer neighbour_node = element.getKey();
                Integer weight = element.getValue();
                if(!visited[neighbour_node]){
                    if(costs[node] + weight < costs[neighbour_node]){
                        costs[neighbour_node] = costs[node] + weight;
                        parent[neighbour_node] = node;
                    }
                    priorityQueue.offer(new Pair(costs[neighbour_node], neighbour_node));
                }
            }
        }
        System.out.println("finished DI");
        System.out.println(Arrays.toString(costs));
        System.out.println(Arrays.toString(parent));
     }

    public boolean floyed_warshal(int[][] predecessors, int[][] costs){
        for(int i = 0 ; i < predecessors.length ; i++){
            for(int j = 0 ; j < predecessors.length ; j++){
                for(int k = 0 ; k < costs[0].length ; k++){
                    if(j == i || k == i)costs[j][k] = predecessors[j][k];
                    else{
                        costs[j][k] = Math.min(predecessors[j][k], (predecessors[j][i] + predecessors[i][k]));
                    }
                }
            }
            predecessors = costs;
        }
        System.out.println(Arrays.deepToString(predecessors));
        System.out.println(Arrays.deepToString(costs));
        return true;
    }

    public boolean bfShortestPath(int sourceNode, int[] costs, int[] parents){
        //returns true if shortest paths are defined in the graph,
        //when there are no negative cycles
        //else returns false
        //updates costs, and parents data which are given as parameters

        //costs --> cost of the shortest path between source node and all other nodes
        //parents --> the parent of target node in the shortest path from source to target

        //number of nodes
        int n = costs.length;

        boolean shortestPathExists = true;
        //loop |E|-1 times
        for(int i = 0; i < this.E-1; i++){
            //for each node
            for(int j = 0; j < n; j++){
                final int node = j;
                //for each neighbor in the current node
                graph.get(node).forEach((neighbor, weight)->{
                    int newCost = costs[node]+weight;
                    //if newCost is cheaper than known cost, update
                    if(newCost < costs[neighbor]){
                        costs[neighbor] = newCost;
                        parents[neighbor] = node;
                    }
                });
            }
        }
        System.out.println(Arrays.toString(costs));
        //last iteration to check for negative loops
        //for each node
        for(int j = 0; j < n; j++){
            final int node = j;

            for(Map.Entry pair: graph.get(node).entrySet()){
                int neighbor = (int)pair.getKey();
                int weight = (int)pair.getValue();
                int newCost = costs[node]+weight;
                if(newCost < costs[neighbor]){
                    shortestPathExists = false;
                    break;
                }
            }
            if(!shortestPathExists){break;}
        }
        return shortestPathExists;
    }

}

