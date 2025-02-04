import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

public class Bot extends TelegramLongPollingBot {
    public InlineKeyboardButton sendForStartTest = InlineKeyboardButton.builder()
            .text("Нажмите для начала теста!")
            .callbackData("start test")
            .build();

    public InlineKeyboardMarkup keyboardForSendStartTest = InlineKeyboardMarkup.builder()
            .keyboardRow(List.of(sendForStartTest))
            .build();

    //Кнопки для ответа на 1 вопрос
    public InlineKeyboardButton dateCreateJava1 = InlineKeyboardButton.builder()
            .text("1994")
            .callbackData("1994")
            .build();

    public InlineKeyboardButton dateCreateJava2 = InlineKeyboardButton.builder()
            .text("1995")
            .callbackData("1995")
            .build();

    public InlineKeyboardButton dateCreateJava3 = InlineKeyboardButton.builder()
            .text("2000")
            .callbackData("2000")
            .build();

    public InlineKeyboardButton dateCreateJava4 = InlineKeyboardButton.builder()
            .text("2005")
            .callbackData("2005")
            .build();

    public InlineKeyboardMarkup keyboardResponseForFirstQuestion = InlineKeyboardMarkup.builder()
            .keyboardRow(List.of(dateCreateJava1))
            .keyboardRow(List.of(dateCreateJava2))
            .keyboardRow(List.of(dateCreateJava3))
            .keyboardRow(List.of(dateCreateJava4))
            .build();

    //Кнопки для ответа на 2 вопрос
    public InlineKeyboardButton nothing = InlineKeyboardButton.builder()
            .text("Ничем")
            .callbackData("ничем")
            .build();

    public InlineKeyboardButton objectBigClassSmallLetter = InlineKeyboardButton.builder()
            .text("Объекты пишутся с большой буквы, а классы нет")
            .callbackData("объекты большой, а классы нет")
            .build();

    public InlineKeyboardButton classPatternObjectCopy = InlineKeyboardButton.builder()
            .text("Классы являются шаблонами для создания объектов")
            .callbackData("класс - шаблон, объект - экземпляр")
            .build();

    public InlineKeyboardButton responseFalse = InlineKeyboardButton.builder()
            .text("Варианты ответов неверные")
            .callbackData("неверные ответы")
            .build();

    public InlineKeyboardMarkup keyboardResponseForSecondQuestion = InlineKeyboardMarkup.builder()
            .keyboardRow(List.of(nothing))
            .keyboardRow(List.of(objectBigClassSmallLetter))
            .keyboardRow(List.of(classPatternObjectCopy))
            .keyboardRow(List.of(responseFalse))
            .build();

    @Override
    public String getBotUsername() {
        return "@ogar_unique_tg_bot";
    }

    @Override
    public String getBotToken() {
        return "7598482212:AAFTyNhjaXL9XNvcx6hkmW5SpSQUgd2afLI";
    }

    @Override
    public void onUpdateReceived(Update update) {
        buttonTab(update);
        sendMenu(update.getMessage().getText(), update);
    }

    public void sendMenu(String messageText, Update update) {
        if (messageText.equals("/start_test")) {
            Long who = update.getMessage().getFrom().getId();
            String txt = "Используйте кнопку ниже, чтобы начать тест!";
            InlineKeyboardMarkup keyboard = keyboardForSendStartTest;

            SendMessage sendMessage = SendMessage.builder()
                    .chatId(who.toString())
                    .text(txt)
                    .replyMarkup(keyboard)
                    .build();

            try {
                execute(sendMessage);
            } catch (Exception ex) {
                ex.getMessage();
            }
        }
    }

    public void buttonTab(Update update) {
        if (update.hasCallbackQuery()) {
            String chatId = update.getCallbackQuery().getMessage().getChatId().toString();
            int idMessage = update.getCallbackQuery().getMessage().getMessageId();
            String dataCallback = update.getCallbackQuery().getData();
//            String queryId = update.getCallbackQuery().getId();

            EditMessageText editMessageText = EditMessageText.builder()
                    .chatId(chatId)
                    .messageId(idMessage)
                    .text("")
                    .build();

            EditMessageReplyMarkup editMessageReplyMarkup = EditMessageReplyMarkup.builder()
                    .chatId(chatId.toString())
                    .messageId(idMessage)
                    .build();


            if (dataCallback.equals("start test")) {
                editMessageText.setText("Когда основали ЯП Java?");
                editMessageReplyMarkup.setReplyMarkup(keyboardResponseForFirstQuestion);
            } else if (dataCallback.equals("1994")) {
                editMessageText.setText("Чем объекты отличаются от классов?");
                editMessageReplyMarkup.setReplyMarkup(keyboardResponseForSecondQuestion);
            } else if (dataCallback.equals("1995")) {
                editMessageText.setText("Чем объекты отличаются от классов?");
                editMessageReplyMarkup.setReplyMarkup(keyboardResponseForSecondQuestion);
            } else if (dataCallback.equals("2000")) {
                editMessageText.setText("Чем объекты отличаются от классов?");
                editMessageReplyMarkup.setReplyMarkup(keyboardResponseForSecondQuestion);
            } else if (dataCallback.equals("2005")) {
                editMessageText.setText("Чем объекты отличаются от классов?");
                editMessageReplyMarkup.setReplyMarkup(keyboardResponseForSecondQuestion);
            }

//            AnswerCallbackQuery answerCallbackQuery = AnswerCallbackQuery.builder()
//                    .callbackQueryId(queryId)
//                    .build();

            try {
//                execute(answerCallbackQuery);
                execute(editMessageText);
                execute(editMessageReplyMarkup);
            } catch (Exception ex) {
                ex.getMessage();
            }
        }
    }
}
