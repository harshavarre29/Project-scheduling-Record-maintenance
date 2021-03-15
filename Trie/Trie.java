package Trie;

import java.util.Arrays;

public class Trie<T> implements TrieInterface {
	TrieNode<T> root;
	String[] arr;
	String[] s;
	char[] str;
	public Trie(){
		root=null;
		arr=new String[10];
	}
	
	private void changesize() {
		int a=arr.length;
		s=new String[2*a];
		for(int i=0;i<arr.length;i++) {
			s[i]=arr[i];
		}
		arr=new String[2*a];
		for(int i=0;i<arr.length;i++) {
			arr[i]=s[i];
		}
	}
	
	private void Insert(String word) {
		int t=0;
		for(int j=0;j<arr.length;j++) {
    		if(arr[j]==null) {
    			arr[j]=word;
    			t++;
    			break;
    		}
    	}
		if(t==0) {
			changesize();
			Insert(word);
		}
	}
	
	private T printing(String word) {
		T t=null;
    	int i=0;
    	TrieNode<T> tr=root;
    	while(i<word.length()) {
    		int a=word.charAt(i);
    		if(tr.trienode[a]==null) {
    			return null;//no element.
    		}
    		else {
    			if(i==word.length()-1) {
    				if(tr.trienode[a].bool) {
    					t=tr.trienode[a].val;
    				}
    				else {
    					return null;//no element.
    				}
    			}
    				tr=tr.trienode[a];
    			
    		}
    		i++;
    	}
    	return t;
	}
	
	private int count(TrieNode<T> tn) {
		if(tn==null) {
			return 0;
		}
		int x=0;
		for(int i=0;i<tn.trienode.length;i++) {
			if(tn.trienode[i]!=null) {
				x++;
			}
		}
		return  x;
	}
	
    public void printTrie(TrieNode trieNode) {
    	String key=trieNode.key;
    	int len=key.length();
    	Sort();
    	for(int i=0;i<arr.length;i++) {
    		if(arr[i]!=null) {
    			if(arr[i].substring(0,len).equals(key)) {
        			T t=printing(arr[i]);
        			System.out.println(t);
        		}
    		}
    		else {
    			break;
    		}
    	}
    }

    @Override
    public boolean delete(String word) {
        int level=0;
        int i=0;
        boolean bool=false;
        for(int j=0;j<arr.length;j++) {
        	if(arr[j]==null)
        		return false;
        	if(arr[j].equals(word)) {
        		bool=true;
        		for(int z=j;z<arr.length-1;z++) {
        			arr[z]=arr[z+1];
        		}
        		arr[arr.length-1]=null;
        		break;
        	}
        }
        TrieNode<T> tr=root;
        while(i<word.length()) {
        	int index=word.charAt(i);
        	int child=count(tr);
        	if(i!=word.length()-1) {
        		if(child==0) {
            		return false;
            	}
        		else if(child>1||tr.trienode[index].bool) {
        			level=i+1;
        		}
        	}
        	else {
        		if(child==0) {
        			return false;
        		}
        		else
        		if(child>1) {
        			level=i+1;
        		}
        	}
        	i++;
        	tr=tr.trienode[index];
        }
            i=0; 
        	TrieNode<T> tri=root;
        	if(level==word.length()) {
        		while(i<word.length()) {
        			int index=word.charAt(i);
        			if(i==level) {
        				tri.trienode[index].val=null;
        				tri.trienode[index].bool=false;
        				return true;
        			}
        			tri=tri.trienode[index];
        			i++;
        		}
        	}
        	else {
        		
        			while(i<=level-1) {
                		int index=word.charAt(i);
                		tri=tri.trienode[index];
                		i++;
                	}
        		
        		tri.trienode[(int)word.charAt(level)]=null;
        		return true;
        	}	
        return false;
    }

    @Override
    public TrieNode search(String word) {
    	T t=null;
    	int i=0;
    	TrieNode<T> tr=root;
    	if(root==null)
    	{
    		return null;
    	}
    	while(i<word.length()) {
    		int a=word.charAt(i);
    		if(tr.trienode[a]==null) {
    			return null;//no element.
    		}
    		else {
    			if(i==word.length()-1) {
    				if(tr.trienode[a].bool) {
    					t=tr.trienode[a].val;
    				}
    				else {
    					return null;//no element.
    				}
    			}
    				tr=tr.trienode[a];
    			
    		}
    		i++;
    	}
		return new TrieNode<T>(t,true);
    }
    private void Sort() {
    	int n=arr.length;
    	  for (int i = 0; i < n-1; i++) 
              for (int j = i; j < n-1; j++) 
                  if (arr[j]!=null&& arr[j+1]!=null&& arr[j].compareTo(arr[j+1])>=0) 
                  { 
                      // swap arr[j+1] and arr[i] 
                      String temp = arr[j+1]; 
                      arr[j+1] = arr[j]; 
                      arr[j] = temp; 
                  } 
      } 
    
    @Override
    public TrieNode startsWith(String prefix) {
    	T t=null;
    	TrieNode<T> tr=root;
    	int i=0;
    	//Sort();
    	while(i<prefix.length()) {
    		int a=prefix.charAt(i);
    		if(tr.trienode[a]==null) {
    			return null;
    		}
    		else {
    			if(i==prefix.length()-1) {
    				tr.trienode[a].key=prefix;
    				return tr.trienode[a];
    			}
    			else {
    				tr=tr.trienode[a];
    			}
    		}
    		i++;
    	}
    	
       return null; 
    }
    @SuppressWarnings("all")
    @Override
    public boolean insert(String word, Object value) {
    	int i=0;
    	this.Insert(word);
    	TrieNode<T>tr=root;
    	if(tr==null)
    	{
    		root=new TrieNode<T>(null,false);
    		tr=root;
    		if(word.length()==0)
    		{
    			tr.val=(T)value;
    			tr.bool=true;
    			return true;
    		}
    		else if (word.length()==1)
    		{
    			tr.trienode[(int)word.charAt(0)]=new TrieNode<T>((T)value,true);
    			return true;
    		}
    	}
        while(i<word.length()) {
        	int a= word.charAt(i);
        	if(tr.trienode[a]!=null) {
        		if(i==word.length()-1) {
        			if(tr.trienode[a].bool==true) {
        				return false;
        			}
        			else {
        				tr.trienode[a].bool=true;
        				tr.trienode[a].val=(T)value;
        			}
        		}
        		tr=tr.trienode[a];
        	}
        	else {
        		if(i!=word.length()-1) {
        			tr.trienode[a]=new TrieNode<T>(null,false);
            		tr=tr.trienode[a];
        		}
        		else {
        			tr.trienode[a]=new TrieNode<T>((T)value,true);
            		tr=tr.trienode[a];
            	}
        	}
        	i++;
        }
        return true;
    }
    public void Printlevel(TrieNode<T> node,int level,int clevel) {
    	int i=0;
    	if(level==clevel) {
    		while(i<127) {
    			if(node.trienode[i]!=null) {
    				for(int j=0;j<str.length;j++) {
    					if(str[j]=='\u0000') {
    						char c=(char)i;
    						//System.out
    						str[j]=c;
    						break;
    					}
    				}
    			}
    			i++;
    		}
    	}
    	else {
    		while(i<127) {
    			if(node.trienode[i]!=null) {
    				Printlevel(node.trienode[i],level,clevel+1);
    			}
    			i++;
    		}
    	}
    }
    @Override
    public void printLevel(int level) {
    	System.out.print("level "+ level+":");
    	str=new char[arr.length];
    	int k=0;
    	Printlevel(root,level,1);
    	  for (int i = 0; i < str.length-1; i++) 
              for (int j = 0; j < str.length-i-1; j++) {
            	  int b=str[j+1];
            	  int a=str[j];
                  if (a>(b)) 
                  { 
                      // swap arr[j+1] and arr[i] 
                      char temp = str[j]; 
                      str[j] = str[j+1]; 
                      str[j+1] = temp; 
                  } 
              }
    	for(int i=0;i<arr.length;i++) {
    		if(str[i]!='\u0000'&& str[i]!=' ') {
    			System.out.print(str[i]+" ");
    		}
    	}
    	System.out.println();
    }

    @Override
    public void print() {
    	int k=0;
    	for(int i=0;i<arr.length;i++) {
    		if(arr[i]!=null) {
    			if(k<arr[i].length()) {
        			k=arr[i].length();
        		}
    		}
    		else {
    			break;
    		}
    	}
    	for(int i=0;i<k+1;i++) {
    		printLevel(i+1);
    		//System.out.println();
    	}
    }
    /*public static void main(String[]args) {
    	Trie t=new Trie();
    	t.insert("A","B");
    	t.insert("x","y");
    	System.out.println(t.search("x"));
    }*/
}