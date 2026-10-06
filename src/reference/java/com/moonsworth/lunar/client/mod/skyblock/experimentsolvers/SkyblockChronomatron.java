package com.moonsworth.lunar.client.mod.skyblock.experimentsolvers;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.ItemsBridge;
import com.moonsworth.lunar.bridge.SlotBridge;
import com.moonsworth.lunar.bridge.GuiScreenBridge;
import com.moonsworth.lunar.bridge.GuiContainerBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil.DyeColor;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.mixin.SkyblockMenuType;
import com.moonsworth.lunar.client.framework.feature.mod.impl.ultrasequencer.ExperimentSolverModule;
import com.moonsworth.lunar.client.event.render.EventRenderSlot;
import com.moonsworth.lunar.client.event.screen.EventScreenOpen;
import com.moonsworth.lunar.client.event.render.EventRenderContainerSlot.ItemRender;
import com.moonsworth.lunar.client.event.mixin.gui.EventSlotUpdate;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import toxi.util.datatypes.ArraySet;

public class SkyblockChronomatron extends ExperimentSolverModule {
   private final Queue<DyeColor> field10 = new ArrayDeque<>();
   private final Set<SlotBridge> field11 = new ArraySet();
   private boolean field12;
   private long field13;

   public SkyblockChronomatron(SkyblockExperimentSolvers skyblockexperimentsolvers1, ToggleOption lightingextension4432) {
      super(SkyblockMenuType.EXPERIMENT_CHRONOMATRON);
      this.method4(ModTraits.field16, ChildModBinding.method4(false, skyblockexperimentsolvers1));
      this.method4(ModTraits.field6, ModEnabledState.method7(lightingextension4432));
      this.method4(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(EventSlotUpdate.class, this::method6);
      this.handle(EventScreenOpen.class, this::method5);
      this.handle(EventRenderSlot.class, this::method4);
      this.handle(ItemRender.class, this::method2);
   }

   public String getId() {
      return "SKYBLOCK_CHRONOMATRON";
   }

   protected void method1(boolean flag1) {
   }

   private void method2(ItemRender data1) {
      if (this.isInGui()) {
         for (SlotBridge bridge3_183 : this.field11) {
            LcuiScreen.method127(data1.method5(), (GuiContainerBridge)data1.method3(), bridge3_183, -16711936);
         }
      }
   }

   private void method13() {
      this.field11.clear();
      GuiScreenBridge bridge5extension61 = Ref.method3().bridge$getCurrentScreenOrRewind();
      if (bridge5extension61 != null) {
         if (bridge5extension61 instanceof GuiContainerBridge bridge5extension_32) {
            List list3 = bridge5extension_32.bridge$inventorySlots();

            for (int index4 = 0; index4 < 55; index4++) {
               SlotBridge bridge3_185 = (SlotBridge)list3.get(index4);
               ItemStackBridge bridgeextension_46 = bridge3_185.bridge$getItemStack();
               if (bridgeextension_46 != null) {
                  DyeColor type27 = SkyblockItemUtil.method4(bridgeextension_46);
                  if (type27 != null && type27.equals(this.field10.peek())) {
                     this.field11.add(bridge3_185);
                  } else {
                     type27 = SkyblockItemUtil.method7(bridgeextension_46);
                     if (type27 != null && type27.equals(this.field10.peek())) {
                        this.field11.add(bridge3_185);
                     }
                  }
               }
            }
         }
      }
   }

   private void method4(EventRenderSlot highlightimpl51) {
      if (this.isInGui()) {
         if (!this.field12 && this.field11.contains(highlightimpl51.method5())) {
            this.field10.poll();
            this.method13();
         } else {
            SkyblockExperimentSolvers skyblockexperimentsolvers2 = (SkyblockExperimentSolvers)((ChildModBinding)this.method7(ModTraits.field16)).method1();
            if ((Boolean)skyblockexperimentsolvers2.method13().get() && !skyblockexperimentsolvers2.method14().isKeyDown()) {
               highlightimpl51.cancel();
            }
         }
      }
   }

   private void method5(EventScreenOpen highlightimpl91) {
      this.field10.clear();
      this.field11.clear();
   }

   private void method6(EventSlotUpdate highlightimpl1) {
      if (this.isInGui()) {
         ItemsBridge bridge2_212 = Bridge.method28();
         if (highlightimpl1.getSlot() == 49) {
            boolean flag4 = highlightimpl1.method3().bridge$getItem() == bridge2_212.method35();
            if (!flag4 && this.field12) {
               this.method13();
            } else if (flag4 && !this.field12) {
               this.field10.clear();
               this.method13();
            }

            this.field12 = flag4;
         } else {
            DyeColor type23 = SkyblockItemUtil.method7(highlightimpl1.method3());
            if (type23 != null) {
               if (this.field12 && Ref.method3().bridge$getSystemTime() - this.field13 > 350L) {
                  this.field10.add(type23);
               }

               this.field13 = Ref.method3().bridge$getSystemTime();
            }
         }
      }
   }
}
