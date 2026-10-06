package com.moonsworth.lunar.client.driver.bridge;

import com.google.gson.JsonObject;
import com.lunarclient.websocket.hostedworld.v1.JoinHostedWorldRequest.Source;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.coordinates.FogIterator;
import com.moonsworth.lunar.client.network.hostedworld.HostedWorldPlayer;
import com.moonsworth.lunar.client.translation.TranslationManager;
import com.moonsworth.lunar.client.driver.DriverGuiExtension;
import com.moonsworth.lunar.client.driver.DriverOverlayRegistry;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.util.memory.Memory;
import com.moonsworth.lunar.client.network.hostedworld.HostedWorldJoinHandler;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.webosr.javascript.CallbackJS;
import java.util.UUID;
import lombok.Generated;

public class HostedWorldBridge implements DriverGuiExtension, GuiIterator.Extension {
   private static DriverOverlayRegistry field1 = null;

   public HostedWorldBridge() {
   }

   @Override
   public GuiIterator getProvider() {
      return Client.method109().method81().method27();
   }

   @CallbackJS("inviteFriend")
   public static void method2(UUID uuid0) {
      Memory memory1 = Ref.method4().method50().method2(uuid0);
      if (memory1 != null) {
         method7(memory1.method10(), HostedWorldBridge.Type.INVITE);
      }
   }

   @CallbackJS("kick")
   public static void method3(UUID uuid0) {
      method7(uuid0, HostedWorldBridge.Type.KICK);
   }

   @CallbackJS("promote")
   public static void method4(UUID uuid0) {
      FogIterator fogiterator1 = Ref.method4().method81();
      HostedWorldPlayer gui2handler2 = fogiterator1.method25(uuid0);
      if (gui2handler2 != null) {
         method7(uuid0, HostedWorldBridge.Type.PROMOTE);
      }
   }

   @CallbackJS("demote")
   public static void method5(UUID uuid0) {
      FogIterator fogiterator1 = Ref.method4().method81();
      HostedWorldPlayer gui2handler2 = fogiterator1.method25(uuid0);
      if (gui2handler2 != null) {
         method7(uuid0, HostedWorldBridge.Type.DEMOTE);
      }
   }

   @CallbackJS("onModalCallback")
   public static void method6(HostedWorldBridge.Type type0, UUID uuid1) {
      switch (type0) {
         case KICK:
            Ref.method3()
               .bridge$getIntegratedServer()
               .bridge$getPlayers()
               .stream()
               .filter(arg1x -> arg1x.bridge$getUniqueID().equals(uuid1))
               .findFirst()
               .ifPresent(arg0x -> arg0x.bridge$kick("You have been kicked from this world."));
            break;
         case DEMOTE:
            Ref.method4().method81().method24(uuid1);
            break;
         case PROMOTE:
            Ref.method4().method81().method23(uuid1);
            break;
         case INVITE:
            FogIterator fogiterator3 = Ref.method4().method81();
            fogiterator3.method23(uuid1);
            fogiterator3.method9(fogiterator3.method28().method1());
            break;
         case JOINING:
            Memory memory2 = Ref.method4().method50().method2(uuid1);
            if (memory2 != null) {
               HostedWorldJoinHandler.method1(memory2, Source.SOURCE_FRIEND_LIST_INLINE);
            }
      }

      if (field1 != null) {
         DriverViewportLegacy.method50().method19(field1);
      }
   }

   private static void method7(UUID uuid0, HostedWorldBridge.Type type1) {
      TranslationManager foghandler282 = Client.method109().method67();
      if (type1 == HostedWorldBridge.Type.INVITE) {
         Memory memory3 = Ref.method4().method50().method2(uuid0);
         if (memory3 != null && Ref.method7() != null) {
            String text4 = foghandler282.method2("gui.hostedWorldInvite", "title", new Object[]{Ref.method7().bridge$getName(), memory3.method11()});
            String text5 = foghandler282.method2("gui.hostedWorldInvite", "lineOne", new Object[]{Ref.method7().bridge$getName(), memory3.method11()});
            method8(memory3.method10(), memory3.method11(), type1, text4, text5);
         }
      } else if (type1 == HostedWorldBridge.Type.JOINING) {
         Memory memory7 = Ref.method4().method50().method2(uuid0);
         if (memory7 != null) {
            String text9 = foghandler282.method2("gui.hostedWorldJoinPrompt", "title", new Object[]{memory7.method11()});
            String text11 = foghandler282.method2("gui.hostedWorldJoinPrompt", "lineOne", new Object[]{memory7.method11()});
            method8(memory7.method10(), memory7.method11(), type1, text9, text11);
         }
      } else {
         FogIterator fogiterator8 = Ref.method4().method81();
         HostedWorldPlayer gui2handler10 = fogiterator8.method25(uuid0);
         if (gui2handler10 != null) {
            switch (type1) {
               case KICK:
                  if (fogiterator8.method20()
                     && Ref.method7() != null
                     && !uuid0.equals(Ref.method7().bridge$getUniqueID())
                     && gui2handler10.method2()) {
                     String text14 = foghandler282.method2(
                        "gui.hostedWorldKick", "title", new Object[]{Ref.method7().bridge$getName(), gui2handler10.getUsername()}
                     );
                     String text16 = foghandler282.method2(
                        "gui.hostedWorldKick", "lineOne", new Object[]{Ref.method7().bridge$getName(), gui2handler10.getUsername()}
                     );
                     method8(gui2handler10.method5(), gui2handler10.getUsername(), type1, text14, text16);
                  }
                  break;
               case DEMOTE:
                  if (fogiterator8.method20() && Ref.method7() != null && !uuid0.equals(Ref.method7().bridge$getUniqueID())) {
                     String text13 = foghandler282.method2(
                        "gui.hostedWorldDemote", "title", new Object[]{Ref.method7().bridge$getName(), gui2handler10.getUsername()}
                     );
                     String text15 = foghandler282.method2(
                        "gui.hostedWorldDemote", "lineOne", new Object[]{Ref.method7().bridge$getName(), gui2handler10.getUsername()}
                     );
                     method8(gui2handler10.method5(), gui2handler10.getUsername(), type1, text13, text15);
                  }
                  break;
               case PROMOTE:
                  if (fogiterator8.method20() && Ref.method7() != null && !uuid0.equals(Ref.method7().bridge$getUniqueID())) {
                     String text12 = foghandler282.method2(
                        "gui.hostedWorldPromote", "title", new Object[]{Ref.method7().bridge$getName(), gui2handler10.getUsername()}
                     );
                     String text6 = foghandler282.method2(
                        "gui.hostedWorldPromote", "lineOne", new Object[]{Ref.method7().bridge$getName(), gui2handler10.getUsername()}
                     );
                     method8(gui2handler10.method5(), gui2handler10.getUsername(), type1, text12, text6);
                  }
            }
         }
      }
   }

   private static void method8(UUID uuid0, String text1, HostedWorldBridge.Type type2, String text3, String text4) {
      field1 = DriverViewportLegacy.method50().method64();
      JsonObject json5 = new JsonObject();
      json5.addProperty("uuid", uuid0.toString());
      json5.addProperty("username", text1);
      json5.addProperty("title", text3);
      json5.addProperty("description", text4);
      json5.addProperty("button", type2.id);
      DriverViewportLegacy.method50().method20(DriverOverlayRegistry.field3, json5);
   }

   public enum Type {
      JOINING("joining"),
      KICK("kick"),
      PROMOTE("promote"),
      DEMOTE("demote"),
      INVITE("invite"),
      CANCEL("cancel");

      private final String id;

      @Override
      public String toString() {
         return this.id;
      }

      @Generated
      Type(String text3) {
         this.id = text3;
      }
   }
}
