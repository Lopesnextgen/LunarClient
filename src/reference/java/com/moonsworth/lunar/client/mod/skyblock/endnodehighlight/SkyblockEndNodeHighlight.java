package com.moonsworth.lunar.client.mod.skyblock.endnodehighlight;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.WorldBridgeExtension;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.highlight.SkyblockIsland;
import com.moonsworth.lunar.client.framework.feature.mod.impl.calculator.TrackedBlockHighlight;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSpawnParticle;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.math.MathUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import org.joml.Vector2d;
import org.joml.Vector3i;

public class SkyblockEndNodeHighlight extends TrackedBlockHighlight {
   private static final long field11 = 2000L;
   private final HashMap<Vector3i, Long> field12 = new HashMap<>();
   private int field13 = 0;

   public SkyblockEndNodeHighlight(Skyblock skyblock1) {
      super(new Vector2d(1.0, 1.0), 1140915968);
      this.method45(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method45(ModTraits.field17, ModCategories.method2(SettingsPage.END));
      this.method45(ModTraits.field19, DynamicCondition.method1(this, () -> IslandUtils.getIsland() == SkyblockIsland.END));
   }

   public String getId() {
      return "SKYBLOCK_END_NODE_HIGHLIGHT";
   }

   protected void method3(EventTick highlightimpl21) {
      this.field13++;
      if (this.field13 > 20) {
         this.field13 = 0;
         ArrayList list2 = new ArrayList();

         for (Entry entry4 : this.field12.entrySet()) {
            if ((Long)entry4.getValue() < Ref.method3().bridge$getSystemTime() - 2000L) {
               list2.add((Vector3i)entry4.getKey());
            }
         }

         for (Vector3i vector3i6 : list2) {
            this.field12.remove(vector3i6);
         }
      }

      if (this.field13 % 5 == 0) {
         super.method3(highlightimpl21);
      }
   }

   protected void method1(EventSpawnParticle highlightimpl151) {
      Vector3i vector3i2 = null;
      if (MathUtils.method18(highlightimpl151.getPosY() % 1.0, 0.25, 0.01)
         && MathUtils.method18(Math.abs(highlightimpl151.getPosX() - 0.5) % 1.0, 0.0, 0.2)
         && MathUtils.method18(Math.abs(highlightimpl151.getPosZ() - 0.5) % 1.0, 0.0, 0.2)) {
         vector3i2 = new Vector3i((int)Math.floor(highlightimpl151.getPosX()), (int)Math.floor(highlightimpl151.getPosY() - 1.0), (int)Math.floor(highlightimpl151.getPosZ()));
      }

      if (MathUtils.method18(highlightimpl151.getPosY() % 1.0, 0.75, 0.01)
         && MathUtils.method18(Math.abs(highlightimpl151.getPosX() - 0.5) % 1.0, 0.0, 0.2)
         && MathUtils.method18(Math.abs(highlightimpl151.getPosZ() - 0.5) % 1.0, 0.0, 0.2)) {
         vector3i2 = new Vector3i((int)Math.floor(highlightimpl151.getPosX()), (int)Math.floor(highlightimpl151.getPosY() + 1.0), (int)Math.floor(highlightimpl151.getPosZ()));
      }

      if (MathUtils.method18(Math.abs(highlightimpl151.getPosX()) % 1.0, 0.25, 0.01)
         && MathUtils.method18(Math.abs(highlightimpl151.getPosY() - 0.5) % 1.0, 0.0, 0.2)
         && MathUtils.method18(Math.abs(highlightimpl151.getPosZ() - 0.5) % 1.0, 0.0, 0.2)) {
         vector3i2 = new Vector3i((int)Math.floor(highlightimpl151.getPosX() + 1.0), (int)Math.floor(highlightimpl151.getPosY()), (int)Math.floor(highlightimpl151.getPosZ()));
      }

      if (MathUtils.method18(Math.abs(highlightimpl151.getPosX()) % 1.0, 0.75, 0.01)
         && MathUtils.method18(Math.abs(highlightimpl151.getPosY() - 0.5) % 1.0, 0.0, 0.2)
         && MathUtils.method18(Math.abs(highlightimpl151.getPosZ() - 0.5) % 1.0, 0.0, 0.2)) {
         vector3i2 = new Vector3i((int)Math.floor(highlightimpl151.getPosX() - 1.0), (int)Math.floor(highlightimpl151.getPosY()), (int)Math.floor(highlightimpl151.getPosZ()));
      }

      if (MathUtils.method18(Math.abs(highlightimpl151.getPosZ()) % 1.0, 0.25, 0.01)
         && MathUtils.method18(Math.abs(highlightimpl151.getPosX() - 0.5) % 1.0, 0.0, 0.2)
         && MathUtils.method18(Math.abs(highlightimpl151.getPosY() - 0.5) % 1.0, 0.0, 0.2)) {
         vector3i2 = new Vector3i((int)Math.floor(highlightimpl151.getPosX()), (int)Math.floor(highlightimpl151.getPosY()), (int)Math.floor(highlightimpl151.getPosZ() + 1.0));
      }

      if (MathUtils.method18(Math.abs(highlightimpl151.getPosZ()) % 1.0, 0.75, 0.01)
         && MathUtils.method18(Math.abs(highlightimpl151.getPosX() - 0.5) % 1.0, 0.0, 0.2)
         && MathUtils.method18(Math.abs(highlightimpl151.getPosY() - 0.5) % 1.0, 0.0, 0.2)) {
         vector3i2 = new Vector3i((int)Math.floor(highlightimpl151.getPosX()), (int)Math.floor(highlightimpl151.getPosY()), (int)Math.floor(highlightimpl151.getPosZ() - 1.0));
      }

      if (vector3i2 != null) {
         Bridge5Extension_5 bridge5extension_53 = Ref.method7();
         if (bridge5extension_53 != null && bridge5extension_53.method15(vector3i2.x(), vector3i2.y(), vector3i2.z()) > 10000.0) {
            return;
         }

         WorldBridgeExtension itemcounter6extension4 = Ref.method8();
         if (itemcounter6extension4 != null && itemcounter6extension4.method5(vector3i2) != Bridge.method34().method46()) {
            return;
         }

         if (this.field12.get(vector3i2) != null && this.field12.get(vector3i2) > Ref.method3().bridge$getSystemTime() - 2000L) {
            this.method4(vector3i2);
         }

         this.field12.put(vector3i2, Ref.method3().bridge$getSystemTime());
      }
   }

   protected boolean method2(Vector3i vector3i1) {
      if (Ref.method7() == null) {
         return false;
      }

      if (Ref.method7().ORICHRORRORHORHOIHCRHOORCRRHOI(vector3i1.x(), vector3i1.y(), vector3i1.z()) > 10000.0) {
         return false;
      }

      WorldBridgeExtension itemcounter6extension2 = Ref.method8();
      return itemcounter6extension2 != null && itemcounter6extension2.method5(vector3i1) != Bridge.method34().method46()
         ? false
         : this.field12.get(vector3i1) != null && this.field12.get(vector3i1) > Ref.method3().bridge$getSystemTime() - 2000L;
   }
}
