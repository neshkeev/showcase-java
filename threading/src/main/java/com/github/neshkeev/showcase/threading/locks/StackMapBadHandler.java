package com.github.neshkeev.showcase.threading.locks;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class StackMapBadHandler {
    private final Map<String, String> map = new HashMap<>();
    private final Deque<String> recent = new ArrayDeque<>();

    private final Lock mapLock = new ReentrantLock();
    private final Lock recentLock = new ReentrantLock();

    public void add(String key, String value) {
        mapLock.lock();
        recentLock.lock();
        try {
            map.put(key, value);
            recent.addFirst(key);
        }
        finally {
            recentLock.unlock();
            mapLock.unlock();
        }
    }

    public String popRecent() {
        if (recent.isEmpty()) return null;

        recentLock.lock();
        mapLock.lock();
        try {
            final var last = recent.pop();
            return map.remove(last);
        }
        finally {
            mapLock.unlock();
            recentLock.unlock();
        }
    }
}