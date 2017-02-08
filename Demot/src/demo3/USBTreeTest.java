package demo3;

public class USBTreeTest
{

	public static void main(String[] args)
	{
		USBTree<Integer> tree = new USBTree<>(0);
		
		for (int i = 1; i < 15; i++)
			tree.add(i);
		
		for (int i = 0; i < 16; i++)
			System.out.println(tree.get(i));
		
		System.out.println(USBTree.routeToIndex(12));

		System.out.println(tree.size());
	}
}