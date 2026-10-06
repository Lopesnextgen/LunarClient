package com.moonsworth.lunar.client.replay.network;

import com.moonsworth.lunar.client.config.ModsSettings;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.replay.network.ByteBufLoader;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;

public class HudPositionPacket extends ReplayPacket {
   private String field1;
   private HudAnchor field2;
   private float x;
   private float y;

   @Override
   public void method1(ByteBufLoader bytebufloader1) {
      this.field1 = bytebufloader1.readString();
      this.field2 = bytebufloader1.method9(HudAnchor.class);
      this.x = bytebufloader1.readFloat();
      this.y = bytebufloader1.readFloat();
   }

   @Override
   public void method2(ByteBufLoader bytebufloader1) {
      bytebufloader1.method1(this.field1);
      bytebufloader1.method10(this.field2);
      bytebufloader1.writeFloat(this.x);
      bytebufloader1.writeFloat(this.y);
   }

   @Override
   public void method3(ReplayContext nameplate41) {
      ModsSettings fogloader32 = Ref.method4().method40();
      Framework7Extension framework7extension3 = fogloader32.method11(this.field1);
      if (framework7extension3 != null) {
         MixinCore9Extension mixincore9extension4 = (MixinCore9Extension)framework7extension3.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field1);
         if (mixincore9extension4 != null) {
            mixincore9extension4.method27(this.field2);
            mixincore9extension4.method17(this.x, this.y);
         }
      }
   }

   @Generated
   public HudPositionPacket(String text1, HudAnchor gui2extension22, float value3, float value4) {
      this.field1 = text1;
      this.field2 = gui2extension22;
      this.x = value3;
      this.y = value4;
   }

   @Generated
   public HudPositionPacket() {
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
