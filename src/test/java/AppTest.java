import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
      goTo("http://localhost:4567/");
      assertThat(pageSource()).contains("Enter your allergy score");
  }
  @Test
  public void submitTest() {
      goTo("http://localhost:4567/");
      fill("#score").with("48");
      submit(".btn");
      assertThat(pageSource()).contains("chocolate");
  }

  @Test
  public void checkAllergies_catAllergies_returnCatAllergies() {
    App testApp = new App();
    ArrayList<String> testArrayList = new ArrayList<String>();
    testArrayList.add("cats");
    assertEquals(testArrayList, testApp.checkAllergies(128));
  }
  @Test
  public void checkAllergies_allAllergies_returnallAllergies() {
    App testApp = new App();
    ArrayList<String> testArrayList = new ArrayList<String>();
    testArrayList.add("cats");
    testArrayList.add("pollen");
    testArrayList.add("chocolate");
    testArrayList.add("tomatoes");
    testArrayList.add("strawberries");
    testArrayList.add("shellfish");
    testArrayList.add("peanuts");
    testArrayList.add("eggs");
    assertEquals(testArrayList, testApp.checkAllergies(255));
  }
}
