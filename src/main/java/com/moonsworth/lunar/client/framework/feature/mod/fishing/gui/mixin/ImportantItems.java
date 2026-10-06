package com.moonsworth.lunar.client.framework.feature.mod.fishing.gui.mixin;

public class ImportantItems {
   private final ImportantItemCategory field1;
   private final ImportantItemCategory field2;

   public ImportantItems(ImportantItemCategory gui21, ImportantItemCategory gui22) {
      this.field1 = gui21;
      this.field2 = gui22;
   }

   public ImportantItemCategory method1() {
      return this.field1;
   }

   public ImportantItemCategory method2() {
      return this.field2;
   }
}
