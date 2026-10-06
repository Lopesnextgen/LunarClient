package com.moonsworth.lunar.client.coordinates.mixin;

import com.lunarclient.common.v1.UuidAndUsername;
import com.lunarclient.websocket.hostedworld.v1.Joinability;
import com.lunarclient.websocket.hostedworld.v1.ListHostedWorldsResponse.HostedWorld;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge20Extension;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.cosmetics.CosmeticManager;
import com.moonsworth.lunar.client.util.net.ServiceEndpoints;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump66;
import com.moonsworth.lunar.config.Config;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.jetbrains.annotations.NotNull;

public class Coordinates {
   private static final ResourceLocationBridge field1 = ThreadModuleDump63.MC_VERSION >= 19
      ? ResourceLocationBridge.create("textures/gui/sprites/server_list/join.png")
      : ResourceLocationBridge.create("textures/gui/server_selection.png");
   private static final ResourceLocationBridge field2 = ResourceLocationBridge.create("textures/gui/sprites/server_list/join_highlighted.png");
   private static final Map<UUID, ResourceLocationBridge> field3 = new HashMap<>();

   @NotNull
   public static ResourceLocationBridge method1(UUID var0) {
      if (!field3.containsKey(var0)) {
         ResourceLocationBridge var1 = ResourceLocationBridge.create("lunar", "hostedworlds/" + var0 + ".png");
         Bridge20Extension var2 = Bridge.method8()
            .method14(null, ServiceEndpoints.method3() + "/face/" + var0.toString(), var1, ResourceLocationBridge.create("lunar", "steve-bust.png"));
         ThreadModuleDump63.method3().bridge$getTextureManager().bridge$loadTexture(var1, var2);
         field3.put(var0, var1);
         return var1;
      } else {
         return field3.get(var0);
      }
   }

   public static void method2(HostedWorld var0, MixinHelper_4 var1, int var2, int value, int value2, int value3, int value4, int value5, boolean flag) {
      UUID var9 = ThreadModuleDump66.method1(var0.getHost().getUuid());
      var1.method10(ThreadModuleDump63.method10(), Component.text(var0.getHost().getUsername() + "'s world"), var2 + 32 + 3, value + 1, 16777215, false);
      if (var0.getJoinability() == Joinability.JOINABILITY_ALLOWED) {
         TextComponent var10 = (TextComponent)((TextComponent)((TextComponent)((TextComponent)Component.text(var0.getOnlinePlayers())
                     .color(NamedTextColor.GRAY))
                  .append(Component.text("/").color(NamedTextColor.DARK_GRAY)))
               .append(Component.text(var0.getMaxPlayers())))
            .color(NamedTextColor.GRAY);
         float var11 = ThreadModuleDump63.method10().bridge$getStringWidth(var10);
         var1.method10(ThreadModuleDump63.method10(), var10, (int)(var2 + value2 - var11 - 6.0F), value + 1, 8421504, false);
      } else {
         TextComponent var20;
         if (var0.getJoinability() == Joinability.JOINABILITY_WORLD_FULL) {
            var20 = (TextComponent)Component.text(ThreadModuleDump63.method4().method67().method2("gui.components", "worldFull"))
               .color(NamedTextColor.DARK_RED);
         } else if (var0.getJoinability() == Joinability.JOINABILITY_INCOMPATIBLE_MINECRAFT_VERSION) {
            String var22 = var0.getMinecraftVersion().getEnum();
            String var12 = Config.get(var22).map(Config::getDisplayName).orElse("unknown");
            var20 = (TextComponent)Component.text(ThreadModuleDump63.method4().method67().method2("gui.components", "worldRequiresVersionShort", var12))
               .color(NamedTextColor.DARK_RED);
         } else {
            var20 = (TextComponent)Component.text(ThreadModuleDump63.method4().method67().method2("gui.components", "worldUnrecognizedError"))
               .color(NamedTextColor.DARK_RED);
         }

         var1.method10(
            ThreadModuleDump63.method10(),
            var20,
            (int)(var2 + value2 - ThreadModuleDump63.method10().bridge$getStringWidth(var20) - 6.0F),
            value + 1,
            8421504,
            false
         );
      }

      var1.method24(method1(var9), var2, value, 32, 32, -1);
      int var21 = var2 + 36;

      for (int var23 = 0; var23 < Math.min(5, var0.getSamplePlayersList().size()); var23++) {
         UuidAndUsername var26 = (UuidAndUsername)var0.getSamplePlayersList().get(var23);
         var1.method24(method1(ThreadModuleDump66.method1(var26.getUuid())), var21, value + 12, 10, 10, -1);
         var21 += 13;
      }

      if (var0.getOnlinePlayers() > 5) {
         var1.method18(ThreadModuleDump63.method10(), "+" + (var0.getOnlinePlayers() - 5), var21, value + 13, 8421504, false);
      }

      var1.push();
      int var24;
      if (var0.hasLogoColor()) {
         var24 = var0.getLogoColor().getColor() | 0xFF000000;
      } else {
         var24 = -1;
      }

      float var27 = var2 - 18;
      float var13 = LcuiScreen.method135(value + value3 / 2.0F - 6.0F);
      LcuiScreen.method31(var1, CosmeticManager.field42, var27, var13, 12.0F, 12.0F, var24);
      if (var0.getLunarPlusBoost()) {
         if (var0.hasPlusColor() && var0.getPlusColor().getColor() > 0) {
            var24 = var0.getPlusColor().getColor() | 0xFF000000;
         } else {
            var24 = -1;
         }

         LcuiScreen.method31(var1, CosmeticManager.field37, var27 + 7.5F, var13 + 2.0F, 4.5F, 4.5F, var24);
      }

      var1.pop();
      int var14 = value4 - var2;
      int var15 = value5 - value;
      if (var0.getLunarPlusBoost() && var14 >= -20 && var14 <= -4 && var15 >= value3 / 2 - 8 && var15 <= value3 / 2 + 8) {
         String var16 = ThreadModuleDump63.method4().method67().method2("gui.components", "hostedWorldLunarPlusBoost");
         float var17 = FontRegistry.method13().method4(var16) + 8.0F;
         float var18 = value4 - 8 - var17;
         float var19 = value5 - 2;
         if (var18 < 0.0F) {
            var18 = value4 + 8;
         }

         LcuiScreen.method117(var1, var18, var19, var17, FontRegistry.method8().getHeight() * 2 + 8, 5.0F, -805306368);
         FontRegistry.method13().method17(var1, var16, var18 + 4.0F, var19 + 2.0F, -268435457, false);
      }

      if (flag && var0.getJoinability() == Joinability.JOINABILITY_ALLOWED && !Bridge.getMinecraftVersion().equals(Config.field1)) {
         LcuiScreen.method66(var1, var2, value, var2 + 32, value + 32, -1601138544);
         if (var14 > 10 && var14 < 26) {
            if (ThreadModuleDump63.MC_VERSION >= 19) {
               var1.method24(field2, var2, value, 32, 32, -1);
            } else if (ThreadModuleDump63.MC_VERSION >= 17) {
               var1.method25(field1, var2, value, 0.0F, 32.0F, 32.0F, 32.0F, 256.0F, 256.0F, -1);
            } else {
               LcuiScreen.method46(var1, field1, var2 - 6, value, 0.0F, 32.0F, 32.0F, 32.0F, 256.0F, 256.0F, 16777215);
            }
         } else if (ThreadModuleDump63.MC_VERSION >= 19) {
            var1.method24(field1, var2, value, 32, 32, -1);
         } else if (ThreadModuleDump63.MC_VERSION >= 17) {
            var1.method25(field1, var2, value, 0.0F, 0.0F, 32.0F, 32.0F, 256.0F, 256.0F, -1);
         } else {
            LcuiScreen.method46(var1, field1, var2 - 6, value, 0.0F, 0.0F, 32.0F, 32.0F, 256.0F, 256.0F, 16777215);
         }
      }
   }
}
