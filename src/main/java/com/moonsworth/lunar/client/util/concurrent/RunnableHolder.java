package com.moonsworth.lunar.client.util.concurrent;

public final class RunnableHolder {
   public static volatile Runnable onOpen = () -> {};
   public static volatile Runnable onClose = () -> {};

   private RunnableHolder() {
   }
}
