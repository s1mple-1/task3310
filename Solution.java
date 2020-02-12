package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        testStrategy(new OurHashMapStorageStrategy(), 1000);
        testStrategy(new HashMapStorageStrategy(), 1000);
        testStrategy(new FileStorageStrategy(), 10);
        testStrategy(new OurHashBiMapStorageStrategy(), 1000);
        testStrategy(new HashBiMapStorageStrategy(),1000);
        testStrategy(new DualHashBidiMapStorageStrategy(), 1000);
    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        Set<Long> idSet = new HashSet<>();
        for (String string : strings) {
            Long id = shortener.getId(string);
            idSet.add(id);
        }
        return idSet;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> stringSet = new HashSet<>();
        for (Long id : keys) {
            String value = shortener.getString(id);
            if (value != null) {
                stringSet.add(value);
            }
        }
        return stringSet;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        Helper.printMessage(strategy.getClass().getSimpleName());

        Set<String> stringSet = new HashSet<>();
        for (long i = 0; i < elementsNumber; i++) {
            stringSet.add(Helper.generateRandomString());
        }

        Shortener shortener = new Shortener(strategy);

        Date beforeId = new Date();
        Set<Long> keysSet = getIds(shortener, stringSet);
        Date afterId = new Date();
        Helper.printMessage(afterId.getTime() - beforeId.getTime() + "");

        Date beforeString = new Date();
        Set<String> valuesSet = getStrings(shortener, keysSet);
        Date afterString = new Date();
        Helper.printMessage(afterString.getTime() - beforeString.getTime() + "");

        if (stringSet.size() == valuesSet.size()) {
            Helper.printMessage("Тест пройден.");
        } else {
            Helper.printMessage("Тест не пройден.");
        }
    }
}

/*
6.2.3. testStrategy(StorageStrategy strategy, long elementsNumber). Метод будет тестировать работу переданной стратегии на определенном количестве элементов elementsNumber. Реализация метода должна:
6.2.3.1. Выводить имя класса стратегии. Имя не должно включать имя пакета.
6.2.3.2. Генерировать тестовое множество строк, используя Helper и заданное количество элементов elementsNumber.
6.2.3.3. Создавать объект типа Shortener, используя переданную стратегию.
6.2.3.4. Замерять и выводить время необходимое для отработки метода getIds для заданной стратегии и заданного множества элементов. Время вывести в миллисекундах. При замере времени работы метода можно пренебречь переключением процессора на другие потоки, временем, которое тратится на сам вызов, возврат значений и вызов методов получения времени (даты). Замер времени произведи с использованием объектов типа Date.
6.2.3.5. Замерять и выводить время необходимое для отработки метода getStrings для заданной стратегии и полученного в предыдущем пункте множества идентификаторов.
6.2.3.6. Сравнивать одинаковое ли содержимое множества строк, которое было сгенерировано и множества, которое было возвращено методом getStrings. Если множества одинаковы, то выведи "Тест пройден.", иначе "Тест не пройден.".
6.2.4. Добавь метод main(). Внутри метода протестируй стратегию HashMapStorageStrategy с помощью 10000 элементов.
6.3. Проверь, что программа работает и тест пройден.
*/
