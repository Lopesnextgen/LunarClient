package com.moonsworth.lunar.client.mod.misc.chat;

import com.google.gson.JsonObject;
import com.lunarclient.adventure.utils.AdventureUtils;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension612;
import com.moonsworth.lunar.bridge.Bridge5Extension9;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.gui.notification.NotificationManager;
import com.moonsworth.lunar.client.config.option.NamedColorOption;
import com.moonsworth.lunar.client.framework.mod.Calculator2Handler;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.chat.Chat2;
import com.moonsworth.lunar.client.framework.feature.chat.Chat3;
import com.moonsworth.lunar.client.framework.feature.chat.Chat4;
import com.moonsworth.lunar.client.framework.feature.chat.Chat5;
import com.moonsworth.lunar.client.event.input.MarkerInputEvent;
import com.moonsworth.lunar.client.event.mixin.chat.EventChatMessageLegacy;
import com.moonsworth.lunar.client.event.mixin.chat.EventComponentMessageLegacy;
import com.moonsworth.lunar.client.event.mixin.chat.EventChatSendLegacy;
import com.moonsworth.lunar.client.event.input.MouseInputTypeLegacy;
import com.moonsworth.lunar.client.event.mixin.gui.ServerJoinEvent;
import com.moonsworth.lunar.client.keystrokes.Highlight3Iterator;
import com.moonsworth.lunar.client.network.server.KeystrokesType;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.DoubleOption;
import com.moonsworth.lunar.client.config.option.FloatOption;
import com.moonsworth.lunar.client.config.option.LabelOption;
import com.moonsworth.lunar.client.config.option.SimpleKeybindOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.TextOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.OptionSupplier;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.render.markers.Markers;
import com.moonsworth.lunar.client.mod.render.nickhider.NickHider;
import com.moonsworth.lunar.client.framework.build.LunarBuildData;
import com.moonsworth.lunar.client.util.ThreadModuleDump3;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.ThreadModuleDump61;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.util.ThreadModuleDump67;
import com.moonsworth.lunar.client.util.ThreadModuleDump68;
import java.io.File;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Consumer;
import lombok.Generated;
import net.kyori.adventure.text.BuildableComponent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent.Builder;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;

public class Chat extends AbstractFeature {
   public static final int field8 = 12;
   public static final ResourceLocationBridge field9 = ResourceLocationBridge.create(
      Bridge.getMinecraftVersion().method22() ? "minecraft:random.orb" : "minecraft:entity.experience_orb.pickup"
   );
   private static final String[] field10 = new String[]{"spooked into the lobby!", "joined the lobby!", "has joined"};
   private static int field11 = -1;
   private static String field12;
   private final ToggleOption field13 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("unlimitedChat")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field14 = (ToggleOption)OptionFactory.method7("stackMessages").method31();
   private final ToggleOption field15 = (ToggleOption)OptionFactory.method7("chatHeight").method31();
   private final ToggleOption field16 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("stackMessagesTimeBased")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final IntegerOption field17 = (IntegerOption)((IntegerOption.Data)((IntegerOption.Data)OptionFactory.method4(
               "timeBasedStackMessagesTimeframe"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(10))
         .method7(1, 60))
      .method31();
   private final ToggleOption field18 = (ToggleOption)OptionFactory.method7("chatStackIgnoreBlank").method31();
   private final ToggleOption field19 = (ToggleOption)OptionFactory.method7("chatStackIgnoreBreak").method31();
   private final FloatOption field20 = (FloatOption)((FloatOption.Data)((FloatOption.Data)OptionFactory.method2(
               "chatBackgroundOpacity"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(1.0F))
         .HRRCROICHIIROIHRCOIHRRHCCRIIRH(0.0F, 1.0F))
      .method31();
   private final ToggleOption field21 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("chatShadow")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field22 = (ToggleOption)OptionFactory.method7("disableChat").method31();
   private final ToggleOption field23 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("noCloseMyChat")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final EnumOption<NamedColorOption> field24 = (EnumOption<NamedColorOption>)OptionFactory.method10("chatNameColor", NamedColorOption.OFF)
      .method31();
   private final ToggleOption field25 = (ToggleOption)OptionFactory.method7("chatNameBold").method31();
   private final ToggleOption field26 = (ToggleOption)OptionFactory.method7("chatNameItalic").method31();
   private final ToggleOption field27 = (ToggleOption)OptionFactory.method7("chatNameUnderline").method31();
   private final ToggleOption field28 = (ToggleOption)OptionFactory.method7("chatNameStrikethrough").method31();
   private final ToggleOption field29 = (ToggleOption)OptionFactory.method7("chatNameObfuscated").method31();
   private final EnumOption<com.moonsworth.lunar.client.framework.feature.chat.Gui2Extension> field30 = (EnumOption<com.moonsworth.lunar.client.framework.feature.chat.Gui2Extension>)OptionFactory.method10(
         "profanity", com.moonsworth.lunar.client.framework.feature.chat.Gui2Extension.OFF
      )
      .method31();
   private final ToggleOption field31 = (ToggleOption)OptionFactory.method7("stopProfaneMessages").method31();
   private final FloatOption field32 = (FloatOption)((FloatOption.Data)((FloatOption.Data)OptionFactory.method2(
               "inputFieldOpacity"
            )
            .CRHHRHHOIIRCIHICRHIRIRCHHRCIHO(5.0F))
         .HRRCROICHIIROIHRCOIHRRHCCRIIRH(0.0F, 10.0F))
      .method31();
   private final ToggleOption field33 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("chatPingSound")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field34 = (ToggleOption)OptionFactory.method7("chatPingExactMatch").method31();
   private final ToggleOption field35 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("smoothChat")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final IntegerOption field36 = (IntegerOption)((IntegerOption.Data)((IntegerOption.Data)OptionFactory.method4(
               "smoothChatSpeed"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(3))
         .method7(1, 10))
      .method31();
   private final ToggleOption field37 = (ToggleOption)OptionFactory.method7("chatTimestamps").method31();
   private final EnumOption<NamedColorOption> field38 = (EnumOption<NamedColorOption>)OptionFactory.method10("timestampColor", NamedColorOption.GRAY)
      .method31();
   private final ToggleOption field39 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("showBrackets")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final EnumOption<NamedColorOption> field40 = (EnumOption<NamedColorOption>)OptionFactory.method10("bracketsColor", NamedColorOption.GRAY)
      .method31();
   private final ToggleOption field41 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("twelveHourClock")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field42 = (ToggleOption)OptionFactory.method7("showAmPm").method31();
   private final ToggleOption field43 = (ToggleOption)OptionFactory.method7("showSeconds").method31();
   private final ToggleOption field44 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("timestampItalics")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field45 = (ToggleOption)OptionFactory.method7("timestampBold").method31();
   private final ToggleOption field46 = (ToggleOption)OptionFactory.method7("copyChat").method31();
   private final ToggleOption field47 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("copyChatRightClick")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ModifierKeybindOption field48 = (ModifierKeybindOption)OptionFactory.method18("copyChatBind")
      .method5(KeyCode.KEY_LCONTROL)
      .method11()
      .method31();
   private final ModifierKeybindOption field49 = (ModifierKeybindOption)OptionFactory.method18("copyChatDebugBind")
      .method5(KeyCode.KEY_NONE)
      .method11()
      .method31();
   private final ToggleOption field50 = (ToggleOption)OptionFactory.method7("hoverImagePreview").method31();
   private final ToggleOption field51 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("fullscreenImage")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final TextOption field52 = (TextOption)OptionFactory.method12("customWhitelistedDomains").method31();
   private final DoubleOption field53 = (DoubleOption)((DoubleOption.Data)((DoubleOption.Data)((DoubleOption.Data)OptionFactory.method1(
                  "minImageSize"
               )
               .OIRHOOIICOCIOOHICRRRICORIHHIHC(0.0))
            .RIIIOHCCHRRRORICCHIIHHOORIIOIR(0.0, 100.0))
         .method6(1))
      .method31();
   private final DoubleOption field54 = (DoubleOption)((DoubleOption.Data)((DoubleOption.Data)((DoubleOption.Data)OptionFactory.method1(
                  "maxImageSize"
               )
               .OIRHOOIICOCIOOHICRRRICORIHHIHC(30.0))
            .RIIIOHCCHRRRORICCHIIHHOORIIOIR(0.0, 100.0))
         .method6(1))
      .method31();
   private final ToggleOption field55 = (ToggleOption)OptionFactory.method7("longChatSingleplayer").method31();
   private final ToggleOption field56 = (ToggleOption)OptionFactory.method7("modernChatLengthHypixel").method31();
   private final SimpleKeybindOption field57 = (SimpleKeybindOption)((SimpleKeybindOption.Data)OptionFactory.method17("chatVisibilityKeybind")
         .method18(this))
      .method12()
      .method31();
   private final SimpleKeybindOption field58 = (SimpleKeybindOption)OptionFactory.method17("chatPeekKeybind").method31();
   private final ToggleOption field59 = (ToggleOption)OptionFactory.method7("chatHeads").method31();
   private final ToggleOption field60 = (ToggleOption)OptionFactory.method7("chatHeadsFetchUnknown").method31();
   private final ToggleOption field61 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("chatEmoji")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field62 = (ToggleOption)OptionFactory.method7("chatEmojiUnicodeOnly").method31();
   private final ToggleOption field63 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)OptionFactory.method7("chatEmojiConvertShortcodes")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private DateTimeFormatter field64;
   private boolean field65 = false;
   private final Chat3 field66 = new Chat3();
   private final com.moonsworth.lunar.client.framework.feature.chat.Chat field67 = new com.moonsworth.lunar.client.framework.feature.chat.Chat();
   private final Chat2 field68 = new Chat2();
   private final Chat4 field69 = new Chat4();
   private final Chat5 field70 = new Chat5();
   private final ChatCommandAliases field71 = new ChatCommandAliases(this);

   public Chat() {
      super(true);
      this.handle(EventChatMessageLegacy.Data2.class, var1 -> {
         if (!var1.isCancelled() && ThreadModuleDump63.method7() != null) {
            this.field69.method1(var1);
         }
      });
      this.handle(ServerJoinEvent.class, this::method6);
      this.handle(EventChatMessageLegacy.Data.class, this::method8);
      this.handle(EventChatSendLegacy.class, this::method7);
      this.handle(EventComponentMessageLegacy.class, this::method10);
      this.handle(com.moonsworth.lunar.client.event.mixin.nameplate.HudBaseRenderEvent.Data4.class, this.field70::method2);
      this.handle(com.moonsworth.lunar.client.event.mixin.rewindhandlers.KeybindEvent.class, this.field70::method7);
      this.handle(MarkerInputEvent.class, this::method1);
      this.field57.method3(() -> this.field65 = !this.field65);
   }

   private void method1(MarkerInputEvent var1) {
      if (this.field46.get()
         && this.field47.get()
         && var1.method3() == 1
         && var1.method4() == MouseInputTypeLegacy.CLICK
         && var1.method1().method1(Bridge5Extension612.class)
         && this.method14()) {
         var1.setCancelled(true);
      }
   }

   @Override
   protected List<Framework7Extension> method9() {
      return List.of(this.field71);
   }

   @Override
   public String getId() {
      return "CHAT";
   }

   @Override
   protected ModDetails method20() {
      return ModDetails.method7().method1(Calculator2Handler.field4).method11(this);
   }

   @Override
   public void method2(RootSettingsAssembler var1) {
      super.method45(var1);
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(
         SettingsPage.GENERAL,
         var1x -> {
            var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field13});
            var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field56, this.field55}).OHHOOIIHIRCCCRCRRRCIICIHOOIRRH();
            var1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
               this.field14, var1xx -> var1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field16, this.field17, this.field18, this.field19})
            );
            var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field15}).OHHOOIIHIRCCCRCRRRCIICIHOOIRRH();
            var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field21, this.field22, this.field23});
            var1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field33, var1xx -> var1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field34}));
            var1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
                  this.field59, var1xx -> var1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field60}).RHIHIIRHRCRHCCIIICHIRCCCOIIOHO(33)
               )
               .ICRHORIIHOHROHOHOCOOHOOCOORRHO(new int[]{6});
            var1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
                  this.field61, var1xx -> var1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field62, this.field63})
               )
               .HOCIIROHCHHIORICCRHIIRIIRCRCOR();
            var1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
               this.field46, var1xx -> var1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field48, this.field47})
            );
            var1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field50, var1xx -> {
               var1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field52, this.field53, this.field54});
               var1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field51}).method3(() -> this.field54.get() == 100.0);
            });
            var1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field35, var1xx -> var1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field36}));
            var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field58});
            var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field57});
            var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(
               new OptionSupplier[]{
                  ((LabelOption.Data)OptionFactory.method15("chatVisibility")
                        .method14(() -> this.method95("chatVisibility", new Object[]{!this.method81() ? "§aON" : "§cOFF"})))
                     .method11()
               }
            );
         }
      );
      var1.method1(
         "nameOptions",
         var1x -> var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(
            new ClientOption[]{this.field24, this.field25, this.field26, this.field27, this.field28, this.field29}
         )
      );
      var1.method1("opacityOptions", var1x -> var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field20, this.field32}));
      var1.method1(
         "filterOptions",
         var1x -> var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(
            new OptionSupplier[]{
               this.field30,
               OptionFactory.method14("openFilter").method4(() -> ThreadModuleDump61.method10(this.method15())),
               OptionFactory.method14("reloadFilter").method4(() -> {
                  this.field69.method5(this.method15());
                  Client.method109().method69().method3("Reloaded custom filter!");
               }),
               this.field31
            }
         )
      );
      var1.method1("timestampOptions", var1x -> var1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field37, var1xx -> {
         var1xx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field38, this.field44, this.field45, this.field41, this.field42, this.field43});
         var1xx.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field39, var1xxx -> var1xxx.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field40}));
      }));
      this.field30.HORHIRROCIOIICIOHCOCCOOHIRCCRI(this.field69::method4);
      Consumer var2 = var1x -> this.method13();
      this.field41.CICORRHIOIIOORRRICCORIOIOCIHII(var2);
      this.field42.CICORRHIOIIOORRRICCORIOIOCIHII(var2);
      this.field43.CICORRHIOIIOORRRICCORIOIOCIHII(var2);
      this.field52.CICORRHIOIIOORRRICCORIOIOCIHII(var1x -> {
         this.field70.clearCache();
         this.field70.method1(var1x.split("[ ,]+"));
      });
      this.field59.HORHIRROCIOIICIOHCOCCOOHIRCCRI(var0 -> {
         Bridge5Extension9 var1x = ThreadModuleDump63.method3().bridge$getGuiIngame();
         if (var1x != null) {
            var1x.bridge$getChatGUI().bridge$rescaleChat();
         }
      });
   }

   private void method13() {
      String var1 = "";
      if (this.field41.get()) {
         var1 = var1 + "h";
      } else {
         var1 = var1 + "HH";
      }

      var1 = var1 + ":mm";
      if (this.field43.get()) {
         var1 = var1 + ":ss";
      }

      if (this.field42.get()) {
         var1 = var1 + " a";
      }

      this.field64 = DateTimeFormatter.ofPattern(var1);
   }

   @Override
   public void load(JsonObject var1) {
      super.load(var1);
      this.field69.method5(this.method15());
   }

   private void method6(ServerJoinEvent var1) {
      this.field66.clear();
   }

   private void method7(EventChatSendLegacy var1) {
      if (!var1.isCancelled() && this.method26()) {
         String var2 = this.field68.method3(var1.getMessage());
         if (!var2.equals(var1.getMessage())) {
            var1.setMessage(var2);
         }
      }
   }

   private void method8(EventChatMessageLegacy.Data var1) {
      if (!var1.isCancelled() && ThreadModuleDump63.method7() != null) {
         if (!Highlight3Iterator.method8(KeystrokesType.HYPIXEL) || !var1.OROIIOCCOORRCRCIIHHOCCCRHICRCC().startsWith("{")) {
            Markers var2 = ThreadModuleDump63.method4().method40().method87();
            boolean var3 = var2.isEnabled() && var2.isNotifyingChat();
            if (var3 || !this.field69.method1(var1)) {
               if (this.field22.get()) {
                  var1.setCancelled(true);
               } else {
                  if (!var3 && this.field33.get()) {
                     this.method9(var1);
                  }

                  if (this.method26()) {
                     this.field68.method1(var1);
                  }

                  if (this.field37.get()) {
                     this.method13(var1);
                  }

                  if (this.field14.get()) {
                     this.field66.method1(var1);
                  }

                  if (this.method24()) {
                     this.field67.method1(var1);
                  }
               }
            }
         }
      }
   }

   private void method9(EventChatMessageLegacy.Data var1) {
      if (this.mc.bridge$getPlayer() != null) {
         if (AdventureTextBridge.doesComponentContain(var1.RIOCCRROHHHOCHOHHCIRHRCOOHRIHI(), field12)) {
            field12 = null;
         } else {
            NickHider var2 = this.field4.method40().method41();
            boolean var3;
            if (this.field34.get()) {
               var3 = AdventureTextBridge.doesComponentContainWord(
                  var1.RIOCCRROHHHOCHOHHCIRHRCOOHRIHI(), ThreadModuleDump63.method7().bridge$getName(), var2.method39().method6()
               );
            } else {
               var3 = AdventureTextBridge.doesComponentContain(
                  var1.RIOCCRROHHHOCHOHHCIRHRCOOHRIHI(), ThreadModuleDump63.method7().bridge$getName(), var2.method39().method6()
               );
            }

            if (var3) {
               if (!AdventureTextBridge.doesComponentContain(var1.RIOCCRROHHHOCHOHHCIRHRCOOHRIHI(), field10)) {
                  this.mc.bridge$getSoundHandler().method1(field9);
               }
            }
         }
      }
   }

   private void method10(EventComponentMessageLegacy var1) {
      if (this.field46.get()) {
         if (this.field48.isKeyDown() || !LunarBuildData.field4 && this.field49.isKeyDown()) {
            if (this.method14()) {
               var1.setCancelled(true);
            }
         }
      }
   }

   private boolean method14() {
      if (field11 == -1) {
         return false;
      }

      Component var1 = this.mc.bridge$getGuiIngame().bridge$getChatGUI().bridge$getMessageContentByLunarId(field11);
      String var2 = AdventureTextBridge.getTextContent(var1);
      if (Chat2.method21()) {
         var2 = this.field68.method4(var2);
      }

      if (!LunarBuildData.field4 && this.field49.isKeyDown()) {
         ThreadModuleDump68.setClipboardString(AdventureUtils.toCompactString(var1));
      } else {
         ThreadModuleDump68.setClipboardString(var2);
      }

      ThreadModuleDump63.method4().method69().method2(NotificationManager.method15("copiedMessage"), this.method12(var2));
      return true;
   }

   private String method12(String var1) {
      StringBuilder var2 = new StringBuilder();
      char[] var3 = var1.toCharArray();

      for (char var7 : var3) {
         if (var7 < 256) {
            var2.append(var7);
         }
      }

      return var2.toString();
   }

   private void method13(EventChatMessageLegacy var1) {
      Builder var2 = (Builder)Component.text().content(LocalTime.now().format(this.field64)).color(this.field38.get().getColor().getAdventureColor());
      if (this.field39.get()) {
         NamedTextColor var3 = this.field40.get().getColor().getAdventureColor();
         var2 = (Builder)((Builder)((Builder)Component.text().append(Component.text("[").color(var3))).append(var2)).append(Component.text("]").color(var3));
      }

      var2.appendSpace();
      if (this.field44.get()) {
         var2.decorate(TextDecoration.ITALIC);
      }

      if (this.field45.get()) {
         var2.decorate(TextDecoration.BOLD);
      }

      BuildableComponent var4 = ((Builder)((Builder)Component.text().append(var2)).append(var1.method2())).build();
      var1.method1(var4);
   }

   private File method15() {
      return new File(ThreadModuleDump48.field25 + File.separator + ThreadModuleDump63.method4().method61().method14().getName(), "profanity_filter.txt");
   }

   public boolean method16() {
      return this.isEnabled() && this.field35.get();
   }

   public float method17() {
      return ThreadModuleDump67.method16(this.method52().get().intValue(), 1.0F, 10.0F, 0.001F, 0.01F);
   }

   public float method19() {
      return this.isEnabled() ? ThreadModuleDump67.method16(this.field32.get(), 0.0F, 10.0F, 0.0F, 2.0F) : 1.0F;
   }

   public boolean method21() {
      return this.isEnabled() && this.method81() && !this.method22();
   }

   public boolean method22() {
      return this.isEnabled() && this.field58.method8() != KeyCode.KEY_NONE && ThreadModuleDump63.method11() == null && this.field58.isKeyDown();
   }

   public boolean method23() {
      return this.isEnabled() && this.field59.get() && ThreadModuleDump63.MC_VERSION != 6;
   }

   public boolean method24() {
      return this.method23() && ThreadModuleDump63.MC_VERSION <= 32;
   }

   public boolean method25() {
      return this.method23() && this.field60.get();
   }

   public boolean method26() {
      return this.isEnabled() && this.field61.get() && ThreadModuleDump63.MC_VERSION >= 7 && Chat2.method21();
   }

   public int method24(boolean var1, int var2) {
      if (!this.isEnabled()) {
         return var2;
      } else if (this.method71().get() && var1) {
         return 10000;
      } else {
         return this.method72().get() && ThreadModuleDump3.method5(KeystrokesType.HYPIXEL.getBrand()) ? 256 : var2;
      }
   }

   @Generated
   public ToggleOption method27() {
      return this.field13;
   }

   @Generated
   public ToggleOption method28() {
      return this.field14;
   }

   @Generated
   public ToggleOption method29() {
      return this.field15;
   }

   @Generated
   public ToggleOption method30() {
      return this.field16;
   }

   @Generated
   public IntegerOption method34() {
      return this.field17;
   }

   @Generated
   public ToggleOption method35() {
      return this.field18;
   }

   @Generated
   public ToggleOption method36() {
      return this.field19;
   }

   @Generated
   public FloatOption method37() {
      return this.field20;
   }

   @Generated
   public ToggleOption method38() {
      return this.field21;
   }

   @Generated
   public ToggleOption method39() {
      return this.field22;
   }

   @Generated
   public ToggleOption method40() {
      return this.field23;
   }

   @Generated
   public EnumOption<NamedColorOption> method41() {
      return this.field24;
   }

   @Generated
   public ToggleOption method42() {
      return this.field25;
   }

   @Generated
   public ToggleOption method43() {
      return this.field26;
   }

   @Generated
   public ToggleOption method44() {
      return this.field27;
   }

   @Generated
   public ToggleOption method45() {
      return this.field28;
   }

   @Generated
   public ToggleOption method46() {
      return this.field29;
   }

   @Generated
   public EnumOption<com.moonsworth.lunar.client.framework.feature.chat.Gui2Extension> method47() {
      return this.field30;
   }

   @Generated
   public ToggleOption method48() {
      return this.field31;
   }

   @Generated
   public ToggleOption method49() {
      return this.field33;
   }

   @Generated
   public ToggleOption method50() {
      return this.field34;
   }

   @Generated
   public ToggleOption method51() {
      return this.field35;
   }

   @Generated
   public IntegerOption method52() {
      return this.field36;
   }

   @Generated
   public ToggleOption method53() {
      return this.field37;
   }

   @Generated
   public EnumOption<NamedColorOption> method54() {
      return this.field38;
   }

   @Generated
   public ToggleOption method55() {
      return this.field39;
   }

   @Generated
   public EnumOption<NamedColorOption> method56() {
      return this.field40;
   }

   @Generated
   public ToggleOption method57() {
      return this.field41;
   }

   @Generated
   public ToggleOption method58() {
      return this.field42;
   }

   @Generated
   public ToggleOption method59() {
      return this.field43;
   }

   @Generated
   public ToggleOption method60() {
      return this.field44;
   }

   @Generated
   public ToggleOption method61() {
      return this.field45;
   }

   @Generated
   public ToggleOption method62() {
      return this.field46;
   }

   @Generated
   public ToggleOption method63() {
      return this.field47;
   }

   @Generated
   public ModifierKeybindOption method64() {
      return this.field48;
   }

   @Generated
   public ModifierKeybindOption method65() {
      return this.field49;
   }

   @Generated
   public ToggleOption method66() {
      return this.field50;
   }

   @Generated
   public ToggleOption method67() {
      return this.field51;
   }

   @Generated
   public TextOption method68() {
      return this.field52;
   }

   @Generated
   public DoubleOption method69() {
      return this.field53;
   }

   @Generated
   public DoubleOption method70() {
      return this.field54;
   }

   @Generated
   public ToggleOption method71() {
      return this.field55;
   }

   @Generated
   public ToggleOption method72() {
      return this.field56;
   }

   @Generated
   public SimpleKeybindOption method73() {
      return this.field57;
   }

   @Generated
   public SimpleKeybindOption method74() {
      return this.field58;
   }

   @Generated
   public ToggleOption method75() {
      return this.field59;
   }

   @Generated
   public ToggleOption method76() {
      return this.field60;
   }

   @Generated
   public ToggleOption method77() {
      return this.field61;
   }

   @Generated
   public ToggleOption method78() {
      return this.field62;
   }

   @Generated
   public ToggleOption method79() {
      return this.field63;
   }

   @Generated
   public DateTimeFormatter method80() {
      return this.field64;
   }

   @Generated
   public boolean method81() {
      return this.field65;
   }

   @Generated
   public Chat3 method82() {
      return this.field66;
   }

   @Generated
   public com.moonsworth.lunar.client.framework.feature.chat.Chat method83() {
      return this.field67;
   }

   @Generated
   public Chat2 method84() {
      return this.field68;
   }

   @Generated
   public Chat4 method85() {
      return this.field69;
   }

   @Generated
   public Chat5 method86() {
      return this.field70;
   }

   @Generated
   public ChatCommandAliases method87() {
      return this.field71;
   }

   @Generated
   public static void method83(int var0) {
      field11 = var0;
   }

   @Generated
   public static int method88() {
      return field11;
   }

   @Generated
   public static void method85(String var0) {
      field12 = var0;
   }
}
