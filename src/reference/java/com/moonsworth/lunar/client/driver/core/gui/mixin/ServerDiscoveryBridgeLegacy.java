package com.moonsworth.lunar.client.driver.core.gui.mixin;

import com.google.gson.JsonElement;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.network.server.ServerMapping;
import com.moonsworth.lunar.client.network.server.RecommendedServer;
import com.moonsworth.lunar.client.network.server.SocialLinks;
import com.moonsworth.lunar.client.network.server.DiscoverySection;
import com.moonsworth.lunar.client.driver.DriverGuiExtensionLegacy;
import com.moonsworth.lunar.client.driver.DriverRouteRegistryLegacy;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.webosr.javascript.CallbackJS;
import com.moonsworth.webosr.wrappers.PromiseJS;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public class ServerDiscoveryBridgeLegacy implements DriverGuiExtensionLegacy {
   @CallbackJS("discoverServers")
   public static void method1(PromiseJS<List<DiscoverySection>> var0) {
      Client.method109().method79().method4(var0::resolve);
   }

   @CallbackJS("searchServers")
   public static void method2(PromiseJS<List<RecommendedServer>> var0, String var1) {
      Client.method109().method79().method5(var1, var0::resolve);
   }

   @CallbackJS("loadAutocompleteSuggestions")
   public static void method3(PromiseJS<List<ServerMapping>> var0) {
      Client.method109().method79().method6(var0::resolve);
   }

   @CallbackJS("loadServerModal")
   public static void method4(PromiseJS<SocialLinks> var0, String var1) {
      Client.method109().method79().method7(var1, var0::resolve, () -> var0.reject("Missing server modal details."));
   }

   @CallbackJS("joinServer")
   public static void method5(String var0, String var1, String text, String text2, String text3) {
      Client.method109().method79().method9(var0, var1, text, text2, text3);
   }

   @CallbackJS("saveServer")
   public static void method6(String var0, String var1) {
      Client.method109().method79().method10(var0, var1);
   }

   @CallbackJS("openDiscovery")
   public static void method7() {
      DriverViewportLegacy.method50().method16(DriverRouteRegistryLegacy.field6);
   }

   @CallbackJS("trackSectionNotInterested")
   public static void method8(PromiseJS<Void> var0, String var1) {
      Client.method109().method79().method11(var1, var0::resolve);
   }

   @Override
   public JsonElement provide() {
      return null;
   }

   @Nullable
   @Override
   public JsonElement method128() {
      return null;
   }
}
