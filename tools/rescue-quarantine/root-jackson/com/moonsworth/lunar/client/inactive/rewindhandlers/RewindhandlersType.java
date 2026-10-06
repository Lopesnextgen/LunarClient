package com.moonsworth.lunar.client.inactive.rewindhandlers;

import com.moonsworth.lunar.Annotation10;
import com.moonsworth.lunar.Annotation23;
import java.io.IOException;

public enum RewindhandlersType {
   QUAD_LIST,
   TRI_LIST;

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
   public static RewindhandlersType forValue(String var0) {
      if (var0.equals("quad_list")) {
         return QUAD_LIST;
      } else if (var0.equals("tri_list")) {
         return TRI_LIST;
      } else {
         throw new IOException("Cannot deserialize PolysEnum");
      }
   }
}
