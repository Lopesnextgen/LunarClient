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
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil.DyeColor;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.mixin.SkyblockMenuType;
import com.moonsworth.lunar.client.framework.feature.mod.impl.ultrasequencer.ExperimentSolverModule;
import com.moonsworth.lunar.client.event.render.EventRenderScreenItem;
import com.moonsworth.lunar.client.event.render.EventRenderScreenOverlay.EventScreenInitPost;
import com.moonsworth.lunar.client.event.mixin.gui.EventSlotUpdate;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.util.collection.AbstractQueueImpl;
import com.moonsworth.lunar.client.framework.Ref;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Generated;

public class SkyblockSuperpairs extends ExperimentSolverModule {
   private final Map<SlotBridge, SkyblockSuperpairs.Data> field10 = new HashMap<>();
   private final AbstractQueueImpl<SlotBridge> field11 = new AbstractQueueImpl(2);
   private SkyblockSuperpairs.Type field12 = SkyblockSuperpairs.Type.BEFORE_FIRST;

   public SkyblockSuperpairs(SkyblockExperimentSolvers skyblockexperimentsolvers1, ToggleOption lightingextension4432) {
      super(SkyblockMenuType.EXPERIMENT_SUPERPAIR);
      this.method9(ModTraits.field16, ChildModBinding.method4(false, skyblockexperimentsolvers1));
      this.method9(ModTraits.field6, ModEnabledState.method7(lightingextension4432));
      this.method9(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(EventSlotUpdate.class, this::method6);
      this.handle(EventScreenInitPost.class, this::method5);
      this.handle(com.moonsworth.lunar.client.event.render.EventRenderContainerSlot.ItemRender.class, this::method2);
      this.handle(com.moonsworth.lunar.client.event.render.EventRenderHologram.EventRenderHologramItem.class, this::method3);
      this.handle(EventRenderScreenItem.class, this::method4);
   }

   public String getId() {
      return "SKYBLOCK_SUPERPAIRS";
   }

   protected void method1(boolean flag1) {
   }

   private void method2(com.moonsworth.lunar.client.event.render.EventRenderContainerSlot.ItemRender data1) {
      if (this.isInGui() && this.field11 != null && this.field12 != SkyblockSuperpairs.Type.GAME_OVER) {
         GuiContainerBridge bridge5extension_32 = (GuiContainerBridge)Ref.method3().bridge$getCurrentScreen();
         List list3 = bridge5extension_32.bridge$inventorySlots();
         SlotBridge bridge3_184 = (SlotBridge)this.field11.peekLast();
         SlotBridge bridge3_185 = (SlotBridge)this.field11.peek();

         for (SkyblockSuperpairs.Data data7 : this.field10.values()) {
            SlotBridge bridge3_188 = (SlotBridge)list3.get(data7.method1().bridge$getIndex());
            ItemStackBridge bridgeextension_49 = bridge3_188.bridge$getItemStack();
            if (bridgeextension_49 != null) {
               if (this.method8(bridgeextension_49)) {
                  if ((this.field12 != SkyblockSuperpairs.Type.AFTER_SECOND || bridge3_185 == null || !bridge3_185.equals(bridge3_188))
                     && (!this.field12.atLeast(SkyblockSuperpairs.Type.BEFORE_SECOND) || bridge3_184 == null || !bridge3_184.equals(bridge3_188))) {
                     SkyblockExperimentSolvers skyblockexperimentsolvers10 = (SkyblockExperimentSolvers)((ChildModBinding)this.method7(ModTraits.field16))
                        .method1();
                     LcuiScreen.method127(
                        data1.method5(), (GuiContainerBridge)data1.IOHOCRRIRROIRCHHHHHRCOIIOHCOHR(), bridge3_188, skyblockexperimentsolvers10.method17().method13()
                     );
                  }
               } else {
                  SkyblockExperimentSolvers skyblockexperimentsolvers11 = (SkyblockExperimentSolvers)((ChildModBinding)this.method7(ModTraits.field16)).method1();
                  if (data7.method3()) {
                     LcuiScreen.method127(
                        data1.method5(), (GuiContainerBridge)data1.IOHOCRRIRROIRCHHHHHRCOIIOHCOHR(), bridge3_188, skyblockexperimentsolvers11.method16().method13()
                     );
                  } else {
                     LcuiScreen.method127(
                        data1.method5(), (GuiContainerBridge)data1.IOHOCRRIRROIRCHHHHHRCOIIOHCOHR(), bridge3_188, skyblockexperimentsolvers11.method15().method13()
                     );
                  }
               }
            }
         }
      }
   }

   private void method3(com.moonsworth.lunar.client.event.render.EventRenderHologram.EventRenderHologramItem data51) {
      if (this.isInGui()) {
         SkyblockSuperpairs.Data data2 = this.field10.get(data51.IHIRRHICIIHIHCRRHOHHOOHOHCHHHI());
         if (data2 != null) {
            data51.method2(data2.method2());
         }
      }
   }

   private void method4(EventRenderScreenItem highlightimpl2_31) {
      if (this.isInGui()) {
         SkyblockSuperpairs.Data data2 = this.field10.get(highlightimpl2_31.method2());
         if (data2 != null) {
            highlightimpl2_31.method4(data2.method2());
         }
      }
   }

   private void method5(EventScreenInitPost data51) {
      if (this.isInGui()) {
         this.field11.clear();
         this.field10.clear();
      }
   }

   private void method6(EventSlotUpdate highlightimpl1) {
      if (this.isInGui() && highlightimpl1.getSlot() != -1 && highlightimpl1.getSlot() < 54) {
         ItemStackBridge bridgeextension_42 = highlightimpl1.method3();
         if (bridgeextension_42 != null) {
            if (bridgeextension_42.bridge$hasDisplayName()) {
               String text3 = bridgeextension_42.bridge$getRawDisplayName();
               switch (text3) {
                  case "Click any button!":
                     this.field12 = SkyblockSuperpairs.Type.BEFORE_FIRST;
                     break;
                  case "Click a second button!":
                     this.field12 = SkyblockSuperpairs.Type.BEFORE_SECOND;
                     break;
                  case "?":
                     this.field12 = SkyblockSuperpairs.Type.AFTER_SECOND;
               }
            }

            if (bridgeextension_42.bridge$hasCustomLore()) {
               this.method7(bridgeextension_42);
            }

            if (this.method8(bridgeextension_42)) {
               GuiContainerBridge bridge5extension_37 = (GuiContainerBridge)Ref.method3().bridge$getCurrentScreen();
               List list8 = bridge5extension_37.bridge$inventorySlots();
               SlotBridge bridge3_189 = (SlotBridge)list8.get(highlightimpl1.getSlot());
               if (!this.field10.containsKey(bridge3_189)) {
                  SkyblockSuperpairs.Data data6 = new SkyblockSuperpairs.Data(bridge3_189, bridgeextension_42);
                  this.method9(data6);
                  this.field11.add(data6.method1());
                  this.field10.put(bridge3_189, data6);
               }
            }
         }
      }
   }

   private void method7(ItemStackBridge bridgeextension_41) {
      for (String text4 : SkyblockItemUtil.method14(bridgeextension_41)) {
         if (text4.equals("§eClick any to proceed!")) {
            this.field12 = SkyblockSuperpairs.Type.GAME_OVER;
            return;
         }
      }
   }

   private boolean method8(ItemStackBridge bridgeextension_41) {
      ItemsBridge bridge2_212 = Bridge.method28();
      ItemBridge bridge6_43 = bridgeextension_41.bridge$getItem();
      return bridge6_43 != null
         && !bridge6_43.equals(bridge2_212.method20())
         && !bridge6_43.equals(bridge2_212.method36())
         && !bridge2_212.method94().contains(bridge6_43)
         && !bridge2_212.method93().contains(bridge6_43);
   }

   private void method9(SkyblockSuperpairs.Data data1) {
      if (this.isInGui() && this.field11 != null) {
         GuiContainerBridge bridge5extension_32 = (GuiContainerBridge)Ref.method3().bridge$getCurrentScreen();
         List list3 = bridge5extension_32.bridge$inventorySlots();
         ItemStackBridge bridgeextension_44 = data1.method2();

         for (SkyblockSuperpairs.Data data6 : this.field10.values()) {
            ItemStackBridge bridgeextension_47 = data6.method2();
            SlotBridge bridge3_188 = (SlotBridge)list3.get(data6.method1().bridge$getIndex());
            ItemStackBridge bridgeextension_49 = bridge3_188.bridge$getItemStack();
            if (bridgeextension_49 != null && !this.method8(bridgeextension_49)) {
               DyeColor type210 = SkyblockItemUtil.method8(bridgeextension_44);
               DyeColor type211 = SkyblockItemUtil.method8(bridgeextension_47);
               if (type210 != null) {
                  if (type210.equals(type211) && !data6.method3()) {
                     data6.method4(true);
                     data1.method4(true);
                     return;
                  }
               } else if (bridgeextension_44.bridge$getItem().equals(bridgeextension_47.bridge$getItem()) && !data6.method3()) {
                  data6.method4(true);
                  data1.method4(true);
                  return;
               }
            }
         }
      }
   }

   private static class Data {
      private final SlotBridge field1;
      private final ItemStackBridge field2;
      private boolean field3 = false;

      @Generated
      public SlotBridge method1() {
         return this.field1;
      }

      @Generated
      public ItemStackBridge method2() {
         return this.field2;
      }

      @Generated
      public boolean method3() {
         return this.field3;
      }

      @Generated
      public void method4(boolean flag1) {
         this.field3 = flag1;
      }

      @Generated
      public Data(SlotBridge bridge3_181, ItemStackBridge bridgeextension_42) {
         this.field1 = bridge3_181;
         this.field2 = bridgeextension_42;
      }
   }

   private enum Type implements Comparable<SkyblockSuperpairs.Type> {
      BEFORE_FIRST(1),
      BEFORE_SECOND(2),
      AFTER_SECOND(3),
      GAME_OVER(4);

      private final int ordinal;

      private boolean atLeast(SkyblockSuperpairs.Type type1) {
         return this.ordinal >= type1.ordinal();
      }

      @Generated
      Type(int number3) {
         this.ordinal = number3;
      }
   }
}
