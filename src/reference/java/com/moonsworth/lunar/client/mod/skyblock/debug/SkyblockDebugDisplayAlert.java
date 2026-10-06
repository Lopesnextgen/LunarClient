package com.moonsworth.lunar.client.mod.skyblock.debug;

import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.ComparableImpl;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.AlertDisplayListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.ComparableImpl.Type;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ConstantName;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class SkyblockDebugDisplayAlert extends AbstractFeature {
   private final AlertDisplayListener field8 = (AlertDisplayListener)this.method63(AlertDisplayListener.class);

   public SkyblockDebugDisplayAlert(SkyblockDebugMod skyblockdebugmod1) {
      super(false);
      this.method2(ModTraits.field16, ChildModBinding.method3(skyblockdebugmod1));
   }

   @ConstantName
   public String getId() {
      return "SKYBLOCK_DEBUG_DISPLAY_ALERT";
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5, ModCategory.field7}).method8().method11(this);
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(
         new ClientOption[]{OptionFactory.method14("debugTriggerDisplayAlert").method4(() -> this.method13()).method31()}
      );
   }

   private void method13() {
      this.field8
         .method2(
            ComparableImpl.method2()
               .method1("DEBUG_FAKE_ALERT")
               .method2(Component.text("DEBUG ALERT", NamedTextColor.GOLD))
               .method3(2000L)
               .method4(Type.CRITICAL)
               .method6()
         );
   }
}
