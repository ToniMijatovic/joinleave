package tonioostblok.joinleave;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;
import sun.rmi.rmic.Main;
import tonioostblok.joinleave.events.PlayerJoin;

public final class Joinleave extends JavaPlugin {
    FileConfiguration config = getConfig();
    public PlayerJoin playerJoin = new PlayerJoin(this);
    private String defaultJoin = "Welcome to the server";
    private String defaultLeave = "Bye!";
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(playerJoin, this);
        this.saveDefaultConfig();
        config.addDefault("join_message", this.defaultJoin);
        config.addDefault("leave_message", this.defaultLeave);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    /**
     *
     * @param args
     * @return String
     */
    public String concatString(String[] args){
        boolean firstTime = true;
        StringBuilder sb = new StringBuilder(50);
        for (String word : args) {
            if (firstTime) {
                firstTime = false;
            } else {
                sb.append(' ');
            }
            sb.append(word);
        }
        return sb.toString();
    }
    public void updateConfig(String key, String val){
        try{
            this.getConfig().set(key,val);
            this.saveConfig();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        switch(label){
            case "join":
                if(sender instanceof Player){
                    Player player = (Player) sender;
                    if(player.hasPermission("join.use")){
                        this.updateConfig("join_message", this.concatString(args));
                        player.sendMessage(ChatColor.GREEN +"Successfully updated the player join text.");
                    }else{
                        player.sendMessage(ChatColor.RED + "You do not have the correct permissions to use this command.");
                    }
                }else{
                    this.updateConfig("join_message", this.concatString(args));
                    sender.sendMessage("Successfully updated the player join text.");
                }
                break;
            case "leave":
                if(sender instanceof Player){
                    Player player = (Player) sender;
                    if(player.hasPermission("leave.use")){
                        this.updateConfig("leave_message", this.concatString(args));
                        player.sendMessage(ChatColor.GREEN + "Successfully updated the player leave text.");
                    }else{
                        player.sendMessage(ChatColor.RED + "You do not have the correct permissions to use this command.");
                    }
                }else{
                    this.updateConfig("leave_message", this.concatString(args));
                    sender.sendMessage("Successfully updated the player leave text.");
                }
                break;
        }
        return true;
    }
}
