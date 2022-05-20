package com.company;

import java.util.Locale;
import java.util.regex.*;
import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.print("Введите действие (зашифровать, расшифровать, выход): ");
            String input = in.nextLine().toLowerCase(Locale.ROOT);
            if (input.equals("расшифровать")) {
                StringBuilder readText = new StringBuilder();
                String line;
        /*
        Считываем стихотворение из текстового файла.
        */
                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("text.txt")));
                    while ((line = br.readLine()) != null) {
                        readText.append(line).append(" ");
                    }
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }

        /*
        В полученном тексте верхние регистры преобразовываем в нижние.
        Создаём символьный массив этого текста.
        */
                String lowerText = readText.toString().toLowerCase(Locale.ROOT);
                char[] characterText = lowerText.toCharArray();

                StringBuilder result = new StringBuilder(); // Переменная, в которую буду помещаться значения, соответствующие номеру строки и номеру символа.

        /*
        Считываем номера строк и номера символов и текстового файла.
        */

                StringBuilder lineAndCharacterNumbers = new StringBuilder();
                try {
                    BufferedReader br1 = new BufferedReader(new FileReader("numbers.txt"));
                        while ((line = br1.readLine()) != null) {
                            lineAndCharacterNumbers.append(line);
                    }
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }

        /*
        Создаём 2 регулярны выражения.
        Первое регулярное выражения для извлечения цифр из скобочек.
        Второе регулярное выражения для извлечения значения после ";".
        */
                String regularForFindNumbers = "\\d+(\s*);(\s*)\\d+";
                String regularForNumberOfSymbol = ";\\d+";
                Pattern patternForNumbers = Pattern.compile(regularForFindNumbers);
                Pattern patternForSymbol = Pattern.compile(regularForNumberOfSymbol);
                Matcher matcherForFindNumbers = patternForNumbers.matcher(lineAndCharacterNumbers.toString());

        /*
        Находим номера строк и номера символов.
        Извлекаем второе число, вычитаем из него единицу и заносим значение в переменную index.
        По индексу ищем соответствующую букву из текста.
        К переменной result добавляем найденную букву.
        */
                while (matcherForFindNumbers.find()) {
                    String t = matcherForFindNumbers.group().replaceAll(" ", "");
                    Matcher matcherForSymbolNumber = patternForSymbol.matcher(t);
                    while (matcherForSymbolNumber.find()) {
                        String numberOfSymbol = matcherForSymbolNumber.group().replaceAll(";", "");
                        int index = Integer.parseInt(numberOfSymbol) - 1;
                        result.append(characterText[index]);
                    }
                }

                if(result.isEmpty()){
                    System.out.println("Файл пуст.");
                }
                else{
                    System.out.println(result); // Выводим полученное слово.
                }
            } else if (input.equals("зашифровать")) {
                try {
                    FileReader fr = new FileReader("text.txt");
                    String x = "";
                    int k = 0;
                    while ((k = fr.read()) != -1) {
                        x += (char) k;
                    }
                    String text = x.toLowerCase(Locale.ROOT);
                    String forIndex = text.replace("\r\n", " ");

                    FileWriter fileWriter = new FileWriter("output.txt");
                    fileWriter.write(text);
                    fileWriter.flush();

                    System.out.print("Введите слово: ");
                    String word = in.nextLine().toLowerCase(Locale.ROOT);
                    char[] letter = word.toLowerCase(Locale.ROOT).toCharArray();

                    String result = "";


                    char[] word1 = word.toCharArray();
                    for (int i = 0; i < word.length(); i++) {
                        String kk = word1[i] + "";
                        String first = (int) numberOfString(kk, text) + "";
                        int index = forIndex.indexOf(letter[i]);
                        String second = index + 1 + "";
                        result += "(" + first + " ; " + second + ") ";
                        if(first.equals("-1")){
                            System.out.println("Символы не найдены. В файл ничего не записалось.");
                            result = "";
                            break;
                        }
                    }
                    FileWriter fw = new FileWriter("numbers.txt");
                    fw.write(result);
                    fw.flush();

                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            else if(input.equals("выход")){
                break;
            }
            else{
                System.out.println("Я не знаю такой команды.");
            }
        }
    }

    static int numberOfString(String str, String word) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("output.txt"));
            String S = word;
            int i = 0;
            while ((S = reader.readLine()) != null) {
                i++;
                if (S.contains(str)) {
                    return i;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
