package ru.task.utils;

/**
 * Created by mirak on 13.03.17.
 * Предназначен для хранения данных о текущем пользователе, выполняющем запрос
 */
public class UserContextHolder {

    private static final ThreadLocal<Data> data = new ThreadLocal<>();

    /**
     * Получает значение ThreadLocal-переменной. Если значение пустое, то инициализирует его.
     * @return Инициализированный объект Data
     */
    public static Data getData() {
        Data d = data.get();
        if (d == null) {
            d = new Data();
            data.set(d);
        }
        return d;
    }

    public static void clearContext(){
        data.remove();
    }

    /**
     * Информация о текущем пользователе
     */
    public static class Data{
        private String login;
        private Long customerId;

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public Long getCustomerId() {
            return customerId;
        }

        public void setCustomerId(Long customerId) {
            this.customerId = customerId;
        }
    }
}
