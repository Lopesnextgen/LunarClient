package com.moonsworth.lunar.client.framework.feature.rewind.nameplate;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.config.ModsSettings;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework5;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.ByteBufLoader;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import lombok.Generated;

public class Nameplate2Iterator5 extends Nameplate2 {
   private String field1;
   private JsonObject field2;

   @Override
   public void method1(ByteBufLoader var1) {
      this.field1 = var1.readString();
      this.field2 = (JsonObject)ThreadModuleDump48.field22.fromJson(var1.readString(), JsonObject.class);
   }

   @Override
   public void method2(ByteBufLoader var1) {
      var1.method1(this.field1);
      var1.method1(ThreadModuleDump48.field22.toJson(this.field2));
   }

   @Override
   public void method3(Nameplate4 var1) {
      ModsSettings var2 = ThreadModuleDump63.method4().method40();
      Framework7Extension var3 = var2.method11(this.field1);
      if (var3 != null) {
         Framework5 var4 = (Framework5)var3.method1(Framework.field14);
         if (var4 != null) {
            for (ClientOption var6 : var4.method2()) {
               var6.reset();
            }
         }

         var3.load(this.field2);
      }
   }

   @Generated
   public Nameplate2Iterator5(String var1, JsonObject var2) {
      this.field1 = var1;
      this.field2 = var2;
   }

   @Generated
   public Nameplate2Iterator5() {
   }

   @Generated
   public String method4() {
      return this.field1;
   }

   @Generated
   public JsonObject method5() {
      return this.field2;
   }
}
