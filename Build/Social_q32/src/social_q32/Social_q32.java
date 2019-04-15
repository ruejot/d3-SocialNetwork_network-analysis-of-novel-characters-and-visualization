package social_q32;

import java.io.File;
import java.io.IOException;
import static java.lang.Math.random;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Social_q32 {
    public static void main(String[] args) throws IOException {

        int MAX = 211; // node, number start with 1.
        int UpperBound = MAX+1;
        int[] store = new int[UpperBound];
        int node = 0;
        int diameter = 0;

        // recording edge of nodes by 2-d arraylist.
        ArrayList<ArrayList<Integer>> adjLists = new ArrayList<ArrayList<Integer>>();
        getEdgeRelation("relation.txt", adjLists, UpperBound);

        
        //count
        int nn = 1;
        int dnode = 0;
        int reCheck = 10;

        //randome pick node & reCheck
        while (nn <= reCheck) {
            if (nn % 2 == 1) {
                Random ran = new Random();
                node = ran.nextInt(MAX)+1;
                while (adjLists.get(node).size() == 0) {
                    node = ran.nextInt(MAX)+1;
                }
            }

            int[] isFriendNode = new int[UpperBound];
            int count = 1; // count diameter
            for (int tmp : adjLists.get(node)) {
                isFriendNode[tmp] = count;
            }
            while (adjLists.get(node).size() != 0) { // isFriendNode is 0 means no edge
                int breaknum = 0;
                for (int n = 0; n < UpperBound; n++) {
                    if (isFriendNode[n] == count) {
                        breaknum = 1;
                        for (int tmp : adjLists.get(n)) {
                            if (isFriendNode[tmp] == 0) {
                                isFriendNode[tmp] = count + 1;
                                dnode = tmp;

                            }
                        }
                    }
                }
                if (breaknum == 0) {
                    if ((count - 1) >= diameter) {
                        diameter = count - 1;
                        // System.out.println(diameter);
                    }
                    System.out.println(" Node " + node + " diameter = " + (count - 1) + " Current diameter =" + diameter
                            + " distance node = " + dnode);
                    break;
                }

                count++;
            }
            node = dnode;
            nn++; // next round
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
