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
import com.moonsworth.lunar.client.config.option.ToggleOption;
import java.util.HashSet;
import java.util.Set;

public class SkyblockFirstLetter extends TerminalSolverModule {
   private final Set<Integer> field11 = new HashSet<>();
   private final Set<Integer> field12 = new HashSet<>();
   private final Set<Integer> field13 = new HashSet<>();
   private Character field14 = null;
   private boolean field15;

   public SkyblockFirstLetter(SkyblockTerminalSolvers skyblockterminalsolvers1, ToggleOption lightingextension4432) {
      super(SkyblockMenuType.TERMINAL_FIRST_LETTER);
      this.method7(ModTraits.field16, ChildModBinding.method4(false, skyblockterminalsolvers1));
      this.method7(ModTraits.field6, ModEnabledState.method7(lightingextension4432));
      this.handle(EventRenderSlot.class, arg1x -> {
         this.method6(arg1x);
         this.method7(arg1x);
      });
      this.handle(EventRenderHologramItem.class, this::method8);
      this.handle(com.moonsworth.lunar.client.event.render.EventRenderScreenOverlay.EventScreenInitPost.class, this::method4);
      this.handle(EventScreenOpen.class, this::method5);
      this.handle(EventSlotUpdate.class, arg1x -> {
         this.method2(arg1x);
         this.method3(arg1x);
      });
   }

   public String getId() {
      return "SKYBLOCK_FIRST_LETTER";
   }

   protected void method1(boolean flag1) {
   }

   private void method2(EventSlotUpdate highlightimpl1) {
      if (this.isInGui()) {
         if (highlightimpl1.getSlot() >= 0 && highlightimpl1.getSlot() < 45) {
            ItemStackBridge bridgeextension_42 = highlightimpl1.method3();
            if (bridgeextension_42 != null) {
               String text3 = ChatFormatting.getTextWithoutFormattingCodes(bridgeextension_42.bridge$getDisplayName());
               if (text3 != null && !text3.isEmpty()) {
                  if (text3.charAt(0) == this.field14) {
                     this.field13.add(highlightimpl1.getSlot());
                  }
               }
            }
         }
      }
   }

   private void method3(EventSlotUpdate highlightimpl1) {
      if (this.isInGui()) {
         if (highlightimpl1.getSlot() == 44) {
            if (!this.field13.equals(this.field12)) {
               this.field11.clear();
            }
         }
      }
   }

   private void method4(com.moonsworth.lunar.client.event.render.EventRenderScreenOverlay.EventScreenInitPost data51) {
      if (this.isInGui()) {
         this.field14 = this.getTitle().charAt(this.getTitle().indexOf("'") + 1);
         this.field15 = true;
      }
   }

   private void method5(EventScreenOpen highlightimpl91) {
      this.field14 = null;
      this.field12.clear();
      this.field12.addAll(this.field13);
      this.field13.clear();
   }

   private void method6(EventRenderSlot highlightimpl51) {
      SkyblockTerminalSolvers skyblockterminalsolvers2 = (SkyblockTerminalSolvers)((ChildModBinding)this.method7(ModTraits.field16)).method1();
      if ((Boolean)skyblockterminalsolvers2.method40().get() && this.isInGui() && !skyblockterminalsolvers2.method41().isKeyDown()) {
         SlotBridge bridge3_183 = highlightimpl51.method5();
         if (bridge3_183 != null) {
            int number4 = bridge3_183.bridge$getIndex();
            ItemStackBridge bridgeextension_45 = bridge3_183.bridge$getItemStack();
            if (bridgeextension_45 != null) {
               if (this.field11.contains(number4) || !this.field13.contains(number4)) {
                  highlightimpl51.cancel();
               }
            }
         }
      }
   }

   private void method7(EventRenderSlot highlightimpl51) {
      if (this.isInGui()) {
         if (!highlightimpl51.isCancelled()) {
            if (this.field15) {
               SlotBridge bridge3_182 = highlightimpl51.method5();
               if (bridge3_182 != null) {
                  this.field11.add(bridge3_182.bridge$getIndex());
                  this.field15 = false;
               }
            }
         }
      }
   }

   private void method8(EventRenderHologramItem data51) {
      if (this.isInGui()) {
         SlotBridge bridge3_182 = data51.method3();
         if (this.method7(bridge3_182, 45)) {
            SkyblockTerminalSolvers skyblockterminalsolvers3 = (SkyblockTerminalSolvers)((ChildModBinding)this.method7(ModTraits.field16)).method1();
            if (this.field13.contains(bridge3_182.bridge$getIndex()) && !this.field11.contains(bridge3_182.bridge$getIndex())) {
               data51.method1(skyblockterminalsolvers3.method35().method14(0.0F));
            } else if ((Boolean)skyblockterminalsolvers3.method34().get()) {
               data51.method2(Bridge.method8().method41());
            }
         }
      }
   }
}
