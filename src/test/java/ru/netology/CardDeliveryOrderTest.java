package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryOrderTest {

    public static String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    public void shouldSendFormPlus5Days() {
        String planingDate = generateDate(5);
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $("[placeholder=\"Город\"].input__control").setValue("Москва");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planingDate);
        $("[name=\"name\"].input__control").setValue("Иванов");
        $("[name=\"phone\"].input__control").setValue("+79334568598");
        $(".checkbox__box").click();
        $(".button__text").click();
        $(withText("Успешно!")).shouldBe(Condition.appear, Duration.ofSeconds(15));
        $("[class='notification__content']").shouldHave(Condition.text("Встреча успешно забронирована на "
                + planingDate), Duration.ofSeconds(15));
    }


    @Test
    public void shouldSendFormUsePopup() {
        String planingDate = generateDate(7);
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $("[placeholder=\"Город\"].input__control").setValue("Мо");
        $$(".menu-item").find(Condition.text("Москва")).click();
        $(".input__icon").click();
        $("[data-day=\"1647896400000\"].calendar__day").click();
        $("[name=\"name\"].input__control").setValue("Иванов");
        $("[name=\"phone\"].input__control").setValue("+79334568598");
        $(".checkbox__box").click();
        $(".button__text").click();
        $(withText("Успешно!")).shouldBe(Condition.appear, Duration.ofSeconds(15));
        $("[class='notification__content']").shouldHave(Condition.text("Встреча успешно забронирована на "
                + planingDate), Duration.ofSeconds(15));
    }

}
