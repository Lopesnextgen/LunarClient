package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.mixin;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.client.framework.feature.rewind.Rewind_4;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Nameplate2;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.holograms.Nameplate2Impl11;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.holograms.Nameplate2Impl12;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.holograms.Nameplate2Impl16;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.holograms.Nameplate2Impl17;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventCursorPosition;
import com.moonsworth.lunar.client.event.mixin.gui.TeleportEvent.TeleportPostEvent;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers5;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;

public class RewindhandlersNameplateCoreImpl extends RewindhandlersNameplateCore {
   private double lastX;
   private double lastY;
   private double lastZ;
   private byte field1 = 0;
   private boolean field2;

   @Override
   public void method4(TeleportPostEvent var1, RewindHandlers5 var2, Rewind_4 var3) {
      Bridge5Extension_5 var4 = ThreadModuleDump63.method7();
      if (var4 != null) {
         this.lastX = var4.bridge$getPosX();
         this.lastY = var4.bridge$getPosY();
         this.lastZ = var4.bridge$getPosZ();
         var3.method9(new Nameplate2Impl17(var4.bridge$getPosX(), var4.bridge$getPosY(), var4.bridge$getPosZ(), var4.bridge$isOnGround()), var2.getTick());
      }
   }

   @Override
   public void method2(EventClientTick var1, RewindHandlers5 var2, Rewind_4 var3) {
      Bridge5Extension_5 var4 = ThreadModuleDump63.method7();
      if (var4 != null) {
         double var5 = var4.bridge$getPosX();
         double var7 = var4.bridge$getPosY();
         double var9 = var4.bridge$getPosZ();
         long var11 = Math.round((var5 - this.lastX) * 4096.0);
         long var13 = Math.round((var7 - this.lastY) * 4096.0);
         long var15 = Math.round((var9 - this.lastZ) * 4096.0);
         Nameplate2 var17;
         if (!var3.method5()
            && this.field1 < 60
            && var4.bridge$isRiding() == this.field2
            && var11 >= -32768L
            && var11 <= 32767L
            && var13 >= -32768L
            && var13 <= 32767L
            && var15 >= -32768L
            && var15 <= 32767L) {
            var17 = new Nameplate2Impl16((short)var11, (short)var13, (short)var15, var4.bridge$isOnGround());
         } else {
            var17 = new Nameplate2Impl17(var5, var7, var9, var4.bridge$isOnGround());
            this.field1 = 0;
            this.field2 = var4.bridge$isRiding();
         }

         var3.method9(var17, var2.getTick());
         var3.method9(
            new Nameplate2Impl11(
               var4.bridge$getMovementInput().bridge$getForwardSpeed(),
               var4.bridge$getMovementInput().bridge$getStrafeSpeed(),
               var4.bridge$getMovementInput().bridge$isSneaking(),
               var4.bridge$getMovementInput().bridge$isJumping()
            ),
            var2.getTick()
         );
         this.lastX = var5;
         this.lastY = var7;
         this.lastZ = var9;
         this.field1++;
      }
   }

   @Override
   public void method3(EventCursorPosition var1, RewindHandlers5 var2, Rewind_4 var3) {
      Bridge5Extension_5 var4 = ThreadModuleDump63.method7();
      if (var4 != null) {
         Object var5 = ThreadModuleDump63.method3().bridge$getRenderViewEntity();
         if (var5 == null) {
            var5 = var4;
         }

         var3.method9(new Nameplate2Impl12((float)var5.bridge$getRotationYaw(), (float)var5.bridge$getRotationPitch()), -1);
      }
   }
}
