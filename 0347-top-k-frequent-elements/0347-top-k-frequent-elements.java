class Solution {
    public int[] topKFrequent(int[] nums, int k) {

    Map<Integer,Integer> map = new HashMap<>();     //HashMap (Frequency Counter)
    for ( int num: nums){
        map.put(num,map.getOrDefault(num,0)+1);       // Counting Frequencies
    }

    PriorityQueue<Map.Entry<Integer,Integer>> heap = new PriorityQueue<>(         //Priority Queue (Max Heap)
        (a,b) -> Integer.compare(b.getValue(),a.getValue())               //Custom Comparator
    );

    for (Map.Entry<Integer,Integer> entry : map.entrySet()){     //Insert Map Entries into Heap
        heap.offer(entry);          // offer() : Adds element into heap.
    } 
    int[] res = new int [k];
    for(int i=0; i<k; i++){                 //poll() : Removes highest frequency element.
                                            //getKey() : Returns the number itself.
        res[i] = Objects.requireNonNull(heap.poll()).getKey();
    }
    return res;

    }
}