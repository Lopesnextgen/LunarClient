package com.moonsworth.lunar.legacy.mixin;

import com.lunarclient.apollo.module.serverlink.ServerLinkModule;
import com.lunarclient.apollo.module.serverlink.pausemenu.LegacyServerLinkPlacement;
import com.lunarclient.gameipc.browser.v1.OpenUrlRequest.Initiator;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.bridge.GuiIngameMenuBridge;
import com.moonsworth.lunar.bridge.MinecraftBridge;
import com.moonsworth.lunar.bridge.GameOptionsBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.network.apollo.ServerLinkApolloHandler;
import com.moonsworth.lunar.client.gui.HostWorldScreen;
import com.moonsworth.lunar.client.ui.menu.FeatureSettingsScreen;
import com.moonsworth.lunar.client.event.LunarEventBus;
import com.moonsworth.lunar.client.event.mixin.gui.EventScreenAction;
import com.moonsworth.lunar.client.util.net.BrowserUtils;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.api.IchorAPI;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiIngameMenu.class)
public abstract class GuiIngameMenuMixin extends GuiScreen implements GuiIngameMenuBridge {
   @Unique
   private static final int VANILLA_ACHIEVEMENTS_BUTTON_ID = 5;
   @Unique
   private static final int VANILLA_STATISTICS_BUTTON_ID = 6;
   @Unique
   private static final int VANILLA_OPEN_TO_LAN_INDEX = 3;
   @Unique
   private static final int FORGE_OPEN_TO_LAN_INDEX = 4;
   @Unique
   private static final int FORGE_MOD_OPTIONS_INDEX = 3;
   @Unique
   private static final int LUNAR_OPTIONS_BUTTON_ID = 100;
   @Unique
   private static final int HOSTED_WORLDS_BUTTON_ID = 101;
   @Unique
   private static final int MULTIPLAYER_BUTTON_ID = 102;
   @Unique
   private static final int SERVER_LINKS_BUTTON_ID = 103;
   @Unique
   private static final int REPORT_BUG_BUTTON_ID = 104;
   @Unique
   private static final int DISCONNECT_BUTTON_ID = 1;

   public GuiIngameMenuMixin() {
   }

   @Inject(method = "initGui", at = @At("TAIL"))
   private void lunar$onInitGui(CallbackInfo callback1) {
      boolean flag2 = IchorAPI.getPipeline(Client.class.getClassLoader()).map(arg0 -> arg0.hasModule("forge")).orElse(false);
      boolean flag3 = this.mc.isIntegratedServerRunning();
      String text4 = Ref.method4().method81().method28() != null ? "World Options" : "Host World";
      GuiButton guibutton5 = new GuiButton(101, this.width / 2 - 100, this.height / 4 + 56, 200, 20, text4);
      GuiButton guibutton6 = new GuiButton(102, this.width / 2 - 100, this.height / 4 + 56, 200, 20, "Multiplayer");
      LegacyServerLinkPlacement legacyserverlinkplacement7 = this.lunar$activePlacement();
      boolean flag8 = legacyserverlinkplacement7 == LegacyServerLinkPlacement.NEW_ROW;
      int number9 = flag8 ? 24 : 0;
      GuiButton guibutton10 = new GuiButton(103, this.width / 2 - 100, this.height / 4 + 80, 98, 20, "Server Links...");
      GuiButton guibutton11 = new GuiButton(104, this.width / 2 + 2, this.height / 4 + 80, 98, 20, "Lunar Support...");
      GuiButton guibutton12 = new GuiButton(100, this.width / 2 + 2, this.height / 4 + 80 + number9, 98, 20, "Lunar Options...");
      List list13 = Ref.MC_VERSION >= 1 ? this.buttonList : this.buttonList$v1_7;
      if (!flag2) {
         list13.remove(3);
      } else {
         list13.remove(4);
         list13.remove(3);
      }

      if (flag3) {
         list13.add(guibutton5);
      } else {
         list13.add(guibutton6);
      }

      if (flag8) {
         list13.add(guibutton10);
         list13.add(guibutton11);
      }

      list13.add(guibutton12);
      if (legacyserverlinkplacement7 != null && !flag8) {
         int number14 = this.lunar$replaceButtonId(legacyserverlinkplacement7);

         for (GuiButton guibutton16 : list13) {
            if (guibutton16.id == number14) {
               guibutton16.displayString = guibutton10.displayString;
               break;
            }
         }
      }
   }

   @Unique
   private LegacyServerLinkPlacement lunar$activePlacement() {
      return Ref.method4()
         .method84()
         .method3(ServerLinkModule.class)
         .map(arg0 -> (ServerLinkApolloHandler)arg0)
         .filter(arg0 -> !arg0.method8().isEmpty())
         .<LegacyServerLinkPlacement>map(ServerLinkApolloHandler::method3)
         .orElse(null);
   }

   @Unique
   private int lunar$replaceButtonId(LegacyServerLinkPlacement legacyserverlinkplacement1) {
      if (legacyserverlinkplacement1 == LegacyServerLinkPlacement.REPLACE_ACHIEVEMENTS) {
         return 5;
      } else {
         return legacyserverlinkplacement1 == LegacyServerLinkPlacement.REPLACE_STATISTICS ? 6 : -1;
      }
   }

   @Inject(method = "actionPerformed", at = @At("HEAD"), cancellable = true)
   private void lunar$onActionPerformed(GuiButton guibutton1, CallbackInfo callback2) {
      LegacyServerLinkPlacement legacyserverlinkplacement3 = this.lunar$activePlacement();
      if (legacyserverlinkplacement3 != null && guibutton1.id == this.lunar$replaceButtonId(legacyserverlinkplacement3)) {
         Ref.method3().bridge$displayScreen((GuiScreenBridge)Bridge.method8().method36(this));
         callback2.cancel();
      } else {
         if (guibutton1.id == 100) {
            Ref.method3()
               .bridge$displayScreen(Bridge.method8().method18(new FeatureSettingsScreen(Ref.method3().bridge$getCurrentScreen())));
            callback2.cancel();
         } else if (guibutton1.id == 102) {
            EventScreenAction highlightimpl144 = (EventScreenAction)LunarEventBus.method29()
               .method12(EventScreenAction.class, () -> new EventScreenAction(() -> this.mc.displayGuiScreen(new GuiMultiplayer(this.mc.currentScreen))));
            if (highlightimpl144 == null || !highlightimpl144.isCancelled()) {
               this.mc.displayGuiScreen(new GuiMultiplayer(this.mc.currentScreen));
            }

            callback2.cancel();
         } else if (guibutton1.id == 101) {
            Ref.method3().bridge$displayScreen(Bridge.method8().method18(new HostWorldScreen("hostedWorldSettings")));
            callback2.cancel();
         } else if (guibutton1.id == 1) {
            EventScreenAction highlightimpl148 = (EventScreenAction)LunarEventBus.method29().method12(EventScreenAction.class, () -> new EventScreenAction(() -> {
               guibutton1.field_178665_b = false;
               if (this.mc.theWorld != null) {
                  this.mc.theWorld.sendQuittingDisconnectingPacket();
               }

               this.mc.loadWorld(null);
               this.mc.displayGuiScreen(new GuiMainMenu());
            }));
            if (highlightimpl148 != null && highlightimpl148.isCancelled()) {
               callback2.cancel();
            }
         } else if (guibutton1.id == 103) {
            Ref.method3().bridge$displayScreen((GuiScreenBridge)Bridge.method8().method36(this));
            callback2.cancel();
         } else if (guibutton1.id == 104) {
            URI uri9;
            try {
               uri9 = new URI("https://support.lunarclient.com");
            } catch (URISyntaxException urisyntaxexception7) {
               return;
            }

            MinecraftBridge bridge5_125 = Ref.method3();
            GameOptionsBridge mixinhelper2_86 = bridge5_125.bridge$getGameSettings();
            if (mixinhelper2_86.bridge$isChatLinks()) {
               if (mixinhelper2_86.bridge$isChatPromptLinks()) {
                  bridge5_125.bridge$displayScreen(Bridge.method8().method33(this, uri9.toString(), uri9, false));
               } else {
                  BrowserUtils.method7(uri9.toString(), Initiator.INITIATOR_UNSPECIFIED);
               }
            }

            callback2.cancel();
         }
      }
   }

   @ModifyConstant(method = "initGui", constant = {@Constant(intValue = 96), @Constant(intValue = 120)})
   private int lunar$shiftButtons(int number1) {
      boolean flag2 = this.lunar$activePlacement() == LegacyServerLinkPlacement.NEW_ROW;
      int number3 = flag2 ? 24 : 0;
      return number1 + number3;
   }
}
