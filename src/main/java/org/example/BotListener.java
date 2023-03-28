package org.example;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;

public class BotListener extends ListenerAdapter {
    HashSet<User> userList=new HashSet<>();
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if(!event.getAuthor().isBot() && userList.contains(event.getAuthor())) {
            String messageSent = event.getMessage().getContentRaw();
            event.getChannel().sendMessage(messageSent).queue();
        }
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if(event.getName().equals("repeat")) {
            userList.add(event.getUser());
            event.deferReply().queue();
            event.getHook().sendMessage("/repeat").queue();
        }
        else if(event.getName().equals("kill")) {
            event.deferReply().queue();
            if(userList.contains(event.getUser())) {
                userList.remove(event.getUser());
                event.getHook().sendMessage("AAHAHAHHH I am dead now AAHHAHAHAHA").setEphemeral(true).queue();
            }
            else {
                event.getHook().sendMessage("Type /repeat first").queue();
            }
        }
    }
}
