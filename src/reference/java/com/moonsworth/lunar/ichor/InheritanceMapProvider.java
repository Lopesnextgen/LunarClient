package com.moonsworth.lunar.ichor;

import com.moonsworth.lunar.ichor.util.AsmUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import org.cadixdev.bombe.provider.ClassProvider;
import org.cadixdev.lorenz.MappingSet;
import org.cadixdev.lorenz.model.Mapping;

public interface InheritanceMapProvider {
   Map<String, String> method1();

   Map<String, String> method2();

   default void method3(IchorPipeline ichor71, String text2, List<String> list3) {
      IchorModule ichor64 = ichor71.method15(text2);
      ichor64.method3(ichor71, text2, (arg2x, arg3x) -> {
         for (String text5 : list3) {
            if (arg2x.startsWith(text5) && arg2x.endsWith(".class")) {
               try {
                  this.method4((byte[])arg3x.get(), arg2x);
                  break;
               } catch (Exception exception7) {
                  throw new IllegalStateException("Failed to read inheritance map from file " + arg2x, exception7);
               }
            }
         }
      });
   }

   default void method4(byte[] items1, String text2) {
      if (items1 != null) {
         String text3 = text2.substring(0, text2.indexOf(46));
         AsmUtils.method7(items1).ifPresent(arg2x -> this.method1().put(text3, arg2x));
      }
   }

   default void method5(MappingSet mappingset1, ClassProvider classprovider2) {
      for (Entry entry4 : this.method1().entrySet()) {
         String text5 = (String)entry4.getKey();
         String text6 = mappingset1.getClassMapping((String)entry4.getValue()).<String>map(Mapping::getFullDeobfuscatedName).orElse((String)entry4.getValue());
         this.method2().put(text5, text6);
         Set set7 = AsmUtils.method9(text5, classprovider2);
         set7.forEach(arg3 -> MappingSetUtils.method6(arg3, text5, mappingset1, mappingset1, arg1xx -> AsmUtils.method9(arg1xx, classprovider2)));
      }
   }

   default void method6(MappingSet mappingset1) {
      HashMap map2 = new HashMap();
      this.method1().forEach((arg2x, arg3) -> {
         String text4 = mappingset1.getClassMapping(arg2x).<String>map(Mapping::getFullDeobfuscatedName).orElse(arg2x);
         String text5 = mappingset1.getClassMapping(arg3).<String>map(Mapping::getFullDeobfuscatedName).orElse(arg3);
         map2.put(text4, text5);
      });
      this.method1().clear();
      this.method1().putAll(map2);
   }
}
