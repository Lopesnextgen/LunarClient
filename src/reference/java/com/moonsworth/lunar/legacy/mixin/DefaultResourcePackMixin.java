package com.moonsworth.lunar.legacy.mixin;

import com.google.common.collect.ImmutableSet;
import com.moonsworth.lunar.bridge.ResourcePackBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.ichor.VersionGate;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Set;
import net.minecraft.client.resources.DefaultResourcePack;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DefaultResourcePack.class)
public abstract class DefaultResourcePackMixin implements ResourcePackBridge {
   public DefaultResourcePackMixin() {
   }

   @Shadow
   public abstract InputStream getResourceStream(ResourceLocation location1);

   @Shadow
   public abstract String getPackName();

   @Shadow
   public abstract BufferedImage getPackImage();

   @Overwrite
   public Set<String> getResourceDomains() {
      return ImmutableSet.of("minecraft", "realms", "lunar");
   }

   public InputStream bridge$getInputStream(ResourceLocationBridge horsestats141) {
      return this.getResourceStream((ResourceLocation)horsestats141);
   }

   @Inject(
      method = "getResourceStream",
      at = @At(value = "INVOKE", target = "Ljava/lang/Class;getResource(Ljava/lang/String;)Ljava/net/URL;"),
      cancellable = true
   )
   @VersionGate(min = 5)
   private void impl$getResourceAsStream(ResourceLocation location1, CallbackInfoReturnable<InputStream> callbackinforeturnable2) {
      String text3 = "/assets/" + location1.getNamespace$v1_12() + "/" + location1.getPath();
      InputStream input4 = DefaultResourcePack.class.getResourceAsStream(text3);
      if (input4 != null) {
         callbackinforeturnable2.setReturnValue(input4);
      }
   }

   public String bridge$getPackName() {
      return this.getPackName();
   }

   public Optional<BufferedImage> bridge$getPackImage() {
      try {
         try {
            return Optional.ofNullable(this.getPackImage());
         } catch (IOException exception2) {
            return Optional.empty();
         }
      } catch (Throwable exception3) {
         throw exception3;
      }
   }
}
