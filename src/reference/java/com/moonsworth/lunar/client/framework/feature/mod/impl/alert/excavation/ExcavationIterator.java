package com.moonsworth.lunar.client.framework.feature.mod.impl.alert.excavation;

import com.google.common.collect.Lists;
import com.moonsworth.lunar.client.framework.feature.mod.impl.alert.excavation.mixin.ExcavationType;
import com.moonsworth.lunar.client.framework.feature.mod.impl.alert.excavation.mixin.FossilPattern;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcavationIterator implements Iterator<Excavation> {
   public static final int field1 = 9;
   public static final int field2 = 6;
   private final List<FossilPattern> field3 = Lists.newArrayList(FossilPattern.values());
   private final List<ExcavationType> field4 = this.method2();
   private int xOffset = 0;
   private int field5 = 0;

   public ExcavationIterator() {
   }

   @Override
   public boolean hasNext() {
      return !this.field3.isEmpty();
   }

   public Excavation method1() {
      FossilPattern excavationtype21 = this.field3.get(this.field3.size() - 1);
      ExcavationType excavationtype2 = this.field4.get(this.field4.size() - 1);
      int number3 = this.xOffset;
      int number4 = this.field5;
      Excavation excavation5 = new Excavation(excavationtype21, number3, number4, excavationtype2);
      this.xOffset++;
      if (this.xOffset + excavation5.getWidth() > 9) {
         this.xOffset = 0;
         this.field5++;
         if (this.field5 + excavation5.getHeight() > 6) {
            this.field5 = 0;
            this.field4.remove(this.field4.size() - 1);
            if (this.field4.isEmpty()) {
               this.field3.remove(this.field3.size() - 1);
               if (!this.field3.isEmpty()) {
                  FossilPattern excavationtype26 = this.field3.get(this.field3.size() - 1);
                  this.field4.addAll(this.method2());
                  this.field4.removeIf(arg1x -> 6 < (arg1x.isSwapXY() ? excavationtype26.getWidth() : excavationtype26.getHeight()));
               }
            }
         }
      }

      return excavation5;
   }

   private List<ExcavationType> method2() {
      FossilPattern excavationtype21 = this.field3.get(this.field3.size() - 1);
      ArrayList list2 = new ArrayList();

      label23:
      for (ExcavationType excavationtype6 : ExcavationType.values()) {
         Excavation excavation7 = new Excavation(excavationtype21, 0, 0, excavationtype6);

         for (ExcavationType excavationtype9 : list2) {
            Excavation excavation10 = new Excavation(excavationtype21, 0, 0, excavationtype9);
            if (excavation7.equals(excavation10)) {
               continue label23;
            }
         }

         list2.add(excavationtype6);
      }

      return list2;
   }
}
