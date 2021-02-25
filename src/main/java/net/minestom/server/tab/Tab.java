package net.minestom.server.tab;

import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;
import net.minestom.server.entity.PlayerSkin;

import java.util.UUID;

public class Tab {
    private UUID uuid;
    private String displayName;
    private boolean isSpecator;
    private Player player;
    private TabType type;

    // For Custom tab names
    private PlayerSkin skin;

    public Tab(UUID id, String name,boolean spectator) {
        type = TabType.CUSTOM;
        uuid = id;
        displayName = name;
        skin = null;
        isSpecator = spectator;
    }

    public Tab(UUID id, String name,boolean spectator,PlayerSkin pSkin) {
        type = TabType.CUSTOM;
        uuid = id;
        displayName = name;
        skin = pSkin;
        isSpecator = spectator;
    }

    public Tab(Player p) {
        player = p;
        type = TabType.PLAYER;
    }

    // Not sure if this is right (Will check later)
    public int getLatency() {
        return (type == TabType.PLAYER) ? player.getLatency() : 4;
    }

    public GameMode getDisplayType() {
        if(type == TabType.PLAYER)
            return player.getGameMode();
        return (isSpecator == true) ? GameMode.SPECTATOR : GameMode.SURVIVAL;
    }

    public TabType getInfoType() {
        return type;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setSkin(PlayerSkin skin) {
        this.skin = skin;
    }

    public UUID getUuid() {
        return uuid;
    }
}
