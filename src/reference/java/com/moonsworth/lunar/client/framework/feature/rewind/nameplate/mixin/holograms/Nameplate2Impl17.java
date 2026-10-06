package com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.holograms;

import com.moonsworth.lunar.bridge.Bridge2_19;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.ByteBufLoader;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.Gui6;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Nameplate2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate3;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.fishing.EventEverySecond;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import lombok.Generated;

public class Nameplate2Impl17 extends Nameplate2 implements Gui6 {
   private double x;
   private double y;
   private double z;
   private boolean field1;

   @Override
   public void method1(ByteBufLoader var1) {
      this.x = var1.readDouble();
      this.y = var1.readDouble();
      this.z = var1.readDouble();
      this.field1 = var1.readBoolean();
   }

   @Override
   public void method2(ByteBufLoader var1) {
      var1.writeDouble(this.x);
      var1.writeDouble(this.y);
      var1.writeDouble(this.z);
      var1.writeBoolean(this.field1);
   }

   @Override
   public void method3(Nameplate4 var1) {
      Bridge5Extension_5 var2 = ThreadModuleDump63.method7();
      if (var2 != null && (!var1.method18() || !var2.bridge$isRiding())) {
         Nameplate3 var3 = var1.method7();
         var1.method3(() -> this.method5(var3, this.x, this.y, this.z, this.field1));
         if (!var1.method18()) {
            this.method5(var3, this.x, this.y, this.z, this.field1);
            ThreadModuleDump63.method3().bridge$displayScreen(null);
            var1.method32(true);
            ThreadModuleDump63.method3().bridge$schedule(() -> ClientEventBus.method29().method12(EventEverySecond.class, EventEverySecond::new));
            if (ThreadModuleDump63.MC_VERSION >= 7) {
               Bridge2_19 var4 = ThreadModuleDump63.method3().bridge$getGameRenderer().bridge$getCamera();
               var4.bridge$setEyeHeight(var2.bridge$getEyeHeight());
            }
         }
      }
   }

   @Generated
   public Nameplate2Impl17(double var1, double var3, double var5, boolean var7) {
      this.x = var1;
      this.y = var3;
      this.z = var5;
      this.field1 = var7;
   }

   @Generated
   public Nameplate2Impl17() {
   }
}
