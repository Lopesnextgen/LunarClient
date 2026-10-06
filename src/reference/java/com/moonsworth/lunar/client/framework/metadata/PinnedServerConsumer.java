package com.moonsworth.lunar.client.framework.metadata;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.client.keystrokes.Keystrokes2;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump80;
import com.moonsworth.lunar.config.Config;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;

public class PinnedServerConsumer extends com.moonsworth.lunar.client.framework.metadata.MetadataConsumer {
   @Override
   public void method4(JsonElement var1) {
      JsonArray var2 = var1.getAsJsonArray();
      ArrayList var3 = new ArrayList(var2.size());
      ArrayList var4 = new ArrayList(var2.size());
      Iterator var5 = var2.iterator();

      while (true) {
         JsonObject var7;
         ArrayList var8;
         boolean var12;
         boolean var16;
         boolean var17;
         do {
            if (!var5.hasNext()) {
               if (!var4.isEmpty()) {
                  var4.forEach(ThreadModuleDump63.method4().method59()::method2);
               } else {
                  var3.forEach(ThreadModuleDump63.method4().method59()::method2);
               }

               return;
            }

            JsonElement var6 = (JsonElement)var5.next();
            var7 = var6.getAsJsonObject();
            var8 = new ArrayList();

            for (JsonElement var10 : var7.get("versions").getAsJsonArray()) {
               Optional var11 = Config.get(var10.getAsString());
               if (!var11.isEmpty()) {
                  var8.add((Config)var11.get());
               }
            }

            var16 = false;
            if (var7.has("removable")) {
               var16 = var7.get("removable").getAsBoolean();
            }

            var17 = false;
            if (!var7.has("modpacks")) {
               break;
            }

            JsonArray var18 = var7.get("modpacks").getAsJsonArray();
            if (var18.isEmpty()) {
               break;
            }

            var17 = true;
            var12 = false;

            for (JsonElement var14 : var18) {
               String var15 = var14.getAsString();
               if (Objects.equals(var15, ThreadModuleDump80.modrinthModpackProjectId) || Objects.equals(var15, ThreadModuleDump80.curseforgeModpackModId)) {
                  var12 = true;
                  break;
               }
            }
         } while (!var12);

         Keystrokes2 var19 = new Keystrokes2(
            var7.get("name").getAsString(),
            var7.get("ip").getAsString(),
            var7.get("expirationDate").getAsLong(),
            var8.contains(Bridge.getMinecraftVersion()),
            var16
         );
         if (var17) {
            var4.add(var19);
         } else {
            var3.add(var19);
         }
      }
   }
}
