package com.moonsworth.lunar.client.framework.feature.mod.holograms.mixin;

import com.moonsworth.lunar.bridge.Bridge5Extension3;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.Click3;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.Gui2Extension3;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.HighlightType2;
import com.moonsworth.lunar.client.event.mixin.chat.EventChatMessageLegacy.Data;
import com.moonsworth.lunar.client.event.mixin.fishing.EventClientTick;
import com.moonsworth.lunar.client.event.mixin.fishing.EventEverySecond;
import com.moonsworth.lunar.client.event.mixin.fishing.EventWorldLifecycle.EventWorldChanged;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump67;
import lombok.Generated;
import org.joml.Vector3d;

public class GuiRewindhandlersHandler22 extends com.moonsworth.lunar.client.framework.listener.DynamicListener {
   private final GuiRewindhandlersHandler2 field7 = (GuiRewindhandlersHandler2)this.method3(GuiRewindhandlersHandler2.class);
   private Bridge5Extension3 field8;
   private boolean field9;

   public GuiRewindhandlersHandler22() {
      this.handle(EventClientTick.class, this::method2);
      this.handle(Data.class, this::method3);
      this.handle(EventWorldChanged.class, this::method4);
      this.handle(EventEverySecond.class, this::method1);
   }

   private void method1(EventEverySecond var1) {
      if (Click3.getIsland() == Gui2Extension3.KUUDRA) {
         if (this.field8 != null) {
            if (!this.field8.bridge$isRemoved()) {
               return;
            }

            this.field8 = null;
         }

         for (BridgeExtension var4 : ThreadModuleDump63.method8().bridge$getEntities()) {
            if (var4 != null && var4 instanceof Bridge5Extension3 var5 && var5.bridge$getSize() == 30 && var5.bridge$getUnboundedHealth() <= 100000.0F) {
               this.field8 = var5;
               return;
            }
         }
      }
   }

   private void method2(EventClientTick var1) {
      if (Click3.getIsland() == Gui2Extension3.KUUDRA) {
         if (this.field8 != null && this.method7() && !this.field9) {
            float var2 = this.field8.bridge$getUnboundedHealth();
            if (var2 < 25000.0F && var2 > 1024.0F) {
               this.field9 = true;
            }
         }
      }
   }

   private void method3(Data var1) {
      if (Click3.getIsland() == Gui2Extension3.KUUDRA) {
         if (this.method7() && !this.field9) {
            String var2 = var1.CRCORIIOCOCRIHHOHRHORCHRHCRIRH();
            if (var2.equals("[NPC] Elle: POW! SURELY THAT'S IT! I don't think he has any more in him!")) {
               this.field9 = true;
            }
         }
      }
   }

   private void method4(EventWorldChanged var1) {
      this.field8 = null;
      this.field9 = false;
   }

   public float getHealth() {
      if (this.field8 == null) {
         return 0.0F;
      }

      float var1 = this.field8.bridge$getUnboundedHealth();
      if (this.method7()) {
         if (this.field9) {
            var1 = (var1 - 1.0F) * 9600.0F;
            if (var1 < 0.0F) {
               var1 = 0.0F;
            }
         } else {
            var1 = (var1 - 25000.0F) / 3.0F * 4.0F;
            if (var1 < 0.0F) {
               var1 = 0.0F;
            }
         }
      }

      return var1;
   }

   public int method5() {
      return this.field9 ? 240000000 : 100000;
   }

   public Vector3d method6(float var1) {
      return this.field8 == null
         ? null
         : new Vector3d(
            ThreadModuleDump67.method15(this.field8.method3(), this.field8.bridge$getPosX(), var1),
            ThreadModuleDump67.method15(this.field8.method4(), this.field8.bridge$getPosY(), var1),
            ThreadModuleDump67.method15(this.field8.method5(), this.field8.bridge$getPosZ(), var1)
         );
   }

   private boolean method7() {
      return this.field7.method6() == HighlightType2.T5;
   }

   @Generated
   public GuiRewindhandlersHandler2 method8() {
      return this.field7;
   }

   @Generated
   public Bridge5Extension3 method9() {
      return this.field8;
   }

   @Generated
   public boolean method10() {
      return this.field9;
   }
}
