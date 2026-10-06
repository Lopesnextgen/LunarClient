package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.ichor.Annotation2;
import com.moonsworth.lunar.ichor.Annotation_2;
import com.moonsworth.lunar.legacy.MixinCore4;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Annotation2(min = 1)
@Annotation_2(absent = "optifine")
@Mixin(ModelRenderer.class)
public class ModelRendererMixin2 implements MixinCore4 {
   @Unique
   private List<ModelRenderer> lunar$attachedRenderers;

   @Override
   public void lunar$attach(ModelRenderer var1) {
      if (this.lunar$attachedRenderers == null) {
         this.lunar$attachedRenderers = new ArrayList<>();
      }

      this.lunar$attachedRenderers.add(var1);
   }

   @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;callList(I)V", shift = Shift.AFTER))
   private void lunar$render(float var1, CallbackInfo var2) {
      if (this.lunar$attachedRenderers != null && !this.lunar$attachedRenderers.isEmpty()) {
         List var3 = this.lunar$attachedRenderers;
         int var4 = 0;

         for (int var5 = var3.size(); var4 < var5; var4++) {
            ModelRenderer var6 = (ModelRenderer)var3.get(var4);
            if (!var6.isHidden && var6.showModel) {
               if (!var6.compiled) {
                  var6.compileDisplayList(var1);
               }

               GlStateManager.callList(var6.displayList);
            }
         }
      }
   }
}
