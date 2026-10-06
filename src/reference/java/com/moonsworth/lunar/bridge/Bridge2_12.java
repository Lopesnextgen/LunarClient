package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.horsestats.Vec3Bridge;
import net.kyori.adventure.text.Component;

public interface Bridge2_12 {
   @Annotation(
      OIRHICCHORIRORCCOOCRRRHRRCRCCI = {
            @BridgeVersionMapping(version = 0, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("worldObj")),
            @BridgeVersionMapping(version = 5, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("world")),
            @BridgeVersionMapping(version = 6, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("level"))
      }
   )
   WorldPlayerLookupBridge method1();

   @Annotation(
      OIRHICCHORIRORCCOOCRRRHRRCRCCI = {
            @BridgeVersionMapping(version = 0, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("isInvisibleToPlayer")),
            @BridgeVersionMapping(version = 6, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("isInvisibleTo"))
      }
   )
   boolean bridge$isInvisibleTo(Bridge6_10 var1);

   @Annotation(
      OIRHICCHORIRORCCOOCRRRHRRCRCCI = {
            @BridgeVersionMapping(version = 0, IHCHIIICCCHCCIROHOHROHOOHRCHRC = {@BridgeTargetMapping("posX"), @BridgeTargetMapping("posY"), @BridgeTargetMapping("posZ")}),
            @BridgeVersionMapping(version = 6, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("setPos(DDD)V"))
      }
   )
   void method2(
      @BridgeParameterMapping(
   OHCRRCHOOCCHIICCCHICHCOHOOHRCC = {
         @BridgeVersionMapping(version = 0, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("posX")),
         @BridgeVersionMapping(version = 6, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping({"position", "x"}))
   }
) Double var1,
      @BridgeParameterMapping(
   OHCRRCHOOCCHIICCCHICHCOHOOHRCC = {
         @BridgeVersionMapping(version = 0, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("posY")),
         @BridgeVersionMapping(version = 6, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping({"position", "y"}))
   }
) Double var2,
      @BridgeParameterMapping(
   OHCRRCHOOCCHIICCCHICHCOHOOHRCC = {
         @BridgeVersionMapping(version = 0, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("posZ")),
         @BridgeVersionMapping(version = 6, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping({"position", "z"}))
   }
) Double var3
   );

   @Annotation(
      OIRHICCHORIRORCCOOCRRRHRRCRCCI = {
            @BridgeVersionMapping(version = 0, IHCHIIICCCHCCIROHOHROHOOHRCHRC = {@BridgeTargetMapping("posX"), @BridgeTargetMapping("posY"), @BridgeTargetMapping("posZ")}),
            @BridgeVersionMapping(version = 6, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("position"))
      }
   )
   Vec3Bridge method3();

   @Annotation(
      OIRHICCHORIRORCCOOCRRRHRRCRCCI = {
            @BridgeVersionMapping(version = 0, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("posY")),
            @BridgeVersionMapping(version = 6, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping({"position", "y"}))
      }
   )
   double bridge$getPosX();

   @Annotation(
      OIRHICCHORIRORCCOOCRRRHRRCRCCI = {
            @BridgeVersionMapping(version = 0, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("posX")), @BridgeVersionMapping(version = 6, IHCHIIICCCHCCIROHOHROHOOHRCHRC = {})
      }
   )
   default void bridge$setPosX(double var1) {
      this.method2(var1, null, null);
   }

   @Annotation(
      OIRHICCHORIRORCCOOCRRRHRRCRCCI = {
            @BridgeVersionMapping(version = 0, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("posY")),
            @BridgeVersionMapping(version = 6, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping({"position", "y"}))
      }
   )
   double bridge$getPosY();

   @Annotation(
      OIRHICCHORIRORCCOOCRRRHRRCRCCI = {
            @BridgeVersionMapping(version = 0, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("posY")), @BridgeVersionMapping(version = 6, IHCHIIICCCHCCIROHOHROHOOHRCHRC = {})
      }
   )
   default void bridge$setPosY(double var1) {
      this.method2(null, var1, null);
   }

   @Annotation(
      OIRHICCHORIRORCCOOCRRRHRRCRCCI = {
            @BridgeVersionMapping(version = 0, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("posZ")),
            @BridgeVersionMapping(version = 6, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping({"position", "z"}))
      }
   )
   double bridge$getPosZ();

   @Annotation(
      OIRHICCHORIRORCCOOCRRRHRRCRCCI = {
            @BridgeVersionMapping(version = 0, IHCHIIICCCHCCIROHOHROHOOHRCHRC = @BridgeTargetMapping("posZ")), @BridgeVersionMapping(version = 6, IHCHIIICCCHCCIROHOHROHOOHRCHRC = {})
      }
   )
   default void bridge$setPosZ(double var1) {
      this.method2(null, null, var1);
   }

   @Annotation("getName")
   Component bridge$getName();
}
