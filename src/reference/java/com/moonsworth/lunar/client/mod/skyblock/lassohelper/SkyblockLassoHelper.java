package com.moonsworth.lunar.client.mod.skyblock.lassohelper;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.WorldBridgeExtension;
import com.moonsworth.lunar.client.framework.mod.ModCategory;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.DynamicCondition;
import com.moonsworth.lunar.client.framework.mod.ModCategories;
import com.moonsworth.lunar.client.framework.mod.ChildModBinding;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.click.IslandUtils;
import com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.SkyblockItemUtil;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.ComparableImpl;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.AlertDisplayListener;
import com.moonsworth.lunar.client.framework.feature.mod.holograms.ComparableImpl.Type;
import com.moonsworth.lunar.client.event.mixin.fishing.EventTick;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.config.option.ConstantName;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.client.util.math.MathUtils;
import com.moonsworth.lunar.files.ValuePair;
import java.util.List;
import java.util.Optional;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.jetbrains.annotations.Nullable;
import com.moonsworth.lunar.client.mod.skyblock.core.Skyblock;

public class SkyblockLassoHelper extends AbstractFeature {
   private final AlertDisplayListener field8 = (AlertDisplayListener)this.method63(AlertDisplayListener.class);

   public SkyblockLassoHelper(Skyblock skyblock1) {
      super(false);
      this.method6(ModTraits.field16, ChildModBinding.method3(skyblock1));
      this.method6(ModTraits.field17, ModCategories.method2(SettingsPage.FORAGING));
      this.method6(ModTraits.field19, DynamicCondition.method1(this, IslandUtils::isOnIsland));
      this.handle(EventTick.class, this::method1);
   }

   private void method1(EventTick highlightimpl21) {
      Bridge5Extension_5 bridge5extension_52 = Ref.method7();
      WorldBridgeExtension itemcounter6extension3 = Ref.method8();
      if (bridge5extension_52 != null && itemcounter6extension3 != null) {
         ItemStackBridge bridgeextension_44 = bridge5extension_52.bridge$getCurrentEquippedItem();
         String text5 = SkyblockItemUtil.method2(bridgeextension_44);
         if (text5 != null) {
            if (text5.endsWith("_LASSO")) {
               this.method13()
                  .ifPresent(
                     arg1x -> {
                        ValuePair files6_22x = this.method2(arg1x);
                        if (files6_22x != null) {
                           Component component3x = ((BridgeExtension)files6_22x.field1).bridge$getCustomName();
                           if ((Float)files6_22x.field2 >= 100.0F) {
                              this.field8
                                 .method2(
                                    ComparableImpl.method2()
                                       .method1("FORAGING_LASSO_PRIMARY")
                                       .method2(Component.text("      REEL      ", NamedTextColor.GOLD))
                                       .method3(100L)
                                       .method4(Type.HIGH)
                                       .method6()
                                 );
                              this.field8
                                 .method2(ComparableImpl.method2().method1("FORAGING_LASSO_SECONDARY").method2(component3x).method3(100L).method4(Type.LOW).method6());
                           } else {
                              this.field8
                                 .method2(
                                    ComparableImpl.method2()
                                       .method1("FORAGING_LASSO_PRIMARY")
                                       .method2(Component.text("          (" + Math.round((Float)files6_22x.field2) + "%)          "))
                                       .method3(100L)
                                       .method4(Type.HIGH)
                                       .method6()
                                 );
                              this.field8
                                 .method2(ComparableImpl.method2().method1("FORAGING_LASSO_SECONDARY").method2(component3x).method3(100L).method4(Type.LOW).method6());
                           }
                        }
                     }
                  );
            }
         }
      }
   }

   @Nullable
   private ValuePair<BridgeExtension, Float> method2(BridgeExtension bridgeextension1) {
      for (BridgeExtension bridgeextension3 : Ref.method8().bridge$getEntities()) {
         if (!(bridgeextension3.method15(bridgeextension1.bridge$getPosX(), bridgeextension3.bridge$getPosY(), bridgeextension1.bridge$getPosZ()) > 9.0)
            && MathUtils.method18(bridgeextension3.bridge$getPosY(), bridgeextension1.bridge$getPosY(), 6.0)) {
            Component component4 = bridgeextension3.bridge$getCustomName();
            if (component4 != null) {
               Float value5 = this.method4(component4);
               if (value5 != null) {
                  return ValuePair.method1(bridgeextension3, value5);
               }
            }
         }
      }

      return null;
   }

   public Optional<BridgeExtension> method13() {
      Bridge5Extension_5 bridge5extension_51 = Ref.method7();
      return Ref.method8()
         .bridge$getEntities()
         .stream()
         .filter(arg1x -> arg1x.bridge$isLeashed() && arg1x.bridge$getLeashedToEntity() == bridge5extension_51)
         .findAny();
   }

   @Nullable
   public Float method4(Component component1) {
      if (component1 instanceof TextComponent text2) {
         if (!text2.content().isEmpty()) {
            return null;
         }

         List list3 = component1.children();
         if (list3.size() != 2) {
            return null;
         }

         Component component4 = (Component)list3.get(0);
         Component component5 = (Component)list3.get(1);
         if (component4 instanceof TextComponent text6) {
            if (component5 instanceof TextComponent text7) {
               if (!text6.content().isBlank()) {
                  return null;
               }

               if (!text7.content().isBlank()) {
                  return null;
               }

               float value8 = text7.content().length() * 100.0F / (text7.content().length() + text6.content().length());
               value8 *= 1.1111112F;
               return Math.min(100.0F, value8);
            } else {
               return null;
            }
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   @ConstantName
   public String getId() {
      return "SKYBLOCK_LASSO_HELPER";
   }

   protected ModDetails method20() {
      return ModDetails.method7().method1(new ModCategory[]{ModCategory.field5}).method11(this);
   }
}
