package com.moonsworth.lunar.client.framework.mod;

public enum ModLoadState {
   CONSTRUCTOR,
   INITIALIZED,
   OPTIONS_INITIALIZED,
   CHILDREN_INITIALIZED,
   LOADING_CONFIG,
   LOADED_CONFIG,
   COMPLETE;

   ModLoadState() {
   }
}
