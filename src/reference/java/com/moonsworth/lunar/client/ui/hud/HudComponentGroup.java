package com.moonsworth.lunar.client.ui.hud;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import lombok.Generated;

public class HudComponentGroup implements HudComponent {
   public static HudComponentGroup.Type field1 = HudComponentGroup.Type.START;
   public static HudComponentGroup.Type field2 = HudComponentGroup.Type.CENTER;
   public static HudComponentGroup.Type field3 = HudComponentGroup.Type.END;
   public static HudComponentGroup.Spacer field4 = new HudComponentGroup.Spacer();
   private final List<HudComponent> field5 = new ArrayList<>();
   private final HudComponentValue<Float> field6 = new HudComponentValue<>(0.0F, false, true);
   private final HudComponentValue<Float> field7 = new HudComponentValue<>(0.0F, false, true);
   private boolean vertical = false;
   private HudComponentGroup.Type field8 = HudComponentGroup.Type.START;
   private Float field9 = null;
   private Float field10 = null;

   public HudComponentGroup() {
   }

   public HudComponentGroup(boolean flag1) {
      this.vertical = flag1;
   }

   public HudComponentGroup(HudComponentGroup.Type type1) {
      if (type1 == null) {
         throw new NullPointerException("Alignment cant be null!");
      }

      this.field8 = type1;
   }

   public HudComponentGroup(boolean flag1, HudComponentGroup.Type type) {
      this.vertical = flag1;
      if (type == null) {
         throw new NullPointerException("Alignment cant be null!");
      }

      this.field8 = type;
   }

   public HudComponentGroup method1(float value1) {
      this.field6.method1(value1);
      return this;
   }

   public HudComponentGroup method2(Supplier<Float> supplier1) {
      this.field6.method2(supplier1);
      return this;
   }

   public HudComponentGroup method3(float value1) {
      this.field7.method1(value1);
      return this;
   }

   public HudComponentGroup method4(int index1, HudComponent mixincore52) {
      if (mixincore52 == null) {
         throw new NullPointerException("Component cant be null!");
      }

      this.method9(index1 + 1);
      this.field5.set(index1, mixincore52);
      return this;
   }

   public HudComponentGroup method5(HudComponent mixincore51) {
      if (mixincore51 == null) {
         throw new NullPointerException("Component cant be null!");
      }

      this.field5.add(mixincore51);
      return this;
   }

   public HudComponentGroup method6(HudComponentGroup.Type type1, HudComponent mixincore52) {
      return this.method5(new HudComponentGroup.Data(type1, mixincore52));
   }

   public HudComponentGroup method7(List<HudComponent> list) {
      for (HudComponent mixincore53 : list) {
         this.method5(mixincore53);
      }

      return this;
   }

   public HudComponentGroup method8() {
      this.field5.clear();
      return this;
   }

   private void method9(int value) {
      while (this.field5.size() < value) {
         this.field5.add(new EmptyHudComponent());
      }
   }

   @Override
   public void clearCache() {
      this.field9 = null;
      this.field10 = null;

      for (HudComponent mixincore52 : this.field5) {
         mixincore52.clearCache();
      }

      this.field7.clearCache();
      this.field6.clearCache();
   }

   @Override
   public float getWidth() {
      if (this.field9 != null) {
         return this.field9;
      }

      if (!this.vertical) {
         float value5 = 0.0F;
         boolean flag6 = true;

         for (HudComponent mixincore58 : this.field5) {
            value5 += mixincore58.getWidth();
            if (!this.method13(mixincore58)) {
               if (!flag6) {
                  value5 += this.field7.get();
               }

               flag6 = false;
            }
         }

         this.field9 = Math.max(value5, this.field6.get());
         return this.field9;
      } else {
         float value1 = 0.0F;

         for (HudComponent mixincore53 : this.field5) {
            float value4 = mixincore53.getWidth();
            if (value4 > value1) {
               value1 = value4;
            }
         }

         this.field9 = value1;
         return value1;
      }
   }

   @Override
   public float getHeight() {
      if (this.field10 != null) {
         return this.field10;
      }

      if (this.vertical) {
         float value5 = 0.0F;
         boolean flag6 = true;

         for (HudComponent mixincore58 : this.field5) {
            value5 += mixincore58.getHeight();
            if (!this.method13(mixincore58)) {
               if (!flag6) {
                  value5 += this.field7.get();
               }

               flag6 = false;
            }
         }

         this.field10 = Math.max(value5, this.field6.get());
         return this.field10;
      } else {
         float value1 = 0.0F;

         for (HudComponent mixincore53 : this.field5) {
            float value4 = mixincore53.getHeight();
            if (value4 > value1) {
               value1 = value4;
            }
         }

         this.field10 = value1;
         return value1;
      }
   }

   @Override
   public void method1(float value1, float value2, HudRenderContext mixincore43) {
      if (this.vertical) {
         this.method12(value1, value2, mixincore43);
      } else {
         this.method11(value1, value2, mixincore43);
      }
   }

   private void method11(float value1, float value2, HudRenderContext mixincore43) {
      float value4 = this.getHeight();
      float value5 = 0.0F;
      int index6 = 0;

      for (HudComponent mixincore58 : this.field5) {
         value5 += mixincore58.getWidth();
         if (mixincore58 instanceof HudComponentGroup.Spacer) {
            index6++;
         }
      }

      float value12 = Math.max(0.0F, this.field6.get() - value5);
      boolean flag13 = true;

      for (HudComponent mixincore510 : this.field5) {
         if (!this.method13(mixincore510)) {
            if (!flag13) {
               value1 += this.field7.get();
            }

            flag13 = false;
         }

         HudComponentGroup.Type type11 = this.field8;
         if (mixincore510 instanceof HudComponentGroup.Data) {
            type11 = ((HudComponentGroup.Data)mixincore510).method2();
         }

         switch (type11) {
            case START:
               mixincore510.method1(value1, value2, mixincore43);
               break;
            case CENTER:
               mixincore510.method1(value1, value2 + (value4 - mixincore510.getHeight()) / 2.0F, mixincore43);
               break;
            case END:
               mixincore510.method1(value1, value2 + value4 - mixincore510.getHeight(), mixincore43);
         }

         value1 += mixincore510.getWidth();
         if (mixincore510 instanceof HudComponentGroup.Spacer) {
            value1 += value12 / index6;
         }
      }
   }

   private void method12(float value1, float value2, HudRenderContext mixincore43) {
      float value4 = this.getWidth();
      float value5 = 0.0F;
      int index6 = 0;

      for (HudComponent mixincore58 : this.field5) {
         value5 += mixincore58.getHeight();
         if (mixincore58 instanceof HudComponentGroup.Spacer) {
            index6++;
         }
      }

      float value12 = Math.max(0.0F, this.field6.get() - value5);
      boolean flag13 = true;

      for (HudComponent mixincore510 : this.field5) {
         if (!(mixincore510 instanceof HudComponentGroup.Spacer)) {
            if (!flag13) {
               value2 += this.field7.get();
            }

            flag13 = false;
         }

         HudComponentGroup.Type type11 = this.field8;
         if (mixincore510 instanceof HudComponentGroup.Data) {
            type11 = ((HudComponentGroup.Data)mixincore510).method2();
         }

         switch (type11) {
            case START:
               mixincore510.method1(value1, value2, mixincore43);
               break;
            case CENTER:
               mixincore510.method1(value1 + (value4 - mixincore510.getWidth()) / 2.0F, value2, mixincore43);
               break;
            case END:
               mixincore510.method1(value1 + value4 - mixincore510.getWidth(), value2, mixincore43);
         }

         value2 += mixincore510.getHeight();
         if (mixincore510 instanceof HudComponentGroup.Spacer) {
            value2 += value12 / index6;
         }
      }
   }

   private boolean method13(HudComponent mixincore51) {
      return mixincore51 instanceof HudComponentGroup.Spacer || mixincore51 instanceof HideableHudComponent && ((HideableHudComponent)mixincore51).isHidden();
   }

   public static class Data extends HudComponentWrapper<HudComponentGroup.Data> {
      private HudComponentGroup.Type field2;

      public Data(HudComponentGroup.Type type1, HudComponent mixincore52) {
         super(mixincore52);
         this.field2 = type1;
      }

      public HudComponentGroup.Data method1(HudComponentGroup.Type type1) {
         this.field2 = type1;
         return this;
      }

      @Generated
      public HudComponentGroup.Type method2() {
         return this.field2;
      }
   }

   public static class Spacer implements HudComponent {
      public Spacer() {
      }

      @Override
      public void clearCache() {
      }

      @Override
      public float getWidth() {
         return 0.0F;
      }

      @Override
      public float getHeight() {
         return 0.0F;
      }

      @Override
      public void method1(float value1, float value2, HudRenderContext mixincore43) {
      }
   }

   public enum Type {
      START,
      CENTER,
      END;

      Type() {
      }
   }
}
