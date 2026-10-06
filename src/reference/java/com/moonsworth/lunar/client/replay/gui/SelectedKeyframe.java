package com.moonsworth.lunar.client.replay.gui;

import com.moonsworth.lunar.client.replay.timeline.KeyframeProperty;
import com.moonsworth.lunar.client.replay.timeline.PropertyGroup;

public class SelectedKeyframe {
   private final PropertyGroup field1;
   private final KeyframeProperty<?, ?> field2;
   private final int field3;

   public SelectedKeyframe(PropertyGroup propertyGroup, KeyframeProperty<?, ?> keyframeProperty, int value) {
      this.field1 = propertyGroup;
      this.field2 = keyframeProperty;
      this.field3 = value;
   }

   public PropertyGroup method1() {
      return this.field1;
   }

   public KeyframeProperty<?, ?> method2() {
      return this.field2;
   }

   public int method3() {
      return this.field3;
   }
}
