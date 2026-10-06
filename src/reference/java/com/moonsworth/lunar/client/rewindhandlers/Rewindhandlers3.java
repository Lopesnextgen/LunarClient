package com.moonsworth.lunar.client.rewindhandlers;

import com.google.gson.JsonParseException;
import com.lunarclient.apollo.common.ApolloComponent;
import com.lunarclient.apollo.common.icon.CustomModelData;
import com.lunarclient.apollo.common.icon.ItemStackIcon;
import com.lunarclient.apollo.common.profile.Profile;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.gui.notification.NotificationType;
import com.moonsworth.lunar.client.util.ThreadModuleDump58;
import com.moonsworth.lunar.client.util.ThreadModuleDump61;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump89;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Locale;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.Nullable;

public final class Rewindhandlers3 {
   private static final int field1 = 256;

   public static void method1(Object var0) {
      Slayer.method4("Apollo", "Received invalid item stack from the server.");
      Slayer.method4("Apollo", "Item: " + var0);
      ThreadModuleDump63.method4().method69().method6(NotificationType.WARNING, "Apollo Item Stack Icon Error", "Received an unknown item. Check logs for more info.");
   }

   @Nullable
   public static ItemStackBridge method2(ItemStackIcon var0) {
      String var1 = var0.getItemName();
      ItemStackBridge var2 = var1 != null && !var1.isEmpty() ? ThreadModuleDump89.getItemStackByName(var1.toLowerCase()) : ThreadModuleDump89.getItemStackById(var0.getItemId());
      if (var2 != null) {
         int var3 = var0.getCustomModelData();
         CustomModelData var4 = var0.getCustomModelDataObject();
         if (var4 != null) {
            var2.bridge$setCustomModelData(var4.getFloats(), var4.getFlags(), var4.getStrings(), var4.getColors());
         } else if (var3 != 0) {
            var2.bridge$setCustomModelData(List.of(), List.of(), List.of(), List.of(var3));
         }

         Profile var5 = var0.getProfile();
         if (var5 != null && var2.bridge$getItem().bridge$isItemSkull()) {
            var2.bridge$setProfile(var5.getId(), var5.getTexture(), var5.getSignature());
         }

         String var6 = var0.getPotion();
         if (var6 != null && !var6.isEmpty()) {
            try {
               Bridge.method36().method18(var2, var6);
            } catch (Exception var8) {
               Slayer.method4("Apollo", "Ignored invalid potion '" + var6 + "': " + var8.getMessage());
            }
         }

         return var2;
      } else {
         method1(var1 != null && !var1.isEmpty() ? var1.toLowerCase(Locale.ENGLISH) : var0.getItemId());
         return null;
      }
   }

   public static void method3(Exception var0, String var1) {
      Slayer.method4("Apollo", "Received invalid resource location path from the server.");
      Slayer.method4("Apollo", "Location: " + var1);
      if (var0 != null) {
         Slayer.method4("Apollo", "Parser error message: " + var0.getMessage());
         var0.printStackTrace();
      }

      ThreadModuleDump63.method4()
         .method69()
         .method6(NotificationType.WARNING, "Apollo Resource Path Error", "Received an invalid resource location path. Check logs for more info.");
   }

   public static Component method4(String var0) {
      try {
         return ApolloComponent.fromJson(var0);
      } catch (JsonParseException var2) {
         method5(var2, var0);
         return null;
      }
   }

   public static void method5(Exception var0, String var1) {
      Slayer.method4("Apollo", "Received invalid component packet from the server.");
      Slayer.method4("Apollo", "Payload: " + var1);
      Slayer.method4("Apollo", "Parser error message: " + var0.getMessage());
      var0.printStackTrace();
      ThreadModuleDump63.method4().method69().method6(NotificationType.WARNING, "Apollo Component Error", "Received an invalid Component. Check logs for more info.");
   }

   public static void method6(Exception var0, String var1) {
      Slayer.method4("Apollo", "Received invalid Apollo packet from the server.");
      Slayer.method4("Apollo", "Payload: " + var1);
      Slayer.method4("Apollo", "Parser error message: " + var0.getMessage());
      var0.printStackTrace();
      ThreadModuleDump63.method4().method69().method6(NotificationType.WARNING, "Apollo Error", "Received an invalid Apollo packet. Check logs for more info.");
   }

   public static String method7(String var0) {
      StringBuilder var1 = new StringBuilder(Math.min(var0.length(), 256));

      for (int var2 = 0; var2 < var0.length() && var1.length() < 256; var2++) {
         char var3 = var0.charAt(var2);
         if (ThreadModuleDump58.method1(var3)) {
            var1.append(var3);
         }
      }

      return var1.toString();
   }

   public static URI method8(String var0) {
      try {
         return ThreadModuleDump61.method4(var0);
      } catch (URISyntaxException var2) {
         method9(var2, var0);
         return null;
      }
   }

   public static void method9(Exception var0, String var1) {
      Slayer.method4("Apollo", "Received invalid url from the server.");
      Slayer.method4("Apollo", "URL: " + var1);
      Slayer.method4("Apollo", "Parser error message: " + var0.getMessage());
      var0.printStackTrace();
      ThreadModuleDump63.method4().method69().method6(NotificationType.WARNING, "Apollo URL Error", "Received an invalid URL. Check logs for more info.");
   }

   @Generated
   private Rewindhandlers3() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
