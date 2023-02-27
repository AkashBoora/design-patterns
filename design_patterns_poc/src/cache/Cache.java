package cache;

import java.util.HashMap;
import java.util.Map;

public class Cache<K,V> {
    // private static Cache<K,V> cacheObject;

    private static final Map<Class<?>, Cache<?, ?>> instances = new HashMap<>();
    
    private Map<K, CacheItem<K,V>> map;
    private CacheItem<K,V> first, last;
    private int size;
    private int hitCount = 0;
    private int missCount = 0;
    private final int CAPACITY;

    private Cache(Class<?> clazz) {
        CAPACITY = 10;
        map = new HashMap<>(CAPACITY);
        instances.put(clazz, this);
    }

    public static synchronized <T, V> Cache<T, V> getInstance(Class<?> clazz) {
        if (!instances.containsKey(clazz)) {
            new Cache<>(clazz);
        }
        return (Cache<T, V>) instances.get(clazz);
    }

    // public static Cache<K,V> getInstance(){
    //     if(cacheObject == null){
    //         synchronized (Cache.class){
    //             if( cacheObject == null) {
    //                 cacheObject = new Cache<K,V>();
    //             }
    //         }
    //     }
    //     return cacheObject;
    // }

    public void put(K key, V value) {
        CacheItem<K,V> node = new CacheItem<K,V>(key, value);
        if(map.containsKey(key) == false) {
            if(getSize() >= CAPACITY) {
                deleteNode(first);
            }
            addNodeToLast(node);
        }
        map.put(key, node);
    }

    private void addNodeToLast(CacheItem<K,V> node) {
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

    private void deleteNode(CacheItem<K,V> node) {
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
        CacheItem<K,V> node = map.get(key);
        node.incrementHitCount();
        reorder(node);
        return (V) node.getValue();
    }

    private void reorder(CacheItem<K,V> node) {
        if(last == node) {
            return;
        }
        CacheItem<K,V> nextNode = node.getNext();
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
