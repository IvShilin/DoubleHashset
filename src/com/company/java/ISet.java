package com.company.java;

/**
 * An implementation of Set with double hashing based on the DoubleHashSet class that
 * implements ISet interfaces using double hashing for collision handling
 *
 * @param <T> any object
 * @author Elina Akimchenkova (BS21-07 e.akimchenkova@innopolis.university)
 */

interface ISet<T> {
    void add(T item) throws IllegalStateException; //// add item in the set

    void remove(T item); // remove an item from a set

    boolean contains(T item); // check if an item belongs to a set

    int size();

    boolean isEmpty(); // check if the set is empty

    int getPrime();

    int hashFunc1(T item);

    int hashFunc2(T item);

    int getNextPrime(int minN);

    boolean isPrime(int num);

}
