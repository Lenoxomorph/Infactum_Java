package com.infactum.bot;

import discord4j.core.*;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

public class TestingGrounds {
    private static final Map<String, Command> commands = new HashMap<>();

    interface Command {
        Mono<Void> execute(MessageCreateEvent event);
    }

    public static void main(String[] args) {
        GatewayDiscordClient client = DiscordClientBuilder.create("NzIwNzcyNjI1MzYxNTM1MDE4.XuK10Q._PFgtbe3Q3Hwoqv6ceU_OGG5ZoQ").build().login().block();
        assert client != null;
        client.getEventDispatcher().on(ReadyEvent.class)
                .subscribe(event -> {
                    User self = event.getSelf();
                    System.out.printf("Logged in as %s#%s%n", self.getUsername(), self.getDiscriminator());
                });
        client.getEventDispatcher().on(MessageCreateEvent.class)
                .map(MessageCreateEvent::getMessage)
                .filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false))
                .filter(message -> message.getContent().equalsIgnoreCase("!ping"))
                .flatMap(Message::getChannel)
                .flatMap(channel -> channel.createMessage("Pong!"))
                .subscribe();
        client.onDisconnect().block();
    }
}