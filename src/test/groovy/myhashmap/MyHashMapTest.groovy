package myhashmap

import spock.lang.Specification

class MyHashMapTest extends Specification {

    private MyHashMap<String, String> hashMap;

    def setup() {
        hashMap = new MyHashMap<>()

    }

    def "Добавляю в мапу ключ-значение" () {
        given:
        hashMap.put("a", "b")
        hashMap.put("b", "c")

        expect:
        hashMap.put("d", "e")
    }

    def "Заполняю мапу значениями" () {
        given:
        hashMap.put("a", "b")
        hashMap.put("b", "c")
        hashMap.put("d", "e")

        expect:
        hashMap.size() == 3
    }

    def "Беру из мапы значение по ключу" () {
        given:
        hashMap.put("a", "b")
        hashMap.put("b", "c")
        hashMap.put("d", "e")

        expect:
        hashMap.get("a") == "b"
    }

    def "Удаляю из мапы значение по ключу" () {
        given:
        hashMap.put("a", "b")
        hashMap.put("b", "c")
        hashMap.put("d", "e")

        expect:
        hashMap.remove("a")
    }

    def "Очищаю мапу" () {
        given:
        hashMap.put("a", "b")
        hashMap.put("b", "c")
        hashMap.put("d", "e")

        when: "Clear map"
        hashMap.clear()
        then:
        hashMap.size() == 0
    }


}
