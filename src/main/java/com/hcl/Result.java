package com.hcl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Result {

    /*
     * Complete the 'minimize' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY point
     *  2. INTEGER k
     */

	public static int minimize(List<Integer> pointList, int k) {
        // int[] point = pointList.toArray();
        int[] point = pointList.stream().mapToInt(i->i).toArray();
    // Write your code here

        if ( (point.length >=1 || point.length <=100) && (k >=1 || k <100000)) { 
            int incre = dfs(point, k, 1, point[0]+k, point[0]+k);
            int decre = dfs(point, k, 1, point[0]-k, point[0]-k);

            return Math.min(incre, decre);
            // return -1;
        }
       
        return -1;
    }

    public static int dfs(int[] point, int k, int idx, int min, int max) {
        if (idx >= point.length) {
            return max - min;
        }
        
        if(point[idx] > -100000 && point[idx] <100000) {
            int min_incre = Math.min(min, point[idx] + k);
            int max_incre = Math.max(max, point[idx] + k);
            int incre = dfs(point, k, idx + 1, min_incre, max_incre);

            int min_decre = Math.min(min, point[idx] - k);
            int max_decre = Math.max(max, point[idx] - k);
            int decre = dfs(point, k, idx + 1, min_decre, max_decre);

            return Math.min(incre, decre);    
        }
        return 0;
    }
    
    public static int minimize2(int[] point, int k) {
        // if (point == null || point.length == 0) {
        //     return -1;
        // }
        if(point.length >=1 || point.length <=100) {
            List<Integer> list = new ArrayList<>();
            return dfs2(point, k, 0, list);    
        }
        return -1;
    }
    
    public static int dfs2(int[] point, int k, int idx, List<Integer> list) {
        if (idx >= point.length) {
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < list.size(); i++) {
                min = Math.min(min, list.get(i));
                max = Math.max(max, list.get(i));
            }
            //System.out.println(list.toString());
            return max - min;
        }
        
        list.add(point[idx] + k);
        int incre = dfs2(point, k, idx + 1, list);
        list.remove(list.size() - 1);
        
        list.add(point[idx] - k);
        int decre = dfs2(point, k, idx + 1, list);
        list.remove(list.size() - 1);
        
        return Math.min(incre, decre);
    }
    
 public static void main(String[] args) {
        
//	 	Arrays.asList(a)
//        System.out.println(minimize(new int[]{-3, 0, 1}, 3)); //3
	 	System.out.println( minimize(new ArrayList( Arrays.asList(-3, 0, 1)), 3)); //3
        System.out.println(minimize2(new int[]{-3, 0, 1}, 3)); //3
        
//        System.out.println(minimize(new int[]{4, 7, -7}, 5)); //4
        System.out.println( minimize(new ArrayList( Arrays.asList(4, 7, -7)), 3)); //3
        System.out.println(minimize2(new int[]{4, 7, -7}, 5)); //4
        
//        System.out.println(minimize(new int[]{-100000, 100000}, 100000)); //0
        System.out.println( minimize(new ArrayList( Arrays.asList(-100000, 100000, 100000)), 3)); //3
        System.out.println(minimize2(new int[]{-100000, 100000}, 100000)); //0
        
    }
}

	