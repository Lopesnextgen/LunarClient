package com.moonsworth.lunar.client.network.apollo;

import com.moonsworth.lunar.bridge.Bridge6_10;
import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.AbstractRenderContext;
import com.moonsworth.lunar.bridge.Bridge_57;
import com.moonsworth.lunar.client.highlight.HighlightImpl_3;
import com.moonsworth.lunar.client.event.mixin.highlight.ItemGlintRenderEvent;
import com.moonsworth.lunar.client.mod.render.glintcolorizer.GlintColorizer;
import com.moonsworth.lunar.client.rewindhandlers.Rewindhandlers;
import com.moonsworth.lunar.client.util.ThreadModuleDump63;
import java.awt.Color;
import java.util.function.IntConsumer;
import com.moonsworth.lunar.client.framework.Client;

public class GlintApolloHandler extends ApolloModuleHandler {
   public GlintApolloHandler() {
      super("glint", "Glint");
      this.method3(ItemGlintRenderEvent.class, this::method2, 120);
   }

   @Override
   public void method3(HighlightImpl_3 var1) {
   }

   private void method2(ItemGlintRenderEvent var1) {
      if (!var1.isCancelled()) {
         AbstractRenderContext var2 = var1.method7();
         if (!var2.method38()) {
            IntConsumer var3 = var1.method3();
            if (var3 != null) {
               ItemStackBridge var4 = (ItemStackBridge)var1.method6();
               if (var4 != null) {
                  Bridge_57 var5 = Rewindhandlers.method1(var4);
                  if (var5 != null) {
                     String var6 = var5.bridge$getString("glint");
                     int var7 = var5.bridge$getInteger("glint");
                     if (var7 != 0 || !var6.isEmpty()) {
                        GlintColorizer var8 = Client.method109().method40().method26();
                        if (var8 != null) {
                           ApolloModuleManager var9 = ThreadModuleDump63.method4().method84();
                           if (!var6.isEmpty()) {
                              var7 = Color.decode(var6).getRGB();
                              var9.method15(this.getId(), "Hex");
                           } else {
                              var9.method15(this.getId(), "RGB");
                           }

                           ItemGlintRenderEvent.Type var10 = var1.method2();
                           if (var10 == ItemGlintRenderEvent.Type.GUI || var10 == ItemGlintRenderEvent.Type.ITEM) {
                              var8.method8(var2, var3, var7);
                              var1.setCancelled(true);
                           } else if (var10 == ItemGlintRenderEvent.Type.EQUIPPED_ARMOR && var1.method5() instanceof Bridge6_10 var11) {
                              var8.method9(var2, () -> var3.accept(-1), var11.method2() + var2.method28(), var7);
                              var1.setCancelled(true);
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }
}
