package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.*;
import org.junit.Assert;
import org.junit.Test;

public class FunctionalTest {


    public void testStorage(Shortener shortener) {
        String one = "Boom";
        String two = "Not A Boom";
        String three = "Boom";

        Long oneId = shortener.getId(one);
        Long twoId = shortener.getId(two);
        Long threeId = shortener.getId(three);

        Assert.assertNotEquals(oneId, twoId);
        Assert.assertEquals(oneId, threeId);

        String beOne = shortener.getString(oneId);
        String beTwo = shortener.getString(twoId);
        String beTree = shortener.getString(threeId);

        Assert.assertEquals(beOne, one);
        Assert.assertEquals(beTwo, two);
        Assert.assertEquals(beTree, three);
    }

    @Test
    public void testHashMapStorageStrategy() {
        Shortener shortener = new Shortener(new HashMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testOurHashMapStorageStrategy() {
        Shortener shortener = new Shortener(new OurHashMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testFileStorageStrategy() {
        Shortener shortener = new Shortener(new FileStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testHashBiMapStorageStrategy() {
        Shortener shortener = new Shortener(new HashBiMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testDualHashBidiMapStorageStrategy() {
        Shortener shortener = new Shortener(new DualHashBidiMapStorageStrategy());
        testStorage(shortener);
    }

    @Test
    public void testOurHashBiMapStorageStrategy() {
        Shortener shortener = new Shortener(new OurHashBiMapStorageStrategy());
        testStorage(shortener);
    }

}
