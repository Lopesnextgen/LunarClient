package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.ichor.util.Annotation2;
import lombok.Generated;
import org.joml.Vector3d;

@Annotation2
public class Holograms10$Data {
   public String text;
   public Vector3d pos;

   public Holograms10$Data(Holograms10$Data var1) {
      this.text = var1.text;
      this.pos = new Vector3d(var1.pos);
   }

   @Generated
   public Holograms10$Data() {
   }

   @Generated
   public Holograms10$Data(String var1, Vector3d vector3d) {
      this.text = var1;
      this.pos = vector3d;
   }
}
