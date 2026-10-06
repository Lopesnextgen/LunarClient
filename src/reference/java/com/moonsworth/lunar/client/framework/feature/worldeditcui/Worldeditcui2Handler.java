package com.moonsworth.lunar.client.framework.feature.worldeditcui;

import java.util.ArrayList;
import java.util.List;
import lombok.Generated;
import org.joml.Vector3d;
import org.joml.Vector3dc;

public class Worldeditcui2Handler implements Worldeditcui2 {
   private final List<Vector3dc> field1 = new ArrayList<>();

   @Override
   public Worldeditcui2 method2(double var1, double var3, double value) {
      this.field1.add(new Vector3d(var1, var3, value));
      return this;
   }

   @Override
   public Worldeditcui2 method4(double var1, double var3) {
      this.field1.add(new Vector3d(var1, -1.0, var3));
      return this;
   }

   @Override
   public Worldeditcui2 method3(int var1, double value, double value2, double value3) {
      if (var1 >= this.field1.size()) {
         this.field1.add(new Vector3d(value, value2, value3));
      } else {
         this.field1.set(var1, new Vector3d(value, value2, value3));
      }

      return this;
   }

   @Generated
   public List<Vector3dc> getPoints() {
      return this.field1;
   }
}
