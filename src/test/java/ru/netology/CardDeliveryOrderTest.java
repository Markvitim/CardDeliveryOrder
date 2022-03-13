package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryOrderTest {

    @Test
    public void shouldSendForm() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $("[placeholder=\"Город\"].input__control").setValue("Москва");
        $("[placeholder=\"Дата встречи\"].input__control").doubleClick();
        $("[placeholder=\"Дата встречи\"].input__control").sendKeys("17.03.2022");
        $("[name=\"name\"].input__control").setValue("Иванов");
        $("[name=\"phone\"].input__control").setValue("+79334568598");
        $(".checkbox__box").click();
        $(".button__text").click();
        $(withText("Успешно!")).shouldBe(Condition.appear, Duration.ofMillis(14999));
    }

    @Test
    public void shouldSendFormUsePopup() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $("[placeholder=\"Город\"].input__control").setValue("Мо");
        $$(".menu-item").find(Condition.text("Москва")).click();
        $(".input__icon").click();
        $("[data-day=\"1647723600000\"].calendar__day").click();
        $("[name=\"name\"].input__control").setValue("Иванов");
        $("[name=\"phone\"].input__control").setValue("+79334568598");
        $(".checkbox__box").click();
        $(".button__text").click();
        $(withText("Успешно!")).shouldBe(Condition.appear, Duration.ofMillis(14999));
    }
}
