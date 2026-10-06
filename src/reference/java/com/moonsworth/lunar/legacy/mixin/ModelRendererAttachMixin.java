package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.ichor.VersionGate;
import com.moonsworth.lunar.ichor.MixinCondition;
import com.moonsworth.lunar.legacy.ModelRendererAttachable;
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

@VersionGate(min = 1)
@MixinCondition(absent = "optifine")
@Mixin(ModelRenderer.class)
public class ModelRendererAttachMixin implements ModelRendererAttachable {
   @Unique
   private List<ModelRenderer> lunar$attachedRenderers;

   public ModelRendererAttachMixin() {
   }

   public void lunar$attach(ModelRenderer modelrenderer1) {
      if (this.lunar$attachedRenderers == null) {
         this.lunar$attachedRenderers = new ArrayList<>();
      }

      this.lunar$attachedRenderers.add(modelrenderer1);
   }

   @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;callList(I)V", shift = Shift.AFTER))
   private void lunar$render(float value1, CallbackInfo callback2) {
      if (this.lunar$attachedRenderers != null && !this.lunar$attachedRenderers.isEmpty()) {
         List list3 = this.lunar$attachedRenderers;
         int index4 = 0;

         for (int index5 = list3.size(); index4 < index5; index4++) {
            ModelRenderer modelrenderer6 = (ModelRenderer)list3.get(index4);
            if (!modelrenderer6.isHidden && modelrenderer6.showModel) {
               if (!modelrenderer6.compiled) {
                  modelrenderer6.compileDisplayList(value1);
               }

               GlStateManager.callList(modelrenderer6.displayList);
            }
         }
      }
   }
}
