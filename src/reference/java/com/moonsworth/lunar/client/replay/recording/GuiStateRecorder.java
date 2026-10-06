package com.moonsworth.lunar.client.replay.recording;

import com.moonsworth.lunar.bridge.GuiRecipeBookBridge;
import com.moonsworth.lunar.bridge.GuiEditSignBridge;
import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension612;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.replay.recording.ReplayHandler;
import com.moonsworth.lunar.client.replay.gui.GuiType;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.gui.Nameplate2Impl;
import com.moonsworth.lunar.client.replay.network.CloseScreenPacket;
import com.moonsworth.lunar.client.replay.network.MouseInputPacket;
import com.moonsworth.lunar.client.replay.network.ScreenAlignmentPacket;
import com.moonsworth.lunar.client.replay.network.OpenChatPacket;
import com.moonsworth.lunar.client.replay.network.OpenInventoryPacket;
import com.moonsworth.lunar.client.replay.network.CursorPositionPacket;
import com.moonsworth.lunar.client.event.input.EventKeyInput;
import com.moonsworth.lunar.client.event.input.EventMarkerInput;
import com.moonsworth.lunar.client.event.screen.EventScreenChange;
import com.moonsworth.lunar.client.event.screen.EventScreenOpen;
import com.moonsworth.lunar.client.event.render.EventRenderContainerSlot.EventRenderContainerSlotPre;
import com.moonsworth.lunar.client.event.mixin.fishing.EventMousePosition;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindRecorder;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.ui.GuiResolution;

public class GuiStateRecorder extends RecorderEventListener {
   private int x;
   private int y;
   private GuiType field1 = GuiType.CENTER;
   private GuiType field2 = GuiType.CENTER;

   public GuiStateRecorder() {
   }

   @Override
   public void method5(EventScreenChange highlightimpl71, RewindRecorder rewindhandlers52, ReplayHandler rewind_43) {
      GuiScreenBridge bridge5extension64 = highlightimpl71.method1();
      if (bridge5extension64 != null) {
         this.field1 = GuiType.CENTER;
         this.field2 = GuiType.CENTER;
         if (bridge5extension64 instanceof GuiRecipeBookBridge) {
            rewind_43.method9(new OpenInventoryPacket(Ref.method3().bridge$getCreativeTab()), rewindhandlers52.getTick());
         } else if (bridge5extension64 instanceof Bridge5Extension612 bridge5extension6125) {
            rewind_43.method9(new OpenChatPacket(bridge5extension6125.bridge$getInitialText()), rewindhandlers52.getTick());
            this.field1 = GuiType.START;
            this.field2 = GuiType.END;
         } else if (bridge5extension64 instanceof GuiEditSignBridge) {
            this.field2 = GuiType.START;
         }

         rewind_43.method9(new ScreenAlignmentPacket(this.field1, this.field2), rewindhandlers52.getTick());
      }
   }

   @Override
   public void method6(EventScreenOpen highlightimpl91, RewindRecorder rewindhandlers52, ReplayHandler rewind_43) {
      if (highlightimpl91.method2() == null) {
         rewind_43.method9(new CloseScreenPacket(), rewindhandlers52.getTick());
      }
   }

   @Override
   public void method7(EventRenderContainerSlotPre data41, RewindRecorder rewindhandlers52, ReplayHandler rewind_43) {
      GuiResolution threadmoduledump714 = LcuiScreen.method151();
      this.x = this.field1.apply(data41.OOCCRCHHRHRCRICOCORHROHCRCROHO().xi(), threadmoduledump714.getScaledWidth());
      this.y = this.field2.apply(data41.OOCCRCHHRHRCRICOCORHROHCRCROHO().RROCOHICOORRHCIHHHCHRCICHIIHCO(), threadmoduledump714.getScaledHeight());
   }

   @Override
   public void method3(EventMousePosition highlightimpl231, RewindRecorder rewindhandlers52, ReplayHandler rewind_43) {
      rewind_43.method9(new CursorPositionPacket(this.x, this.y), -1);
   }

   @Override
   public void method8(EventMarkerInput highlightimpl141, RewindRecorder rewindhandlers52, ReplayHandler rewind_43) {
      GuiScreenBridge bridge5extension64 = Ref.method3().bridge$getCurrentScreen();
      if (bridge5extension64 != null) {
         int number5 = this.field1.apply(highlightimpl141.method2().xi(), bridge5extension64.bridge$getWidth());
         int number6 = this.field2.apply(highlightimpl141.method2().RROCOHICOORRHCIHHHCHRCICHIIHCO(), bridge5extension64.bridge$getHeight());
         rewind_43.method9(
            new MouseInputPacket(
               number5,
               number6,
               highlightimpl141.method3(),
               bridge5extension64.bridge$isShiftKeyDown(),
               bridge5extension64.bridge$isCtrlKeyDown(),
               highlightimpl141.method4(),
               highlightimpl141.method5(),
               highlightimpl141.method6(),
               highlightimpl141.method7(),
               highlightimpl141.method8(),
               highlightimpl141.method9()
            ),
            rewindhandlers52.getTick()
         );
      }
   }

   @Override
   public void method9(EventKeyInput highlightimpl131, RewindRecorder rewindhandlers52, ReplayHandler rewind_43) {
      GuiScreenBridge bridge5extension64 = Ref.method3().bridge$getCurrentScreen();
      if (bridge5extension64 != null) {
         rewind_43.method9(
            new Nameplate2Impl(
               highlightimpl131.getCharacter(), highlightimpl131.getKeyCode(), highlightimpl131.getModifiers(), bridge5extension64.bridge$isShiftKeyDown(), bridge5extension64.bridge$isCtrlKeyDown(), highlightimpl131.method4()
            ),
            rewindhandlers52.getTick()
         );
      }
   }
}
