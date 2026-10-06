package com.moonsworth.lunar.client.replay.network;

import com.google.gson.JsonObject;
import com.moonsworth.lunar.client.config.ModsSettings;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.OptionContainer;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.replay.network.ByteBufLoader;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.framework.LunarConstants;
import com.moonsworth.lunar.client.framework.Ref;
import lombok.Generated;

public class ModuleConfigPacket extends ReplayPacket {
   private String field1;
   private JsonObject field2;

   @Override
   public void method1(ByteBufLoader bytebufloader1) {
      this.field1 = bytebufloader1.readString();
      this.field2 = (JsonObject)LunarConstants.field22.fromJson(bytebufloader1.readString(), JsonObject.class);
   }

   @Override
   public void method2(ByteBufLoader bytebufloader1) {
      bytebufloader1.method1(this.field1);
      bytebufloader1.method1(LunarConstants.field22.toJson(this.field2));
   }

   @Override
   public void method3(ReplayContext nameplate41) {
      ModsSettings fogloader32 = Ref.method4().method40();
      Framework7Extension framework7extension3 = fogloader32.method11(this.field1);
      if (framework7extension3 != null) {
         OptionContainer framework54 = (OptionContainer)framework7extension3.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field14);
         if (framework54 != null) {
            for (ClientOption lightingextension6 : framework54.method2()) {
               lightingextension6.reset();
            }
         }

         framework7extension3.load(this.field2);
      }
   }

   @Generated
   public ModuleConfigPacket(String text1, JsonObject json2) {
      this.field1 = text1;
      this.field2 = json2;
   }

   @Generated
   public ModuleConfigPacket() {
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
