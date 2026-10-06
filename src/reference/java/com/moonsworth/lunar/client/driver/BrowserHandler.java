package com.moonsworth.lunar.client.driver;

import com.google.common.collect.ImmutableSet;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.webosr.input.Mouse.Cursor;
import com.moonsworth.webosr.wrappers.Browser;
import com.moonsworth.webosr.wrappers.Browser.Listener;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BrowserHandler implements Listener {
   private static final Set<String> field1 = ImmutableSet.of(
      "lunarclientprod.com",
      "lunarclientdev.com",
      "lunarclientcdn.com",
      "lunarclient.com",
      "moonsworth.com",
      "moonsworth.store",
      new String[]{
         "mymoonsworth.store",
         "mcstats.com",
         "mcstats.cloud",
         "gstatic.com",
         "ingest.sentry.io",
         "googletagmanager.com",
         "googleoptimize.com",
         "fonts.googleapis.com",
         "moonsworthllc.workers.dev",
         "sky.shiiyu.moe",
         "nmsr.nickac.dev",
         "klipy.com",
         "cdn.jsdelivr.net",
         "cdn.discordapp.com",
         "pbs.twimg.com",
         "static-cdn.jtvnw.net",
         "yt3.googleusercontent.com",
         "yt3.ggpht.com"
      }
   );
   private static final Map<Browser, Cursor> field2 = new HashMap<>();

   public void onBeginLoad(Browser var1, long var2, boolean var4, String var5) {
      super.onBeginLoad(var1, var2, var4, var5);
   }

   public void onFinishLoad(Browser var1, long var2, boolean var4, String var5) {
      super.onFinishLoad(var1, var2, var4, var5);
   }

   public void onFailLoading(Browser var1, long var2, boolean var4, String var5, String var6, String var7, int var8) {
      super.onFailLoading(var1, var2, var4, var5, var6, var7, var8);
   }

   public void onWindowObjectReady(Browser var1, long var2, boolean var4, String var5) {
      if (var5.startsWith(com.moonsworth.lunar.client.driver.core.DriverViewportLegacy.getUrl())) {
         com.moonsworth.lunar.client.driver.core.DriverViewportLegacy.method50().method57().set(false);
         com.moonsworth.lunar.client.driver.core.DriverViewportLegacy.method50().method65(false);
      }
   }

   public void onDocumentReady(Browser var1, long var2, boolean var4, String var5) {
      if (var5.startsWith(com.moonsworth.lunar.client.driver.core.DriverViewportLegacy.getUrl())) {
         var1.evalNoResult(
            "    (function() {\n        let eventBus = document.createElement(\"b\"); // Ultralight doesn't like constructing EventTarget directly\n\n        window.lunarInternalAccept = function(name, data) {\n            eventBus.dispatchEvent(new CustomEvent(name, {detail: data}));\n        };\n        window.lunarReady = {READY};\n        window.lunar = new Proxy({\n                subscribe(name, cb) {\n                    eventBus.addEventListener(name, cb);\n                },\n                unsubscribe(name, cb) {\n                    eventBus.removeEventListener(name, cb);\n                }\n            }, {\n                get(obj, prop) {\n                    if (obj.hasOwnProperty(prop)) return obj[prop];\n                    return async (...args) => {\n                        let val = window[`lunarInternalInvoke`](prop, ...args);\n                        if (typeof val === 'symbol') return \"\";\n                        return val;\n                    };\n                }\n            });\n    })();\n"
               .replace("{READY}", ThreadModuleDump63.method4().method37() ? "true" : "false")
         );
         com.moonsworth.lunar.client.driver.core.DriverViewportLegacy.method50().method58().set(ThreadModuleDump63.method4().method37());
         Slayer.method4("WebOSR", "Lunar Context Created");
         this.method1();
      }
   }

   protected void method1() {
      com.moonsworth.lunar.client.driver.core.DriverViewportLegacy.method50().method57().set(true);
   }

   public void onUpdateHistory(Browser var1) {
      super.onUpdateHistory(var1);
   }

   public boolean onNetworkRequest(Browser var1, String var2, String var3, String var4, String var5, String var6, String var7) {
      try {
         if (com.moonsworth.lunar.client.driver.core.DriverViewportLegacy.method48()) {
            return true;
         }

         if (var2.startsWith("file:")) {
            return true;
         }

         String var8 = new URL(var2).getAuthority();
         if (field1.stream().anyMatch(var1x -> var8.equals(var1x) || var8.endsWith("." + var1x))) {
            return true;
         }

         if (com.moonsworth.lunar.client.driver.core.DriverViewportLegacy.isDebug() && var8.equals("localhost:3000")) {
            return true;
         }

         Slayer.method8("WebOSR", "Load filter blocked: (%s)[%s]", var8, var2);
         return false;
      } catch (Exception var9) {
         var9.printStackTrace();
         Slayer.method8("WebOSR", "Load filter error: %s", var2);
         return false;
      }
   }

   public String onEnforcePublicKey(Browser var1, String var2, String var3, String var4, String var5, String var6, String var7) {
      return super.onEnforcePublicKey(var1, var2, var3, var4, var5, var6, var7);
   }

   public void onChangeTitle(Browser var1, String var2) {
      super.onChangeTitle(var1, var2);
   }

   public void onChangeURL(Browser var1, String var2) {
      super.onChangeURL(var1, var2);
   }

   public void onChangeTooltip(Browser var1, String var2) {
      super.onChangeTooltip(var1, var2);
   }

   public void onRequestClose(Browser var1) {
      super.onRequestClose(var1);
   }

   public void onCursorChange(Browser var1, Cursor var2) {
      if (Client.method109().method41().method6().method90().get()) {
         com.moonsworth.lunar.client.driver.core.DriverViewportLegacy var3 = com.moonsworth.lunar.client.driver.core.DriverViewportLegacy.method50();
         boolean var4 = var3.method63().method1() || var3.method64() != null && var3.method64().method1();
         if (var4) {
            field2.put(var1, var2);
         } else {
            field2.put(var1, Cursor.PASS_THROUGH);
         }

         Cursor var5 = field2.values()
            .stream()
            .filter(var1x -> !this.method2(var1x))
            .findFirst()
            .orElse(var4 && ThreadModuleDump63.MC_VERSION <= 5 ? Cursor.POINTER : Cursor.PASS_THROUGH);
         com.moonsworth.lunar.client.driver.core.DriverViewportLegacy.setCursor(var5);
      } else {
         field2.clear();
      }
   }

   private boolean method2(Cursor var1) {
      return var1 == Cursor.PASS_THROUGH || var1 == Cursor.POINTER;
   }
}
