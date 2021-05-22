package textgen;
import java.util.AbstractList;

public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	public MyLinkedList() {
		size=0;
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		head.next=tail;
		tail.prev=head;
	}
	public boolean add(E element) 
	{
		LLNode<E>n=new LLNode<E>(element);
		n.prev=tail.prev;
		n.next=n.prev.next;
		n.next.prev=n;
		n.prev.next=n;
		size++;
		return true;
	}
	public E get(int index) 
	{
		index++;
		if(index<1||index>size||size==0)throw new IndexOutOfBoundsException("Out of bound");
		int ic=0;
		LLNode<E>curr=head;
		while(curr!=null){
			if(index==ic){
				//System.out.println(curr.data);
				return curr.data;
			}
			else{
				ic++;
				curr=curr.next;
			}
		}
		return null;
		
	}
	public void add(int index, E element ) 
	{
		if(element==null)throw new NullPointerException();
		index++;
		if(index<1||index>size+1)throw new IndexOutOfBoundsException("Out of bound");
		LLNode<E>curr=head;
		for(int i=0;i<index-1;i++)curr=curr.next;
		LLNode<E>n= new LLNode<E>(element);
		n.next=curr.next;
		n.prev=n.next.prev;
		n.next.prev=n;
		n.prev.next=n;
		size++;
	}
	public int size() 
	{
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		index++;
		if(index<1||index>size)throw new IndexOutOfBoundsException("Out of bound");
		if(size==0)throw new NullPointerException();
		LLNode<E>curr=head;
		for(int i=0;i<index;i++)curr=curr.next;
		curr.next.prev=curr.prev;
		curr.prev.next=curr.next;
		size--;
		//System.out.println("Removing data: "+curr.data);
		return curr.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		if(element==null)throw new NullPointerException();
		if(index<0||index>size||size==0)throw new IndexOutOfBoundsException("Out of bound");
		LLNode<E>curr=head;
		for(int i=0;i<index;i++)curr=curr.next;
		curr.data=element;
		System.out.println(index+": "+element);
		return null;
	}   
    public void iterateForward(){
        
        //System.out.println("iterating forward..");
        LLNode<E> tmp = head;
        while(tmp != null){
            System.out.println(tmp.data);
            tmp = tmp.next;
        }
    }

	public static void main(String a[]){
        
        MyLinkedList<Integer> lst = new MyLinkedList<Integer>();
        lst.add(15);
        lst.add(500);
        lst.add(23);
        lst.add(42);
        lst.add(3, 250);
        
        lst.set(2, 5500);

        lst.iterateForward();
        
        lst.remove(4);
        
        lst.iterateForward();

        System.out.println("Get values: ");
        System.out.println("Queue size : "+lst.size());
        for(int i=0;i<lst.size();i++)lst.get(i);
    }

}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}
	public LLNode(E e, LLNode<E>prevNode, LLNode<E>nextNode){
		this(e);
	}

}
