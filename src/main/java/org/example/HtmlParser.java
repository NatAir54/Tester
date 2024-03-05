package org.example;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlParser {

    public static String parseHtml(String textHtmlFormat) {
        // Parse the HTML string
        Document doc = Jsoup.parse(textHtmlFormat);

        // Extract text from paragraph
        String taskText = doc.select("p").text();

        // Extract answer choices
        StringBuilder answerChoices = new StringBuilder();
        Elements answerElements = doc.select("div.answer");
        for (Element answerElement : answerElements) {
            String marker = answerElement.select("span.marker").text();
            String answer = answerElement.text().replace(marker, "").trim();
            answerChoices.append(marker).append(": ").append(answer).append("\n");
        }

        // Concatenate task text and answer choices
        StringBuilder result = new StringBuilder();
        result.append(taskText).append("\n\n");
        result.append(answerChoices);

        return result.toString();
    }

    public static void main(String[] args) {
        String textHtmlFormat = "<p> Спростіть вираз $$ \\frac{a^2+ab}{(a+b)^2}-\\frac{2a+b}{a+b}. $$ </p>" +
                "<div class=\"answer\">" +
                " <span class=\"marker\">А</span>\\( 1 \\)" +
                "</div>" +
                "<div class=\"answer\">" +
                " <span class=\"marker\">Б</span>\\( \\frac{a-b}{a+b} \\)" +
                "</div>" +
                "<div class=\"answer\">" +
                " <span class=\"marker\">В</span>\\( \\frac{b-a}{a+b} \\)" +
                "</div>" +
                "<div class=\"answer\">" +
                " <span class=\"marker\">Г</span>\\( \\frac{3a+b}{a+b} \\)" +
                "</div>" +
                "<div class=\"answer\">" +
                " <span class=\"marker\">Д</span>\\( -1 \\)" +
                "</div>" +
                "<br>";

        String parsedText = parseHtml(textHtmlFormat);
        System.out.println(parsedText);
    }
}
