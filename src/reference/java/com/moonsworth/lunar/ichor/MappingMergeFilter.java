package com.moonsworth.lunar.ichor;

import org.cadixdev.lorenz.merge.MappingSetMergerHandler;
import org.cadixdev.lorenz.merge.MergeContext;
import org.cadixdev.lorenz.merge.MergeResult;
import org.cadixdev.lorenz.model.ClassMapping;
import org.cadixdev.lorenz.model.FieldMapping;
import org.cadixdev.lorenz.model.MethodMapping;

class MappingMergeFilter implements MappingSetMergerHandler {
   MappingMergeFilter(MixinInternalTask mixininternaltask1) {
      this.field1 = mixininternaltask1;
   }

   private boolean method1(String text1) {
      return text1.startsWith("com/mojang/blaze3d/")
         || text1.equals("net/minecraft/client/gui/LayeredDraw$Layer")
         || text1.equals("net/minecraft/client/gui/screens/packs/PackSelectionModel$Entry");
   }

   public MergeResult<MethodMapping> addLeftMethodMapping(MethodMapping methodmapping1, ClassMapping<?, ?> classmapping2, MergeContext mergecontext3) {
      return !this.method1(classmapping2.getFullObfuscatedName())
            && !methodmapping1.getObfuscatedName().startsWith("newInstance")
            && !methodmapping1.getObfuscatedName().startsWith("close")
            && !methodmapping1.getObfuscatedName().startsWith("open")
            && !methodmapping1.getObfuscatedName().startsWith("mode")
            && (this.field1.field5 > 32 || !methodmapping1.getObfuscatedName().equals("getBuffer$v1_16_1"))
         ? new MergeResult(null)
         : super.addLeftMethodMapping(methodmapping1, classmapping2, mergecontext3);
   }

   public FieldMapping addLeftFieldMapping(FieldMapping fieldmapping1, ClassMapping<?, ?> classmapping2, MergeContext mergecontext3) {
      return this.method1(classmapping2.getFullObfuscatedName()) ? super.addLeftFieldMapping(fieldmapping1, classmapping2, mergecontext3) : null;
   }
}
