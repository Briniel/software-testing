package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PrimeTest {

    @Test
    public void testPrime() {
        Assert.assertTrue(Primes.isPrimeFast(Integer.MAX_VALUE), "Число не является простым");
    }

    @Test
    public void testNotPrime() {
        Assert.assertFalse(Primes.isPrime(Integer.MAX_VALUE - 2), "Число является простым");
    }

    @Test(enabled = false)
    public void testPrimeLong() {
        long n = Integer.MAX_VALUE;
        Assert.assertTrue(Primes.isPrime(n), "Число не является простым");
    }
}
