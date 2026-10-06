package com.moonsworth.lunar.client.mod.movement.togglesneak;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudSize;
import com.moonsworth.lunar.client.ui.hud.TypedHudRenderer;
import com.moonsworth.lunar.client.ui.hud.HudConditionSet;
import com.moonsworth.lunar.client.framework.feature.toggle.ToggleActionType;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.TextOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.movement.togglesneak.ToggleSneak;
import com.moonsworth.lunar.client.framework.Ref;
import java.text.DecimalFormat;
import org.jetbrains.annotations.Nullable;

public class ToggleSneakHud extends AbstractFeature {
   private static final ResourceLocationBridge SPRINTING_ICON = ResourceLocationBridge.create("lunar", "icons/sprinting.png");
   private static final ResourceLocationBridge SNEAKING_ICON = ResourceLocationBridge.create("lunar", "icons/sneaking.png");
   private final DecimalFormat speedFormat = new DecimalFormat("#.00");
   private final ToggleOption iconMode = (ToggleOption)OptionFactory.method7("iconMode").method31();
   protected final TextOption sprintingText = (TextOption)OptionFactory.method12("sprintingText").method31();
   protected final TextOption sneakingText = (TextOption)OptionFactory.method12("sneakingText").method31();
   protected final TextOption flyingText = (TextOption)OptionFactory.method12("flyingText").method31();
   private final ToggleOption showRidingText = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("showRidingText").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();
   private final ToggleOption descendingText = (ToggleOption)((ToggleOptionBuilder)OptionFactory.method7("descendingText").OOOIROIIOCOOHICRIRHHHRROHHHHIO(true))
      .method31();

   public ToggleSneakHud(ToggleSneak togglesneak1) {
      super(true);
      this.method3(ModTraits.field16, ChildModBinding.method3(togglesneak1));
      this.method3(ModTraits.field17, ModCategories.method2(SettingsPage.GENERAL));
      this.method3(ModTraits.field1, new ToggleSneakHud.Data());
   }

   public String getId() {
      return "TOGGLE_SNEAK_HUD_CHILD";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(
         SettingsPage.GENERAL,
         arg1x -> arg1x.method9(
            new ClientOption[]{this.iconMode, this.sprintingText, this.sneakingText, this.flyingText, this.showRidingText, this.descendingText}
         )
      );
   }

   private class Data extends TypedHudRenderer<String> {
      public Data() {
         super(0.0F, 0.0F, HudAnchor.TOP_RIGHT);
      }

      protected void method1(MixinHelper_4 mixinhelper_41, TypedHudRenderer<String> mixincore82, String text3, float value4, float value5, boolean flag6, boolean flag7, boolean flag8) {
         if ((Boolean)ToggleSneakHud.this.iconMode.get()) {
            float value9 = Ref.method10().bridge$getStringWidth(text3);
            Bridge5Extension_5 bridge5extension_510 = Ref.method7();
            ResourceLocationBridge horsestats1411 = bridge5extension_510 != null && bridge5extension_510.bridge$isSneaking() ? ToggleSneakHud.SNEAKING_ICON : ToggleSneakHud.SPRINTING_ICON;
            mixinhelper_41.method24(horsestats1411, (int)(value4 + value9 / 2.0F - 12.0F), (int)(value5 - 12.0F), 24, 24, -1);
            this.OHOHCCIHCRCOOIIHRHCIIRRRCIOHRR
               .HHRROIIHRRICIIHIIHICRHHRHOHHOO(mixinhelper_41, text3, value4, value5 + 12.0F, flag7, flag6 ? this.OOOCCCRICCHOORCCRHHRHHCOOCORRC : null);
         } else {
            this.OHOHCCIHCRCOOIIHRHCIIRRRCIOHRR.HHRROIIHRRICIIHIIHICRHHRHOHHOO(mixinhelper_41, text3, value4, value5, flag7, flag6 ? this.OOOCCCRICCHOORCCRHHRHHCOOCORRC : null);
         }
      }

      protected float method15(boolean flag1, float value2) {
         return super.method15(flag1, value2) + (ToggleSneakHud.this.iconMode.get() ? 24 : 0);
      }

      @Nullable
      public String method3(boolean flag1) {
         if (ToggleSneakHud.this.mc.bridge$getPlayer() == null) {
            return flag1 ? "Sprinting" : "";
         }

         String text2 = this.getText();
         return flag1 && "".equals(text2) ? "Sprinting" : text2;
      }

      private String getText() {
         String text1 = "";
         boolean flag2 = (Boolean)ToggleSneakHud.this.method6(
            "flying", ToggleSneakHud.this.mc.bridge$getPlayer().bridge$getPlayerCapabilities().bridge$isFlying()
         );
         boolean flag3 = (Boolean)ToggleSneakHud.this.method6("riding", ToggleSneakHud.this.mc.bridge$getPlayer().bridge$isRiding());
         boolean flag4 = (Boolean)ToggleSneakHud.this.method6(
            "holdingSneak", Bridge.method18().method1(ToggleSneakHud.this.mc.bridge$getGameSettings().bridge$keyBindSneak().bridge$getKey())
         );
         boolean flag5 = (Boolean)ToggleSneakHud.this.method6(
            "holdingSprint", Bridge.method18().method1(ToggleSneakHud.this.mc.bridge$getGameSettings().bridge$keyBindSprint().bridge$getKey())
         );
         ToggleActionType calculator2type6 = null;
         ToggleSneak togglesneak7 = (ToggleSneak)((ChildModBinding)ToggleSneakHud.this.RHRHIOOCICIORIOCIHHCIIRCRHHOII(ModTraits.field16)).method1();
         if (flag2) {
            if ((Boolean)togglesneak7.field16.get() && flag5) {
               calculator2type6 = ToggleActionType.FLYING_BOOST;
               text1 = text1 + this.formatActionText(calculator2type6, ToggleSneakHud.this.speedFormat.format(togglesneak7.field17.get()));
            } else {
               calculator2type6 = ToggleActionType.FLYING;
               text1 = text1 + this.formatActionText(calculator2type6);
            }
         }

         if (flag3 && (Boolean)ToggleSneakHud.this.showRidingText.get()) {
            calculator2type6 = ToggleActionType.RIDING;
            text1 = this.formatActionText(ToggleActionType.RIDING);
         }

         if ((Boolean)ToggleSneakHud.this.method6("sneak", ToggleSneakHud.this.mc.bridge$getGameSettings().bridge$keyBindSneak().bridge$isKeyDown())) {
            if (flag2) {
               text1 = this.method5(ToggleActionType.DESCENDING, calculator2type6);
            } else if (flag3 && calculator2type6 != null) {
               text1 = this.method5(ToggleActionType.DISMOUNTING, calculator2type6);
            } else if (flag4 && !togglesneak7.isSneak()) {
               text1 = text1 + this.formatActionText(ToggleActionType.SNEAKING_HELD);
            } else {
               text1 = text1 + this.formatActionText(ToggleActionType.SNEAKING_TOGGLED);
            }
         } else if ((Boolean)ToggleSneakHud.this.method6(
            "sprint", ToggleSneakHud.this.mc.bridge$getGameSettings().bridge$keyBindSprint().bridge$isKeyDown()
         )) {
            if (!flag2 && !flag3) {
               boolean flag8 = !(Boolean)togglesneak7.field9.get();
               if (flag5 && !togglesneak7.method38()) {
                  text1 = text1 + this.formatActionText(ToggleActionType.SPRINTING_HELD);
               } else if (togglesneak7.method41()) {
                  text1 = text1 + this.formatActionText(ToggleActionType.SPRINTING_HELD);
               } else if (flag8) {
                  text1 = text1 + this.formatActionText(ToggleActionType.SPRINTING_VANILLA);
               } else {
                  text1 = text1 + this.formatActionText(ToggleActionType.SPRINTING_TOGGLED);
               }
            }
         } else if (!flag2 && Ref.method7().bridge$isSprinting()) {
            text1 = text1 + this.formatActionText(ToggleActionType.SPRINTING_VANILLA);
         }

         return text1;
      }

      private String formatActionText(ToggleActionType calculator2type1, Object... items2) {
         return this.method5(calculator2type1, null, items2);
      }

      private String method5(ToggleActionType calculator2type1, ToggleActionType calculator2type2, Object... items3) {
         if ((Boolean)ToggleSneakHud.this.iconMode.get()) {
            switch (calculator2type1) {
               case FLYING_BOOST:
               case SPRINTING_VANILLA:
                  calculator2type1 = ToggleActionType.VANILLA;
                  break;
               case SNEAKING_TOGGLED:
               case SPRINTING_TOGGLED:
                  calculator2type1 = ToggleActionType.TOGGLED;
                  break;
               case SNEAKING_HELD:
               case SPRINTING_HELD:
                  calculator2type1 = ToggleActionType.HELD;
            }
         }

         if ((Boolean)ToggleSneakHud.this.descendingText.get() || calculator2type1 != ToggleActionType.DESCENDING && calculator2type1 != ToggleActionType.DISMOUNTING) {
            if ((Boolean)this.HIOICORHOCCRCOIHCRIIROIOIOIRIC.get()) {
               return calculator2type1.getMainText(ToggleSneakHud.this);
            }

            StringBuilder builder4 = new StringBuilder();
            if (calculator2type2 != null) {
               builder4.append(this.formatActionText(calculator2type2, items3)).append(" ");
            }

            builder4.append(calculator2type1.getMainText(ToggleSneakHud.this));
            if (calculator2type1.getPartition(items3).isPresent()) {
               builder4.append(" (").append((String)calculator2type1.getPartition(items3).get()).append(")");
            }

            return builder4.toString();
         } else {
            return this.formatActionText(calculator2type2, items3);
         }
      }

      public HudSize method15() {
         return new HudSize(10, 18, 22, 50, 56, 80);
      }

      public HudConditionSet method5() {
         return HudConditionSet.method5()
            .method5(
               arg1 -> !arg1.contains(ToggleActionType.DESCENDING.getMainText(ToggleSneakHud.this))
                  && !arg1.contains(ToggleActionType.DISMOUNTING.getMainText(ToggleSneakHud.this))
            )
            .method8();
      }

      protected boolean method17() {
         return false;
      }
   }
}
