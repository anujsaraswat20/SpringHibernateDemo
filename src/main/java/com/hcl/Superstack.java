package com.hcl;

import java.util.LinkedList;
import java.util.ListIterator;

public class Superstack {

	public static void main(String[] args) {
//		String[] operations = {"push 4", "pop", "push 3", "push 5", "push 2", 
//				"inc 3 1", 
//				"pop", "push 1", 
//				"inc 2 2", 
//				"push 4", "pop", "pop"};
//		String[] operations = {"push -36", "push 20", "pop", "push -9", "pop", 
//				
//				"push -53",
//				"pop",
//				"inc 1 -17"
//				};
		String[] operations = {"push 15", "pop", "push -51", "pop", "push 41", "pop", 
		 
		"push -76", "push 51", 
		"push -10", 
		"inc 1 -49"
		};
		
		superStackOp(operations);
	}
	
	static void superStackOp(String[] operations) {
		if (operations == null || operations.length == 0) {
            System.out.println("EMPTY");
            return;
        }
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < operations.length; i++) {
            String current = operations[i];
            if (current.equals("pop")) {
                list.removeLast();
            } else if(current.startsWith("inc")) {
            	int uptoIndex = Integer.parseInt(current.split(" ")[1]);
            	int value = Integer.parseInt(current.split(" ")[2]);
            	
            	int index = 0;
            	while(index < uptoIndex) {
            		int item = list.get(index)+value;
            		list.remove(index);
            		list.add(index, item);
            		++index;
            	}
            }
            else {
                if (current.startsWith("push")) {
                    list.addLast(Integer.parseInt(current.split(" ")[1]));
                } 
                else {
                    int e = Integer.parseInt(current.split(" ")[1]);
                    int k = Integer.parseInt(current.split(" ")[2]);
                    ListIterator<Integer> listIterator = list.listIterator();
                    int j = 1;
                    while (listIterator.hasNext()) {
                        if (j > e)
                            break;
                        listIterator.set(listIterator.next() + k);
                        j++;
                    }
                }
            }
            if (list.isEmpty())
                System.out.println("EMPTY");
            else
                System.out.println(list.getLast());

        }
	}
}
