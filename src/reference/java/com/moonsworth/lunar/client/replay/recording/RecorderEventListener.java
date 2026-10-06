package com.moonsworth.lunar.client.replay.recording;

import com.moonsworth.lunar.client.replay.recording.ReplayHandler;
import com.moonsworth.lunar.client.event.input.EventMarkerInput;
import com.moonsworth.lunar.client.event.resourcepack.EventResourcePackUpdate;
import com.moonsworth.lunar.client.event.screen.EventScreenChange;
import com.moonsworth.lunar.client.event.screen.EventScreenOpen;
import com.moonsworth.lunar.client.event.render.EventRenderContainerSlot.EventRenderContainerSlotPre;
import com.moonsworth.lunar.client.event.combat.EventPreAttackEntity;
import com.moonsworth.lunar.client.event.mixin.fishing.EventPickBlock;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorldEffect;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventMousePosition;
import com.moonsworth.lunar.client.event.mixin.fishing.EventBlockBreakProgress;
import com.moonsworth.lunar.client.event.mixin.fishing.mixin.EventUseItemOnBlock;
import com.moonsworth.lunar.client.event.mixin.gui.EventPacket;
import com.moonsworth.lunar.client.event.mixin.gui.EventServerResourcePackUpdate;
import com.moonsworth.lunar.client.event.mixin.gui.EventTeleportBase.EventTeleportPost;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindRecorder;

public abstract class RecorderEventListener {
   public RecorderEventListener() {
   }

   public void method1(EventPacket highlightimpl131, RewindRecorder rewindhandlers52, ReplayHandler rewind_43) {
   }

   public void method2(EventTick highlightimpl21, RewindRecorder rewindhandlers52, ReplayHandler rewind_43) {
   }

   public void method3(EventMousePosition event, RewindRecorder rewindhandlers52, ReplayHandler rewind_43) {
   }

   public void method4(EventTeleportPost event, RewindRecorder rewindhandlers52, ReplayHandler rewind_43) {
   }

   public void method5(EventScreenChange highlightimpl71, RewindRecorder rewindhandlers52, ReplayHandler rewind_43) {
   }

   public void method6(EventScreenOpen event, RewindRecorder rewindhandlers52, ReplayHandler rewind_43) {
   }

   public void method7(EventRenderContainerSlotPre event, RewindRecorder rewindhandlers52, ReplayHandler rewind_43) {
   }

   public void method8(EventMarkerInput event, RewindRecorder rewindhandlers52, ReplayHandler rewind_43) {
   }

   public void method9(com.moonsworth.lunar.client.event.input.EventKeyInput highlightimpl131, RewindRecorder rewindhandlers52, ReplayHandler rewind_43) {
   }

   public void method10(EventBlockBreakProgress event, RewindRecorder rewindhandlers52, ReplayHandler rewind_43) {
   }

   public void method11(EventWorldEffect event, RewindRecorder rewindhandlers52, ReplayHandler rewind_43) {
   }

   public void method12(EventPreAttackEntity highlightimpl5_21, RewindRecorder rewindhandlers52, ReplayHandler rewind_43) {
   }

   public void method13(EventUseItemOnBlock highlightimpl1, RewindRecorder rewindhandlers52, ReplayHandler rewind_43) {
   }

   public void method14(com.moonsworth.lunar.client.event.mixin.fishing.mixin.EventUseItem highlightimpl21, RewindRecorder rewindhandlers52, ReplayHandler rewind_43) {
   }

   public void method15(EventResourcePackUpdate event, RewindRecorder rewindhandlers52, ReplayHandler rewind_43) {
   }

   public void method16(EventServerResourcePackUpdate event, RewindRecorder rewindhandlers52, ReplayHandler rewind_43) {
   }

   public void method17(com.moonsworth.lunar.client.event.mixin.gui.EventServerResourcePackRemove highlightimpl71, RewindRecorder rewindhandlers52, ReplayHandler rewind_43) {
   }

   public void method18(EventPickBlock event, RewindRecorder rewindhandlers52, ReplayHandler rewind_43) {
   }

   public void method19(com.moonsworth.lunar.client.event.mixin.fishing.EventDropItem highlightimpl1, RewindRecorder rewindhandlers52, ReplayHandler rewind_43) {
   }
}
