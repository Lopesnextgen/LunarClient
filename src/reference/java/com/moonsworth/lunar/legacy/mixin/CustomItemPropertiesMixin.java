package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.ichor.VersionGate;
import com.moonsworth.lunar.legacy.wrapper.ItemModelBaker;
import com.moonsworth.lunar.legacy.wrapper.ModelBakeryHolder;
import java.util.Objects;
import net.minecraft.client.renderer.block.model.ItemModelGenerator;
import net.minecraft.client.renderer.block.model.ModelBlock;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelBakery;
import net.optifine.CustomItemProperties;
import net.optifine.util.StrUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@VersionGate(min = 1)
@Mixin(CustomItemProperties.class)
public class CustomItemPropertiesMixin {
   public CustomItemPropertiesMixin() {
   }

   @Shadow
   public static ModelBlock makeModelBlock(String[] items0) {
      throw new AssertionError();
   }

   @Overwrite
   public static IBakedModel makeBakedModel(TextureMap map, ItemModelGenerator itemmodelgenerator1, String[] items2, boolean flag) {
      String[] items4 = new String[items2.length];

      for (int index5 = 0; index5 < items4.length; index5++) {
         String text6 = items2[index5];
         items4[index5] = StrUtils.removePrefix(text6, "textures/");
      }

      ModelBlock modelblock7 = makeModelBlock(items4);
      ModelBakery modelbakery8 = Objects.requireNonNull(ModelBakeryHolder.field1);
      return ItemModelBaker.method1(modelbakery8, map, modelblock7);
   }
}
