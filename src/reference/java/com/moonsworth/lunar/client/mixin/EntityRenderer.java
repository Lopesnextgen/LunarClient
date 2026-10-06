package com.moonsworth.lunar.client.mixin;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.account.AuthUtil;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.function.Consumer;
import lombok.Generated;

public class EntityRenderer {
   private String field1;

   public EntityRenderer() {
   }

   public void method1(String text1, Consumer<String> consumer2) {
      JsonObject json3 = AuthUtil.method3(this.field1);
      if (json3.has("exp")) {
         if (System.currentTimeMillis() < json3.get("exp").getAsInt() * 1000L) {
            consumer2.accept(this.field1);
         } else {
            Ref.method4().method6(text1, arg2x -> {
               this.field1 = arg2x;
               consumer2.accept(arg2x);
            });
         }
      }
   }

   @Generated
   public void method2(String text1) {
      this.field1 = text1;
   }
}
