package com.moonsworth.lunar.client.replay.recording;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.client.replay.recording.ReplayHandler;
import com.moonsworth.lunar.client.replay.network.ReplayPacket;
import com.moonsworth.lunar.client.replay.network.MovementInputPacket;
import com.moonsworth.lunar.client.replay.network.LookPacket;
import com.moonsworth.lunar.client.replay.network.RelativePositionPacket;
import com.moonsworth.lunar.client.replay.network.PositionPacket;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventMousePosition;
import com.moonsworth.lunar.client.event.mixin.gui.EventTeleportBase.EventTeleportPost;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindRecorder;
import com.moonsworth.lunar.client.framework.Ref;

public class RecorderEventAdapter extends RecorderEventListener {
   private double lastX;
   private double lastY;
   private double lastZ;
   private byte field1 = 0;
   private boolean field2;

   public RecorderEventAdapter() {
   }

   @Override
   public void method4(EventTeleportPost data161, RewindRecorder rewindhandlers52, ReplayHandler rewind_43) {
      Bridge5Extension_5 bridge5extension_54 = Ref.method7();
      if (bridge5extension_54 != null) {
         this.lastX = bridge5extension_54.bridge$getPosX();
         this.lastY = bridge5extension_54.bridge$getPosY();
         this.lastZ = bridge5extension_54.bridge$getPosZ();
         rewind_43.method9(new PositionPacket(bridge5extension_54.bridge$getPosX(), bridge5extension_54.bridge$getPosY(), bridge5extension_54.bridge$getPosZ(), bridge5extension_54.bridge$isOnGround()), rewindhandlers52.getTick());
      }
   }

   @Override
   public void method2(EventTick highlightimpl21, RewindRecorder rewindhandlers52, ReplayHandler rewind_43) {
      Bridge5Extension_5 bridge5extension_54 = Ref.method7();
      if (bridge5extension_54 != null) {
         double value5 = bridge5extension_54.bridge$getPosX();
         double value7 = bridge5extension_54.bridge$getPosY();
         double value9 = bridge5extension_54.bridge$getPosZ();
         long number11 = Math.round((value5 - this.lastX) * 4096.0);
         long number13 = Math.round((value7 - this.lastY) * 4096.0);
         long number15 = Math.round((value9 - this.lastZ) * 4096.0);
         ReplayPacket nameplate217;
         if (!rewind_43.method5()
            && this.field1 < 60
            && bridge5extension_54.bridge$isRiding() == this.field2
            && number11 >= -32768L
            && number11 <= 32767L
            && number13 >= -32768L
            && number13 <= 32767L
            && number15 >= -32768L
            && number15 <= 32767L) {
            nameplate217 = new RelativePositionPacket((short)number11, (short)number13, (short)number15, bridge5extension_54.bridge$isOnGround());
         } else {
            nameplate217 = new PositionPacket(value5, value7, value9, bridge5extension_54.bridge$isOnGround());
            this.field1 = 0;
            this.field2 = bridge5extension_54.bridge$isRiding();
         }

         rewind_43.method9(nameplate217, rewindhandlers52.getTick());
         rewind_43.method9(
            new MovementInputPacket(
               bridge5extension_54.bridge$getMovementInput().bridge$getForwardSpeed(),
               bridge5extension_54.bridge$getMovementInput().bridge$getStrafeSpeed(),
               bridge5extension_54.bridge$getMovementInput().bridge$isSneaking(),
               bridge5extension_54.bridge$getMovementInput().bridge$isJumping()
            ),
            rewindhandlers52.getTick()
         );
         this.lastX = value5;
         this.lastY = value7;
         this.lastZ = value9;
         this.field1++;
      }
   }

   @Override
   public void method3(EventMousePosition highlightimpl231, RewindRecorder rewindhandlers52, ReplayHandler rewind_43) {
      Bridge5Extension_5 bridge5extension_54 = Ref.method7();
      if (bridge5extension_54 != null) {
         Object obj5 = Ref.method3().bridge$getRenderViewEntity();
         if (obj5 == null) {
            obj5 = bridge5extension_54;
         }

         rewind_43.method9(new LookPacket((float)obj5.bridge$getRotationYaw(), (float)obj5.bridge$getRotationPitch()), -1);
      }
   }
}
