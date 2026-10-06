package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.rewindhandlersCore;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge3_17;
import com.moonsworth.lunar.bridge.Bridge3_24;
import com.moonsworth.lunar.bridge.Bridge5Extension6;
import com.moonsworth.lunar.bridge.Bridge5_12;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BridgeType3_3;
import com.moonsworth.lunar.bridge.slayer.Slayer4;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.feature.rewind.gui.AlertImpl;
import com.moonsworth.lunar.client.framework.feature.rewind.highlight.Highlight_3;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplate.RewindhandlersNameplate;
import com.moonsworth.lunar.client.driver.core.Fishing;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers3;
import com.moonsworth.lunar.client.util.ThreadModuleDump6;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump71;
import lombok.Generated;

public class RewindHandlers3Impl8 extends RewindHandlers3 {
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
   private Bridge5Extension6 field19;
   private boolean field20 = false;
   private boolean field21 = true;
   private Bridge3_24 field22;

   public RewindHandlers3Impl8(ThreadModuleDump6<Nameplate4> var1) {
      super(var1);
   }

   public void method14() {
      this.field9.method13(false);
      if (ThreadModuleDump63.MC_VERSION <= 5) {
         Bridge.method5().ifPresent(var1 -> {
            this.field16 = var1.getConfig().hasFastRender();
            if (this.field16) {
               var1.getConfig().setFastRender(false);
               var1.getConfig().updateFramebufferSize();
            }
         });
      }

      Bridge.method5().ifPresent(var1 -> {
         Slayer4 var2 = var1.getConfig();
         if (var2.hasAntiAliasing() && var2.getAntialiasingLevel() > 0 && Fishing.field4 == null) {
            Fishing.field6 = var2.getAntialiasingLevel();
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
         this.field19 = ThreadModuleDump63.method3().bridge$getCurrentScreen();
         ThreadModuleDump63.method3().bridge$setCurrentScreenSilent(((Nameplate4)this.field8.get()).method8().method5());
      }
   }

   public boolean method3(int var1, int var2) {
      boolean var3 = this.field14 != ThreadModuleDump63.method3().bridge$logicalWidth() || this.field15 != ThreadModuleDump63.method3().bridge$logicalHeight();
      if (var3) {
         return false;
      }

      if (this.field11) {
         return true;
      }

      this.field12 = var1;
      this.field13 = var2;
      this.field11 = true;
      return true;
   }

   public void method16() {
      if (this.field11) {
         this.mc.bridge$setDisplayWidth(this.field12);
         this.mc.bridge$setDisplayHeight(this.field13);
         this.mc.bridge$setGuiScale(this.mc.bridge$getGameSettings().bridge$getGuiScale());
         LcuiScreen.method146(false);
         ThreadModuleDump71 var1 = new ThreadModuleDump71(this.mc);
         LcuiScreen.method150(var1);
         this.field11 = false;
      }
   }

   public boolean method5(int var1, int var2, boolean var3) {
      boolean var4 = this.field14 != ThreadModuleDump63.method3().bridge$logicalWidth() || this.field15 != ThreadModuleDump63.method3().bridge$logicalHeight();
      if (var4) {
         this.field14 = ThreadModuleDump63.method3().bridge$logicalWidth();
         this.field15 = ThreadModuleDump63.method3().bridge$logicalHeight();
         return false;
      }

      RewindHandlers var5 = ((Nameplate4)this.field8.get()).method6();
      Highlight_3 var6 = var5.method40().method37();
      boolean var7 = var5.method44() && !var5.method40().method31();
      if (var7) {
         this.method19();
      } else {
         this.field9
            .method2(Math.max(1, (int)var5.method58()), Math.max(1, (int)var5.method59()), this.field17 && Fishing.field4 == null);
      }

      if (var6 != null && !var6.method22()) {
         LcuiScreen.method146(var7);
         this.method3(var1, var2);
         this.field20 = ThreadModuleDump63.method3().bridge$hasOverlay();
         if (!this.field10) {
            this.field22 = ThreadModuleDump63.method3().bridge$getMainRenderTarget();
         }

         ThreadModuleDump63.method3().bridge$setMainRenderTarget(this.field9.method11());
         this.field10 = true;
         this.method21();
         if (this.field21) {
            this.field9.method11().bridge$framebufferClear();
            ThreadModuleDump63.method3().method1(this.field9.method11(), var3);
         } else {
            ThreadModuleDump63.method3().bridge$getMainRenderTarget().bridge$unbindFrameBuffer();
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
         ThreadModuleDump63.method3().bridge$setCurrentScreenSilent(this.field19);
         this.field18 = true;
      }
   }

   public void method7(AbstractRenderContext var1) {
      RewindHandlers var2 = ((Nameplate4)this.field8.get()).method6();
      RewindHandlers3Handler var3 = var2.method49();
      var3.method7(var1);
      if (ThreadModuleDump63.MC_VERSION >= 6) {
         ThreadModuleDump63.method3().bridge$getRenderBuffers().bridge$bufferSource().bridge$endBatch();
      }

      Bridge5_12 var4 = ThreadModuleDump63.method3();
      if (this.field10) {
         var2.method57().method16();
         this.field10 = false;
         this.method16();
         var4.bridge$setMainRenderTarget(this.field22);
         this.field22 = null;
      }

      this.field10 = false;
      ThreadModuleDump63.method3().method1(null, true);
      this.method16();
      float var5 = LcuiScreen.method151().method3();
      float var6 = LcuiScreen.method135(var4.bridge$displayWidth() / var5);
      float var7 = LcuiScreen.method135(var4.bridge$displayHeight() / var5);
      var1.method8(BridgeType3_3.GL_PROJECTION);
      var1.method36();
      Bridge.method42().bridge$loadIdentity();
      var1.method22(0.0, var6, var7, 0.0, 1000.0, ThreadModuleDump63.MC_VERSION >= 29 ? 21000.0 : 3000.0);
      var1.method8(BridgeType3_3.GL_MODELVIEW);
      var1.method36();
      Bridge.method42().bridge$loadIdentity();
      var1.translate(0.0, 0.0, -2000.0);
      RewindHandlers3Impl2 var8 = var2.method50();
      var8.method2(this, var3, var1.method28());
   }

   private void method19() {
      Highlight_3 var1 = ((Nameplate4)this.field8.get()).method6().method40().method37();
      if (var1 != null) {
         RewindhandlersNameplate var2 = var1.method13();
         this.field9.method2(Math.max(1, var2.getWidth()), Math.max(1, var2.getHeight()), this.field17 && Fishing.field4 == null);
         RewindHandlers3Handler var3 = ((Nameplate4)this.field8.get()).method6().method49();
         var3.method8(var2);
      }
   }

   public void method21() {
      Bridge3_17 var1 = ThreadModuleDump63.method3().bridge$getGameRenderer();
      if (var1 != null && this.field9.method11() != null) {
         int var2 = this.field9.method11().bridge$framebufferWidth();
         int var3 = this.field9.method11().bridge$framebufferHeight();
         if (var1.bridge$getRenderTargetsWidth() != var2 || var1.bridge$getRenderTargetsHeight() != var3) {
            var1.bridge$resize(var2, var3);
         }

         RewindHandlers3Impl5 var4 = ((Nameplate4)this.field8.get()).method6().method46();
         var4.method7(false);
      }
   }

   public void method22() {
      this.field9.method11().bridge$framebufferClear();
   }

   public void method23() {
      if (this.field16) {
         ThreadModuleDump63.method3().bridge$schedule(() -> Bridge.method5().ifPresent(var0 -> {
            var0.getConfig().setFastRender(true);
            var0.getConfig().updateFramebufferSize();
         }));
      }

      Bridge.method5().ifPresent(var0 -> {
         Slayer4 var1 = var0.getConfig();
         if (Fishing.field5 && Fishing.field4 == null && var1.getAntialiasingLevel() == 0 && Fishing.field6 != null) {
            Fishing.field4 = Fishing.field6;
            Fishing.field5 = false;
         }
      });
      ThreadModuleDump63.method3().method1(null, true);
      this.method16();
      if (this.field10) {
         this.mc.bridge$setMainRenderTarget(this.field22);
         this.field22 = null;
         this.field10 = false;
      }
   }

   public int getWidth() {
      RewindHandlers var1 = ((Nameplate4)this.field8.get()).method6();
      boolean var2 = var1.method44() && !var1.method40().method31();
      if (var2) {
         Highlight_3 var3 = var1.method40().method37();
         return var3 != null ? var3.method13().getWidth() : this.field9.method11().bridge$framebufferWidth();
      } else {
         return Math.max(1, (int)var1.method58());
      }
   }

   public int getHeight() {
      RewindHandlers var1 = ((Nameplate4)this.field8.get()).method6();
      boolean var2 = var1.method44() && !var1.method40().method31();
      if (var2) {
         Highlight_3 var3 = var1.method40().method37();
         return var3 != null ? var3.method13().getHeight() : this.field9.method11().bridge$framebufferHeight();
      } else {
         return Math.max(1, (int)var1.method59());
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
   public void method14(boolean var1) {
      this.field10 = var1;
   }

   @Generated
   public int method26() {
      return this.field12;
   }

   @Generated
   public void method16(int var1) {
      this.field12 = var1;
   }

   @Generated
   public int method27() {
      return this.field13;
   }

   @Generated
   public void method18(int var1) {
      this.field13 = var1;
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
   public void method21(boolean var1) {
      this.field21 = var1;
   }
}
