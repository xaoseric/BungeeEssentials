/*
 * Copyright (c) 2015 David Shen
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package de.albionco.gssentials.command.admin;

import de.albionco.gssentials.BungeeEssentials;
import de.albionco.gssentials.utils.Dictionary;
import de.albionco.gssentials.utils.Messenger;
import de.albionco.gssentials.utils.Permissions;
import de.albionco.gssentials.command.ServerSpecificCommand;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;

/**
 * Created by David on 4/28/2015.
 *
 * @author David Shen
 */
@SuppressWarnings("deprecation")
public class CSpyCommand extends ServerSpecificCommand {
    public CSpyCommand() {
        super(BungeeEssentials.CSpy_MAIN, Permissions.Admin.SPY_COMMAND, BungeeEssentials.CSpy_ALIAS);
    }

    @Override
    public void run(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) sender;
            if (args != null && args.length == 1) {
                if (args[0].equals("on")) {
                    Messenger.enableCSpy(player);
                    player.sendMessage(Dictionary.format(Dictionary.CSPY_ENABLED));
                } else if (args[0].equals("off")) {
                    Messenger.disableCSpy(player);
                    player.sendMessage(Dictionary.format(Dictionary.CSPY_DISABLED));
                }
            } else {
                if (Messenger.toggleCSpy(player)) {
                    player.sendMessage(Dictionary.format(Dictionary.CSPY_ENABLED));
                } else {
                    player.sendMessage(Dictionary.format(Dictionary.CSPY_DISABLED));
                }
            }
        } else {
            sender.sendMessage(Dictionary.colour("&cConsole may not toggle command spy"));
        }
    }
}