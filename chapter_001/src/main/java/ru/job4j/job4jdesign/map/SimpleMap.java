package ru.job4j.job4jdesign.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Iterable<SimpleMap.Entry<K, V>> {
    private Object[] table;
    private final int fTABLESIZE;
    private int size = 0;
    private int modCount = 0;

    public SimpleMap() {
        fTABLESIZE = 16;
        table = new Object[fTABLESIZE];
    }

    public V put(K key, V value) {
        int hash = Math.abs(key.hashCode() % fTABLESIZE);
        Node node = new Node(hash, key, value);
        Node nodeInBucket = (Node) table[hash];
        Node previousNodeInBucket = null;
        if (nodeInBucket == null) {
            table[hash] = node;
            size++;
            modCount++;
        } else {
            do {
                if (nodeInBucket.key.equals(node.key) && nodeInBucket.key.hashCode() == node.key.hashCode()) {
                    V result = nodeInBucket.value;
                    nodeInBucket.value = node.value;
                    modCount++;
                    return result;
                }
                previousNodeInBucket = nodeInBucket;
                nodeInBucket = nodeInBucket.next;
            } while (nodeInBucket != null);
            previousNodeInBucket.next = node;
            modCount++;
            size++;
        }
        return null;
    }

    public V get(K key) {
        int hash = Math.abs(key.hashCode() % fTABLESIZE);
        Node nodeInBucket = (Node) table[hash];
        if (nodeInBucket != null) {
            do {
                if (nodeInBucket.key.equals(key) && nodeInBucket.key.hashCode() == key.hashCode()) {
                    return nodeInBucket.value;
                }
                nodeInBucket = nodeInBucket.next;
            } while (nodeInBucket != null);
        }
        return null;
    }

    public int getSize() {
        return size;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new SimpleMapIterator();
    }

    private class Node {
        public int hash;
        public K key;
        public V value;
        public Node next;

        public Node(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }
    }

    public static class Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Entry<?, ?> entry = (Entry<?, ?>) o;

            if (key != null ? !key.equals(entry.key) : entry.key != null) {
                return false;
            }
            return value != null ? value.equals(entry.value) : entry.value == null;
        }

        @Override
        public int hashCode() {
            int result = 17;
            result = result * 37 + key.hashCode();
            result = result * 37 + value.hashCode();
            return result;
        }

        @Override
        public String toString() {
            return key + " = " + value;
        }
    }


    private class SimpleMapIterator implements Iterator<Entry<K, V>> {
        private int expectedModCount;
        private Entry<K, V> founded;
        private int tableCellNumber = 0;
        private Node lastNode;

        public SimpleMapIterator() {
            expectedModCount = modCount;
        }

        @Override
        public boolean hasNext() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            if (founded != null) {
                return true;
            }

            if (lastNode != null) {
                if (lastNode.next != null) {
                    founded = new Entry<>(lastNode.next.key, lastNode.next.value);
                    lastNode = lastNode.next;
                    return true;
                } else {
                    lastNode = null;
                    tableCellNumber++;
                }
            }

            while (tableCellNumber < fTABLESIZE) {
                Node node = (Node) table[tableCellNumber];
                if (node != null) {
                    lastNode = node;
                    break;
                }
                tableCellNumber++;
            }
            if (tableCellNumber == fTABLESIZE) {
                return false;
            }
            founded = new Entry<>(lastNode.key, lastNode.value);
            return true;
        }

        @Override
        public Entry<K, V> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Entry<K, V> result = founded;
            founded = null;
            return result;
        }
    }
}
