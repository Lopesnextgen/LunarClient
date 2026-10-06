package com.moonsworth.lunar.client.mod.render.items2d;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.EntityItemExtensionBridge;
import com.moonsworth.lunar.bridge.EntityItemBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.ItemEntityRendererBridge;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.event.mixin.fishing.mixin.EventRenderEntityItem;
import com.moonsworth.lunar.client.config.option.OptionEnumValue;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.EnumOption;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.config.Config;
import java.util.Locale;
import lombok.Generated;
import org.apache.commons.lang3.text.WordUtils;

public class Items2d extends AbstractFeature {
   private final EnumOption<Items2d.Type> field8 = (EnumOption<Items2d.Type>)com.moonsworth.lunar.client.config.option.OptionFactory.method10(
         "renderingOptions", Items2d.Type.SPRITE
      )
      .method31();

   public Items2d() {
      super(false);
      this.method5(ModTraits.field18, arg0 -> arg0.method5(new Config[]{Config.field1}));
      this.handle(EventRenderEntityItem.class, arg0 -> {
         if (!arg0.method4()) {
            if (!Ref.method4().method40().method30().isEnabled()) {
               AbstractRenderContext bridgeextension_91 = arg0.method1();
               arg0.setCancelled(true);
               EntityItemExtensionBridge bridgeextension3_22 = arg0.method3();
               ItemEntityRendererBridge bridge_373 = Bridge.method53();
               bridgeextension_91.method5(arg3x -> bridge_373.method2(arg3x, arg0.method2(), bridgeextension3_22, arg0.getX(), arg0.getY(), arg0.getZ()));
               bridgeextension_91.method6(arg3x -> {
                  arg3x.push();
                  bridge_373.method1((EntityItemBridge)bridgeextension3_22, arg0.getX(), arg0.getY(), arg0.getZ(), arg0.method5());
                  arg3x.pop();
               });
            }
         }
      });
   }

   public String getId() {
      return "2D_ITEMS";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method9(new ClientOption[]{this.field8});
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field6}).method11(this);
   }

   @Generated
   public EnumOption<Items2d.Type> method13() {
      return this.field8;
   }

   public enum Type implements OptionEnumValue {
      SPRITE,
      MODEL;

      Type() {
      }

      public String id() {
         return this.name().toLowerCase(Locale.ROOT);
      }

      @Override
      public String toString() {
         return WordUtils.capitalize(this.name());
      }
   }
}
