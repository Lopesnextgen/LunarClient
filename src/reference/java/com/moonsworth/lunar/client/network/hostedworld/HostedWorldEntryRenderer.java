package com.moonsworth.lunar.client.network.hostedworld;

import com.lunarclient.common.v1.UuidAndUsername;
import com.lunarclient.websocket.hostedworld.v1.Joinability;
import com.lunarclient.websocket.hostedworld.v1.ListHostedWorldsResponse.HostedWorld;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.ThreadDownloadImageDataBridge;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.cosmetics.CosmeticManager;
import com.moonsworth.lunar.client.util.net.ServiceEndpoints;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.network.apollo.ProtoConverter;
import com.moonsworth.lunar.config.Config;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.jetbrains.annotations.NotNull;

public class HostedWorldEntryRenderer {
   private static final ResourceLocationBridge field1 = Ref.MC_VERSION >= 19
      ? ResourceLocationBridge.create("textures/gui/sprites/server_list/join.png")
      : ResourceLocationBridge.create("textures/gui/server_selection.png");
   private static final ResourceLocationBridge field2 = ResourceLocationBridge.create("textures/gui/sprites/server_list/join_highlighted.png");
   private static final Map<UUID, ResourceLocationBridge> field3 = new HashMap<>();

   public HostedWorldEntryRenderer() {
   }

   @NotNull
   public static ResourceLocationBridge method1(UUID uuid0) {
      if (!field3.containsKey(uuid0)) {
         ResourceLocationBridge horsestats141 = ResourceLocationBridge.create("lunar", "hostedworlds/" + uuid0 + ".png");
         ThreadDownloadImageDataBridge bridge20extension2 = Bridge.method8()
            .method14(null, ServiceEndpoints.method3() + "/face/" + uuid0.toString(), horsestats141, ResourceLocationBridge.create("lunar", "steve-bust.png"));
         Ref.method3().bridge$getTextureManager().bridge$loadTexture(horsestats141, bridge20extension2);
         field3.put(uuid0, horsestats141);
         return horsestats141;
      } else {
         return field3.get(uuid0);
      }
   }

   public static void method2(HostedWorld hostedworld0, MixinHelper_4 mixinhelper_41, int number2, int number3, int number4, int number5, int number6, int number7, boolean flag8) {
      UUID uuid9 = ProtoConverter.method1(hostedworld0.getHost().getUuid());
      mixinhelper_41.method10(Ref.method10(), Component.text(hostedworld0.getHost().getUsername() + "'s world"), number2 + 32 + 3, number3 + 1, 16777215, false);
      if (hostedworld0.getJoinability() == Joinability.JOINABILITY_ALLOWED) {
         TextComponent text10 = (TextComponent)((TextComponent)((TextComponent)((TextComponent)Component.text(hostedworld0.getOnlinePlayers())
                     .color(NamedTextColor.GRAY))
                  .append(Component.text("/").color(NamedTextColor.DARK_GRAY)))
               .append(Component.text(hostedworld0.getMaxPlayers())))
            .color(NamedTextColor.GRAY);
         float value11 = Ref.method10().bridge$getStringWidth(text10);
         mixinhelper_41.method10(Ref.method10(), text10, (int)(number2 + number4 - value11 - 6.0F), number3 + 1, 8421504, false);
      } else {
         TextComponent text20;
         if (hostedworld0.getJoinability() == Joinability.JOINABILITY_WORLD_FULL) {
            text20 = (TextComponent)Component.text(Ref.method4().method67().method2("gui.components", "worldFull"))
               .color(NamedTextColor.DARK_RED);
         } else if (hostedworld0.getJoinability() == Joinability.JOINABILITY_INCOMPATIBLE_MINECRAFT_VERSION) {
            String text22 = hostedworld0.getMinecraftVersion().getEnum();
            String text12 = Config.get(text22).<String>map(Config::getDisplayName).orElse("unknown");
            text20 = (TextComponent)Component.text(Ref.method4().method67().method2("gui.components", "worldRequiresVersionShort", text12))
               .color(NamedTextColor.DARK_RED);
         } else {
            text20 = (TextComponent)Component.text(Ref.method4().method67().method2("gui.components", "worldUnrecognizedError"))
               .color(NamedTextColor.DARK_RED);
         }

         mixinhelper_41.method10(
            Ref.method10(),
            text20,
            (int)(number2 + number4 - Ref.method10().bridge$getStringWidth(text20) - 6.0F),
            number3 + 1,
            8421504,
            false
         );
      }

      mixinhelper_41.method24(method1(uuid9), number2, number3, 32, 32, -1);
      int number21 = number2 + 36;

      for (int index23 = 0; index23 < Math.min(5, hostedworld0.getSamplePlayersList().size()); index23++) {
         UuidAndUsername uuidandusername26 = (UuidAndUsername)hostedworld0.getSamplePlayersList().get(index23);
         mixinhelper_41.method24(method1(ProtoConverter.method1(uuidandusername26.getUuid())), number21, number3 + 12, 10, 10, -1);
         number21 += 13;
      }

      if (hostedworld0.getOnlinePlayers() > 5) {
         mixinhelper_41.method18(Ref.method10(), "+" + (hostedworld0.getOnlinePlayers() - 5), number21, number3 + 13, 8421504, false);
      }

      mixinhelper_41.push();
      int number24;
      if (hostedworld0.hasLogoColor()) {
         number24 = hostedworld0.getLogoColor().getColor() | 0xFF000000;
      } else {
         number24 = -1;
      }

      float value27 = number2 - 18;
      float value13 = LcuiScreen.method135(number3 + number5 / 2.0F - 6.0F);
      LcuiScreen.method31(mixinhelper_41, CosmeticManager.field42, value27, value13, 12.0F, 12.0F, number24);
      if (hostedworld0.getLunarPlusBoost()) {
         if (hostedworld0.hasPlusColor() && hostedworld0.getPlusColor().getColor() > 0) {
            number24 = hostedworld0.getPlusColor().getColor() | 0xFF000000;
         } else {
            number24 = -1;
         }

         LcuiScreen.method31(mixinhelper_41, CosmeticManager.field37, value27 + 7.5F, value13 + 2.0F, 4.5F, 4.5F, number24);
      }

      mixinhelper_41.pop();
      int number14 = number6 - number2;
      int number15 = number7 - number3;
      if (hostedworld0.getLunarPlusBoost() && number14 >= -20 && number14 <= -4 && number15 >= number5 / 2 - 8 && number15 <= number5 / 2 + 8) {
         String text16 = Ref.method4().method67().method2("gui.components", "hostedWorldLunarPlusBoost");
         float value17 = FontRegistry.method13().method4(text16) + 8.0F;
         float value18 = number6 - 8 - value17;
         float value19 = number7 - 2;
         if (value18 < 0.0F) {
            value18 = number6 + 8;
         }

         LcuiScreen.method117(mixinhelper_41, value18, value19, value17, FontRegistry.method8().getHeight() * 2 + 8, 5.0F, -805306368);
         FontRegistry.method13().method17(mixinhelper_41, text16, value18 + 4.0F, value19 + 2.0F, -268435457, false);
      }

      if (flag8 && hostedworld0.getJoinability() == Joinability.JOINABILITY_ALLOWED && !Bridge.getMinecraftVersion().equals(Config.field1)) {
         LcuiScreen.method66(mixinhelper_41, number2, number3, number2 + 32, number3 + 32, -1601138544);
         if (number14 > 10 && number14 < 26) {
            if (Ref.MC_VERSION >= 19) {
               mixinhelper_41.method24(field2, number2, number3, 32, 32, -1);
            } else if (Ref.MC_VERSION >= 17) {
               mixinhelper_41.method25(field1, number2, number3, 0.0F, 32.0F, 32.0F, 32.0F, 256.0F, 256.0F, -1);
            } else {
               LcuiScreen.method46(mixinhelper_41, field1, number2 - 6, number3, 0.0F, 32.0F, 32.0F, 32.0F, 256.0F, 256.0F, 16777215);
            }
         } else if (Ref.MC_VERSION >= 19) {
            mixinhelper_41.method24(field1, number2, number3, 32, 32, -1);
         } else if (Ref.MC_VERSION >= 17) {
            mixinhelper_41.method25(field1, number2, number3, 0.0F, 0.0F, 32.0F, 32.0F, 256.0F, 256.0F, -1);
         } else {
            LcuiScreen.method46(mixinhelper_41, field1, number2 - 6, number3, 0.0F, 0.0F, 32.0F, 32.0F, 256.0F, 256.0F, 16777215);
         }
      }
   }
}
