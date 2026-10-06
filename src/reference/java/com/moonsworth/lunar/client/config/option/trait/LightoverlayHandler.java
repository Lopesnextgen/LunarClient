package com.moonsworth.lunar.client.config.option.trait;

import java.util.Map;
import lombok.Generated;
import org.jetbrains.annotations.Nullable;

public class LightoverlayHandler implements Lightoverlay {
   private final int field1;
   private final String[] field2;
   private final TraitType<?>[] field3;
   private final Map<LightoverlayType, int[]>[] field4;

   @Override
   public String method2(int index1) {
      return this.field2[index1];
   }

   @Override
   public TraitType<?> method3(int index1) {
      return this.field3[index1];
   }

   @Nullable
   @Override
   public Map<LightoverlayType, int[]> method4(int index1) {
      return this.field4[index1];
   }

   @Generated
   public LightoverlayHandler(int value, String[] items2, TraitType<?>[] items3, Map<LightoverlayType, int[]>[] items4) {
      this.field1 = value;
      this.field2 = items2;
      this.field3 = items3;
      this.field4 = items4;
   }

   @Generated
   @Override
   public int method1() {
      return this.field1;
   }
}
