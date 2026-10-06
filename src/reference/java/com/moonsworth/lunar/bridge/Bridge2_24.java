package com.moonsworth.lunar.bridge;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.moonsworth.lunar.config.Config;
import com.moonsworth.lunar.ichor.MixinMisc4;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Nullable;
import lombok.Generated;
import org.cadixdev.bombe.provider.ClassProvider;
import org.cadixdev.lorenz.MappingSet;
import org.cadixdev.lorenz.model.Mapping;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

public class Bridge2_24 {
   private static final String field1 = Annotation.class.descriptorString();
   private final Map<String, Bridge2_35> field2 = new HashMap<>();
   private boolean initialized = false;
   private final String field3;
   @Nullable
   private final MappingSet field4;

   public Bridge2_24(String var1) {
      this(var1, null);
   }

   public Bridge2_24(String var1, @Nullable MappingSet var2) {
      this.field3 = var1;
      this.field4 = var2;
   }

   private void method1(ClassProvider var1) {
      if (!this.initialized) {
         this.initialized = true;
         JsonElement var2 = JsonParser.parseString(this.field3);
         if (var2.isJsonObject()) {
            JsonObject var3 = var2.getAsJsonObject();
            if (var3.has("bridges")) {
               for (JsonElement var6 : var3.getAsJsonArray("bridges")) {
                  String var7 = var6.getAsString();
                  ClassNode var8 = var1.getAsNode(var7, 7);
                  Bridge2_35 var9 = this.method2(var8);
                  if (var9 != null) {
                     this.field2.put(var7, var9);
                  }
               }
            }
         }
      }
   }

   @Nullable
   public Bridge2_35 method2(ClassNode var1) {
      if ((var1.access & 512) != 0 && var1.visibleAnnotations != null) {
         AnnotationNode var2 = null;

         for (AnnotationNode var4 : var1.visibleAnnotations) {
            if (var4.desc.equals(field1)) {
               var2 = var4;
               break;
            }
         }

         if (var2 == null) {
            return null;
         }

         Bridge4_2 var10 = Bridge4_2.method1(var2);
         ArrayList var11 = new ArrayList();

         for (MethodNode var6 : var1.methods) {
            AnnotationNode var7 = null;
            if (var6.visibleAnnotations != null) {
               for (AnnotationNode var9 : var6.visibleAnnotations) {
                  if (var9.desc.equals(field1)) {
                     var7 = var9;
                     break;
                  }
               }
            }

            if (var7 != null) {
               Bridge4_2 var12 = Bridge4_2.method2(var7, var6);
               var11.add(new Bridge3_5(var12, var6));
            }
         }

         return new Bridge2_35(var1.name, var10, var11.toArray(new Bridge3_5[0]));
      } else {
         return null;
      }
   }

   public void method3(ClassNode var1, Config var2, ClassProvider var3) {
      this.method1(var3);
      String var4 = this.field4 == null ? var1.name : MixinMisc4.method2(this.field4, var1.name);
      this.method4(var4, var2).ifPresent(var4x -> var4x.method2(this, var1, var2, var3));
   }

   public Optional<Bridge2_35> method4(String var1, Config var2) {
      return Optional.ofNullable(this.field2.get(var1)).or(() -> this.field2.values().stream().filter(var2xx -> var1.equals(var2xx.method1(var2))).findFirst());
   }

   public void method5(com.moonsworth.lunar.ichor.mixin.MixinHelper3 var1) {
      if (var1.getParams() != null) {
         for (com.moonsworth.lunar.ichor.mixin.MixinHelper3 var3 : var1.getParams()) {
            this.method5(var3);
         }
      }

      if (var1.method2() instanceof com.moonsworth.lunar.ichor.mixin.MixinHelper7 var4) {
         String var6 = var4.name();
         if (this.field4 != null) {
            var6 = this.field4.getClassMapping(MixinMisc4.method2(this.field4, var6)).<String>map(Mapping::getDeobfuscatedName).orElse(var6);
         }

         var1.method3(new com.moonsworth.lunar.ichor.mixin.MixinHelper7(var6));
      }
   }

   @Nullable
   @Generated
   public MappingSet getMappings() {
      return this.field4;
   }
}
