package com.jwetherell.algorithms;

import java.text.DecimalFormat;
import java.util.Random;

import com.jwetherell.algorithms.sorts.BinarySearchTreeSort;
import com.jwetherell.algorithms.sorts.CountingSort;
import com.jwetherell.algorithms.sorts.HeapSort;
import com.jwetherell.algorithms.sorts.InsertionSort;
import com.jwetherell.algorithms.sorts.MergeSort;
import com.jwetherell.algorithms.sorts.QuickSort;
import com.jwetherell.algorithms.sorts.RadixSort;



public class Main {
    
    private static final DecimalFormat FORMAT = new DecimalFormat("#.######");
    private static final int SIZE = 55555;

    private static final boolean showResult = false;
    private static final boolean showComparison = true;
    
    private static int insertionCount = 0;
    private static final double[] insertionResults = new double[1*3];
    private static int mergeCount = 0;
    private static final double[] mergeResults = new double[1*3];
    private static int quickCount = 0;
    private static final double[] quickResults = new double[3*3];
    private static int heapCount = 0;
    private static final double[] heapResults = new double[1*3];
    private static int countingCount = 0;
    private static final double[] countingResults = new double[1*3];
    private static int radixCount = 0;
    private static final double[] radixResults = new double[1*3];
    private static int bstsCount = 0;
    private static final double[] bstsResults = new double[3*3];

    private static final boolean showInsertion = true;
    private static final boolean showMerge = true;
    private static final boolean showQuick = true;
    private static final boolean showHeap = true;
    private static final boolean showCounting = true;
    private static final boolean showRadix = true;
    private static final boolean showBSTs = true;
    
    private static int[] unsorted = null;
    private static int maxUnsortedValue = 0;
    private static int[] sorted = null;
    private static int[] reverse = null;
    
    public static void main(String[] args) {
        System.out.println("Generating random array.");
        Random rn = new Random();
        unsorted = new int[SIZE];
        int i=0;
        while (i<unsorted.length) {
            int j = rn.nextInt(unsorted.length*4);
            if (!contains(j,unsorted)) {
                unsorted[i] = j;
                i++;
            }
        }
        System.out.println("Generated random array.");
        
        System.out.println("Finding max in random array.");
        maxUnsortedValue = findMax(unsorted)+1;
        
        System.out.println("Generating sorted array.");
        sorted = new int[SIZE];
        for (i=0; i<sorted.length; i++) {
            sorted[i] = i;
        }
        System.out.println("Generated sorted array.");
        
        System.out.println("Generating reverse sorted array.");
        reverse = new int[SIZE];
        for (i=(reverse.length-1); i>=0; i--) {
            reverse[i] = (SIZE-1)-i;
        }
        System.out.println("Generated reverse sorted array.");
        System.out.println();
        System.out.flush();
        
        if (showInsertion) {
            //Insertion sort
            long bInsertion = System.currentTimeMillis();
            int[] result = InsertionSort.sort(unsorted.clone());
            if (!check(result)) System.err.println("InsertionSort failed.");
            long aInsertion = System.currentTimeMillis();
            double diff = (aInsertion-bInsertion)/1000d;
            System.out.println("Random: InsertionSort="+FORMAT.format(diff));
            if (showResult) showResult(unsorted, result);
            if (showComparison) insertionResults[insertionCount++] = diff;
            System.gc();
            
            bInsertion = System.currentTimeMillis();
            result = InsertionSort.sort(sorted.clone());
            if (!check(result)) System.err.println("InsertionSort failed.");
            aInsertion = System.currentTimeMillis();
            diff = (aInsertion-bInsertion)/1000d;
            System.out.println("Sorted: InsertionSort="+FORMAT.format(diff));
            if (showResult) showResult(sorted, result);
            if (showComparison) insertionResults[insertionCount++] = diff;
            System.gc();
            
            bInsertion = System.currentTimeMillis();
            result = InsertionSort.sort(reverse.clone());
            if (!check(result)) System.err.println("InsertionSort failed.");
            aInsertion = System.currentTimeMillis();
            diff = (aInsertion-bInsertion)/1000d;
            System.out.println("Reverse sorted: InsertionSort="+FORMAT.format(diff));
            if (showResult) showResult(reverse, result);
            if (showComparison) insertionResults[insertionCount++] = diff;
            System.gc();
            
            System.out.println();
            System.out.flush();
        }
        
        if (showMerge) {
            //Merge sort
            long bMerge = System.currentTimeMillis();
            int[] result = MergeSort.sort(unsorted.clone());
            if (!check(result)) System.err.println("MergeSort failed.");
            long aMerge = System.currentTimeMillis();
            double diff = (aMerge-bMerge)/1000d;
            System.out.println("Random: MergeSort="+FORMAT.format(diff));
            if (showResult) showResult(unsorted, result);
            if (showComparison) mergeResults[mergeCount++] = diff;
            System.gc();
            
            bMerge = System.currentTimeMillis();
            result = MergeSort.sort(sorted.clone());
            if (!check(result)) System.err.println("MergeSort failed.");
            aMerge = System.currentTimeMillis();
            diff = (aMerge-bMerge)/1000d;
            System.out.println("Sorted: MergeSort="+FORMAT.format(diff));
            if (showResult) showResult(sorted, result);
            if (showComparison) mergeResults[mergeCount++] = diff;
            System.gc();
            
            bMerge = System.currentTimeMillis();
            result = MergeSort.sort(reverse.clone());
            if (!check(result)) System.err.println("MergeSort failed.");
            aMerge = System.currentTimeMillis();
            diff = (aMerge-bMerge)/1000d;
            System.out.println("Reverse sorted: MergeSort="+FORMAT.format(diff));
            if (showResult) showResult(reverse, result);
            if (showComparison) mergeResults[mergeCount++] = diff;
            System.gc();
            
            System.out.println();
            System.out.flush();
        }
        
        if (showQuick) {
            //Quicksort
            long bQuick = System.currentTimeMillis();
            int[] result = QuickSort.sort(QuickSort.PIVOT_TYPE.FIRST,unsorted.clone());
            if (!check(result)) System.err.println("QuickSort failed.");
            long aQuick = System.currentTimeMillis();
            double diff = (aQuick-bQuick)/1000d;
            System.out.println("Random: QuickSort first element pivot="+FORMAT.format(diff));
            if (showResult) showResult(unsorted, result);
            if (showComparison) quickResults[quickCount++] = diff;
            System.gc();
            
            bQuick = System.currentTimeMillis();
            result = QuickSort.sort(QuickSort.PIVOT_TYPE.FIRST,sorted.clone());
            if (!check(result)) System.err.println("QuickSort failed.");
            aQuick = System.currentTimeMillis();
            diff = (aQuick-bQuick)/1000d;
            System.out.println("Sorted: QuickSort first element pivot="+FORMAT.format(diff));
            if (showResult) showResult(sorted, result);
            if (showComparison) quickResults[quickCount++] = diff;
            System.gc();
            
            bQuick = System.currentTimeMillis();
            result = QuickSort.sort(QuickSort.PIVOT_TYPE.FIRST,reverse.clone());
            if (!check(result)) System.err.println("QuickSort failed.");
            aQuick = System.currentTimeMillis();
            diff = (aQuick-bQuick)/1000d;
            System.out.println("Reverse sorted: QuickSort first element pivot="+FORMAT.format(diff));
            if (showResult) showResult(reverse, result);
            if (showComparison) quickResults[quickCount++] = diff;
            System.gc();
            
            System.out.println();
            System.out.flush();

            bQuick = System.currentTimeMillis();
            result = QuickSort.sort(QuickSort.PIVOT_TYPE.MIDDLE,unsorted.clone());
            if (!check(result)) System.err.println("QuickSort failed.");
            aQuick = System.currentTimeMillis();
            diff = (aQuick-bQuick)/1000d;
            System.out.println("Random: QuickSort middle element pivot="+FORMAT.format(diff));
            if (showResult) showResult(unsorted, result);
            if (showComparison) quickResults[quickCount++] = diff;
            System.gc();
            
            bQuick = System.currentTimeMillis();
            result = QuickSort.sort(QuickSort.PIVOT_TYPE.MIDDLE,sorted.clone());
            if (!check(result)) System.err.println("QuickSort failed.");
            aQuick = System.currentTimeMillis();
            diff = (aQuick-bQuick)/1000d;
            System.out.println("Sorted: QuickSort middle element pivot="+FORMAT.format(diff));
            if (showResult) showResult(sorted, result);
            if (showComparison) quickResults[quickCount++] = diff;
            System.gc();
            
            bQuick = System.currentTimeMillis();
            result = QuickSort.sort(QuickSort.PIVOT_TYPE.MIDDLE,reverse.clone());
            if (!check(result)) System.err.println("QuickSort failed.");
            aQuick = System.currentTimeMillis();
            diff = (aQuick-bQuick)/1000d;
            System.out.println("Reverse sorted: QuickSort middle element pivot="+FORMAT.format(diff));
            if (showResult) showResult(reverse, result);
            if (showComparison) quickResults[quickCount++] = diff;
            System.gc();
            
            System.out.println();
            System.out.flush();

            bQuick = System.currentTimeMillis();
            result = QuickSort.sort(QuickSort.PIVOT_TYPE.RANDOM,unsorted.clone());
            if (!check(result)) System.err.println("Random QuickSort failed.");
            aQuick = System.currentTimeMillis();
            diff = (aQuick-bQuick)/1000d;
            System.out.println("Random: Randomized QuickSort="+FORMAT.format(diff));
            if (showResult) showResult(unsorted, result);
            if (showComparison) quickResults[quickCount++] = diff;
            System.gc();
            
            bQuick = System.currentTimeMillis();
            result = QuickSort.sort(QuickSort.PIVOT_TYPE.RANDOM,sorted.clone());
            if (!check(result)) System.err.println("Random QuickSort failed.");
            aQuick = System.currentTimeMillis();
            diff = (aQuick-bQuick)/1000d;
            System.out.println("Sorted: Randomized QuickSort="+FORMAT.format(diff));
            if (showResult) showResult(sorted, result);
            if (showComparison) quickResults[quickCount++] = diff;
            System.gc();
            
            bQuick = System.currentTimeMillis();
            result = QuickSort.sort(QuickSort.PIVOT_TYPE.RANDOM,reverse.clone());
            if (!check(result)) System.err.println("Random QuickSort failed.");
            aQuick = System.currentTimeMillis();
            diff = (aQuick-bQuick)/1000d;
            System.out.println("Reverse sorted: Randomized QuickSort="+FORMAT.format(diff));
            if (showResult) showResult(reverse, result);
            if (showComparison) quickResults[quickCount++] = diff;
            System.gc();
            
            System.out.println();
            System.out.flush();
        }
        
        if (showHeap) {
            //Heapsort
            long bHeap = System.currentTimeMillis();
            int[] result = HeapSort.sort(unsorted.clone());
            if (!check(result)) System.err.println("HeapSort failed.");
            long aHeap = System.currentTimeMillis();
            double diff = (aHeap-bHeap)/1000d;
            System.out.println("Random: HeapSort="+FORMAT.format(diff));
            if (showResult) showResult(unsorted, result);
            if (showComparison) heapResults[heapCount++] = diff;
            System.gc();
            
            bHeap = System.currentTimeMillis();
            result = HeapSort.sort(sorted.clone());
            if (!check(result)) System.err.println("HeapSort failed.");
            aHeap = System.currentTimeMillis();
            diff = (aHeap-bHeap)/1000d;
            System.out.println("Sorted: HeapSort="+FORMAT.format(diff));
            if (showResult) showResult(sorted, result);
            if (showComparison) heapResults[heapCount++] = diff;
            System.gc();
            
            bHeap = System.currentTimeMillis();
            result = HeapSort.sort(reverse.clone());
            if (!check(result)) System.err.println("HeapSort failed.");
            aHeap = System.currentTimeMillis();
            diff = (aHeap-bHeap)/1000d;
            System.out.println("Reverse sorted: HeapSort="+FORMAT.format(diff));
            if (showResult) showResult(reverse, result);
            if (showComparison) heapResults[heapCount++] = diff;
            System.gc();
            
            System.out.println();
            System.out.flush();
        }
        
        if (showCounting) {
            //Counting sort
            long bCounting = System.currentTimeMillis();
            int[] result = CountingSort.sort(unsorted.clone(),maxUnsortedValue);
            if (!check(result)) System.err.println("CountingSort failed.");
            long aCounting = System.currentTimeMillis();
            double diff = (aCounting-bCounting)/1000d;
            System.out.println("Random: CountingSort="+FORMAT.format(diff));
            if (showResult) showResult(unsorted, result);
            if (showComparison) countingResults[countingCount++] = diff;
            System.gc();
            
            bCounting = System.currentTimeMillis();
            result = CountingSort.sort(sorted.clone(),sorted.length);
            if (!check(result)) System.err.println("CountingSort failed.");
            aCounting = System.currentTimeMillis();
            diff = (aCounting-bCounting)/1000d;
            System.out.println("Sorted: CountingSort="+FORMAT.format(diff));
            if (showResult) showResult(sorted, result);
            if (showComparison) countingResults[countingCount++] = diff;
            System.gc();
            
            bCounting = System.currentTimeMillis();
            result = CountingSort.sort(reverse.clone(),reverse.length);
            if (!check(result)) System.err.println("CountingSort failed.");
            aCounting = System.currentTimeMillis();
            diff = (aCounting-bCounting)/1000d;
            System.out.println("Reverse sorted: CountingSort="+FORMAT.format(diff));
            if (showResult) showResult(reverse, result);
            if (showComparison) countingResults[countingCount++] = diff;
            System.gc();
            
            System.out.println();
            System.out.flush();
        }
        
        if (showRadix) {
            //Radix sort
            long bRadix = System.currentTimeMillis();
            int[] result = RadixSort.sort(unsorted.clone());
            if (!check(result)) System.err.println("RadixSort failed.");
            long aRadix = System.currentTimeMillis();
            double diff = (aRadix-bRadix)/1000d;
            System.out.println("Random: RadixSort="+FORMAT.format(diff));
            if (showResult) showResult(unsorted, result);
            if (showComparison) radixResults[radixCount++] = diff;
            System.gc();
            
            bRadix = System.currentTimeMillis();
            result = RadixSort.sort(sorted.clone());
            if (!check(result)) System.err.println("RadixSort failed.");
            aRadix = System.currentTimeMillis();
            diff = (aRadix-bRadix)/1000d;
            System.out.println("Sorted: RadixSort="+FORMAT.format(diff));
            if (showResult) showResult(sorted, result);
            if (showComparison) radixResults[radixCount++] = diff;
            System.gc();
            
            bRadix = System.currentTimeMillis();
            result = RadixSort.sort(reverse.clone());
            if (!check(result)) System.err.println("RadixSort failed.");
            aRadix = System.currentTimeMillis();
            diff = (aRadix-bRadix)/1000d;
            System.out.println("Reverse sorted: RadixSort="+FORMAT.format(diff));
            if (showResult) showResult(reverse, result);
            if (showComparison) radixResults[radixCount++] = diff;
            System.gc();
            
            System.out.println();
            System.out.flush();
        }
        
        if (showBSTs) {
            //Binary search tree sort
            long bBSTs = System.currentTimeMillis();
            int[] result = BinarySearchTreeSort.sort(BinarySearchTreeSort.ROOT_TYPE.FIRST,unsorted.clone());
            if (!check(result)) System.err.println("BSTs failed.");
            long aBSTs = System.currentTimeMillis();
            double diff = (aBSTs-bBSTs)/1000d;
            System.out.println("Random: BSTs first element="+FORMAT.format(diff));
            if (showResult) showResult(unsorted, result);
            if (showComparison) bstsResults[bstsCount++] = diff;
            System.gc();
            
            bBSTs = System.currentTimeMillis();
            result = BinarySearchTreeSort.sort(BinarySearchTreeSort.ROOT_TYPE.FIRST,sorted.clone());
            if (!check(result)) System.err.println("BSTs failed.");
            aBSTs = System.currentTimeMillis();
            diff = (aBSTs-bBSTs)/1000d;
            System.out.println("Sorted: BSTs first element="+FORMAT.format(diff));
            if (showResult) showResult(sorted, result);
            if (showComparison) bstsResults[bstsCount++] = diff;
            System.gc();
            
            bBSTs = System.currentTimeMillis();
            result = BinarySearchTreeSort.sort(BinarySearchTreeSort.ROOT_TYPE.FIRST,reverse.clone());
            if (!check(result)) System.err.println("BSTs failed.");
            aBSTs = System.currentTimeMillis();
            diff = (aBSTs-bBSTs)/1000d;
            System.out.println("Reverse sorted: BSTs first element="+FORMAT.format(diff));
            if (showResult) showResult(reverse, result);
            if (showComparison) bstsResults[bstsCount++] = diff;
            System.gc();
            
            System.out.println();
            System.out.flush();

            bBSTs = System.currentTimeMillis();
            result = BinarySearchTreeSort.sort(BinarySearchTreeSort.ROOT_TYPE.MIDDLE,unsorted.clone());
            if (!check(result)) System.err.println("BSTs failed.");
            aBSTs = System.currentTimeMillis();
            diff = (aBSTs-bBSTs)/1000d;
            System.out.println("Random: BSTs middle element="+FORMAT.format(diff));
            if (showResult) showResult(unsorted, result);
            if (showComparison) bstsResults[bstsCount++] = diff;
            System.gc();
            
            bBSTs = System.currentTimeMillis();
            result = BinarySearchTreeSort.sort(BinarySearchTreeSort.ROOT_TYPE.MIDDLE,sorted.clone());
            if (!check(result)) System.err.println("BSTs failed.");
            aBSTs = System.currentTimeMillis();
            diff = (aBSTs-bBSTs)/1000d;
            System.out.println("Sorted: BSTs middle element="+FORMAT.format(diff));
            if (showResult) showResult(sorted, result);
            if (showComparison) bstsResults[bstsCount++] = diff;
            System.gc();
            
            bBSTs = System.currentTimeMillis();
            result = BinarySearchTreeSort.sort(BinarySearchTreeSort.ROOT_TYPE.MIDDLE,reverse.clone());
            if (!check(result)) System.err.println("BSTs failed.");
            aBSTs = System.currentTimeMillis();
            diff = (aBSTs-bBSTs)/1000d;
            System.out.println("Reverse sorted: BSTs middle element="+FORMAT.format(diff));
            if (showResult) showResult(reverse, result);
            if (showComparison) bstsResults[bstsCount++] = diff;
            System.gc();
            
            System.out.println();
            System.out.flush();

            bBSTs = System.currentTimeMillis();
            result = BinarySearchTreeSort.sort(BinarySearchTreeSort.ROOT_TYPE.RANDOM,unsorted.clone());
            if (!check(result)) System.err.println("BSTs failed.");
            aBSTs = System.currentTimeMillis();
            diff = (aBSTs-bBSTs)/1000d;
            System.out.println("Random: BSTs random element="+FORMAT.format(diff));
            if (showResult) showResult(unsorted, result);
            if (showComparison) bstsResults[bstsCount++] = diff;
            System.gc();
            
            bBSTs = System.currentTimeMillis();
            result = BinarySearchTreeSort.sort(BinarySearchTreeSort.ROOT_TYPE.RANDOM,sorted.clone());
            if (!check(result)) System.err.println("BSTs failed.");
            aBSTs = System.currentTimeMillis();
            diff = (aBSTs-bBSTs)/1000d;
            System.out.println("Sorted: BSTs random element="+FORMAT.format(diff));
            if (showResult) showResult(sorted, result);
            if (showComparison) bstsResults[bstsCount++] = diff;
            System.gc();
            
            bBSTs = System.currentTimeMillis();
            result = BinarySearchTreeSort.sort(BinarySearchTreeSort.ROOT_TYPE.RANDOM,reverse.clone());
            if (!check(result)) System.err.println("BSTs failed.");
            aBSTs = System.currentTimeMillis();
            diff = (aBSTs-bBSTs)/1000d;
            System.out.println("Reverse sorted: BSTs random element="+FORMAT.format(diff));
            if (showResult) showResult(reverse, result);
            if (showComparison) bstsResults[bstsCount++] = diff;
            System.gc();
            
            System.out.println();
            System.out.flush();
        }

        if (showComparison) showComparison(); 
    }
    
    private static final void showComparison() {
        System.out.println("Algorithm\t\t\tRandom\tSorted\tReverse Sorted");
        if (showInsertion) {
            int i=0;
            System.out.println("Insertion sort\t\t\t"+insertionResults[i++]+"\t"+insertionResults[i++]+"\t"+insertionResults[i++]);
        }
        if (showMerge) {
            int i=0;
            System.out.println("Merger sort\t\t\t"+mergeResults[i++]+"\t"+mergeResults[i++]+"\t"+mergeResults[i++]);
        }
        if (showQuick) {
            int i=0;
            System.out.println("Quicksort with first as pviot:\t"+quickResults[i++]+"\t"+quickResults[i++]+"\t"+quickResults[i++]);
            System.out.println("Quicksort with middle as pviot:\t"+quickResults[i++]+"\t"+quickResults[i++]+"\t"+quickResults[i++]);
            System.out.println("Quicksort with random as pviot:\t"+quickResults[i++]+"\t"+quickResults[i++]+"\t"+quickResults[i++]);
        }
        if (showHeap) {
            int i=0;
            System.out.println("Heap sort\t\t\t"+heapResults[i++]+"\t"+heapResults[i++]+"\t"+heapResults[i++]);
        }
        if (showCounting) {
            int i=0;
            System.out.println("Counting sort\t\t\t"+countingResults[i++]+"\t"+countingResults[i++]+"\t"+countingResults[i++]);
        }
        if (showRadix) {
            int i=0;
            System.out.println("Radix sort\t\t\t"+radixResults[i++]+"\t"+radixResults[i++]+"\t"+radixResults[i++]);
        }
        if (showBSTs) {
            int i=0;
            System.out.println("BSTs with first as root :\t"+bstsResults[i++]+"\t"+bstsResults[i++]+"\t"+bstsResults[i++]);
            System.out.println("BSTs with middle as root:\t"+bstsResults[i++]+"\t"+bstsResults[i++]+"\t"+bstsResults[i++]);
            System.out.println("BSTs with random as root:\t"+bstsResults[i++]+"\t"+bstsResults[i++]+"\t"+bstsResults[i++]);
        }
    }
    
    private static int findMax(int[] array) {
        int max = Integer.MIN_VALUE;
        for (int e : array) {
            if (e>max) max=e;
        }
        return max;
    }

    private static final void showResult(int[] unsorted, int[] result) {
        System.out.println("Unsorted: "+print(unsorted));
        System.out.println("Sorted: "+print(result));
        System.out.flush();
    }
    
    private static final boolean contains(int value, int[] array) {
        for (int i : array) {
            if (i==value) return true;
        }
        return false;
    }
    
    private static final boolean check(int[] array) {
        for (int i=1; i<array.length; i++) {
            if (array[i-1]>array[i]) return false;
        }
        return true;
    }
    
    public static final String print(int[] array) {
        return print(array,0,array.length);
    }
    
    public static final String print(int[] array, int start, int length) {
        final int[] clone = array.clone();
        StringBuilder builder = new StringBuilder();
        for (int i=0; i<length; i++) {
            int e = clone[start+i];
            builder.append(e+" ");
        }
        return builder.toString();
    }
    
    public static final String printWithPivot(int[] array, int pivotIndex, int start, int length) {
        final int[] clone = array.clone();
        StringBuilder builder = new StringBuilder();
        for (int i=0; i<length; i++) {
            int e = clone[start+i];
            if (i==pivotIndex) builder.append("`"+e+"` ");
            else builder.append(e+" ");
        }
        return builder.toString();
    }
}
