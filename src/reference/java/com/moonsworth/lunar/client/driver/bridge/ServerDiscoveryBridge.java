package com.moonsworth.lunar.client.driver.bridge;

import com.google.gson.JsonElement;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.network.server.ServerMapping;
import com.moonsworth.lunar.client.network.server.RecommendedServer;
import com.moonsworth.lunar.client.network.server.SocialLinks;
import com.moonsworth.lunar.client.network.server.DiscoverySection;
import com.moonsworth.lunar.client.driver.DriverGuiExtension;
import com.moonsworth.lunar.client.driver.DriverRouteRegistry;
import com.moonsworth.lunar.client.driver.core.DriverViewportLegacy;
import com.moonsworth.webosr.javascript.CallbackJS;
import com.moonsworth.webosr.wrappers.PromiseJS;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public class ServerDiscoveryBridge implements DriverGuiExtension {
   public ServerDiscoveryBridge() {
   }

   @CallbackJS("discoverServers")
   public static void method1(PromiseJS<List<DiscoverySection>> promisejs0) {
      Client.method109().method79().method4(promisejs0::resolve);
   }

   @CallbackJS("searchServers")
   public static void method2(PromiseJS<List<RecommendedServer>> promisejs0, String text1) {
      Client.method109().method79().method5(text1, promisejs0::resolve);
   }

   @CallbackJS("loadAutocompleteSuggestions")
   public static void method3(PromiseJS<List<ServerMapping>> promisejs0) {
      Client.method109().method79().method6(promisejs0::resolve);
   }

   @CallbackJS("loadServerModal")
   public static void method4(PromiseJS<SocialLinks> promisejs0, String text1) {
      Client.method109().method79().method7(text1, promisejs0::resolve, () -> promisejs0.reject("Missing server modal details."));
   }

   @CallbackJS("joinServer")
   public static void method5(String text0, String text1, String text2, String text3, String text4) {
      Client.method109().method79().method9(text0, text1, text2, text3, text4);
   }

   @CallbackJS("saveServer")
   public static void method6(String text0, String text1) {
      Client.method109().method79().method10(text0, text1);
   }

   @CallbackJS("openDiscovery")
   public static void method7() {
      DriverViewportLegacy.method50().method16(DriverRouteRegistry.field6);
   }

   @CallbackJS("trackSectionNotInterested")
   public static void method8(PromiseJS<Void> promisejs0, String text1) {
      Client.method109().method79().method11(text1, promisejs0::resolve);
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
