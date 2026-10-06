package com.moonsworth.lunar.client.mod.skyblock.fossilexcavationsolver;

import com.moonsworth.lunar.bridge.SlotBridge;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.mod.impl.alert.excavation.Excavation;
import com.moonsworth.lunar.client.framework.feature.mod.impl.alert.excavation.ExcavationGrid;
import com.moonsworth.lunar.client.framework.feature.mod.impl.alert.excavation.mixin.FossilCellState;
import com.moonsworth.lunar.client.framework.listener.ScreenTitleListener;
import com.moonsworth.lunar.client.event.render.EventRenderHologram.EventRenderHologramItem;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.event.mixin.gui.EventSlotUpdate;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.config.option.ConstantName;
import java.util.List;

public class SkyblockFossilExcavationSolver extends AbstractFeature {
   private final ScreenTitleListener field8 = (ScreenTitleListener)this.method63(ScreenTitleListener.class);
   private boolean dirty = false;
   private Excavation field9;
   private int field10 = -1;

   public SkyblockFossilExcavationSolver(Skyblock skyblock1) {
      super(false);
      this.method3(ModTraits.field16, ChildModBinding.method4(true, skyblock1));
      this.method3(ModTraits.field17, ModCategories.method2(SettingsPage.MINING));
      this.method3(ModTraits.field19, DynamicCondition.method1(this, () -> IslandUtils.getIsland() == SkyblockIsland.DWARVEN_MINES));
      this.handle(EventSlotUpdate.class, this::method1);
      this.handle(EventTick.class, this::method2);
      this.handle(EventRenderHologramItem.class, this::method3);
   }

   @ConstantName
   public String getId() {
      return "SKYBLOCK_FOSSIL_EXCAVATION_SOLVER";
   }

   private void method1(EventSlotUpdate highlightimpl1) {
      this.dirty = true;
   }

   private void method2(EventTick highlightimpl21) {
      if (this.dirty) {
         this.dirty = false;
         this.field9 = null;
         this.field10 = -1;
         String text2 = this.field8.method5();
         if ("Fossil Excavator".equals(text2)) {
            ExcavationGrid excavation23 = ExcavationGrid.method1(this.field8.method6().bridge$inventorySlots().stream().map(SlotBridge::bridge$getItemStack).toList());
            List list4 = excavation23.method2();
            if (!list4.isEmpty()) {
               int number5 = 0;

               for (int index6 = 0; index6 < 9; index6++) {
                  for (int index7 = 0; index7 < 6; index7++) {
                     if (excavation23.method3(index6, index7) == FossilCellState.UNKNOWN) {
                        int number8 = index6 + index7 * 9;
                        int index9 = 0;

                        for (Excavation excavation11 : list4) {
                           if (excavation11.matches(index6, index7)) {
                              index9++;
                           }
                        }

                        if (index9 > number5) {
                           number5 = index9;
                           this.field10 = number8;
                        }
                     }
                  }
               }

               if (list4.size() == 1 && excavation23.method4()) {
                  this.field9 = (Excavation)list4.get(0);
               }
            }
         }
      }
   }

   private void method3(EventRenderHologramItem data51) {
      int number2 = data51.method3().bridge$getIndex() % 9;
      int number3 = data51.method3().bridge$getIndex() / 9;
      boolean flag4 = this.field10 == data51.method3().bridge$getIndex();
      if (this.field9 != null) {
         flag4 = this.field9.matches(number2, number3);
      }

      if (flag4 && FossilCellState.from(data51.method3().bridge$getItemStack()) == FossilCellState.UNKNOWN) {
         data51.method1(this.field9 == null ? -256 : -16711936);
      }
   }
}
