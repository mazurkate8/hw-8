package myhashmap;

import utils.MyMap;

import java.util.*;

public class MyHashMap<K, V> implements MyMap<K, V> {
    private MapNode<K, V>[] hashTable;
    private int size = 0;
    private float threshold;

    public static void main(String[] args) {
        MyHashMap<String, String> strings = new MyHashMap<>();
        strings.put("a", "b");
        System.out.println(strings.get("a"));
        strings.put("c", "d");
        System.out.println(strings.size());
        strings.clear();
        System.out.println(strings.size());
    }

    public MyHashMap() {
        hashTable = new MapNode[16];
        threshold = hashTable.length * 0.75f;
    }

    @Override
    public boolean put(K key, V value) {
        if (size + 1 >= threshold) {
            threshold *= 2;
            arrayDoubling();
        }

        MapNode<K, V> newNode = new MapNode<>(key, value);
        int index = hash(key);

        if (hashTable[index] == null) {
            return simpleAdd(index, newNode);
        }

        List<MapNode<K, V>> nodeList = hashTable[index].getNodes();

        for (MapNode<K, V> node : nodeList) {
            // Обработка коллизии
            if (keyExistButValueNew(node, newNode, value) ||
                    collisionProcessing(node, newNode, nodeList)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Обход коллизии когда хеши равны а обьекты разные
     * @param nodeFromList
     * @param newNode
     * @param nodes
     * @return
     */
    private boolean collisionProcessing(
            final MapNode<K, V> nodeFromList,
            final MapNode<K, V> newNode,
            final List<MapNode<K, V>> nodes) {

        if (newNode.hashCode() == nodeFromList.hashCode() &&
                !Objects.equals(newNode.key, nodeFromList.key) &&
                !Objects.equals(newNode.value, nodeFromList.value)) {
            nodes.add(newNode);
            size++;
            return true;
        }
        return false;
    }

    private boolean simpleAdd(int index, MapNode<K, V> newNode) {
        hashTable[index] = new MapNode<>(null, null);
        hashTable[index].getNodes().add(newNode);
        size++;
        return true;
    }

    private boolean keyExistButValueNew(
            final MapNode<K, V> nodeFromList,
            final MapNode<K, V> newNode,
            final V value) {
        if (newNode.getKey().equals(nodeFromList.getKey()) &&
                !newNode.getValue().equals(nodeFromList.getValue())) {
            nodeFromList.setValue(value);
            return true;
        }
        return false;
    }

    private void arrayDoubling() {
        MapNode<K, V>[] oldHashTable = hashTable;
        hashTable = new MapNode[oldHashTable.length * 2];
        size = 0;
        for (MapNode<K, V> node : oldHashTable) {
            if (node != null) {
                for (MapNode<K, V> n : node.getNodes()) {
                    put(n.key, n.value);
                }
            }
        }
    }

    @Override
    public boolean remove(K key) {
        int index = hash(key);
        if (hashTable[index] == null) {
            return false;
        }

        if (hashTable[index].getNodes().size() == 1) {
            hashTable[index] = null;
            return true;
        }
//Значит случилась коллизия и делаем обход всех елементов
        List<MapNode<K, V>> nodeList = hashTable[index].getNodes();
        for (MapNode<K, V> node : nodeList) {
            if (key.equals(node.getKey())) {
                nodeList.remove(node);
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
        int index = hash(key);
        if (index < hashTable.length &&
                hashTable[index] != null) {

            if(hashTable[index].getNodes().size() == 1) {
                return hashTable[index].getNodes().get(0).getValue();
            }

            List<MapNode<K, V>> list = hashTable[index].getNodes();
            for (MapNode<K, V> node : list) {
                if (key.equals(node.getKey())) {
                    return node.getValue();
                }
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean clear() {
        hashTable = new MapNode[16];
        size =0;
        threshold = hashTable.length * 0.75f;
        return true;
    }

    private int hash(final K key) {
        int hash = 31;
        hash = hash * 17 + key.hashCode();
        return hash % hashTable.length;
    }

    @Override
    public Iterator<V> iterator() {
        return new Iterator<V>() {
            int counterArray = 0;
            int valuesCounter = 0;
            Iterator<MapNode<K, V>> subIterator = null;

            @Override
            public boolean hasNext() {
                if (valuesCounter == size) {
                    return false;
                }
                if (subIterator == null || !subIterator.hasNext()) {
                    if (moveToNextCell()) {
                        subIterator = hashTable[counterArray].getNodes().iterator();
                    } else {
                        return false;
                    }
                }
                return subIterator.hasNext();
            }

            private boolean moveToNextCell() {
                counterArray++;
                while (hashTable[counterArray] == null) {
                    counterArray++;
                }
                return hashTable[counterArray] != null;
            }

            @Override
            public V next() {
                valuesCounter++;
                return subIterator.next().getValue();
            }
        };
    }

    private class MapNode<K, V> {
        private List<MapNode<K, V>> nodes;
        private int hash;
        private K key;
        private V value;

        private MapNode(K key, V value) {
            this.key = key;
            this.value = value;
            nodes = new LinkedList<MapNode<K, V>>();
        }

        private List<MapNode<K, V>> getNodes() {
            return nodes;
        }

        private int hash() {
            return hashCode() % hashTable.length;
        }

        private K getKey() {
            return key;
        }

        private V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public int hashCode() {
            hash = 31;
            hash = hash * 17 + key.hashCode();
            hash = hash * 17 + value.hashCode();
            return hash;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (o instanceof MapNode) {
                MapNode<K, V> node = (MapNode) o;
                return (Objects.equals(key, node.getKey()) &&
                        Objects.equals(value, node.getValue()) ||
                        Objects.equals(hash, node.hashCode()));
            }
            return false;
        }
    }
}


