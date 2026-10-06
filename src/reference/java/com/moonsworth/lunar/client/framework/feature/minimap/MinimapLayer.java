package com.moonsworth.lunar.client.framework.feature.minimap;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.MinimapOptionWidget;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2;
import java.util.LinkedHashMap;
import javax.annotation.Nullable;
import lombok.Generated;

public abstract class MinimapLayer<T> {
   private com.moonsworth.lunar.client.mod.render.minimap.MinimapMod field1;
   private double field2;
   private double field3;
   private float width;
   private float height;
   private T field4;

   public abstract void method1(MixinHelper_4 mixinhelper_41, Bridge5Extension_5 bridge5extension_52, float value3, float value4, float value5);

   public void method2(MinimapOptionWidget iterator, MixinHelper_4 mixinhelper_42, Data2 data) {
   }

   public void method3() {
   }

   public void method4(float value, float value2) {
   }

   public void method5() {
   }

   @Nullable
   public abstract LinkedHashMap<String, Runnable> method6();

   @Generated
   public MinimapLayer(com.moonsworth.lunar.client.mod.render.minimap.MinimapMod minimapMod, double value2, double value4, float value, float value3, T t) {
      this.field1 = minimapMod;
      this.field2 = value2;
      this.field3 = value4;
      this.width = value;
      this.height = value3;
      this.field4 = (T)t;
   }

   @Generated
   public com.moonsworth.lunar.client.mod.render.minimap.MinimapMod method7() {
      return this.field1;
   }

   @Generated
   public double method8() {
      return this.field2;
   }

   @Generated
   public double method9() {
      return this.field3;
   }

   @Generated
   public float getWidth() {
      return this.width;
   }

   @Generated
   public float getHeight() {
      return this.height;
   }

   @Generated
   public T method10() {
      return this.field4;
   }
}
