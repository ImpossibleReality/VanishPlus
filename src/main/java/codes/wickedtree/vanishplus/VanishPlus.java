package codes.wickedtree.vanishplus;

import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.network.MessageType;
import net.minecraft.network.packet.s2c.play.EntitiesDestroyS2CPacket;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;
import net.minecraft.network.packet.s2c.play.PlayerListS2CPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;

import java.util.HashSet;

import codes.wickedtree.vanishplus.commands.OverwrittenListCommand;
import codes.wickedtree.vanishplus.commands.OverwrittenMsgCommand;
import codes.wickedtree.vanishplus.commands.VanishCommand;
import codes.wickedtree.vanishplus.data.Settings;
import codes.wickedtree.vanishplus.data.VanishedPlayer;

import static net.minecraft.util.Util.NIL_UUID;

public enum VanishPlus {
    INSTANCE;

    private boolean active = false;

    private final HashSet<VanishedPlayer> vanishedPlayers = new HashSet<>();

    private MinecraftServer server = null;

    private int amountOfVanishedPlayersOnline = 0;

    private Settings settings;

    public void init() {
        settings = Settings.loadSettings();
        registerCommands();
    }

    private void registerCommands() {
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
                    VanishCommand.register(dispatcher);
                    if (settings.overwriteListCommand()) {
                        OverwrittenListCommand.register(dispatcher);
                    }
                    if (settings.overwriteMsgCommand()) {
                        OverwrittenMsgCommand.register(dispatcher);
                    }
                }
        );
    }

    public void onDisconnect(ServerPlayerEntity player) {
        setServer(player.getServer());

        if (vanishedPlayers.stream().anyMatch(vanishedPlayer -> vanishedPlayer.getUuid().equals(player.getUuid()))) {
            decreaseAmountOfOnlineVanishedPlayers();
        }
    }

    public void onPlayerConnect(ServerPlayerEntity player) {
        setServer(player.getServer());

        if (vanishedPlayers.stream().anyMatch(vanishedPlayer -> vanishedPlayer.getUuid().equals(player.getUuid()))) {
            vanishedPlayers.forEach(vanishedPlayer -> {
                if (vanishedPlayer.getUuid().equals(player.getUuid())) {
                    vanishedPlayer.setEntityId(player.getEntityId());

                    server.getPlayerManager().getPlayerList().forEach(playerEntity -> {
                        if (!vanishedPlayer.getUuid().equals(playerEntity.getUuid())) {
                            playerEntity.networkHandler.sendPacket(new EntitiesDestroyS2CPacket(vanishedPlayer.getEntityId()));
                        }
                    });
                }
            });

            increaseAmountOfOnlineVanishedPlayers();
        }
    }

    public boolean isVanished(ServerPlayerEntity player) {
        return isVanished(player.getEntityName());
    }

    public boolean isVanished(String name) {
        return VanishPlus.INSTANCE.getVanishedPlayers().stream().anyMatch(vanishedPlayer -> vanishedPlayer.getName().equals(name));
    }

    public int getFakePlayerCount() {
        return Math.max(server.getCurrentPlayerCount() - amountOfVanishedPlayersOnline, 0); //wrong
    }

    public void decreaseAmountOfOnlineVanishedPlayers() {
        amountOfVanishedPlayersOnline--;
    }

    public void increaseAmountOfOnlineVanishedPlayers() {
        amountOfVanishedPlayersOnline++;
    }

    public HashSet<VanishedPlayer> getVanishedPlayers() {
        return vanishedPlayers;
    }

    public MinecraftServer getServer() {
        return server;
    }

    public void setServer(MinecraftServer server) {
        if (this.server != null) return;
        this.server = server;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }
}
