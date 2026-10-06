package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.Bridge3_24;
import com.moonsworth.lunar.bridge.ShaderBridge;
import com.moonsworth.lunar.bridge.Bridge7_10;
import com.moonsworth.lunar.client.framework.Ref;
import com.moonsworth.lunar.ichor.VersionGate;
import java.util.List;
import net.minecraft.client.shader.Shader;
import net.minecraft.client.shader.ShaderGroup;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ShaderGroup.class)
public abstract class ShaderGroupMixin implements Bridge7_10 {
   @Final
   @Shadow
   public List<Shader> listShaders;

   public ShaderGroupMixin() {
   }

   @Shadow
   public abstract String getShaderGroupName();

   @VersionGate(max = 1)
   @Shadow
   public abstract void loadShaderGroup(float value1);

   @VersionGate(min = 5)
   @Shadow
   public abstract void render(float value1);

   @Shadow
   public abstract void deleteShaderGroup();

   @Override
   public List<ShaderBridge> bridge$listShaders() {
      return this.listShaders;
   }

   @Override
   public String bridge$getShaderGroupName() {
      return this.getShaderGroupName();
   }

   @Override
   public void bridge$process(Bridge3_24 bridge3_241, float value2) {
      if (Ref.MC_VERSION >= 5) {
         this.render(value2);
      } else {
         this.loadShaderGroup(value2);
      }
   }

   @Override
   public void bridge$close() {
      this.deleteShaderGroup();
   }
}
