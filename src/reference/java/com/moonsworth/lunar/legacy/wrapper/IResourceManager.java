package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.Bridge;
import com.moonsworth.lunar.bridge.horsestats.ResourceLocationBridge;
import com.moonsworth.lunar.client.render.shader.ShaderInjectRegistry;
import java.util.List;
import java.util.Set;
import net.minecraft.client.resources.IResource;
import net.minecraft.util.ResourceLocation;

public class IResourceManager implements net.minecraft.client.resources.IResourceManager {
   public static final IResourceManager field1 = new IResourceManager();

   public IResource getResource(ResourceLocation var1) {
      return (IResource)Bridge.method8().method94((ResourceLocationBridge)var1, ShaderInjectRegistry.method16((ResourceLocationBridge)var1));
   }

   public Set getResourceDomains() {
      return Set.of();
   }

   public List getAllResources(ResourceLocation var1) {
      return List.of();
   }

   public Set<String> getResourceDomains() {
      return Set.of();
   }

   public List<IResource> getAllResources(ResourceLocation var1) {
      return List.of();
   }
}
