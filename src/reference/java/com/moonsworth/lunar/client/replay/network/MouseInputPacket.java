package com.moonsworth.lunar.client.replay.network;

import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.client.replay.network.ByteBufLoader;
import com.moonsworth.lunar.client.replay.network.ReplayPacket;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.event.input.MouseInputType;
import com.moonsworth.lunar.client.ui.GuiResolution;
import lombok.Generated;

public class MouseInputPacket extends ReplayPacket {
   private int x;
   private int y;
   private int field1;
   private boolean field2;
   private boolean field3;
   private MouseInputType field4;
   private long field5;
   private double field6;
   private double field7;
   private double field8;
   private double field9;

   @Override
   public void method1(ByteBufLoader bytebufloader1) {
      this.x = bytebufloader1.readVarInt();
      this.y = bytebufloader1.readVarInt();
      this.field1 = bytebufloader1.readVarInt();
      this.field2 = bytebufloader1.readBoolean();
      this.field3 = bytebufloader1.readBoolean();
      this.field4 = bytebufloader1.method9(MouseInputType.class);
      if (this.field4 == MouseInputType.DRAG) {
         this.field5 = bytebufloader1.readLong();
         this.field6 = bytebufloader1.readDouble();
         this.field7 = bytebufloader1.readDouble();
      }

      if (this.field4 == MouseInputType.SCROLL) {
         this.field8 = bytebufloader1.readDouble();
         this.field9 = bytebufloader1.readDouble();
      }
   }

   @Override
   public void method2(ByteBufLoader bytebufloader1) {
      bytebufloader1.method11(this.x);
      bytebufloader1.method11(this.y);
      bytebufloader1.method11(this.field1);
      bytebufloader1.writeBoolean(this.field2);
      bytebufloader1.writeBoolean(this.field3);
      bytebufloader1.method10(this.field4);
      if (this.field4 == MouseInputType.DRAG) {
         bytebufloader1.writeLong(this.field5);
         bytebufloader1.writeDouble(this.field6);
         bytebufloader1.writeDouble(this.field7);
      }

      if (this.field4 == MouseInputType.SCROLL) {
         bytebufloader1.writeDouble(this.field8);
         bytebufloader1.writeDouble(this.field9);
      }
   }

   @Override
   public void method3(ReplayContext nameplate41) {
      com.moonsworth.lunar.client.replay.gui.GuiScreenContext nameplate22 = nameplate41.method8();
      GuiScreenBridge bridge5extension63 = nameplate22.method5();
      if (bridge5extension63 != null) {
         nameplate22.method19(this.field2);
         nameplate22.method20(this.field3);
         nameplate22.method12().put(this.field1, this.field4 == MouseInputType.CLICK || this.field4 == MouseInputType.DRAG);
         int number4;
         int number5;
         if (nameplate41.method20() >= 3) {
            number4 = bridge5extension63.bridge$getWidth();
            number5 = bridge5extension63.bridge$getHeight();
         } else {
            GuiResolution threadmoduledump716 = nameplate41.method6().method46().method17();
            number4 = threadmoduledump716.getScaledWidth();
            number5 = threadmoduledump716.getScaledHeight();
         }

         int number8 = nameplate22.method8().apply(this.x, number4);
         int number7 = nameplate22.method9().apply(this.y, number5);
         switch (this.field4) {
            case CLICK:
               bridge5extension63.bridge$mouseClicked(number8, number7, this.field1);
               break;
            case RELEASE:
               bridge5extension63.bridge$mouseReleased(number8, number7, this.field1);
               break;
            case DRAG:
               bridge5extension63.bridge$mouseClickMove(number8, number7, this.field1, this.field5, this.field6, this.field7);
               break;
            case SCROLL:
               bridge5extension63.bridge$mouseScrolled(number8, number7, this.field8, this.field9);
         }
      }
   }

   @Generated
   public MouseInputPacket() {
   }

   @Generated
   public MouseInputPacket(
      int value, int value2, int value3, boolean flag, boolean flag2, MouseInputType mouseInputType, long number7, double value4, double value5, double value6, double value7
   ) {
      this.x = value;
      this.y = value2;
      this.field1 = value3;
      this.field2 = flag;
      this.field3 = flag2;
      this.field4 = mouseInputType;
      this.field5 = number7;
      this.field6 = value4;
      this.field7 = value5;
      this.field8 = value6;
      this.field9 = value7;
   }
}
