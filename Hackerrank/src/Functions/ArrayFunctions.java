package Functions;

public class ArrayFunctions {
	public static void shiftLeft(int[] arr, int shiftLeftBy) {
		for(int i = 0; i < arr.length-shiftLeftBy; i += 1) {
			arr[i] = arr[i+shiftLeftBy];
		}
	}
	

	public static int maxContiguousSubarray(int[] arr) {
		int prevMax = 0;
		int max = 0;
		for(int i = 0; i < arr.length; i += 1) {
			int alt = arr[i];
			if(prevMax > 0) {
				alt += prevMax;
			}
			prevMax = alt;
			if(max < alt) {
				max = alt;
			}
		}
		return max;
	}
	
	public static String arrayToString(int[] arr) {
		if(arr.length == 0) {
			return "[]";
		}
		StringBuilder strBld = new StringBuilder();
		strBld.append("[");
		strBld.append(arr[0]);
		for(int i = 1; i < arr.length; i += 1) {
			strBld.append(", ");
			strBld.append(arr[i]);
		}
		strBld.append("]");
		return strBld.toString();
	}
	
	//inclusive lowerIndex, exclusive higherindex, middleIndex belongs to upper portion
		public static void merge(int[] arr, int lowerIndex, int middleIndex, int higherIndex) {
			int[] helper = new int[higherIndex-lowerIndex];
			int j = lowerIndex;
			for(int i = 0; i < helper.length; i += 1) {
				helper[i] = arr[j];
				j += 1;
			}
			int i = lowerIndex;
			int ih = 0;
			int jh = middleIndex-lowerIndex;
			int mhi = middleIndex-lowerIndex;
			while(ih < mhi && jh < helper.length) {
				if(helper[ih] <= helper[jh]) {
					arr[i] = helper[ih];
					ih += 1;
					i += 1;
				}else {
					arr[i] = helper[jh];
					jh += 1;
					i += 1;
				}
			}
			while(ih < mhi) {
				arr[i] = helper[ih];
				ih += 1;
				i += 1;
			}
			while(jh < helper.length) {
				arr[i] = helper[jh];
				jh += 1;
				i += 1;
			}
		}
		
		//inclusive lowerIndex, exclusive higherIndex
		public static void mergeSort(int[] arr,int lowerIndex, int higherIndex) {
			if(higherIndex-lowerIndex <= 1) {
				return;
			}
			int middleIndex = (higherIndex+lowerIndex)/2;
			mergeSort(arr,lowerIndex,middleIndex);
			mergeSort(arr,middleIndex,higherIndex);
			merge(arr,lowerIndex,middleIndex,higherIndex);
		}
		//higherIndex is exclusive, lowerIndex inclusive
		public static int binarySearch(int[] arr, int toFind, int lowerIndex, int higherIndex) {
			if(higherIndex <= lowerIndex+1) {
				if(arr[lowerIndex] == toFind) {
					return lowerIndex;
				}else {
					return -1;
				}
			}
			int middleIndex = (higherIndex+lowerIndex)/2;
			if(arr[middleIndex] <= toFind) {
				return binarySearch(arr, toFind, middleIndex, higherIndex);
			}else {
				return binarySearch(arr, toFind, lowerIndex, middleIndex);
			}
		}
		
		
		public static void printArr(int[] arr) {
			System.out.print(arr[0]);
			for(int i = 1;i < arr.length; i+= 1) {
				System.out.print(" "+arr[i]);
			}
			System.out.print("\n");
		}
}
