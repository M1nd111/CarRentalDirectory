package spring.ws.carrentaldirectoryweb.sd.hashTable.hash.functions;

public class FunctionMiddleOfSquare {
    public static Integer getKey(String str, int m)
    {
            // Преобразуем строку в массив символов
            char[] chars = str.toCharArray();

            // Инициализируем переменную для хранения результата
            Integer key = 0;

            // Вычисляем сумму кодов символов строки
            for (char ch : chars) {
                key += ch;
            }

            key *= key;

            String key_str = String.valueOf(key);

            if(key_str.length() <= 2 )
                return Integer.valueOf(key_str.substring(1));

            key_str = key_str.substring(1, key_str.length()-1);
            key = Integer.parseInt(key_str);

            while(key > m){
                key = key % m;
            }

            return key;
    }

}
