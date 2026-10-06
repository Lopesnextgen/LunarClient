package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.nameplate;

import com.moonsworth.lunar.bridge.WorldBridgeExtension;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.AnimatedValue;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.DungeonStateTracker;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms.BettermapSettings;
import com.moonsworth.lunar.client.framework.Ref;
import org.joml.Vector2i;

public class WorldPosition {
   private final AnimatedValue field1;
   private final AnimatedValue field2;
   private final DungeonStateTracker field3;

   public WorldPosition(double value1, double value, DungeonStateTracker holograms2_55) {
      this.field1 = new AnimatedValue(value1, AnimatedValue.Type.LINEAR);
      this.field2 = new AnimatedValue(value, AnimatedValue.Type.LINEAR);
      this.field3 = holograms2_55;
   }

   public static WorldPosition method1(int value, int value2, DungeonStateTracker holograms2_52) {
      return new WorldPosition(value * 32 + DungeonStateTracker.field7.x(), value2 * 32 + DungeonStateTracker.field7.y(), holograms2_52);
   }

   public float method2() {
      return (float)this.field1.getValue();
   }

   public float method3() {
      return (float)this.field2.getValue();
   }

   public void method4(double value1) {
      this.field1.animateTo(value1, 0L);
   }

   public void method5(double value1) {
      this.field2.animateTo(value1, 0L);
   }

   public void method6(double value1, long number3) {
      this.field1.animateTo(value1, number3);
   }

   public void method7(double value1, long number3) {
      this.field2.animateTo(value1, number3);
   }

   public int method8() {
      return (int)((this.field1.getValue() - DungeonStateTracker.field7.x()) / 32.0);
   }

   public int method9() {
      return (int)((this.field2.getValue() - DungeonStateTracker.field7.y()) / 32.0);
   }

   public int method10() {
      return (int)((this.field1.getValue2() - DungeonStateTracker.field7.x()) / 32.0);
   }

   public int method11() {
      return (int)((this.field2.getValue2() - DungeonStateTracker.field7.y()) / 32.0);
   }

   public float method12(BettermapSettings holograms_91) {
      int number2 = this.field3.method27();
      int number3 = this.field3.method28();
      float value4 = 0.0F;
      if (number3 > number2) {
         value4 += 0.5F / number2;
      }

      float value5 = ((float)this.field1.getValue() - DungeonStateTracker.field7.x()) / 32.0F;
      return (value4 + value5 / Math.max(number2, number3)) * (100.0F - holograms_91.method35() * 2.0F);
   }

   public float method13(BettermapSettings holograms_91) {
      int number2 = this.field3.method27();
      int number3 = this.field3.method28();
      float value4 = 0.0F;
      if (number2 > number3) {
         value4 += 0.5F / number3;
      }

      float value5 = ((float)this.field2.getValue() - DungeonStateTracker.field7.y()) / 32.0F;
      return (value4 + value5 / Math.max(number2, number3)) * (100.0F - holograms_91.method35() * 2.0F);
   }

   public boolean isLoaded() {
      WorldBridgeExtension itemcounter6extension1 = Ref.method8();
      if (itemcounter6extension1 == null) {
         return false;
      }

      int number2 = (int)Math.floor(this.method2() / 16.0);
      int number3 = (int)Math.floor(this.method3() / 16.0);

      for (int index4 = 0; index4 <= 2; index4++) {
         for (int index5 = 0; index5 <= 2; index5++) {
            if (!itemcounter6extension1.bridge$isChunkLoaded(number2 + index4, number3 + index5)) {
               return false;
            }
         }
      }

      return true;
   }

   public void method14() {
      this.method4(this.method10() * 32 + DungeonStateTracker.field7.x());
      this.method5(this.method11() * 32 + DungeonStateTracker.field7.y());
   }

   public Vector2i method15() {
      return new Vector2i(this.method8(), this.method9());
   }

   @Override
   public boolean equals(Object object) {
      return object instanceof WorldPosition nameplate42 && nameplate42.method8() == this.method8() && nameplate42.method9() == this.method9();
   }
}
