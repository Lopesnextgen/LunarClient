package com.moonsworth.lunar.client.mod.render.servercustomnameplate;

import com.moonsworth.lunar.bridge.EntityPlayerBridge;
import com.moonsworth.lunar.client.framework.mod.ModTraits;
import com.moonsworth.lunar.client.framework.mod.ModEnabledState;
import com.moonsworth.lunar.client.framework.mod.AbstractFeature;
import com.moonsworth.lunar.client.framework.mod.ModDetails;
import com.moonsworth.lunar.client.event.mixin.highlight.EventRenderNameTag;
import java.util.List;

public class ServerCustomNameplate extends AbstractFeature {
   public ServerCustomNameplate() {
      super(true);
      this.handle(EventRenderNameTag.class, this::method3);
   }

   public String getId() {
      return "SERVER_CUSTOM_NAMEPLATE";
   }

   protected void method1(boolean flag1) {
      this.method3(ModTraits.field6, ModEnabledState.method6(flag1));
   }

   protected ModDetails method20() {
      return null;
   }

   private void method3(EventRenderNameTag highlightimpl111) {
      if (!highlightimpl111.isCancelled()) {
         if (this.field4.method40().method51().isEnabled()) {
            if (highlightimpl111.method2() instanceof EntityPlayerBridge bridgeextension2222) {
               List list4 = (List)this.field4.method58().method3().get(bridgeextension2222.bridge$getUniqueID());
               if (list4 != null) {
                  highlightimpl111.getLines().clear();
                  highlightimpl111.getLines().addAll(list4);
               }
            }
         }
      }
   }
}
