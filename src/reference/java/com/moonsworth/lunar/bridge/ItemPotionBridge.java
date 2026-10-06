package com.moonsworth.lunar.bridge;

import com.moonsworth.lunar.bridge.potion.PotionEffectBridge;
import java.util.Collection;

public interface ItemPotionBridge extends ItemBridge {
   Collection<PotionEffectBridge> bridge$getEffects(ItemStackBridge bridgeextension_41);
}
