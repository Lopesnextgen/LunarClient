package com.moonsworth.lunar.client.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Generated;

public abstract class MixinHelper<T extends MixinHelper<T>> {
   private final Set<MixinHelper25> field1 = new HashSet<>();
   private final Set<MixinHelper23> field2 = new HashSet<>();

   public T method1(MixinHelper25 var1) {
      this.field1.add(var1);
      return (T)this;
   }

   public T method2(MixinHelper25... var1) {
      this.field1.addAll(List.of(var1));
      return (T)this;
   }

   public T method3(MixinHelper25.Data2 var1) {
      return this.method1(var1.method4());
   }

   public T method4(MixinHelper25.Data2... var1) {
      for (MixinHelper25.Data2 var5 : var1) {
         this.method3(var5);
      }

      return (T)this;
   }

   public T method5(MixinHelper23 var1) {
      this.field2.add(var1);
      return (T)this;
   }

   public T method6(MixinHelper23... var1) {
      this.field2.addAll(List.of(var1));
      return (T)this;
   }

   public T method7(MixinHelper23.Data3 var1) {
      return this.method5(var1.method9());
   }

   public T method8(MixinHelper23.Data3... var1) {
      for (MixinHelper23.Data3 var5 : var1) {
         this.method7(var5);
      }

      return (T)this;
   }

   @Generated
   public Set<MixinHelper25> method9() {
      return this.field1;
   }

   @Generated
   public Set<MixinHelper23> method10() {
      return this.field2;
   }
}
