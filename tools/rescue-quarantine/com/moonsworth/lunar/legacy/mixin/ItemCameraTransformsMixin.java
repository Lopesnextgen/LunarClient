package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.MixinHelper2_5;
import com.moonsworth.lunar.bridge.MixinHelper2.Type5;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import com.moonsworth.lunar.ichor.Annotation2;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Annotation2(min = 1)
@Mixin(ItemCameraTransforms.class)
public abstract class ItemCameraTransformsMixin implements MixinHelper2_5 {
   @Shadow
   public abstract void applyTransform(TransformType var1);

   public void bridge$applyTransform(Type5 var1) {
      TransformType var2;
      if (ThreadModuleDump63.MC_VERSION >= 5) {
         var2 = TransformType.valueOf(var1.getModernName());
      } else {
         var2 = TransformType.values()[var1.legacyIndex()];
      }

      this.applyTransform(var2);
   }
}
