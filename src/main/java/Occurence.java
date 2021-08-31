import java.util.ArrayList;
import java.util.Collections;

public class Occurence {
	public static void main(String[] args) {
		
		ArrayList<Integer> scores = new ArrayList<Integer>(); 
		scores.add(3);
		scores.add(7);
		scores.add(2);
		scores.add(7);
		scores.add(7);
		scores.add(10);
		scores.add(4);
		
		Collections.sort(scores, Collections.reverseOrder());
		System.out.println(scores);
		
		int rank = 1;
		int count = 1;
		int finalCount = 0;
		int cutOffRank = 4;
		for (int i = 0; i < scores.size(); i++) {
			
			int pastNum = scores.get(i);
			scores.set(i, rank);
			if(rank <= cutOffRank) {
				finalCount += 1;
			}
			if(i != scores.size()-1) {
				
				if (pastNum != scores.get(i+1)) {
					rank += count;
					count =1;
				} else {
					count += 1;
				}
			}
			
		}
		System.out.println(scores);
		System.out.println(finalCount);
			 
		
	}
}


