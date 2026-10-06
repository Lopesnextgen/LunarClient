package com.moonsworth.lunar.client.framework.feature.rewind.nameplate;

import com.moonsworth.lunar.client.config.ModsSettings;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework5;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.ByteBufLoader;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.AbstractKeybindOption;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import it.unimi.dsi.fastutil.booleans.BooleanConsumer;
import lombok.Generated;

public class Nameplate2Task extends Nameplate2 {
   private String field1;
   private String field2;
   private boolean pressed;
   private boolean value;

   @Override
   public void method1(ByteBufLoader var1) {
      this.field1 = var1.readString();
      this.field2 = var1.readString();
      this.pressed = var1.readBoolean();
      this.value = var1.readBoolean();
   }

   @Override
   public void method2(ByteBufLoader var1) {
      var1.method1(this.field1);
      var1.method1(this.field2);
      var1.writeBoolean(this.pressed);
      var1.writeBoolean(this.value);
   }

   @Override
   public void method3(Nameplate4 var1) {
      ModsSettings var2 = ThreadModuleDump63.method4().method40();
      Framework7Extension var3 = var2.method11(this.field1);
      if (var3 != null) {
         Framework5 var4 = (Framework5)var3.method1(Framework.field14);
         if (var4 == null) {
            return;
         }

         for (ClientOption var6 : var4.method2()) {
            if (var6.getId().equals(this.field2) && var6 instanceof AbstractKeybindOption var7) {
               if (this.pressed) {
                  for (Runnable var9 : var7.method15()) {
                     var9.run();
                  }
               } else {
                  for (BooleanConsumer var11 : var7.method16()) {
                     var11.accept(this.value);
                  }
               }
               break;
            }
         }
      }
   }

   @Generated
   public Nameplate2Task(String var1, String var2, boolean var3, boolean var4) {
      this.field1 = var1;
      this.field2 = var2;
      this.pressed = var3;
      this.value = var4;
   }

   @Generated
   public Nameplate2Task() {
   }

   @Generated
   public String method4() {
      return this.field1;
   }

   @Generated
   public String method5() {
      return this.field2;
   }

   @Generated
   public boolean isPressed() {
      return this.pressed;
   }

   @Generated
   public boolean isValue() {
      return this.value;
   }
}
