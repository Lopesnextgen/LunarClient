package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge11Extension2;
import com.moonsworth.lunar.bridge.Bridge11_2;
import com.moonsworth.lunar.bridge.Bridge13_3;
import com.moonsworth.lunar.bridge.ResourcePackBridge;
import com.moonsworth.lunar.bridge.ResourceBridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.client.resources.FallbackResourceManager;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.SimpleReloadableResourceManager;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(SimpleReloadableResourceManager.class)
public abstract class SimpleReloadableResourceManagerMixin implements Bridge11Extension2 {
   @Shadow
   public Map<String, FallbackResourceManager> domainResourceManagers;

   public SimpleReloadableResourceManagerMixin() {
   }

   @Shadow
   public abstract IResource getResource(ResourceLocation location1);

   @Shadow
   public abstract void reloadResources(List<IResourcePack> list1);

   @Shadow
   public abstract void registerReloadListener(IResourceManagerReloadListener iresourcemanagerreloadlistener1);

   @Shadow
   public abstract Set<String> getResourceDomains();

   @Shadow
   public abstract List<IResource> getAllResources(ResourceLocation location1);

   public ResourceBridge bridge$getResource(ResourceLocationBridge horsestats141) {
      IResourceManager iresourcemanager2 = (IResourceManager)this.domainResourceManagers.get(horsestats141.bridge$getDomain());
      if (iresourcemanager2 != null) {
         try {
            return (ResourceBridge)iresourcemanager2.getResource((ResourceLocation)horsestats141);
         } catch (IOException exception4) {
            return null;
         }
      } else {
         return null;
      }
   }

   public void bridge$putDomainResourceManager(String text1, Bridge11_2 bridge11_22) {
      this.domainResourceManagers.put(text1, (FallbackResourceManager)bridge11_22);
   }

   public void bridge$reloadResources(List<ResourcePackBridge> list1) {
      ArrayList list2 = new ArrayList(list1.size());

      for (ResourcePackBridge bridge144 : list1) {
         list2.add((IResourcePack)bridge144);
      }

      this.reloadResources(list2);
   }

   public void bridge$registerReloadListener(Bridge13_3 bridge13_31) {
      this.registerReloadListener(arg1x -> bridge13_31.method1((Bridge11_2)arg1x));
   }

   public Set<String> bridge$getResourceDomains() {
      return this.getResourceDomains();
   }

   public ResourceBridge bridge$getResource(ResourceLocationBridge horsestats141, boolean flag2) {
      return this.bridge$getResource(horsestats141);
   }

   public List<ResourceBridge> bridge$getAllResources(ResourceLocationBridge horsestats141) {
      List list2 = this.getAllResources((ResourceLocation)horsestats141);
      ArrayList list3 = new ArrayList(list2.size());

      for (IResource iresource5 : list2) {
         list3.add((ResourceBridge)iresource5);
      }

      return list3;
   }
}
