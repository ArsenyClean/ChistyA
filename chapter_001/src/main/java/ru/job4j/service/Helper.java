package ru.job4j.service;

/**
 * Helper class реализует методы с одинаковым функционалом для UserStore и RoleStore.
 * @author Chisty Arseny
 * @since 28.02.2018
 * @param <T> может быть User или Role
 */
class Helper<T extends Base> {

    /**
     * seracher проводит поиск запрашиваемого элемента
     * @param store присылаем наш массив
     * @param id и искомый элемент
     * @return true если элемент найден, false если элемент не найден
     */
    int searcher(SimpleArray<T> store, String id) {
        int i = 0;
        while (i < store.index) {
            if (store.objects[i].getId().equals(id)) {
                i++;
                return i - 1;
            }
            i++;
        }
        return -1;
    }

    /**
     * shifter сдвигает исходный массив при удалении элемента
     * @param store присылаем наш массив
     * @param index и индекс искомого элемента
     * @return true, если сдвиг произведен false, если сдвиг не произведен (свиг не будет
     * произведен в случае, если удален последний элемент массива
     */
    boolean shifter(SimpleArray<T> store, int index) {
        boolean doneFlag = false;
        int i = index;
        while (i < store.index - 1) {
            store.objects[i] = store.objects[i + 1];
            doneFlag = true;
            i++;
        }
        store.objects[i + 1] = null;
        return doneFlag;
    }

    /**
     * toString переводит исходный массив в строку
     * @param store присылает обрабатываемый масси
     * @return строковый эквивален исходного массива
     */
    String toString(SimpleArray<T> store) {
        String result;
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < store.index; i++) {
            result = "[" + i + "]=" + store.objects[i].getId() + " ";
            buffer.append(result);
        }
        result = buffer.toString();
        return result;
    }
}