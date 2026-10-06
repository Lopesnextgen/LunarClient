package com.moonsworth.lunar.client.framework.codegen;

import com.google.common.collect.ImmutableSet;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;
import lombok.Generated;
import com.moonsworth.lunar.client.util.MixinHelper223;
import com.moonsworth.lunar.client.util.Util;
import com.moonsworth.lunar.client.util.UtilType;
import com.moonsworth.lunar.client.util.MixinHelper;
import com.moonsworth.lunar.client.util.MixinHelper23;
import com.moonsworth.lunar.client.util.MixinHelper22;
import com.moonsworth.lunar.client.util.MixinHelper25;
import com.moonsworth.lunar.client.util.MixinHelper224;

public class TypeSpec extends MixinHelper22 {
   private final String field3;
   private final String packageName;
   private final int field4;
   private final List<String> field5;
   private final Set<String> field6;
   private final Set<String> field7;
   private final Set<String> field8;
   private final Set<MixinHelper224> field9;
   private final Set<MixinHelper223> methods;
   private final Set<TypeSpec> field10;
   private final boolean field11;

   private TypeSpec(
      Set<MixinHelper25> var1,
      Set<MixinHelper23> var2,
      String var3,
      String var4,
      int var5,
      List<String> var6,
      Set<String> var7,
      Set<String> var8,
      Set<String> var9,
      Set<MixinHelper224> var10,
      Set<MixinHelper223> var11,
      Set<TypeSpec> var12,
      boolean var13
   ) {
      super(var1, var2);
      this.field3 = var3;
      this.packageName = var4;
      this.field4 = var5;
      this.field5 = var6;
      this.field6 = var7;
      this.field7 = var8;
      this.field8 = var9;
      this.field9 = var10;
      this.methods = var11;
      this.field10 = var12;
      this.field11 = var13;
   }

   @Override
   public String method1(int var1) {
      StringBuilder var2 = new StringBuilder();
      if (!this.field5.isEmpty()) {
         for (String var4 : this.field5) {
            var2.append(var4).append("\n");
         }
      }

      var2.append(this.packageName.isEmpty() ? "" : "package " + this.packageName + ";\n\n");
      if (!this.field6.isEmpty()) {
         for (String var9 : this.field6) {
            var2.append("import ").append(var9).append(";\n");
         }

         var2.append("\n");
      }

      this.method1(var2, var1);
      this.method2(var2, var1);
      String var8 = Modifier.toString(this.field4);
      var2.append(Util.method7(var1)).append(var8.isEmpty() ? "" : var8 + " ").append("class ").append(this.field3);
      if (!this.field7.isEmpty()) {
         StringJoiner var10 = new StringJoiner(",");

         for (String var6 : this.field7) {
            var10.add(var6);
         }

         var2.append(" extends ").append(var10);
      }

      if (!this.field8.isEmpty()) {
         StringJoiner var11 = new StringJoiner(",");

         for (String var19 : this.field8) {
            var11.add(var19);
         }

         var2.append(" implements ").append(var11);
      }

      var2.append(" {\n");
      if (!this.field9.isEmpty()) {
         var2.append("\n");

         for (MixinHelper224 var16 : this.field9) {
            var2.append(var16.method1(var1 + 1)).append("\n\n");
         }
      }

      if (!this.methods.isEmpty()) {
         for (MixinHelper223 var17 : this.methods) {
            var2.append(var17.method1(var1 + 1)).append("\n\n");
         }
      }

      if (!this.field10.isEmpty()) {
         for (TypeSpec var18 : this.field10) {
            var2.append(var18.method1(var1 + 1)).append("\n\n");
         }
      }

      if (this.field11) {
         var2.append(Util.method7(var1 + 1))
            .append("private ")
            .append(this.field3)
            .append("() {")
            .append("\n")
            .append(Util.method7(var1 + 1))
            .append("}")
            .append("\n\n");
      }

      var2.append(Util.method7(var1)).append("}").append("\n");
      return var2.toString();
   }

   public static TypeSpec.Data method2(String var0, String var1) {
      return new TypeSpec.Data(var0, var1);
   }

   public static TypeSpec.Data method3(String var0) {
      return new TypeSpec.Data(var0);
   }

   @Generated
   public String getClassName() {
      return this.field3;
   }

   @Generated
   public String getPackageName() {
      return this.packageName;
   }

   public static class Data extends MixinHelper<TypeSpec.Data> {
      private final String field3;
      private final String field4;
      private final List<String> field5 = new ArrayList<>();
      private final Set<String> field6 = new LinkedHashSet<>();
      private final Set<String> field7 = new HashSet<>();
      private final Set<String> field8 = new HashSet<>();
      private final List<MixinHelper224> field9 = new ArrayList<>();
      private final Set<MixinHelper223> field10 = new HashSet<>();
      private final Set<TypeSpec> field11 = new HashSet<>();
      private boolean field12;
      private int modifiers;

      protected Data(String var1) {
         this.field3 = var1;
         this.field4 = "";
      }

      public TypeSpec.Data method1(UtilType var1) {
         this.modifiers = this.modifiers | switch (var1) {
            case PUBLIC -> 1;
            case PROTECTED -> 4;
            case PRIVATE -> 2;
            case PACKAGE -> 0;
         };
         return this;
      }

      public TypeSpec.Data method2() {
         this.modifiers |= 1024;
         return this;
      }

      public TypeSpec.Data method3() {
         this.modifiers |= 16;
         return this;
      }

      public TypeSpec.Data method4() {
         this.modifiers |= 8;
         return this;
      }

      public TypeSpec.Data method5(List<String> var1) {
         this.field5.addAll(var1);
         return this;
      }

      public TypeSpec.Data method6(String var1) {
         this.field6.add(var1);
         return this;
      }

      public TypeSpec.Data method7(String... var1) {
         this.field6.addAll(List.of(var1));
         return this;
      }

      public TypeSpec.Data method8(List<String> var1) {
         this.field6.addAll(var1);
         return this;
      }

      public TypeSpec.Data method9(String var1) {
         this.field7.add(var1);
         return this;
      }

      public TypeSpec.Data method10(String... var1) {
         this.field7.addAll(List.of(var1));
         return this;
      }

      public TypeSpec.Data method11(String var1) {
         this.field8.add(var1);
         return this;
      }

      public TypeSpec.Data method12(String... var1) {
         this.field8.addAll(List.of(var1));
         return this;
      }

      public TypeSpec.Data method13(MixinHelper224 var1) {
         return this.method14(var1, false);
      }

      public TypeSpec.Data method14(MixinHelper224 var1, boolean var2) {
         if (this.field9.contains(var1)) {
            return this;
         }

         if (var2) {
            this.field9.add(0, var1);
         } else {
            this.field9.add(var1);
         }

         return this;
      }

      public TypeSpec.Data method15(MixinHelper224... var1) {
         for (MixinHelper224 var5 : var1) {
            this.method13(var5);
         }

         return this;
      }

      public TypeSpec.Data method16(MixinHelper224.Data var1) {
         return this.method14(var1.method11(), false);
      }

      public TypeSpec.Data method17(MixinHelper224.Data var1, boolean var2) {
         return this.method14(var1.method11(), var2);
      }

      public TypeSpec.Data method18(MixinHelper224.Data... var1) {
         for (MixinHelper224.Data var5 : var1) {
            this.method16(var5);
         }

         return this;
      }

      public TypeSpec.Data method19(MixinHelper223 var1) {
         this.field10.add(var1);
         return this;
      }

      public TypeSpec.Data method20(MixinHelper223... var1) {
         this.field10.addAll(List.of(var1));
         return this;
      }

      public TypeSpec.Data method21(MixinHelper223.Data var1) {
         this.field10.add(var1.method13());
         return this;
      }

      public TypeSpec.Data method22(MixinHelper223.Data... var1) {
         for (MixinHelper223.Data var5 : var1) {
            this.method21(var5);
         }

         return this;
      }

      public TypeSpec.Data method23(TypeSpec var1) {
         this.field11.add(var1);
         return this;
      }

      public TypeSpec.Data method24(TypeSpec... var1) {
         this.field11.addAll(List.of(var1));
         return this;
      }

      public TypeSpec.Data method25(TypeSpec.Data var1) {
         this.field11.add(var1.method28());
         return this;
      }

      public TypeSpec.Data method26(TypeSpec.Data... var1) {
         for (TypeSpec.Data var5 : var1) {
            this.method25(var5);
         }

         return this;
      }

      public TypeSpec.Data method27() {
         this.field12 = true;
         return this;
      }

      public TypeSpec method28() {
         return new TypeSpec(
            ImmutableSet.copyOf(this.OCIRCRHIRCCHHCROORIROROORCCRCI()),
            ImmutableSet.copyOf(this.OROIORIIOCOCOCROCOOICRIHRCOCIR()),
            this.field3,
            this.field4,
            this.modifiers,
            this.field5,
            ImmutableSet.copyOf(this.field6),
            ImmutableSet.copyOf(this.field7),
            ImmutableSet.copyOf(this.field8),
            ImmutableSet.copyOf(this.field9),
            ImmutableSet.copyOf(this.field10),
            ImmutableSet.copyOf(this.field11),
            this.field12
         );
      }

      @Generated
      protected Data(String var1, String var2) {
         this.field3 = var1;
         this.field4 = var2;
      }
   }
}
