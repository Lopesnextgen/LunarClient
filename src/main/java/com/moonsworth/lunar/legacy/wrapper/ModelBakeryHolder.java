package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.ichor.VersionGate;
import net.minecraft.client.resources.model.ModelBakery;

@VersionGate(min = 1)
public class ModelBakeryHolder {
   public static ModelBakery field1;

   public ModelBakeryHolder() {
   }
}
