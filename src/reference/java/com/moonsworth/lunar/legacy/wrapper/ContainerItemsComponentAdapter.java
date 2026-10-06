package com.moonsworth.lunar.legacy.wrapper;

import com.moonsworth.lunar.bridge.ItemStackBridge;
import com.moonsworth.lunar.bridge.ContainerItemsComponent;
import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ContainerItemsComponentAdapter implements ContainerItemsComponent {
   private final NonNullList<ItemStack> field1;

   public ContainerItemsComponentAdapter(NonNullList<ItemStack> var1) {
      this.field1 = var1;
   }

   @Override
   public List<ItemStackBridge> bridge$items() {
      return (List<ItemStackBridge>)this.field1;
   }
}
