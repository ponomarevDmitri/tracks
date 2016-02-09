package ru.analteam.gtracks.model.dto.quiz;

/**
 * Created by dima-pc on 06.12.2015.
 */
public class Quiz {
    private QuizTheme theme;;
    private QuizUser author;

    public QuizTheme getTheme() {
        return theme;
    }

    public void setTheme(QuizTheme theme) {
        this.theme = theme;
    }

    public QuizUser getAuthor() {
        return author;
    }

    public void setAuthor(QuizUser author) {
        this.author = author;
    }
}
