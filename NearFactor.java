
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class NearFactor {

	public static Map<Integer, Integer> nearFactorMap = new HashMap<Integer, Integer>();
	public static ArrayList<String> list = new ArrayList<String>();


	/**
	 *  Given positive integer n
	 *  Say k is a nearFactor of n if there are some 2 <= d <= n
	 *  such that n/d =k
	 * 	
	 * @param n Number to find near factors of and whether or not it is green or red
	 * @return 1 or -1 depending on whether or not it is red or green
	 */
	public static int findNearFactor(int n){
		int d = 0; //divider
		int k = 1; //nearFactor
		int result = 0; // green or red

		// 1 is green = 1 2 is red = -1
		nearFactorMap.put(1, 1);
		nearFactorMap.put(2, -1);

		// n/d = nearFactor
		while (k <= (n/2)) {
			d = n/k;
			if (n/d == k){
				if (nearFactorMap.containsKey(k)){
					result += nearFactorMap.get(k);
				}
				else{
					result += findNearFactor(k);
				}
			}
			k++;
		}
		//If more of its nearFactors are red
		if (result <= 0){
			//green
			nearFactorMap.put(n, 1);          
			return 1;
		} else {
			//red
			nearFactorMap.put(n, -1);
			return -1;
		}
	}

	public static void main(String[] args) {
		int first;
		int last;

		Scanner sc = new Scanner(System.in);
		while(sc.hasNextLine()){
			String readIn = sc.nextLine();
			if (!readIn.equals("") && readIn.charAt(0) != '#' && 
					Character.isDigit(readIn.charAt(0))) {
				list.add(readIn);
			}
		}
		sc.close();


		int index = 0;
		for(String str : list){

			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) == ' ') {
					index = i;
					break;
				}
			}

			String one = str.substring(0, index);
			String two = str.substring(index + 1);

			if (one.matches("\\d+") && two.matches("\\d+")) {
				first = Integer.parseInt(one);
				last = Integer.parseInt(two);

				System.out.println(str);
				System.out.print("# ");
				for (int i = first; i < first + last; i++) {
					if (!nearFactorMap.containsKey(i)) {
						findNearFactor(i);
					}
					if (nearFactorMap.get(i) > 0) {
						System.out.print("G");
					} else {
						System.out.print("R");
					}
				}
			}
		}

	}
}
