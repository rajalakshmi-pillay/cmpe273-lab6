package edu.sjsu.cmpe.cache.client;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.hash.*;

import java.nio.charset.Charset;
import java.util.List;

public class Client {

    public static void main(String[] args) throws Exception {
        System.out.println("Starting Cache Client...");
        CacheServiceInterface cache1 = new DistributedCacheService(
                "http://localhost:3000");
        CacheServiceInterface cache2 = new DistributedCacheService(
                "http://localhost:3001");
        CacheServiceInterface cache3 = new DistributedCacheService(
                "http://localhost:3002");

       // cache.put(1, "a");
        //System.out.println("put(1 => foo)");

      /*  cache.put(2,"b");
        cache.put(3,"c");
        cache.put(4,"d");
        cache.put(5,"e");
        cache.put(6,"f");
        cache.put(7,"g");
        cache.put(8,"h");
        cache.put(9,"i");
        cache.put(10,"j");*/

       // String value = cache.get(1);
      //  System.out.println("get(1) => " + value);

        System.out.println("Existing Cache Client...");


        CacheServiceInterface[] distributedCache = {cache1, cache2, cache3};
        int[] bucket = new int[10];
        String values[] = {"a","b", "c", "d", "e", "f", "g", "h", "i", "j"};


        int size = 3;
        for(int i=1; i<= 10; i++){
            bucket[i-1] = Hashing.consistentHash(Hashing.md5().hashInt(i), size);
          //  System.out.println("bucket "+bucket[i-1]);

            System.out.println("put(" + i +") => " + values[i-1]);
            System.out.println("Routing to cache "+bucket[i-1]);
            distributedCache[bucket[i-1]].put(i, (String) values[i-1]);
            System.out.println("get("+ i+ ") => " + distributedCache[bucket[i-1]].get(i));
        }

    }

}
