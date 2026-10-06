package com.moonsworth.lunar.altmanager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.multiplayer.GuiConnecting;
import org.lwjgl.input.Keyboard;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

/**
 * Account manager UI.
 *
 * Cracked accounts just need a username; premium accounts can be added from a
 * Microsoft refresh token (client auto-detected), from an existing Minecraft
 * access token, or through the device-code login. Selecting an account then
 * "Login" or "Join server" applies the session.
 */
public class GuiAltManager extends GuiScreen {
    private final GuiScreen parent;
    private final AltStore store;
    private AltList list;
    private GuiTextField usernameField;
    private GuiTextField tokenField;
    private GuiTextField serverField;
    private String status = "";

    public GuiAltManager(GuiScreen parent, AltStore store) {
        this.parent = parent;
        this.store = store;
    }

    @Override
    public void initGui() {
        this.buttonList.clear();
        int left = this.width / 2 - 160;
        int rightA = this.width / 2 + 46;
        int rightB = this.width / 2 + 160;

        this.usernameField = new GuiTextField(1, this.fontRendererObj, left, 34, 200, 20);
        this.usernameField.setMaxStringLength(16);
        this.usernameField.setFocused(true);
        AltAccount selected = this.store.getSelected();
        if (selected != null) {
            this.usernameField.setText(selected.getUsername());
        }

        this.tokenField = new GuiTextField(2, this.fontRendererObj, left, 58, 200, 20);
        this.tokenField.setMaxStringLength(8192);

        this.serverField = new GuiTextField(3, this.fontRendererObj, left, 82, 200, 20);
        this.serverField.setMaxStringLength(255);
        this.serverField.setText("localhost:25565");

        this.buttonList.add(new GuiButton(10, rightA, 34, 110, 20, "Add cracked"));
        this.buttonList.add(new GuiButton(11, rightB, 34, 110, 20, "Random"));
        this.buttonList.add(new GuiButton(12, rightA, 58, 110, 20, "Refresh token"));
        this.buttonList.add(new GuiButton(13, rightB, 58, 110, 20, "Access token"));
        this.buttonList.add(new GuiButton(14, rightA, 82, 110, 20, "Join server"));
        this.buttonList.add(new GuiButton(15, rightB, 82, 110, 20, "Device login"));

        int bottom = this.width / 2 - 157;
        this.buttonList.add(new GuiButton(0, bottom, this.height - 28, 74, 20, "Login"));
        this.buttonList.add(new GuiButton(1, bottom + 80, this.height - 28, 74, 20, "Delete"));
        this.buttonList.add(new GuiButton(2, bottom + 160, this.height - 28, 74, 20, "Copy"));
        this.buttonList.add(new GuiButton(3, bottom + 240, this.height - 28, 74, 20, "Done"));

        this.list = new AltList(this.mc, this.width, this.height, 104, this.height - 56, 22);
    }

    /** Called by the device-login screen after a successful login. */
    public void onExternalLogin(AltAccount account) {
        this.status = "Logged in as " + account.getUsername() + " (premium)";
        if (this.list != null) {
            this.list.refresh();
        }
    }

    @Override
    public void updateScreen() {
        this.usernameField.updateCursorCounter();
        this.tokenField.updateCursorCounter();
        this.serverField.updateCursorCounter();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, "Alt Manager", this.width / 2, 8, 0xFFFFFF);
        AltAccount selected = this.list == null ? null : this.list.getSelected();
        String subtitle = this.store.getAccounts().size() + " account(s)"
                + (selected == null ? "" : "  \u00a77selected: \u00a7f" + selected.getUsername());
        this.drawCenteredString(this.fontRendererObj, subtitle, this.width / 2, 20, 0xAAAAAA);

        int left = this.width / 2 - 160;
        this.drawString(this.fontRendererObj, "Username (cracked / premium name)", left, 24, 0x808080);
        this.drawString(this.fontRendererObj, "Refresh token or Minecraft access token", left, 48, 0x808080);
        this.drawString(this.fontRendererObj, "Server address for direct join", left, 72, 0x808080);

        this.list.drawScreen(mouseX, mouseY, partialTicks);
        super.drawScreen(mouseX, mouseY, partialTicks);

        this.usernameField.drawTextBox();
        this.tokenField.drawTextBox();
        this.serverField.drawTextBox();

        if (!this.status.isEmpty()) {
            this.drawCenteredString(this.fontRendererObj, this.status, this.width / 2, this.height - 42, 0x55FF55);
        }
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (keyCode == Keyboard.KEY_ESCAPE) {
            this.mc.displayGuiScreen(this.parent);
            return;
        }
        if (keyCode == Keyboard.KEY_TAB) {
            if (this.usernameField.isFocused()) {
                this.usernameField.setFocused(false);
                this.tokenField.setFocused(true);
            } else if (this.tokenField.isFocused()) {
                this.tokenField.setFocused(false);
                this.serverField.setFocused(true);
            } else {
                this.serverField.setFocused(false);
                this.usernameField.setFocused(true);
            }
            return;
        }
        if (keyCode == Keyboard.KEY_RETURN) {
            if (this.usernameField.isFocused()) {
                this.addCracked();
            } else if (this.tokenField.isFocused()) {
                this.refreshLogin();
            } else if (this.serverField.isFocused()) {
                this.joinServer();
            }
            return;
        }
        this.usernameField.textboxKeyTyped(typedChar, keyCode);
        this.tokenField.textboxKeyTyped(typedChar, keyCode);
        this.serverField.textboxKeyTyped(typedChar, keyCode);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        this.usernameField.mouseClicked(mouseX, mouseY, mouseButton);
        this.tokenField.mouseClicked(mouseX, mouseY, mouseButton);
        this.serverField.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch (button.id) {
            case 10 -> this.addCracked();
            case 11 -> this.usernameField.setText(this.store.randomName());
            case 12 -> this.refreshLogin();
            case 13 -> this.accessTokenLogin();
            case 14 -> this.joinServer();
            case 15 -> this.mc.displayGuiScreen(new GuiDeviceLogin(this, this.store));
            case 0 -> this.useSelected();
            case 1 -> this.deleteSelected();
            case 2 -> {
                AltAccount selected = this.list.getSelected();
                if (selected != null) {
                    GuiScreen.setClipboardString(selected.getUsername());
                    this.status = "Copied " + selected.getUsername();
                }
            }
            case 3 -> this.mc.displayGuiScreen(this.parent);
            default -> {
            }
        }
    }

    private void addCracked() {
        String username = this.usernameField.getText().trim();
        if (!AltStore.isValidName(username)) {
            this.status = "Names must be 3-16 letters, digits or _";
            return;
        }
        AltAccount account = this.store.addCracked(username);
        this.store.select(account);
        this.list.refresh();
        this.list.setSelected(this.store.getAccounts().indexOf(account));
        this.status = "Added cracked account " + account.getUsername();
    }

    private void useSelected() {
        AltAccount selected = this.list.getSelected();
        if (selected == null) {
            this.status = "Select an account first";
            return;
        }
        SessionSwitcher.apply(selected);
        this.store.select(selected);
        this.status = "Now playing as " + selected.getUsername()
                + (selected.isCracked() ? " (offline session)" : " (premium session)");
    }

    private void deleteSelected() {
        AltAccount selected = this.list.getSelected();
        if (selected != null) {
            this.store.remove(selected);
            this.list.refresh();
            this.status = "Removed " + selected.getUsername();
        }
    }

    private void refreshLogin() {
        String token = this.tokenField.getText().trim();
        if (token.isEmpty()) {
            this.status = "Paste a Microsoft refresh token first";
            return;
        }
        this.status = "Refreshing premium session...";
        this.runAsync(() -> MicrosoftAuth.login(token), account -> {
            AltAccount saved = this.store.addPremium(account.getUsername(), account.getUuid(),
                    account.getAccessToken(), account.getRefreshToken());
            SessionSwitcher.apply(saved);
            this.store.select(saved);
            this.list.refresh();
            this.status = "Logged in as " + saved.getUsername() + " (premium)";
        });
    }

    private void accessTokenLogin() {
        String token = this.tokenField.getText().trim();
        if (token.isEmpty()) {
            this.status = "Paste a Minecraft access token first";
            return;
        }
        this.status = "Validating access token...";
        this.runAsync(() -> MicrosoftAuth.tokenLogin(token), account -> {
            AltAccount saved = this.store.addPremium(account.getUsername(), account.getUuid(),
                    account.getAccessToken(), "");
            SessionSwitcher.apply(saved);
            this.store.select(saved);
            this.list.refresh();
            this.status = "Logged in as " + saved.getUsername() + " (token)";
        });
    }

    private void joinServer() {
        AltAccount selected = this.list.getSelected();
        if (selected != null) {
            SessionSwitcher.apply(selected);
            this.store.select(selected);
        }
        String address = this.serverField.getText().trim();
        if (address.isEmpty()) {
            this.status = "Enter a server address, e.g. mc.example.net";
            return;
        }
        String host = address;
        int port = 25565;
        int colon = address.lastIndexOf(':');
        if (colon > 0 && colon < address.length() - 1) {
            try {
                port = Integer.parseInt(address.substring(colon + 1));
                host = address.substring(0, colon);
            } catch (NumberFormatException ignored) {
            }
        }
        this.mc.displayGuiScreen(new GuiConnecting(this, this.mc, host, port));
    }

    private void runAsync(Callable<AltAccount> task, Consumer<AltAccount> onSuccess) {
        Thread thread = new Thread(() -> {
            try {
                AltAccount result = task.call();
                Minecraft.getMinecraft().addScheduledTask(() -> {
                    try {
                        onSuccess.accept(result);
                    } catch (Exception exception) {
                        this.status = "Error: " + exception.getMessage();
                    }
                });
            } catch (Exception exception) {
                Minecraft.getMinecraft().addScheduledTask(
                        () -> this.status = "Login failed: " + exception.getMessage());
            }
        }, "AltManager-Auth");
        thread.setDaemon(true);
        thread.start();
    }

    /** Scrollable list of accounts. */
    private final class AltList extends GuiSlot {
        private List<AltAccount> accounts = GuiAltManager.this.store.getAccounts();
        private int selectedIndex = -1;

        AltList(Minecraft mc, int width, int height, int top, int bottom, int slotHeight) {
            super(mc, width, height, top, bottom, slotHeight);
            AltAccount selected = GuiAltManager.this.store.getSelected();
            if (selected != null) {
                this.selectedIndex = this.accounts.indexOf(selected);
            }
        }

        void refresh() {
            this.accounts = GuiAltManager.this.store.getAccounts();
            if (this.selectedIndex >= this.accounts.size()) {
                this.selectedIndex = this.accounts.size() - 1;
            }
        }

        AltAccount getSelected() {
            if (this.selectedIndex < 0 || this.selectedIndex >= this.accounts.size()) {
                return null;
            }
            return this.accounts.get(this.selectedIndex);
        }

        void setSelected(int index) {
            this.selectedIndex = index;
        }

        @Override
        protected int getSize() {
            return this.accounts.size();
        }

        @Override
        protected void elementClicked(int slotIndex, boolean isDoubleClick, int mouseX, int mouseY) {
            this.selectedIndex = slotIndex;
            if (isDoubleClick) {
                GuiAltManager.this.useSelected();
            }
        }

        @Override
        protected boolean isSelected(int slotIndex) {
            return slotIndex == this.selectedIndex;
        }

        @Override
        protected void drawBackground() {
            GuiAltManager.this.drawDefaultBackground();
        }

        @Override
        protected void drawSlot(int entryID, int x, int y, int slotHeight, int mouseXIn, int mouseYIn) {
            AltAccount account = this.accounts.get(entryID);
            String type = account.isCracked() ? "\u00a77cracked" : "\u00a7bpremium";
            String label = "\u00a7f" + account.getUsername() + "  " + type;
            GuiAltManager.this.drawString(GuiAltManager.this.fontRendererObj, label, x + 4, y + 4, 0xFFFFFF);
        }
    }
}
