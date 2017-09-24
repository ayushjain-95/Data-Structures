/*
 * https://www.youtube.com/watch?v=v_wj_mOAlig
 * Also called as Fenwik trees
 * It has 2 major parts :-
 * 1)Query -> Find the sum of elements from index l to r where 0 <= l <= r <= n-1  
 * 2)Updating the value
 * If input array is of length 10 then we have 11 nodes in tree...0th node is dummy and doesn't store anything.
 * Note - To get parent of any element...
 * 			1)just get binary equivalent of its index...
 * 			2)find 2s complement of it...
 * 			3)and it with original no...
 * 			4)subtract this from original no
 * 			5)convert back it into decimal and you get its parent
 * 
 * To get next element affected ->just change subtract -> add in step 4
 * If index of parent is x and index of child is y then node[y] will store sum of elements form [x,y)
 *
 *Core Idea --->
 *		suppose we need to find sum of first 13 elements of an array then,
 *		(Note: 13 = 2^3 + 2^2 + 2^0)
 *		sum(13) = range(1,8) + range(9,12) + range(13,13)
 *		i.e, if we have pre calculated  these ranges then sum(13) is easier to find, 
 *				where range(i,j) is sum of elements from index i to j(both inclusive)
 */

public class BinaryIndexedTrees {

	int[] binaryIndexedTreeNodes;
    
	public static void main(String[] args) {
		int[] array = {3,2,-1,6,5,4,-3,3,7,2,3};//{2, 1, 1, 3, 2, 3, 4, 5, 6, 7, 8, 9};
        int n = array.length;
        BinaryIndexedTrees bit = new BinaryIndexedTrees();
        bit.binaryIndexedTreeNodes = new int[n+1];
        for(int i = 0;i < n;i++) {
        	bit.updatebinaryIndexedTree(array, i, array[i]);
        }
        System.out.println(bit.getSum(bit.binaryIndexedTreeNodes, 0));
        
//        for(int node : bit.binaryIndexedTreeNodes) {
//        	System.out.println(node);
//        }
	}
	
	public void updatebinaryIndexedTree(int[] inputArray,int currentIndex,int currentElement) {
		int index = currentIndex + 1;//Index for BIT is 1 greater than input array index
		while(index <= inputArray.length) {
			this.binaryIndexedTreeNodes[index] += currentElement;
			index = this.getNextIndex(index);
		}
	}
	
	public int getSum(int[] binaryIndexedTree,int index) {
		index ++;
		int sum = 0;
		while(index > 0) {
			sum += binaryIndexedTree[index];
			index = this.getParentIndex(index);
		}
		return sum;
	}
	
	
	/*
	 * Helpers....
	 */
	
	public int getNextIndex(int index) {
		index = index + (index & -index);
		return index;
	}
	
	public int getParentIndex(int index) {
		index = index - (index & -index);
		return index;
	}
}
