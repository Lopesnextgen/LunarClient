package com.moonsworth.lunar.client.replay.network;

import com.moonsworth.lunar.client.replay.network.ByteBufLoader;
import com.moonsworth.lunar.client.replay.gui.GuiType;
import com.moonsworth.lunar.client.replay.network.ReplayPacket;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import lombok.Generated;

public class ScreenAlignmentPacket extends ReplayPacket {
   private GuiType field1;
   private GuiType field2;

   @Override
   public void method1(ByteBufLoader bytebufloader1) {
      this.field1 = bytebufloader1.method9(GuiType.class);
      this.field2 = bytebufloader1.method9(GuiType.class);
   }

   @Override
   public void method2(ByteBufLoader bytebufloader1) {
      bytebufloader1.method10(this.field1);
      bytebufloader1.method10(this.field2);
   }

   @Override
   public void method3(ReplayContext nameplate41) {
      com.moonsworth.lunar.client.replay.gui.GuiScreenContext nameplate22 = nameplate41.method8();
      nameplate22.method17(this.field1);
      nameplate22.method18(this.field2);
   }

   @Generated
   public ScreenAlignmentPacket() {
   }

   @Generated
   public ScreenAlignmentPacket(GuiType guiType, GuiType guiType2) {
      this.field1 = guiType;
      this.field2 = guiType2;
   }
}
