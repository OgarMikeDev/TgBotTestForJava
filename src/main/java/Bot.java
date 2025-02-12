import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

public class Bot extends TelegramLongPollingBot {
    //Кнопка и клавиатура для запуска самого теста
    private InlineKeyboardButton sendForStartTest = InlineKeyboardButton.builder()
            .text("Нажмите для начала теста!")
            .callbackData("start test")
            .build();
    private InlineKeyboardMarkup keyboardForSendForStartTest = InlineKeyboardMarkup.builder()
            .keyboardRow(List.of(sendForStartTest))
            .build();

    //кнопки и клавиатура для ответов на 1-й вопрос
    private InlineKeyboardButton dateCreateJava1 = InlineKeyboardButton.builder()
            .text("1990")
            .callbackData("1990")
            .build();
    private InlineKeyboardButton dateCreateJava2 = InlineKeyboardButton.builder()
            .text("1995")
            .callbackData("1995")
            .build();
    private InlineKeyboardButton dateCreateJava3 = InlineKeyboardButton.builder()
            .text("2000")
            .callbackData("2000")
            .build();
    private InlineKeyboardButton dateCreateJava4 = InlineKeyboardButton.builder()
            .text("2005")
            .callbackData("2005")
            .build();
    private InlineKeyboardMarkup keyboardResponseForFirstQuestion = InlineKeyboardMarkup.builder()
            .keyboardRow(List.of(dateCreateJava1))
            .keyboardRow(List.of(dateCreateJava2))
            .keyboardRow(List.of(dateCreateJava3))
            .keyboardRow(List.of(dateCreateJava4))
            .build();

    //кнопки и клавиатура для ответов на 2-й вопрос
    private InlineKeyboardButton nothing = InlineKeyboardButton.builder()
            .text("Ничем")
            .callbackData("ничем")
            .build();
    private InlineKeyboardButton objectBigLetter = InlineKeyboardButton.builder()
            .text("Объекты пишутся с большой буквы, а классы нет")
            .callbackData("о с большой, к с маленькой")
            .build();
    private InlineKeyboardButton classPatternObjectsCopy = InlineKeyboardButton.builder()
            .text("Классы являются шаблонами для создания объектов")
            .callbackData("к - шаблон, о - экземпляр")
            .build();
    private InlineKeyboardButton responseFalse = InlineKeyboardButton.builder()
            .text("Нет правильного ответа")
            .callbackData("неверные ответы")
            .build();
    private InlineKeyboardMarkup keyboardResponseForSecondQuestion = InlineKeyboardMarkup.builder()
            .keyboardRow(List.of(nothing))
            .keyboardRow(List.of(objectBigLetter))
            .keyboardRow(List.of(classPatternObjectsCopy))
            .keyboardRow(List.of(responseFalse))
            .build();

    //кнопки и клавиатура для ответов на 3-й вопрос
    private InlineKeyboardButton modifierAccessAndAllInOnePlace = InlineKeyboardButton.builder()
            .text("В том модификаторах доступа и сокрытии логики в одном месте")
            .callbackData("модификаторы, всё в одном месте")
            .build();
    private InlineKeyboardButton usingObjects = InlineKeyboardButton.builder()
            .text("В использовании объектов")
            .callbackData("использование объектов")
            .build();
    private InlineKeyboardButton noSemantics = InlineKeyboardButton.builder()
            .text("Инкапсуляция - это придуманный термин, чтобы запутать")
            .callbackData("нет смысла")
            .build();
    private InlineKeyboardButton usingInHTML = InlineKeyboardButton.builder()
            .text("Этот термин используется в HTML")
            .callbackData("использование в html")
            .build();
    private InlineKeyboardMarkup keyboardResponseForThirdQuestion = InlineKeyboardMarkup.builder()
            .keyboardRow(List.of(modifierAccessAndAllInOnePlace))
            .keyboardRow(List.of(usingObjects))
            .keyboardRow(List.of(noSemantics))
            .keyboardRow(List.of(usingInHTML))
            .build();

    //кнопки и клавиатура для ответов на 4-й вопрос
    private InlineKeyboardButton noUsingImmutable = InlineKeyboardButton.builder()
            .text("Не нужна")
            .callbackData("не нужна")
            .build();
    private InlineKeyboardButton difficultCode = InlineKeyboardButton.builder()
            .text("Для усложнения кода")
            .callbackData("сложность кода")
            .build();
    private InlineKeyboardButton fastWorkCode = InlineKeyboardButton.builder()
            .text("Для быстрой работы кода")
            .callbackData("быстрая работа кода")
            .build();
    private InlineKeyboardButton defineConstant = InlineKeyboardButton.builder()
            .text("Определения константы - неизменяемых значений")
            .callbackData("определение константы")
            .build();
    private InlineKeyboardMarkup keyboardResponseForFourQuestion = InlineKeyboardMarkup.builder()
            .keyboardRow(List.of(noUsingImmutable))
            .keyboardRow(List.of(difficultCode))
            .keyboardRow(List.of(fastWorkCode))
            .keyboardRow(List.of(defineConstant))
            .build();

    //Счётчик для правильных ответов
    private int countTrueAnswers = 0;


    @Override
    public void onUpdateReceived(Update update) {
        buttonTab(update);
        sendMenu(update.getMessage().getText(), update);
    }

    public void sendMenu(String messageText, Update update) {
        if (messageText.equals("/start_test")) {
            Long senderId = update.getMessage().getFrom().getId();
            String txt = "Используйте кнопку ниже, чтобы начать тест!";
            InlineKeyboardMarkup keyboard = keyboardForSendForStartTest;

            SendMessage sendMessage = SendMessage.builder()
                    .chatId(senderId.toString())
                    .text(txt)
                    .replyMarkup(keyboard)
                    .build();

            try {
                execute(sendMessage);
            } catch (Exception exception) {
                exception.getMessage();
            }
        }
    }

    public void buttonTab(Update update) {
        if (update.hasCallbackQuery()) {
            String dataCallback = update.getCallbackQuery().getData();
            String chatId = update.getCallbackQuery().getMessage().getChatId().toString();
            Integer messageId = update.getCallbackQuery().getMessage().getMessageId();

            EditMessageText editMessageText = EditMessageText.builder()
                    .chatId(chatId)
                    .messageId(messageId)
                    .text("")
                    .build();

            EditMessageReplyMarkup editMessageReplyMarkup = EditMessageReplyMarkup.builder()
                    .chatId(chatId)
                    .messageId(messageId)
                    .build();

            //обработки кнопки для запуска самого теста
            if (dataCallback.equals("start test")) {
                editMessageText.setText("Когда основали ЯП Java?");
                editMessageReplyMarkup.setReplyMarkup(keyboardResponseForFirstQuestion);
            }

            //обработка ответов на 1-й вопрос
            else if (dataCallback.equals("1990")) {
                editMessageText.setText("Чем объекты отличаются от классов?");
                editMessageReplyMarkup.setReplyMarkup(keyboardResponseForSecondQuestion);
            } else if (dataCallback.equals("1995")) {
                countTrueAnswers += 1;
                editMessageText.setText("Чем объекты отличаются от классов?");
                editMessageReplyMarkup.setReplyMarkup(keyboardResponseForSecondQuestion);
            } else if (dataCallback.equals("2000")) {
                editMessageText.setText("Чем объекты отличаются от классов?");
                editMessageReplyMarkup.setReplyMarkup(keyboardResponseForSecondQuestion);
            } else if (dataCallback.equals("2005")) {
                editMessageText.setText("Чем объекты отличаются от классов?");
                editMessageReplyMarkup.setReplyMarkup(keyboardResponseForSecondQuestion);
            }

            //обработка ответов на 2-й вопрос
            else if (dataCallback.equals("ничем")) {
                editMessageText.setText("В чём проявляется сущность инкапсуляции?");
                editMessageReplyMarkup.setReplyMarkup(keyboardResponseForThirdQuestion);
            } else if (dataCallback.equals("о с большой, к с маленькой")) {
                editMessageText.setText("В чём проявляется сущность инкапсуляции?");
                editMessageReplyMarkup.setReplyMarkup(keyboardResponseForThirdQuestion);
            } else if (dataCallback.equals("к - шаблон, о - экземпляр")) {
                countTrueAnswers += 1;
                editMessageText.setText("В чём проявляется сущность инкапсуляции?");
                editMessageReplyMarkup.setReplyMarkup(keyboardResponseForThirdQuestion);
            } else if (dataCallback.equals("неверные ответы")) {
                editMessageText.setText("В чём проявляется сущность инкапсуляции?");
                editMessageReplyMarkup.setReplyMarkup(keyboardResponseForThirdQuestion);
            }

            //обработка ответов на 3-й вопрос
            else if (dataCallback.equals("модификаторы, всё в одном месте")) {
                countTrueAnswers += 1;
                editMessageText.setText("Зачем нужна иммутабельность в коде?");
                editMessageReplyMarkup.setReplyMarkup(keyboardResponseForFourQuestion);
            } else if (dataCallback.equals("использование объектов")) {
                editMessageText.setText("Зачем нужна иммутабельность в коде?");
                editMessageReplyMarkup.setReplyMarkup(keyboardResponseForFourQuestion);
            } else if (dataCallback.equals("нет смысла")) {
                editMessageText.setText("Зачем нужна иммутабельность в коде?");
                editMessageReplyMarkup.setReplyMarkup(keyboardResponseForFourQuestion);
            } else if (dataCallback.equals("использование в html")) {
                editMessageText.setText("Зачем нужна иммутабельность в коде?");
                editMessageReplyMarkup.setReplyMarkup(keyboardResponseForFourQuestion);
            }

            //обработка ответов на 4-й вопрос
            else if (dataCallback.equals("не нужна")) {
                editMessageText.setText(
                        "Тест успешно завершён и Вы ответили верно на " +
                                countTrueAnswers +
                                " вопросов[а]!");
            } else if (dataCallback.equals("сложность кода")) {
                editMessageText.setText(
                        "Тест успешно завершён и Вы ответили верно на " +
                                countTrueAnswers +
                                " вопросов[а]!");
            } else if (dataCallback.equals("быстрая работа кода")) {
                editMessageText.setText(
                        "Тест успешно завершён и Вы ответили верно на " +
                                countTrueAnswers +
                                " вопросов[а]!");
            } else if (dataCallback.equals("определение константы")) {
                countTrueAnswers += 1;
                editMessageText.setText(
                        "Тест успешно завершён и Вы ответили верно на " +
                                countTrueAnswers +
                                " вопросов[а]!");
            }

            try {
                execute(editMessageText);
                execute(editMessageReplyMarkup);
            } catch (Exception exception) {
                exception.getMessage();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "@ogar_control_test_tg_bot";
    }


    @Override
    public String getBotToken() {
        return "7577162083:AAHzqfhalHhS1YPy-AKuBYcJwSqkVdOhVN4";
    }


}
