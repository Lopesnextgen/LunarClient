package com.moonsworth.lunar.client.framework.feature.mod.fishing.click;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.moonsworth.lunar.client.inventorymod.Inventorymod2;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Click6 {
   private static final IntOpenHashSet field1 = new IntOpenHashSet();
   private final Map<String, IntOpenHashSet> field2 = new Object2ObjectOpenHashMap();
   private final List<Click6.Data> startsWithMatchers = new ArrayList<>();
   private final List<Click6.Data> endsWithMatchers = new ArrayList<>();
   private final List<Click6.Data> containsMatchers = new ArrayList<>();
   private final LoadingCache<String, IntOpenHashSet> cache = CacheBuilder.newBuilder().maximumSize(50L).build(CacheLoader.from(this::resolveSlots));

   @Nullable
   public IntOpenHashSet findSlots(String var1) {
      try {
         IntOpenHashSet var2 = (IntOpenHashSet)this.cache.get(var1);
         return var2 == field1 ? null : var2;
      } catch (ExecutionException var3) {
         Inventorymod2.method5(var3, "SkyBlockMiddleClickGuis");
         return null;
      }
   }

   @NotNull
   private IntOpenHashSet resolveSlots(String var1) {
      IntOpenHashSet var2 = this.exactSlots.get(var1);
      if (var2 != null) {
         return var2;
      }

      for (Click6.Data var4 : this.startsWithMatchers) {
         if (var1.startsWith(var4.name())) {
            return var4.method1();
         }
      }

      for (Click6.Data var7 : this.endsWithMatchers) {
         if (var1.endsWith(var7.name())) {
            return var7.method1();
         }
      }

      for (Click6.Data var8 : this.containsMatchers) {
         if (var1.contains(var8.name())) {
            return var8.method1();
         }
      }

      return field1;
   }

   public void registerStartsWith(String var1, IntOpenHashSet var2) {
      this.startsWithMatchers.add(new Click6.Data(var1, var2));
   }

   public void registerEndsWith(String var1, IntOpenHashSet var2) {
      this.endsWithMatchers.add(new Click6.Data(var1, var2));
   }

   public void registerContains(String var1, IntOpenHashSet var2) {
      this.containsMatchers.add(new Click6.Data(var1, var2));
   }

   public void registerExact(String var1, IntOpenHashSet var2) {
      this.exactSlots.put(var1, var2);
   }

   private class Data {
      private final String field1;
      private final IntOpenHashSet field2;

      private Data(String var1, IntOpenHashSet var2) {
         this.EMPTY_SET = var1;
         this.exactSlots = var2;
      }

      public String name() {
         return this.EMPTY_SET;
      }

      public IntOpenHashSet findSlots() {
         return this.exactSlots;
      }
   }
}
