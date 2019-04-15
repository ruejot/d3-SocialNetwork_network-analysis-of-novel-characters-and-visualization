package social_q1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Social_q1 {
    public static void main(String[] args) throws IOException {

        int MAX = 211; // node, number arranged form 1.
        int UpperBound = MAX+1;
        int[] degree = new int[UpperBound]; // degree of nodes.
        float avgDegree = 0;
        float sumDegree = 0;
        
        
        // recording edge of nodes by 2-d arraylist.
        ArrayList<ArrayList<Integer>> adjLists = new ArrayList<ArrayList<Integer>>();
        getEdgeRelation("relation.txt", adjLists, UpperBound);
        
        
        // print list
        for (int w = 0; w <= UpperBound; w++) {
            if (adjLists.get(w).size() != 0) {
                System.out.println(
                        "Node " + w + " : Degree is " + adjLists.get(w).size() + " : Link to " + adjLists.get(w));
            }
        }
        
    }
    
    public static void getEdgeRelation(String filename, ArrayList<ArrayList<Integer>> twoDimLists,int nodesCount) throws IOException {
        //create 2-d
        for (int i = 0; i <= nodesCount; i++) {
            twoDimLists.add(new ArrayList<Integer>());
        }
        // reading edge relation from file and recording.
        int edge = 0;
        int Value1 = 0;
        int Value2 = 0;
        String v1 = null, v2 = null;
        
        // Scanner read file
        Scanner sc = new Scanner(new File(filename));
        while (sc.hasNext()) {
            // 2 nodes group 1 edge.
            edge++;
            v1 = sc.next();
            v2 = sc.next();
            Value1 = Integer.valueOf(v1); // Scanner read String from file then convert into Int.
            Value2 = Integer.valueOf(v2);

            // both initial node record edge relation.
            twoDimLists.get(Value1).add(Value2);
            twoDimLists.get(Value2).add(Value1);
        }
        sc.close();
    }
}
