package com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.rewindhandlersCore;

import com.google.gson.JsonArray;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BridgeType2_9;
import com.moonsworth.lunar.bridge.BridgeType3_3;
import com.moonsworth.lunar.bridge.DepthComparison;
import com.moonsworth.lunar.bridge.LegacyGuiGraphicsBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlers.nameplate.Nameplate4;
import com.moonsworth.lunar.client.framework.feature.rewind.rewindhandlersNameplate.RewindhandlersNameplate;
import com.moonsworth.lunar.client.event.input.InputActionLegacy;
import com.moonsworth.lunar.client.event.mixin.rewindhandlers.KeybindEvent;
import com.moonsworth.lunar.client.event.mixin.rewindhandlers.EventMouseButtonLegacy;
import com.moonsworth.lunar.client.driver.BrowserHandler;
import com.moonsworth.lunar.client.driver.DriverRouteRegistryLegacy;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data5;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.driver.core.MigrationContextLegacy;
import com.moonsworth.lunar.client.driver.core.MigrationContextLegacy.Data2;
import com.moonsworth.lunar.client.driver.nameplate.Nameplate;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers3;
import com.moonsworth.lunar.client.util.ThreadModuleDump6;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump71;
import com.moonsworth.webosr.wrappers.Browser;
import lombok.Generated;

public class RewindHandlers3Handler extends RewindHandlers3 {
   private static final BrowserHandler field9 = new BrowserHandler() {
      public void onWindowObjectReady(Browser var1, long var2, boolean var4, String var5) {
         RewindHandlers3Handler.field10.method57().set(false);
      }

      protected void method1() {
         RewindHandlers3Handler.field10.method57().set(true);
      }
   };
   private static final DriverViewportLegacy field10 = new DriverViewportLegacy(null, field9) {
      protected void method6() {
      }
   };
   private final MigrationContextLegacy field11;
   private long field12 = 0L;
   private boolean field13 = false;
   private Nameplate[] field14 = new Nameplate[0];

   public RewindHandlers3Handler(ThreadModuleDump6<Nameplate4> var1) {
      super(var1);
      field10.method4(1920, 1080, 1920, 1080);
      field10.method18(DriverRouteRegistryLegacy.field3, true);
      field10.method55().method13().setDeviceScaleFactor(1.0);
      this.field11 = ((Data2)((Data2)MigrationContextLegacy.method9(null).method1(field10.method55().method13()).IHCORIOHOHHOIORHCCOOIIIHOCROOI(1920, 1080))
            .HRHCHRICROCCHOHOROROIRIICHCRHH(0, 0))
         .method5(field10)
         .method8();
      this.handle(EventMouseButtonLegacy.class, this::method4);
      this.handle(KeybindEvent.class, this::method5);
   }

   public boolean method14() {
      double var1 = this.method15();
      double var3 = this.method16();

      for (Nameplate var8 : this.field14) {
         if (var8.x() - 50.0F <= var1 && var8.x() + var8.method1() + 50.0F >= var1 && var8.y() - 50.0F <= var3 && var8.y() + var8.method2() + 50.0F >= var3) {
            return true;
         }
      }

      return false;
   }

   public double method15() {
      RewindHandlers var1 = ((Nameplate4)this.field8.get()).method6();
      float var2 = var1.method58();
      float var3 = var1.method59();
      int var4 = this.field11.getWidth();
      int var5 = this.field11.getHeight();
      if (var2 > var3) {
         var2 = var3 * var4 / var5;
         if (var2 > var1.method58()) {
            var2 = var1.method58();
         }
      } else {
         var3 = var2 * var5 / var4;
         if (var3 > var1.method59()) {
            var3 = var1.method59();
            var2 = var3 * var4 / var5;
         }
      }

      float var6 = LcuiScreen.method135((var1.method58() - var2) / 2.0F);
      return (Bridge.method20().getX() - var6) / var2 * this.field11.getWidth();
   }

   public double method16() {
      RewindHandlers var1 = ((Nameplate4)this.field8.get()).method6();
      float var2 = var1.method58();
      float var3 = var1.method59();
      int var4 = this.field11.getWidth();
      int var5 = this.field11.getHeight();
      if (var2 > var3) {
         var2 = var3 * var4 / var5;
         if (var2 > var1.method58()) {
            var2 = var1.method58();
            var3 = var2 * var5 / var4;
         }
      } else {
         var3 = var2 * var5 / var4;
         if (var3 > var1.method59()) {
            var3 = var1.method59();
         }
      }

      float var6 = LcuiScreen.method135((var1.method59() - var3) / 2.0F);
      RewindHandlers3Impl8 var7 = var1.method48();
      double var8;
      if (!Bridge.getMinecraftVersion().method19()) {
         var8 = var7.method27() - Bridge.method20().getY();
      } else {
         var8 = Bridge.method20().getY();
      }

      return (var8 - var6) / var3 * this.field11.getHeight();
   }

   private void method4(EventMouseButtonLegacy var1) {
      this.field11.method14(var1.method2(), var1.method4() == InputActionLegacy.DOWN ? 1 : 0, var1.method3(), new Data5(this.method15(), this.method16()));
   }

   private void method5(KeybindEvent var1) {
   }

   public void method17() {
      DriverViewportLegacy.method47().put(field10.method55().method13(), false);
   }

   public void method7(AbstractRenderContext var1) {
      RewindHandlers var2 = ((Nameplate4)this.field8.get()).method6();
      if (var2.method44()) {
         field10.method4("rewindEffects");
         if (field10.method57().get() && field10.method63() != DriverRouteRegistryLegacy.field11 && this.field12 + 1000L < System.currentTimeMillis()) {
            field10.method16(DriverRouteRegistryLegacy.field11);
            this.field12 = System.currentTimeMillis();
         }

         JsonArray var3 = var2.method37().method13().getOrDefault("effects", new JsonArray());
         if (!var3.isEmpty() || this.field13) {
            this.field13 = !var3.isEmpty();

            while (var2.method57().method25() && field10.method55().method13().getTexture() == -1) {
               DriverViewportLegacy.method52().invokeIteration();
               Thread.yield();
            }
         }

         Browser var4 = field10.method55().method13();
         ThreadModuleDump71 var5 = new ThreadModuleDump71(ThreadModuleDump63.method3());
         if (ThreadModuleDump63.method43()) {
            var1.method27(0, 0, var4.getWidth(), var4.getHeight());
         }

         var1.method8(BridgeType3_3.GL_PROJECTION);
         var1.method36();
         Bridge.method42().bridge$loadIdentity();
         var1.method22(
            0.0,
            (double)var4.getWidth() / var5.getScaleFactor(),
            (double)var4.getHeight() / var5.getScaleFactor(),
            0.0,
            1000.0,
            ThreadModuleDump63.MC_VERSION >= 29 ? 21000.0 : 3000.0
         );
         var1.method8(BridgeType3_3.GL_MODELVIEW);
         var1.method36();
         Bridge.method42().bridge$loadIdentity();
         var1.translate(0.0, 0.0, -2000.0);
         var1.push();
         var1.method19();
         if (ThreadModuleDump63.method43()) {
            var1.method6(false);
         }

         var1.method14();
         var1.method2(BridgeType2_9.GL_ONE, BridgeType2_9.GL_ONE_MINUS_SRC_ALPHA);
         var1.method16();
         var1.method3(DepthComparison.GL_GREATER, 0.01F);
         var1.method12();
         Data5 var6 = new Data5(this.method15(), this.method16());
         this.field11.method14(var6);
         field10.method12(new LegacyGuiGraphicsBridge(var1), var6);
         var1.method2(BridgeType2_9.GL_SRC_ALPHA, BridgeType2_9.GL_ONE_MINUS_SRC_ALPHA);
         var1.method15();
         if (ThreadModuleDump63.method43()) {
            var1.method6(true);
         }

         var1.method18();
         var1.pop();
      }
   }

   public void method8(RewindhandlersNameplate var1) {
      Browser var2 = field10.method55().method13();
      if (var2.getWidth() != var1.getWidth() || var2.getHeight() != var1.getHeight()) {
         var2.triggerResize(var1.getWidth(), var1.getHeight());
         this.field11.method1(var1.getWidth(), var1.getHeight());
      }
   }

   @Generated
   public static DriverViewportLegacy method19() {
      return field10;
   }

   @Generated
   public void method10(Nameplate[] var1) {
      this.field14 = var1;
   }
}
