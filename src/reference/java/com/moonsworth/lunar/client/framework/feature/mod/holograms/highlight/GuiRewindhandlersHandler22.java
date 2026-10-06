package com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight;

import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click3;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.mixin.HighlightType;
import com.moonsworth.lunar.client.guiRewindhandlers.GuiRewindhandlersHandler2_2;
import com.moonsworth.lunar.client.guiRewindhandlers.rewindhandlers.Rewindhandlers2;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.screen.ScreenChangeEvent;
import com.moonsworth.lunar.client.event.render.ScreenInitEvent.ScreenInitPostEvent;
import com.moonsworth.lunar.client.event.mixin.gui.LocationChangeEvent;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.regex.Matcher;
import javax.annotation.Nullable;
import lombok.Generated;

public class GuiRewindhandlersHandler22 extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private final GuiRewindhandlersHandler2_2 field7 = (GuiRewindhandlersHandler2_2)this.method3(GuiRewindhandlersHandler2_2.class);
   @Nullable
   private HighlightType field8 = null;

   public GuiRewindhandlersHandler22() {
      this.method11(ScreenChangeEvent.class, this::method1, 190);
      this.handle(LocationChangeEvent.class, this::method3);
   }

   private void method1(ScreenChangeEvent var1) {
      if (var1.method1() != null) {
         this.method5();
      } else {
         this.field8 = null;
      }
   }

   protected void onEnable() {
      this.method5();
   }

   private void method5() {
      if (Click3.hasIsland()) {
         String var1 = this.field7.method5();
         if (var1 != null && !var1.isEmpty()) {
            for (HighlightType var5 : HighlightType.values()) {
               Matcher var6 = var5.getPattern().matcher(var1);
               if (var6.find()) {
                  this.field8 = var5;
                  return;
               }
            }
         }
      }
   }

   private void method3(LocationChangeEvent var1) {
      if (this.field8 == null) {
         Rewindhandlers2 var2 = var1.method2();
         if (var2 != null) {
            if (var1.method2().field2.equals("SKYBLOCK")) {
               String var3 = this.field7.method5();
               if (var3 == null || var3.isEmpty()) {
                  return;
               }

               for (HighlightType var7 : HighlightType.values()) {
                  Matcher var8 = var7.getPattern().matcher(var3);
                  if (var8.find()) {
                     ClientEventBus.method29().method12(ScreenInitPostEvent.class, () -> new ScreenInitPostEvent(ThreadModuleDump63.method3().bridge$getCurrentScreen()));
                  }
               }
            }
         }
      }
   }

   @Generated
   public GuiRewindhandlersHandler2_2 method6() {
      return this.field7;
   }

   @Nullable
   @Generated
   public HighlightType method7() {
      return this.field8;
   }
}
