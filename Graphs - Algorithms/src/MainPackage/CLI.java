package MainPackage;

import java.util.ArrayList;
import java.util.Scanner;

public class CLI {

    Graph graph;

    Scanner scan;
    public void CLI_runner(){
        scan = new Scanner(System.in);
        System.out.println("Enter the path of the file containing the graph structure, please: ");
        String path = scan.nextLine();
        graph = new Graph(path);
        while (true){
            System.out.println("===================================================================================");
            System.out.println("\t\t\t\t\t\tMain menu");
            System.out.println("Please select the desired operation by typing out the number associated to it");
            System.out.println("1 : Find the shortest paths from source node to all other nodes.");
            System.out.println("2 : Find the shortest paths between all the pairs of nodes.");
            System.out.println("3 : Check if the graph contains a negative cycle.");
            System.out.println("4 : Exit.");
            System.out.print("Option: ");
            int option = scan.nextInt();
            System.out.println("===================================================================================");
            if(option == 1){
                System.out.print("Enter the source node please:");
                int src = scan.nextInt();
                System.out.println();
                src = check_validation_of_node(src);
                int algo = specify_algorithm();
                int[][] costs = new int[graph.size()][graph.size()];
                int[][] predessesor = new int[graph.size()][graph.size()];
                String algo_String;
                if(algo == 1){
                    double startTime = System.nanoTime();
                    graph.apply_dijkstra(src,costs[src],predessesor[src]);
                    double elapsedTimeMicros = (System.nanoTime() - startTime) / 1000;
//                    System.out.println("di100_0.05 : " + elapsedTimeMicros);
                    algo_String = "Dijkstra";
                } else if (algo == 2) {
                    graph.bfShortestPath(src,costs[src],predessesor[src]);
                    algo_String = "Bellman-Ford";
                }
                //E:\CSED25\2nd Year\Second term\Data Structures\Labs\Lab 2\Data-Structure2\Graphs - Algorithms\Graphs\src\Test\Test Files\time tests\0.1\graph1000-0.1.txt
                else{
                    double startTime = System.nanoTime();
                    graph.floyed_warshal(predessesor,costs);
                    double elapsedTimeMicros = (System.nanoTime() - startTime) / 1000;
//                    System.out.println("di100_0.05 : " + elapsedTimeMicros);
//                    graph.floyed_warshal(predessesor,costs);
                    algo_String = "Floyed-Warshal";
                }

                while (true){
                    int subOption = print_subMenue(algo_String);
                    if(subOption == 1 || subOption == 2){
                        System.out.print("Enter the destination node please: ");
                        int dest = scan.nextInt();
                        System.out.println();
                        dest = check_validation_of_node(dest);
                        if(subOption == 1){
                            System.out.print("The shortest path cost form node " + src + " to node " + dest + " is: ");
                            if(costs[src][dest] >= 1000000){
                                System.out.println("No path exists");
                            }else{
                                System.out.print(costs[src][dest]);
                            }
                            System.out.println();
                        }else {
                            if(costs[src][dest] >= 1000000){
                                System.out.println("No path exists");
                            }else{
                                System.out.print("The shortest path itself form node " + src + " to node " + dest + " is: ");
                                System.out.print(getPath(predessesor[src],src,dest));
                                System.out.println();
                            }
                        }
                    } else if (subOption == 3) {
                        break;
                    } else{
                        System.out.println("invalid option please reselct the desired operation..");
                    }
                }

            } else if (option == 2) {
                int algo = specify_algorithm();
                int[][] costs = new int[graph.size()][graph.size()];
                int[][] predessesor = new int[graph.size()][graph.size()];
                String algo_String;
                if(algo == 1){
                    for(int i = 0 ; i<graph.V ; i++){
                        graph.apply_dijkstra(i,costs[i],predessesor[i]);
                    }
                    algo_String = "Dijkstra";
                } else if (algo == 2) {
                    for(int i = 0 ; i<graph.V ; i++){
                        graph.bfShortestPath(i,costs[i],predessesor[i]);
                    }
                    algo_String = "Bellman-Ford";
                }
                else{
                    graph.floyed_warshal(predessesor,costs);
                    algo_String = "Floyed-Warshal";
                }
                while (true){
                    int subOption = print_subMenue2(algo_String);
                    if(subOption == 1 || subOption == 2){
                        System.out.print("Enter the source node please: ");
                        int src = scan.nextInt();
                        System.out.println();
                        src = check_validation_of_node(src);
                        System.out.print("Enter the destination node please: ");
                        int dest = scan.nextInt();
                        System.out.println();
                        dest = check_validation_of_node(dest);
                        if(subOption == 1){
                            System.out.print("The shortest path cost form node " + src + " to node " + dest + " is: ");
                            if(costs[src][dest] >= 1000000){
                                System.out.println("No path exists");
                            }else{
                                System.out.print(costs[src][dest]);
                            }
                            System.out.println();
                        }else {
                            if(costs[src][dest] >= 1000000){
                                System.out.println("No path exists");
                            }else{
                                System.out.print("The shortest path itself form node " + src + " to node " + dest + " is: ");
                                System.out.print(getPath(predessesor[src],src,dest));
                                System.out.println();
                            }
                        }
                    } else if (subOption == 3) {
                        break;
                    } else{
                        System.out.println("invalid option please reselct the desired operation..");
                    }
                }

            } else if (option == 3) {
                int algo = print_subMenue3();
                if(algo == 3)continue;
                int[][] costs = new int[graph.size()][graph.size()];
                int[][] predessesor = new int[graph.size()][graph.size()];
                boolean ShotestPathExist = true;
                if(algo == 1){
                     ShotestPathExist = graph.bfShortestPath(0,costs[0],predessesor[0]);
                } else if (algo == 2) {
                    ShotestPathExist = graph.floyed_warshal(predessesor,costs);
                }
                if(ShotestPathExist){
                    System.out.println("The given graph doesn't contain a negative cycle");
                }else{
                    System.out.println("The given graph contains a negative cycle");
                }

            } else if (option == 4) {
                break;
            }
            else{
                System.out.print("Error, type out 1 to restart otherwise type anything: ");
                int errorHandler = scan.nextInt();
                if(errorHandler != 1)break;
            }
        }
    }

    private int specify_algorithm(){
        System.out.println("Specify the algorithm to run out of the following three: ");
        System.out.println("1 : Dijkestra");
        System.out.println("2 : Bellman-Ford");
        System.out.println("3 : Floyd-Warshall");
        System.out.print("Option: ");
        int option = scan.nextInt();
        System.out.println();
        if(option == 1 || option ==2 || option == 3)return option;
        else{
            System.out.println("Error, please select a valid option!");
            return specify_algorithm();
        }
    }
    private int check_validation_of_node(int nodeEntered){
        int tmp  = nodeEntered;
        while (!(tmp >= 0 && tmp < graph.size())){
            System.out.println("Please enter a valid node: ");
            tmp = scan.nextInt();
        }
        return tmp;
    }

    private String getPath(int[] parents, int src, int dest){
        ArrayList<String>path = new ArrayList<>();
        path.add(String.valueOf(dest));
        if(src == dest)return String.valueOf(src);
        int tmp = dest;
        while (parents[tmp] != src){
            tmp = parents[tmp];
            path.add(String.valueOf(tmp));
        }
        path.add(String.valueOf(src));
        String result = "";
        for(int i = path.size()-1 ; i>=0; i--){
            result += path.get(i);
            if(i != 0)result += " => ";
        }
        return result;
    }

    private int print_subMenue(String algorithm){
        System.out.println("============================================================");
        System.out.println("\t\t\t\t\t\tSub menu of " + algorithm);
        System.out.println("Please select the desired operation out of these two: ");
        System.out.println("1 : Find the shortest path to a specific node.");
        System.out.println("2 : Find the path itself to a specific node.");
        System.out.println("3 : Return to the main menu.");
        System.out.print("Option: ");
        int subOption = scan.nextInt();
        System.out.println();
        return subOption;
    }

    private int print_subMenue2(String algorithm){
        System.out.println("============================================================");
        System.out.println("\t\t\t\t\t\tSub menu of " + algorithm);
        System.out.println("Please select the desired operation out of these two: ");
        System.out.println("1 : The cost of a path from a specific node to another one.");
        System.out.println("2 : Find the path itself from a specific node to another one.");
        System.out.println("3 : Return to the main menu.");
        System.out.print("Option: ");
        int subOption = scan.nextInt();
        System.out.println();
        return subOption;
    }

    private int print_subMenue3(){
        System.out.println("Please select the algorithm to run: ");
        System.out.println("1 : Bellman-Ford algorithm");
        System.out.println("2 : Floyed-Warshal");
        System.out.println("3 : Return to the main menu.");
        System.out.print("Option: ");
        int subOption = scan.nextInt();
        System.out.println();
        return subOption;
    }

}