package com.moonsworth.lunar.ichor.mixin;

import org.objectweb.asm.Type;

public interface DescriptorType {
   String getDescriptor();

   default int getOpcode(int value) {
      return Type.getType(this.getDescriptor()).getOpcode(value);
   }

   default int getSize() {
      return Type.getType(this.getDescriptor()).getSize();
   }
}
