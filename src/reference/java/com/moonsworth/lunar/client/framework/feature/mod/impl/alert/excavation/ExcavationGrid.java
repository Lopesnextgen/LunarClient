package com.moonsworth.lunar.client.framework.feature.mod.impl.alert.excavation;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.client.framework.feature.mod.impl.alert.excavation.mixin.FossilCellState;
import java.util.List;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ExcavationGrid {
   private final FossilCellState[][] field1;

   private ExcavationGrid(FossilCellState[][] items1) {
      this.field1 = items1;
   }

   public static ExcavationGrid method1(List<ItemStackBridge> list) {
      FossilCellState[][] items1 = new FossilCellState[9][];

      for (int index2 = 0; index2 < 9; index2++) {
         items1[index2] = new FossilCellState[6];

         for (int index3 = 0; index3 < 6; index3++) {
            int index4 = index2 + index3 * 9;
            ItemStackBridge bridgeextension_45 = index4 < list.size() ? (ItemStackBridge)list.get(index4) : null;
            FossilCellState excavationtype36 = FossilCellState.from(bridgeextension_45);
            items1[index2][index3] = excavationtype36;
         }
      }

      return new ExcavationGrid(items1);
   }

   public List<Excavation> method2() {
      return StreamSupport.stream(Spliterators.spliteratorUnknownSize(new ExcavationIterator(), 0), false)
         .filter(arg1 -> arg1.method2(this))
         .collect(Collectors.toList());
   }

   public FossilCellState method3(int index1, int index2) {
      return this.field1[index1][index2];
   }

   public boolean method4() {
      for (int index1 = 0; index1 < 9; index1++) {
         for (int index2 = 0; index2 < 6; index2++) {
            if (this.field1[index1][index2] == FossilCellState.FOSSIL) {
               return true;
            }
         }
      }

      return false;
   }
}
