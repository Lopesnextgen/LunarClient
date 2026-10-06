package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.optifine.ShaderProgramBridge;
import com.moonsworth.lunar.ichor.VersionGate;
import net.optifine.shaders.Program;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@VersionGate(min = 1)
@Mixin(Program.class)
public abstract class ProgramMixin implements ShaderProgramBridge {
   @Shadow
   public int id;

   public ProgramMixin() {
   }

   public int getId() {
      return this.id;
   }
}
