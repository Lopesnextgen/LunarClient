package com.moonsworth.lunar.ichor.util;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import org.cadixdev.bombe.analysis.InheritanceProvider.ClassInfo;

public class CachingInheritanceProvider implements org.cadixdev.bombe.analysis.InheritanceProvider {
   private final org.cadixdev.bombe.analysis.InheritanceProvider field1;
   private final Cache<String, Optional<ClassInfo>> field2 = CacheBuilder.method1().method25(5L, TimeUnit.MINUTES).method35();

   public CachingInheritanceProvider(org.cadixdev.bombe.analysis.InheritanceProvider inheritanceprovider1) {
      this.field1 = inheritanceprovider1;
   }

   public Optional<ClassInfo> provide(String text1) {
      try {
         return (Optional<ClassInfo>)this.field2.get(text1, () -> this.field1.provide(text1));
      } catch (ExecutionException executionexception3) {
         throw new RuntimeException(executionexception3);
      }
   }

   public Optional<ClassInfo> provide(String text1, Object obj2) {
      try {
         return (Optional<ClassInfo>)this.field2.get(text1, () -> this.field1.provide(text1, obj2));
      } catch (ExecutionException executionexception4) {
         throw new RuntimeException(executionexception4);
      }
   }
}
