package com.moonsworth.lunar.client.framework.mod;

import com.google.common.collect.ImmutableSet;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import java.util.Set;
import javax.annotation.Nullable;
import lombok.Generated;

public class ModMetadata {
   @Nullable
   private final Set<String> field1;
   @Nullable
   private final Set<String> field2;
   @Nullable
   private final Set<String> field3;
   @Nullable
   private final String field4;

   public String method1(String text) {
      if (this.field4 != null && !this.field4.isEmpty()) {
         return this.field4.contains("%s") ? String.format(this.field4, text) : this.field4;
      } else {
         return "features." + text;
      }
   }

   public ImmutableSet<ModCategory> method2(Set<ModCategory> set1) {
      if (this.field2 != null && !this.field2.isEmpty()) {
         for (String text3 : this.field2) {
            ModCategory calculator2handler4 = ModCategory.method2(text3, false);
            if (set1.contains(calculator2handler4)) {
               set1.remove(calculator2handler4);
            } else {
               set1.add(calculator2handler4);
            }
         }
      }

      return ImmutableSet.copyOf(set1);
   }

   public ImmutableSet<String> method3(@Nullable Set<String> set1) {
      return method5(set1, this.field1);
   }

   public ImmutableSet<String> method4(@Nullable Set<String> set1) {
      return method5(set1, this.field3);
   }

   private static <T> ImmutableSet<T> method5(@Nullable Set<T> set0, @Nullable Set<T> set1) {
      if (set1 != null && !set1.isEmpty()) {
         Set set2 = set0 == null ? Set.of() : set0;
         if (set2.isEmpty()) {
            return ImmutableSet.copyOf(set1);
         }

         for (Object obj4 : set1) {
            if (set2.contains(obj4)) {
               set2.remove(obj4);
            } else {
               set2.add(obj4);
            }
         }

         return ImmutableSet.copyOf(set2);
      } else {
         return set0 == null ? ImmutableSet.of() : ImmutableSet.copyOf(set0);
      }
   }

   @Nullable
   @Generated
   public Set<String> method6() {
      return this.field1;
   }

   @Nullable
   @Generated
   public Set<String> method7() {
      return this.field2;
   }

   @Nullable
   @Generated
   public Set<String> method8() {
      return this.field3;
   }

   @Nullable
   @Generated
   public String getLanguagePath() {
      return this.field4;
   }

   @Generated
   public ModMetadata(@Nullable Set<String> set1, @Nullable Set<String> set2, @Nullable Set<String> set3, @Nullable String text4) {
      this.field1 = set1;
      this.field2 = set2;
      this.field3 = set3;
      this.field4 = text4;
   }
}
