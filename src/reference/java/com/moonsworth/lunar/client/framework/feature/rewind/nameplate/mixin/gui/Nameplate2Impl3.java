package com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.gui;

import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.ByteBufLoader;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Nameplate2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.event.input.MouseInputTypeLegacy;
import com.moonsworth.lunar.client.util.ThreadModuleDump71;
import lombok.Generated;

public class Nameplate2Impl3 extends Nameplate2 {
   private int x;
   private int y;
   private int field1;
   private boolean field2;
   private boolean field3;
   private MouseInputTypeLegacy field4;
   private long field5;
   private double field6;
   private double field7;
   private double field8;
   private double field9;

   @Override
   public void method1(ByteBufLoader var1) {
      this.x = var1.readVarInt();
      this.y = var1.readVarInt();
      this.field1 = var1.readVarInt();
      this.field2 = var1.readBoolean();
      this.field3 = var1.readBoolean();
      this.field4 = var1.method9(MouseInputTypeLegacy.class);
      if (this.field4 == MouseInputTypeLegacy.DRAG) {
         this.field5 = var1.readLong();
         this.field6 = var1.readDouble();
         this.field7 = var1.readDouble();
      }

      if (this.field4 == MouseInputTypeLegacy.SCROLL) {
         this.field8 = var1.readDouble();
         this.field9 = var1.readDouble();
      }
   }

   @Override
   public void method2(ByteBufLoader var1) {
      var1.method11(this.x);
      var1.method11(this.y);
      var1.method11(this.field1);
      var1.writeBoolean(this.field2);
      var1.writeBoolean(this.field3);
      var1.method10(this.field4);
      if (this.field4 == MouseInputTypeLegacy.DRAG) {
         var1.writeLong(this.field5);
         var1.writeDouble(this.field6);
         var1.writeDouble(this.field7);
      }

      if (this.field4 == MouseInputTypeLegacy.SCROLL) {
         var1.writeDouble(this.field8);
         var1.writeDouble(this.field9);
      }
   }

   @Override
   public void method3(Nameplate4 var1) {
      com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate2 var2 = var1.method8();
      Bridge5Extension6 var3 = var2.method5();
      if (var3 != null) {
         var2.method19(this.field2);
         var2.method20(this.field3);
         var2.method12().put(this.field1, this.field4 == MouseInputTypeLegacy.CLICK || this.field4 == MouseInputTypeLegacy.DRAG);
         int var4;
         int var5;
         if (var1.method20() >= 3) {
            var4 = var3.bridge$getWidth();
            var5 = var3.bridge$getHeight();
         } else {
            ThreadModuleDump71 var6 = var1.method6().method46().method17();
            var4 = var6.getScaledWidth();
            var5 = var6.getScaledHeight();
         }

         int var8 = var2.method8().apply(this.x, var4);
         int var7 = var2.method9().apply(this.y, var5);
         switch (this.field4) {
            case CLICK:
               var3.bridge$mouseClicked(var8, var7, this.field1);
               break;
            case RELEASE:
               var3.bridge$mouseReleased(var8, var7, this.field1);
               break;
            case DRAG:
               var3.bridge$mouseClickMove(var8, var7, this.field1, this.field5, this.field6, this.field7);
               break;
            case SCROLL:
               var3.bridge$mouseScrolled(var8, var7, this.field8, this.field9);
         }
      }
   }

   @Generated
   public Nameplate2Impl3() {
   }

   @Generated
   public Nameplate2Impl3(
      int var1, int var2, int var3, boolean var4, boolean var5, MouseInputTypeLegacy var6, long var7, double var9, double var11, double var13, double var15
   ) {
      this.x = var1;
      this.y = var2;
      this.field1 = var3;
      this.field2 = var4;
      this.field3 = var5;
      this.field4 = var6;
      this.field5 = var7;
      this.field6 = var9;
      this.field7 = var11;
      this.field8 = var13;
      this.field9 = var15;
   }
}
