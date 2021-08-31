import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeastRecentPage {

	public static void main(String[] args) {

		int num;
		List<Integer>pages = new ArrayList<>(Arrays.asList(2,3,1,3,2,1,4,3,2));
				
		int maxCacheSize = 3;
		int cacheMisses = 0;
		
		ArrayList<Integer> cacheArray = new ArrayList<>(maxCacheSize);
		
		for (int i = 0; i < pages.size(); i++) {
			int indexOfCache = cacheArray.indexOf(pages.get(i));
			if (indexOfCache >=0) {
				
			} else {
				cacheMisses += 1;
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
