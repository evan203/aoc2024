import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class day01 {
  public static void main(String[] args) throws FileNotFoundException {
    // use Scanner to read puzzle input from file; store into lists
    Scanner sc = new Scanner(new File("input01"));

    // use priority queue to store each list (left col : a, right col : b)
    // for part 1 bc lowest int is at head of queue when added
    PriorityQueue<Integer> aQueue = new PriorityQueue<>(1000);
    PriorityQueue<Integer> bQueue = new PriorityQueue<>(1000);

    // store left col into an arraylist for easy iteration, store right col
    // in hash map where key is num and value is frequency of num
    ArrayList<Integer> aList = new ArrayList<>(1000);
    HashMap<Integer, Integer> bCountMap = new HashMap<>(1000);

    // put text from file into data structures
    int i;
    while (sc.hasNextInt()) {
      i = sc.nextInt();
      aQueue.add(i);
      aList.add(i);
      i = sc.nextInt();
      bQueue.add(i);
      bCountMap.put(i, bCountMap.containsKey(i) ? bCountMap.get(i) + 1 : 1);
    }

    // calculate distance between left and right list starting with the smallest
    // numbers in each list respectively in increasing order
    int totalDistance = 0;
    while (aQueue.size() > 0) {
      totalDistance += Math.abs(aQueue.poll() - bQueue.poll());
    }
    // print part A solution
    System.out.println(totalDistance);

    // calculate similarity score by adding up each number in the left list after
    // multiplying it by the number of times that number appears in the right list
    int similarityScore = 0;
    for (Integer num : aList) {
      if (bCountMap.containsKey(num)) {
        similarityScore += num * bCountMap.get(num);
      }
    }

    System.out.println(similarityScore);

    sc.close();

  }
}
