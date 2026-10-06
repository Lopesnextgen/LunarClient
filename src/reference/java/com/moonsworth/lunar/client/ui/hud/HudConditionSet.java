package com.moonsworth.lunar.client.ui.hud;

import com.moonsworth.lunar.client.util.ThreadModuleDumpType5;
import com.moonsworth.lunar.client.util.ThreadModuleDumpType5.Data3;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class HudConditionSet {
   public static final HudConditionSet field1 = method5().method8();
   private final Data3 field2;
   private final Data3 field3;
   private final Data3 field4;
   private final Data3 field5;

   public ThreadModuleDumpType5 method1(@Nullable String var1) {
      return var1 != null && this.field2 instanceof com.moonsworth.lunar.client.util.ThreadModuleDumpType5.Data var2
         ? var2.method1(var1)
         : this.field2.method1();
   }

   public ThreadModuleDumpType5 method2() {
      return this.field3.method1();
   }

   public ThreadModuleDumpType5 method3() {
      return this.field4.method1();
   }

   public ThreadModuleDumpType5 method4() {
      return this.field5.method1();
   }

   public static MixinCore$Data method5() {
      return new MixinCore$Data();
   }

   @Generated
   public Data3 method6() {
      return this.field2;
   }

   @Generated
   public Data3 method7() {
      return this.field3;
   }

   @Generated
   public Data3 method8() {
      return this.field4;
   }

   @Generated
   public Data3 method9() {
      return this.field5;
   }

   @Generated
   public HudConditionSet(Data3 var1, Data3 var2, Data3 var3, Data3 var4) {
      this.field2 = var1;
      this.field3 = var2;
      this.field4 = var3;
      this.field5 = var4;
   }
}
