package com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.profilerdebugmod;

import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;

public class Profilerdebugmod2 {
   private final Profilerdebugmod4 field1 = new Profilerdebugmod4();
   private long field2;
   private boolean field3;
   private final List<Profilerdebugmod.Data> field4 = new ArrayList<>();

   public void method1(long var1) {
      this.field2 = var1;
   }

   public void method2(StackTraceElement[] var1) {
      this.field1.method1(var1, 1L);
   }

   public void method3(String var1, StackTraceElement[] var2) {
      this.field1.method2(1L);
      Profilerdebugmod4 var3 = this.field1.method5(var1);
      var3.method1(var2, 1L);
   }

   public void method4(Profilerdebugmod_3 var1) {
      this.field1.method7(var1);

      for (Profilerdebugmod.Data var3 : this.field4) {
         var3.method1(var1);
      }
   }

   public void method5(DataOutputStream var1, Profilerdebugmod_3 var2) {
      var1.writeLong(this.field2);
      this.field1.method8(var1, var2);
      var1.writeBoolean(this.field3);
      var1.writeInt(this.field4.size());

      for (Profilerdebugmod.Data var4 : this.field4) {
         var4.method2(var1, var2);
      }
   }

   public void method6(Profilerdebugmod.Data var1) {
      this.field4.add(var1);
   }

   public List<Profilerdebugmod.Data> method7() {
      return this.field4;
   }

   @Generated
   public void method8(boolean var1) {
      this.field3 = var1;
   }
}
