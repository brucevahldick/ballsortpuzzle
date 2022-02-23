package linear.base;

public class CircularlyLinkedList<E> implements LinkedList<E> {

	@SuppressWarnings("hiding")
	private class Node<E> {
		private E element;
		private Node<E> next;

		public Node(E e, Node<E> n) {
			element = e;
			next = n;
		}

		public E getElement() {
			return element;
		}

		public Node<E> getNext() {
			return next;
		}

		public void setNext(Node<E> n) {
			next = n;
		}
		
	}

	public Node<E> tail = null;
	private int size = 0;

        public void inverse() {
            if(tail == null){
                return;
            }
            // pega a seguinte da tail e entra no loop para inverter os próximos
            Node<E> aux = tail.getNext();
            invertNode(aux, aux.getNext());
            // aqui sai do loop e redefine a tail da lista circular
            aux.setNext(tail);
            tail = aux;
        }
        
        private void invertNode(Node<E> bef, Node<E> e){
            if(e != tail)
                invertNode(e, e.getNext());
            e.setNext(bef);
        }
        
	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public E first() {
		if (isEmpty())
			return null;
		return tail.getNext().getElement();
	}

	public E last() {
		if (isEmpty())
			return null;
		return tail.getElement();
	}

	public void rotate() {
		if (tail != null)
			tail = tail.getNext();
	}

	public void addFirst(E e) {
		if (size == 0) {
			tail = new Node<>(e, null);
			tail.setNext(tail);
		} else {
			Node<E> newest = new Node<>(e, tail.getNext());
			tail.setNext(newest);
		}
		size++;
	}

	public void addLast(E e) {
		addFirst(e);
		tail = tail.getNext();
	}

	public E removeFirst() {
		if (isEmpty())
			return null;
		Node<E> head = tail.getNext();
		if (head == tail)
			tail = null;
		else
			tail.setNext(head.getNext());
		size--;
		return head.getElement();
	}

	public String toString() {
		if (tail == null)
			return "()";
		StringBuilder sb = new StringBuilder("(");
		Node<E> walk = tail;
		do {
			walk = walk.getNext();
			sb.append(walk.getElement());
			if (walk != tail)
				sb.append(", ");
		} while (walk != tail);
		sb.append(")");
		return sb.toString();
	}
	
}