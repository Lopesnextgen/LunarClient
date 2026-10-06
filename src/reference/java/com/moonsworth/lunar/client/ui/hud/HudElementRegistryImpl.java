package com.moonsworth.lunar.client.ui.hud;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSet.Builder;
import com.moonsworth.lunar.client.framework.Client;
import com.moonsworth.lunar.client.ui.hud.MovableHudElement;
import com.moonsworth.lunar.client.ui.hud.HudElementRegistry;
import com.moonsworth.lunar.client.framework.mod.AlertExtension;
import com.moonsworth.lunar.client.framework.mod.Framework;
import com.moonsworth.lunar.client.framework.mod.Framework7Extension;
import com.moonsworth.lunar.client.ui.hud.MixinCore9Extension;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class HudElementRegistryImpl implements HudElementRegistry {
   private final Map<Framework7Extension, MovableHudElement> field1 = new HashMap<>();

   @Override
   public Collection<MovableHudElement> method1() {
      Builder var1 = ImmutableSet.builder();

      for (Framework7Extension var3 : Client.method109().method40().IIORHHIRHIORHRCCCOICCRCHRRCCRH()) {
         if (method5(var3)) {
            var1.add(var3);
         }

         AlertExtension var4 = (AlertExtension)var3.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field5);
         if (var4 != null) {
            var4.method3(var1x -> {
               if (var1x.isEnabled()) {
                  if (method5(var1x)) {
                     var1.add(var1x);
                  }

                  return true;
               } else {
                  return false;
               }
            });
         }
      }

      return var1.build().stream().map(this::method4).toList();
   }

   @Override
   public Optional<MovableHudElement> method2(String var1) {
      return this.method3(var1).map(this::method4);
   }

   Optional<Framework7Extension> method3(String var1) {
      for (Framework7Extension var3 : Client.method109().method40().IIORHHIRHIORHRCCCOICCRCHRRCCRH()) {
         if (var3.method2(Framework.field1) && var3.getId().equals(var1)) {
            return Optional.of(var3);
         }

         AlertExtension var4 = (AlertExtension)var3.HIRHCCHIRHRORIICOIHIHCICOIRHHC(Framework.field5);
         if (var4 != null) {
            Framework7Extension var5 = (Framework7Extension)var4.method4(
               var1x -> var1x.method2(Framework.field1) && var1x.getId().equals(var1)
            );
            if (var5 != null) {
               return Optional.of(var5);
            }
         }
      }

      return Optional.empty();
   }

   private MovableHudElement method4(Framework7Extension var1) {
      return this.field1.computeIfAbsent(var1, MovableHudElementImpl::new);
   }

   private static boolean method5(Framework7Extension var0) {
      if (var0.isEnabled() && var0.method2(Framework.field1)) {
         MixinCore9Extension var1 = (MixinCore9Extension)var0.RHRHIOOCICIORIOCIHHCIIRCRHHOII(Framework.field1);
         return var1.method30() && var1.getWidth() > 0.0F && var1.getHeight() > 0.0F;
      } else {
         return false;
      }
   }
}
