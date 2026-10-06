package com.moonsworth.lunar.client.render.texture;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.BridgeType2_9;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.slayer.Slayer2;
import com.moonsworth.lunar.bridge.slayer.Slayer4;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.event.ClientEventBus;
import com.moonsworth.lunar.client.event.mixin.nameplate.HudBaseRenderEvent;
import com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHudLegacy;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel;
import com.moonsworth.lunar.client.mod.misc.chat.Chat;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump71;
import lombok.Generated;

public class FramebufferCaptureTask extends FramebufferCapture {
   private static boolean field5 = false;
   private static boolean field6 = false;
   private static long field7 = 0L;
   public static final FramebufferCaptureTask field8 = new FramebufferCaptureTask();

   public static boolean method1() {
      if (ThreadModuleDump63.method4().method40().method85().method19()) {
         return false;
      }

      if (ThreadModuleDump63.MC_VERSION >= 30) {
         return false;
      }

      if (ThreadModuleDump63.method4().method40().method64().method13()) {
         return false;
      }

      if (!(Boolean)Client.method109().method41().method7().method23().get()
         || ThreadModuleDump63.method11() == com.moonsworth.lunar.client.ui.hud.HudEditorScreen.class
         || !Bridge.method22().method1()) {
         return false;
      }

      if (!Bridge.method5().isPresent()) {
         return true;
      }

      Slayer4 var0 = ((Slayer2)Bridge.method5().get()).getConfig();
      return !var0.hasAntiAliasing() && ThreadModuleDump63.method7() != null
         ? !var0.hasShaders() || !ThreadModuleDump63.method7().bridge$isPotionActive(Bridge.method36().method4())
         : false;
   }

   public static boolean method2() {
      Chat var0 = ThreadModuleDump63.method4().method40().method47();
      return var0.isEnabled() && (var0.getScaledWidth_double6() || var0.getScaledHeight_double1());
   }

   @Override
   protected void method3() {
      field7 = 0L;
   }

   @Override
   public void method6(AbstractRenderContext var1, ThreadModuleDump71 var2, Runnable var3, boolean var4, boolean var5) {
      field6 = true;
      if (this == field8 && !method1()) {
         var3.run();
         field6 = false;
      } else {
         var1.method14();
         var1.method18();
         var1.method4(BridgeType2_9.GL_SRC_ALPHA, BridgeType2_9.GL_ONE_MINUS_SRC_ALPHA, BridgeType2_9.GL_ONE, BridgeType2_9.GL_ZERO);
         this.method1(
            Math.max(1, ThreadModuleDump63.method3().bridge$displayWidth()), Math.max(1, ThreadModuleDump63.method3().bridge$displayHeight())
         );
         long var6 = System.currentTimeMillis();
         long var8 = var6 - field7;
         if (this != field8 || var8 < 0L || var8 > 50L) {
            field5 = this == field8;
            if (field5) {
               field7 = var6;
            }

            if (var5) {
               this.field3.bridge$framebufferClear();
            }

            ThreadModuleDump63.method3().method1(this.field3, false);
            var3.run();
            field5 = false;
            ThreadModuleDump63.method3().method1(null, false);
         }

         field6 = false;
         if (var4) {
            this.method9(var1, (float)var2.getScaledWidth_double(), (float)var2.getScaledHeight_double(), true);
         }
      }
   }

   public static void method5(ThreadModuleDump71 var0, AbstractRenderContext var1, MixinHelper_4 var2, float var3) {
      ClientEventBus.method29()
         .method12(HudBaseRenderEvent.Data4.class, () -> new HudBaseRenderEvent.Data4(var1, var2, new MarkerModel.Data4(var0.getScaledWidth_double(), var0.getScaledHeight_double()), true));
      var1.push();
      var1.scale(var3, var3, var3);
      ClientEventBus.method29()
         .method12(EventRenderHudLegacy.Data.class, () -> new EventRenderHudLegacy.Data(var1, var2, new MarkerModel.Data2(var0.getScaledWidth_double() / var3, var0.getScaledHeight_double() / var3), true));
      var1.pop();
   }

   @Generated
   public static boolean method6() {
      return field5;
   }

   @Generated
   public static boolean method7() {
      return field6;
   }
}
