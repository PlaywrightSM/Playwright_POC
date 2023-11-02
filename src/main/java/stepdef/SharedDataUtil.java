package stepdef;
import io.cucumber.java.Scenario;

public class SharedDataUtil {


    private static Scenario scenario;

    public static Scenario getScenario(){
        return scenario;
    }

    public static void setScenario(Scenario scenario){
        SharedDataUtil.scenario = scenario;
    }
}
