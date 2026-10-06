package com.moonsworth.lunar.client.framework.feature.mod.fishing.holograms;

import com.moonsworth.lunar.ichor.util.KeepName;
import lombok.Generated;
import org.joml.Vector3d;

@KeepName
public class TextHologram {
   public String text;
   public Vector3d pos;

   public TextHologram(TextHologram holograms10$data1) {
      this.text = holograms10$data1.text;
      this.pos = new Vector3d(holograms10$data1.pos);
   }

   @Generated
   public TextHologram() {
   }

   @Generated
   public TextHologram(String text2, Vector3d vector3d2) {
      this.text = text2;
      this.pos = vector3d2;
   }
}
