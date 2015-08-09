/*
 * New BSD License (BSD-new)
 *
 * Copyright (c) 2015 Maxim Roncacé
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     - Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     - Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     - Neither the name of the copyright holder nor the names of its contributors
 *       may be used to endorse or promote products derived from this software
 *       without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.caseif.flint.spleef.command;

import static net.caseif.flint.spleef.Main.EM_COLOR;
import static net.caseif.flint.spleef.Main.ERROR_COLOR;
import static net.caseif.flint.spleef.Main.INFO_COLOR;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Handler for the arena creation command.
 *
 * @author Max Roncacé
 */
public class CreateArenaCommand {

    public static BiMap<UUID, Integer> WIZARDS = HashBiMap.create();
    public static BiMap<UUID, Object[]> WIZARD_INFO = HashBiMap.create();

    public static final int WIZARD_ID = 0;
    public static final int WIZARD_NAME = 1;
    public static final int WIZARD_FIRST_BOUND = 2;
    public static final int WIZARD_SECOND_BOUND = 3;
    public static final int WIZARD_SPAWN_POINT = 4;

    public static void handle(CommandSender sender, String[] args) {
        if (sender.hasPermission("fs.arena.create")) {
            if (sender instanceof Player) {
                if (!WIZARDS.containsKey(((Player) sender).getUniqueId())) {
                    WIZARDS.put(((Player) sender).getUniqueId(), 0);
                    WIZARD_INFO.put(((Player) sender).getUniqueId(), new Object[4]);
                    sender.sendMessage(INFO_COLOR + "[FlintSpleef] Welcome to the arena creation wizard! To start, "
                            + "please enter the ID for the new arena.");
                    sender.sendMessage(INFO_COLOR + "[FlintSpleef] Note: You may exit this wizard at any time by "
                            + "sending " + EM_COLOR + "cancel" + INFO_COLOR + " in the chat.");
                } else {
                    sender.sendMessage(ERROR_COLOR + "[FlintSpleef] You are already in the arena creation wizard");
                }
            } else {
                sender.sendMessage(ERROR_COLOR + "[FlintSpleef] You must be an in-game player to use this command");
            }
        } else {
            sender.sendMessage(ERROR_COLOR + "[FlintSpleef] You do not have permission to use this command");
        }
    }

}