package com.moonsworth.lunar.legacy.mixin;

import com.google.common.base.Functions;
import com.moonsworth.lunar.ichor.Annotation2;
import com.moonsworth.lunar.legacy.wrapper.util.OpenALNative4;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementList;
import net.minecraft.advancements.Advancement.Builder;
import net.minecraft.advancements.AdvancementList.Listener;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Annotation2(min = 5)
@Mixin(AdvancementList.class)
public abstract class AdvancementListMixin {
   @Final
   @Shadow
   public Set<Advancement> roots;
   @Shadow
   public Listener listener;
   @Final
   @Shadow
   public Set<Advancement> nonRoots;
   @Final
   @Shadow
   public Map<ResourceLocation, Advancement> advancements;
   @Final
   @Shadow
   public static Logger LOGGER;

   @Overwrite
   public void loadAdvancements(Map<ResourceLocation, Builder> var1) {
      OpenALNative4 var2 = new OpenALNative4(Functions.forMap(this.advancements, null));

      while (!var1.isEmpty()) {
         boolean var3 = false;
         Iterator var4 = var1.entrySet().iterator();

         while (var4.hasNext()) {
            Entry var5 = (Entry)var4.next();
            ResourceLocation var6 = (ResourceLocation)var5.getKey();
            Builder var7 = (Builder)var5.getValue();
            if (var7.resolveParent(var2)) {
               Advancement var8 = var7.build(var6);
               this.advancements.put(var6, var8);
               var3 = true;
               var4.remove();
               if (var8.getParent() == null) {
                  this.roots.add(var8);
                  if (this.listener != null) {
                     this.listener.rootAdvancementAdded(var8);
                  }
               } else {
                  this.nonRoots.add(var8);
                  if (this.listener != null) {
                     this.listener.nonRootAdvancementAdded(var8);
                  }
               }
            }
         }

         if (!var3) {
            for (Entry var10 : var1.entrySet()) {
               LOGGER.error("Couldn't load advancement " + var10.getKey() + ": " + var10.getValue());
            }
            break;
         }
      }

      LOGGER.info("Loaded " + this.advancements.size() + " advancements");
   }
}
