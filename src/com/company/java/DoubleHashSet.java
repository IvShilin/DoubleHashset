package com.company.java;

public class DoubleHashSet<T> implements ISet<T> {
    private int arraySize;
    private int size = 0;
    Object[] hashArray;

    public DoubleHashSet(int n) {
        if (isPrime(n)) {
            hashArray = new Object[n];
            arraySize = n;
        } else {
            int primeCount = getNextPrime(n);
            hashArray = new Object[primeCount];
        }

    }

    public boolean isPrime(int num) {
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;

    }

    public int getNextPrime(int minN) {
        for (int i = minN; true; i++) {
            if (isPrime(i)) {
                return i;
            }
        }

    }

    public int getPrime() {
        int prime = 0;
        for (int i = arraySize - 1; i >= 1; i--) {
            for (int j = 2; j <= (int) Math.sqrt(i); j++) {
                if (i % j == 0) {
                    prime++;
                }
            }
            if (prime == 0) {
                return i;
            }
        }
        return prime;

    }

    //return prefered index position
    public int hashFunc1(T item) {
        int hasVal = item.hashCode();
        hasVal = hasVal % arraySize;
        if (hasVal < 0) {
            hasVal += arraySize;
        }
        return hasVal; //ideal index position

    }

    //return step size
    public int hashFunc2(T item) {
        int hasVal = item.hashCode();
        hasVal %= arraySize;
        if (hasVal < 0) {
            hasVal += arraySize;
        }
        int prime = getPrime();
        int res = prime - (hasVal % prime);
        return res;

    }

    public void add(T item) throws IllegalStateException {
        if (arraySize <= size) {
            throw new IllegalStateException();
        } else {
            int hasVal = hashFunc1(item);
            int stepSize = hashFunc2(item);

            // In case a collision occurs
            if (hashArray[hasVal] != null) {
                while (hashArray[hasVal] != null) {
                    int newIndex = getNextPrime(hasVal);
                    if (hashArray[newIndex] == null) {
                        hashArray[newIndex] = item;
                        size += 1;
                        break;
                    }
                    hasVal = (hasVal + stepSize) % arraySize;
                }
            }

            //if no collision occurs
            else {
                hashArray[hasVal] = item;
                size += 1;
            }
        }

    }

    public void remove(T item) {
        if (isEmpty()) {
            return;
        }

        int hasVal = hashFunc1(item);
        int stepSize = hashFunc2(item);
        int newIndex = (hasVal + stepSize) % arraySize;

        if (hashArray[hasVal] == item) {
            hashArray[hasVal] = null;
            size -= 1;
        }
        if (hashArray[newIndex] == item) {
            hashArray[newIndex] = null;
            size -= 1;
        }

    }

    public boolean contains(T item) {
        int hasVal = hashFunc1(item);
        int stepSize = hashFunc2(item);

        boolean flag = false;
        if (hashArray[hasVal] == item) {
            flag = true;
        } else {
            int newIndex = (hasVal + stepSize) % arraySize;
            if (hashArray[newIndex] == item) {
                flag = true;
            }
        }
        return flag;

    }


    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

}


