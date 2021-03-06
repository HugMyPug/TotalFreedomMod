package me.totalfreedom.totalfreedommod.command;

import me.totalfreedom.totalfreedommod.rank.Rank;
import me.totalfreedom.totalfreedommod.util.FUtil;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

@CommandPermissions(level = Rank.SUPER_ADMIN, source = SourceType.BOTH, blockHostConsole = true)
@CommandParameters(description = "Send a command as the specified player.", usage = "/<command> <fromname> <outcommand>")
public class Command_gcmd extends FreedomCommand
{

    @Override
    public boolean run(CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (args.length < 2)
        {
            return false;
        }

        final Player player = getPlayer(args[0]);

        if (player == null)
        {
            sender.sendMessage(FreedomCommand.PLAYER_NOT_FOUND);
            return true;
        }

        if (!FUtil.isExecutive(sender.getName()) && plugin.al.isAdmin(player))
        {
            msg("Only Executives may use this command on admins", ChatColor.RED);
            return true;
        }

        final String outCommand = StringUtils.join(args, " ", 1, args.length);

        if (plugin.cb.isCommandBlocked(outCommand, sender))
        {
            return true;
        }

        try
        {
            msg("Sending command as " + player.getName() + ": " + outCommand);
            if (server.dispatchCommand(player, outCommand))
            {
                msg("Command sent.");
            }
            else
            {
                msg("Unknown error sending command.");
            }
        }
        catch (Throwable ex)
        {
            msg("Error sending command: " + ex.getMessage());
        }

        return true;
    }
}
