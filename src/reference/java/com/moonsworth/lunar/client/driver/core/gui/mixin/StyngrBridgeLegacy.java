package com.moonsworth.lunar.client.driver.core.gui.mixin;

import com.google.gson.JsonElement;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.audio.music.StyngrSong;
import com.moonsworth.lunar.client.audio.music.JamManager;
import com.moonsworth.lunar.client.cosmetics.emote.EmoteManager;
import com.moonsworth.lunar.client.cosmetics.emote.EmoteManager.Data;
import com.moonsworth.lunar.client.driver.DriverGuiExtensionLegacy;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.webosr.javascript.CallbackJS;

public class StyngrBridgeLegacy implements DriverGuiExtensionLegacy, GuiIterator.Extension {
   private static Data field1 = null;

   @Override
   public GuiIterator getProvider() {
      return Client.method109().method73().method15();
   }

   @Override
   public JsonElement provide() {
      Client.method109().method73().method11();
      return this.provide();
   }

   @CallbackJS("addJam")
   public static void method2(Integer var0, int var1) {
      EmoteManager var2 = ThreadModuleDump63.method4().method45();
      var2.method20(var0).ifPresent(var2x -> {
         var2x.method2(var1);
         var2.method22();
         var2.method21();
         var2.method5();
      });
   }

   @CallbackJS("removeJam")
   public static void method3(Integer var0) {
      EmoteManager var1 = ThreadModuleDump63.method4().method45();
      var1.method20(var0).ifPresent(var1x -> {
         var1x.method2(0);
         var1.method22();
         var1.method21();
         var1.method5();
      });
   }

   @CallbackJS("previewJam")
   public static void method4(Integer var0) {
      ThreadModuleDump63.method4()
         .method73()
         .method13()
         .stream()
         .filter(var1 -> var1.getJamId() == var0)
         .findFirst()
         .ifPresent(
            var0x -> {
               StyngrSong var1 = (StyngrSong)JamManager.method12().get(var0x.getJamId());
               if (var1 != null) {
                  ThreadModuleDump63.method4().method69().method3("Previewing \"" + var1.getName() + "\"");
                  if (field1 != null) {
                     try {
                        ThreadModuleDump63.method3().bridge$getSoundHandler().bridge$destroySound(field1.getSource());
                        field1 = null;
                     } catch (Exception var3) {
                        var3.printStackTrace();
                     }
                  }

                  ThreadModuleDump63.method4()
                     .method73()
                     .method3(
                        var1.method1().toString(),
                        var1x -> ThreadModuleDump63.method3()
                           .bridge$submit(
                              () -> {
                                 Object var2 = ThreadModuleDump63.method3()
                                    .bridge$getSoundHandler()
                                    .method2(var1x, (Float)ThreadModuleDump63.method4().method41().method6().method73().get(), false);
                                 if (var2 != null) {
                                    field1 = new Data(System.currentTimeMillis(), var2, var1x, var1.getId());
                                 }
                              }
                           )
                     );
               }
            }
         );
   }

   @CallbackJS("stopPreview")
   public static void method5() {
      if (field1 != null) {
         try {
            ThreadModuleDump63.method3().bridge$getSoundHandler().bridge$destroySound(field1.getSource());
            field1 = null;
         } catch (Exception var1) {
            var1.printStackTrace();
         }
      }
   }
}
