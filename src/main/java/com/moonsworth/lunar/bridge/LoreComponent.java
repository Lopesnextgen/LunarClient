package com.moonsworth.lunar.bridge;

import java.util.List;

@FunctionalInterface
public interface LoreComponent {
   List<ChatComponentStyleBridge> bridge$getLines();
}
