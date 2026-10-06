package com.moonsworth.lunar.client.cosmetics;

import com.moonsworth.lunar.bridge.Bridge11_2;
import com.moonsworth.lunar.bridge.Bridge20Extension;
import com.moonsworth.lunar.bridge.Bridge6_9;
import com.moonsworth.lunar.bridge.Bridge8Extension3;
import com.moonsworth.lunar.bridge.Bridge8Extension33;
import com.moonsworth.lunar.bridge.BridgeType2_5;
import com.moonsworth.lunar.bridge.ColorChannelOrder;
import com.moonsworth.lunar.bridge.NativeImageBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.client.render.texture.Util2Handler;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Optional;
import java.util.concurrent.Callable;
import javax.annotation.Nullable;

public class ElytraTextureLoaderLegacy extends Util2Handler {
   @Nullable
   private File field18 = null;
   @Nullable
   private NativeImageBridge field19;
   private BufferedImage field20;
   private ResourceLocationBridge field21;
   private ResourceLocationBridge field22;

   public ElytraTextureLoaderLegacy(ResourceLocationBridge var1, ResourceLocationBridge var2, ResourceLocationBridge resourceLocationBridge, BridgeType2_5 bridgeType2_5) {
      super(var1, bridgeType2_5);
      this.field21 = var2;
      this.field22 = resourceLocationBridge;
      Bridge8Extension3 var5 = ThreadModuleDump63.method3().bridge$getTextureManager().bridge$getTexture(this.field22);
      if (var5 instanceof Bridge20Extension var6) {
         this.field18 = var6.bridge$getFile();
      }

      if (ThreadModuleDump63.MC_VERSION >= 6 && var5 instanceof Bridge8Extension33 var7) {
         this.field19 = var7.bridge$getNativeImage();
      }
   }

   protected Callable<Optional<Bridge6_9>> method11(Bridge11_2 var1, ColorChannelOrder var2) {
      return new ElytraTextureMetadata(this, var1, this.field21, var2, this.field14);
   }

   protected void method5() {
      if (this.field20 != null) {
         this.field20.flush();
         this.field20 = null;
      }
   }
}
