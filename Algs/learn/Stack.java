package algs.emma.learn;

import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.*;

public class Stack<Item> implements Iterable<Item> {
    private Node<Item> first;

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }


    //这是构造函数
    public Stack() {
        first = null;
        //java和js中有多少种null?
        //做一个总结,提示null,NULL,NUL,Nan
    }

    public boolean isEmpty() {
        if (first == null)
            return true;
        else
            return false;
    }

    public void push(Item item) {
        if(isEmpty()) {
            first.item = item;
        }
        else {
            Node<Item> oldfirst = first;
            first.item = item;
            first.next = oldfirst;
        }
    }

    public Item pop() {
        if (isEmpty())
            throw new NoSuchElementException("Stack underflow");
            Item temp = first.item;
            first= first.next;
            return temp;
    }
    public Item peek() {
        if (isEmpty()) {
            throw  new NoSuchElementException("Stack underflow");
        }
        return first.item;
    }
    //所以Iterator和Iterable是干什么的?看Java编程思想和Java核心技术
    public Iterator<Item> iterator() {
        return new ListIterator<Item> (first);
    }

    private class ListIterator<Item> implements Iterator {
        private Node<Item> current;
        public ListIterator(Node<Item> first) {
            current = first;
        }
        public boolean hasNext() {
            return current != null;
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item temp = current.item;
            current = current.next;
            return temp;
        }
    }
}