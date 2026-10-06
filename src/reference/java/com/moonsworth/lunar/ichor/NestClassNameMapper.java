package com.moonsworth.lunar.ichor;

import com.moonsworth.lunar.config.Config;
import com.moonsworth.lunar.ichor.util.FatalIchorError7;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;
import java.util.function.BiFunction;
import lombok.Generated;
import org.cadixdev.bombe.type.ArrayType;
import org.cadixdev.bombe.type.FieldType;
import org.cadixdev.bombe.type.MethodDescriptor;
import org.cadixdev.bombe.type.ObjectType;
import org.cadixdev.bombe.type.Type;

public class NestClassNameMapper implements MixinMisc2 {
   private final Map<String, List<InnerClassRenameInfo>> field1 = new HashMap<>();
   private final Map<String, String> field2 = new HashMap<>();
   private final Map<String, String> field3 = new HashMap<>();

   public void method1(List<String> var1) {
      try {
         HashMap var2 = new HashMap();

         for (String var4 : var1) {
            if (!var4.startsWith("#") && !var4.isBlank()) {
               String[] var5 = var4.split("\t");
               if (var5.length == 6) {
                  String var6 = var5[0];
                  String var7 = var5[1];
                  String var8 = var5[2];
                  String var9 = var5[3];
                  String var10 = var5[4];
                  InnerClassRenameInfo.Type2 var11 = InnerClassRenameInfo.Type2.INNER;
                  if (Character.isDigit(var10.charAt(0))) {
                     try {
                        Integer.parseInt(var10);
                        var11 = InnerClassRenameInfo.Type2.ANONYMOUS;
                     } catch (NumberFormatException var13) {
                        var11 = InnerClassRenameInfo.Type2.LOCAL;
                     }
                  }

                  InnerClassRenameInfo var12 = new InnerClassRenameInfo(
                     var6, var7, var8.isBlank() ? null : var8, var9.isBlank() ? null : var9, var10, Integer.parseInt(var5[5]), var11
                  );
                  var2.put(var6, var12);
               }
            }
         }

         for (Entry var16 : var2.entrySet()) {
            InnerClassRenameInfo var17 = (InnerClassRenameInfo)var16.getValue();
            ArrayList var18 = new ArrayList();
            var18.add(var17);

            for (InnerClassRenameInfo var19 = (InnerClassRenameInfo)var2.get(var17.method3()); var19 != null; var19 = (InnerClassRenameInfo)var2.get(var19.method3())) {
               var18.add(var19);
            }

            for (int var20 = var18.size() - 1; var20 > 0; var20--) {
               InnerClassRenameInfo var22 = (InnerClassRenameInfo)var18.get(var20);
               InnerClassRenameInfo var24 = (InnerClassRenameInfo)var18.get(var20 - 1);
               var24.method10(var22.method1());
            }

            BiFunction var21 = (var1x, var2x) -> {
               if (var2x == null) {
                  var2x = new ArrayList();
               }

               var2x.add(var17);
               return var2x;
            };
            String var23 = var17.method2();
            this.field1.compute(var23, var21);
            this.field1.compute(var17.method3(), var21);
            String var25 = var17.method1();
            this.field2.put(var23, var25);
            this.field3.put(var25, var23);
         }
      } catch (Throwable var14) {
         throw var14;
      }
   }

   @Override
   public String remap(String var1) {
      return this.field2.getOrDefault(var1, var1);
   }

   @Override
   public String unmap(String var1) {
      return this.field3.getOrDefault(var1, var1);
   }

   public FieldType method2(FieldType var1) {
      if (var1 instanceof ObjectType var2) {
         return new ObjectType(this.remap(var2.getClassName()));
      } else {
         return (FieldType)(var1 instanceof ArrayType var3 ? new ArrayType(var3.getDimCount(), this.method2(var3.getComponent())) : var1);
      }
   }

   public MethodDescriptor method3(MethodDescriptor var1) {
      Object var2 = var1.getReturnType();
      if (var2 instanceof FieldType var3) {
         var2 = this.method2(var3);
      }

      List var4 = var1.getParamTypes().stream().map(this::method2).toList();
      return new MethodDescriptor(var4, (Type)var2);
   }

   public static Optional<NestClassNameMapper> method4(Config var0) {
      String var1 = var0.method45() + ".nest";

      try (InputStream var2 = NestClassNameMapper.class.getClassLoader().getResourceAsStream(var1)) {
         if (var2 != null) {
            String[] var3 = new String(FatalIchorError7.toByteArray(var2)).split("\n");
            NestClassNameMapper var4 = new NestClassNameMapper();
            var4.method1(List.of(var3));
            return Optional.of(var4);
         }
      } catch (IOException var8) {
         var8.printStackTrace();
      }

      return Optional.empty();
   }

   @Generated
   public Map<String, List<InnerClassRenameInfo>> method5() {
      return this.field1;
   }

   @Generated
   public Map<String, String> method6() {
      return this.field2;
   }

   @Generated
   public Map<String, String> method7() {
      return this.field3;
   }
}
