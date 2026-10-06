package com.moonsworth.lunar.client.framework.feature.rewind.nameplate;

import com.moonsworth.lunar.client.alert.mixin.Alert2;
import com.moonsworth.lunar.client.alert.mixin.AlertType;
import com.moonsworth.lunar.client.config.ModsSettings;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.ByteBufLoader;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import lombok.Generated;

public class Nameplate2Impl6 extends Nameplate2 {
   private String field1;
   private Nameplate2Impl6.Type field2;

   @Override
   public void method1(ByteBufLoader var1) {
      this.field1 = var1.readString();
      this.field2 = var1.method9(Nameplate2Impl6.Type.class);
   }

   @Override
   public void method2(ByteBufLoader var1) {
      var1.method1(this.field1);
      var1.method10(this.field2);
   }

   @Override
   public void method3(Nameplate4 var1) {
      ModsSettings var2 = ThreadModuleDump63.method4().method40();
      Framework7Extension var3 = var2.method11(this.field1);
      if (var3 != null) {
         Alert2 var4 = (Alert2)var3.method1(Framework.field4);
         if (var4 != null) {
            var4.method1(var3, AlertType.SERVER, this.field2.toBoolean());
         }
      }
   }

   @Generated
   public Nameplate2Impl6(String var1, Nameplate2Impl6.Type var2) {
      this.field1 = var1;
      this.field2 = var2;
   }

   @Generated
   public Nameplate2Impl6() {
   }

   @Generated
   public String method4() {
      return this.field1;
   }

   @Generated
   public Nameplate2Impl6.Type method5() {
      return this.field2;
   }

   public enum Type {
      FORCE_ENABLED,
      FORCE_DISABLED,
      NONE;

      public Boolean toBoolean() {
         return this == FORCE_ENABLED ? Boolean.TRUE : (this == FORCE_DISABLED ? Boolean.FALSE : null);
      }

      public static Nameplate2Impl6.Type fromBoolean(Boolean booleanValue) {
         return booleanValue == null ? NONE : (booleanValue ? FORCE_ENABLED : FORCE_DISABLED);
      }
   }
}
