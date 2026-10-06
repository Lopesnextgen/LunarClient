package com.moonsworth.lunar.client.replay.network;

import com.moonsworth.lunar.client.config.override.SettingIntercept;
import com.moonsworth.lunar.client.config.override.OverrideSource;
import com.moonsworth.lunar.client.config.ModsSettings;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.replay.network.ByteBufLoader;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;

public class ServerSettingOverridePacket extends ReplayPacket {
   private String field1;
   private ServerSettingOverridePacket.OverrideMode field2;

   @Override
   public void method1(ByteBufLoader bytebufloader1) {
      this.field1 = bytebufloader1.readString();
      this.field2 = bytebufloader1.method9(ServerSettingOverridePacket.OverrideMode.class);
   }

   @Override
   public void method2(ByteBufLoader bytebufloader1) {
      bytebufloader1.method1(this.field1);
      bytebufloader1.method10(this.field2);
   }

   @Override
   public void method3(ReplayContext nameplate41) {
      ModsSettings fogloader32 = Ref.method4().method40();
      Framework7Extension framework7extension3 = fogloader32.method11(this.field1);
      if (framework7extension3 != null) {
         SettingIntercept alert24 = (SettingIntercept)framework7extension3.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field4);
         if (alert24 != null) {
            alert24.method1(framework7extension3, OverrideSource.SERVER, this.field2.toBoolean());
         }
      }
   }

   @Generated
   public ServerSettingOverridePacket(String text1, ServerSettingOverridePacket.OverrideMode type2) {
      this.field1 = text1;
      this.field2 = type2;
   }

   @Generated
   public ServerSettingOverridePacket() {
   }

   @Generated
   public String method4() {
      return this.field1;
   }

   @Generated
   public ServerSettingOverridePacket.OverrideMode method5() {
      return this.field2;
   }

   public enum OverrideMode {
      FORCE_ENABLED,
      FORCE_DISABLED,
      NONE;

      OverrideMode() {
      }

      public Boolean toBoolean() {
         return this == FORCE_ENABLED ? Boolean.TRUE : (this == FORCE_DISABLED ? Boolean.FALSE : null);
      }

      public static ServerSettingOverridePacket.OverrideMode fromBoolean(Boolean flag0) {
         return flag0 == null ? NONE : (flag0 ? FORCE_ENABLED : FORCE_DISABLED);
      }
   }
}
