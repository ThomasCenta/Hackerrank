package Functions;
import java.util.LinkedList;
import java.util.Queue;

public class RandomFunctions {
	
	public static long pow(long base, int power) {
		if(power == 0) {
			return 1;
		}
		long half = pow(base, power/2);
		long toReturn = half*half;
		if(power % 2 == 1) {
			toReturn *= base;
		}
		return toReturn;
	}

	public static String matrixToString(int[][] arr) {
		if(arr.length == 0) {
			return "[]";
		}
		StringBuilder strBld = new StringBuilder();
		strBld.append("[");
		strBld.append(ArrayFunctions.arrayToString(arr[0]));
		for(int i = 1; i < arr.length; i += 1) {
			strBld.append("\n ");
			strBld.append(ArrayFunctions.arrayToString(arr[i]));
		}
		strBld.append("]");
		return strBld.toString();
	}
	
	public static String queueToString(Queue<int[]> queue) {
		if(queue.size() == 0) {
			return "{}";
		}
		StringBuilder strBld = new StringBuilder();
		strBld.append("{");
		strBld.append(ArrayFunctions.arrayToString(queue.peek()));
		for(int i = 1; i < queue.size(); i += 1) {
			strBld.append(", ");
			strBld.append(ArrayFunctions.arrayToString(queue.peek()));
			queue.add(queue.poll());
		}
		strBld.append("}");
		return strBld.toString();
	}
	
    
	
	
	
	
	//note that x is index at 0 from left and y is indexed at 0 from top
	public static Queue<int[]> getPossibleKnightMoves(int boardWidth, int boardHeight, int knightX, int knightY, int initialLength, int secondLength){
		Queue<int[]> toReturn = new LinkedList<int[]>();
		if(knightY-initialLength >= 0 && knightX-secondLength >= 0) {
			int[] toAdd = {knightX-secondLength, knightY-initialLength};
			toReturn.add(toAdd);
		}
		if(knightY-initialLength >= 0 && knightX+secondLength < boardWidth) {
			int[] toAdd = {knightX+secondLength, knightY-initialLength};
			toReturn.add(toAdd);
		}
		if(knightY+initialLength < boardHeight && knightX-secondLength >= 0) {
			int[] toAdd = {knightX-secondLength, knightY+initialLength};
			toReturn.add(toAdd);
		}
		if(knightY+initialLength < boardHeight && knightX+secondLength < boardWidth) {
			int[] toAdd = {knightX+secondLength, knightY+initialLength};
			toReturn.add(toAdd);
		}
		if(knightX+initialLength < boardWidth && knightY+secondLength < boardHeight) {
			int[] toAdd = {knightX+initialLength, knightY+secondLength};
			toReturn.add(toAdd);
		}
		if(knightX+initialLength < boardWidth && knightY-secondLength >= 0) {
			int[] toAdd = {knightX+initialLength, knightY-secondLength};
			toReturn.add(toAdd);
		}
		if(knightX-initialLength >= 0 && knightY+secondLength < boardHeight) {
			int[] toAdd = {knightX-initialLength, knightY+secondLength};
			toReturn.add(toAdd);
		}
		if(knightX-initialLength >= 0 && knightY-secondLength >= 0) {
			int[] toAdd = {knightX-initialLength, knightY-secondLength};
			toReturn.add(toAdd);
		}
		return toReturn;
	}
	
	public static boolean contains(Queue<Integer> queue, int toFind) {
		for(int i = 0; i < queue.size(); i +=1) {
			int next = queue.poll();
			queue.add(next);
			if(next == toFind) {return true;}
		}
		return false;
	}
	
}
