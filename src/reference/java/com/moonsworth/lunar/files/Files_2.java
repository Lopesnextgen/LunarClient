package com.moonsworth.lunar.files;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.Generated;
import org.cadixdev.lorenz.MappingSet;
import org.cadixdev.lorenz.model.ClassMapping;
import org.cadixdev.lorenz.model.FieldMapping;
import org.cadixdev.lorenz.model.InnerClassMapping;
import org.cadixdev.lorenz.model.MethodMapping;
import org.cadixdev.lorenz.model.TopLevelClassMapping;

public class Files_2 {
   private final List<Files$Data3> field1;

   public Files_2 method1(Files_2 var1) {
      ArrayList var2 = new ArrayList(this.field1.size());

      class Data4 {
         private final String field1;
         private final String field2;

         Data4(String var1, String var2) {
            this.field1 = var1;
            this.field2 = var2;
         }

         public String name() {
            return this.field1;
         }

         public String method1() {
            return this.field2;
         }
      }

      method2(
         this.field1,
         var1.field1,
         Files$Data3::getNewName,
         Files$Data3::method1,
         (var1x, var2x) -> {
            Files$Data3 var3 = new Files$Data3(var1x.field1, var2x.field2);
            method2(
               var1x.field3,
               var2x.field3,
               Files$Data5::getNewName,
               Files$Data5::method1,
               (var1xx, var2xx) -> var3.field3.add(new Files$Data5(var1xx.field1, var1xx.field2, var2xx.field3, var2xx.field4))
            );
            method2(
               var1x.field4,
               var2x.field4,
               var0x -> new Data4(var0x.field3, var0x.field4),
               var0x -> new Data4(var0x.field1, var0x.field2),
               (var1xx, var2xx) -> var3.field4.add(new Files$Data6(var1xx.field1, var1xx.field2, var2xx.field3, var2xx.field4))
            );
            var2.add(var3);
         }
      );
      return new Files_2(var2);
   }

   private static <T, K> void method2(List<T> var0, List<T> var1, Function<T, K> var2, Function<T, K> var3, BiConsumer<T, T> var4) {
      Map var5 = var1.stream().collect(Collectors.toMap(var3, Function.identity()));

      for (Object var7 : var0) {
         Object var8 = var5.get(var2.apply(var7));
         if (var8 != null) {
            var4.accept(var7, var8);
         }
      }
   }

   public MappingSet method3(MappingSet var1, MappingSet var2) {
      MappingSet var3 = MappingSet.create();

      for (Files$Data3 var5 : this.field1) {
         ClassMapping var6 = var1.getOrCreateClassMapping(var5.field1);
         ClassMapping var7 = var2.getOrCreateClassMapping(var5.field2);
         ClassMapping var8 = var3.getOrCreateClassMapping(var6.getFullDeobfuscatedName());
         var8.setDeobfuscatedName(var7.getFullDeobfuscatedName());

         for (Files$Data5 var10 : var5.field3) {
            var6.getFieldMapping(var10.field1)
               .ifPresent(
                  var3x -> var7.getFieldMapping(var10.field3)
                     .ifPresent(var2xx -> var8.createFieldMapping(var3x.getDeobfuscatedName(), var2xx.getDeobfuscatedName()))
               );
         }

         for (Files$Data6 var12 : var5.field4) {
            var6.getMethodMapping(var12.field1, var12.field2)
               .ifPresent(
                  var3x -> var7.getMethodMapping(var12.field3, var12.field4)
                     .ifPresent(var2xx -> var8.createMethodMapping(var3x.getDeobfuscatedSignature()).setDeobfuscatedName(var2xx.getDeobfuscatedName()))
               );
         }
      }

      return var3;
   }

   public MappingSet method4(MappingSet var1) {
      MappingSet var2 = MappingSet.create();

      for (Files$Data3 var4 : this.field1) {
         var1.getClassMapping(var4.field1)
            .ifPresent(
               var2x -> {
                  ClassMapping var3 = var2.getOrCreateClassMapping(var4.field2);
                  var3.setDeobfuscatedName(var2x.getFullDeobfuscatedName());

                  for (Files$Data5 var5 : var4.field3) {
                     var2x.getFieldMapping(var5.field1)
                        .ifPresent(var2xx -> var3.getOrCreateFieldMapping(var5.field3).setDeobfuscatedName(var2xx.getFullObfuscatedName()));
                  }

                  for (Files$Data6 var7 : var4.field4) {
                     var2x.getMethodMapping(var7.field1, var7.field2)
                        .ifPresent(var2xx -> var3.getOrCreateMethodMapping(var7.field3, var7.field4).setDeobfuscatedName(var2xx.getDeobfuscatedName()));
                  }
               }
            );
      }

      return var2;
   }

   public Files_2 method5() {
      ArrayList var1 = new ArrayList();

      for (Files$Data3 var3 : this.field1) {
         Files$Data3 var4 = new Files$Data3(var3.field2, var3.field1);
         var1.add(var4);

         for (Files$Data5 var6 : var3.field3) {
            Files$Data5 var7 = new Files$Data5(var6.field3, var6.field4, var6.field1, var6.field2);
            var4.field3.add(var7);
         }

         for (Files$Data6 var9 : var3.field4) {
            Files$Data6 var10 = new Files$Data6(var9.field3, var9.field4, var9.field1, var9.field4);
            var4.field4.add(var10);
         }
      }

      return new Files_2(var1);
   }

   public MappingSet method6() {
      MappingSet var1 = MappingSet.create();

      for (Files$Data3 var3 : this.field1) {
         ClassMapping var4 = var1.getOrCreateClassMapping(var3.field1);
         var4.setDeobfuscatedName(var3.field2);

         for (Files$Data5 var6 : var3.field3) {
            var4.createFieldMapping(var6.field1).setDeobfuscatedName(var6.field3);
         }

         for (Files$Data6 var8 : var3.field4) {
            var4.createMethodMapping(var8.field1, var8.field2).setDeobfuscatedName(var8.field3);
         }
      }

      return var1;
   }

   public static Files_2 method7(List<String> var0) {
      try {
         ArrayList var1 = new ArrayList();
         Files$Data3 var2 = null;

         for (String var4 : var0) {
            if (var4.contains("\t")) {
               String[] var5 = var4.split("\t");
               if (var4.startsWith("c\t")) {
                  String var6 = var5[1].substring(1, var5[1].length() - 1);
                  String var7 = var5[2].substring(1, var5[2].length() - 1);
                  var2 = new Files$Data3(var6, var7);
                  var1.add(var2);
               } else if (var4.startsWith("\tm\t")) {
                  if (var2 != null) {
                     String var15 = var5[2];
                     int var17 = var15.indexOf(40);
                     String var8 = var15.substring(0, var17);
                     String var9 = var15.substring(var17);
                     String var10 = var5[3];
                     var17 = var10.indexOf(40);
                     String var11 = var10.substring(0, var17);
                     String var12 = var10.substring(var17);
                     var2.field4.add(new Files$Data6(var8, var9, var11, var12));
                  }
               } else if (var4.startsWith("\tf\t") && var2 != null) {
                  String var16 = var5[2];
                  String[] var19 = var16.split(";;");
                  String var20 = var19[0];
                  String var21 = var19[1];
                  String var22 = var5[3];
                  String[] var23 = var22.split(";;");
                  String var24 = var23[0];
                  String var13 = var23[1];
                  var2.field3.add(new Files$Data5(var20, var21, var24, var13));
               }
            }
         }

         return new Files_2(var1);
      } catch (Throwable var14) {
         throw var14;
      }
   }

   public static Files_2 method8(MappingSet var0) {
      ArrayList var1 = new ArrayList();

      for (TopLevelClassMapping var3 : var0.getTopLevelClassMappings()) {
         method9(var1, var3);
      }

      return new Files_2(var1);
   }

   private static void method9(List<Files$Data3> var0, ClassMapping<?, ?> var1) {
      Files$Data3 var2 = new Files$Data3(var1.getFullObfuscatedName(), var1.getFullDeobfuscatedName());
      var0.add(var2);

      for (FieldMapping var4 : var1.getFieldMappings()) {
         var2.field3
            .add(
               new Files$Data5(var4.getObfuscatedName(), var4.getSignature().toString(), var4.getDeobfuscatedName(), var4.getDeobfuscatedSignature().toString())
            );
      }

      for (MethodMapping var7 : var1.getMethodMappings()) {
         var2.field4
            .add(
               new Files$Data6(var7.getObfuscatedName(), var7.getSignature().toString(), var7.getDeobfuscatedName(), var7.getDeobfuscatedSignature().toString())
            );
      }

      for (InnerClassMapping var8 : var1.getInnerClassMappings()) {
         method9(var0, var8);
      }
   }

   public static Files_2 method10(MappingSet var0, MappingSet var1) {
      ArrayList var2 = new ArrayList();

      label23:
      for (TopLevelClassMapping var4 : var0.getTopLevelClassMappings()) {
         for (TopLevelClassMapping var6 : var1.getTopLevelClassMappings()) {
            if (var4.getFullDeobfuscatedName().equals(var6.getFullDeobfuscatedName())) {
               method11(var2, var4, var6);
               continue label23;
            }
         }

         System.out.println("Couldn't find match for " + var4.getFullDeobfuscatedName());
      }

      return new Files_2(var2);
   }

   private static void method11(List<Files$Data3> var0, ClassMapping<?, ?> var1, ClassMapping<?, ?> var2) {
      Files$Data3 var3 = new Files$Data3(var1.getFullObfuscatedName(), var2.getFullObfuscatedName());
      var0.add(var3);

      for (FieldMapping var5 : var1.getFieldMappings()) {
         for (FieldMapping var7 : var2.getFieldMappings()) {
            if (var5.getDeobfuscatedName().equals(var7.getDeobfuscatedName())) {
               Files$Data5 var8 = new Files$Data5(
                  var5.getObfuscatedName(),
                  var5.getSignature().getType().map(Object::toString).orElse(""),
                  var7.getObfuscatedName(),
                  var7.getSignature().getType().map(Object::toString).orElse("")
               );
               var3.field3.add(var8);
               break;
            }
         }
      }

      for (MethodMapping var11 : var1.getMethodMappings()) {
         for (MethodMapping var15 : var2.getMethodMappings()) {
            if (var11.getDeobfuscatedName().equals(var15.getDeobfuscatedName())
               && var11.getSignature().getDescriptor().toString().equals(var15.getSignature().getDescriptor().toString())) {
               Files$Data6 var17 = new Files$Data6(
                  var11.getObfuscatedName(),
                  var11.getSignature().getDescriptor().toString(),
                  var15.getObfuscatedName(),
                  var15.getSignature().getDescriptor().toString()
               );
               var3.field4.add(var17);
               break;
            }
         }
      }

      for (InnerClassMapping var12 : var1.getInnerClassMappings()) {
         for (InnerClassMapping var16 : var2.getInnerClassMappings()) {
            method11(var0, var12, var16);
         }
      }
   }

   @Generated
   public Files_2(List<Files$Data3> var1) {
      this.field1 = var1;
   }

   @Generated
   public List<Files$Data3> method12() {
      return this.field1;
   }

   @Generated
   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof Files_2 var2)) {
         return false;
      } else {
         if (!var2.canEqual(this)) {
            return false;
         }

         List var3 = this.method12();
         List var4 = var2.method12();
         return var3 == null ? var4 == null : var3.equals(var4);
      }
   }

   @Generated
   protected boolean canEqual(Object var1) {
      return var1 instanceof Files_2;
   }

   @Generated
   @Override
   public int hashCode() {
      byte var1 = 59;
      byte var2 = 1;
      List var3 = this.method12();
      return var2 * 59 + (var3 == null ? 43 : var3.hashCode());
   }

   @Generated
   @Override
   public String toString() {
      return "Match(classMatches=" + this.method12() + ")";
   }
}
