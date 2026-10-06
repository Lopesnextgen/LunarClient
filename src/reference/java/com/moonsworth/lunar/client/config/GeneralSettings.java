package com.moonsworth.lunar.client.config;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lunarclient.dfu.serialization.Codec;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5Extension62;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.Bridge7_8;
import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.ui.widget.ApplyToAllNumberWidget;
import com.moonsworth.lunar.client.ui.widget.ApplyToAllInfoWidget;
import com.moonsworth.lunar.client.ui.widget.ApplyToAllColorWidget;
import com.moonsworth.lunar.client.ui.mainmenu.MainMenuButton;
import com.moonsworth.lunar.client.ui.menu.FeatureSettingsScreen;
import com.moonsworth.lunar.client.fishing.Fishing_2;
import com.moonsworth.lunar.client.mod.misc.EventChest;
import com.moonsworth.lunar.client.config.FeatureFlag;
import com.moonsworth.lunar.client.cosmetics.CosmeticCategoryType;
import com.moonsworth.lunar.client.framework.mod.AlertExtension;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework5;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.SettingsTreeAssembler;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.ListOption;
import com.moonsworth.lunar.client.config.option.OptionSupplier;
import com.moonsworth.lunar.client.config.option.IntegerOption.Data;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.OptionDataProvider;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.driver.PhosphorIconLegacy;
import com.moonsworth.lunar.client.driver.core.gui.Gui;
import com.moonsworth.lunar.client.driver.core.gui.JsonProviderLegacy;
import com.moonsworth.lunar.client.driver.core.gui.GuiIterator;
import com.moonsworth.lunar.client.framework.build.LunarBuildData;
import com.moonsworth.lunar.client.util.ThreadModuleDump49;
import com.moonsworth.lunar.client.util.ThreadModuleDump51;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump71;
import com.moonsworth.lunar.client.util.ThreadModuleDumpType2;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import lombok.Generated;
import mchorse.emoticons.capabilities.cosmetic.EmoteController;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.framework.StreamerMode;

public class GeneralSettings extends com.moonsworth.lunar.client.config.SettingsContainer implements Gui, JsonProviderLegacy {
   private final GuiIterator field2 = new GuiIterator();
   private final ToggleOption field3 = (ToggleOption)OptionFactory.method7("advancedMode").method31();
   private final ToggleOption field4 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("enableAnniversary").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field5 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("enableAprilFools").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field6 = (ToggleOption)OptionFactory.method7("borderlessFullscreen").method31();
   private final EnumOption<com.moonsworth.lunar.client.ui.notification.NotificationAnchor> field7 = (EnumOption<com.moonsworth.lunar.client.ui.notification.NotificationAnchor>)OptionFactory.method10(
         "popupLocation", com.moonsworth.lunar.client.ui.notification.NotificationAnchor.TOP_RIGHT
      )
      .method31();
   private final ToggleOption field8 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("keepInventoryCentered").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field9 = (ToggleOption)OptionFactory.method7("showHudInDebug").method31();
   private final ToggleOption field10 = (ToggleOption)OptionFactory.method7("showHudInShiftTab").method31();
   private final EnumOption<GeneralSettings.Type> field11 = (EnumOption<GeneralSettings.Type>)OptionFactory.method10(
         "useMinecraftScale", GeneralSettings.Type.AUTO
      )
      .method31();
   private final ToggleOption field12 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("highDPIScale").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field13 = (ToggleOption)OptionFactory.method7("transparentBackground").method31();
   private final ToggleOption field14 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("packPreviews").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field15 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("friendsMenu").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field16 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("friendOnlineStatus").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field17 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showInF5").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field18 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("fastReloadScreen").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field19 = (ToggleOption)((ToggleOptionBuilder)((ToggleOptionBuilder)OptionFactory.method7("renderCosmeticParticles")
            .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
         .method15(PhosphorIconLegacy.PI_AI01_STROKE))
      .method31();
   private final ToggleOption field20 = (ToggleOption)((ToggleOptionBuilder)((ToggleOptionBuilder)OptionFactory.method7("renderSprays").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
         .method15(PhosphorIconLegacy.PI_PAINT_BRUSH_STROKE))
      .method31();
   private final ToggleOption field21 = (ToggleOption)((ToggleOptionBuilder)((ToggleOptionBuilder)OptionFactory.method7("renderClothCloaks")
            .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
         .method15(PhosphorIconLegacy.PI_EYE_ON_STROKE))
      .method31();
   private final ToggleOption field22 = (ToggleOption)((ToggleOptionBuilder)((ToggleOptionBuilder)OptionFactory.method7("renderEmotes").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
         .method15(PhosphorIconLegacy.PI_USER_DEFAULT_STROKE))
      .method31();
   private final ToggleOption field23 = (ToggleOption)((ToggleOptionBuilder)((ToggleOptionBuilder)OptionFactory.method7("renderEmoteParticles")
            .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
         .method15(PhosphorIconLegacy.PI_AI01_STROKE))
      .method31();
   private final ToggleOption field24 = (ToggleOption)((ToggleOptionBuilder)((ToggleOptionBuilder)((ToggleOptionBuilder)OptionFactory.method7("useSimpleEmoteModels")
               .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
            .RIHRRCHRORHHOROCHHCCHHOIOIOHCR())
         .method15(PhosphorIconLegacy.PI_USER_DEFAULT_STROKE))
      .method31();
   private final ToggleOption field25 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("rawMouseInput")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(ThreadModuleDump63.MC_VERSION >= 6 && ThreadModuleDump63.method3().bridge$getGameSettings().bridge$getRawMouseInput()))
      .method31();
   private final ToggleOption field26 = (ToggleOption)((ToggleOptionBuilder)((ToggleOptionBuilder)OptionFactory.method7("tabLogo").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
         .method15(PhosphorIconLegacy.PI_EYE_ON_STROKE))
      .method31();
   private final ToggleOption field27 = (ToggleOption)((ToggleOptionBuilder)((ToggleOptionBuilder)OptionFactory.method7("tabBadge").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
         .method15(PhosphorIconLegacy.PI_EYE_ON_STROKE))
      .method31();
   private final ToggleOption field28 = (ToggleOption)((ToggleOptionBuilder)((ToggleOptionBuilder)OptionFactory.method7("nametagLogo").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
         .method15(PhosphorIconLegacy.PI_EYE_ON_STROKE))
      .method31();
   private final ToggleOption field29 = (ToggleOption)((ToggleOptionBuilder)((ToggleOptionBuilder)OptionFactory.method7("nametagBadge").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
         .method15(PhosphorIconLegacy.PI_EYE_ON_STROKE))
      .method31();
   private final ToggleOption field30 = (ToggleOption)((ToggleOptionBuilder)((ToggleOptionBuilder)OptionFactory.method7("achievements")
            .OIRHORRROCHOIRCRHHORHRCIIRHROO(Bridge.getMinecraftVersion().method19() ? "advancements" : "achievements"))
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field31 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("apolloNotifications").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field32 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("tutorialHints").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field33 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("unsecureServer").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final EnumOption<GeneralSettings.Type4> field34 = (EnumOption<GeneralSettings.Type4>)OptionFactory.method10(
         "sortingOptions", GeneralSettings.Type4.CUSTOM
      )
      .method31();
   private final ToggleOption field35 = (ToggleOption)OptionFactory.method7("lockMods").method31();
   private final ToggleOption field36 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("modToggleNotification").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field37 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("searchOptions").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field38 = (ToggleOption)OptionFactory.method7("smartDisconnect").method31();
   private final ToggleOption field39 = (ToggleOption)OptionFactory.method7("autoReconnect").method31();
   private final IntegerOption field40 = (IntegerOption)((Data)((Data)OptionFactory.method4("autoReconnectDelay").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(5))
         .method7(5, 60))
      .method31();
   private final IntegerOption field41 = (IntegerOption)((Data)((Data)OptionFactory.method4("autoReconnectMaxAttempts")
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(3))
         .method7(1, 10))
      .method31();
   private final ToggleOption field42 = (ToggleOption)OptionFactory.method7("dragToReorderServers").method31();
   private final ToggleOption field43 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showNewBubbles").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field44 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("verifyTransfers").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field45 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("promptOnApolloLinkOpens").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field46 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showServerInventoryButtons")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field47 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showServerChatButtons").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field48 = (ToggleOption)OptionFactory.method7("modernKeybindHandling").method31();
   private final ToggleOption field49 = (ToggleOption)OptionFactory.method7("disableHotbarScrolling").method31();
   private final EnumOption<GeneralSettings.Type3> field50 = (EnumOption<GeneralSettings.Type3>)((com.moonsworth.lunar.client.config.option.EnumOption.Data)OptionFactory.method10(
            "mainMenuStyle", GeneralSettings.Type3.LUNAR
         )
         .method11()
         .method10(PhosphorIconLegacy.PI_PAINT_BRUSH_STROKE))
      .method31();
   private final ColorOption field51 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "hudLayoutColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   private final FloatOption field52 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
                  "sneakAnimationSpeed"
               )
               .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
            .method8(0.0F, 2.0F))
         .method15(PhosphorIconLegacy.PI_CAMERA_DEFAULT_STROKE))
      .method31();
   private final ToggleOption field53 = (ToggleOption)OptionFactory.method7("compactMenu").method31();
   private final ToggleOption field54 = (ToggleOption)((ToggleOptionBuilder)((ToggleOptionBuilder)OptionFactory.method7("playJams").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
         .method15(PhosphorIconLegacy.PI_MUSIC_BEAM_NOTE_STROKE))
      .method31();
   private final ToggleOption field55 = (ToggleOption)((ToggleOptionBuilder)((ToggleOptionBuilder)OptionFactory.method7("streamerMode").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
         .method15(PhosphorIconLegacy.PI_YOUTUBE_STROKE))
      .method31();
   private final EnumOption<StreamerMode.Type> field56 = (EnumOption<StreamerMode.Type>)((com.moonsworth.lunar.client.config.option.EnumOption.Data)OptionFactory.method10(
            "streamerModeBehaviour", StreamerMode.Type.AUTOMATIC
         )
         .method10(PhosphorIconLegacy.PI_YOUTUBE_STROKE))
      .method31();
   private final ToggleOption field57 = (ToggleOption)((ToggleOptionBuilder)((ToggleOptionBuilder)OptionFactory.method7("streamerModeNotification")
            .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
         .method15(PhosphorIconLegacy.PI_EYE_ON_STROKE))
      .method31();
   private final FloatOption field58 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
                  "ownJamsVolume"
               )
               .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(0.5F))
            .method8(0.0F, 1.0F))
         .method15(PhosphorIconLegacy.PI_VOLUME_TWO_STROKE))
      .method31();
   private final FloatOption field59 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
                  "jamsOthersVolume"
               )
               .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(0.3F))
            .method8(0.0F, 1.0F))
         .method15(PhosphorIconLegacy.PI_VOLUME_TWO_STROKE))
      .method31();
   private final ToggleOption field60 = (ToggleOption)((ToggleOptionBuilder)((ToggleOptionBuilder)OptionFactory.method7("showCloakOnElytra")
            .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
         .method15(PhosphorIconLegacy.PI_EYE_ON_STROKE))
      .method31();
   private final ToggleOption field61 = (ToggleOption)((ToggleOptionBuilder)((ToggleOptionBuilder)OptionFactory.method7("scaleHatWithSkinLayer")
            .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
         .method15(PhosphorIconLegacy.PI_MAXIMIZE_LINE_ARROW_STROKE))
      .method31();
   private final ToggleOption field62 = (ToggleOption)((ToggleOptionBuilder)((ToggleOptionBuilder)OptionFactory.method7("ingameUiTransparency")
            .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
         .method15(PhosphorIconLegacy.PI_MONITOR01_STROKE))
      .method31();
   private final IntegerOption field63 = (IntegerOption)((Data)((Data)((Data)OptionFactory.method4("uiScrollSpeed").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(32))
            .method7(1, 100))
         .method15(PhosphorIconLegacy.PI_MOUSE_SCROLL_STROKE))
      .method31();
   private final FloatOption field64 = (FloatOption)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
                  "lunarSoundsVolume"
               )
               .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
            .method8(0.0F, 1.0F))
         .method17(() -> true))
      .method31();
   private final EnumOption<GeneralSettings.Type2> field65 = (EnumOption<GeneralSettings.Type2>)((com.moonsworth.lunar.client.config.option.EnumOption.Data)OptionFactory.method10(
            "cosmeticRenderMode", GeneralSettings.Type2.EVERYONE
         )
         .method10(PhosphorIconLegacy.PI_TSHIRT_DEFAULT_STROKE))
      .method31();
   private final EnumOption<GeneralSettings.Type2> field66 = (EnumOption<GeneralSettings.Type2>)OptionFactory.method10(
         "emoteRenderMode", GeneralSettings.Type2.EVERYONE
      )
      .method31();
   private final EnumOption<GeneralSettings.Type2> field67 = (EnumOption<GeneralSettings.Type2>)OptionFactory.method10(
         "sprayRenderMode", GeneralSettings.Type2.EVERYONE
      )
      .method31();
   private final ListOption<String> field68 = (ListOption<String>)OptionFactory.method31(
         "serverCommandPermissionWhitelist", Codec.STRING.listOf()
      )
      .method31();
   private final ListOption<String> field69 = (ListOption<String>)OptionFactory.method31(
         "serverCommandPermissionBlacklist", Codec.STRING.listOf()
      )
      .method31();
   private final ListOption<String> field70 = (ListOption<String>)OptionFactory.method31("maliciousServerIpWhitelist", Codec.STRING.listOf())
      .method31();
   private final ListOption<String> field71 = (ListOption<String>)OptionFactory.method31("maliciousUrlWhitelist", Codec.STRING.listOf())
      .method31();
   private final ListOption<String> field72 = (ListOption<String>)OptionFactory.method31("apolloButtonCommandWhitelist", Codec.STRING.listOf())
      .method31();
   private final ListOption<String> field73 = (ListOption<String>)OptionFactory.method31("apolloButtonUrlWhitelist", Codec.STRING.listOf())
      .method31();
   private final ToggleOption field74 = (ToggleOption)OptionFactory.method7("noxesiumCustomRenderType").method31();
   private final ToggleOption field75 = (ToggleOption)((ToggleOptionBuilder)((ToggleOptionBuilder)OptionFactory.method7("showUICursors").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
         .method15(PhosphorIconLegacy.PI_POINTER_CURSOR_DEFAULT_STROKE))
      .method31();
   public static Float field76;
   public static boolean field77;

   @Override
   protected void method10(SettingsTreeAssembler var1) {
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(
         SettingsPage.GENERAL,
         var1x -> {
            var1x.method12(new ClientOption[]{this.field3}).method3(() -> true);
            var1x.method12(new ClientOption[]{this.field6});
            var1x.method12(new ClientOption[]{this.field25})
               .method4(() -> ThreadModuleDump63.MC_VERSION <= 5 && !ThreadModuleDumpType2.isWindows());
            var1x.method12(new ClientOption[]{this.field38});
            var1x.method10(
               this.field39, var1xx -> var1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field40, this.field41})
            );
            var1x.method12(new ClientOption[]{this.field42});
            var1x.method12(new ClientOption[]{this.field48}).IIOHHROCRCCRCRIHCOHIHRHCOOIHRR(1);
            var1x.method12(new ClientOption[]{this.field8});
            var1x.method12(new ClientOption[]{this.field18}).RHIHIIRHRCRHCCIIICHIRCCCOIIOHO(5);
            var1x.method12(new ClientOption[]{this.field49, this.field50});
            var1x.method12(new ClientOption[]{this.field5}).method3(() -> !EventChest.field1);
            var1x.method12(new ClientOption[]{this.field4}).method3(() -> !EventChest.field2);
         }
      );
      var1.method1("hudOptions", var1x -> {
         var1x.method12(new ClientOption[]{this.field9, this.field10, this.field15, this.field17, this.field11});
         var1x.method12(new ClientOption[]{this.field12}).HOCIIROHCHHIORICCRHIIRIIRCRCOR();
      });
      var1.method1("notificationOptions", var1x -> {
         var1x.method12(new ClientOption[]{this.field30});
         var1x.method12(new ClientOption[]{this.field32}).RHIHIIRHRCRHCCIIICHIRCCCOIIOHO(5);
         var1x.method12(new ClientOption[]{this.field33}).RHIHIIRHRCRHCCIIICHIRCCCOIIOHO(14);
         var1x.method12(new ClientOption[]{this.field43, this.field16, this.field31, this.field7});
      });
      var1.method1("resourcePackOptions", var1x -> {
         var1x.method12(new ClientOption[]{this.field13, this.field14});
         var1x.method12(new ClientOption[]{this.field52}).RHIHIIRHRCRHCCIIICHIRCCCOIIOHO(6);
      });
      var1.method1(
         "modOptions",
         var1x -> var1x.method12(
            new ClientOption[]{this.field34, this.field37, this.field35, this.field36, ThreadModuleDump63.method4().method42().method18()}
         )
      );
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO("cosmeticOptions", PhosphorIconLegacy.PI_TSHIRT_DEFAULT_SOLID, var1x -> {
         var1x.method12(new ClientOption[]{this.field19, this.field26, this.field28, this.field27, this.field29, this.field21});
         var1x.method12(new ClientOption[]{this.field60}).RHIHIIRHRCRHCCIIICHIRCCCOIIOHO(5);
         var1x.method12(new ClientOption[]{this.field61, this.field65});

         for (CosmeticCategoryType var5 : CosmeticCategoryType.values()) {
            if (var5 != CosmeticCategoryType.DEV_COSMETICS || !LunarBuildData.field4) {
               var1x.method12(new ClientOption[]{var5.getShowCosmetic()});
            }
         }
      });
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(
         "emoteOptions",
         PhosphorIconLegacy.PI_USER_DEFAULT_SOLID,
         var1x -> {
            var1x.method12(new ClientOption[]{this.field22, this.field23, this.field24});
            var1x.method10(
               this.field54, var1xx -> var1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field58, this.field59})
            );
         }
      );
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(
         "sprayOptions", PhosphorIconLegacy.PI_PAINT_BRUSH_SOLID, var1x -> var1x.method12(new ClientOption[]{this.field20, this.field67})
      );
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(
         "interfaceOptions",
         PhosphorIconLegacy.PI_MONITOR01_SOLID,
         var1x -> {
            var1x.method12(new ClientOption[]{this.field62, this.field63, this.field75});
            var1x.method10(
               this.field55, var1xx -> var1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field56, this.field57})
            );
         }
      );
      var1.method1(
         "globalColorOptions",
         var1x -> var1x.method12(
            new OptionSupplier[]{
               this.field51,
               ((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8("textColor")
                     .HORHROIOIOICIRHIOCOICHHHIHCIIO((var1xx, var2x) -> new ApplyToAllColorWidget(var1xx, var2x, this.method4(var1xx))))
                  .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1),
               ((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8("borderColor")
                     .HORHROIOIOICIRHIOCOICHHHIHCIIO((var1xx, var2x) -> new ApplyToAllColorWidget(var1xx, var2x, this.method4(var1xx))))
                  .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1),
               ((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8("bracketColor")
                     .HORHROIOIOICIRHIOCOICHHHIHCIIO((var1xx, var2x) -> new ApplyToAllColorWidget(var1xx, var2x, this.method4(var1xx))))
                  .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1),
               ((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8("backgroundColor")
                     .HORHROIOIOICIRHIOCOICHHHIHCIIO((var1xx, var2x) -> new ApplyToAllColorWidget(var1xx, var2x, this.method4(var1xx))))
                  .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(1862270976)
            }
         )
      );
      var1.method1(
         "globalBackgroundOptions",
         var1x -> var1x.method12(
            new OptionSupplier[]{
               ((Data)((Data)OptionFactory.method4("backgroundHeight")
                        .HORHROIOIOICIRHIOCOICHHHIHCIIO((var1xx, var2x) -> new ApplyToAllNumberWidget(var1xx, var2x, this.method4(var1xx))))
                     .method7(10, 24))
                  .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(18),
               ((Data)((Data)OptionFactory.method4("backgroundWidth")
                        .HORHROIOIOICIRHIOCOICHHHIHCIIO((var1xx, var2x) -> new ApplyToAllNumberWidget(var1xx, var2x, this.method4(var1xx))))
                     .method7(40, 72))
                  .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(56),
               ((com.moonsworth.lunar.client.config.option.FloatOption.Data)((com.moonsworth.lunar.client.config.option.FloatOption.Data)OptionFactory.method2(
                           "borderThickness"
                        )
                        .HORHROIOIOICIRHIOCOICHHHIHCIIO((var1xx, var2x) -> new ApplyToAllNumberWidget(var1xx, var2x, this.method4(var1xx))))
                     .method8(0.5F, 3.0F))
                  .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(0.5F)
            }
         )
      );
      var1.method1(
         "globalBooleanOptions",
         var1x -> var1x.method12(
            new OptionSupplier[]{
               ((ToggleOptionBuilder)OptionFactory.method7("background")
                     .HORHROIOIOICIRHIOCOICHHHIHCIIO((var1xx, var2x) -> new ApplyToAllInfoWidget(var1xx, var2x, this.method4(var1xx))))
                  .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true),
               ((ToggleOptionBuilder)OptionFactory.method7("brackets")
                     .HORHROIOIOICIRHIOCOICHHHIHCIIO((var1xx, var2x) -> new ApplyToAllInfoWidget(var1xx, var2x, this.method4(var1xx))))
                  .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true),
               OptionFactory.method7("border").HORHROIOIOICIRHIOCOICHHHIHCIIO((var1xx, var2x) -> new ApplyToAllInfoWidget(var1xx, var2x, this.method4(var1xx))),
               ((ToggleOptionBuilder)OptionFactory.method7("textShadow")
                     .HORHROIOIOICIRHIOCOICHHHIHCIIO((var1xx, var2x) -> new ApplyToAllInfoWidget(var1xx, var2x, this.method4(var1xx))))
                  .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true)
            }
         )
      );
      ((SettingsSectionImpl)var1.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field74})).method2(() -> true);
      this.field11.HORHIRROCIOIICIOHCOCCOOHIRCCRI(var0 -> method13());
      this.field12.CICORRHIOIIOORRRICCORIOIOCIHII(var0 -> {
         if (ThreadModuleDump63.MC_VERSION >= 6) {
            ThreadModuleDump63.method3().bridge$resizeDisplay();
            method13();
         }
      });
      Consumer var2 = var0 -> ThreadModuleDump63.method4().method53().method24();
      this.field61.HORHIRROCIOIICIOHCOCCOOHIRCCRI(var2);
      this.field75.HORHIRROCIOIICIOHCOCCOOHIRCCRI(var0 -> {
         if (!var0) {
            ThreadModuleDump49.method2(null);
            Bridge.method20().method5();
         }
      });
      if (ThreadModuleDump63.MC_VERSION >= 6) {
         this.field25.CICORRHIOIIOORRRICCORIOIOCIHII(var0 -> ThreadModuleDump63.method3().bridge$getGameSettings().bridge$setRawMouseInput(var0));
      } else {
         this.field25.CICORRHIOIIOORRRICCORIOIOCIHII(var0 -> {
            if (ThreadModuleDumpType2.isWindows()) {
               ThreadModuleDump63.method3().bridge$getWindow().bridge$toggleRawInput(var0);
            }
         });
      }

      Consumer var3 = var0 -> {
         if (ThreadModuleDump63.method31(ThreadModuleDump63.method3().bridge$getCurrentScreen()) instanceof FeatureSettingsScreen var1x) {
            var1x.method10().method14().method2();
         }
      };
      this.field53.CICORRHIOIIOORRRICCORIOIOCIHII(var3);
      this.field34.CICORRHIOIIOORRRICCORIOIOCIHII(var3);
      this.field24.CICORRHIOIIOORRRICCORIOIOCIHII(var0 -> {
         if (ThreadModuleDump63.method7() != null && EmoteController.get(ThreadModuleDump63.method7().bridge$getUniqueID()) != null) {
            EmoteController.clearCache();
         }
      });
      this.field11
         .CICORRHIOIIOORRRICCORIOIOCIHII(
            var0 -> {
               if (ThreadModuleDump63.method3().bridge$getCurrentScreen() != null
                  && ThreadModuleDump63.method3().bridge$getCurrentScreen() instanceof Bridge5Extension62) {
                  Bridge7_8 var1x = ((Bridge5Extension62)ThreadModuleDump63.method3().bridge$getCurrentScreen()).method2();
                  if (var1x instanceof MainMenuButton var2x && var2x.method17() instanceof Bridge5Extension62 var3x) {
                     var1x = var3x.method2();
                  }

                  if (!(var1x instanceof FeatureSettingsScreen var5)) {
                     return;
                  }

                  com.moonsworth.lunar.client.ui.LcuiScreen.method150(new ThreadModuleDump71(ThreadModuleDump63.method3()));
                  var5.initGui();
               }
            }
         );
      this.field6.CICORRHIOIIOORRRICCORIOIOCIHII(var0 -> {
         if (System.currentTimeMillis() - ThreadModuleDump63.method4().method61().method15() >= 500L) {
            if (ThreadModuleDump63.method3().bridge$isFullScreen()) {
               ThreadModuleDump63.method3().bridge$toggleFullscreen();
               ThreadModuleDump63.method3().bridge$toggleFullscreen();
            }
         }
      });
      if (EventChest.field1 && FeatureFlag.APRIL_FOOLS_ENTITIES.isEnabled()) {
         this.field5.HORHIRROCIOIICIOHCOCCOOHIRCCRI(var0 -> ThreadModuleDump63.method4().method87().method3("AprilFools", var0));
      }

      if (EventChest.field2 && FeatureFlag.ANNIVERSARY_PARTICLES.isEnabled()) {
         this.field4.HORHIRROCIOIICIOHCOCCOOHIRCCRI(var0 -> ThreadModuleDump63.method4().method87().method3("Anniversary", var0));
      }

      this.field58.CICORRHIOIIOORRRICCORIOIOCIHII(var0 -> {
         Bridge5Extension_5 var1x = ThreadModuleDump63.method7();
         if (var1x != null) {
            ThreadModuleDump63.method4().method45().method29().forEach((var2x, var3x) -> {
               if (var2x.equals(var1x.bridge$getUniqueID())) {
                  ThreadModuleDump63.method3().bridge$getSoundHandler().bridge$setVolume(var3x.getSource(), var0);
               }
            });
         }
      });
      this.field59.CICORRHIOIIOORRRICCORIOIOCIHII(var0 -> {
         Bridge5Extension_5 var1x = ThreadModuleDump63.method7();
         if (var1x != null) {
            ThreadModuleDump63.method4().method45().method29().forEach((var2x, var3x) -> {
               if (!var2x.equals(var1x.bridge$getUniqueID())) {
                  ThreadModuleDump63.method3().bridge$getSoundHandler().bridge$setVolume(var3x.getSource(), var0);
               }
            });
         }
      });
      this.field54.CICORRHIOIIOORRRICCORIOIOCIHII(var0 -> {
         if (ThreadModuleDump63.method4().method45() != null) {
            Map var1x = ThreadModuleDump63.method4().method45().method29();
            var1x.forEach((var0x, var1xx) -> ThreadModuleDump63.method3().bridge$getSoundHandler().bridge$destroySound(var1xx.getSource()));
            var1x.clear();
         }
      });
      this.field50.method9(() -> {
         if (ThreadModuleDump63.method8() == null && ThreadModuleDump63.method3().bridge$getGuiIngame() != null) {
            if (this.field50.get() == GeneralSettings.Type3.VANILLA) {
               ThreadModuleDump63.method3().bridge$displayScreen(Bridge.method8().method19());
            } else {
               ThreadModuleDump63.method4().method23();
            }
         }
      });
      var1.method1("serverSafetySettings", var1x -> {
         var1x.method12(new ClientOption[]{this.field44, this.field45, this.field46, this.field47});
         var1x.method12(new ClientOption[]{this.field70, this.field71});
         var1x.method12(new ClientOption[]{this.field72, this.field73});
         var1x.method12(new ClientOption[]{this.field68, this.field69}).method3(() -> ThreadModuleDump63.MC_VERSION <= 29);
      });
   }

   private static void method13() {
      ThreadModuleDump71 var0 = new ThreadModuleDump71(ThreadModuleDump63.method3());
      com.moonsworth.lunar.client.ui.LcuiScreen.method150(var0);
      if (ThreadModuleDump63.method3().bridge$getCurrentScreen() != null) {
         ThreadModuleDump63.method3().bridge$getCurrentScreen().bridge$setWorldAndResolution(var0.getScaledWidth(), var0.getScaledHeight());
      }
   }

   public boolean method15() {
      return ThreadModuleDump63.method4().method40().method48().method15() && (Boolean)this.field26.get() || (Boolean)this.field27.get();
   }

   private ThreadModuleDump51 method4(ClientOption<?> var1) {
      return (var2, var3) -> {
         this.method5(var1xx -> {
            if (Objects.equals(var1.getId(), var1xx.getId())) {
               try {
                  var1xx.method19(var1);
               } catch (IllegalArgumentException var3x) {
                  Slayer.warn("GeneralOptions applyToAll", var3x);
               }
            }
         });
         return true;
      };
   }

   private void method5(Consumer<ClientOption<?>> var1) {
      for (Framework7Extension var3 : ThreadModuleDump63.method4().method40().IIORHHIRHIORHRCCCOICCRCHRRCCRH()) {
         AlertExtension var4 = (AlertExtension)var3.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field5);
         if (var4 != null) {
            var4.method2(var1x -> {
               Framework5 var2 = (Framework5)var1x.method121(Framework.field14);
               if (var2 != null) {
                  for (ClientOption var4x : var2.method2()) {
                     var1.accept(var4x);
                  }
               }
            });
         }

         Framework5 var5 = (Framework5)var3.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field14);
         if (var5 != null) {
            for (ClientOption var7 : var5.method2()) {
               var1.accept(var7);
            }
         }
      }
   }

   public boolean method6(EntityPlayerBridge var1) {
      return this.method9(var1, this.field65);
   }

   public boolean method7(EntityPlayerBridge var1) {
      return this.method9(var1, this.field66);
   }

   public boolean method8(EntityPlayerBridge var1) {
      return this.method9(var1, this.field67);
   }

   private boolean method9(EntityPlayerBridge var1, EnumOption<GeneralSettings.Type2> var2) {
      if (!var1.bridge$isSelf() && !var1.method2()) {
         GeneralSettings.Type2 var3 = (GeneralSettings.Type2)var2.get();
         if (var3 == GeneralSettings.Type2.EVERYONE) {
            return true;
         } else {
            return var3 == GeneralSettings.Type2.SELF_ONLY ? false : ThreadModuleDump63.method4().method50().method3(var1.bridge$getUniqueID());
         }
      } else {
         return true;
      }
   }

   public GeneralSettings.Type method16() {
      GeneralSettings.Type var1 = (GeneralSettings.Type)this.field11.get();
      return var1 == GeneralSettings.Type.AUTO ? GeneralSettings.Type.OFF : var1;
   }

   @Override
   public String method5() {
      return "general.json";
   }

   @Override
   public void load(JsonObject var1) {
      this.field50.HIRIHCROOIRIORCCOIRRCRHOHCCRRO(Fishing_2.method1() ? GeneralSettings.Type3.VANILLA : GeneralSettings.Type3.LUNAR);
      super.load(var1);
      this.field53.load(var1);
      if (var1.has("mainMenuVolume") && var1.get("mainMenuVolume").isJsonPrimitive()) {
         field76 = var1.get("mainMenuVolume").getAsFloat();
      }

      if (var1.has("mainMenuMuted") && var1.get("mainMenuMuted").isJsonPrimitive()) {
         field77 = var1.get("mainMenuMuted").getAsBoolean();
      }
   }

   @Override
   public void method1(JsonObject var1) {
      super.initAndGet(var1);
      this.field53.load(var1);
      var1.addProperty("mainMenuMuted", field77);
      if (field76 != null) {
         var1.addProperty("mainMenuVolume", field76);
      }
   }

   @Nullable
   public JsonElement method128() {
      return this.provide();
   }

   public JsonElement provide() {
      JsonArray var1 = new JsonArray(this.method10().size());

      for (ClientOption var3 : this.method10()) {
         OptionDataProvider var4 = (OptionDataProvider)var3.HIRHCCHIRHRORIICOIHIHCICOIRHHC(com.moonsworth.lunar.client.config.option.OptionTraits.field10);
         if (var4 != null) {
            var1.add(var4.provide());
         }
      }

      return var1;
   }

   @Generated
   public GuiIterator method17() {
      return this.field2;
   }

   @Generated
   public ToggleOption method18() {
      return this.field3;
   }

   @Generated
   public ToggleOption method19() {
      return this.field4;
   }

   @Generated
   public ToggleOption method20() {
      return this.field5;
   }

   @Generated
   public ToggleOption method21() {
      return this.field6;
   }

   @Generated
   public EnumOption<com.moonsworth.lunar.client.ui.notification.NotificationAnchor> method22() {
      return this.field7;
   }

   @Generated
   public ToggleOption method23() {
      return this.field8;
   }

   @Generated
   public ToggleOption method24() {
      return this.field9;
   }

   @Generated
   public ToggleOption method25() {
      return this.field10;
   }

   @Generated
   public EnumOption<GeneralSettings.Type> method26() {
      return this.field11;
   }

   @Generated
   public ToggleOption method27() {
      return this.field12;
   }

   @Generated
   public ToggleOption method28() {
      return this.field13;
   }

   @Generated
   public ToggleOption method29() {
      return this.field14;
   }

   @Generated
   public ToggleOption method30() {
      return this.field15;
   }

   @Generated
   public ToggleOption method31() {
      return this.field16;
   }

   @Generated
   public ToggleOption method32() {
      return this.field17;
   }

   @Generated
   public ToggleOption method33() {
      return this.field18;
   }

   @Generated
   public ToggleOption method34() {
      return this.field19;
   }

   @Generated
   public ToggleOption method35() {
      return this.field20;
   }

   @Generated
   public ToggleOption method36() {
      return this.field21;
   }

   @Generated
   public ToggleOption method37() {
      return this.field22;
   }

   @Generated
   public ToggleOption method38() {
      return this.field23;
   }

   @Generated
   public ToggleOption method39() {
      return this.field24;
   }

   @Generated
   public ToggleOption method40() {
      return this.field25;
   }

   @Generated
   public ToggleOption method41() {
      return this.field26;
   }

   @Generated
   public ToggleOption method42() {
      return this.field27;
   }

   @Generated
   public ToggleOption method43() {
      return this.field28;
   }

   @Generated
   public ToggleOption method44() {
      return this.field29;
   }

   @Generated
   public ToggleOption method45() {
      return this.field30;
   }

   @Generated
   public ToggleOption method46() {
      return this.field31;
   }

   @Generated
   public ToggleOption method47() {
      return this.field32;
   }

   @Generated
   public ToggleOption method48() {
      return this.field33;
   }

   @Generated
   public EnumOption<GeneralSettings.Type4> method49() {
      return this.field34;
   }

   @Generated
   public ToggleOption method50() {
      return this.field35;
   }

   @Generated
   public ToggleOption method51() {
      return this.field36;
   }

   @Generated
   public ToggleOption method52() {
      return this.field37;
   }

   @Generated
   public ToggleOption method53() {
      return this.field38;
   }

   @Generated
   public ToggleOption method54() {
      return this.field39;
   }

   @Generated
   public IntegerOption method55() {
      return this.field40;
   }

   @Generated
   public IntegerOption method56() {
      return this.field41;
   }

   @Generated
   public ToggleOption method57() {
      return this.field42;
   }

   @Generated
   public ToggleOption method58() {
      return this.field43;
   }

   @Generated
   public ToggleOption method59() {
      return this.field44;
   }

   @Generated
   public ToggleOption method60() {
      return this.field45;
   }

   @Generated
   public ToggleOption method61() {
      return this.field46;
   }

   @Generated
   public ToggleOption method62() {
      return this.field47;
   }

   @Generated
   public ToggleOption method63() {
      return this.field48;
   }

   @Generated
   public ToggleOption method64() {
      return this.field49;
   }

   @Generated
   public EnumOption<GeneralSettings.Type3> method65() {
      return this.field50;
   }

   @Generated
   public ColorOption method66() {
      return this.field51;
   }

   @Generated
   public FloatOption method67() {
      return this.field52;
   }

   @Generated
   public ToggleOption method68() {
      return this.field53;
   }

   @Generated
   public ToggleOption method69() {
      return this.field54;
   }

   @Generated
   public ToggleOption method70() {
      return this.field55;
   }

   @Generated
   public EnumOption<StreamerMode.Type> method71() {
      return this.field56;
   }

   @Generated
   public ToggleOption method72() {
      return this.field57;
   }

   @Generated
   public FloatOption method73() {
      return this.field58;
   }

   @Generated
   public FloatOption method74() {
      return this.field59;
   }

   @Generated
   public ToggleOption method75() {
      return this.field60;
   }

   @Generated
   public ToggleOption method76() {
      return this.field61;
   }

   @Generated
   public ToggleOption method77() {
      return this.field62;
   }

   @Generated
   public IntegerOption method78() {
      return this.field63;
   }

   @Generated
   public FloatOption method79() {
      return this.field64;
   }

   @Generated
   public EnumOption<GeneralSettings.Type2> method80() {
      return this.field65;
   }

   @Generated
   public EnumOption<GeneralSettings.Type2> method81() {
      return this.field66;
   }

   @Generated
   public EnumOption<GeneralSettings.Type2> method82() {
      return this.field67;
   }

   @Generated
   public ListOption<String> method83() {
      return this.field68;
   }

   @Generated
   public ListOption<String> method84() {
      return this.field69;
   }

   @Generated
   public ListOption<String> method85() {
      return this.field70;
   }

   @Generated
   public ListOption<String> method86() {
      return this.field71;
   }

   @Generated
   public ListOption<String> method87() {
      return this.field72;
   }

   @Generated
   public ListOption<String> method88() {
      return this.field73;
   }

   @Generated
   public ToggleOption method89() {
      return this.field74;
   }

   @Generated
   public ToggleOption method90() {
      return this.field75;
   }

   public enum Type implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
      AUTO("auto"),
      OFF("off"),
      MODS("mods"),
      ALL("all");

      private final String id;

      public String id() {
         return this.name();
      }

      @Override
      public String toString() {
         return this.method122(this.id, new Object[0]);
      }

      @Generated
      Type(String var3) {
         this.id = var3;
      }
   }

   public enum Type2 implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
      EVERYONE("everyone"),
      SELF_ONLY("self_only"),
      FRIENDS("friends");

      private final String id;

      public String id() {
         return this.id;
      }

      @Override
      public String toString() {
         return this.method122(this.id, new Object[0]);
      }

      @Generated
      Type2(String var3) {
         this.id = var3;
      }
   }

   public enum Type3 implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
      LUNAR("lunarStyle", "lunarStyleDescription"),
      VANILLA("vanillaStyle", "vanillaStyleDescription");

      private final String id;
      private final String description;

      public String id() {
         return this.id;
      }

      public String description() {
         return this.method122(this.description, new Object[0]);
      }

      @Override
      public String toString() {
         return this.method122(this.id, new Object[0]);
      }

      @Generated
      Type3(String var3, String var4) {
         this.id = var3;
         this.description = var4;
      }
   }

   public enum Type4 implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
      LAST_MODIFIED("lastModified"),
      ALPHABETICAL("alphabetical"),
      CUSTOM("custom");

      private final String translationKey;

      public String id() {
         return this.name();
      }

      @Override
      public String toString() {
         return this.method122(this.translationKey, new Object[0]);
      }

      @Generated
      Type4(String var3) {
         this.translationKey = var3;
      }

      @Generated
      public String getTranslationKey() {
         return this.translationKey;
      }
   }
}
