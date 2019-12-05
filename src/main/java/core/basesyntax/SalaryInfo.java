package core.basesyntax;

import core.basesyntax.exception.IllegalDateParametersException;

public class SalaryInfo {
    /**
     * <p>Реализуйте метод getSalaryInfo(String[] names, String[] data,
     * String dateFrom, String dateTo)
     * вычисляющий зарплату сотрудников. На вход методу подаётся 2 массива и 2 даты,
     * определяющие период за который надо вычислить зарплату, первый массив содержит имена
     * сотрудников организации, второй массив информацию о рабочих часах и ставке. Формат данных
     * второго массива следующий: дата, имя сотрудника, количество отработанных часов,
     * ставка за 1 час. Метод должен вернуть отчёт за период, который передали в метод
     * (обе даты включительно) составленный по следующей форме: Отчёт за период
     * #дата_1# - #дата_2# Имя сотрудника - сумма заработанных средств за этот период
     * Создать класс-ошибку IllegalDateParametersException и сделать так, чтобы
     * метод getSalaryInfo выбрасывал IllegalDateParametersException,
     * если dateFrom > dateTo, с сообщнием "Wrong parameters"</p>
     *
     * <p>Пример ввода: date from = 01.04.2019 date to = 30.04.2019</p>
     *
     * <p>names:
     * Сергей
     * Андрей
     * София</p>
     *
     * <p>data:
     * 26.04.2019 Сергей 60 50
     * 26.04.2019 Андрей 3 200
     * 26.04.2019 Сергей 7 100
     * 26.04.2019 София 9 100
     * 26.04.2019 Сергей 11 50
     * 26.04.2019 Андрей 3 200
     * 26.04.2019 Сергей 7 100
     * 26.04.2019 София 9 100
     * 26.04.2019 Сергей 11 50</p>
     *
     * <p>Пример вывода:
     * Отчёт за период 01.04.2019  - 30.04.2019
     * Сергей - 1550
     * Андрей - 600
     * София - 900</p>
     */
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws IllegalDateParametersException {

        int dateFromP = convertDate(dateFrom);
        int dateToP = convertDate(dateTo);
        if (dateFromP > dateToP) {
            throw new IllegalDateParametersException("Wrong parameters");
        }

        int[] totalSalary = new int[names.length];

        for (int i = 0; i < data.length; i++) {
            String[] dataLineArr = data[i].split(" ");
            if (convertDate(dataLineArr[0]) >= dateFromP
                    && convertDate(dataLineArr[0]) <= dateToP) {
                for (int j = 0; j < names.length; j++) {
                    if (dataLineArr[1].equals(names[j])) {
                        totalSalary[j] += Integer.parseInt(dataLineArr[2])
                                * Integer.parseInt(dataLineArr[3]);
                    }
                }
            }
        }

        StringBuilder result = new StringBuilder("Отчёт за период ")
                .append(dateFrom).append(" - ").append(dateTo).append("\n");
        for (int i = 0; i < names.length; i++) {
            result.append(names[i]).append(" - ").append(totalSalary[i]).append("\n");
        }
        return result.toString();
    }

    private static int convertDate(String data) {
        String[] arr = data.split("[^0-9]+");
        String[] arrReverse = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            arrReverse[i] = arr[arr.length - 1 - i];
        }
        return Integer.valueOf(String.join("", arrReverse));
    }
}
