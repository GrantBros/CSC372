package stuselsort;

import java.util.ArrayList;
import java.util.Comparator;

/*
 * selection sort class using comparator to determine if conditions
 * utilizing a temporary variable to hold information during transfer
 */

public class StudentSorter {

    public static void selectionSort(ArrayList<Student> list, Comparator<Student> comparator) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (comparator.compare(list.get(j), list.get(minIdx)) < 0) {
                    minIdx = j;
                }
            }

            // temporary variable swapping in the outer loop
            Student temp = list.get(i);
            list.set(i, list.get(minIdx));
            list.set(minIdx, temp);
        }
    }
}