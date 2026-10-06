package com.moonsworth.lunar.client.util;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import lombok.Generated;

public class MixinHelper223 extends MixinHelper22 {
   private final String field3;
   private final String returnType;
   private final List<MixinHelper24<String>> params;
   private final List<String> field4;
   private final int field5;
   private final boolean field6;

   private MixinHelper223(
      Set<MixinHelper25> var1, Set<MixinHelper23> var2, String var3, String var4, List<MixinHelper24<String>> var5, List<String> var6, int var7, boolean var8
   ) {
      super(var1, var2);
      this.field3 = var3;
      this.returnType = var4;
      this.params = var5;
      this.field4 = var6;
      this.field5 = var7;
      this.field6 = var8;
   }

   @Override
   public String method1(int var1) {
      StringBuilder var2 = new StringBuilder();
      this.method1(var2, var1);
      this.method2(var2, var1);
      String var3 = Modifier.toString(this.field5);
      var2.append(Util.method7(var1)).append(var3);
      if (!this.field6) {
         if (!var3.isEmpty()) {
            var2.append(" ");
         }

         var2.append(this.returnType);
      }

      var2.append(" ").append(this.field3).append("(");
      boolean var4 = true;

      for (MixinHelper24 var6 : this.params) {
         if (var4) {
            var4 = false;
         } else {
            var2.append(", ");
         }

         var2.append(var6.method1(0));
      }

      var2.append(") {\n");

      for (String var8 : this.field4) {
         var2.append(Util.method8(var8, var1 + 1)).append("\n");
      }

      var2.append(Util.method7(var1)).append("}");
      return var2.toString();
   }

   public static MixinHelper223.Data method2(String var0, String var1) {
      return new MixinHelper223.Data(var0, var1);
   }

   public static class Data extends MixinHelper<MixinHelper223.Data> {
      private final String field3;
      private final String field4;
      private final List<MixinHelper24<String>> field5 = new ArrayList<>();
      private final List<String> field6 = new ArrayList<>();
      private int modifiers;
      private boolean field7;

      public MixinHelper223.Data method1() {
         this.field7 = true;
         return this;
      }

      public MixinHelper223.Data method2(MixinHelper24<String> var1) {
         this.field5.add(var1);
         return this;
      }

      @SafeVarargs
      public final MixinHelper223.Data method3(MixinHelper24<String>... var1) {
         this.field5.addAll(List.of(var1));
         return this;
      }

      public MixinHelper223.Data method4(String var1) {
         this.field6.add(var1);
         return this;
      }

      public MixinHelper223.Data method5(String... var1) {
         this.field6.addAll(List.of(var1));
         return this;
      }

      public MixinHelper223.Data method6(UtilType var1) {
         this.modifiers = this.modifiers | switch (var1) {
            case PUBLIC -> 1;
            case PROTECTED -> 4;
            case PRIVATE -> 2;
            case PACKAGE -> 0;
         };
         return this;
      }

      public MixinHelper223.Data method7() {
         this.modifiers |= 1024;
         return this;
      }

      public MixinHelper223.Data method8() {
         this.modifiers |= 16;
         return this;
      }

      public MixinHelper223.Data method11() {
         this.modifiers |= 8;
         return this;
      }

      public MixinHelper223.Data method12() {
         this.modifiers |= 16;
         return this;
      }

      public MixinHelper223 method13() {
         return new MixinHelper223(
            ImmutableSet.copyOf(this.OCIRCRHIRCCHHCROORIROROORCCRCI()),
            ImmutableSet.copyOf(this.OROIORIIOCOCOCROCOOICRIHRCOCIR()),
            this.field3,
            this.field4,
            ImmutableList.copyOf(this.field5),
            ImmutableList.copyOf(this.field6),
            this.modifiers,
            this.field7
         );
      }

      @Generated
      protected Data(String var1, String var2) {
         this.field3 = var1;
         this.field4 = var2;
      }
   }
}
