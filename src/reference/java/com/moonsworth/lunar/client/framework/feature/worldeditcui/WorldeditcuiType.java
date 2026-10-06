package com.moonsworth.lunar.client.framework.feature.worldeditcui;

import com.moonsworth.lunar.client.framework.feature.worldeditcui.mixin.WorldeditcuiHandler;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public enum WorldeditcuiType {
   CUBOID(CuboidSelection::new),
   ELLIPSOID(EllipsoidSelection::new),
   CYLINDER(CylinderSelection::new),
   POLYGON2D(Polygon2dSelection::new),
   POLYHEDRON(PolyhedronSelection::new);

   private static final Map<WorldeditcuiType, com.moonsworth.lunar.client.framework.feature.worldeditcui.mixin.Worldeditcui> RENDERERS = new EnumMap<>(
      WorldeditcuiType.class
   );
   private final Supplier<WorldeditSelection> constructor;

   WorldeditcuiType(Supplier<WorldeditSelection> supplier3) {
      this.constructor = supplier3;
   }

   public WorldeditSelection create() {
      return this.constructor.get();
   }

   public com.moonsworth.lunar.client.framework.feature.worldeditcui.mixin.Worldeditcui getRenderer() {
      return RENDERERS.get(this);
   }

   static {
      RENDERERS.put(CUBOID, new WorldeditcuiHandler());
   }
}
