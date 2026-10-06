package com.moonsworth.lunar.client.replay.network;

import com.moonsworth.lunar.client.config.ModsSettings;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.OptionContainer;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.replay.network.ByteBufLoader;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.AbstractKeybindOption;
import com.moonsworth.lunar.client.framework.Ref;
import it.unimi.dsi.fastutil.booleans.BooleanConsumer;
import lombok.Generated;

public class ButtonStatePacket extends ReplayPacket {
   private String field1;
   private String field2;
   private boolean pressed;
   private boolean value;

   @Override
   public void method1(ByteBufLoader bytebufloader1) {
      this.field1 = bytebufloader1.readString();
      this.field2 = bytebufloader1.readString();
      this.pressed = bytebufloader1.readBoolean();
      this.value = bytebufloader1.readBoolean();
   }

   @Override
   public void method2(ByteBufLoader bytebufloader1) {
      bytebufloader1.method1(this.field1);
      bytebufloader1.method1(this.field2);
      bytebufloader1.writeBoolean(this.pressed);
      bytebufloader1.writeBoolean(this.value);
   }

   @Override
   public void method3(ReplayContext nameplate41) {
      ModsSettings fogloader32 = Ref.method4().method40();
      Framework7Extension framework7extension3 = fogloader32.method11(this.field1);
      if (framework7extension3 != null) {
         OptionContainer framework54 = (OptionContainer)framework7extension3.HIRHCCHIRHRORIICOIHIHCICOIRHHC(ModTraits.field14);
         if (framework54 == null) {
            return;
         }

         for (ClientOption lightingextension6 : framework54.method2()) {
            if (lightingextension6.getId().equals(this.field2) && lightingextension6 instanceof AbstractKeybindOption lightingextension49137) {
               if (this.pressed) {
                  for (Runnable runnable9 : lightingextension49137.method15()) {
                     runnable9.run();
                  }
               } else {
                  for (BooleanConsumer booleanconsumer11 : lightingextension49137.method16()) {
                     booleanconsumer11.accept(this.value);
                  }
               }
               break;
            }
         }
      }
   }

   @Generated
   public ButtonStatePacket(String text1, String text2, boolean flag3, boolean flag4) {
      this.field1 = text1;
      this.field2 = text2;
      this.pressed = flag3;
      this.value = flag4;
   }

   @Generated
   public ButtonStatePacket() {
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
