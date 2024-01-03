package com.github.neshkeev.showcase.threading.locks;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class StackMapGoodHandler {
    private final Map<String, String> map = new HashMap<>();
    private final Deque<String> recent = new ArrayDeque<>();

    private final Lock mapLock = new ReentrantLock();
    private final Lock recentLock = new ReentrantLock();

    private final Locks locks = new Locks(mapLock, recentLock);

    public void add(String key, String value) {
        locks.lock();
        try {
            map.put(key, value);
            recent.addFirst(key);
        }
        finally {
            locks.unlock();
        }
    }

    public String popRecent() {
        if (recent.isEmpty()) return null;

        locks.lock();
        try {
            final var last = recent.pop();
            return map.remove(last);
        }
        finally {
            locks.unlock();
        }
    }

    private record Locks(Lock... locks) {

        public void lock() {
            for (Lock lock : locks) {
                lock.lock();
            }
        }

        public void unlock() {
            for (Lock lock : locks) {
                lock.unlock();
            }
        }
    }
}