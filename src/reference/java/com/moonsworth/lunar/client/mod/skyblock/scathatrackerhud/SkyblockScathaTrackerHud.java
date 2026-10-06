package com.moonsworth.lunar.client.mod.skyblock.scathatrackerhud;

import com.lunarclient.generated.skyblockprofileresponse.profile.Member;
import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.TextHudComponent;
import com.moonsworth.lunar.client.ui.hud.ItemStackHudComponent;
import com.moonsworth.lunar.client.ui.hud.HideableHudComponent;
import com.moonsworth.lunar.client.ui.hud.PaddedHudComponent;
import com.moonsworth.lunar.client.ui.hud.HudComponentGroup;
import com.moonsworth.lunar.client.ui.hud.MixinCore5Task;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Base;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.WidgetFactory;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin.ItemRarity;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.HudVisibilityWrapper;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.WorldTimeListener;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.SkyblockProfileEvents.SkyblockProfileLoadEvent;
import com.moonsworth.lunar.client.framework.listener.PersistentValuesListener;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.config.option.ConstantName;
import com.moonsworth.lunar.client.framework.hud.HudTimer;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.math.MathUtils;
import com.moonsworth.lunar.client.util.text.TimeFormatting.Type;
import com.moonsworth.lunar.client.render.color.AnimatedColor;
import com.moonsworth.lunar.ichor.util.KeepName;
import java.util.Map;

public class SkyblockScathaTrackerHud extends AbstractFeature {
   private final WorldTimeListener field8 = (WorldTimeListener)this.method63(WorldTimeListener.class);
   private final com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.SkyblockProfileCache field9 = (com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.SkyblockProfileCache)this.method63(
      com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.SkyblockProfileCache.class
   );
   private final PersistentValuesListener field10 = (PersistentValuesListener)this.method63(PersistentValuesListener.class);
   private final ItemStackBridge field11 = SkyblockItemUtil.method12(
      "730a6086-ad87-38fa-8fa4-0b76a060f4fc",
      "ewogICJ0aW1lc3RhbXAiIDogMTYyMDQ0NTc2NDQ1MSwKICAicHJvZmlsZUlkIiA6ICJmNDY0NTcxNDNkMTU0ZmEwOTkxNjBlNGJmNzI3ZGNiOSIsCiAgInByb2ZpbGVOYW1lIiA6ICJSZWxhcGFnbzA1IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2RmMDNhZDk2MDkyZjNmNzg5OTAyNDM2NzA5Y2RmNjlkZTZiNzI3YzEyMWIzYzJkYWVmOWZmYTFjY2FlZDE4NmMiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==",
      "d9k296WS79Ha4wIzRD1L5WdKRklU8OCa+9lv/6B1OHqa0ZPMSJV0Vc8dx/RX+4FxfNYG/ZOohmhcs1KRcU81Ty6SV+7o/6jNBQ14HWbsmb4W+hv/aaLvcO8Jkua7QFUgLs01AHU+K3qFKrW1+5mwvzzbPu8Po0zpj1MjpsDV67Urvf6AMXrLlY2P+wuGXdR7tkireZ/IWvOXYQ3IoptKS8ejSGfxE3rHsVNMG146OsEH9Khv+zV8V48yQDA6S1KBBI9PI3pQQMT1ktrsV6delHw9EQxG1voehNpNfBSraeGQ8S6tKUhZgX/aAdd5R00Fp+i1DyDnXcXB4eehLvTEnIkcwBeYuMC6PbyqFqI2Fr2FF8dTQay6IoxNEG7A8DLubfjVRBI6HXwq2iHkZPzywCSzLs6fleHtstmiY0Gmtay/RU4DrW13AE2+HHWSNalPX8Uu8zqfLCjt1JXTfDEKtFw3h1sKE2Mqy3XZPAnSJS7WNon6nnW7e+n9Z4ed4mrvzxuyZR1clz9YBjaSxJEBcO5XswOq/BSWh7IVo/rBgvM4zrB4+gM4S8U4gvBBXW6eysxEU3lcsRjK3ugrV21wvxNfndugyXwjbLawRw0ny3DAe9Zz/+RYDNM/N+wSYnBSETfSejymhZp155wVGyB+DtkDWzCd3TVyQ0iQydDxuIg="
   );
   private static final long field12 = 30000L;
   private final ToggleOption field13 = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
            "scathaCounterCooldown"
         )
         .method4(true))
      .method31();
   private final ToggleOption field14 = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
            "scathaCounterSinceLastPet"
         )
         .method4(true))
      .method31();
   private final ToggleOption field15 = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
            "scathaCounterSinceLastScatha"
         )
         .method4(true))
      .method31();
   private final ToggleOption field16 = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
            "scathaCounterTimeFarming"
         )
         .method4(true))
      .method31();
   private final ToggleOption field17 = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
            "scathaCounterScathaStreak"
         )
         .method4(true))
      .method31();
   private final ToggleOption field18 = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
            "scathaCounterTimeAndWall"
         )
         .method4(true))
      .method31();
   private final ToggleOption field19 = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
            "scathaCounterSpawnsTable"
         )
         .method4(true))
      .method31();
   private final ToggleOption field20 = (ToggleOption)((com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder)OptionFactory.method7(
            "scathaCounterPetsCounter"
         )
         .method4(true))
      .method31();
   private HudTimer field21;
   private HudTimer field22;
   private HudTimer field23;
   private SkyblockScathaTrackerHud.Data field24 = new SkyblockScathaTrackerHud.Data();

   public SkyblockScathaTrackerHud(Skyblock skyblock1) {
      super(false);
      this.field24.field3 = 1;
      this.method10(ModTraits.field16, ChildModBinding.method4(true, skyblock1));
      this.method10(ModTraits.field17, ModCategories.method2(SettingsPage.MINING));
      this.method10(ModTraits.field19, DynamicCondition.method1(this, () -> IslandUtils.getIsland() == SkyblockIsland.CRYSTAL_HOLLOWS));
      this.method10(ModTraits.field1, HudVisibilityWrapper.method4(new SkyblockScathaTrackerHud.ScathaTrackerHudElement()));
      this.handle(com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.WormScathaEventBase.Data.class, this::method10);
      this.handle(com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.WormScathaEventBase.WormScathaSpawnEvent.class, this::method11);
      this.handle(SkyblockProfileLoadEvent.class, this::method12);
      this.method51(this::onDisable);
      this.method12(this::onEnable);
   }

   @ConstantName
   public String getId() {
      return "SKYBLOCK_SCATHA_TRACKER_HUD";
   }

   private void onDisable() {
      if (this.field21 != null) {
         this.field21.stop();
      }

      if (this.field22 != null) {
         this.field22.stop();
      }

      if (this.field23 != null) {
         this.field23.stop();
      }
   }

   private void onEnable() {
      if (this.field21 != null) {
         this.field21.method2();
      }

      if (this.field22 != null) {
         this.field22.method2();
      }

      if (this.field23 != null) {
         this.field23.method2();
      }
   }

   private SkyblockScathaTrackerHud.ScathaSessionStats method13() {
      return this.field10.method6().scathaStats;
   }

   private SkyblockScathaTrackerHud.ScathaSessionStats method2(boolean flag1) {
      return flag1 ? this.method14() : this.method13();
   }

   private SkyblockScathaTrackerHud.Data method4(boolean flag1) {
      return flag1 ? this.method15() : this.field24;
   }

   private String method5(boolean flag1) {
      if (flag1) {
         return "2.75";
      }

      float value2 = (float)this.field8.method5() / 20.0F / 60.0F / 20.0F;
      return String.format("%.2f", value2);
   }

   private long method6(boolean flag1) {
      if (this.field21 != null && this.field21.get() < 30000L) {
         return 30000L - this.field21.get();
      } else {
         return flag1 ? 21000L : 0L;
      }
   }

   private String method7(boolean flag1) {
      int number2 = this.method2(flag1).streak;
      return number2 <= 0
         ? this.method7("noScathaForSpawns", new Object[]{ChatFormatting.WHITE, String.format("%,d", -number2), ChatFormatting.GRAY})
         : this.method7("scathaStreakAmount", new Object[]{ChatFormatting.WHITE, String.format("%,d", number2)});
   }

   private String method7(HudTimer threadmoduledump451) {
      return threadmoduledump451 == null ? "NA" : Type.EASY_DYNAMIC_1.format(threadmoduledump451.get());
   }

   private String method8(HudTimer threadmoduledump451, boolean flag2, long number3) {
      return flag2 ? Type.EASY_DYNAMIC_1.format(number3) : this.method7(threadmoduledump451);
   }

   private String method9(boolean flag1) {
      if (flag1) {
         return "63%";
      }

      Bridge5Extension_5 bridge5extension_52 = Ref.method7();
      if (bridge5extension_52 == null) {
         return "";
      }

      int number3 = (int)(Math.round(bridge5extension_52.bridge$getRotationYaw() / 90.0) % 4L + 4L) % 4;

      double value4 = switch (number3) {
         case 0 -> (bridge5extension_52.bridge$getPosZ() - 203.0) / 620.0;
         case 1 -> (823.0 - bridge5extension_52.bridge$getPosX()) / 620.0;
         case 2 -> (823.0 - bridge5extension_52.bridge$getPosZ()) / 620.0;
         case 3 -> (bridge5extension_52.bridge$getPosX() - 203.0) / 620.0;
         default -> 0.0;
      };
      return (int)Math.floor(MathUtils.method11(value4, 0.0, 1.0) * 100.0) + "%";
   }

   private void method10(com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.WormScathaEventBase.Data data1) {
      this.field21 = com.moonsworth.lunar.client.framework.hud.HudTimer.Data.method1().method3().method7();
      if (this.field23 == null) {
         this.field23 = com.moonsworth.lunar.client.framework.hud.HudTimer.Data.method1().method3().method7();
      }
   }

   private void method11(com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.WormScathaEventBase.WormScathaSpawnEvent data21) {
      this.field24.field3++;
      this.method13().totalCount++;
      if (data21.method1()) {
         this.field22 = com.moonsworth.lunar.client.framework.hud.HudTimer.Data.method1().method3().method7();
         this.method13().scathaTotalCount++;
         this.field24.field2++;
         this.method13().sinceLastPetCount++;
         if (this.method13().streak <= 0) {
            this.method13().streak = 1;
         } else {
            this.method13().streak++;
         }
      } else {
         this.method13().wormTotalCount++;
         this.field24.field1++;
         if (this.method13().streak > 0) {
            this.method13().streak = -1;
         } else {
            this.method13().streak--;
         }
      }

      this.field10.markDirty();
   }

   private void method12(SkyblockProfileLoadEvent data141) {
      Member member2 = this.field9.method9();
      if (member2 != null) {
         Map map3 = member2.bestiary().kills();
         int number4 = 0;
         if (map3.containsKey("worm_5")) {
            int number5 = ((Double)map3.get("worm_5")).intValue();
            this.method13().wormTotalCount = number5;
            number4 += number5;
         }

         if (map3.containsKey("scatha_10")) {
            int number6 = ((Double)map3.get("scatha_10")).intValue();
            this.method13().scathaTotalCount = number6;
            number4 += number6;
         }

         if (number4 != 0) {
            this.method13().totalCount = number4;
         }
      }
   }

   private SkyblockScathaTrackerHud.ScathaSessionStats method14() {
      SkyblockScathaTrackerHud.ScathaSessionStats data31 = new SkyblockScathaTrackerHud.ScathaSessionStats();
      data31.streak = 3;
      data31.sinceLastPetCount = 152;
      data31.wormTotalCount = 4320;
      data31.scathaTotalCount = 1008;
      data31.totalCount = 5328;
      data31.rarePetCount = 3;
      data31.epicPetCount = 1;
      data31.legendaryPetCount = 1;
      return data31;
   }

   private SkyblockScathaTrackerHud.Data method15() {
      SkyblockScathaTrackerHud.Data data1 = new SkyblockScathaTrackerHud.Data();
      data1.field1 = 48;
      data1.field2 = 16;
      data1.field3 = 64;
      return data1;
   }

   private static class Data {
      public int field1;
      public int field2;
      public int field3;

      private Data() {
      }
   }

   private class ScathaTrackerHudElement extends MixinCore9Base {
      public ScathaTrackerHudElement() {
         super(0.0F, 0.0F, HudAnchor.MIDDLE_LEFT);
         this.method10(
            WidgetFactory.withBackground(
               new HudComponentGroup(true, com.moonsworth.lunar.client.ui.hud.HudComponentGroup.Type.CENTER)
                  .method5(
                     new HudComponentGroup(com.moonsworth.lunar.client.ui.hud.HudComponentGroup.Type.CENTER)
                        .method5(new ItemStackHudComponent(SkyblockScathaTrackerHud.this.field11).method6(2.0).method9(true))
                        .method5(
                           new PaddedHudComponent(
                                 new TextHudComponent(() -> SkyblockScathaTrackerHud.this.method7("scathaStats", new Object[0]), ChatFormatting.GOLD)
                                    .method14(1.5)
                              )
                              .method4(4.0F)
                        )
                        .method6(
                           com.moonsworth.lunar.client.ui.hud.HudComponentGroup.Type.START,
                           new HideableHudComponent(
                                 new MixinCore5Task(
                                       new TextHudComponent(() -> SkyblockScathaTrackerHud.this.method7("resetSession", new Object[0]), ChatFormatting.RED)
                                    )
                                    .method2(() -> SkyblockScathaTrackerHud.this.method7("clickToResetSession", new Object[0]))
                                    .method10(() -> SkyblockScathaTrackerHud.this.field24 = new SkyblockScathaTrackerHud.Data())
                              )
                              .method2(() -> MixinCore5Task.method17() || SkyblockScathaTrackerHud.this.field24.field3 == 0)
                        )
                  )
                  .method5(
                     new HideableHudComponent(
                           new HudComponentGroup()
                              .method5(new TextHudComponent(() -> SkyblockScathaTrackerHud.this.method7("cooldown", new Object[0]), ChatFormatting.RED))
                              .method5(
                                 new TextHudComponent()
                                    .method2(() -> Type.EASY_DYNAMIC_1.format(SkyblockScathaTrackerHud.this.method6(this.OHCROHOHCIHICOCIHROIRCOIIICRCI)))
                                    .method9(ChatFormatting.AQUA)
                              )
                        )
                        .method2(
                           () -> !(Boolean)SkyblockScathaTrackerHud.this.field13.get()
                              || SkyblockScathaTrackerHud.this.method6(this.OHCROHOHCIHICOCIHROIRCOIIICRCI) == 0L
                        )
                  )
                  .method5(
                     new HideableHudComponent(
                           new HudComponentGroup()
                              .method5(
                                 new TextHudComponent(
                                    () -> SkyblockScathaTrackerHud.this.method7("scathasSinceLastDrop", new Object[0]), ChatFormatting.GRAY
                                 )
                              )
                              .method5(
                                 new TextHudComponent("0")
                                    .method2(
                                       () -> String.format("%,d", SkyblockScathaTrackerHud.this.method2(this.OHCROHOHCIHICOCIHROIRCOIIICRCI).sinceLastPetCount)
                                    )
                                    .method9(ChatFormatting.WHITE)
                              )
                        )
                        .method2(() -> !(Boolean)SkyblockScathaTrackerHud.this.field14.get())
                  )
                  .method5(
                     new HideableHudComponent(
                           new HudComponentGroup()
                              .method5(
                                 new TextHudComponent(() -> SkyblockScathaTrackerHud.this.method7("timeSinceLast", new Object[0]), ChatFormatting.GRAY)
                              )
                              .method5(
                                 new TextHudComponent()
                                    .method2(
                                       () -> SkyblockScathaTrackerHud.this.method8(
                                          SkyblockScathaTrackerHud.this.field22, this.OHCROHOHCIHICOCIHROIRCOIIICRCI, 45000L
                                       )
                                    )
                              )
                        )
                        .method2(() -> !(Boolean)SkyblockScathaTrackerHud.this.field15.get())
                  )
                  .method5(
                     new HideableHudComponent(
                           new HudComponentGroup()
                              .method5(new TextHudComponent(() -> SkyblockScathaTrackerHud.this.method7("timeFarming", new Object[0]), ChatFormatting.GRAY))
                              .method5(
                                 new TextHudComponent()
                                    .method2(
                                       () -> SkyblockScathaTrackerHud.this.method8(
                                          SkyblockScathaTrackerHud.this.field23, this.OHCROHOHCIHICOCIHROIRCOIIICRCI, 4820000L
                                       )
                                    )
                              )
                        )
                        .method2(() -> !(Boolean)SkyblockScathaTrackerHud.this.field16.get())
                  )
                  .method5(
                     new HideableHudComponent(
                           new TextHudComponent()
                              .method2(() -> SkyblockScathaTrackerHud.this.method7(this.OHCROHOHCIHICOCIHROIRCOIIICRCI))
                              .method9(ChatFormatting.GRAY)
                        )
                        .method2(() -> !(Boolean)SkyblockScathaTrackerHud.this.field17.get())
                  )
                  .method5(
                     new HideableHudComponent(
                           new PaddedHudComponent(
                                 new HudComponentGroup()
                                    .method5(new ItemStackHudComponent(Bridge.method28().method20()))
                                    .method5(new TextHudComponent(() -> SkyblockScathaTrackerHud.this.method7("day", new Object[0]), ChatFormatting.GRAY))
                                    .method5(new TextHudComponent().method2(() -> SkyblockScathaTrackerHud.this.method5(this.OHCROHOHCIHICOCIHROIRCOIIICRCI)))
                                    .method5(new TextHudComponent("   "))
                                    .method5(new ItemStackHudComponent(Bridge.method28().method98()))
                                    .method5(new TextHudComponent(" "))
                                    .method5(new TextHudComponent().method2(() -> SkyblockScathaTrackerHud.this.method9(this.OHCROHOHCIHICOCIHROIRCOIIICRCI)))
                                    .method5(new TextHudComponent(() -> SkyblockScathaTrackerHud.this.method7("toWall", new Object[0]), ChatFormatting.GRAY))
                              )
                              .method10(2.0F)
                        )
                        .method2(() -> !(Boolean)SkyblockScathaTrackerHud.this.field18.get())
                  )
                  .method5(
                     new HideableHudComponent(
                           new PaddedHudComponent(
                                 new HudComponentGroup()
                                    .method5(
                                       new HudComponentGroup(true, com.moonsworth.lunar.client.ui.hud.HudComponentGroup.Type.START)
                                          .method5(new TextHudComponent(" "))
                                          .method5(
                                             new TextHudComponent(() -> SkyblockScathaTrackerHud.this.method7("worms", new Object[0]), ChatFormatting.YELLOW)
                                          )
                                          .method5(
                                             new TextHudComponent(
                                                () -> SkyblockScathaTrackerHud.this.method7("scathas", new Object[0]), ChatFormatting.YELLOW
                                             )
                                          )
                                          .method5(
                                             new TextHudComponent(
                                                () -> SkyblockScathaTrackerHud.this.method7("total", new Object[0]), AnimatedColor.method12()
                                             )
                                          )
                                    )
                                    .method5(
                                       new HudComponentGroup(true, com.moonsworth.lunar.client.ui.hud.HudComponentGroup.Type.CENTER)
                                          .method5(
                                             new TextHudComponent(() -> SkyblockScathaTrackerHud.this.method7("total", new Object[0]), ChatFormatting.BLUE)
                                          )
                                          .method5(
                                             new TextHudComponent()
                                                .method2(
                                                   () -> String.format(
                                                      "%,d", SkyblockScathaTrackerHud.this.method2(this.OHCROHOHCIHICOCIHROIRCOIIICRCI).wormTotalCount
                                                   )
                                                )
                                          )
                                          .method5(
                                             new TextHudComponent()
                                                .method2(
                                                   () -> String.format(
                                                      "%,d", SkyblockScathaTrackerHud.this.method2(this.OHCROHOHCIHICOCIHROIRCOIIICRCI).scathaTotalCount
                                                   )
                                                )
                                          )
                                          .method5(
                                             new TextHudComponent()
                                                .method2(
                                                   () -> String.format(
                                                      "%,d", SkyblockScathaTrackerHud.this.method2(this.OHCROHOHCIHICOCIHROIRCOIIICRCI).totalCount
                                                   )
                                                )
                                          )
                                    )
                                    .method5(
                                       new PaddedHudComponent(
                                             new HudComponentGroup(true, com.moonsworth.lunar.client.ui.hud.HudComponentGroup.Type.CENTER)
                                                .method5(
                                                   new TextHudComponent(
                                                      () -> SkyblockScathaTrackerHud.this.method7("session", new Object[0]), ChatFormatting.AQUA
                                                   )
                                                )
                                                .method5(
                                                   new TextHudComponent()
                                                      .method2(
                                                         () -> String.format(
                                                            "%,d", SkyblockScathaTrackerHud.this.method4(this.OHCROHOHCIHICOCIHROIRCOIIICRCI).field1
                                                         )
                                                      )
                                                      .method9(ChatFormatting.GRAY)
                                                )
                                                .method5(
                                                   new TextHudComponent()
                                                      .method2(
                                                         () -> String.format(
                                                            "%,d", SkyblockScathaTrackerHud.this.method4(this.OHCROHOHCIHICOCIHROIRCOIIICRCI).field2
                                                         )
                                                      )
                                                      .method9(ChatFormatting.GRAY)
                                                )
                                                .method5(
                                                   new TextHudComponent()
                                                      .method2(
                                                         () -> String.format(
                                                            "%,d", SkyblockScathaTrackerHud.this.method4(this.OHCROHOHCIHICOCIHROIRCOIIICRCI).field3
                                                         )
                                                      )
                                                      .method9(ChatFormatting.GRAY)
                                                )
                                          )
                                          .method4(8.0F)
                                    )
                              )
                              .method10(2.0F)
                        )
                        .method2(() -> !(Boolean)SkyblockScathaTrackerHud.this.field19.get())
                  )
                  .method5(
                     new HideableHudComponent(
                           new HudComponentGroup(com.moonsworth.lunar.client.ui.hud.HudComponentGroup.Type.CENTER)
                              .method5(new ItemStackHudComponent(SkyblockScathaTrackerHud.this.field11).method9(true).method6(1.25))
                              .method5(
                                 new PaddedHudComponent(
                                       new TextHudComponent()
                                          .method2(
                                             () -> String.format("%,d", SkyblockScathaTrackerHud.this.method2(this.OHCROHOHCIHICOCIHROIRCOIIICRCI).rarePetCount)
                                          )
                                          .method11(ItemRarity.RARE.getNamedTextColor())
                                    )
                                    .method4(2.0F)
                                    .method6(8.0F)
                              )
                              .method5(new ItemStackHudComponent(SkyblockScathaTrackerHud.this.field11).method9(true).method6(1.25))
                              .method5(
                                 new PaddedHudComponent(
                                       new TextHudComponent()
                                          .method2(
                                             () -> String.format("%,d", SkyblockScathaTrackerHud.this.method2(this.OHCROHOHCIHICOCIHROIRCOIIICRCI).epicPetCount)
                                          )
                                          .method11(ItemRarity.EPIC.getNamedTextColor())
                                    )
                                    .method4(2.0F)
                                    .method6(8.0F)
                              )
                              .method5(new ItemStackHudComponent(SkyblockScathaTrackerHud.this.field11).method9(true).method6(1.25))
                              .method5(
                                 new PaddedHudComponent(
                                       new TextHudComponent()
                                          .method2(
                                             () -> String.format(
                                                "%,d", SkyblockScathaTrackerHud.this.method2(this.OHCROHOHCIHICOCIHROIRCOIIICRCI).legendaryPetCount
                                             )
                                          )
                                          .method11(ItemRarity.LEGENDARY.getNamedTextColor())
                                    )
                                    .method4(2.0F)
                              )
                        )
                        .method2(() -> !(Boolean)SkyblockScathaTrackerHud.this.field20.get())
                  )
            )
         );
      }
   }

   @KeepName
   public static class ScathaSessionStats {
      public int rarePetCount;
      public int epicPetCount;
      public int legendaryPetCount;
      public int streak;
      public int wormTotalCount;
      public int scathaTotalCount;
      public int totalCount;
      public int sinceLastPetCount;

      public ScathaSessionStats() {
      }
   }
}
