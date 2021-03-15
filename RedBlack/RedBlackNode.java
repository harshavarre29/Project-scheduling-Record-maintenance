package RedBlack;

import Util.RBNodeInterface;
import java.util.ArrayList;
import java.util.List;
public class RedBlackNode<T extends Comparable, E> implements RBNodeInterface<E> {
	T t;
	E e;
	boolean isred;
	RedBlackNode<T,E> right,left,parent;
	int no_of_e=0;
	ArrayList<E> alist=new ArrayList<E>(5);
	RedBlackNode(T t,E e,boolean b){
		this.t=t;
		this.e=e;
		this.right=null;
		this.left=null;
		this.isred=b;
		this.parent=null;
	}
	public void insert(E e) {
		alist.add(e);
		no_of_e++;
	}

    @Override
    public E getValue() {
        return alist.get(0);
    }

    @Override
    public List<E> getValues() {
        return alist;
    }
    public int compareTo(T val) {
    	if(this.alist.toString().compareTo(val.toString())==0)
    	return 0;
    	else if(this.alist.toString().compareTo(val.toString())>0)
    		return 1;
    	else
    		return -1;
    }
}
