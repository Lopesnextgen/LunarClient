package com.moonsworth.lunar.client.mod.skyblock.terminalsolvers;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.SlotBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.minecraft.ChatFormatting;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.mixin.SkyblockMenuType;
import com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.practice.TerminalSolverModule;
import com.moonsworth.lunar.client.event.render.EventRenderSlot;
import com.moonsworth.lunar.client.event.screen.EventScreenOpen;
import com.moonsworth.lunar.client.event.render.EventRenderHologram.EventRenderHologramItem;
import com.moonsworth.lunar.client.event.mixin.gui.EventSlotUpdate;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SkyblockSelectColor extends TerminalSolverModule {
   private static final Pattern field11 = Pattern.compile("^Select all the (?<color>[A-Z ]+) items!$");
   private final Set<Integer> field12 = new HashSet<>();
   private String field13;

   public SkyblockSelectColor(SkyblockTerminalSolvers skyblockterminalsolvers1, ToggleOption lightingextension4432) {
      super(SkyblockMenuType.TERMINAL_SELECT_COLOR);
      this.method3(ModTraits.field16, ChildModBinding.method4(false, skyblockterminalsolvers1));
      this.method3(ModTraits.field6, ModEnabledState.method7(lightingextension4432));
      this.handle(EventRenderHologramItem.class, this::method6);
      this.handle(com.moonsworth.lunar.client.event.render.EventRenderScreenOverlay.EventScreenInitPost.class, this::method5);
      this.handle(EventScreenOpen.class, this::method4);
      this.handle(EventRenderSlot.class, this::method3);
      this.handle(EventSlotUpdate.class, this::method2);
   }

   public String getId() {
      return "SKYBLOCK_SELECT_COLOR";
   }

   protected void method1(boolean flag1) {
   }

   private void method2(EventSlotUpdate highlightimpl1) {
      if (this.isInGui() && this.field13 != null) {
         int index2 = highlightimpl1.getSlot();
         if (index2 >= 0 && index2 < 54) {
            ItemStackBridge bridgeextension_43 = highlightimpl1.method3();
            String text4 = ChatFormatting.getTextWithoutFormattingCodes(bridgeextension_43.bridge$getDisplayName()).toUpperCase(Locale.ROOT);
            if (this.method7(text4, this.field13)) {
               this.field12.add(index2);
            }
         }
      }
   }

   private void method3(EventRenderSlot highlightimpl51) {
      SkyblockTerminalSolvers skyblockterminalsolvers2 = (SkyblockTerminalSolvers)((ChildModBinding)this.method7(ModTraits.field16)).method1();
      if ((Boolean)skyblockterminalsolvers2.method40().get() && this.isInGui() && !skyblockterminalsolvers2.method41().isKeyDown()) {
         SlotBridge bridge3_183 = highlightimpl51.method5();
         if (bridge3_183 != null) {
            int number4 = bridge3_183.bridge$getIndex();
            ItemStackBridge bridgeextension_45 = bridge3_183.bridge$getItemStack();
            if (bridgeextension_45 != null) {
               if (bridgeextension_45.bridge$isItemEnchanted() || !this.field12.contains(number4)) {
                  highlightimpl51.cancel();
               }
            }
         }
      }
   }

   private void method4(EventScreenOpen highlightimpl91) {
      this.field13 = null;
      this.field12.clear();
   }

   private void method5(com.moonsworth.lunar.client.event.render.EventRenderScreenOverlay.EventScreenInitPost data51) {
      if (this.isInGui()) {
         Matcher matcher2 = field11.matcher(this.getTitle());
         if (!matcher2.matches()) {
            CrashReporter.method5(new IllegalStateException("Bad regex for SelectColor GUI"), "SkyblockSelectColor");
         } else {
            this.field13 = matcher2.group("color");
         }
      }
   }

   private void method6(EventRenderHologramItem data51) {
      if (this.isInGui()) {
         SlotBridge bridge3_182 = data51.IHIRRHICIIHIHCRRHOHHOOHOHCHHHI();
         if (this.method3(bridge3_182, 54)) {
            SkyblockTerminalSolvers skyblockterminalsolvers3 = (SkyblockTerminalSolvers)((ChildModBinding)this.method7(ModTraits.field16)).method1();
            if (this.field12.contains(bridge3_182.bridge$getIndex()) && !this.method3(bridge3_182.bridge$getItemStack())) {
               data51.method1(skyblockterminalsolvers3.method35().method14(0.0F));
            } else if ((Boolean)skyblockterminalsolvers3.method34().get()) {
               data51.method2(Bridge.method8().method41());
            }
         }
      }
   }

   private boolean method7(String text1, String text2) {
      return text1.contains("LIGHT " + text2)
         ? false
         : text1.contains(text2)
            || this.field13.equals("SILVER") && text1.contains("LIGHT GRAY")
            || this.field13.equals("WHITE") && (text1.equals("WOOL") || text1.contains("BONE MEAL"))
            || this.field13.equals("BLACK") && text1.contains("INK SACK")
            || this.field13.equals("BLUE") && text1.contains("LAPIS LAZULI")
            || this.field13.equals("BROWN") && text1.contains("COCOA BEAN");
   }
}
