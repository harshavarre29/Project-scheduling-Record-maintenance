package PriorityQueue;

public class MaxHeap<T extends Comparable<T>> implements PriorityQueueInterface<T> {
	class Pair<T extends Comparable<T>>{
		int i;
		T t;
		Pair(int i,T t){
			this.i=i;
			this.t=t;
		}
		public int compareTo(Pair<T> p){
			if(this.t.compareTo(p.t)!=0) {
				return this.t.compareTo(p.t);
			}
			else {
				return p.i-this.i;
			}
		}
	}
	Pair<T>[] arr;
	int currentSize;
	public MaxHeap(){
		arr=new Pair[100];
		currentSize=0;
	}
	private void changesize() {
		int a=arr.length;
		Pair<T>[] s=new Pair[2*a];
		for(int i=0;i<arr.length;i++) {
			s[i]=arr[i];
		}
		this.arr=new Pair[2*a];
		for(int i=0;i<arr.length;i++) {
			this.arr[i]=s[i];
		}
	}
	private void arrangeheap(int index) {
		int a=index/2;
		if(index==1) {
			return;
		}
		if(arr[a]!=null&& arr[index]!=null&&arr[a].compareTo(arr[index])>0) {
			Pair<T> t=arr[index];
			arr[index]=arr[a];
			arr[a]=t;
			arrangeheap(a);
		}
		else {
			return;
		}
		
	}
    @Override
    public void insert(T element) {
    	if( currentSize+1 <arr.length)
    	arr[currentSize+1]=new Pair(currentSize+1,element);
    	else {
    		this.changesize();
    		arr[currentSize+1]=new Pair(currentSize+1,element);
    	}
    	this.arrangeheap(currentSize+1);
    	currentSize++;
    }
    private void afterdelete(int index) {
    	if(2*index+1<arr.length) {
    		if(arr[2*index]==null && arr[2*index+1]==null) {
    			int i=index;
    			arr[i]=null;
//    			while(i<2*index+1&& i+1<arr.length) {
//    				arr[i]=arr[i+1];
//    				
//    				i++;
//    			}
    		}
    		if(arr[2*index+1]==null) {
    			arr[index]=arr[2*index];
    			arr[2*index]=null;
    			afterdelete(2*index);
    		}
    		else if(arr[2*index]==null) {
    			arr[index]=arr[2*index+1];
    			arr[2*index+1]=null;
    			afterdelete(2*index+1);
    		}
    		if(arr[2*index]!=null && arr[2*index+1]!=null) {
    			 if(arr[2*index].compareTo(arr[2*index+1])<0) {
        			arr[index]=arr[2*index];
        			arr[2*index]=null;
        			afterdelete(2*index);
        		}
        		else {
        			arr[index]=arr[2*index+1];
        			arr[2*index+1]=null;
        			afterdelete(2*index+1);
        		}
    		}
    	}
    	
    	else {
    		return;
    	}
    }
    @Override
    public T extractMax() {
//    	for(int i=0;i<arr.length;i++) {
//    		if(arr[i]!=null)
//    		System.out.println(arr[i].i+" "+i);
//    	}
    	if(currentSize==0) {
    		return null;
    	}
        T t=arr[1].t;
        this.afterdelete(1);
        currentSize--;
        return t;
    }
    
}