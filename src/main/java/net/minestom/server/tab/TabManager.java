package net.minestom.server.tab;

import net.minestom.server.MinecraftServer;
import net.minestom.server.network.packet.server.play.PlayerInfoPacket;
import net.minestom.server.utils.time.TimeUnit;

import java.util.ArrayList;
import java.util.List;

public class TabManager {

    private List<Tab> tabs;

    public TabManager() {
        tabs = new ArrayList<>();
    }

    public List<Tab> getTabs() {
        return tabs;
    }

    // TODO: Call every couple of ticks
    public void updateTab() {
        tabs.forEach(tab -> {

        });
    }

    public void removeTab(Tab tab) {
        tabs.remove(tab);
        PlayerInfoPacket packet = new PlayerInfoPacket(PlayerInfoPacket.Action.REMOVE_PLAYER);

        packet.playerInfos.add(new PlayerInfoPacket.RemovePlayer(tab.getUuid()));
        MinecraftServer.getConnectionManager().getOnlinePlayers().forEach((p)->{
            MinecraftServer.getSchedulerManager().buildTask(() -> p.getPlayerConnection().sendPacket(packet)).delay(20, TimeUnit.TICK).schedule();
        });
    }

    public void addTab(Tab tab) {
        tabs.add(tab);
    }
}
