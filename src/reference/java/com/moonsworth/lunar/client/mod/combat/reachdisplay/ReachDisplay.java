package com.moonsworth.lunar.client.mod.combat.reachdisplay;

import com.moonsworth.lunar.bridge.Bridge5Extension_5;
import com.moonsworth.lunar.bridge.BridgeExtension;
import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.client.chat.translation.SharedTranslations;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.ui.hud.HudAnchor;
import com.moonsworth.lunar.client.ui.hud.HudSize;
import com.moonsworth.lunar.client.ui.hud.TypedHudRenderer;
import com.moonsworth.lunar.client.event.entity.EventEntityStatus;
import com.moonsworth.lunar.client.event.combat.EventPreAttackEntity;
import com.moonsworth.lunar.client.event.mixin.fishing.EventSecond;
import com.moonsworth.lunar.client.network.server.ServerBrandWatcher;
import com.moonsworth.lunar.client.network.server.KeystrokesType;
import com.moonsworth.lunar.client.config.option.OptionFactory;
import com.moonsworth.lunar.client.config.option.ClientOption;
import com.moonsworth.lunar.client.config.option.RootSettingsBuilder;
import com.moonsworth.lunar.client.config.option.ColorOption;
import com.moonsworth.lunar.client.config.option.ToggleOption;
import com.moonsworth.lunar.client.config.option.ColorOption.Data;
import com.moonsworth.lunar.client.config.option.SettingsPage;
import com.moonsworth.lunar.client.util.game.NpcUtils;
import com.moonsworth.lunar.client.framework.Ref;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Objects;
import lombok.Generated;
import vavi.util.StringUtil;

public class ReachDisplay extends AbstractFeature {
   private static final DecimalFormat field8 = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.ENGLISH));
   private final ToggleOption field9 = (ToggleOption)OptionFactory.method7("reverseOrder").method31();
   private final ToggleOption field10 = (ToggleOption)OptionFactory.method7("hideZero").method31();
   private final ToggleOption field11 = (ToggleOption)OptionFactory.method7("highlightAttackablePlayers").method31();
   private final ColorOption field12 = (ColorOption)((Data)OptionFactory.method8("highlightColor").method4(1291910912))
      .method31();
   private boolean field13;
   private long sentTime;
   private double field14;
   private int entityId;
   private double field15;

   public ReachDisplay() {
      super(false);
      this.method2(ModTraits.field1, TypedHudRenderer.method22(0.0F, 0.0F, HudAnchor.TOP_LEFT, HudSize.method1(10, 18, 22, 40, 65, 72), arg1 -> {
         if ((Boolean)this.field10.get() && this.field15 == 0.0) {
            return null;
         } else {
            String text2 = field8.format(this.field15);
            if ((Boolean)this.field9.get()) {
               String text3 = StringUtil.capitalize(SharedTranslations.field3.trim()) + ": ";
               return text3 + text2;
            } else {
               return text2 + SharedTranslations.field3;
            }
         }
      }));
      this.handle(EventEntityStatus.class, this::method3);
      this.handle(EventSecond.class, arg1 -> {
         if (Ref.method7() == null || Ref.method7().OOHOIHICOCRORHHRHCRRROIHHIHHOH() - this.sentTime >= 80L) {
            if (this.field13) {
               this.field15 = 0.0;
            }

            this.field13 = !this.field13;
         }
      });
      this.handle(EventPreAttackEntity.class, this::method2);
   }

   public String getId() {
      return "REACH_DISPLAY";
   }

   public void method2(RootSettingsBuilder lightingextension231) {
      lightingextension231.method7(SettingsPage.GENERAL, arg1x -> {
         arg1x.method9(new ClientOption[]{this.field9, this.field10});
         arg1x.HORHROIOIOICIRHIOCOICHHHIHCIIO(this.field11, arg1xx -> arg1xx.method9(new ClientOption[]{this.field12}));
      });
   }

   private void method2(EventPreAttackEntity highlightimpl5_21) {
      this.entityId = highlightimpl5_21.method2().bridge$getEntityId();
      this.field14 = highlightimpl5_21.getDistance();
      this.sentTime = this.mc.bridge$getPlayer().OOHOIHICOCRORHHRHCRRROIHHIHHOH();
   }

   private void method3(EventEntityStatus highlightimpl111) {
      if (highlightimpl111.method2() == 2
         && highlightimpl111.method1().bridge$getEntityId() == this.entityId
         && Ref.method7().OOHOIHICOCRORHHRHCRRROIHHIHHOH() - this.sentTime <= 4L) {
         this.field15 = Math.min(Ref.method7().bridge$entityAttackRange(), this.field14);
      }
   }

   public boolean method4(EntityPlayerBridge bridgeextension2221) {
      if (this.isEnabled() && (Boolean)this.field11.get()) {
         Bridge5Extension_5 bridge5extension_52 = Ref.method7();
         BridgeExtension bridgeextension3 = (BridgeExtension)Ref.method3().bridge$getPointedEntity().orElse(null);
         return bridgeextension2221 != null
               && bridge5extension_52 != null
               && bridgeextension3 != null
               && !Objects.equals(bridgeextension2221.bridge$getUniqueID(), bridge5extension_52.bridge$getUniqueID())
               && !NpcUtils.method3(bridgeextension2221, ServerBrandWatcher.method8(KeystrokesType.HYPIXEL))
            ? bridgeextension2221.bridge$getUniqueID().equals(bridgeextension3.bridge$getUniqueID())
            : false;
      } else {
         return false;
      }
   }

   @Generated
   public ColorOption method13() {
      return this.field12;
   }
}
