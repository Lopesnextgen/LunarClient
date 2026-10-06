package com.moonsworth.lunar.client.driver.bridge;

import com.google.gson.JsonElement;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.audio.music.StyngrSong;
import com.moonsworth.lunar.client.audio.music.JamManager;
import com.moonsworth.lunar.client.cosmetics.emote.EmoteManager;
import com.moonsworth.lunar.client.cosmetics.emote.EmoteManager.Data;
import com.moonsworth.lunar.client.driver.DriverGuiExtension;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.webosr.javascript.CallbackJS;

public class StyngrBridge implements DriverGuiExtension, GuiIterator.Extension {
   private static Data field1 = null;

   public StyngrBridge() {
   }

   @Override
   public GuiIterator getProvider() {
      return Client.method109().method73().method15();
   }

   @Override
   public JsonElement provide() {
      Client.method109().method73().method11();
      return this.RIROCIRRICRRHOOICOROOCHIOOIHHR();
   }

   @CallbackJS("addJam")
   public static void method2(Integer number0, int number1) {
      EmoteManager holograms142 = Ref.method4().method45();
      holograms142.method20(number0).ifPresent(arg2x -> {
         arg2x.method2(number1);
         holograms142.method22();
         holograms142.method21();
         holograms142.method5();
      });
   }

   @CallbackJS("removeJam")
   public static void method3(Integer number0) {
      EmoteManager holograms141 = Ref.method4().method45();
      holograms141.method20(number0).ifPresent(arg1x -> {
         arg1x.method2(0);
         holograms141.method22();
         holograms141.method21();
         holograms141.method5();
      });
   }

   @CallbackJS("previewJam")
   public static void method4(Integer number0) {
      Ref.method4()
         .method73()
         .method13()
         .stream()
         .filter(arg1 -> arg1.getJamId() == number0)
         .findFirst()
         .ifPresent(
            arg0x -> {
               StyngrSong chest1 = (StyngrSong)JamManager.method12().get(arg0x.getJamId());
               if (chest1 != null) {
                  Ref.method4().method69().method3("Previewing \"" + chest1.getName() + "\"");
                  if (field1 != null) {
                     try {
                        Ref.method3().bridge$getSoundHandler().bridge$destroySound(field1.getSource());
                        field1 = null;
                     } catch (Exception exception3) {
                        exception3.printStackTrace();
                     }
                  }

                  Ref.method4()
                     .method73()
                     .method3(
                        chest1.method1().toString(),
                        arg1x -> Ref.method3()
                           .bridge$submit(
                              () -> {
                                 Object obj2 = Ref.method3()
                                    .bridge$getSoundHandler()
                                    .method2(arg1x, (Float)Ref.method4().method41().method6().method73().get(), false);
                                 if (obj2 != null) {
                                    field1 = new Data(System.currentTimeMillis(), obj2, arg1x, chest1.getId());
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
            Ref.method3().bridge$getSoundHandler().bridge$destroySound(field1.getSource());
            field1 = null;
         } catch (Exception exception1) {
            exception1.printStackTrace();
         }
      }
   }
}
