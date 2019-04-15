package socail_q2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Socail_q2 {
    public static void main(String[] args) throws IOException {

        int MAX = 211; // node, number arranged form 1.
        int UpperBound = MAX+1;
        int[] degree = new int[UpperBound]; // degree of nodes.
        float[] acc_Each = new float[UpperBound]; // Average clustering coefficient of node.
        float sumACC = 0;

        
        // recording edge of nodes by 2-d arraylist.
        ArrayList<ArrayList<Integer>> adjLists = new ArrayList<ArrayList<Integer>>();
        getEdgeRelation("relation.txt", adjLists, UpperBound);

        
        // To find clustering coefficient of one node,
        // the important thing is counting triangles
        // which the node is one of the triangle's vertex.
        for (int iNode = 0; iNode <= UpperBound; iNode++) {
            // if Node degree only 0 or 1, the node can not form any triangle. exclude this
            // case.
            if (!adjLists.get(iNode).isEmpty() || adjLists.get(iNode).size() != 1) {
                ArrayList<ArrayList<Integer>> triRecordLists = new ArrayList<>();
                int triCount = 0;

                for (int tmp : adjLists.get(iNode)) {
                    for (int tmp2 : adjLists.get(tmp)) {

                        if (tmp2 > tmp && tmp2 != iNode) {
                            for (int ref : adjLists.get(iNode)) {
                                if (ref == tmp2) {
                                    triCount++;
                                }
                            }
                        }
                    }
                }

                // accountion Acc(Average clustering coefficient)
                if (triCount > 0) {
                    degree[iNode] = adjLists.get(iNode).size();
                    float iAcc = ((float) triCount * 2) / (float) (degree[iNode] * (degree[iNode] - 1));
                    acc_Each[iNode] = iAcc;
                    // printing out each node info.
                    System.out.println("iNode " + iNode + " degree = " + degree[iNode] + " and Tri " + triCount
                            + " and iAcc =" + iAcc);
                    sumACC = sumACC + iAcc;
                }
            }
        }
        sumACC = sumACC / (MAX);
        System.out.println("Average clustering coefficient = " + sumACC);
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
