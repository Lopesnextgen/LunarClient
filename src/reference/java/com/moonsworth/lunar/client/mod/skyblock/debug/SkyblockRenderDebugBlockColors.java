package com.moonsworth.lunar.client.mod.skyblock.debug;

import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.config.option.ConstantName;
import com.moonsworth.lunar.client.framework.Ref;

public class SkyblockRenderDebugBlockColors extends AbstractFeature {
   public SkyblockRenderDebugBlockColors(SkyblockDebugMod skyblockdebugmod1) {
      super(false);
      this.method45(ModTraits.field16, ChildModBinding.method3(skyblockdebugmod1));
   }

   @ConstantName
   public String getId() {
      return "SKYBLOCK_RENDER_DEBUG_BLOCK_COLORS";
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5, ModCategory.field7}).method8().method11(this);
   }

   public void method3(boolean flag1) {
      this.method14();
   }

   public boolean method13() {
      return this.isEnabled();
   }

   private void method14() {
      Ref.method3().bridge$getLevelRenderer().bridge$reloadChunks();
   }
}
