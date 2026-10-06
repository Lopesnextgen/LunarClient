package com.moonsworth.lunar.client.mod.skyblock.glowingmushroomhighlight;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge3_23;
import com.moonsworth.lunar.bridge.particle.ParticleType;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockCave;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.fishing.SkyblockLocationListener;
import com.moonsworth.lunar.client.framework.feature.mod.impl.calculator.TrackedBlockHighlight;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSpawnParticle;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.math.MathUtils;
import org.joml.Vector2d;
import org.joml.Vector3i;

public class SkyblockGlowingMushroomHighlight extends TrackedBlockHighlight {
   private final SkyblockLocationListener field11 = (SkyblockLocationListener)this.method63(SkyblockLocationListener.class);

   public SkyblockGlowingMushroomHighlight(Skyblock skyblock1) {
      super(new Vector2d(0.4, 0.4), 1140915968);
      this.method45(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method45(ModTraits.field17, ModCategories.method2(SettingsPage.FARMING));
      this.method45(
         ModTraits.field19, DynamicCondition.method1(this, () -> this.field11.method5().orElse(null) == SkyblockCave.GLOWING_MUSHROOM_CAVE)
      );
   }

   public String getId() {
      return "SKYBLOCK_GLOWING_MUSHROOM_HIGHLIGHT";
   }

   protected void method1(EventSpawnParticle highlightimpl151) {
      if (highlightimpl151.method2() == ParticleType.ENTITY_EFFECT) {
         if (MathUtils.method18(Math.abs(highlightimpl151.getPosX() % 1.0), 0.5, 0.01)
            && MathUtils.method18(Math.abs(highlightimpl151.getPosZ() % 1.0), 0.5, 0.01)
            && MathUtils.method18(Math.abs(highlightimpl151.getPosY() % 1.0), 0.0999, 0.01)) {
            Vector3i vector3i2 = new Vector3i((int)Math.floor(highlightimpl151.getPosX()), (int)Math.floor(highlightimpl151.getPosY()), (int)Math.floor(highlightimpl151.getPosZ()));
            if (this.method2(vector3i2)) {
               this.method4(vector3i2);
            }
         }
      }
   }

   protected boolean method2(Vector3i vector3i1) {
      if (Ref.method7() != null && !(Ref.method7().ORICHRORRORHORHOIHCRHOORCRRHOI(vector3i1.x(), vector3i1.y(), vector3i1.z()) > 10000.0)) {
         Bridge3_23 bridge3_232 = Ref.method8().method5(vector3i1);
         return bridge3_232 == Bridge.method34().method60() || bridge3_232 == Bridge.method34().method61();
      } else {
         return false;
      }
   }
}
