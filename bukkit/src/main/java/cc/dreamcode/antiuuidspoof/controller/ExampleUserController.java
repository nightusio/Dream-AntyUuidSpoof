package cc.dreamcode.antiuuidspoof.controller;

import cc.dreamcode.antiuuidspoof.AntiUUIDSpoofPlugin;
import cc.dreamcode.antiuuidspoof.user.UserRepository;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.tasker.core.Tasker;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Example usage of user repository.
 * Register controller in plugin component system.
 */
public class ExampleUserController implements Listener {

    private @Inject
    AntiUUIDSpoofPlugin bukkitTemplatePlugin;
    private @Inject UserRepository userRepository;
    private @Inject Tasker tasker;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        final Player player = e.getPlayer();

        // 5 sync task (1 task = 1 tick)
        this.tasker.newChain()
                .async(() -> this.userRepository.findOrCreateByHumanEntity(player)) // async create user
                .acceptSync(user -> {
                    user.setName(player.getName()); // example setter (only for example)
                })
                .acceptAsync(user -> {
                    user.save(); // save after changes (async))
                })
                .acceptSync(user -> {
                    player.sendMessage("hi, " + user.getName()); // send message after save
                })
                .execute();
    }

}
