package com.moonsworth.lunar.client.tps;

public class TpsException extends RuntimeException {
   public TpsException(TpsType var1, Tps tps) {
      super("RenderManager received a submit after COLLECTING stage! Stage: " + var1 + method1(tps));
   }

   private static String method1(Tps tps) {
      return tps instanceof Tps.Data var1 ? " (Geckolib: " + var1.field1.getTexture() + ")" : "";
   }
}
