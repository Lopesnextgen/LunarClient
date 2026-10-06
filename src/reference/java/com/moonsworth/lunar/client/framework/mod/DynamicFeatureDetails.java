package com.moonsworth.lunar.client.framework.mod;

import com.moonsworth.lunar.client.framework.mod.ModCategory;
import java.util.Set;
import java.util.function.Supplier;
import org.jetbrains.annotations.Nullable;

public class DynamicFeatureDetails extends FeatureDetails {
   @Nullable
   private final Supplier<String> field7;
   @Nullable
   private final Supplier<String> field8;

   public DynamicFeatureDetails(
      String text,
      Set<ModCategory> set,
      Set<String> set2,
      Set<String> set3,
      boolean flag,
      boolean flag2,
      @Nullable Supplier<String> supplier7,
      @Nullable Supplier<String> supplier8
   ) {
      super(text, set, set2, set3, flag, flag2);
      this.field7 = supplier7;
      this.field8 = supplier8;
   }

   @Override
   public String getName() {
      return this.field7 != null ? this.field7.get() : super.getName();
   }

   @Override
   public String getDescription() {
      return this.field8 != null ? this.field8.get() : super.getDescription();
   }
}
