package com.moonsworth.lunar.client.mod.skyblock.terminalsolvers;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.SlotBridge;
import com.moonsworth.lunar.bridge.GuiContainerBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil.DyeColor;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.mixin.SkyblockMenuType;
import com.moonsworth.lunar.client.framework.feature.mod.impl.chest.dungeon.practice.TerminalSolverModule;
import com.moonsworth.lunar.client.framework.feature.mod.rewindhandlers.TerminalEvents.Data;
import com.moonsworth.lunar.client.framework.listener.ScreenTitleListener;
import com.moonsworth.lunar.client.event.render.EventRenderSlot;
import com.moonsworth.lunar.client.event.render.EventRenderHologram.EventRenderHologramItem;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import java.util.HashMap;
import java.util.List;

public class SkyblockClickInOrder extends TerminalSolverModule {
   private final ScreenTitleListener field11 = (ScreenTitleListener)this.method4(ScreenTitleListener.class);
   private final HashMap<SlotBridge, Integer> field12 = new HashMap<>();
   private int field13 = 1;

   public SkyblockClickInOrder(SkyblockTerminalSolvers skyblockterminalsolvers1, ToggleOption lightingextension4432) {
      super(SkyblockMenuType.TERMINAL_CLICK_IN_ORDER);
      this.method3(ModTraits.field16, ChildModBinding.method4(false, skyblockterminalsolvers1));
      this.method3(ModTraits.field6, ModEnabledState.method7(lightingextension4432));
      this.handle(EventRenderHologramItem.class, this::method5);
      this.handle(EventRenderSlot.class, this::method3);
      this.handle(EventTick.class, this::method4);
      this.handle(Data.class, this::method2);
   }

   public String getId() {
      return "SKYBLOCK_CLICK_IN_ORDER";
   }

   protected void method1(boolean flag1) {
   }

   private void method2(Data data1) {
      this.field12.clear();
      this.field13 = 1;
   }

   private void method3(EventRenderSlot highlightimpl51) {
      SkyblockTerminalSolvers skyblockterminalsolvers2 = (SkyblockTerminalSolvers)((ChildModBinding)this.method7(ModTraits.field16)).method1();
      if ((Boolean)skyblockterminalsolvers2.method40().get() && this.isInGui() && !skyblockterminalsolvers2.method41().isKeyDown()) {
         SlotBridge bridge3_183 = highlightimpl51.method5();
         if (bridge3_183 != null) {
            ItemStackBridge bridgeextension_44 = bridge3_183.bridge$getItemStack();
            if (SkyblockItemUtil.method5(bridgeextension_44) != DyeColor.RED) {
               highlightimpl51.cancel();
            } else {
               if (bridgeextension_44.bridge$getStackSize() != this.field13) {
                  highlightimpl51.cancel();
               }
            }
         }
      }
   }

   private void method4(EventTick highlightimpl21) {
      if (this.isInGui()) {
         GuiContainerBridge bridge5extension_32 = this.field11.method6();
         int number3 = this.field13;
         this.field13 = 15;
         this.field12.clear();
         List list4 = bridge5extension_32.bridge$inventorySlots();

         for (int index5 = 10; index5 <= 25; index5++) {
            SlotBridge bridge3_186 = (SlotBridge)list4.get(index5);
            ItemStackBridge bridgeextension_47 = bridge3_186.bridge$getItemStack();
            if (SkyblockItemUtil.method5(bridgeextension_47) == DyeColor.RED && bridgeextension_47.bridge$getStackSize() <= 14) {
               int number8 = bridgeextension_47.bridge$getStackSize();
               if (number8 < this.field13 && number8 >= number3) {
                  this.field13 = bridgeextension_47.bridge$getStackSize();
               }

               this.field12.put(bridge3_186, bridgeextension_47.bridge$getStackSize());
            }
         }

         if (this.field13 == 15) {
            this.field13 = number3;
         }
      }
   }

   private void method5(EventRenderHologramItem data51) {
      if (this.isInGui()) {
         if (this.field12 != null) {
            SlotBridge bridge3_182 = data51.method3();
            if (this.method3(bridge3_182, 36)) {
               SkyblockTerminalSolvers skyblockterminalsolvers3 = (SkyblockTerminalSolvers)((ChildModBinding)this.method7(ModTraits.field16)).method1();
               if (!this.field12.containsKey(bridge3_182)) {
                  if ((Boolean)skyblockterminalsolvers3.method34().get()) {
                     data51.method2(Bridge.method8().method41());
                  }
               } else {
                  int number4 = this.field12.get(bridge3_182);
                  int number5 = (Integer)skyblockterminalsolvers3.method36().get();
                  if (number4 - this.field13 == 0) {
                     data51.method1(skyblockterminalsolvers3.method35().method14(0.0F));
                  } else if (number5 >= 2 && number4 - this.field13 == 1) {
                     data51.method1(skyblockterminalsolvers3.method38().method14(0.0F));
                  } else if (number5 >= 3 && number4 - this.field13 == 2) {
                     data51.method1(skyblockterminalsolvers3.method39().method14(0.0F));
                  }
               }
            }
         }
      }
   }
}
