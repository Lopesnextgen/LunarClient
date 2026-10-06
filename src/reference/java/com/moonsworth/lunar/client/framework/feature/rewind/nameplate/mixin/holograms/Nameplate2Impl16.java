package com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.holograms;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.ByteBufLoader;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui6;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Nameplate2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate3;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import lombok.Generated;

public class Nameplate2Impl16 extends Nameplate2 implements Gui6 {
   public static final int field1 = 60;
   public static final double field2 = 4096.0;
   private short field3;
   private short field4;
   private short field5;
   private boolean field6;

   @Override
   public void method1(ByteBufLoader var1) {
      this.field3 = var1.readShort();
      this.field4 = var1.readShort();
      this.field5 = var1.readShort();
      this.field6 = var1.readBoolean();
   }

   @Override
   public void method2(ByteBufLoader var1) {
      var1.writeShort(this.field3);
      var1.writeShort(this.field4);
      var1.writeShort(this.field5);
      var1.writeBoolean(this.field6);
   }

   @Override
   public void method3(Nameplate4 var1) {
      Bridge5Extension_5 var2 = ThreadModuleDump63.method7();
      if (var2 != null && !var2.bridge$isRiding()) {
         Nameplate3 var3 = var1.method7();
         var1.method3(() -> {
            double var2x = var3.getX() + this.field3 / 4096.0;
            double var4 = var3.getY() + this.field4 / 4096.0;
            double var6 = var3.getZ() + this.field5 / 4096.0;
            this.method5(var3, var2x, var4, var6, this.field6);
         });
      }
   }

   @Override
   public Nameplate2 method4(Nameplate4 var1) {
      return new Nameplate2Impl16((short)(-this.field3), (short)(-this.field4), (short)(-this.field5), this.field6);
   }

   @Generated
   public Nameplate2Impl16(short var1, short var2, short var3, boolean var4) {
      this.field3 = var1;
      this.field4 = var2;
      this.field5 = var3;
      this.field6 = var4;
   }

   @Generated
   public Nameplate2Impl16() {
   }
}
