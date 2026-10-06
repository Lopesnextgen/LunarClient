package com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.holograms;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.MovementStateBridge;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.ByteBufLoader;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.Nameplate2;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import lombok.Generated;

public class Nameplate2Impl11 extends Nameplate2 {
   private float field1;
   private float field2;
   private boolean sneaking;
   private boolean jumping;

   @Override
   public void method1(ByteBufLoader var1) {
      this.field1 = var1.readFloat();
      this.field2 = var1.readFloat();
      this.sneaking = var1.readBoolean();
      this.jumping = var1.readBoolean();
   }

   @Override
   public void method2(ByteBufLoader var1) {
      var1.writeFloat(this.field1);
      var1.writeFloat(this.field2);
      var1.writeBoolean(this.sneaking);
      var1.writeBoolean(this.jumping);
   }

   @Override
   public void method3(Nameplate4 var1) {
      Bridge5Extension_5 var2 = ThreadModuleDump63.method7();
      if (var2 != null) {
         MovementStateBridge var3 = var2.bridge$getMovementInput();
         var3.bridge$setMoveForward(this.field1);
         var3.bridge$setMoveStrafe(this.field2);
         var3.bridge$setSneak(this.sneaking);
         var3.bridge$setJump(this.jumping);
      }
   }

   @Generated
   public Nameplate2Impl11(float var1, float var2, boolean var3, boolean flag) {
      this.field1 = var1;
      this.field2 = var2;
      this.sneaking = var3;
      this.jumping = flag;
   }

   @Generated
   public Nameplate2Impl11() {
   }
}
