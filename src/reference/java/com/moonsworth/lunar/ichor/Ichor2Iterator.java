package com.moonsworth.lunar.ichor;

import com.moonsworth.lunar.ichor.util.FatalIchorError6;
import com.moonsworth.lunar.loader.Ichor4Type;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;
import org.cadixdev.bombe.type.signature.FieldSignature;
import org.cadixdev.bombe.type.signature.MethodSignature;
import org.cadixdev.lorenz.MappingSet;
import org.cadixdev.lorenz.model.Mapping;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.InnerClassNode;
import org.objectweb.asm.tree.MethodNode;

public class Ichor2Iterator implements IchorInjection {
   private final Ichor2Iterator.AccessWideningRules field1;

   public Ichor2Iterator(MappingSet var1, InputStream var2) {
      try {
         this.field1 = method7(var2, var1);
      } catch (Throwable var4) {
         throw var4;
      }
   }

   @Override
   public Ichor4[] method2() {
      return new Ichor4[]{Ichor4Type.ACCESS_WIDEN};
   }

   @Annotation7
   public void method2(ClassNode var1) {
      Ichor2Iterator.Data2 var2 = this.field1.field1.get(method6(var1.name));
      if (var2 != null) {
         if (var2.field1) {
            var1.access = FatalIchorError6.method3(var1.access);
         }

         if (var2.field2) {
            var1.access = FatalIchorError6.method4(var1.access, true);
            var1.permittedSubclasses = null;
         }
      }

      for (InnerClassNode var4 : var1.innerClasses) {
         Ichor2Iterator.Data2 var5 = this.field1.field1.get(method6(var4.name));
         if (var5 != null) {
            if (var5.field1) {
               var4.access = FatalIchorError6.method3(var4.access);
            }

            if (var5.field2) {
               var4.access = FatalIchorError6.method4(var4.access, true);
            }
         }
      }
   }

   @Annotation11
   public void method3(ClassNode var1, FieldNode var2) {
      Ichor2Iterator.Data4 var3 = this.field1.fields.get(method6(var1.name) + "." + method5(var2.name) + method6(var2.desc));
      if (var3 != null) {
         if (var3.field1) {
            var2.access = FatalIchorError6.method3(var2.access);
         }

         if (var3.mutable) {
            var2.access &= -17;
         }
      }
   }

   @Annotation
   public void method4(ClassNode var1, MethodNode var2) {
      Ichor2Iterator.Data5 var3 = this.field1.field2.get(method6(var1.name) + "." + method5(var2.name) + method6(var2.desc));
      if (var3 != null) {
         if (var3.field1) {
            var2.access = FatalIchorError6.method3(var2.access);
         }

         if (var3.field2) {
            var2.access &= -17;
            if ((var2.access & 1) == 0) {
               var2.access = var2.access & -3 | 4;
            }
         }
      }
   }

   private static String method5(String var0) {
      int var1 = var0.lastIndexOf("$v");
      if (var1 > 0 && var1 + 2 < var0.length() && Character.isDigit(var0.charAt(var1 + 2))) {
         for (int var2 = var1 + 3; var2 < var0.length(); var2++) {
            char var3 = var0.charAt(var2);
            if (!Character.isLetterOrDigit(var3) && var3 != '_') {
               return var0;
            }
         }

         return var0.substring(0, var1);
      } else {
         return var0;
      }
   }

   private static String method6(String var0) {
      int var1;
      while ((var1 = var0.indexOf("_v")) > 0 && var1 + 2 < var0.length() && Character.isDigit(var0.charAt(var1 + 2))) {
         int var2 = var1 + 3;

         while (true) {
            if (var2 < var0.length()) {
               char var3 = var0.charAt(var2);
               if (Character.isLetterOrDigit(var3) || var3 == '_') {
                  var2++;
                  continue;
               }
            }

            var0 = var0.substring(0, var1) + var0.substring(var2);
            break;
         }
      }

      return var0;
   }

   private static Ichor2Iterator.AccessWideningRules method7(InputStream var0, @Nullable MappingSet var1) {
      Ichor2Iterator.AccessWideningRules var2 = new Ichor2Iterator.AccessWideningRules();

      try (BufferedReader var3 = new BufferedReader(new InputStreamReader(var0))) {
         boolean var4 = false;

         String var5;
         while ((var5 = var3.readLine()) != null) {
            int var6 = var5.indexOf(35);
            if (var6 >= 0) {
               var5 = var5.substring(0, var6);
            }

            var5 = var5.strip();
            if (!var5.isEmpty()) {
               if (!var4) {
                  var4 = true;
               } else {
                  String[] var7 = var5.split("\\s+");
                  if (var7.length >= 3) {
                     String var8 = var7[0];
                     String var9 = var7[1];
                     String var10 = var7[2];
                     switch (var9) {
                        case "class":
                           String var21 = var1 != null ? method8(var10, var1) : var10;
                           Ichor2Iterator.Data2 var23 = var2.field1.computeIfAbsent(var21, var0x -> new Ichor2Iterator.Data2());
                           if ("accessible".equals(var8)) {
                              var23.field1 = true;
                           }

                           if ("extendable".equals(var8)) {
                              var23.field2 = true;
                           }
                           break;
                        case "method":
                           if (var7.length >= 5) {
                              String var20 = var7[3];
                              String var22 = var7[4];
                              String var24 = var1 != null
                                 ? method8(var10, var1) + "." + method9(var10, var20, var22, var1) + method11(var22, var1)
                                 : var10 + "." + var20 + var22;
                              Ichor2Iterator.Data5 var25 = var2.field2.computeIfAbsent(var24, var0x -> new Ichor2Iterator.Data5());
                              if ("accessible".equals(var8)) {
                                 var25.field1 = true;
                              }

                              if ("extendable".equals(var8)) {
                                 var25.field2 = true;
                              }
                           }
                           break;
                        case "field":
                           if (var7.length >= 5) {
                              String var13 = var7[3];
                              String var14 = var7[4];
                              String var15 = var1 != null
                                 ? method8(var10, var1) + "." + method10(var10, var13, var14, var1) + method11(var14, var1)
                                 : var10 + "." + var13 + var14;
                              Ichor2Iterator.Data4 var16 = var2.fields.computeIfAbsent(var15, var0x -> new Ichor2Iterator.Data4());
                              if ("accessible".equals(var8)) {
                                 var16.field1 = true;
                              }

                              if ("mutable".equals(var8)) {
                                 var16.mutable = true;
                              }
                           }
                     }
                  }
               }
            }
         }
      }

      return var2;
   }

   private static String method8(String var0, MappingSet var1) {
      return var1.computeClassMapping(var0).<String>map(Mapping::getFullDeobfuscatedName).orElse(var0);
   }

   private static String method9(String var0, String var1, String var2, MappingSet var3) {
      return var3.computeClassMapping(var0)
         .flatMap(var2x -> var2x.getMethodMapping(MethodSignature.of(var1, var2)))
         .<String>map(Mapping::getDeobfuscatedName)
         .orElse(var1);
   }

   private static String method10(String var0, String var1, String var2, MappingSet var3) {
      return var3.computeClassMapping(var0)
         .flatMap(var2x -> var2x.computeFieldMapping(FieldSignature.of(var1, var2)))
         .<String>map(Mapping::getDeobfuscatedName)
         .orElse(var1);
   }

   private static String method11(String var0, MappingSet var1) {
      StringBuilder var2 = new StringBuilder(var0.length());
      int var3 = 0;

      while (var3 < var0.length()) {
         char var4 = var0.charAt(var3++);
         if (var4 == 'L') {
            int var5 = var0.indexOf(59, var3);
            if (var5 >= 0) {
               var2.append('L').append(method8(var0.substring(var3, var5), var1)).append(';');
               var3 = var5 + 1;
               continue;
            }
         }

         var2.append(var4);
      }

      return var2.toString();
   }

   private static class Data2 {
      boolean field1;
      boolean field2;
   }

   private static class AccessWideningRules {
      final Map<String, Ichor2Iterator.Data2> field1 = new HashMap<>();
      final Map<String, Ichor2Iterator.Data5> field2 = new HashMap<>();
      final Map<String, Ichor2Iterator.Data4> fields = new HashMap<>();
   }

   private static class Data4 {
      boolean field1;
      boolean mutable;
   }

   private static class Data5 {
      boolean field1;
      boolean field2;
   }
}
