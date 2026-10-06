package com.moonsworth.lunar.legacy.mixin;

import com.lunarclient.adventure.transform.renderer.ComponentTransformRenderer;
import com.lunarclient.adventure.transform.renderer.ComponentTransformRenderer.State;
import com.lunarclient.adventure.transform.renderer.ComponentTransformRenderer.TransformableResult;
import com.lunarclient.adventure.transform.transformable.ComponentTransformable;
import com.lunarclient.adventure.transform.transformation.Transformation;
import com.lunarclient.adventure.utils.AdventureMixins;
import java.util.List;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(TextComponent.class)
public interface ComponentTransformableExtension extends ComponentTransformable {
   @Nullable
   default <S extends State> TransformableResult renderSelf(
      @NotNull ComponentTransformRenderer<S> var1, @NotNull Component var2, @Nullable List<Transformation<?, ?>> var3, @NotNull S var4
   ) {
      return AdventureMixins.renderSelfText((TextComponent)this, var1, var2, var3, var4);
   }
}
