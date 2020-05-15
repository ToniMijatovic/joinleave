package tonioostblok.joinleave.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import tonioostblok.joinleave.Joinleave;

public class PlayerJoin implements Listener {
    private String message;
    private Joinleave plugin;

    /**
     *
     * @param plugin
     */
    public PlayerJoin(Joinleave plugin){
        this.plugin = plugin;
    }

    /**
     *
     * @return String
     */
    public String getJoinMessage(){
        this.plugin.reloadConfig();
        this.message = plugin.getConfig().getString("join_message");
        return this.message;
    }

    /**
     *
     * @return String
     */
    public String getLeaveMessage(){
        this.plugin.reloadConfig();
        this.message = plugin.getConfig().getString("leave_message");
        return this.message;
    }

    /**
     *
     * @param e
     */
    @EventHandler
    void onPlayerJoin(PlayerJoinEvent e){
        e.setJoinMessage(this.getJoinMessage());
    }

    /**
     *
     * @param e
     */
    @EventHandler
    void onPlayerLeave(PlayerQuitEvent e){
        e.setQuitMessage(this.getLeaveMessage());
    }
}
