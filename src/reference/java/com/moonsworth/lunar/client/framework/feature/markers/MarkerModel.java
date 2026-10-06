package com.moonsworth.lunar.client.framework.feature.markers;

import com.moonsworth.lunar.bridge.Bridge_65;
import com.moonsworth.lunar.client.ui.LcuiScreen;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.util.Objects;

public abstract class MarkerModel<T extends MarkerModel<T>> {
   protected final double field1;
   protected final double field2;

   private MarkerModel(double var1, double var3, boolean var5) {
      this.field1 = var1 * (var5 ? 1.0 : this.method9());
      this.field2 = var3 * (var5 ? 1.0 : this.method9());
   }

   public static MarkerModel.Data3 method1() {
      return new MarkerModel.Data3(ThreadModuleDump63.method3().bridge$displayWidth(), ThreadModuleDump63.method3().bridge$displayHeight());
   }

   public abstract MarkerModel.Data6 method6();

   public MarkerModel.Data3 method3() {
      return this.method6().method3();
   }

   public MarkerModel.Data4 method7() {
      return this.method6().method7();
   }

   public MarkerModel.Data2 method5() {
      return this.method6().method5();
   }

   public MarkerModel.Data5 method8() {
      return this.method6().method8();
   }

   public abstract double method9();

   public double method10() {
      return this.field1 / this.method9();
   }

   public double method11() {
      return this.field2 / this.method9();
   }

   public float method12() {
      return (float)this.method10();
   }

   public float method13() {
      return (float)this.method11();
   }

   public int xi() {
      return (int)this.method10();
   }

   public int method14() {
      return (int)this.method11();
   }

   public abstract T method13(MarkerModel.Data6 var1);

   public T method14(double var1, double var3) {
      return this.method13(new MarkerModel.Data6(this.field1 + var1 * this.method9(), this.field2 + var3 * this.method9()));
   }

   public T method15(double var1, double var3) {
      return this.method13(new MarkerModel.Data6(this.field1 - var1 * this.method9(), this.field2 - var3 * this.method9()));
   }

   public T method16(double var1) {
      return this.method13(new MarkerModel.Data6(this.field1 * var1, this.field2 * var1));
   }

   public T method17(double var1) {
      return this.method13(new MarkerModel.Data6(this.field1 / var1, this.field2 / var1));
   }

   public T method18(double var1, double var3) {
      return this.method13(new MarkerModel.Data6(this.field1 / var1, this.field2 / var3));
   }

   public T method19(MarkerModel<?> var1) {
      return this.method13(new MarkerModel.Data6(this.field1 + var1.field1, this.field2 + var1.field2));
   }

   public T method20(MarkerModel<?> var1) {
      return this.method13(new MarkerModel.Data6(this.field1 - var1.field1, this.field2 - var1.field2));
   }

   public T method21(double var1) {
      return this.method14(var1, 0.0);
   }

   public T method22(double var1) {
      return this.method14(0.0, var1);
   }

   public T method23(double var1) {
      return this.method15(var1, 0.0);
   }

   public T method24(double var1) {
      return this.method15(0.0, var1);
   }

   @Override
   public boolean equals(Object var1) {
      if (var1 != null && this.getClass() == var1.getClass()) {
         MarkerModel var2 = (MarkerModel)var1;
         return Double.compare(this.field1, var2.field1) == 0 && Double.compare(this.field2, var2.field2) == 0;
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.field1, this.field2);
   }

   public static class Data2 extends MarkerModel<MarkerModel.Data2> {
      public Data2(double var1, double var3) {
         super(var1, var3, false);
      }

      public Data2(MarkerModel.Data6 var1) {
         super(var1.IRHRRROCORORCIORHHORCCHIHHHHRH, var1.OOIHRIHHOCIRHHIIHICCHIOCCIOHRC, true);
      }

      @Override
      public double method9() {
         return LcuiScreen.method17() / LcuiScreen.method19();
      }

      public MarkerModel.Data2 method2(MarkerModel.Data6 var1) {
         return new MarkerModel.Data2(var1);
      }

      @Override
      public MarkerModel.Data6 method6() {
         return new MarkerModel.Data6(this);
      }

      @Override
      public MarkerModel.Data2 method5() {
         return this;
      }
   }

   public static class Data3 extends MarkerModel<MarkerModel.Data3> {
      public Data3(double var1, double var3) {
         super(var1, var3, false);
      }

      public Data3(MarkerModel.Data6 var1) {
         super(var1.IRHRRROCORORCIORHHORCCHIHHHHRH, var1.OOIHRIHHOCIRHHIIHICCHIOCCIOHRC, true);
      }

      @Override
      public double method9() {
         return 1.0 / LcuiScreen.method19();
      }

      public MarkerModel.Data3 method2(MarkerModel.Data6 var1) {
         return new MarkerModel.Data3(var1);
      }

      @Override
      public MarkerModel.Data6 method6() {
         return new MarkerModel.Data6(this);
      }

      @Override
      public MarkerModel.Data3 method3() {
         return this;
      }
   }

   public static class Data4 extends MarkerModel<MarkerModel.Data4> implements Bridge_65 {
      public Data4(double var1, double var3) {
         super(var1, var3, false);
      }

      public Data4(MarkerModel.Data6 var1) {
         super(var1.IRHRRROCORORCIORHHORCCHIHHHHRH, var1.OOIHRIHHOCIRHHIIHICCHIOCCIOHRC, true);
      }

      @Override
      public double method9() {
         return LcuiScreen.method151() == null ? 1.0 : LcuiScreen.method151().method3() / LcuiScreen.method19();
      }

      public MarkerModel.Data4 method2(MarkerModel.Data6 var1) {
         return new MarkerModel.Data4(var1);
      }

      @Override
      public MarkerModel.Data6 method6() {
         return new MarkerModel.Data6(this);
      }

      @Override
      public MarkerModel.Data4 method7() {
         return this;
      }
   }

   public static class Data5 extends MarkerModel<MarkerModel.Data5> {
      public Data5(double var1, double var3) {
         super(var1, var3, false);
      }

      public Data5(MarkerModel.Data6 var1) {
         super(var1.IRHRRROCORORCIORHHORCCHIHHHHRH, var1.OOIHRIHHOCIRHHIIHICCHIOCCIOHRC, true);
      }

      @Override
      public double method9() {
         return com.moonsworth.lunar.client.driver.core.DriverViewportLegacy.method50() == null
            ? 1.0
            : com.moonsworth.lunar.client.driver.core.DriverViewportLegacy.method50().method74() / LcuiScreen.method19();
      }

      public MarkerModel.Data5 method2(MarkerModel.Data6 var1) {
         return new MarkerModel.Data5(var1);
      }

      @Override
      public MarkerModel.Data6 method6() {
         return new MarkerModel.Data6(this);
      }

      @Override
      public MarkerModel.Data5 method8() {
         return this;
      }
   }

   public static class Data6 extends MarkerModel<MarkerModel.Data6> {
      public Data6(double var1, double var3) {
         super(var1, var3, false);
      }

      public Data6(MarkerModel<?> var1) {
         super(var1.field1, var1.field2, true);
      }

      @Override
      public double method9() {
         return 1.0;
      }

      public MarkerModel.Data6 method2(MarkerModel.Data6 var1) {
         return new MarkerModel.Data6(var1);
      }

      @Override
      public MarkerModel.Data6 method6() {
         return this;
      }

      @Override
      public MarkerModel.Data3 method3() {
         return new MarkerModel.Data3(this);
      }

      @Override
      public MarkerModel.Data4 method7() {
         return new MarkerModel.Data4(this);
      }

      @Override
      public MarkerModel.Data2 method5() {
         return new MarkerModel.Data2(this);
      }

      @Override
      public MarkerModel.Data5 method8() {
         return new MarkerModel.Data5(this);
      }
   }
}
