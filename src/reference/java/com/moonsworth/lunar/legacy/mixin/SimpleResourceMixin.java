package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.ResourceBridge;
import com.moonsworth.lunar.bridge.IMetadataSectionBridge;
import com.moonsworth.lunar.client.framework.Ref;
import java.io.InputStream;
import net.minecraft.client.resources.SimpleResource;
import net.minecraft.client.resources.data.IMetadataSection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(SimpleResource.class)
public abstract class SimpleResourceMixin implements ResourceBridge {
   @Shadow
   public String resourcePackName;

   public SimpleResourceMixin() {
   }

   @Shadow
   public abstract InputStream getInputStream();

   @Shadow
   public abstract boolean hasMetadata();

   @Shadow
   public abstract <T extends IMetadataSection> T getMetadata(String text1);

   public boolean bridge$hasMetadata() {
      return this.hasMetadata();
   }

   public <T extends IMetadataSectionBridge> T bridge$getMetadata(String text1) {
      return this.getMetadata(text1);
   }

   public String bridge$getResourcePackName() {
      return Ref.MC_VERSION >= 1 ? this.resourcePackName : "default";
   }

   public InputStream bridge$getInputStream() {
      return this.getInputStream();
   }
}
