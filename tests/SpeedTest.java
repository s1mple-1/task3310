package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SpeedTest {
    public long getTimeToGetIds(Shortener shortener, Set<String> strings, Set<Long> ids) {
        Date startDate = new Date();
        for (String st : strings) {
            ids.add(shortener.getId(st));
        }
        Date endDate = new Date();
        return endDate.getTime() - startDate.getTime();
    }

    public long getTimeToGetStrings(Shortener shortener, Set<Long> ids, Set<String> strings) {
        Date startDate = new Date();
        for (Long ln : ids) {
            strings.add(shortener.getString(ln));
        }
        Date endDate = new Date();
        return endDate.getTime() - startDate.getTime();
    }

    @Test
    public void testHashMapStorage() {
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());
        Set<String> origStrings = new HashSet<>();
        for (int i = 0; i < 1000; i++) {
           origStrings.add(Helper.generateRandomString());
        }

        Set<Long> idReceived = new HashSet<>();
        Long onesId = getTimeToGetIds(shortener1, origStrings, idReceived);
        Long twosId = getTimeToGetIds(shortener2, origStrings, idReceived);
        Assert.assertNotEquals(onesId, twosId);

        Long onesString = getTimeToGetStrings(shortener1, idReceived, origStrings);
        Long twosString = getTimeToGetStrings(shortener2, idReceived, origStrings);
        Assert.assertEquals(onesString, twosString, 30);
    }
}

/*
15.4. Добавь в класс SpeedTest тест testHashMapStorage(). Он должен:
        15.4.2. Генерировать с помощью Helper 10000 строк и помещать их в сет со строками, назовем его origStrings.
        15.4.3. Получать время получения идентификаторов для origStrings (вызывать метод getTimeToGetIds для shortener1, а затем для shortener2).
        15.4.4. Проверять с помощью junit, что время, полученное в предыдущем пункте для shortener1 больше, чем для shortener2.
        15.4.5. Получать время получения строк (вызывать метод getTimeToGetStrings для shortener1 и shortener2).
        15.4.6. Проверять с помощью junit, что время, полученное в предыдущем пункте для shortener1 примерно равно времени для shortener2. Используй метод assertEquals(float expected, float actual, float delta). В качестве delta можно использовать 30, этого вполне достаточно для наших экспериментов.*/
