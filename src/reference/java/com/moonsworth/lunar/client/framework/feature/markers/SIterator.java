package com.moonsworth.lunar.client.framework.feature.markers;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public abstract class SIterator<T, S> implements Markers_3<Markers3_2, S, T> {
   private static SIterator2 field1 = null;
   private static SIterator_2 field2 = null;
   private final BiMap<String, String> field3 = HashBiMap.create();
   private final List<SExtension_2<S, T>> field4 = new ArrayList<>();

   public SIterator() {
      this.initMappings();
   }

   protected void method1(String var1, String var2) {
      this.field3.put(var1, var2);
   }

   protected void method2(SExtension_2<S, T> var1) {
      this.field4.add(var1);
   }

   protected abstract void initMappings();

   public Optional<T> method3(@NotNull Markers3_2 var1, int var2) {
      for (SExtension_2 var4 : this.field4) {
         try {
            if (var4.method1(var1)) {
               return var4.method3(var1);
            }
         } catch (Throwable var6) {
            Slayer.method9(var6, "Error while applying DynamicMapping in adaptFrom", new Object[0]);
            return Optional.empty();
         }
      }

      if (!this.HIRHCCHIRHRORIICOIHIHCICOIRHHC(var1)) {
         return this.method1(var1);
      }

      String var7 = var1.value();
      int var8 = ThreadModuleDump63.MC_VERSION;
      if (var8 > 5 && var2 <= 5) {
         var7 = (String)this.field3.getOrDefault(var7, var7);
      } else if (var8 <= 5 && var2 > 5) {
         var7 = (String)this.field3.inverse().getOrDefault(var7, var7);
      }

      return this.method1(new Markers3_2(var7, var1.method1()));
   }

   public Optional<Markers3_2> method5(@NotNull S var1) {
      if (this.HCCIHCROHHCOOHCHCHHHICIOROHICC(var1)) {
         for (SExtension_2 var3 : this.field4) {
            try {
               if (var3.method1(var1)) {
                  return var3.method3(var1);
               }
            } catch (Throwable var5) {
               Slayer.method9(var5, "Error while applying DynamicMapping in getKeyFor", new Object[0]);
               return Optional.empty();
            }
         }
      }

      return this.CORRCOHCRHOHHOIHOIOICORROHOOOO(var1);
   }

   public static SIterator2 method5() {
      if (field1 == null) {
         field1 = new SIterator2();
      }

      return field1;
   }

   public static SIterator_2 method6() {
      if (field2 == null) {
         field2 = new SIterator_2();
      }

      return field2;
   }
}
