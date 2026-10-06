package com.moonsworth.lunar.genesis;
import com.google.common.cache.CacheStats;

public interface MixinHelper42$Extension {
   void recordHits(int var1);

   void recordMisses(int var1);

   void recordLoadSuccess(long var1);

   void recordLoadException(long var1);

   void recordEviction();

   CacheStats method1();
}
