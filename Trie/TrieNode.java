package Trie;


import Util.NodeInterface;


public class TrieNode<T> implements NodeInterface<T> {
    TrieNode<T>[] trienode;
    T val;
    String key;
    boolean bool;
    TrieNode(T value,boolean b){
      this.val=value;
      this.bool=b;
      trienode=new TrieNode[128];
    }
    
    public T getValue() {
        return this.val;
    }


}
