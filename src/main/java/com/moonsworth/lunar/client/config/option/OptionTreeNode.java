package com.moonsworth.lunar.client.config.option;

import java.util.List;
import java.util.function.BooleanSupplier;
import org.jetbrains.annotations.NotNull;

public interface OptionTreeNode<ON> {
   @NotNull
   List<ON> getChildren();

   BooleanSupplier method1();
}
