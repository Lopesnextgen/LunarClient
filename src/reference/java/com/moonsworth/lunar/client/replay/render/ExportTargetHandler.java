package com.moonsworth.lunar.client.replay.render;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.GameRendererBridge;
import com.moonsworth.lunar.bridge.Bridge3_24;
import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.bridge.MinecraftBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.GlMatrixMode;
import com.moonsworth.lunar.bridge.optifine.OptifineConfigBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.replay.gui.AlertImpl;
import com.moonsworth.lunar.client.replay.timeline.ReplayTimeline;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.replay.export.ExportSettings;
import com.moonsworth.lunar.client.driver.core.Fishing;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandler;
import com.moonsworth.lunar.client.util.collection.ValueHolder;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.ui.GuiResolution;
import lombok.Generated;

public class ExportTargetHandler extends RewindHandler {
   private final AlertImpl field9 = new AlertImpl();
   private boolean field10 = false;
   private boolean field11 = false;
   private int field12 = 0;
   private int field13 = 0;
   private int field14 = 0;
   private int field15 = 0;
   private boolean field16 = false;
   private boolean field17 = false;
   private boolean field18 = true;
   private GuiScreenBridge field19;
   private boolean field20 = false;
   private boolean field21 = true;
   private Bridge3_24 field22;

   public ExportTargetHandler(ValueHolder<ReplayContext> threadmoduledump61) {
      super(threadmoduledump61);
   }

   public void method14() {
      this.field9.method13(false);
      if (Ref.MC_VERSION <= 5) {
         Bridge.method5().ifPresent(arg1 -> {
            this.field16 = arg1.getConfig().hasFastRender();
            if (this.field16) {
               arg1.getConfig().setFastRender(false);
               arg1.getConfig().updateFramebufferSize();
            }
         });
      }

      Bridge.method5().ifPresent(arg1 -> {
         OptifineConfigBridge slayer42 = arg1.getConfig();
         if (slayer42.hasAntiAliasing() && slayer42.getAntialiasingLevel() > 0 && Fishing.field4 == null) {
            Fishing.field6 = slayer42.getAntialiasingLevel();
            Fishing.field4 = 0;
            Fishing.field5 = true;
            this.field17 = true;
         }
      });
      this.method19();
   }

   public void method15() {
      if (this.field18) {
         this.field18 = false;
         this.field19 = Ref.method3().bridge$getCurrentScreen();
         Ref.method3().bridge$setCurrentScreenSilent(((ReplayContext)this.OHCCCCRIOIHOCRCCIHCIHIIHHHHORO.get()).method8().method5());
      }
   }

   public boolean method3(int number1, int number2) {
      boolean flag3 = this.field14 != Ref.method3().bridge$logicalWidth() || this.field15 != Ref.method3().bridge$logicalHeight();
      if (flag3) {
         return false;
      }

      if (this.field11) {
         return true;
      }

      this.field12 = number1;
      this.field13 = number2;
      this.field11 = true;
      return true;
   }

   public void method16() {
      if (this.field11) {
         this.mc.bridge$setDisplayWidth(this.field12);
         this.mc.bridge$setDisplayHeight(this.field13);
         this.mc.bridge$setGuiScale(this.mc.bridge$getGameSettings().bridge$getGuiScale());
         LcuiScreen.method146(false);
         GuiResolution threadmoduledump711 = new GuiResolution(this.mc);
         LcuiScreen.method150(threadmoduledump711);
         this.field11 = false;
      }
   }

   public boolean method5(int number1, int number2, boolean flag3) {
      boolean flag4 = this.field14 != Ref.method3().bridge$logicalWidth() || this.field15 != Ref.method3().bridge$logicalHeight();
      if (flag4) {
         this.field14 = Ref.method3().bridge$logicalWidth();
         this.field15 = Ref.method3().bridge$logicalHeight();
         return false;
      }

      RewindHandlers rewindhandlers5 = ((ReplayContext)this.OHCCCCRIOIHOCRCCIHCIHIIHHHHORO.get()).method6();
      ReplayTimeline highlight_36 = rewindhandlers5.method40().method37();
      boolean flag7 = rewindhandlers5.method44() && !rewindhandlers5.method40().method31();
      if (flag7) {
         this.method19();
      } else {
         this.field9
            .ORICHRORRORHORHOIHCRHOORCRRHOI(Math.max(1, (int)rewindhandlers5.method58()), Math.max(1, (int)rewindhandlers5.method59()), this.field17 && Fishing.field4 == null);
      }

      if (highlight_36 != null && !highlight_36.method22()) {
         LcuiScreen.method146(flag7);
         this.method3(number1, number2);
         this.field20 = Ref.method3().bridge$hasOverlay();
         if (!this.field10) {
            this.field22 = Ref.method3().bridge$getMainRenderTarget();
         }

         Ref.method3().bridge$setMainRenderTarget(this.field9.method11());
         this.field10 = true;
         this.method21();
         if (this.field21) {
            this.field9.method11().bridge$framebufferClear();
            Ref.method3().method1(this.field9.method11(), flag3);
         } else {
            Ref.method3().bridge$getMainRenderTarget().bridge$unbindFrameBuffer();
         }

         this.field17 = Fishing.field4 != null;
         return true;
      } else {
         this.field10 = false;
         return false;
      }
   }

   public void method17() {
      if (!this.field18) {
         Ref.method3().bridge$setCurrentScreenSilent(this.field19);
         this.field18 = true;
      }
   }

   public void method7(AbstractRenderContext bridgeextension_91) {
      RewindHandlers rewindhandlers2 = ((ReplayContext)this.OHCCCCRIOIHOCRCCIHCIHIIHHHHORO.get()).method6();
      ReplayDriverHandler rewindhandlers3handler3 = rewindhandlers2.method49();
      rewindhandlers3handler3.method7(bridgeextension_91);
      if (Ref.MC_VERSION >= 6) {
         Ref.method3().bridge$getRenderBuffers().bridge$bufferSource().bridge$endBatch();
      }

      MinecraftBridge bridge5_124 = Ref.method3();
      if (this.field10) {
         rewindhandlers2.method57().method16();
         this.field10 = false;
         this.method16();
         bridge5_124.bridge$setMainRenderTarget(this.field22);
         this.field22 = null;
      }

      this.field10 = false;
      Ref.method3().method1(null, true);
      this.method16();
      float value5 = LcuiScreen.method151().method3();
      float value6 = LcuiScreen.method135(bridge5_124.bridge$displayWidth() / value5);
      float value7 = LcuiScreen.method135(bridge5_124.bridge$displayHeight() / value5);
      bridgeextension_91.method8(GlMatrixMode.GL_PROJECTION);
      bridgeextension_91.method36();
      Bridge.method42().bridge$loadIdentity();
      bridgeextension_91.method22(0.0, value6, value7, 0.0, 1000.0, Ref.MC_VERSION >= 29 ? 21000.0 : 3000.0);
      bridgeextension_91.method8(GlMatrixMode.GL_MODELVIEW);
      bridgeextension_91.method36();
      Bridge.method42().bridge$loadIdentity();
      bridgeextension_91.translate(0.0, 0.0, -2000.0);
      SelectionHighlightHandler rewindhandlers3impl28 = rewindhandlers2.method50();
      rewindhandlers3impl28.method2(this, rewindhandlers3handler3, bridgeextension_91.method28());
   }

   private void method19() {
      ReplayTimeline highlight_31 = ((ReplayContext)this.OHCCCCRIOIHOCRCCIHCIHIIHHHHORO.get()).method6().method40().method37();
      if (highlight_31 != null) {
         ExportSettings rewindhandlersnameplate2 = highlight_31.method13();
         this.field9.method2(Math.max(1, rewindhandlersnameplate2.getWidth()), Math.max(1, rewindhandlersnameplate2.getHeight()), this.field17 && Fishing.field4 == null);
         ReplayDriverHandler rewindhandlers3handler3 = ((ReplayContext)this.OHCCCCRIOIHOCRCCIHCIHIIHHHHORO.get()).method6().method49();
         rewindhandlers3handler3.method8(rewindhandlersnameplate2);
      }
   }

   public void method21() {
      GameRendererBridge bridge3_171 = Ref.method3().bridge$getGameRenderer();
      if (bridge3_171 != null && this.field9.method11() != null) {
         int number2 = this.field9.method11().bridge$framebufferWidth();
         int number3 = this.field9.method11().bridge$framebufferHeight();
         if (bridge3_171.bridge$getRenderTargetsWidth() != number2 || bridge3_171.bridge$getRenderTargetsHeight() != number3) {
            bridge3_171.bridge$resize(number2, number3);
         }

         ScreenRenderHandler rewindhandlers3impl54 = ((ReplayContext)this.OHCCCCRIOIHOCRCCIHCIHIIHHHHORO.get()).method6().method46();
         rewindhandlers3impl54.method7(false);
      }
   }

   public void method22() {
      this.field9.method11().bridge$framebufferClear();
   }

   public void method23() {
      if (this.field16) {
         Ref.method3().bridge$schedule(() -> Bridge.method5().ifPresent(arg0 -> {
            arg0.getConfig().setFastRender(true);
            arg0.getConfig().updateFramebufferSize();
         }));
      }

      Bridge.method5().ifPresent(arg0 -> {
         OptifineConfigBridge slayer41 = arg0.getConfig();
         if (Fishing.field5 && Fishing.field4 == null && slayer41.getAntialiasingLevel() == 0 && Fishing.field6 != null) {
            Fishing.field4 = Fishing.field6;
            Fishing.field5 = false;
         }
      });
      Ref.method3().method1(null, true);
      this.method16();
      if (this.field10) {
         this.mc.bridge$setMainRenderTarget(this.field22);
         this.field22 = null;
         this.field10 = false;
      }
   }

   public int getWidth() {
      RewindHandlers rewindhandlers1 = ((ReplayContext)this.OHCCCCRIOIHOCRCCIHCIHIIHHHHORO.get()).method6();
      boolean flag2 = rewindhandlers1.method44() && !rewindhandlers1.method40().method31();
      if (flag2) {
         ReplayTimeline highlight_33 = rewindhandlers1.method40().method37();
         return highlight_33 != null ? highlight_33.method13().getWidth() : this.field9.method11().bridge$framebufferWidth();
      } else {
         return Math.max(1, (int)rewindhandlers1.method58());
      }
   }

   public int getHeight() {
      RewindHandlers rewindhandlers1 = ((ReplayContext)this.OHCCCCRIOIHOCRCCIHCIHIIHHHHORO.get()).method6();
      boolean flag2 = rewindhandlers1.method44() && !rewindhandlers1.method40().method31();
      if (flag2) {
         ReplayTimeline highlight_33 = rewindhandlers1.method40().method37();
         return highlight_33 != null ? highlight_33.method13().getHeight() : this.field9.method11().bridge$framebufferHeight();
      } else {
         return Math.max(1, (int)rewindhandlers1.method59());
      }
   }

   @Generated
   public AlertImpl method24() {
      return this.field9;
   }

   @Generated
   public boolean method25() {
      return this.field10;
   }

   @Generated
   public void method14(boolean flag1) {
      this.field10 = flag1;
   }

   @Generated
   public int method26() {
      return this.field12;
   }

   @Generated
   public void method16(int number1) {
      this.field12 = number1;
   }

   @Generated
   public int method27() {
      return this.field13;
   }

   @Generated
   public void method18(int number1) {
      this.field13 = number1;
   }

   @Generated
   public boolean method28() {
      return this.field20;
   }

   @Generated
   public boolean method29() {
      return this.field21;
   }

   @Generated
   public void method21(boolean flag1) {
      this.field21 = flag1;
   }
}
