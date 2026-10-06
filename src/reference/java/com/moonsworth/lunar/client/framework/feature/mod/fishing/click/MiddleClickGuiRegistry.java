package com.moonsworth.lunar.client.framework.feature.mod.fishing.click;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MiddleClickGuiRegistry {
   private static final IntOpenHashSet field1 = new IntOpenHashSet();
   private final Map<String, IntOpenHashSet> field2 = new Object2ObjectOpenHashMap();
   private final List<MiddleClickGuiRegistry.Data> prefixMatches = new ArrayList<>();
   private final List<MiddleClickGuiRegistry.Data> suffixMatches = new ArrayList<>();
   private final List<MiddleClickGuiRegistry.Data> containsMatches = new ArrayList<>();
   private final LoadingCache<String, IntOpenHashSet> cache = CacheBuilder.newBuilder().maximumSize(50L).build(CacheLoader.from(this::load));

   public MiddleClickGuiRegistry() {
   }

   @Nullable
   public IntOpenHashSet find(String text1) {
      try {
         IntOpenHashSet intopenhashset2 = (IntOpenHashSet)this.cache.get(text1);
         return intopenhashset2 == field1 ? null : intopenhashset2;
      } catch (ExecutionException executionexception3) {
         CrashReporter.method5(executionexception3, "SkyBlockMiddleClickGuis");
         return null;
      }
   }

   @NotNull
   private IntOpenHashSet load(String text1) {
      IntOpenHashSet intopenhashset2 = this.exactMatches.get(text1);
      if (intopenhashset2 != null) {
         return intopenhashset2;
      }

      for (MiddleClickGuiRegistry.Data data4 : this.prefixMatches) {
         if (text1.startsWith(data4.name())) {
            return data4.method1();
         }
      }

      for (MiddleClickGuiRegistry.Data data7 : this.suffixMatches) {
         if (text1.endsWith(data7.name())) {
            return data7.method1();
         }
      }

      for (MiddleClickGuiRegistry.Data data8 : this.containsMatches) {
         if (text1.contains(data8.name())) {
            return data8.method1();
         }
      }

      return field1;
   }

   public void registerPrefix(String text1, IntOpenHashSet intopenhashset2) {
      this.prefixMatches.add(new MiddleClickGuiRegistry.Data(text1, intopenhashset2));
   }

   public void registerSuffix(String text1, IntOpenHashSet intopenhashset2) {
      this.suffixMatches.add(new MiddleClickGuiRegistry.Data(text1, intopenhashset2));
   }

   public void registerContains(String text1, IntOpenHashSet intopenhashset2) {
      this.containsMatches.add(new MiddleClickGuiRegistry.Data(text1, intopenhashset2));
   }

   public void registerExact(String text1, IntOpenHashSet intopenhashset2) {
      this.exactMatches.put(text1, intopenhashset2);
   }

   private class Data {
      private final String field1;
      private final IntOpenHashSet field2;

      private Data(String text1, IntOpenHashSet intopenhashset2) {
         this.EMPTY_SLOTS = text1;
         this.exactMatches = intopenhashset2;
      }

      public String name() {
         return this.EMPTY_SLOTS;
      }

      public IntOpenHashSet find() {
         return this.exactMatches;
      }
   }
}
