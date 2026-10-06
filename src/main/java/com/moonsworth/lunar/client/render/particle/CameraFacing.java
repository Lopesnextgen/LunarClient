package com.moonsworth.lunar.client.render.particle;

import lombok.Generated;

public enum CameraFacing {
   ROTATE_XYZ("rotate_xyz"),
   ROTATE_Y("rotate_y"),
   LOOKAT_XYZ("lookat_xyz"),
   LOOKAT_Y("lookat_y"),
   DIRECTION_X("direction_x"),
   DIRECTION_Y("direction_y"),
   DIRECTION_Z("direction_z");

   public final String id;

   public static CameraFacing fromString(String text) {
      for (CameraFacing glintcolorizertype_24 : values()) {
         if (glintcolorizertype_24.id.equals(text)) {
            return glintcolorizertype_24;
         }
      }

      return null;
   }

   @Generated
   CameraFacing(String text) {
      this.id = text;
   }
}
