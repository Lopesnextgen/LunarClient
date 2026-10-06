package com.moonsworth.lunar.client.framework.feature.debug.fpsdebugmod.profilerdebugmod;

import java.io.DataOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Profilerdebugmod4 {
   private long time;
   private final String className;
   private final String methodName;
   private final int field1;
   private final Map<String, Profilerdebugmod4> field2 = new HashMap<>();

   public Profilerdebugmod4() {
      this.className = "";
      this.methodName = "root";
      this.field1 = 0;
   }

   public Profilerdebugmod4(String var1) {
      this.className = "";
      this.methodName = var1;
      this.field1 = 0;
   }

   public Profilerdebugmod4(StackTraceElement var1) {
      this.className = var1.getClassName();
      this.methodName = var1.getMethodName();
      this.field1 = var1.getLineNumber();
   }

   public void method1(StackTraceElement[] var1, long var2) {
      if (var1.length != 0) {
         this.method3(var1, var1.length - 1, var2);
      }
   }

   public void method2(long var1) {
      this.time += var1;
   }

   private void method3(StackTraceElement[] var1, int var2, long var3) {
      this.time += var3;
      if (var2 != 0) {
         StackTraceElement var5 = var1[var2];
         Profilerdebugmod4 var6 = this.method4(var5);
         var6.method3(var1, --var2, var3);
      }
   }

   private Profilerdebugmod4 method4(StackTraceElement var1) {
      String var2 = method6(var1);
      return this.field2.computeIfAbsent(var2, var1x -> new Profilerdebugmod4(var1));
   }

   public Profilerdebugmod4 method5(String var1) {
      return this.field2.computeIfAbsent(var1, Profilerdebugmod4::new);
   }

   private static String method6(StackTraceElement var0) {
      return var0.getClassName() + "#" + var0.getMethodName() + ":" + var0.getLineNumber();
   }

   public void method7(Profilerdebugmod_3 var1) {
      var1.method1(this.className);
      var1.method1(this.methodName);

      for (Entry var3 : this.field2.entrySet()) {
         ((Profilerdebugmod4)var3.getValue()).method7(var1);
      }
   }

   public void method8(DataOutputStream var1, Profilerdebugmod_3 var2) {
      var1.writeLong(this.time);
      var2.writeString(this.className);
      var2.writeString(this.methodName);
      var1.writeInt(this.field1);
      var1.writeInt(this.field2.size());

      for (Entry var4 : this.field2.entrySet()) {
         ((Profilerdebugmod4)var4.getValue()).method8(var1, var2);
      }
   }
}
