package com.moonsworth.lunar.client.framework.feature.mod;

import com.moonsworth.lunar.ichor.VersionGate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import lombok.Generated;

@VersionGate(min = 1)
public class WidgetStateCache extends GuiComponent {
   private final Map<String, Map<String, WidgetState>> field2 = new HashMap<>();
   private int field3 = 0;
   private final Set<String> field4 = new HashSet<>();
   private String field5 = null;

   public WidgetStateCache(GuiRenderer mixinhelper1) {
      super(mixinhelper1);
   }

   @Override
   public void start() {
      this.field3 = 0;
      this.field4.clear();
   }

   @Override
   public void end() {
      Map map1 = this.field2.get(this.method15());
      if (map1 != null) {
         ArrayList list2 = new ArrayList();

         for (String text4 : map1.keySet()) {
            if (!this.field4.contains(text4)) {
               list2.add(text4);
            }
         }

         for (String text6 : list2) {
            ((WidgetState)map1.remove(text6)).delete();
         }
      }
   }

   public void method1(String text1) {
      Map map2 = this.field2.get(text1);
      if (map2 != null) {
         for (WidgetState mixinhelper_34 : map2.values()) {
            mixinhelper_34.delete();
         }
      }

      this.field2.remove(text1);
   }

   public void method2(String text1, String text2) {
      Map map3 = this.field2.get(text1);
      if (map3 != null) {
         WidgetState mixinhelper_34 = (WidgetState)map3.get(text2);
         if (mixinhelper_34 != null) {
            mixinhelper_34.delete();
            map3.remove(text1);
         }
      }
   }

   public WidgetState method3(String text1) {
      Map map2 = this.field2.computeIfAbsent(this.method15(), arg0 -> new HashMap<>());
      return (WidgetState)map2.get(text1);
   }

   private WidgetState method4(String text1, int number2, int number3, WidgetStateCache mixinhelper24) {
      this.field4.add(text1);
      Map map5 = this.field2.computeIfAbsent(this.method15(), arg0 -> new HashMap<>());
      WidgetState mixinhelper_36 = (WidgetState)map5.get(text1);
      if (mixinhelper_36 == null) {
         mixinhelper_36 = new WidgetState(text1, number2, number3, mixinhelper24);
         map5.put(text1, mixinhelper_36);
      }

      return mixinhelper_36;
   }

   public void method5(String text1, int number2, int number3, int number4, int number5) {
      if (this.isSupported()) {
         if (!this.method3().method5(number2, number3, number4, number5)) {
            this.field5 = text1;
            if (this.field3++ <= 1) {
               WidgetState mixinhelper_36 = this.method4(text1, number4, number5, this);
               mixinhelper_36.method1(number2, number3, number4, number5);
               this.HRROORRCRHHHCCIORROORCIHOHRIHH.method3();
               if (!mixinhelper_36.isDirty) {
                  this.HRROORRCRHHHCCIORROORCIHOHRIHH.method12(false);
               } else {
                  this.method3().update();
                  mixinhelper_36.method2();
                  this.HRROORRCRHHHCCIORROORCIHOHRIHH.method13(false);
               }
            }
         }
      }
   }

   private boolean isSupported() {
      return false;
   }

   public void method6(String text1) {
      if (this.isSupported()) {
         if (text1.equals(this.field5)) {
            this.field5 = null;
            if (this.field3-- <= 1) {
               WidgetState mixinhelper_32 = this.method3(text1);
               if (mixinhelper_32.isDirty) {
                  mixinhelper_32.method3();
                  mixinhelper_32.isDirty = false;
               }

               this.HRROORRCRHHHCCIORROORCIHOHRIHH.method4();
               mixinhelper_32.method4();
            }
         }
      }
   }

   public void method7(String text1, String text2) {
      Map map3 = this.field2.get(text1);
      if (map3 != null) {
         WidgetState mixinhelper_34 = (WidgetState)map3.get(text2);
         if (mixinhelper_34 != null) {
            mixinhelper_34.isDirty = true;
         }
      }
   }

   public void method8(String text1) {
      WidgetState mixinhelper_32 = this.method3(text1);
      if (mixinhelper_32 != null) {
         mixinhelper_32.isDirty = true;
      }
   }

   public GuiRenderState method17() {
      return this.method1();
   }

   @Generated
   public int method18() {
      return this.field3;
   }
}
