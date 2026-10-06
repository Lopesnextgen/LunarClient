package com.moonsworth.lunar.genesis;

public enum MixinHelper23$Type {
   NEW,
   STARTING,
   RUNNING,
   STOPPING,
   TERMINATED,
   FAILED;

   MixinHelper23$Type() {
   }

   abstract boolean isTerminal();
}
