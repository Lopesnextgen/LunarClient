package com.moonsworth.lunar.client.framework.feature.minimap;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.MixinHelper_4;
import com.moonsworth.lunar.client.ui.MinimapOptionWidget;
import com.moonsworth.lunar.client.framework.feature.markers.MarkerModel.Data2;
import java.util.LinkedHashMap;
import javax.annotation.Nullable;
import lombok.Generated;

public abstract class Minimap2_2<T> {
   private com.moonsworth.lunar.client.mod.render.minimap.Minimap field1;
   private double field2;
   private double field3;
   private float width;
   private float height;
   private T field4;

   public abstract void method1(MixinHelper_4 var1, Bridge5Extension_5 var2, float var3, float var4, float var5);

   public void method2(MinimapOptionWidget var1, MixinHelper_4 var2, Data2 var3) {
   }

   public void method3() {
   }

   public void method4(float var1, float var2) {
   }

   public void method5() {
   }

   @Nullable
   public abstract LinkedHashMap<String, Runnable> method6();

   @Generated
   public Minimap2_2(com.moonsworth.lunar.client.mod.render.minimap.Minimap var1, double var2, double var4, float value, float value2, T t) {
      this.field1 = var1;
      this.field2 = var2;
      this.field3 = var4;
      this.width = value;
      this.height = value2;
      this.field4 = (T)t;
   }

   @Generated
   public com.moonsworth.lunar.client.mod.render.minimap.Minimap method7() {
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
