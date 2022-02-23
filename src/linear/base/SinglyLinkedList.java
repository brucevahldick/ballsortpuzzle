package linear.base;

public class SinglyLinkedList<E> implements LinkedList<E> {

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

    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void insert(int idx, E e) {
        if (idx == 0) {
            addFirst(e);
        } else if (idx >= size) {
            addLast(e);
        } else {

            Node<E> aux = head;
            for (int i = 0; i < idx - 1; i++) {
                aux = aux.getNext();
            }

            Node<E> node = new Node<>(e, aux.getNext());
            aux.setNext(node);
            size++;
        }
    }

    public E remove(int idx) {
        
        if(isEmpty())
            return null;
        if(idx == 0)
            return removeFirst();
        
        if(idx >= size)
            idx = size - 1;
        
        Node<E> aux = head;
        for (int i = 0; i < idx - 1; i++) {
            aux = aux.getNext();
        }
        
        E s = aux.getNext().getElement();
        aux.setNext(aux.getNext().getNext());
        size--;
        
        if(aux.getNext() == null)
            tail = aux;
        
        return s;
    }

    public E first() {
        if (isEmpty()) {
            return null;
        }
        return head.getElement();
    }

    public E last() {
        if (isEmpty()) {
            return null;
        }
        return tail.getElement();
    }

    public void addFirst(E e) {
        head = new Node<>(e, head);
        if (size == 0) {
            tail = head;
        }
        size++;
    }

    public void addLast(E e) {
        Node<E> newest = new Node<>(e, null);
        if (isEmpty()) {
            head = newest;
        } else {
            tail.setNext(newest);
        }
        tail = newest;
        size++;
    }

    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }
        E answer = head.getElement();
        head = head.getNext();
        size--;
        if (size == 0) {
            tail = null;
        }
        return answer;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        Node<E> walk = head;
        while (walk != null) {
            sb.append(walk.getElement());
            if (walk != tail) {
                sb.append(", ");
            }
            walk = walk.getNext();
        }
        sb.append(")");
        return sb.toString();
    }
}
