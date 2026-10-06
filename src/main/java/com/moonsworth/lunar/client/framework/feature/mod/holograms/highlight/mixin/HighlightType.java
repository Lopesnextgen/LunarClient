package com.moonsworth.lunar.client.framework.feature.mod.holograms.highlight.mixin;

import java.util.regex.Pattern;
import lombok.Generated;

public enum HighlightType {
   COMMISSIONS("^Commissions$"),
   TERMINAL_MATCH_COLOR("^Change all to same color!$"),
   TERMINAL_CLICK_IN_ORDER("^Click in order!$"),
   TERMINAL_SELECT_COLOR("^Select all the"),
   TERMINAL_FIRST_LETTER("^What starts with:"),
   TERMINAL_MELODY("^Click the button on time!$"),
   TERMINAL_CORRECT_PANES("^Correct all the panes!$"),
   EXPERIMENT_CHRONOMATRON("^Chronomatron \\("),
   EXPERIMENT_SUPERPAIR("^Superpairs \\("),
   EXPERIMENT_ULTRASEQUENCER("^Ultrasequencer \\("),
   CHOCOLATE_FACTORY("^Chocolate Factory$"),
   CHOCOLATE_SHOP("^Chocolate Shop$"),
   CHOCOLATE_FACTORY_MILESTONES("^Chocolate Factory Milestones$"),
   CHOCOLATE_SHOP_MILESTONES("^Chocolate Shop Milestones$"),
   HOPPITTY_COLLECTION("Hoppity's Collection$"),
   RABBIT_HITMAN("^Rabbit Hitman$"),
   CHOOSE_A_FACTION("^Choose a Faction$"),
   CHOOSE_SPECIFIC_FACTION("^Choose (City|Mountain|Country|Beach) Rabbits\\?$"),
   FACTION_RABBITS("^(City|Mountain|Country|Beach) Rabbits$"),
   LEAVE_FACTION("^Leave Faction\\?$"),
   CHOCOBITS("^Chocobits$"),
   HOTM("^Heart of the Mountain$"),
   HOTF("^Heart of the Forest$"),
   RESET_HOTM("^Reset Heart of the Mountain"),
   RESET_HOTF("^Reset Heart of the Forest"),
   SPIRIT_LEAP("^Spirit Leap$"),
   ATTRIBUTE_MENU("^Attribute Menu$"),
   PARTY_FINDER("^Party Finder$"),
   CATACOMBS_GATE("^Catacombs Gate$"),
   CROESUS("^(\\(\\S+\\) )?Croesus$"),
   VESUVIUS("^Vesuvius$"),
   DNA_SOLVER("^(?!Ultimate).+ DNA$"),
   COMMUNITY_SHOP("^Community Shop$"),
   BITS_SHOP("^Bits Shop - "),
   SKYMART_FARMING_ESSENTIALS("SkyMart Farming Essentials$"),
   SKYMART_FARMING_TOOLS("SkyMart Farming Tools$"),
   SKYMART_BARN_SKINS("SkyMart Barn Skins$"),
   SKYMART_GREENHOUSE_SKINS("SkyMart Greenhouse Skins$"),
   SKYMART_PESTS("SkyMart Pests$"),
   WARDROBE("Armor Sets$"),
   EQUIPMENT("Equipment Sets$"),
   LOADOUTS("Loadouts$"),
   WARDROBE_OLD("^Wardrobe"),
   COLLECTIONS(" Collections$"),
   COLLECTION(" Collection$"),
   YOUR_SKILLS("^Your Skills$"),
   BAZAAR_ORDERS(" Bazaar Orders$"),
   SALVAGE("^Salvage Items$"),
   DRACONIC_SACRIFICE("^Draconic Sacrifice$"),
   FARMING_TOOLKIT("^Farming Toolkit$"),
   FISHING_BAG("^Fishing Bag$"),
   OFFER_PETS("^Offer Pets$"),
   RAFFLE_TASKS("^Raffle Tasks$"),
   AUCTION_BROWSER("^Auction Browser$"),
   AUCTION_SEARCH("^Auctions: \""),
   YOUR_BIDS("^Your Bids$"),
   CREATE_AUCTION("^Create Auction$"),
   CREATE_BIN_AUCTION("^Create Bin Auction$"),
   FORAGING_BEACON("^Tune Frequency$"),
   UPGRADE_SIGNAL_STRENGTH("^Upgrade Signal Strength$");

   private final Pattern pattern;

   HighlightType(String text) {
      this.pattern = Pattern.compile(text);
   }

   public boolean isChocolateFactoryGui() {
      return this == CHOCOLATE_FACTORY
         || this == CHOCOLATE_SHOP
         || this == CHOCOLATE_FACTORY_MILESTONES
         || this == CHOCOLATE_SHOP_MILESTONES
         || this == HOPPITTY_COLLECTION
         || this == RABBIT_HITMAN
         || this == CHOOSE_A_FACTION
         || this == CHOOSE_SPECIFIC_FACTION
         || this == FACTION_RABBITS
         || this == LEAVE_FACTION
         || this == CHOCOBITS;
   }

   public boolean isTerminalGui() {
      return this == TERMINAL_MATCH_COLOR
         || this == TERMINAL_CLICK_IN_ORDER
         || this == TERMINAL_SELECT_COLOR
         || this == TERMINAL_FIRST_LETTER
         || this == TERMINAL_MELODY
         || this == TERMINAL_CORRECT_PANES;
   }

   public boolean isChestClaimGui() {
      return this == CROESUS || this == VESUVIUS;
   }

   public boolean isBitsShopGui() {
      return this == COMMUNITY_SHOP || this == BITS_SHOP;
   }

   public boolean isCollectionGui() {
      return this == COLLECTIONS || this == COLLECTION;
   }

   public boolean isAuctionGui() {
      return this == AUCTION_BROWSER || this == AUCTION_SEARCH || this == YOUR_BIDS || this == CREATE_AUCTION || this == CREATE_BIN_AUCTION;
   }

   @Generated
   public Pattern getPattern() {
      return this.pattern;
   }
}
