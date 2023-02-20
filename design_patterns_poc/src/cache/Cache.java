package cache;

import java.util.HashMap;
import java.util.Map;

public class Cache<K,V> {
    private static Cache cacheObject;

    private Map<K, CacheItem> map;
    private CacheItem first, last;
    private int size;
    private final int CAPACITY;
    private int hitCount = 0;
    private int missCount = 0;
    public Cache() {
        CAPACITY = 10;  //
        map = new HashMap<>(CAPACITY);
    }

    public static Cache getInstance(){
        if(cacheObject == null){
            synchronized (Cache.class){
                if( cacheObject == null) {
                    cacheObject = new Cache();
                }
            }
        }
        return cacheObject;
    }

    public void put(K key, V value) {
        CacheItem node = new CacheItem(key, value);
        if(map.containsKey(key) == false) {
            if(getSize() >= CAPACITY) {
                deleteNode(first);
            }
            addNodeToLast(node);
        }
        map.put(key, node);
    }

    private void addNodeToLast(CacheItem node) {
        if(last != null) {
            last.setNext(node);
            node.setPrev(last);
        }
        last = node;
        if(first == null) {
            first = node;
        }
        size++;
    }

    private void deleteNode(CacheItem node) {
        if(node == null){
            return;
        }
        if(node == last){
            last = last.getPrev();
        }
        if(node == first){
            first = first.getNext();
        }
        map.remove(node.getKey());
        node = null;
        size --;
    }

    public V get(K key) {
        if(!map.containsKey(key)){
            return null;
        }
        CacheItem node = map.get(key);
        node.incrementHitCount();
        reorder(node);
        return (V) node.getValue();
    }

    private void reorder(CacheItem node) {
        if(last == node) {
            return;
        }
        CacheItem nextNode = node.getNext();
        while (nextNode != null) {
            if(nextNode.getHitCount() > node.getHitCount()) {
                break;
            }
            if(first == node) {
                first = nextNode;
            }
            if(node.getPrev() != null) {
                node.getPrev().setNext(nextNode);
            }
            nextNode.setPrev(node.getPrev());
            node.setPrev(nextNode);
            node.setNext(nextNode.getNext());
            if(nextNode.getNext() != null) {
                nextNode.getNext().setPrev(node);
            }
            nextNode.setNext(node);
            nextNode = node.getNext();
        }
        if(node.getNext() == null) {
            last = node;
        }
    }

    public void delete(K key){
        deleteNode(map.get(key));
    }


    public int getSize() {
        return size;
    }

    private int getHitCount() {
        return hitCount;
    }

    private int getMissCount() {
        return missCount;
    }
}
