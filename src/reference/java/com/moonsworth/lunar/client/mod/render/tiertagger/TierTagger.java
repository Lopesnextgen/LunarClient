package com.moonsworth.lunar.client.mod.render.tiertagger;

import com.google.common.collect.ImmutableMap.Builder;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.moonsworth.lunar.bridge.AdventureTextBridge;
import com.moonsworth.lunar.bridge.Bridge5Extension4;
import com.moonsworth.lunar.bridge.KeyCode;
import com.moonsworth.lunar.client.util.Slayer;
import com.moonsworth.lunar.client.framework.mod.Calculator2Handler;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.mod.FrameworkType;
import com.moonsworth.lunar.client.command.PlayerArgumentParser;
import com.moonsworth.lunar.client.command.MixinNameplate2;
import com.moonsworth.lunar.client.command.MixinNameplateImpl;
import com.moonsworth.lunar.client.command.MixinNameplateIterator;
import com.moonsworth.lunar.client.framework.feature.tiertagger.Tiertagger;
import com.moonsworth.lunar.client.framework.feature.tiertagger.Tiertagger2;
import com.moonsworth.lunar.client.framework.feature.tiertagger.Tiertagger3;
import com.moonsworth.lunar.client.framework.feature.tiertagger.Tiertagger5;
import com.moonsworth.lunar.client.framework.feature.tiertagger.Tiertagger6Impl;
import com.moonsworth.lunar.client.framework.feature.tiertagger.Tiertagger6Impl_2;
import com.moonsworth.lunar.client.framework.feature.tiertagger.Tiertagger_2;
import com.moonsworth.lunar.client.framework.feature.tiertagger.mixin.Gui2Extension;
import com.moonsworth.lunar.client.framework.feature.tiertagger.mixin.Gui2Extension2;
import com.moonsworth.lunar.client.framework.feature.tiertagger.mixin.LightingExtension4952;
import com.moonsworth.lunar.client.framework.feature.tiertagger.mixin.LightingExtension49522;
import com.moonsworth.lunar.client.framework.feature.tiertagger.rewindhandlers.Tiertagger6Iterator;
import com.moonsworth.lunar.client.event.mixin.gui.DisconnectEvent;
import com.moonsworth.lunar.client.event.mixin.highlight.EntityLabelRenderEvent;
import com.moonsworth.lunar.client.inventorymod.Inventorymod2;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsAssembler;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.OptionSupplier;
import com.moonsworth.lunar.client.config.option.SettingsSectionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsSectionImpl;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.framework.build.LunarBuildData;
import com.moonsworth.lunar.client.util.ThreadModuleDump48;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.config.Config;
import java.net.http.HttpClient;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;

public class TierTagger extends AbstractFeature {
   public static final ExecutorService field8 = new ThreadPoolExecutor(
      8,
      64,
      60L,
      TimeUnit.SECONDS,
      new ArrayBlockingQueue<>(64),
      new ThreadFactoryBuilder()
         .setDaemon(true)
         .setNameFormat("Tier Tagger Thread %d")
         .setUncaughtExceptionHandler((var0, var1x) -> Slayer.method9(var1x, "Uncaught error on thread %s!", var0.getName()))
         .build(),
      new CallerRunsPolicy()
   );
   public static final HttpClient field9 = HttpClient.newBuilder().executor(field8).connectTimeout(Duration.ofSeconds(10L)).build();
   public final LightingExtension49522 field10 = LightingExtension49522.method6(this);
   public final EnumOption<Gui2Extension2> field11 = (EnumOption<Gui2Extension2>)com.moonsworth.lunar.client.config.option.OptionFactory.method10(
         "tierDisplayMode", Gui2Extension2.HIGHEST_FALLBACK
      )
      .method31();
   public final EnumOption<Gui2Extension> field12 = (EnumOption<Gui2Extension>)com.moonsworth.lunar.client.config.option.OptionFactory.method10(
         "shownStatistic", Gui2Extension.TIER
      )
      .method31();
   public final LightingExtension4952 field13 = new LightingExtension4952(this, "gameMode", false);
   public final LightingExtension4952 field14 = new LightingExtension4952(this, "secondaryGameMode", true);
   public final ToggleOption field15 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("mcTiersFormat")
      .method31();
   private final ToggleOption field16 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("suffix")
      .method31();
   public final ToggleOption field17 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("separateNametag")
      .method31();
   public final ToggleOption field18 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "showGmIcons"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   public final ToggleOption field19 = (ToggleOption)((ToggleOption.ToggleOptionBuilder)com.moonsworth.lunar.client.config.option.OptionFactory.method7(
            "showRegion"
         )
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   public final ToggleOption field20 = (ToggleOption)com.moonsworth.lunar.client.config.option.OptionFactory.method7("showRetired")
      .method31();
   private final ModifierKeybindOption field21 = (ModifierKeybindOption)((ModifierKeybindOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method18(
            "switchTierSource"
         )
         .method18(this))
      .method5(KeyCode.KEY_NONE)
      .method31();
   private final ModifierKeybindOption field22 = (ModifierKeybindOption)((ModifierKeybindOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method18(
            "switchGameMode"
         )
         .method18(this))
      .method5(KeyCode.KEY_NONE)
      .method31();
   private final ColorOption field23 = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "colorHT1"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-12470))
      .method15()
      .method16()
      .method31();
   private final ColorOption field24 = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "colorMT1"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1392305))
      .method15()
      .method16()
      .method31();
   private final ColorOption field25 = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "colorLT1"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-2772139))
      .method15()
      .method16()
      .method31();
   private final ColorOption field26 = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "colorHT2"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-5983289))
      .method15()
      .method16()
      .method31();
   private final ColorOption field27 = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "colorMT2"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-6905682))
      .method15()
      .method16()
      .method31();
   private final ColorOption field28 = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "colorLT2"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-7828075))
      .method15()
      .method16()
      .method31();
   private final ColorOption field29 = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "colorHT3"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-2258871))
      .method15()
      .method16()
      .method31();
   private final ColorOption field30 = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "colorMT3"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-3639236))
      .method15()
      .method16()
      .method31();
   private final ColorOption field31 = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "colorLT3"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-5019600))
      .method15()
      .method16()
      .method31();
   private final ColorOption field32 = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "colorHT4"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-10134663))
      .method15()
      .method16()
      .method31();
   private final ColorOption field33 = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "colorMT4"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-10134663))
      .method15()
      .method16()
      .method31();
   private final ColorOption field34 = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "colorLT4"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-10134663))
      .method15()
      .method16()
      .method31();
   private final ColorOption field35 = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "colorHT5"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-10134663))
      .method15()
      .method16()
      .method31();
   private final ColorOption field36 = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "colorMT5"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-10134663))
      .method15()
      .method16()
      .method31();
   private final ColorOption field37 = (ColorOption)((ColorOption.Data)com.moonsworth.lunar.client.config.option.OptionFactory.method8(
            "colorLT5"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-10134663))
      .method15()
      .method16()
      .method31();
   public final Map<String, ColorOption> field38 = new Builder()
      .put("HT1", this.field23)
      .put("MT1", this.field24)
      .put("LT1", this.field25)
      .put("HT2", this.field26)
      .put("MT2", this.field27)
      .put("LT2", this.field28)
      .put("HT3", this.field29)
      .put("MT3", this.field30)
      .put("LT3", this.field31)
      .put("HT4", this.field32)
      .put("MT4", this.field33)
      .put("LT4", this.field34)
      .put("HT5", this.field35)
      .put("MT5", this.field36)
      .put("LT5", this.field37)
      .build();
   private final Tiertagger3 field39 = new Tiertagger3(this);
   private final Tiertagger field40 = new Tiertagger(this, this.field39);
   private final Tiertagger2 field41 = new Tiertagger2(this, this.field39);
   private int field42 = 0;

   public TierTagger() {
      super(false);
      this.handle(
         DisconnectEvent.class,
         var1 -> {
            for (com.moonsworth.lunar.client.framework.feature.tiertagger.Gui2Extension var3 : com.moonsworth.lunar.client.framework.feature.tiertagger.Gui2Extension.VALUES) {
               var3.getTierProvider().clearCache();
               var3.getTierProvider().method6();
            }

            this.clearCaches();
         }
      );
      this.handle(com.moonsworth.lunar.client.event.mixin.highlight.NameTagRenderEvent.class, this.field40::method3);
      if (ThreadModuleDump63.MC_VERSION >= 16) {
         this.handle(EntityLabelRenderEvent.EntityLabelLinesEvent.class, this.field40::method2);
      }

      this.method32().method1(Framework.field8, (var1, var2, var3) -> {
         this.field10.method7(var3 != null && var3.ordinal() > FrameworkType.LOADING_CONFIG.ordinal());
         if (var3 == FrameworkType.LOADED_CONFIG) {
            this.field10.method1(this.field10.method9(), false);
         }
      });
      this.method25(
         new MixinNameplate2(
            MixinNameplateImpl.method1("lctiers")
               .method3(var1 -> this.field41.method1(ThreadModuleDump63.method4().method31().getName()))
               .method2(MixinNameplateIterator.method1("username", PlayerArgumentParser.field2).method8(var1 -> this.field41.method1(var1.getString("username"))))
         )
      );
      this.method17(Framework.field18, var0 -> var0.method5(Config.field1));
   }

   @Override
   public String getId() {
      return "TIER_TAGGER";
   }

   @Override
   protected ModDetails method20() {
      return ModDetails.method7().method1(Calculator2Handler.field3, Calculator2Handler.field4).method3("uku", "Netiyiy").method11(this);
   }

   @Override
   public void method4() {
      super.method4();
      this.clearCaches();
   }

   @Override
   public void method3(boolean var1) {
      for (com.moonsworth.lunar.client.framework.feature.tiertagger.Gui2Extension var3 : com.moonsworth.lunar.client.framework.feature.tiertagger.Gui2Extension.VALUES) {
         var3.getTierProvider().method6();
         if (var1) {
            var3.getTierProvider().method3();
         }
      }
   }

   @Override
   public void method2(RootSettingsAssembler var1) {
      var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(
         SettingsPage.GENERAL,
         var1x -> {
            var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field10, this.field21}).method6(5);
            if (ThreadModuleDump63.MC_VERSION <= 1) {
               var1x.method13(this.method25("tiertests", new Object[0]));
            }

            var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field12});
            var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(
                  new ClientOption[]{this.field13, this.field14, this.field11, this.field22.method3(() -> {
                     this.field13.method8();
                     this.method14();
                  })}
               )
               .method3(() -> this.field12.get() == Gui2Extension.RANK);
            var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field15}).method3(() -> !this.field10.method9().isTierTests());
            var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field16}).method3(this.field17::get);
            var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field17, this.field18});
            var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field19}).method3(() -> !this.field10.getTierProvider().method1().method4());
            var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{this.field20})
               .method3(() -> this.field12.get() == Gui2Extension.RANK || !this.field10.getTierProvider().method1().method5());
            var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(
               new OptionSupplier[]{
                  com.moonsworth.lunar.client.config.option.OptionFactory.method14("showIconExamples")
                     .method4(
                        () -> {
                           if (ThreadModuleDump63.method7() != null) {
                              ArrayList var1xx = new ArrayList();

                              for (Tiertagger_2 var3 : this.field10.getTierProvider().method3()) {
                                 if (var3.method1().isPresent()) {
                                    var1xx.add(Component.text(var3.niceName() + ": " + var3.method1().get()).color(TextColor.color(var3.method2())));
                                 }
                              }

                              if (!var1xx.isEmpty()) {
                                 ThreadModuleDump63.method7()
                                    .bridge$addChatMessage(
                                       AdventureTextBridge.asBridge(Component.text(this.field10.method9().niceName() + " icons:").color(TextColor.color(5636095)))
                                    );

                                 for (Component var5 : var1xx) {
                                    ThreadModuleDump63.method7().bridge$addChatMessage(AdventureTextBridge.asBridge(var5));
                                 }
                              }
                           }
                        }
                     )
               }
            );
            var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(
               new OptionSupplier[]{
                  com.moonsworth.lunar.client.config.option.OptionFactory.method14("showTierExamples")
                     .method4(
                        () -> {
                           if (ThreadModuleDump63.method7() != null) {
                              ArrayList var1xx = new ArrayList();
                              if (this.field10.method9().isTierTests()) {
                                 Tiertagger6Iterator.method7()
                                    .method9()
                                    .values()
                                    .stream()
                                    .sorted(Comparator.comparingInt(var0 -> Tiertagger5.method2(var0.tier(), var0.method1())))
                                    .forEach(
                                       var2 -> {
                                          int var3x = var2.method1();
                                          int var4 = var2.tier();
                                          var1xx.add(
                                             ((TextComponent)((TextComponent)((TextComponent)Component.text(this.field39.method3(var4, var3x))
                                                         .color(this.field39.method2(var4, var3x)))
                                                      .append(Component.text(" (").color(NamedTextColor.GRAY)))
                                                   .append(Component.text(var2.displayName()).color(this.field39.method2(var4, var3x))))
                                                .append(Component.text(")").color(NamedTextColor.GRAY))
                                          );
                                       }
                                    );
                              } else {
                                 for (Tiertagger5 var3 : this.field10.getTierProvider().method1().method3()) {
                                    var1xx.add(this.field39.method1(var3.tier(), var3.method3()));
                                 }
                              }

                              if (!var1xx.isEmpty()) {
                                 ThreadModuleDump63.method7()
                                    .bridge$addChatMessage(
                                       AdventureTextBridge.asBridge(Component.text(this.field10.method9().niceName() + " tiers:").color(TextColor.color(5636095)))
                                    );

                                 for (Component var5 : var1xx) {
                                    ThreadModuleDump63.method7().bridge$addChatMessage(AdventureTextBridge.asBridge(var5));
                                 }
                              }
                           }
                        }
                     )
               }
            );
            var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(
               new OptionSupplier[]{
                  com.moonsworth.lunar.client.config.option.OptionFactory.method14("clearCache")
                     .method4(
                        () -> {
                           for (com.moonsworth.lunar.client.framework.feature.tiertagger.Gui2Extension var2 : com.moonsworth.lunar.client.framework.feature.tiertagger.Gui2Extension.VALUES) {
                              var2.getTierProvider().clearCache();
                           }

                           this.clearCaches();
                        }
                     )
               }
            );
            if (ThreadModuleDump48.field30 || !LunarBuildData.field4) {
               var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new OptionSupplier[]{com.moonsworth.lunar.client.config.option.OptionFactory.method14("debug").method4(() -> {
                  String var1xx = String.format("[TierTagger] Display cache: %s, %s", this.field40.method1(), this.field10.getTierProvider().method7());
                  Slayer.method3(var1xx);
               })});
            }
         }
      );
      ((SettingsSectionImpl)var1.HORHROIOIOICIRHIOCOICHHHIHCIIO(SettingsPage.COLOR, var1x -> this.field38.forEach((var2, var3) -> {
         SettingsSectionBuilder var4 = var1x.ICRHORIIHOHROHOHOCOOHOOCOORRHO(new ClientOption[]{var3});
         if (var2.startsWith("MT")) {
            var4.method3(() -> !this.field10.getTierProvider().method1().method1());
         }
      }))).method6(5);
      if (ThreadModuleDump63.MC_VERSION > 1) {
         this.field21.method3(() -> {
            this.field10.method8();
            this.method14();
         });
         this.field38.values().forEach(var1x -> var1x.method9(this::clearCaches));
      }

      this.field11.method9(this::clearCaches);
      this.field12.method9(this::clearCaches);
      this.field16.method9(this::clearCaches);
      this.field17.method9(this::clearCaches);
      this.field18.method9(this::clearCaches);
      this.field19.method9(this::clearCaches);
      this.field20.method9(this::clearCaches);
      this.field15.method9(this::clearCaches);
   }

   public void clearCaches() {
      this.field40.clearCache();
   }

   public boolean method13() {
      return this.field16.get() && !this.field17.get();
   }

   private void method14() {
      if (ThreadModuleDump63.method7() != null) {
         Bridge5Extension4 var1 = ThreadModuleDump63.method3().bridge$getGuiIngame().bridge$getChatGUI();
         if (this.field42 != 0) {
            var1.method1(this.field42);
         }

         Tiertagger_2 var2 = this.field13.get();
         this.field42 = var1.bridge$addMessageWithLunarId(
            AdventureTextBridge.asBridge(
               ((TextComponent)((TextComponent)Component.text("[TierTagger] Current mode: ")
                        .append(Component.text(var2.niceName()).color(TextColor.color(var2.method2()))))
                     .append(Component.text(" (" + this.field10.method9().niceName() + ")")))
                  .color(NamedTextColor.GRAY)
            )
         );
      }
   }

   static {
      ((ThreadPoolExecutor)field8).allowCoreThreadTimeOut(true);

      try {
         Tiertagger6Impl.method3().init();
         com.moonsworth.lunar.client.framework.feature.tiertagger.nameplate.Tiertagger6Impl.method3().init();
         Tiertagger6Iterator.method7().init();
         Tiertagger6Impl_2.method3().init();
      } catch (Throwable var1) {
         Inventorymod2.method5(var1, "Loading tier tagger data");
      }
   }
}
