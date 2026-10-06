package com.moonsworth.lunar.bridge;

import java.util.Locale;
import lombok.Generated;
import org.intellij.lang.annotations.Subst;

public enum KeyCode {
   KEY_NONE(0),
   KEY_0(48),
   KEY_1(49),
   KEY_2(50),
   KEY_3(51),
   KEY_4(52),
   KEY_5(53),
   KEY_6(54),
   KEY_7(55),
   KEY_8(56),
   KEY_9(57),
   KEY_A(65),
   KEY_B(66),
   KEY_C(67),
   KEY_D(68),
   KEY_E(69),
   KEY_F(70),
   KEY_G(71),
   KEY_H(72),
   KEY_I(73),
   KEY_J(74),
   KEY_K(75),
   KEY_L(76),
   KEY_M(77),
   KEY_N(78),
   KEY_O(79),
   KEY_P(80),
   KEY_Q(81),
   KEY_R(82),
   KEY_S(83),
   KEY_T(84),
   KEY_U(85),
   KEY_V(86),
   KEY_W(87),
   KEY_X(88),
   KEY_Y(89),
   KEY_Z(90),
   KEY_RSHIFT(16),
   KEY_LSHIFT(16),
   KEY_FUNCTION(63),
   KEY_LCONTROL(17),
   KEY_RCONTROL(17),
   KEY_LCOMMAND(55),
   KEY_RCOMMAND(55),
   KEY_LWINDOWS(91),
   KEY_RWINDOWS(92),
   KEY_CLEAR(218),
   KEY_LMENU(18),
   KEY_RMENU(18),
   KEY_RETURN(13),
   KEY_ESCAPE(27),
   KEY_GRAVE(192),
   KEY_TAB(9),
   KEY_UP(38),
   KEY_BACK(8),
   KEY_LEFT(37),
   KEY_RIGHT(39),
   KEY_HOME(36),
   KEY_PGUP(33),
   KEY_PGDOWN(34),
   KEY_PG(93),
   KEY_END(35),
   KEY_DELETE(46),
   KEY_F1(112),
   KEY_F2(113),
   KEY_F3(114),
   KEY_F4(115),
   KEY_F5(116),
   KEY_F6(117),
   KEY_F7(118),
   KEY_F8(119),
   KEY_F9(120),
   KEY_F10(121),
   KEY_F11(122),
   KEY_F12(123),
   KEY_DOWN(40),
   KEY_SPACE(32),
   KEY_MOUSE1(0),
   KEY_MOUSE2(0),
   KEY_MOUSE3(0),
   KEY_MOUSE4(0),
   KEY_MOUSE5(0),
   KEY_MOUSE6(0),
   KEY_MOUSE7(0),
   KEY_MOUSE8(0),
   KEY_MOUSE9(0),
   KEY_MOUSE10(0),
   KEY_MOUSE11(0),
   KEY_MOUSE12(0),
   KEY_MOUSE13(0),
   KEY_MOUSE14(0),
   KEY_MOUSE15(0),
   KEY_MOUSE16(0),
   KEY_MINUS(189),
   KEY_EQUALS(187),
   KEY_LBRACKET(219),
   KEY_RBRACKET(221),
   KEY_SEMICOLON(186),
   KEY_APOSTROPHE(222),
   KEY_BACKSLASH(220),
   KEY_COMMA(188),
   KEY_PERIOD(190),
   KEY_SLASH(191),
   KEY_MULTIPLY(106),
   KEY_CAPITAL(20),
   KEY_NUMLOCK(144),
   KEY_SCROLL(145),
   KEY_PRINTSC(44),
   KEY_NUMPAD7(103),
   KEY_NUMPAD8(104),
   KEY_NUMPAD9(105),
   KEY_SUBTRACT(109),
   KEY_NUMPAD4(100),
   KEY_NUMPAD5(101),
   KEY_NUMPAD6(102),
   KEY_ADD(107),
   KEY_NUMPAD1(97),
   KEY_NUMPAD2(98),
   KEY_NUMPAD3(99),
   KEY_NUMPAD0(96),
   KEY_DECIMAL(110),
   KEY_F13(124),
   KEY_F14(125),
   KEY_F15(126),
   KEY_F16(127),
   KEY_F17(128),
   KEY_F18(129),
   KEY_F19(130),
   KEY_NUMPADEQUALS(187),
   KEY_NUMPADENTER(13),
   KEY_DIVIDE(111),
   KEY_PAUSE(19),
   KEY_INSERT(45);

   final int vk;

   public static KeyCode fromMouse(int var0) {
      return valueOf("KEY_MOUSE" + (var0 + 1));
   }

   @Subst("KEY_NAME")
   public String getName() {
      String var1;
      if (!this.name().contains("_")) {
         var1 = this.name();
      } else {
         var1 = this.name().split("_")[1];
      }

      var1 = var1.replace("MENU", "ALT");
      if (var1.contains("META")) {
         if (System.getProperty("os.name").toLowerCase(Locale.ROOT).contains("mac")) {
            var1 = var1.replace("META", "COMMAND");
         } else {
            var1 = var1.replace("META", "WINDOWS");
         }
      }

      return var1.toUpperCase(Locale.ROOT);
   }

   public String getShortName(String var1) {
      if (this.isMouse()) {
         return "M" + this.getName().substring("MOUSE".length());
      }

      return switch (this) {
         case KEY_LSHIFT -> "LSHFT";
         case KEY_RSHIFT -> "RSHFT";
         case KEY_LCONTROL -> "LCTRL";
         case KEY_RCONTROL -> "RCTRL";
         case KEY_LMENU -> "LALT";
         case KEY_RMENU -> "RALT";
         case KEY_LWINDOWS -> "LWIN";
         case KEY_RWINDOWS -> "RWIN";
         case KEY_LCOMMAND -> "LCMD";
         case KEY_RCOMMAND -> "RCMD";
         case KEY_FUNCTION -> "FN";
         case KEY_RETURN -> "ENT";
         case KEY_ESCAPE -> "ESC";
         case KEY_BACK -> "BKSP";
         case KEY_SPACE -> "SPC";
         case KEY_UP -> "UP";
         case KEY_DOWN -> "DOWN";
         case KEY_LEFT -> "LEFT";
         case KEY_RIGHT -> "RIGHT";
         case KEY_PGUP -> "PGUP";
         case KEY_PGDOWN -> "PGDN";
         case KEY_DELETE -> "DEL";
         case KEY_INSERT -> "INS";
         case KEY_PRINTSC -> "PRTSC";
         case KEY_CAPITAL -> "CAPS";
         case KEY_NUMLOCK -> "NUMLK";
         case KEY_SCROLL -> "SCRLK";
         case KEY_CLEAR -> "CLEAR";
         case KEY_GRAVE -> "`";
         case KEY_MINUS -> "-";
         case KEY_EQUALS -> "=";
         case KEY_LBRACKET -> "[";
         case KEY_RBRACKET -> "]";
         case KEY_SEMICOLON -> ";";
         case KEY_APOSTROPHE -> "'";
         case KEY_BACKSLASH -> "\\";
         case KEY_COMMA -> ",";
         case KEY_PERIOD -> ".";
         case KEY_SLASH -> "/";
         case KEY_NUMPAD0 -> "N0";
         case KEY_NUMPAD1 -> "N1";
         case KEY_NUMPAD2 -> "N2";
         case KEY_NUMPAD3 -> "N3";
         case KEY_NUMPAD4 -> "N4";
         case KEY_NUMPAD5 -> "N5";
         case KEY_NUMPAD6 -> "N6";
         case KEY_NUMPAD7 -> "N7";
         case KEY_NUMPAD8 -> "N8";
         case KEY_NUMPAD9 -> "N9";
         case KEY_NUMPADEQUALS -> "N=";
         case KEY_NUMPADENTER -> "NENT";
         case KEY_MULTIPLY -> "N*";
         case KEY_SUBTRACT -> "N-";
         case KEY_ADD -> "N+";
         case KEY_DECIMAL -> "N.";
         case KEY_DIVIDE -> "N/";
         default -> var1;
      };
   }

   public boolean isMouse() {
      return this.name().startsWith("KEY_MOUSE");
   }

   public static KeyCode fromMouseButton(int var0) {
      return switch (var0) {
         case 0 -> KEY_MOUSE1;
         case 1 -> KEY_MOUSE2;
         case 2 -> KEY_MOUSE3;
         case 3 -> KEY_MOUSE4;
         case 4 -> KEY_MOUSE5;
         case 5 -> KEY_MOUSE6;
         case 6 -> KEY_MOUSE7;
         case 7 -> KEY_MOUSE8;
         case 8 -> KEY_MOUSE9;
         case 9 -> KEY_MOUSE10;
         case 10 -> KEY_MOUSE11;
         case 11 -> KEY_MOUSE12;
         case 12 -> KEY_MOUSE13;
         case 13 -> KEY_MOUSE14;
         case 14 -> KEY_MOUSE15;
         case 15 -> KEY_MOUSE16;
         default -> null;
      };
   }

   @Generated
   public int getVk() {
      return this.vk;
   }

   @Generated
   KeyCode(int value) {
      this.vk = value;
   }
}
