package com.moonsworth.lunar.client.replay.render;

import com.google.gson.JsonArray;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.GlBlendFactor;
import com.moonsworth.lunar.bridge.GlMatrixMode;
import com.moonsworth.lunar.bridge.DepthFunction;
import com.moonsworth.lunar.bridge.LegacyGuiGraphicsBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.replay.gui.ReplayContext;
import com.moonsworth.lunar.client.replay.export.ExportSettings;
import com.moonsworth.lunar.client.event.input.InputAction;
import com.moonsworth.lunar.client.event.mixin.rewindhandlers.EventKeybind;
import com.moonsworth.lunar.client.event.mixin.rewindhandlers.EventMouseButton;
import com.moonsworth.lunar.client.driver.BrowserHandler;
import com.moonsworth.lunar.client.driver.DriverRouteRegistry;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data5;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.driver.core.MigrationContextLegacy;
import com.moonsworth.lunar.client.driver.core.MigrationContextLegacy.Data2;
import com.moonsworth.lunar.client.driver.nameplate.Nameplate;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandlers;
import com.moonsworth.lunar.client.mod.misc.rewind.RewindHandler;
import com.moonsworth.lunar.client.util.collection.ValueHolder;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.ui.GuiResolution;
import com.moonsworth.webosr.wrappers.Browser;
import lombok.Generated;

public class ReplayDriverHandler extends RewindHandler {
   private static final BrowserHandler field9 = new BrowserHandler() {
      public void onWindowObjectReady(Browser browser1, long number2, boolean flag4, String text5) {
         ReplayDriverHandler.field10.method57().set(false);
      }

      protected void method1() {
         ReplayDriverHandler.field10.method57().set(true);
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

   public ReplayDriverHandler(ValueHolder<ReplayContext> threadmoduledump61) {
      super(threadmoduledump61);
      field10.method3(1920, 1080, 1920, 1080);
      field10.method18(DriverRouteRegistry.field3, true);
      field10.method55().method13().setDeviceScaleFactor(1.0);
      this.field11 = ((Data2)((Data2)MigrationContextLegacy.method9(null).method1(field10.method55().method13()).IHCORIOHOHHOIORHCCOOIIIHOCROOI(1920, 1080))
            .HRHCHRICROCCHOHOROROIRIICHCRHH(0, 0))
         .method5(field10)
         .method8();
      this.handle(EventMouseButton.class, this::method4);
      this.handle(EventKeybind.class, this::method5);
   }

   public boolean method14() {
      double value1 = this.method15();
      double value3 = this.method16();

      for (Nameplate nameplate8 : this.field14) {
         if (nameplate8.x() - 50.0F <= value1 && nameplate8.x() + nameplate8.method1() + 50.0F >= value1 && nameplate8.y() - 50.0F <= value3 && nameplate8.y() + nameplate8.method2() + 50.0F >= value3) {
            return true;
         }
      }

      return false;
   }

   public double method15() {
      RewindHandlers rewindhandlers1 = ((ReplayContext)this.OHCCCCRIOIHOCRCCIHCIHIIHHHHORO.get()).method6();
      float value2 = rewindhandlers1.method58();
      float value3 = rewindhandlers1.method59();
      int number4 = this.field11.getWidth();
      int number5 = this.field11.getHeight();
      if (value2 > value3) {
         value2 = value3 * number4 / number5;
         if (value2 > rewindhandlers1.method58()) {
            value2 = rewindhandlers1.method58();
         }
      } else {
         value3 = value2 * number5 / number4;
         if (value3 > rewindhandlers1.method59()) {
            value3 = rewindhandlers1.method59();
            value2 = value3 * number4 / number5;
         }
      }

      float value6 = LcuiScreen.method135((rewindhandlers1.method58() - value2) / 2.0F);
      return (Bridge.method20().getX() - value6) / value2 * this.field11.getWidth();
   }

   public double method16() {
      RewindHandlers rewindhandlers1 = ((ReplayContext)this.OHCCCCRIOIHOCRCCIHCIHIIHHHHORO.get()).method6();
      float value2 = rewindhandlers1.method58();
      float value3 = rewindhandlers1.method59();
      int number4 = this.field11.getWidth();
      int number5 = this.field11.getHeight();
      if (value2 > value3) {
         value2 = value3 * number4 / number5;
         if (value2 > rewindhandlers1.method58()) {
            value2 = rewindhandlers1.method58();
            value3 = value2 * number5 / number4;
         }
      } else {
         value3 = value2 * number5 / number4;
         if (value3 > rewindhandlers1.method59()) {
            value3 = rewindhandlers1.method59();
         }
      }

      float value6 = LcuiScreen.method135((rewindhandlers1.method59() - value3) / 2.0F);
      ExportTargetHandler rewindhandlers3impl87 = rewindhandlers1.method48();
      double value8;
      if (!Bridge.getMinecraftVersion().method19()) {
         value8 = rewindhandlers3impl87.method27() - Bridge.method20().getY();
      } else {
         value8 = Bridge.method20().getY();
      }

      return (value8 - value6) / value3 * this.field11.getHeight();
   }

   private void method4(EventMouseButton highlightimpl31) {
      this.field11.method14(highlightimpl31.method2(), highlightimpl31.method4() == InputAction.DOWN ? 1 : 0, highlightimpl31.method3(), new Data5(this.method15(), this.method16()));
   }

   private void method5(EventKeybind highlightimpl1) {
   }

   public void method17() {
      DriverViewportLegacy.method47().put(field10.method55().method13(), false);
   }

   public void method7(AbstractRenderContext bridgeextension_91) {
      RewindHandlers rewindhandlers2 = ((ReplayContext)this.OHCCCCRIOIHOCRCCIHCIHIIHHHHORO.get()).method6();
      if (rewindhandlers2.method44()) {
         field10.method4("rewindEffects");
         if (field10.method57().get() && field10.method63() != DriverRouteRegistry.field11 && this.field12 + 1000L < System.currentTimeMillis()) {
            field10.method16(DriverRouteRegistry.field11);
            this.field12 = System.currentTimeMillis();
         }

         JsonArray array3 = rewindhandlers2.method37().IRROHHOHHHCIOICCIORROOHCCRRRRC().getOrDefault("effects", new JsonArray());
         if (!array3.isEmpty() || this.field13) {
            this.field13 = !array3.isEmpty();

            while (rewindhandlers2.method57().method25() && field10.method55().method13().getTexture() == -1) {
               DriverViewportLegacy.method52().invokeIteration();
               Thread.yield();
            }
         }

         Browser browser4 = field10.method55().method13();
         GuiResolution threadmoduledump715 = new GuiResolution(Ref.method3());
         if (Ref.method43()) {
            bridgeextension_91.method27(0, 0, browser4.getWidth(), browser4.getHeight());
         }

         bridgeextension_91.method8(GlMatrixMode.GL_PROJECTION);
         bridgeextension_91.method36();
         Bridge.method42().bridge$loadIdentity();
         bridgeextension_91.method22(
            0.0,
            (double)browser4.getWidth() / threadmoduledump715.method3(),
            (double)browser4.getHeight() / threadmoduledump715.method3(),
            0.0,
            1000.0,
            Ref.MC_VERSION >= 29 ? 21000.0 : 3000.0
         );
         bridgeextension_91.method8(GlMatrixMode.GL_MODELVIEW);
         bridgeextension_91.method36();
         Bridge.method42().bridge$loadIdentity();
         bridgeextension_91.translate(0.0, 0.0, -2000.0);
         bridgeextension_91.push();
         bridgeextension_91.method19();
         if (Ref.method43()) {
            bridgeextension_91.method6(false);
         }

         bridgeextension_91.method14();
         bridgeextension_91.method2(GlBlendFactor.GL_ONE, GlBlendFactor.GL_ONE_MINUS_SRC_ALPHA);
         bridgeextension_91.method16();
         bridgeextension_91.method3(DepthFunction.GL_GREATER, 0.01F);
         bridgeextension_91.method12();
         Data5 data56 = new Data5(this.method15(), this.method16());
         this.field11.method14(data56);
         field10.method12(new LegacyGuiGraphicsBridge(bridgeextension_91), data56);
         bridgeextension_91.method2(GlBlendFactor.GL_SRC_ALPHA, GlBlendFactor.GL_ONE_MINUS_SRC_ALPHA);
         bridgeextension_91.method15();
         if (Ref.method43()) {
            bridgeextension_91.method6(true);
         }

         bridgeextension_91.method18();
         bridgeextension_91.pop();
      }
   }

   public void method8(ExportSettings rewindhandlersnameplate1) {
      Browser browser2 = field10.method55().method13();
      if (browser2.getWidth() != rewindhandlersnameplate1.getWidth() || browser2.getHeight() != rewindhandlersnameplate1.getHeight()) {
         browser2.triggerResize(rewindhandlersnameplate1.getWidth(), rewindhandlersnameplate1.getHeight());
         this.field11.method1(rewindhandlersnameplate1.getWidth(), rewindhandlersnameplate1.getHeight());
      }
   }

   @Generated
   public static DriverViewportLegacy method19() {
      return field10;
   }

   @Generated
   public void method10(Nameplate[] items1) {
      this.field14 = items1;
   }
}
