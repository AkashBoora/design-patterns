import cache.Cache;

public class Main {
    public static void main(String[] args) {
        Cache cache = Cache.getInstance();

        cache.put("key1","hello");

        Cache cache1 = Cache.getInstance();

        System.out.println(cache+" "+cache1);
        System.out.println(cache1.get("key1"));
    }
}