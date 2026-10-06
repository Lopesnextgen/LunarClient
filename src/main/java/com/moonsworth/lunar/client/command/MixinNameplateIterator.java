package com.moonsworth.lunar.client.command;

import com.moonsworth.lunar.client.command.Nameplate2_2;
import com.moonsworth.lunar.client.util.ThreadModuleDump27;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class MixinNameplateIterator extends MixinNameplate {
   private final String field3;
   private final MixinCore<?> field4;
   @Nullable
   private Nameplate2_2 field5;

   public MixinNameplateIterator(String var1, MixinCore<?> var2) {
      this.field3 = var1;
      this.field4 = var2;
   }

   public static MixinNameplateIterator method1(String text, MixinCore<?> var1) {
      return new MixinNameplateIterator(text, var1);
   }

   public MixinNameplateIterator method2(Nameplate2_2 var1) {
      this.field5 = var1;
      return this;
   }

   @Nullable
   public Nameplate2_2 method3() {
      return this.field5 != null ? this.field5 : this.field4.method3();
   }

   public MixinNameplateIterator method4(String... var1) {
      return this.method5(Arrays.asList(var1));
   }

   public MixinNameplateIterator method5(Iterable<String> var1) {
      return this.method2((var1x, var2) -> {
         String var3 = var2.method1().toLowerCase(Locale.ROOT);

         for (String var5 : var1) {
            if (var5.toLowerCase(Locale.ROOT).startsWith(var3)) {
               var2.method2(var5);
            }
         }
      });
   }

   public MixinNameplateIterator method6(Enum<?>[] var1) {
      ArrayList var2 = new ArrayList(var1.length);

      for (Enum var6 : var1) {
         var2.add(var6.name());
      }

      return this.method5(var2);
   }

   public MixinNameplateIterator method7(MixinNameplate var1) {
      super.method1(var1);
      return this;
   }

   public MixinNameplateIterator method8(MixinHelper var1) {
      super.method2(var1);
      return this;
   }

   @Override
   public boolean method3(ThreadModuleDump27 var1, MixinHelper22 var2) {
      Object var3 = this.field4.method1(var1);
      if (var3 == null) {
         return false;
      }

      var2.method1(this.field3, var3);
      return true;
   }

   @Generated
   public String getName() {
      return this.field3;
   }

   @Generated
   public MixinCore<?> method10() {
      return this.field4;
   }
}
