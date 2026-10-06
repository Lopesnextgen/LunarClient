package com.moonsworth.lunar.client.framework.feature.worldeditcui;

import java.util.ArrayList;
import java.util.List;
import lombok.Generated;
import org.joml.Vector3d;
import org.joml.Vector3dc;

public class WorldEditSelectionBase implements WorldeditSelection {
   private final List<Vector3dc> field1 = new ArrayList<>();

   public WorldEditSelectionBase() {
   }

   @Override
   public WorldeditSelection method2(double value1, double value3, double value) {
      this.field1.add(new Vector3d(value1, value3, value));
      return this;
   }

   @Override
   public WorldeditSelection method4(double value1, double value3) {
      this.field1.add(new Vector3d(value1, -1.0, value3));
      return this;
   }

   @Override
   public WorldeditSelection method3(int index1, double value, double value2, double value3) {
      if (index1 >= this.field1.size()) {
         this.field1.add(new Vector3d(value, value2, value3));
      } else {
         this.field1.set(index1, new Vector3d(value, value2, value3));
      }

      return this;
   }

   @Generated
   public List<Vector3dc> getPoints() {
      return this.field1;
   }
}
