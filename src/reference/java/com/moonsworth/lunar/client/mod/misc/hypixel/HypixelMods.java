package com.moonsworth.lunar.client.mod.misc.hypixel;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.google.common.collect.UnmodifiableIterator;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.lunarclient.adventure.matcher.ComponentMatcher;
import com.lunarclient.adventure.pattern.ComponentPattern;
import com.lunarclient.adventure.transform.ComponentTransformer;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.ResourceBridge;
import com.moonsworth.lunar.bridge.PlayerInfoBridge;
import com.moonsworth.lunar.bridge.Bridge2_42;
import com.moonsworth.lunar.bridge.TextBridge;
import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.util.LunarLogger;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.tps.LevelHeadSource;
import com.moonsworth.lunar.client.framework.feature.tps.Tps;
import com.moonsworth.lunar.client.framework.feature.tps.BedwarsLevelFormats;
import com.moonsworth.lunar.client.framework.listener.HypixelLocationListener;
import com.moonsworth.lunar.client.framework.listener.HypixelPartyTracker;
import com.moonsworth.lunar.client.framework.listener.HypixelLocation;
import com.moonsworth.lunar.client.framework.listener.PartyState;
import com.moonsworth.lunar.client.event.mixin.EventChatMessage;
import com.moonsworth.lunar.client.event.mixin.EventTabComplete;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSecond;
import com.moonsworth.lunar.client.event.mixin.gui.EventDisconnect;
import com.moonsworth.lunar.client.event.mixin.gui.EventLocationChange;
import com.moonsworth.lunar.client.network.server.KeystrokesType;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.TextOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.ColorOption.Data;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import com.moonsworth.lunar.client.mod.misc.hypixel.HypixelTps;
import com.moonsworth.lunar.client.framework.build.LunarBuildData;
import com.moonsworth.lunar.client.util.net.ServiceEndpoints;
import com.moonsworth.lunar.client.util.game.NpcUtils;
import com.moonsworth.lunar.client.util.math.ColorUtils;
import com.moonsworth.lunar.client.util.net.ServerUtils;
import com.moonsworth.lunar.client.util.text.UuidUtils;
import com.moonsworth.lunar.client.framework.LunarConstants;
import com.moonsworth.lunar.client.framework.Ref;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.Builder;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import lombok.Generated;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

public class HypixelMods extends AbstractFeature {
   private static final Pattern field8 = Pattern.compile("^From .*: .*");
   private static final Pattern field9 = Pattern.compile("^You found .*in the well!");
   private static final Pattern field10 = Pattern.compile(".*\\[TEAM] .*: .*");
   private static final Pattern field11 = Pattern.compile(".*: .*");
   private static final int field12 = 256 - "/achat ".length();
   private static final ImmutableList<String> field13 = ImmutableList.of(
      "Winner #1 (",
      "Top Survivors",
      "Winners - ",
      "Winners: ",
      "Winner: ",
      "Winning Team: ",
      " won the game!",
      "Top Seeker: ",
      "Last team standing!",
      "1st Place: ",
      "1st Killer - ",
      "1st Place - ",
      new String[]{"Winner: ", " - Damage Dealt - ", "Damage Dealt: ", "Winning Team -", "1st - ", " Duel - ", "Most Wool Placed  -"}
   );
   private static final ComponentMatcher field14 = ComponentPattern.regex("joined\\.$", NamedTextColor.YELLOW).toMatcher();
   private static final ComponentMatcher field15 = ComponentPattern.regex("left\\.$", NamedTextColor.YELLOW).toMatcher();
   private final HypixelPartyTracker field16 = (HypixelPartyTracker)this.method63(HypixelPartyTracker.class);
   private final ToggleOption field17 = (ToggleOption)OptionFactory.method7("autoFriend").method31();
   private final ToggleOption field18 = (ToggleOption)OptionFactory.method7("autoGG").method31();
   private final ToggleOption field19 = (ToggleOption)OptionFactory.method7("antiGG").method31();
   private final ToggleOption field20 = (ToggleOption)OptionFactory.method7("autoTip").method31();
   private final ToggleOption field21 = (ToggleOption)OptionFactory.method7("autoWho").method31();
   private final ToggleOption field22 = (ToggleOption)OptionFactory.method7("levelHead").method31();
   private final EnumOption<LevelHeadSource> field23 = (EnumOption<LevelHeadSource>)OptionFactory.method10("levelHeadSource", LevelHeadSource.NETWORK)
      .method31();
   private final ToggleOption field24 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("hypixelAutocomplete").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field25 = (ToggleOption)OptionFactory.method7("shortChannelNames").method31();
   private final ToggleOption field26 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("removeGuildOnTab").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field27 = (ToggleOption)OptionFactory.method7("removeLobbyStatuses").method31();
   private final ToggleOption field28 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("removeGuildMotd").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field29 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("levelAbove").RRRCHRRHIOCOCIHCHIHHHHRORHROIR())
      .method31();
   private final ColorOption field30 = (ColorOption)((Data)OptionFactory.method8("levelColor").ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-171))
      .method31();
   private final ColorOption field31 = (ColorOption)((Data)OptionFactory.method8("levelHeadNumberColor")
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-11141121))
      .method31();
   private final ToggleOption field32 = (ToggleOption)((ToggleOptionBuilder)((ToggleOptionBuilder)OptionFactory.method7("useBedwarsLevelsFormat")
            .RRRCHRRHIOCOCIHCHIHHHHRORHROIR())
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field33 = (ToggleOption)OptionFactory.method7("hideTeamChat").method31();
   private final ToggleOption field34 = (ToggleOption)OptionFactory.method7("hideGuildChat").method31();
   private final ToggleOption field35 = (ToggleOption)OptionFactory.method7("hidePartyChat").method31();
   private final ToggleOption field36 = (ToggleOption)OptionFactory.method7("hideShout").method31();
   private final ToggleOption field37 = (ToggleOption)OptionFactory.method7("hideSpectatorChat").method31();
   private final ToggleOption field38 = (ToggleOption)OptionFactory.method7("hideJoinMessages").method31();
   private final ToggleOption field39 = (ToggleOption)OptionFactory.method7("hideLeaveMessages").method31();
   private final ToggleOption field40 = (ToggleOption)OptionFactory.method7("hidePrivateMessages").method31();
   private final ToggleOption field41 = (ToggleOption)OptionFactory.method7("hideSoulWellAnnouncements").method31();
   private final ToggleOption field42 = (ToggleOption)OptionFactory.method7("hideMysteryBoxAnnouncements").method31();
   private final TextOption field43 = (TextOption)((com.moonsworth.lunar.client.config.option.TextOption.Data)OptionFactory.method12(
            "autoGGMessage"
         )
         .method2("gg"))
      .method7(arg1 -> ((String)this.field43.get()).length() > field12 && !TextOption.method1(arg1) ? null : arg1)
      .method5(arg1 -> {
         if (arg1.isEmpty()) {
            this.field43.method10("gg");
         }
      })
      .method7(arg1 -> ((String)this.field43.get()).length() > field12 && !TextOption.method1(arg1) ? null : arg1)
      .method31();
   private final Map<UUID, String> field44 = new ConcurrentHashMap<>();
   private final Map<String, UUID> field45 = new ConcurrentHashMap<>();
   private final Set<UUID> field46 = Sets.newSetFromMap(new ConcurrentHashMap());
   private final JsonParser field47 = new JsonParser();
   private static final Splitter field48 = Splitter.on(' ').trimResults().omitEmptyStrings();
   private static final String field49 = "--------------  Guild: Message Of The Day  --------------";
   private static final String field50 = "-------------------------------------------";
   private static final String field51 = "lunar:data/hypixel/autocomplete.json";
   private Tps field52 = null;
   private long field53;
   private long field54;
   private BedwarsLevelFormats field55;
   private static final ResourceLocationBridge field56 = ResourceLocationBridge.create("lunar:hypixel/bedwars_levels_format.json");
   private final HypixelTps field57 = new HypixelTps(this);
   private long field58 = 0L;
   private int field59 = -1;
   private boolean field60 = false;
   private final ComponentTransformer field61 = ComponentTransformer.replaceRegex("^Party", "P");
   private final ComponentTransformer field62 = ComponentTransformer.replaceRegex("^Guild >", "G >");
   private final ComponentTransformer field63 = ComponentTransformer.replaceRegex("^Friend >", "F >");
   private final ComponentTransformer field64 = ComponentTransformer.replaceRegex("^Officer >", "O >");

   private void method13() {
      Gson gson1 = new Gson();
      ResourceBridge bridge152 = this.mc.bridge$getResourceManager().bridge$getResource(field56);
      if (bridge152 == null) {
         LunarLogger.method5("Could not find bedwars levels format json file: " + field56, new Object[0]);
      } else {
         InputStream input3 = bridge152.bridge$getInputStream();
         JsonReader jsonreader4 = new JsonReader(new InputStreamReader(input3));
         this.field55 = (BedwarsLevelFormats)gson1.fromJson(jsonreader4, BedwarsLevelFormats.class);
      }
   }

   private String method2(int number1) {
      int index2 = Math.min(number1 / 100, this.field55.method1().size() - 1);
      com.moonsworth.lunar.client.framework.feature.tps.BedwarsLevelFormats.Data data3 = (com.moonsworth.lunar.client.framework.feature.tps.BedwarsLevelFormats.Data)this.field55
         .method1()
         .get(index2);
      String text4 = data3.method1();
      String text5 = String.valueOf(number1);
      StringBuilder builder6 = new StringBuilder();
      builder6.append(ChatFormatting.getByCode(text4.charAt(0))).append('[');

      for (int index7 = 0; index7 < text5.length(); index7++) {
         builder6.append(ChatFormatting.getByCode(text4.charAt(Math.min(index7 + 1, 4)))).append(text5.charAt(index7));
      }

      builder6.append(ChatFormatting.getByCode(text4.charAt(5))).append(data3.getSymbol());
      builder6.append(ChatFormatting.getByCode(text4.charAt(6))).append(']');
      return builder6.toString();
   }

   public HypixelMods() {
      super(true);
      this.method54(
         ModTraits.field18,
         arg0 -> ((com.moonsworth.lunar.client.framework.mod.ModSupport.Data)arg0.build()).method5(KeystrokesType.HYPIXEL)
      );
      this.handle(EventDisconnect.class, this::method4);
      this.handle(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage.class, this::method11);
      this.handle(EventLocationChange.class, this::method10);
      this.handle(com.moonsworth.lunar.client.event.mixin.highlight.EventRenderNameTag.class, this::method5);
      this.handle(EventTabComplete.class, this::method6);
      this.handle(EventSecond.class, this::method3);
      this.field53 = 0L;
      this.field23.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg1 -> {
         this.field44.clear();
         this.field45.clear();
         this.field46.clear();
      });
      this.field32.HORHIRROCIOIICIOHCOCCOOHIRCCRI(arg1 -> {
         this.field44.clear();
         this.field45.clear();
         this.field46.clear();
      });
      this.method13();
   }

   public String getId() {
      return "HYPIXEL_MOD";
   }

   private void method3(EventSecond highlightimpl41) {
      this.field58++;
      this.field58 %= 4L;
      if (this.field58 == 0L) {
         if (Ref.method4() != null && this.mc.bridge$getSession() != null && this.mc.bridge$getSession().bridge$isValidSession()) {
            String text2 = UuidUtils.method1(this.mc.bridge$getSession().bridge$getPlayerID());
            if (this.isEnabled() && (Boolean)this.field22.get() && !this.field46.isEmpty()) {
               ImmutableSet set3 = ImmutableSet.copyOf(this.field46);
               this.field46.clear();
               StringBuilder builder4 = new StringBuilder("?");

               for (UUID uuid6 : set3) {
                  builder4.append("uuid=").append(uuid6).append('&');
               }

               builder4.append("gameMode=")
                  .append(ServerUtils.getServer() == null ? "unknown" : URLEncoder.encode(ServerUtils.getServer()))
                  .append('&');
               builder4.append("sourceUuid=").append(text2).append('&');
               builder4.append("commit=").append(LunarBuildData.field3).append("&");
               builder4.append("level=").append(((LevelHeadSource)this.field23.get()).getQueryParam()).append("&");
               builder4.setLength(builder4.length() - 1);
               String text8 = ServiceEndpoints.method4();
               URI uri9 = URI.create(text8 + "/hypixel/levelhead" + builder4);
               Builder builder7 = HttpRequest.newBuilder()
                  .header("User-Agent", "LunarClient/" + LunarBuildData.field2)
                  .header("X-Hypixel-Gamemode", ServerUtils.getServer() == null ? "unknown" : URLEncoder.encode(ServerUtils.getServer()))
                  .header("X-SourceUuid", text2)
                  .header("X-LC-Commit", LunarBuildData.field3)
                  .uri(uri9)
                  .GET();
               HttpClient.newBuilder().build().sendAsync(builder7.build(), BodyHandlers.ofString()).thenAccept(arg2x -> {
                  JsonObject json3x = null;
                  if (arg2x != null) {
                     String text4x = arg2x.body();
                     if (text4x != null) {
                        try {
                           json3x = this.field47.parse(text4x).getAsJsonObject();
                        } catch (JsonParseException jsonparseexception10) {
                        }

                        if (json3x != null) {
                           for (UUID uuid6x : set3) {
                              String text7x = uuid6x.toString();
                              if (json3x.has(text7x)) {
                                 JsonElement element8x = json3x.get(text7x);
                                 if (!element8x.isJsonNull()) {
                                    String text9x;
                                    if (this.field23.get() == LevelHeadSource.BEDWARS && (Boolean)this.field32.get()) {
                                       text9x = this.method2(element8x.getAsInt());
                                    } else {
                                       text9x = String.valueOf(element8x.getAsInt());
                                    }

                                    this.field44.put(uuid6x, text9x);
                                    continue;
                                 }
                              }

                              this.field45.values().removeIf(arg1xx -> arg1xx.equals(uuid6x));
                           }
                        }
                     }
                  }
               });
            }
         }
      }
   }

   private void method4(EventDisconnect highlightimpl111) {
      this.field45.clear();
   }

   private void method5(com.moonsworth.lunar.client.event.mixin.highlight.EventRenderNameTag highlightimpl111) {
      if (highlightimpl111.method2() instanceof EntityPlayerBridge bridgeextension2222) {
         if ((Boolean)this.field22.get()) {
            UUID uuid10 = bridgeextension2222.bridge$getUniqueID();
            if (!NpcUtils.method3(bridgeextension2222, true)) {
               Component component4 = bridgeextension2222.bridge$getCustomName();
               if (component4 == null || !component4.hasDecoration(TextDecoration.OBFUSCATED)) {
                  if (this.field44.containsKey(uuid10)) {
                     String text11 = this.field44.get(bridgeextension2222.bridge$getUniqueID());
                     if (!text11.isEmpty() && !highlightimpl111.isCancelled()) {
                        int number12 = ColorUtils.method26(this.field30.method14(0.0F), 0.8745098F);
                        int number7 = ColorUtils.method26(this.field31.method14(0.0F), 0.8745098F);
                        String text8 = ((LevelHeadSource)this.field23.get()).getNametagPrefix();
                        TextComponent text9 = (TextComponent)Component.text(text8, TextColor.color(number12)).append(Component.text(text11, TextColor.color(number7)));
                        if ((Boolean)this.field29.get()) {
                           highlightimpl111.getLines().add(text9);
                        } else {
                           highlightimpl111.getLines().add(0, text9);
                        }
                     }
                  } else {
                     if (uuid10.version() == 1) {
                        int number5 = ((LevelHeadSource)this.field23.get()).generateRandomLevelForNicked(uuid10);
                        String text6;
                        if (this.field23.get() == LevelHeadSource.BEDWARS && (Boolean)this.field32.get()) {
                           text6 = this.method2(number5);
                        } else {
                           text6 = String.valueOf(number5);
                        }

                        this.field44.put(uuid10, text6);
                     } else {
                        if (this.field45.containsKey(bridgeextension2222.bridge$getName())) {
                           return;
                        }

                        this.field45.put(bridgeextension2222.bridge$getName(), uuid10);
                        this.field44.put(uuid10, "");
                        this.field46.add(uuid10);
                     }
                  }
               }
            }
         }
      }
   }

   private void method6(EventTabComplete highlightimpl61) {
      if ((Boolean)this.field24.get()) {
         if (highlightimpl61.method1().startsWith("/")) {
            this.method14();
            String text2 = highlightimpl61.method1().substring(1).toLowerCase(Locale.ROOT);
            ArrayList list3 = new ArrayList(field48.splitToList(text2));
            if (highlightimpl61.method1().endsWith(" ")) {
               list3.add("");
            }

            if (!list3.isEmpty()) {
               this.field16.method7().<Set>map(PartyState::method1).ifPresent(arg4 -> {
                  int index5 = 0;
                  Tps tps6 = this.field52;

                  while (list3.size() > index5) {
                     String text7 = (String)list3.get(index5);
                     if (index5 == list3.size() - 1) {
                        ArrayList list8 = new ArrayList();

                        for (String text10 : tps6.method1().keySet()) {
                           if (text10.startsWith(text7)) {
                              if (index5 == 0) {
                                 list8.add("/" + text10);
                              } else {
                                 list8.add(text10);
                              }
                           }
                        }

                        for (String text12 : tps6.getValues()) {
                           if (text12.equals("%PARTY%")) {
                              for (String text23 : arg4) {
                                 if (text23.toLowerCase(Locale.ROOT).startsWith(text7)) {
                                    list8.add(text23);
                                 }
                              }

                              String text21 = Ref.method7().bridge$getName();
                              if (text21.toLowerCase(Locale.ROOT).startsWith(text7) && !list8.contains(text21)) {
                                 list8.add(text21);
                              }
                           } else if (!text12.equals("%PLAYERS%")) {
                              if (text12.startsWith(text7)) {
                                 if (index5 == 0) {
                                    list8.add("/" + text12);
                                 } else {
                                    list8.add(text12);
                                 }
                              }
                           } else {
                              for (String text14 : arg4) {
                                 if (text14.toLowerCase(Locale.ROOT).startsWith(text7)) {
                                    list8.add(text14);
                                 }
                              }

                              for (PlayerInfoBridge bridge2_3322 : this.mc.bridge$getClientPacketListener().bridge$getPlayerInfoMap()) {
                                 String text15 = bridge2_3322.bridge$getGameProfile().getName();
                                 if (text15.toLowerCase(Locale.ROOT).startsWith(text7)) {
                                    list8.add(text15);
                                 }
                              }
                           }
                        }

                        if (list8.size() == 1) {
                           String text17 = (String)list8.get(0);
                           if (("/" + text2).endsWith(text17)) {
                              highlightimpl61.method4(new String[0]);
                              return;
                           }
                        }

                        if (!list8.isEmpty()) {
                           highlightimpl61.method4(list8.toArray(new String[0]));
                        }

                        return;
                     }

                     tps6 = (Tps)tps6.method1().get(text7);
                     if (tps6 == null) {
                        return;
                     }

                     index5++;
                  }
               });
            }
         }
      }
   }

   private void method14() {
      if (this.field52 == null) {
         ResourceLocationBridge horsestats141 = ResourceLocationBridge.create("lunar:data/hypixel/autocomplete.json");
         ResourceBridge bridge152 = Bridge.method9().bridge$getResourceManager().bridge$getResource(horsestats141);
         if (bridge152 == null) {
            LunarLogger.method5("Couldn't find the autocomplete json file: lunar:data/hypixel/autocomplete.json", new Object[0]);
            this.field52 = new Tps(new String[0], new HashMap());
         } else {
            try (InputStreamReader reader3 = new InputStreamReader(bridge152.bridge$getInputStream())) {
               this.field52 = (Tps)LunarConstants.field22.fromJson(reader3, Tps.class);
            } catch (Exception exception8) {
               exception8.printStackTrace();
               this.field52 = new Tps(new String[0], new HashMap());
            }
         }
      }
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.field26, this.field28, this.field25, this.field17, this.field20});
      lightingextension231.method7(this.field18, arg1x -> arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field43}));
      lightingextension231.method9(new ClientOption[]{this.field19, this.field21, this.field22});
      ((SettingsSectionImpl)lightingextension231.method9(new ClientOption[]{this.field24})).method6(new int[]{1});
      ((SettingsSectionImpl)lightingextension231.method1("levelHeadOptions", arg1x -> {
         arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field23, this.field30, this.field31, this.field29});
         arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field32}).method3(() -> this.field23.get() != LevelHeadSource.BEDWARS);
      })).method2(() -> !(Boolean)this.field22.get());
      lightingextension231.method1(
         "chatOptions",
         arg1x -> arg1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(
            new ClientOption[]{
               this.field40,
               this.field33,
               this.field35,
               this.field34,
               this.field36,
               this.field37,
               this.field27,
               this.field38,
               this.field39,
               this.field41,
               this.field42
            }
         )
      );
   }

   protected ModDetails method20() {
      return ModDetails.method7()
         .method1(new ModCategory[]{ModCategory.field5})
         .method3(new String[]{"Cow (Autocomplete)"})
         .method2(new String[]{"AutoGG", "Auto GG", "Level Head"})
         .method11(this);
   }

   private void method10(EventLocationChange highlightimpl201) {
      if (this.mc.bridge$getPlayer() != null) {
         if ((Boolean)this.field20.get()) {
            HypixelLocation rewindhandlers22 = highlightimpl201.method2();
            if (rewindhandlers22 != null && (rewindhandlers22.field4 == null || !rewindhandlers22.field2.equals("SKYBLOCK") || !rewindhandlers22.field4.equals("Dungeon"))) {
               this.mc.bridge$getPlayer().bridge$sendCommand("/tip all");
            }
         }

         if ((Boolean)this.field21.get()) {
            this.mc.bridge$getPlayer().bridge$sendCommand("/who");
            this.field60 = true;
         }
      }
   }

   private void method11(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage data1) {
      Component component2 = data1.RIOCCRROHHHOCHOHHCIRHRCOOHRIHI();
      if (this.field59 >= 0) {
         this.field59++;
         if (this.field59 != 1
            && (
               this.field59 >= 6
                  || component2.color() != null
                     && component2.color().value() == NamedTextColor.AQUA.value()
                     && TextBridge.getTextContent(component2).startsWith("-------------------------------------------")
            )) {
            this.field59 = -1;
         }

         data1.setCancelled(true);
      } else {
         String text3 = data1.OROIIOCCOORRCRCIIHHOCCCRHICRCC();
         if ((Boolean)this.field21.get()) {
            if (text3.equalsIgnoreCase("Cages opened! FIGHT!")) {
               this.mc.bridge$getPlayer().bridge$sendCommand("/who");
               this.field60 = true;
            } else if (this.field60) {
               if (text3.equalsIgnoreCase("This command is not available on this server!") || text3.equalsIgnoreCase("Game hasn't started yet!")) {
                  data1.setCancelled(true);
                  this.field60 = false;
               } else if (text3.startsWith("Mode: ") || text3.startsWith("ONLINE: ")) {
                  this.field60 = false;
               }
            }
         }

         if ((Boolean)this.field20.get()
            && text3.equalsIgnoreCase("You already tipped everyone that has boosters active, so there isn't anybody to be tipped right now!")) {
            data1.setCancelled(true);
         } else {
            if ((Boolean)this.field19.get()) {
               String text4 = text3.toLowerCase(Locale.ROOT);
               String text5 = (String)this.field43.get();
               if (text4.endsWith("gg") || text4.endsWith("good game") || !text5.isEmpty() && text4.endsWith(text5)) {
                  data1.setCancelled(true);
               }
            }

            if ((Boolean)this.field18.get()) {
               if (Ref.method3().bridge$getSystemTime() - this.field53 <= TimeUnit.SECONDS.toMillis(2L)) {
                  return;
               }

               if (text3.startsWith(" ") && !text3.startsWith(" + ") && !((String)this.field43.get()).isEmpty()) {
                  UnmodifiableIterator unmodifiableiterator9 = field13.iterator();

                  while (unmodifiableiterator9.hasNext()) {
                     String text11 = (String)unmodifiableiterator9.next();
                     if (text3.contains(text11)) {
                        this.mc.bridge$getPlayer().bridge$sendCommand("/achat " + (String)this.field43.get());
                        this.field53 = Ref.method3().bridge$getSystemTime();
                        break;
                     }
                  }
               }
            }

            if ((Boolean)this.field17.get() && !text3.contains(":") && text3.contains("Friend request from")) {
               for (String text7 : text3.split("\n")) {
                  if (text7.contains("Friend request from ")) {
                     String text8 = text7.replace("Friend request from ", "");
                     text8 = text8.split(" ")[text8.split(" ").length - 1];
                     this.mc.bridge$getPlayer().bridge$sendCommand("/friend accept " + text8);
                     break;
                  }
               }
            }

            if (!data1.isCancelled()) {
               if ((Boolean)this.field27.get()
                  && TextBridge.containsColoredText(component2, NamedTextColor.GOLD, new String[]{"joined the lobby!", "spooked into the lobby!"})) {
                  data1.setCancelled(true);
               } else if ((Boolean)this.field38.get() && field14.matches(component2)) {
                  data1.setCancelled(true);
               } else if ((Boolean)this.field39.get() && field15.matches(component2)) {
                  data1.setCancelled(true);
               } else if ((Boolean)this.field40.get() && field8.matcher(text3).matches()) {
                  data1.setCancelled(true);
               } else if ((Boolean)this.field36.get() && TextBridge.startsWith(component2, "[SHOUT]", NamedTextColor.GOLD)) {
                  data1.setCancelled(true);
               } else if ((Boolean)this.field37.get() && TextBridge.startsWith(component2, "[SPECTATOR]", NamedTextColor.GRAY)) {
                  data1.setCancelled(true);
               } else if ((Boolean)this.field41.get() && field9.matcher(text3).matches()) {
                  data1.setCancelled(true);
               } else if ((Boolean)this.field42.get() & TextBridge.startsWith(component2, "✦") && text3.contains("Mystery Box")) {
                  data1.setCancelled(true);
               } else if ((Boolean)this.field33.get()) {
                  if (Client.method109().method40().method67().method35()) {
                     if (field11.matcher(text3).matches()) {
                        data1.setCancelled(true);
                     }
                  } else if (field10.matcher(text3).matches()) {
                     data1.setCancelled(true);
                  }
               }

               if (!data1.isCancelled()
                  && (Boolean)this.field28.get()
                  && Ref.method3().bridge$getSystemTime() - this.field54 < 3000L
                  && component2.color() != null
                  && component2.color().value() == NamedTextColor.AQUA.value()
                  && TextBridge.getTextContent(component2).equals("--------------  Guild: Message Of The Day  --------------")) {
                  this.field59 = 0;
                  data1.setCancelled(true);
               } else {
                  if (!data1.isCancelled()) {
                     this.method16(data1, text3);
                  }
               }
            }
         }
      }
   }

   public void method3(boolean flag1) {
      if (flag1) {
         this.field54 = Ref.method3().bridge$getSystemTime();
      }
   }

   public boolean method15() {
      return this.isEnabled() && (Boolean)this.field26.get() && HypixelLocationListener.field7.method7().method1();
   }

   public String method14(String text1) {
      return text1.endsWith("]") && this.method15() ? text1.substring(0, text1.lastIndexOf(91)) : text1;
   }

   public Bridge2_42 method15(String text1, Component component2) {
      if (text1.endsWith("]") && this.method15()) {
         String text3 = text1.substring(text1.lastIndexOf(91));
         return TextBridge.asBridge(ComponentTransformer.replaceLiteral(text3, "").transform(component2));
      } else {
         return TextBridge.asBridge(component2);
      }
   }

   private void method16(EventChatMessage highlightimpl1, String text2) {
      ComponentTransformer componenttransformer3 = null;
      if (text2.startsWith("Party > ")) {
         if ((Boolean)this.field35.get()) {
            highlightimpl1.setCancelled(true);
         } else if ((Boolean)this.field25.get()) {
            componenttransformer3 = this.field61;
         }
      } else if (text2.startsWith("Guild > ")) {
         if ((Boolean)this.field34.get()) {
            highlightimpl1.setCancelled(true);
         } else if ((Boolean)this.field25.get()) {
            componenttransformer3 = this.field62;
         }
      } else if ((Boolean)this.field25.get()) {
         if (text2.startsWith("Friend > ")) {
            componenttransformer3 = this.field63;
         } else if (text2.startsWith("Officer > ")) {
            componenttransformer3 = this.field64;
         }
      }

      if (componenttransformer3 != null) {
         highlightimpl1.method1(componenttransformer3.transform(highlightimpl1.method2()));
      }
   }

   protected List<Framework7Extension> method9() {
      return List.of(this.field57);
   }

   @Generated
   public ToggleOption method19() {
      return this.field17;
   }

   @Generated
   public ToggleOption method21() {
      return this.field18;
   }

   @Generated
   public ToggleOption method22() {
      return this.field19;
   }

   @Generated
   public ToggleOption method23() {
      return this.field20;
   }

   @Generated
   public ToggleOption method24() {
      return this.field21;
   }

   @Generated
   public ToggleOption method25() {
      return this.field22;
   }

   @Generated
   public EnumOption<LevelHeadSource> method26() {
      return this.field23;
   }

   @Generated
   public ToggleOption method27() {
      return this.field24;
   }

   @Generated
   public ToggleOption method28() {
      return this.field25;
   }

   @Generated
   public ToggleOption method29() {
      return this.field26;
   }

   @Generated
   public ToggleOption method30() {
      return this.field27;
   }

   @Generated
   public ToggleOption method34() {
      return this.field28;
   }

   @Generated
   public ColorOption method35() {
      return this.field30;
   }

   @Generated
   public ColorOption method36() {
      return this.field31;
   }

   @Generated
   public ToggleOption method37() {
      return this.field32;
   }

   @Generated
   public ToggleOption method38() {
      return this.field33;
   }

   @Generated
   public ToggleOption method39() {
      return this.field34;
   }

   @Generated
   public ToggleOption method40() {
      return this.field35;
   }

   @Generated
   public ToggleOption method41() {
      return this.field36;
   }

   @Generated
   public ToggleOption method42() {
      return this.field37;
   }

   @Generated
   public ToggleOption method43() {
      return this.field38;
   }

   @Generated
   public ToggleOption method44() {
      return this.field39;
   }

   @Generated
   public ToggleOption method45() {
      return this.field40;
   }

   @Generated
   public ToggleOption method46() {
      return this.field41;
   }

   @Generated
   public Map<UUID, String> method47() {
      return this.field44;
   }

   @Generated
   public Set<UUID> method48() {
      return this.field46;
   }

   @Generated
   public long method49() {
      return this.field53;
   }
}
