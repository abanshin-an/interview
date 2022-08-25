package ru.interview.lesson2;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.insert(30);
        list.insert(10);
        list.insert(5);
        list.insertLast(25);
        list.display();
        System.out.println("Удаление элементов списка");
        list.delete(25);
        list.deleteLast();
        list.deleteFirst();
        list.display();
        ArrayList<Integer> arraylist = new ArrayList<>(10);
        arraylist.add(4);
        arraylist.add(0, 33);
        arraylist.add(1, 23);
        arraylist.display();
        arraylist.remove(1);
        arraylist.display();
    }
}

class Link {
    final Integer value;
    Link next;
    Link prev;

    public Link(Link prev, int value, Link next) {
        this.value = value;
        this.next = next;
        this.prev = prev;
    }

    public void display() {
        System.out.println("Value: " + this.value);
    }

    public int getValue() {
        return value;
    }

}

class LinkedList {
    Link first;
    Link last;


    private int size = 0;

    public LinkedList() {
        first = null;
        last = null;
    }

    public Integer getFirst() {
        return first.value;
    }

    public Integer getLast() {
        return last.value;
    }

    public boolean isEmpty() {
        return (first == null);
    }

    public int getSize() {
        return size;
    }

    public void insert(int value) {
        insertBefore(first, value);
    }

    public void insertLast(int value) {
        insertAfter(last, value);
    }

    public boolean delete(Integer value) {
        if (value == null) {
            for (Link x = first; x != null; x = x.next) {
                if (x.value == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Link x = first; x != null; x = x.next) {
                if (x.getValue() == value) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    public Integer deleteLast() {
        var res = last.value;
        unlink(last);
        return res;
    }

    public Integer deleteFirst() {
        var res = first.value;
        unlink(first);
        return res;
    }

    public void insertBefore(Link link, Integer value) {
        final Link pred = (link == null) ? null : link.prev;
        final Link newLink = new Link(pred, value, link);
        if (link != null) {
            link.prev = newLink;
        }
        if (pred == null)
            first = newLink;
        else
            pred.next = newLink;
        fixFirstLast(newLink);
        size++;
    }

    public void insertAfter(Link link, Integer value) {
        final Link next = (link == null) ? null : link.next;
        Link newLink = new Link(link, value, next);
        if (this.isEmpty()) {
            first = newLink;
        } else {
            last.next = newLink;
        }
        last = newLink;
        fixFirstLast(newLink);
        size++;
    }

    private void fixFirstLast(Link newLink) {
        if (last == null) {
            last = newLink;
        }
        if (first == null) {
            first = newLink;
        }
    }

    private void unlink(Link x) {
        if (size == 0)
            return;
        final Link next = x.next;
        final Link prev = x.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.prev = null;
        }
        size--;
    }

    public void display() {
        Link current = first;
        while (current != null) {
            current.display();
            current = current.next;
        }
    }

    public Link find(Integer value) {
        Link current = first;
        while (current.getValue() != value) {
            if (current.next == null)
                return null;
            else
                current = current.next;
        }
        return current;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Link current = first;
        while (current != null) {
            sb.append(current.value).append(' ');
            current = current.next;
        }
        return sb.toString();
    }
}

class ArrayList<T> {
    private int size;
    private Object[] elementData;
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA={};
    private static final Object[] EMPTY_ELEMENTDATA={};
    private static final int DEFAULT_CAPACITY = 10; // Default initial capacity.
    private void ensureCapacityInternal(int minCapacity) {
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        ensureExplicitCapacity(minCapacity);
    }

    private void ensureExplicitCapacity(int minCapacity) {
        // overflow-conscious code
        if (minCapacity - elementData.length > 0)
            grow(minCapacity);
    }
    private void grow(int minCapacity) {
        int oldCapacity = elementData.length;
        if (oldCapacity > 0 || elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            int newCapacity = minCapacity >> 1;
            elementData = Arrays.copyOf(elementData, newCapacity);
        } else {
            elementData = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
    }

    public ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: "+  initialCapacity);
        }
    }

    public void add(T t){
        ensureCapacityInternal(size + 1);  // Size Increments
        elementData[size++] = t;
    }

    public void add(int index, T t) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("Out Of bounds index "+index);
        }
        final int s = size;
        Object[] elementData1 = this.elementData;
        if (s == elementData1.length)
            grow(size+1);
        System.arraycopy(elementData, index,
                elementData, index + 1,
                s - index);
        elementData[index] = t;
        size = s + 1;
    }

    public T remove(int index) {
        rangeCheck(index);
        Object oldValue = elementData[index];
        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index+1, elementData, index, numMoved);
        elementData[--size] = null; // clear to let GC do its work
        return (T)oldValue;
    }

    private void rangeCheck(int index) {
        if (index<0 || index>=size) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public boolean isEmpty(){
        return size==0;
    }

    public int size(){
        return size;
    }

    public void display() {
        System.out.print("\n Array { ");
        if (size>0) {
            System.out.print(""+elementData[0]);
            for (int i = 1; i < size; i++)
                System.out.print(" , " + elementData[i]);
        }
        System.out.println(" } ");
    }

}