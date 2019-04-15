package social_q3;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Social_q3 {
    public static void main(String[] args) throws IOException {

        int MAX = 211; // node, number start with 1.
        int UpperBound = MAX+1;
        int node = 0;
        int diameter = 0;


        // recording edge of nodes by 2-d arraylist.
        ArrayList<ArrayList<Integer>> adjLists = new ArrayList<ArrayList<Integer>>();
        getEdgeRelation("relation.txt", adjLists, UpperBound);


        //count sequentially
        while (node < UpperBound) {
            int[] isFriendNode = new int[UpperBound];
            int count = 1;
            //record this node's linked nodes.
            for (int tmp : adjLists.get(node)) {
                isFriendNode[tmp] = count;
            }
            while (adjLists.get(node).size() >= 1) {
                int breaknum = 0;
                for (int n = 0; n < UpperBound; n++) {
                    if (isFriendNode[n] == count) { // means have extend from 'adjLists.get(node) ->'
                        breaknum++;
                        for (int tmp : adjLists.get(n)) { // check next extention, 'adjLists.get(node) ->n ->'
                            if (isFriendNode[tmp] == 0) { // if here isFriendNode[] is 0, means extention from n is no link with adjLists.get(node)
                                isFriendNode[tmp] = count + 1; // so diameter can +1('adjLists.get(node) ->n ->here')
                                // System.out.println(count);
                            }
                        }
                    }
                }
                if (breaknum == 0) {
                    count = count - 1;
                    if (count >= diameter) {
                        diameter = count;
                        // System.out.println(" Node "+node+" diameter = "+count+ " Current diameter
                        // ="+diameter);
                    }
                    break;
                }
                count++;
            }

            if (adjLists.get(node).size() != 0) {
                System.out.println(" Node " + node + " diameter measured of this node = " + count
                        + ", Current diameter =" + diameter);
            }
            // System.out.println(" Node "+node+" diameter = "+count+ " Current diameter
            // ="+diameter);
            node++;
        }
        System.out.println("End.");
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
