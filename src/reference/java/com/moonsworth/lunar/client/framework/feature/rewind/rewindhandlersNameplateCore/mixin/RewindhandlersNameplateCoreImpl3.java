package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplateCore.mixin;

import com.moonsworth.lunar.bridge.Bridge4_15;
import com.moonsworth.lunar.bridge.Bridge5Extension3_2;
import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.bridge.Bridge5Extension612;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.feature.rewind.Rewind_4;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.GuiType;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.gui.Nameplate2Impl;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.gui.Nameplate2Impl2;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.gui.Nameplate2Impl3;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.gui.Nameplate2Impl4;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.gui.Nameplate2Impl5;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.gui.Nameplate2Impl6;
import com.moonsworth.lunar.client.framework.feature.rewind.nameplate.mixin.gui.Nameplate2Impl7;
import com.moonsworth.lunar.client.event.input.KeyInputEvent;
import com.moonsworth.lunar.client.event.input.MarkerInputEvent;
import com.moonsworth.lunar.client.event.screen.ScreenChangeEvent;
import com.moonsworth.lunar.client.event.screen.ScreenOpenEvent;
import com.moonsworth.lunar.client.event.render.ContainerSlotRenderEvent.ContainerSlotPreEvent;
import com.moonsworth.lunar.client.event.mixin.fishing.EventCursorPosition;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers5;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump71;

public class RewindhandlersNameplateCoreImpl3 extends RewindhandlersNameplateCore {
   private int x;
   private int y;
   private GuiType field1 = GuiType.CENTER;
   private GuiType field2 = GuiType.CENTER;

   @Override
   public void method5(ScreenChangeEvent var1, RewindHandlers5 var2, Rewind_4 var3) {
      Bridge5Extension6 var4 = var1.method1();
      if (var4 != null) {
         this.field1 = GuiType.CENTER;
         this.field2 = GuiType.CENTER;
         if (var4 instanceof Bridge4_15) {
            var3.method9(new Nameplate2Impl6(ThreadModuleDump63.method3().bridge$getCreativeTab()), var2.getTick());
         } else if (var4 instanceof Bridge5Extension612 var5) {
            var3.method9(new Nameplate2Impl5(var5.bridge$getInitialText()), var2.getTick());
            this.field1 = GuiType.START;
            this.field2 = GuiType.END;
         } else if (var4 instanceof Bridge5Extension3_2) {
            this.field2 = GuiType.START;
         }

         var3.method9(new Nameplate2Impl4(this.field1, this.field2), var2.getTick());
      }
   }

   @Override
   public void method6(ScreenOpenEvent var1, RewindHandlers5 var2, Rewind_4 var3) {
      if (var1.method2() == null) {
         var3.method9(new Nameplate2Impl2(), var2.getTick());
      }
   }

   @Override
   public void method7(ContainerSlotPreEvent var1, RewindHandlers5 var2, Rewind_4 var3) {
      ThreadModuleDump71 var4 = LcuiScreen.method151();
      this.x = this.field1.apply(var1.method1().xi(), var4.getScaledWidth());
      this.y = this.field2.apply(var1.method1().RROCOHICOORRHCIHHHCHRCICHIIHCO(), var4.getScaledHeight());
   }

   @Override
   public void method3(EventCursorPosition var1, RewindHandlers5 var2, Rewind_4 var3) {
      var3.method9(new Nameplate2Impl7(this.x, this.y), -1);
   }

   @Override
   public void method8(MarkerInputEvent var1, RewindHandlers5 var2, Rewind_4 var3) {
      Bridge5Extension6 var4 = ThreadModuleDump63.method3().bridge$getCurrentScreen();
      if (var4 != null) {
         int var5 = this.field1.apply(var1.method2().xi(), var4.bridge$getWidth());
         int var6 = this.field2.apply(var1.method2().RROCOHICOORRHCIHHHCHRCICHIIHCO(), var4.bridge$getHeight());
         var3.method9(
            new Nameplate2Impl3(
               var5,
               var6,
               var1.method3(),
               var4.bridge$isShiftKeyDown(),
               var4.bridge$isCtrlKeyDown(),
               var1.method4(),
               var1.method5(),
               var1.method6(),
               var1.method7(),
               var1.method8(),
               var1.method9()
            ),
            var2.getTick()
         );
      }
   }

   @Override
   public void method9(KeyInputEvent var1, RewindHandlers5 var2, Rewind_4 var3) {
      Bridge5Extension6 var4 = ThreadModuleDump63.method3().bridge$getCurrentScreen();
      if (var4 != null) {
         var3.method9(
            new Nameplate2Impl(
               var1.getCharacter(), var1.getKeyCode(), var1.getModifiers(), var4.bridge$isShiftKeyDown(), var4.bridge$isCtrlKeyDown(), var1.method4()
            ),
            var2.getTick()
         );
      }
   }
}
