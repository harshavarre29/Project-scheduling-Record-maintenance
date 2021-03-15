Name : Varre Harsha Vardhan 
Entry_no: 2018mt60796

ASSIGNMENT4-----

this assignment is comparision of the following dictionaries.

1> TRIE --
	it is a data structure.  Using Trie, search complexities can be brought to optimal limit (key length).Every node of Trie consists of multiple branches. Each branch represents a possible character of keys. We need to mark the last node of every key as end of word node. A Trie node field isEndOfWord is used to distinguish the node as end of word node.
	Inserting a key into Trie is a simple approach. Every character of the input key is inserted as an individual Trie node. Note that the children is an array of pointers (or references) to next level trie nodes. The key character acts as an index into the array children. If the input key is new or an extension of the existing key, we need to construct non-existing nodes of the key, and mark end of the word for the last node. If the input key is a prefix of the existing key in Trie, we simply mark the last node of the key as the end of a word. The key length determines Trie depth.
	Searching for a key is similar to insert operation, however, we only compare the characters and move down. The search can terminate due to the end of a string or lack of key in the trie. In the former case, if the isEndofWord field of the last node is true, then the key exists in the trie. In the second case, the search terminates without examining all the characters of the key, since the key is not present in the trie.

2> HEAP(Priorityqueue).
	A max-heap is a complete binary tree in which the value in each internal node is greater than or equal to the values in the children of that node.
-->here we represent this by using an array as
	Arr[(i-1)/2] Returns the parent node.
	Arr[(2*i)+1] Returns the left child node.
	Arr[(2*i)+2] Returns the right child node.
	Inserting a new key takes O(Log n) time. We add a new key at the end of the tree. If new key is smaller than its parent, then we don’t need to do anything. Otherwise, we need to traverse up to fix the violated heap property.
	Removes the maximum element from MaxHeap. Time Complexity of this Operation is O(Log n) as this operation needs to maintain the heap property (by calling heapify()) after removing root.

3> RED-BLACK TREE(balanced bst).
--> properties of red-black trees.
1) Every node has a color either red or black.

2) Root of tree is always black.

3) There are no two adjacent red nodes (A red node cannot have a red parent or red child).

4) Every path from a node (including root) to any of its descendant NULL node has the same number of black nodes.

--> the search in red black tree is same as in bst.as the tree is balenced the complexity will be less.

	insertion can be done by two cases 
1-- recoloring
2-- rotation.

rotation will stop.  recoloring propagates.



