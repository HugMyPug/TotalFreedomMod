package me.totalfreedom.totalfreedommod.bridge;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import java.util.Map;
import me.totalfreedom.totalfreedommod.FreedomService;
import me.totalfreedom.totalfreedommod.TotalFreedomMod;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;

public class WorldGuardBridge extends FreedomService
{

    public WorldGuardBridge(TotalFreedomMod plugin)
    {
        super(plugin);
    }

    @Override
    protected void onStart()
    {
    }

    @Override
    protected void onStop()
    {
    }

    public RegionManager getRegionManager(World world)
    {
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        return container.get(BukkitAdapter.adapt(world));
    }

    public boolean wipeRegions(World world)
    {
        RegionManager regionManager = getRegionManager(world);
        if (regionManager != null)
        {
            Map<String, ProtectedRegion> regions = regionManager.getRegions();
            if (regions.isEmpty())
            {
                return false;
            }
            for (ProtectedRegion region : regions.values())
            {
                regionManager.removeRegion(region.getId());
            }
            return true;
        }
        return false;
    }

    public boolean isPluginEnabled()
    {
        Plugin plugin = server.getPluginManager().getPlugin("WorldGuard");
        return plugin != null && plugin.isEnabled();
    }
}
