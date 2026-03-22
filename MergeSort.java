package fashiony;

import java.time.LocalDateTime;
import java.util.List;

public class MergeSort {
    //Merge sort implmeneted from our lectures, that was altred for date input(LocalDateTime).
    public static void merge(List<Version> vers, int l, int m, int r){
        int n1 = m - l + 1;
        int n2 = r - m;

        Version L[] = new Version[n1];
        Version R[] = new Version[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = vers.get(l + i);
        for (int j = 0; j < n2; ++j)
            R[j] = vers.get(m + 1 + j);

        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            LocalDateTime lD = VersionManager.getDate(L[i]) == null ? LocalDateTime.MIN : VersionManager.getDate(L[i]);
            LocalDateTime rD = VersionManager.getDate(R[j]) == null ? LocalDateTime.MIN : VersionManager.getDate(R[j]);
            if (lD.compareTo(rD)<=0) {
                vers.set(k, L[i]);
                i++;
            }
            else {
                vers.set(k, R[j]);
                j++;
            }
            k++;
        }
        while (i < n1) {
            vers.set(k, L[i]);        
            i++;
            k++;
        }
        while (j < n2) {
            vers.set(k, R[j]);
            j++;
            k++;
        }
    }
    public static void mergeSort(List<Version> vers, int l, int r){
        if(vers!= null && !vers.isEmpty()){
            if (l < r) {
                int m = l + (r - l) / 2;
                mergeSort(vers, l, m);
                mergeSort(vers, m + 1, r);
                merge(vers, l, m, r);
            }
        }else{
            System.out.println("EMPTY VERSION LIST");
        }
    }
}
