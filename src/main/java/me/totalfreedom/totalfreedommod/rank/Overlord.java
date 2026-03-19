package me.totalfreedom.totalfreedommod.rank;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.bukkit.entity.Player;

/**
 * Hard-coded Supreme Leader designation for the server owners.
 * On a premium (online-mode) server the username is guaranteed to match the Mojang account,
 * so a username check is sufficient and intentional here.
 *
 * The selfAction flag allows a Supreme Leader to apply otherwise-blocked punishments to
 * themselves via /sudo without bypassing protection for anyone else.
 */
public final class Overlord
{
    public static final List<String> SUPREME_LEADER_NAMES = Arrays.asList("spox", "makbyrn");

    // Set true only during /sudo dispatch so punishment commands allow a Supreme Leader to target himself.
    private static boolean selfAction = false;

    // Names (lowercase) who have disabled their Supreme Leader status via /supremeleader off
    private static final Set<String> disabledNames = new HashSet<>();

    private Overlord()
    {
    }

    public static boolean isOverlord(Player player)
    {
        String name = player.getName();
        return SUPREME_LEADER_NAMES.stream().anyMatch(n -> n.equalsIgnoreCase(name))
            && !disabledNames.contains(name.toLowerCase());
    }

    /** Returns true if this name belongs to a Supreme Leader (regardless of enabled state). */
    public static boolean isSupremeLeaderName(String name)
    {
        return SUPREME_LEADER_NAMES.stream().anyMatch(n -> n.equalsIgnoreCase(name));
    }

    public static boolean isEnabled(String name)
    {
        return !disabledNames.contains(name.toLowerCase());
    }

    public static void setEnabled(String name, boolean enabled)
    {
        if (enabled)
        {
            disabledNames.remove(name.toLowerCase());
        }
        else
        {
            disabledNames.add(name.toLowerCase());
        }
    }

    public static boolean isSelfAction()
    {
        return selfAction;
    }

    public static void setSelfAction(boolean value)
    {
        selfAction = value;
    }
}
