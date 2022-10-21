package lucas.base.util;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * packageName    : lucas.base.util
 * fileName       : SimpleLru
 * author         : lucas
 * date           : 2022-10-17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-10-17        lucas       최초 생성
 */
public class SimpleLru<K, V> {
    private final Map<K, V> accessCache;
    private final Map<K, V> creationCache;



    final int maxSize;
    public SimpleLru(final int maxSize) {
        this.maxSize = maxSize;
        this.accessCache = new ConcurrentHashMap<K, V>(maxSize);
        this.creationCache =
                new LinkedHashMap<K, V>(maxSize, 0.75f) {
                    @Override
                    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                        if (size() > maxSize) {
                            accessCache.remove(eldest.getKey());
                            return true;
                        }
                        else {
                            return false;
                        }
                    }
                };
    }

    public void put(K key, V value) {
        this.accessCache.put(key, value);
        synchronized (this.creationCache) {
            this.creationCache.put(key, value);
        }
    }

    public void remove(K key) {
        this.accessCache.remove(key);
        synchronized (this.creationCache) {
            this.creationCache.remove(key);
        }
    }

    public V get(K key) {
        return this.accessCache.get(key);
    }

    public int size() {
        return this.accessCache.size();
    }

    public Set<Map.Entry<K, V>> entrySet() {
        return this.accessCache.entrySet();
    }
}
