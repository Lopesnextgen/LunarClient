package com.moonsworth.lunar.legacy.mixin;

import com.moonsworth.lunar.bridge.AbstractMethodErrorImpl;
import com.moonsworth.lunar.bridge.BlockStateBridge;
import com.moonsworth.lunar.bridge.BlockButtonBridge;
import com.moonsworth.lunar.client.framework.Ref;
import net.minecraft.block.BlockButton;
import net.minecraft.block.state.IBlockState;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BlockButton.class)
public abstract class BlockButtonMixin implements BlockButtonBridge {
   public BlockButtonMixin() {
   }

   public boolean bridge$isPowered(BlockStateBridge bridge2_171) {
      if (Ref.MC_VERSION >= 1) {
         return (Boolean)((IBlockState)bridge2_171).getValue(BlockButton.POWERED);
      } else {
         throw new AbstractMethodErrorImpl();
      }
   }
}
