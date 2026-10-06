package com.moonsworth.lunar.client.replay.recording;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.client.replay.recording.ReplayHandler;
import com.moonsworth.lunar.client.replay.network.AttackStrengthPacket;
import com.moonsworth.lunar.client.replay.network.HeldItemPacket;
import com.moonsworth.lunar.client.replay.network.DropItemPacket;
import com.moonsworth.lunar.client.replay.network.StartUsingItemPacket;
import com.moonsworth.lunar.client.replay.network.PlayerAnimationPacket;
import com.moonsworth.lunar.client.replay.network.SetSprintingPacket;
import com.moonsworth.lunar.client.replay.network.AttackPacket;
import com.moonsworth.lunar.client.replay.network.UseItemPacket;
import com.moonsworth.lunar.client.replay.network.PickBlockPacket;
import com.moonsworth.lunar.client.replay.network.StopUsingItemPacket;
import com.moonsworth.lunar.client.replay.network.SwingHandPacket;
import com.moonsworth.lunar.client.replay.network.ItemSwapPacket;
import com.moonsworth.lunar.client.replay.network.SetPausedPacket;
import com.moonsworth.lunar.client.event.combat.EventPreAttackEntity;
import com.moonsworth.lunar.client.event.mixin.fishing.EventPickBlock;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.fishing.mixin.EventUseItemOnBlock;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindRecorder;
import com.moonsworth.lunar.client.framework.Ref;

public class PlayerStateRecorder extends RecorderEventListener {
   private int field1 = -1;
   private int field2 = -1;
   private boolean field3 = false;
   private boolean field4 = false;
   private boolean field5 = false;
   private int field6 = 0;
   private int field7 = 0;

   public PlayerStateRecorder() {
   }

   @Override
   public void method2(EventTick highlightimpl21, RewindRecorder rewindhandlers52, ReplayHandler rewind_43) {
      Bridge5Extension_5 bridge5extension_54 = Ref.method7();
      if (bridge5extension_54 != null) {
         if (rewind_43.method5() || bridge5extension_54.bridge$getCurrentEquippedItemIndex() != this.field1) {
            this.field1 = bridge5extension_54.bridge$getCurrentEquippedItemIndex();
            rewind_43.method9(new HeldItemPacket(this.field1), rewindhandlers52.getTick());
         }

         if (rewind_43.method5() || Ref.method3().bridge$isGamePaused() != this.field4) {
            this.field4 = Ref.method3().bridge$isGamePaused();
            rewind_43.method9(new SetPausedPacket(this.field4), rewindhandlers52.getTick());
            rewindhandlers52.method10(this.field4);
         }

         if (!this.field4 && bridge5extension_54.bridge$isSwingInProgress() && bridge5extension_54.bridge$getSwingProgress() == 0) {
            rewind_43.method9(new SwingHandPacket(bridge5extension_54.bridge$getSwingingArm()), rewindhandlers52.getTick());
         }

         if (rewind_43.method5() || bridge5extension_54.bridge$isSprinting() != this.field3) {
            this.field3 = bridge5extension_54.bridge$isSprinting();
            rewind_43.method9(new SetSprintingPacket(this.field3), rewindhandlers52.getTick());
         }

         if (rewind_43.method5() || Ref.method3().bridge$getGameSettings().bridge$getThirdPersonView() != this.field2) {
            this.field2 = Ref.method3().bridge$getGameSettings().bridge$getThirdPersonView();
            rewind_43.method9(new PlayerAnimationPacket(this.field2), rewindhandlers52.getTick());
         }

         if (rewind_43.method5() || bridge5extension_54.bridge$isUsingItem() != this.field5) {
            this.field5 = bridge5extension_54.bridge$isUsingItem();
            if (this.field5) {
               rewind_43.method9(new StartUsingItemPacket(bridge5extension_54.bridge$getSwingingArm()), rewindhandlers52.getTick());
            } else {
               rewind_43.method9(new StopUsingItemPacket(), rewindhandlers52.getTick());
            }
         }

         if (Ref.MC_VERSION >= 5 && (rewind_43.method5() || bridge5extension_54.bridge$getAttackStrengthTicker() != this.field6)) {
            this.field6 = bridge5extension_54.bridge$getAttackStrengthTicker();
            if (rewind_43.method5() || this.field6 == 1) {
               rewind_43.method9(new AttackStrengthPacket(this.field6), rewindhandlers52.getTick());
            }
         }

         if (Ref.MC_VERSION >= 35 && (rewind_43.method5() || bridge5extension_54.bridge$getItemSwapTicker() != this.field7)) {
            this.field7 = bridge5extension_54.bridge$getItemSwapTicker();
            if (rewind_43.method5() || this.field7 == 1) {
               rewind_43.method9(new ItemSwapPacket(this.field7), rewindhandlers52.getTick());
            }
         }
      }
   }

   @Override
   public void method12(EventPreAttackEntity highlightimpl5_21, RewindRecorder rewindhandlers52, ReplayHandler rewind_43) {
      rewind_43.method9(new AttackPacket(), rewindhandlers52.getTick());
   }

   @Override
   public void method13(EventUseItemOnBlock highlightimpl1, RewindRecorder rewindhandlers52, ReplayHandler rewind_43) {
      rewind_43.method9(new com.moonsworth.lunar.client.replay.network.UseItemOnBlockPacket(highlightimpl1), rewindhandlers52.getTick());
   }

   @Override
   public void method14(com.moonsworth.lunar.client.event.mixin.fishing.mixin.EventUseItem highlightimpl21, RewindRecorder rewindhandlers52, ReplayHandler rewind_43) {
      rewind_43.method9(new UseItemPacket(highlightimpl21), rewindhandlers52.getTick());
   }

   @Override
   public void method18(EventPickBlock event, RewindRecorder rewindhandlers52, ReplayHandler rewind_43) {
      rewind_43.method9(new PickBlockPacket(), rewindhandlers52.getTick());
   }

   @Override
   public void method19(com.moonsworth.lunar.client.event.mixin.fishing.EventDropItem highlightimpl1, RewindRecorder rewindhandlers52, ReplayHandler rewind_43) {
      rewind_43.method9(new DropItemPacket(highlightimpl1.method1()), rewindhandlers52.getTick());
   }
}
