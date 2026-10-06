package com.moonsworth.lunar.client.framework.feature.rewind.nameplate;

import com.moonsworth.lunar.client.config.ModsSettings;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.ByteBufLoader;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import lombok.Generated;

public class Nameplate2Impl3 extends Nameplate2 {
   private String field1;
   private HudAnchor field2;
   private float x;
   private float y;

   @Override
   public void method1(ByteBufLoader var1) {
      this.field1 = var1.readString();
      this.field2 = var1.method9(HudAnchor.class);
      this.x = var1.readFloat();
      this.y = var1.readFloat();
   }

   @Override
   public void method2(ByteBufLoader var1) {
      var1.method1(this.field1);
      var1.method10(this.field2);
      var1.writeFloat(this.x);
      var1.writeFloat(this.y);
   }

   @Override
   public void method3(Nameplate4 var1) {
      ModsSettings var2 = ThreadModuleDump63.method4().method40();
      Framework7Extension var3 = var2.method11(this.field1);
      if (var3 != null) {
         MixinCore9Extension var4 = (MixinCore9Extension)var3.method1(Framework.field1);
         if (var4 != null) {
            var4.method27(this.field2);
            var4.method17(this.x, this.y);
         }
      }
   }

   @Generated
   public Nameplate2Impl3(String var1, HudAnchor var2, float var3, float var4) {
      this.field1 = var1;
      this.field2 = var2;
      this.x = var3;
      this.y = var4;
   }

   @Generated
   public Nameplate2Impl3() {
   }

   @Generated
   public String method4() {
      return this.field1;
   }

   @Generated
   public HudAnchor method5() {
      return this.field2;
   }

   @Generated
   public float getX() {
      return this.x;
   }

   @Generated
   public float getY() {
      return this.y;
   }
}
