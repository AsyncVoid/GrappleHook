package me.Async.GH;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class Main extends JavaPlugin implements Listener
{
	@Override
	public void onEnable()
	{
		Bukkit.getPluginManager().registerEvents(this, this);
	}
	
	@Override
	public void onDisable()
	{
		
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if(sender instanceof Player)
		{
			Player player = (Player)sender;
			ItemStack is = new ItemStack(Material.FISHING_ROD, 1);
			ItemMeta im = is.getItemMeta();
			im.setDisplayName("§rGrapple Hook");
			is.setItemMeta(im);
			player.getInventory().addItem(is);
		}
		return false;
	}
	
	@EventHandler
	public void onProjectileHit(ProjectileHitEvent ev)
	{
		if(ev.getEntity() instanceof FishHook)
		{
			FishHook fh = (FishHook)ev.getEntity();
			Player shooter = (Player)fh.getShooter();
			Location loc = fh.getLocation();
			Vector vector = loc.toVector().subtract(shooter.getLocation().toVector()).normalize();
			shooter.setVelocity(vector);
			fh.remove();
		}
	}
}
