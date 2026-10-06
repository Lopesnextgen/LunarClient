package com.moonsworth.lunar.ichor;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class MixinLegacy {
   private final String field1;
   private final MixinLegacy field2;
   private final List<MixinLegacy> interfaces;
   public static final MixinLegacy field3 = new MixinLegacy("java/lang/Object", null, List.of());

   public MixinLegacy(String var1, MixinLegacy var2, List<MixinLegacy> var3) {
      this.field1 = var1;
      this.field2 = var2;
      this.interfaces = var3;
   }

   public String name() {
      return this.field1.replace('.', '/');
   }

   public boolean method1(MixinLegacy var1) {
      if (this.field1.equals(var1.field1)) {
         return true;
      }

      LinkedHashSet var2 = new LinkedHashSet();

      for (MixinLegacy var3 = var1; var3 != null; var3 = var3.field2) {
         var2.add(var3.field1);
      }

      if (var2.contains(this.field1)) {
         return true;
      }

      Set var4 = method2(var1);
      return var4.contains(this.field1);
   }

   private static Set<String> method2(MixinLegacy mixinLegacy) {
      LinkedHashSet var1 = new LinkedHashSet();

      for (MixinLegacy var3 : mixinLegacy.interfaces) {
         var1.add(var3.field1);
         var1.addAll(method2(var3));
      }

      return var1;
   }

   public MixinLegacy method3() {
      return this.field2;
   }

   public List<MixinLegacy> getInterfaces() {
      return this.interfaces;
   }
}
