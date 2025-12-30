package bst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import bst.BSTVisualizer;



public class BinarySearchTree<E> {
  BinaryNode<E> root;  // Används också i BSTVisaulizer
  int size;            // Används också i BSTVisaulizer
  private Comparator<E> comparator;
  //private ArrayList<E> node_list = new ArrayList<>();
    
	/**
	 * Constructs an empty binary search tree.
	 */
	public BinarySearchTree() {
		root = null;
		size = 0;
		comparator = (e1, e2) -> ((Comparable<E>) e1).compareTo(e2);
	}
	
	/**
	 * Constructs an empty binary search tree, sorted according to the specified comparator.
	 */
	public BinarySearchTree(Comparator<E> comparator) {
		root = null;
		size = 0;
		this.comparator = comparator;
	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * @param e element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E e) {
		size++;
		//node_list.add(e);
		if(root == null) {
			root = new BinaryNode<E>(e);
			return true;
		}
		
		return(add(e, root));
		
	}
	private boolean add(E e, BinaryNode<E> node) {
		int comp = comparator.compare(e, node.element);

		if(comp == 0) {
			size--;
			return false;
		}
		else if(comp < 0) {
			if(node.left == null) {
				node.left = new BinaryNode<E>(e);
				return true;
			}
			else {
				return add(e, node.left);
			}
		}

		else {
			if(node.right == null) {
				node.right = new BinaryNode<E>(e);
				return true;
			}
			else {
				return add(e, node.right);
			}
		}
	}
	
	/**
	 * Computes the height of tree.
	 * @return the height of the tree
	 */
	public int height() {
		return height(root);
		
	}

	private int height(BinaryNode<E> node) {
		if(node == null) return 0;
		else {
			return 1 + Math.max(height(node.left), height(node.right));
		}
	}
	
	
	/**
	 * Returns the number of elements in this tree.
	 * @return the number of elements in this tree
	 */
	public int size() {
		return size;
	}


	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		inorder(root, sb);
		return sb.toString();
	}

	private void inorder(BinaryNode<E> n, StringBuilder sb) {
		if (n == null) return;
		inorder(n.left, sb);
		sb.append(n.element).append(". ");
		inorder(n.right, sb);
	}

	
	

	
	/**
	 * Removes all of the elements from this list.
	 */
	public void clear() {
		root = null;
		size = 0;
	}

	/** 
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {
		ArrayList<E> sorted = new ArrayList<>(size);
		toArray(root, sorted);       
		//sorted.sort(comparator);                
		root = buildTree(sorted, 0, sorted.size() - 1); 
		
	}
	
	/*
	 * Adds all elements from the tree rooted at n in inorder to the list sorted.
	 */
	private void toArray(BinaryNode<E> n, ArrayList<E> sorted) {
		if (n == null) return;
		toArray(n.left, sorted);
		sorted.add(n.element);
		toArray(n.right, sorted);
	}
	
	/*
	 * Builds a complete tree from the elements from position first to 
	 * last in the list sorted.
	 * Elements in the list a are assumed to be in ascending order.
	 * Returns the root of tree.
	 */
	
	private BinaryNode<E> buildTree(ArrayList<E> sorted, int first, int last) {
		int mid_point = Mid_point(first, last);
			root = new BinaryNode<>(sorted.get(mid_point));
			

			buildTree(root, mid_point, sorted, first, last);
		return root;
	}
	private void buildTree(BinaryNode<E> n, int mid_point, ArrayList<E> sorted, int first, int last) {

		 if (first <= mid_point - 1) {
			int mid_point_l = Mid_point(first, mid_point - 1);
			n.left = new BinaryNode<>(sorted.get(mid_point_l));
			buildTree(n.left, mid_point_l, sorted, first, mid_point - 1);
		}

		
		if (mid_point + 1 <= last) {
			int mid_point_r = Mid_point(mid_point + 1, last);
			n.right = new BinaryNode<>(sorted.get(mid_point_r));
			buildTree(n.right, mid_point_r, sorted, mid_point + 1, last);
		}

	}
	
	

	private int Mid_point(int start, int end) {
		return start + (end - start) / 2;
	}
	
	static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
		}	
	}
	

	public static void main(String[] args) throws Exception {
        BinarySearchTree<String> tree = new BinarySearchTree<>(
			(a, b) -> Integer.compare(
				Integer.parseInt(a),
				Integer.parseInt(b)
			));

		
		tree.add("1");
		tree.add("2");
		tree.add("3");
		tree.add("9");
		tree.add("5");
		tree.add("7");
		tree.add("8");
		tree.add("20");
		
		

		tree.rebuild();

		BSTVisualizer canvas = new BSTVisualizer("tree", 600, 600);
		canvas.drawTree(tree);
		System.out.println(tree.toString());
    }

}
