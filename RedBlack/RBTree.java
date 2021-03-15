package RedBlack;


public class RBTree<T extends Comparable, E> implements RBTreeInterface<T, E>  {
	RedBlackNode<T,E> root;
	RBTree(){
		root=null;
	}
	private RedBlackNode<T,E> Rotation(RedBlackNode<T,E> node,RedBlackNode<T,E> child) {
		
		if(node.left!=null && node.parent.left!=null &&node.left.t.compareTo(child.t)==0 && node.parent.left.t.compareTo(node.t)==0) {
			RedBlackNode<T,E> temp;
			temp=node.right;
			node.right=node.parent;
			node.parent.left=temp;
			temp=node.parent.parent;
			node.parent.parent=node;
			node.parent=temp;
			node.right.left.parent=node.right;
			node.isred=false;
			node.right.isred=true;
			return node;
			//Do recolouring Also
		}//Tamassia Notation x=node.parent,y=node,z=child
		
		else if(node.left!=null && node.parent.right!=null &&node.left.t.compareTo(child.t)==0 && node.parent.right.t.compareTo(node.t)==0) {
			RedBlackNode<T,E> T1,T2,T3,T4;
			T2=child.left;
			T3=child.right;
			T4=node.right;
			T1=node.parent.left;
			child.left=node.parent;
			child.left.left=T1;
			child.left.right=T2;
			child.right=node;
			child.right.right=T4;
			child.right.left=T3;
			if(T3!=null)
			child.right.left.parent=child.right;
			if(T4!=null)
			child.right.right.parent=child.right;
			if(T1!=null)
			child.left.left.parent=child.left;
			if(T2!=null)
			child.left.right.parent=child.left;
			child.left.parent=child;
			child.right.parent=child;
			child.isred=false;
			child.left.isred=true;
			return child;
		}
		else if(node.right!=null&& node.parent.right!=null &&node.right.t.compareTo(child.t)==0 && node.parent.right.t.compareTo(node.t)==0) {
			RedBlackNode<T,E> temp;
			temp=node.left;
			node.left=node.parent;
			node.parent.right=temp;
			temp=node.parent.parent;
			node.parent.parent=node;
			node.parent=temp;
			node.left.right.parent=node.left;
			node.isred=false;
			node.left.isred=false;
			return node;
		}
		else if(node.right!=null && node.parent.left!=null &&node.right.t.compareTo(child.t)==0&& node.parent.left.t.compareTo(node.t)==0) {
			RedBlackNode<T,E> T1,T2,T3,T4;
			T1=node.left;
			T2=child.left;
			T3=child.right;
			T4=node.parent.right;
			child.left=node;
			child.right=node.parent;
			child.left.left=T1;
			child.left.right=T2;
			child.right=node;
			child.right.right=T4;
			child.right.left=T3;
			if(T3!=null)
			child.right.left.parent=child.right;
			if(T4!=null)
			child.right.right.parent=child.right;
			if(T1!=null)
			child.left.left.parent=child.left;
			if(T2!=null)
			child.left.right.parent=child.left;
			child.left.parent=child;
			child.right.parent=child;
			child.isred=false;
			child.right.isred=true;
			return child;
			
		}
		return null;
	}
	private void recoulring(RedBlackNode<T,E> node,RedBlackNode<T,E> child){
		if(node.parent.right.t.compareTo(node.t)==0) {
			node.isred=false;
			node.parent.isred=true;
			node.parent.left.isred=false;
			//return node.parent;
		}
		else {
			node.isred=false;
			node.parent.isred=true;
			node.parent.right.isred=false;
			//return node.parent;
		}
		//node=node.parent;
		if(node.parent.parent==null) {//IF Child of root
			node.parent.isred=false;
		}
		else {
			if(node.parent.parent.isred) {
				if(node.parent.parent.parent.right.t.compareTo(node.parent.parent.t)==0) {
					if(node.parent.parent.parent.left.isred) {
						recoulring(node.parent.parent,node.parent);
					}
					else {
						node.parent.parent.parent=Rotation(node.parent.parent,node.parent);
						//restructure
					}
				}
				else {
					if(node.parent.parent.parent.right.isred) {
						recoulring(node.parent.parent,node.parent);
					}
					else {
						node.parent.parent.parent=Rotation(node.parent.parent,node.parent);
						//restructure
					}
				}
			}
		}
	}
	private RedBlackNode<T,E> Insert(RedBlackNode<T,E> node,T key,E value){
		if(node==null) {
			node=new RedBlackNode(key,value,true);
			node.insert(value);
			return node;
		}
		else {
			if(node.t.compareTo(key)>0) {
				node.right=Insert(node.right,key,value);
				node.right.parent= node;
				//Handled The parent and Child pointer Setting
				if(node.isred) {
					if(node.parent.right!=null &&node.parent.right.t.compareTo(node.t)==0) {
						if(node.parent.left!=null&&node.parent.left.isred) {//Red Uncle
							recoulring(node,node.right);
							return node;
						}
						else {
							return Rotation(node,node.right);
						}
					}
					else {
						if(node.parent.right!=null&&node.parent.right.isred) {
							recoulring(node,node.left);
						}
						else {
							return Rotation(node,node.right);
						}
					}
				}
				//Check if inserted node is red and the current Node is red
				//Resolve double red
				return node;
			}
			else if(node.t.compareTo(key)==0) {
				node.insert(value);
				return node;
			}
			else {
				node.left=Insert(node.left,key,value);
				node.left.parent=node;
				if(node.isred) {
					
					if(node.parent.right!=null && node.parent.right.t.compareTo(node.t)==0) {
						if(node.parent.left!=null&&node.parent.left.isred) {
							recoulring(node,node.right);
						}
						else {
							node.parent=Rotation(node,node.left);
						}
					}
					else {
						if(node.parent.right!=null&& node.parent.right.isred) {
							recoulring(node,node.left);
						}
						else {
							node.parent=Rotation(node,node.right);
						}
					}
				}
				//Check if inserted node is red and the current Node is red
				//Resolve double red
				return node;
			}
		}
		
	}
    @Override
    public void insert(T key, E value) {
    	if(root==null) {
    		root=new RedBlackNode(key,value,false);
    		root.insert(value);
    	}
    	if(root!=null) {
    		RedBlackNode<T,E> node=root;
    		if(node.t.compareTo(key)>0) {
				node.right=this.Insert(node.right,key,value);
				node.right.parent=node;
			}
			else if(node.t.compareTo(key)==0) {
				node.insert(value);
				
			}
			else {
				node.left=this.Insert(node.left,key,value);
				node.left.parent=node;
			}
    	}
    }

    @Override
    public RedBlackNode<T, E> search(T key) {
    	RedBlackNode<T,E> node=root;
    	RedBlackNode<T,E> temp=null;
    	while(true) {
    		if(node==null) {
    			break;
    		}
    		else {
    			if(node.t.compareTo(key)>0) {
        			node=node.right;
        		}
        		else if(node.t.compareTo(key)<0) {
        			node=node.left;
        		}
        		else {
        			temp= node;
        			break;
        		}
    		}
    	}
        return temp;
    }
}