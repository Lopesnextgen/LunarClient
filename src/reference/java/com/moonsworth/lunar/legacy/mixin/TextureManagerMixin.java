package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge8Extension3;
import com.moonsworth.lunar.bridge.Bridge8Extension33;
import com.moonsworth.lunar.bridge.TextureManagerBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import java.util.List;
import java.util.Map;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.ITickable;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(TextureManager.class)
public abstract class TextureManagerMixin implements TextureManagerBridge {
   @Shadow
   public Map<ResourceLocation, ITextureObject> mapTextureObjects;
   @Shadow
   public List<ITickable> listTickables;

   public TextureManagerMixin() {
   }

   @Shadow
   public abstract boolean loadTexture(ResourceLocation location1, ITextureObject itextureobject2);

   @Shadow
   public abstract void bindTexture(ResourceLocation location1);

   @Shadow
   public abstract ResourceLocation getDynamicTextureLocation(String text1, DynamicTexture dynamictexture2);

   @Shadow
   public abstract ITextureObject getTexture(ResourceLocation location1);

   public boolean bridge$loadTexture(ResourceLocationBridge horsestats141, Bridge8Extension3 bridge8extension32) {
      return this.loadTexture((ResourceLocation)horsestats141, (ITextureObject)bridge8extension32);
   }

   public void bridge$bindTexture(ResourceLocationBridge horsestats141) {
      this.bindTexture((ResourceLocation)horsestats141);
   }

   public void bridge$deleteTexture(ResourceLocationBridge horsestats141) {
      ITextureObject itextureobject2 = this.mapTextureObjects.remove((ResourceLocation)horsestats141);
      if (itextureobject2 instanceof AbstractTexture) {
         ((AbstractTexture)itextureobject2).deleteGlTexture();
         this.listTickables.remove(itextureobject2);
      }
   }

   @NotNull
   public Bridge8Extension3 bridge$getTexture(ResourceLocationBridge horsestats141) {
      ITextureObject itextureobject2 = this.getTexture((ResourceLocation)horsestats141);
      if (itextureobject2 == null) {
         SimpleTexture simpletexture3 = new SimpleTexture((ResourceLocation)horsestats141);
         this.loadTexture((ResourceLocation)horsestats141, simpletexture3);
         return (Bridge8Extension3)simpletexture3;
      } else {
         return (Bridge8Extension3)itextureobject2;
      }
   }

   public ResourceLocationBridge bridge$getDynamicTextureLocation(String text1, Bridge8Extension33 bridge8extension332) {
      return (ResourceLocationBridge)this.getDynamicTextureLocation(text1, (DynamicTexture)bridge8extension332);
   }

   public Map<ResourceLocationBridge, Bridge8Extension3> bridge$getTextureMap() {
      return this.mapTextureObjects;
   }
}
