package com.moonsworth.lunar.client.mod.misc.radio;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.lunarclient.gameipc.styngr.v1.FetchStateRequest;
import com.lunarclient.gameipc.styngr.v1.NextTrackRequest;
import com.lunarclient.gameipc.styngr.v1.OpenSelectorRequest;
import com.lunarclient.gameipc.styngr.v1.SetMutedRequest;
import com.lunarclient.gameipc.styngr.v1.SetPlayingRequest;
import com.lunarclient.gameipc.styngr.v1.SetVolumeRequest;
import com.lunarclient.gameipc.styngr.v1.StyngrTrackControlsUpdate;
import com.lunarclient.gameipc.styngr.v1.StyngrTrackProgressUpdate;
import com.lunarclient.gameipc.styngr.v1.StyngrTrackState;
import com.lunarclient.gameipc.styngr.v1.StyngrTrackUpdate;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.ui.notification.NotificationType;
import com.moonsworth.lunar.client.gui.notification.NotificationManager;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.render.font.CachedFontImpl;
import com.moonsworth.lunar.client.ui.widget.ProgressBarWidget;
import com.moonsworth.lunar.client.render.font.FontRegistry;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudSize;
import com.moonsworth.lunar.client.ui.hud.TypedHudRenderer;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import com.moonsworth.lunar.client.ui.hud.HudConditionSet;
import com.moonsworth.lunar.client.event.input.EventMarkerInput;
import com.moonsworth.lunar.client.event.input.MouseInputType;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.holograms.EventWebSocketReady;
import com.moonsworth.lunar.client.event.mixin.nameplate.EventRenderHudBase;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ModifierKeybindOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.render.texture.DownloadedImageCache;
import com.moonsworth.lunar.client.ui.MousePosition;
import com.moonsworth.lunar.client.network.ipc.WebSocketClientIterator;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import org.java_websocket.enums.ReadyState;
import org.jetbrains.annotations.Nullable;

public class Radio extends AbstractFeature {
   public static final ResourceLocationBridge field8 = ResourceLocationBridge.create("lunar", "icons/assets/play-15x15-other.png");
   public static final ResourceLocationBridge field9 = ResourceLocationBridge.create("lunar", "icons/assets/pause-16x16.png");
   private static final ResourceLocationBridge field10 = ResourceLocationBridge.create("lunar", "icons/radio/missing.png");
   private static final int field11 = 2000;
   private static final float field12 = 30.0F;
   private static final long field13 = 2000L;
   private final IntegerOption field14 = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)OptionFactory.method4(
                  "volume"
               )
               .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(50))
            .OCRRICRIORICCCRHIOHORCICIHHICO(0, 100))
         .ROORCHCIOHIRRHCHCIRRHHHCRHRCOI())
      .method31();
   private final ToggleOption field15 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("muteRadio").ROORCHCIOHIRRHCHCIRRHHHCRHRCOI())
      .method31();
   private final ToggleOption field16 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showCoverArt").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field17 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showProgress").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ModifierKeybindOption field18 = (ModifierKeybindOption)((com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data)((com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data)OptionFactory.method18(
               "playPauseKeybind"
            )
            .method18(this))
         .CCCHIHCOIHIHRICIRCRIICHHHRHIIH(true))
      .method31();
   private final ModifierKeybindOption field19 = (ModifierKeybindOption)((com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data)((com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data)OptionFactory.method18(
               "skipTrackKeybind"
            )
            .method18(this))
         .CCCHIHCOIHIHRICIRCRIICHHHRHIIH(true))
      .method31();
   private final ModifierKeybindOption field20 = (ModifierKeybindOption)((com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data)((com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data)OptionFactory.method18(
               "muteSongKeybind"
            )
            .method18(this))
         .CCCHIHCOIHIHRICIRCRIICHHHRHIIH(true))
      .method31();
   private final ModifierKeybindOption field21 = (ModifierKeybindOption)((com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data)((com.moonsworth.lunar.client.config.option.ModifierKeybindOption.Data)OptionFactory.method18(
               "openStationListKeybind"
            )
            .method18(this))
         .CCCHIHCOIHIHRICIRCRIICHHHRHIIH(true))
      .method31();
   private final ColorOption field22 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "songNameTextColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   private final ColorOption field23 = (ColorOption)((com.moonsworth.lunar.client.config.option.ColorOption.Data)OptionFactory.method8(
            "artistNameTextColor"
         )
         .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(-1))
      .method31();
   private final ColorOption field24 = (ColorOption)OptionFactory.method8("radioProgressBarColor")
      .method10(79, 148, 252, 255)
      .method31();
   private final ToggleOption field25 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("boldSongName").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field26 = (ToggleOption)OptionFactory.method7("boldAristName").method31();
   private final ToggleOption field27 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("songScrollAnimation").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field28 = (ToggleOption)OptionFactory.method7("showWhenNothingPlaying").method31();
   private final ToggleOption field29 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showDuration").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field30 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showPlayButton").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ProgressBarWidget field31 = new ProgressBarWidget(null, field8);
   private final Cache<String, ResourceLocationBridge> field32 = CacheBuilder.newBuilder()
      .initialCapacity(10)
      .expireAfterAccess(20L, TimeUnit.MINUTES)
      .maximumSize(60L)
      .removalListener(arg1 -> this.mc.bridge$getTextureManager().bridge$deleteTexture((ResourceLocationBridge)arg1.getValue()))
      .build();
   private long field33 = 0L;
   private String field34;
   private StyngrTrackState field35;
   private StyngrTrackControlsUpdate field36;
   private StyngrTrackProgressUpdate field37;
   private float field38 = 0.0F;
   private boolean field39 = true;
   private long field40 = 0L;

   public Radio() {
      super(true);
      this.method35(ModTraits.field1, new Radio.Data());
      this.handle(EventWebSocketReady.class, arg1 -> {
         WebSocketClientIterator websocketclientiterator2 = arg1.method1();
         websocketclientiterator2.method1(StyngrTrackUpdate.class, arg1x -> {
            this.method1(arg1x.hasTrack() ? arg1x.getTrack() : null);
            this.mc.bridge$submit(this::method14);
         });
         websocketclientiterator2.method1(StyngrTrackControlsUpdate.class, arg1x -> {
            this.field36 = arg1x;
            this.field14.method10((int)(arg1x.getVolume() * 100.0F));
            this.field15.method10(arg1x.getMuted());
            this.mc.bridge$submit(this::method14);
         });
         websocketclientiterator2.method1(StyngrTrackProgressUpdate.class, arg1x -> this.field37 = arg1x);
      });
      this.handle(
         EventTick.class,
         arg1 -> {
            if (Ref.method14() - this.field33 > 2000L) {
               Ref.method6()
                  .ifPresent(
                     arg1x -> {
                        if (arg1x.getReadyState() != ReadyState.OPEN) {
                           this.method1(null);
                           this.field36 = null;
                           this.field37 = null;
                           this.method14();
                        }

                        arg1x.method18()
                           .fetchState(
                              null,
                              FetchStateRequest.newBuilder().build(),
                              arg1xx -> {
                                 this.method1(arg1xx.hasTrack() ? arg1xx.getTrack() : null);
                                 this.field36 = StyngrTrackControlsUpdate.newBuilder()
                                    .setMuted(arg1xx.getControls().getMuted())
                                    .setVolume(arg1xx.getControls().getVolume())
                                    .setPlaying(this.field35 != null && arg1xx.getControls().getPlaying())
                                    .build();
                                 this.field37 = StyngrTrackProgressUpdate.newBuilder()
                                    .setDuration(arg1xx.getProgress().getDuration())
                                    .setPosition(arg1xx.getProgress().getPosition())
                                    .build();
                                 this.field14.method10((int)(this.field36.getVolume() * 100.0F));
                                 this.field15.method10(this.field36.getMuted());
                                 this.mc.bridge$submit(this::method14);
                              }
                           );
                     }
                  );
               this.field33 = Ref.method14();
            }
         }
      );
      this.handle(
         EventMarkerInput.class,
         arg1 -> {
            if (arg1.method3() == 0 && arg1.method4() == MouseInputType.CLICK) {
               MixinCore9Extension mixincore9extension2 = (MixinCore9Extension)this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field1);
               com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2 data23 = (com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2)arg1.method2()
                  .method5()
                  .IHCORIOHOHHOIORHCCOOIIIHOCROOI(mixincore9extension2.getScale());
               if (this.field35 != null && (Boolean)this.field30.get() && this.field31.method3(data23)) {
                  this.field31.method6(data23, arg1.method3());
               }
            }
         }
      );
   }

   private void method1(@Nullable StyngrTrackState styngrtrackstate1) {
      this.field35 = styngrtrackstate1;
      this.field34 = styngrtrackstate1 != null ? String.join(", ", styngrtrackstate1.getArtistNamesList()) : null;
   }

   public String getId() {
      return "RADIO";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.AUDIO, arg1x -> arg1x.method9(new ClientOption[]{this.field14, this.field15})
      );
      lightingextension231.method7(
         SettingsPage.GENERAL,
         arg1x -> arg1x.method9(
            new ClientOption[]{this.field16, this.field17, this.field27, this.field28, this.field26, this.field25, this.field29, this.field30}
         )
      );
      lightingextension231.method7(
         SettingsPage.COLOR, arg1x -> arg1x.method9(new ClientOption[]{this.field22, this.field23, this.field24})
      );
      lightingextension231.method7(
         SettingsPage.CONTROLS,
         arg1x -> arg1x.method9(new ClientOption[]{this.field18, this.field19, this.field20, this.field21})
      );
      this.field18.method3(() -> {
         if (this.field36 != null && this.field35 != null) {
            Ref.method6().ifPresent(arg1x -> {
               boolean flag2 = !this.field36.getPlaying();
               arg1x.method18().setPlaying(null, SetPlayingRequest.newBuilder().setPlaying(flag2).build(), arg0 -> {});
               Ref.method4().method69().method3(NotificationManager.method15(flag2 ? "radio_resuming" : "radio_pausing", new Object[0]));
            });
         }
      });
      this.field19
         .method3(
            () -> {
               if (this.field35 != null) {
                  if (this.field35.getRemainingSkips() <= 0 && this.field35.getRemainingSkips() != -1) {
                     Ref.method4().method69().method7(NotificationType.ERROR, NotificationManager.method15("radio_out_of_skips", new Object[0]));
                  } else {
                     Ref.method6()
                        .ifPresent(
                           arg1x -> {
                              arg1x.method18().nextTrack(null, NextTrackRequest.newBuilder().build(), arg0 -> {});
                              if (this.field35.getRemainingSkips() == -1) {
                                 Ref.method4().method69().method3(NotificationManager.method15("radio_skipping", new Object[0]));
                              } else {
                                 int number2 = this.field35.getRemainingSkips() - 1;
                                 Ref.method4()
                                    .method69()
                                    .method3(NotificationManager.method15(number2 == 1 ? "radio_skipping_singular" : "radio_skipping_plural", new Object[]{number2}));
                              }
                           }
                        );
                  }
               }
            }
         );
      this.field20.method3(() -> {
         this.field15.method10(!(Boolean)this.field15.get());
         Ref.method4().method69().method3(NotificationManager.method15(this.field15.get() ? "radio_mute" : "radio_unmute", new Object[0]));
      });
      this.field21.method3(() -> Ref.method6().ifPresent(arg0 -> {
         arg0.method18().openSelector(null, OpenSelectorRequest.newBuilder().build(), arg0x -> {});
         Ref.method4().method69().method3(NotificationManager.method15("radio_open_station_list", new Object[0]));
      }));
      this.field31
         .method4(
            (arg1x, arg2) -> {
               if (this.field36 != null) {
                  Ref.method6()
                     .ifPresent(
                        arg1xx -> arg1xx.method18().setPlaying(null, SetPlayingRequest.newBuilder().setPlaying(!this.field36.getPlaying()).build(), arg0 -> {})
                     );
               }

               return true;
            }
         );
      this.field31.method5(false);
      this.field31.method15(true);
      this.field14
         .HORHIRROCIOIICIOHCOCCOOHIRCCRI(
            arg1x -> Ref.method6()
               .ifPresent(
                  arg1xx -> arg1xx.method18()
                     .setVolume(null, SetVolumeRequest.newBuilder().setVolume(((Integer)this.field14.get()).intValue() / 100.0F).build(), arg0 -> {})
               )
         );
      this.field15
         .HORHIRROCIOIICIOHCOCCOOHIRCCRI(
            arg0 -> Ref.method6()
               .ifPresent(arg1x -> arg1x.method18().setMuted(null, SetMutedRequest.newBuilder().setMuted(arg0).build(), arg0xx -> {}))
         );
   }

   public ResourceLocationBridge method3(String text1, String text2) {
      try {
         return (ResourceLocationBridge)this.field32.get(text1, () -> {
            ResourceLocationBridge horsestats141x = ResourceLocationBridge.create("lunar", "styngr_cover_art/" + UUID.randomUUID());
            DownloadedImageCache.method5(horsestats141x, text2);
            return horsestats141x;
         });
      } catch (ExecutionException executionexception4) {
         throw new RuntimeException(executionexception4);
      }
   }

   public static String method4(CachedFontImpl fishing2impl0, String text1, int number2) {
      if (text1 != null && !(fishing2impl0.method4(text1) <= number2)) {
         String text3 = fishing2impl0.method21(text1, number2 - fishing2impl0.method4("..."));
         return text3 + "...";
      } else {
         return text1;
      }
   }

   public boolean method13() {
      return this.field35 != null && this.field36 != null && this.field36.getPlaying();
   }

   private void method14() {
      Bridge5Extension_5 bridge5extension_51 = Ref.method7();
      if (bridge5extension_51 != null) {
         Ref.method4().method53().method63().computeIfPresent(bridge5extension_51.bridge$getUniqueID(), (arg1x, arg2) -> arg2.method3(this.method13()));
      }
   }

   private class Data extends TypedHudRenderer<String> {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.TOP_LEFT);
      }

      public void method1(RootSettingsBuilder lightingextension231) {
         super.method1(lightingextension231);
         lightingextension231.method6(this.OHOHCCIHCRCOOIIHRHCIIRRRCIOHRR);
         lightingextension231.method6(this.ORHRIHRICHICRCOCIIRIOICOIICHOI);
         lightingextension231.method6(this.OOOCCCRICCHOORCCRHHRHHCOOCORRC);
         lightingextension231.method6(this.HOHRIORRHIIIOOIHOOOCOHOHOHCHII);
      }

      public void method3(EventRenderHudBase highlightimpl1, float value2, float value3, boolean flag4) {
         MixinHelper_4 mixinhelper_45 = highlightimpl1.method2();
         String text6 = Radio.this.field35 == null ? "Nothing playing" : Radio.this.field35.getTitle();
         boolean flag7 = (Boolean)this.HIOICORHOCCRCOIHCRIIROIOIOIRIC.get();
         CachedFontImpl fishing2impl8 = Radio.this.field25.get() ? FontRegistry.field13 : FontRegistry.field11;
         CachedFontImpl fishing2impl9 = Radio.this.field26.get() ? FontRegistry.field10 : FontRegistry.field9;
         float value10 = 140.0F;
         int number11 = Ref.method10().method19();
         float value12 = number11;
         float value13 = Math.max(value12, ((Integer)this.OCRCICOROIRHIOHCCIHHICORCHCOOO.get()).intValue()) + 4.0F;
         float value14 = value13;
         float value15 = 6.0F;
         this.method33(value10, value13);
         float value16 = value13 - value12;
         float value17 = value3 + value16 / 2.0F - 8.0F;
         float value18 = Radio.this.field16.get() ? value2 + value14 : value2 + 2.0F;
         float value19 = value10 - (value18 - value2) - (Radio.this.field30.get() ? 15.0F : 1.0F);
         float value20 = fishing2impl8.method4(text6);
         boolean flag21 = value20 > value19;
         if (!(Boolean)Radio.this.field17.get()) {
            value18 += 3.0F;
            value19 -= 3.0F;
            value17 += 3.0F;
         }

         if (flag7) {
            this.HROIRCHIHORCHCHCRICOOOIOOHIRCH.method11(mixinhelper_45, value2, value3, value10, this.getHeight());
            if ((Boolean)this.OIRIICOHRIHHOCCCIICCRRCICOOCHI.get()) {
               this.CRROIIOHCOROIIOROHHCHIRRCORCRH.method11(mixinhelper_45, this, value2, value3, value10, this.getHeight(), (Float)this.HCHIRRHHICRCCIOOHCOICHHIORICHH.get());
            }
         }

         if ((Boolean)Radio.this.field16.get()) {
            float value22 = this.getHeight() / 2.0F;
            if ((Boolean)Radio.this.field17.get()) {
               value22 -= value15 / 2.0F;
            }

            LcuiScreen.method31(mixinhelper_45, this.method24(), value2, value3, value22 * 2.0F, value22 * 2.0F, -1);
         }

         long number29 = Ref.method3().bridge$getSystemTime();
         if (flag21 && (Boolean)Radio.this.field27.get()) {
            if (Radio.this.field40 == 0L) {
               Radio.this.field40 = number29;
            }

            long number24 = number29 - Radio.this.field40;
            if (number24 > 2000L) {
               float value26 = (float)(number24 - 2000L) / 1000.0F * 30.0F;
               if (Radio.this.field39) {
                  Radio.this.field38 = Math.min(value26, value20 - value19);
                  if (Radio.this.field38 >= value20 - value19) {
                     Radio.this.field39 = false;
                     Radio.this.field40 = number29;
                  }
               } else {
                  Radio.this.field38 = Math.max(value20 - value19 - value26, 0.0F);
                  if (Radio.this.field38 <= 0.0F) {
                     Radio.this.field39 = true;
                     Radio.this.field40 = number29;
                  }
               }
            }
         } else {
            Radio.this.field38 = 0.0F;
            Radio.this.field40 = 0L;
            Radio.this.field39 = true;
         }

         boolean flag30 = (Boolean)Radio.this.field27.get();
         if (flag30) {
            LcuiScreen.method111(mixinhelper_45, value18, value17, value19, value13, this.getScale());
         } else {
            text6 = Radio.method4(fishing2impl8, text6, (int)value19);
         }

         String text25 = Radio.method4(fishing2impl9, Radio.this.field34, (int)value19);
         if (Radio.this.field22.getAlpha() > 0) {
            Radio.this.field22.method11(mixinhelper_45, fishing2impl8, text6, value18 - Radio.this.field38, value17, false);
         }

         if (flag30) {
            LcuiScreen.method112(mixinhelper_45);
         }

         if (Radio.this.field23.getAlpha() > 0) {
            Radio.this.field23.method11(mixinhelper_45, fishing2impl9, text25, value18, value17 + 10.0F, false);
         }

         if ((Boolean)Radio.this.field17.get()) {
            float value31 = 0.0F;
            if (Radio.this.field35 != null && Radio.this.field37 != null) {
               value31 = Radio.this.field37.getPosition() / Radio.this.field37.getDuration() * value10;
            }

            LcuiScreen.method94(mixinhelper_45, value2, value3 + value13 - value15, value10, value15, method10(Radio.this.field24.method14(0.0F), 180));
            LcuiScreen.method94(mixinhelper_45, value2, value3 + value13 - value15, value31, value15, Radio.this.field24.method14(0.0F));
            if (Radio.this.field35 != null && Radio.this.field37 != null && (Boolean)Radio.this.field29.get()) {
               FontRegistry.method6().method17(mixinhelper_45, "§o" + method9(Radio.this.field37.getPosition()), value2 + 1.0F, value3 + value13 - value15, -1, false);
               FontRegistry.method6().method17(mixinhelper_45, "§o" + method9(Radio.this.field37.getDuration()), value2 + value10 - 15.0F, value3 + value13 - value15, -1, false);
            }
         }

         if ((Boolean)Radio.this.field30.get()) {
            Radio.this.field31.method7(7.0F);
            Radio.this.field31.method9(7.0F);
            if (Radio.this.field35 != null && Radio.this.field36 != null && Radio.this.field36.getPlaying()) {
               Radio.this.field31.method4(Radio.field9);
            } else {
               Radio.this.field31.method4(Radio.field8);
            }

            float value32 = (value13 - (Radio.this.field17.get() ? value15 : 0.0F)) / 2.0F - 8.0F;
            float value27 = 14.0F;
            Radio.this.field31.RIIICIRHRCIHOOOORHOICRIICCCRHR(value2 + value10 - value27, value3 + value32, 13.0F, 16.0F);
            com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2 data228 = (com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2)MousePosition.method1()
               .method5()
               .IHCORIOHOHHOIORHCCOOIIIHOCROOI(this.getScale());
            Radio.this.field31.method3(highlightimpl1.method2(), data228, Radio.this.field31.method3(data228));
         }
      }

      public boolean method31() {
         return !(Boolean)Radio.this.field27.get();
      }

      public boolean method4(boolean flag1) {
         return (Boolean)Radio.this.field28.get() || Radio.this.field35 != null;
      }

      public HudConditionSet method5() {
         return HudConditionSet.method5().method1(false).method8();
      }

      public HudSize method15() {
         return HudSize.method1(22, 24, 64, 60, 140, 300);
      }

      @Nullable
      public String method8(boolean flag1) {
         return null;
      }

      private ResourceLocationBridge method24() {
         return Radio.this.field35 == null ? Radio.field10 : Radio.this.method3(Radio.this.field35.getTitle(), Radio.this.field35.getImageUrl());
      }

      private static String method9(float value0) {
         int number1 = (int)value0;
         int number2 = number1 / 60;
         int number3 = number1 % 60;
         return String.format("%02d:%02d", number2, number3);
      }

      private static int method10(int number0, int number1) {
         int number2 = number0 >> 24 & 0xFF;
         int number3 = (number0 >> 16 & 0xFF) * number1 / 255;
         int number4 = (number0 >> 8 & 0xFF) * number1 / 255;
         int number5 = (number0 & 0xFF) * number1 / 255;
         return number2 << 24 | number3 << 16 | number4 << 8 | number5;
      }
   }
}
