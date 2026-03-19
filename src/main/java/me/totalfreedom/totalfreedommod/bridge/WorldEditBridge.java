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

<<<<<<< HEAD
<<<<<<< HEAD
    //
    private WorldEditPlugin worldeditPlugin = null;
=======
=======
>>>>>>> f9a42f04 (Fix WorldEdit and WorldGuard compatibility for 1.21.x)
    private WorldEditPlugin worldedit = null;
>>>>>>> f9a42f04 (Fix WorldEdit and WorldGuard compatibility for 1.21.x)

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
<<<<<<< HEAD
<<<<<<< HEAD
    }

    public WorldEditPlugin getWorldEditPlugin()
    {
        if (worldeditPlugin == null)
        {
            try
            {
                Plugin we = server.getPluginManager().getPlugin("WorldEdit");
                if (we != null)
                {
                    if (we instanceof WorldEditPlugin)
                    {
                        worldeditPlugin = (WorldEditPlugin)we;
                    }
                }
            }
            catch (Exception ex)
            {
                FLog.severe(ex);
            }
        }

        return worldeditPlugin;
=======
>>>>>>> f9a42f04 (Fix WorldEdit and WorldGuard compatibility for 1.21.x)
=======
>>>>>>> f9a42f04 (Fix WorldEdit and WorldGuard compatibility for 1.21.x)
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
<<<<<<< HEAD
                {
                    for (int i = 0; i < count; i++)
                    {
<<<<<<< HEAD
                        com.sk89q.worldedit.entity.Player fuckyou = (com.sk89q.worldedit.entity.Player)bukkitPlayer;
                        session.undo(session.getBlockBag(fuckyou), fuckyou);
=======
                        session.undo(session.getBlockBag(wePlayer), wePlayer);
>>>>>>> f9a42f04 (Fix WorldEdit and WorldGuard compatibility for 1.21.x)
                    }
                }
            }
        }
        catch (Exception ex)
        {
            FLog.severe(ex);
        }
    }

<<<<<<< HEAD
    public void redo(Player player, int count)
    {
        try
        {
            LocalSession session = getPlayerSession(player);
            if (session != null)
            {
                final BukkitPlayer bukkitPlayer = getBukkitPlayer(player);
                if (bukkitPlayer != null)
                {
                    for (int i = 0; i < count; i++)
                    {
                        com.sk89q.worldedit.entity.Player fuckyou = (com.sk89q.worldedit.entity.Player)bukkitPlayer;
                        session.redo(session.getBlockBag(fuckyou), fuckyou);
=======
                {
                    for (int i = 0; i < count; i++)
                    {
                        session.undo(session.getBlockBag(wePlayer), wePlayer);
>>>>>>> f9a42f04 (Fix WorldEdit and WorldGuard compatibility for 1.21.x)
                    }
                }
            }
        }
        catch (Exception ex)
        {
            FLog.severe(ex);
        }
    }

<<<<<<< HEAD
=======
>>>>>>> f9a42f04 (Fix WorldEdit and WorldGuard compatibility for 1.21.x)
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

    public int getDefaultLimit()
=======
    public void setLimit(Player player, int limit)
>>>>>>> f9a42f04 (Fix WorldEdit and WorldGuard compatibility for 1.21.x)
    {
        final WorldEditPlugin wep = getWorldEditPlugin();
        if (wep == null)
        {
            return 0;
        }

        return wep.getLocalConfiguration().defaultChangeLimit;

    }

    public int getMaxLimit()
    {
        final WorldEditPlugin wep = getWorldEditPlugin();
        if (wep == null)
        {
            return 0;
        }
    }

<<<<<<< HEAD
        return wep.getLocalConfiguration().maxChangeLimit;

=======
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
>>>>>>> f9a42f04 (Fix WorldEdit and WorldGuard compatibility for 1.21.x)
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
