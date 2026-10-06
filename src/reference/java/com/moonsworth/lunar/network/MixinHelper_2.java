package com.moonsworth.lunar.network;

import com.moonsworth.lunar.network.mixin.MixinHelperException;
import java.util.ArrayList;
import java.util.HashMap;
import okhttp3.Call;

public class MixinHelper_2 {
   private com.moonsworth.lunar.network.mixin.MixinHelper field1;
   private int field2;
   private String field3;

   public MixinHelper_2() {
      this(com.moonsworth.lunar.network.mixin.MixinHelper9.method1());
   }

   public MixinHelper_2(com.moonsworth.lunar.network.mixin.MixinHelper var1) {
      this.field1 = var1;
   }

   public com.moonsworth.lunar.network.mixin.MixinHelper method1() {
      return this.field1;
   }

   public void method2(com.moonsworth.lunar.network.mixin.MixinHelper var1) {
      this.field1 = var1;
   }

   public int method3() {
      return this.field2;
   }

   public void method4(int var1) {
      this.field2 = var1;
   }

   public String method5() {
      return this.field3;
   }

   public void method6(String var1) {
      this.field3 = var1;
   }

   public Call method7(MixinHelper11 var1, com.moonsworth.lunar.network.mixin.MixinHelper3 var2) {
      String var3 = null;
      String[] var4 = new String[0];
      if (this.field3 != null) {
         var3 = this.field3;
      } else if (var4.length > 0) {
         var3 = var4[this.field2];
      } else {
         var3 = null;
      }

      MixinHelper11 var5 = var1;
      String var6 = "/game/event/batch";
      ArrayList var7 = new ArrayList();
      ArrayList var8 = new ArrayList();
      HashMap var9 = new HashMap();
      HashMap var10 = new HashMap();
      HashMap var11 = new HashMap();
      String[] var12 = new String[0];
      String var13 = this.field1.method52(var12);
      if (var13 != null) {
         var9.put("Accept", var13);
      }

      String[] var14 = new String[]{"application/json"};
      String var15 = this.field1.method53(var14);
      if (var15 != null) {
         var9.put("Content-Type", var15);
      }

      String[] var16 = new String[0];
      return this.field1.method63(var3, var6, "POST", var7, var8, var5, var9, var10, var11, var16, var2);
   }

   private Call method8(MixinHelper11 var1, com.moonsworth.lunar.network.mixin.MixinHelper3 var2) {
      if (var1 == null) {
         throw new MixinHelperException("Missing the required parameter 'gameEventBatchPostRequest' when calling gameEventBatchPost(Async)");
      } else {
         return this.method7(var1, var2);
      }
   }

   public void method9(MixinHelper11 var1) {
      this.method10(var1);
   }

   public com.moonsworth.lunar.network.mixin.MixinHelper4<Void> method10(MixinHelper11 var1) {
      Call var2 = this.method8(var1, null);
      return this.field1.method58(var2);
   }

   public Call method11(MixinHelper11 var1, com.moonsworth.lunar.network.mixin.MixinHelper3<Void> var2) {
      Call var3 = this.method8(var1, var2);
      this.field1.method60(var3, var2);
      return var3;
   }
}
