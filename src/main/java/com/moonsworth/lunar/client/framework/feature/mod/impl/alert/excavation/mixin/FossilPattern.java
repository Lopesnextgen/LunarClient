package com.moonsworth.lunar.client.framework.feature.mod.impl.alert.excavation.mixin;

public enum FossilPattern {
   SPINE(new boolean[][]{{true, false, false}, {true, true, false}, {true, true, true}, {true, true, true}, {true, true, false}, {true, false, false}}),
   HELIX(
      new boolean[][]{{true, true, true, true}, {false, false, false, true}, {true, true, false, true}, {true, false, false, true}, {true, true, true, true}}
   ),
   FOOTPRINT(
      new boolean[][]{
         {true, false, true, false, true},
         {true, false, true, false, true},
         {false, true, true, true, false},
         {false, true, true, true, false},
         {false, false, true, false, false}
      }
   ),
   WEBBED(
      new boolean[][]{
         {false, false, false, true, false, false, false},
         {true, false, false, true, false, false, true},
         {false, true, false, true, false, true, false},
         {false, false, true, true, true, false, false}
      }
   ),
   CLAW(
      new boolean[][]{
         {false, true, false, true, false, false},
         {true, false, true, false, true, false},
         {false, true, false, true, true, false},
         {false, false, true, true, true, true},
         {false, false, false, false, true, false}
      }
   ),
   TUSK(
      new boolean[][]{
         {false, false, true, false, false},
         {false, true, false, true, false},
         {true, false, false, false, false},
         {false, true, false, false, false},
         {false, false, true, true, true}
      }
   ),
   CLUBBED(
      new boolean[][]{
         {false, true, true, true, true, false, false, false},
         {true, false, false, false, false, true, false, false},
         {false, true, false, false, false, false, true, true},
         {false, false, false, false, false, false, true, true}
      }
   ),
   UGLY(
      new boolean[][]{
         {false, true, false, false},
         {true, true, true, false},
         {true, true, true, true},
         {true, true, true, true},
         {true, true, true, false},
         {false, true, false, false}
      }
   );

   private final boolean[][] pattern;

   FossilPattern(boolean[][] items3) {
      this.pattern = items3;
   }

   public int getWidth() {
      return this.pattern.length;
   }

   public int getHeight() {
      return this.pattern[0].length;
   }

   public boolean matches(int index1, int index2) {
      return this.pattern[index1][index2];
   }
}
