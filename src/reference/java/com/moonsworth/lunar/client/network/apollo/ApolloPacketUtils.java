package com.moonsworth.lunar.client.network.apollo;

import com.google.gson.JsonParseException;
import com.lunarclient.apollo.common.ApolloComponent;
import com.lunarclient.apollo.common.icon.CustomModelData;
import com.lunarclient.apollo.common.icon.ItemStackIcon;
import com.lunarclient.apollo.common.profile.Profile;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.ui.notification.NotificationType;
import com.moonsworth.lunar.client.util.text.TextSanitizer;
import com.moonsworth.lunar.client.util.net.BrowserUtils;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.game.ItemTypeLookup;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Locale;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.Nullable;

public final class ApolloPacketUtils {
   private static final int field1 = 256;

   public static void method1(Object obj0) {
      LunarLogger.method4("Apollo", "Received invalid item stack from the server.", new Object[0]);
      LunarLogger.method4("Apollo", "Item: " + obj0, new Object[0]);
      Ref.method4().method69().method6(NotificationType.WARNING, "Apollo Item Stack Icon Error", "Received an unknown item. Check logs for more info.");
   }

   @Nullable
   public static ItemStackBridge method2(ItemStackIcon itemstackicon0) {
      String text1 = itemstackicon0.getItemName();
      ItemStackBridge bridgeextension_42 = text1 != null && !text1.isEmpty() ? ItemTypeLookup.method1(text1.toLowerCase()) : ItemTypeLookup.method2(itemstackicon0.getItemId());
      if (bridgeextension_42 != null) {
         int number3 = itemstackicon0.getCustomModelData();
         CustomModelData custommodeldata4 = itemstackicon0.getCustomModelDataObject();
         if (custommodeldata4 != null) {
            bridgeextension_42.bridge$setCustomModelData(custommodeldata4.getFloats(), custommodeldata4.getFlags(), custommodeldata4.getStrings(), custommodeldata4.getColors());
         } else if (number3 != 0) {
            bridgeextension_42.bridge$setCustomModelData(List.of(), List.of(), List.of(), List.of(number3));
         }

         Profile profile5 = itemstackicon0.getProfile();
         if (profile5 != null && bridgeextension_42.bridge$getItem().bridge$isItemSkull()) {
            bridgeextension_42.bridge$setProfile(profile5.getId(), profile5.getTexture(), profile5.getSignature());
         }

         String text6 = itemstackicon0.getPotion();
         if (text6 != null && !text6.isEmpty()) {
            try {
               Bridge.method36().method18(bridgeextension_42, text6);
            } catch (Exception exception8) {
               LunarLogger.method4("Apollo", "Ignored invalid potion '" + text6 + "': " + exception8.getMessage(), new Object[0]);
            }
         }

         return bridgeextension_42;
      } else {
         method1(text1 != null && !text1.isEmpty() ? text1.toLowerCase(Locale.ENGLISH) : itemstackicon0.getItemId());
         return null;
      }
   }

   public static void method3(Exception exception0, String text1) {
      LunarLogger.method4("Apollo", "Received invalid resource location path from the server.", new Object[0]);
      LunarLogger.method4("Apollo", "Location: " + text1, new Object[0]);
      if (exception0 != null) {
         LunarLogger.method4("Apollo", "Parser error message: " + exception0.getMessage(), new Object[0]);
         exception0.printStackTrace();
      }

      Ref.method4()
         .method69()
         .method6(NotificationType.WARNING, "Apollo Resource Path Error", "Received an invalid resource location path. Check logs for more info.");
   }

   public static Component method4(String text0) {
      try {
         return ApolloComponent.fromJson(text0);
      } catch (JsonParseException jsonparseexception2) {
         method5(jsonparseexception2, text0);
         return null;
      }
   }

   public static void method5(Exception exception0, String text1) {
      LunarLogger.method4("Apollo", "Received invalid component packet from the server.", new Object[0]);
      LunarLogger.method4("Apollo", "Payload: " + text1, new Object[0]);
      LunarLogger.method4("Apollo", "Parser error message: " + exception0.getMessage(), new Object[0]);
      exception0.printStackTrace();
      Ref.method4().method69().method6(NotificationType.WARNING, "Apollo Component Error", "Received an invalid Component. Check logs for more info.");
   }

   public static void method6(Exception exception0, String text1) {
      LunarLogger.method4("Apollo", "Received invalid Apollo packet from the server.", new Object[0]);
      LunarLogger.method4("Apollo", "Payload: " + text1, new Object[0]);
      LunarLogger.method4("Apollo", "Parser error message: " + exception0.getMessage(), new Object[0]);
      exception0.printStackTrace();
      Ref.method4().method69().method6(NotificationType.WARNING, "Apollo Error", "Received an invalid Apollo packet. Check logs for more info.");
   }

   public static String method7(String text0) {
      StringBuilder builder1 = new StringBuilder(Math.min(text0.length(), 256));

      for (int index2 = 0; index2 < text0.length() && builder1.length() < 256; index2++) {
         char character3 = text0.charAt(index2);
         if (TextSanitizer.isAllowedCharacter(character3)) {
            builder1.append(character3);
         }
      }

      return builder1.toString();
   }

   public static URI method8(String text0) {
      try {
         return BrowserUtils.method4(text0);
      } catch (URISyntaxException urisyntaxexception2) {
         method9(urisyntaxexception2, text0);
         return null;
      }
   }

   public static void method9(Exception exception0, String text1) {
      LunarLogger.method4("Apollo", "Received invalid url from the server.", new Object[0]);
      LunarLogger.method4("Apollo", "URL: " + text1, new Object[0]);
      LunarLogger.method4("Apollo", "Parser error message: " + exception0.getMessage(), new Object[0]);
      exception0.printStackTrace();
      Ref.method4().method69().method6(NotificationType.WARNING, "Apollo URL Error", "Received an invalid URL. Check logs for more info.");
   }

   @Generated
   private ApolloPacketUtils() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }
}
