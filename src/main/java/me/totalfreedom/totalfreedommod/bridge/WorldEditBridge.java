package me.totalfreedom.totalfreedommod.bridge;

import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.bukkit.BukkitPlayer;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import me.totalfreedom.totalfreedommod.FreedomService;
import me.totalfreedom.totalfreedommod.TotalFreedomMod;
import me.totalfreedom.totalfreedommod.util.FLog;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class WorldEditBridge extends FreedomService
{

    private WorldEditPlugin worldedit = null;

    public WorldEditBridge(TotalFreedomMod plugin)
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

    public void undo(Player player, int count)
    {
        try
        {
            LocalSession session = getPlayerSession(player);
            if (session != null)
            {
                final com.sk89q.worldedit.entity.Player wePlayer = getBukkitPlayer(player);
                if (wePlayer != null)
                {
                    for (int i = 0; i < count; i++)
                    {
                        session.undo(session.getBlockBag(wePlayer), wePlayer);
                    }
                }
            }
        }
        catch (Exception ex)
        {
            FLog.severe(ex);
        }
    }

    public void setLimit(Player player, int limit)
    {
        try
        {
            final LocalSession session = getPlayerSession(player);
            if (session != null)
            {
                session.setBlockChangeLimit(limit);
            }
        }
        catch (Exception ex)
        {
            FLog.severe(ex);
        }
    }

    private WorldEditPlugin getWorldEditPlugin()
    {
        if (worldedit == null)
        {
            try
            {
                Plugin we = server.getPluginManager().getPlugin("WorldEdit");
                if (we instanceof WorldEditPlugin)
                {
                    worldedit = (WorldEditPlugin) we;
                }
            }
            catch (Exception ex)
            {
                FLog.severe(ex);
            }
        }

        return worldedit;
    }

    private LocalSession getPlayerSession(Player player)
    {
        final WorldEditPlugin wep = getWorldEditPlugin();
        if (wep == null)
        {
            return null;
        }

        try
        {
            return wep.getSession(player);
        }
        catch (Exception ex)
        {
            FLog.severe(ex);
            return null;
        }
    }

    private com.sk89q.worldedit.entity.Player getBukkitPlayer(Player player)
    {
        final WorldEditPlugin wep = getWorldEditPlugin();
        if (wep == null)
        {
            return null;
        }

        try
        {
            BukkitPlayer bp = wep.wrapPlayer(player);
            return (com.sk89q.worldedit.entity.Player) bp;
        }
        catch (Exception ex)
        {
            FLog.severe(ex);
            return null;
        }
    }
}
