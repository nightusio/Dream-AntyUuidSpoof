package cc.dreamcode.antiuuidspoof.controller;

import cc.dreamcode.platform.DreamLogger;
import eu.okaeri.injector.annotation.Inject;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

import static cc.dreamcode.antiuuidspoof.util.UUIDUtil.generateUUIDFromName;

public class PlayerJoinController implements Listener {

    @Inject
    private DreamLogger dreamLogger;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getName();
        UUID playerUUID = player.getUniqueId();

        if (!playerUUID.equals(generateUUIDFromName(playerName))) {
            player.kickPlayer("Błąd uwierzytelniania UUID. Prosimy o ponowne połączenie się z serwerem.");
            this.dreamLogger.warning("Gracz " + playerName + " próbował sfałszować UUID!");
        }
    }

}
