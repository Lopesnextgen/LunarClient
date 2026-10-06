package com.moonsworth.lunar.client.util;

import com.google.common.collect.ImmutableSet;
import java.lang.reflect.Modifier;
import java.util.Set;
import javax.annotation.Nullable;
import lombok.Generated;

public class MixinHelper224 extends MixinHelper22 {
   private String fieldName;
   private String field3;
   @Nullable
   private String field4;
   private int modifiers;
   @Nullable
   private String field5;

   public MixinHelper224(@Nullable String var1) {
      super(null, null);
      this.field5 = var1;
   }

   private MixinHelper224(Set<MixinHelper25> var1, Set<MixinHelper23> var2, String var3, String var4, @Nullable String var5, int var6) {
      super(var1, var2);
      this.fieldName = var3;
      this.field3 = var4;
      this.field4 = var5;
      this.modifiers = var6;
   }

   @Override
   public String method1(int var1) {
      if (this.field5 != null) {
         return Util.method7(var1) + this.field5;
      }

      StringBuilder var2 = new StringBuilder();
      this.method1(var2, var1);
      this.method2(var2, var1);
      var2.append(Util.method7(var1) + Modifier.toString(this.modifiers)).append(" ").append(this.field3).append(" ").append(this.fieldName);
      if (this.field4 != null) {
         String[] var3 = this.field4.split("\n");
         var2.append(" = ").append(var3[0]);

         for (int var4 = 1; var4 < var3.length; var4++) {
            var2.append("\n").append(Util.method7(var1 + 1)).append(var3[var4]);
         }
      }

      var2.append(";");
      return var2.toString();
   }

   public static MixinHelper224.Data method2(String var0, String var1) {
      return new MixinHelper224.Data(var0, var1);
   }

   public static class Data extends MixinHelper<MixinHelper224.Data> {
      private final String field3;
      private final String field4;
      private String field5;
      private int modifiers;

      public MixinHelper224.Data method1(String var1) {
         this.field5 = var1;
         return this;
      }

      public MixinHelper224.Data method2(String... var1) {
         this.field5 = String.join("\n", var1);
         return this;
      }

      public MixinHelper224.Data method3(String var1) {
         if (this.field5 == null) {
            this.field5 = var1;
         } else {
            this.field5 = this.field5 + "\n" + var1;
         }

         return this;
      }

      public MixinHelper224.Data method4(UtilType var1) {
         this.modifiers = this.modifiers | switch (var1) {
            case PUBLIC -> 1;
            case PROTECTED -> 4;
            case PRIVATE -> 2;
            case PACKAGE -> 0;
         };
         return this;
      }

      public MixinHelper224.Data method5() {
         this.modifiers |= 16;
         return this;
      }

      public MixinHelper224.Data method6() {
         this.modifiers |= 8;
         return this;
      }

      public MixinHelper224.Data method7() {
         this.modifiers |= 128;
         return this;
      }

      public MixinHelper224.Data method8() {
         this.modifiers |= 64;
         return this;
      }

      public MixinHelper224 method11() {
         return new MixinHelper224(
            ImmutableSet.copyOf(this.OCIRCRHIRCCHHCROORIROROORCCRCI()),
            ImmutableSet.copyOf(this.OROIORIIOCOCOCROCOOICRIHRCOCIR()),
            this.field3,
            this.field4,
            this.field5,
            this.modifiers
         );
      }

      @Generated
      protected Data(String var1, String var2) {
         this.field3 = var1;
         this.field4 = var2;
      }
   }
}
