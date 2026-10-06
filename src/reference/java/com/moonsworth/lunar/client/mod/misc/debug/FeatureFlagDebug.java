package com.moonsworth.lunar.client.mod.misc.debug;

import com.moonsworth.lunar.client.config.FeatureFlag;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.OptionContainer;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ToggleOption.ToggleOptionBuilder;
import com.moonsworth.lunar.client.config.option.OptionTraits;
import org.apache.commons.lang3.mutable.MutableBoolean;

public class FeatureFlagDebug extends AbstractFeature {
   public FeatureFlagDebug() {
      super(true);
   }

   public String getId() {
      return "FEATURE_FLAG_DEBUG";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      for (FeatureFlag rewindhandlerstype25 : FeatureFlag.values()) {
         String text6 = rewindhandlerstype25.getIdentifier();
         String text7 = text6.substring(0, 1).toLowerCase() + text6.substring(1);
         String text8 = text6.substring(0, 1).toUpperCase() + text6.substring(1);
         ToggleOption lightingextension4439 = (ToggleOption)((ToggleOptionBuilder)((ToggleOptionBuilder)OptionFactory.method7(text7).OIRHORRROCHOIRCRHHORHRCIIRHROO(text8))
               .method4(true))
            .method31();
         lightingextension4439.CICORRHIOIIOORRRICCORIOIOCIHII(arg2 -> {
            if (this.isEnabled() && rewindhandlerstype25.isEnabled() != arg2) {
               rewindhandlerstype25.setValue(arg2);
               if (rewindhandlerstype25.getDynamicReset() != null) {
                  rewindhandlerstype25.getDynamicReset().accept(arg2);
               }
            }
         });
         lightingextension231.method9(new ClientOption[]{lightingextension4439});
      }
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field7}).method11(this);
   }

   public void method3(boolean flag1) {
      for (FeatureFlag rewindhandlerstype25 : FeatureFlag.values()) {
         if (flag1) {
            MutableBoolean mutableboolean6 = new MutableBoolean();
            OptionContainer framework57 = (OptionContainer)this.method7(ModTraits.field14);
            if (framework57 != null) {
               framework57.method4(
                  arg2 -> {
                     if (arg2.method2(OptionTraits.field11)
                        && arg2.get() instanceof Boolean flag3
                        && arg2.getId().equalsIgnoreCase(rewindhandlerstype25.getIdentifier())) {
                        rewindhandlerstype25.setValue(flag3);
                        mutableboolean6.setValue(true);
                        return true;
                     } else {
                        return false;
                     }
                  }
               );
            }

            if (mutableboolean6.isFalse()) {
               rewindhandlerstype25.setValue(true);
            }
         } else {
            rewindhandlerstype25.setValue(true);
         }
      }
   }
}
