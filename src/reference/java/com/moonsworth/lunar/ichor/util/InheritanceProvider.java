package com.moonsworth.lunar.ichor.util;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import org.cadixdev.bombe.analysis.InheritanceProvider.ClassInfo;

public class InheritanceProvider implements org.cadixdev.bombe.analysis.InheritanceProvider {
   private final org.cadixdev.bombe.analysis.InheritanceProvider field1;
   private final Cache<String, Optional<ClassInfo>> field2 = CacheBuilder.method1().method25(5L, TimeUnit.MINUTES).method35();

   public InheritanceProvider(org.cadixdev.bombe.analysis.InheritanceProvider var1) {
      this.field1 = var1;
   }

   public Optional<ClassInfo> provide(String var1) {
      try {
         return this.field2.get(var1, () -> this.field1.provide(var1));
      } catch (ExecutionException var3) {
         throw new RuntimeException(var3);
      }
   }

   public Optional<ClassInfo> provide(String var1, Object var2) {
      try {
         return this.field2.get(var1, () -> this.field1.provide(var1, var2));
      } catch (ExecutionException var4) {
         throw new RuntimeException(var4);
      }
   }
}
