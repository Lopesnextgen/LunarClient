package com.moonsworth.lunar.client.framework.feature.keystrokes;

import java.util.function.Supplier;
import lombok.Generated;

public enum KeystrokeAnimationStyle implements com.moonsworth.lunar.client.config.option.OptionEnumValue {
   FILL("Fill", FillKeystrokeRenderer::new),
   SMOOTH_FILL("Smooth Fill", SmoothFillKeystrokeRenderer::new),
   RIPPLE("Ripple", RippleKeystrokeRenderer::new),
   COLLAPSE("Collapse", CollapseKeystrokeRenderer::new),
   ZIPPER("Zipper", ZipperKeystrokeRenderer::new),
   TRIANGULATE("Triangulate", TriangulateKeystrokeRenderer::new),
   SPIRAL("Spiral", SpiralKeystrokeRenderer::new),
   SAND("Sand", SandKeystrokeRenderer::new),
   CIRCULAR_FILL("Circular Fill", CircularFillKeystrokeRenderer::new),
   MULTI_SQUARE_FILL("Multi Square Fill", MultiSquareFillKeystrokeRenderer::new),
   HEAD_FILL("Head Fill", HeadFillKeystrokeRenderer::new),
   CROSS_COLLAPSE("Cross Collapse", CrossCollapseKeystrokeRenderer::new),
   CROSS_GROW("Cross Grow", CrossGrowKeystrokeRenderer::new),
   HORIZONTAL_COLLAPSE("Horizontal Collapse", HorizontalCollapseKeystrokeRenderer::new),
   HORIZONTAL_GROW("Horizontal Grow", HorizontalGrowKeystrokeRenderer::new),
   VERTICAL_COLLAPSE("Vertical Collapse", VerticalCollapseKeystrokeRenderer::new),
   VERTICAL_GROW("Vertical Grow", VerticalGrowKeystrokeRenderer::new),
   DIAGONAL_COLLAPSE("Diagonal Collapse", DiagonalCollapseKeystrokeRenderer::new),
   DIAGONAL_GROW("Diagonal Grow", DiagonalGrowKeystrokeRenderer::new);

   private final String id;
   private final Supplier<KeystrokeRenderer> factory;

   public KeystrokeRenderer create() {
      return this.factory.get();
   }

   public String id() {
      return this.id;
   }

   @Override
   public String toString() {
      return this.OHROCHICOIOICHOCRROORRCIIICIHO(this.id, new Object[0]);
   }

   @Generated
   KeystrokeAnimationStyle(String text3, Supplier<KeystrokeRenderer> supplier4) {
      this.id = text3;
      this.factory = supplier4;
   }
}
