package com.moonsworth.lunar.client.framework.feature.markers;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public abstract class IconRegistry<T, S> implements DynamicMapping<Markers3_2, S, T> {
   private static EntityIconRegistry field1 = null;
   private static ItemIconRegistry field2 = null;
   private final BiMap<String, String> field3 = HashBiMap.create();
   private final List<IconMapping<S, T>> field4 = new ArrayList<>();

   public IconRegistry() {
      this.initMappings();
   }

   protected void method1(String text1, String text2) {
      this.field3.put(text1, text2);
   }

   protected void method2(IconMapping<S, T> sextension_21) {
      this.field4.add(sextension_21);
   }

   protected abstract void initMappings();

   public Optional<T> method3(@NotNull Markers3_2 markers3_21, int number2) {
      for (IconMapping sextension_24 : this.field4) {
         try {
            if (sextension_24.method1(markers3_21)) {
               return sextension_24.method3(markers3_21);
            }
         } catch (Throwable exception6) {
            LunarLogger.method9(exception6, "Error while applying DynamicMapping in adaptFrom", new Object[0]);
            return Optional.empty();
         }
      }

      if (!this.HIRHCCHIRHRORIICOIHIHCICOIRHHC(markers3_21)) {
         return this.method1(markers3_21);
      }

      String text7 = markers3_21.value();
      int number8 = Ref.MC_VERSION;
      if (number8 > 5 && number2 <= 5) {
         text7 = (String)this.field3.getOrDefault(text7, text7);
      } else if (number8 <= 5 && number2 > 5) {
         text7 = (String)this.field3.inverse().getOrDefault(text7, text7);
      }

      return this.method1(new Markers3_2(text7, markers3_21.method1()));
   }

   public Optional<Markers3_2> method5(@NotNull S value1) {
      if (this.HCCIHCROHHCOOHCHCHHHICIOROHICC(value1)) {
         for (IconMapping sextension_23 : this.field4) {
            try {
               if (sextension_23.method1(value1)) {
                  return sextension_23.method3(value1);
               }
            } catch (Throwable exception5) {
               LunarLogger.method9(exception5, "Error while applying DynamicMapping in getKeyFor", new Object[0]);
               return Optional.empty();
            }
         }
      }

      return this.CORRCOHCRHOHHOIHOIOICORROHOOOO(value1);
   }

   public static EntityIconRegistry method5() {
      if (field1 == null) {
         field1 = new EntityIconRegistry();
      }

      return field1;
   }

   public static ItemIconRegistry method6() {
      if (field2 == null) {
         field2 = new ItemIconRegistry();
      }

      return field2;
   }
}
