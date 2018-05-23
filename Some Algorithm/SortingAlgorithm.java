// 文字描述请参考https://www.cnblogs.com/eniac12/p/5329396.html#s32

package test;

import java.util.Arrays;

public class SortingAlgorithm {

	//冒泡排序
	public static void bubbleSort(int number[]) {
		
		for (int i = 0; i < number.length - 1; i++) {
			for (int j = 0; j < number.length - i - 1; j++) {
				if (number[j] > number[j+1]) {
					number[j] = number[j + 1] - number[j];
					number[j + 1] = number[j+1] - number[j];
					number[j] = number[j] + number[j + 1];
				}
			}
		}
	}
	
	//选择排序
	public static void selectionSort(int number[]) {
		
		for (int i = 0; i < number.length - 1; i++) {
			int k = i;
			for (int j = i + 1; j < number.length; j++) {
				if (number[j] < number[k]) {
					k = j;
				}
			}
			if (k != i) {
				int temp = number[i];
				number[i] = number[k];
				number[k] = temp;
			}
		}
	}
	
	//插入排序
	public static void insertionSort(int number[]) {
		
		for (int i = 1 ; i < number.length; i++) {
			int temp = number[i];
			int j = i - 1;
			while (j >= 0 && temp < number[j]){
				number[j+1] = number[j];
				j--;
			}
			number[j+1] = temp;
		}
	}
	
	//二分法插入排序
	public static void insertionSortDichotomy(int number[]) {
		
		for (int i = 1; i < number.length; i++) {
			int temp = number[i];
			int left = 0;
			int right = i - 1;
			while (left <= right) {
				int mid = (left + right) / 2;
				if (number[mid] > temp)
					right = mid - 1;
				else left = mid + 1;
			}
			for (int j = i - 1; j >= left; j--) {
				number[j+1] = number[j];
			}
			number[left] = temp;
		}
	}
	
	//希尔排序
	public static void shellSort(int number[]) {
		int h = 0;
		while (h <= number.length) {
			h = 3 * h + 1;
		}
		while (h >= 1) {
			for (int i = h; i < number.length; i++) {
				int j = i - h;
				int temp = number[i];
				while (j >= 0 && number[j] > temp) {
					number[j+h] = number[j];
					j = j - h;
				}
				number[j + h] = temp;
			}
			h = (h - 1) / 3;
		}
	}
	
	//归并排序（递归）
	public static void mergingSortRecursion(int num[], int left, int right) {
		if (left == right) return;
		int mid = (left + right) / 2;
		mergingSortRecursion(num, left, mid);
		mergingSortRecursion(num, mid + 1, right);
		merge(num, left, mid, right);
	}
	
	//（非递归）
	public static void mergingSortIteration(int num[]) {
		int left, mid, right;
		for (int i = 1; i < num.length; i *= 2) {
			left = 0;
			while (left + i < num.length) {
				mid = left + i - 1;
				right = mid + i < num.length ? mid + i : num.length - 1;
				merge(num, left, mid, right);
				left = right + 1;
			}
		}
	}
	
	static void merge(int num[], int left, int mid, int right) {
		int len = right - left + 1;
		int temp[] = new int[len];
		int index = 0;
		int i = left;
		int j = mid + 1;
		while (i <= mid && j <= right) {
			temp[index++] = num[i] <= num[j] ? num[i++] : num[j++];
		}
		while (i <= mid) {
			temp[index++] = num[i++];
		}
		while (j <= right) {
			temp[index++] = num[j++];
		}
		for (int k = 0; k < len; k++) {
			num[left++] = temp[k];
		}
	}
	
	//堆排序
	public static void heapSort(int number[]) {
		int heap_size = buildHeap(number, number.length);
		while (heap_size > 1) {
			swap(number, 0, --heap_size);
			heapify(number, 0, heap_size);
		}
	}
	
	static void heapify(int num[], int i, int size) {
		int left_child = 2 * i + 1;
		int right_child = 2 * i + 2;
		int max = i;
		if (left_child < size && num[left_child] > num[max]) {
			max = left_child;
		}
		if (right_child < size && num[right_child] > num[max]) {
			max = right_child;
		}
		if (max != i) {
			swap(num, i, max);
			heapify(num, max, size);
		}
	}
	
	static int buildHeap(int num[],int n) {
		int heap_size = n;
		for (int i = heap_size /2 - 1; i >= 0; i--) {
			heapify(num, i, heap_size);
		}
		return heap_size;
	}
	
	//快速排序
	public static void quickSort(int number[], int left, int right) {
		if (left >= right) return;
		int pivot_index = partition(number,left,right);
		quickSort(number, left, pivot_index - 1); 
		quickSort(number, pivot_index + 1, right);
	}
	
	static int partition(int number[], int left, int right) {
		int pivot = number[right];
		int tail = left - 1;
		for (int i = left; i < right; i++) {
			if (number[i] <= pivot) {
				swap(number, ++tail, i);
			}
		}
		swap(number, tail+1, right);
		return tail + 1;
	}
	
	static void swap(int num[], int i, int j) {
		int temp = num[i];
		num[i] = num[j];
		num[j] = temp;
	}
	
	public static void main(String[] args) {
		int number[] = {15,458,85,36,65,87,97,48,25,36,759,364,35,1,47,674,55,1657,845};
//		bubbleSort(number);
//		selectionSort(number);
//		insertionSort(number);
//		insertionSortDichotomy(number);
//		shellSort(number);
//		mergingSortRecursion(number, 0, number.length - 1);
		mergingSortIteration(number);
//		heapSort(number);
//		quickSort(number, 0, number.length - 1);
		System.out.println(Arrays.toString(number));
	}

}
