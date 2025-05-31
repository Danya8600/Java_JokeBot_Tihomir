package ru.tihomirov.java_bot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.tihomirov.java_bot.model.Jokes;
import ru.tihomirov.java_bot.model.JokeCall;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class TelegramUpdateListener implements UpdatesListener {

    private final JokesService jokesService;
    private final JokeCallService jokeCallService;
    private final TelegramBot telegramBot;

    public TelegramUpdateListener(JokesService jokesService, JokeCallService jokeCallService, TelegramBot telegramBot) {
        this.jokesService = jokesService;
        this.jokeCallService = jokeCallService;
        this.telegramBot = telegramBot;
    }

    @Override
    public int process(List<Update> list) {
        for (Update update : list) {
            if (update.message() != null && update.message().text() != null) {
                String messageText = update.message().text();
                Long chatId = update.message().chat().id();

                if (messageText.equals("/start")) {
                    telegramBot.execute(new SendMessage(chatId,
                            "Доступные команды:\n/jokes - случайная шутка\n/topjokes - топ-5 популярных анекдотов"));
                }

                if (messageText.equals("/jokes")) {
                    Pageable pageable = PageRequest.of(0, 100);
                    Page<Jokes> jokesPage = jokesService.getAllJokes(null, pageable);
                    List<Jokes> jokes = jokesPage.getContent();

                    if (!jokes.isEmpty()) {
                        Jokes joke = jokes.get((int) (Math.random() * jokes.size()));
                        String text = '"' + joke.getTitle() + '"' + '.' + "\n" + joke.getContent();
                        telegramBot.execute(new SendMessage(chatId, text));

                        JokeCall jokeCall = new JokeCall();
                        jokeCall.setJokeId(joke.getId());
                        jokeCall.setUserId(chatId);
                        jokeCall.setCallTime(LocalDateTime.now());

                        jokeCallService.saveJokeCall(jokeCall);
                    }
                }

                if (messageText.equals("/topjokes")) {
                    List<Long> topJokeIds = jokeCallService.getTopJokeIds(5);

                    if (topJokeIds.isEmpty()) {
                        telegramBot.execute(new SendMessage(chatId, "Топ пока пуст. Попробуйте позже!"));
                    } else {
                        StringBuilder sb = new StringBuilder("Топ-5 популярных анекдотов:\n");

                        int rank = 1;
                        for (Long jokeId : topJokeIds) {
                                Jokes joke = jokesService.getJokesById(jokeId);
                                sb.append(rank).append(". ").append(joke.getTitle()).append("\n");
                                rank++;
                        }

                        telegramBot.execute(new SendMessage(chatId, sb.toString()));
                    }
                }
            }
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onReady() {
        telegramBot.setUpdatesListener(this);
    }
}
