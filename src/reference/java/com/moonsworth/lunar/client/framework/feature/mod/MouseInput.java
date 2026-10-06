package com.moonsworth.lunar.client.framework.feature.mod;

import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.math.IntRectangle;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import lombok.Generated;

@VersionGate(min = 1)
public class MouseInput extends GuiComponent {
   private final Map<String, List<IntRectangle>> field2 = new HashMap<>();
   private List<IntRectangle> field3 = new ArrayList<>();
   private List<IntRectangle> field4 = new ArrayList<>();
   private boolean field5 = false;
   private boolean field6 = false;
   private Map<String, List<MouseClick>> field7 = new HashMap<>();
   private Map<String, List<MouseClick>> field8 = new HashMap<>();
   private boolean field9 = false;
   private int field10 = 0;
   private int field11 = 0;

   public MouseInput(GuiRenderer mixinhelper1) {
      super(mixinhelper1);
   }

   @Override
   public void start() {
      this.field7 = this.field8;
      this.field8 = new HashMap<>();
      this.field5 = this.field6;
      this.field6 = false;
      if (this.field5) {
         this.method7().method17();
      }

      this.field3 = this.field2.get(this.method15());
      this.field4 = new ArrayList<>();
      this.field2.put(this.method15(), this.field4);
   }

   public void method1(int number1, int number2) {
      this.field10 = number1;
      this.field11 = number2;
   }

   public boolean method2(String text1, int number2, int number3, int number4) {
      if (number4 > 1) {
         return false;
      }

      if (!this.HRROORRCRHHHCCIORROORCIHOHRIHH.method73() && !this.field3.stream().anyMatch(arg2x -> arg2x.method9(number2, number3))) {
         return false;
      }

      this.field9 = true;
      return true;
   }

   public boolean method3(String text1, int number2, int number3, int number4) {
      if (number4 > 1) {
         return false;
      }

      this.field6 = true;
      if (!this.field9) {
         return false;
      }

      this.field9 = false;
      if (!this.field8.containsKey(text1)) {
         this.field8.put(text1, new ArrayList<>());
      }

      this.field8.get(text1).add(new MouseClick(number2, number3, number4));
      this.method9().method10(true);
      return true;
   }

   public MouseClick method4(int number1, int number2, int number3, int number4) {
      if (!this.method1().field2) {
         return null;
      }

      if (!this.HRROORRCRHHHCCIORROORCIHOHRIHH.method73()) {
         IntRectangle threadmoduledump705 = IntRectangle.method3(number1, number2, number3, number4);
         IntRectangle threadmoduledump706 = this.method3().method6(threadmoduledump705);
         if (threadmoduledump706 != null) {
            this.field4.add(threadmoduledump706);
         }
      }

      if (!this.field7.containsKey(this.method15())) {
         return null;
      }

      Iterator iterator7 = this.field7.get(this.method15()).iterator();

      while (iterator7.hasNext()) {
         MouseClick mixinhelper_28 = (MouseClick)iterator7.next();
         if (mixinhelper_28.method1(number1, number2, number3, number4) && this.method3().method4(mixinhelper_28.x(), mixinhelper_28.y())) {
            iterator7.remove();
            return mixinhelper_28;
         }
      }

      return null;
   }

   public boolean method5(int number1, int number2, int number3, int number4) {
      return this.field10 >= number1
         && this.field10 <= number1 + number3
         && this.field11 >= number2
         && this.field11 <= number2 + number4
         && this.method3().method4(this.field10, this.field11);
   }

   public boolean method17() {
      return Ref.method3().bridge$isMouseButtonDown(0) || Ref.method3().bridge$isMouseButtonDown(1);
   }

   @Generated
   public int method18() {
      return this.field10;
   }

   @Generated
   public int method19() {
      return this.field11;
   }
}
