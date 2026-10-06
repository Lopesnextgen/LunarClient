package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.config.Config;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.MethodNode;

public class Bridge4_2 {
   private final String field1;
   private final Bridge5_14[] field2;

   public Bridge4_2(String var1, Bridge5_14[] var2) {
      this.field1 = var1;
      this.field2 = var2;
   }

   @NotNull
   public static Bridge4_2 method1(AnnotationNode var0) {
      return method2(var0, null);
   }

   @NotNull
   public static Bridge4_2 method2(AnnotationNode var0, @Nullable MethodNode var1) {
      String var2 = null;
      Bridge5_14[] var3 = null;
      List var4 = var0.values;
      if (var4 != null) {
         for (byte var5 = 0; var5 < var4.size(); var5 += 2) {
            String var6 = var4.get(var5).toString();
            Object var7 = var4.get(var5 + 1);
            if (var7 instanceof String var8) {
               if (!var8.isEmpty()) {
                  var2 = var7.toString();
               }
            } else if (var7 instanceof List var9) {
               List var10 = var9;
               var3 = new Bridge5_14[var10.size()];

               for (int var11 = 0; var11 < var10.size(); var11++) {
                  AnnotationNode var12 = (AnnotationNode)var10.get(var11);
                  List var13 = var12.values;
                  Integer var14 = null;
                  Bridge6_6[] var15 = null;

                  for (byte var16 = 0; var16 < var13.size(); var16 += 2) {
                     String var17 = var13.get(var16).toString();
                     Object var18 = var13.get(var16 + 1);
                     if (var18 instanceof Integer var19) {
                        var14 = var19;
                     } else if (var18 instanceof List var20) {
                        List var21 = var20;
                        var15 = new Bridge6_6[var21.size()];

                        for (int var22 = 0; var22 < var21.size(); var22++) {
                           AnnotationNode var23 = (AnnotationNode)var21.get(var22);
                           List var24 = var23.values;
                           String[] var25 = null;

                           for (byte var26 = 0; var26 < var24.size(); var26 += 2) {
                              String var27 = var24.get(var26).toString();
                              if (var24.get(var26 + 1) instanceof List var29) {
                                 ArrayList var30 = (ArrayList)var29;
                                 if (var30.size() > 0) {
                                    var25 = var30.toArray(new String[0]);
                                 }
                              }
                           }

                           var15[var22] = new Bridge6_6(var25);
                        }
                     }
                  }

                  if (var14 != null && var15 != null) {
                     var3[var11] = new Bridge5_14(var14, var15);
                  }
               }
            }
         }
      }

      if (var2 == null && var1 != null && var3 == null) {
         var2 = var1.name;
         if (var2.startsWith("bridge$")) {
            var2 = var2.replace("bridge$", "");
         }
      }

      if (var2 == null && var3 == null) {
         throw new IllegalStateException("Malformed bridge: new views present in " + var0.desc);
      } else {
         return new Bridge4_2(var2, var3);
      }
   }

   @Nullable
   public Bridge6_6[] method3(Config var1) {
      Bridge6_6[] var2 = null;
      if (this.field1 != null) {
         var2 = new Bridge6_6[]{new Bridge6_6(new String[]{this.field1})};
      }

      if (this.field2 != null) {
         for (Bridge5_14 var6 : this.field2) {
            Config var7 = Config.method2(var6.version()).orElseThrow(() -> new IllegalStateException("Failed to parse Minecraft version " + var6.version()));
            if (var7 == var1) {
               return var6.method1();
            }

            if (var1.method3(var7)) {
               var2 = var6.method1();
            }
         }
      }

      return var2;
   }

   public String name() {
      return this.field1;
   }

   public Bridge5_14[] method4() {
      return this.field2;
   }
}
