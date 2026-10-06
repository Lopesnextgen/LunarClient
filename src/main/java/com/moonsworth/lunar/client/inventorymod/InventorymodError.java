package com.moonsworth.lunar.client.inventorymod;

public final class InventorymodError extends Throwable {
   private final String field1;

   public InventorymodError(Throwable var1, String text, Throwable throwable) {
      super(text == null ? var1.getClass().getName() : var1.getClass().getName() + ": " + text, throwable);
      this.field1 = var1.getClass().getName();
      this.setStackTrace(var1.getStackTrace());
   }

   @Override
   public String toString() {
      String var1 = this.getLocalizedMessage();
      return var1 != null ? var1 : this.field1;
   }
}
