package org.example;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class ParrotDiscordBot {

    Dotenv config;

    public static void main(String[] args) throws LoginException, InterruptedException {
        JDA bot = (JDA) JDABuilder.createDefault(
                Dotenv.configure().load().get("TOKEN"),
                        GatewayIntent.GUILD_MESSAGES,
                        GatewayIntent.MESSAGE_CONTENT,
                        GatewayIntent.GUILD_MEMBERS)
                .setActivity(Activity.playing("with your mom"))
                .addEventListeners(new BotListener())
                .build().awaitReady();
        Guild guildId=bot.getGuildById("1004447619545833553");
        if(guildId!=null) {
            guildId.upsertCommand("repeat", "Basically what the names say").queue();
            guildId.upsertCommand("kill", "Stop parrot from repeating after you").queue();
        }
    }

}
