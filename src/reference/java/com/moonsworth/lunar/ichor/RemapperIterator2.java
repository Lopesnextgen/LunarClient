package com.moonsworth.lunar.ichor;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import lombok.Generated;
import org.cadixdev.bombe.analysis.InheritanceType;
import org.cadixdev.bombe.analysis.InheritanceProvider.ClassInfo;
import org.cadixdev.bombe.type.MethodDescriptor;
import org.cadixdev.bombe.type.signature.MethodSignature;
import org.cadixdev.lorenz.MappingSet;
import org.cadixdev.lorenz.model.ClassMapping;
import org.cadixdev.lorenz.model.FieldMapping;
import org.cadixdev.lorenz.model.MethodMapping;

public class RemapperIterator2 extends RemapperIterator {
   public static boolean DEBUG = System.getProperty("ichor.debug.remapper", null) != null;
   protected final AutoCloseableIterator field3;
   protected boolean field4;
   protected boolean field5;
   protected Set<String> field6 = Collections.newSetFromMap(new ConcurrentHashMap<>());

   public RemapperIterator2(AutoCloseableIterator var1, MappingSet var2, org.cadixdev.bombe.analysis.InheritanceProvider var3, boolean var4, boolean var5) {
      super(var2, var3);
      this.field3 = var1;
      this.field4 = var4;
      this.field5 = var5;
   }

   @Override
   public ClassMapping<?, ?> getCompletedClassMapping(String var1) {
      ClassMapping var2 = this.field1.getOrCreateClassMapping(var1);
      if (this.field4) {
         this.method1(var2);
      }

      var2.complete(this.field2);
      return var2;
   }

   public void method1(ClassMapping<?, ?> var1) {
      if (this.field6.add(var1.getFullObfuscatedName())) {
         ClassInfo var2 = (ClassInfo)this.field2.provide(var1.getFullDeobfuscatedName()).orElse(null);
         if (var2 != null) {
            Set var3 = var2.provideParents(this.field2);
            if (!var3.isEmpty()) {
               HashMap var4 = new HashMap();

               for (Entry var6 : var2.getMethods().entrySet()) {
                  Set var7 = var4.computeIfAbsent(((MethodSignature)var6.getKey()).getName(), var0 -> new HashSet());
                  var7.add((MethodSignature)var6.getKey());
               }

               for (ClassInfo var18 : var3) {
                  String var19 = var18.getName();
                  if (!var19.equals("java/lang/Object")) {
                     String var8 = this.field4 ? MixinMisc4.method2(this.field1, var19) : null;
                     ClassMapping var9 = var8 == null ? null : (ClassMapping)this.field1.getClassMapping(var8).orElse(null);
                     if (var9 != null) {
                        var9 = this.getCompletedClassMapping(var9.getFullObfuscatedName());

                        for (FieldMapping var11 : var9.getFieldMappings()) {
                           if (!var1.computeFieldMapping(var11.getSignature()).isPresent()
                              && var18.canInherit(var2, var11.getDeobfuscatedSignature())
                              && var1.getFieldMapping(var11.getSignature()).isEmpty()) {
                              var1.createFieldMapping(var11.getSignature(), var11.getDeobfuscatedName());
                           }
                        }

                        for (MethodMapping var22 : var9.getMethodMappings()) {
                           if (!this.field5
                              || var18.getMethod(var22.getSignature()) != InheritanceType.NONE
                              || var18.getMethod(var22.getDeobfuscatedSignature()) != InheritanceType.NONE) {
                              if (var18.canInherit(var2, var22.getDeobfuscatedSignature())) {
                                 Optional var12 = var1.getMethodMapping(var22.getSignature());
                                 if (var12.isEmpty()) {
                                    var1.createMethodMapping(var22.getSignature(), var22.getDeobfuscatedName());
                                 }
                              }

                              if (var4.containsKey(var22.getObfuscatedName())) {
                                 for (MethodSignature var13 : (Set)var4.get(var22.getObfuscatedName())) {
                                    MethodDescriptor var14 = var13.getDescriptor();
                                    MethodSignature var15 = var22.getSignature();
                                    MethodDescriptor var16 = var15.getDescriptor();
                                    if (Objects.equals(var14.getParamTypes(), var16.getParamTypes())
                                       && var16.getReturnType().isAssignableFrom(var14.getReturnType(), this.field2)
                                       && var1.getMethodMapping(var13).isEmpty()) {
                                       var1.createMethodMapping(var13, var22.getDeobfuscatedName());
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   @Generated
   public boolean method2() {
      return this.field4;
   }
}
