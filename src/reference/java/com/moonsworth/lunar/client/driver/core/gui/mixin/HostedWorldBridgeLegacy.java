package com.moonsworth.lunar.client.driver.core.gui.mixin;

import com.google.gson.JsonObject;
import com.lunarclient.websocket.hostedworld.v1.JoinHostedWorldRequest.Source;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.coordinates.FogIterator;
import com.moonsworth.lunar.client.coordinates.Gui2Handler;
import com.moonsworth.lunar.client.translation.TranslationManager;
import com.moonsworth.lunar.client.driver.DriverGuiExtensionLegacy;
import com.moonsworth.lunar.client.driver.DriverOverlayRegistryLegacy;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.memory.Memory;
import com.moonsworth.lunar.client.util.Highlight3Task;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.webosr.javascript.CallbackJS;
import java.util.UUID;
import lombok.Generated;

public class HostedWorldBridgeLegacy implements DriverGuiExtensionLegacy, GuiIterator.Extension {
   private static DriverOverlayRegistryLegacy field1 = null;

   @Override
   public GuiIterator getProvider() {
      return Client.method109().method81().method27();
   }

   @CallbackJS("inviteFriend")
   public static void method2(UUID var0) {
      Memory var1 = ThreadModuleDump63.method4().method50().method2(var0);
      if (var1 != null) {
         method7(var1.method10(), HostedWorldBridgeLegacy.Type.INVITE);
      }
   }

   @CallbackJS("kick")
   public static void method3(UUID var0) {
      method7(var0, HostedWorldBridgeLegacy.Type.KICK);
   }

   @CallbackJS("promote")
   public static void method4(UUID var0) {
      FogIterator var1 = ThreadModuleDump63.method4().method81();
      Gui2Handler var2 = var1.method25(var0);
      if (var2 != null) {
         method7(var0, HostedWorldBridgeLegacy.Type.PROMOTE);
      }
   }

   @CallbackJS("demote")
   public static void method5(UUID var0) {
      FogIterator var1 = ThreadModuleDump63.method4().method81();
      Gui2Handler var2 = var1.method25(var0);
      if (var2 != null) {
         method7(var0, HostedWorldBridgeLegacy.Type.DEMOTE);
      }
   }

   @CallbackJS("onModalCallback")
   public static void method6(HostedWorldBridgeLegacy.Type var0, UUID var1) {
      switch (var0) {
         case KICK:
            ThreadModuleDump63.method3()
               .bridge$getIntegratedServer()
               .bridge$getPlayers()
               .stream()
               .filter(var1x -> var1x.bridge$getUniqueID().equals(var1))
               .findFirst()
               .ifPresent(var0x -> var0x.bridge$kick("You have been kicked from this world."));
            break;
         case DEMOTE:
            ThreadModuleDump63.method4().method81().method24(var1);
            break;
         case PROMOTE:
            ThreadModuleDump63.method4().method81().method23(var1);
            break;
         case INVITE:
            FogIterator var3 = ThreadModuleDump63.method4().method81();
            var3.method23(var1);
            var3.method9(var3.method28().method1());
            break;
         case JOINING:
            Memory var2 = ThreadModuleDump63.method4().method50().method2(var1);
            if (var2 != null) {
               Highlight3Task.method1(var2, Source.SOURCE_FRIEND_LIST_INLINE);
            }
      }

      if (field1 != null) {
         DriverViewportLegacy.method50().method19(field1);
      }
   }

   private static void method7(UUID var0, HostedWorldBridgeLegacy.Type var1) {
      TranslationManager var2 = Client.method109().method67();
      if (var1 == HostedWorldBridgeLegacy.Type.INVITE) {
         Memory var3 = ThreadModuleDump63.method4().method50().method2(var0);
         if (var3 != null && ThreadModuleDump63.method7() != null) {
            String var4 = var2.method2("gui.hostedWorldInvite", "title", new Object[]{ThreadModuleDump63.method7().bridge$getName(), var3.method11()});
            String var5 = var2.method2("gui.hostedWorldInvite", "lineOne", new Object[]{ThreadModuleDump63.method7().bridge$getName(), var3.method11()});
            method8(var3.method10(), var3.method11(), var1, var4, var5);
         }
      } else if (var1 == HostedWorldBridgeLegacy.Type.JOINING) {
         Memory var7 = ThreadModuleDump63.method4().method50().method2(var0);
         if (var7 != null) {
            String var9 = var2.method2("gui.hostedWorldJoinPrompt", "title", new Object[]{var7.method11()});
            String var11 = var2.method2("gui.hostedWorldJoinPrompt", "lineOne", new Object[]{var7.method11()});
            method8(var7.method10(), var7.method11(), var1, var9, var11);
         }
      } else {
         FogIterator var8 = ThreadModuleDump63.method4().method81();
         Gui2Handler var10 = var8.method25(var0);
         if (var10 != null) {
            switch (var1) {
               case KICK:
                  if (var8.method20()
                     && ThreadModuleDump63.method7() != null
                     && !var0.equals(ThreadModuleDump63.method7().bridge$getUniqueID())
                     && var10.method2()) {
                     String var14 = var2.method2(
                        "gui.hostedWorldKick", "title", new Object[]{ThreadModuleDump63.method7().bridge$getName(), var10.getUsername()}
                     );
                     String var16 = var2.method2(
                        "gui.hostedWorldKick", "lineOne", new Object[]{ThreadModuleDump63.method7().bridge$getName(), var10.getUsername()}
                     );
                     method8(var10.method5(), var10.getUsername(), var1, var14, var16);
                  }
                  break;
               case DEMOTE:
                  if (var8.method20() && ThreadModuleDump63.method7() != null && !var0.equals(ThreadModuleDump63.method7().bridge$getUniqueID())) {
                     String var13 = var2.method2(
                        "gui.hostedWorldDemote", "title", new Object[]{ThreadModuleDump63.method7().bridge$getName(), var10.getUsername()}
                     );
                     String var15 = var2.method2(
                        "gui.hostedWorldDemote", "lineOne", new Object[]{ThreadModuleDump63.method7().bridge$getName(), var10.getUsername()}
                     );
                     method8(var10.method5(), var10.getUsername(), var1, var13, var15);
                  }
                  break;
               case PROMOTE:
                  if (var8.method20() && ThreadModuleDump63.method7() != null && !var0.equals(ThreadModuleDump63.method7().bridge$getUniqueID())) {
                     String var12 = var2.method2(
                        "gui.hostedWorldPromote", "title", new Object[]{ThreadModuleDump63.method7().bridge$getName(), var10.getUsername()}
                     );
                     String var6 = var2.method2(
                        "gui.hostedWorldPromote", "lineOne", new Object[]{ThreadModuleDump63.method7().bridge$getName(), var10.getUsername()}
                     );
                     method8(var10.method5(), var10.getUsername(), var1, var12, var6);
                  }
            }
         }
      }
   }

   private static void method8(UUID var0, String var1, HostedWorldBridgeLegacy.Type var2, String var3, String var4) {
      field1 = DriverViewportLegacy.method50().method64();
      JsonObject var5 = new JsonObject();
      var5.addProperty("uuid", var0.toString());
      var5.addProperty("username", var1);
      var5.addProperty("title", var3);
      var5.addProperty("description", var4);
      var5.addProperty("button", var2.id);
      DriverViewportLegacy.method50().method20(DriverOverlayRegistryLegacy.field3, var5);
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
      Type(String var3) {
         this.id = var3;
      }
   }
}
