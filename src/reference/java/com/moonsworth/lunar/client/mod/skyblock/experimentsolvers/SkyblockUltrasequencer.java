package com.moonsworth.lunar.client.mod.skyblock.experimentsolvers;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.ItemsBridge;
import com.moonsworth.lunar.bridge.SlotBridge;
import com.moonsworth.lunar.bridge.GuiContainerBridge;
import com.moonsworth.lunar.bridge.ItemBridge;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.mixin.SkyblockMenuType;
import com.moonsworth.lunar.client.framework.feature.mod.impl.ultrasequencer.ExperimentSolverModule;
import com.moonsworth.lunar.client.event.render.EventRenderSlot;
import com.moonsworth.lunar.client.event.screen.EventScreenOpen;
import com.moonsworth.lunar.client.event.render.EventRenderScreenOverlay.EventScreenInitPost;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.gui.EventSlotUpdate;
import com.moonsworth.lunar.client.framework.crash.CrashReporter;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;

public class SkyblockUltrasequencer extends ExperimentSolverModule {
   private final List<SkyblockUltrasequencer.Data> nodes = new LinkedList<>();
   private boolean field10;
   private int field11;
   private long field12;

   public SkyblockUltrasequencer(SkyblockExperimentSolvers skyblockexperimentsolvers1, ToggleOption lightingextension4432) {
      super(SkyblockMenuType.EXPERIMENT_ULTRASEQUENCER);
      this.method5(ModTraits.field16, ChildModBinding.method4(false, skyblockexperimentsolvers1));
      this.method5(ModTraits.field6, ModEnabledState.method7(lightingextension4432));
      this.method5(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(EventSlotUpdate.class, this::method8);
      this.handle(EventScreenInitPost.class, this::method6);
      this.handle(EventScreenOpen.class, this::method7);
      this.handle(EventRenderSlot.class, this::method5);
      this.handle(com.moonsworth.lunar.client.event.render.EventRenderContainerSlot.ItemRender.class, this::method4);
      this.handle(EventTick.class, this::method2);
      this.handle(com.moonsworth.lunar.client.event.render.EventRenderHologram.EventRenderHologramItem.class, this::method3);
   }

   public String getId() {
      return "SKYBLOCK_ULTRASEQUENCER";
   }

   protected void method1(boolean flag1) {
   }

   private void method2(EventTick highlightimpl21) {
      if (this.isInGui()) {
         if (EventTick.field1 == this.field12) {
            GuiContainerBridge bridge5extension_32 = (GuiContainerBridge)Ref.method3().bridge$getCurrentScreen();
            List list3 = bridge5extension_32.bridge$inventorySlots();

            for (int index4 = 0; index4 < 54; index4++) {
               SlotBridge bridge3_185 = (SlotBridge)list3.get(index4);
               if (this.method9(bridge3_185.bridge$getItemStack())) {
                  try {
                     this.nodes.add(new SkyblockUltrasequencer.Data(bridge3_185));
                  } catch (IllegalArgumentException illegalargumentexception7) {
                     CrashReporter.method5(illegalargumentexception7, "SkyBlockUltrasequencer");
                  }
               }
            }

            Collections.sort(this.nodes);
         }
      }
   }

   private void method3(com.moonsworth.lunar.client.event.render.EventRenderHologram.EventRenderHologramItem data51) {
      if (this.isInGui() && !this.field10) {
         for (SkyblockUltrasequencer.Data data3 : this.nodes) {
            if (data3.method2().equals(data51.IHIRRHICIIHIHCRRHOHHOOHOHCHHHI())) {
               data51.method2(data3.field3);
               return;
            }
         }
      }
   }

   private void method4(com.moonsworth.lunar.client.event.render.EventRenderContainerSlot.ItemRender data1) {
      if (this.isInGui() && !this.field10) {
         if (this.nodes.size() > this.field11) {
            LcuiScreen.method127(
               data1.method5(),
               (GuiContainerBridge)data1.IOHOCRRIRROIRCHHHHHRCOIIOHCOHR(),
               this.nodes.get(this.field11).method2(),
               -16711936
            );
            if (this.nodes.size() > this.field11 + 1) {
               LcuiScreen.method127(
                  data1.method5(),
                  (GuiContainerBridge)data1.IOHOCRRIRROIRCHHHHHRCOIIOHCOHR(),
                  this.nodes.get(this.field11 + 1).method2(),
                  -256
               );
            }
         }
      }
   }

   private void method5(EventRenderSlot highlightimpl51) {
      if (this.isInGui()) {
         if (!this.field10 && this.nodes.size() > this.field11 && this.nodes.get(this.field11).method2().equals(highlightimpl51.method5())) {
            this.field11++;
         } else {
            SkyblockExperimentSolvers skyblockexperimentsolvers2 = (SkyblockExperimentSolvers)((ChildModBinding)this.method7(ModTraits.field16)).method1();
            if ((Boolean)skyblockexperimentsolvers2.method13().get() && !skyblockexperimentsolvers2.method14().isKeyDown()) {
               highlightimpl51.cancel();
            }
         }
      }
   }

   private void method6(EventScreenInitPost data51) {
      if (this.isInGui()) {
         this.field10 = true;
         this.field12 = EventTick.field1 + 20;
      }
   }

   private void method7(EventScreenOpen highlightimpl91) {
      this.nodes.clear();
      this.field11 = 0;
   }

   private void method8(EventSlotUpdate highlightimpl1) {
      if (this.isInGui()) {
         ItemsBridge bridge2_212 = Bridge.method28();
         if (highlightimpl1.getSlot() == 49) {
            boolean flag3 = highlightimpl1.method3().bridge$getItem() == bridge2_212.method35();
            if (!flag3 && this.field10) {
               this.field11 = 0;
            } else if (flag3 && !this.field10) {
               this.nodes.clear();
               this.field11 = 0;
               this.field12 = EventTick.field1 + 20;
            }

            this.field10 = flag3;
         }
      }
   }

   private boolean method9(ItemStackBridge bridgeextension_41) {
      if (bridgeextension_41 != null && !bridgeextension_41.bridge$isEmpty()) {
         ItemsBridge bridge2_212 = Bridge.method28();
         ItemBridge bridge6_43 = bridgeextension_41.bridge$getItem();
         return !bridge6_43.equals(bridge2_212.method20()) && !bridge6_43.equals(bridge2_212.method35()) && !bridge2_212.method93().contains(bridge6_43);
      } else {
         return false;
      }
   }

   private static class Data implements Comparable<SkyblockUltrasequencer.Data> {
      private final SlotBridge field1;
      private final int field2;
      private final ItemStackBridge field3;

      private Data(SlotBridge bridge3_181) {
         this.field1 = bridge3_181;
         this.field3 = bridge3_181.bridge$getItemStack();
         if (this.field3 == null) {
            throw new IllegalArgumentException("Tried to create a new node with null ItemStack");
         }

         this.field2 = this.field3.bridge$getStackSize();
      }

      public int method1(@NotNull SkyblockUltrasequencer.Data data1) {
         return this.field2 - data1.field2;
      }

      @Generated
      public SlotBridge method2() {
         return this.field1;
      }

      @Generated
      public int getPosition() {
         return this.field2;
      }

      @Generated
      public ItemStackBridge method3() {
         return this.field3;
      }
   }
}
