
import java.util.Iterator;
import java.util.NoSuchElementException;

public class OneDirList<T> implements Iterable<T> {
    private ListItem<T> head;
    private ListItem<T> tail;
    
    public void addToHead(T value) {
        if(head != null) {
            ListItem<T> newItem = new ListItem<>(value);
            newItem.next = head;
            head = newItem;
        } else
            head = tail = new ListItem<>(value);
    }


    public T peekFromHead() {
        return head != null ? head.value : null;
    }
    
    public T removeFromHead() {
        if(head == null)
            return null;
        T res = head.value;
        if(head != tail)
            head = head.next;
        else
            head = tail = null;
        return res;
    }
    
    public void addToTail(T value) {
        if(tail != null) {
            tail.next = new ListItem<>(value);
            tail = tail.next;
        } else
            head = tail = new ListItem<>(value);
    }
    
    public T peekFromTail() {
        return tail != null ? tail.value : null;
    }
    
    public T removeFromTail() {
        if(tail == null)
            return null;
        
        T res = tail.value;
        if(head != tail) {
            ListItem<T> it = head;
            while(it.next != tail)
                it = it.next;
            tail = it;
            tail.next = null;
        } else
            head = tail = null;
        return res;
    }
    
    public boolean contains(T value) {
        return findItem(value) != null;
    }
    
    private ListItem<T> findItem(T value) {
        ListItem<T> it = head;
        while(it != null) {
            if(it.checkValue(value))
                return it;
            it = it.next;
        }
        return null;
    }
    
    public boolean isEmpty() {
        return head == null;
    }
    
    public void printAll() {
        ListItem<T> it = head;
        while(it != null) {
            System.out.println(it.value);
            it = it.next;
        }
    }
    
    public void remove(T value) {
        if(head == null)
            return;
        if(head.checkValue(value)) {
            removeFromHead();
            return;
        }
        ListItem<T> prev = head,
                it = head.next;
        while(it != null) {
            if(it.checkValue(value)) {
                if(it == tail)
                    removeFromTail();
                else
                    prev.next = it.next;
                return;
            }
            prev = it;
            it = it.next;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator<>(head);
    }
    
    public Iterable<T> after(T val) {
        /*return new Iterable<T>() {
            @Override
            public Iterator<T> iterator() {
                ListItem<T> start = findItem(val);
                return new ListIterator<>(start);
            }
        };*/
        return () -> new ListIterator<>(findItem(val));
    }

    public Iterable<T> before(T val) {
        return () -> new BeforeListIterator<>(head, val);
    }
    
    private static class ListItem<V> {
        V value;
        ListItem<V> next;

        ListItem(V value) {
            this.value = value;
        }
        
        boolean checkValue(V value) {
            return value == null && this.value == null
                    || value != null && value.equals(this.value);
        }
    }
    
    private static class ListIterator<V> implements Iterator<V> {
        private ListItem<V> nextNode;

        public ListIterator(ListItem<V> startNode) {
            this.nextNode = startNode;
        }
        
        @Override
        public boolean hasNext() {
            return nextNode != null;
        }

        @Override
        public V next() {
            if(nextNode == null)
                throw new NoSuchElementException();
            V res = nextNode.value;
            nextNode = nextNode.next;
            return res;
        }
    }
    
    private static class BeforeListIterator<V> implements Iterator<V> {
        private ListItem<V> nextNode;
        private V finishVal;

        public BeforeListIterator(ListItem<V> startNode, V finishVal) {
            this.nextNode = startNode;
            this.finishVal = finishVal;
        }
        
        @Override
        public boolean hasNext() {
            return nextNode != null && !nextNode.checkValue(finishVal);
        }

        @Override
        public V next() {
            if(nextNode == null || nextNode.checkValue(finishVal))
                throw new NoSuchElementException();
            V res = nextNode.value;
            nextNode = nextNode.next;
            return res;
        }
    }
}
