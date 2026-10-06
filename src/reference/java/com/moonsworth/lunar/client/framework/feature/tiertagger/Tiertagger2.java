package com.moonsworth.lunar.client.framework.feature.tiertagger;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import com.moonsworth.lunar.bridge.Bridge2_33;
import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.Itemcounter6Extension;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.mod.render.tiertagger.TierTagger;
import com.moonsworth.lunar.client.util.ThreadModuleDump22;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent.Builder;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.jetbrains.annotations.Nullable;

class Tiertagger2 {
   private final TierTagger field1;
   private final Tiertagger3 field2;

   public void method1(String var1) {
      Tiertagger2_2 var2 = this.field1.field10.getTierProvider();
      String var3 = this.field1.field10.method9().niceName();
      GameProfile var4 = this.method3(var1);
      TierTagger.field8.execute(() -> {
         try {
            GameProfile var5 = var4 != null ? var4 : this.method5(var1, var2);
            if (var5 == null) {
               this.method6(Component.text("[TierTagger] Couldn't find player " + var1 + ".").color(NamedTextColor.RED));
               return;
            }

            Tiertagger4 var6 = var2.method5(var5.getId());
            this.method2(var5.getName(), var6, var3);
         } catch (Exception var7) {
            Slayer.method9(var7, "Tier lookup failed for %s", new Object[]{var1});
            this.method6(Component.text("[TierTagger] Failed to look up " + var1 + ".").color(NamedTextColor.RED));
         }
      });
   }

   private void method2(String var1, @Nullable Tiertagger4 var2, String var3) {
      if (var2 != null && !var2.method4().isEmpty()) {
         this.method6(Component.text("[TierTagger] " + var1 + "'s tiers (" + var3 + "):").color(TextColor.color(5636095)));
         if (var2.method2().isPresent() || var2.method1().isPresent()) {
            Component var4 = Component.text("Overall: ").color(NamedTextColor.GRAY);
            if (var2.method2().isPresent()) {
               var4 = var4.append(Component.text("#" + var2.method2().getAsInt()).color(NamedTextColor.WHITE));
               if (var2.method3().isPresent()) {
                  var4 = var4.append(Component.text(" (" + var2.method3().getAsInt() + " points)").color(NamedTextColor.GRAY));
               }
            }

            if (var2.method1().isPresent()) {
               TiertaggerType var5 = var2.method1().get();
               var4 = var4.append(Component.text(" " + var5.name()).color(TextColor.color(var5.color)));
            }

            this.method6(var4);
         }

         var2.method4()
            .values()
            .stream()
            .sorted(Comparator.comparingInt(var0 -> var0.method2().method1()))
            .forEach(
               var1x -> {
                  Tiertagger_2 var2x = var1x.method1();
                  String var3x = var2x.method1().map(var0 -> var0 + " ").orElse("");
                  Object var4x = ((Builder)((Builder)((Builder)Component.text()
                              .append(Component.text(var3x + var2x.niceName()).color(TextColor.color(var2x.method2()))))
                           .append(Component.text(": ").color(NamedTextColor.GRAY)))
                        .append(this.field2.method1(var1x.method2().tier(), var1x.method2().method3())))
                     .build();
                  if (var1x.method4().orElse(false)) {
                     var4x = var4x.append(Component.text(" (retired)").color(NamedTextColor.GRAY));
                  }

                  this.method6((Component)var4x);
               }
            );
      } else {
         this.method6(Component.text("[TierTagger] " + var1 + " has no tiers on " + var3 + ".").color(NamedTextColor.GRAY));
      }
   }

   @Nullable
   private GameProfile method3(String var1) {
      List var2 = ThreadModuleDump63.method3().bridge$getGuiIngame().bridge$getPlayerInfoList();
      if (var2 != null) {
         for (Bridge2_33 var4 : var2) {
            GameProfile var5 = var4.bridge$getGameProfile();
            if (this.method4(var5, var1)) {
               return var5;
            }
         }
      }

      Itemcounter6Extension var7 = ThreadModuleDump63.method8();
      if (var7 != null) {
         for (Bridge6_10 var9 : var7.bridge$getPlayerEntities()) {
            GameProfile var6 = var9.bridge$getGameProfile();
            if (this.method4(var6, var1)) {
               return var6;
            }
         }
      }

      return null;
   }

   private boolean method4(@Nullable GameProfile var1, String var2) {
      return var1 != null && var1.getId() != null && var2.equalsIgnoreCase(var1.getName()) && !ThreadModuleDump22.method1(var1.getName(), var1.getId(), true);
   }

   @Nullable
   private GameProfile method5(String var1, Tiertagger2_2 var2) {
      Optional var3 = var2.method10("https://api.mojang.com/users/profiles/minecraft/" + var1, false).get();
      if (var3.isEmpty()) {
         throw new Exception("Mojang profile request failed");
      }

      JsonElement var4 = (JsonElement)var3.get();
      if (!var4.isJsonNull() && var4.isJsonObject()) {
         JsonObject var5 = var4.getAsJsonObject();
         if (var5.has("id") && var5.has("name")) {
            UUID var6 = UUID.fromString(
               var5.get("id").getAsString().replaceFirst("(\\p{XDigit}{8})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}{12})", "$1-$2-$3-$4-$5")
            );
            return new GameProfile(var6, var5.get("name").getAsString());
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   private void method6(Component var1) {
      ThreadModuleDump63.method3().bridge$schedule(() -> {
         if (ThreadModuleDump63.method7() != null) {
            ThreadModuleDump63.method7().bridge$addChatMessage(AdventureTextBridge.asBridge(var1));
         }
      });
   }

   @Generated
   public Tiertagger2(TierTagger var1, Tiertagger3 var2) {
      this.field1 = var1;
      this.field2 = var2;
   }
}
