package com.moonsworth.lunar.client.cosmetics.inactive.rewindhandlers;

import com.moonsworth.lunar.Annotation10;
import com.moonsworth.lunar.Annotation23;
import java.io.IOException;

public enum PolyMeshType {
   QUAD_LIST,
   TRI_LIST;

   PolyMeshType() {
   }

   @Annotation10
   public String toValue() {
      switch (this) {
         case QUAD_LIST:
            return "quad_list";
         case TRI_LIST:
            return "tri_list";
         default:
            return null;
      }
   }

   @Annotation23
   public static PolyMeshType forValue(String text0) {
      if (text0.equals("quad_list")) {
         return QUAD_LIST;
      } else if (text0.equals("tri_list")) {
         return TRI_LIST;
      } else {
         throw new IOException("Cannot deserialize PolysEnum");
      }
   }
}
