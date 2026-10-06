package com.moonsworth.lunar.client.framework.feature.tiertagger;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import com.moonsworth.lunar.bridge.PlayerInfoBridge;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.WorldBridgeExtension;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.mod.render.tiertagger.TierTagger;
import com.moonsworth.lunar.client.util.game.NpcUtils;
import com.moonsworth.lunar.client.framework.Ref;
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

class TierLookupHandler {
   private final TierTagger field1;
   private final TierBadgeFormatter field2;

   public void method1(String text1) {
      Tiertagger2_2 tiertagger2_22 = this.field1.field10.getTierProvider();
      String text3 = this.field1.field10.method9().niceName();
      GameProfile gameprofile4 = this.method3(text1);
      TierTagger.field8.execute(() -> {
         try {
            GameProfile gameprofile5 = gameprofile4 != null ? gameprofile4 : this.method5(text1, tiertagger2_22);
            if (gameprofile5 == null) {
               this.method6(Component.text("[TierTagger] Couldn't find player " + text1 + ".").color(NamedTextColor.RED));
               return;
            }

            TierPlayerProfile tiertagger46 = tiertagger2_22.method5(gameprofile5.getId());
            this.method2(gameprofile5.getName(), tiertagger46, text3);
         } catch (Exception exception7) {
            LunarLogger.method9(exception7, "Tier lookup failed for %s", new Object[]{text1});
            this.method6(Component.text("[TierTagger] Failed to look up " + text1 + ".").color(NamedTextColor.RED));
         }
      });
   }

   private void method2(String text1, @Nullable TierPlayerProfile tiertagger42, String text3) {
      if (tiertagger42 != null && !tiertagger42.method4().isEmpty()) {
         this.method6(Component.text("[TierTagger] " + text1 + "'s tiers (" + text3 + "):").color(TextColor.color(5636095)));
         if (tiertagger42.method2().isPresent() || tiertagger42.method1().isPresent()) {
            Component component4 = Component.text("Overall: ").color(NamedTextColor.GRAY);
            if (tiertagger42.method2().isPresent()) {
               component4 = component4.append(Component.text("#" + tiertagger42.method2().getAsInt()).color(NamedTextColor.WHITE));
               if (tiertagger42.method3().isPresent()) {
                  component4 = component4.append(Component.text(" (" + tiertagger42.method3().getAsInt() + " points)").color(NamedTextColor.GRAY));
               }
            }

            if (tiertagger42.method1().isPresent()) {
               TiertaggerType tiertaggertype5 = tiertagger42.method1().get();
               component4 = component4.append(Component.text(" " + tiertaggertype5.name()).color(TextColor.color(tiertaggertype5.color)));
            }

            this.method6(component4);
         }

         tiertagger42.method4()
            .values()
            .stream()
            .sorted(Comparator.comparingInt(arg0 -> arg0.method2().method1()))
            .forEach(
               arg1x -> {
                  TierGameMode tiertagger_22x = arg1x.method1();
                  String text3x = tiertagger_22x.method1().map(arg0 -> arg0 + " ").orElse("");
                  Object obj4x = ((Builder)((Builder)((Builder)Component.text()
                              .append(Component.text(text3x + tiertagger_22x.niceName()).color(TextColor.color(tiertagger_22x.method2()))))
                           .append(Component.text(": ").color(NamedTextColor.GRAY)))
                        .append(this.field2.method1(arg1x.method2().tier(), arg1x.method2().method3())))
                     .build();
                  if (arg1x.method4().orElse(false)) {
                     obj4x = obj4x.append(Component.text(" (retired)").color(NamedTextColor.GRAY));
                  }

                  this.method6((Component)obj4x);
               }
            );
      } else {
         this.method6(Component.text("[TierTagger] " + text1 + " has no tiers on " + text3 + ".").color(NamedTextColor.GRAY));
      }
   }

   @Nullable
   private GameProfile method3(String text1) {
      List list2 = Ref.method3().bridge$getGuiIngame().bridge$getPlayerInfoList();
      if (list2 != null) {
         for (PlayerInfoBridge bridge2_334 : list2) {
            GameProfile gameprofile5 = bridge2_334.bridge$getGameProfile();
            if (this.method4(gameprofile5, text1)) {
               return gameprofile5;
            }
         }
      }

      WorldBridgeExtension itemcounter6extension7 = Ref.method8();
      if (itemcounter6extension7 != null) {
         for (Bridge6_10 bridge6_109 : itemcounter6extension7.bridge$getPlayerEntities()) {
            GameProfile gameprofile6 = bridge6_109.bridge$getGameProfile();
            if (this.method4(gameprofile6, text1)) {
               return gameprofile6;
            }
         }
      }

      return null;
   }

   private boolean method4(@Nullable GameProfile gameprofile1, String text2) {
      return gameprofile1 != null && gameprofile1.getId() != null && text2.equalsIgnoreCase(gameprofile1.getName()) && !NpcUtils.method1(gameprofile1.getName(), gameprofile1.getId(), true);
   }

   @Nullable
   private GameProfile method5(String text1, Tiertagger2_2 tiertagger2_22) {
      Optional optional3 = tiertagger2_22.method10("https://api.mojang.com/users/profiles/minecraft/" + text1, false).get();
      if (optional3.isEmpty()) {
         throw new Exception("Mojang profile request failed");
      }

      JsonElement element4 = (JsonElement)optional3.get();
      if (!element4.isJsonNull() && element4.isJsonObject()) {
         JsonObject json5 = element4.getAsJsonObject();
         if (json5.has("id") && json5.has("name")) {
            UUID uuid6 = UUID.fromString(
               json5.get("id").getAsString().replaceFirst("(\\p{XDigit}{8})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}{12})", "$1-$2-$3-$4-$5")
            );
            return new GameProfile(uuid6, json5.get("name").getAsString());
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   private void method6(Component component1) {
      Ref.method3().bridge$schedule(() -> {
         if (Ref.method7() != null) {
            Ref.method7().bridge$addChatMessage(TextBridge.asBridge(component1));
         }
      });
   }

   @Generated
   public TierLookupHandler(TierTagger tiertagger1, TierBadgeFormatter tiertagger32) {
      this.field1 = tiertagger1;
      this.field2 = tiertagger32;
   }
}
