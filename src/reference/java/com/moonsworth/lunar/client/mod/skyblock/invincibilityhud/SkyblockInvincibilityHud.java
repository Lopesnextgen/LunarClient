package com.moonsworth.lunar.client.mod.skyblock.invincibilityhud;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.bridge.minecraft.EntityEquipmentSlotBridge;
import com.moonsworth.lunar.client.config.option.NamedColorOption;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudSize;
import com.moonsworth.lunar.client.ui.hud.HudLine;
import com.moonsworth.lunar.client.ui.hud.TypedHudRenderer;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.ChatMessageQueue;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.TextComponentFactory;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.framework.feature.mod.gui.nameplate.HudVisibilityWrapper;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.IntegerOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.framework.hud.HudTimer;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.apache.commons.lang3.text.WordUtils;
import org.jetbrains.annotations.Nullable;

public class SkyblockInvincibilityHud extends AbstractFeature {
   private static final ResourceLocationBridge field8 = ResourceLocationBridge.create("lunar", "skyblock/hud/totem_of_undying.png");
   private static final ResourceLocationBridge field9 = ResourceLocationBridge.create("lunar", "skyblock/hud/bonzo_mask.png");
   private static final ResourceLocationBridge field10 = ResourceLocationBridge.create("lunar", "skyblock/hud/spirit_mask.png");
   private static final ResourceLocationBridge field11 = ResourceLocationBridge.create("lunar", "skyblock/hud/phoenix.png");
   private static final Pattern field12 = Pattern.compile("^Cooldown: (?<time>\\d+)s$");
   private final ToggleOption field13 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("skyblockInvincibilityPrefix")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final EnumOption<SkyblockInvincibilityHud.InvincibilityTimerUnit> field14 = (EnumOption<SkyblockInvincibilityHud.InvincibilityTimerUnit>)OptionFactory.method10(
         "skyblockInvincibilityTimerMode", SkyblockInvincibilityHud.InvincibilityTimerUnit.SECONDS
      )
      .method31();
   private final IntegerOption field15 = (IntegerOption)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)((com.moonsworth.lunar.client.config.option.IntegerOption.Data)OptionFactory.method4(
               "skyblockInvincibilityDecimals"
            )
            .ORCRHOICOIHCRRIOHIHIROHOCRRIOO(2))
         .method7(0, 2))
      .method31();
   private final ToggleOption field16 = (ToggleOption)OptionFactory.method7("skyblockInvincibilityAnnounce").method31();
   private final ToggleOption field17 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("skyblockInvincibilityOnlyDungeons")
         .OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final EnumOption<NamedColorOption> field18 = (EnumOption<NamedColorOption>)OptionFactory.method10(
         "skyblockInvincibilityTimerColor", NamedColorOption.GREEN
      )
      .method31();
   private final EnumOption<NamedColorOption> field19 = (EnumOption<NamedColorOption>)OptionFactory.method10(
         "skyblockInvincibilityPrefixColor", NamedColorOption.GRAY
      )
      .method31();
   private final ToggleOption field20 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showInvincibilityTimer").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field21 = (ToggleOption)OptionFactory.method7("hideWhenNotInvincible").method31();
   private final ToggleOption field22 = (ToggleOption)OptionFactory.method7("hideWhenOffCooldown").method31();
   private final ToggleOption field23 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showBonzoMaskCooldown").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field24 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showSpiritMaskCooldown").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption field25 = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showPhoenixCooldown").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   @Nullable
   private SkyblockInvincibilityHud.Type field26;
   private HudTimer field27;
   private HudTimer field28;
   private HudTimer field29;
   private HudTimer field30;

   public SkyblockInvincibilityHud(Skyblock skyblock1) {
      super(false);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method2(ModTraits.field1, HudVisibilityWrapper.method4(new SkyblockInvincibilityHud.Data()));
      this.method2(ModTraits.field17, ModCategories.method2(SettingsPage.DUNGEONS));
      this.method2(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage.class, this::method1);
   }

   private void method1(com.moonsworth.lunar.client.event.mixin.EventChatMessage.TypedChatMessage data1) {
      if (!(Boolean)this.field17.get() || IslandUtils.getIsland() == SkyblockIsland.DUNGEON) {
         String text2 = data1.CRCORIIOCOCRIHHOHRHORCHRHCRIRH();
         if (!text2.contains(":")) {
            byte number3 = -1;
            SkyblockInvincibilityHud.Type type4 = null;
            if (text2.equals("Second Wind Activated! Your Spirit Mask saved your life!")) {
               number3 = 3;
               type4 = SkyblockInvincibilityHud.Type.SPIRIT_MASK;
               this.field29 = com.moonsworth.lunar.client.framework.hud.HudTimer.Data.method1().method2().method4().method5(30000L).method7().method2();
               this.method2("Spirit Mask", 3);
            } else if (text2.equals("Your Phoenix Pet saved you from certain death!")) {
               number3 = 4;
               type4 = SkyblockInvincibilityHud.Type.PHOENIX;
               this.field30 = com.moonsworth.lunar.client.framework.hud.HudTimer.Data.method1().method2().method4().method5(60000L).method7().method2();
               this.method2("Phoenix", 4);
            } else if (!text2.contains(":") && text2.endsWith("Bonzo's Mask saved your life!")) {
               number3 = 3;
               type4 = SkyblockInvincibilityHud.Type.BONZO_MASK;
               ItemStackBridge bridgeextension_45 = Ref.method7().bridge$getEquipmentInSlot(EntityEquipmentSlotBridge.HEAD);
               if (SkyblockItemUtil.method2(bridgeextension_45).endsWith("BONZO_MASK")) {
                  for (String text7 : SkyblockItemUtil.method15(bridgeextension_45)) {
                     Matcher matcher8 = field12.matcher(text7);
                     if (matcher8.matches()) {
                        this.field28 = com.moonsworth.lunar.client.framework.hud.HudTimer.Data.method1()
                           .method2()
                           .method4()
                           .method5(Long.parseLong(matcher8.group("time")) * 1000L)
                           .method7()
                           .method2();
                        break;
                     }
                  }
               }

               this.method2("Bonzo's Mask", 3);
            }

            if (number3 != -1) {
               this.field26 = type4;
               this.field27 = com.moonsworth.lunar.client.framework.hud.HudTimer.Data.method1().method2().method4().method5(number3 * 1000).method7().method2();
            }
         }
      }
   }

   private void method2(String text1, int number2) {
      if ((Boolean)this.field16.get()) {
         ChatMessageQueue.method1("/pc " + text1 + " activated! (" + number2 + "s)");
      }
   }

   public String getId() {
      return "SKYBLOCK_INVINCIBILITY_HUD";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL,
         arg1x -> {
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field13, arg1xx -> arg1xx.method9(new ClientOption[]{this.field19}));
            arg1x.method9(new ClientOption[]{this.field14});
            arg1x.method9(new ClientOption[]{this.field15})
               .method3(() -> this.field14.get() != SkyblockInvincibilityHud.InvincibilityTimerUnit.SECONDS);
            arg1x.method9(new ClientOption[]{this.field18, this.field16, this.field17});
            arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(
               this.field20, arg1xx -> arg1xx.method9(new ClientOption[]{this.field21, this.field22})
            );
            arg1x.method9(new ClientOption[]{this.field23, this.field24, this.field25});
         }
      );
   }

   private class Data extends TypedHudRenderer<List<HudLine>> {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.MIDDLE_LEFT, true, true);
      }

      public HudSize method15() {
         return HudSize.method1(50, 200, 400, 100, 140, 200);
      }

      @Nullable
      public List<HudLine> method2(boolean flag1) {
         if (flag1) {
            return this.method3(3200L, 8750L, -1L, 43600L, null);
         }

         if (!this.shouldRender()) {
            return null;
         }

         long number2 = SkyblockInvincibilityHud.this.field27 != null ? SkyblockInvincibilityHud.this.field27.get() : 0L;
         long number4 = SkyblockInvincibilityHud.this.field28 != null ? SkyblockInvincibilityHud.this.field28.get() : 0L;
         long number6 = SkyblockInvincibilityHud.this.field29 != null ? SkyblockInvincibilityHud.this.field29.get() : 0L;
         long number8 = SkyblockInvincibilityHud.this.field30 != null ? SkyblockInvincibilityHud.this.field30.get() : 0L;
         return this.method3(number2, number4, number6, number8, number2 > 0L ? SkyblockInvincibilityHud.this.field26 : null);
      }

      @Nullable
      private List<HudLine> method3(long number1, long number3, long number5, long number7, @Nullable SkyblockInvincibilityHud.Type type9) {
         ArrayList list10 = new ArrayList();
         if ((Boolean)SkyblockInvincibilityHud.this.field20.get() && (number1 > 0L || !(Boolean)SkyblockInvincibilityHud.this.field21.get())) {
            TextComponent text11;
            if (number1 > 0L) {
               String text12 = switch ((SkyblockInvincibilityHud.InvincibilityTimerUnit)SkyblockInvincibilityHud.this.field14.get()) {
                  case TICKS -> number1 / 50L + "t";
                  case SECONDS -> {
                     double value13 = number1 / 1000.0;
                     yield String.format("%." + SkyblockInvincibilityHud.this.field15.get() + "f", value13) + "s";
                  }
               };
               text11 = Component.text(text12, TextComponentFactory.styleOf(SkyblockInvincibilityHud.this.field18));
            } else {
               text11 = Component.text('✖', NamedTextColor.RED);
            }

            if ((Boolean)SkyblockInvincibilityHud.this.field13.get()) {
               text11 = (TextComponent)Component.text(
                     SkyblockInvincibilityHud.this.HHRROIIHRRICIIHIIHICRHHRHOHHOO("invincible", new Object[0]) + ": ",
                     TextComponentFactory.styleOf(SkyblockInvincibilityHud.this.field19)
                  )
                  .append(text11);
            }

            list10.add(new HudLine(SkyblockInvincibilityHud.field8, text11));
         }

         if (this.method4(SkyblockInvincibilityHud.this.field23, number3, type9 == SkyblockInvincibilityHud.Type.BONZO_MASK)) {
            TextComponent text15;
            if (number3 > 0L) {
               String text18 = String.format("%." + SkyblockInvincibilityHud.this.field15.get() + "f", number3 / 1000.0) + "s";
               text15 = Component.text(text18, TextComponentFactory.styleOf(SkyblockInvincibilityHud.this.field18));
            } else {
               text15 = Component.text('✔', NamedTextColor.GREEN);
            }

            if ((Boolean)SkyblockInvincibilityHud.this.field13.get()) {
               text15 = (TextComponent)Component.text("Bonzo's Mask: ", TextComponentFactory.styleOf(SkyblockInvincibilityHud.this.field19)).append(text15);
            }

            list10.add(new HudLine(SkyblockInvincibilityHud.field9, text15));
         }

         if (this.method4(SkyblockInvincibilityHud.this.field24, number5, type9 == SkyblockInvincibilityHud.Type.SPIRIT_MASK)) {
            TextComponent text16;
            if (number5 > 0L) {
               String text19 = String.format("%." + SkyblockInvincibilityHud.this.field15.get() + "f", number5 / 1000.0) + "s";
               text16 = Component.text(text19, TextComponentFactory.styleOf(SkyblockInvincibilityHud.this.field18));
            } else {
               text16 = Component.text('✔', NamedTextColor.GREEN);
            }

            if ((Boolean)SkyblockInvincibilityHud.this.field13.get()) {
               text16 = (TextComponent)Component.text("Spirit Mask: ", TextComponentFactory.styleOf(SkyblockInvincibilityHud.this.field19)).append(text16);
            }

            list10.add(new HudLine(SkyblockInvincibilityHud.field10, text16));
         }

         if (this.method4(SkyblockInvincibilityHud.this.field25, number7, type9 == SkyblockInvincibilityHud.Type.PHOENIX)) {
            TextComponent text17;
            if (number7 > 0L) {
               String text20 = String.format("%." + SkyblockInvincibilityHud.this.field15.get() + "f", number7 / 1000.0) + "s";
               text17 = Component.text(text20, TextComponentFactory.styleOf(SkyblockInvincibilityHud.this.field18));
            } else {
               text17 = Component.text('✔', NamedTextColor.GREEN);
            }

            if ((Boolean)SkyblockInvincibilityHud.this.field13.get()) {
               text17 = (TextComponent)Component.text("Phoenix: ", TextComponentFactory.styleOf(SkyblockInvincibilityHud.this.field19)).append(text17);
            }

            list10.add(new HudLine(SkyblockInvincibilityHud.field11, text17));
         }

         return list10.isEmpty() ? null : list10;
      }

      private boolean method4(ToggleOption lightingextension4431, long number2, boolean flag4) {
         return !lightingextension4431.get() ? false : !(Boolean)SkyblockInvincibilityHud.this.field22.get() || number2 > 0L || flag4;
      }

      protected boolean method20() {
         return false;
      }

      protected boolean method22() {
         return false;
      }

      private boolean shouldRender() {
         if (!IslandUtils.isOnIsland()) {
            return false;
         } else {
            return SkyblockInvincibilityHud.this.field17.get() && IslandUtils.getIsland() != SkyblockIsland.DUNGEON
               ? false
               : (Boolean)SkyblockInvincibilityHud.this.field20.get()
                  || (Boolean)SkyblockInvincibilityHud.this.field23.get()
                  || (Boolean)SkyblockInvincibilityHud.this.field24.get()
                  || (Boolean)SkyblockInvincibilityHud.this.field25.get();
         }
      }

      protected com.moonsworth.lunar.client.ui.hud.HudRowAlignment method16() {
         return com.moonsworth.lunar.client.ui.hud.HudRowAlignment.LEFT;
      }
   }

   private enum Type {
      BONZO_MASK,
      SPIRIT_MASK,
      PHOENIX;

      Type() {
      }
   }

   private enum InvincibilityTimerUnit implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
      SECONDS,
      TICKS;

      InvincibilityTimerUnit() {
      }

      public String id() {
         return "skyblockInvincibility" + WordUtils.capitalizeFully(this.name());
      }

      @Override
      public String toString() {
         return this.method51(this.id(), new Object[0]);
      }
   }
}
